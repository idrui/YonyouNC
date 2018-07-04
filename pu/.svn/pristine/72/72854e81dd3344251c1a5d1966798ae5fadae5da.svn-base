package nc.impl.pu.m21;

import nc.impl.pu.m21.action.PayPlanCancleToPayReqAction;
import nc.impl.pu.m21.action.PayPlanSaveAction;
import nc.impl.pu.m21.action.PayPlanToPayReqAction;
import nc.impl.pubapp.pub.smart.SmartServiceImpl;
import nc.itf.pu.m21.IOrderPayPlan;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.uif2.LoginContext;

/**
 * 付款计划实现类
 * 
 * @since 6.0
 * @version 2011-1-4 上午08:32:30
 * @author wuxla
 */

public class OrderPayPlanImpl extends SmartServiceImpl implements IOrderPayPlan {
  @Override
  public BatchOperateVO batchSave(BatchOperateVO batchVO) throws BusinessException {
    try {
      if (null == batchVO) {
        return null;
      }
      return new PayPlanSaveAction().batchSave(batchVO);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public PayPlanViewVO[] cancelPayReq(PayPlanViewVO[] views)
      throws BusinessException {
    try {
      return new PayPlanCancleToPayReqAction().canclePayReq(views);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public ISuperVO[] queryByDataVisibilitySetting(LoginContext context,
      Class<? extends ISuperVO> clz) throws BusinessException {
    try {
      return null;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public PayPlanViewVO[] toPayReq(PayPlanViewVO[] views)
      throws BusinessException {
    try {
      return new PayPlanToPayReqAction().toPayReq(views);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
