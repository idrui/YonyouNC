package nc.pubitf.pu.m21.mmdp.dt;

import nc.vo.pub.BusinessException;

/**
 * PU-������ٲ�ѯ����-�ɹ������ֶ�ӳ��ӿ�
 * 
 * @since 6.0
 * @version 2012-11-6 ����10:33:25
 * @author liuyand
 */
public interface IQueryPurOrderMapForDT {

  /**
   * �ɹ�������Ϊ������ٹ������ֶ�ӳ��
   * <ul>
   * <li>Լ��
   * <ol>
   * <li>�ֶ�ӳ������������������ñ�����
   * <li>�ӿڲ����Ǻ��ֵ���,ֻҪ���ֵ��ݡ�
   * </ol>
   * <li>������ٹ�������
   * <ol>
   * <li>δɾ��
   * <li>�Ǻ��ֵ���
   * </ul>
   * 
   * @return
   * @throws BusinessException
   */
  PurchaseOrderDTSupplyMapVO getDTSupplyMapVO() throws BusinessException;

}
