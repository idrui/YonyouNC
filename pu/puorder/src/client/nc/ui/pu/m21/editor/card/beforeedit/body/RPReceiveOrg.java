package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;

public class RPReceiveOrg implements ICardBodyBeforeEditEventListener {

	@Override
	public void beforeEdit(CardBodyBeforeEditEvent event) {
		String pk_order = (String)event.getBillCardPanel().getBodyValueAt(event.getRow(),
            OrderReceivePlanVO.PK_ORDER);
		if(pk_order == null || pk_order.isEmpty()){
			 event.setReturnValue(Boolean.FALSE);
		}
	}

}
