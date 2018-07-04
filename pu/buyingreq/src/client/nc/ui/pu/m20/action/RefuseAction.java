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
		this.setBtnName("拒绝");
		this.setCode("refuse");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// begin 王梓懿 2018-05-23 新增加弹出框填写拒绝原因
		String ret = (String) MessageDialog.showInputDlg(model.getContext()
				.getEntranceUI(), "拒绝原因", "请填写拒绝原因:", null, Integer.MAX_VALUE);
		// MessageDialog规范返回值为null为点击了非确定按钮，如果不输入值直接点确定按钮则返回空串
		if (ret == null) {
			return;
		}
		// end

		int[] a = this.editor.getBillCardPanel().getBillTable()
				.getSelectedRows();
		IDW66QueryMaintain sb = NCLocator.getInstance().lookup(
				IDW66QueryMaintain.class);
		// 这样做效率太低，暂时不改了
		for (int i = 0; i < a.length; i++) {
			int j = a[i];
			String pk_praybill_b = (String) this.editor.getBillCardPanel()
					.getBodyValueAt(j, "pk_praybill_b");

			sb.Changestsreg(pk_praybill_b,ret);
			this.editor.getBillCardPanel().setBodyValueAt("09", j, "sts_req");
			this.editor.getBillCardPanel().setBodyValueAt(ret, j, "vbdef2");
		}
		// 刷一遍按钮逻辑 2018-05-23 王梓懿
		this.model.fireEvent(new AppEvent("FireInTheHole"));
	}

	/**
	 * @description 按钮的显示逻辑实在是看不过去了，捎带着改了吧
	 * @author 王梓懿
	 * @date 2018-05-23
	 */
	@Override
	protected boolean isActionEnable() {
		int[] rows = this.editor.getBillCardPanel().getBillTable()
				.getSelectedRows();
		if (rows == null || rows.length == 0) {
			return false;
		}
		// 如果拒绝了就不让再次拒绝了
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
		// 在model里注册一下监听 2018-05-23 王梓懿
		model.addAppEventListener(this);
	}

}
