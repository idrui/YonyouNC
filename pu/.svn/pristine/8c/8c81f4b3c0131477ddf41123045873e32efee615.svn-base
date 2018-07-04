package nc.pubitf.pu.m25.pu.settle;

import java.util.Map;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为采购结算提供的查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-19 下午04:58:52
 */
public interface IInvoiceSettleQuery {
  /** 要查询的物料范围 -处理结算时是否按已查入库单物料过滤 **/
  String INCLUDE_MAR__KEY = "include_pk_material";

  /** 为结算进行查询的类型 **/
  String QRY_TYPE_KEY = "po_invoice_query_for_settle_type";

  /** 为采购入结算进行查询的类型 **/
  String QRY_TYPE_PO = "qry_type_po";

  /** 为消耗汇总结算进行查询的类型 **/
  String QRY_TYPE_VMI = "qry_type_vmi";

  /**
   * 方法功能描述：判断给定的发票是否存在已经传过应付。
   * <p>
   * <b>参数说明</b>
   * 
   * @param invoiceHIDs 发票的头ID数组
   * @return true 存在传过应付的发票 false 全部未付过应付
   * @throws BusinessException <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-5 下午04:32:02
   */
  public boolean isExistsSentAP(String[] invoiceHIDs) throws BusinessException;

  /**
   * 根据前台查询对话框返回的查询方案查询可结算的采购发票
   * 
   * @param queryScheme 前台查询对话框返回的查询方案
   * @return 可结算的采购发票
   * @throws BusinessException
   */
  public InvoiceQueryResultFor27 queryByWhereSql(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * 查询发票头ID，查询结算发票以及关联的费用折扣发票（所属一张发票或关联的费用发票）<br>
   * 些服务最初用于发票发起的自动结算
   * 
   * @param invoiceHId
   * @param queryType 是采购查询，还是消耗汇总查询
   * @return
   * @throws BusinessException
   */
  public InvoiceQueryResultFor27 queryGoodsAndRelaFeeByHID(String invoiceHId,
      String queryType) throws BusinessException;

  /**
   * 方法功能描述：根据发票行ID查询本币无税单价。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bids 发票行ID数组
   * @return Map<行ID，无税单价>
   * @throws BusinessException <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-12 下午02:29:26
   */
  public Map<String, UFDouble> queryPrice(String[] bids)
      throws BusinessException;

  /**
   * 方法功能描述：根据发票行ID查询本币含税单价。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bids 发票行ID数组
   * @return Map<行ID，含税单价>
   * @throws BusinessException <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-21 下午04:44:45
   */
  public Map<String, UFDouble> queryTaxPrice(String[] bids)
      throws BusinessException;

}
