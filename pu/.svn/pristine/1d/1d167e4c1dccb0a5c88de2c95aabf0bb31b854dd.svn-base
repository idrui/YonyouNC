package nc.impl.pu.report.invoice;

import nc.bs.pu.report.invoice.InvoiceDetailQuery;
import nc.itf.pu.report.invoice.IInvoiceReport;
import nc.pub.smart.context.SmartContext;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 采购发票（查询及报表）服务实现
 * 
 * @since 6.0
 * @version 2011-9-14 下午3:18:28
 * @author zhaoyha
 */
public class InvoiceReportImpl implements IInvoiceReport {

  @Override
  public String getInvoiceDetailAPVerifySql(SmartContext context)
      throws BusinessException {
    try {
      return new InvoiceDetailQuery().getApVerifySql(context);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public String getInvoiceDetailSourceSql(SmartContext context)
      throws BusinessException {
    try {
      return new InvoiceDetailQuery().getSourceSql(context);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;

  }

}
