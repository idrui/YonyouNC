package nc.vo.pu.m23.rule;

import nc.ui.pub.bill.IBillItem;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.pub.rule.IScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TotalValueScale;

/**
 * 到货单精度处理类
 * 
 * @since 6.0
 * @version 2010-11-4 下午08:12:53
 * @author tianft
 */
public class ArriveScaleRule implements IScaleProcessor {

  public void setGeneralScale(BillScaleProcessor scale) {
    this.setGeneralScale(scale, PosEnum.body);
  }

  /**
   * 普通的精度处理
   * 
   * @param scale - 普通的精度处理器
   */
  public void setGeneralScale(BillScaleProcessor scale, PosEnum posEnum) {
    // 换算率
    String[] changeRates = new String[] {
      ArriveItemVO.VCHANGERATE
    };
    // 金额
    String[] mnykeys = new String[] {
      ArriveItemVO.NMNY, ArriveItemVO.NTAXMNY, ArriveItemVO.NTAX
    };
    // 原币金额
    String[] orgmnykeys = new String[] {
      ArriveItemVO.NORIGTAXMNY, ArriveItemVO.NORIGMNY,
    };
    // 表头合计金额，表体的币种不一样，对表头合计不再处理精度（需求wangyf2011.6.26确认）
    // 表头合计金额－根据公共需求2011.9.7走模板精度
    // 业务单位数量
    String[] assistNumkeys =
        new String[] {
          ArriveItemVO.NASTNUM, ArriveItemVO.NPLANASTNUM,
          ArriveItemVO.NWASTASTNUM, ArriveItemVO.NPRESENTASTNUM
        };
    // 主数量
    String[] numkeys =
        new String[] {
          ArriveItemVO.NACCUMCHECKNUM, ArriveItemVO.NACCUMLETGONUM,
          ArriveItemVO.NACCUMREPLNUM, ArriveItemVO.NACCUMSTORENUM,
          ArriveItemVO.NNUM, ArriveItemVO.NPLANNUM, ArriveItemVO.NWASTNUM,
          ArriveItemVO.NPRESENTNUM, ArriveItemVO.NELIGNUM,
          ArriveItemVO.NNOTELIGNUM, ArriveItemVO.NCANSTORENUM,
          ArriveItemVO.NCANARRIVENUM, ArriveItemVO.NCHECKNUM,
          ArriveItemVO.NWILLELIGNUM, ArriveItemVO.NWILLNOTELIGNUM,
          ArriveItemVO.NACCUMBACKNUM
        };
    // 原币价格
    String[] OcurrPricekeys = new String[] {
      ArriveItemVO.NORIGPRICE, ArriveItemVO.NORIGTAXPRICE
    };
    // 本币价格
    String[] CurrPricekeys = new String[] {
      ArriveItemVO.NPRICE, ArriveItemVO.NTAXPRICE
    };
    // 表体税率
    String[] taxRateKey_B = new String[] {
      ArriveItemVO.NTAXRATE
    };
    // 表体税率
    scale.setTaxRateCtlInfo(taxRateKey_B, posEnum, null);
    // 换算率
    scale.setHslCtlInfo(changeRates, posEnum, null);
    // 业务单位数量精度
    scale.setNumCtlInfo(assistNumkeys, posEnum, null, ArriveItemVO.CASTUNITID,
        posEnum, null);
    // 主单位数量精度
    scale.setNumCtlInfo(numkeys, posEnum, null, ArriveItemVO.CUNITID, posEnum,
        null);
    // 金额精度
    scale.setMnyCtlInfo(mnykeys, posEnum, null, ArriveItemVO.CCURRENCYID,
        posEnum, null);
    // 原币金额精度
    scale.setMnyCtlInfo(orgmnykeys, posEnum, null,
        ArriveItemVO.CORIGCURRENCYID, posEnum, null);
    // 原币单价精度
    scale.setPriceCtlInfo(OcurrPricekeys, posEnum, null,
        ArriveItemVO.CORIGCURRENCYID, posEnum, null);
    // 本币单价精度
    scale.setPriceCtlInfo(CurrPricekeys, posEnum, null,
        ArriveItemVO.CCURRENCYID, posEnum, null);
    // 折本汇率精度
    this.setOrgExchange(scale, PosEnum.body.equals(posEnum) ? IBillItem.BODY
        : IBillItem.HEAD);
    // 进行计算
    scale.process();
  }

  /**
   * 表头合计精度处理
   * 
   * @param totalScale 合计的精度处理器
   */
  public void setHeaderScale(TotalValueScale totalScale) {
    totalScale.setHeadTailKeys(new String[] {
      ArriveHeaderVO.NTOTALASTNUM, ArriveHeaderVO.NTOTALTAXMNY
    });
  }

  @Override
  public void setScale(BillScaleProcessor scale, TotalValueScale totalScale) {
    if (scale != null) {
      this.setGeneralScale(scale);
    }
    if (totalScale != null) {
      this.setHeaderScale(totalScale);
    }
  }

  @Override
  public void setScaleForSingleTable(BillScaleProcessor scale,
      TotalValueScale totalScale) {
    if (scale != null) {
      this.setGeneralScale(scale, PosEnum.head);
    }
    if (totalScale != null) {
      this.setHeaderScale(totalScale);
    }
  }

  private void setOrgExchange(BillScaleProcessor scale, int pos) {
    FieldInfo rate = new FieldInfo(ArriveItemVO.NEXCHANGERATE, pos, null);
    FieldInfo srcCurr = new FieldInfo(ArriveItemVO.CORIGCURRENCYID, pos, null);
    FieldInfo destCurr = new FieldInfo(ArriveItemVO.CCURRENCYID, pos, null);
    FieldInfo org = new FieldInfo(ArriveItemVO.PK_PSFINANCEORG, pos, null);
    scale.setOrgExchangeCtlInfo(rate, srcCurr, destCurr, org);
  }

}
