package nc.vo.pu.report.enumeration;

/**
 * �ɹ���;��ѯ�ֶ�ö��
 * 
 * @since 6.0
 * @version 2012-9-18 ����10:53:28
 * @author lixyp
 */
public enum OrderOnwayQueryFieldCode {

  /** ��Ӧ�̵������� */
  AREACL_NAME("bd_areacl.name"),

  /** ���ϻ������� */
  MARBASCLASS_CODE("bd_marbasclass.code"),

  /** ���ϲɹ����� */
  MARPUCLASS_CODE("bd_marpuclass.code"),

  /** ���ϱ��� */
  MATERIAL_CODE("bd_material_v.code"),

  /** �������� */
  MATERIAL_NAME("bd_material_v.name"),

  /** ��Ӧ�̱��� */
  SUPPLIER_CODE("bd_supplier.code"),

  /** ��Ӧ������ */
  SUPPLIER_NAME("bd_supplier.name");

  private String code = null;

  private OrderOnwayQueryFieldCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return this.code;
  }
}
