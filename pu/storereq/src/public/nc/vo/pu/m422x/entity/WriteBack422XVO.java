package nc.vo.pu.m422x.entity;

import java.io.Serializable;

import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物资需求申请回写信息
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-19 下午04:05:09
 */
public class WriteBack422XVO implements Serializable {

  private static final long serialVersionUID = 8255850324578069892L;

  /** 用户是否确认过 **/
  private boolean bIsUserConfirm = false;

  /** 差额数量 **/
  private UFDouble diffNum;

  /** 下游单据主表PK **/
  private String pk_downbill;

  /** 下游单据表体PK **/
  private String pk_downbill_b;

  /** 库存组织 **/
  private String pk_org;

  /** 物资需求申请主表PK **/
  private String pk_storereq;

  /** 物资需求申请子表PK **/
  private String pk_storereq_b;

  /** 表体时间戳 **/
  private String strBTS;

  /** 表头时间戳 **/
  private String strHTS;

  /**
   * 差额数量
   * 
   * @return diffNum 差额数量
   */
  public UFDouble getDiffNum() {
    return this.diffNum;
  }

  /**
   * 下游单据主表PK
   * 
   * @return pk_downbill
   */
  public String getPk_downbill() {
    return this.pk_downbill;
  }

  /**
   * 下游单据表体PK
   * 
   * @return pk_downbill_b
   */
  public String getPk_downbill_b() {
    return this.pk_downbill_b;
  }

  /**
   * @return 库存组织
   */
  public String getPk_org() {
    return this.pk_org;
  }

  /**
   * 物资需求申请主表PK
   * 
   * @return pk_storereq
   */
  public String getPk_storereq() {
    return this.pk_storereq;
  }

  /**
   * 物资需求申请子表PK
   * 
   * @return pk_storereq_b
   */
  public String getPk_storereq_b() {
    return this.pk_storereq_b;
  }

  /**
   * 表体时间戳
   * 
   * @return strBTS
   */
  public String getStrBTS() {
    return this.strBTS;
  }

  /**
   * 表头时间戳
   * 
   * @return strHTS
   */
  public String getStrHTS() {
    return this.strHTS;
  }

  /**
   * 获取用户是否确认过
   * 
   * @return 用户确认过，不再提示
   */
  public boolean isUserConfirm() {
    return this.bIsUserConfirm;
  }

  /**
   * 差额数量
   * 
   * @param diffNum
   *          要设置的 差额数量
   */
  public void setDiffNum(UFDouble diffNum) {
    this.diffNum = diffNum;
  }

  /**
   * @param pkDownbill
   *          下游单据主表PK
   */
  public void setPk_downbill(String pkDownbill) {
    this.pk_downbill = pkDownbill;
  }

  /**
   * @param pkDownbillB
   *          下游单据表体PK
   */
  public void setPk_downbill_b(String pkDownbillB) {
    this.pk_downbill_b = pkDownbillB;
  }

  /**
   * @param pkOrg
   *          要设置的库存组织
   */
  public void setPk_org(String pkOrg) {
    this.pk_org = pkOrg;
  }

  /**
   * @param pk_storereq
   *          物资需求申请主表PK
   */
  public void setPk_storereq(String pk_storereq) {
    this.pk_storereq = pk_storereq;
  }

  /**
   * @param pk_storereqB
   *          物资需求申请子表PK
   */
  public void setPk_storereq_b(String pk_storereqB) {
    this.pk_storereq_b = pk_storereqB;
  }

  /**
   * @param strBTS
   *          表体时间戳
   */
  public void setStrBTS(String strBTS) {
    this.strBTS = strBTS;
  }

  /**
   * @param strHTS
   *          表头时间戳
   */
  public void setStrHTS(String strHTS) {
    this.strHTS = strHTS;
  }

  /**
   * 设置用户是否确认过
   * 
   * @param newVal
   *          用户是否确认过
   */
  public void setUserConfirm(boolean newVal) {
    this.bIsUserConfirm = newVal;
  }

}
