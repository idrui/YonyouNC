package nc.vo.pu.m27.util;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>把各种发票类型(0:货物行，1：折扣行，2:劳务行，3：零数量行)合并成InvoiceSettleVO
 * <li>1、结算时把查询出来的各种发票向界面赋值时，需要进行转化
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-9 上午11:19:50
 */
public class CombineToInvoiceSettleVO {

  // 折扣发票
  private FeeDiscountSettleVO[] discountInvoices;

  // 劳务发票
  private FeeDiscountSettleVO[] feeInvoices;

  // 货物发票
  private InvoiceSettleVO[] goodsInvoices;

  private List<InvoiceSettleVO> retsultInvoices;

  public InvoiceSettleVO[] combineInvoice() {
    this.retsultInvoices = new ArrayList<InvoiceSettleVO>();
    if (!ArrayUtils.isEmpty(this.goodsInvoices)) {
      for (InvoiceSettleVO invoice : this.goodsInvoices) {
        this.retsultInvoices.add(invoice);
      }
    }
    if (!ArrayUtils.isEmpty(this.feeInvoices)) {
      for (InvoiceSettleVO invoice : this.feeInvoices) {
        this.retsultInvoices.add(invoice);
      }
    }
    if (!ArrayUtils.isEmpty(this.discountInvoices)) {
      for (InvoiceSettleVO invoice : this.discountInvoices) {
        this.retsultInvoices.add(invoice);
      }
    }

    if (this.retsultInvoices.size() == 0) {
      return null;
    }
    return this.retsultInvoices.toArray(new InvoiceSettleVO[0]);
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
