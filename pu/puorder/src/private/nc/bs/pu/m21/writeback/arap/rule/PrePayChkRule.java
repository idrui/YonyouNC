package nc.bs.pu.m21.writeback.arap.rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.arap.ArapServicesUtil;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pu.pub.util.APSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @description
 *              采购订单预付款检查规则
 * @scene
 *        付款单回写
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午4:09:05
 * @author luojw
 */

public class PrePayChkRule implements IRule<PayPlanViewVO> {

  @Override
  public void process(PayPlanViewVO[] views) {
    Map<String, UFDouble> map = new HashMap<String, UFDouble>();
    for (PayPlanViewVO view : views) {
      UFDouble norgprepaylimit = view.getNorgprepaylimit();
      if (null == norgprepaylimit) {
        continue;
      }

      String pk_order = view.getPk_order();
      if (map.containsKey(pk_order)) {
        continue;
      }

      String pk_org = view.getPk_financeorg();
      UFBoolean ap17 = APSysParamUtil.getAP17(pk_org);
      if (!ap17.booleanValue()) {
        continue;
      }
      map.put(pk_order, norgprepaylimit);
    }
    if (0 == map.size()) {
      return;
    }
    Collection<String> keySet = map.keySet();
    String[] hids = keySet.toArray(new String[keySet.size()]);
    Map<String, UFDouble> prepayMap = ArapServicesUtil.getPrePay(hids);
    for (Entry<String, UFDouble> entry : prepayMap.entrySet()) {
      UFDouble prepay = entry.getValue();
      if (0 == MathTool.compareTo(prepay, UFDouble.ZERO_DBL)) {
        return;
      }
      UFDouble limit = map.get(entry.getKey());
      if (MathTool.compareTo(prepay, limit) > 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0138")/*
                                                                     * @res
                                                                     * "预付款超出订单预付款限额，请检查！"
                                                                     */);
      }
    }
  }
}
