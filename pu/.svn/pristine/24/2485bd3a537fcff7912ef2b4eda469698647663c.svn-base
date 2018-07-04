package nc.bs.pu.m21.writeback.cmp.rule;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.cmp.m36d1.OrderPayPlanWriteBackParaFor36D1;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单累计付款申请（原币和本币）金额计算规则类
 * @scene
 *        付款申请单回写
 * @param
 *        Map<String, OrderPayPlanWriteBackParaFor36D1> backVoMap
 *        资金付款申请单回写订单付款计划参数VO
 * @since 6.3
 * @version 2014-10-22 下午3:18:06
 * @author luojw
 */
public class AccumPayAppMnyCalRule implements IRule<PayPlanViewVO> {

  private Map<String, OrderPayPlanWriteBackParaFor36D1> backVoMap;

  public AccumPayAppMnyCalRule(
      Map<String, OrderPayPlanWriteBackParaFor36D1> backVoMap) {
    this.backVoMap = backVoMap;
  }

  @Override
  public void process(PayPlanViewVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (PayPlanViewVO vo : vos) {
      // 更新累计付款申请金额
      vo.setNaccumpayapporgmny(MathTool.add(vo.getNaccumpayapporgmny(),
          this.backVoMap.get(vo.getPk_order_payplan()).getDiffOrigMny()));
      // 更新累计付款申请本币金额
      vo.setNaccumpayappmny(MathTool.add(vo.getNaccumpayappmny(),
          this.backVoMap.get(vo.getPk_order_payplan()).getDiffMny()));
    }
  }

}
