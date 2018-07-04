package nc.impl.pu.m21.action.rule.approve;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.event.IPOEventType;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              �ɹ�����������¼�����
 * @scene
 *        �ɹ���������
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����10:57:11
 * @author luojw
 */

public class SendApproveAfterRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    try {
      String sourceid = PUMDValue.Order.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IPOEventType.TYPE_SENDAPPROVE_AFTER, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
