package nc.bs.pu.m21.maintain;

import java.util.Map;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.scmf.payplan.rule.PayPlanDateRule;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pu.pub.util.CirVOUtil;

/**
 * 更新付款计划
 * 
 * @since 6.0
 * @version 2011-1-17 下午12:04:40
 * @author wuxla
 */

public class PayPlanUpdateBP {

  public PayPlanViewVO[] update(PayPlanViewVO[] views, PayPlanViewVO[] orgViews) {
    PayPlanVO[] vos = new PayPlanVO[views.length];
    PayPlanVO[] originVOs = new PayPlanVO[views.length];

    for (int i = 0; i < views.length; ++i) {
      vos[i] = (PayPlanVO) views[i].getVO(PayPlanVO.class);
      originVOs[i] = (PayPlanVO) orgViews[i].getVO(PayPlanVO.class);
      OrderHeaderVO headVO =
          (OrderHeaderVO) orgViews[i].getVO(OrderHeaderVO.class);
      views[i].setVO(headVO);
    }

    CompareAroundProcesser<PayPlanVO> processer =
        new CompareAroundProcesser<PayPlanVO>(
            OrderPluginPoint.PAYPLAN_UPDATE_BP);
    this.addRule(processer);

    processer.before(vos, originVOs);
    VOUpdate<PayPlanVO> update = new VOUpdate<PayPlanVO>();
    vos = update.update(vos, originVOs);
    vos = processer.after(vos, originVOs);

    Map<String, PayPlanVO> itemMap = CirVOUtil.createKeyVOMap(vos);

    for (int i = 0; i < views.length; ++i) {
      PayPlanVO vo = (PayPlanVO) views[i].getVO(PayPlanVO.class);
      views[i].setVO(itemMap.get(vo.getPk_order_payplan()));
    }

    return views;
  }

  private void addRule(CompareAroundProcesser<PayPlanVO> processer) {
    //processer.addBeforeRule(new PayPlanRateRule());
    processer.addBeforeRule(new PayPlanDateRule<PayPlanVO>());
    // processer.addBeforeRule(new UpdFollowupBillChkRule<PayPlanVO>());
//    processer.addBeforeRule(new PayPlanNotEditItemCheckRule());

  }
}
