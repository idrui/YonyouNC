package nc.vo.pu.m21.rule;

import nc.vo.pu.m21.entity.OrderOnwayHeaderVO;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.pub.rule.IScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TotalValueScale;

public class OrderOnWayScaleRule implements IScaleProcessor {

  /** 主无税净价 */
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
    // 主数量
    String[] numkeys =
        new String[] {
          OrderOnwayItemVO.NONWAYNUM, OrderOnwayItemVO.NSENDOUTNUM,
          OrderOnwayItemVO.NCONFIRMNUM, OrderOnwayItemVO.NNUM,
          OrderOnwayItemVO.NACCUNUM
        };
    // 价格
    String[] pricekeys = new String[] {
      OrderOnwayItemVO.NORIGNETPRICE
    };
    // 原币金额
    String[] orgmnykeys = new String[] {
      OrderOnwayItemVO.NORIGMNY
    };

    // 表头税率
    String[] taxRateKey_H = new String[] {
      OrderOnwayHeaderVO.NHTAXRATE
    };
    // 表头整单税率
    scale.setTaxRateCtlInfo(taxRateKey_H, PosEnum.head, null);
    // 主单位数量精度
    scale.setNumCtlInfo(numkeys, posEnum, null, OrderOnwayItemVO.CUNITID,
        posEnum, null);
    // 单价精度
    scale.setPriceCtlInfo(pricekeys, posEnum, null,
        OrderOnwayHeaderVO.CORIGCURRENCYID, posEnum, null);
    // 原币金额精度
    scale.setMnyCtlInfo(orgmnykeys, posEnum, null,
        OrderOnwayHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
    // 进行计算
    scale.process();
  }
}
