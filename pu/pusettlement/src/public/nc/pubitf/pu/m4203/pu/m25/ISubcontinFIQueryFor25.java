package nc.pubitf.pu.m4203.pu.m25;

import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * ί�мӹ��븱���ṩ����Ʊ�Ĳ�ѯ����
 * 
 * @since 6.0
 * @version 2011-4-8 ����06:37:09
 * @author �����
 */

public interface ISubcontinFIQueryFor25 {

  /**
   * ���������id��ѯ���ж�Ӧ�����ϣ��������㣺
   * <ul>
   * <li>�����
   * <li>�������ι�������
   * </ul>
   * 
   * @param itemIds
   * @return
   * @throws BusinessException
   */
  Map<String, String> queryQualityMaterial(String[] itemIds)
      throws BusinessException;

}
