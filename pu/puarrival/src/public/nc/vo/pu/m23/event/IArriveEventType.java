package nc.vo.pu.m23.event;

import nc.bs.businessevent.IEventType;

/**
 * 到货单事件类型
 * 
 * @since 6.3
 * @version 2013-5-6 下午03:06:26
 * @author fanly3
 */
public interface IArriveEventType extends IEventType {
  /**
   * 到货单送审前
   */
  String TYPE_SENDAPPROVE_BEFORE = "40042301";

  /**
   * 到货单送审后
   */
  String TYPE_SENDAPPROVE_AFTER = "40042302";

  /**
   * 到货单收回前
   */
  String TYPE_UNSENDAPPROVE_BEFORE = "40042303";

  /**
   * 到货单收回后
   */
  String TYPE_UNSENDAPPROVE_AFTER = "40042304";
}
