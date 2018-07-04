package nc.vo.pu.m21.query.currency;

import java.io.Serializable;

import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>币种查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-28 上午11:24:40
 */
public class CurrencyInfo implements Serializable {
  private static final long serialVersionUID = 1L;

  private String currencyId;

  private UFDouble groupExchangeRate;

  private UFDouble nexchangebrate = UFDouble.ZERO_DBL;

  private UFDouble publicExchangeRate;

  private String settleFinanceId;

  /**
   * 方法功能描述：获得结算财务组织的组织本位币
   * <p>
   * <b>参数说明</b>
   * 
   * @return 结算财务组织的组织本位币
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-28 上午11:26:09
   */
  public String getCurrencyId() {
    return this.currencyId;
  }

  /**
   * 方法功能描述：获得集团本位币的汇率
   * <p>
   * <b>参数说明</b>
   * 
   * @return 集团本位币的汇率
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-28 上午11:27:21
   */
  public UFDouble getGroupExchangeRate() {
    return this.groupExchangeRate;
  }

  /**
   * 方法功能描述：原币-->结算财务组织本位币的折本汇率
   * <p>
   * <b>参数说明</b>
   * 
   * @return 折本汇率
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-28 上午11:26:27
   */
  public UFDouble getNexchangebrate() {
    return this.nexchangebrate;
  }

  /**
   * 方法功能描述：获得全局本位币的汇率
   * <p>
   * <b>参数说明</b>
   * 
   * @return 全局本位币的汇率
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-28 上午11:27:51
   */
  public UFDouble getPublicExchangeRate() {
    return this.publicExchangeRate;
  }

  /**
   * 方法功能描述：获得结算财务组织ID
   * <p>
   * <b>参数说明</b>
   * 
   * @return 结算财务组织ID
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-28 上午11:25:26
   */
  public String getSettleFinanceId() {
    return this.settleFinanceId;
  }

  public void setCurrencyId(String currencyId) {
    this.currencyId = currencyId;
  }

  public void setGroupExchangeRate(UFDouble groupExchangeRate) {
    this.groupExchangeRate = groupExchangeRate;
  }

  public void setNexchangebrate(UFDouble nexchangebrate) {
    this.nexchangebrate = nexchangebrate;
  }

  public void setPublicExchangeRate(UFDouble publicExchangeRate) {
    this.publicExchangeRate = publicExchangeRate;
  }

  public void setSettleFinanceId(String settleFinanceId) {
    this.settleFinanceId = settleFinanceId;
  }

}
