package nc.impl.pu.report.estmothstat.sqlbuilder.est;

import nc.bs.pubapp.AppBsContext;
import nc.impl.pu.report.estmothstat.sqlbuilder.settle.M50SettleSqlbuilder;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pu.report.queryinfo.eststat.PuEstStatQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.StringUtils;

/**
 * @since 6.0
 * @version 2011-8-22 下午03:08:31
 * @author 田锋涛
 */

public class M50EstSqlbuilder extends M50SettleSqlbuilder {

  /**
   * @param queryPara
   */
  public M50EstSqlbuilder(PuEstStatQryInfoPara queryPara) {
    super(queryPara);
  }

  @Override
  public String getQuerySql() {
    String whereSql = this.builderWhereSql();
    if (StringUtils.isEmpty(whereSql)) {
      return null;
    }
    SqlBuilder sql = new SqlBuilder();
    // 货物暂估
    sql.append(this.getGoodEstSql(whereSql));
    sql.append(" union all ");
    // 费用暂估
    sql.append(this.getFeeEstSql(whereSql));
    return sql.toString();
  }

  /**
   * 费用暂估
   * 
   * @param whereSql
   * @return
   */
  private String getFeeEstSql(String whereSql) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" SELECT ");
    sql.append("        eb.pk_storeorg pk_storeorg, ");
    sql.append("        eb.pk_storeorg_v pk_storeorg_v, ");
    sql.append("        eb.pk_financeorg pk_org, ");
    sql.append("        eb.pk_financeorg_v pk_org_v, ");
    sql.append("        ( '' ) pk_purchaseorg, ");
    sql.append("        ( '' ) pk_purchaseorg_v, ");
    sql.append("        bd_material.pk_marbasclass pk_marbasclass, ");
    sql.append("        bd_material.pk_measdoc cunitid, ");
    sql.append("        eb.pk_material pk_material, ");// 货物
    sql.append("        ef.pk_supplier pk_supplier, ");// 费用暂估供应商
    // sql.append("        bd_supplier.pk_areacl pk_areacl, ");
    sql.append("        ( '' ) pk_dept, ");
    sql.append("        ( '' ) pk_dept_v, ");
    sql.append("        ( '' ) pk_psndoc, ");
    sql.append("        eb.pk_stordoc pk_stordoc, ");
    sql.append("        ( '' ) cwhsmanagerid, ");
    sql.append("        0 unestnum, ");// 费用暂估数量
    // sql.append("        ef.nestmny unestmny, ");// 费用暂估金额
    sql.append("        ef.ncalcostmny unestmny, ");// 费用暂估金额-计成本金额
    sql.append("        eb.ccurrencyid ccurrencyid ");// 本币币种
    sql.append("  FROM  po_vmifi eb ");
    sql.append(" INNER  JOIN  po_vmifi_fee ef ON ef.pk_stockps = eb.pk_stockps ");
    sql.append(" INNER  JOIN  bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    if (this.containSupplier()) {
      sql.append(" INNER  JOIN  bd_supplier bd_supplier ON ef.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append(" WHERE  eb.pk_group", AppBsContext.getInstance().getPkGroup());
    sql.append("   AND  eb.dr   = 0 ");
    sql.append("   AND isnull(ef.btoia,'N')   = 'Y' ");
    sql.append("   AND  ef.dr   = 0 ");
    sql.append("   AND ");
    sql.append(whereSql.replace("eb.pk_supplier", "ef.pk_supplier"));// 费用物料
    // 费用暂估日期
    sql.append("   AND  ");
    sql.append(this.buildAddWhere().replace("DTOCOSTAPDATE", "ef.destdate"));
    // 费用未结算或者结算了但未传成本
    sql.append("   AND  ");
    sql.append(this.getFeeUnclashAddWhere());
    return sql.toString();
  }

  /**
   * 货物暂估-结算未传IA部分
   * 
   * @param whereSql
   * @return
   */
  private String getGoodEstIAUnClashSql(String whereSql) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" SELECT ");
    sql.append("        eb.pk_storeorg pk_storeorg, ");
    sql.append("        eb.pk_storeorg_v pk_storeorg_v, ");
    sql.append("        eb.pk_financeorg pk_org, ");
    sql.append("        eb.pk_financeorg_v pk_org_v, ");
    sql.append("        ( '' ) pk_purchaseorg, ");
    sql.append("        ( '' ) pk_purchaseorg_v, ");
    sql.append("        bd_material.pk_marbasclass pk_marbasclass, ");
    sql.append("        bd_material.pk_measdoc cunitid, ");
    sql.append("        eb.pk_material pk_material, ");
    sql.append("        eb.pk_supplier pk_supplier, ");
    // sql.append("        bd_supplier.pk_areacl pk_areacl, ");
    sql.append("        ( '' ) pk_dept, ");
    sql.append("        ( '' ) pk_dept_v, ");
    sql.append("        ( '' ) pk_psndoc, ");
    sql.append("        eb.pk_stordoc pk_stordoc, ");
    sql.append("        ( '' ) cwhsmanagerid, ");
    sql.append("        sb.nsettlenum unestnum, ");
    sql.append("        sb.nclashestmoney unestmny, ");
    sql.append("        eb.ccurrencyid ccurrencyid ");
    sql.append("  FROM  po_vmifi eb ");
    sql.append(" inner join po_settlebill_b sb on sb.pk_stock_b=eb.pk_stockps_b ");
    sql.append(" inner join po_settlebill sh on sh.pk_settlebill = sb.pk_settlebill ");
    sql.append(" INNER  JOIN  bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    if (this.containSupplier()) {
      sql.append(" INNER  JOIN  bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append(" WHERE  eb.pk_group", AppBsContext.getInstance().getPkGroup());
    sql.append("   AND  eb.dr   = 0 ");
    sql.append("   and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// 暂估成本或者确认了成本

    // wuxla 2013-4-24 影响成本的才查询出来
    sql.append("  and eb.baffectcost", UFBoolean.TRUE);

    sql.append("   AND sb.dr = 0 ");
    sql.append("   AND sh.dr = 0 ");
    sql.append("   and ( isnull(sh.btoia,'N') ='N' ");// 结算过但未传成本
    sql.append("         or isnull(sh.btoia,'N') ='Y' ");// 当前查询会计期间之后的数据
    if (this.getQueryPara() != null) {// 语义模型点完成时可能为空。
      sql.append("         and sh.dbilldate", ">", this.getQueryPara()
          .getEndDate().toString());
    }
    sql.append("        ) ");
    sql.append("   AND ");
    sql.append(whereSql);
    sql.append("   AND  ");
    sql.append(this.buildAddWhere());
    return sql.toString();
  }

  /**
   * 货物暂估-结算过的未冲销部分
   * 
   * @param whereSql
   * @return
   */
  private String getGoodEstSettleUnClashSql(String whereSql) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" SELECT ");
    sql.append("        eb.pk_storeorg pk_storeorg, ");
    sql.append("        eb.pk_storeorg_v pk_storeorg_v, ");
    sql.append("        eb.pk_financeorg pk_org, ");
    sql.append("        eb.pk_financeorg_v pk_org_v, ");
    sql.append("        ( '' ) pk_purchaseorg, ");
    sql.append("        ( '' ) pk_purchaseorg_v, ");
    sql.append("        bd_material.pk_marbasclass pk_marbasclass, ");
    sql.append("        bd_material.pk_measdoc cunitid, ");
    sql.append("        eb.pk_material pk_material, ");
    sql.append("        eb.pk_supplier pk_supplier, ");
    // sql.append("        bd_supplier.pk_areacl pk_areacl, ");
    sql.append("        ( '' ) pk_dept, ");
    sql.append("        ( '' ) pk_dept_v, ");
    sql.append("        ( '' ) pk_psndoc, ");
    sql.append("        eb.pk_stordoc pk_stordoc, ");
    sql.append("        ( '' ) cwhsmanagerid, ");
    sql.append("        ( isnull(eb.nestnum,0) - isnull(eb.naccestcoststtlnum,0) ) unestnum, ");
    // sql.append("        ( isnull(eb.nestmny,0) - isnull(eb.naccestcostwashmny,0) ) unestmny, ");
    sql.append("        ( isnull(eb.nestcalcostmny,0) - isnull(eb.naccestcostwashmny,0) ) unestmny, "); // 计成本金额
    sql.append("        eb.ccurrencyid ccurrencyid ");
    sql.append("  FROM  po_vmifi eb ");
    sql.append(" INNER  JOIN  bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    if (this.containSupplier()) {
      sql.append(" INNER  JOIN  bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append(" WHERE  eb.pk_group", AppBsContext.getInstance().getPkGroup());
    sql.append("   AND  eb.dr   = 0 ");
    sql.append("   and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// 暂估成本或者确认了成本

    // wuxla 2013-4-24 影响成本的才查询出来
    sql.append("  and eb.baffectcost", UFBoolean.TRUE);

    // 有尚未冲销部分金额
    sql.append("   AND isnull(eb.nestcalcostmny,0) - isnull(eb.naccestcostwashmny,0)<>0 ");
    sql.append("   AND ");
    sql.append(whereSql);
    sql.append("   AND  ");
    sql.append(this.buildAddWhere());
    return sql.toString();
  }

  /**
   * 货物暂估未冲销部分统计
   * 
   * @param whereSql
   * @return
   */
  private String getGoodEstSql(String whereSql) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(this.getGoodEstSettleUnClashSql(whereSql));
    sql.append(" union all ");
    sql.append(this.getGoodEstIAUnClashSql(whereSql));
    return sql.toString();
  }
}
