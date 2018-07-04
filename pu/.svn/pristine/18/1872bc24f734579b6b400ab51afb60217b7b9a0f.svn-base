/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-19 下午03:32:16
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估的展示界面，取消暂估也用此界面
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-19 下午03:32:16
 */
public class EstEditor extends BillListView {

	/**
   * 
   */
	private static final long serialVersionUID = -5034261411234221332L;

	private BillCardPanel billCardPanel;

	// 鉴于pubapp这段代码经常改动，而暂估节点不需要pubapp的特殊处理，这里覆写set，get方法
	private IBillListPanelValueSetter billListPanelValueSetter;

	private List<Action> bodyActions;

	/**
	 * 父类方法重写
	 * 
	 * @see nc.ui.uif2.editor.BillListView#afterEdit(nc.ui.pub.bill.BillEditEvent)
	 */
	@Override
	public void afterEdit(BillEditEvent e) {
		super.afterEdit(e);
		// 从界面同步模型中的数据
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
	 * 父类方法重写
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

	// yanxm5 合计行
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
	 * 方法功能描述：设置表体的行操作动作可用性。
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param edit
	 *          <p>
	 * @since 6.0
	 * @author zhyhang
	 * @time 2010-6-15 下午06:10:07
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
	 *          要设置的 bodyActions
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
		// 放开字段的批改
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
		// 2012-06-28 tianft 根据需求，屏蔽批改填充属性（今天一同学说，他领证了，恭喜！）
		BillCardPanelUtils cardUtils = new BillCardPanelUtils(bcp);
		cardUtils.disableItemsFill();

		EstEditorInitializer initializer = new EstEditorInitializer(this);
		// 初始化模板头（货物暂估精度和入库相关字段精度）
		initializer.setStockScale(EstVOUtil.getGoodsEstScaleKeyInfo(),
				EstVOUtil.getStockScaleKeyInfo());
		// 初始化模板体(费用暂估精度)
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
	 * 父类方法重写
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
		// 这里不需要处理父类的逻辑
		return;
	}

	/**
	 * 父类方法重写
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
