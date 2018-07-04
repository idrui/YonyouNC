package nc.bs.pu.m21.writeback.cmp.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单付款计划行累积付款申请金额校验规则
 * @scene
 *        付款申请单回写
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午3:20:02
 * @author luojw
 */
public class AccumPayAppOrgMnyRule implements IRule<PayPlanViewVO> {

  @Override
  public void process(PayPlanViewVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (PayPlanViewVO vo : vos) {
      if (MathTool.compareTo(vo.getNaccumpayapporgmny(), vo.getNorigmny()) > 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0371")/*
                                                                     * @res
                                                                     * "校验累计付款申请金额不能大于付款计划行的金额"
                                                                     */);
      }
    }
  }

}
