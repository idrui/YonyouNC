/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-22 上午09:48:33
 */
package nc.itf.pu.m21;

import nc.vo.pu.m21.query.price.OrderPriceQueryParam;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单侧边栏询价
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-10-22 上午09:48:33
 */
public interface IOrderDefPriceSideForm {

  /**
   * 方法功能描述：采购订单侧边栏询价
   * <p>
   * <b>参数说明</b>
   * 
   * @param param 询价参数对象
   * @param ps 价格来源
   * @param pp 参数PO28
   * @return 方法执行完毕会将把携带了四个价格信息的参数返回
   * @throws BusinessException <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-22 上午09:49:59
   */
  public OrderPriceQueryParam queryOrderPrice(OrderPriceQueryParam param,
      PriceSource ps, PricePriority pp) throws BusinessException;

}
