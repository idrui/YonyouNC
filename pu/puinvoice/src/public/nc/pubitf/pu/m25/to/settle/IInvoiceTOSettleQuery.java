package nc.pubitf.pu.m25.to.settle;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pub.BusinessException;

/**
 * 为内部交易费用结算，查询采购费用发票的服务
 * 
 * @since 6.0
 * @version 2011-1-24 下午08:20:06
 * @author zhyhang
 */
public interface IInvoiceTOSettleQuery {
  /**
   * 用于映射放到queryscheme中的结算财务组织
   */
  final static String QS_SETTLE_FIORG_KEY = "qs_settle_fiorg_key";

  /**
   * 根据指定的查询方案，查询可进行结算的采购费用发票
   * 
   * @param qs 由查询对话框得到的查询方案
   * @return 费用发票结算VO
   * @throws BusinessException
   */
  FeeDiscountSettleVO[] query(IQueryScheme qs) throws BusinessException;

}
