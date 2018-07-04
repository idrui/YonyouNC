package nc.impl.pu.report.journal;

import nc.vo.pu.report.queryinfo.journal.JournalConstant;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/*
 * 物料基本分类 * 物料 * 采购组织 * 采购部门 * 采购员 *
 *  月份 * 日期 供应商 订单主数量  * 订单无税单价 * 订单无税金额* 
 *  订单税额 订单价税合计 订单金额百分比(%) 付款金额 付款金额百分比(%)  
 *  退货主数量 到货主数量  到货金额  退库主数量  入库主数量  入库金额  发票主数量
 *    发票无税单价  发票无税金额 发票税额,发票价税合计,发票金额百分比(%)
 *    ,结算数量,结算单价, 结算金额
 * 汇总口径  ：// 物料基本分类 // 物料 // 采购组织 // 采购部门 // 采购员// 月份 日期 供应商
 */
public class SettleSqlGetter implements ISqlGetter {
  private ConditionVO[] conditions;

  // po_settlebill
  private String Join_27 =
      "  from po_invoice po_invoice   INNER JOIN po_invoice_b po_invoice_b  "
          + "ON po_invoice.pk_invoice = po_invoice_b.pk_invoice and po_invoice_b.dr=0 "
          + " inner join po_settlebill_b po_settlebill_b "
          + " on po_invoice_b.pk_invoice_b = po_settlebill_b.pk_invoice_b and po_settlebill_b.dr=0 "
          + " inner join po_settlebill po_settlebill "
          + " on po_settlebill.pk_settlebill = po_settlebill_b.pk_settlebill"
          + " inner join bd_material bd_material "
          + " on po_settlebill_b.pk_material = bd_material.pk_material "
          + " inner join bd_marbasclass bd_marbasclass "
          + " on bd_marbasclass.pk_marbasclass = bd_material.pk_marbasclass "
          + " inner join BD_SUPPLIER BD_SUPPLIER "
          + " on BD_SUPPLIER.pk_supplier = po_settlebill_b.pk_supplier ";

  private String SELECT_Feild = "select "
      + " BD_MARBASCLASS.pk_marbasclass pk_marbasclass , "
      + " BD_MATERIAL.pk_material pk_material,"
      + " po_settlebill.pk_org_v pk_org_v,"
      + " po_settlebill_b.PK_DEPT_V pk_dept_v , "
      + " po_settlebill_b.pk_psndoc pk_psndoc,"
      + " substring(po_settlebill.dbilldate,6,2) monthvalue,"
      + " substring(po_settlebill.dbilldate,1,10) dbilldate, "
      + " po_settlebill_b.PK_SUPPLIER PK_SUPPLIER, "
      + " po_settlebill.ccurrencyid corigcurrencyid ," // 币种
      + " 0 ordernnum, "
      + " 0 ordernmny , " + " 0 orderntax , 0 nacccancelinvmny ,"
      + " 0 arrivennum," + " 0 arrivebacknnum," + " 0 arrivenmny ,"
      + " 0 purnnum ," + " 0 purbacknnum ," + " 0 purnmny ,"
      + " 0 invoicennum ," + " 0 invoicenmny ," + " 0 invoicetax ,"
      + " po_settlebill_b.nsettlenum settlennum ,"
      + " po_settlebill_b.nmoney settlenmny ";

  private String whereConds;

  public SettleSqlGetter(String whereConds, ConditionVO[] conditions) {
    this.whereConds = whereConds;
    this.conditions = conditions;
  }

  @Override
  public String getSql(String[] groups) {
    SqlBuilder sqlSelect = new SqlBuilder();
    sqlSelect.append(this.SELECT_Feild);
    sqlSelect.append(this.Join_27);
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
        this.whereConds.replace(JournalConstant.DBILLDATE,
            "po_settlebill.dbilldate");// 单据日期
    whereSql =
        whereSql.replace(JournalConstant.PK_ORG, "po_invoice.pk_purchaseorg");// 采购组织
    whereSql =
        whereSql.replace(JournalConstant.PK_STOCKORG, "po_invoice.pk_stockorg");// 替换收货库存组织
    whereSql =
        whereSql.replace(JournalConstant.PK_PSNDOC, "po_invoice.pk_bizpsn");// 业务员
    whereSql = whereSql.replace(JournalConstant.PK_DEPT, "po_invoice.pk_dept");// 部门
    whereSql = whereSql.replace(JournalConstant.VFREE, "po_invoice_b.vfree");// 自由辅助属性
    whereSql =
        whereSql.replace(JournalConstant.CPRODUCTORID,
            "po_invoice_b.cproductorid");// 生产厂商
    whereSql =
        whereSql.replace(JournalConstant.CPROJECTID, "po_invoice_b.cprojectid");// 项目
    whereSql =
        whereSql.replace(JournalConstant.PK_GROUP, "po_invoice_b.pk_group");// 集团

    return whereSql;
  }

}
