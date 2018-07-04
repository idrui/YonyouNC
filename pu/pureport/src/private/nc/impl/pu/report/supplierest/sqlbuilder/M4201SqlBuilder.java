package nc.impl.pu.report.supplierest.sqlbuilder;

import nc.impl.pu.report.supplierest.SupplierEstReportImpl;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pu.report.queryinfo.supplierest.PuSupplierEstQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 采购入库单
 * 
 * @since 6.0
 * @version 2011-3-22 下午12:07:26
 * @author yinfy
 */

public class M4201SqlBuilder extends AbstractSupplierEstSqlBuilder {

  public M4201SqlBuilder(PuSupplierEstQryInfoPara para) {
    super(para);
  }

  @Override
  public String getQuerySql() {
    StringBuilder sb = new StringBuilder();
    sb.append(SupplierEstReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getInitialSql());
    sb.append(" ) M4201T1 union all ");
    sb.append(SupplierEstReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getCurrEstSql());
    sb.append(" )  M4201T2 union all ");
    sb.append(SupplierEstReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getCurrClashestSql());
    sb.append(" )  M4201T3 ");
    return sb.toString();
  }

  /**
   * 本期货物暂估冲销及货物结算
   * 
   * @return
   */
  private String getCurrGoodClashAndSettleSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append("    SELECT ");
    sb.append("           eb.pk_supplier pk_supplier, ");
    sb.append("           0 unestnum, ");
    sb.append("           0 unestmny,  ");
    sb.append("           0 estnum, ");
    sb.append("           0 nestmny, ");
    sb.append("           case when isnull(sh.btoia,'N')='Y'");
    sb.append("             then  sb.nsettlenum ");
    sb.append("             else   0 ");
    sb.append("           end nsettlenum, ");// 冲销暂估数量（需求确认，回冲数据只有传过存货时才统计）
    sb.append("           sb.ngoodsmoney ngoodsmoney, ");// 货物结算金额
    sb.append("           case when isnull(sh.btoia,'N')='Y'");
    sb.append("             then  sb.nclashestmoney ");
    sb.append("             else   0 ");
    sb.append("           end nclashestmoney ");// 冲销暂估金额（需求确认，回冲数据只有传过存货时才统计）
    sb.append("      FROM po_purchaseinfi eh  ");
    sb.append("     INNER JOIN po_purchaseinfi_b eb ON eh.pk_stockps = eb.pk_stockps  ");
    sb.append("     INNER JOIN po_settlebill_b sb ON sb.pk_stock_b = eb.pk_stockps_b  ");
    sb.append("     INNER JOIN po_settlebill sh ON sh.pk_settlebill = sb.pk_settlebill  ");
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
    sb.append("       and sb.dr=0 ");
    sb.append("       and sh.dr=0 ");
    sb.append("       and isnull(sb.nclashestmoney,0) <> 0 ");// 冲销金额

    // wuxla 2013-4-23 影响成本的才查询出来
    sb.append("       and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// 暂估成本或者确认了成本
    sb.append("       and eb.baffectcost", UFBoolean.TRUE);

    sb.append("       and sh.dbilldate", ">=", this.getPara().getBegindate()
        .toString());
    sb.append("       and sh.dbilldate", "<=", this.getPara().getEnddate()
        .toString());
    sb.append("       and ");
    sb.append(this.getGoodCommonWhere());

    return sb.toString();
  }

  /**
   * @return
   */
  private String getCurrGoodEstSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append("    SELECT ");
    sb.append("           eb.pk_supplier pk_supplier, ");
    // sb.append("           bd_supplier.name suppliername, ");
    sb.append("           0 unestnum, ");
    sb.append("           0 unestmny,  ");
    sb.append("           eb.nestnum estnum, ");
    // sb.append("           eb.nestmny nestmny, ");
    sb.append("           eb.nestcalcostmny nestmny, ");// v61=计成本金额
    sb.append("           0 nsettlenum, ");
    sb.append("           0 ngoodsmoney, ");
    sb.append("           0 nclashestmoney ");
    sb.append("      FROM po_purchaseinfi eh  ");
    sb.append("     INNER JOIN po_purchaseinfi_b eb ON eh.pk_stockps = eb.pk_stockps  ");
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

    // wuxla
    sb.append("       and eb.baffectcost", UFBoolean.TRUE);

    sb.append("       and isnull(eb.nestcalcostmny,0) <> 0");// 数据
    // 本期暂估日期
    sb.append("       and eb.dtocostapdate", ">=", this.getPara()
        .getBegindate().toString());
    sb.append("       and eb.dtocostapdate", "<=", this.getPara().getEnddate()
        .toString());
    sb.append("       and ");
    sb.append(this.getGoodCommonWhere());

    return sb.toString();
  }

  /**
   * 货物暂估-结算但未传成本部分
   * 
   * @return
   */
  private String getInitialGoodEstIAUnClashSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(" SELECT ");
    sb.append("      eb.pk_supplier pk_supplier, ");
    sb.append("      sb.nsettlenum unestnum, ");
    sb.append("      sb.nclashestmoney unestmny, ");
    sb.append("      0 estnum, ");
    sb.append("      0 nestmny, ");
    sb.append("      0 nsettlenum, ");
    sb.append("      0 ngoodsmoney, ");
    sb.append("      0 nclashestmoney ");
    sb.append("  FROM po_purchaseinfi eh  ");
    sb.append(" INNER JOIN po_purchaseinfi_b eb ON eh.pk_stockps = eb.pk_stockps  ");
    sb.append(" inner join po_settlebill_b sb on sb.pk_stock_b=eb.pk_stockps_b ");
    sb.append(" inner join po_settlebill sh on sh.pk_settlebill = sb.pk_settlebill ");
    // 查询物料分类时才会用到
    if (this.containMaterial()) {
      sb.append("  INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material  ");
    }
    // 查询供应商分类时才会用到
    if (this.containSupplier()) {
      sb.append("  INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier  ");
    }
    sb.append(" WHERE eh.dr=0 ");
    sb.append("   and eb.dr=0 ");
    sb.append("   and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// 暂估成本或者确认了成本

    // wuxla 2013-4-24 影响成本的才查询出来
    sb.append("    and eb.baffectcost", UFBoolean.TRUE);

    sb.append("   AND sb.dr = 0 ");
    sb.append("   AND sh.dr = 0 ");
    sb.append("   and ( isnull(sh.btoia,'N') ='N' ");// 结算过但未传成本
    sb.append("         or  isnull(sh.btoia,'N') ='Y' ");// 查询日期之后的结算过的部分不能统计，要加进来
    sb.append("         and sh.dbilldate", ">=", this.getPara().getBegindate()
        .toString());
    sb.append("        ) ");
    sb.append("   and eb.dtocostapdate is not null ");
    // 期初日期
    sb.append("   and eb.dtocostapdate<='"
        + this.getPara().getBegindate().toString() + "'");
    sb.append("   and ");
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
    sb.append("    SELECT ");
    sb.append("           eb.pk_supplier pk_supplier, ");
    sb.append("           (isnull(eb.nestnum,0)-isnull(eb.naccestcoststtlnum,0)) unestnum, ");
    // sb.append("           (isnull(eb.nestmny,0)-isnull(eb.naccestcostwashmny,0)) unestmny,  ");
    sb.append("           (isnull(eb.nestcalcostmny,0)-isnull(eb.naccestcostwashmny,0)) unestmny,  ");// v61计成本金额
    sb.append("           0 estnum, ");
    sb.append("           0 nestmny, ");
    sb.append("           0 nsettlenum, ");
    sb.append("           0 ngoodsmoney, ");
    sb.append("           0 nclashestmoney ");
    sb.append("      FROM po_purchaseinfi eh  ");
    sb.append("     INNER JOIN po_purchaseinfi_b eb ON eh.pk_stockps = eb.pk_stockps  ");
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

    sb.append("       and (isnull(eb.nestcalcostmny,0)-isnull(eb.naccestcostwashmny,0)) <> 0");// 有未冲销的数据
    sb.append("       and  eb.dtocostapdate is not null ");
    // 期初日期
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

  protected String getCurrClashestSql() {
    StringBuilder sb = new StringBuilder();
    // 货物冲销及结算
    sb.append(this.getCurrGoodClashAndSettleSql());
    sb.append(" union all ");
    // 费用冲销及结算
    sb.append(this.getCurrFeeClashAndSettleSql());
    return sb.toString();
  }

  protected String getCurrEstSql() {
    StringBuilder sb = new StringBuilder();
    // 货物暂估增加
    sb.append(this.getCurrGoodEstSql());
    sb.append(" union all ");
    // 费用暂估增加
    sb.append(this.getCurrFeeEstSql());
    return sb.toString();
  }

  /**
   * 费用暂估冲销及费用结算
   * 
   * @return
   */
  protected String getCurrFeeClashAndSettleSql() {
    SqlBuilder sb = new SqlBuilder();
    // 费用暂估冲销
    sb.append(this.getCurrFeeClashSql());
    sb.append(" union all ");
    // 费用结算
    sb.append(this.getCurrFeeSettleSql());
    return sb.toString();
  }

  /**
   * 本期费用冲销暂估金额
   * 
   * @return
   */
  protected String getCurrFeeClashSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append("    SELECT ");
    sb.append("           ef.pk_supplier pk_supplier, ");
    sb.append("           0 unestnum, ");
    sb.append("           0 unestmny,  ");
    sb.append("           0 estnum, ");
    sb.append("           0 nestmny, ");
    sb.append("           0 nsettlenum, ");
    sb.append("           0 ngoodsmoney, ");//
    // sb.append("           ef.nestmny nclashestmoney ");// 冲销暂估金额
    sb.append("           ef.ncalcostmny nclashestmoney ");// 冲销暂估金额 v61计成本金额
    sb.append("      FROM po_purchaseinfi eh  ");
    sb.append("     INNER JOIN po_purchaseinfi_b eb ON eh.pk_stockps = eb.pk_stockps  ");
    sb.append("     INNER JOIN po_purchaseinfi_fee ef ON ef.pk_stockps_b=eb.pk_stockps_b ");
    sb.append("     INNER JOIN po_settlebill_b sb ON sb.pk_settlebill_b=ef.pk_firstsettle_b ");// 费用发票结算行
    sb.append("     INNER JOIN po_settlebill sh ON sh.pk_settlebill = sb.pk_settlebill  ");
    // 查询物料分类时才会用到
    if (this.containMaterial()) {
      sb.append("     INNER JOIN bd_material bd_material ON ef.pk_feematerial = bd_material.pk_material  ");
    }
    // 查询供应商分类时才会用到
    if (this.containSupplier()) {
      sb.append("     INNER JOIN bd_supplier bd_supplier ON ef.pk_supplier = bd_supplier.pk_supplier  ");
    }
    sb.append("     WHERE eh.dr=0 ");
    sb.append("       and eb.dr=0 ");
    sb.append("       and ef.dr=0 ");
    sb.append("       and sb.dr=0 ");
    sb.append("       and sh.dr=0 ");
    sb.append("       and isnull(sh.btoia,'N')='Y' "); // 需求确认，回冲数据只有传过存货时才统计
    // 本期结算日期
    sb.append("       and sh.dbilldate", ">=", this.getPara().getBegindate()
        .toString());
    sb.append("       and sh.dbilldate", "<=", this.getPara().getEnddate()
        .toString());
    sb.appendIDIsNotNull(" and ef.pk_firstsettle");// 只统计暂估的数据
    sb.append("       and ");
    sb.append(this.getFeeSettleCommonWhere());
    return sb.toString();
  }

  protected String getCurrFeeEstSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(" SELECT ");
    sb.append("       fe.pk_supplier pk_supplier, ");
    // sb.append("       bd_supplier.name suppliername, ");
    sb.append("       0 unestnum, ");// 费用暂估数量为0
    sb.append("       0 unestmny , ");// 费用暂估金额
    sb.append("       0 estnum, ");
    // sb.append("       fe.nestmny nestmny, ");
    sb.append("       fe.ncalcostmny nestmny, ");// v61计成本金额
    sb.append("       0 nsettlenum, ");
    sb.append("       0 ngoodsmoney, ");
    sb.append("       0 nclashestmoney ");
    sb.append("  FROM po_purchaseinfi eh ");
    sb.append(" INNER JOIN po_purchaseinfi_b eb ON eh.pk_stockps = eb.pk_stockps ");
    // 查询物料分类时才会用到 mengjian 注：查询费用信息时物料表应与费用中的物料关联
    sb.append(" INNER JOIN po_purchaseinfi_fee fe ON eb.pk_stockps_b = fe.pk_stockps_b ");
    if (this.containMaterial()) {
      sb.append(" INNER JOIN bd_material bd_material ON fe.pk_feematerial = bd_material.pk_material ");
    }
    // 查询供应商分类时才会用到
    if (this.containSupplier()) {
      sb.append(" INNER JOIN bd_supplier bd_supplier ON fe.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append(" where eb.dr=0 ");
    sb.append("   AND isnull(fe.btoia,'N')   = 'Y' ");
    sb.append("   and fe.dr=0 ");
    sb.append("   and eh.dr=0 ");
    // 费用暂估日期
    sb.append("   and fe.destdate>='"
        + this.getPara().getBegindate().toString() + "'");
    sb.append("   and fe.destdate<='" + this.getPara().getEnddate().toString()
        + "'");
    sb.append("       and ");
    sb.append(this.getFeeEstCommonWhere());
    return sb.toString();
  }

  /**
   * 本期费用结算金额
   * 
   * @return
   */
  protected String getCurrFeeSettleSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append("    SELECT ");
    sb.append("           ef.pk_supplier pk_supplier, ");
    sb.append("           0 unestnum, ");
    sb.append("           0 unestmny,  ");
    sb.append("           0 estnum, ");
    sb.append("           0 nestmny, ");
    sb.append("           0 nsettlenum, ");
    sb.append("           fe.nallotmoney ngoodsmoney, ");// 费用分摊金额
    sb.append("           0 nclashestmoney ");// 冲销暂估金额
    sb.append("      FROM po_purchaseinfi eh  ");
    sb.append("     INNER JOIN po_purchaseinfi_b eb ON eh.pk_stockps = eb.pk_stockps  ");
    sb.append("     INNER JOIN po_purchaseinfi_fee ef ON ef.pk_stockps_b=eb.pk_stockps_b");
    sb.append("     INNER JOIN po_settlebill_b sb ON sb.pk_stock_b = eb.pk_stockps_b  ");
    sb.append("     INNER JOIN po_settlebill sh ON sh.pk_settlebill = sb.pk_settlebill  ");
    sb.append("     INNER JOIN po_settle_feeallot fe ON fe.pk_settlebill_b = sb.pk_settlebill_b ");
    sb.append("                and ef.pk_srcfeematerial=fe.pk_srcmaterial");
    // 查询物料分类时才会用到
    if (this.containMaterial()) {
      sb.append("     INNER JOIN bd_material bd_material ON ef.pk_feematerial = bd_material.pk_material  ");
    }
    // 查询供应商分类时才会用到
    if (this.containSupplier()) {
      sb.append("     INNER JOIN bd_supplier bd_supplier ON ef.pk_supplier = bd_supplier.pk_supplier  ");
    }
    sb.append("     WHERE eh.dr=0 ");
    sb.append("       and eb.dr=0 ");
    sb.append("   AND isnull(ef.btoia,'N')   = 'Y' ");
    sb.append("       and ef.dr=0 ");
    sb.append("       and sb.dr=0 ");
    sb.append("       and sh.dr=0 ");
    sb.append("       and fe.dr=0 ");
    // 本期结算日期
    sb.append("       and sh.dbilldate", ">=", this.getPara().getBegindate()
        .toString());
    sb.append("       and sh.dbilldate", "<=", this.getPara().getEnddate()
        .toString());
    sb.appendIDIsNotNull(" and ef.pk_firstsettle");// 只统计暂估的数据
    sb.append("       and ");
    sb.append(this.getFeeSettleCommonWhere());
    return sb.toString();
  }

  protected String getInitialFeeEstSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(" SELECT ");
    sb.append("       fe.pk_supplier pk_supplier, ");
    // sb.append("       bd_supplier.name suppliername, ");
    sb.append("       0 unestnum, ");// 费用暂估数量为0
    // sb.append("       isnull(fe.nestmny,0) unestmny,  ");// 费用暂估金额即未冲销的金额
    sb.append("       isnull(fe.ncalcostmny,0) unestmny,  ");// 费用暂估金额即未冲销的金额
                                                             // v61计成本金额
    sb.append("       0 estnum, ");
    sb.append("       0 nestmny, ");
    sb.append("       0 nsettlenum, ");
    sb.append("       0 ngoodsmoney, ");
    sb.append("       0 nclashestmoney ");
    sb.append("  FROM po_purchaseinfi eh ");
    sb.append(" INNER JOIN po_purchaseinfi_b eb ON eh.pk_stockps = eb.pk_stockps ");
    // 查询物料分类时才会用到 mengjian 注：查询费用信息时物料表应与费用中的物料关联
    sb.append(" INNER JOIN po_purchaseinfi_fee fe ON eb.pk_stockps_b = fe.pk_stockps_b ");
    if (this.containMaterial()) {
      sb.append(" INNER JOIN bd_material bd_material ON fe.pk_feematerial = bd_material.pk_material ");
    }
    // 查询供应商分类时才会用到
    if (this.containSupplier()) {
      sb.append(" INNER JOIN bd_supplier bd_supplier ON fe.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append(" where eh.dr=0 and eb.dr=0 and fe.dr=0 ");
    sb.append("   AND isnull(fe.btoia,'N')   = 'Y' ");
    // 费用暂估日期
    sb.append("   and fe.destdate<='"
        + this.getPara().getBegindate().toString() + "'");
    sb.append("   and ");
    sb.append(this.getFeeUnclashAddWhere()); // 费用未结算或者结算了但未传成本
    sb.append("   and ");
    sb.append(this.getFeeEstCommonWhere());
    return sb.toString();
  }

  protected String getInitialSql() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.getInitialGoodEstSql());
    sb.append("    union all ");
    sb.append(this.getInitialFeeEstSql());
    return sb.toString();
  }

}
