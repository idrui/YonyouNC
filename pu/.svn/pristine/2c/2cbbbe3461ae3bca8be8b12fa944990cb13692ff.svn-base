/**
 * $构造"发票"查询按钮$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-6 下午02:05:38
 */
package nc.ui.pu.m27.match.action;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletContext;
import nc.pubitf.pu.m25.IInvoiceForToQueryAction;
import nc.pubitf.pu.m25.to.settle.IInvoiceTOSettleQuery;
import nc.ui.pu.m27.match.view.query.BaseInvoicStlQryDlgInitializer;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.uif2app.ToftPanelAdaptorEx;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.pub.enumeration.PuNodeCode;
import nc.vo.pu.pub.enumeration.PuNodeKey;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.uif2.LoginContext;

/**
 * 采购发票为内部交易结算提供的查询action
 * 
 * @since 6.0
 * @version 2011-1-24 上午11:49:59
 * @author 田锋涛
 */
public class InvoiceForToQueryAction extends DefaultQueryAction implements
    IInvoiceForToQueryAction {
  private static final long serialVersionUID = -5667491871111125098L;

  private BillManageModel model = null;

  // 财务组织
  private String pk_finaorg = null;

  private transient IQueryConditionDLGInitializer queryIniializer = null;

  /**
   * 内部交易调结算可用此方法获取QueryScheme
   * 
   * @return
   */
  public IQueryScheme getCurQueryScheme(String pk_org) {
    this.setPk_finaorg(pk_org);
    if (this.getQryDLGDelegator().showModal() == UIDialog.ID_OK) {
      IQueryScheme qs = this.getQryDLGDelegator().getQueryScheme();
      qs.put(IInvoiceTOSettleQuery.QS_SETTLE_FIORG_KEY, pk_org);
      return qs;
    }
    return null;
  }

  @Override
  public BillManageModel getModel() {
    if (this.model == null) {
      this.model = new BillManageModel();
      this.model.setContext(this.getLoginContext());
    }
    return this.model;
  }

  @Override
  public String getNodeKey() {
    return PuNodeKey.n4004140003.code();
  }

  public String getPk_finaorg() {
    return this.pk_finaorg;
  }

  @Override
  public IQueryConditionDLGInitializer getQryCondDLGInitializer() {
    if (this.queryIniializer == null) {
      this.queryIniializer = new BaseInvoicStlQryDlgInitializer();
    }
    return this.queryIniializer;
  }

  public void setPk_finaorg(String pkFinaorg) {
    this.pk_finaorg = pkFinaorg;
  }

  private FuncRegisterVO getFuncRegisterVO() {
    FuncRegisterVO funVO =
        WorkbenchEnvironment.getInstance().getFuncRegisterVO(
            PuNodeCode.n40041400.code());
    return funVO;
  }

  /**
   * 构造自己接的logcontext
   * 
   * @return
   */
  private LoginContext getLoginContext() {
    LoginContext context = new LoginContext();
    context.setPk_group(ClientContext.getInstance().getPk_group());
    context.setPk_org(this.getPk_finaorg());
    context.setPk_loginUser(ClientContext.getInstance().getLoginUserId());
    context.setNodeCode(this.getFuncRegisterVO().getFuncode());
    FuncletContext fctx = new FuncletContext(this.getFuncRegisterVO());
    ToftPanelAdaptorEx adptor = new ToftPanelAdaptorEx();
    adptor.init(fctx);
    context.setEntranceUI(adptor);
    return context;
  }

}
