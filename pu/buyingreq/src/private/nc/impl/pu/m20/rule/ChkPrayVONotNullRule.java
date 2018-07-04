package nc.impl.pu.m20.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              ������빺��VO ����빺������ͷ��������������һ��Ϊ�����׳�ҵ���쳣
 * @scene
 *        �ƻ�������ʽ�����빺��������������ʽ�����빺��
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����1:42:44
 * @author yanxm5
 */
public class ChkPrayVONotNullRule implements IRule<PraybillVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PraybillVO[] vos) {
    for (PraybillVO vo : vos) {
      if (null == vo) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0055")/*
                                                                     * @res
                                                                     * "������빺��������ڿ�ֵ"
                                                                     */);
        return;
      }
      // ����
      if (null == vo.getHVO()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0056")/*
                                                                     * @res
                                                                     * "������빺��������ڱ�ͷΪ�յĵ��ݣ�����"
                                                                     */);
      }
      if (null == vo.getBVO()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0057")/*
                                                                     * @res
                                                                     * "������빺���������еı���Ϊ�գ�����"
                                                                     */);
      }
    }
  }
}
