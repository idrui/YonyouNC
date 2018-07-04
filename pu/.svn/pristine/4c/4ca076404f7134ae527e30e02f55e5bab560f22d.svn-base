/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-14 下午06:55:01
 */
package nc.ui.pu.m21.editor.card.afteredit;

import java.util.Map;

import nc.ui.pu.m21.editor.card.afteredit.body.RPArrvstoorg;
import nc.ui.pu.m21.editor.card.afteredit.body.RPBodyNum;
import nc.ui.pu.m21.editor.card.afteredit.body.RPReceiveWarehouse;
import nc.ui.pu.m21.editor.card.afteredit.body.RPReceiveaddress;
import nc.ui.pu.m21.editor.card.afteredit.body.RPRowNo;
import nc.ui.pu.m21.editor.card.afteredit.body.RPVvenddevaddr;
import nc.ui.pu.m21.service.OrderReceivePlanModel;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.AbstractRelationCalculateListener;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货计划表体编辑后事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-14 下午06:55:01
 */
public class RPCardBodyAfterEditEventHandler extends
    AbstractCardBodyAfterEditEventHandler {

  private OrderReceivePlanModel model;

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
    // 行号
    listenerMap.put("crowno", new RPRowNo(this.model));
    // 数量
    listenerMap.put(OrderReceivePlanVO.NNUM, new RPBodyNum());
    // 收货库存组织
    listenerMap.put(OrderReceivePlanVO.PK_ARRVSTOORG_V, new RPArrvstoorg());
    // 收货仓库
    listenerMap
        .put(OrderReceivePlanVO.PK_RECVSTORDOC, new RPReceiveWarehouse());
    // 收货地址
    listenerMap.put(OrderReceivePlanVO.PK_RECEIVEADDRESS,
        new RPReceiveaddress());
    // 供应商发货地址
    listenerMap.put(OrderReceivePlanVO.VVENDDEVADDR, new RPVvenddevaddr());

  }

  /**
   * @param model
   *          要设置的 model
   */
  public void setModel(OrderReceivePlanModel model) {
    this.model = model;
  }

}
