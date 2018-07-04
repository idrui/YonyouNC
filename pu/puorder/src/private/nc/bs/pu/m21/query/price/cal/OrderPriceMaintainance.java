package nc.bs.pu.m21.query.price.cal;

import java.util.Calendar;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderPriceVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.m21.enumeration.EnumBillStatus;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.ICalendar;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 采购询价-采购价格表维护类的基类，包含有两种维护类共同的部分
 * 
 * @since 6.1
 * @version 2011-11-19 上午09:02:07
 * @author yangtian
 */
public abstract class OrderPriceMaintainance {

  private UFDate m_endtime = null;

  /**
   * 所有计算po_order_price 表的数据都会公共用到一段代码，所以提取出来
   * 
   * @param beginTime 开始时间
   * @param endTime 结束时间
   * @param bvalidate 插入的 bvalidate字段的值是'N'还是'Y'
   * @param innerCode 因为这段代码是环绕型的，所以要把不同代码传入，放到公共代码的中间
   * @return String sql
   */
  public String calculateOrderPriceForAll(String innerCode, String bvalidate,
      UFDate beginTime, UFDate endTime) {
    SqlBuilder sql = new SqlBuilder();
    // 应该有36个字段，po_order_price
    sql.append(" insert into po_order_price( ts, bvalidate, pk_order_price,  dbilldate,  modifiedtime, "
        + this.getSelectPart(" ")
        + PriceCalculatorUtil.getSelectPartForLowestQuery(" ", "m ") + " ) ");
    sql.append(" select  '" + new UFDate().toString() + "', '" + bvalidate
        + "', a.pk_order_b,  a.dbilldate, a.ts, " + this.getSelectPart(" a.")
        + PriceCalculatorUtil.getSelectPartForLowestQuery(" a.", "m"));
    // 第一层子查询
    sql.append(" from( ");

    sql.append(" select b.pk_order_b,  b.dbilldate, a.ts, "
        + this.getSelectPart(" b."));
    sql.append(this.getAnalysisFunctionPartForLowestPrice(" b.") + ", ");

    sql.append(" row_number()  over(partition by   substring(a.ts, 1,7) ,   "
        + this.getAnalysisFunctionColumnForLatestPrice(" b.")
        + "  order by  b.dbilldate desc ) rno  ");

    sql.append(" from po_order_b b inner  join po_order a on b.pk_order=a.pk_order ");

    // 填入各种应用场景不同的代码部分
    sql.append(innerCode);

    sql.append("   b.dr=0 and a.dr=0  and b.fisactive<>"
        + EnumActive.REVISEHISTORY.value() + " ");

    sql.append(" and a." + OrderHeaderVO.FORDERSTATUS, new int[] {
      POEnumBillStatus.APPROVE.toInt(), EnumBillStatus.EXPORT.toInt()
    });
    sql.append(PriceCalculatorUtil.getBeginAndEndTimeQueryPart(" a.ts ",
        beginTime, endTime, 0));
    // 第一层子查询
    sql.append(" ) a ");
    sql.append("where  a.rno=1");
    return sql.toString();
  }

  /**
   * 所有计算po_order_price 表的数据都会公共用到一段代码，所以提取出来
   * 
   * @param beginTime 开始时间
   * @param endTime 结束时间
   * @param innerCode 因为这段代码是环绕型的，所以要把不同代码传入，放到公共代码的中间
   * @return String sql
   */
  public String calculateOrderPriceForAll(String innerCode, UFDate beginTime,
      UFDate endTime) {
    return this.calculateOrderPriceForAll(innerCode, "Y", beginTime, endTime);
  }

  /**
   * 所有计算po_order_price 表的数据都会公共用到一段代码，所以提取出来
   * 
   * @param beginTime 开始时间
   * @param endTime 结束时间
   * @param String otherWherePart 按日预警需要的限制条件
   */
  public void calculatePriceBForAll(UFDate beginTime, UFDate endTime,
      String otherWherePart) {

    SqlBuilder sql = new SqlBuilder();
    sql.append(" insert into po_order_price_b(ts,pk_order_b,dr,pk_order_price) ");
    sql.append(" select '"
        + new UFDate().toString()
        + "', b.pk_order_b, 0, prc.pk_order_price from po_order_b b inner join  po_order a on  a.pk_order=b.pk_order  ");
    sql.append(" inner join po_order_price prc on   b.pk_org=prc.pk_org ");
    sql.append(" and b.pk_supplier=prc.pk_supplier and b.corigcurrencyid=prc.corigcurrencyid and b.pk_srcmaterial=prc.pk_srcmaterial ");
    sql.append(" and b.pk_psfinanceorg=prc.pk_psfinanceorg and b.pk_arrvstoorg=prc.pk_arrvstoorg and substring(prc.modifiedtime,1,7) =substring(a.ts,1,7) ");
    sql.append("  where  b.dr=0 and b.fisactive<>"
        + EnumActive.REVISEHISTORY.value() + otherWherePart + " ");
    sql.append(" and a." + OrderHeaderVO.FORDERSTATUS, new int[] {
      POEnumBillStatus.APPROVE.toInt(), EnumBillStatus.EXPORT.toInt()
    });
    sql.append(PriceCalculatorUtil.getBeginAndEndTimeQueryPart(" a.ts ",
        beginTime, endTime, 0));
    PriceCalculatorUtil.update(sql.toString(), "calculate4OrderPriceBByTime");
  }

  /**
   * 删除po_order_price的操作
   * 
   * @param wherePart 获得sql的where的部分
   */
  public void deleteOrderPrice(String wherePart) {

    SqlBuilder sql = new SqlBuilder();
    sql.append(" delete from po_order_price  " + wherePart);
    PriceCalculatorUtil.update(sql.toString(), "deleteOrderPrice");

  }

  /**
   * 删除po_order_price_b的操作
   * 
   * @param wherePart 获得sql的where的部分
   */
  public void deleteOrderPriceB(String wherePart) {

    SqlBuilder sql = new SqlBuilder();
    sql.append(" delete from po_order_price_b  " + wherePart);

    PriceCalculatorUtil.update(sql.toString(), "deleteOrderPriceB");
  }

  /**
   * 执行维护工作
   * 
   * @param UFDate lastAlertDate上次预警时间
   */

  public abstract void doMaintainance(UFDate lastAlertDate);

  /**
   * 执行定时任务
   * 
   * @throws BusinessException
   */

  public void excuteTimeTask() throws BusinessException {
    try {
      if (PriceCalculatorUtil.addLock()) {
        // 在这里获得上次计算的时间,如果能查到就计算上个月的，如果没法查到说明是第一次计算，就计算所有时间
        UFDate lastAlertDate = this.getLastAlertDate();
        if (lastAlertDate == null) {
          // 当没有查到特殊信息数据的时候就做之前的所有月的数据
          this.initCalculate();
        }
        else if (this.isNeedReCal().booleanValue()) {

          this.doMaintainance(lastAlertDate);

        }
      }
      else {
        Log.error("采购询价表计算失败，资源被锁住，请稍后访问");/* -=notranslate=- */
      }

    }
    catch (Exception e) {
      PriceCalculatorUtil.logError(e);/* -=notranslate=- */
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * 获得sql中统计最新价的时候的分析函数的部分
   * 
   * @param tableName 表名
   * @return String 返回sql
   */
  private String getAnalysisFunctionColumnForLatestPrice(String tableName) {

    String attributeNames[] =
        new String[] {
          OrderPriceVO.PK_ORG, OrderPriceVO.PK_SUPPLIER,
          OrderPriceVO.PK_SRCMATERIAL, OrderPriceVO.CORIGCURRENCYID,
          OrderPriceVO.PK_PSFINANCEORG, OrderPriceVO.PK_ARRVSTOORG
        };

    SqlBuilder analysisFunctionPart = new SqlBuilder();
    for (String attributeName : attributeNames) {
      analysisFunctionPart.append(tableName + attributeName);
      analysisFunctionPart
          .append(attributeName == OrderPriceVO.PK_ARRVSTOORG ? " " : ", ");
    }
    return analysisFunctionPart.toString();
  }

  /**
   * 获得sql中统计最低价的时候的分析函数的部分
   * 
   * @param tableName 表名
   * @return String sql
   */
  private String getAnalysisFunctionPartForLowestPrice(String tableName) {
    SqlBuilder analysisFunctionPart = new SqlBuilder();
    String attributeNames[] =
        new String[] {
          OrderPriceVO.NNETPRICE, OrderPriceVO.NTAXNETPRICE,
          OrderPriceVO.NORIGNETPRICE, OrderPriceVO.NORIGTAXNETPRICE
        };

    for (String attributeName : attributeNames) {
      analysisFunctionPart.append(" min( " + tableName + attributeName
          + ") over(partition by substring(a.ts, 1,7) , "
          + this.getAnalysisFunctionColumnForLatestPrice(tableName) + " ) ");
      analysisFunctionPart.append(" " + attributeName + "m ");
      analysisFunctionPart
          .append(attributeName == OrderPriceVO.NORIGTAXNETPRICE ? " " : ", ");
    }
    return analysisFunctionPart.toString();
  }

  /**
   * 因为sql中大部分select的部分都是一样的，所以可以抽取出一样的部分放在一起
   * 
   * @param tableName 表名
   * @return String sql
   */
  private String getSelectPart(String tableName) {
    String attributeNames[] =
        new String[] {
          OrderPriceVO.PK_ORG, OrderPriceVO.PK_PSFINANCEORG,
          OrderPriceVO.PK_ARRVSTOORG, OrderPriceVO.PK_SRCMATERIAL,
          OrderPriceVO.PK_SUPPLIER,

          OrderPriceVO.CORIGCURRENCYID, OrderPriceVO.CUNITID,
          OrderPriceVO.FTAXTYPEFLAG, OrderPriceVO.NTAXRATE,
          OrderPriceVO.CASTUNITID, OrderPriceVO.CQTUNITID,

          OrderPriceVO.NQTORIGTAXNETPRC, OrderPriceVO.NQTTAXNETPRICE,
          OrderPriceVO.NORIGTAXNETPRICE, OrderPriceVO.NTAXNETPRICE,

          OrderPriceVO.NQTORIGNETPRICE, OrderPriceVO.NQTNETPRICE,
          OrderPriceVO.NNETPRICE, OrderPriceVO.NORIGNETPRICE,

          OrderPriceVO.NQTTAXPRICE, OrderPriceVO.NQTORIGTAXPRICE,
          OrderPriceVO.NORIGTAXPRICE, OrderPriceVO.NTAXPRICE,

          OrderPriceVO.NQTORIGPRICE, OrderPriceVO.NQTPRICE,
          OrderPriceVO.NORIGPRICE, OrderPriceVO.NPRICE
        };

    SqlBuilder selectPart = new SqlBuilder();
    for (String attributeName : attributeNames) {
      if (attributeName != null) {
        selectPart.append(tableName + attributeName + ", ");
      }
    }
    // + PriceCalculatorUtil.getSelectPartForQuery(tableName);
    return selectPart.toString();
  }

  /**
   * 第一次预警，初始化各个表的计算
   */

  private void initCalculate() {
    PriceCalculatorUtil.logTime("订单价格初次提取预警开始时间是:");/* -=notranslate=- */
    // 初始计算的时候先清空 po_order_price表和po_order_price_b
    this.deleteOrderPrice(" ");
    this.initOrderPrice();
    this.deleteOrderPriceB(" ");
    this.initPriceB();

    // 只有计算成功后才能进行插入操作
    this.insertAlertDate();
    PriceCalculatorUtil.logTime("订单价格初次提取预警结束时间是:");/* -=notranslate=- */
  }

  /**
   * 为Po_order_price表计算一段时间下的所有的数据
   * 如果开始时间和结束时间都是null，表示要计算所有月份的数据
   * 
   * @param endTime 结束时间
   */

  private void initOrderPrice() {
    String excuteSql =
        this.calculateOrderPriceForAll(" where ", null, this.getEndTime());
    PriceCalculatorUtil.update(excuteSql, "initOrderPrice");

  }

  private void initPriceB() {

    this.calculatePriceBForAll(null, this.getEndTime(), " ");
  }

  /**
   * 插入当前预警的时间
   */
  private void insertAlertDate() {

    SqlBuilder sqlinsert = new SqlBuilder();
    sqlinsert
        .append(" insert into   po_order_price (pk_order_price, modifiedtime ) values ('"
            + OrderPriceVO.PK_CALC + "', '" + this.getEndTime() + "') ");
    PriceCalculatorUtil.update(sqlinsert.toString(), "insertAlertDate");
  }

  /**
   * 获得本次计算的结束时间
   * 之所以要取今天的前一天作为预警数据的结束是因为怕现在的数据会因为正在做啥的或者传输延迟而丢失。而且还有时差问题，美国21日中国22日，差12个小时
   * 
   * @param tableName 表名
   * @return UFDate 返回月预警的结束时间
   */
  protected UFDate getEndTime() {
    if (null == this.m_endtime) {// 保证一次计算中每个sql的endtime都是一致的，不能计算price的时候是一个时间，计算priceb的时候是一个时间
      Calendar now = Calendar.getInstance();
      this.m_endtime = new UFDateTime(now.getTime()).getDate().getDateBefore(1);
    }
    return this.m_endtime;
  }

  /**
   * 获得上次预警的时间
   * 
   * @return UFDate 返回上次预警的时间
   */
  protected UFDate getLastAlertDate() {
    SqlBuilder sqlquery = new SqlBuilder();
    sqlquery
        .append(" select  a.modifiedtime from po_order_price a where a.pk_order_price='"
            + OrderPriceVO.PK_CALC + "' ");

    DataAccessUtils utils = new DataAccessUtils();
    String[] results =
        utils.query(sqlquery.toString()).toOneDimensionStringArray();
    if (results.length == 0) {
      return null;
    }
    return new UFDateTime(results[0], ICalendar.BASE_TIMEZONE).getDate();
  }

  protected UFBoolean isNeedReCal() {
    return UFBoolean.TRUE;
  }

}
