package nc.impl.pu.m21.action.rule.maintain;

import java.util.HashSet;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 状态检查
 * 
 * @since 6.0
 * @version 2011-1-22 上午09:14:43
 * @author wuxla
 */

public class OrderStatusChkRule implements IRule<PayPlanViewVO> {
  private OrderHeaderVO[] originAddHeadVOs;

  public OrderStatusChkRule(OrderHeaderVO[] originAddHeadVOs) {
    this.originAddHeadVOs = originAddHeadVOs;
  }

  @Override
  public void process(PayPlanViewVO[] views) {

    Set<String> set = new HashSet<String>();
    this.checkViews(views, set);
    this.checkVOs(this.originAddHeadVOs, set);
    if (set.size() > 0) {
      String[] codes = set.toArray(new String[set.size()]);
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < codes.length; ++i) {
        if (i > 0) {
          sb.append(", ");
        }
        sb.append(codes[i]);
      }
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004030_0", "04004030-0277", null, new String[] {
            sb.toString()
          })/* 订单{0}未审核，不能保存付款计划 */);
    }
  }

  private void checkViews(PayPlanViewVO[] views, Set<String> set) {
    if (ArrayUtils.isEmpty(views)) {
      return;
    }
    for (PayPlanViewVO view : views) {
      if (!POEnumBillStatus.APPROVE.value().equals(view.getForderstatus())) {
        set.add(view.getVbillcode());
      }
    }
  }

  private void checkVOs(OrderHeaderVO[] originAddVOs, Set<String> set) {
    if (ArrayUtils.isEmpty(originAddVOs)) {
      return;
    }

    for (OrderHeaderVO vo : originAddVOs) {
      if (!POEnumBillStatus.APPROVE.value().equals(vo.getForderstatus())) {
        set.add(vo.getVbillcode());
      }
    }
  }
}
