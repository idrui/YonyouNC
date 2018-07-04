package nc.pubitf.pu.m21.purp.discount;

import java.io.Serializable;

public class DiscountRefQueryVO implements Serializable {

  private static final long serialVersionUID = -8081813222193335158L;

  /**
   * ��ʼ����
   */
  private String begindate;

  /**
   * ��������
   */
  private String enddate;

  /**
   * �ۿ۹��򵵰�����
   */
  private String pk_discount;

  /**
   * ��֯
   */
  private String[] pk_orgs;

  /**
   * ��Ӧ��
   */
  private String pk_supplier;

  public String getBegindate() {
    return this.begindate;
  }

  public String getEnddate() {
    return this.enddate;
  }

  public String getPk_discount() {
    return this.pk_discount;
  }

  public String[] getPk_orgs() {
    return this.pk_orgs;
  }

  public String getPk_supplier() {
    return this.pk_supplier;
  }

  public void setBegindate(String begindate) {
    this.begindate = begindate;
  }

  public void setEnddate(String enddate) {
    this.enddate = enddate;
  }

  public void setPk_discount(String pk_discount) {
    this.pk_discount = pk_discount;
  }

  public void setPk_orgs(String[] pk_orgs) {
    this.pk_orgs = pk_orgs;
  }

  public void setPk_supplier(String pk_supplier) {
    this.pk_supplier = pk_supplier;
  }
}
