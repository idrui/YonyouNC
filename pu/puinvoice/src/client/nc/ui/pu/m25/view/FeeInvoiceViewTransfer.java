/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-15 下午08:26:13
 */
package nc.ui.pu.m25.view;

import java.awt.Color;
import java.awt.Container;
import java.util.List;

import javax.swing.Action;
import javax.swing.JComponent;

import nc.ui.pub.beans.ExtTabbedPane;
import nc.ui.pubapp.uif2app.event.AppUiStateChangeEvent;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.actions.ActionContributors;
import nc.ui.uif2.actions.IActionContributor;
import nc.ui.uif2.actions.StandAloneToftPanelActionContainer;
import nc.ui.uif2.components.IAutoShowUpComponent;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>在普通发票维护和费用发票维护界面切换的管理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-3-15 下午08:26:13
 */
public class FeeInvoiceViewTransfer implements
    IAppEventHandler<AppUiStateChangeEvent> {

  /** 内部类，维护普通发票界面的信息 **/
  private static class NormInvcContext {
    /** 普通发票卡片按钮 **/
    private List<Action> normCardAction;

    /** 普通发票卡片页签名称 **/
    private String normCardTitle;

    /** 普通发票编辑按钮 **/
    private List<Action> normEditAction;

    /** 普通发票列表按钮 **/
    private List<Action> normListAction;

    /** 普通发票列表页签名称 **/
    private String normListTitle;

    /** 发票节点名称颜色 **/
    private Color normNodeTitleColor;

    /** 普通发票维护显示的列表还是卡片，以便费用返回时显示 **/
    private JComponent normShowingView;

    public NormInvcContext() {
      //
    }

    /**
     * @return normCardAction
     */
    public List<Action> getNormCardAction() {
      return this.normCardAction;
    }

    /**
     * @return normCardTitle
     */
    public String getNormCardTitle() {
      return this.normCardTitle;
    }

    /**
     * @return normEditAction
     */
    public List<Action> getNormEditAction() {
      return this.normEditAction;
    }

    /**
     * @return normListAction
     */
    public List<Action> getNormListAction() {
      return this.normListAction;
    }

    /**
     * @return normListTitle
     */
    public String getNormListTitle() {
      return this.normListTitle;
    }

    /**
     * @return normNodeTitleColor
     */
    public Color getNormNodeTitleColor() {
      return this.normNodeTitleColor;
    }

    /**
     * @return normShowingView
     */
    public JComponent getNormShowingView() {
      return this.normShowingView;
    }

    /**
     * @param normCardAction
     *          要设置的 normCardAction
     */
    public void setNormCardAction(List<Action> normCardAction) {
      this.normCardAction = normCardAction;
    }

    /**
     * @param normCardTitle
     *          要设置的 normCardTitle
     */
    public void setNormCardTitle(String normCardTitle) {
      this.normCardTitle = normCardTitle;
    }

    /**
     * @param normEditAction
     *          要设置的 normEditAction
     */
    public void setNormEditAction(List<Action> normEditAction) {
      this.normEditAction = normEditAction;
    }

    /**
     * @param normListAction
     *          要设置的 normListAction
     */
    public void setNormListAction(List<Action> normListAction) {
      this.normListAction = normListAction;
    }

    /**
     * @param normListTitle
     *          要设置的 normListTitle
     */
    public void setNormListTitle(String normListTitle) {
      this.normListTitle = normListTitle;
    }

    /**
     * @param normNodeTitleColor
     *          要设置的 normNodeTitleColor
     */
    public void setNormNodeTitleColor(Color normNodeTitleColor) {
      this.normNodeTitleColor = normNodeTitleColor;
    }

    /**
     * @param normShowingView
     *          要设置的 normShowingView
     */
    public void setNormShowingView(JComponent normShowingView) {
      this.normShowingView = normShowingView;
    }

  }

  /** 动作提供器,保存了卡片和列表的动作 **/
  private ActionContributors actionContributor;

  /** 费用发票卡片按钮 **/
  private List<Action> feeCardAction;

  /** 费用发票编辑按钮 **/
  private List<Action> feeEditAction;

  /** 费用发票列表按钮 **/
  private List<Action> feeListAction;

  /** 普通发票维护界面的环境信息 **/
  private NormInvcContext normContext;

  /** 普通发票编辑按钮(框架未提供getEditAction方法，只能注入) **/
  private List<Action> normEditAction;

  /**
   * @return actionContributor
   */
  public ActionContributors getActionContributor() {
    return this.actionContributor;
  }

  /**
   * @return feeCardAction
   */
  public List<Action> getFeeCardAction() {
    return this.feeCardAction;
  }

  /**
   * @return feeEditAction
   */
  public List<Action> getFeeEditAction() {
    return this.feeEditAction;
  }

  /**
   * @return feeListAction
   */
  public List<Action> getFeeListAction() {
    return this.feeListAction;
  }

  /**
   * @return normEditAction
   */
  public List<Action> getNormEditAction() {
    return this.normEditAction;
  }

  @Override
  public void handleAppEvent(AppUiStateChangeEvent e) {
    if (InvoiceUIState.FEE_INVC == e.getNewAppUiState()) {
      this.chgToFeeUI();
    }
    else if (InvoiceUIState.NORM_INVC == e.getNewAppUiState()) {
      this.chgToNormUI();
    }
  }

  /**
   * @param actionContributor
   */
  public void setActionContributor(ActionContributors actionContributor) {
    this.actionContributor = actionContributor;
  }

  /**
   * @param feeCardAction
   *          要设置的 feeCardAction
   */
  public void setFeeCardAction(List<Action> feeCardAction) {
    this.feeCardAction = feeCardAction;
  }

  /**
   * @param feeEditAction
   *          要设置的 feeEditAction
   */
  public void setFeeEditAction(List<Action> feeEditAction) {
    this.feeEditAction = feeEditAction;
  }

  /**
   * @param feeListAction
   *          要设置的 feeListAction
   */
  public void setFeeListAction(List<Action> feeListAction) {
    this.feeListAction = feeListAction;
  }

  /**
   * @param normEditAction
   *          要设置的 normEditAction
   */
  public void setNormEditAction(List<Action> normEditAction) {
    this.normEditAction = normEditAction;
  }

  /**
   * 方法功能描述：切换卡片和列表的action。
   * <p>
   * <b>参数说明</b>
   * 
   * @param cardAction
   *          卡片action
   * @param listAction
   *          列表action
   * @param editAction
   *          编辑状态action
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-20 下午02:44:11
   */
  private void chgActions(List<Action> cardAction, List<Action> listAction,
      List<Action> editAction) {
    StandAloneToftPanelActionContainer container =
        this.findCardActionContainer();
    boolean orgActive = container.isActived();
    if (orgActive) {
      container.setActived(false);
    }
    container.setActions(cardAction);
    container.setActived(orgActive);
    container.setEditActions(editAction);
    container = this.findListActionContainer();
    orgActive = container.isActived();
    if (orgActive) {
      container.setActived(false);
    }
    container.setActions(listAction);
    container.setActived(orgActive);
  }

  /**
   * 方法功能描述：切换到费用发票维护界面。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-20 下午02:40:35
   */
  private void chgToFeeUI() {
    this.getNormInvcContext().setNormShowingView(this.findShowingView());
    this.getNormInvcContext().setNormCardAction(this.findNormCardAction());
    this.getNormInvcContext().setNormListAction(this.findNormListAction());
    this.getNormInvcContext().setNormEditAction(this.getNormEditAction());
    this.chgActions(this.feeCardAction, this.feeListAction, this.feeEditAction);
    JComponent feeShowingView =
        (JComponent) this.findCardActionContainer()
            .getTabbedPaneAwareComponent();
    if (feeShowingView instanceof ShowUpableBillForm) {
      ((ShowUpableBillForm) feeShowingView).showMeUp();
    }
  }

  /**
   * 方法功能描述：切换到普通发票维护界面。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-20 下午02:42:00
   */
  private void chgToNormUI() {
    this.chgActions(this.getNormInvcContext().getNormCardAction(), this
        .getNormInvcContext().getNormListAction(), this.getNormInvcContext()
        .getNormEditAction());
    JComponent feeShowingView = this.findShowingView();
    if (this.getNormInvcContext().getNormShowingView() != null
        && !this.getNormInvcContext().getNormShowingView()
            .equals(feeShowingView)
        && this.getNormInvcContext().getNormShowingView() instanceof IAutoShowUpComponent) {
      ((IAutoShowUpComponent) this.getNormInvcContext().getNormShowingView())
          .showMeUp();
    }
    // ExtTabbedPane nodeUI = this.getTopParent(feeShowingView);
    // nodeUI.setTitleAt(nodeUI.getTabCount() - 1, this.getNormInvcContext()
    // .getNormCardTitle());
    // nodeUI.setTitleAt(nodeUI.getTabCount() - 2, this.getNormInvcContext()
    // .getNormListTitle());
    // nodeUI.setForegroundAt(nodeUI.getTabCount() - 1,
    // this.getNormInvcContext()
    // .getNormNodeTitleColor());
    // nodeUI.setForegroundAt(nodeUI.getTabCount() - 2,
    // this.getNormInvcContext()
    // .getNormNodeTitleColor());
    // nodeUI.setToolTipText(null);
  }

  private StandAloneToftPanelActionContainer findCardActionContainer() {
    for (IActionContributor contributor : this.getActionContributor()
        .getContributors()) {
      if (contributor instanceof StandAloneToftPanelActionContainer
          && ((StandAloneToftPanelActionContainer) contributor)
              .getTabbedPaneAwareComponent() instanceof ShowUpableBillForm) {
        return (StandAloneToftPanelActionContainer) contributor;
      }
    }
    return null;
  }

  private StandAloneToftPanelActionContainer findListActionContainer() {
    for (IActionContributor contributor : this.getActionContributor()
        .getContributors()) {
      if (contributor instanceof StandAloneToftPanelActionContainer
          && ((StandAloneToftPanelActionContainer) contributor)
              .getTabbedPaneAwareComponent() instanceof ShowUpableBillListView) {
        return (StandAloneToftPanelActionContainer) contributor;
      }
    }
    return null;
  }

  private List<Action> findNormCardAction() {
    return this.findCardActionContainer().getActions();
  }

  private List<Action> findNormListAction() {
    return this.findListActionContainer().getActions();

  }

  private JComponent findShowingView() {
    JComponent comp =
        (JComponent) this.findListActionContainer()
            .getTabbedPaneAwareComponent();
    if (null == comp || !comp.isShowing()) {
      comp =
          (JComponent) this.findCardActionContainer()
              .getTabbedPaneAwareComponent();
    }
    return comp;
  }

  private NormInvcContext getNormInvcContext() {
    if (this.normContext == null) {
      this.normContext = new NormInvcContext();
    }
    return this.normContext;

  }

  private ExtTabbedPane getTopParent(Container normView) {
    Container parent = normView;
    while (parent != null) {
      if (parent instanceof ExtTabbedPane) {
        return (ExtTabbedPane) parent;
      }
      parent = parent.getParent();
    }
    return null;
  }

}
