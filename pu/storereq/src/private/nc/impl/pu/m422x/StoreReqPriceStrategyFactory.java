package nc.impl.pu.m422x;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pu.m21.price.CostPriceStrategory;
import nc.impl.pu.m21.price.IPriceStrategy;
import nc.impl.pu.m21.price.PlanPriceStrategory;
import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class StoreReqPriceStrategyFactory {
  private static StoreReqPriceStrategyFactory fatory =
      new StoreReqPriceStrategyFactory();

  private Map<PriceSource, Class<? extends IPriceStrategy>> strategies =
      new HashMap<PriceSource, Class<? extends IPriceStrategy>>();

  private StoreReqPriceStrategyFactory() {
    // ע��ο��ɱ�ѯ�۲���
    this.strategies.put(PriceSource.RefCostPrice, CostPriceStrategory.class);
    // ע��ƻ���ѯ�۲���
    this.strategies.put(PriceSource.PlanPrice, PlanPriceStrategory.class);
    // ע�ᶩ�����¼�ѯ�۲���
    this.strategies.put(PriceSource.OrderNewestPrice,
        OrderLatestPriceStrategory4StoreReq.class);
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
  public static StoreReqPriceStrategyFactory getInstance() {
    return StoreReqPriceStrategyFactory.fatory;
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
