/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018��3��22�� ����4:29:12 
 * @version: V6.5   
 */
package nc.ui.pu.cgfa.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction;

/** 
 * @Description: TODO
 * @author: wangzy
 * @date: 2018��3��22�� ����4:29:12 
 */
public class UNCommitAction extends UnCommitScriptAction {

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO �Զ����ɵķ������
		super.doAction(e);
		//�ں�̨������������ǰ̨ ����ܲ 2018-06-11
		//CgfaSendApprove.reFreshDate(this.getModel());
	}

}