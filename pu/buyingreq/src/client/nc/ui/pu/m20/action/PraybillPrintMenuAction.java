/**
 * $�ļ�˵��$
 *
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-2 ����11:26:19
 */
package nc.ui.pu.m20.action;

import nc.funcnode.ui.action.MenuAction;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ӡ��ť��
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-2-2 ����11:26:19
 */
public class PraybillPrintMenuAction extends MenuAction {

  private static final long serialVersionUID = 2248947182555208820L;

  /**
   * PraybillPrintMenuAction �Ĺ�����
   */
  public PraybillPrintMenuAction() {
    super("printMenuAction", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC001-0000007")/*@res "��ӡ"*/);
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}