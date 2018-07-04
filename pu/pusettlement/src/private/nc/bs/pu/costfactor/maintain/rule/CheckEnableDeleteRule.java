/**
 * $�ļ�˵��$
 * 
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-9 ����02:53:58
 */
package nc.bs.pu.costfactor.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.costfactor.entity.CostfactorVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              ͨ���жϴ���ĳɱ�Ҫ�ض��嵥��VO�Ƿ�Ϊ�� �ж��Ƿ���ɾ��
 * @scene
 *        �ɱ�Ҫ�ض���ɾ��BP
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����2:21:32
 * @author yanxm5
 */
public class CheckEnableDeleteRule implements IRule<CostfactorVO> {

  @Override
  public void process(CostfactorVO[] bill) {
    if (ArrayUtils.isEmpty(bill)) {
      return;
    }
  }
}
