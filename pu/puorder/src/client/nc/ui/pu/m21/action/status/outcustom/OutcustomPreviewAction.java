/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-20 上午09:10:32
 */
package nc.ui.pu.m21.action.status.outcustom;

import nc.bs.uif2.IActionCode;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.actions.PrintPreviewAction;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>装运预览
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-20 上午09:10:32
 */
public class OutcustomPreviewAction extends PrintPreviewAction {

  private static final long serialVersionUID = 8629973973175753704L;

  public OutcustomPreviewAction() {
	SCMActionInitializer.initializeAction(this, IActionCode.PREVIEW);
//    this.setBtnName("预览");
//    this.setCode("uncustomPreviewAction");
//    this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName() + "(Ctrl+W)");
//    this.putValue(Action.ACCELERATOR_KEY,
//        KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
  }

}
