package nc.pubitf.pu.m27.ec;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * 电子商务查询
 * 
 * @since 6.0
 * @version 2011-5-8 下午12:20:50
 * @author wuxla
 */

public interface ISettleBillQueryForEC {

  /**
   * 根据订单主键查询累计结算数量和金额信息
   * 
   * @param bids 订单主表主键
   * @return 结算视图VO数组
   * @throws BusinessException
   */
  SettleBillViewECVO[] querySettleBillByOrderBid(String[] bids)
      throws BusinessException;

  /**
   * 根据订单主键查询累计结算数量和金额信息
   * 
   * @param hids 订单主表主键
   * @return 结算视图VO数组
   * @throws BusinessException
   */
  SettleBillViewECVO[] querySettleBillByOrderHid(String[] hids)
      throws BusinessException;

  /**
   * 根据订单表体主键查询累计结算信息
   * 
   * @return
   * @throws BusinessException
   */
  Map<String, UFDouble[]> querySettleInfoByOrderBid(String[] bids)
      throws BusinessException;
}
