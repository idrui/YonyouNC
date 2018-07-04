package nc.impl.pu.report.estmothstat.sqlbuilder.est;

import nc.bs.pubapp.AppBsContext;
import nc.impl.pu.report.estmothstat.sqlbuilder.settle.M45SettleSqlbuilder;
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

public class M45EstSqlbuilder extends M45SettleSqlbuilder {

  /**
   * @param queryPara
   */
  public M45EstSqlbuilder(PuEstStatQryInfoPara queryPara) {
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
   * 费用暂估未冲销统计
   * 
   * @param whereSql
   * @return
   */
  private String getFeeEstSql(String whereSql) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("SELECT ");
    sql.append("    eh.pk_org pk_storeorg, ");
    sql.append("    eh.pk_org_v pk_storeorg_v, ");
    sql.append("    eb.pk_financeorg pk_org, ");
    sql.append("    eb.pk_financeorg_v pk_org_v, ");
    sql.append("    eb.pk_purchaseorg pk_purchaseorg, ");
    sql.append("    eb.pk_purchaseorg_v pk_purchaseorg_v, ");
    sql.append("    bd_material.pk_marbasclass pk_marbasclass, ");
    sql.append("    bd_material.pk_measdoc cunitid, ");
    sql.append("    eb.pk_material pk_material, ");// 货物
    sql.append("    ef.pk_supplier pk_supplier, ");// 费用暂估供应商
    sql.append("    eh.pk_dept pk_dept, ");
    sql.append("    eh.pk_dept_v pk_dept_v, ");
    sql.append("    eh.pk_psndoc pk_psndoc, ");
    sql.append("    eh.pk_stordoc pk_stordoc, ");
    sql.append("    eh.cwhsmanagerid cwhsmanagerid, ");
    sql.append("    0 unestnum, ");// 费用暂估数量
    // sql.append("    ef.nestmny unestmny, ");// 费用暂估金额
    sql.append("    ef.ncalcostmny unestmny, ");// 费用暂估金额 ：v61取计成本金额
    sql.append("    eb.ccurrencyid ccurrencyid ");// 也是费用暂估的本币
    sql.append("  FROM po_purchaseinfi eh ");
    sql.append(" INNER JOIN po_purchaseinfi_b eb ON eh.pk_stockps = eb.pk_stockps ");
    sql.append(" INNER JOIN po_purchaseinfi_fee ef ON eb.pk_stockps_b = ef.pk_stockps_b ");
    // 关联费用物料
    sql.append(" INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    // inner join： 对于暂估来说肯定是要供应商的，而且暂估月统计没有供应商的数据也不应该查询出来
    if (this.containSupplier()) {
      sql.append(" INNER JOIN bd_supplier bd_supplier ON ef.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append(" where eb.dr=0 and ef.dr=0 ");
    sql.append("   AND isnull(ef.btoia,'N')   = 'Y' ");
    sql.append("   and eh.pk_group", AppBsContext.getInstance().getPkGroup());
    sql.append("   AND eh.dr   = 0 ");
    sql.append("   AND eb.dr = 0 ");
    sql.append(" AND ");
    sql.append(whereSql.replace("eb.pk_supplier", "ef.pk_supplier"));
    sql.append(" AND  ");
    // 费用暂估日期 供应商
    sql.append(this.buildAddWhere().replace("DTOCOSTAPDATE", "ef.destdate"));
    sql.append(" AND  ");
    // 费用未结算或者结算了但未传成本
    sql.append(this.getFeeUnclashAddWhere());
    return sql.toString();
  }

  /**
   * 货物暂估-结算未传存货部分
   * 
   * @param whereSql
   * @return
   */
  private String getGoodEstIAUnClashSql(String whereSql) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("SELECT ");
    sql.append("    eh.pk_org pk_storeorg, ");
    sql.append("    eh.pk_org_v pk_storeorg_v, ");
    sql.append("    eb.pk_financeorg pk_org, ");
    sql.append("    eb.pk_financeorg_v pk_org_v, ");
    sql.append("    eb.pk_purchaseorg pk_purchaseorg, ");
    sql.append("    eb.pk_purchaseorg_v pk_purchaseorg_v, ");
    sql.append("    bd_material.pk_marbasclass pk_marbasclass, ");
    sql.append("    bd_material.pk_measdoc cunitid, ");
    sql.append("    eb.pk_material pk_material, ");
    sql.append("    eb.pk_supplier pk_supplier, ");
    sql.append("    eh.pk_dept pk_dept, ");
    sql.append("    eh.pk_dept_v pk_dept_v, ");
    sql.append("    eh.pk_psndoc pk_psndoc, ");
    sql.append("    eh.pk_stordoc pk_stordoc, ");
    sql.append("    eh.cwhsmanagerid cwhsmanagerid, ");
    sql.append("    sb.nsettlenum unestnum, ");
    sql.append("    sb.nclashestmoney unestmny, ");
    sql.append("    eb.ccurrencyid ccurrencyid ");
    sql.append(" FROM  po_purchaseinfi eh ");
    sql.append(" INNER JOIN po_purchaseinfi_b eb ON eh.pk_stockps = eb.pk_stockps ");
    sql.append(" inner join po_settlebill_b sb on sb.pk_stock_b=eb.pk_stockps_b ");
    sql.append(" inner join po_settlebill sh on sh.pk_settlebill = sb.pk_settlebill ");
    sql.append("INNER JOIN  bd_material bd_material  ON  eb.pk_material = bd_material.pk_material ");
    if (this.containSupplier()) {
      sql.append("INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append("WHERE ");
    sql.append(" eh.pk_group", AppBsContext.getInstance().getPkGroup());
    sql.append("    AND eh.dr   = 0 ");
    sql.append("    AND eb.dr = 0 ");
    sql.append("    and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// 暂估成本或者确认了成本

    // wuxla 2013-4-24 影响成本的才查询出来
    sql.append("    and eb.baffectcost", UFBoolean.TRUE);

    sql.append("    AND sb.dr = 0 ");
    sql.append("    AND sh.dr = 0 ");
    sql.append("    and ( isnull(sh.btoia,'N') ='N' ");// 结算过但未传成本
    sql.append("          or isnull(sh.btoia,'N') ='Y' ");// 当前查询会计期间之后的数据
    // 语义模型点完成时可能为空。
    if (this.getQueryPara() != null) {
      sql.append("          and sh.dbilldate", ">", this.getQueryPara()
          .getEndDate().toString());
    }
    sql.append("         ) ");
    sql.append(" AND ");
    sql.append(whereSql);
    sql.append(" AND  ");
    sql.append(this.buildAddWhere());
    return sql.toString();
  }

  /**
   * 货物暂估-结算回冲剩余
   * 
   * @param whereSql
   * @return
   */
  private String getGoodEstSettleUnClashSql(String whereSql) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("SELECT ");
    sql.append("    eh.pk_org pk_storeorg, ");
    sql.append("    eh.pk_org_v pk_storeorg_v, ");
    sql.append("    eb.pk_financeorg pk_org, ");
    sql.append("    eb.pk_financeorg_v pk_org_v, ");
    sql.append("    eb.pk_purchaseorg pk_purchaseorg, ");
    sql.append("    eb.pk_purchaseorg_v pk_purchaseorg_v, ");
    sql.append("    bd_material.pk_marbasclass pk_marbasclass, ");
    sql.append("    bd_material.pk_measdoc cunitid, ");
    sql.append("    eb.pk_material pk_material, ");
    sql.append("    eb.pk_supplier pk_supplier, ");
    sql.append("    eh.pk_dept pk_dept, ");
    sql.append("    eh.pk_dept_v pk_dept_v, ");
    sql.append("    eh.pk_psndoc pk_psndoc, ");
    sql.append("    eh.pk_stordoc pk_stordoc, ");
    sql.append("    eh.cwhsmanagerid cwhsmanagerid, ");
    sql.append("    ( isnull(eb.nestnum,0) - isnull(eb.naccestcoststtlnum,0) ) unestnum, ");
    // sql.append("    ( isnull(eb.nestmny,0) - isnull(eb.naccestcostwashmny,0) ) unestmny, ");
    // v61 取计成本金额
    sql.append("    ( isnull(eb.nestcalcostmny,0) - isnull(eb.naccestcostwashmny,0) ) unestmny, ");
    sql.append("    eb.ccurrencyid ccurrencyid ");
    sql.append("FROM po_purchaseinfi eh ");
    sql.append("INNER JOIN po_purchaseinfi_b eb ON eh.pk_stockps = eb.pk_stockps ");
    sql.append("INNER JOIN bd_material bd_material  ON  eb.pk_material = bd_material.pk_material ");
    if (this.containSupplier()) {
      sql.append("INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append("WHERE eh.pk_group", AppBsContext.getInstance().getPkGroup());
    sql.append("  AND eh.dr   = 0 ");
    sql.append("  AND eb.dr = 0 ");
    sql.append("  and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// 暂估成本或者确认了成本

    // wuxla 2013-4-24 影响成本的才查询出来
    sql.append("    and eb.baffectcost", UFBoolean.TRUE);

    // 有未冲销金额
    sql.append(" and  isnull(eb.nestnum,0) - isnull(eb.naccestcoststtlnum,0) <> 0");
    sql.append(" AND ");
    sql.append(whereSql);
    sql.append(" AND  ");
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
