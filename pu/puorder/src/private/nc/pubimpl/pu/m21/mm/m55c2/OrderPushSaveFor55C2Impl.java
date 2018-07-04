package nc.pubimpl.pu.m21.mm.m55c2;

import nc.pubimpl.pu.m21.action.mm.m55c2.OrderPushSaveFor55C2Action;
import nc.pubitf.pu.m21.mm.m55c2.IOrderPushSaveFor55C2;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmf.sourcing.entity.SourceReturnVO;

/**
 * 为离散制造提供的接口
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>生产订单产能分流推式创建采购订单
 * </ul>
 * <p>
 * <p>
 * 
 * @since 6.0
 * @version 2012-10-26 上午11:05:22
 * @author liuyand
 */
public class OrderPushSaveFor55C2Impl implements IOrderPushSaveFor55C2 {

  @Override
  public OrderVO[] pushSave(OrderVO[] orderVOs, SourceReturnVO[] SourceReturnVOs)
      throws BusinessException {
    try {
      return new OrderPushSaveFor55C2Action().pushSave(orderVOs,
          SourceReturnVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
