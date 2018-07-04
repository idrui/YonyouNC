/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-14 下午03:58:13
 */
package nc.ui.pu.m21.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.ui.pu.m21.action.direction.OrderFirstLineAction;
import nc.ui.pu.m21.action.direction.OrderLastLineAction;
import nc.ui.pu.m21.action.direction.OrderNextLineAction;
import nc.ui.pu.m21.action.direction.OrderPreLineAction;
import nc.ui.pu.m21.rule.ConfirmScaleSetter;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.BillOrgPanel;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.UIState;
import nc.ui.uif2.components.AutoShowUpEventSource;
import nc.ui.uif2.components.IAutoShowUpComponent;
import nc.ui.uif2.components.IAutoShowUpEventListener;
import nc.ui.uif2.components.IComponentWithActions;
import nc.ui.uif2.components.ITabbedPaneAwareComponent;
import nc.ui.uif2.components.ITabbedPaneAwareComponentListener;
import nc.ui.uif2.components.TabbedPaneAwareCompnonetDelegate;
import nc.ui.uif2.model.AppEventConst;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.bd.pub.NODE_TYPE;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.pub.Constructor;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>状态维护对应的卡片
 * <li>主要用于解决卡片上显示多张单据编辑态分页问题
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-7-14 下午03:58:13
 */
public class StatusBillFormEditor extends BillForm implements
    IAutoShowUpComponent, ITabbedPaneAwareComponent, IComponentWithActions {

  private static final long serialVersionUID = -7791496960565930163L;

  private List<Action> actions = null;

  private final AutoShowUpEventSource autoShowUpComponent =
      new AutoShowUpEventSource(this);

  private BillOrgPanel billOrgPanel;

  /** 当前Obj是否改变 */
  private boolean curObjChanged = false;

  private Object currentCardPanelSelectObj = null;

  private boolean showOrgPanel = true;

  private final TabbedPaneAwareCompnonetDelegate tabbedPaneAwareCompnonetDelegate =
      new TabbedPaneAwareCompnonetDelegate();

  @Override
  public void addTabbedPaneAwareComponentListener(
      ITabbedPaneAwareComponentListener l) {
    this.tabbedPaneAwareCompnonetDelegate
        .addTabbedPaneAwareComponentListener(l);
  }

  @Override
  public boolean canBeHidden() {
    // 编辑态不允许切换
    if (this.getModel().getUiState() == UIState.EDIT
        || this.getModel().getUiState() == UIState.ADD) {
      return false;
    }

    return this.tabbedPaneAwareCompnonetDelegate.canBeHidden();
  }

  @Override
  public List<Action> getActions() {
    if (null == this.actions && this.getModel() instanceof BillManageModel) {
      this.actions = this.getDefaultActions();
    }
    return this.actions;
  }

  public BillOrgPanel getBillOrgPanel() {
    if (null == this.billOrgPanel && this.isShowOrgPanel()) {
      this.billOrgPanel = this.createDefaultBillOrgPanel();
    }
    return this.billOrgPanel;
  }

  // 获取表体选择行VO
  // @SuppressWarnings("deprecation")
  // 元素据的字段
  public ISuperVO[] getBodySelectedVOs() {
    if (null == this.getValue()) {
      return null;
    }
    if (ArrayUtils.isEmpty(this.getSelectedRows())) {
      return null;
    }

    BillIndex index = new BillIndex(new AbstractBill[] {
      ((AbstractBill) this.getValue())
    });
    SuperVO[] vos =
        (SuperVO[]) ((AbstractBill) this.getValue()).getChildrenVO();
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    IVOMeta meta = vos[0].getMetaData();
    SuperVO[] itemVOs =
        Constructor.construct(vos[0].getClass(), this.getSelectedRows().length);
    if (ArrayUtils.isEmpty(itemVOs)) {
      return null;
    }
    String pk_item = meta.getPrimaryAttribute().getName();
    for (int i = 0; i < this.getSelectedRows().length; ++i) {
      String pk_Order_b =
          (String) this.getBillCardPanel().getBillModel()
              .getValueAt(this.getSelectedRows()[i], pk_item);
      // SuperVO itemVO = itemMap.get(pk_Order_b);
      SuperVO itemVO = (SuperVO) index.get(meta, pk_Order_b);
      itemVOs[i] = itemVO;
    }
    return itemVOs;
  }

  public int[] getSelectedRows() {
    int[] selectedRows =
        this.getBillCardPanel().getBodyPanel().getTable().getSelectedRows();
    return selectedRows;
  }

  @Override
  public Object getValue() {
    if (null != this.billOrgPanel && this.isShowOrgPanel()) {
      this.billOrgPanel.stopEditing();
    }
    return super.getValue();
  }

  @Override
  public void handleEvent(AppEvent event) {
    // 打开节点时先显示列表，并选中第一行，这时卡片界面为空，
    // 直接点击修改，过程是先修改状态，再切换卡片，因为有编辑态
    // 不同步数据的逻辑，所以这种情况下卡片界面不能显示要修改的数据，
    // 所以点修改时先根据需要同步数据
    if (AppEventConst.UISTATE_CHANGED == event.getType()
        && this.getModel().getUiState() == UIState.EDIT && this.dataChanged()) {
      this.synchronizeDataFromModel0();
    }

    if (AppEventConst.SELECTED_DATE_CHANGED == event.getType()) {
      // 数据更新时，标记变化
      if (this.currentCardPanelSelectObj == this.getModel().getSelectedData()) {
        this.curObjChanged = true;
      }
    }

    super.handleEvent(event);

    if (AppEventConst.UISTATE_CHANGED == event.getType()
        // 新增和修改状态下不能同步当前选中的数据
        && this.getModel().getUiState() != UIState.ADD
        && this.getModel().getUiState() != UIState.EDIT) {
      this.synchronizeDataFromModel0();
    }

  }

  @Override
  public void initUI() {
    super.initUI();
    if (this.isShowOrgPanel()) {
      this.add(this.getBillOrgPanel(), BorderLayout.NORTH);
    }
    // initOrgHandler();
    // 多选属性，需要在添加RowStateChangeEventListener之前设置
    // this.getBillListPanel().setMultiSelect(true);
    this.getBillCardPanel().setBodyMultiSelect(true);

    // 清除增行、删行等默认编辑菜单
    BillScrollPane billScrollPane =
        (BillScrollPane) this.getBillCardPanel().getSelectedScrollPane(1);
    billScrollPane.clearDefalutEditAction();
  }

  @Override
  public boolean isComponentVisible() {
    return this.tabbedPaneAwareCompnonetDelegate.isComponentVisible();
  }

  public boolean isShowOrgPanel() {
    return this.showOrgPanel
        && this.getModel().getContext().getNodeType() == NODE_TYPE.ORG_NODE;
  }

  public void setActions(List<Action> actions) {
    this.actions = actions;
  }

  @Override
  public void setAutoShowUpEventListener(IAutoShowUpEventListener l) {
    this.autoShowUpComponent.setAutoShowUpEventListener(l);
  }

  public void setBillOrgPanel(BillOrgPanel billOrgPanel) {
    this.billOrgPanel = billOrgPanel;
  }

  @Override
  public void setComponentVisible(boolean visible) {
    this.tabbedPaneAwareCompnonetDelegate.setComponentVisible(visible);
    if (this.getModel().getUiState() != UIState.ADD
        && this.getModel().getUiState() != UIState.EDIT) { // 切换到卡片界面时同步数据
      this.synchronizeDataFromModel();
    }
  }

  public void setShowOrgPanel(boolean showOrgPanel) {
    this.showOrgPanel = showOrgPanel;
  }

  /*
   * 因为组织面板为此类的一部分，所以当setValue方式执行时，组织面板的值也应该被设置
   * @see nc.ui.uif2.editor.BillForm#setValue(java.lang.Object)
   */
  @Override
  public void setValue(Object object) {
    // 设置精度
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    new ConfirmScaleSetter(pk_group).setCardScale(this.getBillCardPanel());

    if (this.isShowOrgPanel()) {
      this.getBillOrgPanel().setPkOrg(
          this.getBillOrgPanel().getOrgGetter().getPkOrg(object));
    }
    this.getBillCardPanel().getBillData().updateValue();
    super.setValue(object);
    OrderVO vo = null;
    if (null != object && object.getClass().isArray()) {
      vo = (OrderVO) new Object[] {
        object
      }[0];
    }
    else if (null != object) {
      vo = (OrderVO) object;
    }
    if (null == vo) {
      return;
    }

    BillModel bm = this.getBillCardPanel().getBillModel();
  }

  @Override
  public void showMeUp() {
    // 切换到卡片时，如果列表下选中多行，则可操作行默认置成第一行
    if (((BillManageModel) this.getModel()).getSelectedOperaRows().length > 1) {
      ((BillManageModel) this.getModel()).setSelectedOperaRows(new int[] {
        ((BillManageModel) this.getModel()).getSelectedOperaRows()[0]
            .intValue()
      });
    }
    this.autoShowUpComponent.showMeUp();
  }

  private BillOrgPanel createDefaultBillOrgPanel() {
    BillOrgPanel orgPanel = new BillOrgPanel();
    orgPanel.setModel(this.getModel());
    orgPanel.initUI();
    return orgPanel;
  }

  private boolean dataChanged() {
    return this.currentCardPanelSelectObj != this.getModel().getSelectedData()
        || this.curObjChanged;
  }

  private List<Action> getDefaultActions() {
    List<Action> action = new ArrayList<Action>();
    BillManageModel billManageModel = (BillManageModel) this.getModel();

    OrderFirstLineAction firstLineAction = new OrderFirstLineAction();
    firstLineAction.setModel(billManageModel);
    firstLineAction.setEnabled(false);
    firstLineAction.setEditor(this.getBillCardPanel());
    action.add(firstLineAction);

    OrderPreLineAction preLineAction = new OrderPreLineAction();
    preLineAction.setModel(billManageModel);
    preLineAction.setEnabled(false);
    preLineAction.setEditor(this.getBillCardPanel());
    action.add(preLineAction);

    OrderNextLineAction nextLineAction = new OrderNextLineAction();
    nextLineAction.setModel(billManageModel);
    nextLineAction.setEnabled(false);
    nextLineAction.setEditor(this.getBillCardPanel());
    action.add(nextLineAction);

    OrderLastLineAction lastLineAction = new OrderLastLineAction();
    lastLineAction.setModel(billManageModel);
    lastLineAction.setEnabled(false);
    lastLineAction.setEditor(this.getBillCardPanel());
    action.add(lastLineAction);

    return action;
  }

  private void synchronizeDataFromModel0() {
    super.synchronizeDataFromModel();
    this.currentCardPanelSelectObj = this.getModel().getSelectedData();
    this.curObjChanged = false;
  }

}
