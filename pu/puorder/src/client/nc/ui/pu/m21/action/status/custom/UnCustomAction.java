/**
 * $�ļ�˵��$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-20 ����04:33:35
 */
package nc.ui.pu.m21.action.status.custom;

import nc.ui.pu.m21.action.status.sendout.UnSendoutAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.enumeration.OnwayStatusQryEnum;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-7-20 ����04:33:35
 */
public class UnCustomAction extends UnSendoutAction {

  private static final long serialVersionUID = 5898250710176138720L;

  // private CustomQueryActionMy queryAction = null;

  public UnCustomAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_UNAPPLYCUSTOM);
    // this.setBtnName("������");
    // this.setCode("unCustomAction");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName() + "(Shift+X)");
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.SHIFT_MASK));
    // this.setEnabled(false);
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.m21.action.AbstractUnOnwayStatusAction#getIsDone()
   */
  // @Override
  // public boolean getIsDone() {
  // // return this.queryAction.getOperate().booleanValue();
  // return false;
  // }

  /**
   * @return queryAction
   */
  // public CustomQueryActionMy getQueryAction() {
  // return this.queryAction;
  // }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.m21.action.AbstractUnOnwayStatusAction#getStatus()
   */
  @Override
  public Integer getStatus() {
    return (Integer) OnwayStatus.STATUS_COMEIN.value();
  }

  @Override
  public String getStatusStr() {
    return OnwayStatusQryEnum.biscustom.code();
  }

  /**
   * @param queryAction
   *          Ҫ���õ� queryAction
   */
  // public void setQueryAction(CustomQueryActionMy queryAction) {
  // this.queryAction = queryAction;
  // }
}
