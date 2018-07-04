package nc.vo.pu.m23.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ic.ATPServices;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 
 * @description
 * 到货单的可用量公共规则类，分为操作前、操作后规则
 * @scene
 * 到货单审批
 * @param
 * isBeforeRule
 *
 * @since 6.3
 * @version 2010-7-2 上午10:35:40
 * @author hanbin
 */

public class ArriveATPUpdateRule implements IRule<ArriveVO> {

  private String cbilltype = POBillType.Arrive.getCode();

  private boolean isBeforeRule;

  public ArriveATPUpdateRule(boolean isBeforeRule) {
    this.isBeforeRule = isBeforeRule;
  }

  @Override
  public void process(ArriveVO[] vos) {
    if (this.isBeforeRule) {
      this.modifyATPBefore(vos);
    }
    else {
      this.modifyATPAfter(vos);
    }
  }
  /*
   * 操作后规则
   */
  private void modifyATPAfter(AggregatedValueObject[] bills) {
    ATPServices.modifyATPAfter(this.cbilltype, bills);
  }

  /*
   * 操作前规则
   */
  private void modifyATPBefore(AggregatedValueObject[] bills) {
    ATPServices.modifyATPBefore(this.cbilltype, bills);
  }
}
