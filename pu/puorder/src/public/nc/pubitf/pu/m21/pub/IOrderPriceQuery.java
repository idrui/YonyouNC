package nc.pubitf.pu.m21.pub;

import nc.vo.pu.m21.query.price.OrderItemPriceVO;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>Ϊ��Ʊѯ�ۡ��ݹ�ѯ�ۡ��빺��ѯ���ṩ����
 * <li>Ϊ�ڲ����ײ�ѯ���²ɹ����ṩ����
 * <li>Ϊ���������ѯ���²ɹ����ṩ����
 * <li>Ϊ�ɹ���ⵥ��ѯ�����۸��ṩ������ⵥ�����ʲ���Ƭʹ�ã�
 * </ul>
 * 
 * @version 2010-3-21 ����02:56:30
 * @since 6.0
 * @author duy
 */
public interface IOrderPriceQuery {
  /**
   * ����������������ѯĳ�ض��ɹ������еĵ���
   * 
   * @param orderItemIds �ɹ�������ID����
   * @return ���������Ķ����۸���������
   * @throws BusinessException
   * @since 6.0
   */
  public OrderItemPriceVO[] queryItemPrice(String[] orderItemIds)
      throws BusinessException;

  /**
   * ��ѯ���������¼۸�
   * Ϊ�ɹ��۸��ṩ�����¼۲�ѯ�ӿڡ�
   * �ӿ�����Ϊ��ѯVO���飬���а�����Ӧ�̡����֡��ɹ���֯������OID���۸����Ϣ��
   * ��ʵ�ִ˽ӿ�ʱ�����ݲ���vo���������в�ѯ���������ֵ����VO��ֱ�ӷ��ء�
   * ��������Ϊ���飬���֮ǰ�ṩ�Ľӿڣ��˽ӿ��ܹ�֧�ֶ๩Ӧ�̡�����֡���ɹ���֯��������
   * 
   * @param conds ���¼۲�ѯVO����
   * @return ͬ�ϡ�
   * @throws BusinessException
   */
  public LastestPriceQueryVO[] queryLatestPrice(LastestPriceQueryVO[] queryVos)
      throws BusinessException;

  /**
   * ����������������ѯ���������¼۸�
   * 
   * @param purchaseOrg �ɹ���֯
   * @param supplier ��Ӧ�̣�����Ϊ�գ�
   * @param currency ����
   * @param params ѯ�۲�����������
   * @return ����ִ����ϻὫ��Я�����ĸ��۸���Ϣ�Ĳ�������
   * @throws BusinessException
   * @since 6.0
   */
  public IOrderPriceQueryParam[] queryLatestPrice(String purchaseOrg,
      String supplier, String currency, IOrderPriceQueryParam[] params)
      throws BusinessException;

  /**
   * ����������������ѯ���������¼۸񣨿��Բ�ָ�����ڷ�Χ�ڵ����¼ۣ�
   * 
   * @param purchaseOrg �ɹ���֯
   * @param supplier ��Ӧ�̣�����Ϊ�գ�
   * @param currency ����
   * @param params ѯ�۲�����������
   * @param startDate ��ʼ����
   * @param endDate ��������
   * @return ����ִ����ϻὫ��Я�����ĸ��۸���Ϣ�Ĳ�������
   * @throws BusinessException
   * @since 6.0
   */
  public IOrderPriceQueryParam[] queryLatestPrice(String purchaseOrg,
      String supplier, String currency, IOrderPriceQueryParam[] params,
      UFDate startDate, UFDate endDate) throws BusinessException;

  /**
   * �����������������ݽ��������֯�����ϲ�ѯ���²ɹ������۸�
   * 
   * @param financeOrg ���������֯��OID
   * @param materials ����OID����
   * @return ���������Ķ����۸���������
   * @throws BusinessException
   * @since 6.0
   */
  public OrderPriceData[] queryLatestPrice(String financeOrg, String[] materials)
      throws BusinessException;

  /**
   * ����������������ѯ��������ͼ۸�
   * 
   * @param purchaseOrg �ɹ���֯
   * @param supplier ��Ӧ��
   * @param currency ����
   * @param params ѯ�۲�����������
   * @return ����ִ����ϻὫ��Я�����ĸ��۸���Ϣ�Ĳ�������
   * @throws BusinessException
   * @since 6.0
   */
  public IOrderPriceQueryParam[] queryLowestPrice(String purchaseOrg,
      String supplier, String currency, IOrderPriceQueryParam[] params)
      throws BusinessException;
}
