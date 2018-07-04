/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018��5��22�� ����4:38:33 
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
 * @Description:������ҪΪ���ڵ���ܾ���ť�������������������޸�Ϊ��ܾ�ǰһ��
 * @author: wangzy
 * @date: 2018��5��22�� ����4:38:33
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
		this.setBtnName("ȡ���ܾ�");
		this.setCode("unrefuse");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// ��ȡѡ�������
		int[] a = this.editor.getBillCardPanel().getBillTable()
				.getSelectedRows();
		IDW66QueryMaintain service = NCLocator.getInstance().lookup(
				IDW66QueryMaintain.class);
		// ѡ��ı����е�pk
		String[] bpks = new String[a.length];
		for (int i = 0; i < a.length; i++) {
			int j = a[i];
			String pk_praybill_b = (String) this.editor.getBillCardPanel()
					.getBodyValueAt(j, "pk_praybill_b");
			bpks[i] = pk_praybill_b;
		}
		// ���ݿ����sts_req��ֵΪ03
		service.UnRefuse(bpks);
		// ���µ�ǰ�����ֵ
		for (int i = 0; i < a.length; i++) {
			int j = a[i];
			this.editor.getBillCardPanel().setBodyValueAt("01", j, "sts_req");
			this.editor.getBillCardPanel().setBodyValueAt(null, j, "vbdef2");
		}
		//ˢһ�£���֤��ť��״̬
		this.model.fireEvent(new AppEvent("�����������ע���ϱ�������ô�����ܵö�����"));

	}

	@Override
	protected boolean isActionEnable() {
		int[] rows = this.editor.getBillCardPanel().getBillTable()
				.getSelectedRows();
		if (rows == null || rows.length == 0) {
			return false;
		}
		// �����ǰ����ѡ����ֻҪ�в��Ǿܾ�״̬�ı����У�����ʹ��
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
