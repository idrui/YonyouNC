package nc.impl.pu.m21.price;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格策略的工厂类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-21 下午04:06:42
 */
public class PriceStrategyFactory {
  private static PriceStrategyFactory fatory = new PriceStrategyFactory();

  private Map<PriceSource, Class<? extends IPriceStrategy>> strategies =
      new HashMap<PriceSource, Class<? extends IPriceStrategy>>();

  private PriceStrategyFactory() {
    // 注册供应商价目表询价策略
    this.strategies.put(PriceSource.SupplierPrice, VendorPriceStrategory.class);
    // 注册参考成本询价策略
    this.strategies.put(PriceSource.RefCostPrice, CostPriceStrategory.class);
    // 注册计划价询价策略
    this.strategies.put(PriceSource.PlanPrice, PlanPriceStrategory.class);
    // 注册订单最新价询价策略
    this.strategies.put(PriceSource.OrderNewestPrice,
        OrderLatestPriceStrategory.class);
    // 注册订单最低价询价策略
    this.strategies.put(PriceSource.OrderMinPrice,
        OrderLowestPriceStrategory.class);
    // 注册请购单询价策略
    this.strategies.put(PriceSource.PrayPrice, BuyingReqPriceStrategory.class);
  }

  /**
   * 方法功能描述：获得价格策略的一个工厂实例。
   * <p>
   * <b>参数说明</b>
   * 
   * @return 价格策略工厂实例
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 下午04:09:14
   */
  public static PriceStrategyFactory getInstance() {
    return PriceStrategyFactory.fatory;
  }

  /**
   * 方法功能描述：根据询价来源获得询价策略。
   * <p>
   * <b>参数说明</b>
   * 
   * @param priceSource 价格来源
   * @return 询价策略
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 下午04:30:36
   */
  public IPriceStrategy getPriceStrategy(PriceSource priceSource) {
    Class<? extends IPriceStrategy> className =
        this.strategies.get(priceSource);
    IPriceStrategy strategy = null;
    try {
      strategy = className.newInstance();
    }
    catch (Exception e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return strategy;
  }
}
