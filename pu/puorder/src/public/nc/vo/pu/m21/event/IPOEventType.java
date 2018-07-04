package nc.vo.pu.m21.event;

import nc.bs.businessevent.IEventType;

/**
 * 采购订单事件类型
 * 
 * @since 6.0
 * @version 2011-3-10 上午09:21:09
 * @author wuxla
 */

public interface IPOEventType extends IEventType {

  /**
   * 采购订单取消付款申请后
   */
  String TYPE_CANCEL_PAYAPP_AFTER = "40042112";

  /**
   * 采购订单取消付款申请前
   */
  String TYPE_CANCEL_PAYAPP_BEFORE = "40042111";

  /**
   * 最终关闭后
   */
  String TYPE_FINALCLOSE_AFTER = "40042136";

  /**
   * 最终关闭前
   */
  String TYPE_FINALCLOSE_BEFORE = "40042135";

  /**
   * 最终打开后
   */
  String TYPE_FINALOPEN_AFTER = "40042138";

  /**
   * 最终打开前
   */
  String TYPE_FINALOPEN_BEFORE = "40042137";

  /**
   * 订单付款关闭后
   */
  String TYPE_PAYCLOSE_AFTER = "40042102";

  /**
   * 订单付款关闭前
   */
  String TYPE_PAYCLOSE_BEFORE = "40042101";

  /**
   * 订单付款打开后
   */
  String TYPE_PAYOPEN_AFTER = "40042104";

  /**
   * 订单付款打开前
   */
  String TYPE_PAYOPEN_BEFORE = "40042103";

  /**
   * 行关闭后
   */
  String TYPE_ROWCLOSE_AFTER = "40042132";

  /**
   * 行关闭前
   */
  String TYPE_ROWCLOSE_BEFORE = "40042131";

  /**
   * 行打开后
   */
  String TYPE_ROWOPEN_AFTER = "40042134";

  /**
   * 行打开前
   */
  String TYPE_ROWOPEN_BEFORE = "40042133";

  /**
   * 采购订单送审后
   */
  String TYPE_SENDAPPROVE_AFTER = "40042122";

  /**
   * 采购订单送审前
   */
  String TYPE_SENDAPPROVE_BEFORE = "40042121";

  /**
   * 采购订单收回前
   */
  String TYPE_UNSENDAPPROVE_BEFORE = "40042123";

  /**
   * 采购订单收回后
   */
  String TYPE_UNSENDAPPROVE_AFTER = "40042124";

}
