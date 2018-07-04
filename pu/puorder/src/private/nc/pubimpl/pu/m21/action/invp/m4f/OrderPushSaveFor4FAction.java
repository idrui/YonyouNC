package nc.pubimpl.pu.m21.action.invp.m4f;

import nc.pubimpl.pu.m21.action.mm.m55b4.OrderPushSaveFor55B4Action;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.margin.OrderMarginRule;
import nc.vo.scmpub.res.billtype.INVPBillType;

/**
 * �ɹ�����Ϊ���ƻ������ṩ����ʽ���涯��action
 * 
 * @since 6.0
 * @version 2011-12-12 ����04:59:27
 * @author �����
 */

public class OrderPushSaveFor4FAction extends OrderPushSaveFor55B4Action {

  // ��Ϊ���������������ʱ�߼�һ�£������ȼ̳�55B4��Action

  @Override
  protected void processMargin(OrderVO[] orderVOs) {
    new OrderMarginRule(INVPBillType.PoOrder.getCode(), null).process(orderVOs);
  }

}
