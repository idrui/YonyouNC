/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-15 上午09:32:57
 */
package nc.ui.pu.position.action;

import nc.ui.pubapp.uif2app.actions.billlist.CancelAction;
import nc.ui.pubapp.uif2app.view.BillListEditor;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>取消Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-15 上午09:32:57
 */
public class PositionCancelAction extends CancelAction {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 8069946215779175551L;

  @Override
  protected void onCancel() {
    super.onCancel();
    ((BillListEditor) this.getEditor()).setMultiSelectionEnable(true);
    ((BillListEditor) this.getEditor()).getBillListPanel().getHeadTable()
        .setSortEnabled(true);
    ((BillListEditor) this.getEditor()).updateUI();
  }
}
