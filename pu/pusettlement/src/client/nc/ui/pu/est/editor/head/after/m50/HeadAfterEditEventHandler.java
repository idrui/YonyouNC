package nc.ui.pu.est.editor.head.after.m50;

import java.util.Map;

import nc.ui.pu.est.editor.head.after.Cesttaxcodeid;
import nc.ui.pu.pub.editor.list.handler.AbstractListHeadAfterEditEventHandler;
import nc.ui.pu.pub.editor.list.listener.IListHeadAfterEditEventListener;
import nc.vo.pu.est.entity.GoodsEstVO;

/**
 * ���Ļ��ܱ�ͷ�༭�¼�
 * 
 * @since 6.0
 * @version 2012-2-17 ����04:29:18
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
