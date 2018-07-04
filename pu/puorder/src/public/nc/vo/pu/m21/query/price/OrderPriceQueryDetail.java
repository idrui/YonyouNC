package nc.vo.pu.m21.query.price;

import java.io.Serializable;

import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����ѯ�۵���ϸ�в�����������ʹ�ã���ΪOrderPriceQueryParam����ϸ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-24 ����03:42:12
 */
public class OrderPriceQueryDetail implements Serializable {
  private static final long serialVersionUID = -3058757842198679925L;

  private String buyingReq;// ����빺����ϸID

  private String castunitid;// ���۵�λ

  private String contract;// ��òɹ���ͬ��ϸID

  private String financeOrg;// ��ý��������֯��OID

  private String material;// �������VID

  private String materialSourceId;// �������OID

  private UFDouble nnosubtaxrate;// ���ɵֿ�˰��

  private UFDouble origPrice;// �������λԭ�Ҳ���˰����

  private UFDouble origTaxPrice;// �������λԭ�Һ�˰����

  private UFDouble price;// �������λ���Ҳ���˰����

  private String productor;// �����������

  private UFDouble qtorigprice;// ��ñ��۵�λԭ�Ҳ���˰����

  private UFDouble qtorigtaxprice;// ��ñ��۵�λԭ�Һ�˰����

  private String quantityLevel;// ��������ȼ�

  private String receiveArea;// ����ջ�����

  private int row;// ��ǰ��

  private UFDouble taxPrice;// �������λ���Һ�˰����

  private UFDouble taxRate;// ˰�ʣ���Ӧ�̼�Ŀ��ѯ���ã�

  private Integer taxTypeFlag;// ��˰��𣨹�Ӧ�̼�Ŀ��ѯ���ã�

  private String transportType;// ������䷽ʽ

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
