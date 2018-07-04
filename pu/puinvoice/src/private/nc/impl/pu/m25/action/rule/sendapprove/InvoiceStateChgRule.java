/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-9 上午11:54:56
 */
package nc.impl.pu.m25.action.rule.sendapprove;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.VOStatus;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>送审时将发票状态设为审核中
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-9 上午11:54:56
 */
public class InvoiceStateChgRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    for (InvoiceVO vo : vos) {
      vo.getParentVO().setStatus(VOStatus.UPDATED);
      vo.getParentVO().setFbillstatus(POEnumBillStatus.APPROVING.toInteger());
    }
  }

}
