package nc.impl.pu.report.estdiffer;

import nc.impl.pu.report.estdiffer.sqlbuilder.EstDifferSqlBuilder;
import nc.itf.pu.report.estdiffer.IEstDifferReport;
import nc.pub.smart.context.SmartContext;

/**
 * 暂估差异报表查询实现类
 * 
 * @since 6.1
 * @version 2012-8-17 下午04:22:57
 * @author tianft
 */
public class EstDifferReportImpl implements IEstDifferReport {

  @Override
  public String getQuerySql(SmartContext context) {
    return new EstDifferSqlBuilder(context).toString();
  }
}
