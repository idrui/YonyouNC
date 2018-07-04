package nc.pubitf.pu.m21.ec;

import java.util.List;
import java.util.Map;

import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.ec.NumSummaryQueryECVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

/**
 * 电子商务查询服务
 * 
 * @since 6.0
 * @version 2011-3-15 下午05:57:55
 * @author wuxla
 */

public interface IOrderQueryForEC {
  /**
   * 根据查询条件联查采购订单聚合VO
   * 
   * @param condVo 查询条件
   * @return 采购订单聚合VO
   */
  OrderVO[] linkQueryOrderVOByConds(LinkQueryCondVO condVo)
      throws BusinessException;

  /**
   * 查询审批通过的订单
   * 
   * @param queryScheme 查询方案
   * @return 订单表头VO数组
   * @throws BusinessException
   */
  OrderApprovedViewVO[] queryApprovedOrder(OrderApprovedQueryCondVO condVO)
      throws BusinessException;

  /**
   * 根据条件查询产品的最新价
   * 
   * @param purchaseOrg 采购组织
   * @param moids 物料OID数组
   * @param startDate 起始询价单据日期<font color="red">（可为空）</font>
   * @param endDate 结束询价单据日期<font color="red">（可为空）</font>
   * @return OrderPriceData[]返回查到的数据
   * @throws BusinessException
   */
  OrderPriceData[] queryLatestPrice(String purchaseOrg, String[] moids,
      UFDate startDate, UFDate endDate, String pk_currery)
      throws BusinessException;

  /**
   * 查询订单状态为审核通过的、供应链维护完成且发布的到货计划。查询出的信息
   * 以列表形式展现，并且查询结果按照供应链提交到货计划日期顺序排列
   * 
   * @param queryScheme 查询方案
   * @return 到货计划视图VO
   * @throws BusinessException
   */
  OrderReceivePlanPubedViewVO[] queryMaintainedAndPublishedRecPlans(
      OrderReceivePlanPubedQueryCondVO condVO, String srcType)
      throws BusinessException;

  /**
   * 供应商登陆EC门户 -> 点击排程计划节点 -> 输入查询条件 -> 查询待确认排程计划。
   * 根据输入的条件查询待确认的采购订单。
   * 
   * @param cond
   * @return
   * @throws BusinessException
   */
  OrderVO[] queryOnConfirmOrder(OnConfirmOrderQueryCondVO condVO)
      throws BusinessException;

  /**
   * 根据条件查询订单执行情况
   * 
   * @param queryScheme 查询方案
   * @return 订单表头VO数组
   * @throws BusinessException
   */
  OrderExecDetailHeadVO[] queryOrderExecDetail(OrderExecDetailQueryCondVO condVO)
      throws BusinessException;

  /**
   * 根据订单主表主键查询订单执行情况明细信息
   * 
   * @param hid 订单表头id
   * @return 订单视图VO数组
   * @throws BusinessException
   */
  OrderExecDetailViewVO[] queryOrderExecDetailByHid(String[] hids)
      throws BusinessException;

  /**
   * 根据订单主表主键查询订单子表信息
   * 
   * @param hid 采购订单表头主键
   * @return 订单表体
   * @throws BusinessException
   */
  OrderItemApprovedVO[] queryOrderItemApprovedVOByHid(String[] hids)
      throws BusinessException;

  /**
   * 根据订单主表主键查询订单子表信息，已经发布
   * 
   * @param hids 采购订单表头主键
   * @return
   * @throws BusinessException
   */
  OrderItemPubedVO[] queryOrderItemPubedVOByHid(String[] hids)
      throws BusinessException;

  /**
   * 根据产品主键，查看该产品的订单明细
   * 
   * @param pk_org 采购组织
   * @param pk_mateirals 产品主键
   * @param pk_supplier 供应商主键
   * @param startDate 汇总起始日期
   * @param endDate 汇总结束日期
   * @return 订单明细VO数组
   * @throws BusinessException
   */
  OrderMatViewECVO[] queryOrderItemsByMat(NumSummaryQueryECVO queryECVO)
      throws BusinessException;

  /**
   * 根据表体主键查询表体VO数组
   * 
   * @param bids 表体主键
   * @return 表体VO数组
   * @throws BusinessException
   */
  OrderItemVO[] queryOrderItemsForEC(String[] bids) throws BusinessException;

  /**
   * 根据表体主键查询采购订单VO
   * 
   * @param bids 表体主键
   * @return 采购订单VO
   * @throws BusinessException
   */
  OrderVO[] queryOrderVOByBids(String[] bids) throws BusinessException;

  /**
   * 查询已经发布的订单信息
   * 
   * @param queryScheme 查询条件
   * @return 发布订单VO
   * @throws BusinessException
   */
  OrderPublishedViewVO[] queryPublishOrder(OrderPublishedQueryCondVO condVO)
      throws BusinessException;

  /**
   * 查询采购订单主键对应到货计划号
   * 
   * @param hid 采购订单主键
   * @return key:采购订单主键, value:到货计划号ArrayList
   * @throws BusinessException
   */
  Map<String, List<String>> queryReceivePlanCode(String[] hids)
      throws BusinessException;

  /**
   * 门户供应商维护到货计划要根据采购订单来维护到货计划，供应商能参照到的订
   * 单范围需要满足的条件是：订单业务类型有到货计划，订单状态为审核通过的，
   * 且订单行收货库存组织不为空的本供应商的采购订单。查询出的信息以列表形式展
   * 现，并且查询结果按照提交日期顺序排列
   * 
   * @param queryScheme
   * @return
   * @throws BusinessException
   */
  OrderReceivePlanViewECVO[] queryReceivePlansForSupplierMaintain(
      OrderReceivePlanQueryCondVO condVO) throws BusinessException;

  /**
   * 根据订单表体主键查询累计到货计划明细
   * 
   * @param bid 订单表体主键
   * @return 到货计划VO数组
   * @throws BusinessException
   */
  OrderReceivePlanECVO[] queryReceivePlanVOsByBId(String[] bids)
      throws BusinessException;

  /**
   * 根据订单主键查询累计到货计划明细
   * 
   * @param hid 订单主键
   * @return 到货计划VO数组
   * @throws BusinessException
   */
  OrderReceivePlanECVO[] queryReceivePlanVOsByHId(String[] hids)
      throws BusinessException;

  /**
   * 查询是否曾经发布的订单信息
   * 
   * @param queryScheme 查询条件
   * @return 发布订单VO
   * @throws BusinessException
   */
  OrderReleasedOverViewVO[] queryReleasedOverOrder(
      OrderReleasedOverQueryCondVO condVO) throws BusinessException;

  /**
   * 根据到货计划主键查询到货计划信息
   * 
   * @param bb1ids 到货计划主键
   * @return 到货计划VO
   * @throws BusinessException
   */
  OrderReceivePlanVO[] queryRPVOsByIds(String[] bb1ids)
      throws BusinessException;

  /**
   * 根据条件查询产品的供货情况
   * 
   * @param queryScheme 查询方案
   * @return 供货情况VO数组
   * @throws BusinessException
   */
  SupplyDetailVO[] querySupplyDetail(SupplyDetailQueryVO condVO)
      throws BusinessException;

  /**
   * 电子商务供应商门户确认，保存订单。
   * 必须保证数据的完整性
   * 
   * @param orderVos
   * @param ctxs
   * @return
   * @throws BusinessException
   */
  OrderVO[] save(OrderVO[] orderVos) throws BusinessException;

  /**
   * @param batchVO 新增或者修改的到货计划
   * @param orderVO 采购订单，主要是主键和ts
   * @return [0]为BatchOperateVO，[1]为OrderVO
   * @throws BusinessException
   */
  Object[] saveReveivePlanVO(BatchOperateVO batchVO, OrderVO orderVO)
      throws BusinessException;
}
