package nc.vo.pu.m21.query.price;

import java.io.Serializable;

import nc.vo.pub.lang.UFDate;

/**
 * @since 6.0
 * @version 2010-11-6 ����02:46:19
 * @author wanghuid
 */
public class CoopPriceQueryParam implements Serializable {

  private static final long serialVersionUID = 8236356058212451775L;

  private UFDate billDate;// ��õ�������

  private String billtype; // ��������

  private String currency;// ��ñ���ID

  private CoopPriceQueryDetail[] detail;

  // private String[] financialPKs; // ������֯pk

  private String purchaseOrg;// ��òɹ���֯��OID

  private String supplier;// ��ù�Ӧ��ID

  private String trasType;// ��������

  public UFDate getBillDate() {
    return this.billDate;
  }

  /**
   * @return billtype
   */
  public String getBilltype() {
    return this.billtype;
  }

  public String getCurrency() {
    return this.currency;
  }

  public CoopPriceQueryDetail[] getDetail() {
    return this.detail;
  }

  /**
   * @return financialPK
   */
  // public String[] getFinancialPKs() {
  // return this.financialPKs;
  // }

  public String getPurchaseOrg() {
    return this.purchaseOrg;
  }

  public String getSupplier() {
    return this.supplier;
  }

  /**
   * @return trasType
   */
  public String getTrasType() {
    return this.trasType;
  }

  public void setBillDate(UFDate billDate) {
    this.billDate = billDate;
  }

  /**
   * @param billtype
   *          Ҫ���õ� billtype
   */
  public void setBilltype(String billtype) {
    this.billtype = billtype;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public void setDetail(CoopPriceQueryDetail[] detail) {
    this.detail = detail;
  }

  /**
   * @param financialPK
   *          Ҫ���õ� financialPK
   */
  // public void setFinancialPKs(String[] financialPKs) {
  // this.financialPKs = financialPKs;
  // }

  public void setPurchaseOrg(String purchaseOrg) {
    this.purchaseOrg = purchaseOrg;
  }

  public void setSupplier(String supplier) {
    this.supplier = supplier;
  }

  /**
   * @param trasType
   *          Ҫ���õ� trasType
   */
  public void setTrasType(String trasType) {
    this.trasType = trasType;
  }

}
