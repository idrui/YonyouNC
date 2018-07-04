package nc.impl.pu.m25.pricequery;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m25.IPriceQuery;
import nc.pubitf.pu.m21.pub.IOrderPriceQuery;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.query.price.OrderItemPriceVO;
import nc.vo.pu.m25.entity.InvoicePriceQueryVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 订单价查询
 * 
 * @since 6.0
 * @version 2011-7-29 下午02:24:40
 * @author 田锋涛
 */

public class OrderPriceQuery implements IPriceQuery {

  public OrderPriceQuery() {
    //
  }

  @Override
  public void query(InvoicePriceQueryVO[] para) {
    try {
      Set<String> orderBIds = this.getOrderBIds(para);
      if (0 == orderBIds.size()) {
        return;
      }
      IOrderPriceQuery opq =
          NCLocator.getInstance().lookup(IOrderPriceQuery.class);
      OrderItemPriceVO[] orderPriceVos =
          opq.queryItemPrice(orderBIds.toArray(new String[orderBIds.size()]));
      Map<String, OrderItemPriceVO> orderItemMap =
          CirVOUtil.createKeyVOMap(orderPriceVos);
      for (InvoicePriceQueryVO vo : para) {
        if (orderItemMap.containsKey(vo.getPk_order_b())) {
          OrderItemPriceVO opvo = orderItemMap.get(vo.getPk_order_b());
          String currency = vo.getCorigcurrencyid();
          if (currency.equals(opvo.getCurrencyId())) {
            vo.setNorigprice(opvo.getOrigPrice());
            vo.setNorigtaxprice(opvo.getOrigTaxPrice());
          }
          else {
            vo.setNprice(opvo.getPrice());
            vo.setNtaxprice(opvo.getTaxPrice());
          }
        }
      }
    }
    catch (BusinessException e) {

      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  private Set<String> getOrderBIds(InvoicePriceQueryVO[] para) {
    Set<String> orderBIds = new HashSet<String>();
    for (InvoicePriceQueryVO vo : para) {
      if (!StringUtil.isEmptyWithTrim(vo.getPk_order_b())) {
        orderBIds.add(vo.getPk_order_b());
      }
    }
    return orderBIds;
  }

}
