package nc.vo.pu.m4202.enumeration;

/**
 * ���Ļ��ܽ����ѯ�Ի���ʹ�õ�field_codeö��
 * 
 * @since 6.0
 * @version 2011-3-31 ����02:53:04
 * @author �����
 */
public enum VmiQryFieldCode {

  /** �Ƿ��ѷ��ý��� **/
  bfeesettled("bfeesettled"),
  /** ���������ϣ����ڽ���Ĳ�ѯ���Ƿ�������Ĺ��ˣ� **/
  bmarinclude("bmarinclude"),
  /** �Ƿ��ѽ��� **/
  bsettled("bsettled"),
  /** ���������֯ */
  financeorg("pk_financeorg"),
  /** ���� **/
  group("pk_group"),
  /** ���ϻ������� **/
  marbascls("pk_material.pk_marbasclass.code"),
  /** ���ϱ��� **/
  marcode("pk_stockps_b.pk_material.code"),
  /** �������� **/
  marname("pk_stockps_b.pk_material.name"),
  /** ����֯ **/
  org("pk_org"),
  /** ���ϱ��� **/
  srcmarcode("pk_srcmaterial.code"),
  /** �������� **/
  srcmarname("pk_srcmaterial.name"),
  /** �ֿ� */
  stordoc("pk_stordoc"),
  /** �����֯ */
  storeorg("pk_storeorg"),
  /** ��Ӧ�� **/
  supplier("pk_supplier");

  private String code;

  private VmiQryFieldCode(String code) {
    this.code = code;
  }

  public String code() {
    return this.code;
  }

}
