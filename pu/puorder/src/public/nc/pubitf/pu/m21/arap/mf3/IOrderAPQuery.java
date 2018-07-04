package nc.pubitf.pu.m21.arap.mf3;

import java.util.List;

import nc.vo.pub.BusinessException;

/**
 * @since 6.0
 * @version 2011-7-4 ����03:33:14
 * @author wuxla
 */

public interface IOrderAPQuery {

  /**
   * ���ݶ�������id��ѯ��������ƻ�id
   * 
   * @param bids ��������id
   * @return ��������ƻ�id�����Ϊ�գ�����null
   * @throws BusinessException
   */
  List<String> getPayPlanIDByOrderBID(List<String> bids)
      throws BusinessException;
}
