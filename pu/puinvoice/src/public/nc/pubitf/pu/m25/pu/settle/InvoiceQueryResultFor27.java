package nc.pubitf.pu.m25.pu.settle;

import java.io.Serializable;

import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ʊ�ṩ������Ĳ�ѯ���ؽ���࣬�Ի���ۿۡ���������˷���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-9 ����06:23:18
 */
public class InvoiceQueryResultFor27 implements Serializable {
  private static final long serialVersionUID = -8848238179649434552L;

  // �ۿ۷�Ʊ
  private FeeDiscountSettleVO[] discountInvoices;

  // ����Ʊ
  private FeeDiscountSettleVO[] feeInvoices;

  // ���﷢Ʊ
  private InvoiceSettleVO[] goodsInvoices;

  public FeeDiscountSettleVO[] getDiscountInvoices() {
    return this.discountInvoices;
  }

  public FeeDiscountSettleVO[] getFeeInvoices() {
    return this.feeInvoices;
  }

  public InvoiceSettleVO[] getGoodsInvoices() {
    return this.goodsInvoices;
  }

  public void setDiscountInvoices(FeeDiscountSettleVO[] discountInvoices) {
    this.discountInvoices = discountInvoices;
  }

  public void setFeeInvoices(FeeDiscountSettleVO[] feeInvoices) {
    this.feeInvoices = feeInvoices;
  }

  public void setGoodsInvoices(InvoiceSettleVO[] goodsInvoices) {
    this.goodsInvoices = goodsInvoices;
  }
}
