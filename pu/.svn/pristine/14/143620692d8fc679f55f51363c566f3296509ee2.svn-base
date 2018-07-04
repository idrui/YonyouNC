package nc.vo.pu.m21.rule;

import java.util.HashSet;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              采购订单冻结检查
 * @scene
 *        采购订单付款计划生成付款申请、付款申请单回写、付款单回写
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午3:20:42
 * @author luojw
 */

public class OrderPayFreezeChkRule implements IRule<PayPlanViewVO> {

  @Override
  public void process(PayPlanViewVO[] vos) {
    Set<String> codeSet = new HashSet<String>();
    for (PayPlanViewVO vo : vos) {
      if (UFBoolean.TRUE.equals(vo.getBfrozen())) {
        codeSet.add(vo.getVbillcode());
      }
    }
    if (0 == codeSet.size()) {
      return;
    }

    String[] codes = codeSet.toArray(new String[codeSet.size()]);
    ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4004030_0", "04004030-0336", null, codes)/*
                                                               * 订单{0}已经冻结，
                                                               * 不能生成付款单或付款申请
                                                               */);
  }
}
