/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-9 下午01:51:56
 */
package nc.impl.pu.m21;

import nc.bs.pu.m21.maintain.OrderPushCoopSaleBP;
import nc.itf.pu.m21.IOrderRelateFuncQuery;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>关联功能实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-9 下午01:51:56
 */
public class OrderRelateFuncQueryImpl implements IOrderRelateFuncQuery {

  @Override
  public OrderVO[] pushCoop30(OrderVO[] vos) throws BusinessException {
    try {
      return new OrderPushCoopSaleBP().pushCoop30(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
