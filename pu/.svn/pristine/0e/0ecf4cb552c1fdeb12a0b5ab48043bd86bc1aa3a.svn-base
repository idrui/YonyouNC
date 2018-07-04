package nc.pubitf.pu.m25.it;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pub.BusinessException;

/**
 * 提供给进口的发票查询
 * 
 * @since 6.31
 * @version 2013-10-9 上午11:26:17
 * @author mengjian
 */
public interface IInvoiceSettleQueryForIT {
  /**
   * 根据前台查询对话框返回的查询方案查询可结算的进口发票
   * 
   * @param queryScheme 前台查询对话框返回的查询方案
   * @return 可结算的进口发票
   * @throws BusinessException
   */
  InvoiceQueryResultForIT queryByWhereSql4IT(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * add by liangchen1
   * 根据发票bid查出表体合同号
   * item只包含bid和ctcode
   */
  InvoiceItemVO[] queryCtCodeByInvoiceBid(String[] bids)
      throws BusinessException;

}
