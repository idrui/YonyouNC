package nc.pubitf.pu.m422x.pubquery;

import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pub.BusinessException;

/**
 * �����������뵥�ṩ����ģ��Ĺ�����ѯ����
 * 
 * @since 6.3
 * @version 2012-10-25 ����03:20:02
 * @author fanly3
 */
public interface IStoreReqPubQuery {

  /**
   * ͨ�������������뵥����ID��ѯ��Ӧ�������������뵥����VO��֧��������ѯ��
   * 
   * @param bids �����������뵥����id����
   * @return �����������뵥����VO
   * @throws BusinessException
   */
  StoreReqAppItemVO[] queryItemVOByBids(String[] bids) throws BusinessException;

  StoreReqAppItemVO[] queryItemVOByCSourcebids(String[] bids, String[] fields)
      throws BusinessException;
}
