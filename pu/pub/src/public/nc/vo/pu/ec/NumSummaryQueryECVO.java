package nc.vo.pu.ec;

import java.io.Serializable;

import nc.vo.pub.lang.UFDate;

public class NumSummaryQueryECVO implements Serializable {
  private static final long serialVersionUID = -3829618822693665338L;

  private UFDate beginDate;// 汇总结束日期

  private UFDate endDate;// 汇总起始日期

  private String[] pk_materials;// 产品主键

  private String pk_org;// 采购组织

  private String pk_supplier;// 供应商主键

  public UFDate getBeginDate() {
    return this.beginDate;
  }

  public UFDate getEndDate() {
    return this.endDate;
  }

  public String[] getPk_materials() {
    return this.pk_materials;
  }

  public String getPk_org() {
    return this.pk_org;
  }

  public String getPk_supplier() {
    return this.pk_supplier;
  }

  public void setBeginDate(UFDate beginDate) {
    this.beginDate = beginDate;
  }

  public void setEndDate(UFDate endDate) {
    this.endDate = endDate;
  }

  public void setPk_materials(String[] pk_materials) {
    this.pk_materials = pk_materials;
  }

  public void setPk_org(String pk_org) {
    this.pk_org = pk_org;
  }

  public void setPk_supplier(String pk_supplier) {
    this.pk_supplier = pk_supplier;
  }
}
