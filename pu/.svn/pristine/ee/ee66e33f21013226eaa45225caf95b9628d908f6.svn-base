package nc.bs.pu.m21.writeback.dm.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @description
 *              采购订单数量和累计运输数量正负一致检查
 * @scene
 *        运输单回写到货计划
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午10:11:57
 * @author luojw
 */

public class ReceivePlanDevChkRule implements IRule<OrderReceivePlanVO> {

  @Override
  public void process(OrderReceivePlanVO[] vos) {
    for (OrderReceivePlanVO vo : vos) {
      if (MathTool.isDiffSign(vo.getNnum(), vo.getNaccumdevnum())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0140")/*
                                                                     * @res
                                                                     * "订单数量和累计运输数量正负不一致"
                                                                     */);
      }
      // 不再检查容差
    }
  }

}
