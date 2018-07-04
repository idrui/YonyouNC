package nc.ui.pu.m25.linkquery;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBillItemHyperlinkEvent;
import nc.ui.pubapp.uif2app.linkquery.CardLinkQueryHyperlinkHandler;
import nc.ui.pubapp.uif2app.linkquery.CardLinkQueryInfoProvider;

/**
 * 费用发票联查货物发票卡片超链接处理
 * 
 * @since 6.0
 * @version 2011-7-1 上午10:38:46
 * @author 田锋涛
 */

public class InvoiceCardLinkQueryHyperlinkHandler extends
    CardLinkQueryHyperlinkHandler {

  @Override
  public void handleAppEvent(CardBillItemHyperlinkEvent e) {
    if (this.getSrcBillNOField().equals(e.getItem().getKey())) {
      String srcbillid = this.getSrcBillID(e);

      FuncletInitData initData =
          InvoiceLinkQueryUtil.getFuncletInitData(this.getSrcBillType(),
              srcbillid);
      // FuncletWindowLauncher.openFuncNodeForceModalDialog(this.getModel()
      // .getContext().getEntranceUI(),
      // InvoiceLinkQueryUtil.getFuncRegisterVO(), initData, null, true,
      // new Dimension(800, 600), null);
      InvoiceLinkQueryUtil.openFuncNodeForceModalDialog(this.getModel()
          .getContext().getEntranceUI(), initData);
    }
  }

  private String getSrcBillID(CardBillItemHyperlinkEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    BillItem srcBillNOItem = e.getItem();
    int selectedRow = e.getRow();
    CardLinkQueryInfoProvider infoProvider =
        new CardLinkQueryInfoProvider(this.getSrcBillIDField(),
            this.getSrcBillTypeField(), this.getSrcBillTypeFieldPos());
    return infoProvider.getSrcBillID(selectedRow, srcBillNOItem, cardPanel);
  }

}
