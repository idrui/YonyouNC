package nc.ui.pu.m21.action;

import nc.ui.pubapp.uif2app.actions.BodyInsertLineAction;
import nc.vo.pu.m21.entity.OrderPaymentVO;

public class PaymentBodyInsertLineAction extends BodyInsertLineAction {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1737655851378595252L;

  @Override
  protected void afterLineInsert(int index) {
    super.afterLineInsert(index);
    Integer showorder =
        (Integer) this.getCardPanel().getBillModel()
            .getValueAt(index + 1, OrderPaymentVO.SHOWORDER);
    this.getCardPanel().setBodyValueAt(showorder, index,
        OrderPaymentVO.SHOWORDER);
    for (int i = index + 1; i < this.getCardPanel().getRowCount(); i++) {
      showorder = showorder + 1;
      this.getCardPanel()
          .setBodyValueAt(showorder, i, OrderPaymentVO.SHOWORDER);
    }
  }

}
