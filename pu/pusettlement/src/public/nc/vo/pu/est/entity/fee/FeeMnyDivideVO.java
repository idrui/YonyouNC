/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-23 ����11:18:20
 */
package nc.vo.pu.est.entity.fee;

import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���÷�̯����VO���ҷ��ط�̯�õ��Ľ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-23 ����11:18:20
 */
public class FeeMnyDivideVO {
  // ��ͷ����
  private String billhpk;

  private UFDouble billmny;

  private UFDouble billnum;

  private String billpk;

  // ��������
  private String billtype;

  private UFDouble byvalue;

  /** ��̯���Ľ�ԭ�Ҽ�˰�ϼƣ� */
  private UFDouble dividedmny;

  /** �Ƿ񵹼��У������һ�� */
  private boolean lastRow = false;

  private String mvid;

  /** Ҫ��̯���ܵļǳɱ���� **/
  private UFDouble totalCalcostMny;

  /** Ҫ��̯���ܵļ�˰��� **/
  private UFDouble totalCaltaxMny;

  /** Ҫ��̯���ܵļ�˰�ϼƣ����ң� **/
  private UFDouble totalMny;

  /** Ҫ��̯���ܵĲ��ɵֿ�˰����ң� **/
  private UFDouble totalNoSubTaxMny;

  /** Ҫ��̯���ܵ���˰��ԭ�ң� **/
  private UFDouble totalOrigMny;

  /** Ҫ��̯���ܵ�˰����ң� **/
  private UFDouble totalOrigTax;

  /** Ҫ��̯���ܵļ�˰�ϼƣ�ԭ�ң� **/
  private UFDouble totalOrigTaxMny;

  /** Ҫ��̯���ܵ�˰����ң� **/
  private UFDouble totalTax;

  /** Ҫ��̯���ܵ���˰�����ң� **/
  private UFDouble totalTaxMny;

  /**
   * FeeMnyDivideVO �Ĺ�����
   * 
   * @param billmny
   *          ���ݽ������Խ���̯��ʽ
   * @param billnum
   *          �������������ڳ�����̯��ʽ���������̯��ʽ
   * @param pk_material
   *          ����VID
   */
  public FeeMnyDivideVO(UFDouble billmny, UFDouble billnum, String pk_material) {
    this.billmny = billmny;
    this.billnum = billnum;
    this.mvid = pk_material;
  }

  public String getBillhpk() {
    return this.billhpk;
  }

  /**
   * @return billmny
   */
  public UFDouble getBillmny() {
    return this.billmny;
  }

  /**
   * @return billnum
   */
  public UFDouble getBillnum() {
    return this.billnum;
  }

  /**
   * @return billpk
   */
  public String getBillpk() {
    return this.billpk;
  }

  public String getBilltype() {
    return this.billtype;
  }

  /**
   * @return byvalue ��̯���ݵ�ֵ(�����������������)
   */
  public UFDouble getByvalue() {
    return this.byvalue;
  }

  /**
   * @return dividedmny ��̯���Ľ��
   */
  public UFDouble getDividedmny() {
    return this.dividedmny;
  }

  /**
   * @return mvid
   */
  public String getMvid() {
    return this.mvid;
  }

  public UFDouble getTotalCalcostMny() {
    return this.totalCalcostMny;
  }

  public UFDouble getTotalCaltaxMny() {
    return this.totalCaltaxMny;
  }

  public UFDouble getTotalMny() {
    return this.totalMny;
  }

  public UFDouble getTotalNoSubTaxMny() {
    return this.totalNoSubTaxMny;
  }

  public UFDouble getTotalOrigMny() {
    return this.totalOrigMny;
  }

  public UFDouble getTotalOrigTax() {
    return this.totalOrigTax;
  }

  public UFDouble getTotalOrigTaxMny() {
    return this.totalOrigTaxMny;
  }

  public UFDouble getTotalTax() {
    return this.totalTax;
  }

  public UFDouble getTotalTaxMny() {
    return this.totalTaxMny;
  }

  public boolean isLastRow() {
    return this.lastRow;
  }

  public void setBillhpk(String billhpk) {
    this.billhpk = billhpk;
  }

  /**
   * @param billmny
   *          Ҫ���õ� billmny
   */
  public void setBillmny(UFDouble billmny) {
    this.billmny = billmny;
  }

  /**
   * @param billnum
   *          Ҫ���õ� billnum
   */
  public void setBillnum(UFDouble billnum) {
    this.billnum = billnum;
  }

  /**
   * @param billpk
   *          Ҫ���õ� billpk
   */
  public void setBillpk(String billpk) {
    this.billpk = billpk;
  }

  public void setBilltype(String billtype) {
    this.billtype = billtype;
  }

  /**
   * @param byvalue
   *          Ҫ���õ� ��̯���ݵ�ֵ((�����������������)
   */
  public void setByvalue(UFDouble byvalue) {
    this.byvalue = byvalue;
  }

  /**
   * @param dividedmny
   *          Ҫ���õ� dividedmny
   */
  public void setDividedmny(UFDouble dividedmny) {
    this.dividedmny = dividedmny;
  }

  public void setLastRow(boolean lastRow) {
    this.lastRow = lastRow;
  }

  /**
   * @param mvid
   *          Ҫ���õ� mvid
   */
  public void setMvid(String mvid) {
    this.mvid = mvid;
  }

  public void setTotalCalcostMny(UFDouble totalCalcostMny) {
    this.totalCalcostMny = totalCalcostMny;
  }

  public void setTotalCaltaxMny(UFDouble totalCaltaxMny) {
    this.totalCaltaxMny = totalCaltaxMny;
  }

  public void setTotalMny(UFDouble totalMny) {
    this.totalMny = totalMny;
  }

  public void setTotalNoSubTaxMny(UFDouble totalNoSubTaxMny) {
    this.totalNoSubTaxMny = totalNoSubTaxMny;
  }

  public void setTotalOrigMny(UFDouble totalOrigMny) {
    this.totalOrigMny = totalOrigMny;
  }

  public void setTotalOrigTax(UFDouble totalOrigTax) {
    this.totalOrigTax = totalOrigTax;
  }

  public void setTotalOrigTaxMny(UFDouble totalOrigTaxMny) {
    this.totalOrigTaxMny = totalOrigTaxMny;
  }

  public void setTotalTax(UFDouble totalTax) {
    this.totalTax = totalTax;
  }

  public void setTotalTaxMny(UFDouble totalTaxMny) {
    this.totalTaxMny = totalTaxMny;
  }

}
