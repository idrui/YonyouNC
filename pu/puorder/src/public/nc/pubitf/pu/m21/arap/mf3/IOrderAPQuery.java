package nc.pubitf.pu.m21.arap.mf3;

import java.util.List;

import nc.vo.pub.BusinessException;

/**
 * @since 6.0
 * @version 2011-7-4 下午03:33:14
 * @author wuxla
 */

public interface IOrderAPQuery {

  /**
   * 根据订单表体id查询订单付款计划id
   * 
   * @param bids 订单表体id
   * @return 订单付款计划id，如果为空，返回null
   * @throws BusinessException
   */
  List<String> getPayPlanIDByOrderBID(List<String> bids)
      throws BusinessException;
}
