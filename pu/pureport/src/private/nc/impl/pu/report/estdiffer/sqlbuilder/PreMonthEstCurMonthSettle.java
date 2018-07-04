package nc.impl.pu.report.estdiffer.sqlbuilder;

import nc.impl.pu.report.estmothstat.sqlbuilder.AbstractEstMonthStatSqlBuilder;
import nc.impl.pu.report.estmothstat.sqlbuilder.settle.SettleSqlbuilder;
import nc.vo.pu.report.queryinfo.estdiffer.PuEstDifferQryInfoPara;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 本月前暂估本月结算
 * 
 * @since 6.1
 * @version 2012-8-17 下午04:13:17
 * @author tianft
 */
public class PreMonthEstCurMonthSettle extends AbstractEstMonthStatSqlBuilder {

  public PreMonthEstCurMonthSettle(PuEstDifferQryInfoPara queryPara) {
    super(queryPara);
  }

  @Override
  public String getAddWhereSql() {
    if (this.getQueryPara() != null) {
      return this.getQueryPara().getPreEstCurSettleDateSql();
    }
    return null;
  }

  @Override
  public String getQuerySql() {
    SettleSqlbuilder settleSql = new SettleSqlbuilder(this.getQueryPara());
    settleSql.setAddWhereSql(this.getAddWhereSql());
    settleSql.setSelectFromWhere(this.getSelectFromWhere());
    return settleSql.getQuerySql() + " PreMonthEstCurMonthSettle ";
  }

  @Override
  public String getSelectFromWhere() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" SELECT ");
    sql.append("     pk_storeorg pk_storeorg, ");
    sql.append("     pk_storeorg_v pk_storeorg_v, ");
    sql.append("     pk_org pk_financeorg, ");
    sql.append("     pk_org_v pk_financeorg_v, ");
    sql.append("     pk_purchaseorg pk_purchaseorg, ");
    sql.append("     pk_purchaseorg_v pk_purchaseorg_v, ");
    sql.append("     pk_marbasclass pk_marbasclass, ");
    sql.append("     cunitid cunitid , ");
    sql.append("     pk_material pk_material, ");
    sql.append("     pk_supplier pk_supplier, ");
    sql.append("     pk_dept pk_dept, ");
    sql.append("     pk_dept_v pk_dept_v, ");
    sql.append("     pk_psndoc pk_psndoc, ");
    // 本月前暂估本月结算
    sql.append("     nsettlenum prensettlenum, ");
    sql.append("     ngoodsmoney prengoodsmoney, ");
    sql.append("     nclashestmoney prenclashestmoney, ");
    // sql.append("     ( 0 )  prendifferrate, ");
    // 本月暂估本月结算
    sql.append("     ( 0 ) currnsettlenum, ");
    sql.append("     ( 0 ) currngoodsmoney, ");
    sql.append("     ( 0 ) currnclashestmoney, ");
    // sql.append("     ( 0 ) currndifferrate, ");

    sql.append("     ccurrencyid ccurrencyid ");
    sql.append(" From ");

    return sql.toString();
  }

}
