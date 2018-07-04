/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 上午11:25:38
 */
package nc.bs.pu.m20.maintain.rule.pub;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.scmpub.util.VOFieldLengthChecker;

/**
 * @description
 *              请购单字段长度检查规则
 * @scene
 *        请购单新增、修改
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午9:47:26
 * @author yanxm5
 */
public class ChkLenRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    VOFieldLengthChecker.checkVOFieldsLength(vos);
  }
}
