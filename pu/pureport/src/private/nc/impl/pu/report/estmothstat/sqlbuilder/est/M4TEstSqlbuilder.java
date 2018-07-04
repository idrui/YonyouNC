package nc.impl.pu.report.estmothstat.sqlbuilder.est;

import nc.bs.pubapp.AppBsContext;
import nc.impl.pu.report.estmothstat.sqlbuilder.settle.M4TSettleSqlbuilder;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.enumeration.InitialEstStatus;
import nc.vo.pu.report.queryinfo.eststat.PuEstStatQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.StringUtils;

/**
 * @since 6.0
 * @version 2011-8-22 下午03:08:31
 * @author 田锋涛
 */

public class M4TEstSqlbuilder extends M4TSettleSqlbuilder {

  /**
   * @param queryPara
   */
  public M4TEstSqlbuilder(PuEstStatQryInfoPara queryPara) {
    super(queryPara);
  }

  /**
   * 货物暂估-结算未传成本部分统计
   * 
   * @return
   */
  public String getGoodEstIAUnClashSql() {
    String whereSql = this.builderWhereSql();
    if (StringUtils.isEmpty(whereSql)) {
      return null;
    }
    SqlBuilder sql = new SqlBuilder();
    sql.append("SELECT ");
    sql.append("    eh.pk_stockorg pk_storeorg, ");
    sql.append("    eh.pk_stockorg_v pk_storeorg_v, ");
    sql.append("    eh.pk_org pk_org, ");
    sql.append("    eh.pk_org_v pk_org_v, ");
    sql.append("    eh.pk_purchaseorg pk_purchaseorg, ");
    sql.append("    eh.pk_purchaseorg_v pk_purchaseorg_v, ");
    sql.append("    bd_material.pk_marbasclass pk_marbasclass, ");
    sql.append("    bd_material.pk_measdoc cunitid, ");
    sql.append("    eb.pk_material pk_material, ");
    sql.append("    eh.pk_supplier pk_supplier, ");
    sql.append("    eh.pk_dept pk_dept, ");
    sql.append("    eh.pk_dept_v pk_dept_v, ");
    sql.append("    eh.pk_bizpsn pk_psndoc, ");
    sql.append("    eh.pk_stordoc pk_stordoc, ");
    sql.append("    eh.pk_managepsn cwhsmanagerid, ");
    sql.append("    sb.nsettlenum unestnum, ");
    sql.append("    sb.nclashestmoney unestmny, ");
    sql.append("    eh.ccurrencyid ccurrencyid ");
    sql.append("FROM  po_initialest eh ");
    sql.append("INNER JOIN po_initialest_b eb ON eh.pk_initialest = eb.pk_initialest ");
    sql.append("inner join po_settlebill_b sb on sb.pk_stock_b=eb.pk_initialest_b ");
    sql.append("inner join po_settlebill sh on sh.pk_settlebill = sb.pk_settlebill ");
    if (this.containSupplier()) {
      sql.append("INNER JOIN  bd_supplier bd_supplier  ON  eh.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append("INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    sql.append("WHERE eh.pk_group", AppBsContext.getInstance().getPkGroup());
    sql.append("  AND eh.dr   = 0 ");
    sql.append("  AND eb.dr = 0 ");
    sql.append("  AND sb.dr = 0 ");
    sql.append("  AND sh.dr = 0 ");

    // 查询审批态单据
    sql.append(" and eh." + InitialEstHeaderVO.FBILLSTATUS,
        InitialEstStatus.APPROVED.toInt());
    // wuxla 2013-4-24 影响成本的才查询出来
    sql.append("  and eb.baffectcost", UFBoolean.TRUE);

    sql.append("  and ( isnull(sh.btoia,'N') ='N' ");// 结算过但未传成本
    sql.append("        or isnull(sh.btoia,'N') ='Y' ");// 当前查询会计期间之后的数据
    if (this.getQueryPara() != null) {// 语义模型点完成时可能为空
      sql.append("        and sh.dbilldate", ">", this.getQueryPara()
          .getEndDate().toString());
    }
    sql.append("       ) ");
    sql.append("  AND ");
    sql.append(whereSql);
    sql.append("  AND  ");
    sql.append(this.buildAddWhere());
    return sql.toString();
  }

  /**
   * 货物暂估-尚未结算回冲统计
   * 
   * @return
   */
  public String getGoodEstSettleUnClashSql() {
    String whereSql = this.builderWhereSql();
    if (StringUtils.isEmpty(whereSql)) {
      return null;
    }
    SqlBuilder sql = new SqlBuilder();
    sql.append("SELECT ");
    sql.append("    eh.pk_stockorg pk_storeorg, ");
    sql.append("    eh.pk_stockorg_v pk_storeorg_v, ");
    sql.append("    eh.pk_org pk_org, ");
    sql.append("    eh.pk_org_v pk_org_v, ");
    sql.append("    eh.pk_purchaseorg pk_purchaseorg, ");
    sql.append("    eh.pk_purchaseorg_v pk_purchaseorg_v, ");
    sql.append("    bd_material.pk_marbasclass pk_marbasclass, ");
    sql.append("    bd_material.pk_measdoc cunitid, ");
    sql.append("    eb.pk_material pk_material, ");
    sql.append("    eh.pk_supplier pk_supplier, ");
    sql.append("    eh.pk_dept pk_dept, ");
    sql.append("    eh.pk_dept_v pk_dept_v, ");
    sql.append("    eh.pk_bizpsn pk_psndoc, ");
    sql.append("    eh.pk_stordoc pk_stordoc, ");
    sql.append("    eh.pk_managepsn cwhsmanagerid, ");
    sql.append("    ( isnull(eb.nnum,0) - isnull(eb.naccsettlenum,0) ) unestnum, ");
    sql.append("    ( isnull(eb.ncalcostmny,0) - isnull(eb.naccwashmny,0) ) unestmny, ");// v61计成本金额
    sql.append("    eh.ccurrencyid ccurrencyid ");
    sql.append("FROM  po_initialest eh ");
    if (this.containSupplier()) {
      sql.append("INNER JOIN  bd_supplier bd_supplier  ON  eh.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append("INNER JOIN po_initialest_b eb ON eh.pk_initialest = eb.pk_initialest ");
    sql.append("INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    sql.append("WHERE eh.pk_group", AppBsContext.getInstance().getPkGroup());
    sql.append("  AND eh.dr   = 0 ");
    sql.append("  AND eb.dr = 0 ");

    // 查询审批态单据
    sql.append(" and eh." + InitialEstHeaderVO.FBILLSTATUS,
        InitialEstStatus.APPROVED.toInt());
    // wuxla 2013-4-24 影响成本的才查询出来
    sql.append("  and eb.baffectcost", UFBoolean.TRUE);

    sql.append("  AND isnull(eb.ncalcostmny,0) - isnull(eb.naccwashmny,0) <>0");
    sql.append("  AND ");
    sql.append(whereSql);
    sql.append("  AND  ");
    sql.append(this.buildAddWhere());
    return sql.toString();
  }

  @Override
  public String getQuerySql() {
    String whereSql = this.builderWhereSql();
    if (StringUtils.isEmpty(whereSql)) {
      return null;
    }
    return this.getGoodEstSql();
  }

  /**
   * 货物暂估未冲销部分统计
   * 
   * @param whereSql
   * @return
   */
  private String getGoodEstSql() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(this.getGoodEstSettleUnClashSql());
    sql.append(" union all ");
    sql.append(this.getGoodEstIAUnClashSql());
    return sql.toString();
  }

}
