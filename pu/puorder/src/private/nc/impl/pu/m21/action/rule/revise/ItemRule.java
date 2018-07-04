package nc.impl.pu.m21.action.rule.revise;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.rule.ItemNotNullCheckRule;

/**
 * @description
 *              采购订单表体非空检查
 * @scene
 *        采购订单修订
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午10:18:19
 * @author luojw
 */
public class ItemRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    ItemNotNullCheckRule rule = new ItemNotNullCheckRule();
    rule.checkItem(vos);
  }

}
