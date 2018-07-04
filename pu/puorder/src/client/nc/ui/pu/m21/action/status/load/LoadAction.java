/**
 * $�ļ�˵��$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-17 ����04:31:13
 */
package nc.ui.pu.m21.action.status.load;

import nc.ui.pu.m21.action.status.sendout.SendoutAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.enumeration.OnwayStatusQryEnum;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>װ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-17 ����04:31:13
 */
public class LoadAction extends SendoutAction {

  private static final long serialVersionUID = -5802492378622450144L;

  // private LoadQueryActionMy queryAction = null;
  public LoadAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_LOAD);
    // this.setBtnName("װ��");
    // this.setCode("loadAction");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName() + "(Alt+X)");
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
  }

  /**
   * @param queryAction
   *          Ҫ���õ� queryAction
   */
  // public void setQueryAction(LoadQueryActionMy queryAction) {
  // this.queryAction = queryAction;
  // }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.m21.action.AbstractOnwayStatusAction#getIsDone()
   */
  // @Override
  // public boolean getIsDone() {
  // return this.queryAction.getOperate().booleanValue();
  // }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.m21.action.AbstractOnwayStatusAction#getStatus()
   */
  @Override
  public Integer getStatus() {
    return (Integer) OnwayStatus.STATUS_SHIP.value();
  }

  /**
   * @return queryAction
   */
  // public LoadQueryActionMy getQueryAction() {
  // return this.queryAction;
  // }

  @Override
  public String getStatusStr() {
    return OnwayStatusQryEnum.bisload.code();
  }

}
