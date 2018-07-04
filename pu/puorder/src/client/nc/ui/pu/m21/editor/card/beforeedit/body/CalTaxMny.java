/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-26 ����08:03:17
 */
package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * ��˰���༭ǰ�¼�����
 * 
 * @since 6.0
 * @version 2012-2-20 ����11:12:04
 * @author tianft
 */
public class CalTaxMny implements ICardBodyBeforeEditEventListener {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    Integer buySellFlag =
        (Integer) event.getBillCardPanel().getBodyValueAt(event.getRow(),
            OrderItemVO.FBUYSELLFLAG);
    // ����������=���ڲɹ�ʱ���ɱ༭����������=���ڲɹ�ʱ���ҵ��֧�ֱ༭��
    boolean editable =
        BuySellFlagEnum.OUTPUT.value().equals(buySellFlag)
            || BuySellFlagEnum.IMPORT.value().equals(buySellFlag);
    event.setReturnValue(editable);
  }
}
