/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-13 下午04:25:46
 */
package nc.impl.pu.m21.action.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;

/**
 * @description
 *              清空来自同一张合同的预付款
 * @scene
 *        采购订单取消审核
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午11:11:13
 * @author luojw
 */
public class ClearPrePayMnyRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    // zhaoyha 补充规则

  }

}
