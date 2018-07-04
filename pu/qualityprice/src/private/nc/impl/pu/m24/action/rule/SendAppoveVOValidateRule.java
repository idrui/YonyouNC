package nc.impl.pu.m24.action.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              �۸���㵥������VO��״̬�ȼ��
 * @scene
 *        �۸���㵥����
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����4:30:25
 * @author luojw
 */
public class SendAppoveVOValidateRule implements IRule<PricestlVO> {

  @Override
  public void process(PricestlVO[] vos) {
    // ����״̬�Ϸ��Լ��
    this.orderStatusCheck(vos);
  }

  private void orderStatusCheck(PricestlVO[] vos) {
    for (PricestlVO vo : vos) {
      // ״̬�����ɵĲ����ύ
      if (!(vo.getHVO().getFbillstatus().intValue() == POEnumBillStatus.FREE
          .toInt())) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004070_0", "04004070-0013", null, new String[] {
              vo.getHVO().getVbillcode()
            })/* ����{0}��״̬����ȷ�������ύ�� */);
      }
    }
  }

}
