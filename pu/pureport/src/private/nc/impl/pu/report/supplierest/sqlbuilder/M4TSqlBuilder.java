package nc.impl.pu.report.supplierest.sqlbuilder;

import nc.impl.pu.report.supplierest.SupplierEstReportImpl;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.enumeration.InitialEstStatus;
import nc.vo.pu.report.queryinfo.supplierest.PuSupplierEstQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 期初暂估查询构造器
 * 
 * @since 6.0
 * @version 2011-5-18 下午03:28:55
 * @author 田锋涛
 */

public class M4TSqlBuilder extends AbstractSupplierEstSqlBuilder {

  public M4TSqlBuilder(PuSupplierEstQryInfoPara para) {
    super(para);
  }

  @Override
  public String getQuerySql() {
    StringBuilder sb = new StringBuilder();
    sb.append(SupplierEstReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getInitialSql());// 期初数量和期初余额
    sb.append(" ) M4TT1 union all ");
    sb.append(SupplierEstReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getCurrEstSql()); // 本期增加暂估数量、本期增加暂估金额
    sb.append(" ) M4TT2 union all ");
    sb.append(SupplierEstReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getCurrClashestAndSettleSql()); // 本期冲销暂估数量、本期冲销暂估金额
    sb.append(" ) M4TT3 ");

    return sb.toString();
  }

  /**
   * 本期冲销暂估数量、本期结算金额、本期冲销暂估金额
   * 
   * @return
   */
  private String getCurrClashestAndSettleSql() {
    // 本期冲销暂估数量、本期结算金额、本期冲销暂估金额
    SqlBuilder sb = new SqlBuilder();
    sb.append(this.getCurrClashGoodEstAndSettleSql());
    // sb.append(" union all ");
    // sb.append(this.getCurrFeeSettleSql());
    return sb.toString();

  }

  /**
   * 货物暂估冲销金额数量，货物结算金额
   * 
   * @return
   */
  private String getCurrClashGoodEstAndSettleSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(" SELECT eh.pk_supplier pk_supplier, ");
    sb.append("        0 unestnum, ");
    sb.append("        0 unestmny, ");
    sb.append("        0 estnum, ");
    sb.append("        0 nestmny, ");
    sb.append("        case when isnull(sh.btoia,'N')='Y'");
    sb.append("             then  sb.nsettlenum ");
    sb.append("             else   0 ");
    sb.append("        end nsettlenum, ");// 冲销暂估数量（需求确认，回冲数据只有传过存货时才统计）
    sb.append("        sb.ngoodsmoney ngoodsmoney, ");// 货物结算金额
    sb.append("        case when isnull(sh.btoia,'N')='Y'");
    sb.append("             then  sb.nclashestmoney ");
    sb.append("             else   0 ");
    sb.append("        end nclashestmoney ");// 冲销暂估金额（需求确认，回冲数据只有传过存货时才统计）
    sb.append("   FROM po_initialest eh ");
    sb.append("  INNER JOIN po_initialest_b eb ON eh.pk_initialest = eb.pk_initialest ");
    sb.append("  INNER JOIN po_settlebill_b sb ON sb.pk_stock_b = eb.pk_initialest_b ");
    sb.append("  INNER JOIN po_settlebill sh ON sh.pk_settlebill = sb.pk_settlebill ");
    // 查询物料分类时才会用到
    if (this.containMaterial()) {
      sb.append("  INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    }
    // 查询供应商分类时才会用到
    if (this.containSupplier()) {
      sb.append("  INNER JOIN bd_supplier bd_supplier ON eh.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append("  where eb.dr=0 ");
    sb.append("    and eh.dr=0 ");
    sb.append("    and sb.dr=0 ");
    sb.append("    and sh.dr=0 ");
    sb.append("    and isnull(sb.nclashestmoney,0)<>0 ");

    // 查询审批态单据
    sb.append(" and eh." + InitialEstHeaderVO.FBILLSTATUS,
        InitialEstStatus.APPROVED.toInt());
    // wuxla
    sb.append("   and eb.baffectcost", UFBoolean.TRUE);

    // 本期结算日期
    sb.append("    and sh.dbilldate", ">=", this.getPara().getBegindate()
        .toString());
    sb.append("    and sh.dbilldate", "<=", this.getPara().getEnddate()
        .toString());
    sb.append("       and ");
    sb.append(this.getGoodCommonWhere());
    return sb.toString();
  }

  private String getCurrEstSql() {
    // 本期增加暂估数量、本期增加暂估金额
    SqlBuilder sb = new SqlBuilder();
    sb.append(" SELECT eh.pk_supplier pk_supplier, ");
    // sb.append("        bd_supplier.name suppliername, ");
    sb.append("        0 unestnum, ");
    sb.append("        0 unestmny, ");
    sb.append("        isnull(eb.nnum,0) estnum, ");
    // sb.append("        isnull(eb.nmny,0) nestmny, ");
    sb.append("        isnull(eb.ncalcostmny,0) nestmny, ");// v61计成本金额
    sb.append("        0 nsettlenum, ");
    sb.append("        0 ngoodsmoney, ");
    sb.append("        0 nclashestmoney ");
    sb.append("  FROM po_initialest eh ");
    sb.append(" INNER JOIN po_initialest_b eb ON eh.pk_initialest = eb.pk_initialest ");
    // 查询物料分类时才会用到
    if (this.containMaterial()) {
      sb.append(" INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    }
    // 查询供应商分类时才会用到
    if (this.containSupplier()) {
      sb.append(" INNER JOIN bd_supplier bd_supplier ON eh.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append(" where eb.dr=0  ");
    sb.append("   and eh.dr=0 ");

    // 查询审批态单据
    sb.append(" and eh." + InitialEstHeaderVO.FBILLSTATUS,
        InitialEstStatus.APPROVED.toInt());
    // wuxla
    sb.append("   and eb.baffectcost", UFBoolean.TRUE);

    // 暂估日期介于查询日期
    sb.append("   and eh.dbilldate", ">=", this.getPara().getBegindate()
        .toString());
    sb.append("   and eh.dbilldate", "<=", this.getPara().getEnddate()
        .toString());
    sb.append("       and ");
    sb.append(this.getGoodCommonWhere());

    return sb.toString();
  }

  /**
   * 货物暂估-结算未传成本部分
   * 
   * @return
   */
  private String getInitialGoodEstIAUnClashSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(" SELECT eh.pk_supplier pk_supplier, ");
    sb.append("        sb.nsettlenum unestnum, ");
    sb.append("        sb.nclashestmoney unestmny, ");
    sb.append("        0 estnum, ");
    sb.append("        0 nestmny, ");
    sb.append("        0 nsettlenum, ");
    sb.append("        0 ngoodsmoney, ");
    sb.append("        0 nclashestmoney ");
    sb.append("  FROM po_initialest eh ");
    sb.append(" INNER JOIN po_initialest_b eb ON eh.pk_initialest = eb.pk_initialest ");
    sb.append(" inner join po_settlebill_b sb on sb.pk_stock_b=eb.pk_initialest_b ");
    sb.append(" inner join po_settlebill sh on sh.pk_settlebill = sb.pk_settlebill ");
    // 查询物料分类时才会用到
    if (this.containMaterial()) {
      sb.append(" INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    }
    // 查询供应商分类时才会用到
    if (this.containSupplier()) {
      sb.append(" INNER JOIN bd_supplier bd_supplier ON eh.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append(" where eb.dr=0  ");
    sb.append("   and eh.dr=0");
    sb.append("   AND sb.dr = 0 ");
    sb.append("   AND sh.dr = 0 ");

    // wuxla
    // 查询审批态单据
    sb.append(" and eh." + InitialEstHeaderVO.FBILLSTATUS,
        InitialEstStatus.APPROVED.toInt());
    sb.append("   and eb.baffectcost", UFBoolean.TRUE);

    sb.append("   and ( isnull(sh.btoia,'N') ='N' ");// 结算过但未传成本
    sb.append("         or  isnull(sh.btoia,'N') ='Y' ");// 查询日期之后的结算过的部分不能统计，要加进来
    sb.append("         and sh.dbilldate", ">=", this.getPara().getBegindate()
        .toString());
    sb.append("        ) ");
    // 暂估日期 < 开始日
    sb.append("   and eh.dbilldate", "<", this.getPara().getBegindate()
        .toString());
    sb.append("       and ");
    sb.append(this.getGoodCommonWhere());
    return sb.toString();
  }

  /**
   * 货物暂估-结算未冲销部分
   * 
   * @return
   */
  private String getInitialGoodEstSettleUnClashSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(" SELECT eh.pk_supplier pk_supplier, ");
    sb.append("        (isnull(eb.nnum,0)-isnull(eb.naccsettlenum,0)) unestnum, ");
    // sb.append("        (isnull(eb.nmny,0)-isnull(eb.naccwashmny,0)) unestmny, ");ncalcostmny
    sb.append("        (isnull(eb.ncalcostmny,0)-isnull(eb.naccwashmny,0)) unestmny, ");// v61计成本金额
    sb.append("        0 estnum, ");
    sb.append("        0 nestmny, ");
    sb.append("        0 nsettlenum, ");
    sb.append("        0 ngoodsmoney, ");
    sb.append("        0 nclashestmoney ");
    sb.append("  FROM po_initialest eh ");
    sb.append(" INNER JOIN po_initialest_b eb ON eh.pk_initialest = eb.pk_initialest ");
    // 查询物料分类时才会用到
    if (this.containMaterial()) {
      sb.append(" INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    }
    // 查询供应商分类时才会用到
    if (this.containSupplier()) {
      sb.append(" INNER JOIN bd_supplier bd_supplier ON eh.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append(" where eb.dr=0  ");
    sb.append("   and eh.dr=0");

    // 查询审批态单据
    sb.append(" and eh." + InitialEstHeaderVO.FBILLSTATUS,
        InitialEstStatus.APPROVED.toInt());

    // wuxla
    sb.append("  and eb.baffectcost", UFBoolean.TRUE);

    // 未全部冲销
    sb.append("   and (isnull(eb.ncalcostmny,0)-isnull(eb.naccwashmny,0)) <> 0");
    // 暂估日期 < 开始日
    sb.append("   and eh.dbilldate", "<", this.getPara().getBegindate()
        .toString());
    sb.append("       and ");
    sb.append(this.getGoodCommonWhere());
    return sb.toString();
  }

  /**
   * 期初货物暂估
   * 
   * @return
   */
  private String getInitialSql() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.getInitialGoodEstSettleUnClashSql());
    sb.append(" union all ");
    sb.append(this.getInitialGoodEstIAUnClashSql());
    return sb.toString();
  }

  @Override
  protected String getQryDlgWhereSql() {
    String sql = super.getQryDlgWhereSql();
    sql = sql.replace("eb.pk_org", "eh.pk_stockorg");// 替换库存组织
    sql = sql.replace("eb.pk_org_v", "eh.pk_stockorg_v");// 替换库存组织
    sql = sql.replace("eb.pk_financeorg", "eb.pk_org");// 替换财务组织
    sql = sql.replace("eb.pk_financeorg_v", "eb.pk_org_v");// 替换财务组织
    sql = sql.replace("eb.pk_purchaseorg", "eh.pk_purchaseorg");// 替换采购组织
    sql = sql.replace("eb.pk_purchaseorg_v", "eh.pk_purchaseorg_v");// 替换采购组织
    sql = sql.replace("eb.pk_supplier", "eh.pk_supplier");// 替换供应商
    sql = sql.replace("eh.pk_psndoc", "eh.pk_bizpsn");// 采购员
    return sql;
  }
}
