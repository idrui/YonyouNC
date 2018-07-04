package nc.pubitf.pu.m27;

import java.util.List;

import nc.vo.pu.m27.query.SettleBillInfo;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����ϵ��ѯ����-Ϊ�ɹ���Ʊ�ṩ���ɹ���Ʊ��Ӧ��ʱ���ã�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-7-13 ����01:57:43
 */
public interface ISettleBillQueryFor25 {
  /**
   * ��������������������ⵥ����ϸID�����ҽ����ϵ<br>
   * ��ѯ�����Ľ��������<br>
   * 1����Ʊ��ⵥ�������<br>
   * 2���������Գ����<br>
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_stock_b ��ⵥ����ϸID
   * @return �����ϵ
   * @throws BusinessException <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-27 ����11:29:49
   */
  public List<SettleBillInfo> querySettleBills(String pk_stock_b)
      throws BusinessException;

  /**
   * �����������������ݷ�Ʊ��ID�����ҽ����ϵ<br>
   * ��ѯ�����Ľ��������<br>
   * 1����Ʊ��ⵥ�������<br>
   * 2��������Ʊ�Գ����<br>
   * 3�������෢Ʊ�ж�Ӧ����ⵥ��<br>
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_invoices ��Ʊ�ĵ���ID
   * @return �����ϵ��key-��Ʊ����ID��value-�����ϵ�е���ⵥ��Ϣ
   * @throws BusinessException <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-13 ����06:22:00
   */
  public MapList<String, SettleBillInfo> querySettleBills(String[] pk_invoices)
      throws BusinessException;

  /**
   * �õ��ӽ��㵥�ϲ�ѯ����Դ�����ķ�Ʊ��ϢSQLƬ��
   * 
   * @param ordBIDWhr ��Դ�����Ĳ�ѯ������ʼ in (...)������Ϊ��ʱ��
   * @return select pk_invoice_b from ...
   * @throws BusinessException
   */
  String getQryNoOrderInvcBIDSql(String ordBIDWhr) throws BusinessException;
}
