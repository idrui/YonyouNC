package nc.bs.pu.m21.writeback.cmp.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              �ɹ���������ƻ����ۻ�����������У�����
 * @scene
 *        �������뵥��д
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����3:20:02
 * @author luojw
 */
public class AccumPayAppOrgMnyRule implements IRule<PayPlanViewVO> {

  @Override
  public void process(PayPlanViewVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (PayPlanViewVO vo : vos) {
      if (MathTool.compareTo(vo.getNaccumpayapporgmny(), vo.getNorigmny()) > 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0371")/*
                                                                     * @res
                                                                     * "У���ۼƸ���������ܴ��ڸ���ƻ��еĽ��"
                                                                     */);
      }
    }
  }

}
