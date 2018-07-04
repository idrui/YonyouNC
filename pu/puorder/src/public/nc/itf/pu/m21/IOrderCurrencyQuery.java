package nc.itf.pu.m21;

import nc.vo.pu.m21.query.currency.CurrencyInfo;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ֺͻ��ʵĲ�ѯ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-28 ����11:14:09
 */
public interface IOrderCurrencyQuery {
  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param origCurrencyId ԭ�ұ���ID
   * @param date ����
   * @param currencyInfos ���ֺͻ�����Ϣ������Я�����������֯��ID��
   * @return ��������֯��λ�ҡ��۱����ʡ����ű�λ�һ��ʡ���֯��λ�һ��ʵ���Ϣ
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-28 ����11:33:28
   */
  public CurrencyInfo[] queryCurrencyInfo(String origCurrencyId, UFDate date,
      CurrencyInfo[] currencyInfos) throws BusinessException;
}
