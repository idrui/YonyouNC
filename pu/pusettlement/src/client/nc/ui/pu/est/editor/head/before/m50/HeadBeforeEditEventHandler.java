package nc.ui.pu.est.editor.head.before.m50;

import java.util.Map;

import nc.ui.pu.est.editor.head.before.Cffileid;
import nc.ui.pu.est.editor.head.before.Ctaxcodeid;
import nc.ui.pu.est.editor.head.before.Nestcaltaxmny;
import nc.ui.pu.pub.editor.list.handler.AbstractListHeadBeforeEditEventHandler;
import nc.ui.pu.pub.editor.list.listener.IListHeadBeforeEditEventListener;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;

/**
 * 消耗汇总编辑前事件
 * 
 * @since 6.0
 * @version 2012-3-16 上午10:22:52
 * @author wuxla
 */
public class HeadBeforeEditEventHandler extends
    AbstractListHeadBeforeEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, IListHeadBeforeEditEventListener> listenerMap) {
    // 计税金额
    listenerMap.put(GoodsEstVO.NESTCALTAXMNY, new Nestcaltaxmny());
    // 税码
    listenerMap.put(GoodsEstVO.CESTTAXCODEID, new Ctaxcodeid());
    // 特征码
    listenerMap.put(InitialEstItemVO.CFFILEID, new Cffileid());
  }

}
