/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-12 ����03:05:01
 */
package nc.bs.pu.est.m50.rule;

import nc.bs.pu.est.rule.EstFeeItemProcRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.m4202.entity.VmiFIFeeVO;

/**
 * 
 * @description
 * �ݹ�ʱ������壨�����ݹ�������Ĭ���У��ɱ�Ҫ���ж��壩
 * @scene
 * ǰ̨����(����ͬʱ)�ݹ���ѯ�ݹ�
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-23 ����9:59:43
 * @author zhangshqb
 */
public class VMIEstFeeProcRule extends EstFeeItemProcRule implements
    IRule<VmiEstVO> {

  public VMIEstFeeProcRule() {
    super(VmiFIFeeVO.class);
  }

  @Override
  public void process(VmiEstVO[] vos) {
    super.process(vos);
  }

}
