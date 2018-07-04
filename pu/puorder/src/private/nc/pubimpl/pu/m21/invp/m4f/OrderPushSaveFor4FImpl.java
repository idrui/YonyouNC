package nc.pubimpl.pu.m21.invp.m4f;

import nc.pubimpl.pu.m21.action.invp.m4f.OrderPushSaveFor4FAction;
import nc.pubitf.pu.m21.invp.m4f.IOrderPushSaveFor4F;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.scmf.sourcing.entity.SourceReturnVO;

/**
 * 采购订单为库存计划计划订单提供的保存服务
 * 
 * @since 6.0
 * @version 2011-12-9 上午11:40:59
 * @author 田锋涛
 */

public class OrderPushSaveFor4FImpl implements IOrderPushSaveFor4F {

  @Override
  public OrderVO[] pushSave(OrderVO[] orderVOs, SourceReturnVO[] SourceReturnVOs)
      throws BusinessException {
    return new OrderPushSaveFor4FAction().pushSave(orderVOs, SourceReturnVOs);
  }

}
