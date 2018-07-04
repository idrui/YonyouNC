/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 ����01:21:16
 */
package nc.bs.pu.costfactor.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.pub.rule.ItemNotNullCheckRule;

/**
 * @description
 *              ͨ������ĳɱ�Ҫ�ض��嵥��VO����
 *              �������Ƿ�Ϊ��
 *              ��鴫�������������
 *              ����Ԫ���Ƿ��п�ֵ����ͷ������������
 * @scene
 *        �ɱ�Ҫ�ض����޸ı���BP
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����2:27:20
 * @author yanxm5
 */
public class BodyEmptyRule implements IRule<CostfactorVO> {

  @Override
  public void process(CostfactorVO[] vos) {
    new ItemNotNullCheckRule().checkItem(vos);
  }

}
