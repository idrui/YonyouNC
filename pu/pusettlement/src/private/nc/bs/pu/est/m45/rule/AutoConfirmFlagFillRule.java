/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-7 ����04:20:21
 */
package nc.bs.pu.est.m45.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;

/**
 * 
 * @description
 * ȷ�ϳɱ���Ӧ��ʱ���±�ͷ�Զ��������־
 * @scene
 * �Զ����ɱ���Ӧ��
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-23 ����9:38:01
 * @author zhangshqb
 */
public class AutoConfirmFlagFillRule implements IRule<PurchaseinFIVO> {

  @Override
  public void process(PurchaseinFIVO[] vos) {
    for (PurchaseinFIVO vo : vos) {
      vo.getParentVO().setStatus(VOStatus.UPDATED);
      vo.getParentVO().setBautotofi(UFBoolean.TRUE);
    }

  }

}
