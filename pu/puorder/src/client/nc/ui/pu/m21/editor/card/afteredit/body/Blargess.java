package nc.ui.pu.m21.editor.card.afteredit.body;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�Ƿ���Ʒ�༭������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-23 ����11:25:24
 */
public class Blargess implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    int row = event.getRow();
    CardEditorHelper card = new CardEditorHelper(event.getBillCardPanel());
    UFBoolean blargess =
        (UFBoolean) card.getBodyValue(row, OrderItemVO.BLARGESS);

    if (blargess != null && !blargess.booleanValue()) {
      // ����λԭ�Һ�˰����
      UFDouble norigtaxnetprice =
          (UFDouble) card.getBodyValue(row, OrderItemVO.NORIGTAXNETPRICE);
      if (norigtaxnetprice != null
          && norigtaxnetprice.compareTo(UFDouble.ZERO_DBL) == 0) {
        // �������Ʒ������λԭ�Һ�˰����Ϊ0��������λԭ�Һ�˰���ۺ�����λԭ�Һ�˰������Ϊ��
        card.setBodyValue(row, OrderItemVO.NORIGTAXPRICE, null);
        card.setBodyValue(row, OrderItemVO.NORIGTAXNETPRICE, null);
      }

      event.getBillCardPanel().getBillModel().reCalcurateAll();
    }
  }

}
