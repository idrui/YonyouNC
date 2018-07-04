package nc.ui.pu.m21.action;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.vo.pu.m21.entity.OrderPaymentVO;

public class PaymentBodyAddLineAction extends BodyAddLineAction {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1359893952467800002L;

  @Override
  protected void afterLineInsert(int index) {
    super.afterLineInsert(index);
    CardEditorHelper helper = new CardEditorHelper(this.getCardPanel());
    helper.setBodyValue(index, OrderPaymentVO.SHOWORDER, index + 1);
  }
}
