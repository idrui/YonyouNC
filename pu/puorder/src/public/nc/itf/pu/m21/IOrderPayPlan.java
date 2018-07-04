package nc.itf.pu.m21;

import nc.itf.pubapp.pub.smart.ISmartService;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pub.BusinessException;

/**
 * ����ƻ�ά��
 * 
 * @since 6.0
 * @version 2011-1-4 ����08:31:54
 * @author wuxla
 */

public interface IOrderPayPlan extends ISmartService {
  /**
   * ȡ����������
   * 
   * @param views ����ƻ���ͼVO
   * @return
   * @throws BusinessException
   */
  PayPlanViewVO[] cancelPayReq(PayPlanViewVO[] views) throws BusinessException;

  /**
   * ���ɸ�������
   * 
   * @param views ����ƻ���ͼVO
   * @return
   * @throws BusinessException
   */
  PayPlanViewVO[] toPayReq(PayPlanViewVO[] views) throws BusinessException;
}
