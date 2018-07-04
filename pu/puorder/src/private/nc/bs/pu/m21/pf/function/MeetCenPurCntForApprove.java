package nc.bs.pu.m21.pf.function;

import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmf.pu.cenpurule.entity.ControlType;

/**
 * 动作约束-流程-是否满足集采合同
 * 
 * @since 6.0
 * @version 2012-7-23 下午04:27:24
 * @author liuyand
 */
public class MeetCenPurCntForApprove extends CenPurRuleFunction {

  public UFBoolean meetCenPurCnt(AggregatedValueObject vo)
      throws BusinessException {
    OrderVO ordervo = (OrderVO) vo;
    this.setCtrltype(ControlType.CONTRACT.toInt());
    return this.meetCenPuRule(ordervo);
  }
}
