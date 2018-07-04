package nc.pubitf.pu.m21.pim;

import java.io.Serializable;

import nc.vo.pub.lang.UFDate;

public class QueryLatestPriceParam implements Serializable {

  private static final long serialVersionUID = 5166355067391231577L;

  /**
   * ���� ����Ϊ�գ�
   */
  private String[] currencys;

  /**
   * ����ѯ�۵������� ����Ϊ�գ�
   */
  private UFDate endDate;

  /**
   * ������֯ ����Ϊ�գ�
   */
  private String[] financeOrgs;

  /**
   * ���ϵ�OID����
   */
  private String[] materials;

  /**
   * �ɹ���֯ ����Ϊ�գ�
   */
  private String[] purchaseOrgs;

  /**
   * ��ʼѯ�۵������� ����Ϊ�գ�
   */
  private UFDate startDate;

  /**
   * ��Ӧ�� ����Ϊ�գ�
   */
  private String[] suppliers;

  /**
   * ���� ����Ϊ�գ�
   */
  public String[] getCurrencys() {
    return this.currencys;
  }

  /**
   * ����ѯ�۵������� ����Ϊ�գ�
   */
  public UFDate getEndDate() {
    return this.endDate;
  }

  /**
   * ������֯ ����Ϊ�գ�
   */
  public String[] getFinanceOrgs() {
    return this.financeOrgs;
  }

  /**
   * ����id����
   */
  public String[] getMaterials() {
    return this.materials;
  }

  /**
   * �ɹ���֯ ����Ϊ�գ�
   */
  public String[] getPurchaseOrgs() {
    return this.purchaseOrgs;
  }

  /**
   * ��ʼѯ�۵������� ����Ϊ�գ�
   */
  public UFDate getStartDate() {
    return this.startDate;
  }

  /**
   * ��Ӧ�� ����Ϊ�գ�
   */
  public String[] getSuppliers() {
    return this.suppliers;
  }

  /**
   * ���� ����Ϊ�գ�
   */
  public void setCurrencys(String[] currencys) {
    this.currencys = currencys;
  }

  /**
   * ����ѯ�۵������� ����Ϊ�գ�
   */
  public void setEndDate(UFDate endDate) {
    this.endDate = endDate;
  }

  /**
   * ������֯ ����Ϊ�գ�
   */
  public void setFinanceOrg(String[] financeOrgs) {
    this.financeOrgs = financeOrgs;
  }

  /**
   * ����id����
   */
  public void setMaterials(String[] materials) {
    this.materials = materials;
  }

  /**
   * �ɹ���֯ ����Ϊ�գ�
   */
  public void setPurchaseOrgs(String[] purchaseOrgs) {
    this.purchaseOrgs = purchaseOrgs;
  }

  /**
   * ��ʼѯ�۵������� ����Ϊ�գ�
   */
  public void setStartDate(UFDate startDate) {
    this.startDate = startDate;
  }

  /**
   * ��Ӧ�� ����Ϊ�գ�
   */
  public void setSuppliers(String[] suppliers) {
    this.suppliers = suppliers;
  }

}
