/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * 
 * @Title: ReviseAction.java 
 * @Prject: pu
 * @Package: nc.ui.pu.cgfarevise.action 
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018��1��24�� ����1:32:52 
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
 * @date: 2018��1��24�� ����1:32:52
 */
public class ReviseAction extends EditAction {

	private ShowUpableBillForm editor;

	/**
	 * @Title:ReviseAction
	 * @Description:TODO
	 */
	public ReviseAction() {
		// TODO �Զ����ɵĹ��캯�����
		this.setBtnName("�޶�");
		this.setCode("revise");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		super.doAction(e);
		//��Ҫ�ж��Ƿ������Σ���������ξͲ��������޶�
		BillCardPanel cardPanel = this.editor.getBillCardPanel();
		BillItem billcodeitem = cardPanel.getHeadItem(ContractHVO.VBILLCODE);
		billcodeitem.setEnabled(false);
		//���岻Ҫ�Ķ�
		cardPanel.getBillModel().setEnabled(false);
	}

	@Override
	protected boolean isActionEnable() {
		if (this.getModel().getSelectedData() != null) {
			AggCgfa vo = (AggCgfa) this.getModel().getSelectedData();
			Cgfa hvo = (Cgfa) vo.getParent();
			//���״̬�Ϳ����޶�
			if ("1".equals(hvo.getAttributeValue("bill_status"))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param editor
	 *            ��Ƭ
	 */
	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}

}
