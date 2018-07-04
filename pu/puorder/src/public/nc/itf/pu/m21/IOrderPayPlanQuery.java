package nc.itf.pu.m21;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.AggPayPlanVO;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.para.SysInitVO;

/**
 * ����ƻ���ѯ
 * 
 * @since 6.0
 * @version 2011-1-4 ����09:01:07
 * @author wuxla
 */

public interface IOrderPayPlanQuery {
  /**
   * ����ƻ��������и������뵥���߸����ò���ֵ�������޸ġ�
   * ��ѯ����ƻ��Ƿ��Ѿ����ɸ���������߸��
   * 
   * @return
   * @throws BusinessException
   */
  boolean checkPO88Para(SysInitVO vo) throws BusinessException;

  // PayPlanViewVO[] queryPayPlanVOs(String cond) throws BusinessException;

  /**
   * ��ѯ
   * 
   * @param queryScheme��ѯ����
   * @return
   * @throws BusinessException
   */
  PayPlanViewVO[] queryByQueryScheme(IQueryScheme queryScheme)
      throws BusinessException;

  PayPlanViewVO[] queryPayPlanViews(String[] bids) throws BusinessException;

  /**
   * ���ݶ�����ͷid��ѯ����ƻ�
   * 
   * @param hids
   * @return
   * @throws BusinessException
   */
  AggPayPlanVO[] queryPayPlanVOs(String[] hids) throws BusinessException;
}
