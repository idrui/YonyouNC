package nc.vo.pu.m21.query.price;

import java.io.Serializable;

import nc.vo.pub.lang.UFDate;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ѯ����ʹ�õĽӿ����Ͳ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-21 ����03:25:00
 */
public class OrderPriceQueryParam implements Serializable {
  private static final long serialVersionUID = 7299924672471328628L;

  private UFDate billDate;// ��õ�������

  private String currency;// ��ñ���ID

  private OrderPriceQueryDetail[] detail;

  private UFDate endDate;// ѯ�۽�������

  private String purchaseOrg;// ��òɹ���֯��OID

  private UFDate startDate; // ѯ�ۿ�ʼ����

  private String supplier;// ��ù�Ӧ��ID

  public UFDate getBillDate() {
    return this.billDate;
  }

  public String getCurrency() {
    return this.currency;
  }

  public OrderPriceQueryDetail[] getDetail() {
    return this.detail;
  }

  public UFDate getEndDate() {
    return this.endDate;
  }

  public String getPurchaseOrg() {
    return this.purchaseOrg;
  }

  public UFDate getStartDate() {
    return this.startDate;
  }

  public String getSupplier() {
    return this.supplier;
  }

  public void setBillDate(UFDate billDate) {
    this.billDate = billDate;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public void setDetail(OrderPriceQueryDetail[] detail) {
    this.detail = detail;
  }

  public void setEndDate(UFDate endDate) {
    this.endDate = endDate;
  }

  public void setPurchaseOrg(String purchaseOrg) {
    this.purchaseOrg = purchaseOrg;
  }

  public void setStartDate(UFDate startDate) {
    this.startDate = startDate;
  }

  public void setSupplier(String supplier) {
    this.supplier = supplier;
  }

}
