package nc.impl.pu.m422x;

import java.util.HashMap;
import java.util.Map;

import nc.bs.pu.m21.query.price.LatestPriceQueryBP;
import nc.impl.pu.m21.price.OrderPriceStrategy;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pu.m21.query.price.OrderPriceQueryDetail;
import nc.vo.pu.m21.query.price.OrderPriceQueryParam;
import nc.vo.pu.pub.enumeration.PricePriority;

import org.apache.commons.lang.StringUtils;

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
public class OrderLatestPriceStrategory4StoreReq extends OrderPriceStrategy {

  @Override
  public void queryPrice() {
    OrderPriceQueryParam param = this.getQueryParameter();
    String purchaseOrg = param.getPurchaseOrg();
    String currency = param.getCurrency();
    if (StringUtils.trimToNull(purchaseOrg) == null
    // || StringUtils.trimToNull(supplier) == null
        || StringUtils.trimToNull(currency) == null) {
      return;
    }

    // 询价
    OrderPriceData[] prices = this.queryOrderPrice(new String[] {
      purchaseOrg
    },
    // new String[] {
    // supplier
    // }
        null, new String[] {
          currency
        }, this.getMaterials());

    // 把询到的价格放置到参数上
    this.setPrice(prices);
  }

  private String[] getMaterials() {
    OrderPriceQueryDetail[] details = this.getQueryParameter().getDetail();
    String[] materials = new String[details.length];
    for (int i = 0; i < details.length; i++) {
      materials[i] = details[i].getMaterialSourceId();
    }
    return materials;
  }

  private void setPrice(OrderPriceData[] prices) {
    Map<String, OrderPriceData> map = new HashMap<String, OrderPriceData>();
    for (OrderPriceData price : prices) {
      // sw 物资需求申请单没有供应商信息，可能寻到多个供应商的最新报价，取日期最新的报价
      OrderPriceData compare = map.get(price.getMaterial());
      if (compare == null) {
        map.put(price.getMaterial(), price);
      }
      else if (compare.getTs().before(price.getTs())) {
        map.put(price.getMaterial(), price);
      }
    }

    OrderPriceQueryDetail[] details = this.getQueryParameter().getDetail();
    for (OrderPriceQueryDetail detail : details) {
      OrderPriceData price = map.get(detail.getMaterialSourceId());
      if (price != null) {
        if (this.getPricePriority() == PricePriority.PRICE_PRIOR_TO_TAXPRICE) {
          detail.setOrigPrice(price.getOrigPrice());
        }
        else {
          detail.setOrigTaxPrice(price.getOrigTaxPrice());
        }
      }
    }
  }

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
