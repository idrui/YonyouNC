package nc.bs.pu.m21.maintain.rule.delete;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.bs.businessevent.IEventType;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              采购订单删除后事件处理
 * @scene
 *        采购订单删除
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午8:46:29
 * @author luojw
 */

public class DeleteAfterEventRule implements ICompareRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    try {
      String sourceid = PUMDValue.Order.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IEventType.TYPE_DELETE_AFTER, originVOs));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
