package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.ui.pu.m21.rule.ReferenceFilterWarehouse;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>收货仓库的编辑前事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-31 下午01:21:16
 */
public class ReceiveWarehouse implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    int row = event.getRow();
    // 需求库存组织
    String pk_org =
        (String) event.getBillCardPanel().getBodyValueAt(event.getRow(),
            OrderItemVO.PK_ARRVSTOORG);
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
    ReferenceFilterWarehouse filter =
        new ReferenceFilterWarehouse(panel, OrderItemVO.PK_ARRVSTOORG,
            OrderItemVO.PK_RECVSTORDOC);
    filter.filter(row);
  }

}
