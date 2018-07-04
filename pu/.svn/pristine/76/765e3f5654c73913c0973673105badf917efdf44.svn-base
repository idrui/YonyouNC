package nc.vo.pu.m20.rule;

import nc.bs.pub.pf.CheckStatusCallbackContext;
import nc.bs.pub.pf.ICheckStatusCallback;
import nc.bs.scmpub.pf.PfBeforeAndAfterAction;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.vo.pf.change.IActionDriveChecker;
import nc.vo.pf.change.IChangeVOCheck;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.pf.IPFSourceBillFinder;
import nc.vo.pub.pf.SourceBillInfo;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

public class PFPrayBillVOCheck extends PfBeforeAndAfterAction implements
    IActionDriveChecker, IChangeVOCheck, IPFSourceBillFinder,
    ICheckStatusCallback {

  @Override
  public void callCheckStatus(CheckStatusCallbackContext cscc)
      throws BusinessException {
    try {
      // 是否终止
      if (!cscc.isTerminate()) {
        return;
      }
      if (null == cscc.getBillVo()
          || !(cscc.getBillVo() instanceof AbstractBill)) {
        return;
      }
      AbstractBill billVO = (AbstractBill) cscc.getBillVo();
      if (billVO.getParentVO() == null) {
        return;
      }
      // 2013-1-29 流程终止，需要更新状态和审批信息，不更新审批信息，再次提交无法收回。
      VOUpdate<ISuperVO> bo = new VOUpdate<ISuperVO>();
      String[] names =
          new String[] {
            PraybillHeaderVO.FBILLSTATUS, PraybillHeaderVO.APPROVER,
            PraybillHeaderVO.TAUDITTIME
          };
      bo.update(new ISuperVO[] {
        billVO.getParent()
      }, names);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public boolean checkValidOrNeed(AggregatedValueObject srcBillVo,
      String srcAction, String destBilltype, String drivedAction)
      throws BusinessException {
    return true;
  }

  @Override
  public SourceBillInfo[] findSourceBill(String pk_srcBilltype,
      Object billEntity) throws BusinessException {
    return null;
  }

  @Override
  public boolean isEnableDrive(String srcBilltype,
      AggregatedValueObject srcBillVO, String srcAction, String destBillType,
      String beDrivedActionName) throws BusinessException {
    return true;
  }

}
