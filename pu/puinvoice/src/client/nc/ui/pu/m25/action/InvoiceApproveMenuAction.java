/**
 *
 */
package nc.ui.pu.m25.action;

import nc.funcnode.ui.action.MenuAction;

/**
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>审核的下拉按钮</li>
 * </ul>
 * <p>
 * </p>
 *
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-1-28 下午04:57:55
 */
public class InvoiceApproveMenuAction extends MenuAction {
  private static final long serialVersionUID = -6304790981636331753L;

  public InvoiceApproveMenuAction() {
    super("approve_operation", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC001-0000027")/*@res "审核"*/);
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}