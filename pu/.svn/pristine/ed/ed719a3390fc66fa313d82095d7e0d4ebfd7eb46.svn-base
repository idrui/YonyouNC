package nc.bs.pu.m25.ap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.arap.termitem.PaymentDateVO;
import nc.vo.arap.termitem.PaymentDateVO.AccountDate;
import nc.vo.bd.payment.IPaymentUtil;
import nc.vo.ct.entity.CtAbstractVO;
import nc.vo.ct.enumeration.CtTable;
import nc.vo.ct.purdaily.entity.CtPuVO;
import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.IBillType;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.collections.CollectionUtils;

/**
 * 给财务的应付服务，根据付款协议，查询各种起算（生效）日期
 * 
 * @since 6.0
 * @version 2011-8-29 下午08:44:40
 * @author zhaoyha
 */
public class PaytermBeginDateQuery {
  private final static String STOREH_NAME = "ic_purchasein_h";

  /**
   * 根据财务的收付协议起算日期VO，查询对应的业务（单据）日期
   * 
   * @param pdvoList
   * @return
   *         Map{key=应付单来源bid(nc.vo.arap.termitem.PaymentDateVO.itemid)+日期类型枚举名称
   *         （
   *         nc.vo .arap.termitem.PaymentDateVO.AccountDate.name()）,<br>
   *         value=相应的业务（单据）日期(可能为null)}
   */
  public Map<String, UFDate> query(List<PaymentDateVO> pdvoList) {
    Map<String, UFDate> pdateMap = new HashMap<String, UFDate>();
    if (CollectionUtils.isEmpty(pdvoList)) {
      return pdateMap;
    }
    // 得到统一的一个查询SQL
    SqlBuilder sql = this.getSql(pdvoList);
    // sql可能为空
    // 比如付款协议是发票审核日期或者发票日期，因为暂估应付单传的参数是45，所以拼的sql就为空
    if (sql.toString().length() == 0) {
      return pdateMap;
    }
    IRowSet rs = new DataAccessUtils().query(sql.toString());
    while (rs.next()) {
      // 生成返回MAP，可能有多余entry
      this.addPDateMap(pdateMap, rs);
    }
    return pdateMap;
  }

  private void addPDateMap(Map<String, UFDate> pdateMap, IRowSet rs) {
    String bid = rs.getString(0);
    UFDate date = null;
    // 合同生效日期
    date = rs.getUFDate(1);
    this.addPDateMap(pdateMap, bid, date,
        IPaymentUtil.PURCHASE_CONTRACT_EFFECTIVE_DATE);
    // 采购订单审批日期
    date = rs.getUFDate(2);
    this.addPDateMap(pdateMap, bid, date,
        IPaymentUtil.PURCHASE_ORDER_APPROVE_DATE);
    // 到货单审批日期
    date = rs.getUFDate(3);
    this.addPDateMap(pdateMap, bid, date, IPaymentUtil.RECEIPT_APPROVE_DATE);
    // 入库日期
    date = rs.getUFDate(4);
    this.addPDateMap(pdateMap, bid, date, IPaymentUtil.STORE_RECEIPT_DATE);
    // 入库单签字日期
    date = rs.getUFDate(5);
    this.addPDateMap(pdateMap, bid, date,
        IPaymentUtil.STORE_RECEIPT_SIGNATURE_DATE);
    // 发票日期
    date = rs.getUFDate(6);
    this.addPDateMap(pdateMap, bid, date, IPaymentUtil.PURCHASE_INVOICE_DATE);
    // 发票审批日期
    date = rs.getUFDate(7);
    this.addPDateMap(pdateMap, bid, date,
        IPaymentUtil.PURCHASE_INVOICE_APPROVE_DATE);
  }

  private void addPDateMap(Map<String, UFDate> pdateMap, String bid,
      UFDate date, String datetype) {
    if (null != date) {
      pdateMap.put(bid + datetype, date);
    }
  }

  private SqlBuilder fromArriveStorePart() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(PUEntity.M23_H_TABLE + " arriveh  inner join ");
    sql.append(PUEntity.PurchaseinFI_B_TAB + " storeb on arriveh."
        + ArriveHeaderVO.PK_ARRIVEORDER);
    sql.append("=storeb." + PurchaseinFIItemVO.CSOURCEID + " ");
    return sql;
  }

  private SqlBuilder fromCtPoPart() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(PUEntity.M21_B_TABLE + " pob  inner join ");
    sql.append(CtTable.ct_pu.name() + " cth on cth." + CtPuVO.PK_CT_PU);
    sql.append("=pob." + OrderItemVO.CCONTRACTID + " ");
    return sql;
  }

  private SqlBuilder fromStorePart() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(PaytermBeginDateQuery.STOREH_NAME + " storeh inner join ");
    sql.append(PUEntity.PurchaseinFI_B_TAB + " storeb on storeh."
        + MetaNameConst.CGENERALHID);
    sql.append("=storeb." + PurchaseinFIItemVO.PK_STOCKPS + " ");
    return sql;
  }

  private SqlBuilder getArriveDateSql4Invoice(List<PaymentDateVO> pdvoList,
      String bidInWhere) {
    SqlBuilder sql = new SqlBuilder();
    if (this.needQuery(pdvoList, AccountDate.RECEIPT_APPROVE_DATE)) {
      sql.append(this.selectInvoiceBID());
      sql.append(this.selectArriveDatePart());
      sql.append("from " + this.fromArriveStorePart() + " inner join   ");
      sql.append(PUEntity.M25_B_TAB + " invoiceb on ");
      sql.append("invoiceb." + InvoiceItemVO.CSOURCEBID + "=");
      sql.append("storeb." + PurchaseinFIItemVO.PK_STOCKPS_B + " ");
      sql.append("where storeb.dr=0 and arriveh.dr=0 and invoiceb.dr=0");
      sql.append(" and " + bidInWhere);
    }
    return sql;
  }

  private SqlBuilder getArriveDateSql4PurchsIn(List<PaymentDateVO> pdvoList,
      String bidInWhere) {
    SqlBuilder sql = new SqlBuilder();
    if (this.needQuery(pdvoList, AccountDate.RECEIPT_APPROVE_DATE)) {
      sql.append(this.selectPurchsInBID());
      sql.append(this.selectArriveDatePart());
      sql.append("from " + this.fromArriveStorePart() + "  ");
      sql.append("where storeb.dr=0 and arriveh.dr=0 ");
      sql.append(" and " + bidInWhere);
    }
    return sql;
  }

  private String[] getBID(List<PaymentDateVO> pdvoList) {
    Set<String> bidset = new HashSet<String>();
    for (PaymentDateVO pdvo : pdvoList) {
      bidset.add(pdvo.getItemid());
    }
    return bidset.toArray(new String[bidset.size()]);
  }

  private SqlBuilder getCTDateSql4Invoice(List<PaymentDateVO> pdvoList,
      String bidInWhere) {
    SqlBuilder sql = new SqlBuilder();
    if (this.isCtEnable()
        && this.needQuery(pdvoList,
            AccountDate.PURCHASE_CONTRACT_EFFECTIVE_DATE)) {
      sql.append(this.selectInvoiceBID());
      sql.append(this.selectCTDatePart());
      sql.append("from " + this.fromCtPoPart() + " inner join ");
      sql.append(PUEntity.M25_B_TAB + " invoiceb on pob."
          + OrderItemVO.PK_ORDER_B);
      sql.append("=invoiceb." + InvoiceItemVO.PK_ORDER_B + " ");
      sql.append("where invoiceb.dr=0 and pob.dr=0 and cth.dr=0");
      sql.append(" and " + bidInWhere);
    }
    return sql;
  }

  private SqlBuilder getCTDateSql4PurchsIn(List<PaymentDateVO> pdvoList,
      String bidInWhere) {
    SqlBuilder sql = new SqlBuilder();
    if (this.isCtEnable()
        && this.needQuery(pdvoList,
            AccountDate.PURCHASE_CONTRACT_EFFECTIVE_DATE)) {
      sql.append(this.selectPurchsInBID());
      sql.append(this.selectCTDatePart());
      sql.append("from " + this.fromCtPoPart() + " inner join ");
      sql.append(PUEntity.PurchaseinFI_B_TAB + " storeb on pob."
          + OrderItemVO.PK_ORDER_B);
      sql.append("=storeb." + PurchaseinFIItemVO.PK_ORDER_B + " ");
      sql.append("where storeb.dr=0 and pob.dr=0 and cth.dr=0");
      sql.append(" and " + bidInWhere);
    }
    return sql;
  }

  private SqlBuilder getInvoiceDateSql(List<PaymentDateVO> pdvoList,
      String bidInWhere) {
    SqlBuilder sql = new SqlBuilder();
    if (this.needQuery(pdvoList, AccountDate.PURCHASE_INVOICE_DATE)
        || this.needQuery(pdvoList, AccountDate.PURCHASE_INVOICE_APPROVE_DATE)) {
      sql.append(this.selectInvoiceBID());
      sql.append("null,null,null,null,null,");
      sql.append("invoiceh." + InvoiceHeaderVO.DBILLDATE);
      sql.append(",invoiceh." + InvoiceHeaderVO.TAUDITTIME + " ");
      sql.append("from " + PUEntity.M25_H_TAB + " invoiceh inner join ");
      sql.append(PUEntity.M25_B_TAB + " invoiceb on ");
      sql.append("invoiceh." + InvoiceHeaderVO.PK_INVOICE + "=");
      sql.append("invoiceb." + InvoiceItemVO.PK_INVOICE + " ");
      sql.append("where invoiceb.dr=0 and invoiceh.dr=0 ");
      sql.append(" and " + bidInWhere);
    }
    return sql;
  }

  private SqlBuilder getInvoiceSql(List<PaymentDateVO> pdvoList) {
    List<PaymentDateVO> invcPdLst =
        this.getPdVOByBilltype(pdvoList, POBillType.Invoice);
    if (CollectionUtils.isEmpty(invcPdLst)) {
      return null;
    }
    String[] invcBids = this.getBID(invcPdLst);
    String bidInWhere =
        new IDExQueryBuilder(PUTempTable.tmp_po_25_01.name()).buildAnotherSQL(
            "invoiceb." + InvoiceItemVO.PK_INVOICE_B, invcBids);
    SqlBuilder sql = new SqlBuilder();
    // 查询合同日期
    SqlBuilder queryDateSql = this.getCTDateSql4Invoice(invcPdLst, bidInWhere);
    this.unionAll(sql, queryDateSql);
    // 订单日期
    queryDateSql = this.getPODateSql4Invoice(invcPdLst, bidInWhere);
    this.unionAll(sql, queryDateSql);
    // 到货日期
    queryDateSql = this.getArriveDateSql4Invoice(invcPdLst, bidInWhere);
    this.unionAll(sql, queryDateSql);
    // 入库、入库签字日期
    queryDateSql = this.getStoreDateSql4Invoice(invcPdLst, bidInWhere);
    this.unionAll(sql, queryDateSql);
    // 发票、发票审批日期
    queryDateSql = this.getInvoiceDateSql(invcPdLst, bidInWhere);
    this.unionAll(sql, queryDateSql);
    return sql;
  }

  private List<PaymentDateVO> getPdVOByBilltype(List<PaymentDateVO> pdvoList,
      IBillType billType) {
    List<PaymentDateVO> filterPdvoLst = new ArrayList<PaymentDateVO>();
    for (PaymentDateVO pdvo : pdvoList) {
      if (billType.getCode().equals(pdvo.getBilltype())) {
        filterPdvoLst.add(pdvo);
      }
    }
    return filterPdvoLst;
  }

  private SqlBuilder getPODateSql4Invoice(List<PaymentDateVO> pdvoList,
      String bidInWhere) {
    SqlBuilder sql = new SqlBuilder();
    if (this.needQuery(pdvoList, AccountDate.PURCHASE_ORDER_APPROVE_DATE)) {
      sql.append(this.selectInvoiceBID());
      sql.append(this.selectPODatePart());
      sql.append("from " + PUEntity.M21_H_TABLE + " poh inner join ");
      sql.append(PUEntity.M25_B_TAB + " invoiceb on poh."
          + OrderHeaderVO.PK_ORDER);
      sql.append("=invoiceb." + InvoiceItemVO.PK_ORDER + " ");
      sql.append("where invoiceb.dr=0 and poh.dr=0 ");
      sql.append(" and " + bidInWhere);
    }
    return sql;
  }

  private SqlBuilder getPODateSql4PurchsIn(List<PaymentDateVO> pdvoList,
      String bidInWhere) {
    SqlBuilder sql = new SqlBuilder();
    if (this.needQuery(pdvoList, AccountDate.PURCHASE_ORDER_APPROVE_DATE)) {
      sql.append(this.selectPurchsInBID());
      sql.append(this.selectPODatePart());
      sql.append("from " + PUEntity.M21_H_TABLE + " poh inner join ");
      sql.append(PUEntity.PurchaseinFI_B_TAB + " storeb on poh."
          + OrderHeaderVO.PK_ORDER);
      sql.append("=storeb." + PurchaseinFIItemVO.PK_ORDER + " ");
      sql.append("where storeb.dr=0 and poh.dr=0 ");
      sql.append(" and " + bidInWhere);
    }
    return sql;
  }

  private SqlBuilder getPurchsInSql(List<PaymentDateVO> pdvoList) {
    List<PaymentDateVO> purinLst =
        this.getPdVOByBilltype(pdvoList, ICBillType.PurchaseIn);
    if (CollectionUtils.isEmpty(purinLst)) {
      return null;
    }
    String[] purinBids = this.getBID(purinLst);
    String bidInWhere =
        new IDExQueryBuilder(PUTempTable.tmp_po_25_02.name()).buildSQL(
            "storeb." + PurchaseinFIItemVO.PK_STOCKPS_B, purinBids);
    SqlBuilder sql = new SqlBuilder();
    // 查询合同日期
    SqlBuilder queryDateSql = this.getCTDateSql4PurchsIn(purinLst, bidInWhere);
    this.unionAll(sql, queryDateSql);
    // 订单日期
    queryDateSql = this.getPODateSql4PurchsIn(purinLst, bidInWhere);
    this.unionAll(sql, queryDateSql);
    // 到货日期
    queryDateSql = this.getArriveDateSql4PurchsIn(purinLst, bidInWhere);
    this.unionAll(sql, queryDateSql);
    // 入库、入库签字日期
    queryDateSql = this.getStoreDateSql(purinLst, bidInWhere);
    this.unionAll(sql, queryDateSql);
    return sql;
  }

  private SqlBuilder getSql(List<PaymentDateVO> pdvoList) {
    SqlBuilder sqlPurin = this.getPurchsInSql(pdvoList);
    SqlBuilder sqlInvoice = this.getInvoiceSql(pdvoList);
    SqlBuilder sql = new SqlBuilder();
    this.unionAll(sql, sqlPurin);
    this.unionAll(sql, sqlInvoice);
    return sql;
  }

  private SqlBuilder getStoreDateSql(List<PaymentDateVO> pdvoList,
      String bidInWhere) {
    SqlBuilder sql = new SqlBuilder();
    if (this.needQuery(pdvoList, AccountDate.STORE_RECEIPT_DATE)
        || this.needQuery(pdvoList, AccountDate.STORE_RECEIPT_SIGNATURE_DATE)) {
      sql.append(this.selectPurchsInBID());
      sql.append(this.selectStoreDatePart());
      sql.append("from " + this.fromStorePart() + " ");
      sql.append("where storeb.dr=0 and storeh.dr=0 ");
      sql.append(" and " + bidInWhere);
    }
    return sql;
  }

  private SqlBuilder getStoreDateSql4Invoice(List<PaymentDateVO> pdvoList,
      String bidInWhere) {
    SqlBuilder sql = new SqlBuilder();
    if (this.needQuery(pdvoList, AccountDate.STORE_RECEIPT_DATE)
        || this.needQuery(pdvoList, AccountDate.STORE_RECEIPT_SIGNATURE_DATE)) {
      sql.append(this.selectInvoiceBID());
      sql.append(this.selectStoreDatePart());
      sql.append("from " + this.fromStorePart() + "  inner join ");
      sql.append(PUEntity.M25_B_TAB + " invoiceb on ");
      sql.append("invoiceb." + InvoiceItemVO.CSOURCEBID + "=");
      sql.append("storeb." + PurchaseinFIItemVO.PK_STOCKPS_B + " ");
      sql.append("where storeb.dr=0 and storeh.dr=0 and invoiceb.dr=0 ");
      sql.append(" and " + bidInWhere);
    }
    return sql;
  }

  private boolean isCtEnable() {
    return SysInitGroupQuery.isCTEnabled();
  }

  private boolean needQuery(List<PaymentDateVO> pdvoList, AccountDate datetype) {
    for (PaymentDateVO pdvo : pdvoList) {
      if (AccountDate.valueOf(pdvo.getDateType()) == datetype) {
        return true;
      }
    }
    return false;
  }

  private SqlBuilder selectArriveDatePart() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("null,null,arriveh." + ArriveHeaderVO.TAUDITTIME);
    sql.append(",null,null,null,null ");
    return sql;
  }

  private SqlBuilder selectCTDatePart() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("cth." + CtAbstractVO.ACTUALVALIDATE);
    sql.append(",null,null,null,null,null,null ");
    return sql;
  }

  private SqlBuilder selectInvoiceBID() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select invoiceb." + InvoiceItemVO.PK_INVOICE_B + ",");
    return sql;
  }

  private SqlBuilder selectPODatePart() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("null,poh." + OrderHeaderVO.TAUDITTIME);
    sql.append(",null,null,null,null,null ");
    return sql;
  }

  private SqlBuilder selectPurchsInBID() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select storeb." + PurchaseinFIItemVO.PK_STOCKPS_B + ",");
    return sql;
  }

  private SqlBuilder selectStoreDatePart() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("null,null,null,");
    sql.append("storeb." + PurchaseinFIItemVO.DBIZDATE + "," + "storeh."
        + ICPubMetaNameConst.TAUDITTIME + ",null,null ");
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
