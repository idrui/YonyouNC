/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 下午01:21:16
 */
package nc.bs.pu.position.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.position.entity.PositionVO;
import nc.vo.pu.pub.rule.ItemNotNullCheckRule;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>检查单据是否为空
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-26 下午01:21:16
 */
public class BodyEmptyRule implements IRule<PositionVO> {

  @Override
  public void process(PositionVO[] vos) {
    new ItemNotNullCheckRule().checkItem(vos);
  }

}
