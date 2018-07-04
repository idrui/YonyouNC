package nc.impl.pu.m21.action.rule.maintain;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.vo.pu.m21.entity.AggPayPlanVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @description
 *              采购订单生成付款计划时，根据对应采购订单交易类型属性“允许超订单付款”的选值，
 *              如果选值是不允许，则在付款计划保存时检查当前订单分拆的所有付款计划行的原币金额的合计不能大于当前订单的整单价税合计；
 *              如果选值是允许，则不作此检查。
 * @scene
 *        采购订单付款计划修改
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午3:37:22
 * @author luojw
 */

public class OverPayChkRule implements IRule<AggPayPlanVO> {

  @Override
  public void process(AggPayPlanVO[] vos) {
    Map<String, PoTransTypeVO> transtypeMap = this.getTranstypeMap(vos);
    StringBuilder sb = new StringBuilder();
    for (AggPayPlanVO vo : vos) {
      String vtrantypecode = vo.getHVO().getVtrantypecode();
      if (null == transtypeMap.get(vtrantypecode)) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004030_0", "04004030-0278", null, new String[] {
              vtrantypecode
            })/* 采购订单交易类型{0}扩展表没有数据，请检查！ */);
      }
      if (transtypeMap.get(vtrantypecode).getBoverpay().booleanValue()) {
        continue;
      }

      UFDouble overpaySum = UFDouble.ZERO_DBL;
      for (PayPlanVO payplanVO : vo.getPayPlanVO()) {
        overpaySum = MathTool.add(overpaySum, payplanVO.getNorigmny());
      }

      if (MathTool.compareTo(overpaySum, vo.getHVO().getNtotalorigmny()) > 0) {
        if (sb.length() > 0) {
          sb.append(", ");
        }
        sb.append(vo.getHVO().getVbillcode());
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004030_0", "04004030-0279", null, new String[] {
            sb.toString()
          })/* 订单{0}所有付款计划行的原币金额的合计大于当前订单的整单价税合计，请检查 */);
    }

  }

  private Map<String, PoTransTypeVO> getTranstypeMap(AggPayPlanVO[] vos) {
    Set<String> transTypeSet = new HashSet<String>();
    for (AggPayPlanVO vo : vos) {
      transTypeSet.add(vo.getHVO().getVtrantypecode());
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
