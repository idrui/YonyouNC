package nc.bs.pu.m21.query.price;

import nc.bs.pu.m21.query.price.cal.OrderPriceQuery;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pub.lang.UFDate;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������ͼ۵Ĳ�ѯBP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-21 ����10:03:55
 */
public class LowestPriceQueryBP {
  /**
   * ����������������ѯ������ͼۡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param purchaseOrg
   *          �ɹ���֯
   * @param supplier
   *          ��Ӧ��
   * @param currency
   *          ����
   * @param materials
   *          ����OID����
   * @return �����۸������
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-23 ����04:58:14
   */
  public OrderPriceData[] queryLowestPrice(String[] purchaseOrgs,
      String[] suppliers, String[] currencies, String[] materials,
      UFDate startDate, UFDate endDate) {
    return new OrderPriceQuery().queryLowestPrice(purchaseOrgs, suppliers,
        currencies, materials, startDate, endDate);
  }

}
