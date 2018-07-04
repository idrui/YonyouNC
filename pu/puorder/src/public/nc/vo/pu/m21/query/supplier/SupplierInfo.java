package nc.vo.pu.m21.query.supplier;

import java.io.Serializable;

import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>供应商相关的信息
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-27 下午08:18:31
 */
public class SupplierInfo implements Serializable {
  private static final long serialVersionUID = -3331690087357076166L;

  private String address;

  private String bankAccount;

  private String billingSupplier;

  private String defaultCurrency;

  private String defaultPaymentTerm;

  private UFBoolean isFreeCust;

  private String ctrantypeid;
  
  private String vtrantypecode;

  private String pk_country;

  private String respDepartment;

  private String respDepartment_v;

  private String respPerson;

  private String transportType;

  /**
   * 方法功能描述：获得供应商发货地址
   * <p>
   * <b>参数说明</b>
   * 
   * @return 发货地址
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-27 下午08:46:00
   */
  public String getAddress() {
    return this.address;
  }

  /**
   * 方法功能描述：获得开户银行
   * <p>
   * <b>参数说明</b>
   * 
   * @return 开户银行
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-27 下午08:45:45
   */
  public String getBankAccount() {
    return this.bankAccount;
  }

  /**
   * 方法功能描述：获得开票供应商
   * <p>
   * <b>参数说明</b>
   * 
   * @return 开票供应商
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-27 下午08:33:53
   */
  public String getBillingSupplier() {
    return this.billingSupplier;
  }

  /**
   * 方法功能描述：获得默认交易币种
   * <p>
   * <b>参数说明</b>
   * 
   * @return 币种
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-27 下午08:33:17
   */
  public String getDefaultCurrency() {
    return this.defaultCurrency;
  }

  /**
   * 方法功能描述：获得默认付款协议
   * <p>
   * <b>参数说明</b>
   * 
   * @return 付款协议
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-27 下午08:33:32
   */
  public String getDefaultPaymentTerm() {
    return this.defaultPaymentTerm;
  }

  /**
   * 方法功能描述：散户
   * <p>
   * <b>参数说明</b>
   * 
   * @return 散户返回true，否则返回false
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-27 下午08:31:38
   */
  public UFBoolean getIsFreeCust() {
    return this.isFreeCust;
  }

  public String getCtranTypeid() {
    return this.ctrantypeid;
  }

  /**
   * 所属国家
   * 
   * @return
   */
  public String getPk_country() {
    return this.pk_country;
  }

  /**
   * 方法功能描述：获得负责部门
   * <p>
   * <b>参数说明</b>
   * 
   * @return 部门
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-27 下午08:32:33
   */
  public String getRespDepartment() {
    return this.respDepartment;
  }

  public String getRespDepartment_v() {
    return this.respDepartment_v;
  }

  /**
   * 方法功能描述：获得负责业务员
   * <p>
   * <b>参数说明</b>
   * 
   * @return 业务员
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-27 下午08:32:58
   */
  public String getRespPerson() {
    return this.respPerson;
  }

  /**
   * 方法功能描述：获得运输方式
   * <p>
   * <b>参数说明</b>
   * 
   * @return 运输方式
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-27 下午08:34:17
   */
  public String getTransportType() {
    return this.transportType;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setBankAccount(String bankAccount) {
    this.bankAccount = bankAccount;
  }

  public void setBillingSupplier(String billingSupplier) {
    this.billingSupplier = billingSupplier;
  }

  public void setDefaultCurrency(String defaultCurrency) {
    this.defaultCurrency = defaultCurrency;
  }

  public void setDefaultPaymentTerm(String defaultPaymentTerm) {
    this.defaultPaymentTerm = defaultPaymentTerm;
  }

  public void setIsFreeCust(UFBoolean isFreeCust) {
    this.isFreeCust = isFreeCust;
  }

  public void setCtrantypeid(String ctrantypeid) {
    this.ctrantypeid = ctrantypeid;
  }

  /**
   * 所属国家
   */
  public void setPk_country(String pk_country) {
    this.pk_country = pk_country;
  }

  public void setRespDepartment(String respDepartment) {
    this.respDepartment = respDepartment;
  }

  public void setRespDepartment_v(String respDepartment_v) {
    this.respDepartment_v = respDepartment_v;
  }

  public void setRespPerson(String respPerson) {
    this.respPerson = respPerson;
  }

  public void setTransportType(String transportType) {
    this.transportType = transportType;
  }

  public String getVtrantypecode() {
    return this.vtrantypecode;
  }

  public void setVtrantypecode(String vtrantypecode) {
    this.vtrantypecode = vtrantypecode;
  }

}
