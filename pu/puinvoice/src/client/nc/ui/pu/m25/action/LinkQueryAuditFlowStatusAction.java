/**
 * $�ļ�˵��$
 *
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-11 ����02:29:00
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������״̬
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-11 ����02:29:00
 */
public class LinkQueryAuditFlowStatusAction extends InvoiceNormalAction {

  private static final long serialVersionUID = -7647009301428651738L;

  public LinkQueryAuditFlowStatusAction() {
    this.setBtnName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0008")/*@res "������״̬"*/);
    this.setCode("auditFlowStatus");
    this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
    // Alt+S
    this.putValue(Action.ACCELERATOR_KEY,
        KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
  }

  /**
   * ���෽����д
   *
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    //
  }

  /**
   * ���෽����д
   *
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    return super.isActionEnable();
  }

}