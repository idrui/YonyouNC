package nc.vo.pu.m25.rule.maintain.save;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceRowType;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 *            �ɹ����÷�Ʊ����ʱ��ֻ��¼��������ۿ�������
 * @scene
 *      �ɹ����÷�Ʊ����
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-4-29 ����04:50:37
 * @author zhaoyha
 */
public class FeeMaterialCheckRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    for (InvoiceVO vo : vos) {
      boolean fee = ValueUtils.getBoolean(vo.getParentVO().getBfee());
      if (!fee) {
        continue;
      }
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        if (InvoiceRowType.DISCOUNT_ROW != item.getFrowtype().intValue()
            && InvoiceRowType.FEE_ROW != item.getFrowtype().intValue()) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004050_0", "04004050-0023")/*
                                                                       * @res
                                                                       * "���÷�Ʊ��ֻ��¼��������ۿ�������"
                                                                       */);
        }
      }
    }
  }

}
