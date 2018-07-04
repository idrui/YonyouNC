/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 下午01:21:16
 */
package nc.bs.pu.costfactor.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.pub.rule.ItemNotNullCheckRule;

/**
 * @description
 *              通过传入的成本要素定义单据VO数组
 *              检查表体是否为空
 *              检查传入数组的完整性
 *              数组元素是否有空值，表头，表体完整性
 * @scene
 *        成本要素定义修改保存BP
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午2:27:20
 * @author yanxm5
 */
public class BodyEmptyRule implements IRule<CostfactorVO> {

  @Override
  public void process(CostfactorVO[] vos) {
    new ItemNotNullCheckRule().checkItem(vos);
  }

}
