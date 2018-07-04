/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-14 ����11:27:46
 */
package nc.ui.pu.m21.editor.card.afteredit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����ַ��գ��������ص������ص�Ҳ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-14 ����11:27:46
 */
public class RPReceiveaddress implements ICardBodyAfterEditEventListener {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent)
   */
  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    String str =
        (String) panel
            .getBodyValueAt(row, OrderReceivePlanVO.PK_RECEIVEADDRESS);
    // �����ַ��գ��������ص������ص�Ҳ���
    if (StringUtil.isEmptyWithTrim(str)) {
      panel.setBodyValueAt(null, row, OrderReceivePlanVO.CDEVADDRID);
      panel.setBodyValueAt(null, row, OrderReceivePlanVO.CDEVAREAID);
    }
  }

}
