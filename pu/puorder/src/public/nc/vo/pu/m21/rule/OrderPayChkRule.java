package nc.vo.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pu.m21.pub.PayPlanDataUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              �ɹ������������������
 * @scene
 *        �ɹ���������ƻ����ɸ�������
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����3:21:51
 * @author luojw
 */
public class OrderPayChkRule implements IRule<PayPlanViewVO> {

  @Override
  public void process(PayPlanViewVO[] vos) {
    String[] bills = PayPlanDataUtil.getHasPayOrders(vos);
    if (ArrayUtils.isEmpty(bills)) {
      return;
    }
    StringBuffer msg = new StringBuffer();
    // msg.append("����ִ�д˲��������¶���������Ӧ�����и������롢�򸶿��\n �����ţ�");
    msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004030_0", "04004030-0356")/*
                                      * @res
                                      * ����ִ�д˲��������¶���������Ӧ�����и������롢�򸶿��\n �����ţ�
                                      */);

    for (int i = 0; i < bills.length; i++) {
      msg.append(bills[i]);
      if (i < bills.length - 1) {
        msg.append(",");
      }
    }
    ExceptionUtils.wrappBusinessException(msg.toString());
  }

}
