package nc.bs.pu.m21.pf.function;

import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmf.pu.cenpurule.entity.ControlType;

/**
 * ����Լ��-����-�Ƿ����㼯�ɹ�Ӧ����¼
 * 
 * @since 6.0
 * @version 2012-7-23 ����04:28:57
 * @author liuyand
 */
public class MeetCenPurVrmForApprove extends CenPurRuleFunction {
  public UFBoolean meetCenPurVrm(AggregatedValueObject vo)
      throws BusinessException {
    OrderVO ordervo = (OrderVO) vo;
    this.setCtrltype(ControlType.VENDORLIST.toInt());
    return this.meetCenPuRule(ordervo);
  }
}
