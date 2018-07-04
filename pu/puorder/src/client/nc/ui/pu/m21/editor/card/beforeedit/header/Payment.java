package nc.ui.pu.m21.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderHeaderVO;

public class Payment implements ICardHeadTailBeforeEditEventListener {

	@Override
	public void beforeEdit(CardHeadTailBeforeEditEvent e) {
		Boolean bisreplenish = (Boolean) e.getBillCardPanel()
				.getHeadItem(OrderHeaderVO.BISREPLENISH).getValueObject();
		if (bisreplenish.booleanValue()) {
			e.setReturnValue(Boolean.FALSE);
		}
		String pk_org = e.getContext().getPk_org();
		UIRefPane refpane = (UIRefPane) e.getBillCardPanel()
				.getHeadItem(e.getKey()).getComponent();
		refpane.getRefModel().setPk_org(pk_org);
	}

}
