package nc.ui.pu.m23.check.editor.list.afteredit;

import java.util.Map;

import nc.ui.pu.pub.editor.list.handler.AbstractListHeadAfterEditEventHandler;
import nc.ui.pu.pub.editor.list.listener.IListHeadAfterEditEventListener;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.pu.m23.entity.ArriveItemVO;

/**
 * <p>
 * <b>到货检验表头编辑事件的处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>处理编辑后事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author guizhw
 * @time 2011-10-13 上午11:20:09
 */

public class ListHeadAfterEditEventHandler extends
    AbstractListHeadAfterEditEventHandler {
  private BillManageModel model;

  public BillManageModel getModel() {
    return this.model;
  }

  @Override
  public void registerEventListener(
      Map<String, IListHeadAfterEditEventListener> listenerMap) {
    NumChangeHandler numHandler = new NumChangeHandler(this.model);
    listenerMap.put(ArriveItemVO.NCHECKNUM, numHandler);
    listenerMap.put(ArriveItemVO.NWILLELIGNUM, numHandler);
    listenerMap.put(ArriveItemVO.NWILLNOTELIGNUM, numHandler);
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

}
