package nc.vo.pu.m20.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * ���������
 * 
 * @since 6.0
 * @version 2011-4-24 ����04:26:49
 * @author wuxla
 */

public class SupplierQuotaPara implements Serializable {

  private static final long serialVersionUID = -5974391647483297798L;

  /**
   * ����
   */
  private UFDate date;

  /**
   * �빺����
   */
  private UFDouble npraynum;

  /**
   * ����������
   */
  private List<UFDouble> nquotanumList = new ArrayList<UFDouble>();

  /**
   * ����VID
   */
  private String pk_material;

  /**
   * �빺����ͷid
   */
  private String pk_praybill;

  /**
   * �빺������id
   */
  private String pk_praybill_b;

  /**
   * �ɹ���֯
   */
  private String pk_purchaseorg;

  /**
   * ����OID
   */
  private String pk_srcmaterial;

  /**
   * ��Ӧ��
   */
  private List<String> supplierList = new ArrayList<String>();

  public UFDate getDate() {
    return this.date;
  }

  public UFDouble getNpraynum() {
    return this.npraynum;
  }

  public List<UFDouble> getNquotanumList() {
    return this.nquotanumList;
  }

  public String getPk_material() {
    return this.pk_material;
  }

  public String getPk_praybill() {
    return this.pk_praybill;
  }

  public String getPk_praybill_b() {
    return this.pk_praybill_b;
  }

  public String getPk_purchaseorg() {
    return this.pk_purchaseorg;
  }

  public String getPk_srcmaterial() {
    return this.pk_srcmaterial;
  }

  public List<String> getSupplierList() {
    return this.supplierList;
  }

  public void setDate(UFDate date) {
    this.date = date;
  }

  public void setNpraynum(UFDouble npraynum) {
    this.npraynum = npraynum;
  }

  public void setNquotanumList(List<UFDouble> nquotanumList) {
    this.nquotanumList = nquotanumList;
  }

  public void setPk_material(String pk_material) {
    this.pk_material = pk_material;
  }

  public void setPk_praybill(String pk_praybill) {
    this.pk_praybill = pk_praybill;
  }

  public void setPk_praybill_b(String pk_praybill_b) {
    this.pk_praybill_b = pk_praybill_b;
  }

  public void setPk_purchaseorg(String pk_purchaseorg) {
    this.pk_purchaseorg = pk_purchaseorg;
  }

  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.pk_srcmaterial = pk_srcmaterial;
  }

  public void setSupplierList(List<String> supplierList) {
    this.supplierList = supplierList;
  }

}
