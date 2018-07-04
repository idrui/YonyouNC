package nc.ui.pu.pub.editor.card.handler;

import org.apache.commons.lang.ArrayUtils;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;

/**
 * <p>
 * <b>��������ͷ�༭�¼��Ĵ������࣬������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����༭ǰ�¼�
 * <li>����༭���¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-24 ����05:00:09
 */
public abstract class AbstractCardHeadTailAfterEditEventHandler
    extends
    AbstractCardEventHandler<ICardHeadTailAfterEditEventListener, CardHeadTailAfterEditEvent>
    implements IAppEventHandler<CardHeadTailAfterEditEvent> {

  public AbstractCardHeadTailAfterEditEventHandler() {
    super();
  }

  @Override
  public void handleAppEvent(CardHeadTailAfterEditEvent e) {

    this.setCardEditEvent(e);

    // ����ǵ�һ�δ����¼���ע���¼�������
    if (this.isFirstFlag()) {
      this.registerEventListener(this.getListenerMap());

      this.setFirstFlag(false);
    }

    // û������,ֱ�ӷ���
    if (this.getEventListener(e) == null) {
      return;
    }

    /* 2015��12��10�� wangweir �¼���Ӧǰ���ñ��岻�ϼ�,���������еĺϼƼ���Ч������ Begin*/
    setBodyCalculate(e, false);
    
    // �༭���¼�
    this.getEventListener(e).afterEdit(e);
    
    setBodyCalculate(e, true);
    /* 2015��12��10�� wangweir End*/
  }

  /**
   * �¼���Ӧǰ���ñ���ϼ�״̬
   * 
   * @param e CardHeadTailAfterEditEvent
   * @param isEnable true if enable body calculate
   */
  protected void setBodyCalculate(CardHeadTailAfterEditEvent e,
      boolean isEnable) {
    String[] tableCodes =
        e.getBillCardPanel().getBillData().getBodyTableCodes();

    if (ArrayUtils.isEmpty(tableCodes)) {
      return;
    }
    for (String tableCode : tableCodes) {
      e.getBillCardPanel().getBillModel(tableCode).setNeedCalculate(isEnable);
    }
  }

  private ICardHeadTailAfterEditEventListener getEventListener(
      CardHeadTailAfterEditEvent e) {
    String itemKey = e.getKey();

    return this.getListenerMap().get(itemKey);
  }
}
