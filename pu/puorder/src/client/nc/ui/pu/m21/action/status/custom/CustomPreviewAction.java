/**
 * $�ļ�˵��$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-20 ����09:10:32
 */
package nc.ui.pu.m21.action.status.custom;

import nc.bs.uif2.IActionCode;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.actions.PrintPreviewAction;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����Ԥ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-20 ����09:10:32
 */
public class CustomPreviewAction extends PrintPreviewAction {

  private static final long serialVersionUID = 5693268425173666099L;

  public CustomPreviewAction() {
	SCMActionInitializer.initializeAction(this, IActionCode.PREVIEW);
//    this.setBtnName("Ԥ��");
//    this.setCode("customPreviewAction");
//    this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName() + "(Ctrl+W)");
//    this.putValue(Action.ACCELERATOR_KEY,
//        KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
  }

}