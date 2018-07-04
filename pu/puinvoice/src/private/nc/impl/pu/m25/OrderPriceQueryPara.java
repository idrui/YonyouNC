/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-9 上午10:10:55
 */
package nc.impl.pu.m25;

import nc.pubitf.pu.m21.pub.IOrderPriceQueryParam;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>询订单价格参数VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-9 上午10:10:55
 */
public class OrderPriceQueryPara implements IOrderPriceQueryParam {

  private String financeOrg;

  private String material;

  private UFDouble origPrice;

  private UFDouble origTaxPrice;

  private UFDouble price;

  private UFDouble taxPrice;

  public OrderPriceQueryPara(String pk_srcmaterial) {
    this.material = pk_srcmaterial;
  }

  @Override
  public String getFinanceOrg() {
    return this.financeOrg;
  }

  /**
   * @return material
   */
  @Override
  public String getMaterial() {
    return this.material;
  }

  /**
   * @return origPrice
   */
  @Override
  public UFDouble getOrigPrice() {
    return this.origPrice;
  }

  /**
   * @return origTaxPrice
   */
  @Override
  public UFDouble getOrigTaxPrice() {
    return this.origTaxPrice;
  }

  /**
   * @return price
   */
  @Override
  public UFDouble getPrice() {
    return this.price;
  }

  /**
   * @return taxPrice
   */
  @Override
  public UFDouble getTaxPrice() {
    return this.taxPrice;
  }

  @Override
  public void setFinanceOrg(String financeOrg) {
    this.financeOrg = financeOrg;
  }

  /**
   * @param material 要设置的 material
   */
  public void setMaterial(String material) {
    this.material = material;
  }

  /**
   * @param origPrice 要设置的 origPrice
   */
  @Override
  public void setOrigPrice(UFDouble origPrice) {
    this.origPrice = origPrice;
  }

  /**
   * @param origTaxPrice 要设置的 origTaxPrice
   */
  @Override
  public void setOrigTaxPrice(UFDouble origTaxPrice) {
    this.origTaxPrice = origTaxPrice;
  }

  /**
   * @param price 要设置的 price
   */
  @Override
  public void setPrice(UFDouble price) {
    this.price = price;
  }

  /**
   * @param taxPrice 要设置的 taxPrice
   */
  @Override
  public void setTaxPrice(UFDouble taxPrice) {
    this.taxPrice = taxPrice;
  }

}
