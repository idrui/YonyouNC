package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * ÔùÆ·
 * 
 * @since 6.0
 * @version 2011-9-28 ÉÏÎç11:22:54
 * @author wuxla
 */

public class Blargess implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    Object obj = panel.getBodyValueAt(event.getRow(), OrderItemVO.BRECEIVEPLAN);
    if (UFBoolean.TRUE.equals(obj)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }
    event.setReturnValue(Boolean.TRUE);
  }

}
