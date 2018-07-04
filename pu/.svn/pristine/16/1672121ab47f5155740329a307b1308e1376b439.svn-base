package nc.bs.pu.m21.query.price.cal;

import java.util.Calendar;

import nc.bs.uap.lock.PKLock;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.uap.IPKLockBS;
import nc.vo.pu.m21.entity.OrderPriceVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 采购询价-工具类。
 * 
 * @since 6.1
 * @version 2011-11-19 上午09:02:07
 * @author yangtian
 */
public class PriceCalculatorUtil {
  /**
   * 加排他锁
   * 并发控制，在执行无效数据重算维护和新数据重算预警之前要锁住特殊pk，这样可以让修订/弃审操作与两种预警互斥。
   */

  public static boolean addLock() {

    return PKLock.getInstance().addDynamicLock(OrderPriceVO.PK_CALC);
  }

  /**
   * 加共享锁，这样同一个用户可以并行操作表；但是会排除预警的锁，所以更新删除与预警是串行的。
   * 并发控制，在执行无效数据重算维护和新数据重算预警之前要锁住特殊pk，这样可以让修订/弃审操作与两种预警互斥，而修订/弃审操作之间是并行的。
   */

  public static boolean addSharedLock() {

    return PKLock.getInstance().addDynamicLock(
        OrderPriceVO.PK_CALC + IPKLockBS.STR_SHARED_LOCK);

  }

  /**
   * 查询语句的时间限制部分拼接的sql
   * 
   * @param columnName字段的表名
   * @param beginTime 开始时间
   * @param endTime 结束时间
   * @param offsetSecondOnBegin 开始时间增加毫秒数
   * @return String sql
   */
  public static String getBeginAndEndTimeQueryPart(String columnName,
      UFDate beginTime, UFDate endTime, int offsetSecondOnBegin) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" ");
    UFDate beginDate = null;
    /* !!不可以把and放在外面，因为and从这个函数里出来的时候在完全没有填入时间的时候可能没有and */

    if (beginTime != null) {// 因为sql规范说只能用>=所以要把beginTime往前推一秒
      beginDate = UFDate.getDate(beginTime.getMillis() + offsetSecondOnBegin);
      sql.append(" and " + columnName + "  >=  '" + beginDate + "' ");
    }

    if (endTime != null) {
      sql.append(" and " + columnName + "  <=  '" + endTime + "' ");
    }
    return sql.toString();
  }

  /**
   * 获得查询最低价中选择价格部分
   * 
   * @param tableName 表名
   * @param postfix 后缀
   * @return String
   */
  // 不可以把物料放这里，因为预警的时候需要没有物料
  public static String getSelectPartForLowestQuery(String tableName,
      String postfix) {
    String attributeNames[] =
        new String[] {
          OrderPriceVO.NNETPRICE, OrderPriceVO.NTAXNETPRICE,
          OrderPriceVO.NORIGNETPRICE, OrderPriceVO.NORIGTAXNETPRICE
        };

    SqlBuilder selectPart = new SqlBuilder();

    for (String attributeName : attributeNames) {
      selectPart.append(tableName + attributeName + postfix);
      selectPart.append(attributeName == OrderPriceVO.NORIGTAXNETPRICE ? " "
          : ", ");
    }

    return selectPart.toString();
  }

  /**
   * 记录一次
   * 
   * @param e 异常信息
   */
  public static void logError(Exception e) {
    Log.error("采购询价错误：");/* -=notranslate=- */
    Log.error(e);
  }

  /**
   * 记录日志
   * 
   * @param message 日志信息
   */

  public static void logTime(String message) {
    Log.debug("采购询价"/* -=notranslate=- */+ message
        + UFDate.getDate(Calendar.getInstance().getTime()).toString());
  }

  /**
   * 对查询数据库操作进行封装，记录日志
   * 
   * @param sql 要执行的sql
   * @param functionName 函数名
   */
  public static IRowSet query(String sql, String functionName) {

    PriceCalculatorUtil.logTime(functionName + "开始执行时间是：");/* -=notranslate=- */
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet returnvalue = utils.query(sql);
    PriceCalculatorUtil.logTime(functionName + "执行结束时间是：");/* -=notranslate=- */
    return returnvalue;

  }

  /**
   * 对更新数据库操作进行封装，记录日志
   * 
   * @param sql 要执行的sql
   * @param functionName 函数名
   */
  public static int update(String sql, String functionName) {
    PriceCalculatorUtil.logTime(functionName + "开始执行时间是：");/* -=notranslate=- */
    DataAccessUtils utils = new DataAccessUtils();
    int returnvalue = utils.update(sql);
    PriceCalculatorUtil.logTime(functionName + "执行结束时间是：");/* -=notranslate=- */
    return returnvalue;

  }
}
