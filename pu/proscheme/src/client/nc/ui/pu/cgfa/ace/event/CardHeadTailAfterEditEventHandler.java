/**
 * 
 */
package nc.ui.pu.cgfa.ace.event;

import java.util.ArrayList;
import java.util.List;

import nc.md.data.access.NCObject;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.bd.ref.model.SupplierDefaultRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.bd.supplier.SupplierVO;

/**
 * @author wangzym
 * @version 2017年4月24日 上午11:10:35
 */
public class CardHeadTailAfterEditEventHandler implements
		IAppEventHandler<CardHeadTailAfterEditEvent> {
	private nc.ui.pubapp.uif2app.view.ShowUpableBillForm billForm;

	public nc.ui.pubapp.uif2app.view.ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(
			nc.ui.pubapp.uif2app.view.ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}

	public CardHeadTailAfterEditEventHandler() {
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		// TODO 自动生成的方法存根
		// TODO 自动生成的方法存根
		// 如果选择供应商则支持多选
		if (e.getKey().equals("supplier")) {

			UIRefPane refPane = (UIRefPane) e.getBillCardPanel()
					.getHeadItem("supplier").getComponent();
			nc.ui.bd.ref.model.SupplierDefaultRefModel model = (SupplierDefaultRefModel) refPane
					.getRefModel();
			String[] pks = model.getPkValues();
			String codes = getCode(pks);
			codes=codes.substring(1, codes.length()-1);
			e.getBillCardPanel().setHeadItem("suppliercode", codes);
		}

	}

	/**
	 * @param pks
	 * @return
	 */
	private String getCode(String[] pks) {
		// TODO 自动生成的方法存根
		List<String> code=new ArrayList<String>();
		try {
			NCObject[] codes = MDPersistenceService
					.lookupPersistenceQueryService().queryBillOfNCObjectByPKs(
							SupplierVO.class, pks, new String[] { "code" },
							false);
			for (NCObject ncObject : codes) {
				SupplierVO vo=(SupplierVO) ncObject.getContainmentObject();
				code.add(vo.getCode());
			}

		} catch (MetaDataException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}if (code.size()==0) {
			return null;
		}
		return code.toString();
	}

}
