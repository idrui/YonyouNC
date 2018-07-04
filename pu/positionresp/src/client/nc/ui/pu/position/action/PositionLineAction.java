package nc.ui.pu.position.action;

import nc.funcnode.ui.action.MenuAction;
import nc.ui.ml.NCLangRes;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>行维护功能按钮组
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-14 上午08:36:11
 */
public class PositionLineAction extends MenuAction {

  private static final long serialVersionUID = 2248947182555208820L;

  public PositionLineAction() {
    super("lineMenuAction", NCLangRes.getInstance().getStrByID("4004080_0", "04004080-0010")/*行维护*/);
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
