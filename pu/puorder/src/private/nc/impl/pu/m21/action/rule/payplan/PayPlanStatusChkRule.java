package nc.impl.pu.m21.action.rule.payplan;

import java.util.HashSet;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              采购订单付款计划审核检查
 * @scene
 *        采购订单付款计划生成付款申请
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午3:17:33
 * @author luojw
 */

public class PayPlanStatusChkRule implements IRule<PayPlanViewVO> {

  @Override
  public void process(PayPlanViewVO[] vos) {
    Set<String> codeSet = new HashSet<String>();
    for (PayPlanViewVO vo : vos) {
      if (!POEnumBillStatus.APPROVE.value().equals(vo.getForderstatus())) {
        codeSet.add(vo.getVbillcode());
      }
    }
    if (0 == codeSet.size()) {
      return;
    }

    String[] codes = codeSet.toArray(new String[codeSet.size()]);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < codes.length; ++i) {
      if (i > 0) {
        sb.append(", ");
      }
      sb.append(codes[i]);
    }
    ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
        .getStrByID("4004030_0", "04004030-0281", null, new String[] {
          sb.toString()
        })/* 订单{0}未审核，不能生成付款申请 */);
  }

}
