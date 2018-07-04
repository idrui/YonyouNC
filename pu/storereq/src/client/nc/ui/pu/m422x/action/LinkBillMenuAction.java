/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-19 下午01:56:08
 */
package nc.ui.pu.m422x.action;

import nc.funcnode.ui.action.MenuAction;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-19 下午01:56:08
 */
public class LinkBillMenuAction extends MenuAction {

  private static final long serialVersionUID = -226424813078547336L;

  public LinkBillMenuAction() {
    super("linkBillMenuAction", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004010_0","04004010-0002")/*@res "联查"*/);
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