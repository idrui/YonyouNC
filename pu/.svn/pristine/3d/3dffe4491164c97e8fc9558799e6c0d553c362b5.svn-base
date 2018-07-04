package nc.impl.pu.m21.action.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.rule.RowNoCheckRule;

/**
 * 
 * @description
 *            采购订单保存时，进行行号检查
 * @scene
 *      采购订单保存
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-3-21 上午11:26:21
 * @author wuxla
 */
public class RowNoRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    RowNoCheckRule rule = new RowNoCheckRule();
    rule.checkRowNo(vos, OrderItemVO.CROWNO);
  }

}
