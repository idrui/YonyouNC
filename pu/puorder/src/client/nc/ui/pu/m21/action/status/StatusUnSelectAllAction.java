/**
 * $�ļ�˵��$
 *
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-20 ����04:40:38
 */
package nc.ui.pu.m21.action.status;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import nc.ui.pubapp.uif2app.actions.UnSelectAllAction;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>״̬ά��ȫ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-7-20 ����04:40:38
 */
public class StatusUnSelectAllAction extends UnSelectAllAction {

  private static final long serialVersionUID = 3914272209935300295L;

  public StatusUnSelectAllAction() {
    super();
    this.putValue(Action.SHORT_DESCRIPTION, nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("4004030_0", "04004030-0028")/*
                                                                 * @res
                                                                 * "ȫ��(Alt+A)"
                                                                 */);
    this.putValue(Action.ACCELERATOR_KEY,
        KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));
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

}
