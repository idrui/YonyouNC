/**
 * 
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.util.BusitypeSetter;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.BillForm;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.rule.maintain.DefaultRececountrySetter;
import nc.vo.pu.m25.rule.maintain.DefaultSendcountrySetter;
import nc.vo.pu.m25.rule.maintain.DefaultTaxcountrySetter;
import nc.vo.pu.m25.rule.maintain.InvoiceVatDefaultValueFillRule;
import nc.vo.pu.m25.rule.maintain.InvoiceVatDefaultValueFillRule.ICountrySetter;
import nc.vo.pu.pub.rule.SetPeptRule;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li></li>
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-1-28 下午02:00:31
 */
public class InvoiceAddManualAction extends AbstractReferenceAction {

	private static final long serialVersionUID = 7280021438919507432L;

	private IBillCardPanelEditor editor;

	private AbstractAppModel model;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		this.model.setUiState(UIState.ADD);

		CardEditorHelper helper = new CardEditorHelper(this.getEditor()
				.getBillCardPanel());

		// 设置部门以及vid
		new SetPeptRule(helper, this.model.getContext(), InvoiceHeaderVO.PK_BIZPSN,
				InvoiceHeaderVO.PK_DEPT, InvoiceHeaderVO.PK_DEPT_V).setPsnAndDept();

		List<IPURemoteCallCombinator> rccRuleLst = new ArrayList<IPURemoteCallCombinator>();

		// 注册可合并的远程调用
		this.registerRccRule(rccRuleLst, helper);
		// 执行远程调用规则
		this.doRccRule(rccRuleLst);

	}

	public IBillCardPanelEditor getEditor() {
		return this.editor;
	}

	public AbstractAppModel getModel() {
		return this.model;
	}

	public void setEditor(IBillCardPanelEditor editor) {
		this.editor = editor;
	}

	public void setModel(AbstractAppModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	private void doRccRule(List<IPURemoteCallCombinator> rccRuleLst) {
		for (IPURemoteCallCombinator rccRule : rccRuleLst) {
			if (null != rccRule) {
				rccRule.process();
			}
		}
	}

	private void regiest_bizRule(List<IPURemoteCallCombinator> rccRuleLst) {
		CardEditorHelper billhelper = new CardEditorHelper(
				this.editor.getBillCardPanel());
		new BusitypeSetter(POBillType.Invoice, billhelper, this.model.getContext())
				.manualAddSet(rccRuleLst);
	}

	private void registerRccRule(List<IPURemoteCallCombinator> rccRuleLst,
			CardEditorHelper helper) {
		// 支持批量远程调用－确定业务流程－注册
		this.regiest_bizRule(rccRuleLst);
		this.register_VatDefaultValueRule(rccRuleLst, helper);
	}

	@Override
	protected boolean isActionEnable() {
		return this.model.getUiState() == UIState.NOT_EDIT;
	}

	@Override
	protected boolean isManual() {
		return true;
	}

	private void register_VatDefaultValueRule(
			List<IPURemoteCallCombinator> rccRuleLst, CardEditorHelper helper) {
		IKeyValue[] billhelpers = new CardEditorHelper[] { helper };
		List<ICountrySetter> csetterLst = new ArrayList<InvoiceVatDefaultValueFillRule.ICountrySetter>();
		csetterLst.add(new DefaultSendcountrySetter());
		csetterLst.add(new DefaultRececountrySetter());
		csetterLst.add(new DefaultTaxcountrySetter());
		IPURemoteCallCombinator rccrule = new InvoiceVatDefaultValueFillRule(
				billhelpers, csetterLst);
		rccrule.prepare();
		rccRuleLst.add(rccrule);
	}

}
