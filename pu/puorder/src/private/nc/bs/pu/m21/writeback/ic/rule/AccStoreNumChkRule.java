/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-15 上午11:40:16
 */
package nc.bs.pu.m21.writeback.ic.rule;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.ic.m45.IOrderWriteBackPara;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单累计入库数量检查
 * @scene
 *        采购入库单回写订单
 * @param
 *        IOrderWriteBackPara[] wbVos 采购订单公共回写参数接口
 * @since 6.3
 * @version 2014-10-22 下午3:25:14
 * @author luojw
 */
public class AccStoreNumChkRule implements IRule<OrderViewVO> {

  private IOrderWriteBackPara[] wbVos;

  public AccStoreNumChkRule(IOrderWriteBackPara[] wbVos) {
    this.wbVos = wbVos;
  }

  @Override
  public void process(OrderViewVO[] views) {
    if (ArrayUtils.isEmpty(views)) {
      return;
    }
    Map<String, OrderViewVO> viewMap = CirVOUtil.createKeyVOMap(views);
    for (IOrderWriteBackPara wbVo : this.wbVos) {
      OrderViewVO view = viewMap.get(wbVo.getBID());
      // 累计到货(入库)数量与订单数量检查
      this.checkArrNum(view);
      this.checkSign(view, wbVo);
    }
  }

  private void checkArrNum(OrderViewVO view) {
    if (UFBoolean.TRUE.equals(view.getBrefwhenreturn())) {
      return;
    }
    UFDouble nnum = view.getNnum();
    UFDouble naccumstorenum = view.getNaccumstorenum();
    UFDouble nbackstorenum = view.getNbackstorenum();
    UFDouble naccbacknum =
        MathTool.add(view.getNbackarrvnum(), view.getNbackstorenum());
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

  private void checkSign(OrderViewVO view, IOrderWriteBackPara wbVo) {
    if (!wbVo.isReturn() || UFBoolean.TRUE.equals(view.getBrefwhenreturn())) {
      if (MathTool.isDiffSign(view.getNnum(), view.getNaccumarrvnum())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0143")/*
                                                                     * @res
                                                                     * "存在订单累计入库主数量与订单数量正负不一致的行，请检查！"
                                                                     */);
      }
    }
  }
}
