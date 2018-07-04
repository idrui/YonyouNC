package nc.bs.pu.m21.upgrade.v61.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.VOStatus;

/**
 * 升级时单据状态的改变
 * 
 * @since 6.0
 * @version 2012-2-29 下午04:36:03
 * @author tianft
 */
public class OrderVoStatusUpdateRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      vo.getHVO().setStatus(VOStatus.UPDATED);
      for (OrderItemVO item : vo.getBVO()) {
        item.setStatus(VOStatus.UPDATED);
      }
    }
  }

}
