/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-19 ����03:32:16
 */
package nc.ui.pu.est.view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.Action;

import nc.ui.pu.est.model.EstUIContext;
import nc.ui.pu.est.model.EstVOBillListPanelValueSetter;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillTabbedPane;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.BillListView;
import nc.ui.scmpub.util.BillCardPanelUtils;
import nc.ui.uif2.AppEvent;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.enumeration.QueryEstType;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pub.bill.BillTempletVO;

import org.apache.commons.collections.CollectionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ݹ���չʾ���棬ȡ���ݹ�Ҳ�ô˽���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-19 ����03:32:16
 */
public class EstEditor extends BillListView {

	/**
   * 
   */
	private static final long serialVersionUID = -5034261411234221332L;

	private BillCardPanel billCardPanel;

	// ����pubapp��δ��뾭���Ķ������ݹ��ڵ㲻��Ҫpubapp�����⴦�����︲дset��get����
	private IBillListPanelValueSetter billListPanelValueSetter;

	private List<Action> bodyActions;

	/**
	 * ���෽����д
	 * 
	 * @see nc.ui.uif2.editor.BillListView#afterEdit(nc.ui.pub.bill.BillEditEvent)
	 */
	@Override
	public void afterEdit(BillEditEvent e) {
		super.afterEdit(e);
		// �ӽ���ͬ��ģ���е�����
		this.synModelValue(e.getRow());
	}

	/**
	 * @return billcardpanel
	 */
	public BillCardPanel getBillCardPanel() {
		return this.billCardPanel;
	}

	@Override
	public IBillListPanelValueSetter getBillListPanelValueSetter() {
		return this.billListPanelValueSetter;
	}

	/**
	 * @return bodyActions
	 */
	public List<Action> getBodyActions() {
		return this.bodyActions;
	}

	/**
	 * ���෽����д
	 * 
	 * @see nc.ui.pubapp.uif2app.view.BillListView#initUI()
	 */
	@Override
	public void initUI() {
		super.initUI();
		this.createBillCardPanel();
		this.initBillCardPanel();
		this.initValuesetter();
	}

	// yanxm5 �ϼ���
	@Override
	public boolean isShowTotalLine() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setBillListPanelValueSetter(
			IBillListPanelValueSetter billListPanelValueSetter) {
		super.setBillListPanelValueSetter(billListPanelValueSetter);
		this.billListPanelValueSetter = billListPanelValueSetter;
	}

	/**
	 * �����������������ñ�����в������������ԡ�
	 * <p>
	 * <b>����˵��</b>
	 * 
	 * @param edit
	 *          <p>
	 * @since 6.0
	 * @author zhyhang
	 * @time 2010-6-15 ����06:10:07
	 */
	public void setBodyActionEditable(boolean edit) {
		if (ListUtil.isEmpty(this.bodyActions)) {
			return;
		}
		for (Action action : this.bodyActions) {
			action.setEnabled(edit);
		}
	}

	/**
	 * @param bodyActions
	 *          Ҫ���õ� bodyActions
	 */
	public void setBodyActions(List<Action> bodyActions) {
		this.bodyActions = bodyActions;
	}

	private void createBillCardPanel() {
		this.billCardPanel = new BillCardPanel();
		BillTempletVO templet = this.getBillListPanel().getBillListData()
				.getBillTempletVO();
		this.billCardPanel.setBillData(new BillData(templet));

		String[] enableItems = new String[] { GoodsEstVO.NESTMNY };
		BillCardPanelUtils cardUtils = new BillCardPanelUtils(
				this.getBillCardPanel());
		// �ſ��ֶε�����
		cardUtils.enableItemsFill(enableItems);
	}

	private EstUIContext getEstUIContext() {
		return (EstUIContext) this.getModel().getContext();
	}

	private void initBillCardPanel() {
		BillListPanel blp = this.getBillListPanel();
		BillCardPanel bcp = this.getBillCardPanel();
		BillTabbedPane lpane = blp.getBodyTabbedPane();
		blp.getBodyUIPanel().remove(lpane);
		BillTabbedPane cpane = bcp.getBodyTabbedPane();
		blp.getBodyUIPanel().add(cpane, BorderLayout.CENTER);
		bcp.setPosMaximized(IBillItem.BODY);
		EstCardPanelInitializer cardInit = new EstCardPanelInitializer(bcp, blp,
				this.getModel());
		cardInit.initCardEventTransfer();
		cardInit.initBodyLineActions(this.getBodyActions());
		// 2012-06-28 tianft ����������������������ԣ�����һͬѧ˵������֤�ˣ���ϲ����
		BillCardPanelUtils cardUtils = new BillCardPanelUtils(bcp);
		cardUtils.disableItemsFill();

		EstEditorInitializer initializer = new EstEditorInitializer(this);
		// ��ʼ��ģ��ͷ�������ݹ����Ⱥ��������ֶξ��ȣ�
		initializer.setStockScale(EstVOUtil.getGoodsEstScaleKeyInfo(),
				EstVOUtil.getStockScaleKeyInfo());
		// ��ʼ��ģ����(�����ݹ�����)
		initializer.setFeeScale(EstVOUtil.getFeeEstScaleKeyInfo());
	}

	private void initValuesetter() {
		EstVOBillListPanelValueSetter vs = (EstVOBillListPanelValueSetter) this
				.getBillListPanelValueSetter();
		vs.setBillcardpanel(this.getBillCardPanel());
	}

	private void setBodyActionEditable() {
		EstUIContext context = this.getEstUIContext();
		QueryEstType esttype = context.getEsttype();
		boolean edit = false;
		if (QueryEstType.FEE_EST == esttype || QueryEstType.GOODS_EST == esttype) {
			edit = CollectionUtils.isNotEmpty(this.getModel().getData());
		}
		this.setBodyActionEditable(edit);
	}

	private void synModelValue(int row) {
		EditorToModelValueSetter setter = new EditorToModelValueSetter(
				this.getBillListPanel(), this.getModel());
		setter.setModelHeadValue(row);
	}

	/**
	 * ���෽����д
	 * 
	 * @see nc.ui.uif2.editor.BillListView#handleDataDeleted(nc.ui.uif2.AppEvent)
	 */
	@Override
	protected void handleDataDeleted(AppEvent event) {
		super.handleDataDeleted(event);
		this.setBodyActionEditable();
	}

	@Override
	protected void setValueSetter() {
		// ���ﲻ��Ҫ��������߼�
		return;
	}

	/**
	 * ���෽����д
	 * 
	 * @see nc.ui.uif2.editor.BillListView#synchronizeDataFromModel()
	 */
	@Override
	protected void synchronizeDataFromModel() {
		this.getBillListPanel().getHeadBillModel().setNeedCalculate(false);
		super.synchronizeDataFromModel();
		this.setBodyActionEditable();
		this.getBillListPanel().getHeadBillModel().setNeedCalculate(true);
	}
}
