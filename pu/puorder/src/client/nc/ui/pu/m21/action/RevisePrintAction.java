/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-6 ����11:52:39
 */
package nc.ui.pu.m21.action;

import nc.funcnode.ui.action.MenuAction;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ӡ�˵� ����
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-6 ����11:52:39
 */
public class RevisePrintAction extends MenuAction {

  private static final long serialVersionUID = 7828675684944749161L;

  public RevisePrintAction() {
    super("revisePrintMenuAction", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC001-0000007")/*@res "��ӡ"*/);
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