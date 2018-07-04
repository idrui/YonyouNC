package nc.vo.pu.pub.enumeration;

/**
 * ҵ����־��actionö��
 * 
 * @since 6.0
 * @version 2011-5-25 ����09:35:13
 * @author �����
 */
public enum PuBusiLogActionCode {
  /** ���� **/
  approve("approve", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0003")/* @res "����" */),
  /**
   * �����ر�
   */
  arriveClose("arvClose", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4004000_0", "04004000-0071")/* @res "�����ر�" */),

  /**
   * ������
   */
  arriveOpen("arvOpen", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0072")/* @res "������" */),

  /**
   * �ر�
   */
  close("close", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0073")/* @res "�����ر�" */),
  /**
   * �ύ
   */
  commit("commit", null/* @res "�ύ" */),
  /**
   * ɾ��
   */
  delete("delete", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "common", "UC001-0000039")/* @res "ɾ��" */),

  // ����
  edit("edit", null),
  /**
   * ����
   */
  freeze("freeze", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "common", "UC001-0000030")/* @res "����" */),

  // ����
  insert("insert", null),

  /**
   * ��Ʊ�ر�
   */
  invoiceClose("invClose", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4004000_0", "04004000-0074")/* @res "��Ʊ�ر�" */),
  /**
   * ��Ʊ��
   */
  invoiceOpen("invOpen", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0075")/* @res "��Ʊ��" */),
  /**
   * ��
   */
  open("open", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0076")/* @res "������" */),

  /**
   * ȡ������
   */
  /**
   * ����ر�
   */
  payClose("payClose", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0077")/* @res "����ر�" */),
  /**
   * �����
   */
  payOpen("payOpen", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0078")/* @res "�����" */),

  /**
   * �йر�
   */
  rowClose("rowClose", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0079")/* @res "�йر�" */),
  /**
   * �д�
   */
  rowOpen("rowOpen", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0080")/* @res "�д�" */),
  /**
   * ����
   */
  save("save", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
      "UC001-0000001")/* @res "����" */),
  /**
   * �ύ
   */
  sendapprove("sendapprove", null/* @res "�ύ" */),

  /**
   * ���ر�
   */
  stockClose("stockClose", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4004000_0", "04004000-0081")/* @res "���ر�" */),
  /**
   * ����
   */
  stockOpen("stockOpen", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0082")/* @res "����" */),

  unapprove("unapprove", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0083")/* @res "ȡ������" */),
  /**
   * ���ύ
   */
  unCommit("uncommit", null/* @res "���ύ" */),
  /**
   * �ⶳ
   */
  unfreeze("unfreeze", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "common", "UC001-0000031")/* @res "�ⶳ" */),
  /**
   * �ջ�
   */
  unSendapprove("unSendapprove", null/* @res "�ջ�" */),
  // ����
  update("update", null);

  private String code;

  private String name;

  private PuBusiLogActionCode(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return this.code;
  }

  public String getName() {
    return this.name;
  }
}
