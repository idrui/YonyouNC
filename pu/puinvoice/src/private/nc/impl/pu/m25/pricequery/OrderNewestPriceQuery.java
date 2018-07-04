package nc.impl.pu.m25.pricequery;

import nc.bs.framework.common.NCLocator;
import nc.impl.pu.m25.OrderPriceQueryPara;
import nc.itf.pu.m25.IPriceQuery;
import nc.pubitf.pu.m21.pub.IOrderPriceQuery;
import nc.pubitf.pu.m21.pub.IOrderPriceQueryParam;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoicePriceQueryVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 订单最新价查询
 * 
 * @since 6.0
 * @version 2011-7-29 下午02:17:56
 * @author 田锋涛
 */

public class OrderNewestPriceQuery implements IPriceQuery {

  public OrderNewestPriceQuery() {
    //
  }

  @Override
  public void query(InvoicePriceQueryVO[] para) {
    String purOrg = this.getPurOrg(para);
    String supplier = this.getSupplier(para);
    String currency = this.getCurrency(para);
    if (null == purOrg || null == supplier || null == currency) {
      return;
    }
    IOrderPriceQueryParam[] orderPricePara = this.getOrderPriceQueryPara(para);
    orderPricePara =
        this.queryByOrderService(purOrg, supplier, currency, orderPricePara);
    for (int i = 0; i < para.length; ++i) {
      IOrderPriceQueryParam opp = orderPricePara[i];
      para[i].setNorigprice(opp.getOrigPrice());
      para[i].setNorigtaxprice(opp.getOrigTaxPrice());
    }
  }

  private String getCurrency(InvoicePriceQueryVO[] para) {
    for (InvoicePriceQueryVO vo : para) {
      if (!StringUtil.isEmptyWithTrim(vo.getCorigcurrencyid())) {
        return vo.getCorigcurrencyid();
      }
    }
    return null;
  }

  private OrderPriceQueryPara[] getOrderPriceQueryPara(
      InvoicePriceQueryVO[] para) {
    OrderPriceQueryPara[] orderPara = new OrderPriceQueryPara[para.length];
    for (int i = 0; i < para.length; ++i) {
      InvoicePriceQueryVO vo = para[i];
      OrderPriceQueryPara op = new OrderPriceQueryPara(vo.getPk_srcmaterial());
      orderPara[i] = op;
    }
    return orderPara;
  }

  private String getPurOrg(InvoicePriceQueryVO[] para) {
    for (InvoicePriceQueryVO vo : para) {
      if (!StringUtil.isEmptyWithTrim(vo.getPk_purchaseorg())) {
        return vo.getPk_purchaseorg();
      }
    }
    return null;
  }

  private String getSupplier(InvoicePriceQueryVO[] para) {
    for (InvoicePriceQueryVO vo : para) {
      if (!StringUtil.isEmptyWithTrim(vo.getPk_supplier())) {
        return vo.getPk_supplier();
      }
    }
    return null;
  }

  private IOrderPriceQueryParam[] queryByOrderService(String purchaseOrg,
      String supplier, String currency, IOrderPriceQueryParam[] params) {
    try {
      IOrderPriceQuery opq =
          NCLocator.getInstance().lookup(IOrderPriceQuery.class);
      return opq.queryLatestPrice(purchaseOrg, supplier, currency, params);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

}
