package nc.bs.pu.m21.maintain.rule.save;

import nc.bs.businessevent.BdUpdateEvent;
import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.bs.businessevent.IEventType;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              触发保存前事件
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午2:56:04
 * @author luojw
 */

public class SaveEventBeforeRule implements ICompareRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    if (StringUtil.isEmptyWithTrim(vos[0].getHVO().getPk_order())) {
      this.insertBeforeEvent(vos);
    }
    else {
      this.updateBeforeEvent(vos, originVOs);
    }
  }

  private void insertBeforeEvent(OrderVO[] vos) {
    try {
      String sourceid = PUMDValue.Order.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IEventType.TYPE_INSERT_BEFORE, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void updateBeforeEvent(OrderVO[] vos, OrderVO[] originVOs) {
    try {
      String sourceid = PUMDValue.Order.value();
      EventDispatcher.fireEvent(new BdUpdateEvent(sourceid,
          IEventType.TYPE_UPDATE_BEFORE, originVOs, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
