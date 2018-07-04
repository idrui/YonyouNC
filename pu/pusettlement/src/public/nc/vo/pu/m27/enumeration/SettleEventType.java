/**
 * $�ļ�˵��$
 *
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-18 ����07:09:36
 */
package nc.vo.pu.m27.enumeration;

/**
 * <p>
 * <b>�����¼�����ö��</b>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-20 ����07:09:36
 */
public enum SettleEventType {

  BEFORE_SHOW_RESULT(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0128")/*@res "��ʼ������������"*/, 9),

  FEEDISTRIBUTE_FINISHED(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0129")/*@res "���÷�̯����"*/, 11),

  INIT_PROCESS(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0130")/*@res "��ʼ��ƥ�����"*/, 6), INIT_QUERY(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0131")/*@res "��ʼ����ѯ����"*/, 5),

  MATCH_CANCEL(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0132")/*@res "ȡ��ƥ��"*/, 10),

  PREPARE_MATCHVOS(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0133")/*@res "׼��ƥ������"*/, 8),

  PREPARE_SETTLEVOS(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0134")/*@res "׼����������"*/, 7),

  SAME_MATERIAL_SETTLE(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0135")/*@res "ͬ���Ͻ���"*/, 4),

  SHOW_INVOICE(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0136")/*@res "��ʾ��Ʊ"*/, 1),

  SHOW_PROCESS(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0137")/*@res "��ʾƥ�����"*/, 2),

  SHOW_RESULT(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0138")/*@res "��ʾ����������"*/, 3), SHOW_STOCK(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0139")/*@res "��ʾ��ⵥ"*/, 0);

  private String name;

  private int value;

  SettleEventType(String name, int value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return this.name;
  }

  public int getValue() {
    return this.value;
  }

}