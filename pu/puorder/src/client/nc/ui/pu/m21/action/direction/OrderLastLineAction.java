/**
 * $�ļ�˵��$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-14 ����04:26:01
 */
package nc.ui.pu.m21.action.direction;

import java.awt.event.ActionEvent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.LastLineAction;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ĩҳ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-7-14 ����04:26:01
 */
public class OrderLastLineAction extends LastLineAction {

  private static final long serialVersionUID = -1449334230988880585L;

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
