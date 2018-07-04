package nc.pubitf.pu.m21.pim;

import java.io.Serializable;

import nc.vo.pub.lang.UFDate;

public class QueryLatestPriceParam implements Serializable {

  private static final long serialVersionUID = 5166355067391231577L;

  /**
   * 币种 （可为空）
   */
  private String[] currencys;

  /**
   * 结束询价单据日期 （可为空）
   */
  private UFDate endDate;

  /**
   * 财务组织 （可为空）
   */
  private String[] financeOrgs;

  /**
   * 物料的OID数组
   */
  private String[] materials;

  /**
   * 采购组织 （可为空）
   */
  private String[] purchaseOrgs;

  /**
   * 起始询价单据日期 （可为空）
   */
  private UFDate startDate;

  /**
   * 供应商 （可为空）
   */
  private String[] suppliers;

  /**
   * 币种 （可为空）
   */
  public String[] getCurrencys() {
    return this.currencys;
  }

  /**
   * 结束询价单据日期 （可为空）
   */
  public UFDate getEndDate() {
    return this.endDate;
  }

  /**
   * 财务组织 （可为空）
   */
  public String[] getFinanceOrgs() {
    return this.financeOrgs;
  }

  /**
   * 物料id数组
   */
  public String[] getMaterials() {
    return this.materials;
  }

  /**
   * 采购组织 （可为空）
   */
  public String[] getPurchaseOrgs() {
    return this.purchaseOrgs;
  }

  /**
   * 起始询价单据日期 （可为空）
   */
  public UFDate getStartDate() {
    return this.startDate;
  }

  /**
   * 供应商 （可为空）
   */
  public String[] getSuppliers() {
    return this.suppliers;
  }

  /**
   * 币种 （可为空）
   */
  public void setCurrencys(String[] currencys) {
    this.currencys = currencys;
  }

  /**
   * 结束询价单据日期 （可为空）
   */
  public void setEndDate(UFDate endDate) {
    this.endDate = endDate;
  }

  /**
   * 财务组织 （可为空）
   */
  public void setFinanceOrg(String[] financeOrgs) {
    this.financeOrgs = financeOrgs;
  }

  /**
   * 物料id数组
   */
  public void setMaterials(String[] materials) {
    this.materials = materials;
  }

  /**
   * 采购组织 （可为空）
   */
  public void setPurchaseOrgs(String[] purchaseOrgs) {
    this.purchaseOrgs = purchaseOrgs;
  }

  /**
   * 起始询价单据日期 （可为空）
   */
  public void setStartDate(UFDate startDate) {
    this.startDate = startDate;
  }

  /**
   * 供应商 （可为空）
   */
  public void setSuppliers(String[] suppliers) {
    this.suppliers = suppliers;
  }

}
