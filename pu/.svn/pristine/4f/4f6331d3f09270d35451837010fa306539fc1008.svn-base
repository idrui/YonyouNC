package nc.bs.pu.m21.query.price;

import nc.bs.pu.m21.query.price.cal.OrderPriceQuery;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pub.lang.UFDate;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单最新价的查询BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-21 下午10:03:32
 */
public class LatestPriceQueryBP {
  /**
   * 查询集采订单的指定财务组织下的物料的订单最新价(只为内部交易用)
   * 
   * @param fiorgs 财务组织数组
   * @param moids 物料OID数组
   * @return OrderPriceData数组，可能为零长或null
   */
  public OrderPriceData[] queryByFIOrgAndMaterial(String[] fiorgs,
      String[] moids) {
    // 执行查询，返回结果
    return new OrderPriceQuery().queryLatestPrice4TO(fiorgs, moids, null, null);
  }

  /**
   * 查询 采购订单的指定收货库存组织下的物料的订单最新价(只为生产制造使用)
   * 
   * @param pk_arrvstoorgs 收货库存组织
   * @param moids 物料OID数组
   * @return OrderPriceData数组，可能为零长或null
   */
  public OrderPriceData[] queryForMM(String[] pk_arrvstoorgs, String[] moids) {
    // 执行查询，返回结果
    return new OrderPriceQuery().queryLatestPrice4MM(pk_arrvstoorgs, moids,
        null, null);
  }

  /**
   * 方法功能描述：查询订单最新价。
   * <p>
   * <b>参数说明</b>
   * 
   * @param purchaseOrg 采购组织<font color="red">（可为空）</font>
   * @param financeOrg 财务组织<font color="red">（可为空）</font>
   * @param supplier 供应商<font color="red">（可为空）</font>
   * @param currency 币种<font color="red">（可为空）</font>
   * @param materials 物料的OID数组
   * @param startDate 起始询价单据日期<font color="red">（可为空）</font>
   * @param endDate 结束询价单据日期<font color="red">（可为空）</font>
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 下午10:47:27
   */
  public OrderPriceData[] queryLatestPrice(String[] purchaseOrgs,
      String[] financeOrgs, String[] suppliers, String[] currencies,
      String[] materials, UFDate startDate, UFDate endDate) {

    return new OrderPriceQuery().queryLatestPrice(purchaseOrgs, financeOrgs,
        suppliers, currencies, materials, startDate, endDate);
  }

}
