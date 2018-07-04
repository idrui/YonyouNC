package nc.vo.pu.pub.enumeration;

import nc.vo.pub.lang.ICalendar;

/**
 * 采购int型常量枚举
 * 
 * @since 6.0
 * @version 2011-11-30 上午8:45:51
 * @author zhaoyha
 */
public enum PuIntEnum {
  /** 订单价格预警， 一共尝试6次 **/
  poprc_retry_time(6),
  /** 订单价格预警，十分钟一次 **/
  poprc_wait_time(ICalendar.MILLIS_PER_MINUTE * 10);

  private int value;

  private PuIntEnum(int value) {
    this.value = value;
  }

  public int value() {
    return this.value;
  }

}
