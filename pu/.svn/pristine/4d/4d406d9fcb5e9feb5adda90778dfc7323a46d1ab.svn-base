package nc.ui.pu.m21.editor.card.afteredit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pub.lang.UFDouble;

public class Accrate implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int count = panel.getRowCount();
    if (count == event.getRow() + 1) {
      panel.addLine(OrderPaymentVO.TABSNAME);
      panel.setBodyValueAt(count + 1, count, OrderPaymentVO.SHOWORDER,
          OrderPaymentVO.TABSNAME);
    }
    UFDouble sum = new UFDouble(0);
    for (int i = 0; i < count; i++) {
      UFDouble accrate =
          (UFDouble) panel.getBodyValueAt(i, OrderPaymentVO.ACCRATE);
      if (accrate == null) {
        continue;
      }
      if (accrate.compareTo(UFDouble.ZERO_DBL) == 0) {
        MessageDialog.showWarningDlg(panel, null, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0379")/*
                                                                     * 付款比例不允许为0，
                                                                     * 请重新输入
                                                                     */);
        panel.setBodyValueAt(null, event.getRow(), OrderPaymentVO.ACCRATE);
        continue;
      }
      sum = sum.add(accrate);
    }
    if (sum.compareTo(new UFDouble(100)) == 1) {
      MessageDialog.showWarningDlg(panel, null, nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0380")/*
                                                                   * 付款比例之和不允许超过100！
                                                                   */);
      UFDouble value = ((UFDouble) event.getValue()).add(100).sub(sum);
      panel.setBodyValueAt(value, event.getRow(), OrderPaymentVO.ACCRATE);
    }
  }
}
