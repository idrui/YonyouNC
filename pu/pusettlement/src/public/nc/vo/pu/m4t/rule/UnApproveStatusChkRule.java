package nc.vo.pu.m4t.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.enumeration.InitialEstStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 *�ڳ��ݹ���״̬��飺״̬����������̬
 * @scene
 * �ڳ��ݹ���ȡ������
 * @param
 * ��
 *
 * @since 6.3
 * @version 2010-9-8 ����09:25:43
 * @author wuxla
 */
 
public class UnApproveStatusChkRule implements IRule<InitialEstVO> {

  /**
   * ���෽����д,״̬������̬�ĵ��ݲ���ȡ������
   */
  @Override
  public void process(InitialEstVO[] vos) {
    for (InitialEstVO vo : vos) {
      // ״̬������̬�ĵ��ݲ���ȡ������
      if (InitialEstStatus.FEE.value().equals(vo.getHeader().getFbillstatus())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0193")/*@res "����״̬����ȷ�����ܲ�����"*/);
      }
    }
  }
}