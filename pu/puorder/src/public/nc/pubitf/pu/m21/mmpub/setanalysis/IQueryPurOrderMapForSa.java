package nc.pubitf.pu.m21.mmpub.setanalysis;

import nc.vo.pub.BusinessException;

/**
 * ���׷�����ѯ����-�ɹ������ֶ�ӳ��ӿ�
 * 
 * @since 6.0
 * @version 2012-11-5 ����04:04:28
 * @author liuyand
 */
public interface IQueryPurOrderMapForSa {
  /**
   * ���׷�����ѯ����-�ɹ������ֶ�ӳ��
   * <ul>
   * <li>Լ��:
   * <ol>
   * <li>�ֶ�ӳ������������������ñ�����
   * </ol>
   * <li>���׷�����������
   * <ol>
   * <li>���°汾
   * <li>�йر�Ϊfalse
   * <li>������-�ۼ����������-Ԥ��������������
   * <li>�Ǻ��ֵ���
   * </ul>
   * 
   * @return
   * @throws BusinessException
   */
  PurchaseOrderSaSupplyMapVO getSaSupplyMapVO() throws BusinessException;
}
