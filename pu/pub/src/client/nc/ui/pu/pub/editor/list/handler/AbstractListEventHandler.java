package nc.ui.pu.pub.editor.list.handler;

import java.util.HashMap;
import java.util.Map;

import nc.ui.pu.pub.editor.list.listener.IListEventListener;
import nc.ui.pubapp.uif2app.event.PubAppEvent;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>列表编辑事件默认处理类
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
public abstract class AbstractListEventHandler<T1 extends IListEventListener, T2 extends PubAppEvent> {
  // 是否第一次处理事件
  private boolean isFirstFlag = true;

  private T2 listEditEvent;

  /**
   * <字段名称，监听类>
   */
  private Map<String, T1> listenerMap;

  public AbstractListEventHandler() {
    super();
  }

  public T2 getListEditEvent() {
    return this.listEditEvent;
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

  public abstract void registerEventListener(Map<String, T1> listenerMap1);

  public void setFirstFlag(boolean isFirstFlag) {
    this.isFirstFlag = isFirstFlag;
  }

  public void setListEditEvent(T2 listEditEvent) {
    this.listEditEvent = listEditEvent;
  }
}
