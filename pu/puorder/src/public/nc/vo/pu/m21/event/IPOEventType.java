package nc.vo.pu.m21.event;

import nc.bs.businessevent.IEventType;

/**
 * �ɹ������¼�����
 * 
 * @since 6.0
 * @version 2011-3-10 ����09:21:09
 * @author wuxla
 */

public interface IPOEventType extends IEventType {

  /**
   * �ɹ�����ȡ�����������
   */
  String TYPE_CANCEL_PAYAPP_AFTER = "40042112";

  /**
   * �ɹ�����ȡ����������ǰ
   */
  String TYPE_CANCEL_PAYAPP_BEFORE = "40042111";

  /**
   * ���չرպ�
   */
  String TYPE_FINALCLOSE_AFTER = "40042136";

  /**
   * ���չر�ǰ
   */
  String TYPE_FINALCLOSE_BEFORE = "40042135";

  /**
   * ���մ򿪺�
   */
  String TYPE_FINALOPEN_AFTER = "40042138";

  /**
   * ���մ�ǰ
   */
  String TYPE_FINALOPEN_BEFORE = "40042137";

  /**
   * ��������رպ�
   */
  String TYPE_PAYCLOSE_AFTER = "40042102";

  /**
   * ��������ر�ǰ
   */
  String TYPE_PAYCLOSE_BEFORE = "40042101";

  /**
   * ��������򿪺�
   */
  String TYPE_PAYOPEN_AFTER = "40042104";

  /**
   * ���������ǰ
   */
  String TYPE_PAYOPEN_BEFORE = "40042103";

  /**
   * �йرպ�
   */
  String TYPE_ROWCLOSE_AFTER = "40042132";

  /**
   * �йر�ǰ
   */
  String TYPE_ROWCLOSE_BEFORE = "40042131";

  /**
   * �д򿪺�
   */
  String TYPE_ROWOPEN_AFTER = "40042134";

  /**
   * �д�ǰ
   */
  String TYPE_ROWOPEN_BEFORE = "40042133";

  /**
   * �ɹ����������
   */
  String TYPE_SENDAPPROVE_AFTER = "40042122";

  /**
   * �ɹ���������ǰ
   */
  String TYPE_SENDAPPROVE_BEFORE = "40042121";

  /**
   * �ɹ������ջ�ǰ
   */
  String TYPE_UNSENDAPPROVE_BEFORE = "40042123";

  /**
   * �ɹ������ջغ�
   */
  String TYPE_UNSENDAPPROVE_AFTER = "40042124";

}
