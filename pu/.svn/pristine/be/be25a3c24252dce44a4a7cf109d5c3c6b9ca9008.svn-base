package nc.impl.pu.report.estmothstat.sqlbuilder.settle;

import nc.bs.pubapp.AppBsContext;
import nc.impl.pu.report.estmothstat.sqlbuilder.AbstractEstMonthStatSqlBuilder;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.enumeration.InitialEstStatus;
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

public class M4TSettleSqlbuilder extends AbstractEstMonthStatSqlBuilder {

  /**
   * @param queryPara
   */
  public M4TSettleSqlbuilder(PuEstStatQryInfoPara queryPara) {
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
    // sql.append(" union all ");
    // // 费用结算
    // sql.append(this.getFeeSettleSql(whereSql));
    return sql.toString();

  }

  /**
   * @param whereSql
   * @return
   */
  private String getGoodSettleSql(String whereSql) {

    SqlBuilder sql = new SqlBuilder();
    sql.append(" SELECT ");
    sql.append("     eh.pk_stockorg pk_storeorg, ");
    sql.append("     eh.pk_stockorg_v pk_storeorg_v, ");
    sql.append("     eh.pk_org pk_org, ");
    sql.append("     eh.pk_org_v pk_org_v, ");
    sql.append("     eh.pk_purchaseorg pk_purchaseorg, ");
    sql.append("     eh.pk_purchaseorg_v pk_purchaseorg_v, ");
    sql.append("     bd_material.pk_marbasclass pk_marbasclass, ");
    sql.append("     bd_material.pk_measdoc cunitid, ");
    sql.append("     eb.pk_material pk_material, ");
    sql.append("     eh.pk_supplier pk_supplier, ");
    // sql.append("     bd_supplier.pk_areacl pk_areacl, ");
    sql.append("     eh.pk_dept pk_dept, ");
    sql.append("     eh.pk_dept_v pk_dept_v, ");
    sql.append("     eh.pk_bizpsn pk_psndoc, ");
    sql.append("     eh.pk_stordoc pk_stordoc, ");
    sql.append("     eh.pk_managepsn cwhsmanagerid, ");
    sql.append("     sb.nsettlenum nsettlenum, ");
    sql.append("     sb.ngoodsmoney ngoodsmoney, ");
    sql.append("     case when isnull(sh.btoia,'N')='Y'");
    sql.append("          then  sb.nclashestmoney ");
    sql.append("          else   0 ");
    sql.append("     end nclashestmoney, ");// 冲销暂估金额（需求确认，回冲数据只有传过存货时才统计）
    // sql.append("     sb.nclashestmoney nclashestmoney, ");
    sql.append("     eh.ccurrencyid ccurrencyid ");
    sql.append(" FROM po_settlebill sh ");
    sql.append(" INNER JOIN po_settlebill_b sb ON sh.pk_settlebill = sb.pk_settlebill and sb.dr=0 ");
    sql.append(" INNER JOIN po_initialest_b eb ON sb.pk_stock_b = eb.pk_initialest_b and eb.dr=0 ");
    sql.append(" INNER JOIN po_initialest eh ON eb.pk_initialest = eh.pk_initialest and eh.dr=0 ");
    sql.append(" INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    if (this.containSupplier()) {
      sql.append(" INNER JOIN bd_supplier bd_supplier ON eh.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append(" WHERE eh.pk_group  ", AppBsContext.getInstance().getPkGroup());
    sql.append("  AND sh.dr   = 0 ");
    // sql.append("  AND eb.dr = 0 ");
    sql.append("  AND isnull(sb.nclashestmoney,0) <> 0 ");

    // 查询审批态单据
    sql.append(" and eh." + InitialEstHeaderVO.FBILLSTATUS,
        InitialEstStatus.APPROVED.toInt());
    // wuxla
    sql.append("   AND isnull(sh.btoia,'N')='Y' ");// 需求确认，回冲数据只有传过存货时才统计
    sql.append("  and eb.baffectcost", UFBoolean.TRUE);

    sql.append(" AND ");
    sql.append(whereSql);
    sql.append(" AND  ");
    sql.append(this.buildAddWhere());
    return sql.toString();
  }

  protected String buildAddWhere() {
    SqlBuilder sql = new SqlBuilder();
    if (!StringUtils.isEmpty(this.getAddWhereSql())) {
      sql.append(this.getAddWhereSql().replace("DTOCOSTAPDATE", "eh.dbilldate")
          .replace("DSETTLEDATE", "sh.dbilldate")
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
    // 统计内容不包含期初暂估 - 枚举用4213替代，不用4T。这是因为查询模板枚举型默认值不支持字符型。
    if (!this.getQueryPara().getQueryTypes()
        .contains(POBillType.InitialEstSettleBill.getCode())) {
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
        "pk_financeorg", "eh.pk_org"
      }, {
        "pk_supplier", "eh.pk_supplier"
      }, {
        "pk_srcmaterial", "eb.pk_srcmaterial"
      }, {
        "suppliername", "bd_supplier.name"// 多语时？
      }, {
        "materialname", "bd_material.name"
      }, {
        "pk_storeorg", "eh.pk_stockorg"
      }, {
        "pk_group", "eh.pk_group"
      }, {
        "cwhsmanagerid", "eh.pk_managepsn"
      }, {
        "pk_stordoc", "eh.pk_stordoc"
      }, {
        "pk_psndoc", "eh.pk_bizpsn"
      }, {
        "pk_dept", "eh.pk_dept"
      }

    };
    for (int i = 0; i < matchFields.length; i++) {
      sql = sql.replace(matchFields[i][0], matchFields[i][1]);
    }
    return sql;
  }
}
