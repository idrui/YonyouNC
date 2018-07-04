package nc.ui.pu.pub.editor.list.handler;

import nc.ui.pu.pub.editor.list.listener.IListBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListBodyAfterEditEvent;

public abstract class AbstractListBodyAfterEditEventHandler
    extends
    AbstractListEventHandler<IListBodyAfterEditEventListener, ListBodyAfterEditEvent>
    implements IAppEventHandler<ListBodyAfterEditEvent> {

  private ListBodyAfterEditEvent ListBodyAfterEditEvent;

  public AbstractListBodyAfterEditEventHandler() {
    super();
  }

  public ListBodyAfterEditEvent getListBodyAfterEditEvent() {
    return this.ListBodyAfterEditEvent;
  }

  @Override
  public void handleAppEvent(ListBodyAfterEditEvent e) {

    this.setListBodyAfterEditEvent(e);

    // 如果是第一次处理事件，注册事件监听类、注册单价金额关系换算的监听类。
    if (this.isFirstFlag()) {
      this.registerEventListener(this.getListenerMap());

      this.setFirstFlag(false);
    }

    if (this.getEventListener(e) != null) {
      this.getEventListener(e).afterEdit(e);
    }
  }

  public void setListBodyAfterEditEvent(
      ListBodyAfterEditEvent ListBodyAfterEditEvent) {
    this.ListBodyAfterEditEvent = ListBodyAfterEditEvent;
  }

  private IListBodyAfterEditEventListener getEventListener(
      ListBodyAfterEditEvent e) {
    String itemKey = e.getKey();

    return this.getListenerMap().get(itemKey);
  }
}
