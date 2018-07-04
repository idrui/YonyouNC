package nc.bs.pu.report.invoice;

import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.jdbc.framework.util.DBConsts;
import nc.pub.smart.context.SmartContext;
import nc.vo.arap.payable.PayableBillItemVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.m4203.entity.SubcontinFIHeaderVO;
import nc.vo.pu.m4203.entity.SubcontinFIItemVO;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 采购发票明细查询的一些服务实现
 * 
 * @since 6.0
 * @version 2011-9-14 下午4:47:43
 * @author zhaoyha
 */
public class InvoiceDetailQuery {
  /**
   * 得到查询应付单核销信息的SQL
   * 
   * @param context
   * @return
   */
  public String getApVerifySql(SmartContext context) {
    if (SysInitGroupQuery.isAPEnabled()) {
      return ArapServicesForPUUtil.getApVerifyMnySql().toString();
    }
    // 如果应付未启用的情况下返回一个假的核销信息查询SQL
    return this.getMaskApVerifySql();
  }

  /**
   * 得到查询来源信息的SQL
   * 
   * @param context
   * @return
   */
  public String getSourceSql(SmartContext context) {
    SqlBuilder sql = new SqlBuilder();
    // 拼上采购入
    this.unionAll(sql, this.getPurchsInSql());
    // 拼上期初
    this.unionAll(sql, this.getInitEstSql());
    // 拼上VMI
    this.unionAll(sql, this.getVMIInSql());
    // 拼上委外入
    this.unionAll(sql, this.getSubcontInSql());
    return sql.toString();
  }

  private SqlBuilder getInitEstSql() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select stockb." + InitialEstHeaderVO.PK_INITIALEST + " "
        + PurchaseinFIItemVO.PK_STOCKPS);
    sql.append(",stockb." + InitialEstItemVO.PK_INITIALEST_B + " "
        + PurchaseinFIItemVO.PK_STOCKPS_B);
    sql.append(",stockh." + InitialEstHeaderVO.VBILLCODE);
    sql.append(",stockh." + InitialEstHeaderVO.DBILLDATE + " "
        + PurchaseinFIItemVO.DBIZDATE);
    sql.append(",stockb." + InitialEstItemVO.NTAXPRICE + " "
        + PurchaseinFIItemVO.NTAXPRICE);
    sql.append(",stockb." + InitialEstItemVO.NTAXMNY + " "
        + PurchaseinFIItemVO.NTAXMNY);
    sql.append(" from ");
    sql.append(PUEntity.M4T_H_TAB + " stockh");
    sql.append(" inner join " + PUEntity.M4T_B_TAB + " stockb on ");
    sql.append("stockh." + InitialEstHeaderVO.PK_INITIALEST + "=");
    sql.append("stockb." + InitialEstItemVO.PK_INITIALEST);
    sql.append(" where stockb.dr", 0);
    return sql;
  }

  private String getMaskApVerifySql() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select '" + DBConsts.NULL_WAVE + "' "
        + PayableBillItemVO.TOP_ITEMID + ", ");
    sql.append("null " + ArapServicesForPUUtil.NVERIFYMNY);
    sql.append(",null " + ArapServicesForPUUtil.NORIGVERIFYMNY);
    sql.append(" from " + PUEntity.M25_B_TAB);
    sql.appendIDIsNull(" where " + InvoiceItemVO.PK_INVOICE_B);
    return sql.toString();
  }

  private SqlBuilder getPurchsInSql() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select stockb." + PurchaseinFIItemVO.PK_STOCKPS);
    sql.append(",stockb." + PurchaseinFIItemVO.PK_STOCKPS_B);
    sql.append(",stockh." + PurchaseinFIHeaderVO.VBILLCODE);
    sql.append(",stockb." + PurchaseinFIItemVO.DBIZDATE);
    sql.append(",stockb." + PurchaseinFIItemVO.NTAXPRICE);
    sql.append(",stockb." + PurchaseinFIItemVO.NTAXMNY);
    sql.append(" from ");
    sql.append(PUEntity.PurchaseinFI_H_TAB + " stockh");
    sql.append(" inner join " + PUEntity.PurchaseinFI_B_TAB + " stockb on ");
    sql.append("stockh." + PurchaseinFIHeaderVO.PK_STOCKPS + "=");
    sql.append("stockb." + PurchaseinFIItemVO.PK_STOCKPS);
    sql.append(" where stockb.dr", 0);
    return sql;
  }

  private SqlBuilder getSubcontInSql() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select stockb." + SubcontinFIItemVO.PK_STOCKPS);
    sql.append(",stockb." + SubcontinFIItemVO.PK_STOCKPS_B);
    sql.append(",stockh." + SubcontinFIHeaderVO.VBILLCODE);
    sql.append(",stockb." + SubcontinFIItemVO.DBIZDATE);
    sql.append(",stockb." + SubcontinFIItemVO.NCOSTPRICE + " "
        + PurchaseinFIItemVO.NTAXPRICE);
    sql.append(",stockb." + SubcontinFIItemVO.NCOSTMNY + " "
        + PurchaseinFIItemVO.NTAXMNY);
    sql.append(" from ");
    sql.append(PUEntity.SUBCONTIN_H_TAB + " stockh");
    sql.append(" inner join " + PUEntity.SUBCONTIN_B_TAB + " stockb on ");
    sql.append("stockh." + SubcontinFIHeaderVO.PK_STOCKPS + "=");
    sql.append("stockb." + SubcontinFIItemVO.PK_STOCKPS);
    sql.append(" where stockb.dr", 0);
    return sql;
  }

  private SqlBuilder getVMIInSql() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select stockh." + VmiFIHeaderVO.PK_STOCKPS);
    sql.append(",stockh." + VmiFIHeaderVO.PK_STOCKPS_B);
    sql.append(",stockh." + VmiFIHeaderVO.VBILLCODE);
    sql.append(",stockh." + VmiFIHeaderVO.DBIZDATE);
    sql.append(",stockh." + VmiFIHeaderVO.NTAXNETPRICE + " "
        + PurchaseinFIItemVO.NTAXPRICE);
    sql.append(",null " + PurchaseinFIItemVO.NTAXMNY);
    sql.append(" from ");
    sql.append(PUEntity.VMIFI_H_TAB + " stockh");
    sql.append(" where stockh.dr", 0);
    return sql;
  }

  private void unionAll(SqlBuilder unionSql, SqlBuilder singleSql) {
    if (unionSql.toString().length() > 0 && null != singleSql
        && singleSql.toString().length() > 0) {
      unionSql.append(" union all ");
      unionSql.append(singleSql);
    }
    else if (unionSql.toString().length() == 0 && null != singleSql
        && singleSql.toString().length() > 0) {
      unionSql.append(singleSql);
    }
  }

}
