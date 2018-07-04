/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-14 下午12:53:07
 */
package nc.ui.pu.m21.editor.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m21.editor.card.beforeedit.body.RPReceiveAddress;
import nc.ui.pu.m21.editor.card.beforeedit.body.RPReceiveOrg;
import nc.ui.pu.m21.editor.card.beforeedit.body.RPReceiveWarehouse;
import nc.ui.pu.m21.editor.card.beforeedit.body.RPRowNo;
import nc.ui.pu.m21.editor.card.beforeedit.body.RPVenddevaddr;
import nc.ui.pu.m21.service.OrderReceivePlanModel;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货计划编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-14 下午12:53:07
 */
public class RPCardBodyBeforeEditEventHandler extends
    AbstractCardBodyBeforeEditEventHandler {

  private OrderReceivePlanModel model;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.handler.AbstractCardEventHandler#registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {
  	// 库存组织等字段编辑性控制，在选择订单号之前不允许编辑。
  	listenerMap
  	.put(OrderReceivePlanVO.PK_ARRVSTOORG_V, new RPReceiveOrg());
  	listenerMap
  	.put(OrderReceivePlanVO.NASTNUM, new RPReceiveOrg());
  	listenerMap
  	.put(OrderReceivePlanVO.DPLANARRVDATE, new RPReceiveOrg());
  	listenerMap
  	.put(OrderReceivePlanVO.CDEVAREAID, new RPReceiveOrg());
  	listenerMap
  	.put(OrderReceivePlanVO.CDEVADDRID, new RPReceiveOrg());
  	listenerMap
  	.put(OrderReceivePlanVO.CVENDDEVADDRID, new RPReceiveOrg());
  	listenerMap
  	.put(OrderReceivePlanVO.CVENDDEVAREAID, new RPReceiveOrg());
    // 收货仓库
    listenerMap
        .put(OrderReceivePlanVO.PK_RECVSTORDOC, new RPReceiveWarehouse(this.model));
    // 行号
    listenerMap.put("crowno", new RPRowNo());
    // 收货地址
    listenerMap.put(OrderReceivePlanVO.PK_RECEIVEADDRESS, new RPReceiveAddress(
        this.model));
    // 供应商发货地址
    listenerMap.put(OrderReceivePlanVO.VVENDDEVADDR, new RPVenddevaddr(
        this.model));
  }

  public void setModel(OrderReceivePlanModel model) {
    this.model = model;
  }
}
