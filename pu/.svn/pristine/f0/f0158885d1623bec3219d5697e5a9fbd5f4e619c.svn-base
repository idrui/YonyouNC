package nc.bs.pu.m21.writeback.dm.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @description
 *              到货计划vo运输关闭检查
 * @scene
 *        运输单回写到货计划
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午10:14:33
 * @author luojw
 */

public class AutoRPTransCloseRule implements IRule<OrderReceivePlanVO> {

  @Override
  public void process(OrderReceivePlanVO[] vos) {
    for (OrderReceivePlanVO vo : vos) {
      if (MathTool.compareTo(vo.getNaccumdevnum(), vo.getNnum()) >= 0) {
        vo.setBtransclosed(UFBoolean.TRUE);
      }
      else {
        vo.setBtransclosed(UFBoolean.FALSE);
      }
    }
  }

}
