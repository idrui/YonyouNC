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
 * @version 2017年4月11日 上午8:50:05
 */
public class UNApproveAction extends UNApproveScriptAction{

	public nc.ui.pubapp.uif2app.view.ShowUpableBillForm editor;
	/* （非 Javadoc）
	 * @see nc.ui.pubapp.uif2app.actions.pflow.ScriptPFlowAction#doAction(java.awt.event.ActionEvent)
	 */
	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
//		先判断是否有下游的单据，也就是价格审批单
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
			MessageDialog.showWarningDlg(editor, "提示", "本单据已经生成下游单据，请删除下游单据后再进行弃审操作");
			return;
		} else {

			super.doAction(e);
		}
		//在后台处理，不放在前台 王梓懿 2018-06-11
		//CgfaSendApprove.reFreshDate(this.getModel());
	}
	/**
	 * @return editor
	 */
	public nc.ui.pubapp.uif2app.view.ShowUpableBillForm getEditor() {
		return editor;
	}
	/**
	 * @param editor 要设置的 editor
	 */
	public void setEditor(nc.ui.pubapp.uif2app.view.ShowUpableBillForm editor) {
		this.editor = editor;
	}

	/**
	 * 
	 */
	

}
