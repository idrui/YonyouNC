package nc.vo.pu.m25.enumeration;

/**
 * �ɹ���Ʊ��ѯ�Ի������ʹ�õ�field_codeö��
 * 
 * @since 6.0
 * @version 2011-1-17 ����10:53:08
 * @author zhaoyha
 */
public enum InvoiceQryFieldCode {
  /** �Ƿ���÷�Ʊ�����ڽ���Ĳ�ѯ�� **/
  bfeeinvoice("bfeeinvoice"),
  /** ���������ϣ����ڽ���Ĳ�ѯ�� **/
  bmarinclude("bmarinclude"),
  /** �Ƿ����������÷�Ʊ�Ƿ���÷�Ʊ **/
  brelafeeinclude("brelafeeinclude"),
  /** �ɹ����� **/
  deptcode("pk_dept.code"),
  /** ��Ʊ�ӱ������� **/
  invoicebdate("invoicebody.dbilldate"),
  /** ��Ʊ�ӱ��� **/
  invoicebgroup("invoicebody.pk_group"),
  /** ��Ʊ�ӱ����� **/
  invoicebid("invoicebody.pk_invoice_b"),
  /** ��Ʊ�ӱ�����֯ **/
  invoiceborg("invoicebody.pk_org"),
  /** ��Ʊ�ӱ�Ӧ�� **/
  invoicebsup("invoicebody.pk_supplier"),
  /** ���ϻ������� **/
  marbascls("invoicebody.pk_material.pk_marbasclass"),
  /** ���ϱ��� **/
  marcode("invoicebody.pk_material.code"),
  /** �������� **/
  marname("invoicebody.pk_material.name"),
  /** ��Ʊ����֯ **/
  pkorg("pk_org"),
  /** ���ϱ��� **/
  srcmarcode("invoicebody.pk_srcmaterial.code"),
  /** �������� **/
  srcmarname("invoicebody.pk_srcmaterial.name"),
  /** ��Ӧ�� **/
  supplier("pk_supplier");

  private String code;

  private InvoiceQryFieldCode(String code) {
    this.code = code;
  }

  public String code() {
    return this.code;
  }

}
