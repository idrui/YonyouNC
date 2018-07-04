package nc.bs.pu.m21.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              采购订单解冻状态检查的规则
 * @scene
 *        采购订单解冻
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午11:15:09
 * @author luojw
 */
public class UnfreezeStatusCheckRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      OrderHeaderVO header = vo.getHVO();
      if (UFBoolean.FALSE.equals(header.getBfrozen())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0134")/*
                                                                     * @res
                                                                     * "订单没有被冻结，不能进行解冻！"
                                                                     */);
      }
    }
  }

}
