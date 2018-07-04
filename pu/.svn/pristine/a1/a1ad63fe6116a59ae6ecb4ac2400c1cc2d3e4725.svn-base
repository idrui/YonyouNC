/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-26 下午06:40:12
 */
package nc.bs.pu.m21.writeback.pu.rule;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.pu.m23.IOrderWriteBackParaFor23;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

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
 * @time 2010-4-26 下午06:40:12
 */
public class ReceivePlanArrChkRule implements IRule<OrderReceivePlanVO> {
  private OrderViewVO[] views;

  private IOrderWriteBackParaFor23[] wbVos;

  public ReceivePlanArrChkRule(IOrderWriteBackParaFor23[] wbVos,
      OrderViewVO[] views) {
    this.wbVos = wbVos;
    this.views = views;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderReceivePlanVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    Map<String, OrderReceivePlanVO> map =
        new HashMap<String, OrderReceivePlanVO>();
    for (OrderReceivePlanVO vo : vos) {
      map.put(vo.getPk_order_bb1(), vo);
    }
    Map<String, OrderViewVO> viewMap = CirVOUtil.createKeyVOMap(this.views);
    for (IOrderWriteBackParaFor23 wbVo : this.wbVos) {
      String bb1 = wbVo.getBBID();
      if (bb1 != null) {
        OrderReceivePlanVO vo = map.get(bb1);
        OrderViewVO view = viewMap.get(wbVo.getBID());
        this.checkArrNum(view, vo);
        this.checkSign(view, vo, wbVo);
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
    UFDouble naccumarrvnum = rpVO.getNaccumarrvnum();
    UFDouble nbackarrvnum = rpVO.getNbackarrvnum();
    UFDouble naccbacknum =
        MathTool.add(rpVO.getNbackarrvnum(), rpVO.getNbackstorenum());
    if (MathTool.compareTo(nnum, UFDouble.ZERO_DBL) > 0) {
      if (MathTool.compareTo(nbackarrvnum, naccumarrvnum) > 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0144")/*
                                                                     * @res
                                                                     * "存在订单累计退货主数量超出累计到货主数量的行，请检查！"
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
      IOrderWriteBackParaFor23 wbVo) {
    if (null == rpVO) {
      return;
    }
    if (!wbVo.isReturn() || UFBoolean.TRUE.equals(view.getBrefwhenreturn())) {
      if (MathTool.isDiffSign(rpVO.getNnum(), rpVO.getNaccumarrvnum())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0145")/*
                                                                     * @res
                                                                     * "存在订单累计到货主数量与订单数量正负不一致的行，请检查！"
                                                                     */);
      }
    }
  }
}
