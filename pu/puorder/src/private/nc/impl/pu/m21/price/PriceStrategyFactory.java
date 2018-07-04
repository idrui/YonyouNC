package nc.impl.pu.m21.price;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�۸���ԵĹ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-21 ����04:06:42
 */
public class PriceStrategyFactory {
  private static PriceStrategyFactory fatory = new PriceStrategyFactory();

  private Map<PriceSource, Class<? extends IPriceStrategy>> strategies =
      new HashMap<PriceSource, Class<? extends IPriceStrategy>>();

  private PriceStrategyFactory() {
    // ע�ṩӦ�̼�Ŀ��ѯ�۲���
    this.strategies.put(PriceSource.SupplierPrice, VendorPriceStrategory.class);
    // ע��ο��ɱ�ѯ�۲���
    this.strategies.put(PriceSource.RefCostPrice, CostPriceStrategory.class);
    // ע��ƻ���ѯ�۲���
    this.strategies.put(PriceSource.PlanPrice, PlanPriceStrategory.class);
    // ע�ᶩ�����¼�ѯ�۲���
    this.strategies.put(PriceSource.OrderNewestPrice,
        OrderLatestPriceStrategory.class);
    // ע�ᶩ����ͼ�ѯ�۲���
    this.strategies.put(PriceSource.OrderMinPrice,
        OrderLowestPriceStrategory.class);
    // ע���빺��ѯ�۲���
    this.strategies.put(PriceSource.PrayPrice, BuyingReqPriceStrategory.class);
  }

  /**
   * ����������������ü۸���Ե�һ������ʵ����
   * <p>
   * <b>����˵��</b>
   * 
   * @return �۸���Թ���ʵ��
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 ����04:09:14
   */
  public static PriceStrategyFactory getInstance() {
    return PriceStrategyFactory.fatory;
  }

  /**
   * ������������������ѯ����Դ���ѯ�۲��ԡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param priceSource �۸���Դ
   * @return ѯ�۲���
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 ����04:30:36
   */
  public IPriceStrategy getPriceStrategy(PriceSource priceSource) {
    Class<? extends IPriceStrategy> className =
        this.strategies.get(priceSource);
    IPriceStrategy strategy = null;
    try {
      strategy = className.newInstance();
    }
    catch (Exception e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return strategy;
  }
}
