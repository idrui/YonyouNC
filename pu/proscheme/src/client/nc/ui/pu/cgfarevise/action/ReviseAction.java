/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * 
 * @Title: ReviseAction.java 
 * @Prject: pu
 * @Package: nc.ui.pu.cgfarevise.action 
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018年1月24日 下午1:32:52 
 * @version: V6.5   
 */
package nc.ui.pu.cgfarevise.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.actions.EditAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmf.pub.keyvalue.CardKeyValue;
import nc.vo.ftpub.res.enumeration.ReviseStatus;
import nc.vo.it.m5801.entity.ContractBVO;
import nc.vo.it.m5801.entity.ContractHVO;
import nc.vo.it.m5801.entity.ContractVO;
import nc.vo.it.m5801.enumeration.BillStatus;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pu.cgfa.Cgfa;
import nc.vo.scmf.pub.keyvalue.IKeyValue;

/**
 * @ClassName: ReviseAction
 * @Description: TODO
 * @author: wangzy
 * @date: 2018年1月24日 下午1:32:52
 */
public class ReviseAction extends EditAction {

	private ShowUpableBillForm editor;

	/**
	 * @Title:ReviseAction
	 * @Description:TODO
	 */
	public ReviseAction() {
		// TODO 自动生成的构造函数存根
		this.setBtnName("修订");
		this.setCode("revise");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		super.doAction(e);
		//需要判断是否有下游，如果有下游就不让他再修订
		BillCardPanel cardPanel = this.editor.getBillCardPanel();
		BillItem billcodeitem = cardPanel.getHeadItem(ContractHVO.VBILLCODE);
		billcodeitem.setEnabled(false);
		//表体不要改动
		cardPanel.getBillModel().setEnabled(false);
	}

	@Override
	protected boolean isActionEnable() {
		if (this.getModel().getSelectedData() != null) {
			AggCgfa vo = (AggCgfa) this.getModel().getSelectedData();
			Cgfa hvo = (Cgfa) vo.getParent();
			//审核状态就可以修订
			if ("1".equals(hvo.getAttributeValue("bill_status"))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param editor
	 *            卡片
	 */
	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}

}
