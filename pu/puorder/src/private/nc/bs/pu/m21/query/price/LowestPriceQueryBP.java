package nc.bs.pu.m21.query.price;

import nc.bs.pu.m21.query.price.cal.OrderPriceQuery;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pub.lang.UFDate;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单最低价的查询BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-21 下午10:03:55
 */
public class LowestPriceQueryBP {
  /**
   * 方法功能描述：查询订单最低价。
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
   *          物料OID数组
   * @return 订单价格的数组
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-23 下午04:58:14
   */
  public OrderPriceData[] queryLowestPrice(String[] purchaseOrgs,
      String[] suppliers, String[] currencies, String[] materials,
      UFDate startDate, UFDate endDate) {
    return new OrderPriceQuery().queryLowestPrice(purchaseOrgs, suppliers,
        currencies, materials, startDate, endDate);
  }

}
