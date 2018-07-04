package nc.impl.pu.report.estdiffer;

import nc.impl.pu.report.estdiffer.sqlbuilder.EstDifferSqlBuilder;
import nc.itf.pu.report.estdiffer.IEstDifferReport;
import nc.pub.smart.context.SmartContext;

/**
 * �ݹ����챨���ѯʵ����
 * 
 * @since 6.1
 * @version 2012-8-17 ����04:22:57
 * @author tianft
 */
public class EstDifferReportImpl implements IEstDifferReport {

  @Override
  public String getQuerySql(SmartContext context) {
    return new EstDifferSqlBuilder(context).toString();
  }
}
