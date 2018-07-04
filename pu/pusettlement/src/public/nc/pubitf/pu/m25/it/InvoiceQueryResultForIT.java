package nc.pubitf.pu.m25.it;

import nc.pubitf.pu.m25.pu.settle.InvoiceQueryResultFor27;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;

/**
 * 提供给进口结算的查询发票结果
 * 
 * @since 6.31
 * @version 2013-10-9 下午02:26:31
 * @author mengjian
 */
public class InvoiceQueryResultForIT extends InvoiceQueryResultFor27 {

  private static final long serialVersionUID = -4826302519008270469L;

  // 进口调整货物发票
  private FeeDiscountSettleVO[] adjInvoices;

  public FeeDiscountSettleVO[] getAdjInvoices() {
    return this.adjInvoices;
  }

  public void setAdjInvoices(FeeDiscountSettleVO[] adjInvoices) {
    this.adjInvoices = adjInvoices;
  }

}
