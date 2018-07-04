package nc.ui.pu.m21.action.status.outcustom;

import nc.ui.pu.m21.action.status.AbstractStatusQueryAction;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.enumeration.OnwayStatusQryEnum;

/**
 * @since 6.0
 * @version 2011-1-26 ÏÂÎç01:00:21
 * @author wuxla
 */

public class OutcustomQueryAction extends AbstractStatusQueryAction {

  private static final long serialVersionUID = 2346426737923753460L;

  @Override
  protected int getOnwayStatusInt() {
    return ((Integer) OnwayStatus.STATUS_GETOUT.value()).intValue();
  }

  @Override
  protected String getOnwayStatusStr() {
    return OnwayStatusQryEnum.bisoutcustom.code();
  }

}
