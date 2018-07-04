package nc.ui.pu.m21.editor.card.afteredit.header;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m21.entity.OrderHeaderVO;

public class RefWhenReturn implements ICardHeadTailAfterEditEventListener {
  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    // 退货和退货/库基于原订单补货 互斥,有一方被选中,另一个不能修改
    CardEditorHelper editor = new CardEditorHelper(event.getBillCardPanel());
    Object oldval = event.getOldValue();
    BillItem eventsrcitem = editor.getEditor().getHeadItem(event.getKey());
    // 与事件源互斥的keyname
    String keyname = null;
    if (event.getKey().equals(OrderHeaderVO.BRETURN)) {
      keyname = OrderHeaderVO.BREFWHENRETURN;
    }
    else if (event.getKey().equals(OrderHeaderVO.BREFWHENRETURN)) {
      keyname = OrderHeaderVO.BRETURN;
    }
    Boolean breturn = (Boolean) editor.getHeadValue(keyname);
    if (breturn != null && breturn.booleanValue()) {
      eventsrcitem.setValue(oldval);
    }
  }
}
