package nc.pubitf.pu.m21.ic.m4c;

import java.util.Map;

import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.BusinessException;

/**
 * 销售出库单查询采购订单
 * 
 * @since 6.0
 * @version 2011-4-27 下午01:10:28
 * @author wuxla
 */

public interface IOrderQueryFor4C {
  /**
   * 根据销售订单行id查询协同采购订单
   * 采购订单协同销售订单
   * 销售订单协同采购订单
   * 两种订单只能按一种情况查询
   * 
   * @param sobids销售订单行id
   * @return Map，key：销售订单行id，value：对应的采购订单表体VO
   * @throws BusinessException
   */
  Map<String, OrderItemVO> queryCoopOrderVO(String[] sobids)
      throws BusinessException;
}
