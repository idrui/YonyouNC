package nc.itf.pu.m422x;

import nc.vo.pu.m21.query.price.OrderPriceQueryParam;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����������뵥ѯ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.5
 * @since 6.5
 * @author sw
 * @time 2015-8-17
 */
public interface IStoreReqDefaultPriceQuery {
  /**
   * ��������������Ϊ�����������뵥ѯ�ۡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param param
   *          ѯ�۲�������
   * @return ����ִ����ϻὫ��Я�����ĸ��۸���Ϣ�Ĳ�������
   *         <p>
   * @since 6.5
   * @author sw
   * @time 2015-8-17
   */
  public OrderPriceQueryParam[] queryOrderPrice4StoreReq(
      OrderPriceQueryParam[] param) throws BusinessException;
}
