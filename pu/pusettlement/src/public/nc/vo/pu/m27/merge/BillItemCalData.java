package nc.vo.pu.m27.merge;

import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ŀ1��������㵥��������Ҫ������ֶΣ����ۡ����ɱ�Ҫ�ء��ۿ�
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author wangyf
 * @time 2010-3-25 ����02:54:14
 */

public class BillItemCalData {
  UFDouble[] naFactor = new UFDouble[CostfactorVO.MAX_NUM];

  UFDouble nDiscount = null;

  UFDouble nGoodsMoney = null;

  UFDouble nGoodsPrice = null;

  UFDouble nInvoiceNum = null;

  UFDouble nMoney = null;

  UFDouble nPrice = null;

  UFDouble nReasonWasteMny = null;

  UFDouble nReasonWasteNum = null;

  UFDouble nReasonWastePrice = null;

  public UFDouble[] getNaFactor() {
    return this.naFactor;
  }

  public UFDouble getNdiscount() {
    return this.nDiscount;
  }

  public UFDouble getNgoodsmoney() {
    return this.nGoodsMoney;
  }

  public UFDouble getNgoodsprice() {
    return this.nGoodsPrice;
  }

  public UFDouble getNinvoicenum() {
    return this.nInvoiceNum;
  }

  public UFDouble getNmoney() {
    return this.nMoney;
  }

  public UFDouble getNprice() {
    return this.nPrice;
  }

  public UFDouble getNReasonWasteMny() {
    return this.nReasonWasteMny;
  }

  public UFDouble getNReasonWasteNum() {
    return this.nReasonWasteNum;
  }

  public UFDouble getNReasonWastePrice() {
    return this.nReasonWastePrice;
  }

  public void setNaFactor(UFDouble[] naFactor) {
    this.naFactor = naFactor;

  }

  public void setNdiscount(UFDouble discount) {
    this.nDiscount = discount;
  }

  public void setNgoodsmoney(UFDouble invoiceMoney) {
    this.nGoodsMoney = invoiceMoney;
  }

  public void setNgoodsprice(UFDouble goodsPrice) {
    this.nGoodsPrice = goodsPrice;
  }

  public void setNinvoicenum(UFDouble num) {
    this.nInvoiceNum = num;
  }

  public void setNmoney(UFDouble money) {
    this.nMoney = money;
  }

  public void setNprice(UFDouble price) {
    this.nPrice = price;
  }

  public void setNReasonWasteMny(UFDouble reasonWasteMny) {
    this.nReasonWasteMny = reasonWasteMny;
  }

  public void setNReasonWasteNum(UFDouble reasonWasteNum) {
    this.nReasonWasteNum = reasonWasteNum;
  }

  public void setNReasonWastePrice(UFDouble reasonWastePrice) {
    this.nReasonWastePrice = reasonWastePrice;
  }
}
