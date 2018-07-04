package nc.vo.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pu.m21.pub.PayPlanDataUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单付款、付款申请检查
 * @scene
 *        采购订单付款计划生成付款申请
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午3:21:51
 * @author luojw
 */
public class OrderPayChkRule implements IRule<PayPlanViewVO> {

  @Override
  public void process(PayPlanViewVO[] vos) {
    String[] bills = PayPlanDataUtil.getHasPayOrders(vos);
    if (ArrayUtils.isEmpty(bills)) {
      return;
    }
    StringBuffer msg = new StringBuffer();
    // msg.append("不能执行此操作！以下订单后续的应付单有付款申请、或付款单。\n 订单号：");
    msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004030_0", "04004030-0356")/*
                                      * @res
                                      * 不能执行此操作！以下订单后续的应付单有付款申请、或付款单。\n 订单号：
                                      */);

    for (int i = 0; i < bills.length; i++) {
      msg.append(bills[i]);
      if (i < bills.length - 1) {
        msg.append(",");
      }
    }
    ExceptionUtils.wrappBusinessException(msg.toString());
  }

}
