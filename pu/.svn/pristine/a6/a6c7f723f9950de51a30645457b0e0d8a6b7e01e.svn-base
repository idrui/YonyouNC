/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-11 下午02:29:00
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.m25.rule.InvoiceQueryPriceHandler;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * 采购发票优质优价按钮功能，满足条件：
 * <ul>
 * <li>原币币种等于本位币；
 * <li>根据入库单生成的；
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-11 下午02:29:00
 */
public class InvoiceHqhpAction extends InvoiceNormalAction {

  private static final long serialVersionUID = -4075835748710745407L;

  public InvoiceHqhpAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_HQHP);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    // 注册－询价远程调用合并
    InvoiceQueryPriceHandler priceHandler =
        new InvoiceQueryPriceHandler(this.getEditor().getBillCardPanel());
    priceHandler.prepareHqHpPrice();
    // 优质优价处理
    priceHandler.handleHqHpPrice();

  }

  @Override
  protected boolean isActionEnable() {
    // 只有编辑态才可用
    return this.getModel().getAppUiState() == AppUiState.EDIT
        || this.getModel().getAppUiState() == AppUiState.ADD
        || AppUiState.TRANSFERBILL_ADD == this.getModel().getAppUiState();
  }

}
