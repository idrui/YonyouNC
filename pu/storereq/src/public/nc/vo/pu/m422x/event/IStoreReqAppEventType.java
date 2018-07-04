package nc.vo.pu.m422x.event;

import nc.bs.businessevent.IEventType;

/**
 * �����������뵥�¼�����
 * 
 * @since 6.3
 * @version 2013-1-31 ����11:28:53
 * @author fanly3
 */
public interface IStoreReqAppEventType extends IEventType {
  /**
   * �����������뵥�йرպ�
   */
  String TYPE_LINECLOSE_AFTER = "4004422X01";

  /**
   * �����������뵥�д򿪺�
   */
  String TYPE_LINEOPEN_AFTER = "4004422X02";

  /**
   * �йر�ǰ
   */
  String TYPE_LINECLOSE_BEFORE = "4004422X03";

  /**
   * �д�ǰ
   */
  String TYPE_LINEOPEN_BEFORE = "4004422X04";

  /**
   * �����ر�ǰ
   */
  String TYPE_CLOSE_BEFORE = "4004422X05";

  /**
   * �����رպ�
   */
  String TYPE_CLOSE_AFTER = "4004422X06";

  /**
   * ������ǰ
   */
  String TYPE_OPEN_BEFORE = "4004422X07";

  /**
   * �����򿪺�
   */
  String TYPE_OPEN_AFTER = "4004422X08";

}
