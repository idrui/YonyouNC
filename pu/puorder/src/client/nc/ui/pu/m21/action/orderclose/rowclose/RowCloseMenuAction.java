/**
 * $文件说明$
 *
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-9 上午09:37:23
 */
package nc.ui.pu.m21.action.orderclose.rowclose;

import nc.funcnode.ui.action.MenuAction;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>行关闭
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-4-9 上午09:37:23
 */
public class RowCloseMenuAction extends MenuAction {

  /**
   *
   */
  private static final long serialVersionUID = -1036650435548395694L;

  public RowCloseMenuAction() {
    super("row_Close_Operator", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0016")/*@res "行关闭"*/);
  }

}