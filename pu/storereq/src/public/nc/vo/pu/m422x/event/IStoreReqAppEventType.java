package nc.vo.pu.m422x.event;

import nc.bs.businessevent.IEventType;

/**
 * 物资需求申请单事件类型
 * 
 * @since 6.3
 * @version 2013-1-31 上午11:28:53
 * @author fanly3
 */
public interface IStoreReqAppEventType extends IEventType {
  /**
   * 物资需求申请单行关闭后
   */
  String TYPE_LINECLOSE_AFTER = "4004422X01";

  /**
   * 物资需求申请单行打开后
   */
  String TYPE_LINEOPEN_AFTER = "4004422X02";

  /**
   * 行关闭前
   */
  String TYPE_LINECLOSE_BEFORE = "4004422X03";

  /**
   * 行打开前
   */
  String TYPE_LINEOPEN_BEFORE = "4004422X04";

  /**
   * 整单关闭前
   */
  String TYPE_CLOSE_BEFORE = "4004422X05";

  /**
   * 整单关闭后
   */
  String TYPE_CLOSE_AFTER = "4004422X06";

  /**
   * 整单打开前
   */
  String TYPE_OPEN_BEFORE = "4004422X07";

  /**
   * 整单打开后
   */
  String TYPE_OPEN_AFTER = "4004422X08";

}
