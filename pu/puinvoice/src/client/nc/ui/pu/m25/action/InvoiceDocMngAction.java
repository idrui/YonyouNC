/**
 * $�ļ�˵��$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-11 ����02:51:58
 */
package nc.ui.pu.m25.action;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import nc.ui.pubapp.uif2app.actions.DocMngAction;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-11 ����02:51:58
 */
public class InvoiceDocMngAction extends DocMngAction {

  private static final long serialVersionUID = -2161247710444497603L;

  public InvoiceDocMngAction() {
    super();
    // ctrl+D
    this.putValue(Action.ACCELERATOR_KEY,
        KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_MASK));
  }

}
