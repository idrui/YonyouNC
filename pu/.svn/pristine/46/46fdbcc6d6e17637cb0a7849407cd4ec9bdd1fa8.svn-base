package nc.ui.pu.costfactor.action;

import java.awt.event.ActionEvent;

import nc.bs.uif2.IActionCode;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.actions.ActionInitializer;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.ui.uif2.model.BillManageModel;
import nc.ui.uif2.model.IAppModelDataManager;

public class CostFactorRefreshAction extends NCAction {

  private static final long serialVersionUID = -7090626834515232335L;

  private IAppModelDataManager dataManager;

  private AbstractUIAppModel model = null;

  public CostFactorRefreshAction() {
    super();
    ActionInitializer.initializeAction(this, IActionCode.REFRESH);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.getDataManager().initModel();
    this.showQueryInfo();
  }

  public IAppModelDataManager getDataManager() {
    return this.dataManager;
  }

  public AbstractUIAppModel getModel() {
    return this.model;
  }

  public void setDataManager(IAppModelDataManager dataManager) {
    this.dataManager = dataManager;
  }

  public void setModel(AbstractUIAppModel model) {
    this.model = model;
  }

  protected void showQueryInfo() {
    int size = ((BillManageModel) this.getModel()).getData().size();
    if (size >= 0) {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0",
              "0pubapp-0266", null, new String[] {
                "" + size
              })/* @res "刷新成功，共刷新{0}张单据。" */, this.getModel().getContext());
    }
  }
}
