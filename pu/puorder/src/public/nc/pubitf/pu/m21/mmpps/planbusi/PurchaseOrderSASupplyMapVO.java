package nc.pubitf.pu.m21.mmpps.planbusi;

import java.io.Serializable;

/**
 * PU-供给影响分析查询供给-采购订单字段映射VO
 * 
 * @since 6.0
 * @version 2012-11-7 上午09:06:59
 * @author liuyand
 */
public class PurchaseOrderSASupplyMapVO implements Serializable {

  private static final long serialVersionUID = 4431904067644714597L;

  /** 入库关闭 */
  private String bstockclose;

  /** 单位 */
  private String castunitid;

  /** 特征码 **/
  private String cffileid;

  /** 主单位 */
  private String cunitid;

  /** 客户 */
  private String customerid;

  /** 单据日期 */
  private String dbillDate;

  /** 源头单据行ID */
  private String firstBid;

  /** 源头单据号 */
  private String firstCode;

  /** 源头单据ID */
  private String firstId;

  /** 源头单据行号 */
  private String firstRowNo;

  /** 源头单据类型 */
  private String firstType;

  /** 自由项1 */
  private String free1;

  /** 自由项10 */
  private String free10;

  /** 自由项2 */
  private String free2;

  /** 自由项3 */
  private String free3;

  /** 自由项4 */
  private String free4;

  /** 自由项5 */
  private String free5;

  /** 自由项6 */
  private String free6;

  /** 自由项7 */
  private String free7;

  /** 自由项8 */
  private String free8;

  /** 自由项9 */
  private String free9;

  /** 来源表 */
  private String fromSql;

  /** 物料主键 */
  private String materialid;

  /** 物料版本主键 */
  private String materialvid;

  /** 数量 */
  private String nastnum;

  /** 执行主数量 */
  private String nexenum;

  /** 主数量 */
  private String nnum;

  /** 供给主数量 */
  private String nsupplynum;

  /** 生产厂商 */
  private String productorid;

  /** 项目 */
  private String projectid;

  /** 预留主数量 */
  private String reservatioNnum;

  /** 来源单据行ID */
  private String srcBid;

  /** 来源单据号 */
  private String srcCode;

  /** 来源单据ID */
  private String srcId;

  /** 来源单据行号 */
  private String srcRowNo;

  /** 来源单据类型 */
  private String srcType;

  /** 供给表体主键 */
  private String supplybid;

  /** 供给单据号 */
  private String supplyCode;

  /** 供给日期 */
  private String supplyDate;

  /** 供给表头主键 */
  private String supplyid;

  /** 供给库存组织 */
  private String supplyOrgid;

  /** 供给库存组织版本 */
  private String supplyOrgvid;

  /** 供给单据行号 */
  private String supplyRowNo;

  /** 供给单据类型的编码值 */
  private String supplyTypeCodeValue;

  /** 供给单据类型的主键值 */
  private String supplyTypeIdValue;

  /** 单据状态 */
  private String vBillStatus;

  /** 单据状态枚举元数据ID */
  private String vBillStatusEnumID;

  /** 换算率 */
  private String vchangerate;

  /** 供应商 */
  private String vendorid;

  /** 过滤条件 */
  private String whereSql;

  public String getBstockclose() {
    return this.bstockclose;
  }

  public String getCastunitid() {
    return this.castunitid;
  }

  public String getCffileid() {
    return this.cffileid;
  }

  public String getCunitid() {
    return this.cunitid;
  }

  public String getCustomerid() {
    return this.customerid;
  }

  public String getDbillDate() {
    return this.dbillDate;
  }

  public String getFirstBid() {
    return this.firstBid;
  }

  public String getFirstCode() {
    return this.firstCode;
  }

  public String getFirstId() {
    return this.firstId;
  }

  public String getFirstRowNo() {
    return this.firstRowNo;
  }

  public String getFirstType() {
    return this.firstType;
  }

  public String getFree1() {
    return this.free1;
  }

  public String getFree10() {
    return this.free10;
  }

  public String getFree2() {
    return this.free2;
  }

  public String getFree3() {
    return this.free3;
  }

  public String getFree4() {
    return this.free4;
  }

  public String getFree5() {
    return this.free5;
  }

  public String getFree6() {
    return this.free6;
  }

  public String getFree7() {
    return this.free7;
  }

  public String getFree8() {
    return this.free8;
  }

  public String getFree9() {
    return this.free9;
  }

  public String getFrom() {
    return this.fromSql;
  }

  public String getFromSql() {
    return this.fromSql;
  }

  public String getMaterialid() {
    return this.materialid;
  }

  public String getMaterialvid() {
    return this.materialvid;
  }

  public String getNastnum() {
    return this.nastnum;
  }

  public String getNexenum() {
    return this.nexenum;
  }

  public String getNnum() {
    return this.nnum;
  }

  public String getNsupplynum() {
    return this.nsupplynum;
  }

  public String getProductorid() {
    return this.productorid;
  }

  public String getProjectid() {
    return this.projectid;
  }

  public String getReservatioNnum() {
    return this.reservatioNnum;
  }

  public String getSrcBid() {
    return this.srcBid;
  }

  public String getSrcCode() {
    return this.srcCode;
  }

  public String getSrcId() {
    return this.srcId;
  }

  public String getSrcRowNo() {
    return this.srcRowNo;
  }

  public String getSrcType() {
    return this.srcType;
  }

  public String getSupplybid() {
    return this.supplybid;
  }

  public String getSupplyCode() {
    return this.supplyCode;
  }

  public String getSupplyDate() {
    return this.supplyDate;
  }

  public String getSupplyid() {
    return this.supplyid;
  }

  public String getSupplyOrgid() {
    return this.supplyOrgid;
  }

  public String getSupplyOrgvid() {
    return this.supplyOrgvid;
  }

  public String getSupplyRowNo() {
    return this.supplyRowNo;
  }

  public String getSupplyTypeCodeValue() {
    return this.supplyTypeCodeValue;
  }

  public String getSupplyTypeIdValue() {
    return this.supplyTypeIdValue;
  }

  public String getvBillStatus() {
    return this.vBillStatus;
  }

  public String getVBillStatus() {
    return this.vBillStatus;
  }

  public String getvBillStatusEnumID() {
    return this.vBillStatusEnumID;
  }

  public String getVBillStatusEnumID() {
    return this.vBillStatusEnumID;
  }

  public String getVchangerate() {
    return this.vchangerate;
  }

  public String getVendorid() {
    return this.vendorid;
  }

  public String getWhere() {
    return this.whereSql;
  }

  public String getWhereSql() {
    return this.whereSql;
  }

  public void setBstockclose(String bstockclose) {
    this.bstockclose = bstockclose;
  }

  public void setCastunitid(String castunitid) {
    this.castunitid = castunitid;
  }

  public void setCffileid(String cffileid) {
    this.cffileid = cffileid;
  }

  public void setCunitid(String cunitid) {
    this.cunitid = cunitid;
  }

  public void setCustomerid(String customerid) {
    this.customerid = customerid;
  }

  public void setDbillDate(String dbillDate) {
    this.dbillDate = dbillDate;
  }

  public void setFirstBid(String firstBid) {
    this.firstBid = firstBid;
  }

  public void setFirstCode(String firstCode) {
    this.firstCode = firstCode;
  }

  public void setFirstId(String firstId) {
    this.firstId = firstId;
  }

  public void setFirstRowNo(String firstRowNo) {
    this.firstRowNo = firstRowNo;
  }

  public void setFirstType(String firstType) {
    this.firstType = firstType;
  }

  public void setFree1(String free1) {
    this.free1 = free1;
  }

  public void setFree10(String free10) {
    this.free10 = free10;
  }

  public void setFree2(String free2) {
    this.free2 = free2;
  }

  public void setFree3(String free3) {
    this.free3 = free3;
  }

  public void setFree4(String free4) {
    this.free4 = free4;
  }

  public void setFree5(String free5) {
    this.free5 = free5;
  }

  public void setFree6(String free6) {
    this.free6 = free6;
  }

  public void setFree7(String free7) {
    this.free7 = free7;
  }

  public void setFree8(String free8) {
    this.free8 = free8;
  }

  public void setFree9(String free9) {
    this.free9 = free9;
  }

  public void setFromSql(String fromSql) {
    this.fromSql = fromSql;
  }

  public void setMaterialid(String materialid) {
    this.materialid = materialid;
  }

  public void setMaterialvid(String materialvid) {
    this.materialvid = materialvid;
  }

  public void setNastnum(String nastnum) {
    this.nastnum = nastnum;
  }

  public void setNexenum(String nexenum) {
    this.nexenum = nexenum;
  }

  public void setNnum(String nnum) {
    this.nnum = nnum;
  }

  public void setNsupplynum(String nsupplynum) {
    this.nsupplynum = nsupplynum;
  }

  public void setProductorid(String productorid) {
    this.productorid = productorid;
  }

  public void setProjectid(String projectid) {
    this.projectid = projectid;
  }

  public void setReservatioNnum(String reservatioNnum) {
    this.reservatioNnum = reservatioNnum;
  }

  public void setSrcBid(String srcBid) {
    this.srcBid = srcBid;
  }

  public void setSrcCode(String srcCode) {
    this.srcCode = srcCode;
  }

  public void setSrcId(String srcId) {
    this.srcId = srcId;
  }

  public void setSrcRowNo(String srcRowNo) {
    this.srcRowNo = srcRowNo;
  }

  public void setSrcType(String srcType) {
    this.srcType = srcType;
  }

  public void setSupplybid(String supplybid) {
    this.supplybid = supplybid;
  }

  public void setSupplyCode(String supplyCode) {
    this.supplyCode = supplyCode;
  }

  public void setSupplyDate(String supplyDate) {
    this.supplyDate = supplyDate;
  }

  public void setSupplyid(String supplyid) {
    this.supplyid = supplyid;
  }

  public void setSupplyOrgid(String supplyOrgid) {
    this.supplyOrgid = supplyOrgid;
  }

  public void setSupplyOrgvid(String supplyOrgvid) {
    this.supplyOrgvid = supplyOrgvid;
  }

  public void setSupplyRowNo(String supplyRowNo) {
    this.supplyRowNo = supplyRowNo;
  }

  public void setSupplyTypeCodeValue(String supplyTypeCodeValue) {
    this.supplyTypeCodeValue = supplyTypeCodeValue;
  }

  public void setSupplyTypeIdValue(String supplyTypeIdValue) {
    this.supplyTypeIdValue = supplyTypeIdValue;
  }

  public void setvBillStatus(String vBillStatus) {
    this.vBillStatus = vBillStatus;
  }

  public void setvBillStatusEnumID(String vBillStatusEnumID) {
    this.vBillStatusEnumID = vBillStatusEnumID;
  }

  public void setVchangerate(String vchangerate) {
    this.vchangerate = vchangerate;
  }

  public void setVendorid(String vendorid) {
    this.vendorid = vendorid;
  }

  public void setWhereSql(String whereSql) {
    this.whereSql = whereSql;
  }

}
