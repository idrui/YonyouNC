package nc.vo.pu.m20.entity;

import java.util.ArrayList;

import nc.vo.pub.NullFieldException;
import nc.vo.pub.ValidationException;
import nc.vo.pub.ValueObject;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺��ִ������VO- ������������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-21 ����11:11:29
 */
public class PrayExecVO extends ValueObject {

  private static final long serialVersionUID = -1991597905668971567L;

  /** ��������� **/
  private UFDouble completenum;

  /** �������� **/
  private String cproductorid;

  /** ��Ŀ **/
  private String cprojectid;

  /** ������λID(������) **/
  private String cunitid;

  /** �������� **/
  private UFDate dreqdate;

  /** ���� **/
  private UFDouble nnum;

  /** ����pk **/
  private String pk_material;

  /** �����֯PK **/
  private String pk_org;

  /** �빺������ID **/
  private String pk_praybill;

  /** �빺������ID **/
  private String pk_praybill_b;

  /** �ֿ�ID **/
  private String pk_reqstor;

  /** ����Oid **/
  private String pk_srcmaterial;

  /** ��Ӧ��ID **/
  private String pk_supplier;

  /** �빺���� **/
  private String vbillcode;

  /** ������1 **/
  private String vfree1;

  /** ������10 **/
  private String vfree10;

  /** ������2 **/
  private String vfree2;

  /** ������3 **/
  private String vfree3;

  /** ������4 **/
  private String vfree4;

  /** ������5 **/
  private String vfree5;

  /** ������6 **/
  private String vfree6;

  /** ������6 **/
  private String vfree7;

  /** ������8 **/
  private String vfree8;

  /** ������9 **/
  private String vfree9;

  /**
   * �����������Ե�FieldObjects����Ҫ����ϵͳ�����У� ҵ������в����õ������FieldObjects��
   */
  /**
   * ʹ�������ֶν��г�ʼ���Ĺ����ӡ� �������ڣ�(2001-11-14)
   */
  public PrayExecVO() {
    // ȱʡ���췽��
  }

  /**
   * ���������
   * 
   * @return UFDouble
   */
  public UFDouble getCompletenum() {
    return this.completenum;
  }

  /** �������� **/
  public String getCproductorid() {
    return this.cproductorid;
  }

  /** ��Ŀ **/
  public String getCprojectid() {
    return this.cprojectid;
  }

  /**
   * ����λ
   * 
   * @return String
   */
  public String getCunitid() {
    return this.cunitid;
  }

  /**
   * ��������
   * 
   * @return UFDate
   */
  public UFDate getDreqdate() {
    return this.dreqdate;
  }

  /**
   * ������ֵ�������ʾ���ơ� �������ڣ�(2001-11-14)
   * 
   * @return java.lang.String ������ֵ�������ʾ���ơ�
   */
  @Override
  public String getEntityName() {

    return "PrayExec";
  }

  /**
   * ����
   * 
   * @return UFDouble
   */
  public UFDouble getNnum() {
    return this.nnum;
  }

  /**
   * ����PK
   * 
   * @return String
   */
  public String getPk_material() {
    return this.pk_material;
  }

  /**
   * �����֯PK
   * 
   * @return String
   */
  public String getPk_org() {
    return this.pk_org;
  }

  /**
   * �빺������PK
   * 
   * @return String
   */
  public String getPk_praybill() {
    return this.pk_praybill;
  }

  /**
   * �빺���ӱ�PK
   * 
   * @return String
   */
  public String getPk_praybill_b() {
    return this.pk_praybill_b;
  }

  /**
   * �ֿ�PK
   * 
   * @return String
   */
  public String getPk_reqstor() {
    return this.pk_reqstor;
  }

  public String getPk_srcmaterial() {
    return this.pk_srcmaterial;
  }

  /**
   * ��Ӧ��PK
   * 
   * @return String
   */
  public String getPk_supplier() {
    return this.pk_supplier;
  }

  /**
   * �빺����
   * 
   * @return String
   */
  public String getVbillcode() {
    return this.vbillcode;
  }

  public java.lang.String getVfree1() {
    return this.vfree1;
  }

  /**
   * @return vfree10
   */
  public String getvfree10() {
    return this.vfree10;
  }

  public java.lang.String getVfree2() {
    return this.vfree2;
  }

  public java.lang.String getVfree3() {
    return this.vfree3;
  }

  public java.lang.String getVfree4() {
    return this.vfree4;
  }

  public java.lang.String getVfree5() {
    return this.vfree5;
  }

  /**
   * @return vfree6
   */
  public String getvfree6() {
    return this.vfree6;
  }

  /**
   * @return vfree7
   */
  public String getvfree7() {
    return this.vfree7;
  }

  /**
   * @return vfree8
   */
  public String getvfree8() {
    return this.vfree8;
  }

  /**
   * @return vfree9
   */
  public String getvfree9() {
    return this.vfree9;
  }

  /**
   * ���������
   * 
   * @param newwcsl
   *          UFDouble
   */
  public void setCompletenum(UFDouble newWcsl) {

    this.completenum = newWcsl;
  }

  /** �������� **/
  public void setCproductorid(String cproductorid) {
    this.cproductorid = cproductorid;
  }

  /** ��Ŀ **/
  public void setCprojectid(String cprojectid) {
    this.cprojectid = cprojectid;
  }

  /**
   * ����λ
   * 
   * @param newjldwid
   *          String
   */
  public void setCunitid(String newJldwid) {

    this.cunitid = newJldwid;
  }

  /**
   * ��������
   * 
   * @param newxqrq
   *          UFDate
   */
  public void setDreqdate(UFDate dreqdate) {

    this.dreqdate = dreqdate;
  }

  /**
   * ����
   * 
   * @param newxqsl
   *          UFDouble
   */
  public void setNnum(UFDouble nnum) {

    this.nnum = nnum;
  }

  /**
   * ����PK
   * 
   * @param newpk_invmandoc
   *          String
   */
  public void setPk_material(String material) {

    this.pk_material = material;
  }

  /**
   * �����֯PK
   * 
   * @param newpk_corp
   *          String
   */
  public void setPk_org(String newPk_org) {

    this.pk_org = newPk_org;
  }

  /**
   * �빺������PK
   * 
   * @param newlyid
   *          String
   */
  public void setPk_praybill(String pk_praybill) {

    this.pk_praybill = pk_praybill;
  }

  /**
   * �빺���ӱ�PK
   * 
   * @param pk_praybill_b
   */
  public void setPk_praybill_b(String pk_praybill_b) {

    this.pk_praybill_b = pk_praybill_b;
  }

  /**
   * �ֿ�PK
   * 
   * @param newckid
   *          String
   */
  public void setPk_reqstor(String newCkid) {

    this.pk_reqstor = newCkid;
  }

  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.pk_srcmaterial = pk_srcmaterial;
  }

  /**
   * ��Ӧ��PK
   * 
   * @param newksid
   *          String
   */
  public void setPk_supplier(String newKsid) {

    this.pk_supplier = newKsid;
  }

  /**
   * �빺����
   * 
   * @param newlydjh
   *          String
   */
  public void setVbillcode(String newLydjh) {

    this.vbillcode = newLydjh;
  }

  public void setVfree1(java.lang.String strTmp) {
    this.vfree1 = strTmp;
  }

  /**
   * @param mVfree10
   *          Ҫ���õ� vfree10
   */
  public void setvfree10(String mVfree10) {
    this.vfree10 = mVfree10;
  }

  public void setVfree2(java.lang.String strTmp) {
    this.vfree2 = strTmp;
  }

  public void setVfree3(java.lang.String strTmp) {
    this.vfree3 = strTmp;
  }

  public void setVfree4(java.lang.String strTmp) {
    this.vfree4 = strTmp;
  }

  public void setVfree5(java.lang.String strTmp) {
    this.vfree5 = strTmp;
  }

  /**
   * @param mVfree6
   *          Ҫ���õ� vfree6
   */
  public void setvfree6(String mVfree6) {
    this.vfree6 = mVfree6;
  }

  /**
   * @param mVfree7
   *          Ҫ���õ� vfree7
   */
  public void setvfree7(String mVfree7) {
    this.vfree7 = mVfree7;
  }

  /**
   * @param mVfree8
   *          Ҫ���õ� vfree8
   */
  public void setvfree8(String mVfree8) {
    this.vfree8 = mVfree8;
  }

  /**
   * @param mVfree9
   *          Ҫ���õ� vfree9
   */
  public void setvfree9(String mVfree9) {
    this.vfree9 = mVfree9;
  }

  /**
   * ��֤���������֮��������߼���ȷ�ԡ� �������ڣ�(2001-11-14)
   * 
   * @exception nc.vo.pub.ValidationException
   *              �����֤ʧ�ܣ��׳� ValidationException���Դ�����н��͡�
   */
  @Override
  public void validate() throws ValidationException {

    ArrayList<String> errFields = new ArrayList<String>(); // errFields record
    // those null fields
    // that cannot be null.
    // ����Ƿ�Ϊ������յ��ֶθ��˿�ֵ���������Ҫ�޸��������ʾ��Ϣ��
    if (this.pk_material == null) {
      errFields.add("pk_material");
    }
    if (this.pk_praybill == null) {
      errFields.add("pk_praybill");
    }
    if (this.pk_praybill_b == null) {
      errFields.add("pk_praybill_b");
    }
    if (this.vbillcode == null) {
      errFields.add("vbillcode");
    }
    // construct the exception message:
    StringBuffer message = new StringBuffer();
    message.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004020_0", "04004020-0085")/* @res "�����ֶβ���Ϊ�գ�" */);
    if (errFields.size() > 0) {
      String[] temp = errFields.toArray(new String[0]);
      message.append(temp[0]);
      for (int i = 1; i < temp.length; i++) {
        message.append("[");
        message.append(temp[i]);
        message.append("]");
      }
      // throw the exception:
      throw new NullFieldException(message.toString());
    }
  }
}
