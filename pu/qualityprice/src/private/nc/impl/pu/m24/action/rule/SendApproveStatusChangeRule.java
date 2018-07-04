package nc.impl.pu.m24.action.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.VOStatus;

/**
 * @description
 *              �۸���㵥������VO��״̬����
 * @scene
 *        �۸���㵥����
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����4:31:28
 * @author luojw
 */
public class SendApproveStatusChangeRule implements IRule<PricestlVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PricestlVO[] vos) {
    for (PricestlVO vo : vos) {
      vo.getHVO().setFbillstatus((Integer) POEnumBillStatus.APPROVING.value());
      vo.getHVO().setStatus(VOStatus.UPDATED);
    }
  }

}
