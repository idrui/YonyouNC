/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-1 下午04:43:48
 */
package nc.ui.pu.m21.editor.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m21.editor.card.beforeedit.body.SetOnwayFieldEnable;
import nc.ui.pu.m21.editor.card.beforeedit.body.SupplierFilter;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>装运编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-9-1 下午04:43:48
 */
public class OutcustomCardBodyBeforeEditEventHandler extends
    AbstractCardBodyBeforeEditEventHandler {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.handler.AbstractCardEventHandler#registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {

    // 装运单据号
    listenerMap.put(OrderOnwayItemVO.VBILLCODE, new SetOnwayFieldEnable(
        PoTransTypeVO.BOUTCUSTOMCODE));

    // 装运日期
    listenerMap.put(OrderOnwayItemVO.DBILLDATE, new SetOnwayFieldEnable(
        PoTransTypeVO.BOUTCUSTOMDATE));

    listenerMap.put(OrderOnwayItemVO.CCARRIER, new SupplierFilter());

  }

}
