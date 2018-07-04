/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-27 ����09:41:02
 */
package nc.bs.pu.est.rule.pricequery;

import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ݹ�ѯ�۷��صĽ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-27 ����09:41:02
 */
public class EstPriceQryResltData {
  private String BID;

  private UFDouble calcostmny; // �Ƴɱ����

  private UFDouble caltaxmny;// ��˰���

  private String HID;

  private UFDouble mny;

  private boolean needCalc = true;

  private UFDouble nosubtax; // ���ɵֿ�˰��

  private UFDouble nosubTaxRate; // ���ɵֿ�˰�ʣ�Ŀǰ��Ӧ�̼�Ŀ���ã�

  private UFDouble origmny;

  private UFDouble origPrice;

  private UFDouble origtax;

  private UFDouble origTaxPrice;

  private UFDouble origtotalmny;

  private UFDouble price;

  private UFDouble tax;

  private UFDouble taxPrice;

  private UFDouble taxRate;// ˰�ʣ�Ŀǰ��Ӧ�̼�Ŀ���ã�

  private Integer taxTypeFlag;// ��˰���Ŀǰ��Ӧ�̼�Ŀ���ã�

  private UFDouble totalmny;

  /**
   * EstPriceQryResltData �Ĺ�����
   * 
   * @param bid �ݹ������ӱ�ID
   * @param hid �ݹ���������ID
   */
  public EstPriceQryResltData(String hid, String bid) {
    this.BID = bid;
    this.HID = hid;
  }

  /**
   * EstPriceQryResltData �Ĺ�����
   * 
   * @param bid �ݹ������ӱ�ID
   * @param hid �ݹ���������ID
   * @param price ��˰����
   * @param taxPrice ��˰����
   * @param isOrigPrice �Ƿ�ԭ�Ҽ۸�
   */
  public EstPriceQryResltData(String bid, String hid, UFDouble price,
      UFDouble taxPrice, boolean isOrigPrice) {
    this.BID = bid;
    this.HID = hid;
    if (!isOrigPrice) {
      this.price = price;
      this.taxPrice = taxPrice;
    }
    else {
      this.origPrice = price;
      this.origTaxPrice = taxPrice;
    }
  }

  /**
   * @return �ݹ������ӱ�ID
   */
  public String getBID() {
    return this.BID;
  }

  public UFDouble getCalcostmny() {
    return this.calcostmny;
  }

  public UFDouble getCaltaxmny() {
    return this.caltaxmny;
  }

  /**
   * @return �ݹ���������ID
   */
  public String getHID() {
    return this.HID;
  }

  /**
   * @return ��˰���
   */
  public UFDouble getMny() {
    return this.mny;
  }

  public UFDouble getNosubtax() {
    return this.nosubtax;
  }

  /**
   * ���ɵֿ�˰��
   * <p>
   * ʹ�ó�������Ӧ�̼�Ŀ��
   * <ul>
   * <li>
   * </ul>
   * 
   * @return
   */
  public UFDouble getNosubTaxRate() {
    return this.nosubTaxRate;
  }

  /**
   * @return ԭ����˰���
   */
  public UFDouble getOrigmny() {
    return this.origmny;
  }

  /**
   * @return origPrice ԭ����˰����
   */
  public UFDouble getOrigPrice() {
    return this.origPrice;
  }

  /**
   * @return ԭ��˰��
   */
  public UFDouble getOrigtax() {
    return this.origtax;
  }

  /**
   * @return ԭ�Һ�˰����
   */
  public UFDouble getOrigTaxPrice() {
    return this.origTaxPrice;
  }

  /**
   * @return ԭ�Ҽ�˰�ϼ�
   */
  public UFDouble getOrigtotalmny() {
    return this.origtotalmny;
  }

  /**
   * @return price ��˰����
   */
  public UFDouble getPrice() {
    return this.price;
  }

  /**
   * @return ˰��
   */
  public UFDouble getTax() {
    return this.tax;
  }

  /**
   * @return taxPrice ��˰����
   */
  public UFDouble getTaxPrice() {
    return this.taxPrice;
  }

  public UFDouble getTaxRate() {
    return this.taxRate;
  }

  public Integer getTaxTypeFlag() {
    return this.taxTypeFlag;
  }

  /**
   * @return ��˰�ϼ�
   */
  public UFDouble getTotalmny() {
    return this.totalmny;
  }

  /**
   * @return ��ȡ���ۺ���㣬����ֱ��Я�����ۼ������Ϣ
   */
  public boolean isNeedCalc() {
    return this.needCalc;
  }

  /**
   * @param bid �ݹ������ӱ�ID
   */
  public void setBID(String bid) {
    this.BID = bid;
  }

  public void setCalcostmny(UFDouble calcostmny) {
    this.calcostmny = calcostmny;
  }

  public void setCaltaxmny(UFDouble caltaxmny) {
    this.caltaxmny = caltaxmny;
  }

  /**
   * @param hid �ݹ���������ID
   */
  public void setHID(String hid) {
    this.HID = hid;
  }

  /**
   * @param mny ��˰���
   */
  public void setMny(UFDouble mny) {
    this.mny = mny;
  }

  /**
   * @param needCalc ��ȡ���ۺ���㣬����ֱ��Я�����ۼ������Ϣ
   */
  public void setNeedCalc(boolean needCalc) {
    this.needCalc = needCalc;
  }

  public void setNosubtax(UFDouble nosubtax) {
    this.nosubtax = nosubtax;
  }

  /**
   * ���ɵֿ�˰��
   * <p>
   * ʹ�ó�������Ӧ�̼�Ŀ��
   * <ul>
   * <li>
   * </ul>
   * 
   * @param nosubTaxRate
   */
  public void setNosubTaxRate(UFDouble nosubTaxRate) {
    this.nosubTaxRate = nosubTaxRate;
  }

  /**
   * @param origmny ԭ����˰���
   */
  public void setOrigmny(UFDouble origmny) {
    this.origmny = origmny;
  }

  /**
   * @param origPrice ԭ����˰����
   */
  public void setOrigPrice(UFDouble origPrice) {
    this.origPrice = origPrice;
  }

  /**
   * @param origtax ԭ��˰��
   */
  public void setOrigtax(UFDouble origtax) {
    this.origtax = origtax;
  }

  /**
   * @param origTaxPrice ԭ�Һ�˰����
   */
  public void setOrigTaxPrice(UFDouble origTaxPrice) {
    this.origTaxPrice = origTaxPrice;
  }

  /**
   * @param origtotalmny ԭ�Ҽ�˰�ϼ�
   */
  public void setOrigtotalmny(UFDouble origtotalmny) {
    this.origtotalmny = origtotalmny;
  }

  /**
   * @param price ��˰����
   */
  public void setPrice(UFDouble price) {
    this.price = price;
  }

  /**
   * @param tax ˰��
   */
  public void setTax(UFDouble tax) {
    this.tax = tax;
  }

  /**
   * @param taxPrice ��˰����
   */
  public void setTaxPrice(UFDouble taxPrice) {
    this.taxPrice = taxPrice;
  }

  public void setTaxRate(UFDouble taxRate) {
    this.taxRate = taxRate;
  }

  public void setTaxTypeFlag(Integer taxTypeFlag) {
    this.taxTypeFlag = taxTypeFlag;
  }

  /**
   * @param totalmny ��˰�ϼ�
   */
  public void setTotalmny(UFDouble totalmny) {
    this.totalmny = totalmny;
  }
}
