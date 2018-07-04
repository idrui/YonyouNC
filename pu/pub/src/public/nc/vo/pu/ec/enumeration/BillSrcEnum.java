package nc.vo.pu.ec.enumeration;

public enum BillSrcEnum {
  /** �ɹ���������ִ�������ѯ **/
  ALL("all"),
  /** �ɹ����� **/
  EC("ec"),
  /** ��Դ��Ӧ�� **/
  SCM("scm");

  private String code;

  private BillSrcEnum(String code) {
    this.code = code;
  }

  public String code() {
    return this.code;
  }

}
