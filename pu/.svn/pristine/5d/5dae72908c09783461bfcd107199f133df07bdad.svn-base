/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-24 下午02:47:05
 */
package nc.ui.pu.m27.match.action;

import java.awt.event.ActionEvent;

import nc.bs.uif2.IActionCode;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.SelectAllAction;
import nc.ui.scmpub.action.SCMActionInitializer;

/**
 * <p>
 * <b>结算的"全选"按钮</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-24 下午02:47:05
 */
public class MatchSelectAllAction extends SelectAllAction {

  private static final long serialVersionUID = 2769908482088157296L;

  public MatchSelectAllAction() {
    super();
    SCMActionInitializer.initializeAction(this, IActionCode.SELALL);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    BillListPanel panel = this.getListEditor().getBillListPanel();
    // 全选列表的表头（发票记录）
    this.selectedAll(panel.getHeadBillModel());
    // 全选列表的表体（入库单记录）
    this.selectedAll(panel.getBodyBillModel());
    // 刷新界面
    this.getListEditor().updateUI();
  }

  private boolean existNotSelectedRow(BillModel bm) {
    int count = bm.getRowCount();
    for (int i = 0; i < count; i++) {
      if (BillModel.SELECTED != bm.getRowState(i)) {
        return true;
      }
    }
    return false;
  }

  private void selectedAll(BillModel bm) {
    int count = bm.getRowCount();
    for (int row = 0; row < count; row++) {
      bm.setRowState(row, BillModel.SELECTED);
    }
  }

  @Override
  protected boolean isActionEnable() {
    BillListPanel panel = this.getListEditor().getBillListPanel();
    // 如果列表的表头（发票）存在未选中记录，则返回true
    if (this.existNotSelectedRow(panel.getHeadBillModel())) {
      return true;
    }
    // 如果列表的表体（入库单）存在未选中记录，则返回true
    if (this.existNotSelectedRow(panel.getBodyBillModel())) {
      return true;
    }

    return false;
  }

}
