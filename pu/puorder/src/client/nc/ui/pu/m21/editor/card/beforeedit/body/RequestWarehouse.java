package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.ui.pu.m21.rule.ReferenceFilterWarehouse;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderItemVO;

public class RequestWarehouse implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    int row = event.getRow();
    // ��������֯
    String pk_org =
        (String) event.getBillCardPanel().getBodyValueAt(event.getRow(),
            OrderItemVO.PK_REQSTOORG);
    if (pk_org == null) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    // ���˲ֿ����
    this.filterWarehouseRef(event.getBillCardPanel(), row);

    // ���÷���ֵ
    event.setReturnValue(Boolean.TRUE);

  }

  private void filterWarehouseRef(BillCardPanel panel, int row) {
    // ��������ֿ����
    ReferenceFilterWarehouse filter =
        new ReferenceFilterWarehouse(panel, OrderItemVO.PK_REQSTOORG,
            OrderItemVO.PK_REQSTORDOC);
    filter.reqFilter(row);
  }

}
