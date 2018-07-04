/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-29 下午07:29:14
 */
package nc.ui.pu.m21.action.status.output;

import java.util.HashSet;
import java.util.Iterator;

import nc.bs.uif2.IActionCode;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.SelectAllAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.model.BillManageModel;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>全选按钮
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-6-29 下午07:29:14
 */
public class OrderSelectAllAction extends SelectAllAction {

  private static final long serialVersionUID = -4474808510188879428L;

  public OrderSelectAllAction() {
    SCMActionInitializer.initializeAction(this, IActionCode.SELALL);
  }

  private void setSelectedRows() {
    BillListPanel panel = this.getListEditor().getBillListPanel();
    BillModel bm = panel.getHeadBillModel();

    int count = bm.getRowCount();
    HashSet<Integer> selectedRows = new HashSet<Integer>();
    for (int i = 0; i < count; i++) {
      if (BillModel.SELECTED == bm.getRowState(i)) {
        selectedRows.add(i);
      }
    }
    if (selectedRows.size() == 0) {
      return;
    }
    int[] intSelectedRows = new int[selectedRows.size()];

    Integer selectedRow = null;
    Iterator<Integer> iterRows = selectedRows.iterator();
    int i = 0;
    while (iterRows.hasNext()) {
      selectedRow = iterRows.next();
      intSelectedRows[i] = selectedRow;
      i++;
    }
    ((BillManageModel) this.getModel())
        .setSelectedOperaRowsWithoutEvent(intSelectedRows);
  }

  @Override
  protected boolean isActionEnable() {
    this.setSelectedRows();
    return true;
  }
}
