/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-11-3 ����07:58:16
 */
package nc.ui.pu.m4t.action;

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
 * @time 2010-11-3 ����07:58:16
 */
public class AssistAction extends MenuAction {

  private static final long serialVersionUID = -229854938215820102L;

  public AssistAction() {
    super("assistMenuAction", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0044")/*@res "��������"*/);
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