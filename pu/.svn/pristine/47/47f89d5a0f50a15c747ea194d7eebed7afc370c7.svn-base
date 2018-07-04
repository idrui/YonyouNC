package nc.bs.pu.m25.query.pu.it;

import nc.bs.pu.m25.query.pu.InvoiceQueryForSettleBP;
import nc.pubitf.pu.m25.it.InvoiceQueryResultForIT;
import nc.pubitf.pu.m25.pu.settle.InvoiceQueryResultFor27;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m25.enumeration.InvoicePuImportEnum;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class InvoiceQueryForITSettleBP extends InvoiceQueryForSettleBP {

  @Override
  protected InvoiceQueryResultFor27 constructResult(IQueryScheme queryScheme,
      InvoiceSettleVO[] goodsInvoices, FeeDiscountSettleVO[] feeInvoices,
      FeeDiscountSettleVO[] discountInvoices, FeeDiscountSettleVO[] adjInvoices) {
    InvoiceQueryResultForIT result = new InvoiceQueryResultForIT();
    result.setGoodsInvoices(goodsInvoices);
    result.setFeeInvoices(feeInvoices);
    result.setDiscountInvoices(discountInvoices);
    result.setAdjInvoices(adjInvoices);
    return result;
  }

  @Override
  protected Object getPUorITWhere(String field) {
    String where = "";
    // 进口结算查询发票
    where =
        field + " = " + InvoicePuImportEnum.IMPORTINVOICE.toInt() + " or "
            + field + " = " + InvoicePuImportEnum.ADJUSTINVOICE.toInt() + " ";
    return " and (" + where + ")";
  }

  @Override
  protected SqlBuilder getWhrByOrderTrantype(String type, String balias) {
    return new SqlBuilder();
  }

}
