package nc.bs.pu.m21.maintain.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              �ɹ������Ƿ���Эͬ�������۶������
 * @scene
 *        �ɹ�����Эͬ�������۶���
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����9:39:25
 * @author luojw
 */

public class CooptosoRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    List<String> list = new ArrayList<String>();
    for (OrderVO vo : vos) {
      if (UFBoolean.TRUE.equals(vo.getHVO().getBcooptoso())) {
        list.add(vo.getHVO().getVbillcode());
      }
    }

    if (list.size() > 0) {
      String[] codes = list.toArray(new String[list.size()]);
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004030_0", "04004030-0243", null, codes)/*
                                                                 * ����{0}�Ѿ�Эͬ�������۶���
                                                                 * ���������������۶���
                                                                 */);
    }
  }

}
