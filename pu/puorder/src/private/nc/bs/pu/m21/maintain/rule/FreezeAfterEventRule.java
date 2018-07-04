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
 *              �ɹ�����������¼�����
 * @scene
 *        �ɹ���������
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����10:19:40
 * @author luojw
 */
public class FreezeAfterEventRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    try {
      String sourceid = PUMDValue.Order.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IEventType.TYPE_ORDERFREEZE_AFTER, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
