package nc.impl.pu.report.supplierestdetail.sqlbuilder;

import nc.impl.pu.report.supplierestdetail.SupplierEstDetailReportImpl;
import nc.vo.pu.report.enumeration.PuEstDetailMemoEnum;
import nc.vo.pu.report.queryinfo.supplierest.PuSupplierEstQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 期初暂估单暂估明细
 * 
 * @since 6.0
 * @version 2011-5-21 下午07:09:16
 * @author 田锋涛
 */

public class M4TEstDetailSqlBuilder extends AbstractEstDetailSqlBuilder {

  /**
   * @param para
   */
  public M4TEstDetailSqlBuilder(PuSupplierEstQryInfoPara para) {
    super(para);
  }

  @Override
  public String getQuerySql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(SupplierEstDetailReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getGoodsEst());
    sb.append(" ) M4TEstT1 union all ");
    sb.append(SupplierEstDetailReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getGoodsSettle());
    sb.append(" ) M4TEstT2 ");
    return sb.toString();
  }

  protected String getGoodsEst() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(" SELECT bd_material.pk_marbasclass pk_marbasclass,");
    sb.append("        eb.pk_material pk_material,");
    sb.append("        eh.pk_supplier pk_supplier,");
    sb.append("        '' pk_feematerial,");
    sb.append(PuEstDetailMemoEnum.M4T.toInt() + " billtype,");
    sb.append("        eh.vbillcode vbillcode,");
    sb.append("        eh.dbilldate dbilldate,");
    sb.append("        eb.nnum nestnum,");
    // sb.append("        eb.nmny nestmny,");
    sb.append("        eb.ncalcostmny nestmny,");// v61计成本金额
    sb.append("        0 nsettlenum,");
    sb.append("        0 nclashestmoney,");
    sb.append("        0 ngoodsmoney,");
    sb.append("        eb.pk_initialest_b pk_stockps_b,");// 入库主键
    sb.append("        'est' data_type,");// 暂估数据
    sb.append("        eh.ccurrencyid ccurrencyid ");
    sb.append("   FROM po_initialest eh ");
    sb.append("  INNER JOIN po_initialest_b eb ON eh.pk_initialest = eb.pk_initialest ");
    sb.append("  INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    // 查询供应商分类时才会用到
    if (this.containSupplier()) {
      sb.append("  INNER JOIN bd_supplier bd_supplier ON eh.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append("  where eb.dr=0  ");
    sb.append("    and eh.dr=0  ");
    sb.append("    and eh.dbilldate >='"
        + this.getPara().getBegindate().toString() + "'");
    sb.append("   and eh.dbilldate <='"
        + this.getPara().getEnddate().toString() + "'");

    // wuxla 2013-4-23 影响成本的才查询出来
    sb.append("   and eb.baffectcost", UFBoolean.TRUE);

    sb.append("   and ");
    sb.append(this.getGoodCommonWhere());
    // sb.append(this.getQryDlgWhereSql());
    return sb.toString();
  }

  protected String getGoodsSettle() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(" SELECT bd_material.pk_marbasclass pk_marbasclass,");
    sb.append("        eb.pk_material pk_material,");
    sb.append("        eh.pk_supplier pk_supplier,");
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
    sb.append("        eb.pk_initialest_b pk_stockps_b,");// 入库主键
    sb.append("        'settle' data_type,");// 结算数据
    sb.append("        eh.ccurrencyid ccurrencyid ");
    sb.append("   FROM po_settlebill sh ");
    sb.append("  INNER JOIN po_settlebill_b sb ON sh.pk_settlebill = sb.pk_settlebill ");
    sb.append("  INNER JOIN po_initialest_b eb ON sb.pk_stock_b = eb.pk_initialest_b ");
    sb.append("  INNER JOIN po_initialest eh ON eb.pk_initialest = eh.pk_initialest ");
    sb.append("  INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    // 查询供应商分类时才会用到
    if (this.containSupplier()) {
      sb.append("  INNER JOIN bd_supplier bd_supplier ON eh.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append("  where eb.dr=0 ");
    sb.append("    and sb.dr=0  ");
    sb.append("    and sh.dr=0  ");
    sb.append("    and eh.dr=0  ");

    // wuxla 2013-4-23 影响成本的才查询出来
    sb.append("    and eb.baffectcost", UFBoolean.TRUE);

    sb.append("    and sh.dbilldate>='"
        + this.getPara().getBegindate().toString() + "'");
    sb.append("    and sh.dbilldate<='"
        + this.getPara().getEnddate().toString() + "'");
    sb.append("    and isnull(sb.nclashestmoney,0)<>0 ");
    sb.append("    and ");
    sb.append(this.getGoodCommonWhere());
    // sb.append(this.getQryDlgWhereSql());
    return sb.toString();
  }

  @Override
  protected String getQryDlgWhereSql() {
    String sql = super.getQryDlgWhereSql();
    sql = sql.replace("eb.pk_org", "eh.pk_stockorg");// 替换库存组织
    sql = sql.replace("eb.pk_financeorg", "eb.pk_org");// 替换财务组织
    sql = sql.replace("eb.pk_purchaseorg", "eh.pk_purchaseorg");// 替换采购组织
    sql = sql.replace("eb.pk_supplier", "eh.pk_supplier");// 替换供应商
    sql = sql.replace("eh.pk_psndoc", "eh.pk_bizpsn");// 采购员
    return sql;
  }

}
