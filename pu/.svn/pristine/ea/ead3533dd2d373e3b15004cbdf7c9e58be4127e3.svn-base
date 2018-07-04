package nc.ui.pu.m25.action;

import nc.ui.uif2.actions.AbstractDirectPrintAction;
import nc.ui.uif2.actions.DirectOutputAction;
import nc.ui.uif2.model.AbstractUIAppModel;

public class InvoiceCloseOutputAction extends DirectOutputAction{

  private static final long serialVersionUID = -6953016448204486293L;
  
  /** 模型  */
  protected AbstractUIAppModel model;
  
  /** 打印类 */
  private AbstractDirectPrintAction printAction = null;
  
  @Override
  protected boolean isActionEnable() {
    return this.printAction.isEnabled();
  }
  
  /**
   * 
   * @param model
   */
  public void setModel(AbstractUIAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  public InvoiceCloseOutputAction(AbstractDirectPrintAction pDirectPrintAction) {
    super(pDirectPrintAction);
    this.printAction = pDirectPrintAction;
  }

}
