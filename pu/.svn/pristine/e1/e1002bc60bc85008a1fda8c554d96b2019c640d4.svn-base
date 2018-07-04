package nc.vo.pu.est.entity.fee;

import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>某种费用项用于分摊的金额
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-23 上午11:32:35
 */
public class FeeMnyVO {
  private int digit = 2;

  /** 计成本金额 **/
  private UFDouble estCalcostmny;

  /** 计税金额 **/
  private UFDouble estCaltaxmny;

  /** 本币无税金额 **/
  private UFDouble estMny;

  /** 不可抵扣税额 **/
  private UFDouble estNoSubTaxmny;

  /** 本币税额 **/
  private UFDouble estTax;

  /** 本币价税合计 **/
  private UFDouble estTaxmny;

  private String feeoid;

  /** 原币价税合计 **/
  private UFDouble mny;

  /** 原币无税金额 **/
  private UFDouble origEstMny;

  /**
   * FeeMnyVO 的构造子
   * 
   * @param feeoid 费用物料OID
   * @param mny
   * @param digit 当前费用对应的精度[暂估时可能有外币]
   */
  public FeeMnyVO(String feeoid, UFDouble mny, int digit) {
    this.feeoid = feeoid;
    this.mny = mny;
    this.digit = digit;
  }

  /**
   * @return digit
   */
  public int getDigit() {
    return this.digit;
  }

  /**
   * wuxla V61 记成本金额
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * 
   * @return
   */
  public UFDouble getEstCalcostmny() {
    return this.estCalcostmny;
  }

  public UFDouble getEstCaltaxmny() {
    return this.estCaltaxmny;
  }

  public UFDouble getEstMny() {
    return this.estMny;
  }

  public UFDouble getEstNoSubTaxmny() {
    return this.estNoSubTaxmny;
  }

  public UFDouble getEstTax() {
    return this.estTax;
  }

  public UFDouble getEstTaxmny() {
    return this.estTaxmny;
  }

  /**
   * @return 费用物料OID
   */
  public String getFeeoid() {
    return this.feeoid;
  }

  /**
   * @return 费用总金额
   */
  public UFDouble getMny() {
    return this.mny;
  }

  public UFDouble getOrigEstMny() {
    return this.origEstMny;
  }

  /**
   * @param digit 要设置的 digit
   */
  public void setDigit(int digit) {
    this.digit = digit;
  }

  // public UFDouble getOrigEstTax() {
  // return this.origEstTax;
  // }

  public void setEstCalcostmny(UFDouble estCalcostmny) {
    this.estCalcostmny = estCalcostmny;
  }

  public void setEstCaltaxmny(UFDouble estCaltaxmny) {
    this.estCaltaxmny = estCaltaxmny;
  }

  public void setEstMny(UFDouble estMny) {
    this.estMny = estMny;
  }

  public void setEstNoSubTaxmny(UFDouble estNoSubTaxmny) {
    this.estNoSubTaxmny = estNoSubTaxmny;
  }

  public void setEstTax(UFDouble estTax) {
    this.estTax = estTax;
  }

  public void setEstTaxmny(UFDouble estTaxmny) {
    this.estTaxmny = estTaxmny;
  }

  /**
   * @param feeoid 要设置的 feeoid
   */
  public void setFeeoid(String feeoid) {
    this.feeoid = feeoid;
  }

  /**
   * @param mny 要设置的 mny
   */
  public void setMny(UFDouble mny) {
    this.mny = mny;
  }

  public void setOrigEstMny(UFDouble origEstMny) {
    this.origEstMny = origEstMny;
  }

  // public void setOrigEstTax(UFDouble origEstTax) {
  // this.origEstTax = origEstTax;
  // }

}
