package nc.ui.pu.m25.linkquery;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.BillScrollPane.BillTable;
import nc.ui.pubapp.uif2app.event.list.ListBillItemHyperlinkEvent;
import nc.ui.pubapp.uif2app.linkquery.ListLinkQueryHyperlinkHandler;
import nc.ui.pubapp.uif2app.linkquery.ListLinkQueryInfoProvider;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 费用发票联查货物发票列表超链接处理
 *
 * @since 6.0
 * @version 2011-7-1 上午10:39:11
 * @author 田锋涛
 */

public class InvoiceListLinkQueryHyperlinkHandler extends
    ListLinkQueryHyperlinkHandler {
  @Override
  public void handleAppEvent(ListBillItemHyperlinkEvent e) {
    BillItem item = e.getItem();
    if (this.getSrcBillNOField().equals(item.getKey())) {
      String srcbillid = this.getSrcBillID(e);
      FuncletInitData initData =
          InvoiceLinkQueryUtil.getFuncletInitData(this.getSrcBillType(e),
              srcbillid);
      InvoiceLinkQueryUtil.openFuncNodeForceModalDialog(this.getModel()
          .getContext().getEntranceUI(), initData);
    }
  }

  private String getSrcBillID(BillListPanel listPanel, int selectedRow,
      BillItem srcBillNOItem) {
    String srcBillID = null;
    BillModel headBillModel = listPanel.getHeadBillModel();
    if (srcBillNOItem == headBillModel.getItemByKey(this.getSrcBillNOField())) {
      srcBillID =
          (String) headBillModel.getValueAt(selectedRow,
              this.getSrcBillIDField());
    }
    else {
      String tableCode = srcBillNOItem.getTableCode();
      BillModel bodyBillModel = listPanel.getBodyBillModel(tableCode);
      srcBillID =
          (String) bodyBillModel.getValueAt(selectedRow,
              this.getSrcBillIDField());
    }
    if (srcBillID == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0017")/*@res "单据上没有存储来源单据主键"*/);
    }
    return srcBillID;
  }

  private String getSrcBillID(ListBillItemHyperlinkEvent e) {
    BillListPanel listPanel = e.getBillListPanel();
    BillItem srcBillNOItem = e.getItem();
    BillTable source = (BillTable) e.getSource();
    int selectedRow = source.getSelectedRow();
    return this.getSrcBillID(listPanel, selectedRow, srcBillNOItem);
  }

  private String getSrcBillType(ListBillItemHyperlinkEvent e) {
    BillTable source = (BillTable) e.getSource();
    int selectedRow = source.getSelectedRow();
    BillListPanel listPanel = e.getBillListPanel();

    ListLinkQueryInfoProvider infoProvider =
        new ListLinkQueryInfoProvider(this.getSrcBillIDField(),
            this.getSrcBillTypeField(), this.getSrcBillTypeFieldPos());

    return infoProvider.getSrcBillType(listPanel, selectedRow);
  }
}