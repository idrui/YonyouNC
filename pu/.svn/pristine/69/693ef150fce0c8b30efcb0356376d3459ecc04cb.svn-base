package nc.vo.pu.m21.query.price;

import java.io.Serializable;

import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

/**
 * 订单价格信息的承载对象
 * <p>
 * 
 * @version 2010-3-21 下午10:53:39
 * @since 6.0
 * @author duy
 */
public class OrderPriceData implements Serializable {

  private static final long serialVersionUID = 8763539796308656258L;

  // 要查询的单位（主、辅、还是报价，由接口约定）
  private String cunit;

  // 原币币种
  private String currencyId;

  // 物料OID
  private String material;

  // 原币不含税净价（是主还是报价，由接口约定）
  private UFDouble origPrice;

  // 原币含税净价（是主还是报价，由接口约定）
  private UFDouble origTaxPrice;

  /**
   * 结算财务组织
   */
  private String pk_financeorg;

  /** 采购组织 */
  private String pk_purchaseOrg = null;

  /** 供应商 */
  private String pk_supplier = null;

  // 本币不含税净价（是主还是报价，由接口约定）
  private UFDouble price;

  // 本币含税净价（是主还是报价，由接口约定）
  private UFDouble taxPrice;

  private UFDateTime ts;

  public OrderPriceData(final String pk_purchaseOrg, final String pk_supplier,
      final String currencyId, final String material, final UFDouble origPrice,
      final UFDouble origTaxPrice, final UFDouble price,
      final UFDouble taxPrice, final String pk_financeorg, final String cunit) {
    this.pk_purchaseOrg = pk_purchaseOrg;
    this.pk_supplier = pk_supplier;
    this.price = price;
    this.taxPrice = taxPrice;
    this.origPrice = origPrice;
    this.origTaxPrice = origTaxPrice;
    this.material = material;
    this.currencyId = currencyId;
    this.pk_financeorg = pk_financeorg;
    this.cunit = cunit;
  }

  /**
   * 要查询的单位（主、辅、还是报价，由接口约定）
   * 
   * @return
   */
  public final String getCunit() {
    return this.cunit;
  }

  /**
   * 获得币种
   * 
   * @return 币种
   * @since 6.0
   */
  public final String getCurrencyId() {
    return this.currencyId;
  }

  /**
   * 方法功能描述：物料的OID（当查询订单最新价时有值）
   * 
   * @return 物料的OID
   * @since 6.0
   */
  public final String getMaterial() {
    return this.material;

  }

  /**
   * 方法功能描述：原币不含税净价
   * 
   * @return 原币不含税净价
   * @since 6.0
   */
  public final UFDouble getOrigPrice() {
    return this.origPrice;
  }

  /**
   * 方法功能描述：原币含税净价
   * 
   * @return 原币含税净价
   * @since 6.0
   */
  public final UFDouble getOrigTaxPrice() {
    return this.origTaxPrice;
  }

  /**
   * 订单上的结算财务组织
   * 
   * @return
   */
  public final String getPk_financeorg() {
    return this.pk_financeorg;
  }

  /** 采购组织 getter 方法 */
  public final String getPk_purchaseOrg() {
    return this.pk_purchaseOrg;
  }

  /** 供应商 getter 方法 */
  public final String getPk_supplier() {
    return this.pk_supplier;
  }

  /**
   * 获得本币不含税净价
   * 
   * @return 本币不含税净价
   */
  public final UFDouble getPrice() {
    return this.price;
  }

  /**
   * 获得本币含税净价
   * 
   * @return 本币含税净价
   */
  public final UFDouble getTaxPrice() {
    return this.taxPrice;
  }

  /**
   * 获得ts字段
   * 
   * @return 本币含税净价
   */
  public final UFDateTime getTs() {
    return this.ts;
  }

  public final void setCunit(final String cunit) {
    this.cunit = cunit;
  }

  /**
   * 设定结算财务组织
   */
  public final void setPk_financeorg(final String pk_financeorg) {
    this.pk_financeorg = pk_financeorg;
  }

  /** 采购组织 setter 方法 */
  public final void setPk_purchaseOrg(String pk_purchaseOrg) {
    this.pk_purchaseOrg = pk_purchaseOrg;
  }

  /** 供应商 setter 方法 */
  public final void setPk_supplier(String pk_supplier) {
    this.pk_supplier = pk_supplier;
  }

  public final void setTs(final UFDateTime ts) {
    this.ts = ts;
  }
}
