/**
 *
 */
package nc.ui.pu.m25.action;

import nc.funcnode.ui.action.MenuAction;

/**
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ӡ�˵� ����</li>
 * </ul>
 * <p>
 * </p>
 *
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-1-28 ����07:09:00
 */
public class InvoicePrintMenuAction extends MenuAction {

  private static final long serialVersionUID = 8465667399177564811L;

  public InvoicePrintMenuAction() {
    super("printMunu", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC001-0000007")/*@res "��ӡ"*/);
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}