package nc.ui.pu.m27.match.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pu.m27.match.model.SettleUIState.DistState;
import nc.ui.pu.m27.match.view.MatchDisplayListPanel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>构造"返回"按钮
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @author tianft
 * @time 2010-1-6 下午02:05:38
 */
public class MatchBackAction extends NCAction {
  private static final long serialVersionUID = -5667491871111125098L;

  private InvoiceQueryAction invoiceQueryAction;

  private MatchDisplayListPanel listView;

  private MatchManageModel model;

  private AbstractStockQueryAction2 stockQueryAction;

  /**
   * InvoiceAction 的构造子
   */
  public MatchBackAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_MATCHBACK);
    // this.setBtnName("返回");
    // this.setCode("btnMatchBack");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.getModel().setFeeDistUIState(DistState.not_dist);
    this.getModel().setInvcDistUIState(DistState.not_dist);
    // 重新查询发票和入库单
    this.invoiceQueryAction.executeQuery(this.invoiceQueryAction
        .getCurQueryScheme());
    this.stockQueryAction.executeQuery(this.stockQueryAction
        .getCurQueryScheme());
    this.getListView().showMeUp();
  }

  public MatchDisplayListPanel getListView() {
    return this.listView;
  }

  public MatchManageModel getModel() {
    return this.model;
  }

  public void setInvoiceQueryAction(InvoiceQueryAction invoiceQueryAction) {
    this.invoiceQueryAction = invoiceQueryAction;
  }

  public void setListView(MatchDisplayListPanel listView) {
    this.listView = listView;
  }

  public void setModel(MatchManageModel model) {
    this.model = model;
  }

  public void setStockQueryAction(AbstractStockQueryAction2 stockQueryAction) {
    this.stockQueryAction = stockQueryAction;
  }

}
