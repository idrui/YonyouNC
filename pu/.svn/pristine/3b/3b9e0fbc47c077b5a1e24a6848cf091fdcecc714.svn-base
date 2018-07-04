package nc.bs.pu.m21.query.price.cal;

import nc.vo.pu.m21.entity.OrderPriceVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 采购询价-订单价格提取。
 * 
 * @since 6.1
 * @version 2011-11-19 上午09:02:07
 * @author yangtian
 */
public class OrderPriceMonthlyMaintainance extends OrderPriceMaintainance {

  @Override
  public void doMaintainance(UFDate lastAlertDate) {

    PriceCalculatorUtil.logTime("订单价格提取预警开始时间是:");/* -=notranslate=- */
    // 取上次预警时间那个月的第一天
    UFDate beginTime =
        lastAlertDate.getDateBefore(lastAlertDate.getDay() - 1).asBegin();

    UFDate endTime = this.getEndTime();
    // 应该把上次预警日期的当月数据都删除之后重算
    this.deleteIncompletedData(beginTime, endTime);
    // 执行月预警，开始时间应该是上次预警时间那个月的第一天
    this.calculateOrderPriceByTime(beginTime, endTime);
    // // 根据刚更新好的po_order_price表来重新计算po_order_price_表的数据
    this.calculatePriceBByTime(beginTime, endTime);

    // 只有计算成功后才能进行更新上次预警时间的操作
    this.updateAlertDate(endTime);

    PriceCalculatorUtil.logTime("订单价格提取结束时间是:");/* -=notranslate=- */
  }

  private void calculateOrderPriceByTime(UFDate beginTime, UFDate endTime) {
    String excuteSql =
        this.calculateOrderPriceForAll(" where ", beginTime, endTime);
    PriceCalculatorUtil.update(excuteSql, "calculateOrderPriceByTime");

  }

  private void calculatePriceBByTime(UFDate beginTime, UFDate endTime) {
    this.calculatePriceBForAll(beginTime, endTime, " ");
  }

  /**
   * 因为如果上次预警是在这个月中间做的，数并没有包含这个月的整个数据。把这个月之前计算了一半的数据删除，这样就可以保证一个月一个维度序列只有一条数据代表，
   * 否则代表数据太多就失去了建临时表的意义
   */
  private void deleteIncompletedData(UFDate theFirstDate, UFDate nowDate) {

    // 因为要支持每天进行月预警，所以要先删除本月以前计算的数据

    SqlBuilder sql1 = new SqlBuilder();
    sql1.append("  where  pk_order_price in (select pk_order_price from po_order_price where  bvalidate='Y'  ");
    sql1.append(PriceCalculatorUtil.getBeginAndEndTimeQueryPart(
        " modifiedtime ", theFirstDate, nowDate, 0) + ") ");

    this.deleteOrderPriceB(sql1.toString());

    SqlBuilder sql = new SqlBuilder();
    sql.append(" where bvalidate='Y' "
        + PriceCalculatorUtil.getBeginAndEndTimeQueryPart(" modifiedtime ",
            theFirstDate, nowDate, 0));
    sql.append(" and pk_order_price<>'" + OrderPriceVO.PK_CALC + "'");

    this.deleteOrderPrice(sql.toString());

  }

  private void updateAlertDate(UFDate endTime) {
    // 不可以用this.getEndTime()来去结束时间，而是要把开始预警的时间传进来，否则会有计算预警哪段时间时间间隔的误差
    SqlBuilder sqlinsert = new SqlBuilder();
    sqlinsert.append(" update po_order_price set modifiedtime='" + endTime
        + "' where pk_order_price='" + OrderPriceVO.PK_CALC + "' ");
    PriceCalculatorUtil.update(sqlinsert.toString(), "updateAlertDate");
  }

  @Override
  protected UFBoolean isNeedReCal() {
    UFDate curend = this.getEndTime().asBegin();
    UFDate last = this.getLastAlertDate().asBegin();
    if (curend.equals(last)) {
      return UFBoolean.FALSE;
    }
    return UFBoolean.TRUE;
  }

}
