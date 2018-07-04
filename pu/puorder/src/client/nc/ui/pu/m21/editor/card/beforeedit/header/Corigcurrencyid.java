package nc.ui.pu.m21.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * ���ֱ༭ǰ�¼������Ʊ��ֵı༭�ԡ�<br/>
 * ���֮ǰ�л���֯ʱ�������ĺ�ͬ��ȡ������������Ȼ�޷��༭�����⡣
 * 
 * @since 6.3
 * @version 2013-7-19 ����11:20:55
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
