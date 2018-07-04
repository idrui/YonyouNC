/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-17 下午04:33:35
 */
package nc.ui.pu.m21.action.status.outcustom;

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
public class UnoutcustomAction extends UnSendoutAction {

  private static final long serialVersionUID = 2609276443587790632L;

  // private OutcustomQueryActionMy queryAction = null;

  public UnoutcustomAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_CANCELOUT);
    // this.setBtnName("反出关");
    // this.setCode("unOutcustomAction");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName() + "(Shift+X)");
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.SHIFT_MASK));
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
  // public OutcustomQueryActionMy getQueryAction() {
  // return this.queryAction;
  // }

  /**
   * 父类方法重写
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
   *          要设置的 queryAction
   */
  // public void setQueryAction(OutcustomQueryActionMy queryAction) {
  // this.queryAction = queryAction;
  // }
}
