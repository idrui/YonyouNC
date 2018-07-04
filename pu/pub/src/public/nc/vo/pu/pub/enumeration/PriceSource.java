/**
 * $�ļ�˵��$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-19 ����10:03:31
 */
package nc.vo.pu.pub.enumeration;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ��۸���Դ��ö��
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-19 ����10:03:31
 */
public enum PriceSource {
  /** ��Ĭ�� **/
  NoDefault(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0064")/*@res "��Ĭ��"*/),
  /** ������ͼ� **/
  OrderMinPrice(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0065")/*@res "������ͼ�"*/),
  /** �������¼� **/
  OrderNewestPrice(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0066")/*@res "�������¼�"*/),
  /** ������ **/
  OrderPice(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0067")/*@res "������"*/),
  /** �ƻ��� **/
  PlanPrice(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0003465")/*@res "�ƻ���"*/),
  /** �빺���� **/
  PrayPrice(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0068")/*@res "�빺������"*/),
  /** ��ⵥ�� **/
  PurchaseInPrice(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0000370")/*@res "��ⵥ��"*/),
  /** �ο��ɱ� **/
  RefCostPrice(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0000958")/*@res "�ο��ɱ�"*/),
  /** ���½���� **/
  SettleNewestPrice(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0069")/*@res "���½����"*/),
  /** ��Ӧ�̼�Ŀ�� **/
  SupplierPrice(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0070")/*@res "��Ӧ�̼�Ŀ��"*/);

  private String code = null;

  private PriceSource(String code) {
    this.code = code;
  }

  public String code() {
    return this.code;
  }
}