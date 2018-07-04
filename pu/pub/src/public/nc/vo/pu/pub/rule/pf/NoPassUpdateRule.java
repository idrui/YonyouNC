package nc.vo.pu.pub.rule.pf;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * @description
 *              ������������Ҫ��,�����޸ı���ʱ,�����ǰ״̬��������ͨ��,��״̬��Ϊ����̬
 * @scene
 *        �����������뵥�����������빺���Ͳɹ��������桢�޸�
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����10:43:41
 * @author yanxm5
 */
public class NoPassUpdateRule<E extends AbstractBill> implements IRule<E> {

  @Override
  public void process(E[] vos) {
    ApproveFlowUtil.processBillStatusWhenEdit(vos);
  }

}
