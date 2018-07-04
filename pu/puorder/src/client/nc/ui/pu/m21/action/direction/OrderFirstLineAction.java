/**
 * $�ļ�˵��$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-14 ����04:24:50
 */
package nc.ui.pu.m21.action.direction;

import java.awt.event.ActionEvent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.FirstLineAction;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ҳ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-7-14 ����04:24:50
 */
public class OrderFirstLineAction extends FirstLineAction {

  private static final long serialVersionUID = 8377225362426266314L;

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
