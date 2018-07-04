package nc.vo.pu.m21.query.price;

import java.io.Serializable;

import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单询价的明细行参数，不单独使用，作为OrderPriceQueryParam的明细
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-24 下午03:42:12
 */
public class OrderPriceQueryDetail implements Serializable {
  private static final long serialVersionUID = -3058757842198679925L;

  private String buyingReq;// 获得请购单明细ID

  private String castunitid;// 报价单位

  private String contract;// 获得采购合同明细ID

  private String financeOrg;// 获得结算财务组织的OID

  private String material;// 获得物料VID

  private String materialSourceId;// 获得物料OID

  private UFDouble nnosubtaxrate;// 不可抵扣税率

  private UFDouble origPrice;// 获得主单位原币不含税净价

  private UFDouble origTaxPrice;// 获得主单位原币含税净价

  private UFDouble price;// 获得主单位本币不含税净价

  private String productor;// 获得生产厂商

  private UFDouble qtorigprice;// 获得报价单位原币不含税净价

  private UFDouble qtorigtaxprice;// 获得报价单位原币含税净价

  private String quantityLevel;// 获得质量等级

  private String receiveArea;// 获得收货地区

  private int row;// 当前行

  private UFDouble taxPrice;// 获得主单位本币含税净价

  private UFDouble taxRate;// 税率（供应商价目表询价用）

  private Integer taxTypeFlag;// 扣税类别（供应商价目表询价用）

  private String transportType;// 获得运输方式

  public String getBuyingReq() {
    return this.buyingReq;
  }

  public String getCastunitid() {
    return this.castunitid;
  }

  public String getContract() {
    return this.contract;
  }

  public String getFinanceOrg() {
    return this.financeOrg;
  }

  public String getMaterial() {
    return this.material;
  }

  public String getMaterialSourceId() {
    return this.materialSourceId;
  }

  public UFDouble getNnosubtaxrate() {
    return this.nnosubtaxrate;
  }

  public UFDouble getOrigPrice() {
    return this.origPrice;
  }

  public UFDouble getOrigTaxPrice() {
    return this.origTaxPrice;
  }

  public UFDouble getPrice() {
    return this.price;
  }

  public String getProductor() {
    return this.productor;
  }

  public UFDouble getQtorigprice() {
    return this.qtorigprice;
  }

  public UFDouble getQtorigtaxprice() {
    return this.qtorigtaxprice;
  }

  public String getQuantityLevel() {
    return this.quantityLevel;
  }

  public String getReceiveArea() {
    return this.receiveArea;
  }

  public int getRow() {
    return this.row;
  }

  public UFDouble getTaxPrice() {
    return this.taxPrice;
  }

  public UFDouble getTaxRate() {
    return this.taxRate;
  }

  public Integer getTaxTypeFlag() {
    return this.taxTypeFlag;
  }

  public String getTransportType() {
    return this.transportType;
  }

  public void setBuyingReq(String buyingReq) {
    this.buyingReq = buyingReq;
  }

  public void setCastunitid(String castunitid) {
    this.castunitid = castunitid;
  }

  public void setContract(String contract) {
    this.contract = contract;
  }

  public void setFinanceOrg(String financeOrg) {
    this.financeOrg = financeOrg;
  }

  public void setMaterial(String material) {
    this.material = material;
  }

  public void setMaterialSourceId(String materialSourceId) {
    this.materialSourceId = materialSourceId;
  }

  public UFDouble setNnosubtaxrate(UFDouble nnosubtaxrate) {
    return this.nnosubtaxrate = nnosubtaxrate;
  }

  public void setOrigPrice(UFDouble origPrice) {
    this.origPrice = origPrice;
  }

  public void setOrigTaxPrice(UFDouble origTaxPrice) {
    this.origTaxPrice = origTaxPrice;
  }

  public void setPrice(UFDouble price) {
    this.price = price;
  }

  public void setProductor(String productor) {
    this.productor = productor;
  }

  public void setQtorigprice(UFDouble qtorigprice) {
    this.qtorigprice = qtorigprice;
  }

  public void setQtorigtaxprice(UFDouble qtorigtaxprice) {
    this.qtorigtaxprice = qtorigtaxprice;
  }

  public void setQuantityLevel(String quantityLevel) {
    this.quantityLevel = quantityLevel;
  }

  public void setReceiveArea(String receiveArea) {
    this.receiveArea = receiveArea;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public void setTaxPrice(UFDouble taxPrice) {
    this.taxPrice = taxPrice;
  }

  public void setTaxRate(UFDouble taxRate) {
    this.taxRate = taxRate;
  }

  public void setTaxTypeFlag(Integer taxTypeFlag) {
    this.taxTypeFlag = taxTypeFlag;
  }

  public void setTransportType(String transportType) {
    this.transportType = transportType;
  }
}
