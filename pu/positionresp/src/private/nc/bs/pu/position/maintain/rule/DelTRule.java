/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-7 下午02:09:58
 */
package nc.bs.pu.position.maintain.rule;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.position.entity.PositionTVO;
import nc.vo.pu.position.entity.PositionVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-7 下午02:09:58
 */
public class DelTRule implements IRule<PositionVO> {

  @Override
  public void process(PositionVO[] vos) {
    for (PositionVO positionVO : vos) {
      // 删除附表
      this.delOld(positionVO);
    }
  }

  private void delOld(PositionVO vo) {
    String pk_position = vo.getHVO().getPk_position();

    SqlBuilder sql = new SqlBuilder();
    sql.append(" delete from po_position_t where ");
    sql.append(PositionTVO.PK_POSITION, pk_position);
    new DataAccessUtils().update(sql.toString());
  }

}
