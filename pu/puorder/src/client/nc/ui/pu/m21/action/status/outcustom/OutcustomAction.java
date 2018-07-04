/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-17 下午04:31:13
 */
package nc.ui.pu.m21.action.status.outcustom;

import nc.ui.pu.m21.action.status.sendout.SendoutAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.enumeration.OnwayStatusQryEnum;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>装运
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-17 下午04:31:13
 */
public class OutcustomAction extends SendoutAction {

  private static final long serialVersionUID = 5705900890856150382L;

  // private OutcustomQueryActionMy queryAction = null;

  public OutcustomAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_OUT);
    // this.setBtnName("出关");
    // this.setCode("outcustomAction");
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
   * @see nc.ui.pu.m21.action.AbstractOnwayStatusAction#getStatus()
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
