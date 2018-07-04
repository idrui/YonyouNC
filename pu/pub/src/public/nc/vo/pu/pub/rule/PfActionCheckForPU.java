package nc.vo.pu.pub.rule;

import nc.bs.pub.pf.CheckStatusCallbackContext;
import nc.bs.pub.pf.ICheckStatusCallback;
import nc.bs.scmpub.pf.PfBeforeAndAfterAction;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * 采购审批流检查询类
 * 增加实现ICheckStatusCallback接口，以在流程管理中心终止流程单据时，更新单据为自由态及其它业务检查
 * 
 * @since 6.0
 * @version 2011-5-9 下午01:41:44
 * @author liuchx
 */
public class PfActionCheckForPU extends PfBeforeAndAfterAction implements
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
      String[] names = new String[] {
        "fbillstatus", "approver", "taudittime"
      };
      bo.update(new ISuperVO[] {
        billVO.getParent()
      }, names);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }
}
