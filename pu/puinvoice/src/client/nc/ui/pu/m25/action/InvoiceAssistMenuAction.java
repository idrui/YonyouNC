/**
 * $�ļ�˵��$
 *
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-11 ����02:24:26
 */
package nc.ui.pu.m25.action;

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
 * @author tianft
 * @time 2010-4-11 ����02:24:26
 */
public class InvoiceAssistMenuAction extends MenuAction {

  private static final long serialVersionUID = 1353951264452542520L;

  public InvoiceAssistMenuAction() {
    super("assistMenuAction", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0000")/*@res "��������"*/);
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}