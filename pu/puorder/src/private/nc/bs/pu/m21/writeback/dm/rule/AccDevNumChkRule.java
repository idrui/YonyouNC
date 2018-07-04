package nc.bs.pu.m21.writeback.dm.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @description
 *              采购订单订单数量和累计运输数量正负不一致
 * @scene
 *        运输单回写订单
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午3:55:52
 * @author luojw
 */

public class AccDevNumChkRule implements IRule<OrderViewVO> {

  @Override
  public void process(OrderViewVO[] vos) {
    for (OrderViewVO vo : vos) {
      if (MathTool.isDiffSign(vo.getNnum(), vo.getNaccumdevnum())) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004030_0", "04004030-0140")/* 订单数量和累计运输数量正负不一致 */);
      }
      // 不再检查容差
    }
  }

}
