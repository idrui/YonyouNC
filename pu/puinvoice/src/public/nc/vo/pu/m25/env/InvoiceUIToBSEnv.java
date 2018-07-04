/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-25 上午11:49:14
 */
package nc.vo.pu.m25.env;

import nc.vo.pu.m25.enumeration.InvoiceBillAction;
import nc.vo.pu.m25.enumeration.InvoiceUserAnswerType;
import nc.vo.pu.m27.query.SettleBillInfo;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pflow.PfUserObject;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购发票操作时前台到后台的环境信息，一般随平台动作的userObj向外传
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
 * @time 2010-1-25 上午11:49:14
 */
public class InvoiceUIToBSEnv extends PfUserObject {
  private static final long serialVersionUID = 7959855454540972318L;

  /** 用户审批发票时，是否一同审批未审批费用发票 **/
  private InvoiceUserAnswerType approveFee = InvoiceUserAnswerType.NO_ANSWER;

  /** 是否结算自动传应付 */
  private boolean autoSettleSendAP = false;

  /** 某个动作（脚本）是否由前台触发 **/
  private UFBoolean bManual = UFBoolean.FALSE;

  /** 用户删除发票时，是否一同删除费用发票 **/
  private InvoiceUserAnswerType delAnswer = InvoiceUserAnswerType.NO_ANSWER;

  /** 回写订单的数量容差检查提示 **/
  private InvoiceUserAnswerType overPONum = InvoiceUserAnswerType.NO_ANSWER;

  /** 回写订单的单价容差检查提示 **/
  private InvoiceUserAnswerType overPOPrice = InvoiceUserAnswerType.NO_ANSWER;

  /**
   * 发票传应付时使用，保存结算明细信息 结算后自动传应付会设置此值，以减少查询次数
   **/
  private MapList<String, SettleBillInfo> sttlInfoMap;

  /**
   * 前台动作触发者,只用一个bManaul标志无法完成级联驱动脚本的情况 因为流程平台会将此环境信息一直向被驱动脚本传递
   * 例如：approve->sendap 如果只在appvove中将bManual设为true，那在sendap中也拿到true，这里不对的
   * 因此还要增加这个触发者来作为一个条件，一块起作用
   **/
  private InvoiceBillAction trigger = null;

  /**
   * @return approveFee
   */
  public InvoiceUserAnswerType getApproveFee() {
    return this.approveFee;
  }

  /**
   * @return bManual
   */
  public UFBoolean getBManual() {
    return this.bManual;
  }

  /**
   * @return delAnswer
   */
  public InvoiceUserAnswerType getDelAnswer() {
    return this.delAnswer;
  }

  /**
   * @return overPONum
   */
  public InvoiceUserAnswerType getOverPONum() {
    return this.overPONum;
  }

  /**
   * @return overPOPrice
   */
  public InvoiceUserAnswerType getOverPOPrice() {
    return this.overPOPrice;
  }

  /**
   * @return sttlInfoMap 发票传应付时使用，保存结算明细信息
   */

  public MapList<String, SettleBillInfo> getSttlInfoMap() {
    return this.sttlInfoMap;
  }

  /**
   * @return trigger
   */
  public InvoiceBillAction getTrigger() {
    return this.trigger;
  }

  public boolean isAutoSettleSendAP() {
    return this.autoSettleSendAP;
  }

  /**
   * @param approveFee 要设置的 approveFee
   */
  public void setApproveFee(InvoiceUserAnswerType approveFee) {
    this.approveFee = approveFee;
  }

  public void setAutoSettleSendAP(boolean autoSettleSendAP) {
    this.autoSettleSendAP = autoSettleSendAP;
  }

  /**
   * @param manual 要设置的 bManual
   */
  public void setBManual(final UFBoolean manual) {
    this.bManual = manual;
  }

  /**
   * @param delAnswer 要设置的 delAnswer
   */
  public void setDelAnswer(InvoiceUserAnswerType delAnswer) {
    this.delAnswer = delAnswer;
  }

  /**
   * @param overPONum 要设置的 overPONum
   */
  public void setOverPONum(InvoiceUserAnswerType overPONum) {
    this.overPONum = overPONum;
  }

  /**
   * @param overPOPrice 要设置的 overPOPrice
   */
  public void setOverPOPrice(InvoiceUserAnswerType overPOPrice) {
    this.overPOPrice = overPOPrice;
  }

  /**
   * @param sttlInfoMap 要设置的 发票传应付时使用，保存结算明细信息
   */
  public void setSttlInfoMap(MapList<String, SettleBillInfo> sttlInfoMap) {
    this.sttlInfoMap = sttlInfoMap;
  }

  /**
   * @param trigger 要设置的 trigger
   */
  public void setTrigger(InvoiceBillAction trigger) {
    this.trigger = trigger;
  }

}
