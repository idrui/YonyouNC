package nc.bs.pu.m21.writeback.dm.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @description
 *              采购订单自动运输关闭
 * @scene
 *        运输单回写订单
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午4:00:08
 * @author luojw
 */

public class AutoTransCloseRule implements IRule<OrderViewVO> {

  @Override
  public void process(OrderViewVO[] vos) {
    for (OrderViewVO vo : vos) {
      if (MathTool.compareTo(vo.getNaccumdevnum(), vo.getNnum()) >= 0) {
        vo.setBtransclosed(UFBoolean.TRUE);
      }
      else {
        vo.setBtransclosed(UFBoolean.FALSE);
      }
    }
  }

}
