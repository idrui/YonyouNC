/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-1 ����08:36:11
 */
package nc.ui.pu.m21.action;

import nc.funcnode.ui.action.MenuAction;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������ܰ�ť��
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-1 ����08:36:11
 */
public class AssistAction extends MenuAction {

  private static final long serialVersionUID = 2248947182555208820L;

  public AssistAction() {
    super("assistMenuAction", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0033")/*@res "��������"*/);
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}