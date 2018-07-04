package nc.vo.pu.m21.context;

import java.io.Serializable;

import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单操作时前台到后台的环境信息，一般随平台动作的userObj向外传
 * <ul>
 * <li>后台检查需要用户确认的标志
 * <li>动作脚本是否由前台手工触发标志
 * </ul>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-25 上午11:41:55
 */
public class OrderContext implements Serializable {
  private static final long serialVersionUID = 8369670137222113733L;

  /**
   * 回写合同，超数量或金额，用户是否确认过
   */
  private UFBoolean contractConfirm = UFBoolean.FALSE;

  /**
   * 超最高限价，用户是否确认过
   */
  private UFBoolean maxPriceConfirm = UFBoolean.FALSE;

  /**
   * 超最高库存，用户是否确认过
   */
  private UFBoolean maxStockConfirm = UFBoolean.FALSE;

  /**
   * 集采控制规则
   */
  private UFBoolean meetcenpurruleconfirm = UFBoolean.FALSE;

  private UFBoolean meetcntcenpurruleconfirm = UFBoolean.FALSE;

  private UFBoolean meetvrmcenpurruleconfirm = UFBoolean.FALSE;

  /**
   * 超现存量，用户是否确认过
   */
  private UFBoolean overPresentStockConfirm = UFBoolean.FALSE;

  /**
   * 请购单数量容差控制
   */
  private UFBoolean PrayNumToleranceConfirm = UFBoolean.FALSE;

  /**
   * 订单修订，容差检查走参数
   */
  private UFBoolean reviseToleranceConfirm = UFBoolean.FALSE;

  /**
   * @return contractConfirm
   */
  public UFBoolean getContractConfirm() {
    return this.contractConfirm;
  }

  /**
   * @return maxPriceConfirm
   */
  public UFBoolean getMaxPriceConfirm() {
    return this.maxPriceConfirm;
  }

  /**
   * @return maxStockConfirm
   */
  public UFBoolean getMaxStockConfirm() {
    return this.maxStockConfirm;
  }

  public UFBoolean getMeetcenpurruleconfirm() {
    return this.meetcenpurruleconfirm;
  }

  public UFBoolean getMeetcntcenpurruleconfirm() {
    return this.meetcntcenpurruleconfirm;
  }

  public UFBoolean getMeetvrmcenpurruleconfirm() {
    return this.meetvrmcenpurruleconfirm;
  }

  public UFBoolean getOverPresentStockConfirm() {
    return this.overPresentStockConfirm;
  }

  public UFBoolean getPrayNumToleranceConfirm() {
    return this.PrayNumToleranceConfirm;
  }

  public UFBoolean getReviseToleranceConfirm() {
    return this.reviseToleranceConfirm;
  }

  /**
   * @param contractConfirm 要设置的 contractConfirm
   */
  public void setContractConfirm(UFBoolean contractConfirm) {
    this.contractConfirm = contractConfirm;
  }

  /**
   * @param maxPriceConfirm 要设置的 maxPriceConfirm
   */
  public void setMaxPriceConfirm(UFBoolean maxPriceConfirm) {
    this.maxPriceConfirm = maxPriceConfirm;
  }

  /**
   * @param maxStockConfirm 要设置的 maxStockConfirm
   */
  public void setMaxStockConfirm(UFBoolean maxStockConfirm) {
    this.maxStockConfirm = maxStockConfirm;
  }

  public void setMeetcenpurruleconfirm(UFBoolean meetcenpurruleconfirm) {
    this.meetcenpurruleconfirm = meetcenpurruleconfirm;
  }

  public void setMeetcntcenpurruleconfirm(UFBoolean meetcntcenpurruleconfirm) {
    this.meetcntcenpurruleconfirm = meetcntcenpurruleconfirm;
  }

  public void setMeetvrmcenpurruleconfirm(UFBoolean meetvrmcntcenpurruleconfirm) {
    this.meetvrmcenpurruleconfirm = meetvrmcntcenpurruleconfirm;
  }

  public void setOverPresentStockConfirm(UFBoolean overPresentStockConfirm) {
    this.overPresentStockConfirm = overPresentStockConfirm;
  }

  public void setPrayNumToleranceConfirm(UFBoolean prayNumToleranceConfirm) {
    this.PrayNumToleranceConfirm = prayNumToleranceConfirm;
  }

  public void setReviseToleranceConfirm(UFBoolean reviseToleranceConfirm) {
    this.reviseToleranceConfirm = reviseToleranceConfirm;
  }

}
