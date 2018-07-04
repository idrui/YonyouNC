package nc.impl.pu.m21.action.rule.maintain;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.AggPayPlanVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.pub.util.APSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @description
 *              采购订单表头如果录入预付款限额，
 *              则该订单生成付款计划保存时检查付款计划行中预付款标志为是所有行的原币金额的合计必须小于订单表头预付款限额。
 *              并且付款计划行生成的付款申请单的预付款标志不允许修改。
 * @scene
 *        采购订单付款计划修改
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午3:36:25
 * @author luojw
 */

public class PayLimitRule implements IRule<AggPayPlanVO> {

  @Override
  public void process(AggPayPlanVO[] vos) {
    StringBuilder sb = new StringBuilder();
    for (AggPayPlanVO vo : vos) {
      UFDouble norgprepaylimit = vo.getHVO().getNorgprepaylimit();
      if (null == norgprepaylimit) {
        continue;
      }

      String pk_org = vo.getPayPlanVO()[0].getPk_financeorg();
      UFBoolean ap17 = APSysParamUtil.getAP17(pk_org);
      if (!ap17.booleanValue()) {
        continue;
      }
      UFDouble prepaySum = UFDouble.ZERO_DBL;
      for (PayPlanVO payplanVO : vo.getPayPlanVO()) {
        if (payplanVO.getBpreflag().booleanValue()) {
          prepaySum = MathTool.add(prepaySum, payplanVO.getNorigmny());
        }
      }

      if (MathTool.compareTo(prepaySum, norgprepaylimit) > 0) {
        if (sb.length() > 0) {
          sb.append(", ");
        }
        sb.append(vo.getHVO().getVbillcode());
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004030_0", "04004030-0280", null, new String[] {
            sb.toString()
          })/* 订单{0}付款计划行中预付款标志为是所有行的原币金额的合计超过订单表头预付款限额，请检查！ */);
    }
  }
}
