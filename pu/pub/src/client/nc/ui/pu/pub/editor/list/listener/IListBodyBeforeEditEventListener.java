package nc.ui.pu.pub.editor.list.listener;

import nc.ui.pubapp.uif2app.event.list.ListBodyBeforeEditEvent;

public interface IListBodyBeforeEditEventListener extends IListEventListener {

  public void beforeEdit(ListBodyBeforeEditEvent event);
}
