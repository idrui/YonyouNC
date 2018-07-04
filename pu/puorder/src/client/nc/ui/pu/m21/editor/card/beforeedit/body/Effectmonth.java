package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.bs.ml.NCLangResOnserver;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderPaymentVO;

public class Effectmonth implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    Object checkdate =
        panel.getBodyValueAt(event.getRow(), OrderPaymentVO.CHECKDATA);
    if (checkdate == null) {
      MessageDialog.showWarningDlg(panel, null, NCLangResOnserver.getInstance()
          .getStrByID("4004030_0", "04004030-0377")/* 请先填写固定结账日！ */);
      event.setReturnValue(Boolean.FALSE);
    }

  }

}
