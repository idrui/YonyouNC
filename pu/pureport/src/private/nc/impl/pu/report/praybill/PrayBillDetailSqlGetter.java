package nc.impl.pu.report.praybill;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pub.smart.context.SmartContext;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.report.queryinfo.praybill.PraybillReportQueryView;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * 请购单明细查询
 * 
 * @since 6.0
 * @version 2011-9-5 上午09:23:54
 * @author liuchx
 */
public class PrayBillDetailSqlGetter {

  final static String COMMON_JOIN_20 =
      " from po_praybill po_praybill inner join po_praybill_b po_praybill_b "
          + " on po_praybill.pk_praybill = po_praybill_b.pk_praybill "
          + " and po_praybill_b.dr=0 and po_praybill.dr = 0  "; // TODO

  final static String COMMON_JOIN_21 = " left join po_order_b po_order_b "
      + " on po_order_b.cpraybillhid = po_praybill_b.pk_praybill "
      + " and po_order_b.cpraybillbid = po_praybill_b.pk_praybill_b "
      + " and po_order_b.fisactive in (0,1) and po_order_b.dr =0 "
      + " left join po_order po_order "
      + " on po_order_b.pk_order = po_order.pk_order "
      + " and po_order.dr = 0  " + " and po_order.bislatest = 'Y' ";

  final static String COMMON_JOIN_23 =
      " inner join po_arriveorder_b po_arriveorder_b "
          + " on po_arriveorder_b.csourcebid = po_order_b.pk_order_b and po_arriveorder_b.dr = 0  "
          + " inner join po_arriveorder po_arriveorder"
          + " on po_arriveorder.pk_arriveorder = po_arriveorder_b.pk_arriveorder "
          + " and po_arriveorder.dr = 0 ";

  final static String COMMON_JOIN_23_61 =
      " left join po_arriveorder_b po_arriveorder_b "
          + " on po_arriveorder_b.csourcebid = sc_order_b.pk_order_b  and po_arriveorder_b.dr = 0 "
          + " left join po_arriveorder po_arriveorder"
          + " on po_arriveorder.pk_arriveorder = po_arriveorder_b.pk_arriveorder "
          + " and po_arriveorder.dr = 0 ";

  final static String COMMON_JOIN_45 =
      " left join ic_purchasein_b ic_purchasein_b  "
          + " on ic_purchasein_b.cfirstbillbid = po_order_b.pk_order_b and ic_purchasein_b.dr = 0 "
          + " left join ic_purchasein_h ic_purchasein_h on ic_purchasein_h.cgeneralhid = ic_purchasein_b.cgeneralhid "
          + " and ic_purchasein_h.dr = 0 ";

  final static String COMMON_JOIN_47 =
      " left join IC_SUBCONTIN_B IC_SUBCONTIN_B "
          + " on IC_SUBCONTIN_B.cfirstbillbid = sc_order_b.pk_order_b and IC_SUBCONTIN_B.dr = 0 "
          + " left join IC_SUBCONTIN_H IC_SUBCONTIN_H on IC_SUBCONTIN_H.cgeneralhid = IC_SUBCONTIN_B.cgeneralhid "
          + " and IC_SUBCONTIN_H.dr = 0 ";

  final static String COMMON_JOIN_61 =
      " left join sc_order_b sc_order_b "
          + " on sc_order_b.csrcbid = po_praybill_b.pk_praybill_b and SC_order_b.dr =0 "
          + " left join sc_order sc_order "
          + " on sc_order_b.pk_order = SC_order.pk_order "
          + " and SC_order.dr = 0 ";

  final static String COMMON_JOIN_INNER_23_45 =
      " left join ic_purchasein_b ic_purchasein_b "
          + " on ic_purchasein_b.csourcebillbid = po_arriveorder_b.pk_arriveorder_b and ic_purchasein_b.dr = 0 "
          + " left join ic_purchasein_h ic_purchasein_h on ic_purchasein_h.cgeneralhid = ic_purchasein_b.cgeneralhid "
          + " and ic_purchasein_h.dr = 0 ";

  final static String COMMON_JOIN_INNER_23_47 =
      " left join IC_SUBCONTIN_B IC_SUBCONTIN_B "
          + " on IC_SUBCONTIN_B.csourcebillbid = po_arriveorder_b.pk_arriveorder_b and IC_SUBCONTIN_B.dr = 0 "
          + " left join IC_SUBCONTIN_H IC_SUBCONTIN_H on IC_SUBCONTIN_H.cgeneralhid = IC_SUBCONTIN_B.cgeneralhid "
          + " and IC_SUBCONTIN_H.dr = 0 ";

  final static String COMMON_JOIN_INNER_45 =
      " inner join ic_purchasein_b ic_purchasein_b "
          + " on ic_purchasein_b.csourcebillbid = po_order_b.pk_order_b and ic_purchasein_b.dr = 0 "
          + " inner join ic_purchasein_e ic_purchasein_e "
          + " on ic_purchasein_e.cgeneralbid =ic_purchasein_b.cgeneralbid "
          + " inner join ic_purchasein_h ic_purchasein_h on ic_purchasein_h.cgeneralhid = ic_purchasein_b.cgeneralhid "
          + " and ic_purchasein_h.dr = 0 ";

  final static String COMMON_JOIN_INNER_47 =
      " inner join IC_SUBCONTIN_B IC_SUBCONTIN_B "
          + " on IC_SUBCONTIN_B.csourcebillbid = sc_order_b.pk_order_b and IC_SUBCONTIN_B.dr = 0 "
          + " inner join IC_SUBCONTIN_H IC_SUBCONTIN_H on IC_SUBCONTIN_H.cgeneralhid = IC_SUBCONTIN_B.cgeneralhid "
          + " and IC_SUBCONTIN_H.dr = 0 ";

  final static String COMMON_JOIN_INNERORDER =
      " from po_order po_order "
          + " inner join po_order_b po_order_b on po_order_b.pk_order=po_order.pk_order "
          + " and po_order_b.dr = 0 and po_order_b.fisactive in (0,1) ";

  final static String COMMON_JOIN_INNERSCORDER =
      " from sc_order sc_order "
          + " inner join sc_order_b sc_order_b on sc_order_b.pk_order=sc_order.pk_order "
          + " and sc_order_b.dr = 0 ";

  final static String DETAIL_20 =
      "po_praybill_b.pk_praybill_b pk_praybill_b,"
          + " po_praybill.vbillcode vbillcode,"// 请购单号
          + " po_praybill.pk_org_v pk_org_v," // 库存组织
          + " po_praybill_b.pk_reqstor pk_reqstor," // 需求仓库
          + " po_praybill.pk_planpsn pk_planpsn," // 计划员
          + " po_praybill.pk_plandept_v pk_plandept_v," // 计划部门
          + " po_praybill_b.crowno crowno," // 行号
          + " po_praybill_b.pk_material pk_material,"
          + " po_praybill_b.cproductorid cproductorid,"
          + " po_praybill_b.cunitid cunitid,"
          + " po_praybill_b.nnum nnum,"
          + " po_praybill_b.ntaxprice ntaxprice," // 主本币含税单价
          + " po_praybill_b.ntaxmny ntaxmny,"// 本币价税合计
          + " po_praybill_b.dreqdate dreqdate,"// 需求日期
          + " po_praybill_b.dsuggestdate dsuggestdate,"// 建议订货日期
          + " po_praybill_b.pk_suggestsupplier pk_suggestsupplier,"// 建议供应商
          + " po_praybill_b.cprojectid cprojectid," // 项目
          + " po_praybill_b.csourcetypecode csourcetypecode," // 来源单据类型
          + " po_praybill_b.vsourcecode vsourcecode,"// 来源单据号
          + " CASE WHEN po_praybill_b.browclose= 'Y' then isnull(po_praybill_b.nnum,0)-isnull(po_praybill_b.naccumulatenum,0) else 0 end close_nnum ,"
          + " po_praybill_b.pk_purchaseorg_v pk_purchaseorg_v," // 采购组织"
          + " po_praybill_b.casscustid ,"; // 客户

  final static String DETAIL_FEILD_INNER_ARRIC =
      " tinner.arrive_dbilldate arrive_dbilldate,"// 到货日期
          + " tinner.arrive_nnum arrive_nnum,"
          + " tinner.arrive_backnnum arrive_backnnum,"
          + " tinner.purin_dbilldate purin_dbilldate,"// 入库日期
          + " tinner.purin_nnum purin_nnum,"
          + " tinner.purin_backnnum purin_backnnum,"
          + " (tinner.arr_replenish_nnum+"// 退货补货主数量
          + "tinner.ic_replenish_nnum) replenish_nnum"; // 退库补货主数量

  final static String DETAIL_FEILD_INNER_ARRIC_SC =
      " tscinner.arrive_dbilldate arrive_dbilldate,"// 到货日期
          + " tscinner.arrive_nnum arrive_nnum,"
          + " tscinner.arrive_backnnum arrive_backnnum,"
          + " tscinner.purin_dbilldate purin_dbilldate,"// 入库日期
          + " tscinner.purin_nnum purin_nnum,"
          + " tscinner.purin_backnnum purin_backnnum,"
          + " null replenish_nnum ";

  final static String DETAIL_FEILD_INNER_ORDER =
      " po_order_b.ccurrencyid ccurrencyid," // 结算财务组织本位币
          + " po_order.pk_supplier order_pk_supplier," // 供应商
          + " po_order.vbillcode order_vbillcode," // 订单号
          + " po_order.dbilldate order_dbilldate," // 订单日期
          + " po_order_b.pk_material order_material,"// 订单物料编码
          + " po_order_b.nnum order_nnum,"// 订货主数量
          + " po_order_b.nprice nprice,"// 订货单价
          + " po_order_b.nmny order_nmny,"// 订货金额
          + " po_order_b.dplanarrvdate dplanarrvdate, ";// 计划到货日期

  final static String DETAIL_FEILD_INNER_SCORDER =
      " sc_order.ccurrencyid ccurrencyid," // 结算财务组织本位币
          + " sc_order.pk_supplier order_pk_supplier," // 供应商
          + " sc_order.vbillcode order_vbillcode," // 订单号
          + " sc_order.dbilldate order_dbilldate," // 订单日期
          + " sc_order_b.pk_material order_material,"// 订单物料编码
          + " sc_order_b.nnum order_nnum,"// 订货主数量
          + " sc_order_b.nprice nprice,"// 订货单价
          + " sc_order_b.nmny order_nmny,"// 订货金额
          + " dplanarrvdate dplanarrvdate, ";// 计划到货日期

  final static String DETAIL_FEILD_ORDER =
      " po_order_b.ccurrencyid ccurrencyid," // 结算财务组织本位币
          + " po_order.pk_supplier order_pk_supplier," // 供应商
          + " po_order.vbillcode order_vbillcode," // 订单号
          + " po_order.dbilldate order_dbilldate," // 订单日期
          + " po_order_b.pk_material order_material,"// 订单物料编码
          + " po_order_b.nnum order_nnum,"// 订货主数量
          + " po_order_b.nprice nprice,"// 订货单价
          + " po_order_b.nmny order_nmny,"// 订货金额
          + " po_order_b.dplanarrvdate dplanarrvdate,"// 计划到货日期
          + " po_arriveorder.dbilldate arrive_dbilldate,"// 到货日期
          + " CASE WHEN po_arriveorder.bisback= 'N' then po_arriveorder_b.nnum else 0 end arrive_nnum,"
          + " CASE WHEN po_arriveorder.bisback= 'Y' then 0-po_arriveorder_b.nnum else 0 end arrive_backnnum,"
          + " ic_purchasein_h.dbilldate purin_dbilldate,"// 入库日期
          + " CASE WHEN ic_purchasein_h.freplenishflag = 'N' then ic_purchasein_b.nnum else 0 end purin_nnum,"
          + " CASE WHEN ic_purchasein_h.freplenishflag = 'Y' then 0-ic_purchasein_b.nnum else 0 end purin_backnnum,"
          + "  po_arriveorder_b.naccumreplnum replenish_nnum ";// 补货主数量

  final static String DETAIL_FEILD_ORDER_INNER_23 =
      " po_order_b.pk_order_b corderbid ," // 来源
          + " po_arriveorder.dbilldate arrive_dbilldate,"// 到货日期
          + " CASE WHEN po_arriveorder.bisback= 'N' then po_arriveorder_b.nnum else 0 end arrive_nnum,"
          + " CASE WHEN po_arriveorder.bisback= 'Y' then 0-po_arriveorder_b.nnum else 0 end arrive_backnnum,"
          + " ic_purchasein_h.dbilldate purin_dbilldate,"// 入库日期
          + " CASE WHEN ic_purchasein_h.freplenishflag = 'N' then ic_purchasein_b.nnum else 0 end purin_nnum,"
          + " CASE WHEN ic_purchasein_h.freplenishflag = 'Y' then 0-ic_purchasein_b.nnum else 0 end purin_backnnum,"
          + "  isnull(po_arriveorder_b.naccumreplnum,0) arr_replenish_nnum, "// 补货主数量
          + " 0 ic_replenish_nnum";

  final static String DETAIL_FEILD_ORDER_INNER_45 =
      " po_order_b.pk_order_b corderbid ," // 来源
          + " null arrive_dbilldate,"// 到货日期
          + " 0 arrive_nnum,"
          + " 0 arrive_backnnum,"
          + " ic_purchasein_h.dbilldate purin_dbilldate,"// 入库日期
          + " CASE WHEN ic_purchasein_h.freplenishflag = 'N' then ic_purchasein_b.nnum else 0 end purin_nnum,"
          + " CASE WHEN ic_purchasein_h.freplenishflag = 'Y' then 0-ic_purchasein_b.nnum else 0 end purin_backnnum,"
          + " 0 arr_replenish_nnum,"
          + "  isnull(ic_purchasein_e.nreplenishednum,0) ic_replenish_nnum ";// 补货主数量

  final static String DETAIL_FEILD_SCORDER =
      " sc_order.ccurrencyid ccurrencyid," // 结算财务组织本位币
          + " sc_order.pk_supplier order_pk_supplier," // 供应商
          + " sc_order.vbillcode order_vbillcode," // 订单号
          + " sc_order.dbilldate order_dbilldate," // 订单日期
          + " sc_order_b.pk_material order_material,"// 订单物料编码
          + " sc_order_b.nnum order_nnum,"// 订货主数量
          + " sc_order_b.nprice nprice,"// 订货单价
          + " sc_order_b.nmny order_nmny,"// 订货金额
          + " dplanarrvdate dplanarrvdate,"// 计划到货日期
          + " po_arriveorder.dbilldate arrive_dbilldate,"// 到货日期
          + " CASE WHEN po_arriveorder.bisback= 'N' then po_arriveorder_b.nnum else 0 end arrive_nnum,"
          + " CASE WHEN po_arriveorder.bisback= 'Y' then 0-po_arriveorder_b.nnum else 0 end arrive_backnnum,"
          + " IC_SUBCONTIN_H.dbilldate purin_dbilldate,"// 入库日期
          + " CASE WHEN IC_SUBCONTIN_H.freplenishflag = 'N' then IC_SUBCONTIN_B.nnum else 0 end purin_nnum,"
          + " CASE WHEN IC_SUBCONTIN_H.freplenishflag = 'Y' then 0-IC_SUBCONTIN_B.nnum else 0 end purin_backnnum,"
          + "  po_arriveorder_b.naccumreplnum  replenish_nnum ";// 补货主数量

  final static String DETAIL_FEILD_SCORDER_INNER_23 =
      " sc_order_b.pk_order_b cscorderbid ," // 来源
          + " po_arriveorder.dbilldate arrive_dbilldate,"// 到货日期
          + " CASE WHEN po_arriveorder.bisback= 'N' then po_arriveorder_b.nnum else 0 end arrive_nnum,"
          + " CASE WHEN po_arriveorder.bisback= 'Y' then 0-po_arriveorder_b.nnum else 0 end arrive_backnnum,"
          + " IC_SUBCONTIN_H.dbilldate purin_dbilldate,"// 入库日期
          + " CASE WHEN IC_SUBCONTIN_H.freplenishflag = 'N' then IC_SUBCONTIN_B.nnum else 0 end purin_nnum,"
          + " CASE WHEN IC_SUBCONTIN_H.freplenishflag = 'Y' then 0-IC_SUBCONTIN_B.nnum else 0 end purin_backnnum,"
          + "  null replenish_nnum ";// 补货主数量

  final static String DETAIL_FEILD_SCORDER_INNER_47 =
      " sc_order_b.pk_order_b cscorderbid ," // 来源
          + " null arrive_dbilldate,"// 到货日期
          + " 0 arrive_nnum,"
          + " 0 arrive_backnnum,"
          + " IC_SUBCONTIN_H.dbilldate purin_dbilldate,"// 入库日期
          + " CASE WHEN IC_SUBCONTIN_H.freplenishflag = 'N' then IC_SUBCONTIN_B.nnum else 0 end purin_nnum,"
          + " CASE WHEN IC_SUBCONTIN_H.freplenishflag = 'Y' then 0-IC_SUBCONTIN_B.nnum else 0 end purin_backnnum,"
          + " null replenish_nnum ";

  final static String FREE_DEF = "po_praybill.vdef1 vdef1,"
      + "po_praybill.vdef2 vdef2," + "po_praybill.vdef3 vdef3,"
      + "po_praybill.vdef4 vdef4," + "po_praybill.vdef5 vdef5,"
      + "po_praybill.vdef6 vdef6," + "po_praybill.vdef7 vdef7,"
      + "po_praybill.vdef8 vdef8," + "po_praybill.vdef9 vdef9,"
      + "po_praybill.vdef10 vdef10," + "po_praybill.vdef11 vdef11,"
      + "po_praybill.vdef12 vdef12," + "po_praybill.vdef13 vdef13,"
      + "po_praybill.vdef14 vdef14," + "po_praybill.vdef15 vdef15,"
      + "po_praybill.vdef16 vdef16," + "po_praybill.vdef17 vdef17,"
      + "po_praybill.vdef18 vdef18," + "po_praybill.vdef19 vdef19,"
      + "po_praybill.vdef20 vdef20," + "po_praybill_b.vfree1 vfree1,"
      + "po_praybill_b.vfree2 vfree2," + "po_praybill_b.vfree3 vfree3,"
      + "po_praybill_b.vfree4 vfree4," + "po_praybill_b.vfree5 vfree5,"
      + "po_praybill_b.vfree6 vfree6," + "po_praybill_b.vfree7 vfree7,"
      + "po_praybill_b.vfree8 vfree8," + "po_praybill_b.vfree9 vfree9,"
      + "po_praybill_b.vfree10 vfree10," + "po_praybill_b.vbdef1 vbdef1,"
      + "po_praybill_b.vbdef2 vbdef2," + "po_praybill_b.vbdef3 vbdef3,"
      + "po_praybill_b.vbdef4 vbdef4," + "po_praybill_b.vbdef5 vbdef5,"
      + "po_praybill_b.vbdef6 vbdef6," + "po_praybill_b.vbdef7 vbdef7,"
      + "po_praybill_b.vbdef8 vbdef8," + "po_praybill_b.vbdef9 vbdef9,"
      + "po_praybill_b.vbdef10 vbdef10," + "po_praybill_b.vbdef11 vbdef11,"
      + "po_praybill_b.vbdef12 vbdef12," + "po_praybill_b.vbdef13 vbdef13,"
      + "po_praybill_b.vbdef14 vbdef14," + "po_praybill_b.vbdef15 vbdef15,"
      + "po_praybill_b.vbdef16 vbdef16," + "po_praybill_b.vbdef17 vbdef17,"
      + "po_praybill_b.vbdef18 vbdef18," + "po_praybill_b.vbdef19 vbdef19,"
      + "po_praybill_b.vbdef20 vbdef20 , ";

  private String bsc;
  
  private PraybillReportQueryView sqlview;
  
  public PrayBillDetailSqlGetter(String bsc, ConditionVO[] conds) {
    this.bsc = bsc;
    if(conds != null){
      this.sqlview = new PraybillReportQueryView(conds);
    }
  }

  public String getPrayBillWhenNull() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select ");
    sql.append(PrayBillDetailSqlGetter.DETAIL_20);
    sql.append(PrayBillDetailSqlGetter.FREE_DEF);
    sql.append(PrayBillDetailSqlGetter.DETAIL_FEILD_ORDER);
    sql.append(PrayBillDetailSqlGetter.COMMON_JOIN_20);
    sql.append(" and 1=2 ");
    sql.append(PrayBillDetailSqlGetter.COMMON_JOIN_21);
    sql.append(PrayBillDetailSqlGetter.COMMON_JOIN_23);
    sql.append(PrayBillDetailSqlGetter.COMMON_JOIN_45);
    return sql.toString();
  }

  /**
   * @return
   */
  public String getReportSql(SmartContext context) {
    // 取最大行数
    // this.maxRow = RptMaxRowUtil.getMaxRow(context);
    if (this.bsc.equalsIgnoreCase("N")) {
      return this.getSqlFromOrder();
    }
    if (this.bsc.equalsIgnoreCase("Y")) {
      return this.getSqlFromSCOrder();
    }
    if (this.bsc.equalsIgnoreCase("ALL")) {
      SqlBuilder sqlUnion = new SqlBuilder();
      sqlUnion.append(this.getSqlFromOrder());
      if (SysInitGroupQuery.isSCEnabled()) {
        sqlUnion.append(" union all ");
        sqlUnion.append(this.getSqlFromSCOrder());
      }
      return sqlUnion.toString();

    }
    return null;
  }

  private String getSqlFromOrder() {
    String[] bids = this.queryPraybill(UFBoolean.FALSE);
    if (ArrayUtils.isEmpty(bids)) {
      return this.getPrayBillWhenNull();
    }
    SqlBuilder sql = new SqlBuilder();

    sql.append(" select ");
    sql.append(PrayBillDetailSqlGetter.DETAIL_20);
    sql.append(PrayBillDetailSqlGetter.FREE_DEF);
    sql.append(PrayBillDetailSqlGetter.DETAIL_FEILD_INNER_ORDER);
    sql.append(PrayBillDetailSqlGetter.DETAIL_FEILD_INNER_ARRIC);
    sql.append(PrayBillDetailSqlGetter.COMMON_JOIN_20);
    sql.append(" and ");

    IDExQueryBuilder sqlIn =
        new IDExQueryBuilder(PUTempTable.tmp_po_rpt_07.name());
    String bidSql = sqlIn.buildSQL(" ", bids);

    String sqlInString = PraybillItemVO.PK_PRAYBILL_B + bidSql;
    sql.append(sqlInString);
    String orderInString = "po_order_b.cpraybillbid" + bidSql;

    sql.append(PrayBillDetailSqlGetter.COMMON_JOIN_21);
    String sqlinner = this.getSqlInnerOrder(orderInString);
    sql.append(" left join ");
    sql.append(sqlinner);
    sql.append(" on tinner.corderbid = po_order_b.pk_order_b ");

    return sql.toString();
  }

  private String getSqlFromSCOrder() {
    String[] bids = this.queryPraybill(UFBoolean.TRUE);
    if (ArrayUtils.isEmpty(bids)) {
      return this.getPrayBillWhenNull();
    }

    SqlBuilder sql = new SqlBuilder();
    sql.append(" select ");
    sql.append(PrayBillDetailSqlGetter.DETAIL_20);
    sql.append(PrayBillDetailSqlGetter.FREE_DEF);
    sql.append(PrayBillDetailSqlGetter.DETAIL_FEILD_INNER_SCORDER);
    sql.append(PrayBillDetailSqlGetter.DETAIL_FEILD_INNER_ARRIC_SC);
    sql.append(PrayBillDetailSqlGetter.COMMON_JOIN_20);
    sql.append(" and ");

    IDExQueryBuilder sqlIn =
        new IDExQueryBuilder(PUTempTable.tmp_po_rpt_08.name());
    String bidCond = sqlIn.buildSQL(" ", bids);

    String sqlInString = PraybillItemVO.PK_PRAYBILL_B + bidCond;
    sql.append(sqlInString);

    String scorderInString = "sc_order_b.csrcbid" + bidCond;

    SqlBuilder scsql = new SqlBuilder();
    scsql.append("(");
    scsql.append("select sc_order.pk_order ");
    scsql.append(" from sc_order sc_order inner join sc_order_b sc_order_b ");
    scsql.append(" on sc_order.pk_order=sc_order_b.pk_order ");
    scsql.append(" where sc_order.dr = 0 and sc_order_b.dr = 0 ");
    scsql.append(" and sc_order.blatestflag", UFBoolean.TRUE);
    scsql.append(" and " + scorderInString);
    scsql.append(")");
    String common_join_61 =
        " left join sc_order_b sc_order_b "
            + " on sc_order_b.csrcid = po_praybill_b.pk_praybill "
            + " and sc_order_b.csrcbid = po_praybill_b.pk_praybill_b "
            + " and sc_order_b.pk_order in (" + scsql + " ) "
            + " left join sc_order sc_order "
            + " on sc_order_b.pk_order = SC_order.pk_order "
            + " and SC_order.dr = 0 and SC_order_b.dr =0 ";
    sql.append(common_join_61);

    String sqlinner = this.getSqlInnerSCOrder(scorderInString);
    sql.append(" left join ");
    sql.append(sqlinner);
    sql.append(" on tscinner.cscorderbid = sc_order_b.pk_order_b ");
    return sql.toString();
  }

  private String getSqlInnerOrder(String orderInString) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" (");
    sql.append(" select ");
    sql.append(PrayBillDetailSqlGetter.DETAIL_FEILD_ORDER_INNER_23);
    sql.append(PrayBillDetailSqlGetter.COMMON_JOIN_INNERORDER);
    sql.append(" and " + orderInString);
    sql.append(PrayBillDetailSqlGetter.COMMON_JOIN_23);
    sql.append(PrayBillDetailSqlGetter.COMMON_JOIN_INNER_23_45);

    sql.append(" union all ");

    sql.append(" select ");
    sql.append(PrayBillDetailSqlGetter.DETAIL_FEILD_ORDER_INNER_45);
    sql.append(PrayBillDetailSqlGetter.COMMON_JOIN_INNERORDER);
    sql.append(" and ");
    sql.append(orderInString);
    sql.append(PrayBillDetailSqlGetter.COMMON_JOIN_INNER_45);
    sql.append(") tinner");
    return sql.toString();
  }

  private String getSqlInnerSCOrder(String orderInString) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" (");
    sql.append(" select ");
    sql.append(PrayBillDetailSqlGetter.DETAIL_FEILD_SCORDER_INNER_23);
    sql.append(PrayBillDetailSqlGetter.COMMON_JOIN_INNERSCORDER);
    sql.append(" and " + orderInString);
    sql.append(PrayBillDetailSqlGetter.COMMON_JOIN_23_61);
    sql.append(PrayBillDetailSqlGetter.COMMON_JOIN_INNER_23_47);
    sql.append(" union all ");
    sql.append(" select ");
    sql.append(PrayBillDetailSqlGetter.DETAIL_FEILD_SCORDER_INNER_47);
    sql.append(PrayBillDetailSqlGetter.COMMON_JOIN_INNERSCORDER);
    sql.append(" and " + orderInString);
    sql.append(PrayBillDetailSqlGetter.COMMON_JOIN_INNER_47);
    sql.append(") tscinner ");
    return sql.toString();
  }

  private String[] queryPraybill(UFBoolean isbsc) {
    this.sqlview.setBsctype(isbsc);
    String sql = this.sqlview.getViewSql();
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rs = utils.query(sql.toString());
    return rs.toOneDimensionStringArray();
  }


}
