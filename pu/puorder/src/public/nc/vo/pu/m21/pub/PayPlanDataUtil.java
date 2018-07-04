package nc.vo.pu.m21.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.itf.pu.m21.IOrderPayPlanData;
import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pu.m21.rule.OrderPayPlan;
import nc.vo.pu.m21.rule.PayPlanData;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-1-23 上午10:10:48
 * @author wuxla
 */

public class PayPlanDataUtil {

  /**
   * 获取后续应付单已经付款或者付款申请的订单
   * 
   * @param views
   * @return
   */
  public static String[] getHasPayOrders(PayPlanViewVO[] views) {
    Set<String> ids = new HashSet<String>();
    Map<String, String> billNo = new HashMap<String, String>();
    for (PayPlanViewVO view : views) {
      ids.add(view.getPk_order());
      billNo.put(view.getPk_order(), view.getVbillcode());
    }
    Map<String, UFBoolean> checkResult =
        ArapServicesForPUUtil.hasNextPaybillByPuID(ids.toArray(new String[ids
            .size()]));
    if (MapUtils.isEmpty(checkResult)) {
      return null;
    }
    Set<String> bills = new HashSet<String>();
    for (Entry<String, UFBoolean> entry : checkResult.entrySet()) {
      // 应付单已经付款或者付款申请的订单，加入到返回结果里
      if (entry.getValue().booleanValue()) {
        bills.add(billNo.get(entry.getKey()));
      }
    }
    if (bills.size() > 0) {
      return bills.toArray(new String[bills.size()]);
    }
    return null;

  }

  public static PayPlanVO[] getPayPlanData(OrderVO[] vos) {
	IOrderPayPlanData[] payplanDatas = new PayPlanData[vos.length];
    for (int i = 0; i < vos.length; ++i) {
      payplanDatas[i] = new PayPlanData(vos[i]);
    }
    OrderPayPlan<PayPlanVO, IOrderPayPlanData> payplan =
        new OrderPayPlan<PayPlanVO, IOrderPayPlanData>(PayPlanVO.class, payplanDatas,
            OrderPaymentVO.class);
    List<PayPlanVO[]> payplanList = payplan.getPlan();

    List<PayPlanVO> list = new ArrayList<PayPlanVO>();
    for (int i = 0; i < payplanDatas.length; ++i) {
      PayPlanVO[] payplanVOs = payplanList.get(i);
      if (!ArrayUtils.isEmpty(payplanVOs)) {
        for (PayPlanVO vo : payplanVOs) {
          vo.setPk_order(vos[i].getHVO().getPk_order());
          list.add(vo);
        }
      }
    }
    return list.toArray(new PayPlanVO[list.size()]);
  }

}
