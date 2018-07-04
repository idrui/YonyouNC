package nc.bs.pu.m21.query.price.cal;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.m21.enumeration.EnumBillStatus;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.ICalendar;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 采购询价-询价查询。
 * 
 * @since 6.1
 * @version 2011-11-19 上午09:02:07
 * @author yangtian
 */
public class OrderPriceQuery {

  /**
   * 查询最新价
   * 
   * @param purchaseOrg 采购组织<font color="red">（可为空）</font>
   * @param financeOrg 财务组织<font color="red">（可为空）</font>
   * @param supplier 供应商<font color="red">（可为空）</font>
   * @param currency 币种<font color="red">（可为空）</font>
   * @param materials 物料的OID数组
   * @param startDate 起始询价单据日期<font color="red">（可为空）</font>
   * @param endDate 结束询价单据日期<font color="red">（可为空）</font>
   * @return OrderPriceData[]返回查到的数据
   */
  public OrderPriceData[] queryLatestPrice(String[] purchaseOrgs,
      String[] financeOrgs, String[] suppliers, String[] currencies,
      String[] materials, UFDate startDate, UFDate endDate) {
    PriceCalculatorUtil.logTime("采购询价，询最新价开始时间:");/* -=notranslate=- */

    // 取本月的第一天作为分界时间，这一天以前的数据都从询价表里查，这一天之后的数据都从采购订单里查
    OrderPriceData[] prices = null;
    try {
      prices =
          this.queryPrice(new OrderPriceDailyMaintainance().getLastAlertDate(),
              new QueryLatestPriceStrategy(purchaseOrgs, financeOrgs,
                  suppliers, currencies, materials), startDate, endDate);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    PriceCalculatorUtil.logTime("采购询价，询最新价开始时间:");/* -=notranslate=- */

    return prices;

  }

  /**
   * 为制造查询最新价
   * 
   * @param pk_arrvstoorgs 收货库存组织
   * @param moids 物料OID数组
   * @param startDate 起始询价单据日期<font color="red">（可为空）</font>
   * @param endDate 结束询价单据日期<font color="red">（可为空）</font>
   * @return OrderPriceData[]返回查到的数据
   */
  public OrderPriceData[] queryLatestPrice4MM(String[] pk_arrvstoorgs,
      String[] moids, UFDate startDate, UFDate endDate) {
    PriceCalculatorUtil.logTime("采购询价，制造询最新价开始时间:");/* -=notranslate=- */

    // 取本月的第一天作为分界时间，这一天以前的数据都从询价表里查，这一天之后的数据都从采购订单里查
    OrderPriceData[] prices = null;
    try {
      prices =
          this.queryPrice(new OrderPriceDailyMaintainance().getLastAlertDate(),
              new QueryLatestPrice4MMStrategy(pk_arrvstoorgs, moids),
              startDate, endDate);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    PriceCalculatorUtil.logTime("采购询价，制造询最新价结束时间:");/* -=notranslate=- */

    return prices;
  }

  /**
   * 为内部交易查询最新价
   * 
   * @param fiorgs 财务组织数组
   * @param moids 物料OID数组
   * @param startDate 起始询价单据日期<font color="red">（可为空）</font>
   * @param endDate 结束询价单据日期<font color="red">（可为空）</font>
   * @return OrderPriceData[]返回查到的数据
   */
  public OrderPriceData[] queryLatestPrice4TO(String[] fiorgs, String[] moids,
      UFDate startDate, UFDate endDate) {
    PriceCalculatorUtil.logTime("采购询价，内部交易询最新价开始时间:");/* -=notranslate=- */

    // 取本月的第一天作为分界时间，这一天以前的数据都从询价表里查，这一天之后的数据都从采购订单里查
    OrderPriceData[] prices = null;
    try {
      prices =
          this.queryPrice(new OrderPriceDailyMaintainance().getLastAlertDate(),
              new QueryLatestPrice4ToStrategy(fiorgs, moids), startDate,
              endDate);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    PriceCalculatorUtil.logTime("采购询价，内部交易询最新价结束时间:");/* -=notranslate=- */

    return prices;
  }

  /**
   * 查询最低价
   * 
   * @param purchaseOrg
   *          采购组织
   * @param supplier
   *          供应商
   * @param currency
   *          币种
   * @param materials
   *          物料OID数组
   * @param startDate 起始询价单据日期<font color="red">（可为空）</font>
   * @param endDate 结束询价单据日期<font color="red">（可为空）</font>
   * @return OrderPriceData[]返回查到的数据
   */
  public OrderPriceData[] queryLowestPrice(String[] purchaseOrgs,
      String[] suppliers, String[] currencies, String[] materials,
      UFDate startDate, UFDate endDate) {
    PriceCalculatorUtil.logTime("采购询价，询最低价开始时间:");/* -=notranslate=- */
    // 取本月的第一天作为分界时间，这一天以前的数据都从询价表里查，这一天之后的数据都从采购订单里查
    OrderPriceData[] prices = null;
    try {
      prices =
          this.queryPrice(new OrderPriceDailyMaintainance().getLastAlertDate(),
              new QueryLowestPriceStrategy(purchaseOrgs, suppliers, currencies,
                  materials), startDate, endDate);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    PriceCalculatorUtil.logTime("采购询价，询最低价结束时间:");/* -=notranslate=- */

    return prices;
  }

  private OrderPriceData[] queryPrice(UFDate divideTime,
      IQueryPriceStrategy queryPriceStrategy, UFDate startDate, UFDate endDate)
      throws BusinessException {
    try {

      // 对dbilldate这个限制条件，要取开始时间所在月的第一天，结束时间所在月的最后一天
      // 其实也许不用这么做吧，虽然ts是按月统计的，但是dbilldate不是呀
      UFDate startTime = null;
      UFDate endTime = null;
      if (startDate != null) {
        startTime = startDate.getDateBefore(startDate.getDay() - 1);
      }
      if (endDate != null) {
        endTime =
            endDate.getDateAfter(endDate.getDaysMonth() - endDate.getDay())
                .asEnd();
      }
      return this.queryPriceByDivideTime(divideTime, queryPriceStrategy,
          startTime, endTime);
    }
    catch (Exception e) {
      Log.error(e);
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 3种询价查询的框架是相同的，所以，写出相同的地方；并把strategy传入，来拼那些不同的地方
   * divideTime是一个分界点，这点到现在这个时间段查采购订单表po_order_b，一般是上次预警时间
   * 从这个时间点到以前最早的时间就查询询价表po_order_price，从这个时间点到以前的时间，
   * bvalidate字段是无效的数据还是查询采购订单表po_order_b；
   * 把这3个数据union all起来作为总的数据
   * 
   * @param divideTime查询询价表和采购订单表的分界时间，一般是上次询价的时间
   * @param queryPriceStrategy 询价的场景
   * @param startDate 起始询价单据日期<font color="red">（可为空）</font>
   * @param endDate 结束询价单据日期<font color="red">（可为空）</font>
   * @return OrderPriceData[]返回查到的数据
   */
  private OrderPriceData[] queryPriceByDivideTime(UFDate divideTime,
      IQueryPriceStrategy queryPriceStrategy, UFDate startDate, UFDate endDate) {

    SqlBuilder sql = new SqlBuilder();
    sql.append(" select " + queryPriceStrategy.getSelectPart(" a."));
    // 第一层子查询
    sql.append(" from( ");

    sql.append(" select "
        + queryPriceStrategy.getSelectPartFromUnionData(" b.", "m "));
    // 第二层子查询
    sql.append(" from( ");
    // 从po_order_b表里查从上次预警时间到现在的数据
    sql.append(" select t.dbilldate, "
        + queryPriceStrategy.getSelectPart(" t."));
    sql.append(" from (");
    // 第三层子查询开始
    sql.append(" select /*+no_index(c I_PU_21_B_CALC)*/  c.dbilldate, "
        + queryPriceStrategy.getAnalysisFunctionPart(" c."));
    sql.append(" from po_order_b c inner join po_order d on c.pk_order=d.pk_order ");
    sql.append(queryPriceStrategy.getFromPart());
    sql.append(" where  d.dr=0  and c.dr=0 and c.fisactive<>"
        + EnumActive.REVISEHISTORY.value() + " ");
    sql.append(" and d." + OrderHeaderVO.FORDERSTATUS, new int[] {
      POEnumBillStatus.APPROVE.toInt(), EnumBillStatus.EXPORT.toInt()
    });
    // 这里加一天是为了能用索引，所以要用><把一段数据括起来
    sql.append(queryPriceStrategy.getQueryConditionPart(" d.ts", " c.",
        divideTime, new UFDate().getDateAfter(1)));
    sql.append(PriceCalculatorUtil.getBeginAndEndTimeQueryPart(" c.dbilldate",
        startDate, endDate, ICalendar.MILLIS_PER_SECOND));

    // 第三层子查询结束
    sql.append(" ) t  WHERE t.rno=1 ");

    sql.append(" union all ");
    // 从po_order_price表里查有效的数据
    sql.append(" select c.dbilldate,   "
        + queryPriceStrategy.getSelectPart(" c."));
    sql.append(" from  po_order_price c  " + queryPriceStrategy.getFromPart());
    sql.append(" where c.bvalidate='Y' "
        + queryPriceStrategy.getQueryConditionPart(" c.modifiedtime", " c.",
            null, divideTime));
    sql.append(PriceCalculatorUtil.getBeginAndEndTimeQueryPart(" c.dbilldate",
        startDate, endDate, ICalendar.MILLIS_PER_SECOND));

    sql.append(" union all ");
    // 计算divdetime之前的那些状态标识为无效的
    sql.append(" select t.dbilldate, "
        + queryPriceStrategy.getSelectPart(" t."));
    sql.append(" from ( ");
    // 第三层子查询开始
    sql.append(" select c.dbilldate,   "
        + queryPriceStrategy.getAnalysisFunctionPart(" c."));
    sql.append(" from po_order_b c " + queryPriceStrategy.getFromPart()
        + " inner join  po_order_price_b d on  d.pk_order_b=c.pk_order_b ");

    sql.append("  inner join po_order f on f.pk_order=c.pk_order    ");
    sql.append(" inner  join po_order_price e  on e.pk_order_price = d.pk_order_price ");
    sql.append(" where  e.bvalidate='N' ");
    sql.append(" and f." + OrderHeaderVO.FORDERSTATUS, new int[] {
      POEnumBillStatus.APPROVE.toInt(), EnumBillStatus.EXPORT.toInt()
    });
    sql.append(queryPriceStrategy.getQueryConditionPart(" f.ts", " e.", null,
        null));
    sql.append(" and f.dr=0 and c.dr=0 and c.fisactive<>"
        + EnumActive.REVISEHISTORY.value() + " ");
    // 第三层子查询结束
    sql.append(PriceCalculatorUtil.getBeginAndEndTimeQueryPart(" c.dbilldate",
        startDate, endDate, ICalendar.MILLIS_PER_SECOND));

    sql.append(" ) t  WHERE t.rno=1 ");

    // 第二层子查询
    sql.append(" ) b ");
    // 第一层子查询
    sql.append(" ) a ");
    sql.append(" where  a.rno=1");

    return queryPriceStrategy.getResult(PriceCalculatorUtil.query(
        sql.toString(), "queryLatestPrice"));

  }
}
