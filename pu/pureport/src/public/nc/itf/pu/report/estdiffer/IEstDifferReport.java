package nc.itf.pu.report.estdiffer;

import nc.pub.smart.context.SmartContext;

/**
 * 暂估差异表报表查询接口
 * 
 * @since 6.1
 * @version 2012-8-17 下午03:51:56
 * @author tianft
 */
public interface IEstDifferReport {
  /**
   * 获取完整的查询sql
   * 
   * @param context
   * @return
   */
  String getQuerySql(SmartContext context);
}
