package nc.pubitf.pu.m20.mmdp.dt;

import nc.vo.pub.BusinessException;

/**
 * ������ٲ�ѯ����-�빺���ֶ�ӳ��ӿ�
 * 
 * @since 6.3
 * @version 2012-11-2 ����09:34:59
 * @author fanly3
 */
public interface IQueryPrayOrderMapForDT {
  /**
   * �����빺����Ϊ������ٹ������ֶ�ӳ��
   * 
   * @return PrayOrderDTSupplyMapVO
   * @throws BusinessException
   */
  PrayOrderDTSupplyMapVO getDTSupplyMapVO() throws BusinessException;
}
