/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-27 ����11:13:20
 */
package nc.bs.pu.m20.maintain.rule.close;

import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.enumeration.EnumBillStatue;

/**
 * @description
 *              �빺��ȫ���йرպ������ر�
 * @scene
 *        �빺�������ر�
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����9:16:34
 * @author yanxm5
 */
public class CloseBill implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    this.closeBills(vos);
  }

  private void closeBill(PraybillHeaderVO vo) {
    VOUpdate<PraybillHeaderVO> up = new VOUpdate<PraybillHeaderVO>();
    up.update(new PraybillHeaderVO[] {
      vo
    }, new String[] {
      PraybillHeaderVO.FBILLSTATUS
    });
  }

  private void closeBills(PraybillVO[] Vos) {
    for (PraybillVO vo : Vos) {
      // �����ȫ���رգ����Զ������ر�
      if (EnumBillStatue.CLOSE.toInt() == vo.getHVO().getFbillstatus()
          .intValue()) {
        continue;
      }

      boolean needClose = true;
      PraybillItemVO[] items = vo.getBVO();
      for (PraybillItemVO item : items) {
        if (!item.getBrowclose().booleanValue()) {
          needClose = false;
          break;
        }
      }

      if (needClose) {
        vo.getHVO().setFbillstatus(EnumBillStatue.CLOSE.toInteger());
        this.closeBill(vo.getHVO());
      }
    }
  }
}
