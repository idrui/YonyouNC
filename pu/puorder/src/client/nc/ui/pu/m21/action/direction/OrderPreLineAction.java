/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-14 下午04:22:59
 */
package nc.ui.pu.m21.action.direction;

import java.awt.event.ActionEvent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.PreLineAction;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>上一页
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-7-14 下午04:22:59
 */
public class OrderPreLineAction extends PreLineAction {

  private static final long serialVersionUID = 6081310773710661488L;

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
