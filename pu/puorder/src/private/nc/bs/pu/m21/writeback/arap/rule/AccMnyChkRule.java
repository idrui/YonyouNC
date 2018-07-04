/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-28 下午04:55:34
 */
package nc.bs.pu.m21.writeback.arap.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.vo.pu.m21.entity.AggPayPlanVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @description
 *              采购订单金额检查
 * @scene
 *        付款单回写
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午4:08:27
 * @author luojw
 */
public class AccMnyChkRule implements IRule<PayPlanViewVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PayPlanViewVO[] views) {

    AggPayPlanVO[] orderVOs = PayPlanViewVO.getAggPayPlanVO(views);
    Map<String, PoTransTypeVO> transtypeMap = this.getTranstypeMap(orderVOs);
    StringBuilder sb = new StringBuilder();

    for (AggPayPlanVO orderVO : orderVOs) {
      PoTransTypeVO transtypeVO =
          transtypeMap.get(orderVO.getHVO().getVtrantypecode());
      UFBoolean boverpay = transtypeVO.getBoverpay();
      if (boverpay != null && boverpay.booleanValue()) {
        continue;
      }

      this.checkItemNorigtaxmny(orderVO, sb);
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }

  /**
   * 方法功能描述：检查表体累计付款金额
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVO
   * @param sb
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-28 下午06:34:17
   */
  private void checkItemNorigtaxmny(AggPayPlanVO orderVO, StringBuilder sb) {
    PayPlanVO[] itemVOs = orderVO.getPayPlanVO();
    StringBuilder builder = new StringBuilder();
    for (PayPlanVO itemVO : itemVOs) {
      UFDouble norigtaxmny = itemVO.getNorigmny();
      if (MathTool.absCompareTo(itemVO.getNaccumpayorgmny(), norigtaxmny) > 0) {
        builder.append(NCLangResOnserver.getInstance().getStrByID("4004030_0",
            "04004030-0248", null, new String[] {
              itemVO.getCrowno()
            })/* 第{0}行累计付款金额已经大于该行的原币价税合计，请检查！\n */);
      }
    }

    if (builder.length() > 0) {
      sb.append(NCLangResOnserver.getInstance().getStrByID("4004030_0",
          "04004030-0249", null, new String[] {
            orderVO.getHVO().getVbillcode(), builder.toString()
          })/* 订单{0}\n{1} */);
    }
  }

  /**
   * 方法功能描述：交易类型映射
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-30 下午07:40:55
   */
  private Map<String, PoTransTypeVO> getTranstypeMap(AggPayPlanVO[] orderVOs) {
    Set<String> transTypeSet = new HashSet<String>();
    for (AggPayPlanVO orderVO : orderVOs) {
      transTypeSet.add(orderVO.getHVO().getVtrantypecode());
    }

    IPoTransTypeQuery service =
        NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
    try {
      return service.queryAttrByTypes(transTypeSet.toArray(new String[0]),
          new String[] {
            PoTransTypeVO.BOVERPAY
          });
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return null;
  }
}
