/**
 * $文件说明$
 *
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-17 下午09:14:05
 */
package nc.bs.pu.m21.writeback.pu.rule;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.pu.m23.IOrderWriteBackParaFor23;
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
 * <li>累计到货数量检查
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-1-17 下午09:14:05
 */
public class AccArrNumChkRule implements IRule<OrderViewVO> {
  private IOrderWriteBackParaFor23[] wbVos;

  public AccArrNumChkRule(IOrderWriteBackParaFor23[] wbVos) {
    this.wbVos = wbVos;
  }

  @Override
  public void process(OrderViewVO[] views) {
    if (ArrayUtils.isEmpty(views)) {
      return;
    }
    Map<String, OrderViewVO> viewMap = CirVOUtil.createKeyVOMap(views);
    for (IOrderWriteBackParaFor23 wbVo : this.wbVos) {
      OrderViewVO view = viewMap.get(wbVo.getBID());
      // 累计到货(入库)数量与订单数量检查
      this.checkArrNum(view);
      this.checkSign(view, wbVo);
      // 累计到货、累计退货和累计入库之间关系检查
    }
  }

  private void checkArrNum(OrderViewVO view) {
    if (UFBoolean.TRUE.equals(view.getBrefwhenreturn())) {
      return;
    }
    UFDouble nnum = view.getNnum();
    UFDouble naccumarrvnum = view.getNaccumarrvnum();
    UFDouble nbackarrvnum = view.getNbackarrvnum();
    UFDouble naccbacknum =
        MathTool.add(view.getNbackarrvnum(), view.getNbackstorenum());
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

  private void checkSign(OrderViewVO view, IOrderWriteBackParaFor23 wbVo) {
    if (!wbVo.isReturn() || UFBoolean.TRUE.equals(view.getBrefwhenreturn())) {
      if (MathTool.isDiffSign(view.getNnum(), view.getNaccumarrvnum())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0145")/*
                                                                     * @res
                                                                     * "存在订单累计到货主数量与订单数量正负不一致的行，请检查！"
                                                                     */);
      }
    }
    // else {
    // if (MathTool.compareTo(view.getNaccumarrvnum(), UFDouble.ZERO_DBL) < 0) {
    // ExceptionUtils.wrappBusinessException("累计到货数量必须大于零！");
    // }
    // }
  }
}
