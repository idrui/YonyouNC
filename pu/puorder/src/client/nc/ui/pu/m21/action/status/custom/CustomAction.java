/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-17 下午04:31:13
 */
package nc.ui.pu.m21.action.status.custom;

import nc.ui.pu.m21.action.status.sendout.SendoutAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.enumeration.OnwayStatusQryEnum;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>报关
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-17 下午04:31:13
 */
public class CustomAction extends SendoutAction {

  private static final long serialVersionUID = 4883075124511268923L;

  // private CustomQueryActionMy queryAction = null;

  public CustomAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_APPLYCUSTOM);
    // this.setBtnName("报关");
    // this.setCode("customAction");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName() + "(Alt+X)");
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.m21.action.AbstractOnwayStatusAction#getIsDone()
   */
  // @Override
  // public boolean getIsDone() {
  // // return this.queryAction.getOperate().booleanValue();
  // return true;
  // }

  /**
   * @return queryAction
   */
  // public CustomQueryActionMy getQueryAction() {
  // return this.queryAction;
  // }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.m21.action.AbstractOnwayStatusAction#getStatus()
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
   *          要设置的 queryAction
   */
  // public void setQueryAction(CustomQueryActionMy queryAction) {
  // this.queryAction = queryAction;
  // }
}
