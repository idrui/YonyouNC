package nc.pubimpl.pu.m21.action.invp.m4f;

import nc.pubimpl.pu.m21.action.mm.m55b4.OrderPushSaveFor55B4Action;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.margin.OrderMarginRule;
import nc.vo.scmpub.res.billtype.INVPBillType;

/**
 * 采购订单为库存计划订单提供的推式保存动作action
 * 
 * @since 6.0
 * @version 2011-12-12 下午04:59:27
 * @author 田锋涛
 */

public class OrderPushSaveFor4FAction extends OrderPushSaveFor55B4Action {

  // 因为跟从生产制造过来时逻辑一致，这里先继承55B4的Action

  @Override
  protected void processMargin(OrderVO[] orderVOs) {
    new OrderMarginRule(INVPBillType.PoOrder.getCode(), null).process(orderVOs);
  }

}
