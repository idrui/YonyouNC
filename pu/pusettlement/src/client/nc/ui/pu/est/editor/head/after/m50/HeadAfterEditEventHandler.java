package nc.ui.pu.est.editor.head.after.m50;

import java.util.Map;

import nc.ui.pu.est.editor.head.after.Cesttaxcodeid;
import nc.ui.pu.pub.editor.list.handler.AbstractListHeadAfterEditEventHandler;
import nc.ui.pu.pub.editor.list.listener.IListHeadAfterEditEventListener;
import nc.vo.pu.est.entity.GoodsEstVO;

/**
 * 消耗汇总表头编辑事件
 * 
 * @since 6.0
 * @version 2012-2-17 下午04:29:18
 * @author wuxla
 */
public class HeadAfterEditEventHandler extends
    AbstractListHeadAfterEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, IListHeadAfterEditEventListener> listenerMap) {
    listenerMap.put(GoodsEstVO.CESTTAXCODEID, new Cesttaxcodeid());
  }

}
