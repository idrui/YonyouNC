/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-14 ����10:19:01
 */
package nc.impl.pu.m21.action.rule.approve;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              �ɹ�����������VO��״̬�ȼ��
 * @scene
 *        �ɹ���������
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����10:55:29
 * @author luojw
 */
public class SendAppoveVOValidateRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    // ����״̬�Ϸ��Լ��
    this.orderStatusCheck(vos);

  }

  private void orderStatusCheck(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      // ״̬�����ɵĲ����ύ
    	/*
    	 * add by wandl 
    	 * ����������̬���������еĲſ����ύ
    	 */
      if (!vo.getHVO().getForderstatus().equals(POEnumBillStatus.FREE.value())
      		&& !vo.getHVO().getForderstatus().equals(POEnumBillStatus.APPROVING.value())) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004030_0", "04004030-0274", null, new String[] {
              vo.getHVO().getVbillcode()
            })/* ����{0}��״̬����ȷ�������ύ�� */);
      }
    }
  }

}
