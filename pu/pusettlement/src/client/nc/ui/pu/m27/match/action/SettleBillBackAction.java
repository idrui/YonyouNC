/**
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-6 下午02:05:38
 */
package nc.ui.pu.m27.match.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
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
 * @since 6.0
 * @author tianft
 * @time 2010-1-6 下午02:05:38
 */
public class SettleBillBackAction extends MatchDefaultAction {
  private static final long serialVersionUID = -5667491871111125098L;

  private InvoiceQueryAction invoiceQueryAction;

  private AbstractStockQueryAction2 stockQueryAction;

  /**
   * InvoiceAction 的构造子
   */
  public SettleBillBackAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_MATCHBACK);
    // this.setBtnName("返回");
    // this.setCode("btnSettleBillBack");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    // 清除settleBillVOS（此处必须清除，在匹配结果页面的行切换事件将会使用该值进行判断）
    MatchManageModel model =
        (MatchManageModel) this.invoiceQueryAction.getModel();
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
