/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-27 上午11:13:08
 */
package nc.bs.pu.m20.maintain.rule.close;

import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;

/**
 * @description
 *              请购单行打开后整单状态打开
 * @scene
 *        请购单整单打开
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午10:17:06
 * @author yanxm5
 */
public class OpenBill implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    this.openBills(vos);
  }

  private void openBill(PraybillHeaderVO vo) {
    VOUpdate<PraybillHeaderVO> up = new VOUpdate<PraybillHeaderVO>();
    up.update(new PraybillHeaderVO[] {
      vo
    }, new String[] {
      PraybillHeaderVO.FBILLSTATUS
    });
  }

  private void openBills(PraybillVO[] Vos) {
    for (PraybillVO vo : Vos) {
      // 只要有一行未关闭，则行状态为打开
      if (POEnumBillStatus.APPROVE.toInt() != vo.getHVO().getFbillstatus()
          .intValue()) {
        vo.getHVO().setFbillstatus(POEnumBillStatus.APPROVE.toInteger());
        this.openBill(vo.getHVO());
      }
    }
  }

}
