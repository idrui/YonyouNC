package nc.vo.pu.m4201.enumeration;

/**
 * ��ⵥ�����ѯ�Ի���ʹ�õ�field_codeö��
 * 
 * @since 6.0
 * @version 2011-3-6 ����10:04:12
 * @author zhaoyha
 */
public enum StockQryFieldCode {
  /** �Ƿ���ȷ��Ӧ���ͳɱ� **/
  bconfirmed("bconfirminclude"),
  /** �Ƿ��ѷ��ý��� **/
  bfeesettled("bfeesettled"),
  /** ���������ϣ����ڽ���Ĳ�ѯ���Ƿ����������ⵥ���Ϲ�����ⵥ���ϣ� **/
  bmarinclude("bmarinclude"),
  /** �Ƿ��ѽ��㣨���ڽ���Ĳ�ѯ�� **/
  bsettled("bsettled"),
  /** ���������֯ **/
  financeorgbody("pk_stockps_b.pk_financeorg"),
  /** ��Ʊ������key�����ڴ�scheme��Ϊkeyʹ�� */
  invoice_matrial_id("invoice_matrial_id"),
  /** ��ⵥ�ӱ��� **/
  invoicebgroup("pk_stockps_b.pk_group"),
  /** ��ⵥ�ӱ�����֯ **/
  invoiceborg("pk_stockps_b.pk_org"),
  /** ��ⵥ�ӱ�Ӧ�� **/
  invoicebsup("pk_stockps_b.pk_supplier"),
  /** ���ϻ������� **/
  marbascls("pk_stockps_b.pk_material.pk_marbasclass"),
  /** ���ϱ��� **/
  marcode("pk_stockps_b.pk_material.code"),
  /** �������� **/
  marname("pk_stockps_b.pk_material.name"),
  /** ���ϱ��� **/
  srcmarcode("pk_stockps_b.pk_srcmaterial.code"),
  /** �������� **/
  srcmarname("pk_stockps_b.pk_srcmaterial.name"),
  /** ��ѯ����ⵥ�������� **/
  stockbilltype("stockbilltype"),

  /** �ֿ� */
  stordoc("pk_stordoc"),

  /** �����֯ */
  storeorg("pk_storeorg"),
  /** ��ͬ�� */
  vctcode("pk_stockps_b.vctcode");

  private String code;

  private StockQryFieldCode(String code) {
    this.code = code;
  }

  public String code() {
    return this.code;
  }

}
