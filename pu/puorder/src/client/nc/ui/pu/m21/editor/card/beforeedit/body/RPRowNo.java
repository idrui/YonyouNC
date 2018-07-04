/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-28 下午04:44:38
 */
package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>行号编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-28 下午04:44:38
 */
public class RPRowNo implements ICardBodyBeforeEditEventListener {

  /**
   * 父类方法重写
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
