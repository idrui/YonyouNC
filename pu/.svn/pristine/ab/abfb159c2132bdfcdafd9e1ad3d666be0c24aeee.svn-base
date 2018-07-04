package nc.pubitf.pu.m23.ec;

import nc.vo.pu.ec.NumSummaryQueryECVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;

/**
 * 为电子商务提供到货单查询
 * 
 * @since 6.0
 * @version 2011-5-7 上午11:08:46
 * @author wuxla
 */

public interface IArriveQueryForEC {

  /**
   * 根据订单表体主键查询该订单的到货单明细信息
   * 
   * @param bid 订单表体主键
   * @return 到货单视图VO数组
   * @throws BusinessException
   */
  public ArriveOrderViewECVO[] queryArriveByOrderBid(String[] bids)
      throws BusinessException;

  /**
   * 根据订单主键查询该订单的到货单明细信息
   * 
   * @param hid 订单主键
   * @return 到货单视图VO数组
   * @throws BusinessException
   */
  ArriveOrderViewECVO[] queryArriveByOrderHid(String[] hids)
      throws BusinessException;

  /**
   * 根据产品主键，查看该产品的到货明细
   * 
   * @param pk_org 采购组织
   * @param pk_mateirals 产品主键
   * @param pk_supplier 供应商主键
   * @param startDate 汇总起始日期
   * @param endDate 汇总结束日期
   * @return 到货单视图VO数组
   * @throws BusinessException
   */
  ArriveMatViewECVO[] queryArriveItemsByMat(NumSummaryQueryECVO queryECVO)
      throws BusinessException;

  /**
   * 根据表头主键查询到货单表体
   * 
   * @param hid 表头主键
   * @return 到货单表体VO数组
   * @throws BusinessException
   */
  ArriveItemECVO[] queryArriveItemVOByHid(String hid) throws BusinessException;

  /**
   * 根据供应商输入条件查询退货单信息。
   * 
   * @param condVo 供应商在界面输入的条件
   * @return 退货单视图VO数组
   * @throws BusinessException
   */
  ArriveVO[] queryBackArriveByCond(BackArriveQueryCondVO condVo)
      throws BusinessException;

  /**
   * 根据退货单主健查询退货单信息
   * 
   * @param hid 表头主键
   * @return 退货单视图VO数组
   * @throws BusinessException
   */
  ArriveVO[] queryBackArriveByHid(String[] hids) throws BusinessException;

  /**
   * 根据订单表体主键查询该订单的退货单明细信息
   * 
   * @param bid 订单表体主键
   * @return 到货单视图VO数组
   * @throws BusinessException
   */
  BackArriveOrderViewECVO[] queryBackArriveByOrderBid(String[] bids)
      throws BusinessException;

  /**
   * 根据订单主键查询该订单的退货单明细信息
   * 
   * @param hid 订单主键
   * @return 到货单视图VO数组
   * @throws BusinessException
   */
  BackArriveOrderViewECVO[] queryBackArriveByOrderHid(String[] hids)
      throws BusinessException;

  /**
   * 根据产品主键，查看该产品的退货明细
   * 
   * @param pk_org 采购组织
   * @param pk_mateirals 产品主键
   * @param pk_supplier 供应商主键
   * @param startDate 汇总起始日期
   * @param endDate 汇总结束日期
   * @return 到货单视图VO数组
   * @throws BusinessException
   */
  BackArriveMatViewECVO[] queryBackArriveItemsByMat(
      NumSummaryQueryECVO queryECVO) throws BusinessException;

  /**
   * 根据查询条件查询到货信息
   * 
   * @param condVo 查询条件VO
   * @return 汇总数量VO数组
   * @throws BusinessException
   */
  ArriveNumSummaryECVO[] queryNumSummaryByCond(SupplyDetailQueryCondVO condVo)
      throws BusinessException;

  /**
   * 根据订单主实体ID查询到货信息
   * 
   * @param bids 订单表体ID数组
   * @return 汇总数量VO数组
   * @throws BusinessException
   */
  ArriveNumSummaryECVO[] queryNumSummaryByOrderBids(String[] bids)
      throws BusinessException;

  /**
   * 查询发布到供应商门户的采购订单对应的到货单、并且到货单状态为审批通过，查询出的信息以列表形式展现，并且查询结果按照到货日期顺序排列
   * 
   * @param queryScheme 查询方案
   * @return 到货单表头VO数组
   * @throws BusinessException
   */
  ArrivePublishedViewVO[] queryPublishedAndApprovedArrive(
      ArrivePublishedQueryCondVO condVO) throws BusinessException;
}
