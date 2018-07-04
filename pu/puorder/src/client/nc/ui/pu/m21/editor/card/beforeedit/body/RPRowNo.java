/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-28 ����04:44:38
 */
package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�кű༭ǰ�¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-28 ����04:44:38
 */
public class RPRowNo implements ICardBodyBeforeEditEventListener {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    UIRefPane pane = (UIRefPane) panel.getBodyItem("crowno").getComponent();
    pane.requestFocus();
    pane.getUITextField().requestFocus();
    // pane.getUITextField().requestDefaultFocus();
    pane.getUITextField().requestFocus(true);
    panel.stopEditing();
    Object obj = panel.getBodyValueAt(row, "crowno");
    ((UIRefPane) panel.getBodyItem("crowno").getComponent())
        .setText((String) obj);
  }

}
