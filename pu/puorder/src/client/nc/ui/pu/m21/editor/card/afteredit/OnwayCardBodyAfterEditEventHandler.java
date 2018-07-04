/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-19 下午06:27:07
 */
package nc.ui.pu.m21.editor.card.afteredit;

import java.util.Map;

import nc.ui.pu.m21.editor.card.afteredit.body.SendOutNum;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.AbstractRelationCalculateListener;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-19 下午06:27:07
 */
public class OnwayCardBodyAfterEditEventHandler extends
    AbstractCardBodyAfterEditEventHandler {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.handler.AbstractCardBodyAfterEditEventHandler#getCalculateListener()
   */
  @Override
  public AbstractRelationCalculateListener getCalculateListener() {
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.handler.AbstractCardEventHandler#registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardBodyAfterEditEventListener> listenerMap) {
    // 本次发货数量
    listenerMap.put(OrderOnwayItemVO.NSENDOUTNUM, new SendOutNum());
  }
}
