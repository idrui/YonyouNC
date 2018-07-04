package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.BodyDelLineAction;
import nc.vo.pu.m21.entity.OrderPaymentVO;

public class PaymentBodyDelLineAction extends BodyDelLineAction {
  /**
	 * 
	 */
  private static final long serialVersionUID = -6867730235372037559L;

  @Override
  protected boolean doBeforeAction(ActionEvent e) {
    boolean action = super.doBeforeAction(e);
    if (action) {
      BillCardPanel panel = this.getCardPanel();
      int index = this.getCurrentSelectIndex();
      int count = panel.getRowCount();
      for (int i = index + 1; i < count; i++) {
        panel.setBodyValueAt(i, i, OrderPaymentVO.SHOWORDER);
      }
    }
    return action;
  }
}
