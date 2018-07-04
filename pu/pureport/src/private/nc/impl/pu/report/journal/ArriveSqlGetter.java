package nc.impl.pu.report.journal;

import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.pub.constant.PUEntity;
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
public class ArriveSqlGetter implements ISqlGetter {

  private ConditionVO[] conditions;

  private String Join_23 =
      " "
          + " from po_arriveorder po_arriveorder inner join po_arriveorder_b po_arriveorder_b "
          + " on PO_ARRIVEORDER.PK_ARRIVEORDER = PO_ARRIVEORDER_b.PK_ARRIVEORDER and po_arriveorder_b.dr=0 "
          + " inner join bd_material bd_material on "
          + " po_arriveorder_b.PK_MATERIAL = bd_material.pk_material "
          + " inner join bd_marbasclass bd_marbasclass "
          + " on bd_marbasclass.pk_marbasclass = bd_material.pk_marbasclass "
          + " inner join BD_SUPPLIER BD_SUPPLIER "
          + " on BD_SUPPLIER.pk_supplier = PO_ARRIVEORDER.pk_supplier";

  private String SELECT_Feild =
      "select "
          + " BD_MARBASCLASS.pk_marbasclass pk_marbasclass , "
          + " BD_MATERIAL.pk_material pk_material,"
          + " PO_ARRIVEORDER.PK_PURCHASEORG_V pk_org_v,"
          + " PO_ARRIVEORDER.PK_DEPT_V pk_dept_v , "
          + " PO_ARRIVEORDER.PK_PUPSNDOC pk_psndoc,"
          + " substring(PO_ARRIVEORDER.dbilldate,6,2) monthvalue,"
          + " substring(PO_ARRIVEORDER.dbilldate,1,10) dbilldate, "
          + " PO_ARRIVEORDER.PK_SUPPLIER PK_SUPPLIER, "
          + " po_arriveorder_b.corigcurrencyid corigcurrencyid ," // 币种
          + " 0 ordernnum, "
          + " 0 ordernmny , "
          + " 0 orderntax ,"
          + " 0 nacccancelinvmny ,"
          + " CASE WHEN po_arriveorder.bisback= 'N' then po_arriveorder_b.nnum else 0 end arrivennum,"
          + " CASE WHEN po_arriveorder.bisback= 'Y' then po_arriveorder_b.nnum else 0 end arrivebacknnum,"
          + " PO_ARRIVEORDER_b.NMNY arrivenmny ," + " 0 purnnum ,"
          + " 0 purbacknnum ," + " 0 purnmny ," + " 0 invoicennum ,"
          + " 0 invoicenmny ," + " 0 invoicetax ," + " 0 settlennum ,"
          + " 0 settlenmny ";

  private String whereConds;

  public ArriveSqlGetter(String wheresql, ConditionVO[] conditions) {
    this.whereConds = wheresql;
    this.conditions = conditions;
  }

  @Override
  public String getSql(String[] groups) {
    SqlBuilder sqlSelect = new SqlBuilder();
    sqlSelect.append(this.SELECT_Feild);
    sqlSelect.append(this.Join_23);
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
        this.whereConds.replace(JournalConstant.DBILLDATE, PUEntity.M23_H_TABLE
            + "." + ArriveHeaderVO.DBILLDATE);// 单据日期
    whereSql =
        whereSql.replace(JournalConstant.PK_ORG, PUEntity.M23_H_TABLE + "."
            + ArriveHeaderVO.PK_PURCHASEORG);// 采购组织
    whereSql =
        whereSql.replace(JournalConstant.PK_STOCKORG, PUEntity.M23_H_TABLE
            + "." + ArriveHeaderVO.PK_ORG);// 替换收货库存组织
    whereSql =
        whereSql.replace(JournalConstant.PK_PSNDOC, PUEntity.M23_H_TABLE + "."
            + ArriveHeaderVO.PK_PUPSNDOC);// 业务员
    whereSql =
        whereSql.replace(JournalConstant.PK_DEPT, PUEntity.M23_H_TABLE + "."
            + ArriveHeaderVO.PK_DEPT);// 部门
    whereSql =
        whereSql.replace(JournalConstant.VFREE, "po_arriveorder_b.vfree");// 自由辅助属性
    whereSql =
        whereSql.replace(JournalConstant.CPRODUCTORID, PUEntity.M23_B_TABLE
            + "." + ArriveItemVO.CPRODUCTORID);// 生产厂商
    whereSql =
        whereSql.replace(JournalConstant.CPROJECTID, PUEntity.M23_B_TABLE + "."
            + ArriveItemVO.CPROJECTID);// 项目
    whereSql =
        whereSql.replace(JournalConstant.PK_GROUP, PUEntity.M23_B_TABLE + "."
            + ArriveItemVO.PK_GROUP);// 项目

    return whereSql;
  }

}
