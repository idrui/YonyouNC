package nc.ui.pu.pub.editor.list.handler;

import nc.ui.pu.pub.editor.list.listener.IListHeadAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent;

public abstract class AbstractListHeadAfterEditEventHandler
    extends
    AbstractListEventHandler<IListHeadAfterEditEventListener, ListHeadAfterEditEvent>
    implements IAppEventHandler<ListHeadAfterEditEvent> {

  public AbstractListHeadAfterEditEventHandler() {
    super();
  }

  @Override
  public void handleAppEvent(ListHeadAfterEditEvent e) {

    this.setListEditEvent(e);

    // 如果是第一次处理事件，注册事件监听类
    if (this.isFirstFlag()) {
      this.registerEventListener(this.getListenerMap());

      this.setFirstFlag(false);
    }

    // 没有配置,直接返回
    if (this.getEventListener(e) == null) {
      return;
    }

    // 编辑后事件
    this.getEventListener(e).afterEdit(e);
  }

  private IListHeadAfterEditEventListener getEventListener(
      ListHeadAfterEditEvent e) {
    String itemKey = e.getKey();

    return this.getListenerMap().get(itemKey);
  }
}
