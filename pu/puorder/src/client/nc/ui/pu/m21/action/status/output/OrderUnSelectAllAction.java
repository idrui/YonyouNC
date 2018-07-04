/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-29 下午07:31:51
 */
package nc.ui.pu.m21.action.status.output;

import nc.bs.uif2.IActionCode;
import nc.ui.pubapp.uif2app.actions.UnSelectAllAction;
import nc.ui.scmpub.action.SCMActionInitializer;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>全消
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-6-29 下午07:31:51
 */
public class OrderUnSelectAllAction extends UnSelectAllAction {

  private static final long serialVersionUID = -6452725452698949870L;

  public OrderUnSelectAllAction() {
	SCMActionInitializer.initializeAction(this, IActionCode.SELNONE);
//    super();
//    this.putValue(Action.SHORT_DESCRIPTION, "全消(Alt+A)");
//    this.putValue(Action.ACCELERATOR_KEY,
//        KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));
  }

}
