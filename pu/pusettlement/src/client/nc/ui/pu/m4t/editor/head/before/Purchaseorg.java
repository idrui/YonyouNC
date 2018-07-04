package nc.ui.pu.m4t.editor.head.before;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 采购组织
 * 
 * @since 6.0
 * @version 2011-2-28 上午08:34:03
 * @author wuxla
 */

public class Purchaseorg implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel panel = e.getBillCardPanel();
    if (this.isFromOrder(panel)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }

    e.setReturnValue(Boolean.TRUE);
  }

  private boolean isFromOrder(BillCardPanel panel) {
    int rowcount = panel.getRowCount();
    if (0 == rowcount) {
      return false;
    }

    for (int i = 0; i < rowcount; ++i) {
      String csourcetypecode =
          (String) panel.getBodyValueAt(i, InitialEstItemVO.CSOURCETYPECODE);
      if (POBillType.Order.getCode().equals(csourcetypecode)) {
        return true;
      }
    }

    return false;
  }
}
