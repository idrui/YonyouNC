package nc.impl.pu.m21.price;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m20.pub.IQueryPrayBill;
import nc.vo.pu.m21.query.price.OrderPriceQueryDetail;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单询价策略
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-21 下午03:59:47
 */
public class BuyingReqPriceStrategory extends AbstractPriceStrategy {

  @Override
  public void queryPrice() {
    String[] buyingReqItemIds = this.getBuyingReqItemIds();
    IQueryPrayBill query = NCLocator.getInstance().lookup(IQueryPrayBill.class);
    try {
      Map<String, UFDouble> prices = query.queryPriceByItemPK(buyingReqItemIds);
      this.setPrice(prices);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  private String[] getBuyingReqItemIds() {
    OrderPriceQueryDetail[] details = this.getQueryParameter().getDetail();
    List<String> ids = new ArrayList<String>();
    for (OrderPriceQueryDetail detail : details) {
      if (detail.getBuyingReq() != null) {
        ids.add(detail.getBuyingReq());
      }
    }
    return ids.toArray(new String[ids.size()]);
  }

  private void setPrice(Map<String, UFDouble> prices) {
    OrderPriceQueryDetail[] details = this.getQueryParameter().getDetail();
    for (OrderPriceQueryDetail detail : details) {
      if ((detail.getBuyingReq() != null)
          && prices.containsKey(detail.getBuyingReq())) {
        detail.setOrigTaxPrice(prices.get(detail.getBuyingReq()));
      }
    }
  }
}
