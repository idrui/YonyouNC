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
 * <li>���鵥��
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-11 ����02:29:00
 */
public class LinkQueryBillAction extends InvoiceNormalAction {

  private static final long serialVersionUID = -4075835748710745407L;

  public LinkQueryBillAction() {
    this.setBtnName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0009")/*@res "���鵥��"*/);
    this.setCode("btnLinkQueryBill");
    this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
    // Ctrl+K
    this.putValue(Action.ACCELERATOR_KEY,
        KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_MASK));
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