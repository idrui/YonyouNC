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
 * <li>按发票类型对查询出的VO进行分类，主要依据枚举类InvoiceRowType(0:货物行，1：折扣行，2:劳务行，3：零数量行)
 * <li>1、结算时第一个界面选择发票向第二个节目跳转时调用
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-9 上午11:19:50
 */
public class ClassifyInvoiceByType {

  // 折扣发票
  private List<FeeDiscountSettleVO> discountInvoices;

  // 劳务发票
  private List<FeeDiscountSettleVO> feeInvoices;

  // 货物发票
  private List<InvoiceSettleVO> goodsInvoices;

  // 初始发票（有待进行分类的数组）
  private InvoiceSettleVO[] initInvoices;

  public ClassifyInvoiceByType(InvoiceSettleVO[] initInvoices) {
    this.initInvoices = initInvoices;
  }

  public void classifyInvoice() {
    this.goodsInvoices = new ArrayList<InvoiceSettleVO>();
    this.feeInvoices = new ArrayList<FeeDiscountSettleVO>();
    this.discountInvoices = new ArrayList<FeeDiscountSettleVO>();

    if (ArrayUtils.isEmpty(this.initInvoices)) {
      return;
    }

    for (InvoiceSettleVO invoice : this.initInvoices) {
      // 发票行类型，对应枚举类InvoiceRowType(0:货物行，1：折扣行，2:劳务行，3：零数量行)
      int type = invoice.getFrowtype().intValue();
      if (type == 0) {
        this.goodsInvoices.add(invoice);
      }
      else if (type == 1) {
        this.discountInvoices.add((FeeDiscountSettleVO) invoice);
      }
      else if (type == 2) {
        this.feeInvoices.add((FeeDiscountSettleVO) invoice);
      }
    }
  }

  public FeeDiscountSettleVO[] getDiscountInvoices() {
    return this.discountInvoices.toArray(new FeeDiscountSettleVO[0]);
  }

  public FeeDiscountSettleVO[] getFeeInvoices() {
    return this.feeInvoices.toArray(new FeeDiscountSettleVO[0]);
  }

  public InvoiceSettleVO[] getGoodsInvoices() {
    return this.goodsInvoices.toArray(new InvoiceSettleVO[0]);
  }
}
