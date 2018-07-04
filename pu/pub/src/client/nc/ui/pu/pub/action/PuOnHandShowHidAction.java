/**
 * $�ļ�˵��$
 * 
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-31 ����10:41:36
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ� ������ʾ/���ذ�ť
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-8-31 ����10:41:36
 */
public class PuOnHandShowHidAction extends NCAction {

  private static final long serialVersionUID = -5771455814680977918L;

  /** ��Ƭ��ͼ **/
  private ShowUpableBillForm card;

  /** ������� **/
  private TangramContainer contain;

  /** ������� **/
  private IOnhandDisplayable onhandpane;

  /** ���������Դ��Ϣ **/
  private PuDefaultOnhandPanelSrc onhandPanelSrc;

  private TemplateContainer templateContainer;

  /** ��������� **/
  private IBillData userdefitemPreparator;

  /** Ĭ�Ϲ����� **/
  public PuOnHandShowHidAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_ONHANDINFO);
  }

  /**
   * չʾ/���ض���
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

  /** ȡ�ÿ�Ƭ��ͼ **/
  public ShowUpableBillForm getCard() {
    return this.card;
  }

  /** ȡ��������� **/
  public nc.ui.uif2.TangramContainer getContain() {
    return this.contain;
  }

  /** ȡ�ô������ **/
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

  /** ���ÿ�Ƭ��ͼ **/
  public void setCard(ShowUpableBillForm card) {
    this.card = card;
  }

  /** ����������� **/
  public void setContain(nc.ui.uif2.TangramContainer contain) {
    this.contain = contain;
  }

  /** ���ô������ **/
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
