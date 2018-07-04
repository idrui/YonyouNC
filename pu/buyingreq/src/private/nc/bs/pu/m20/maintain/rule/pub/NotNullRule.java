/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-18 上午11:04:57
 */
package nc.bs.pu.m20.maintain.rule.pub;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.rule.ChkEmptyWhenSave;

/**
 * @description
 *              请购单非空项检查规则
 * @scene
 *        请购单新增、修改
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午9:42:23
 * @author yanxm5
 */
public class NotNullRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    this.checkNotNull(vos);
  }

  private void checkNotNull(PraybillVO[] vos) {
    if (null == vos || vos.length == 0) {
      return;
    }
    for (PraybillVO vo : vos) {
      new ChkEmptyWhenSave().chkEmpty(vo);
    }
  }
}
