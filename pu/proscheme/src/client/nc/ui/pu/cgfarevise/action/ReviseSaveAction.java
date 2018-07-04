/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * 
 * @Title: ReviseSaveAction.java 
 * @Prject: pu
 * @Package: nc.ui.pu.cgfarevise.action 
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018年1月24日 下午1:48:13 
 * @version: V6.5   
 */
package nc.ui.pu.cgfarevise.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.it.m5801.revise.IM5801Revise;
import nc.itf.pu.cgfarevise.ICgfaReviseService;
import nc.ui.ftpub.action.FTActionInitializer;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.query2.model.IModelDataManager;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.IShowMsgConstant;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.vo.ftpub.res.FTActionCode;
import nc.vo.it.m5801.entity.ContractVO;
import nc.vo.it.m5801.enumeration.BillStatus;
import nc.vo.it.pub.exception.LongProtocolTrantypeException;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;

/**
 * @ClassName: ReviseSaveAction
 * @Description: TODO
 * @author: wangzy
 * @date: 2018年1月24日 下午1:48:13
 */
public class ReviseSaveAction extends nc.ui.uif2.actions.SaveAction {

	private static final long serialVersionUID = 893071739244705779L;

	private IModelDataManager dataManager;

	/**
	 * 初始化按钮编码
	 */
	public ReviseSaveAction() {
		this.setBtnName("修订保存");
		this.setCode("revisesave");
	}

	@Override
	public void doAction(ActionEvent actionevent) throws Exception {
		this.doSave(true);
	}

	private void doSave(boolean isSave) {
		AggCgfa[] originBills = new AggCgfa[] { (AggCgfa) this.getModel()
				.getSelectedData() };
		Object value = this.getEditor().getValue();
		this.validate(value);
		AggCgfa[] clientBills = new AggCgfa[] { (AggCgfa) value };
		ClientBillToServer<AggCgfa> util = new ClientBillToServer<AggCgfa>();
		AggCgfa[] fullBills = util.construct(originBills, clientBills);

		ICgfaReviseService service = NCLocator.getInstance().lookup(
				ICgfaReviseService.class);
		try {
			AggCgfa[] revisingBills = service.saveCgfaForRevise(fullBills,
					isSave);
			ClientBillCombinServer<AggCgfa> combin = new ClientBillCombinServer<AggCgfa>();
			combin.combine(clientBills, revisingBills);
			this.getModel().directlyAdd(clientBills[0]);
			this.getDataManager().refresh();
			BillManageModel model = (BillManageModel) this.getModel();
			int index = model.findBusinessData(clientBills[0]);
			model.setSelectedRow(index);
			// this.getModel().directlyDelete(originBills[0]);
			ShowStatusBarMsgUtil.showStatusBarMsg(IShowMsgConstant
					.getSaveSuccessInfo(), this.getModel().getContext());
		} catch (BusinessException ex) {
			ExceptionUtils.wrappException(ex);
		}
		this.getModel().setUiState(UIState.NOT_EDIT);
	}

	/**
	 * 
	 * @return data
	 */
	public IModelDataManager getDataManager() {
		return this.dataManager;
	}

	@Override
	protected boolean isActionEnable() {
		boolean uistate = this.getModel().getUiState() == UIState.EDIT
				|| this.getModel().getUiState() == UIState.ADD;
		ShowUpableBillForm editor = (ShowUpableBillForm) this.getEditor();
		if (editor != null) {
			AggCgfa vo = (AggCgfa) editor.getValue();
			// 修订单据 审批通过的单据才可以被修订
			if (vo != null) {
				String headStatus = (String) vo.getParentVO()
						.getAttributeValue("bill_status");
				return uistate && ("-1".equals(headStatus));
			}
		}
		return uistate;
	}

	/**
	 * 
	 * @param dataManager
	 */
	public void setDataManager(IModelDataManager dataManager) {
		this.dataManager = dataManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Logger.debug("Entering " + this.getClass().toString()
				+ ".actionPerformed");
		this.beforeDoAction();
		try {
			if (this.interceptor == null
					|| this.interceptor.beforeDoAction(this, e)) {
				try {
					this.doAction(e);
					if (this.interceptor != null) {
						this.interceptor.afterDoActionSuccessed(this, e);
					}
				} catch (Exception ex) {
					this.dealBusiException(e, ex);
				}
			}
		} finally {
			Logger.debug("Leaving " + this.getClass().toString()
					+ ".actionPerformed");
		}
	}

	private void dealBusiException(ActionEvent e, Exception ex) {

		if (null != ex.getCause()
				&& null != ex.getCause().getCause()
				&& ex.getCause().getCause() instanceof LongProtocolTrantypeException) {
			int dlgResult = MessageDialog.showYesNoDlg(WorkbenchEnvironment
					.getInstance().getWorkbench().getParent(), null,
					ex.getMessage());
			if (UIDialog.ID_YES == dlgResult) {
				this.doSave(false);
			} else {
				return;
			}
		} else {
			if (this.interceptor == null
					|| this.interceptor.afterDoActionFailed(this, e, ex)) {
				if (this.getExceptionHandler() != null) {
					this.processExceptionHandler(ex);
				} else if (ex instanceof RuntimeException) {
					throw (RuntimeException) ex;
				}

				throw new RuntimeException(ex);
			}
		}

	}

}