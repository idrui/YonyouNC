package nc.itf.pu.m21;

import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.MapList;

public interface IOrderPaymentQuery {
  /**
   * 通过ID主键查询付款协议子表
   * 
   * @param ids 采购订单表体付款协议主键
   * @return
   * @throws BusinessException
   */
  public OrderPaymentVO[] queryOrderPaymentByIds(String[] ids)
      throws BusinessException;

  /**
   * 通过订单的ID来查询表体付款协议
   * 
   * @param ids 采购订单表头主键
   * @return
   * @throws BusinessException
   */
  public MapList<String, OrderPaymentVO> queryOrderPaymentByOrderIds(
      String[] ids) throws BusinessException;
}
