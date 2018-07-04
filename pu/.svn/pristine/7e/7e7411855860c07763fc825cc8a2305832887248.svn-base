package nc.pubift.pu.m25.ec;

import nc.vo.pu.ec.NumSummaryQueryECVO;
import nc.vo.pub.BusinessException;

/**
 * 为电子商务提供发票查询
 * 
 * @since 6.0
 * @version 2011-5-7 上午11:24:20
 * @author wuxla
 */

public interface IInvoiceQueryForEC {

  /**
   * 根据订单表体主键查询发票明细信息
   * 
   * @param bid 订单主键
   * @return 发票视图VO数组
   * @throws BusinessException
   */
  InvoiceOrderViewECVO[] queryInvoiceByOrderBid(String[] bids)
      throws BusinessException;

  /**
   * 根据订单主键查询发票明细信息
   * 
   * @param hid 订单主键
   * @return 发票视图VO数组
   * @throws BusinessException
   */
  InvoiceOrderViewECVO[] queryInvoiceByOrderHid(String[] hid)
      throws BusinessException;

  /**
   * 根据产品主键，查看该产品的发票明细
   * 
   * @param pk_materials 产品主键
   * @param pk_supplier 供应商主键
   * @return 发票视图VO数组
   * @throws BusinessException
   */
  InvoiceMatViewECVO[] queryInvoiceItemsByMat(NumSummaryQueryECVO queryECVO)
      throws BusinessException;

  /**
   * 根据发票主键查询发票明细
   * 
   * @param hid 表头主键
   * @return 发票明细VO数组
   * @throws BusinessException
   */
  InvoiceItemECVO[] queryInvoiceItemVOByHid(String[] hid)
      throws BusinessException;

  /**
   * 根据查询条件查询开票信息
   * 
   * @param condVo 查询条件VO
   * @return 汇总数量VO数组
   * @throws BusinessException
   */
  InvoiceNumSummaryECVO[] queryNumSummaryByCond(
      InvoiceSupplyDetailQueryCondVO condVo) throws BusinessException;

  /**
   * 根据订单主实体ID查询开票信息
   * 
   * @param bids 订单表体ID数组
   * @return 汇总数量VO数组
   * @throws BusinessException
   */
  InvoiceNumSummaryECVO[] queryNumSummaryByOrderBids(String[] bids)
      throws BusinessException;

  /**
   * 发布到供应商门户的采购订单对应的发票、并且发票状态为审批通过，查询出的
   * 信息以列表形式展现，并且查询结果按照发票日期顺序排列
   * 
   * @param queryScheme
   * @return 采购发票表头VO数组
   * @throws BusinessException
   */
  InvoicePublishedViewVO[] queryPublishedAndApprovedInvoice(
      InvoicePublishedQueryCondVO condVO) throws BusinessException;
}
