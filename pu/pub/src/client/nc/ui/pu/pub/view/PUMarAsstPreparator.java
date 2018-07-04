package nc.ui.pu.pub.view;

import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator;

/**
 * ���÷�Χ����Ƭ�����ҷǱ༭̬����Ҫ�༭����Ҫע�����ϸ������Եĳ�����
 * ����ƽ̨�ڱ༭ǰ�ж�����ǷǱ༭̬�򷵻�false������ҵ�����Լ��ı༭ǰ����trueʧЧ��
 * ƽ̨�ܾ��޸ģ����ǲɹ�����Լ��Ĵ����ࡣ
 * 
 * @since 6.3
 * @version 2012-12-19 ����02:00:47
 * @author lixyp
 */
public class PUMarAsstPreparator extends MarAsstPreparator {

  @Override
  protected void beforEdit(CardBodyBeforeEditEvent e) {
    boolean editable =
        this.getMarAsstEditDelegate().beforEdit(
            e.getBillCardPanel().getBillModel(), e.getRow(), e.getKey(),
            e.getReturnValue());
    e.setReturnValue(Boolean.valueOf(editable));
    return;
  }

}
