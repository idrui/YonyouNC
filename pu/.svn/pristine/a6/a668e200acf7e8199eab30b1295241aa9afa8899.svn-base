/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-16 下午01:02:33
 */
package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.ui.pu.m21.rule.ReferenceFilterWarehouse;
import nc.ui.pu.m21.service.OrderReceivePlanModel;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterWareHouseRefUtils;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货收货仓库的编辑前事件处理类。
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-16 下午01:02:33
 */
public class RPReceiveWarehouse implements ICardBodyBeforeEditEventListener {


  private OrderReceivePlanModel model;
  
  public RPReceiveWarehouse(OrderReceivePlanModel model) {
    this.model = model;
  }

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    int row = event.getRow();
    // 收货库存组织
    String pk_org =
        (String) event.getBillCardPanel().getBodyValueAt(event.getRow(),
            OrderReceivePlanVO.PK_ARRVSTOORG);
    if (pk_org == null) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    // 过滤收货仓库参照
    this.filterWarehouseRef(event.getBillCardPanel(), row);

    // 设置返回值
    event.setReturnValue(Boolean.TRUE);
  }

  private void filterWarehouseRef(BillCardPanel panel, int row) {
    // 过滤收货仓库参照

    OrderVO orderVO =  this.model.getOrderVO();
    ReferenceFilterWarehouse filter =
        new ReferenceFilterWarehouse(panel, OrderReceivePlanVO.PK_ARRVSTOORG,
            OrderReceivePlanVO.PK_RECVSTORDOC);
    filter.filterRPWarehouse(orderVO, row);
  }

}
