/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-9 上午11:54:56
 */
package nc.impl.pu.m20.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.VOStatus;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>送审时将请购单状态设为提交
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-26 上午11:33:40
 */
public class PraybillStateChgRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    for (PraybillVO vo : vos) {
      vo.getHVO().setStatus(VOStatus.UPDATED);
      vo.getHVO().setFbillstatus(POEnumBillStatus.APPROVING.toInteger());
    }
  }

}
