package nc.ui.pu.m27.match.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.m27.match.view.MatchResultListPanel;
import nc.ui.pu.pub.util.LinkQueryUtil;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.bill.BillModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>联查发票
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-10-1 下午10:57:10
 */
public class LinkQueryInvoiceAction extends NCAction {
  private static final long serialVersionUID = 5583249071566642852L;

  private MatchResultListPanel listView;

  public LinkQueryInvoiceAction() {
    super();
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.PU_LINKQUERYINVOICE);
    // this.setBtnName("联查发票");
    // this.setCode("btnLinkQueryInvoice");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    int row =
        this.getListView().getBillListPanel().getHeadTable().getSelectedRow();
    BillModel bm = this.getListView().getBillListPanel().getHeadBillModel();
    String billType =
        (String) bm.getValueAt(row, InvoiceSettleVO.VTRANTYPECODE);
    String billId = (String) bm.getValueAt(row, InvoiceSettleVO.PK_INVOICE);
    String org = (String) bm.getValueAt(row, InvoiceSettleVO.PK_ORG);
    LinkQueryUtil utils = new LinkQueryUtil();
    utils.linkQuery(billType, billId, org);
  }

  public MatchResultListPanel getListView() {
    return this.listView;
  }

  public void setListView(MatchResultListPanel listView) {
    this.listView = listView;
    this.listView.getModel().addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    UITable ut = this.getListView().getBillListPanel().getHeadTable();
    int count = ut.getRowCount();
    int selectedRow = ut.getSelectedRow();
    return count > 0 && selectedRow >= 0;
  }
}
