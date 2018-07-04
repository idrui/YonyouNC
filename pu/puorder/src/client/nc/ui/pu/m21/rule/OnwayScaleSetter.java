package nc.ui.pu.m21.rule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderOnwayHeaderVO;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ÿ�Ƭ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-19 ����03:35:01
 */
public class OnwayScaleSetter {
  private String pk_group;

  public OnwayScaleSetter(String pk_group) {
    this.pk_group = pk_group;
  }

  /**
   * �����������������ÿ�Ƭ���澫��
   * <p>
   * <b>����˵��</b>
   * 
   * @param panel
   *          ��Ƭ
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-19 ����03:35:01
   */
  public void setCardScale(BillCardPanel panel) {
    this.setBodyScale(new CardPaneScaleProcessor(this.pk_group, panel));
  }

  private void setBodyScale(BillScaleProcessor scale) {

    // // ���ҽ��
    // String[] mnykeys = new String[] {
    // OrderOnwayItemVO.NONWAYMNY, OrderOnwayItemVO.NSTATUSMNY
    // };
    // ԭ�ҽ��
    String[] orgmnykeys = new String[] {
      OrderOnwayItemVO.NONWAYMNY, OrderOnwayItemVO.NSTATUSMNY
    };
    // ������
    String[] numkeys =
        new String[] {
          OrderOnwayItemVO.NACCUNUM, OrderOnwayItemVO.NNUM,
          OrderOnwayItemVO.NONWAYNUM, OrderOnwayItemVO.NSENDOUTNUM
        };
    // �۸�
    String[] pricekeys = new String[] {
      OrderItemVO.NORIGNETPRICE
    };

    // ����λ��������
    scale.setNumCtlInfo(numkeys, PosEnum.body, null, OrderOnwayItemVO.CUNITID,
        PosEnum.body, null);
    // ���ҽ���
    // scale.setMnyCtlInfo(mnykeys, PosEnum.body, null,
    // OrderOnwayItemVO.CCURRENCYID, PosEnum.body, null);
    // ԭ�ҽ���
    scale.setMnyCtlInfo(orgmnykeys, PosEnum.body, null,
        OrderOnwayHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
    // ���۾���
    scale.setPriceCtlInfo(pricekeys, PosEnum.body, null,
        OrderOnwayHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
    // ���м���
    scale.process();
  }
}
