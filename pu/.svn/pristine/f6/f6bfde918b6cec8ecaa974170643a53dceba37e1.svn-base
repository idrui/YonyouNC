package nc.bs.pu.m21.query.price.cal;

import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.data.IRowSet;

/**
 * 为不同的查询场景提供不同的查询服务
 * 
 * @since 6.1
 * @version 2011-11-20 上午11:25:32
 * @author yangtian
 */
public interface IQueryPriceStrategy {
  /**
   * 获得sql中select的分析函数部分
   * 
   * @param tableName 表名
   * @return String sql
   */
  public String getAnalysisFunctionPart(String tableName);

  /**
   * 获得sql的from部分
   * 
   * @return String sql
   */
  public String getFromPart();

  /**
   * 获得sql的where部分
   * 
   * @param modifiedTimeTableName modifiedtime字段的表名
   * @param tableName 表名
   * @param beginTime 开始时间
   * @param endTime 结束时间
   * @return String sql
   */
  public String getQueryConditionPart(String modifiedTimeTableName,
      String tableName, UFDate beginTime, UFDate endTime);

  /**
   * 加工从数据库中查询到的数据，之后返回给调用方
   * 
   * @param rowset 数据库中原始数据
   * @return OrderPriceData[] 经过加工后的数据
   */
  public OrderPriceData[] getResult(IRowSet rowset);

  /**
   * 从po_order_price表里取数据的select部分
   * 
   * @param tableName 表名
   * @return String sql
   */

  public String getSelectPart(String tableName);

  /**
   * 其实和getAnalysisFunctionPart很像，只是差最低价的时候有区别，主要区别就是那些最低价字段是不是要加'm'后缀
   * 
   * @param tableName 表名
   * @return String sql
   */
  public String getSelectPartFromUnionData(String tableName, String postfix);
}
