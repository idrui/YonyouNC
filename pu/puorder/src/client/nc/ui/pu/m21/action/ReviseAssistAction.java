/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-6 上午11:41:47
 */
package nc.ui.pu.m21.action;

import nc.funcnode.ui.action.MenuAction;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>修订辅助功能
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-6 上午11:41:47
 */
public class ReviseAssistAction extends MenuAction {

  private static final long serialVersionUID = 681152104062580213L;

  public ReviseAssistAction() {
    super("reviseAssistAction", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0033")/*@res "辅助功能"*/);
  }

  /**
   * 父类方法重写
   *
   * @see javax.swing.AbstractAction#isEnabled()
   */
  @Override
  public boolean isEnabled() {
    return true;
  }

}