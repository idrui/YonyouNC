package nc.bs.pu.m21.maintain.rule.save;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              采购订单付款计划不能存在账期到期日在起算日期之前的行
 * @scene
 *        采购订单付款计划新增、修改
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午3:15:02
 * @author luojw
 */

public class PayPlanDateRule implements IRule<PayPlanVO> {

  @Override
  public void process(PayPlanVO[] vos) {
    for (PayPlanVO vo : vos) {
      UFDate dbegindate = vo.getDbegindate();
      if (null == dbegindate) {
        continue;
      }
      UFDate denddate = vo.getDenddate();
      if (null == denddate) {
        continue;
      }

      if (dbegindate.after(denddate)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0120")/*
                                                                     * @res
                                                                     * "存在账期到期日在起算日期之前的行，请检查"
                                                                     */);
      }
    }
  }

}
