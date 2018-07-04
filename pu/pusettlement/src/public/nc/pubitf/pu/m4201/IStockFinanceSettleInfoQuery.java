package nc.pubitf.pu.m4201;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * ��ѯ�ɹ������񸱱�������Ϣ
 * 
 * @since 6.0
 * @version 2011-3-25 ����03:18:45
 * @author yinfy
 */

public interface IStockFinanceSettleInfoQuery {
  /**
   * ��ѯ�ɹ��������н���ƽ������
   * 
   * @param bids �ɹ���⸱������pks
   * @return
   * @throws BusinessException
   */
  public Map<String, UFDouble> getPriceArrayByRowIdsNoTax(String[] bids)
      throws BusinessException;

  /**
   * ��ѯ�ѽ�������
   * 
   * @param bids ����bid
   * @return
   * @throws BusinessException
   */
  public Map<String, UFDouble> getTotalSettleNum(String[] bids)
      throws BusinessException;
}
