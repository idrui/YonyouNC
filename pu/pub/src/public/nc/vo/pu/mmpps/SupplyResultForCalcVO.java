package nc.vo.pu.mmpps;

import java.io.Serializable;

import nc.pubitf.scmpub.scmpub.mmpps.calc.ISupplyResultForCalc;

public class SupplyResultForCalcVO implements ISupplyResultForCalc,
    Serializable {

  private static final long serialVersionUID = 1L;

  private String customerid;

  private String firstBid;

  private String firstCode;

  private String firstId;

  private String firstRowNo;

  private String firstType;

  private String free1;

  private String free10;

  private String free2;

  private String free3;

  private String free4;

  private String free5;

  private String free6;

  private String free7;

  private String free8;

  private String free9;

  private String fromSql;

  private String materialid;

  private String materialvid;

  private String nnum;

  private String productorid;

  private String projectid;

  private String reservatioNnum;

  private String supplybid;

  private String supplyCode;

  private String supplyDate;

  private String supplyid;

  private String supplyOrgid;

  private String supplyOrgvid;

  private String supplyRowNo;

  private String supplyTypeCodeValue;

  private String supplyTypeIdValue;

  private String vendorid;

  private String whereSql;

  /** 来源单据行id */
  private String csrcbid;

  /** 来源单据id */
  private String csrcid;

  /** 单据状态 */
  private String fstatusflag;

  /** 单据状态枚举元数据id */
  private String fstatusflagEnumID;

  /** 来源单据号 */
  private String vsrccode;

  /** 来源单据行号 */
  private String vsrcrowno;

  /** 来源单据类型 */
  private String vsrctype;

  @Override
  public String getCcustmaterialid() {
    return null;
  }

  @Override
  public String getCsrcbid() {
    return this.csrcbid;
  }

  @Override
  public String getCsrcid() {
    return this.csrcid;
  }

  @Override
  public String getCustomerid() {
    return this.customerid;
  }

  @Override
  public String getFirstBid() {
    return this.firstBid;
  }

  @Override
  public String getFirstCode() {
    return this.firstCode;
  }

  @Override
  public String getFirstId() {
    return this.firstId;
  }

  @Override
  public String getFirstRowNo() {
    return this.firstRowNo;
  }

  @Override
  public String getFirstType() {
    return this.firstType;
  }

  @Override
  public String getFree1() {
    return this.free1;
  }

  @Override
  public String getFree10() {
    return this.free10;
  }

  @Override
  public String getFree2() {
    return this.free2;
  }

  @Override
  public String getFree3() {
    return this.free3;
  }

  @Override
  public String getFree4() {
    return this.free4;
  }

  @Override
  public String getFree5() {
    return this.free5;
  }

  @Override
  public String getFree6() {
    return this.free6;
  }

  @Override
  public String getFree7() {
    return this.free7;
  }

  @Override
  public String getFree8() {
    return this.free8;
  }

  @Override
  public String getFree9() {
    return this.free9;
  }

  @Override
  public String getFrom() {
    return this.fromSql;
  }

  @Override
  public String getFstatusflag() {
    return this.fstatusflag;
  }

  @Override
  public String getFstatusflagEnumID() {
    return this.fstatusflagEnumID;
  }

  @Override
  public String getMaterialid() {
    return this.materialid;
  }

  @Override
  public String getMaterialvid() {
    return this.materialvid;
  }

  @Override
  public String getNnum() {
    return this.nnum;
  }

  @Override
  public String getProductorid() {
    return this.productorid;
  }

  @Override
  public String getProjectid() {
    return this.projectid;
  }

  @Override
  public String getReservatioNnum() {
    return this.reservatioNnum;
  }

  @Override
  public String getSupplybid() {
    return this.supplybid;
  }

  @Override
  public String getSupplyCode() {
    return this.supplyCode;
  }

  @Override
  public String getSupplyDate() {
    return this.supplyDate;
  }

  @Override
  public String getSupplyid() {
    return this.supplyid;
  }

  @Override
  public String getSupplyOrgid() {
    return this.supplyOrgid;
  }

  @Override
  public String getSupplyOrgvid() {
    return this.supplyOrgvid;
  }

  @Override
  public String getSupplyRowNo() {
    return this.supplyRowNo;
  }

  @Override
  public String getSupplyTypeCodeValue() {
    return this.supplyTypeCodeValue;
  }

  @Override
  public String getSupplyTypeIdValue() {
    return this.supplyTypeIdValue;
  }

  @Override
  public String getVendorid() {
    return this.vendorid;
  }

  @Override
  public String getVsrccode() {
    return this.vsrccode;
  }

  @Override
  public String getVsrcrowno() {
    return this.vsrcrowno;
  }

  @Override
  public String getVsrctype() {
    return this.vsrctype;
  }

  @Override
  public String getWhere() {
    return this.whereSql;
  }

  public void setCustomerid(String customerid) {
    this.customerid = customerid;
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

  public void setNnum(String nnum) {
    this.nnum = nnum;
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

  public void setVendorid(String vendorid) {
    this.vendorid = vendorid;
  }

  public void setWhereSql(String whereSql) {
    this.whereSql = whereSql;
  }

  public void setCsrcbid(String csrcbid) {
    this.csrcbid = csrcbid;
  }

  public void setCsrcid(String csrcid) {
    this.csrcid = csrcid;
  }

  public void setFstatusflag(String fstatusflag) {
    this.fstatusflag = fstatusflag;
  }

  public void setFstatusflagEnumID(String fstatusflagEnumID) {
    this.fstatusflagEnumID = fstatusflagEnumID;
  }

  public void setVsrccode(String vsrccode) {
    this.vsrccode = vsrccode;
  }

  public void setVsrcrowno(String vsrcrowno) {
    this.vsrcrowno = vsrcrowno;
  }

  public void setVsrctype(String vsrctype) {
    this.vsrctype = vsrctype;
  }

}
