package nc.impl.pu.m21.action.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.VOStatus;

/**
 * @description
 *              修改订单的状态为送审
 * @scene
 *        采购订单送审
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午10:56:14
 * @author luojw
 */
public class SendApproveStatusChangeRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      vo.getHVO().setForderstatus((Integer) POEnumBillStatus.APPROVING.value());
      vo.getHVO().setStatus(VOStatus.UPDATED);
    }
  }

}
