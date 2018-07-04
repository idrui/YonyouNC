package nc.impl.pu.m21.action.rule.payplan;

import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;

/**
 * @description
 *              采购订单付款计划回写
 * @scene
 *        采购订单付款计划取消付款申请
 * @param UFBoolean payreq 如果为真累计付款金额设置原币金额，否则设置为空
 * @since 6.3
 * @version 2014-10-21 下午3:02:31
 * @author luojw
 */

public class WriteBackPayPlanRule implements IRule<PayPlanViewVO> {
  private UFBoolean payreq;

  public WriteBackPayPlanRule(UFBoolean payreq) {
    this.payreq = payreq;
  }

  @Override
  public void process(PayPlanViewVO[] vos) {
    if (this.payreq.booleanValue()) {
      for (PayPlanViewVO vo : vos) {
        vo.setNaccumpayapporgmny(vo.getNorigmny());
        vo.setNaccumpayappmny(vo.getNmny());
      }
    }
    else {
      for (PayPlanViewVO vo : vos) {
        vo.setNaccumpayapporgmny(null);
        vo.setNaccumpayappmny(null);
      }
    }

    ViewUpdate<PayPlanViewVO> update = new ViewUpdate<PayPlanViewVO>();
    update.update(vos, PayPlanVO.class, new String[] {
      AbstractPayPlanVO.NACCUMPAYAPPORGMNY, AbstractPayPlanVO.NACCUMPAYAPPMNY
    });
  }
}
