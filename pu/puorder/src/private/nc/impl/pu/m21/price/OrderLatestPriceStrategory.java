package nc.impl.pu.m21.price;

import nc.bs.pu.m21.query.price.LatestPriceQueryBP;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pu.m21.query.price.OrderPriceQueryParam;

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
