package nc.ui.pu.m23.action;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.bs.uif2.IActionCode;
import nc.ui.pubapp.uif2app.actions.MasterDetailPrintAction;
import nc.ui.uif2.actions.ActionInitializer;

public class PrintUIAction extends MasterDetailPrintAction {

  private static final long serialVersionUID = 2061080088740164264L;

  public PrintUIAction() {
    // setCode(IActionCode.PRINT);
    ActionInitializer.initializeAction(this, IActionCode.PRINT);
    // putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_W,
    // Event.CTRL_MASK));
    this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    return;
  }
}
