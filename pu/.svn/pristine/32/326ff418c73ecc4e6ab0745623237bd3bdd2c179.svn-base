/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-16 下午01:16:52
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.m25.model.InvoiceBillManageModel;
import nc.ui.pu.m25.view.InvoiceUIState;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>从费用发票界面返回普通发票维护界面
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-16 下午01:16:52
 */
public class InvoiceRetFromFeeBillAction extends NCAction {

  /**
   * 
   */
  private static final long serialVersionUID = -1758361178125930073L;

  private InvoiceBillManageModel model = null;

  /**
   * InvoiceRetFromFeeBillAction 的构造子
   */
  public InvoiceRetFromFeeBillAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_MATCHBACK);
//    this.setBtnName("返回");
//    this.setCode("返回普通发票");
//    this.putValue(Action.SHORT_DESCRIPTION, "返回普通发票维护界面");
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.getModel().restoreNormContext();
    this.getModel().setAppUiState(InvoiceUIState.NORM_INVC);
    this.getModel().setAppUiState(AppUiState.NOT_EDIT);

  }

  public InvoiceBillManageModel getModel() {
    return this.model;
  }

  public void setModel(InvoiceBillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    return AppUiState.NOT_EDIT == this.getModel().getAppUiState();
  }
}
