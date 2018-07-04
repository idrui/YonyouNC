/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-14 下午03:54:12
 */
package nc.ui.pu.m21.action.direction;

import java.awt.event.ActionEvent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.NextLineAction;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>下一页
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-7-14 下午03:54:12
 */
public class OrderNextLineAction extends NextLineAction {

  private static final long serialVersionUID = -1091148110014045279L;

  private BillCardPanel editor;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.editor.stopEditing();
    super.doAction(e);
  }

  public BillCardPanel getEditor() {
    return this.editor;
  }

  public void setEditor(BillCardPanel editor) {
    this.editor = editor;
  }

}
