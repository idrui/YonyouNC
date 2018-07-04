/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-6 ����08:03:48
 */
package nc.pubitf.pu.m21.mm;

import nc.pubitf.scmpub.scmpub.mmpps.calc.ISupplyResultForCalc;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����������ų̣��ɹ������������ӿ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-6 ����08:03:48
 */
public interface IOrderQueryExecForMM {

  /**
   * <p>
   * ʹ�ó�������Ʒ�ɱ�ģ�����ɳɱ�BOM��������ʱ����Ҫȡ���ϵ����²ɹ�����Ϊ��������ĵ��ۡ�
   * <ul>
   * <li>
   * <li>���ҹ�����֯�����ϵ����²ɹ���
   * </ul>
   * 
   * @param pk_arrvstoorgs ������֯���ջ������֯��
   * @param moids ����oid
   * @return OrderPriceData �鵽�����¼ۡ����ұ���
   */
  public OrderPriceData[] getLatestPrice(String[] pk_arrvstoorgs, String[] moids)
      throws BusinessException;

  /**
   * mrp/mps��ѯ�ɹ������Ĺ�����SQLƬ��
   * 
   * @param includeRed �Ƿ��ѯ�˿�ĵ���
   * @return ����sqlƬ�ε�vo��������һЩ�ֶ���Ϣ��
   * @throws BusinessException
   */
  public ISupplyResultForCalc getSupplyResult(boolean includeRed)
      throws BusinessException;
}
