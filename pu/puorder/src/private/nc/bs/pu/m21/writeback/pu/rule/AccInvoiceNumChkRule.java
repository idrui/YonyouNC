/**
 * $文件说明$
 *
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-22 下午09:39:06
 */
package nc.bs.pu.m21.writeback.pu.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单累计开票数量检查
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-1-22 下午09:39:06
 */
public class AccInvoiceNumChkRule implements IRule<OrderViewVO> {

  @Override
  public void process(OrderViewVO[] vos) {
    for (OrderViewVO view : vos) {
      UFDouble orderNum = view.getNnum();
      UFDouble accInvoiceNum = view.getNaccuminvoicenum();
      if (MathTool.isDiffSign(orderNum, accInvoiceNum)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0146")/*@res "存在累计开票数量与订单数量正负不一致 的行，请检查！"*/);
      }
    }
  }

}