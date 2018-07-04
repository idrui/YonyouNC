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
import nc.vo.pu.m20.rule.ChkDate;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购日期、需求日期、建议订货日期检查规则
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-26 上午11:25:38
 */
public class ChkDateRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    this.ChkDate(vos);
  }

  private void ChkDate(PraybillVO[] vos) {
    if ((null == vos) || (vos.length == 0)) {
      return;
    }
    for (PraybillVO vo : vos) {
      new ChkDate().checkDate(vo);
    }
  }

}
