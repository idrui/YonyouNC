/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018年5月22日 下午4:38:33 
 * @version: V6.5   
 */
package nc.ui.pu.m20.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.dw66to20.query.IDW66QueryMaintain;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.ui.uif2.model.AbstractAppModel;

/**
 * @Description:本类主要为了在点击拒绝按钮后的逆向操作，将数据修改为与拒绝前一致
 * @author: wangzy
 * @date: 2018年5月22日 下午4:38:33
 */
public class UnRefuseAction extends NCAction {

	private IBillCardPanelEditor editor;

	private AbstractAppModel model;

	public IBillCardPanelEditor getEditor() {
		return editor;
	}

	public void setEditor(IBillCardPanelEditor editor) {
		this.editor = editor;
	}

	public AbstractAppModel getModel() {
		return model;
	}

	public void setModel(AbstractAppModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	public UnRefuseAction() {
		this.setBtnName("取消拒绝");
		this.setCode("unrefuse");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// 获取选择表体行
		int[] a = this.editor.getBillCardPanel().getBillTable()
				.getSelectedRows();
		IDW66QueryMaintain service = NCLocator.getInstance().lookup(
				IDW66QueryMaintain.class);
		// 选择的表体行的pk
		String[] bpks = new String[a.length];
		for (int i = 0; i < a.length; i++) {
			int j = a[i];
			String pk_praybill_b = (String) this.editor.getBillCardPanel()
					.getBodyValueAt(j, "pk_praybill_b");
			bpks[i] = pk_praybill_b;
		}
		// 数据库更新sts_req的值为03
		service.UnRefuse(bpks);
		// 更新当前界面的值
		for (int i = 0; i < a.length; i++) {
			int j = a[i];
			this.editor.getBillCardPanel().setBodyValueAt("01", j, "sts_req");
			this.editor.getBillCardPanel().setBodyValueAt(null, j, "vbdef2");
		}
		//刷一下，保证按钮的状态
		this.model.fireEvent(new AppEvent("如果参数不在注释上标明，那么这个框架得多垃圾"));

	}

	@Override
	protected boolean isActionEnable() {
		int[] rows = this.editor.getBillCardPanel().getBillTable()
				.getSelectedRows();
		if (rows == null || rows.length == 0) {
			return false;
		}
		// 如果当前单据选中了只要有不是拒绝状态的表体行，不让使用
		for (int i = 0; i < rows.length; i++) {
			int len = rows[i];
			String req = (String) this.editor.getBillCardPanel()
					.getBodyValueAt(len, "sts_req");
			if (!"09".equals(req)) {
				return false;
			}
		}
		return super.isActionEnable();
	}

}
