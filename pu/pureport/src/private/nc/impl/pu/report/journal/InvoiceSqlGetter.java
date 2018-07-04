package nc.impl.pu.report.journal;

import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.report.queryinfo.journal.JournalConstant;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class InvoiceSqlGetter implements ISqlGetter {

  private ConditionVO[] conditions;

  private String Join_25 =
      " from po_invoice po_invoice inner join po_invoice_b po_invoice_b "
          + " on po_invoice.pk_invoice = po_invoice_b.pk_invoice and po_invoice_b.dr=0 "
          + " inner join bd_material bd_material "
          + " on po_invoice_b.pk_material = bd_material.pk_material"
          + " inner join bd_marbasclass bd_marbasclass "
          + " on bd_marbasclass.pk_marbasclass = bd_material.pk_marbasclass "
          + " inner join BD_SUPPLIER BD_SUPPLIER "
          + " on BD_SUPPLIER.pk_supplier = po_invoice.pk_supplier";

  // 物料基本分类
  // 物料
  // 采购组织
  // 采购部门
  // 采购员
  // 月份
  // 日期
  // 供应商
  // 订单主数量
  // 订单无税单价
  // 订单无税金额
  // 订单税额
  // 订单价税合计
  // 订单金额百分比(%)
  // 付款金额
  // 付款金额百分比(%)
  // 退货主数量
  // 到货主数量
  // 到货金额
  // 退库主数量
  // 入库主数量
  // 入库金额
  // 发票主数量
  // 发票无税单价
  // 发票无税金额
  // 发票税额
  // 发票价税合计
  // 发票金额百分比(%)
  // 结算数量
  // 结算单价
  // 结算金额
  private String SELECT_Feild = "select "
      // 物料基本分类
      // 物料
      // 采购组织
      // 采购部门
      // 采购员
      // 月份
      // 日期
      // 供应商
      // 币种
      + " BD_MARBASCLASS.pk_marbasclass pk_marbasclass , "
      + " BD_MATERIAL.pk_material pk_material,"
      + " PO_INVOICE.pk_purchaseorg_v pk_org_v,"
      + " PO_INVOICE.PK_DEPT_V pk_dept_v , "
      + " PO_INVOICE.pk_bizpsn pk_psndoc,"
      + " substring(PO_INVOICE.dbilldate,6,2) monthvalue,"
      + " substring(PO_INVOICE.dbilldate,1,10) dbilldate, "
      + " PO_INVOICE.PK_SUPPLIER PK_SUPPLIER, " 
      + " po_invoice.corigcurrencyid corigcurrencyid ," 
      + " 0 ordernnum, "
      + " 0 ordernmny , " + " 0 orderntax ," + " 0 nacccancelinvmny ,"
      + " 0 arrivennum ," + " 0 arrivebacknnum ," + " 0 arrivenmny ,"
      + " 0 purnnum ," + " 0 purbacknnum ," + " 0 purnmny ,"
      + " po_invoice_b.nnum invoicennum ," + " po_invoice_b.nmny invoicenmny ,"
      + " po_invoice_b.ntax invoicetax ," + " 0 settlennum ,"
      + " 0 settlenmny ";

  private String whereConds;

  public InvoiceSqlGetter(String wheresql, ConditionVO[] conditions) {
    this.whereConds = wheresql;
    this.conditions = conditions;
  }

  @Override
  public String getSql(String[] groups) {

    final SqlBuilder sqlSelect = new SqlBuilder();
    sqlSelect.append(this.SELECT_Feild);
    sqlSelect.append(this.Join_25);
    sqlSelect.append(" where ");
    sqlSelect.append(this.replaceWhere());
    return sqlSelect.toString();
  }

  /**
   * 替换sql
   * 
   * @return
   */
  private String replaceWhere() {

    String whereSql =
        this.whereConds.replace(JournalConstant.DBILLDATE, PUEntity.M25_H_TAB
            + "." + InvoiceHeaderVO.DBILLDATE);// 单据日期
    whereSql =
        whereSql.replace(JournalConstant.PK_ORG, PUEntity.M25_H_TAB + "."
            + InvoiceHeaderVO.PK_PURCHASEORG);// 采购组织
    whereSql =
        whereSql.replace(JournalConstant.PK_STOCKORG, PUEntity.M25_H_TAB + "."
            + InvoiceHeaderVO.PK_STOCKORG);// 替换收货库存组织
    whereSql =
        whereSql.replace(JournalConstant.PK_PSNDOC, PUEntity.M25_H_TAB + "."
            + InvoiceHeaderVO.PK_BIZPSN);// 业务员
    whereSql =
        whereSql.replace(JournalConstant.PK_DEPT, PUEntity.M25_H_TAB + "."
            + InvoiceHeaderVO.PK_DEPT);// 部门
    whereSql =
        whereSql.replace(JournalConstant.VFREE, PUEntity.M25_B_TAB + ".vfree");// 自由辅助属性
    whereSql =
        whereSql.replace(JournalConstant.CPRODUCTORID, PUEntity.M25_B_TAB + "."
            + InvoiceItemVO.CPRODUCTORID);// 生产厂商
    whereSql =
        whereSql.replace(JournalConstant.CPROJECTID, PUEntity.M25_B_TAB + "."
            + InvoiceItemVO.CPROJECTID);// 项目
    whereSql =
        whereSql.replace(JournalConstant.PK_GROUP, PUEntity.M25_B_TAB + "."
            + InvoiceItemVO.PK_GROUP);// 集团

    return whereSql;
  }

}
