/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-27 上午09:41:02
 */
package nc.bs.pu.est.rule.pricequery;

import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估询价返回的结果
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-27 上午09:41:02
 */
public class EstPriceQryResltData {
  private String BID;

  private UFDouble calcostmny; // 计成本金额

  private UFDouble caltaxmny;// 计税金额

  private String HID;

  private UFDouble mny;

  private boolean needCalc = true;

  private UFDouble nosubtax; // 不可抵扣税额

  private UFDouble nosubTaxRate; // 不可抵扣税率（目前供应商价目表用）

  private UFDouble origmny;

  private UFDouble origPrice;

  private UFDouble origtax;

  private UFDouble origTaxPrice;

  private UFDouble origtotalmny;

  private UFDouble price;

  private UFDouble tax;

  private UFDouble taxPrice;

  private UFDouble taxRate;// 税率（目前供应商价目表用）

  private Integer taxTypeFlag;// 扣税类别（目前供应商价目表用）

  private UFDouble totalmny;

  /**
   * EstPriceQryResltData 的构造子
   * 
   * @param bid 暂估单据子表ID
   * @param hid 暂估单据主表ID
   */
  public EstPriceQryResltData(String hid, String bid) {
    this.BID = bid;
    this.HID = hid;
  }

  /**
   * EstPriceQryResltData 的构造子
   * 
   * @param bid 暂估单据子表ID
   * @param hid 暂估单据主表ID
   * @param price 无税单价
   * @param taxPrice 含税单价
   * @param isOrigPrice 是否原币价格
   */
  public EstPriceQryResltData(String bid, String hid, UFDouble price,
      UFDouble taxPrice, boolean isOrigPrice) {
    this.BID = bid;
    this.HID = hid;
    if (!isOrigPrice) {
      this.price = price;
      this.taxPrice = taxPrice;
    }
    else {
      this.origPrice = price;
      this.origTaxPrice = taxPrice;
    }
  }

  /**
   * @return 暂估单据子表ID
   */
  public String getBID() {
    return this.BID;
  }

  public UFDouble getCalcostmny() {
    return this.calcostmny;
  }

  public UFDouble getCaltaxmny() {
    return this.caltaxmny;
  }

  /**
   * @return 暂估单据主表ID
   */
  public String getHID() {
    return this.HID;
  }

  /**
   * @return 无税金额
   */
  public UFDouble getMny() {
    return this.mny;
  }

  public UFDouble getNosubtax() {
    return this.nosubtax;
  }

  /**
   * 不可抵扣税率
   * <p>
   * 使用场景：供应商价目表
   * <ul>
   * <li>
   * </ul>
   * 
   * @return
   */
  public UFDouble getNosubTaxRate() {
    return this.nosubTaxRate;
  }

  /**
   * @return 原币无税金额
   */
  public UFDouble getOrigmny() {
    return this.origmny;
  }

  /**
   * @return origPrice 原币无税单价
   */
  public UFDouble getOrigPrice() {
    return this.origPrice;
  }

  /**
   * @return 原币税额
   */
  public UFDouble getOrigtax() {
    return this.origtax;
  }

  /**
   * @return 原币含税单价
   */
  public UFDouble getOrigTaxPrice() {
    return this.origTaxPrice;
  }

  /**
   * @return 原币价税合计
   */
  public UFDouble getOrigtotalmny() {
    return this.origtotalmny;
  }

  /**
   * @return price 无税单价
   */
  public UFDouble getPrice() {
    return this.price;
  }

  /**
   * @return 税额
   */
  public UFDouble getTax() {
    return this.tax;
  }

  /**
   * @return taxPrice 含税单价
   */
  public UFDouble getTaxPrice() {
    return this.taxPrice;
  }

  public UFDouble getTaxRate() {
    return this.taxRate;
  }

  public Integer getTaxTypeFlag() {
    return this.taxTypeFlag;
  }

  /**
   * @return 价税合计
   */
  public UFDouble getTotalmny() {
    return this.totalmny;
  }

  /**
   * @return 是取单价后计算，还是直接携带单价及金额信息
   */
  public boolean isNeedCalc() {
    return this.needCalc;
  }

  /**
   * @param bid 暂估单据子表ID
   */
  public void setBID(String bid) {
    this.BID = bid;
  }

  public void setCalcostmny(UFDouble calcostmny) {
    this.calcostmny = calcostmny;
  }

  public void setCaltaxmny(UFDouble caltaxmny) {
    this.caltaxmny = caltaxmny;
  }

  /**
   * @param hid 暂估单据主表ID
   */
  public void setHID(String hid) {
    this.HID = hid;
  }

  /**
   * @param mny 无税金额
   */
  public void setMny(UFDouble mny) {
    this.mny = mny;
  }

  /**
   * @param needCalc 是取单价后计算，还是直接携带单价及金额信息
   */
  public void setNeedCalc(boolean needCalc) {
    this.needCalc = needCalc;
  }

  public void setNosubtax(UFDouble nosubtax) {
    this.nosubtax = nosubtax;
  }

  /**
   * 不可抵扣税率
   * <p>
   * 使用场景：供应商价目表
   * <ul>
   * <li>
   * </ul>
   * 
   * @param nosubTaxRate
   */
  public void setNosubTaxRate(UFDouble nosubTaxRate) {
    this.nosubTaxRate = nosubTaxRate;
  }

  /**
   * @param origmny 原币无税金额
   */
  public void setOrigmny(UFDouble origmny) {
    this.origmny = origmny;
  }

  /**
   * @param origPrice 原币无税单价
   */
  public void setOrigPrice(UFDouble origPrice) {
    this.origPrice = origPrice;
  }

  /**
   * @param origtax 原币税额
   */
  public void setOrigtax(UFDouble origtax) {
    this.origtax = origtax;
  }

  /**
   * @param origTaxPrice 原币含税单价
   */
  public void setOrigTaxPrice(UFDouble origTaxPrice) {
    this.origTaxPrice = origTaxPrice;
  }

  /**
   * @param origtotalmny 原币价税合计
   */
  public void setOrigtotalmny(UFDouble origtotalmny) {
    this.origtotalmny = origtotalmny;
  }

  /**
   * @param price 无税单价
   */
  public void setPrice(UFDouble price) {
    this.price = price;
  }

  /**
   * @param tax 税额
   */
  public void setTax(UFDouble tax) {
    this.tax = tax;
  }

  /**
   * @param taxPrice 含税单价
   */
  public void setTaxPrice(UFDouble taxPrice) {
    this.taxPrice = taxPrice;
  }

  public void setTaxRate(UFDouble taxRate) {
    this.taxRate = taxRate;
  }

  public void setTaxTypeFlag(Integer taxTypeFlag) {
    this.taxTypeFlag = taxTypeFlag;
  }

  /**
   * @param totalmny 价税合计
   */
  public void setTotalmny(UFDouble totalmny) {
    this.totalmny = totalmny;
  }
}
