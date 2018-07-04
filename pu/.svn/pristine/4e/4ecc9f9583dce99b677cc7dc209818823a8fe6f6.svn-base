package nc.impl.pu.m21.action.rule.approve;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单弃审删除付款计划
 * @scene
 *        采购订单取消审核
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午11:13:37
 * @author luojw
 */

public class DeletePayPlanRule implements ICompareRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    StringBuilder sb = new StringBuilder();
    List<PayPlanVO> payplanList = new ArrayList<PayPlanVO>();
    for (OrderVO originVO : originVOs) {
      this.check(originVO, sb, payplanList);
    }
    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004030_0", "04004030-0272", null, new String[] {
            sb.toString()
          })/* 订单{0}付款计划已经生成后续单据，不允许弃审 */);
      return;
    }

    if (0 == payplanList.size()) {
      return;
    }

    PayPlanVO[] payplanVOs =
        payplanList.toArray(new PayPlanVO[payplanList.size()]);
    VODelete<PayPlanVO> delete = new VODelete<PayPlanVO>();
    delete.delete(payplanVOs);

    for (OrderVO vo : vos) {
      // vo.setChildren(PayPlanVO.class, null);
      vo.setChildren(PayPlanVO.class, new PayPlanVO[0]);
    }
  }

  private void check(OrderVO originVO, StringBuilder sb,
      List<PayPlanVO> payplanList) {
    PayPlanVO[] payplanVOs =
        (PayPlanVO[]) originVO.getChildren(PayPlanVO.class);
    if (ArrayUtils.isEmpty(payplanVOs)) {
      return;
    }
    for (PayPlanVO payplanVO : payplanVOs) {
      if (MathTool.compareTo(payplanVO.getNaccumpayapporgmny(),
          UFDouble.ZERO_DBL) > 0
          || MathTool.compareTo(payplanVO.getNaccumpayorgmny(),
              UFDouble.ZERO_DBL) > 0) {
        if (sb.length() > 0) {
          sb.append(", ");
        }
        sb.append(originVO.getHVO().getVbillcode());
        return;
      }
    }

    payplanList.addAll(Arrays.asList(payplanVOs));
  }
}
