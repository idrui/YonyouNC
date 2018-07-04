package nc.impl.pu.report.praybill;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pub.smart.context.SmartContext;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.report.queryinfo.praybill.PraybillReportQueryView;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * 物料执行汇总
 * 
 * @since 6.0
 * @version 2011-8-23 上午11:19:22
 * @author liuchx
 */
public class PrayBillMarSqlGetter {

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
      + "po_praybill_b.vbdef20 vbdef20 ";

  private UFBoolean bsc;

  private String rptPermissionPart;


  private PraybillReportQueryView sqlview;
  
  public PrayBillMarSqlGetter(String bsc, String rptPermissionPart, ConditionVO[] conds) {
    this.bsc = UFBoolean.valueOf(bsc);
    this.rptPermissionPart = rptPermissionPart;
    this.sqlview = new PraybillReportQueryView(conds);
  }

  public String getPrayExe() {
    // 请购单表 po_praybill_b. po_praybill.
    String[] bids = this.queryPraybill();
    if (ArrayUtils.isEmpty(bids)) {
      return new PrayBillDetailSqlGetter(null, null).getPrayBillWhenNull();
    }

    IDExQueryBuilder sqlIn =
        new IDExQueryBuilder(PUTempTable.tmp_po_rpt_11.name());
    String sqlInString = sqlIn.buildSQL(PUEntity.M20_B_TABLE + "." + PraybillItemVO.PK_PRAYBILL_B, bids);

    String select20 =
        " select po_praybill_b.pk_praybill_b pk_praybill_b,po_praybill.vbillcode vbillcode, po_praybill.pk_org_v pk_org_v,po_praybill_b.pk_reqstor pk_reqstor,po_praybill.pk_planpsn pk_planpsn,"
            + " po_praybill.pk_plandept_v pk_plandept_v,po_praybill_b.crowno crowno,bd_material_v.pk_material pk_material, "// 2012-08-22:按最新版汇总并返回
            + " po_praybill_b.cproductorid cproductorid, po_praybill_b.cunitid cunitid, po_praybill_b.nnum nnum,"
            + " po_praybill_b.ntaxprice ntaxprice,po_praybill_b.ntaxmny ntaxmny,po_praybill_b.dreqdate dreqdate,"
            + " po_praybill_b.dsuggestdate dsuggestdate ,po_praybill_b.pk_suggestsupplier pk_suggestsupplier,po_praybill_b.cprojectid cprojectid,"
            + " po_praybill_b.csourcetypecode csourcetypecode,po_praybill_b.vsourcecode vsourcecode,"
            + " po_praybill_b.pk_purchaseorg_v pk_purchaseorg_v, "
            + " CASE WHEN po_praybill_b.browclose= 'Y' then isnull(po_praybill_b.nnum,0)-isnull(po_praybill_b.naccumulatenum,0) else 0 end close_nnum ,"
            + " null ccurrencyid , 0 order_nnum , 0 order_nmny ,0 arrive_nnum, "
            + " 0 arrive_backnnum , 0 purin_nnum , 0 purin_backnnum , 0 replenish_nnum ,"
            + PrayBillMarSqlGetter.FREE_DEF
            + " ,po_praybill_b.casscustid "
            + " from po_praybill po_praybill inner join po_praybill_b po_praybill_b "
            + " on (po_praybill.pk_praybill = po_praybill_b.pk_praybill and "
            + sqlInString
            + " )"
            + " inner join bd_material_v on bd_material_v.pk_source=po_praybill_b.pk_srcmaterial ";

    String select21 =
        " select po_praybill_b.pk_praybill_b pk_praybill_b ,po_praybill.vbillcode vbillcode, po_praybill.pk_org_v pk_org_v,po_praybill_b.pk_reqstor pk_reqstor,po_praybill.pk_planpsn pk_planpsn,"
            + " po_praybill.pk_plandept_v pk_plandept_v,po_praybill_b.crowno crowno,bd_material_v.pk_material pk_material, "// 2012-08-22:按最新版汇总并返回
            + " po_praybill_b.cproductorid cproductorid, po_praybill_b.cunitid cunitid, "
            + " 0 nnum, 0 ntaxprice,0 ntaxmny,"
            + " po_praybill_b.dreqdate dreqdate,"
            + " po_praybill_b.dsuggestdate dsuggestdate ,po_praybill_b.pk_suggestsupplier pk_suggestsupplier,po_praybill_b.cprojectid cprojectid,"
            + " po_praybill_b.csourcetypecode csourcetypecode,po_praybill_b.vsourcecode vsourcecode,"
            + " po_praybill_b.pk_purchaseorg_v pk_purchaseorg_v, "
            + " 0 close_nnum ,"
            + " null ccurrencyid , po_order_b.nnum order_nnum , po_order_b.nmny order_nmny  ,0 arrive_nnum, "
            + " 0 arrive_backnnum , 0 purin_nnum , 0 purin_backnnum , 0 replenish_nnum,"
            + PrayBillMarSqlGetter.FREE_DEF
            + " ,po_praybill_b.casscustid "
            + " from po_praybill po_praybill inner join po_praybill_b po_praybill_b "
            + " on (po_praybill.pk_praybill = po_praybill_b.pk_praybill and "
            + sqlInString
            + "  )"
            + " inner join po_order_b po_order_b "
            + " on (po_order_b.cpraybillbid = po_praybill_b.pk_praybill_b and po_order_b.dr=0 and po_order_b.fisactive in (0,1)) "
            + " inner join bd_material_v on bd_material_v.pk_source=po_praybill_b.pk_srcmaterial ";

    String select23 =
        " select po_praybill_b.pk_praybill_b pk_praybill_b ,po_praybill.vbillcode vbillcode, po_praybill.pk_org_v pk_org_v,po_praybill_b.pk_reqstor pk_reqstor,po_praybill.pk_planpsn pk_planpsn,"
            + " po_praybill.pk_plandept_v pk_plandept_v,po_praybill_b.crowno crowno,bd_material_v.pk_material pk_material, "// 2012-08-22:按最新版汇总并返回
            + " po_praybill_b.cproductorid cproductorid, po_praybill_b.cunitid cunitid, "
            + " 0 nnum, 0 ntaxprice,0 ntaxmny,"
            + " po_praybill_b.dreqdate dreqdate,"
            + " po_praybill_b.dsuggestdate dsuggestdate ,po_praybill_b.pk_suggestsupplier pk_suggestsupplier,po_praybill_b.cprojectid cprojectid,"
            + " po_praybill_b.csourcetypecode csourcetypecode,po_praybill_b.vsourcecode vsourcecode,"
            + " po_praybill_b.pk_purchaseorg_v pk_purchaseorg_v, "
            + " 0 close_nnum ,"
            + " null ccurrencyid , 0 order_nnum , 0 order_nmny,  "
            + " CASE WHEN po_arriveorder.bisback= 'N' then po_arriveorder_b.nnum else 0 end arrive_nnum,"
            + " CASE WHEN po_arriveorder.bisback= 'Y' then 0-po_arriveorder_b.nnum else 0 end arrive_backnnum,"
            + " 0 purin_nnum , 0 purin_backnnum , po_arriveorder_b.naccumreplnum replenish_nnum, "
            + PrayBillMarSqlGetter.FREE_DEF
            + " ,po_praybill_b.casscustid "
            + " from po_praybill po_praybill inner join po_praybill_b po_praybill_b "
            + " on (po_praybill.pk_praybill = po_praybill_b.pk_praybill and "
            + sqlInString
            + ")"
            + " inner join bd_material_v on bd_material_v.pk_source=po_praybill_b.pk_srcmaterial ";

    if (this.bsc.booleanValue()) {
      select23 +=
          " inner join sc_order_b sc_order_b on (sc_order_b.csrcbid = po_praybill_b.pk_praybill_b) "
              + " inner join po_arriveorder_b po_arriveorder_b on (po_arriveorder_b.csourcebid = sc_order_b.pk_order_b and po_arriveorder_b.dr=0) "
              + " inner join po_arriveorder po_arriveorder on (po_arriveorder.pk_arriveorder = po_arriveorder_b.pk_arriveorder)";
    }
    else {
      select23 +=
          " inner join po_order_b po_order_b on (po_order_b.cpraybillbid = po_praybill_b.pk_praybill_b) "
              + " inner join po_arriveorder_b po_arriveorder_b on (po_arriveorder_b.csourcebid = po_order_b.pk_order_b and po_arriveorder_b.dr=0) "
              + " inner join po_arriveorder po_arriveorder on (po_arriveorder.pk_arriveorder = po_arriveorder_b.pk_arriveorder)";
    }

    String select45 =
        " select po_praybill_b.pk_praybill_b pk_praybill_b ,po_praybill.vbillcode vbillcode, po_praybill.pk_org_v pk_org_v,po_praybill_b.pk_reqstor pk_reqstor,po_praybill.pk_planpsn pk_planpsn,"
            + " po_praybill.pk_plandept_v pk_plandept_v,po_praybill_b.crowno crowno,bd_material_v.pk_material pk_material, "// 2012-08-22:按最新版汇总并返回
            + " po_praybill_b.cproductorid cproductorid, po_praybill_b.cunitid cunitid, "
            + " 0 nnum, 0 ntaxprice,0 ntaxmny,"
            + " po_praybill_b.dreqdate dreqdate,"
            + " po_praybill_b.dsuggestdate dsuggestdate ,po_praybill_b.pk_suggestsupplier pk_suggestsupplier,po_praybill_b.cprojectid cprojectid,"
            + " po_praybill_b.csourcetypecode csourcetypecode,po_praybill_b.vsourcecode vsourcecode,"
            + " po_praybill_b.pk_purchaseorg_v pk_purchaseorg_v, "
            + " 0 close_nnum ,"
            + " null ccurrencyid , 0 order_nnum , 0 order_nmny,  "
            + " 0 arrive_nnum,"
            + " 0 arrive_backnnum,"
            + " CASE WHEN ic_purchasein_h.freplenishflag = 'N' then ic_purchasein_b.nnum else 0 end purin_nnum,"
            + " CASE WHEN ic_purchasein_h.freplenishflag = 'Y' then 0-ic_purchasein_b.nnum else 0 end purin_backnnum,"
            + " ic_purchasein_e.nreplenishednum replenish_nnum ,"
            + PrayBillMarSqlGetter.FREE_DEF
            + " ,po_praybill_b.casscustid "
            + " from po_praybill po_praybill inner join po_praybill_b po_praybill_b "
            + " on (po_praybill.pk_praybill = po_praybill_b.pk_praybill)"
            + " inner join po_order_b po_order_b "
            + " on (po_order_b.cpraybillbid = po_praybill_b.pk_praybill_b and "
            + sqlInString
            + ") "
            + " inner join ic_purchasein_b ic_purchasein_b "
            + " on ic_purchasein_b.cfirstbillbid = po_order_b.pk_order_b "
            + " inner join ic_purchasein_e ic_purchasein_e "
            + " on ic_purchasein_e.cgeneralbid =ic_purchasein_b.cgeneralbid "
            + " inner join ic_purchasein_h ic_purchasein_h "
            + " on ic_purchasein_h.cgeneralhid = ic_purchasein_b.cgeneralhid and ic_purchasein_b.dr=0 "
            + " inner join bd_material_v on bd_material_v.pk_source=po_praybill_b.pk_srcmaterial ";

    String select61 =
        " select po_praybill_b.pk_praybill_b pk_praybill_b ,po_praybill.vbillcode vbillcode, po_praybill.pk_org_v pk_org_v,po_praybill_b.pk_reqstor pk_reqstor,po_praybill.pk_planpsn pk_planpsn,"
            + " po_praybill.pk_plandept_v pk_plandept_v,po_praybill_b.crowno crowno,bd_material_v.pk_material pk_material, "// 2012-08-22:按最新版汇总并返回
            + " po_praybill_b.cproductorid cproductorid, po_praybill_b.cunitid cunitid, "
            + " 0 nnum, 0 ntaxprice,0 ntaxmny,"
            + " po_praybill_b.dreqdate dreqdate,"
            + " po_praybill_b.dsuggestdate dsuggestdate ,po_praybill_b.pk_suggestsupplier pk_suggestsupplier,po_praybill_b.cprojectid cprojectid,"
            + " po_praybill_b.csourcetypecode csourcetypecode,po_praybill_b.vsourcecode vsourcecode,"
            + " po_praybill_b.pk_purchaseorg_v pk_purchaseorg_v, "
            + " 0 close_nnum ,"
            + " null ccurrencyid , sc_order_b.nnum order_nnum , sc_order_b.nmny order_nmny  ,0 arrive_nnum, "
            + " 0 arrive_backnnum , 0 purin_nnum , 0 purin_backnnum , 0 replenish_nnum,"
            + PrayBillMarSqlGetter.FREE_DEF
            + " ,po_praybill_b.casscustid "
            + " from po_praybill po_praybill inner join po_praybill_b po_praybill_b "
            + " on (po_praybill.pk_praybill = po_praybill_b.pk_praybill and "
            + sqlInString
            + "  )"
            + " inner join sc_order_b sc_order_b "
            + " on (sc_order_b.csrcbid = po_praybill_b.pk_praybill_b and sc_order_b.dr=0) "
            + " inner join bd_material_v on bd_material_v.pk_source=po_praybill_b.pk_srcmaterial ";

    String select47 =
        " select po_praybill_b.pk_praybill_b pk_praybill_b ,po_praybill.vbillcode vbillcode, po_praybill.pk_org_v pk_org_v,po_praybill_b.pk_reqstor pk_reqstor,po_praybill.pk_planpsn pk_planpsn,"
            + " po_praybill.pk_plandept_v pk_plandept_v,po_praybill_b.crowno crowno,bd_material_v.pk_material pk_material, "// 2012-08-22:按最新版汇总并返回
            + " po_praybill_b.cproductorid cproductorid, po_praybill_b.cunitid cunitid, "
            + " 0 nnum, 0 ntaxprice,0 ntaxmny,"
            + " po_praybill_b.dreqdate dreqdate,"
            + " po_praybill_b.dsuggestdate dsuggestdate ,po_praybill_b.pk_suggestsupplier pk_suggestsupplier,po_praybill_b.cprojectid cprojectid,"
            + " po_praybill_b.csourcetypecode csourcetypecode,po_praybill_b.vsourcecode vsourcecode,"
            + " po_praybill_b.pk_purchaseorg_v pk_purchaseorg_v, "
            + " 0 close_nnum ,"
            + " null ccurrencyid , 0 order_nnum , 0 order_nmny,  "
            + " 0 arrive_nnum,"
            + " 0 arrive_backnnum,"
            + " CASE WHEN ic_subcontin_h.freplenishflag = 'N' then ic_subcontin_b.nnum else 0 end purin_nnum,"
            + " CASE WHEN ic_subcontin_h.freplenishflag = 'Y' then 0-ic_subcontin_b.nnum else 0 end purin_backnnum,"
            + " 0 replenish_nnum ,"
            + PrayBillMarSqlGetter.FREE_DEF
            + " ,po_praybill_b.casscustid "
            + " from po_praybill po_praybill inner join po_praybill_b po_praybill_b "
            + " on (po_praybill.pk_praybill = po_praybill_b.pk_praybill)"
            + " inner join sc_order_b sc_order_b "
            + " on (sc_order_b.csrcbid = po_praybill_b.pk_praybill_b and "
            + sqlInString
            + ") "
            + " inner join ic_subcontin_b ic_subcontin_b "
            + " on ic_subcontin_b.cfirstbillbid = sc_order_b.pk_order_b "
            + " inner join ic_subcontin_h ic_subcontin_h "
            + " on ic_subcontin_h.cgeneralhid = ic_subcontin_b.cgeneralhid and ic_subcontin_b.dr=0 "
            + " inner join bd_material_v on bd_material_v.pk_source=po_praybill_b.pk_srcmaterial ";

    String rets =
        select20 + " union all " + select21 + " union all " + select23
            + " union all " + select45;

    if (this.bsc.booleanValue()) {
      rets += " union all " + select61 + " union all " + select47;
    }

    return rets;
  }

  public String getReportSql(SmartContext context) {
    // 取最大行数
    // this.maxRow = RptMaxRowUtil.getMaxRow(context);
    // 请购单执行汇总汇总字段
    String group_Feild_Mar_Exe = " pk_material, cunitid, casscustid  ";
    String sum_Feild =
        " ,sum(nnum) nnum"
            + " ,sum(close_nnum) close_nnum"
            + ",sum(order_nnum) order_nnum , " // 订货主数量
            + " CASE WHEN SUM(order_nnum)= 0 THEN 0 ELSE SUM(order_nmny)/SUM(order_nnum) END nprice , "
            // 订货单价
            + "sum(order_nmny) order_nmny ,"// 订货金额
            + " sum(arrive_nnum) arrive_nnum ," // 到货主数量
            + " sum(arrive_backnnum) arrive_backnnum ,"// 退货主数量
            + " sum(purin_nnum) purin_nnum ,"// 入库主数量
            + " sum(purin_backnnum) purin_backnnum ,"// 入库主数量
            + " sum(replenish_nnum) replenish_nnum  ";

    String def =
        ",VBDEF1,VBDEF10,VBDEF11,VBDEF12,VBDEF13,VBDEF14,VBDEF15,VBDEF16,VBDEF17,VBDEF18,VBDEF19,VBDEF2,VBDEF20,VBDEF3,VBDEF4,VBDEF5,VBDEF6,VBDEF7,VBDEF8,VBDEF9,VFREE1,VFREE10,VFREE2,VFREE3,VFREE4,VFREE5,VFREE6,VFREE7,VFREE8,VFREE9,VDEF1,VDEF10,VDEF11,VDEF12,VDEF13,VDEF14,VDEF15,VDEF16,VDEF17,VDEF18,VDEF19,VDEF2,VDEF20,VDEF3,VDEF4,VDEF5,VDEF6,VDEF7,VDEF8,VDEF9";

    SqlBuilder exeSql = new SqlBuilder();
    exeSql.append("select ");
    exeSql.append(group_Feild_Mar_Exe);
    exeSql.append(def);
    exeSql.append(sum_Feild);
    exeSql.append(" from (");
    exeSql.append(this.getPrayExe());
    exeSql.append(this.rptPermissionPart);
    exeSql.append(") prayexe group by ");
    exeSql.append(group_Feild_Mar_Exe);
    exeSql.append(def);
    exeSql.append(" order by prayexe.pk_material ");
    return exeSql.toString();
  }

  private String[] queryPraybill() {
    String sql = this.sqlview.getViewSql();
    DataAccessUtils utils = new DataAccessUtils();
    // 设置最大行数
    // utils.setMaxRows(this.maxRow);
    IRowSet rs = utils.query(sql.toString());
    return rs.toOneDimensionStringArray();
  }

}
