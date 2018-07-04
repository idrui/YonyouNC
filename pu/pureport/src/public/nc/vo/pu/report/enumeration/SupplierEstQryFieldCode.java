package nc.vo.pu.report.enumeration;

/**
 * ��Ӧ���ݹ���صĲ�ѯ�����ֶ�ö��
 * 
 * @since 6.0
 * @version 2011-7-27 ����03:00:23
 * @author �����
 */

public enum SupplierEstQryFieldCode {

  /** ��ȫ��������ⵥ�Ƿ�ͳ�� */
  bincldfinish("bincldfinish"),
  /** �������� */
  fperiod("fperiod"),
  /** �������� */
  material_name("bd_material.name"),
  /** ��Ӧ�̵������� */
  pk_areacl("bd_supplier.pk_areacl"),
  /** �ɹ����� */
  pk_dept("eh.pk_dept"),
  /** ������֯ */
  pk_financeorg("eb.pk_financeorg"),
  /** ���ϻ���������� */
  pk_marbasclass("bd_material.pk_marbasclass"),
  /** �����֯ */
  pk_org("eb.pk_org"),
  /** �ɹ�Ա */
  pk_psndoc("eh.pk_psndoc"),
  /** �ɹ���֯ */
  pk_purchaseorg("eb.pk_purchaseorg"),
  /** ���ϱ��� */
  pk_srcmaterial("eb.pk_srcmaterial"),
  /** ��Ӧ�̱��� */
  pk_supplier("eb.pk_supplier"),
  /** ͳ������ */
  qrycontent("qrycontent"),
  /** ��Ӧ������ */
  supplier_name("bd_supplier.name"),

  /** ���κ� */
  vbatchcode("eb.vbatchcode");

  private String code;

  private SupplierEstQryFieldCode(String code) {
    this.code = code;
  }

  public String code() {
    return this.code;
  }
}
