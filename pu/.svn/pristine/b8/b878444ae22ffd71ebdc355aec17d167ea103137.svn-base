package nc.bs.pu.m21.query.price.cal;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 采购询价-订单无效数据重算
 * 
 * @since 6.1
 * @version 2011-11-19 上午09:02:07
 * @author yangtian
 */
public class OrderPriceDailyMaintainance extends OrderPriceMaintainance {

  @Override
  public void doMaintainance(UFDate lastAlertDate) {

    PriceCalculatorUtil.logTime("订单无效数据重算开始时间是:");/* -=notranslate=- */
    if (this.isDataEmpty().booleanValue()) {
      new OrderPriceMonthlyMaintainance().doMaintainance(lastAlertDate);
    }
    else {
      /*
       * 原理：其实每日更新和每月更新一样的，只是限定的范围不一样。
       * 每月更新是由时间限定的数据，而每日更新的数据限定范围就是bvalidate和dr。
       * 步骤：1先把price表中bvalidate='N'的都删除，之后根据priceb中dr为‘1’的重算price，但插入的数据都是bvalidate
       * ='N'的；
       * 2再删除priceb中dr=‘1’的，之后根据price中bvalidate='N'的重算priceb；
       * 3之后再更新price表中bvalidate='N'都为'Y'.
       * 失效数据的排除：因为有endtime，可以
       * 排除出更新的数据，并且要和orderb连接所以可以排除删除的数据
       * 如果更新就麻烦了，dr，bv，ts，pkprice
       */
      this.deleteOrderPrice(" where  bvalidate='N' ");

      this.calculateOrderPriceInvalidateData(lastAlertDate);
      this.deleteOrderPriceB(" where   dr=1  ");
      this.calculatePriceBInvalidateData(lastAlertDate);
      this.updateOrderPriceToValidate();
    }
    PriceCalculatorUtil.logTime("订单无效数据重算结束时间是:");/* -=notranslate=- */

  }

  /**
   * 当更新po_order_b表时更新po_order_price_b表和更新po_order_price表
   * 只有在询价表中被审批的数据进行修订或者弃审的时候才会执行这个操作，否则不会执行
   * 
   * @param pk_order_bs表的主键，和在po_order_b中对应数据的主键是一样的
   */
  public void updateChangedDataToInvalidateByOrderB(String[] pk_order_bs) {
    try {
      if (pk_order_bs != null) {
        this.updatePriceBToInvalidateByOrderB(pk_order_bs);
        this.updatePriceToInvalidateByOrderB(pk_order_bs);
      }
    }
    catch (Exception e) {
      PriceCalculatorUtil.logError(e);/* -=notranslate=- */
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 每日重算那些无效的数据
   * 
   * @param endDate 上次预警的时间
   */
  private void calculateOrderPriceInvalidateData(UFDate lastAlertDate) {

    String excuteSql =
        this.calculateOrderPriceForAll(
            "  inner join po_order_price_b c on  c.pk_order_b=b.pk_order_b    where  c.dr=1  and ",
            null, lastAlertDate);
    PriceCalculatorUtil.update(excuteSql, "calculateInvalidateDataDaily");
  }

  private void calculatePriceBInvalidateData(UFDate lastAlertDate) {

    this.calculatePriceBForAll(null, lastAlertDate, " and prc.bvalidate='N' ");
  }

  private UFBoolean isDataEmpty() {
    SqlBuilder sqlquery = new SqlBuilder();
    sqlquery.append(" select  count(*) from po_order_price ");

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sqlquery.toString());
    rowset.next();
    int totalRows = rowset.getInt(0);

    if (totalRows <= 1) {
      return UFBoolean.TRUE;
    }
    return UFBoolean.FALSE;
  }

  private void updateOrderPriceToValidate() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" update po_order_price  set bvalidate='Y' where bvalidate='N' ");
    PriceCalculatorUtil.update(sql.toString(), "calculateInvalidateDataDaily");

  }

  private void updatePriceBToInvalidateByOrderB(String[] pk_order_bs) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(this.updateToInvalidateByOrderB(
        " update po_order_price_b  set dr=1 ", pk_order_bs));
    PriceCalculatorUtil.update(sql.toString(),
        "updatePriceBToInvalidateByOrderB");
  }

  private void updatePriceToInvalidateByOrderB(String[] pk_order_bs) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(this.updateToInvalidateByOrderB(
        " update po_order_price  set bvalidate='N' ", pk_order_bs));
    PriceCalculatorUtil.update(sql.toString(),
        "updatePriceToInvalidateByOrderB");
  }

  /**
   * 提取updatePriceBToInvalidateByOrderB和updatePriceToInvalidateByOrderB的公共部分
   * 
   * @param String theDifferentPart 这两个函数生成sql的不同部分
   * @param pk_order_bs 那些无效的id
   * @return String 返回sql
   */
  private String updateToInvalidateByOrderB(String theDifferentPart,
      String[] pk_order_bs) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(theDifferentPart + "  where pk_order_price in ( ");
    sql.append(" select   a.pk_order_price  ");
    sql.append(" from po_order_price_b a inner join po_order_price b on a.pk_order_price=b.pk_order_price ");
    sql.append(" where ");
    String orderBsSql =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_06.name()).buildSQL("",
            pk_order_bs);
    sql.append("  a.pk_order_b " + orderBsSql);
    sql.append(" ) ");
    return sql.toString();
  }

}
