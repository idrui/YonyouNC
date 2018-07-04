package nc.impl.pu.report.estmothstat.sqlbuilder;

import nc.impl.pu.report.estmothstat.sqlbuilder.settle.SettleSqlbuilder;
import nc.vo.pu.report.queryinfo.eststat.PuEstStatQryInfoPara;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 本月前暂估本月结算
 * 
 * @since 6.0
 * @version 2011-8-22 下午01:44:47
 * @author 田锋涛
 */

public class PreMonthEstAndSettle extends AbstractEstMonthStatSqlBuilder {

  /**
   * @param queryPara
   */
  public PreMonthEstAndSettle(PuEstStatQryInfoPara queryPara) {
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
    return settleSql.getQuerySql() + " PreMonthEstAndSettle ";
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
    // sql.append("     pk_areacl pk_areacl, ");
    sql.append("     pk_dept pk_dept, ");
    sql.append("     pk_dept_v pk_dept_v, ");
    sql.append("     pk_psndoc pk_psndoc, ");
    sql.append("     pk_stordoc pk_stordoc, ");
    sql.append("     cwhsmanagerid cwhsmanagerid, ");
    // 本月前暂估本月结算
    sql.append("     nsettlenum prensettlenum, ");
    sql.append("     ngoodsmoney prengoodsmoney, ");
    sql.append("     nclashestmoney prenclashestmoney, ");
    // 本月入库本月结算
    sql.append("     ( 0 ) currnsettlenum, ");
    sql.append("     ( 0 ) currngoodsmoney, ");
    sql.append("     ( 0 ) currnclashestmoney, ");
    // 本月入库本月暂估
    sql.append("     ( 0 ) currunestnum, ");
    sql.append("     ( 0 ) currunestmny, ");
    // 本月前暂估本月未结算
    sql.append("     ( 0 ) preunestnum, ");
    sql.append("     ( 0 ) preunestmny, ");
    sql.append("     ccurrencyid ccurrencyid ");
    sql.append(" From ");

    return sql.toString();
  }

}
