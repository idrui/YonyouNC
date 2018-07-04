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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单的编辑按钮动作类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-31 上午10:45:07
 */
public class OrderEditAction extends PUEditAction {
	private static final long serialVersionUID = -116097492156325868L;

	private BillForm editor;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		OrderVO order = (OrderVO) this.getModel().getSelectedData();
		/*
		 * add by wandl
		 * 审批中单据修改，判断当前操作用户是否当前审批人，如果不是当前审批人则不允许修改单据
		 */
		ApproveFlowUtil.userCanEditCheck(order, OrderHeaderVO.VTRANTYPECODE);
		if (order.getHVO().getBpublish().booleanValue()
				&& EnumRespStatus.ONCONFIRM.toInteger().equals(
						order.getHVO().getIrespstatus())) {
			ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
					.getNCLangRes().getStrByID("4004030_0", "04004030-0367")/*
																																	 * @res
																																	 * "订单已发布，但门户未响应或已拒绝，不可修改"
																																	 */);
		}

		super.doAction(e);
		if (order.getHVO().getBisreplenish().booleanValue()) {
			this.getEditor()
					.getBillCardPanel()
					.getBillTable(OrderPaymentVO.TABSNAME).setEnabled(false);
		}

		// 控制可编辑性
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
