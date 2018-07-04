/**
 * $文件说明$
 * 
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-9 下午02:53:58
 */
package nc.bs.pu.costfactor.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.costfactor.entity.CostfactorVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              通过判断传入的成本要素定义单据VO是否为空 判断是否能删除
 * @scene
 *        成本要素定义删除BP
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午2:21:32
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
