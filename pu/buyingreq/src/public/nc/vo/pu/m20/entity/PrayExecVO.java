package nc.vo.pu.m20.entity;

import java.util.ArrayList;

import nc.vo.pub.NullFieldException;
import nc.vo.pub.ValidationException;
import nc.vo.pub.ValueObject;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单执行数量VO- 给生产制造用
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-21 上午11:11:29
 */
public class PrayExecVO extends ValueObject {

  private static final long serialVersionUID = -1991597905668971567L;

  /** 已完成数量 **/
  private UFDouble completenum;

  /** 生产厂商 **/
  private String cproductorid;

  /** 项目 **/
  private String cprojectid;

  /** 计量单位ID(主计量) **/
  private String cunitid;

  /** 需求日期 **/
  private UFDate dreqdate;

  /** 数量 **/
  private UFDouble nnum;

  /** 物料pk **/
  private String pk_material;

  /** 库存组织PK **/
  private String pk_org;

  /** 请购单主表ID **/
  private String pk_praybill;

  /** 请购单附表ID **/
  private String pk_praybill_b;

  /** 仓库ID **/
  private String pk_reqstor;

  /** 物料Oid **/
  private String pk_srcmaterial;

  /** 供应商ID **/
  private String pk_supplier;

  /** 请购单号 **/
  private String vbillcode;

  /** 自由项1 **/
  private String vfree1;

  /** 自由项10 **/
  private String vfree10;

  /** 自由项2 **/
  private String vfree2;

  /** 自由项3 **/
  private String vfree3;

  /** 自由项4 **/
  private String vfree4;

  /** 自由项5 **/
  private String vfree5;

  /** 自由项6 **/
  private String vfree6;

  /** 自由项6 **/
  private String vfree7;

  /** 自由项8 **/
  private String vfree8;

  /** 自由项9 **/
  private String vfree9;

  /**
   * 描述上面属性的FieldObjects。主要用于系统工具中， 业务代码中不会用到下面的FieldObjects。
   */
  /**
   * 使用主键字段进行初始化的构造子。 创建日期：(2001-11-14)
   */
  public PrayExecVO() {
    // 缺省构造方法
  }

  /**
   * 已完成数量
   * 
   * @return UFDouble
   */
  public UFDouble getCompletenum() {
    return this.completenum;
  }

  /** 生产厂商 **/
  public String getCproductorid() {
    return this.cproductorid;
  }

  /** 项目 **/
  public String getCprojectid() {
    return this.cprojectid;
  }

  /**
   * 主单位
   * 
   * @return String
   */
  public String getCunitid() {
    return this.cunitid;
  }

  /**
   * 需求日期
   * 
   * @return UFDate
   */
  public UFDate getDreqdate() {
    return this.dreqdate;
  }

  /**
   * 返回数值对象的显示名称。 创建日期：(2001-11-14)
   * 
   * @return java.lang.String 返回数值对象的显示名称。
   */
  @Override
  public String getEntityName() {

    return "PrayExec";
  }

  /**
   * 数量
   * 
   * @return UFDouble
   */
  public UFDouble getNnum() {
    return this.nnum;
  }

  /**
   * 物料PK
   * 
   * @return String
   */
  public String getPk_material() {
    return this.pk_material;
  }

  /**
   * 库存组织PK
   * 
   * @return String
   */
  public String getPk_org() {
    return this.pk_org;
  }

  /**
   * 请购单主表PK
   * 
   * @return String
   */
  public String getPk_praybill() {
    return this.pk_praybill;
  }

  /**
   * 请购单子表PK
   * 
   * @return String
   */
  public String getPk_praybill_b() {
    return this.pk_praybill_b;
  }

  /**
   * 仓库PK
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
   * 供应商PK
   * 
   * @return String
   */
  public String getPk_supplier() {
    return this.pk_supplier;
  }

  /**
   * 请购单号
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
   * 已完成数量
   * 
   * @param newwcsl
   *          UFDouble
   */
  public void setCompletenum(UFDouble newWcsl) {

    this.completenum = newWcsl;
  }

  /** 生产厂商 **/
  public void setCproductorid(String cproductorid) {
    this.cproductorid = cproductorid;
  }

  /** 项目 **/
  public void setCprojectid(String cprojectid) {
    this.cprojectid = cprojectid;
  }

  /**
   * 主单位
   * 
   * @param newjldwid
   *          String
   */
  public void setCunitid(String newJldwid) {

    this.cunitid = newJldwid;
  }

  /**
   * 需求日期
   * 
   * @param newxqrq
   *          UFDate
   */
  public void setDreqdate(UFDate dreqdate) {

    this.dreqdate = dreqdate;
  }

  /**
   * 数量
   * 
   * @param newxqsl
   *          UFDouble
   */
  public void setNnum(UFDouble nnum) {

    this.nnum = nnum;
  }

  /**
   * 物料PK
   * 
   * @param newpk_invmandoc
   *          String
   */
  public void setPk_material(String material) {

    this.pk_material = material;
  }

  /**
   * 库存组织PK
   * 
   * @param newpk_corp
   *          String
   */
  public void setPk_org(String newPk_org) {

    this.pk_org = newPk_org;
  }

  /**
   * 请购单主表PK
   * 
   * @param newlyid
   *          String
   */
  public void setPk_praybill(String pk_praybill) {

    this.pk_praybill = pk_praybill;
  }

  /**
   * 请购单子表PK
   * 
   * @param pk_praybill_b
   */
  public void setPk_praybill_b(String pk_praybill_b) {

    this.pk_praybill_b = pk_praybill_b;
  }

  /**
   * 仓库PK
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
   * 供应商PK
   * 
   * @param newksid
   *          String
   */
  public void setPk_supplier(String newKsid) {

    this.pk_supplier = newKsid;
  }

  /**
   * 请购单号
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
   *          要设置的 vfree10
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
   *          要设置的 vfree6
   */
  public void setvfree6(String mVfree6) {
    this.vfree6 = mVfree6;
  }

  /**
   * @param mVfree7
   *          要设置的 vfree7
   */
  public void setvfree7(String mVfree7) {
    this.vfree7 = mVfree7;
  }

  /**
   * @param mVfree8
   *          要设置的 vfree8
   */
  public void setvfree8(String mVfree8) {
    this.vfree8 = mVfree8;
  }

  /**
   * @param mVfree9
   *          要设置的 vfree9
   */
  public void setvfree9(String mVfree9) {
    this.vfree9 = mVfree9;
  }

  /**
   * 验证对象各属性之间的数据逻辑正确性。 创建日期：(2001-11-14)
   * 
   * @exception nc.vo.pub.ValidationException
   *              如果验证失败，抛出 ValidationException，对错误进行解释。
   */
  @Override
  public void validate() throws ValidationException {

    ArrayList<String> errFields = new ArrayList<String>(); // errFields record
    // those null fields
    // that cannot be null.
    // 检查是否为不允许空的字段赋了空值，你可能需要修改下面的提示信息：
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
        "4004020_0", "04004020-0085")/* @res "下列字段不能为空：" */);
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
