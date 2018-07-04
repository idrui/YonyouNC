package nc.itf.pu.report.estmothstat;

import nc.pub.smart.context.SmartContext;

/**
 * 暂估月统计数据加工
 * 
 * @since 6.0
 * @version 2011-8-22 下午12:00:24
 * @author 田锋涛
 */

public interface IEstMonthStatReport {
  /**
   * 获取完整的查询sql
   * 
   * @param context
   * @return
   */
  String getQuerySql(SmartContext context);
}
