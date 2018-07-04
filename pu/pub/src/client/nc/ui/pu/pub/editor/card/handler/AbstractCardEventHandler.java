package nc.ui.pu.pub.editor.card.handler;

import java.util.HashMap;
import java.util.Map;

import nc.ui.pu.pub.editor.card.listener.ICardEventListener;
import nc.ui.pubapp.uif2app.event.PubAppEvent;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>卡片表体编辑事件默认处理类
 * </ul>
 * <p>
 * 
 * @param <T1>
 *          <p>
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-3-3 下午04:16:19
 */
public abstract class AbstractCardEventHandler<T1 extends ICardEventListener, T2 extends PubAppEvent> {
  private T2 cardEditEvent;

  /**
   * 是否第一次处理事件
   */
  private boolean isFirstFlag = true;

  /**
   * <字段名称，监听类>
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
   * 方法功能描述：注册事件的监听类，由具体子类实现
   * <p>
   * <b>参数说明</b>
   * 
   * @param listenerMap
   *          <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-3-3 下午04:13:48
   */
  public abstract void registerEventListener(Map<String, T1> listenerMap1);

  public void setCardEditEvent(T2 cardEditEvent) {
    this.cardEditEvent = cardEditEvent;
  }

  public void setFirstFlag(boolean isFirstFlag) {
    this.isFirstFlag = isFirstFlag;
  }
}
