package nc.vo.pu.m21.rule;

import nc.vo.pu.m21.entity.OrderOnwayHeaderVO;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.pub.rule.IScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TotalValueScale;

public class OrderOnWayScaleRule implements IScaleProcessor {

  /** ����˰���� */
  public static final String NORIGNETPRICE = "norignetprice";

  @Override
  public void setScale(BillScaleProcessor scale, TotalValueScale totalScale) {
    if (scale != null) {
      this.setBodyScale(scale);
    }
    if (totalScale != null) {
      this.setHeadScale(totalScale);
    }
  }

  @Override
  public void setScaleForSingleTable(BillScaleProcessor scale,
      TotalValueScale totalScale) {
    if (scale != null) {
      this.setScale(scale, PosEnum.head);
    }
    if (totalScale != null) {
      this.setHeadScale(totalScale);
    }
  }

  private void setBodyScale(BillScaleProcessor scale) {
    this.setScale(scale, PosEnum.body);
  }

  private void setHeadScale(TotalValueScale scale) {
    scale.setHeadTailKeys(new String[] {
      OrderOnwayHeaderVO.NTOTALASTNUM, OrderOnwayHeaderVO.NTOTALORIGMNY,
      OrderOnwayHeaderVO.NTOTALPIECE, OrderOnwayHeaderVO.NTOTALVOLUME,
      OrderOnwayHeaderVO.NTOTALWEIGHT
    });
  }

  private void setScale(BillScaleProcessor scale, PosEnum posEnum) {
    // ������
    String[] numkeys =
        new String[] {
          OrderOnwayItemVO.NONWAYNUM, OrderOnwayItemVO.NSENDOUTNUM,
          OrderOnwayItemVO.NCONFIRMNUM, OrderOnwayItemVO.NNUM,
          OrderOnwayItemVO.NACCUNUM
        };
    // �۸�
    String[] pricekeys = new String[] {
      OrderOnwayItemVO.NORIGNETPRICE
    };
    // ԭ�ҽ��
    String[] orgmnykeys = new String[] {
      OrderOnwayItemVO.NORIGMNY
    };

    // ��ͷ˰��
    String[] taxRateKey_H = new String[] {
      OrderOnwayHeaderVO.NHTAXRATE
    };
    // ��ͷ����˰��
    scale.setTaxRateCtlInfo(taxRateKey_H, PosEnum.head, null);
    // ����λ��������
    scale.setNumCtlInfo(numkeys, posEnum, null, OrderOnwayItemVO.CUNITID,
        posEnum, null);
    // ���۾���
    scale.setPriceCtlInfo(pricekeys, posEnum, null,
        OrderOnwayHeaderVO.CORIGCURRENCYID, posEnum, null);
    // ԭ�ҽ���
    scale.setMnyCtlInfo(orgmnykeys, posEnum, null,
        OrderOnwayHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
    // ���м���
    scale.process();
  }
}
