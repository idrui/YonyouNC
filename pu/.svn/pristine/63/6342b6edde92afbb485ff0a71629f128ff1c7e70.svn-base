/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-5 下午06:49:29
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;

import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>参照增行-采购订单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-5 下午06:49:29
 */
public class InvoiceRef21AddRowsAction extends InvoiceRef21AddAction {

  private static final long serialVersionUID = 5942667618060274034L;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.doRefAddRowAction();
  }

  @Override
  protected String getSrcBillTypeCode() {
    // 参照增行的上游单据类型
    return POBillType.Order.getCode();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.m25.action.InvoiceRef21AddAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    return true;
  }
}
