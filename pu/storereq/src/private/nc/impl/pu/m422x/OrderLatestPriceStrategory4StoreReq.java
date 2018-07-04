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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������¼�ѯ�۲���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-21 ����03:58:35
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

    // ѯ��
    OrderPriceData[] prices = this.queryOrderPrice(new String[] {
      purchaseOrg
    },
    // new String[] {
    // supplier
    // }
        null, new String[] {
          currency
        }, this.getMaterials());

    // ��ѯ���ļ۸���õ�������
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
      // sw �����������뵥û�й�Ӧ����Ϣ������Ѱ�������Ӧ�̵����±��ۣ�ȡ�������µı���
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
