package nc.vo.pu.m21.rule;

import java.util.HashSet;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              �ɹ����������رռ��
 * @scene
 *        �ɹ���������ƻ����ɸ������롢�������뵥��д�������д
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����3:19:06
 * @author luojw
 */

public class OrderPayCloseChkRule implements IRule<PayPlanViewVO> {

  @Override
  public void process(PayPlanViewVO[] vos) {
    Set<String> codeSet = new HashSet<String>();
    for (PayPlanViewVO vo : vos) {
      if (UFBoolean.TRUE.equals(vo.getBfinalclose())) {
        codeSet.add(vo.getVbillcode());
      }
    }
    if (0 == codeSet.size()) {
      return;
    }

    String[] codes = codeSet.toArray(new String[codeSet.size()]);
    ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4004030_0", "04004030-0313", null, codes)/*
                                                               * ����{0}�Ѿ����չرգ�
                                                               * �������ɸ�������
                                                               */);
  }

}
