/**
 * $文件说明$
 * 
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-31 上午10:41:36
 */
package nc.ui.pu.pub.action;

import java.awt.event.ActionEvent;

import nc.ui.ic.onhand.pane.QueryOnHandInfoPanel;
import nc.ui.pu.pub.handpanel.PuDefaultOnhandPanelSrc;
import nc.ui.pub.bill.IBillData;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmf.ic.onhand.IOnhandDisplayable;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.TangramContainer;
import nc.ui.uif2.editor.TemplateContainer;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购 存量显示/隐藏按钮
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-8-31 上午10:41:36
 */
public class PuOnHandShowHidAction extends NCAction {

  private static final long serialVersionUID = -5771455814680977918L;

  /** 卡片视图 **/
  private ShowUpableBillForm card;

  /** 面板容器 **/
  private TangramContainer contain;

  /** 存量面板 **/
  private IOnhandDisplayable onhandpane;

  /** 存量面板来源信息 **/
  private PuDefaultOnhandPanelSrc onhandPanelSrc;

  private TemplateContainer templateContainer;

  /** 自由项处理器 **/
  private IBillData userdefitemPreparator;

  /** 默认构造子 **/
  public PuOnHandShowHidAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_ONHANDINFO);
  }

  /**
   * 展示/隐藏动作
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.card.showMeUp();
    if (this.getOnhandpane().isComponentDisplayable()) {
      this.getOnhandpane().setIsComponentDisplayable(false);
    }
    else {
      this.getOnhandpane().setIsComponentDisplayable(true);
    }
    this.contain.resetLayout();
    this.card.showMeUp();
    int row = this.card.getBillCardPanel().getBillTable().getSelectedRow();
    ((nc.ui.ic.onhand.pane.QueryOnHandInfoPanel) this.getOnhandpane())
        .freshData(row);
  }

  /** 取得卡片视图 **/
  public ShowUpableBillForm getCard() {
    return this.card;
  }

  /** 取得面板容器 **/
  public nc.ui.uif2.TangramContainer getContain() {
    return this.contain;
  }

  /** 取得存量面板 **/
  public IOnhandDisplayable getOnhandpane() {
    if (null == ((QueryOnHandInfoPanel) this.onhandpane).getOnhandPanelSrc()) {
      ((QueryOnHandInfoPanel) this.onhandpane)
          .setOnhandPanelSrc(this.onhandPanelSrc);
      ((QueryOnHandInfoPanel) this.onhandpane)
          .setTemplateContainer(this.templateContainer);
      ((QueryOnHandInfoPanel) this.onhandpane)
          .setUserdefitemPreparator(this.userdefitemPreparator);
      ((QueryOnHandInfoPanel) this.onhandpane).initialize();
    }
    return this.onhandpane;
  }

  /** 设置卡片视图 **/
  public void setCard(ShowUpableBillForm card) {
    this.card = card;
  }

  /** 设置面板容器 **/
  public void setContain(nc.ui.uif2.TangramContainer contain) {
    this.contain = contain;
  }

  /** 设置存量面板 **/
  public void setOnhandpane(IOnhandDisplayable onhandpane) {
    this.onhandpane = onhandpane;
  }

  public void setOnhandPanelSrc(PuDefaultOnhandPanelSrc onhandPanelSrc) {
    this.onhandPanelSrc = onhandPanelSrc;
  }

  public void setTemplateContainer(TemplateContainer templateContainer) {
    this.templateContainer = templateContainer;
  }

  public void setUserdefitemPreparator(IBillData userdefitemPreparator) {
    this.userdefitemPreparator = userdefitemPreparator;
  }
}
