/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-15 上午11:47:11
 */
package nc.bs.pu.m20.maintain.rule.close;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;

/**
 * @description
 *              设置请购单为打开状态
 * @scene
 *        请购单整单打开
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午10:14:34
 * @author yanxm5
 */
public class SetOpenStatus implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    this.setVOStatusOpen(vos);
  }

  private void setVOStatusOpen(PraybillVO[] Vos) {
    for (PraybillVO vo : Vos) {
      vo.getHVO().setFbillstatus(POEnumBillStatus.APPROVE.toInteger());
      vo.getHVO().setStatus(VOStatus.UPDATED);
      PraybillItemVO[] items = vo.getBVO();
      for (PraybillItemVO item : items) {
        if (item.getBrowclose().booleanValue()) {
          item.setStatus(VOStatus.UPDATED);
          item.setBrowclose(UFBoolean.FALSE);
        }

      }
    }
  }
}
