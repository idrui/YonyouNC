package nc.itf.pu.report.arrival;

import nc.pub.smart.context.SmartContext;
import nc.pub.smart.exception.SmartException;

/**
 * 采购到货-日到货情况查询
 * 
 * @since 6.3
 * @version 2012-8-21 下午09:29:23
 * @author fanly3
 */
public interface IDayArriveReport {
  /**
   * 获取完整的SQL
   * 
   * @param context 语义模型的上下文对象
   * @return 返回日到货情况的完整SQL
   * @throws SmartException 语义模型异常
   */
  String getDayArrvQuerySql(SmartContext context) throws SmartException;

}
