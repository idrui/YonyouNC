/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-14 上午10:46:18
 */
package nc.ui.pu.m21.editor.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m21.editor.card.beforeedit.body.BillCodeEnable;
import nc.ui.pu.m21.editor.card.beforeedit.body.DateEnable;
import nc.ui.pu.m21.editor.card.beforeedit.body.NumEnable;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>对方确认编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-7-14 上午10:46:18
 */
public class ConfirmCardBodyBeforeEditEventHandler extends
    AbstractCardBodyBeforeEditEventHandler {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.handler.AbstractCardEventHandler#registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {
    listenerMap.put(OrderItemVO.VVENDORORDERCODE, new BillCodeEnable());
    listenerMap.put(OrderItemVO.VVENDORORDERROW, new BillCodeEnable());
    listenerMap.put(OrderItemVO.NCONFIRMNUM, new NumEnable());
    listenerMap.put(OrderItemVO.DCONFIRMDATE, new DateEnable());
  }

}
