/**
 * $�ļ�˵��$
 *
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-12 ����02:27:17
 */
package nc.ui.pu.m21.action.orderclose;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.funcnode.ui.action.INCAction;
import nc.ui.uif2.NCAction;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ�����Ԥ��
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-4-12 ����02:27:17
 */
public class ClosePreviewAction extends NCAction {

  /**
   *
   */
  private static final long serialVersionUID = -2263889216674435468L;

  public ClosePreviewAction() {

    String name = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0025")/*@res "��ӡԤ��"*/;
    this.setBtnName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC001-0000112")/*@res "Ԥ��"*/);
    this.putValue(INCAction.CODE, name);
    this.putValue(Action.SHORT_DESCRIPTION, name);
  }

  /**
   * ���෽����д
   *
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    return;
  }

  @Override
  protected boolean isActionEnable() {
    return true;
  }

}