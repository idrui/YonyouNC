package nc.pubitf.pu.m21.ec;

import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

/**
 * 补货申请推式保存采购订单
 * 
 * @since 6.0
 * @version 2012-11-12 下午06:25:13
 * @author lixyp
 */
public interface IOrderPushSaveForEC38 {

  /**
   * 补货申请推式保存采购订单。
   * 
   * @param orderVOs 订单聚合VO
   * @return 保存后的订单聚合VO
   * @throws BusinessException
   */
  OrderVO[] pushSave(OrderVO[] orderVOs) throws BusinessException;
}
