package nc.impl.pu.report.supplierest.sqlbuilder;

import nc.impl.pu.report.supplierest.SupplierEstReportImpl;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pu.report.queryinfo.supplierest.PuSupplierEstQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 委外加工单
 * 
 * @since 6.0
 * @version 2011-3-22 下午02:29:13
 * @author yinfy
 */

public class M4203SqlBuilder extends AbstractSupplierEstSqlBuilder {

  /**
   * @param para
   */
  public M4203SqlBuilder(PuSupplierEstQryInfoPara para) {
    super(para);
  }

  @Override
  public String getQuerySql() {
    StringBuilder sb = new StringBuilder();
    sb.append(SupplierEstReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getInitialSql());
    sb.append(" ) M4203T1 union all ");
    sb.append(SupplierEstReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getCurrEstSql());
    sb.append(" )  M4203T2 union all ");
    sb.append(SupplierEstReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getCurrClashestSql());
    sb.append(" )  M4203T3 ");
    return sb.toString();
  }

  /**
   * 本期货物冲销金额数量以及结算金额
   */
  private String getCurrClashGoodEstSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append("  SELECT ");
    sb.append("        eb.pk_supplier pk_supplier, ");
    sb.append("        0 unestnum, ");
    sb.append("        0 unestmny, ");
    sb.append("        0 estnum, ");
    sb.append("        0 nestmny, ");
    sb.append("        case when isnull(sh.btoia,'N')='Y'");
    sb.append("             then  sb.nsettlenum ");
    sb.append("             else   0 ");
    sb.append("        end nsettlenum, ");// 冲销暂估数量（需求确认，回冲数据只有传过存货时才统计）
    sb.append("        sb.ngoodsmoney ngoodsmoney, ");
    sb.append("        case when isnull(sh.btoia,'N')='Y'");
    sb.append("             then  sb.nclashestmoney ");
    sb.append("             else   0 ");
    sb.append("        end nclashestmoney ");// 冲销暂估金额（需求确认，回冲数据只有传过存货时才统计）
    sb.append("  FROM po_settlebill sh ");
    sb.append(" INNER JOIN po_settlebill_b sb ON sh.pk_settlebill = sb.pk_settlebill ");
    sb.append(" INNER JOIN po_subcontinfi_b eb ON sb.pk_stock_b = eb.pk_stockps_b ");
    sb.append(" INNER JOIN po_subcontinfi eh ON eb.pk_stockps = eh.pk_stockps ");
    // 查询物料分类时才会用到
    if (this.containMaterial()) {
      sb.append(" INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    }
    // 查询供应商分类时才会用到
    if (this.containSupplier()) {
      sb.append(" INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append(" where eb.dr=0 ");
    sb.append("   and eh.dr=0 ");
    sb.append("   and sh.dr=0 ");
    sb.append("   and sb.dr=0 ");
    sb.append("   AND isnull(sb.bwashest,'N') = 'Y' ");

    // wuxla
    sb.append("    and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// 暂估成本或者确认了成本
    sb.append("   and eb.baffectcost", UFBoolean.TRUE);

    // 本期结算日期范围
    sb.append("   and sh.dbilldate", ">=", this.getPara().getBegindate()
        .toString());
    sb.append("   and sh.dbilldate", "<=", this.getPara().getEnddate()
        .toString());
    sb.append("       and ");
    sb.append(this.getGoodCommonWhere());
    return sb.toString();
  }

  /**
   * 货物暂估-结算未传成本统计
   * 
   * @return
   */
  private String getInitialGoodEstIAUnClashSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append("    SELECT ");
    sb.append("           eb.pk_supplier pk_supplier, ");
    sb.append("           sb.nsettlenum unestnum, ");
    sb.append("           sb.nclashestmoney unestmny, ");
    sb.append("           0 estnum, ");
    sb.append("           0 nestmny, ");
    sb.append("           0 nsettlenum, ");
    sb.append("           0 ngoodsmoney, ");
    sb.append("           0 nclashestmoney ");
    sb.append("      FROM po_subcontinfi eh  ");
    sb.append("     INNER JOIN po_subcontinfi_b eb ON eh.pk_stockps = eb.pk_stockps  ");
    sb.append("     inner join po_settlebill_b sb on sb.pk_stock_b=eb.pk_stockps_b ");
    sb.append("     inner join po_settlebill sh on sh.pk_settlebill = sb.pk_settlebill ");
    // 查询物料分类时才会用到
    if (this.containMaterial()) {
      sb.append("   INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material  ");
    }
    // 查询供应商分类时才会用到
    if (this.containSupplier()) {
      sb.append("   INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier  ");
    }
    sb.append("     WHERE eh.dr=0 ");
    sb.append("       and eb.dr=0 ");
    sb.append("       AND sb.dr = 0 ");
    sb.append("       AND sh.dr = 0 ");

    // wuxla 2013-4-24 影响成本的才查询出来
    sb.append("    and eb.baffectcost", UFBoolean.TRUE);

    sb.append("       and ( isnull(sh.btoia,'N') ='N' ");// 结算过但未传成本
    sb.append("             or  isnull(sh.btoia,'N') ='Y' ");// 查询日期之后的结算过的部分不能统计，要加进来
    sb.append("             and sh.dbilldate", ">=", this.getPara()
        .getBegindate().toString());
    sb.append("            ) ");
    // 期初日期范围
    sb.append("       and eb.dtocostapdate<='"
        + this.getPara().getBegindate().toString() + "'");
    sb.append("       and ");
    sb.append(this.getGoodCommonWhere());

    return sb.toString();
  }

  /**
   * 货物暂估-结算未冲销部分统计
   * 
   * @return
   */
  private String getInitialGoodEstSettleUnClashSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append("    SELECT ");
    sb.append("           eb.pk_supplier pk_supplier, ");
    sb.append("           (isnull(eb.ninnum,0)-isnull(eb.naccumsettlenum,0)) unestnum, ");
    sb.append("           (isnull(eb.ncostmny,0)-isnull(eb.naccestcostwashmny,0)) unestmny,  ");
    sb.append("           0 estnum, ");
    sb.append("           0 nestmny, ");
    sb.append("           0 nsettlenum, ");
    sb.append("           0 ngoodsmoney, ");
    sb.append("           0 nclashestmoney ");
    sb.append("      FROM po_subcontinfi eh  ");
    sb.append("     INNER JOIN po_subcontinfi_b eb ON eh.pk_stockps = eb.pk_stockps  ");
    // 查询物料分类时才会用到
    if (this.containMaterial()) {
      sb.append("     INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material  ");
    }
    // 查询供应商分类时才会用到
    if (this.containSupplier()) {
      sb.append("     INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier  ");
    }
    sb.append("     WHERE eh.dr=0 ");
    sb.append("       and eb.dr=0 ");
    sb.append("       and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// 暂估成本或者确认了成本

    // wuxla 影响成本的才统计
    sb.append("       and eb.baffectcost", UFBoolean.TRUE);

    // 期初日期范围
    sb.append("       and eb.dtocostapdate<='"
        + this.getPara().getBegindate().toString() + "'");
    sb.append("       and ");
    sb.append(this.getGoodCommonWhere());

    return sb.toString();
  }

  /**
   * 货物暂估部分统计
   * 
   * @return
   */
  private String getInitialGoodEstSql() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.getInitialGoodEstSettleUnClashSql());
    sb.append(" union all ");
    sb.append(this.getInitialGoodEstIAUnClashSql());
    return sb.toString();
  }

  /**
   * 本期冲销,对于委托加工入库，没有费用暂估，所以冲销的都是货物，
   * 但是结算金额要区分货物和费用，可能供应商是不一样的
   * 
   * @return
   */
  protected String getCurrClashestSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(this.getCurrClashGoodEstSql());
    // sb.append(" union all ");
    // sb.append(this.getCurrFeeSettleSql());
    return sb.toString();

  }

  /**
   * 本期暂估增加
   * 
   * @return
   */
  protected String getCurrEstSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(" SELECT eb.pk_supplier pk_supplier, ");
    // sb.append("        bd_supplier.name suppliername, ");
    sb.append("        0 unestnum, ");
    sb.append("        0 unestmny, ");
    sb.append("        eb.ninnum estnum, ");
    sb.append("        eb.ncostmny nestmny, ");
    sb.append("        0 nsettlenum, ");
    sb.append("        0 ngoodsmoney, ");
    sb.append("        0 nclashestmoney ");
    sb.append("   FROM po_subcontinfi eh ");
    sb.append("  INNER JOIN po_subcontinfi_b eb ON eh.pk_stockps = eb.pk_stockps ");
    // 查询物料分类时才会用到
    if (this.containMaterial()) {
      sb.append("  INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    }
    // 查询供应商分类时才会用到
    if (this.containSupplier()) {
      sb.append("  INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append("  WHERE eh.dr=0 ");
    sb.append("    and eb.dr=0 ");
    sb.append("    and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// 暂估成本或者确认了成本

    // wuxla
    sb.append("    and eb.baffectcost", UFBoolean.TRUE);

    // 本期暂估日期范围
    sb.append("    and eb.dtocostapdate", ">=", this.getPara().getBegindate()
        .toString());
    sb.append("    and eb.dtocostapdate", "<=", this.getPara().getEnddate()
        .toString());
    sb.append("       and ");
    sb.append(this.getGoodCommonWhere());
    return sb.toString();
  }

  /**
   * 期初数据
   * 
   * @return
   */
  protected String getInitialSql() {
    return this.getInitialGoodEstSql();
  }
}
