/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-26 下午04:16:00
 */
package nc.bs.pu.m21.writeback.ic.rule;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.ic.m45.IOrderWriteBackPara;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              到货计划累计入库数量检查
 * @scene
 *        采购入库单回写到货计划
 * @param IOrderWriteBackPara[] wbVos 采购订单公共回写参数接口 OrderViewVO[] views 采购订单的视图VO
 * @since 6.3
 * @version 2014-10-22 上午9:55:55
 * @author luojw
 */
public class ReceivePlanStoreChkRule implements IRule<OrderReceivePlanVO> {

  private OrderViewVO[] views;

  private IOrderWriteBackPara[] wbVos;

  public ReceivePlanStoreChkRule(IOrderWriteBackPara[] wbVos,
      OrderViewVO[] views) {
    this.wbVos = wbVos;
    this.views = views;
  }

  @Override
  public void process(OrderReceivePlanVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    Map<String, OrderViewVO> viewMap = CirVOUtil.createKeyVOMap(this.views);
    // 到货计划累计入库数量检查
    Map<String, OrderReceivePlanVO> map =
        new HashMap<String, OrderReceivePlanVO>();
    for (OrderReceivePlanVO vo : vos) {
      map.put(vo.getPk_order_bb1(), vo);
    }

    for (IOrderWriteBackPara wbVo : this.wbVos) {
      String bb1 = wbVo.getBBID();
      if (bb1 != null) {
        OrderReceivePlanVO rpVO = map.get(bb1);
        OrderViewVO view = viewMap.get(wbVo.getBID());
        // 累计到货(入库)数量与订单数量检查
        this.checkArrNum(view, rpVO);
        this.checkSign(view, rpVO, wbVo);
      }
    }
  }

  private void checkArrNum(OrderViewVO view, OrderReceivePlanVO rpVO) {
    if (null == rpVO) {
      return;
    }
    if (UFBoolean.TRUE.equals(view.getBrefwhenreturn())) {
      return;
    }
    UFDouble nnum = rpVO.getNnum();
    UFDouble naccumstorenum = rpVO.getNaccumstorenum();
    UFDouble nbackstorenum = rpVO.getNbackstorenum();
    UFDouble naccbacknum =
        MathTool.add(rpVO.getNbackarrvnum(), rpVO.getNbackstorenum());
    if (MathTool.compareTo(nnum, UFDouble.ZERO_DBL) > 0) {
      if (MathTool.compareTo(nbackstorenum, naccumstorenum) > 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0141")/*
                                                                     * @res
                                                                     * "存在订单累计退库主数量超出累计入库主数量的行，请检查！"
                                                                     */);
      }
    }
    else {
      if (MathTool.absCompareTo(naccbacknum, nnum) > 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0142")/*
                                                                     * @res
                                                                     * "存在订单的累计退货数量与累计退库数量之和超出订单数量的行，请检查！"
                                                                     */);
      }
    }
  }

  private void checkSign(OrderViewVO view, OrderReceivePlanVO rpVO,
      IOrderWriteBackPara wbVo) {
    if (null == rpVO) {
      return;
    }
    if (!wbVo.isReturn() || UFBoolean.TRUE.equals(view.getBrefwhenreturn())) {
      if (MathTool.isDiffSign(rpVO.getNnum(), rpVO.getNaccumarrvnum())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0143")/*
                                                                     * @res
                                                                     * "存在订单累计入库主数量与订单数量正负不一致的行，请检查！"
                                                                     */);
      }
    }
  }
}
