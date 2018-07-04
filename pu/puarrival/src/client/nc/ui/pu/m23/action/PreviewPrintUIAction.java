package nc.ui.pu.m23.action;

import java.awt.event.ActionEvent;

import nc.bs.uif2.IActionCode;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.actions.ActionInitializer;

public class PreviewPrintUIAction extends NCAction {

  private static final long serialVersionUID = -4392288420244111903L;

  public PreviewPrintUIAction() {
    super();
    ActionInitializer.initializeAction(this, IActionCode.PREVIEW);
    // String name = "‘§¿¿";
    // this.setBtnName(name);
    // this.putValue(INCAction.CODE, name);
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_W, Event.CTRL_MASK));
    // this.putValue(Action.SHORT_DESCRIPTION, name);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    return;
  }

  @Override
  public String getBtnName() {
    return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC001-0000112")/*@res "‘§¿¿"*/;
  }
}