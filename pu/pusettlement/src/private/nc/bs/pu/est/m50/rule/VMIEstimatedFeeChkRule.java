/**
 * $�ļ�˵��$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-19 ����09:03:51
 */
package nc.bs.pu.est.m50.rule;

import nc.bs.pu.est.rule.EstimatedFeeChkRule;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.est.entity.m50.VmiEstVO;

/**
 * 
 * @description
 * ����Ƿ�����Ѿ��ݹ��Ĺ�������
 * ֻ�д������ݹ���ʹ�ô˹���
 * ����ͷ���һ���ݹ��Ĳ��üӴ˹���У��
 * ��Ϊ�����Ի����ݹ���˵��δ������ϣ�Ҳδ�ݹ���ϣ��ڴ������²��������������ݹ�
 * @scene
 * �ݹ���BP����
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-23 ����9:54:46
 * @author zhangshqb
 */
public class VMIEstimatedFeeChkRule extends EstimatedFeeChkRule implements
    ICompareRule<VmiEstVO> {

  @Override
  public void process(VmiEstVO[] vos, VmiEstVO[] originVOs) {
    super.process(vos, originVOs);
  }
}
