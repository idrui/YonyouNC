package nc.pubitf.pu.m21.ia.mi2;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * @since 6.0
 * @version 2011-4-15 下午02:50:32
 * @author wuxla
 */

public interface IOrderQueryForI2 {
  /**
   * 根据订单行id查询来源直运销售订单体id，如果来源销售订单不是直运，则返回map中没有
   * 
   * @param bids
   * @return key：采购订单表体id value：直运销售订单表体id
   * @throws BusinessException
   */
  Map<String, String> queryDirectSOBidForIA(String[] bids)
      throws BusinessException;

  /**
   * 根据订单行id查询来源销售订单体id和交易类型
   * 
   * @param bids 订单表体id数组
   * @return MapList-key：采购订单表体id；value：ArrayList（0-销售订单体id，1-来源交易类型）
   * @throws BusinessException
   */
  MapList<String, String> querySOBidAndCtransIdForIA(String[] bids)
      throws BusinessException;
}
