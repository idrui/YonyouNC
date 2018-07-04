package nc.ui.pu.pub.editor.list.handler;

import nc.ui.pu.pub.editor.list.listener.IListBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListBodyBeforeEditEvent;

/**
 * <p>
 * <b>到货单表体编辑事件的代理处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>处理编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-24 下午05:00:09
 */
public abstract class AbstractListBodyBeforeEditEventHandler
    extends
    AbstractListEventHandler<IListBodyBeforeEditEventListener, ListBodyBeforeEditEvent>
    implements IAppEventHandler<ListBodyBeforeEditEvent> {

  public AbstractListBodyBeforeEditEventHandler() {
    super();
  }

  public void afterHandleEvent() {
    // 子类可以复写
  }

  public void beforeHandleEvent() {
    // 子类可以复写
  }

  @Override
  public void handleAppEvent(ListBodyBeforeEditEvent e) {
    this.setListEditEvent(e);

    // 如果是第一次处理事件，注册事件监听类
    if (this.isFirstFlag()) {
      this.registerEventListener(this.getListenerMap());
      this.setFirstFlag(false);
    }

    if (e.getReturnValue() == null) {
      e.setReturnValue(Boolean.TRUE);
    }

    this.beforeHandleEvent();

    // 没有配置,直接返回
    if (this.getEventListener(e) != null) {
      this.getEventListener(e).beforeEdit(e);
    }

    this.afterHandleEvent();
  }

  private IListBodyBeforeEditEventListener getEventListener(
      ListBodyBeforeEditEvent e) {
    String itemKey = e.getKey();
    return this.getListenerMap().get(itemKey);
  }
}
