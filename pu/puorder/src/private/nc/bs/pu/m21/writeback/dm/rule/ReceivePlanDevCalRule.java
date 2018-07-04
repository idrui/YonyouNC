package nc.bs.pu.m21.writeback.dm.rule;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.dm.m4804.IOrderWriteBackParaFor4804;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @description
 *              运输单回写到货计划回写累计运输主数量
 * @scene
 *        运输单回写到货计划
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午10:08:57
 * @author luojw
 */

public class ReceivePlanDevCalRule implements IRule<OrderReceivePlanVO> {
  private IOrderWriteBackParaFor4804[] wbVos;

  public ReceivePlanDevCalRule(IOrderWriteBackParaFor4804[] wbVos) {
    this.wbVos = wbVos;
  }

  @Override
  public void process(OrderReceivePlanVO[] vos) {
    Map<String, OrderReceivePlanVO> rpMap =
        new HashMap<String, OrderReceivePlanVO>();
    for (OrderReceivePlanVO rpVO : vos) {
      rpMap.put(rpVO.getPk_order_bb1(), rpVO);
    }

    for (IOrderWriteBackParaFor4804 wbVo : this.wbVos) {
      String bbid = wbVo.getBBID();
      if (null == bbid) {
        continue;
      }
      OrderReceivePlanVO rpVO = rpMap.get(bbid);
      if (null == rpVO) {
        continue;
      }
      UFDouble newAccNum =
          MathTool.add(rpVO.getNaccumdevnum(), wbVo.getDiffNum());
      rpVO.setNaccumdevnum(newAccNum);
    }
  }

}
