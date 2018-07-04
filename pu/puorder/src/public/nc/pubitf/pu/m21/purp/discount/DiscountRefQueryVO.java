package nc.pubitf.pu.m21.purp.discount;

import java.io.Serializable;

public class DiscountRefQueryVO implements Serializable {

  private static final long serialVersionUID = -8081813222193335158L;

  /**
   * 开始日期
   */
  private String begindate;

  /**
   * 结束日期
   */
  private String enddate;

  /**
   * 折扣规则档案主键
   */
  private String pk_discount;

  /**
   * 组织
   */
  private String[] pk_orgs;

  /**
   * 供应商
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
