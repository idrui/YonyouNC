package nc.pubitf.pu.m21.ia.mi2;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * @since 6.0
 * @version 2011-4-15 ����02:50:32
 * @author wuxla
 */

public interface IOrderQueryForI2 {
  /**
   * ���ݶ�����id��ѯ��Դֱ�����۶�����id�������Դ���۶�������ֱ�ˣ��򷵻�map��û��
   * 
   * @param bids
   * @return key���ɹ���������id value��ֱ�����۶�������id
   * @throws BusinessException
   */
  Map<String, String> queryDirectSOBidForIA(String[] bids)
      throws BusinessException;

  /**
   * ���ݶ�����id��ѯ��Դ���۶�����id�ͽ�������
   * 
   * @param bids ��������id����
   * @return MapList-key���ɹ���������id��value��ArrayList��0-���۶�����id��1-��Դ�������ͣ�
   * @throws BusinessException
   */
  MapList<String, String> querySOBidAndCtransIdForIA(String[] bids)
      throws BusinessException;
}
