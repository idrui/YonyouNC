/**
 * $�ļ�˵��$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-13 ����04:21:40
 */
package nc.impl.pu.m21.action.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              �ɹ����������Ϸ��Թ���
 * @scene
 *        �ɹ���������
 * @param ��
 * @since 6.3
 * @version 2014-10-20 ����2:45:53
 * @author luojw
 */
public class ApproveVOValidateRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    // ����״̬�Ϸ��Լ��
    this.orderStatusCheck(vos);

    // ��������ļ��
    this.orderFreezeCheck(vos);

  }

  private void orderFreezeCheck(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      // ���˻������Ѿ����ᣬ��������
      if (UFBoolean.TRUE.equals(vo.getHVO().getBfrozen())
          && UFBoolean.FALSE.equals(vo.getHVO().getBreturn())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0154")/*
                                                                     * @res
                                                                     * "�����Ѿ����ᣬ����������"
                                                                     */);
      }
    }
  }

  private void orderStatusCheck(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      // ״̬�����ɻ��ύ�������в��ܽ�������
      if (!vo.getHVO().getForderstatus().equals(POEnumBillStatus.FREE.value())
          && !vo.getHVO().getForderstatus()
              .equals(POEnumBillStatus.APPROVING.value())
          && !vo.getHVO().getForderstatus()
              .equals(POEnumBillStatus.COMMIT.value())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0155")/*
                                                                     * @res
                                                                     * "����״̬����ȷ������������"
                                                                     */);
      }
    }
  }

}
