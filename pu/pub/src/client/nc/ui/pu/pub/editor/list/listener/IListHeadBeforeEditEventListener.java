package nc.ui.pu.pub.editor.list.listener;

import nc.ui.pubapp.uif2app.event.list.ListHeadBeforeEditEvent;

public interface IListHeadBeforeEditEventListener extends IListEventListener {

  public void beforeEdit(ListHeadBeforeEditEvent e);
}
