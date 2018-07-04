package nc.bs.pu.m21.maintain;

import java.util.HashMap;
import java.util.Map;

import nc.bs.pu.m21.maintain.rule.save.PayPlanDateRule;
import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.m21.entity.PayPlanViewVO;

/**
 * 付款计划新增
 * 
 * @since 6.0
 * @version 2011-1-8 上午08:15:18
 * @author wuxla
 */

public class PayPlanInsertBP {

  public PayPlanViewVO[] insert(PayPlanViewVO[] views,
      OrderHeaderVO[] originAddHeadVOs) {
    PayPlanVO[] vos = new PayPlanVO[views.length];
    Map<String, OrderHeaderVO> headMap = new HashMap<String, OrderHeaderVO>();
    for (int i = 0; i < views.length; ++i) {
      vos[i] = (PayPlanVO) views[i].getVO(PayPlanVO.class);
    }
    for (OrderHeaderVO headVO : originAddHeadVOs) {
      headMap.put(headVO.getPk_order(), headVO);
    }

    AroundProcesser<PayPlanVO> processer =
        new AroundProcesser<PayPlanVO>(OrderPluginPoint.PAYPLAN_INSERT_BP);
    this.addRule(processer);

    processer.before(vos);
    VOInsert<PayPlanVO> insert = new VOInsert<PayPlanVO>();
    vos = insert.insert(vos);
    vos = processer.after(vos);

    for (int i = 0; i < vos.length; ++i) {
      views[i].setVO(vos[i]);
      views[i].setVO(headMap.get(vos[i].getPk_order()));
    }

    return views;
  }

  private void addRule(AroundProcesser<PayPlanVO> processer) {
    processer.addBeforeRule(new PayPlanDateRule());
  }

}
