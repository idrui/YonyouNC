package nc.bs.pu.m21.maintain.rule.save;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.PlanArriveDateRule;

/**
 * @description
 *              采购订单计划到货日期不能早于订单日期
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午11:31:01
 * @author luojw
 */
public class PlanArriveDateCheckRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    PlanArriveDateRule rule = new PlanArriveDateRule();
    rule.checkPlanArriveDate(vos);
  }

}
