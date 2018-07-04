package nc.impl.pu.report.supplierest.sqlbuilder;

import nc.impl.pu.report.supplierestdetail.sqlbuilder.AbstractEstDetailSqlBuilder;
import nc.vo.pu.report.queryinfo.supplierest.PuSupplierEstQryInfoPara;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 供应商暂估余额的抽象类
 * 
 * @since 6.0
 * @version 2011-5-21 上午10:17:04
 * @author 田锋涛
 */

public abstract class AbstractSupplierEstSqlBuilder extends
    AbstractEstDetailSqlBuilder {

  /**
   * @param para
   */
  public AbstractSupplierEstSqlBuilder(PuSupplierEstQryInfoPara para) {
    super(para);
  }

  /**
   * 统计费用未冲销时部分需加以下条件
   * 
   * @return
   */
  public String getFeeUnclashAddWhere() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" (");
    // 费用未结算或者结算了但未传成本
    sql.appendIDIsNull("fe.pk_firstsettle");//
    sql.append("     or not exists ");
    sql.append("      ( select 1 from po_settlebill_b sb2 ");
    sql.append("         inner join po_settlebill sh2 on sb2.pk_settlebill=sh2.pk_settlebill");
    sql.append("         where sb2.dr=0 and sh2.dr=0 and sb2.pk_settlebill_b = fe.pk_firstsettle_b  ");
    sql.append("          and isnull(sh2.btoia,'N')='Y'");
    // 本期之前结算的不统计
    sql.append("          and sh2.dbilldate", "<", this.getPara()
        .getBegindate().toString());
    sql.append("      )");
    sql.append("  )");
    return sql.toString();
  }

  /**
   * 费用暂估时的where，拼费用的物料和供应商
   * 
   * @return
   */
  @Override
  protected String getFeeEstCommonWhere() {
    String feeWhere = this.getGoodCommonWhere();
    feeWhere = feeWhere.replace("eb.pk_supplier", "fe.pk_supplier");
    // 费用暂估替换
    feeWhere = feeWhere.replace("eb.pk_srcmaterial", "fe.pk_srcfeematerial");
    return feeWhere;
  }

}
