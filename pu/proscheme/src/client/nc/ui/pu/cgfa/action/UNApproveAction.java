/**
 * 
 */
package nc.ui.pu.cgfa.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.IQueryForCgfaForward;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pu.cgfa.Cgfa;

/**
 * @author  wangzym
 * @version 2017��4��11�� ����8:50:05
 */
public class UNApproveAction extends UNApproveScriptAction{

	public nc.ui.pubapp.uif2app.view.ShowUpableBillForm editor;
	/* ���� Javadoc��
	 * @see nc.ui.pubapp.uif2app.actions.pflow.ScriptPFlowAction#doAction(java.awt.event.ActionEvent)
	 */
	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO �Զ����ɵķ������
//		���ж��Ƿ������εĵ��ݣ�Ҳ���Ǽ۸�������
		boolean hasForward=false;
		if (this.getModel().getSelectedData()==null) {
			return;
		}else{
			
			AggCgfa agg=(AggCgfa)this.getModel().getSelectedData();
			Cgfa hvo=(Cgfa)agg.getParent();
			String hid=hvo.getAttributeValue("pk_equipment_id").toString();
			IQueryForCgfaForward queryForCgfaForward =NCLocator.getInstance().lookup(IQueryForCgfaForward.class);
		hasForward=queryForCgfaForward.hasForward(hid);
		}
		if (hasForward) {
			MessageDialog.showWarningDlg(editor, "��ʾ", "�������Ѿ��������ε��ݣ���ɾ�����ε��ݺ��ٽ����������");
			return;
		} else {

			super.doAction(e);
		}
		//�ں�̨����������ǰ̨ ����ܲ 2018-06-11
		//CgfaSendApprove.reFreshDate(this.getModel());
	}
	/**
	 * @return editor
	 */
	public nc.ui.pubapp.uif2app.view.ShowUpableBillForm getEditor() {
		return editor;
	}
	/**
	 * @param editor Ҫ���õ� editor
	 */
	public void setEditor(nc.ui.pubapp.uif2app.view.ShowUpableBillForm editor) {
		this.editor = editor;
	}

	/**
	 * 
	 */
	

}
