package nc.bs.pu.m21.maintain.rule.save;

import java.util.Set;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.VOTool;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单付款计划行，如果已经生成付款单或者付款申请，非编辑项检查规则，只有原币金额,账期到期日字段可编辑。
 * @scene
 *        采购订单付款计划修改
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午3:39:33
 * @author luojw
 */
public class PayPlanNotEditItemCheckRule implements ICompareRule<PayPlanVO> {

  @Override
  public void process(PayPlanVO[] vos, PayPlanVO[] originVOs) {
    if (ArrayUtils.isEmpty(vos) || ArrayUtils.isEmpty(originVOs)
        || vos.length != originVOs.length) {
      return;
    }

    VOTool tool = new VOTool();
    for (int i = 0; i < originVOs.length; i++) {
      // 付款计划行是否已生成下游单据
      if (MathTool.compareTo(originVOs[i].getNaccumpayapporgmny(),
          UFDouble.ZERO_DBL) > 0
          || MathTool.compareTo(originVOs[i].getNaccumpayorgmny(),
              UFDouble.ZERO_DBL) > 0) {
        Set<String> diffFieldSet = tool.getDifferentField(originVOs[i], vos[i]);
        if (diffFieldSet.isEmpty()) {
          return;
        }

        if (diffFieldSet.contains(AbstractPayPlanVO.NORIGMNY)) {
          diffFieldSet.remove(AbstractPayPlanVO.NORIGMNY);
        }
        if (diffFieldSet.contains(AbstractPayPlanVO.DENDDATE)) {
          diffFieldSet.remove(AbstractPayPlanVO.DENDDATE);
        }

        if (!diffFieldSet.isEmpty()) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004030_0", "04004030-0370")/*
                                                                       * @res
                                                                       * "付款计划行，如果已经生成付款单或者付款申请,只有原币金额字段可编辑"
                                                                       */);
        }
      }
    }
  }
}
