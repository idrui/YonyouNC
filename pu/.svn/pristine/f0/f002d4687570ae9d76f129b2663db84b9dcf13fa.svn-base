package nc.ui.pu.m21.action.status.load;

import nc.ui.pu.m21.action.status.AbstractStatusQueryAction;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.enumeration.OnwayStatusQryEnum;

/**
 * @since 6.0
 * @version 2011-1-26 обнГ12:58:45
 * @author wuxla
 */

public class LoadQueryAction extends AbstractStatusQueryAction {

  private static final long serialVersionUID = -2716167733829107739L;

  @Override
  protected int getOnwayStatusInt() {
    return ((Integer) OnwayStatus.STATUS_SHIP.value()).intValue();
  }

  @Override
  protected String getOnwayStatusStr() {
    return OnwayStatusQryEnum.bisload.code();
  }

}
