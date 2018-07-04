package nc.pubimpl.pu.it;

import nc.pubimpl.pu.it.action.SettleBillCancelIAActionForIT;
import nc.pubimpl.pu.it.action.SettleBillDeleteActionForIT;
import nc.pubimpl.pu.it.action.SettleBillToIAActionForIT;
import nc.pubitf.pu.it.ISettleBillMaintainForIT;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since 6.31
 * @version 2013-11-21 ÏÂÎç01:35:02
 * @author mengjian
 */
public class SettleBillMaintainImplForIT implements ISettleBillMaintainForIT {

  @Override
  public SettleBillVO[] cancelToIA4IT(SettleBillVO[] settleBillVOs)
      throws BusinessException {
    SettleBillVO[] vos = null;
    try {
      SettleBillCancelIAActionForIT bp = new SettleBillCancelIAActionForIT();
      vos = bp.cancelIA(settleBillVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return vos;
  }

  @Override
  public void deleteSettleBills4IT(SettleBillVO[] settleBillVOs)
      throws BusinessException {
    try {
      new SettleBillDeleteActionForIT().delete(settleBillVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public SettleBillVO[] toIA4IT(SettleBillVO[] settleBillVOs)
      throws BusinessException {
    SettleBillVO[] vos = null;
    try {
      SettleBillToIAActionForIT bp = new SettleBillToIAActionForIT();
      vos = bp.toIA(settleBillVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return vos;
  }
}
