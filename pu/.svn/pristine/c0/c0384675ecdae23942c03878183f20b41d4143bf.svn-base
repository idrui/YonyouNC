/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-24 下午02:47:57
 */
package nc.ui.pu.m27.match.action;

import java.awt.event.ActionEvent;

import nc.bs.uif2.IActionCode;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.UnSelectAllAction;
import nc.ui.scmpub.action.SCMActionInitializer;

/**
 * <p>
 * <b>结算的"全消"按钮</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-24 下午02:47:57
 */
public class MatchUnSelectAllAction extends UnSelectAllAction {

  private static final long serialVersionUID = -6983508960586900377L;

  public MatchUnSelectAllAction() {
    super();
    SCMActionInitializer.initializeAction(this, IActionCode.SELNONE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    BillListPanel panel = this.getListEditor().getBillListPanel();
    // 取消选中列表的表头（发票记录）
    this.unSelectedAll(panel.getHeadBillModel());
    // 取消选中列表的表体（入库单记录）
    this.unSelectedAll(panel.getBodyBillModel());
    // 刷新界面
    this.getListEditor().updateUI();
  }

  private boolean existSelectedRow(BillModel bm) {
    int count = bm.getRowCount();
    for (int i = 0; i < count; i++) {
      if (BillModel.SELECTED == bm.getRowState(i)) {
        return true;
      }
    }
    return false;
  }

  private void unSelectedAll(BillModel bm) {
    int count = bm.getRowCount();
    for (int row = 0; row < count; row++) {
      bm.setRowState(row, BillModel.UNSTATE);
    }
  }

  @Override
  protected boolean isActionEnable() {
    BillListPanel panel = this.getListEditor().getBillListPanel();
    // 如果列表的表头（发票）存在选中记录，则返回true
    if (this.existSelectedRow(panel.getHeadBillModel())) {
      return true;
    }
    // 如果列表的表体（入库单）存在选中记录，则返回true
    if (this.existSelectedRow(panel.getBodyBillModel())) {
      return true;
    }
    return false;
  }
}
