package nc.ui.pu.m25.editor.card.beforeedit.body;

import nc.ui.bd.feature.ref.model.FFileSkuCodeRefModel;
import nc.ui.ml.NCLangRes;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.ClientContext;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.bd.feature.ffile.param.FFileDlgParam;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class Cffileid implements ICardBodyBeforeEditEventListener {

	@Override
	public void beforeEdit(CardBodyBeforeEditEvent event) {
		BillCardPanel panel = event.getBillCardPanel();
		String vsourcecode = (String) panel.getBodyValueAt(event.getRow(),
				InvoiceItemVO.VSOURCECODE);
		if (vsourcecode == null || vsourcecode.isEmpty()) {
			BillCardPanel cardPanel = event.getBillCardPanel();
			IKeyValue keyvalue = new CardEditorHelper(cardPanel);
			int row = event.getRow();
			String cmaterialvid = (String) keyvalue.getBodyValue(row,
					InvoiceItemVO.PK_MATERIAL);
			if (PubAppTool.isNull(cmaterialvid)) {
				ShowStatusBarMsgUtil.showStatusBarMsg(NCLangRes.getInstance()
						.getStrByID("4006011_0", "04006011-0508")/* 请先录入物料 */, event
						.getContext());
				event.setReturnValue(false);
			}
			UIRefPane uiPane = (UIRefPane) cardPanel.getBodyItem(event.getKey())
					.getComponent();
			FFileDlgParam param = new FFileDlgParam();
			param.setCmaterialvid(cmaterialvid);
			param.setCmaterialid((String) keyvalue.getBodyValue(row,
					InvoiceItemVO.PK_SRCMATERIAL));
			param.setPk_group((String) keyvalue
					.getHeadValue(InvoiceHeaderVO.PK_GROUP));
			if (event.getContext() instanceof ClientContext) {
				param.setLoginContext(((ClientContext) event.getContext())
						.convertLoginContext());
			} else {
				param.setLoginContext(event.getContext());
			}
			FFileSkuCodeRefModel refModel = (FFileSkuCodeRefModel) uiPane
					.getRefModel();
			refModel.setParams(param);
			refModel.setUiRefPane(uiPane);
			refModel.reset();
			event.setReturnValue(Boolean.TRUE);
		} else {
			event.setReturnValue(Boolean.FALSE);
		}
	}

}
