package nc.bs.pu.m21.writeback.dm.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @description
 *              �����ƻ�vo����رռ��
 * @scene
 *        ���䵥��д�����ƻ�
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����10:14:33
 * @author luojw
 */

public class AutoRPTransCloseRule implements IRule<OrderReceivePlanVO> {

  @Override
  public void process(OrderReceivePlanVO[] vos) {
    for (OrderReceivePlanVO vo : vos) {
      if (MathTool.compareTo(vo.getNaccumdevnum(), vo.getNnum()) >= 0) {
        vo.setBtransclosed(UFBoolean.TRUE);
      }
      else {
        vo.setBtransclosed(UFBoolean.FALSE);
      }
    }
  }

}
