/**
 * $�ļ�˵��$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-22 ����09:48:33
 */
package nc.itf.pu.m21;

import nc.vo.pu.m21.query.price.OrderPriceQueryParam;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ����������ѯ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-10-22 ����09:48:33
 */
public interface IOrderDefPriceSideForm {

  /**
   * ���������������ɹ����������ѯ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param param ѯ�۲�������
   * @param ps �۸���Դ
   * @param pp ����PO28
   * @return ����ִ����ϻὫ��Я�����ĸ��۸���Ϣ�Ĳ�������
   * @throws BusinessException <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-22 ����09:49:59
   */
  public OrderPriceQueryParam queryOrderPrice(OrderPriceQueryParam param,
      PriceSource ps, PricePriority pp) throws BusinessException;

}
