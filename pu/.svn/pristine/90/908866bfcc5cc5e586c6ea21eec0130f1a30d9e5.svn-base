/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 下午01:21:16
 */
package nc.bs.pu.m20.maintain.rule.pub;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.rule.ItemNotNullCheckRule;

/**
 * @description
 *              请购单检查传入数组的完整性
 *              数组元素是否有空值，表头，表体完整性
 * @scene
 *        请购单整单关闭、新增、整单打开、发布/取消发布到电子商务、修改
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午9:04:57
 * @author yanxm5
 */
public class BodyEmptyRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    new ItemNotNullCheckRule().checkItem(vos);
  }

}
