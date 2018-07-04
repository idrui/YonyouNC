package nc.pubitf.pu.m21.pub;

import java.io.Serializable;

import nc.vo.pub.lang.UFDouble;

/**
 * 批量条件寻最新价数据封装类。
 * 
 * @since 6.3
 * @version 2012-12-3 上午10:01:03
 * @author lixyp
 */
public class LastestPriceQueryVO implements Serializable {

  private static final long serialVersionUID = -170721450021841063L;

  /** 币种 */
  private String currency = null;

  /** 主本币无税净价 */
  private UFDouble nnetprice = null;

  /** 主无税净价 */
  private UFDouble norignetprice = null;

  /** 主含税净价 */
  private UFDouble norigtaxnetprice = null;

  /** 主本币含税净价 */
  private UFDouble ntaxnetprice = null;

  /** 采购组织 */
  private String pk_org = null;

  /** 物料OID */
  private String pk_srcmaterial = null;

  /** 供应商 */
  private String pk_supplier = null;

  /** 币种 getter 方法 */
  public String getCurrency() {
    return this.currency;
  }

  /** 主本币无税净价 getter 方法 */
  public UFDouble getNnetprice() {
    return this.nnetprice;
  }

  /** 主无税净价 getter 方法 */
  public UFDouble getNorignetprice() {
    return this.norignetprice;
  }

  /** 主含税净价 getter 方法 */
  public UFDouble getNorigtaxnetprice() {
    return this.norigtaxnetprice;
  }

  /** 主本币含税净价 getter 方法 */
  public UFDouble getNtaxnetprice() {
    return this.ntaxnetprice;
  }

  /** 采购组织 getter 方法 */
  public String getPk_org() {
    return this.pk_org;
  }

  /** 物料OID getter 方法 */
  public String getPk_srcmaterial() {
    return this.pk_srcmaterial;
  }

  /** 供应商 getter 方法 */
  public String getPk_supplier() {
    return this.pk_supplier;
  }

  /** 币种 setter 方法 */
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /** 主本币无税净价 setter 方法 */
  public void setNnetprice(UFDouble nnetprice) {
    this.nnetprice = nnetprice;
  }

  /** 主无税净价 setter 方法 */
  public void setNorignetprice(UFDouble norignetprice) {
    this.norignetprice = norignetprice;
  }

  /** 主含税净价 setter 方法 */
  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.norigtaxnetprice = norigtaxnetprice;
  }

  /** 主本币含税净价 setter 方法 */
  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.ntaxnetprice = ntaxnetprice;
  }

  /** 采购组织 setter 方法 */
  public void setPk_org(String pk_org) {
    this.pk_org = pk_org;
  }

  /** 物料OID setter 方法 */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.pk_srcmaterial = pk_srcmaterial;
  }

  /** 供应商 setter 方法 */
  public void setPk_supplier(String pk_supplier) {
    this.pk_supplier = pk_supplier;
  }
}
