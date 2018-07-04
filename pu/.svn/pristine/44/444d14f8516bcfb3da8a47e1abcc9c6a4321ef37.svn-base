package nc.ui.pu.m21.action;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.BodyPasteLineAction;
import nc.vo.pu.m21.entity.OrderPaymentVO;

public class PaymentPastLineAction extends BodyPasteLineAction {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  public PaymentPastLineAction() {
    List<String> list = new ArrayList<String>();
    list.add(OrderPaymentVO.ACCRATE);
    list.add(OrderPaymentVO.PK_PAYMENT);
    this.setClearItems(list);
  }

  @Override
  public void doAction() {
    super.doAction();
    int lastPastedRow = this.lastPastedRow();
    int rowlength =
        this.getCardPanel().getBodyPanel().getTableModel().getPasteLineNumer();
    this.setDefaultValue(lastPastedRow, rowlength);
  }

  private void setDefaultValue(int lastPastedRow, int rowlength) {
    BillCardPanel panel = this.getCardPanel();
    int count = panel.getRowCount(OrderPaymentVO.TABSNAME);
    for (int i = 0; i < count; i++) {
      panel.setBodyValueAt(i + 1, i, OrderPaymentVO.SHOWORDER);
    }
  }

}
