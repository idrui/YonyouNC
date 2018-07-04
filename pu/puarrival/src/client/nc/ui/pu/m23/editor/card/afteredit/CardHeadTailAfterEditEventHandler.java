package nc.ui.pu.m23.editor.card.afteredit;

import java.util.Map;

import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;

/**
 * <p>
 * <b>到货单表头编辑事件的处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>处理编辑后事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-24 下午05:00:09
 */
public class CardHeadTailAfterEditEventHandler extends
    AbstractCardHeadTailAfterEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, ICardHeadTailAfterEditEventListener> listenerMap) {

    // 业务员
    // listenerMap.put(ArriveHeaderVO.PK_PUPSNDOC, new PUPersonDoc());
  }

}
