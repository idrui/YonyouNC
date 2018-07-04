/**
 * $�ļ�˵��$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-13 ����04:15:14
 */
package nc.impl.pu.m21.action.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              �ɹ���������״̬�Ϸ��Լ�顢�رռ�顢������
 * @scene
 *        �ɹ�����ȡ�����
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����11:03:19
 * @author luojw
 */
public class UnApproveVOValidateRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    // ����״̬�Ϸ��Լ��
    this.orderStatusCheck(vos);

    // �رռ��
    this.orderCloseStatusCheck(vos);

    // ��������ļ��
    this.orderFreezeCheck(vos);

  }

  /**
   * ������������������رռ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderVO
   * @param sb
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-10 ����04:15:42
   */
  private void itemVOCloseStatusCheck(OrderVO orderVO, StringBuilder sb) {
    OrderItemVO[] itemVOs = orderVO.getBVO();
    if (ArrayUtils.isEmpty(itemVOs)) {
      return;
    }

    for (OrderItemVO itemVO : itemVOs) {
      if (itemVO.getFisactive().equals(EnumActive.CLOSE.value())) {
        sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4004030_0", "04004030-0157")/* @res "���ݴ��ڹرյ��У����ܲ�����\n" */);
        return;
      }
    }
  }

  /**
   * �����������������������رջ�����һ�йر���������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-10 ����04:04:08
   */
  private void orderCloseStatusCheck(OrderVO[] vos) {
    StringBuilder sb = new StringBuilder();
    for (OrderVO vo : vos) {
      OrderHeaderVO headerVO = vo.getHVO();
      if (headerVO.getBfinalclose().booleanValue()) {
        sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4004030_0", "04004030-0158")/* @res "���������չرգ����ܲ�����\n" */);
        continue;
      }
      this.itemVOCloseStatusCheck(vo, sb);
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }

  private void orderFreezeCheck(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      // �����Ѿ����ᣬ����ȡ������
      if (UFBoolean.TRUE.equals(vo.getHVO().getBfrozen())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0159")/*
                                                                     * @res
                                                                     * "�����Ѿ����ᣬ����ȡ��������"
                                                                     */);
      }
    }
  }

  private void orderStatusCheck(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      // ״̬������̬�ĵ��ݲ���ȡ������Ҳ�����ջ�
      if (vo.getHVO().getForderstatus().equals(POEnumBillStatus.FREE.value())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0160")/*
                                                                     * @res
                                                                     * "����״̬����ȷ�����ܲ�����"
                                                                     */);
      }
    }
  }

}
