/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-1 ����08:36:11
 */
package nc.ui.pu.m20.action;

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
 * @author GGR
 * @time 2010-4-14 ����08:36:11
 */
public class PraybillAssistAction extends MenuAction {

  private static final long serialVersionUID = 2248947182555208820L;

  /**
   * PraybillAssistAction �Ĺ�����
   */
  public PraybillAssistAction() {
    super("assistMenuAction", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0","04004020-0006")/*@res "��������"*/);
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}