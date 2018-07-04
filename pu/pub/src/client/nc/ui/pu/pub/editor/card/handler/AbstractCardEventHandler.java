package nc.ui.pu.pub.editor.card.handler;

import java.util.HashMap;
import java.util.Map;

import nc.ui.pu.pub.editor.card.listener.ICardEventListener;
import nc.ui.pubapp.uif2app.event.PubAppEvent;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ƭ����༭�¼�Ĭ�ϴ�����
 * </ul>
 * <p>
 * 
 * @param <T1>
 *          <p>
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-3-3 ����04:16:19
 */
public abstract class AbstractCardEventHandler<T1 extends ICardEventListener, T2 extends PubAppEvent> {
  private T2 cardEditEvent;

  /**
   * �Ƿ��һ�δ����¼�
   */
  private boolean isFirstFlag = true;

  /**
   * <�ֶ����ƣ�������>
   */
  private Map<String, T1> listenerMap;

  public AbstractCardEventHandler() {
    super();
  }

  public T2 getCardEditEvent() {
    return this.cardEditEvent;
  }

  public Map<String, T1> getListenerMap() {
    if (this.listenerMap == null) {
      this.listenerMap = new HashMap<String, T1>();
    }

    return this.listenerMap;
  }

  public boolean isFirstFlag() {
    return this.isFirstFlag;
  }

  /**
   * ��������������ע���¼��ļ����࣬�ɾ�������ʵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param listenerMap
   *          <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-3-3 ����04:13:48
   */
  public abstract void registerEventListener(Map<String, T1> listenerMap1);

  public void setCardEditEvent(T2 cardEditEvent) {
    this.cardEditEvent = cardEditEvent;
  }

  public void setFirstFlag(boolean isFirstFlag) {
    this.isFirstFlag = isFirstFlag;
  }
}
