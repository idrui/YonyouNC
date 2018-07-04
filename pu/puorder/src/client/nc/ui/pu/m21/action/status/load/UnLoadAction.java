/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-17 下午04:33:35
 */
package nc.ui.pu.m21.action.status.load;

import nc.ui.pu.m21.action.status.sendout.UnSendoutAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.enumeration.OnwayStatusQryEnum;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>反装运
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-7-20 下午04:33:35
 */
public class UnLoadAction extends UnSendoutAction {

  private static final long serialVersionUID = 559596741041881092L;

  // private LoadQueryActionMy queryAction = null;

  public UnLoadAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_CANCELLOAD);
    // this.setBtnName("反装运");
    // this.setCode("unLoadAction");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName() + "(Shift+X)");
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.SHIFT_MASK));
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.m21.action.AbstractUnOnwayStatusAction#getStatus()
   */
  @Override
  public Integer getStatus() {
    return (Integer) OnwayStatus.STATUS_SHIP.value();
  }

  /**
   * 父类方法重写
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
  // public LoadQueryActionMy getQueryAction() {
  // return this.queryAction;
  // }

  @Override
  public String getStatusStr() {
    return OnwayStatusQryEnum.bisload.code();
  }

  /**
   * @param queryAction
   *          要设置的 queryAction
   */
  // public void setQueryAction(LoadQueryActionMy queryAction) {
  // this.queryAction = queryAction;
  // }
}
