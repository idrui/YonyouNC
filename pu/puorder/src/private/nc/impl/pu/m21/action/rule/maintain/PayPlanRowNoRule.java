package nc.impl.pu.m21.action.rule.maintain;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.AggPayPlanVO;
import nc.vo.pu.pub.rule.RowNoCheckRule;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;

/**
 * 
 * @description
 *            �ɹ��������ɸ���ƻ�ʱ���кŽ��м��
 * @scene
 *      �ɹ��������ɸ���ƻ�
 * @param
 * 
 *
 * @since 6.0
 * @version 2011-1-18 ����10:19:42
 * @author wuxla
 */
public class PayPlanRowNoRule implements IRule<AggPayPlanVO> {

  @Override
  public void process(AggPayPlanVO[] vos) {
    RowNoCheckRule rule = new RowNoCheckRule();
    rule.checkRowNo(vos, AbstractPayPlanVO.CROWNO);
  }

}
