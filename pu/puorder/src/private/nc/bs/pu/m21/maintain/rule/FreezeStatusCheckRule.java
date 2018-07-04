package nc.bs.pu.m21.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              �ɹ���������״̬���Ĺ���
 * @scene
 *        �ɹ���������
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����9:37:17
 * @author luojw
 */
public class FreezeStatusCheckRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      OrderHeaderVO header = vo.getHVO();
      if (UFBoolean.TRUE.equals(header.getBfrozen())
          || UFBoolean.TRUE.equals(header.getBfinalclose())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0126")/*
                                                                     * @res
                                                                     * "�����չرջ򶳽�Ķ��������ٽ��ж��ᣡ"
                                                                     */);
      }
    }
  }

}
