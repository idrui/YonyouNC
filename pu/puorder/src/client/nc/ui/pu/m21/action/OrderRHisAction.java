package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m20.IPraybillRevise;
import nc.itf.pu.m21.IOrderEditRecordQuery;
import nc.ui.pu.m21.view.OrderReviseHistoryDlg;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * �ɹ������޶���ʷ�汾Action
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ʾ��ʷ�汾��Ϣ
 * </ul>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2009-12-28 ����04:33:56
 */
public class OrderRHisAction extends NCAction {

	private static final long serialVersionUID = 2588413990103479642L;

	/** �б���ͼ **/
	private ShowUpableBillListView editor;

	/** ��ʷ�汾�Ի��� **/
	private OrderReviseHistoryDlg hisDialog;

	/** ����Ӧ��ģ�� **/
	private AbstractUIAppModel model = null;

	/**
	 * ������
	 */
	public OrderRHisAction() {
		SCMActionInitializer
				.initializeAction(this, SCMActionCode.SCM_REVISEHISTORY);
		// String name = "�޶���ʷ";
		// this.setBtnName(name);
		// this.putValue(INCAction.CODE, name);
		// this.putValue(Action.SHORT_DESCRIPTION, name);
		//
		// ��ݼ���ȷ��
		// int modifiers = Event.ALT_MASK;
		// this.putValue(Action.ACCELERATOR_KEY,
		// KeyStroke.getKeyStroke('H', modifiers));
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		try {
			OrderVO ordervo = (OrderVO) this.editor.getModel().getSelectedData();
			OrderVO[] editRecords = null;
			if (ordervo == null) {
				ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
						.getNCLangRes().getStrByID("4004030_0", "04004030-0039")/*
																																		 * @res
																																		 * "��ѡ��Ҫ��ѯ������"
																																		 */);
			}
			OrderItemVO[] bvos = ordervo.getBVO();
			List<String> pkList = new ArrayList<String>();
			for (OrderItemVO bvo : bvos) {
				pkList.add(bvo.getPk_order_b());
			}
			IOrderEditRecordQuery service = NCLocator.getInstance().lookup(
					IOrderEditRecordQuery.class);
			editRecords = service.queryOrderPrice(pkList);
			this.getHisDialog().setVOs(editRecords);
			this.getHisDialog().showModal();
		} catch (Exception e1) {
			ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
					.getNCLangRes().getStrByID("4004020_0", "04004020-0070")/*
																																	 * @res
																																	 * "��ʾ��ʷ��Ϣ����"
																																	 */);
		}
	}

	/**
	 * ȡ���б���ͼ
	 * 
	 * @return �б���ͼ
	 */
	public ShowUpableBillListView getEditor() {
		return this.editor;
	}

	/**
	 * ȡ����ʷ��Ϣ�Ի���
	 * 
	 * @return ��ʷ��Ϣ�Ի���
	 */
	public OrderReviseHistoryDlg getHisDialog() {

		if (this.hisDialog == null) {
			String pk_org = this.model.getContext().getPk_group();
			String title = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"40040402", "1400404020004")/* @res "�ɹ������޶���ʷ" */;
			this.hisDialog = new OrderReviseHistoryDlg(this.model.getContext()
					.getEntranceUI(), pk_org, title);
		}
		return this.hisDialog;
	}

	/**
	 * ȡ�ù���Ӧ��ģ��
	 * 
	 * @return ����Ӧ��ģ��
	 */
	public AbstractUIAppModel getModel() {
		return this.model;
	}

	/**
	 * ȡ���빺���޶���������
	 * 
	 * @return �빺���޶���������
	 */
	public IPraybillRevise getService() {
		return NCLocator.getInstance().lookup(IPraybillRevise.class);
	}

	/**
	 * �����б���ͼ��
	 * 
	 * @param editor
	 *          �б���ͼ
	 */
	public void setEditor(ShowUpableBillListView editor) {
		this.editor = editor;
	}

	/**
	 * ���ù���Ӧ��ģ�͡�
	 * 
	 * @param model
	 *          ����Ӧ��ģ��
	 */
	public void setModel(AbstractUIAppModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	@Override
	protected boolean isActionEnable() {

		if (null == this.editor) {
			return false;
		}

		int selectRowCount = this.editor.getBillListPanel().getHeadTable()
				.getSelectedRowCount();
		// ��ʷ��ѯ
		if (selectRowCount != 1) {
			return false;
		}

		if (((Integer) this.editor
				.getBillListPanel()
				.getHeadBillModel()
				.getValueAt(
						this.editor.getBillListPanel().getHeadTable().getSelectedRow(),
						"nversion")).floatValue() < 2) {
			return false;
		}

		return true;

	}

}
