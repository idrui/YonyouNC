/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-8 ����11:17:49
 */
package nc.ui.pu.m21.action;

import nc.funcnode.ui.action.MenuAction;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-8 ����11:17:49
 */
public class LinkBillMenuAction extends MenuAction {

  private static final long serialVersionUID = -3817687232211504335L;

  public LinkBillMenuAction() {
    super("linkBillMenuAction", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0046")/*@res "����"*/);
  }

  /**
   * ���෽����д
   *
   * @see javax.swing.AbstractAction#isEnabled()
   */
  @Override
  public boolean isEnabled() {
    return true;
  }

}