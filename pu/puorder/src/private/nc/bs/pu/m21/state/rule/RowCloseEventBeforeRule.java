package nc.bs.pu.m21.state.rule;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.event.IPOEventType;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单行关闭前事件触发器
 * @scene
 *        采购订单行关闭
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午10:50:21
 * @author luojw
 */
public class RowCloseEventBeforeRule implements IRule<OrderCloseVO> {

  @Override
  public void process(OrderCloseVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    try {
      String sourceid = PUMDValue.Order.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IPOEventType.TYPE_ROWCLOSE_BEFORE, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
