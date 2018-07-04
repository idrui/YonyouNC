package nc.bs.pu.m21.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;

/**
 * @description
 *              采购订单冻结的状态规则
 * @scene
 *        采购订单冻结
 * @param String freezeReason 冻结原因
 * @since 6.3
 * @version 2014-10-21 上午10:17:19
 * @author luojw
 */
public class FreezeRule implements IRule<OrderVO> {
  private String freezeReason;

  public FreezeRule(String freezeReason) {
    this.freezeReason = freezeReason;
  }

  @Override
  public void process(OrderVO[] vos) {
    new FreezeInfoSetter(vos).setFreezeInfo(this.freezeReason);
  }

}
