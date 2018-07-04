package nc.bs.pu.m21.query.price;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.vo.pu.m21.query.price.OrderItemPriceVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单价格查询的BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-21 下午10:04:12
 */
public class OrderPriceQueryBP {
  /**
   * 方法功能描述：根据主健查询订单明细，可以只出现其中的部分属性。
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderItemIds 订单明细的ID数组
   * @param itemKeys 需要查询的属性名称数组（如果为空，则查询全部属性）
   * @return 符合条件的订单明细数组
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 下午10:09:07
   */
  public OrderItemPriceVO[] queryOrderItemByIds(String[] orderItemIds) {
    OrderItemPriceVO[] prices =
        new ViewQuery<OrderItemPriceVO>(OrderItemPriceVO.class)
            .query(orderItemIds);
    return prices;
  }
}
