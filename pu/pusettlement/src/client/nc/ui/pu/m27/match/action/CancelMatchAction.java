/**
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-6 下午02:05:38
 */
package nc.ui.pu.m27.match.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m27.ISettleBillMaintain;
import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>取消结算按钮事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-6 下午02:05:38
 */
public class CancelMatchAction extends MatchDefaultAction {
  private static final long serialVersionUID = -5667491871111125098L;

  private InvoiceQueryAction invoiceQueryAction;

  private AbstractStockQueryAction2 stockQueryAction;

  /**
   * InvoiceAction 的构造子
   */
  public CancelMatchAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_CANCELMATCH);
    // this.setBtnName("取消匹配");
    // this.setCode("btnCancelMatch");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    MatchManageModel model =
        (MatchManageModel) this.invoiceQueryAction.getModel();

    // 删除结算单
    if (model.getSettleBillVOs() != null) {
      ISettleBillMaintain serv =
          NCLocator.getInstance().lookup(ISettleBillMaintain.class);
      serv.deleteSettleBills(model.getSettleBillVOs());
    }

    // 清除settleBillVOS（此处必须清除，在匹配结果页面的行切换事件将会使用该值进行判断）
    model.initSettleBillVOs(null);

    // 重新查询发票和入库单
    this.invoiceQueryAction.executeQuery(this.invoiceQueryAction
        .getCurQueryScheme());
    this.stockQueryAction.executeQuery(this.stockQueryAction
        .getCurQueryScheme());
  }

  public void setInvoiceQueryAction(InvoiceQueryAction invoiceQueryAction) {
    this.invoiceQueryAction = invoiceQueryAction;
    this.setContext(invoiceQueryAction.getModel().getContext());
  }

  public void setStockQueryAction(AbstractStockQueryAction2 stockQueryAction) {
    this.stockQueryAction = stockQueryAction;
  }

}
