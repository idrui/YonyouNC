package nc.vo.pu.pub.enumeration;

import nc.vo.pub.lang.ICalendar;

/**
 * �ɹ�int�ͳ���ö��
 * 
 * @since 6.0
 * @version 2011-11-30 ����8:45:51
 * @author zhaoyha
 */
public enum PuIntEnum {
  /** �����۸�Ԥ���� һ������6�� **/
  poprc_retry_time(6),
  /** �����۸�Ԥ����ʮ����һ�� **/
  poprc_wait_time(ICalendar.MILLIS_PER_MINUTE * 10);

  private int value;

  private PuIntEnum(int value) {
    this.value = value;
  }

  public int value() {
    return this.value;
  }

}
