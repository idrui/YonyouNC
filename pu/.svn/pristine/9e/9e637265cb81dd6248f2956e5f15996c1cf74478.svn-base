package nc.ui.pu.est.action.m45;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估处理刷新action
 * </ul>
 * <p>
 * <p>
 * 
 * @since 6.0
 * @version 2012-2-17 上午11:31:00
 * @author lixyp
 */
public class PurchaseInEstRefreshAction extends DefaultRefreshAction {

  private PurchaseInEstQueryAction queryAction;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.getQueryAction().executeQuery(this.getQueryAction().getQueryScheme());
    this.showQueryInfo();
  }

  public PurchaseInEstQueryAction getQueryAction() {
    return this.queryAction;
  }

  public void setQueryAction(PurchaseInEstQueryAction queryAction) {
    this.queryAction = queryAction;
  }

  @Override
  protected boolean isActionEnable() {
    if (this.getQueryAction() != null) {
      return this.getQueryAction().getQueryScheme() != null;
    }
    return super.isActionEnable();
  }

}
