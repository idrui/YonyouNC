package nc.impl.pu.report.estmothstat;

import nc.impl.pu.report.estmothstat.sqlbuilder.CurMonthStockInAndEst;
import nc.impl.pu.report.estmothstat.sqlbuilder.CurMonthStockInAndSettle;
import nc.impl.pu.report.estmothstat.sqlbuilder.PreMonthEstAndNoSettle;
import nc.impl.pu.report.estmothstat.sqlbuilder.PreMonthEstAndSettle;
import nc.itf.pu.report.estmothstat.IEstMonthStatReport;
import nc.pub.smart.context.SmartContext;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.queryinfo.eststat.PuEstStatQryInfoPara;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 暂估月统计数据加工实现
 * 
 * @since 6.0
 * @version 2011-8-22 下午12:02:41
 * @author 田锋涛
 */

public class EstMonthStatReportImpl implements IEstMonthStatReport {

  @Override
  public String getQuerySql(SmartContext context) {

    PuEstStatQryInfoPara queryPara =
        (PuEstStatQryInfoPara) context.getAttribute(PuQueryInfoPara.QUERY_PARA);
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select ");
    sql.append("   pk_storeorg , ");
    sql.append("   pk_storeorg_v , ");
    sql.append("   pk_financeorg , ");
    sql.append("   pk_financeorg_v , ");
    sql.append("   pk_purchaseorg , ");
    sql.append("   pk_purchaseorg_v , ");
    sql.append("   pk_marbasclass , ");
    sql.append("   cunitid , ");
    sql.append("   pk_material , ");
    sql.append("   pk_supplier , ");
    // sql.append("   pk_areacl , ");
    sql.append("   pk_dept , ");
    sql.append("   pk_dept_v , ");
    sql.append("   pk_psndoc , ");
    sql.append("   pk_stordoc , ");
    sql.append("   cwhsmanagerid , ");
    // 本月前暂估本月结算
    // 1 PRENSETTLENUM 本月前 - 结算数量
    // 2 PRENGOODSMONEY 本月前 - 结算金额
    // 3 PRENCLASHESTMONEY 本月前 - 冲销暂估金额
    sql.append("   prensettlenum, ");
    sql.append("   prengoodsmoney, ");
    sql.append("   prenclashestmoney, ");
    // 本月入库本月结算
    // 1 CURRNSETTLENUM 本月 - 结算数量
    // 2 CURRNGOODSMONEY 本月 - 结算金额
    // 3 CURRNCLASHESTMONEY 本月 - 冲销暂估金额
    sql.append("   currnsettlenum, ");
    sql.append("   currngoodsmoney, ");
    sql.append("   currnclashestmoney, ");
    // 本月入库本月暂估
    // 1 CURRUNESTNUM 本月 - 未冲销暂估数量
    // 2 CURRUNESTMNY 本月 - 未冲销暂估金额
    sql.append("   currunestnum, ");
    sql.append("   currunestmny, ");

    // 本月前暂估本月未结算
    // 1 PREUNESTNUM 本月前 - 未冲销暂估数量
    // 2 PREUNESTMNY 本月前 - 未冲销暂估金额
    sql.append("   preunestnum, ");
    sql.append("   preunestmny, ");

    sql.append("   ccurrencyid ");
    sql.append(" from ");
    sql.append("   ( ");
    sql.append("     ( ");
    sql.append(new CurMonthStockInAndEst(queryPara).getQuerySql());// 本月入库本月暂估
    sql.append("     )  ");
    sql.append("     union all  ");
    sql.append("     ( ");
    sql.append(new PreMonthEstAndSettle(queryPara).getQuerySql());// 本月前暂估本月结算
    sql.append("     )  ");
    sql.append("     union all  ");
    sql.append("     ( ");
    sql.append(new CurMonthStockInAndSettle(queryPara).getQuerySql());// 本月入库本月结算
    sql.append("     )  ");
    sql.append("     union all  ");
    sql.append("     ( ");
    sql.append(new PreMonthEstAndNoSettle(queryPara).getQuerySql());// 本月前暂估本月未结算
    sql.append("     )  ");
    sql.append("   )  SETTLESTAT ");
    Log.debug(" EstMonthStatReportImpl sql=" + sql.toString());
    return sql.toString();
  }

}
