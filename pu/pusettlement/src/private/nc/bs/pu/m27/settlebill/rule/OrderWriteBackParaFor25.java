package nc.bs.pu.m27.settlebill.rule;

import nc.pubitf.pu.m21.pu.m25.IOrderWriteBackParaFor25;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>结算时无来源订单的发票回写采购订单用的参数类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-7-20 下午05:13:25
 */
public class OrderWriteBackParaFor25 implements IOrderWriteBackParaFor25 {
  private String bid; // 与发票结算的入库单对应的订单明细ID

  private UFDouble diffMny; // 本次结算数量*本币含税单价

  private UFDouble diffNum; // 本次结算数量

  private boolean discard; // 删除标识

  private String hid; // 与发票结算的入库单对应的订单ID

  private String invoicebid; // 发票的表体ID

  private UFDouble ntaxprice; // 发票的本币含税单价

  private UFDouble price; // 发票的本币无税单价

  private boolean priceUserConfirm;// 发票价格超订单价格时用户确认

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
   * 父类方法重写
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
