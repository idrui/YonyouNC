package nc.itf.pu.m21;

import nc.itf.pubapp.pub.smart.ISmartService;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pub.BusinessException;

/**
 * 付款计划维护
 * 
 * @since 6.0
 * @version 2011-1-4 上午08:31:54
 * @author wuxla
 */

public interface IOrderPayPlan extends ISmartService {
  /**
   * 取消付款申请
   * 
   * @param views 付款计划视图VO
   * @return
   * @throws BusinessException
   */
  PayPlanViewVO[] cancelPayReq(PayPlanViewVO[] views) throws BusinessException;

  /**
   * 生成付款申请
   * 
   * @param views 付款计划视图VO
   * @return
   * @throws BusinessException
   */
  PayPlanViewVO[] toPayReq(PayPlanViewVO[] views) throws BusinessException;
}
