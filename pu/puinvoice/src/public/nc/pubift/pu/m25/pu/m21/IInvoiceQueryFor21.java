package nc.pubift.pu.m25.pu.m21;

import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * Ϊ�����ṩ�Ĳ�ѯ����
 * 
 * @since 6.0
 * @version 2011-3-6 ����03:58:25
 * @author wuxla
 */

public interface IInvoiceQueryFor21 {
  /**
   * ���ݷ�Ʊ��id�ҵ�������
   * 
   * @param bids
   * @return
   */
  Map<String, String> queryOrderBidByInvoiceBid(String[] bids)
      throws BusinessException;

}
