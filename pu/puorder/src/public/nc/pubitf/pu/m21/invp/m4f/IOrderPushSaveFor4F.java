package nc.pubitf.pu.m21.invp.m4f;

import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.scmf.sourcing.entity.SourceReturnVO;

/**
 * 采购订单为库存计划计划订单提供的保存服务
 * 
 * @since 6.0
 * @version 2011-12-9 上午11:38:42
 * @author 田锋涛
 */

public interface IOrderPushSaveFor4F {

  /**
   * <p>
   * 使用场景：
   * <ul>
   * <li>计划订单（库存计划）推式保存采购订单
   * </ul>
   * 
   * @param orderVOs 订单vo数组
   * @param SourceReturnVOs 寻源vo数组，与订单vo数组一一对应，用于下游后续补充单价等
   * @throws BusinessException
   */
  OrderVO[] pushSave(OrderVO[] orderVOs, SourceReturnVO[] SourceReturnVOs)
      throws BusinessException;

}
