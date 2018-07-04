package nc.bs.pu.m21.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              采购订单冻结状态检查的规则
 * @scene
 *        采购订单冻结
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午9:37:17
 * @author luojw
 */
public class FreezeStatusCheckRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      OrderHeaderVO header = vo.getHVO();
      if (UFBoolean.TRUE.equals(header.getBfrozen())
          || UFBoolean.TRUE.equals(header.getBfinalclose())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0126")/*
                                                                     * @res
                                                                     * "已最终关闭或冻结的订单不能再进行冻结！"
                                                                     */);
      }
    }
  }

}
