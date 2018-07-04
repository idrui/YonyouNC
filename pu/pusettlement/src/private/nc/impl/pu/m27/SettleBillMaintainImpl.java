package nc.impl.pu.m27;

import nc.bs.pu.m27.settlebill.SettleBillCancelIABP;
import nc.bs.pu.m27.settlebill.SettleBillDeleteBP;
import nc.bs.pu.m27.settlebill.SettleBillToIABP;
import nc.itf.pu.m27.ISettleBillMaintain;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class SettleBillMaintainImpl implements ISettleBillMaintain {

  @Override
  public SettleBillVO[] cancelToIA(SettleBillVO[] settleBillVOs)
      throws BusinessException {
    SettleBillVO[] vos = null;
    try {
      SettleBillCancelIABP bp = new SettleBillCancelIABP();
      vos = bp.cancelIA(settleBillVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return vos;
  }

  @Override
  public void deleteSettleBills(SettleBillVO[] settleBillVOs)
      throws BusinessException {
    try {
      new SettleBillDeleteBP().delete(settleBillVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public SettleBillVO[] toIA(SettleBillVO[] settleBillVOs)
      throws BusinessException {
    SettleBillVO[] vos = null;
    try {
      SettleBillToIABP bp = new SettleBillToIABP();
      vos = bp.toIA(settleBillVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return vos;
  }

}
