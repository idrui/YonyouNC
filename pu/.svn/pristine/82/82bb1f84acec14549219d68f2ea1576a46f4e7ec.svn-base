/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-27 上午10:57:41
 */
package nc.bs.pu.est.rule.pricequery;

import nc.vo.pu.pub.enumeration.PriceSource;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估询价策略生成工厂
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-27 上午10:57:41
 */
public class EstPriceQryStrategyFactory {

  /**
   * 方法功能描述：根据参数生成采购入库单暂估的询价策略。
   * <p>
   * <b>参数说明</b>
   * 
   * @param ps 询价参数
   * @return 根据询价参数得到的询价策略
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-27 上午09:22:36
   */
  public static AbstractEstPriceQueryStrategy getPurchsInStrategy(PriceSource ps) {
    AbstractEstPriceQueryStrategy strategy = null;
    if (PriceSource.OrderPice == ps) {
      strategy = new EstOrderPriceQryStrategy();
    }
    else if (PriceSource.PurchaseInPrice == ps) {
      strategy = new EstPurchaseInPriceQryStrategy();
    }
    else if (PriceSource.OrderNewestPrice == ps) {
      strategy = new EstOrderNewestPriceQryStrategy();
    }
    else if (PriceSource.SettleNewestPrice == ps) {
      strategy = new EstNewestSettlePriceQryStrategy();
    }
    else if (PriceSource.PlanPrice == ps) {
      strategy = new EstPlanPriceQryStrategy();
    }
    else if (PriceSource.SupplierPrice == ps) {
      strategy = new EstSupplierPriceQryStrategy();
    }
    return strategy;
  }

  /**
   * 方法功能描述：根据参数生成VMI的询价策略。
   * <p>
   * <b>参数说明</b>
   * 
   * @param ps
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-27 上午09:22:58
   */
  public static AbstractEstPriceQueryStrategy getVMIStrategy(PriceSource ps) {
    AbstractEstPriceQueryStrategy strategy = null;
    if (PriceSource.OrderPice == ps) {
      strategy = new VMIEstOrderPriceQryStrategy();
    }
    else if (PriceSource.PurchaseInPrice == ps) {
      // 根据需求与取订单价一样的处理逻辑
      strategy = new VMIEstOrderPriceQryStrategy();
    }
    else if (PriceSource.SettleNewestPrice == ps) {
      strategy = new EstNewestSettlePriceQryStrategy();
    }
    else if (PriceSource.PlanPrice == ps) {
      strategy = new EstPlanPriceQryStrategy();
    }
    else {
      // strategy = null;
    }
    return strategy;
  }
}
