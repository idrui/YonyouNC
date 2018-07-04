package nc.ui.pu.m25.linkquery;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m25.IInvoiceQuery;
import nc.ui.pub.linkoperate.ILinkQueryData;
import nc.ui.pub.pf.IUINodecodeSearcher;
import nc.vo.pu.m25.enumeration.InvoicePuImportEnum;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>NC631需求，区分普通采购和进出口采购
 * <li>联查根据vo走不同的单据模板
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.3
 * @since 6.3
 * @author liangchen1
 * @time 2013-8-21 上午10:42:13
 */

public class InvoiceNodeSearcher implements IUINodecodeSearcher {

  private static String ADJUSTINVOICE = "41114020";

  private static String IMPORTINVOICE = "41114010";

  private static String PUINVOICE = "40041000";

  @Override
  public String findNodecode(ILinkQueryData lqd) {
    String pk = lqd.getBillID();
    int type = InvoicePuImportEnum.PUINVOICE.toIntValue();
    try {
      IInvoiceQuery query = NCLocator.getInstance().lookup(IInvoiceQuery.class);
      type = query.getInvoiceTypeByIds(new String[] {
        pk
      });

    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    if (InvoicePuImportEnum.PUINVOICE.toIntValue() == type) {
      return InvoiceNodeSearcher.PUINVOICE;
    }
    if (InvoicePuImportEnum.IMPORTINVOICE.toIntValue() == type) {
      return InvoiceNodeSearcher.IMPORTINVOICE;
    }
    if (InvoicePuImportEnum.ADJUSTINVOICE.toIntValue() == type) {
      return InvoiceNodeSearcher.ADJUSTINVOICE;
    }
    return null;
  }
}
