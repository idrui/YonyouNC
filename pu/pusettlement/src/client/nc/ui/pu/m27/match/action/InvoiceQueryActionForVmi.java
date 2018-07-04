package nc.ui.pu.m27.match.action;

import nc.pubitf.pu.m25.pu.settle.IInvoiceSettleQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>消耗汇总结算中的采购发票查询Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-10-23 下午03:10:35
 */
public class InvoiceQueryActionForVmi extends InvoiceQueryAction {
  private static final long serialVersionUID = -5667491871111125098L;

  @Override
  protected void setQueryType(IQueryScheme queryScheme) {
    queryScheme.put(IInvoiceSettleQuery.QRY_TYPE_KEY,
        IInvoiceSettleQuery.QRY_TYPE_VMI);
  }
}
