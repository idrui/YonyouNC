package nc.ui.pu.m20.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.bs.trade.business.HYPubBO;
import nc.itf.pu.dw66to20.query.IDW66QueryMaintain;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m20.entity.PraybillVO;

public class RefuseAction extends NCAction {

	private IBillCardPanelEditor editor;

	private AbstractAppModel model;

	private static final long serialVersionUID = 1L;

	public RefuseAction() {
		this.setBtnName("�ܾ�");
		this.setCode("refuse");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// begin ����ܲ 2018-05-23 �����ӵ�������д�ܾ�ԭ��
		String ret = (String) MessageDialog.showInputDlg(model.getContext()
				.getEntranceUI(), "�ܾ�ԭ��", "����д�ܾ�ԭ��:", null, Integer.MAX_VALUE);
		// MessageDialog�淶����ֵΪnullΪ����˷�ȷ����ť�����������ֱֵ�ӵ�ȷ����ť�򷵻ؿմ�
		if (ret == null) {
			return;
		}
		// end

		int[] a = this.editor.getBillCardPanel().getBillTable()
				.getSelectedRows();
		IDW66QueryMaintain sb = NCLocator.getInstance().lookup(
				IDW66QueryMaintain.class);
		// ������Ч��̫�ͣ���ʱ������
		for (int i = 0; i < a.length; i++) {
			int j = a[i];
			String pk_praybill_b = (String) this.editor.getBillCardPanel()
					.getBodyValueAt(j, "pk_praybill_b");

			sb.Changestsreg(pk_praybill_b,ret);
			this.editor.getBillCardPanel().setBodyValueAt("09", j, "sts_req");
			this.editor.getBillCardPanel().setBodyValueAt(ret, j, "vbdef2");
		}
		// ˢһ�鰴ť�߼� 2018-05-23 ����ܲ
		this.model.fireEvent(new AppEvent("FireInTheHole"));
	}

	/**
	 * @description ��ť����ʾ�߼�ʵ���ǿ�����ȥ�ˣ��Ӵ��Ÿ��˰�
	 * @author ����ܲ
	 * @date 2018-05-23
	 */
	@Override
	protected boolean isActionEnable() {
		int[] rows = this.editor.getBillCardPanel().getBillTable()
				.getSelectedRows();
		if (rows == null || rows.length == 0) {
			return false;
		}
		// ����ܾ��˾Ͳ����ٴξܾ���
		for (int i = 0; i < rows.length; i++) {
			int len = rows[i];
			String req = (String) this.editor.getBillCardPanel()
					.getBodyValueAt(len, "sts_req");
			if ("09".equals(req)) {
				return false;
			}
		}
		return super.isActionEnable();
	}

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
		// ��model��ע��һ�¼��� 2018-05-23 ����ܲ
		model.addAppEventListener(this);
	}

}
