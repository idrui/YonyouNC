package nc.pubitf.pu.m23.ic.m45;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * ��ⵥת��ʱ��ѯ�������Ƿ��Ѿ�ת��
 * 
 * @since 6.0
 * @version 2010-12-21 ����07:01:57
 * @author wuxla
 */

public interface IQueryBTransAssetFor45 {

  /**
   * ��ⵥת��ʱ��ѯ�������Ƿ��Ѿ�ת��
   * 
   * @param bids ��������������
   * @return key������������ value���Ѿ�ת��ΪY������ΪN
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryBTransAssetFor45(String[] bids)
      throws BusinessException;
}
