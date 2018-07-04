package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.ui.pu.m21.service.OrderReceivePlanModel;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pu.ref.FilterSuppAddressRefUtils;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;

public class RPVenddevaddr implements ICardBodyBeforeEditEventListener {

  private OrderReceivePlanModel model;

  public RPVenddevaddr(OrderReceivePlanModel model) {
    this.model = model;
  }

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    OrderHeaderVO orderHeaderVO = this.model.getOrderVO().getHVO();

    String pk_order = (String) event.getBillCardPanel().getBodyValueAt(
				event.getRow(), OrderReceivePlanVO.PK_ORDER);
		if (pk_order == null || pk_order.isEmpty()) {
			event.setReturnValue(Boolean.FALSE);
			return;
		}
    
    String pk_supplier = orderHeaderVO.getPk_supplier();
    if (pk_supplier != null) {
      FilterSuppAddressRefUtils utils =
          new FilterSuppAddressRefUtils((UIRefPane) event.getBillCardPanel()
              .getBodyItem(OrderReceivePlanVO.VVENDDEVADDR).getComponent());
      utils.filteRefBySupplier(pk_supplier, orderHeaderVO.getPk_org());
      event.setReturnValue(Boolean.TRUE);
    }
    else {
      event.setReturnValue(Boolean.FALSE);
    }
  }

}
