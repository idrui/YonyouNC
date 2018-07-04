package nc.impl.pu.m21.action.rule.payplan;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @description
 *              采购订单付款申请检查规则
 * @scene
 *        采购订单付款计划取消付款申请
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午2:59:10
 * @author luojw
 */

public class PayReqChkRule implements IRule<PayPlanViewVO> {

  @Override
  public void process(PayPlanViewVO[] vos) {
    StringBuilder sb = new StringBuilder();
    for (PayPlanViewVO vo : vos) {
      if (MathTool.compareTo(vo.getNaccumpayapporgmny(), UFDouble.ZERO_DBL) == 0) {
        sb.append(NCLangResOnserver.getInstance().getStrByID("4004030_0",
            "04004030-0282", null, new String[] {
              vo.getVbillcode(), vo.getCrowno()
            })/* 订单{0}序号为{1}的付款计划行未生成付款申请，不能取消\n */);
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }

}
