/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-9 ����01:37:39
 */
package nc.ui.pu.m21.action;

import nc.funcnode.ui.action.MenuAction;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-9 ����01:37:39
 */
public class RelateFuncMenuAction extends MenuAction {

  private static final long serialVersionUID = -6520295681646380410L;

  public RelateFuncMenuAction() {
    super("relateFunMenuAction", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0064")/*@res "��������"*/);
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