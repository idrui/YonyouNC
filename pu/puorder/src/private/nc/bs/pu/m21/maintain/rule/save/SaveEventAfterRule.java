package nc.bs.pu.m21.maintain.rule.save;

import nc.bs.businessevent.BdUpdateEvent;
import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.bs.businessevent.IEventType;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单触发保存后事件
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午3:16:35
 * @author luojw
 */

public class SaveEventAfterRule implements ICompareRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    if (ArrayUtils.isEmpty(originVOs)) {
      this.insertAfterEvent(vos);
    }
    else {
      this.updateAfterEvent(vos, originVOs);
    }
  }

  private void insertAfterEvent(OrderVO[] vos) {
    try {
      String sourceid = PUMDValue.Order.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IEventType.TYPE_INSERT_AFTER, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void updateAfterEvent(OrderVO[] vos, OrderVO[] originVOs) {
    try {
      String sourceid = PUMDValue.Order.value();
      EventDispatcher.fireEvent(new BdUpdateEvent(sourceid,
          IEventType.TYPE_UPDATE_AFTER, originVOs, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
