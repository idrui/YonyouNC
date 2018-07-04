package nc.ui.pu.m27.match.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.m27.match.view.MatchResultListPanel;
import nc.ui.pu.pub.util.LinkQueryUtil;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.bill.BillModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>联查入库单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-10-1 下午10:50:00
 */
public class LinkQueryStockAction extends NCAction {
  private static final long serialVersionUID = 1941125214057367981L;

  private MatchResultListPanel listView;

  public LinkQueryStockAction() {
    super();
    SCMActionInitializer
        .initializeAction(this, SCMActionCode.PU_LINKQUERYSTOCK);
    // this.setBtnName("联查入库单");
    // this.setCode("btnLinkQueryStock");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    int row =
        this.getListView().getBillListPanel().getBodyTable().getSelectedRow();
    BillModel bm = this.getListView().getBillListPanel().getBodyBillModel();
    String billType = (String) bm.getValueAt(row, StockSettleVO.VTRANTYPECODE);
    String billId = (String) bm.getValueAt(row, StockSettleVO.PK_STOCKPS);
    String org = (String) bm.getValueAt(row, StockSettleVO.PK_ORG);
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
    UITable ut = this.getListView().getBillListPanel().getBodyTable();
    int count = ut.getRowCount();
    int selectedRow = ut.getSelectedRow();
    return count > 0 && selectedRow >= 0;
  }
}
