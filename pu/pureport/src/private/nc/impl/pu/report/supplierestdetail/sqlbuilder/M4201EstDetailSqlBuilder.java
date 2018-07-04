package nc.impl.pu.report.supplierestdetail.sqlbuilder;

import nc.impl.pu.report.supplierestdetail.SupplierEstDetailReportImpl;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pu.report.enumeration.PuEstDetailMemoEnum;
import nc.vo.pu.report.queryinfo.supplierest.PuSupplierEstQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 采购入库单暂估明细
 * 
 * @since 6.0
 * @version 2011-3-31 下午06:28:06
 * @author yinfy
 */

public class M4201EstDetailSqlBuilder extends M4203EstDetailSqlBuilder {

  public M4201EstDetailSqlBuilder(PuSupplierEstQryInfoPara para) {
    super(para);
  }

  @Override
  public String getQuerySql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(SupplierEstDetailReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getGoodsEst());
    sb.append(" ) M4201T1 union all ");
    sb.append(SupplierEstDetailReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getFeeEst());
    sb.append(" ) M4201T2 union all ");
    sb.append(SupplierEstDetailReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getGoodsSettle());
    sb.append(" ) M4201T3 union all ");
    sb.append(SupplierEstDetailReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getFeeSettle());
    sb.append(" ) M4201T4 ");
    return sb.toString();
  }

  /**
   * 费用结算-回冲和结算
   * 
   * @return
   */
  private String getFeeSettle() {
    SqlBuilder sb = new SqlBuilder();
    // 费用回冲
    sb.append(this.getFeeSettleForClash());
    sb.append(" union all ");
    // 费用结算
    sb.append(this.getFeeSettleForSettle());
    return sb.toString();
  }

  protected String getFeeEst() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" SELECT bd_material.pk_marbasclass pk_marbasclass,");// 货物物料分类
    sql.append("        eb.pk_material pk_material,");// 货物
    sql.append("        fe.pk_supplier pk_supplier,");// 费用供应商
    sql.append("        fe.pk_feematerial pk_feematerial,");// 费用
    sql.append(PuEstDetailMemoEnum.M45.toInt() + " billtype,");
    sql.append("        eh.vbillcode vbillcode,");
    sql.append("        eh.dbilldate dbilldate,");
    sql.append("        0 nestnum,");// 暂估数量
    // sql.append("        fe.nestmny nestmny,");// 暂估金额
    sql.append("        fe.ncalcostmny nestmny,");// 暂估金额：v61计成本金额
    sql.append("        0 nsettlenum,");// 冲销暂估数量
    sql.append("        0 nclashestmoney,");// 冲销暂估金额
    sql.append("        0 ngoodsmoney,");// 结算金额
    sql.append("        eb.pk_stockps_b pk_stockps_b,");// 入库主键
    sql.append("        'est' data_type,");// 暂估数据
    sql.append("        eb.ccurrencyid ccurrencyid ");
    sql.append("  FROM po_purchaseinfi eh ");
    sql.append(" INNER JOIN po_purchaseinfi_b eb ON eh.pk_stockps = eb.pk_stockps ");
    sql.append(" INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    sql.append(" INNER JOIN po_purchaseinfi_fee fe ON eb.pk_stockps_b = fe.pk_stockps_b ");
    // 查询供应商分类时才会用到
    if (this.containSupplier()) {
      sql.append(" INNER JOIN bd_supplier bd_supplier ON fe.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append(" where eb.dr=0 ");
    sql.append("   and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// 暂估成本或者确认了成本
    sql.append("   and eh.dr=0 ");
    sql.append("   and fe.dr=0 ");
    sql.append("   and fe.destdate>='"
        + this.getPara().getBegindate().toString() + "'");
    sql.append("   and fe.destdate<='" + this.getPara().getEnddate().toString()
        + "' ");
    sql.append("   and isnull(fe.ncalcostmny,0) <> 0 ");// 暂估数据
    sql.append("       and ");
    sql.append(this.getFeeEstCommonWhere());
    return sql.toString();
  }

  /**
   * 费用结算部分-回冲费用统计
   * 
   * @return
   */
  protected String getFeeSettleForClash() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select bd_material.pk_marbasclass pk_marbasclass,");
    sql.append("        eb.pk_material pk_material,");
    sql.append("        ef.pk_supplier pk_supplier,");
    sql.append("        ef.pk_feematerial pk_feematerial,");
    sql.append(PuEstDetailMemoEnum.M27.toInt() + " billtype,");
    sql.append("        sh.vbillcode vbillcode,");
    sql.append("        sh.dbilldate dbilldate,");
    sql.append("        0 nestnum,");
    sql.append("        0 nestmny,");
    sql.append("        0 nsettlenum,");
    // 2011-09-16 跟需求(wangyinfen)和测试（suncong）最终确认，费用冲销金额记录第一次冲销的金额，
    // 结算金额取当前行费用的结算金额，所以数据有可能出现冲销金额比结算金额还多的情况
    // sql.append("        ef.nestmny  nclashestmoney,");// 费用暂估金额全部冲销
    sql.append("        ef.ncalcostmny  nclashestmoney,");// 费用暂估金额全部冲销v61取计成本金额
    sql.append("        0 ngoodsmoney,");
    sql.append("        eb.pk_stockps_b pk_stockps_b,");// 入库主键
    sql.append("        'settle' data_type,");// 结算数据
    sql.append("        eb.ccurrencyid ccurrencyid ");
    sql.append("   FROM po_settlebill sh ");
    sql.append("  INNER JOIN po_settlebill_b sb ON sh.pk_settlebill = sb.pk_settlebill ");
    sql.append("  INNER JOIN po_purchaseinfi_fee ef ON sb.pk_settlebill_b=ef.pk_firstsettle_b ");// 关联第一次结算的费用发票
    sql.append("  INNER JOIN po_purchaseinfi_b eb ON  ef.pk_stockps_b =  eb.pk_stockps_b ");
    sql.append("  INNER JOIN po_purchaseinfi eh ON eh.pk_stockps=eb.pk_stockps ");
    sql.append("  INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    // 查询供应商分类时才会用到
    if (this.containSupplier()) {
      sql.append("INNER JOIN bd_supplier bd_supplier ON ef.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append("  where eb.dr=0 ");
    sql.append("    and eh.dr=0 ");
    sql.append("    and sh.dr=0 ");
    sql.append("    and isnull(sh.btoia,'N')='Y' "); // 需求确认，回冲数据只有传过存货时才统计

    // wuxla 2013-4-23 影响成本的才查询出来
    sql.append("    and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// 暂估成本或者确认了成本
    sql.append("    and eb.baffectcost", UFBoolean.TRUE);

    sql.append("    and ef.dr=0 ");
    sql.append("    and sb.dr=0 ");
    sql.append("    and sh.dbilldate", ">=", this.getPara().getBegindate()
        .toString());
    sql.append("    and sh.dbilldate", "<=", this.getPara().getEnddate()
        .toString());
    sql.appendIDIsNotNull(" and ef.pk_firstsettle");// 只统计暂估的数据
    sql.append("    and ");
    sql.append(this.getFeeSettleCommonWhere());
    return sql.toString();
  }

  /**
   * 费用结算部分-结算金额统计
   * 如果本期冲销过某一费用，此费用本期内的所有结算金额都要统计
   * 
   * @return
   */
  protected String getFeeSettleForSettle() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select bd_material.pk_marbasclass pk_marbasclass,");
    sql.append("        eb.pk_material pk_material,");
    sql.append("        ef.pk_supplier pk_supplier,");
    sql.append("        ef.pk_feematerial pk_feematerial,");
    sql.append(PuEstDetailMemoEnum.M27.toInt() + " billtype,");
    sql.append("        sh.vbillcode vbillcode,");
    sql.append("        sh.dbilldate dbilldate,");
    sql.append("        0 nestnum,");
    sql.append("        0 nestmny,");
    sql.append("        0 nsettlenum,");
    sql.append("        0  nclashestmoney,");
    sql.append("        fe.nallotmoney ngoodsmoney,");// 费用分摊金额即结算金额
    sql.append("        eb.pk_stockps_b pk_stockps_b,");// 入库主键
    sql.append("        'settle' data_type,");// 结算数据
    sql.append("        eb.ccurrencyid ccurrencyid ");
    sql.append("   FROM po_settlebill sh ");
    sql.append("  INNER JOIN po_settlebill_b sb ON sh.pk_settlebill = sb.pk_settlebill ");
    sql.append("  INNER JOIN po_purchaseinfi_b eb ON sb.pk_stock_b = eb.pk_stockps_b ");
    sql.append("  INNER JOIN po_purchaseinfi eh ON eb.pk_stockps = eh.pk_stockps ");
    sql.append("  INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    sql.append("  INNER JOIN po_settle_feeallot fe ON fe.pk_settlebill_b = sb.pk_settlebill_b");
    sql.append("  INNER JOIN po_purchaseinfi_fee ef ON eb.pk_stockps_b = ef.pk_stockps_b ");
    sql.append("             and ef.pk_srcfeematerial=fe.pk_srcmaterial ");
    // 查询供应商分类时才会用到
    if (this.containSupplier()) {
      sql.append("INNER JOIN bd_supplier bd_supplier ON ef.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append("  where eb.dr=0 ");
    sql.append("    and eh.dr=0 ");
    sql.append("    AND isnull(ef.btoia,'N')   = 'Y' ");
    sql.append("    and sb.dr=0 ");
    sql.append("    and fe.dr=0 ");
    sql.append("    and ef.dr=0 ");
    sql.append("    and sh.dbilldate", ">=", this.getPara().getBegindate()
        .toString());
    sql.append("    and sh.dbilldate", "<=", this.getPara().getEnddate()
        .toString());
    sql.appendIDIsNotNull(" and ef.pk_firstsettle");// 只统计暂估的数据
    sql.append("    and ");
    sql.append(this.getFeeSettleCommonWhere());
    return sql.toString();
  }

  @Override
  protected String getGoodsEst() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" SELECT   ");
    sql.append("        bd_material.pk_marbasclass pk_marbasclass,");
    sql.append("        eb.pk_material pk_material,");
    sql.append("        eb.pk_supplier pk_supplier,");
    sql.append("        '' pk_feematerial,");
    sql.append(PuEstDetailMemoEnum.M45.toInt() + " billtype,");
    sql.append("        eh.vbillcode vbillcode,");
    sql.append("        eh.dbilldate dbilldate,");
    sql.append("        eb.nestnum nestnum,");
    // sql.append("        eb.nestmny nestmny,");
    sql.append("        eb.nestcalcostmny nestmny,");// v61计成本金额
    sql.append("        0 nsettlenum,");
    sql.append("        0 nclashestmoney,");
    sql.append("        0 ngoodsmoney,");
    sql.append("        eb.pk_stockps_b pk_stockps_b,");// 入库主键
    sql.append("        'est' data_type,");// 暂估数据
    sql.append("        eb.ccurrencyid ccurrencyid ");
    sql.append("   FROM po_purchaseinfi eh ");
    sql.append("  INNER JOIN po_purchaseinfi_b eb ON eh.pk_stockps = eb.pk_stockps ");
    sql.append("  INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    // 查询供应商分类时才会用到
    if (this.containSupplier()) {
      sql.append(" INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append("  where eb.dr=0  ");
    sql.append("    and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// 暂估成本或者确认了成本

    // wuxla 2013-4-23 影响成本的才查询出来
    sql.append("    and eb.baffectcost", UFBoolean.TRUE);

    sql.append("    and eh.dr=0  ");
    sql.append("    and eb.dtocostapdate>='"
        + this.getPara().getBegindate().toString() + "'");
    sql.append("    and eb.dtocostapdate <='"
        + this.getPara().getEnddate().toString() + "'");
    sql.append(" and isnull(eb.nestcalcostmny,0)<>0 ");// 暂估数据
    sql.append("       and ");
    sql.append(this.getGoodCommonWhere());
    return sql.toString();
  }

  @Override
  protected String getGoodsSettle() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(" SELECT bd_material.pk_marbasclass pk_marbasclass,");
    sb.append("        eb.pk_material pk_material,");
    sb.append("        eb.pk_supplier pk_supplier,");
    sb.append("        '' pk_feematerial,");
    sb.append(PuEstDetailMemoEnum.M27.toInt() + " billtype,");
    sb.append("        sh.vbillcode vbillcode,");
    sb.append("        sh.dbilldate dbilldate,");
    sb.append("        0 nestnum,");
    sb.append("        0 nestmny,");
    sb.append("        case when isnull(sh.btoia,'N')='Y'");
    sb.append("             then  sb.nsettlenum ");
    sb.append("             else   0 ");
    sb.append("        end nsettlenum, ");// 冲销暂估数量（需求确认，回冲数据只有传过存货时才统计）
    sb.append("        case when isnull(sh.btoia,'N')='Y'");
    sb.append("             then  sb.nclashestmoney ");
    sb.append("             else   0 ");
    sb.append("        end nclashestmoney, ");// 冲销暂估金额（需求确认，回冲数据只有传过存货时才统计）
    sb.append("        sb.ngoodsmoney ngoodsmoney,");
    sb.append("        eb.pk_stockps_b pk_stockps_b,");// 入库主键
    sb.append("        'settle' data_type,");// 结算数据
    sb.append("        eb.ccurrencyid ccurrencyid ");
    sb.append("   FROM po_settlebill sh ");
    sb.append("  INNER JOIN po_settlebill_b sb ON sh.pk_settlebill = sb.pk_settlebill ");
    sb.append("  INNER JOIN po_purchaseinfi_b eb ON sb.pk_stock_b = eb.pk_stockps_b ");
    sb.append("  INNER JOIN po_purchaseinfi eh ON sb.pk_stock = eh.pk_stockps ");
    sb.append("  INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    // 查询供应商分类时才会用到
    if (this.containSupplier()) {
      sb.append("  INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append("  where eb.dr=0 ");
    sb.append("    and eh.dr=0 ");
    sb.append("    and sb.dr=0 ");
    sb.append("    and sh.dbilldate>='");
    sb.append(this.getPara().getBegindate().toString() + "'");
    sb.append("    and sh.dbilldate<='"
        + this.getPara().getEnddate().toString() + "'");
    sb.append("    and isnull(sb.nclashestmoney,0)<>0 ");// 暂估过且回冲的数据，冲销暂估金额不为零

    sb.append("    and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// 暂估成本或者确认了成本
    // wuxla 2013-4-23 影响成本的才查询出来
    sb.append("    and eb.baffectcost", UFBoolean.TRUE);

    sb.append("    and ");
    sb.append(this.getGoodCommonWhere());
    return sb.toString();
  }

}
