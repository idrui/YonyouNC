package nc.vo.pu.m21.rule;

import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pubapp.scale.VOScaleProcessor;

/**
 * �����ƻ�����
 * 
 * @since 6.0
 * @version 2011-4-15 ����06:20:10
 * @author wuxla
 */

public class ReceivePlanScaleRule {
  /**
   * @param scale
   */
  public void setScale(VOScaleProcessor scale) {
    // ������
    // String[] changeRates = new String[] {
    // OrderReceivePlanVO.VCHANGERATE, OrderReceivePlanVO.VQTUNITRATE
    // };

    // ���۵�λ����
    String[] quoteNumkeys = new String[] {
      OrderReceivePlanVO.NQTUNITNUM
    };
    // ҵ��λ����
    String[] assistNumkeys = new String[] {
      OrderReceivePlanVO.NASTNUM
    };
    // ������
    String[] numkeys =
        new String[] {
          OrderReceivePlanVO.NACCUMARRVNUM, OrderReceivePlanVO.NACCUMDEVNUM,
          OrderReceivePlanVO.NACCUMSTORENUM, OrderReceivePlanVO.NACCUMWASTNUM,
          OrderReceivePlanVO.NBACKARRVNUM, OrderReceivePlanVO.NBACKSTORENUM,
          OrderReceivePlanVO.NNUM, OrderReceivePlanVO.NACCUMRECEIVENUM
        };

    // �����ʾ���
    // scale.setHslCtlInfo(changeRates);
    // ���۵�λ��������
    scale.setNumCtlInfo(quoteNumkeys, OrderReceivePlanVO.CQTUNITID);
    // ҵ��λ��������
    scale.setNumCtlInfo(assistNumkeys, OrderItemVO.CASTUNITID);
    // ����λ��������
    scale.setNumCtlInfo(numkeys, OrderItemVO.CUNITID);

    // ���м���
    scale.process();
  }
}
