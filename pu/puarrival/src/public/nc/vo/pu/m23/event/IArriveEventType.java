package nc.vo.pu.m23.event;

import nc.bs.businessevent.IEventType;

/**
 * �������¼�����
 * 
 * @since 6.3
 * @version 2013-5-6 ����03:06:26
 * @author fanly3
 */
public interface IArriveEventType extends IEventType {
  /**
   * ����������ǰ
   */
  String TYPE_SENDAPPROVE_BEFORE = "40042301";

  /**
   * �����������
   */
  String TYPE_SENDAPPROVE_AFTER = "40042302";

  /**
   * �������ջ�ǰ
   */
  String TYPE_UNSENDAPPROVE_BEFORE = "40042303";

  /**
   * �������ջغ�
   */
  String TYPE_UNSENDAPPROVE_AFTER = "40042304";
}
