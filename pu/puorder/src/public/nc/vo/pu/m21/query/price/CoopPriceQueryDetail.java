package nc.vo.pu.m21.query.price;

import java.io.Serializable;

import nc.vo.pub.lang.UFDouble;

/**
 * @since 6.0
 * @version 2010-11-6 ����02:47:17
 * @author wanghuid
 */

public class CoopPriceQueryDetail implements Serializable {

  private static final long serialVersionUID = 2880200965294102945L;

  // �ۿ�
  private UFDouble Discount;

  // �������������֯
  private String financialPK;

  // ����
  private UFDouble netPrice;

  // ����
  private UFDouble nnum;

  // �ջ�����
  private String pk_areacl;

  // ����
  private String pk_currtype;

  private String pk_group;

  // ����
  private String pk_material;

  // �����ȼ�
  private String pk_qualitylevel;

  // ���۵�λ
  private String pk_unit;

  // // ���ϻ�������
  // private String pk_marbasclass;

  // ����
  private UFDouble price;

  private int row;// ��ǰ��

  // ������֯
  private String saleOrg;

  /**
   * ���ɸ�������1
   */
  private String vfree1;

  /**
   * ���ɸ�������10
   */
  private String vfree10;

  /**
   * ���ɸ�������2
   */
  private String vfree2;

  /**
   * ���ɸ�������3
   */
  private String vfree3;

  /**
   * ���ɸ�������4
   */
  private String vfree4;

  /**
   * ���ɸ�������5
   */
  private String vfree5;

  /**
   * ���ɸ�������6
   */
  private String vfree6;

  /**
   * ���ɸ�������7
   */
  private String vfree7;

  /**
   * ���ɸ�������8
   */
  private String vfree8;

  /**
   * ���ɸ�������9
   */
  private String vfree9;

  public UFDouble getDiscount() {
    return this.Discount;
  }

  public String getFinancialPK() {
    return this.financialPK;
  }

  public UFDouble getNetPrice() {
    return this.netPrice;
  }

  public UFDouble getNnum() {
    return this.nnum;
  }

  public String getPk_areacl() {
    return this.pk_areacl;
  }

  public String getPk_currtype() {
    return this.pk_currtype;
  }

  public String getPk_group() {
    return this.pk_group;
  }

  public String getPk_material() {
    return this.pk_material;
  }

  public String getPk_qualitylevel() {
    return this.pk_qualitylevel;
  }

  public String getPk_unit() {
    return this.pk_unit;
  }

  public UFDouble getPrice() {
    return this.price;
  }

  public int getRow() {
    return this.row;
  }

  public String getSaleOrg() {
    return this.saleOrg;
  }

  public String getVfree1() {
    return this.vfree1;
  }

  public String getVfree10() {
    return this.vfree10;
  }

  public String getVfree2() {
    return this.vfree2;
  }

  public String getVfree3() {
    return this.vfree3;
  }

  public String getVfree4() {
    return this.vfree4;
  }

  public String getVfree5() {
    return this.vfree5;
  }

  public String getVfree6() {
    return this.vfree6;
  }

  public String getVfree7() {
    return this.vfree7;
  }

  public String getVfree8() {
    return this.vfree8;
  }

  public String getVfree9() {
    return this.vfree9;
  }

  public void setDiscount(UFDouble discount) {
    this.Discount = discount;
  }

  public void setFinancialPK(String financialPK) {
    this.financialPK = financialPK;
  }

  public void setNetPrice(UFDouble netPrice) {
    this.netPrice = netPrice;
  }

  public void setNnum(UFDouble nnum) {
    this.nnum = nnum;
  }

  public void setPk_areacl(String pk_areacl) {
    this.pk_areacl = pk_areacl;
  }

  public void setPk_currtype(String pk_currtype) {
    this.pk_currtype = pk_currtype;
  }

  public void setPk_group(String pk_group) {
    this.pk_group = pk_group;
  }

  public void setPk_material(String pk_material) {
    this.pk_material = pk_material;
  }

  public void setPk_qualitylevel(String pk_qualitylevel) {
    this.pk_qualitylevel = pk_qualitylevel;
  }

  public void setPk_unit(String pk_unit) {
    this.pk_unit = pk_unit;
  }

  public void setPrice(UFDouble price) {
    this.price = price;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public void setSaleOrg(String saleOrg) {
    this.saleOrg = saleOrg;
  }

  // public String getPk_marbasclass() {
  // return this.pk_marbasclass;
  // }

  public void setVfree1(String vfree1) {
    this.vfree1 = vfree1;
  }

  public void setVfree10(String vfree10) {
    this.vfree10 = vfree10;
  }

  public void setVfree2(String vfree2) {
    this.vfree2 = vfree2;
  }

  public void setVfree3(String vfree3) {
    this.vfree3 = vfree3;
  }

  public void setVfree4(String vfree4) {
    this.vfree4 = vfree4;
  }

  // public void setPk_marbasclass(String pk_marbasclass) {
  // this.pk_marbasclass = pk_marbasclass;
  // }

  public void setVfree5(String vfree5) {
    this.vfree5 = vfree5;
  }

  public void setVfree6(String vfree6) {
    this.vfree6 = vfree6;
  }

  public void setVfree7(String vfree7) {
    this.vfree7 = vfree7;
  }

  public void setVfree8(String vfree8) {
    this.vfree8 = vfree8;
  }

  public void setVfree9(String vfree9) {
    this.vfree9 = vfree9;
  }

}
