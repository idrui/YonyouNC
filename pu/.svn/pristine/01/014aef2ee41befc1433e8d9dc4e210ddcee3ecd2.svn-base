package nc.pubitf.pu.m21.ic.report;

import nc.vo.pub.BusinessException;

/**
 * 采购订单给库存报表提供的服务
 * 
 * @since 6.0
 * @version 2011-12-6 上午11:23:45
 * @author zhaoyha
 */
public interface IOrderQueryForICReport {

  /**
   * 借入备查簿查询采购订单
   * 
   * @param borrowHIDs 借入单表头ID数组（为了效率考虑，不要求与BID数组一一对应）
   * @param borrowBIDs 借入单表体ID数组
   * @return 可能为null
   * @throws BusinessException
   */
  OrderViewForICBorrowMemo[] queryForBorrowMemo(String[] borrowHIDs,
      String[] borrowBIDs) throws BusinessException;

}
