package nc.vo.pu.m20.price;

import java.io.Serializable;

import nc.pubitf.pu.m21.pub.IOrderPriceQueryParam;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ѯ����ʹ�õĽӿ����Ͳ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-21 ����03:25:00
 */
public class OrderPrice implements IOrderPriceQueryParam, Serializable {
  
  public OrderPrice() {}
  
  public OrderPrice(String material) {
    this.material = material;
  }

  private static final long serialVersionUID = 7299924672471328628L;

  private String financeOrg;

  private String material;

  private UFDouble origPrice;

  private UFDouble origTaxPrice;

  private UFDouble price;

  private UFDouble taxPrice;

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
   * @param material
   *          Ҫ���õ� material
   */
  public void setMaterial(String material) {
    this.material = material;
  }

  /**
   * @param origPrice
   *          Ҫ���õ� origPrice
   */
  @Override
  public void setOrigPrice(UFDouble origPrice) {
    this.origPrice = origPrice;
  }

  /**
   * @param origTaxPrice
   *          Ҫ���õ� origTaxPrice
   */
  @Override
  public void setOrigTaxPrice(UFDouble origTaxPrice) {
    this.origTaxPrice = origTaxPrice;
  }

  /**
   * @param price
   *          Ҫ���õ� price
   */
  @Override
  public void setPrice(UFDouble price) {
    this.price = price;
  }

  /**
   * @param taxPrice
   *          Ҫ���õ� taxPrice
   */
  @Override
  public void setTaxPrice(UFDouble taxPrice) {
    this.taxPrice = taxPrice;
  }
}
