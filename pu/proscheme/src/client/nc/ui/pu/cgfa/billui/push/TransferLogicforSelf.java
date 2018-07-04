/**   
 * Copyright  2017 Yonyou. All rights reserved.
 * 
 * @Title: TransferLogicforSelf.java 
 * @Prject: SCM_SO
 * @Package: nc.ui.so.m30.billui.push 
 * @Description: TODO
 * @author: wangzy   
 * @date: 2017��12��27�� ����11:18:49 
 * @version: V6.5   
 */
package nc.ui.pu.cgfa.billui.push;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.billref.dest.DefaultBillDataLogic;
import nc.ui.so.m30.billui.action.printaction.SaleOrderPreviewAction;
import nc.ui.uif2.UIState;

/**
 * @ClassName: TransferLogicforSelf
 * @Description: TODO
 * @author: wangzy
 * @date: 2018��05��31�� ����11:18:49
 */
public class TransferLogicforSelf extends DefaultBillDataLogic {
	@Override
	public void doTransferAddLogic(Object selectedData) {

		// ���������õ�������
		super.doTransferAddLogic(selectedData);
		super.getBillForm().getModel().setUiState(UIState.NOT_EDIT);
		this.getBillForm().getModel().initModel(selectedData);
	}
}