package nc.bs.pu.m21.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;

/**
 * @description
 *              采购订单解冻的状态规则
 * @scene
 *        采购订单解冻
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午11:15:39
 * @author luojw
 */
public class UnfreezeRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      OrderHeaderVO header = vo.getHVO();
      header.setBfrozen(UFBoolean.FALSE);
      header.setPk_freezepsndoc(null);
      header.setVfrozenreason(null);
      header.setTfreezetime(null);
      header.setStatus(VOStatus.UPDATED);
    }
  }

}
