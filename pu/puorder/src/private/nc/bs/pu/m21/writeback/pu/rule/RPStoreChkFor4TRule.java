/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-15 上午09:04:25
 */
package nc.bs.pu.m21.writeback.pu.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialIntoleranceService;
import nc.pubitf.pu.m21.ic.m45.IOrderWriteBackPara;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.exception.AskPriceException;
import nc.vo.pu.m21.pub.OrderVOUtil;
import nc.vo.pu.pub.constant.PUParaValue.ctrltype;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-15 上午09:04:25
 */
public class RPStoreChkFor4TRule implements IRule<OrderReceivePlanVO> {

  private IOrderWriteBackPara[] wbVos;

  public RPStoreChkFor4TRule(IOrderWriteBackPara[] wbVos) {
    this.wbVos = wbVos;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderReceivePlanVO[] vos) {
    ctrltype PO02 = PUSysParamUtil.getPO02(vos[0].getPk_org());
    Map<String, UFDouble> intoleranceMap = this.getIntoleranceMap(vos);
    Map<String, IOrderWriteBackPara> wbMap =
        OrderVOUtil.getInsance().createWBMap(this.wbVos);

    for (OrderReceivePlanVO vo : vos) {
      IOrderWriteBackPara wbVo = wbMap.get(vo.getPk_order_b());
      this.checkAccNumber(vo, wbVo, PO02, intoleranceMap);
    }
  }

  private void checkAccNumber(OrderReceivePlanVO vo, IOrderWriteBackPara wbVo,
      ctrltype PO02, Map<String, UFDouble> intoleranceMap) {

    try {
      UFDouble orderNum = vo.getNnum();
      UFDouble accNum = vo.getNaccumstorenum();
      if (MathTool.isDiffSign(orderNum, accNum)) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004030_0", "04004030-0147")/*
                                                      * @res
                                                      * "存在订单累计入库数量与订单数量正负不一致的行，请检查！"
                                                      */);
      }
      UFDouble intolerance = intoleranceMap.get(vo.getPk_material());
      UFDouble toleranceNum =
          orderNum.multiply(intolerance.add(100)).div(100.0);
      // 累计到货(入库数量)大于订单容差数量
      if (MathTool.compareTo(accNum, toleranceNum) > 0) {
        if (ctrltype.not_save.equals(PO02)) {
          throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004030_0", "04004030-0148")/*
                                                                       * @res
                                                                       * "存在期初暂估单数量超出订单行数量容差范围！"
                                                                       */);
        }
        else if (ctrltype.ask.equals(PO02) && !wbVo.isNumUserConfirm()) {
          throw new AskPriceException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004030_0", "04004030-0149")/*
                                                                       * @res
                                                                       * "存在入库单收数量超出订单行数量容差范围,是否继续？"
                                                                       */);
        }
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private Map<String, UFDouble> getIntoleranceMap(OrderReceivePlanVO[] vos) {
    Set<String> pk_materialSet = new HashSet<String>();
    for (OrderReceivePlanVO vo : vos) {
      pk_materialSet.add(vo.getPk_material());
    }
    try {
      return MaterialIntoleranceService.queryIntolerance(pk_materialSet
          .toArray(new String[pk_materialSet.size()]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

}
