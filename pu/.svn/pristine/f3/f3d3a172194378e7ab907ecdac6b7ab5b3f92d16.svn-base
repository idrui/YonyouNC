package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.ui.bd.ref.RefPubUtil;
import nc.ui.pu.m21.service.OrderReceivePlanModel;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pu.ref.FilterCustAddressRefUtils;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;

public class RPReceiveAddress implements ICardBodyBeforeEditEventListener {

	private OrderReceivePlanModel model;

	public RPReceiveAddress(OrderReceivePlanModel model) {
		this.model = model;
	}

	@Override
	public void beforeEdit(CardBodyBeforeEditEvent event) {
		String pk_order = (String) event.getBillCardPanel().getBodyValueAt(
				event.getRow(), OrderReceivePlanVO.PK_ORDER);
		if (pk_order == null || pk_order.isEmpty()) {
			event.setReturnValue(Boolean.FALSE);
			return;
		}
		OrderHeaderVO orderHeaderVO = this.model.getOrderVO().getHVO();
		UIRefPane refPanel = (UIRefPane) event.getBillCardPanel()
				.getBodyItem(OrderReceivePlanVO.PK_RECEIVEADDRESS).getComponent();

		String pk_customer = orderHeaderVO.getPk_recvcustomer();
		if (pk_customer == null) {
			refPanel.setRefModel(RefPubUtil.getRefModel("地址簿"));/* -=notranslate=- */
		} else {
			refPanel.setRefModel(RefPubUtil.getRefModel("客户收货地址"));/* -=notranslate=- */
			FilterCustAddressRefUtils utils = new FilterCustAddressRefUtils(refPanel);
			utils.filteRefByCustomer(pk_customer, null);
		}
	}
}
