package nc.ui.pu.pub.editor.list.listener;

import nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent;

public interface IListHeadAfterEditEventListener extends IListEventListener {

  public void afterEdit(ListHeadAfterEditEvent event);
}
