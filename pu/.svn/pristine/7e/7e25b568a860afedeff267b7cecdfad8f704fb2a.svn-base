/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 下午01:09:42
 */
package nc.bs.pu.m20.maintain.rule.pub;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.rule.RowNoCheckRule;

/**
 * @description
 *              请购单行号检查
 * @scene
 *        请购单新增、修改
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午9:57:03
 * @author yanxm5
 */
public class RownoCheckRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    new RowNoCheckRule().checkRowNo(vos, PraybillItemVO.CROWNO);
  }
}
