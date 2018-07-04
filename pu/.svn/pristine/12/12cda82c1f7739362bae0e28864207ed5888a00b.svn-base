package nc.ui.pu.est.action.m50;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction;

public class VMIEstRefreshAction extends DefaultRefreshAction {

  private VMIEstQueryAction queryAction = null;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.getQueryAction().executeQuery(this.getQueryAction().getQueryScheme());
    this.showQueryInfo();
  }

  public VMIEstQueryAction getQueryAction() {
    return this.queryAction;
  }

  public void setQueryAction(VMIEstQueryAction queryAction) {
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
