package nc.vo.pu.pub.enumeration;

/**
 * �ɹ�string�ͳ���ö��
 * 
 * @since 6.1
 * @version 2011-12-13 ����8:45:51
 * @author yangtian
 */
public enum PuStrEnum {
  /** �����۸�Ԥ������Чʱ�� **/
  poprc_altt_effibegintime("20:00:00"),
  /** �����۸�Ԥ���� ��Ч�۸����Ԥ��Ĭ�Ͽ�ʼʱ�� **/
  poprc_daily_altexectm("21:00:00"),
  /** �����۸�Ԥ���� ��Ч�۸����Ԥ������pk **/
  poprc_daily_alttypepk("0001Z810000000001145"),
  /** �����۸�Ԥ�����۸���ȡԤ��Ĭ�Ͽ�ʼʱ�� **/
  poprc_monthly_altexectm("22:00:00"),
  /** �����۸�Ԥ�����۸���ȡԤ������pk **/
  poprc_monthly_alttypepk("0001Z810000000001147"),
  /** �����۸�Ԥ�����۸���ȡ��ִ������ **/
  poprc_monthly_execday("02"),
  /** ��֧�ֽ������͵ĵ���VO��ȡ�������͵Ŀ������� **/
  vo_att_notranstype("po_transtype_no_support");

  private String code;

  private PuStrEnum(String code) {
    this.code = code;
  }

  public String code() {
    return this.code;
  }
}
