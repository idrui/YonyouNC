package nc.pubitf.pu.m25.pu.settle;

import java.io.Serializable;

import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票提供给结算的查询返回结果类，对货物、折扣、劳务进行了分类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-9 下午06:23:18
 */
public class InvoiceQueryResultFor27 implements Serializable {
  private static final long serialVersionUID = -8848238179649434552L;

  // 折扣发票
  private FeeDiscountSettleVO[] discountInvoices;

  // 劳务发票
  private FeeDiscountSettleVO[] feeInvoices;

  // 货物发票
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
