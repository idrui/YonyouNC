/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-18 ����09:49:22
 */
package nc.ui.pu.m422x.editor.card.beforeedit.body;

import nc.ui.pu.m422x.rule.EditableByUnit;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-18 ����09:49:22
 */
public class ChangeRate implements ICardBodyBeforeEditEventListener {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    String material =
        (String) panel.getBodyValueAt(row, StoreReqAppItemVO.PK_MATERIAL);
    if (StringUtil.isEmptyWithTrim(material)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    new EditableByUnit(panel).setEditable(new int[] {
      row
    });
  }

}
