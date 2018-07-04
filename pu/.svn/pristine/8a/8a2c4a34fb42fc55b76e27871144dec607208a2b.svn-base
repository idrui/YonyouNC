/**
 *
 */
package nc.ui.pu.m25.action;

import java.awt.Event;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import nc.funcnode.ui.action.MenuAction;

/**
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>关联功能菜单</li>
 * </ul>
 * <p>
 * </p>
 *
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-1-28 下午06:57:09
 */
public class RelatingFunctionsMenuAction extends MenuAction {

  private static final long serialVersionUID = 5669555245174882178L;

  public RelatingFunctionsMenuAction() {
    super("relatingFunctions", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0016")/*@res "关联功能"*/);

    // Ctrl+Alt+N
    this.putValue(Action.ACCELERATOR_KEY,
        KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK + Event.ALT_MASK));
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}