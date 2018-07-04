package nc.ui.pu.m21.rule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.ui.pubapp.scale.TotalValueScaleProcessor;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ÿ�Ƭ����
 * <li>�����б���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-20 ����03:29:50
 */
public class ConfirmScaleSetter {
  private String pk_group;

  public ConfirmScaleSetter(String pk_group) {
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
   * @author duy
   * @time 2010-5-17 ����03:35:01
   */
  public void setCardScale(BillCardPanel panel) {
    this.setBodyScale(new CardPaneScaleProcessor(this.pk_group, panel));
    this.setHeadScale(new TotalValueScaleProcessor(panel));
  }

  /**
   * �������������������б���澫��
   * <p>
   * <b>����˵��</b>
   * 
   * @param panel
   *          �б�
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-5-17 ����03:35:17
   */
  public void setListScale(BillListPanel panel) {
    this.setBodyScale(new ListPaneScaleProcessor(this.pk_group, panel));
    this.setHeadScale(new TotalValueScaleProcessor(panel));
  }

  private void setBodyScale(BillScaleProcessor scale) {
    // �ɱ�����
    String[] costpricekeys = new String[] {};
    // ȫ�ֱ�λ�ҽ��
    String[] globalmnykeys = new String[] {
      OrderItemVO.NGLOBALMNY, OrderItemVO.NGLOBALTAXMNY
    };
    // ���ű�λ�ҽ��
    String[] groupmnykeys = new String[] {
      OrderItemVO.NGROUPMNY, OrderItemVO.NGROUPTAXMNY
    };
    // ������
    String[] changeRates = new String[] {
      OrderItemVO.VCHANGERATE, OrderItemVO.VQTUNITRATE
    };
    // ����˰��
    String[] taxRateKey_B = new String[] {
      OrderItemVO.NTAXRATE, OrderItemVO.NNOSUBTAXRATE
    };

    // ���ҽ��
    String[] mnykeys =
        new String[] {
          OrderItemVO.NMNY, OrderItemVO.NTAXMNY, OrderItemVO.NTAX,
          OrderItemVO.NACCCANCELINVMNY, OrderItemVO.NACCUMINVOICEMNY,
          OrderItemVO.NFEEMNY
        };
    // ���۵�λ����
    String[] quoteNumkeys = new String[] {
      OrderItemVO.NQTUNITNUM
    };
    // ҵ��λ����
    String[] assistNumkeys = new String[] {
      OrderItemVO.NASTNUM
    };
    // ������
    String[] numkeys =
        new String[] {
          OrderItemVO.NACCUMARRVNUM, OrderItemVO.NACCUMDEVNUM,
          OrderItemVO.NACCUMINVOICENUM, OrderItemVO.NACCUMRPNUM,
          OrderItemVO.NACCUMSTORENUM, OrderItemVO.NACCUMWASTNUM,
          OrderItemVO.NBACKARRVNUM, OrderItemVO.NBACKSTORENUM,
          OrderItemVO.NCANARRIVENUM, OrderItemVO.NCANINVOICENUM,
          OrderItemVO.NCONFIRMNUM, OrderItemVO.NNUM,
        };
    // ԭ�Ҽ۸�
    String[] OcurrPricekeys =
        new String[] {
          OrderItemVO.NORIGNETPRICE, OrderItemVO.NORIGPRICE,
          OrderItemVO.NORIGTAXNETPRICE, OrderItemVO.NORIGTAXPRICE,
          OrderItemVO.NQTORIGNETPRICE, OrderItemVO.NQTORIGPRICE,
          OrderItemVO.NQTORIGTAXNETPRC, OrderItemVO.NQTORIGTAXPRICE
        };
    // ���Ҽ۸�
    String[] CurrPriceKeys =
        new String[] {
          OrderItemVO.NNETPRICE, OrderItemVO.NQTNETPRICE,
          OrderItemVO.NQTTAXNETPRICE, OrderItemVO.NTAXNETPRICE
        };
    // ԭ�ҽ��
    String[] orgmnykeys = new String[] {
      OrderItemVO.NORIGMNY, OrderItemVO.NORIGTAXMNY, OrderItemVO.NCONFIRMMNY
    };
    // ����
    String[] weightkeys = new String[] {
      OrderItemVO.NWEIGHT
    };
    // ���
    String[] volumnkeys = new String[] {
      OrderItemVO.NVOLUMN
    };
    // ����
    String[] packkeys = new String[] {
      OrderItemVO.NPACKNUM
    };

    // ˰��
    scale.setTaxRateCtlInfo(taxRateKey_B, PosEnum.body, null);

    // �ɱ��۸��ֶξ���
    scale.setCostPriceCtlInfo(costpricekeys, PosEnum.body, null);
    // ȫ�ֱ�λ�ҽ���
    scale.setGlobalLocMnyCtlInfo(globalmnykeys, PosEnum.body, null);
    // ���ű�λ�ҽ���
    scale.setGroupLocMnyCtlInfo(groupmnykeys, PosEnum.body, null);
    // �����ʾ���
    scale.setHslCtlInfo(changeRates, PosEnum.body, null);
    // ���۵�λ��������
    scale.setNumCtlInfo(quoteNumkeys, PosEnum.body, null,
        OrderItemVO.CQTUNITID, PosEnum.body, null);
    // ҵ��λ��������
    scale.setNumCtlInfo(assistNumkeys, PosEnum.body, null,
        OrderItemVO.CASTUNITID, PosEnum.body, null);
    // ����λ��������
    scale.setNumCtlInfo(numkeys, PosEnum.body, null, OrderItemVO.CUNITID,
        PosEnum.body, null);
    // ���ҽ���
    scale.setMnyCtlInfo(mnykeys, PosEnum.body, null, OrderItemVO.CCURRENCYID,
        PosEnum.body, null);
    // ԭ�ҵ��۾���
    scale.setPriceCtlInfo(OcurrPricekeys, PosEnum.body, null,
        OrderHeaderVO.CORIGCURRENCYID, PosEnum.body, null);
    // ���ҵ��۾���
    scale.setPriceCtlInfo(CurrPriceKeys, PosEnum.body, null,
        OrderItemVO.CCURRENCYID, PosEnum.body, null);
    // ԭ�ҽ���
    scale.setMnyCtlInfo(orgmnykeys, PosEnum.body, null,
        OrderHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
    // ��������
    scale.setWeightCtlInfo(weightkeys, PosEnum.body, null);
    // �������
    scale.setVolumnCtlInfo(volumnkeys, PosEnum.body, null);
    // ��������
    scale.setUnitCtlInfo(packkeys, PosEnum.body, null, OrderItemVO.PK_MATERIAL,
        PosEnum.body, null);
    // ���м���
    scale.process();
  }

  private void setHeadScale(TotalValueScaleProcessor scale) {
    scale.setHeadTailKeys(new String[] {
      OrderHeaderVO.NTOTALASTNUM, OrderHeaderVO.NTOTALORIGMNY,
      OrderHeaderVO.NTOTALPIECE, OrderHeaderVO.NTOTALVOLUME,
      OrderHeaderVO.NTOTALWEIGHT
    });
  }

}
