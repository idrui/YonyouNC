package nc.impl.pu.report.estmothstat.sqlbuilder.settle;

import nc.bs.pubapp.AppBsContext;
import nc.impl.pu.report.estmothstat.sqlbuilder.AbstractEstMonthStatSqlBuilder;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pu.report.queryinfo.eststat.PuEstStatQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.StringUtils;

/**
 * @since 6.0
 * @version 2011-8-22 下午04:48:47
 * @author 田锋涛
 */

public class M45SettleSqlbuilder extends AbstractEstMonthStatSqlBuilder {

  /**
   * @param queryPara
   */
  public M45SettleSqlbuilder(PuEstStatQryInfoPara queryPara) {
    super(queryPara);
  }

  @Override
  public String getQuerySql() {
    String whereSql = this.builderWhereSql();
    if (StringUtils.isEmpty(whereSql)) {
      return null;
    }
    SqlBuilder sql = new SqlBuilder();
    // 货物结算
    sql.append(this.getGoodSettleSql(whereSql));
    sql.append(" union all ");
    // 费用结算
    sql.append(this.getFeeSettleSql(whereSql));
    return sql.toString();
  }

  /**
   * @param whereSql
   * @return
   */
  private String getFeeSettleSql(String whereSql) {
    SqlBuilder sb = new SqlBuilder();
    // 费用回冲
    sb.append(this.getFeeSettleSqlForClash(whereSql));
    sb.append(" union all ");
    // 费用结算
    sb.append(this.getFeeSettleSqlForSettle(whereSql));
    return sb.toString();
  }

  /**
   * 费用结算sql
   * 
   * @param whereSql
   * @return
   */
  private String getFeeSettleSqlForClash(String whereSql) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" SELECT ");
    sql.append("     eh.pk_org pk_storeorg, ");
    sql.append("     eh.pk_org_v pk_storeorg_v, ");
    sql.append("     sh.pk_org pk_org, ");
    sql.append("     sb.pk_org_v pk_org_v, ");
    sql.append("     eb.pk_purchaseorg pk_purchaseorg, ");
    sql.append("     eb.pk_purchaseorg_v pk_purchaseorg_v, ");
    sql.append("     bd_material.pk_marbasclass pk_marbasclass, ");
    sql.append("     bd_material.pk_measdoc cunitid, ");
    sql.append("     eb.pk_material pk_material, ");// 货物
    sql.append("     ef.pk_supplier pk_supplier, ");// 费用供应商
    // sql.append("     bd_supplier.pk_areacl pk_areacl, ");
    sql.append("     eh.pk_dept pk_dept, ");
    sql.append("     eh.pk_dept_v pk_dept_v, ");
    sql.append("     eh.pk_psndoc pk_psndoc, ");
    sql.append("     eh.pk_stordoc pk_stordoc, ");
    sql.append("     eh.cwhsmanagerid cwhsmanagerid, ");
    sql.append("     0 nsettlenum, ");// 费用结算数量
    sql.append("     0 ngoodsmoney, ");// 费用结算金额即分摊的金额
    // sql.append("     ef.nestmny nclashestmoney, ");//
    // 如果是费用暂估则结算的金额即是冲销的金额（费用全部冲销）
    sql.append("     ef.ncalcostmny nclashestmoney, ");// 如果是费用暂估则结算的金额即是冲销的金额（费用全部冲销）v61=>ncalcostmny
    sql.append("     eb.ccurrencyid ccurrencyid ");
    sql.append("  FROM po_settlebill sh ");
    sql.append(" INNER JOIN po_settlebill_b sb ON sh.pk_settlebill = sb.pk_settlebill ");
    sql.append(" INNER JOIN po_purchaseinfi_fee ef ON ef.pk_firstsettle_b = sb.pk_settlebill_b ");
    sql.append(" INNER JOIN po_purchaseinfi_b eb ON ef.pk_stockps_b = eb.pk_stockps_b ");
    sql.append(" INNER JOIN po_purchaseinfi eh ON eb.pk_stockps = eh.pk_stockps ");
    sql.append(" INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    if (this.containSupplier()) {
      sql.append(" INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append(" WHERE sh.pk_group ", AppBsContext.getInstance().getPkGroup());
    sql.append("   AND sh.dr   = 0 ");
    sql.append("   AND isnull(sh.btoia,'N')='Y'");// 需求确认，回冲数据只有传过存货时才统计
    sql.append("   AND sb.dr = 0 ");
    sql.append("   AND eb.dr = 0 ");
    sql.append("   AND eh.dr = 0 ");
    sql.append("   AND ef.dr = 0 ");
    sql.append("   AND ");
    // 替换为费用的供应商
    sql.append(whereSql.replace("eb.pk_supplier", "ef.pk_supplier"));
    // .replace(
    // "eb.pk_srcmaterial",
    // "ef.pk_feesrcmaterial")
    sql.appendIDIsNotNull(" and ef.pk_firstsettle");// 只统计暂估的数据
    sql.append("   AND ");
    sql.append(this.buildAddWhere().replace("DTOCOSTAPDATE", "ef.destdate"));

    return sql.toString();
  }

  private String getFeeSettleSqlForSettle(String whereSql) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" SELECT ");
    sql.append("     eh.pk_org pk_storeorg, ");
    sql.append("     eh.pk_org_v pk_storeorg_v, ");
    sql.append("     sh.pk_org pk_org, ");
    sql.append("     sb.pk_org_v pk_org_v, ");
    sql.append("     eb.pk_purchaseorg pk_purchaseorg, ");
    sql.append("     eb.pk_purchaseorg_v pk_purchaseorg_v, ");
    sql.append("     bd_material.pk_marbasclass pk_marbasclass, ");
    sql.append("     bd_material.pk_measdoc cunitid, ");
    sql.append("     eb.pk_material pk_material, ");// 物料
    sql.append("     ef.pk_supplier pk_supplier, ");// 费用供应商
    // sql.append("     bd_supplier.pk_areacl pk_areacl, ");
    sql.append("     eh.pk_dept pk_dept, ");
    sql.append("     eh.pk_dept_v pk_dept_v, ");
    sql.append("     eh.pk_psndoc pk_psndoc, ");
    sql.append("     eh.pk_stordoc pk_stordoc, ");
    sql.append("     eh.cwhsmanagerid cwhsmanagerid, ");
    sql.append("     0 nsettlenum, ");// 费用结算数量
    sql.append("     fe.nallotmoney ngoodsmoney, ");// 费用结算金额即分摊的金额
    sql.append("     0 nclashestmoney, ");// 如果是费用暂估则结算的金额即是冲销的金额（费用全部冲销）
    sql.append("     eb.ccurrencyid ccurrencyid ");
    sql.append("  FROM po_settlebill sh ");
    sql.append(" INNER JOIN po_settlebill_b sb ON sh.pk_settlebill = sb.pk_settlebill ");
    sql.append(" INNER JOIN po_settle_feeallot fe ON fe.pk_settlebill_b = sb.pk_settlebill_b ");
    sql.append(" INNER JOIN po_purchaseinfi_b eb ON sb.pk_stock_b = eb.pk_stockps_b ");
    sql.append(" INNER JOIN po_purchaseinfi_fee ef ON ef.pk_stockps_b = eb.pk_stockps_b ");
    sql.append("            and ef.pk_srcfeematerial=fe.pk_srcmaterial ");
    sql.append(" INNER JOIN po_purchaseinfi eh ON eb.pk_stockps = eh.pk_stockps ");
    sql.append(" INNER JOIN bd_material bd_material ON sb.pk_material = bd_material.pk_material ");
    if (this.containSupplier()) {
      sql.append(" INNER JOIN bd_supplier bd_supplier ON fe.pk_supplier = bd_supplier.pk_supplier ");
    }

    sql.append(" WHERE sh.pk_group ", AppBsContext.getInstance().getPkGroup());
    sql.append("   AND sh.dr   = 0 ");
    sql.append("   AND isnull(ef.btoia,'N')   = 'Y' ");
    sql.append("   AND sb.dr = 0 ");
    sql.append("   AND fe.dr = 0 ");
    sql.append("   AND eb.dr = 0 ");
    sql.append("   AND eh.dr = 0 ");
    sql.append("   AND ef.dr   = 0 ");
    sql.append("   AND ");
    // 替换为费用的供应商
    sql.append(whereSql.replace("eb.pk_supplier", "ef.pk_supplier"));
    // .replace("eb.pk_srcmaterial",
    // "fe.pk_srcmaterial")
    sql.appendIDIsNotNull(" and ef.pk_firstsettle");// 只统计暂估的数据
    sql.append("   AND ");
    sql.append(this.buildAddWhere().replace("DTOCOSTAPDATE", "ef.destdate"));

    return sql.toString();
  }

  /**
   * 货物结算
   * 
   * @param whereSql
   * @return
   */
  private String getGoodSettleSql(String whereSql) {

    SqlBuilder sql = new SqlBuilder();
    sql.append(" SELECT ");
    sql.append("     eh.pk_org pk_storeorg, ");
    sql.append("     eh.pk_org_v pk_storeorg_v, ");
    sql.append("     sh.pk_org pk_org, ");
    sql.append("     sb.pk_org_v pk_org_v, ");
    sql.append("     eb.pk_purchaseorg pk_purchaseorg, ");
    sql.append("     eb.pk_purchaseorg_v pk_purchaseorg_v, ");
    sql.append("     bd_material.pk_marbasclass pk_marbasclass, ");
    sql.append("     bd_material.pk_measdoc cunitid, ");
    sql.append("     eb.pk_material pk_material, ");
    sql.append("     eb.pk_supplier pk_supplier, ");
    // sql.append("     bd_supplier.pk_areacl pk_areacl, ");
    sql.append("     eh.pk_dept pk_dept, ");
    sql.append("     eh.pk_dept_v pk_dept_v, ");
    sql.append("     eh.pk_psndoc pk_psndoc, ");
    sql.append("     eh.pk_stordoc pk_stordoc, ");
    sql.append("     eh.cwhsmanagerid cwhsmanagerid, ");
    sql.append("     sb.nsettlenum nsettlenum, ");
    sql.append("     sb.ngoodsmoney ngoodsmoney, ");
    sql.append("     case when isnull(sh.btoia,'N')='Y'");
    sql.append("          then  sb.nclashestmoney ");
    sql.append("          else   0 ");
    sql.append("     end nclashestmoney, ");// 冲销暂估金额（需求确认，回冲数据只有传过存货时才统计）
    // sql.append("     sb.nclashestmoney nclashestmoney, ");
    sql.append("     eb.ccurrencyid ccurrencyid ");
    sql.append("  FROM po_settlebill sh ");
    sql.append(" INNER JOIN po_settlebill_b sb ON sh.pk_settlebill = sb.pk_settlebill ");
    sql.append(" INNER JOIN po_purchaseinfi_b eb ON sb.pk_stock_b = eb.pk_stockps_b ");
    sql.append(" INNER JOIN po_purchaseinfi eh ON eb.pk_stockps = eh.pk_stockps ");
    sql.append(" INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    sql.append(" INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier ");
    sql.append(" WHERE sh.pk_group ", AppBsContext.getInstance().getPkGroup());
    sql.append("   AND sh.dr   = 0 ");
    sql.append("   AND sb.dr = 0 ");
    sql.append("   AND eb.dr = 0 ");
    sql.append("   AND eh.dr = 0 ");
    sql.append("   AND isnull(sb.nclashestmoney,0) <> 0 ");

    // wuxla 2013-4-23 影响成本的才查询出来
    sql.append("    and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// 暂估成本或者确认了成本
    sql.append("    and eb.baffectcost", UFBoolean.TRUE);

    sql.append("   AND ");
    sql.append(whereSql);
    sql.append("   AND ");
    sql.append(this.buildAddWhere());

    return sql.toString();
  }

  protected String buildAddWhere() {
    SqlBuilder sql = new SqlBuilder();
    if (!StringUtils.isEmpty(this.getAddWhereSql())) {
      sql.append(this.getAddWhereSql().replace("DSETTLEDATE", "sh.dbilldate")
          .replace("DBILLDATE", "eh.dbilldate"));
    }
    else {
      sql.append(" 1=1 ");
    }
    return sql.toString();
  }

  protected String builderWhereSql() {
    if (this.getQueryPara() == null) {
      return " 1=1 ";// 预览时不抛错
    }
    // 统计内容不包含采购入库
    if (!this.getQueryPara().getQueryTypes()
        .contains(POBillType.PurchaseInFinance.getCode())) {
      return null;
    }
    String dlgWhere = this.getQueryPara().getDlgWherePart();
    return this.replaceWhereSql(dlgWhere);
  }

  /**
   * @param dlgWhere
   * @return
   */
  protected String replaceWhereSql(String dlgWhere) {
    String sql = dlgWhere;

    String[][] matchFields = new String[][] {
      {
        "pk_financeorg", "eb.pk_financeorg"
      }, {
        "pk_supplier", "eb.pk_supplier"
      }, {
        "pk_srcmaterial", "eb.pk_srcmaterial"
      }, {
        "pk_storeorg", "eb.pk_org"
      }, {
        "suppliername", "bd_supplier.name"// 多语时？
      }, {
        "materialname", "bd_material.name"
      }, {
        "pk_group", "eh.pk_group"
      }, {
        "pk_dept", "eh.pk_dept"
      }, {
        "pk_psndoc", "eh.pk_psndoc"
      }, {
        "pk_stordoc", "eh.pk_stordoc"
      }

    };
    for (int i = 0; i < matchFields.length; i++) {
      sql = sql.replace(matchFields[i][0], matchFields[i][1]);
    }
    return sql;
  }
}
