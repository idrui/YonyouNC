/**
 * $�ļ�˵��$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-17 ����04:33:35
 */
package nc.ui.pu.m21.action.status.outcustom;

import nc.ui.pu.m21.action.status.sendout.UnSendoutAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.enumeration.OnwayStatusQryEnum;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��װ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-7-20 ����04:33:35
 */
public class UnoutcustomAction extends UnSendoutAction {

  private static final long serialVersionUID = 2609276443587790632L;

  // private OutcustomQueryActionMy queryAction = null;

  public UnoutcustomAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_CANCELOUT);
    // this.setBtnName("������");
    // this.setCode("unOutcustomAction");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName() + "(Shift+X)");
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.SHIFT_MASK));
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.m21.action.AbstractUnOnwayStatusAction#getIsDone()
   */
  // @Override
  // public boolean getIsDone() {
  // return this.queryAction.getOperate().booleanValue();
  // }

  /**
   * @return queryAction
   */
  // public OutcustomQueryActionMy getQueryAction() {
  // return this.queryAction;
  // }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.m21.action.AbstractUnOnwayStatusAction#getStatus()
   */
  @Override
  public Integer getStatus() {
    return (Integer) OnwayStatus.STATUS_GETOUT.value();
  }

  @Override
  public String getStatusStr() {
    return OnwayStatusQryEnum.bisoutcustom.code();
  }

  /**
   * @param queryAction
   *          Ҫ���õ� queryAction
   */
  // public void setQueryAction(OutcustomQueryActionMy queryAction) {
  // this.queryAction = queryAction;
  // }
}
