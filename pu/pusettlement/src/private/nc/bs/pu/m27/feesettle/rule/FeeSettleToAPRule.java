package nc.bs.pu.m27.feesettle.rule;

import nc.impl.pu.m27.bp.SettleMakeInvoiceToAP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m27.entity.SettleBillVO;

/**
 * 
 * @description
 * 费用结算 时传应付
 * @scene
 * 费用结算
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午4:16:17
 * @author zhangshqb
 */
public class FeeSettleToAPRule implements IRule<SettleBillVO> {

  @Override
  public void process(SettleBillVO[] vos) {

    // 导致发票传应付
    new SettleMakeInvoiceToAP(vos).makeInvoiceToAP();
  }
}
