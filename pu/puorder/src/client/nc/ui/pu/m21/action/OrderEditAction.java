package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.m21.rule.EditableSetter;
import nc.ui.pu.pub.action.PUEditAction;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumRespStatus;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ������ı༭��ť������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-31 ����10:45:07
 */
public class OrderEditAction extends PUEditAction {
	private static final long serialVersionUID = -116097492156325868L;

	private BillForm editor;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		OrderVO order = (OrderVO) this.getModel().getSelectedData();
		/*
		 * add by wandl
		 * �����е����޸ģ��жϵ�ǰ�����û��Ƿ�ǰ�����ˣ�������ǵ�ǰ�������������޸ĵ���
		 */
		ApproveFlowUtil.userCanEditCheck(order, OrderHeaderVO.VTRANTYPECODE);
		if (order.getHVO().getBpublish().booleanValue()
				&& EnumRespStatus.ONCONFIRM.toInteger().equals(
						order.getHVO().getIrespstatus())) {
			ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
					.getNCLangRes().getStrByID("4004030_0", "04004030-0367")/*
																																	 * @res
																																	 * "�����ѷ��������Ż�δ��Ӧ���Ѿܾ��������޸�"
																																	 */);
		}

		super.doAction(e);
		if (order.getHVO().getBisreplenish().booleanValue()) {
			this.getEditor()
					.getBillCardPanel()
					.getBillTable(OrderPaymentVO.TABSNAME).setEnabled(false);
		}

		// ���ƿɱ༭��
		this.getEditor().getBillCardPanel().getBodyTabbedPane().setSelectedIndex(0);
		new EditableSetter(this.getEditor().getBillCardPanel()).setEditableAll();
	}

	public BillForm getEditor() {
		return this.editor;
	}

	public void setEditor(BillForm editor) {
		this.editor = editor;
	}

	@Override
	protected boolean isActionEnable() {
		boolean isEnable = super.isActionEnable();
		if (isEnable) {
			OrderVO order = (OrderVO) this.getModel().getSelectedData();
			isEnable &= ApproveFlowUtil.isCanEdit(order);
		}
		return isEnable;
	}
}
