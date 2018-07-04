package nc.bs.pu.m21.maintain.rule.save;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.AggPayPlanVO;
import nc.vo.pu.pub.rule.RowNoCheckRule;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;

/**
 * @description
 *              采购订单付款计划行号检查
 * @scene
 *        采购订单付款计划修改
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午3:35:20
 * @author luojw
 */

public class PayPlanRowNoRule implements IRule<AggPayPlanVO> {

  @Override
  public void process(AggPayPlanVO[] vos) {
    RowNoCheckRule rule = new RowNoCheckRule();
    rule.checkRowNo(vos, AbstractPayPlanVO.CROWNO);
  }

}
