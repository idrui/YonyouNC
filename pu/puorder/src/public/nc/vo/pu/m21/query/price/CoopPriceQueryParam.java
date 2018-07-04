package nc.vo.pu.m21.query.price;

import java.io.Serializable;

import nc.vo.pub.lang.UFDate;

/**
 * @since 6.0
 * @version 2010-11-6 下午02:46:19
 * @author wanghuid
 */
public class CoopPriceQueryParam implements Serializable {

  private static final long serialVersionUID = 8236356058212451775L;

  private UFDate billDate;// 获得单据日期

  private String billtype; // 单据类型

  private String currency;// 获得币种ID

  private CoopPriceQueryDetail[] detail;

  // private String[] financialPKs; // 财务组织pk

  private String purchaseOrg;// 获得采购组织的OID

  private String supplier;// 获得供应商ID

  private String trasType;// 交易类型

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
   *          要设置的 billtype
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
   *          要设置的 financialPK
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
   *          要设置的 trasType
   */
  public void setTrasType(String trasType) {
    this.trasType = trasType;
  }

}
