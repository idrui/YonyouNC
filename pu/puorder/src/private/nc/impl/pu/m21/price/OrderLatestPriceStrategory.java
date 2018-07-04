package nc.impl.pu.m21.price;

import nc.bs.pu.m21.query.price.LatestPriceQueryBP;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pu.m21.query.price.OrderPriceQueryParam;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单最新价询价策略
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-21 下午03:58:35
 */
public class OrderLatestPriceStrategory extends OrderPriceStrategy {

  @Override
  protected OrderPriceData[] queryOrderPrice(String[] purchaseOrgs,
      String[] suppliers, String[] currencies, String[] materials) {
    LatestPriceQueryBP bp = new LatestPriceQueryBP();
    OrderPriceQueryParam param = this.getQueryParameter();
    OrderPriceData[] prices =
        bp.queryLatestPrice(purchaseOrgs, null, suppliers, currencies,
            materials, null != param ? param.getStartDate() : null,
            null != param ? param.getEndDate() : null);
    return prices;
  }
}
