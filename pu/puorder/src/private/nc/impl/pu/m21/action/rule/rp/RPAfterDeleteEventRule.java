package nc.impl.pu.m21.action.rule.rp;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.bs.businessevent.IEventType;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 到货计划删除后事件
 * 
 * @author lixyp
 * 
 */
public class RPAfterDeleteEventRule implements IRule<OrderReceivePlanVO> {

  @Override
  public void process(OrderReceivePlanVO[] vos) {
    try {
      String sourceid = PUMDValue.ReceivePlan.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IEventType.TYPE_DELETE_AFTER, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
