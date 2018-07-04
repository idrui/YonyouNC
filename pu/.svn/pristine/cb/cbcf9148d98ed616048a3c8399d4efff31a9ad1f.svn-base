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
public class PurSqlGetter implements ISqlGetter {

  private ConditionVO[] conditions;

  private String Join_45 =
      " "
          + " from IC_PURCHASEIN_H IC_PURCHASEIN_H inner join IC_PURCHASEIN_B IC_PURCHASEIN_B "
          + " on IC_PURCHASEIN_B.CGENERALHID = IC_PURCHASEIN_H.CGENERALHID and IC_PURCHASEIN_B.dr=0  "
          + " inner join bd_material bd_material "
          + " on IC_PURCHASEIN_B.CMATERIALVID = bd_material.pk_material "
          + " inner join bd_marbasclass bd_marbasclass "
          + " on bd_marbasclass.pk_marbasclass = bd_material.pk_marbasclass "
          + " inner join BD_SUPPLIER BD_SUPPLIER "
          + " on BD_SUPPLIER.pk_supplier = IC_PURCHASEIN_H.cvendorid ";

  private String Join_M4T =
      " "
          + " from po_initialest po_initialest inner join po_initialest_b po_initialest_b "
          + " on po_initialest_b.pk_initialest = po_initialest.pk_initialest and po_initialest_b.dr=0 "
          + " inner join bd_material bd_material "
          + " on po_initialest_b.pk_material = bd_material.pk_material "
          + " inner join bd_marbasclass bd_marbasclass "
          + " on bd_marbasclass.pk_marbasclass = bd_material.pk_marbasclass "
          + " inner join BD_SUPPLIER BD_SUPPLIER "
          + " on BD_SUPPLIER.pk_supplier = po_initialest.pk_supplier ";

  private String SELECT_45Feild =
      "select "
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
          + " IC_PURCHASEIN_H.cpurorgvid pk_org_v,"
          + " IC_PURCHASEIN_H.cdptvid pk_dept_v , "
          + " IC_PURCHASEIN_H.cbizid pk_psndoc,"
          + " substring(IC_PURCHASEIN_H.dbilldate,6,2) monthvalue,"
          + " substring(IC_PURCHASEIN_H.dbilldate,1,10) dbilldate, "
          + " IC_PURCHASEIN_H.cvendorid PK_SUPPLIER, "
          + " IC_PURCHASEIN_B.corigcurrencyid corigcurrencyid, "
          + " 0 ordernnum, "
          + " 0 ordernmny , "
          + " 0 orderntax ,"
          + " 0 nacccancelinvmny ,"
          + " 0 arrivennum,"
          + " 0 arrivebacknnum,"
          + " 0 arrivenmny ,"
          + " CASE WHEN IC_PURCHASEIN_H.freplenishflag = 'N' then IC_PURCHASEIN_B.nnum else 0 end purnnum,"
          + " CASE WHEN IC_PURCHASEIN_H.freplenishflag = 'Y' then IC_PURCHASEIN_B.nnum else 0 end purbacknnum,"
          + " IC_PURCHASEIN_B.nmny purnmny ," + " 0 invoicennum ,"
          + " 0 invoicenmny ," + " 0 invoicetax ," + " 0 settlennum ,"
          + " 0 settlenmny ";

  private String SELECT_M4TFeild = "select "
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
      + " po_initialest.pk_purchaseorg_v pk_org_v,"
      + " po_initialest.pk_dept_v pk_dept_v , "
      + " po_initialest.pk_bizpsn pk_psndoc,"
      + " substring(po_initialest.dbilldate,6,2) monthvalue,"
      + " substring(po_initialest.dbilldate,1,10) dbilldate, "
      + " po_initialest.pk_supplier PK_SUPPLIER, "
      + " po_initialest.corigcurrencyid corigcurrencyid ," 
      + " 0 ordernnum, "
      + " 0 ordernmny , " + " 0 orderntax ," + " 0 nacccancelinvmny ,"
      + " 0 arrivennum," + " 0 arrivebacknnum," + " 0 arrivenmny ,"
      + " po_initialest_b.nnum purnnum," + " 0 purbacknnum,"
      + " po_initialest_b.nmny purnmny ," + " 0 invoicennum ,"
      + " 0 invoicenmny ," + " 0 invoicetax ," + " 0 settlennum ,"
      + " 0 settlenmny ";

  private String whereConds;

  public PurSqlGetter(String wheresql, ConditionVO[] conditions) {
    this.whereConds = wheresql;
    this.conditions = conditions;
  }

  @Override
  public String getSql(String[] groups) {

    SqlBuilder sqlSelect = new SqlBuilder();
    // 采购入库单
    sqlSelect.append(this.SELECT_45Feild);
    sqlSelect.append(this.Join_45);
    sqlSelect.append(" where ");
    sqlSelect.append(this.replace45Where());
    sqlSelect.append(" union all ");
    // 期初暂估单
    sqlSelect.append(this.SELECT_M4TFeild);
    sqlSelect.append(this.Join_M4T);
    sqlSelect.append(" where ");
    sqlSelect.append(this.replaceM4TWhere());
    return sqlSelect.toString();
  }

  /**
   * 替换sql
   * 
   * @return
   */
  private String replace45Where() {

    String whereSql =
        this.whereConds.replace(JournalConstant.DBILLDATE,
            "IC_PURCHASEIN_H.dbilldate");// 单据日期
    whereSql =
        whereSql.replace(JournalConstant.PK_ORG, "IC_PURCHASEIN_H.cpurorgoid");// 采购组织
    whereSql =
        whereSql.replace(JournalConstant.PK_STOCKORG, "IC_PURCHASEIN_H.pk_org");// 替换收货库存组织
    whereSql =
        whereSql.replace(JournalConstant.PK_PSNDOC, "IC_PURCHASEIN_H.cbizid");// 业务员
    whereSql =
        whereSql.replace(JournalConstant.PK_DEPT, "IC_PURCHASEIN_H.cdptid");// 部门
    whereSql = whereSql.replace(JournalConstant.VFREE, "IC_PURCHASEIN_B.vfree");// 自由辅助属性
    whereSql =
        whereSql.replace(JournalConstant.CPRODUCTORID,
            "IC_PURCHASEIN_B.cproductorid");// 生产厂商
    whereSql =
        whereSql.replace(JournalConstant.CPROJECTID,
            "IC_PURCHASEIN_B.cprojectid");// 项目
    whereSql =
        whereSql.replace(JournalConstant.PK_GROUP, "IC_PURCHASEIN_B.pk_group");// 集团

    return whereSql;
  }

  /**
   * 替换sql
   * 
   * @return
   */
  private String replaceM4TWhere() {

    String whereSql =
        this.whereConds.replace(JournalConstant.DBILLDATE,
            "po_initialest.dbilldate");// 单据日期
    whereSql =
        whereSql
            .replace(JournalConstant.PK_ORG, "po_initialest.pk_purchaseorg");// 采购组织
    whereSql =
        whereSql.replace(JournalConstant.PK_STOCKORG,
            "po_initialest.pk_stockorg");// 替换收货库存组织
    whereSql =
        whereSql.replace(JournalConstant.PK_PSNDOC, "po_initialest.pk_bizpsn");// 业务员
    whereSql =
        whereSql.replace(JournalConstant.PK_DEPT, "po_initialest.pk_dept");// 部门
    whereSql = whereSql.replace(JournalConstant.VFREE, "po_initialest_b.vfree");// 自由辅助属性
    whereSql =
        whereSql.replace(JournalConstant.CPRODUCTORID,
            "po_initialest_b.cproductorid");// 生产厂商
    whereSql =
        whereSql.replace(JournalConstant.CPROJECTID,
            "po_initialest_b.cprojectid");// 项目
    whereSql =
        whereSql.replace(JournalConstant.PK_GROUP, "po_initialest_b.pk_group");// 集团

    return whereSql;
  }
}
