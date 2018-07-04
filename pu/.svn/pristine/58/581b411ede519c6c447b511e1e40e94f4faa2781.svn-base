package nc.itf.pu.report.invoice;

import nc.pub.smart.context.SmartContext;
import nc.pub.smart.exception.SmartException;
import nc.vo.pub.BusinessException;

/**
 * 采购发票（查询及报表）服务
 * 
 * @since 6.0
 * @version 2011-9-14 下午3:08:32
 * @author zhaoyha
 */
public interface IInvoiceReport {

  /**
   * 发票明细查询－生成查询发票财务核销信息的SQL片断
   * 
   * @param context
   * @return
   * @throws SmartException
   */
  String getInvoiceDetailAPVerifySql(SmartContext context)
      throws BusinessException;

  /**
   * 发票明细查询－生成查询发票来源信息的SQL片断
   * 
   * @param context 包括发票查询的主条件信息
   * @return
   * @throws SmartException
   */
  String getInvoiceDetailSourceSql(SmartContext context)
      throws BusinessException;

}
