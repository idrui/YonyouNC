package nc.vo.pu.report.enumeration;

public enum OrderSummaryQryFieldCode {

  /** ���չر� */
  bfinalclose("bfinalclose"),
  /** ���� */
  bfrozen("bfrozen"),
  /** �˻� */
  breturn("breturn"),
  /** �ͻ� */
  casscustid("pk_order_b.casscustid"),
  /** �ɹ�Ա */
  cemployeeid("cemployeeid"),
  /** �������� */
  cproductorid("pk_order_b.cproductorid"),
  /** ��Ŀ */
  cprojectid("pk_order_b.cprojectid"),
  /** �������� */
  ctrantypeid("ctrantypeid"),
  /** �������� */
  dbilldate("dbilldate"),
  /** ����״̬ */
  forderstatus("forderstatus"),
  /** ���ϲɹ�������� */
  marpuclasscode("pk_order_b.pk_material.materialstock.pk_marpuclass.code"),
  /** ���Ϸ��� */
  materialclasscode("pk_order_b.pk_srcmaterial.pk_marbasclass.code"),
  /** ���ϱ��� */
  materialcode("pk_order_b.pk_srcmaterial.code"),
  /** �������� */
  materialname("pk_order_b.pk_srcmaterial.name"),
  /** �ɹ����� */
  pk_dept("pk_dept"),
  /** �����Զ�����1 */
  pk_order_b_vbdef1("pk_order_b.vbdef1"),
  /** �����Զ�����10 */
  pk_order_b_vbdef10("pk_order_b.vbdef10"),
  /** �����Զ�����11 */
  pk_order_b_vbdef11("pk_order_b.vbdef11"),
  /** �����Զ�����12 */
  pk_order_b_vbdef12("pk_order_b.vbdef12"),
  /** �����Զ�����13 */
  pk_order_b_vbdef13("pk_order_b.vbdef13"),
  /** �����Զ�����14 */
  pk_order_b_vbdef14("pk_order_b.vbdef14"),
  /** �����Զ�����15 */
  pk_order_b_vbdef15("pk_order_b.vbdef15"),
  /** �����Զ�����16 */
  pk_order_b_vbdef16("pk_order_b.vbdef16"),
  /** �����Զ�����17 */
  pk_order_b_vbdef17("pk_order_b.vbdef17"),
  /** �����Զ�����18 */
  pk_order_b_vbdef18("pk_order_b.vbdef18"),
  /** �����Զ�����19 */
  pk_order_b_vbdef19("pk_order_b.vbdef19"),
  /** �����Զ�����2 */
  pk_order_b_vbdef2("pk_order_b.vbdef2"),
  /** �����Զ�����20 */
  pk_order_b_vbdef20("pk_order_b.vbdef20"),
  /** �����Զ�����3 */
  pk_order_b_vbdef3("pk_order_b.vbdef3"),
  /** �����Զ�����4 */
  pk_order_b_vbdef4("pk_order_b.vbdef4"),
  /** �����Զ�����5 */
  pk_order_b_vbdef5("pk_order_b.vbdef5"),
  /** �����Զ�����6 */
  pk_order_b_vbdef6("pk_order_b.vbdef6"),
  /** �����Զ�����7 */
  pk_order_b_vbdef7("pk_order_b.vbdef7"),
  /** �����Զ�����8 */
  pk_order_b_vbdef8("pk_order_b.vbdef8"),
  /** �����Զ�����9 */
  pk_order_b_vbdef9("pk_order_b.vbdef9"),
  /** ���ɸ�������1 */
  pk_order_b_vfree1("pk_order_b.vfree1"),
  /** ���ɸ�������2 */
  pk_order_b_vfree2("pk_order_b.vfree2"),
  /** ���ɸ�������3 */
  pk_order_b_vfree3("pk_order_b.vfree3"),
  /** ���ɸ�������4 */
  pk_order_b_vfree4("pk_order_b.vfree4"),
  /** ���ɸ�������5 */
  pk_order_b_vfree5("pk_order_b.vfree5"),
  /** �ɹ���֯ */
  pk_org("pk_org"),
  /** ��Ӧ�̵������� */
  supplierareacl("pk_supplier.pk_areacl"),
  /** ��Ӧ�̱��� */
  suppliercode("pk_supplier.code"),
  /** ��Ӧ������ */
  suppliername("pk_supplier.name"),
  /** ������� */
  vbillcode("vbillcode"),
  /** �Զ�����1 */
  vdef1("vdef1"),
  /** �Զ�����10 */
  vdef10("vdef10"),
  /** �Զ�����11 */
  vdef11("vdef11"),
  /** �Զ�����12 */
  vdef12("vdef12"),
  /** �Զ�����13 */
  vdef13("vdef13"),
  /** �Զ�����14 */
  vdef14("vdef14"),
  /** �Զ�����15 */
  vdef15("vdef15"),
  /** �Զ�����16 */
  vdef16("vdef16"),
  /** �Զ�����17 */
  vdef17("vdef17"),
  /** �Զ�����18 */
  vdef18("vdef18"),
  /** �Զ�����19 */
  vdef19("vdef19"),
  /** �Զ�����2 */
  vdef2("vdef2"),
  /** �Զ�����20 */
  vdef20("vdef20"),
  /** �Զ�����3 */
  vdef3("vdef3"),
  /** �Զ�����4 */
  vdef4("vdef4"),
  /** �Զ�����5 */
  vdef5("vdef5"),
  /** �Զ�����6 */
  vdef6("vdef6"),
  /** �Զ�����7 */
  vdef7("vdef7"),
  /** �Զ�����8 */
  vdef8("vdef8"),
  /** �Զ�����9 */
  vdef9("vdef9");

  private String code = null;

  private OrderSummaryQryFieldCode(String code) {
    this.code = code;
  }

  public String getFieldCode() {
    return this.code;
  }
}
