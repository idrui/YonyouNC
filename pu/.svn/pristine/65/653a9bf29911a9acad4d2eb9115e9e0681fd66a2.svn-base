package nc.impl.pu.m21.price;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pu.m21.query.price.OrderPriceQueryDetail;
import nc.vo.pu.m21.query.price.OrderPriceQueryParam;
import nc.vo.pu.pub.enumeration.PricePriority;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单最新价和订单最低价的询价策略基础类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-5-26 下午04:37:16
 */
public abstract class OrderPriceStrategy extends AbstractPriceStrategy {

  @Override
  public void queryPrice() {
    OrderPriceQueryParam param = this.getQueryParameter();
    String purchaseOrg = param.getPurchaseOrg();
    String supplier = param.getSupplier();
    String currency = param.getCurrency();
    if (StringUtils.trimToNull(purchaseOrg) == null
        || StringUtils.trimToNull(supplier) == null
        || StringUtils.trimToNull(currency) == null) {
      return;
    }

    // 询价
    OrderPriceData[] prices = this.queryOrderPrice(new String[] {
      purchaseOrg
    }, new String[] {
      supplier
    }, new String[] {
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
      map.put(price.getMaterial(), price);
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

  /**
   * 方法功能描述：查询订单价格。
   * <p>
   * <b>参数说明</b>
   * 
   * @param purchaseOrg
   *          采购组织
   * @param supplier
   *          供应商
   * @param currency
   *          币种
   * @param materials
   *          物料的OID数组
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 下午10:47:27
   */
  protected abstract OrderPriceData[] queryOrderPrice(String[] purchaseOrgs,
      String[] suppliers, String[] currencies, String[] materials);
}
