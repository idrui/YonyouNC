package nc.ui.pu.m21.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * 币种编辑前事件，控制币种的编辑性。<br/>
 * 解决之前切换组织时，关联的合同被取消，但币种仍然无法编辑的问题。
 * 
 * @since 6.3
 * @version 2013-7-19 上午11:20:55
 * @author lixyp
 */
public class Corigcurrencyid implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel card = e.getBillCardPanel();
    int rowCount = card.getRowCount();
    boolean enable = true;

    for (int row = 0; row < rowCount; row++) {
      if (card.getBodyValueAt(row, OrderItemVO.CCONTRACTID) != null) {
        enable = false;
        break;
      }
    }

    if (enable) {
      e.setReturnValue(Boolean.TRUE);
    }
    else {
      e.setReturnValue(Boolean.FALSE);
    }
  }
}
