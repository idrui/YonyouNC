/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-19 上午09:31:59
 */
package nc.pubimpl.pu.m21.mm.m55B4;

import nc.pubimpl.pu.m21.action.mm.m55b4.OrderPushSaveFor55B4Action;
import nc.pubitf.pu.m21.mm.m55B4.IOrderPushSaveFor55B4;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmf.sourcing.entity.SourceReturnVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>计划订单推式保存采购订单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-19 上午09:31:59
 */
public class OrderPushSaveFor55B4Impl implements IOrderPushSaveFor55B4 {

  @Override
  public OrderVO[] pushSave(OrderVO[] orderVOs, SourceReturnVO[] SourceReturnVOs)
      throws BusinessException {
    try {
      return new OrderPushSaveFor55B4Action().pushSave(orderVOs,
          SourceReturnVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
