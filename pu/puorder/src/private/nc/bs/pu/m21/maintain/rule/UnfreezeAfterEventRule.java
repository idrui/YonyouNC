package nc.bs.pu.m21.maintain.rule;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.bs.businessevent.IEventType;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              采购订单解冻后规则
 * @scene
 *        采购订单解冻
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午11:16:48
 * @author luojw
 */
public class UnfreezeAfterEventRule implements IRule<OrderVO> {
  @Override
  public void process(OrderVO[] vos) {
    try {
      String sourceid = PUMDValue.Order.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IEventType.TYPE_UNORDERFREEZE_AFTER, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
