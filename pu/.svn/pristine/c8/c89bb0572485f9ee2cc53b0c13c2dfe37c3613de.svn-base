package nc.ui.pu.m21.action.status.custom;

import nc.ui.pu.m21.action.status.AbstractStatusQueryAction;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.enumeration.OnwayStatusQryEnum;

/**
 * @since 6.0
 * @version 2011-1-26 обнГ12:56:41
 * @author wuxla
 */

public class CustomQueryAction extends AbstractStatusQueryAction {

  private static final long serialVersionUID = 2287180779948318881L;

  @Override
  protected int getOnwayStatusInt() {
    return ((Integer) OnwayStatus.STATUS_COMEIN.value()).intValue();
  }

  @Override
  protected String getOnwayStatusStr() {
    return OnwayStatusQryEnum.biscustom.code();
  }

}
