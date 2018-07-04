
package nc.vo.pu.m4t.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.enumeration.InitialEstStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/**
 * 
 * @description
 *�ڳ��ݹ���״̬��飺�����Ƿ�����̬
 * @scene
 * �ڳ��ݹ�������
 * @param
 * ��
 *
 * @since 6.3
 * @version 2010-9-8 ����09:11:00
 * @author wuxla
 */


public class ApproveStatusChkRule implements IRule<InitialEstVO> {

	/**
	 * ���෽����д,�����ڳ��ݹ���״̬��飬״̬�����ɲ��ܽ�������
	 */
  @Override
  public void process(InitialEstVO[] vos) {
    for (InitialEstVO vo : vos) {
      // ״̬�����ɲ��ܽ�������
      if (InitialEstStatus.APPROVED.value().equals(
          vo.getHeader().getFbillstatus())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0171")/*@res "����״̬����ȷ������������"*/);
      }
    }
  }

}