
package nc.ui.pu.cgfa.ace.event;

import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.bd.ref.model.SupplierDefaultRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.exchange.CalcExchgCardPanelSetter;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

/**
 * @author wangzym
 * @version 2017年4月24日 上午11:09:13
 */
public class CardHeadTailBeforeEditHandler implements
		IAppEventHandler<CardHeadTailBeforeEditEvent> {
	private ShowUpableBillForm billForm;

	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(
			nc.ui.pubapp.uif2app.view.ShowUpableBillForm billForm) {
		this.billForm = billForm;

	}

	@Override
	public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
		// TODO 自动生成的方法存根
		// 如果选择供应商则支持多选
		if (e.getKey().equals("supplier")) {
			UIRefPane ref = (UIRefPane) e.getBillCardPanel()
					.getHeadItem("supplier").getComponent();
			ref.setMultiSelectedEnabled(true);

		}

		e.setReturnValue(Boolean.TRUE);

	}

	public CardHeadTailBeforeEditHandler() {
		// TODO 自动生成的构造函数存根
	}

}
