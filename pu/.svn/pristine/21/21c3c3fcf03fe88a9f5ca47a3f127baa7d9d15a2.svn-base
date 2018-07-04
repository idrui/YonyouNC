/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-8 下午08:56:14
 */
package nc.bs.pu.position.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.position.entity.PositionItemVO;
import nc.vo.pu.position.entity.PositionVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>补充数据
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-8 下午08:56:14
 */
public class FillDataRule implements IRule<PositionVO> {

  @Override
  public void process(PositionVO[] vos) {
    for (PositionVO vo : vos) {
      String pk_org = vo.getHVO().getPk_org();
      String pk_position = vo.getHVO().getPk_position();
      PositionItemVO[] items = vo.getBVO();
      for (PositionItemVO item : items) {
        if (null == item.getPk_org()) {
          item.setPk_org(pk_org);
        }

        if (null == item.getPk_position()) {
          item.setPk_position(pk_position);
        }

      }
    }
  }
}
