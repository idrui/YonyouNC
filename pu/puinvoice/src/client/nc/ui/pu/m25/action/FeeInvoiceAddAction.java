/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-6 下午01:19:29
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;

import nc.bs.uif2.IActionCode;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.actions.ActionInitializer;
import nc.ui.uif2.model.AbstractAppModel;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>新增费用发票
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-6 下午01:19:29
 */
public class FeeInvoiceAddAction extends NCAction {

  /**
   * 
   */
  private static final long serialVersionUID = -1580985889764808959L;

  private AbstractAppModel model = null;

  public FeeInvoiceAddAction() {
    super();
    ActionInitializer.initializeAction(this, IActionCode.ADD);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.getModel().setUiState(UIState.ADD);

  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    return this.model.getUiState() == UIState.NOT_EDIT;
  }

}
