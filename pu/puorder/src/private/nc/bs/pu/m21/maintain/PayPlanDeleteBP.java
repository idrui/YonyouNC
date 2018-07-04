package nc.bs.pu.m21.maintain;

import nc.bs.pu.m21.maintain.rule.delete.PayPlanNotAllDelRule;
import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.scmf.payplan.rule.FollowupBillChkRule;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.m21.entity.PayPlanViewVO;

/**
 * 付款计划删除BP
 * 
 * @since 6.0
 * @version 2011-1-17 下午12:04:22
 * @author wuxla
 */

public class PayPlanDeleteBP {

  public void delete(PayPlanViewVO[] views) {
    PayPlanVO[] vos = new PayPlanVO[views.length];
    for (int i = 0; i < views.length; ++i) {
      vos[i] = (PayPlanVO) views[i].getVO(PayPlanVO.class);
    }

    AroundProcesser<PayPlanVO> processer =
        new AroundProcesser<PayPlanVO>(OrderPluginPoint.PAYPLAN_DELETE_BP);
    this.addRule(processer);

    processer.before(vos);

    VODelete<PayPlanVO> delete = new VODelete<PayPlanVO>();
    delete.delete(vos);

    processer.after(vos);
  }

  private void addRule(AroundProcesser<PayPlanVO> processer) {
    processer.addAfterRule(new PayPlanNotAllDelRule());
    processer.addBeforeRule(new FollowupBillChkRule<PayPlanVO>());
  }
}
