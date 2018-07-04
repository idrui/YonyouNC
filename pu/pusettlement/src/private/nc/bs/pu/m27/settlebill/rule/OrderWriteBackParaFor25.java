package nc.bs.pu.m27.settlebill.rule;

import nc.pubitf.pu.m21.pu.m25.IOrderWriteBackParaFor25;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����ʱ����Դ�����ķ�Ʊ��д�ɹ������õĲ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-7-20 ����05:13:25
 */
public class OrderWriteBackParaFor25 implements IOrderWriteBackParaFor25 {
  private String bid; // �뷢Ʊ�������ⵥ��Ӧ�Ķ�����ϸID

  private UFDouble diffMny; // ���ν�������*���Һ�˰����

  private UFDouble diffNum; // ���ν�������

  private boolean discard; // ɾ����ʶ

  private String hid; // �뷢Ʊ�������ⵥ��Ӧ�Ķ���ID

  private String invoicebid; // ��Ʊ�ı���ID

  private UFDouble ntaxprice; // ��Ʊ�ı��Һ�˰����

  private UFDouble price; // ��Ʊ�ı�����˰����

  private boolean priceUserConfirm;// ��Ʊ�۸񳬶����۸�ʱ�û�ȷ��

  @Override
  public String getBBID() {
    return null;
  }

  @Override
  public String getBID() {
    return this.bid;
  }

  @Override
  public UFDouble getDiffMny() {
    return this.diffMny;
  }

  @Override
  public UFDouble getDiffNum() {
    return this.diffNum;
  }

  @Override
  public String getHID() {
    return this.hid;
  }

  public String getInvoicebid() {
    return this.invoicebid;
  }

  public UFDouble getNtaxprice() {
    return this.ntaxprice;
  }

  /**
   * ���෽����д
   * 
   * @see nc.pubitf.pu.m21.pu.m25.IOrderWriteBackParaFor25#getPrice()
   */
  @Override
  public UFDouble getPrice() {
    return this.price;
  }

  @Override
  public boolean isClose() {
    return false;
  }

  @Override
  public boolean isDiscard() {
    return this.discard;
  }

  @Override
  public boolean isFee() {
    return false;
  }

  @Override
  public boolean isNumUserConfirm() {
    return false;
  }

  @Override
  public boolean isPriceUserConfirm() {
    return this.priceUserConfirm;
  }

  @Override
  public boolean isReturn() {
    return false;
  }

  public void setBid(String bid) {
    this.bid = bid;
  }

  public void setDiffMny(UFDouble diffMny) {
    this.diffMny = diffMny;
  }

  public void setDiffNum(UFDouble diffNum) {
    this.diffNum = diffNum;
  }

  public void setDiscard(boolean discard) {
    this.discard = discard;
  }

  public void setHid(String hid) {
    this.hid = hid;
  }

  public void setInvoicebid(String invoicebid) {
    this.invoicebid = invoicebid;
  }

  public void setNtaxprice(UFDouble ntaxprice) {
    this.ntaxprice = ntaxprice;
  }

  public void setPrice(UFDouble price) {
    this.price = price;
  }

  public void setPriceUserConfirm(boolean confirm) {
    this.priceUserConfirm = confirm;
  }
}
