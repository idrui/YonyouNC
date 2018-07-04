/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-10 下午02:17:09
 */
package nc.vo.pu.m25.pub;

import nc.ui.pub.bill.IBillItem;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.rule.IScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TotalValueScale;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表头\表体的精度处理器
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-7-10 下午02:17:09
 */
public class InvoiceScaleProcessor implements IScaleProcessor {

  @Override
  public void setScale(BillScaleProcessor scale, TotalValueScale totalScale) {
    this.setScale(scale, totalScale, PosEnum.body, false);
  }

  public void setScaleForCheck(BillScaleProcessor scale) {
    this.setScale(scale, null, PosEnum.body, true);
  }

  @Override
  public void setScaleForSingleTable(BillScaleProcessor scale,
      TotalValueScale totalScale) {
    this.setScale(scale, totalScale, PosEnum.head, false);
  }

  private void setGlobalExchange(BillScaleProcessor scale) {
    FieldInfo rate =
        new FieldInfo(InvoiceHeaderVO.NGLOBALEXCHGRATE, IBillItem.HEAD, null);
    FieldInfo orgOrigCurr =
        new FieldInfo(InvoiceHeaderVO.CORIGCURRENCYID, IBillItem.HEAD, null);
    FieldInfo orgLocCurr =
        new FieldInfo(InvoiceHeaderVO.CCURRENCYID, IBillItem.HEAD, null);
    scale.setGlobalExchangeCtlInfo(rate, orgOrigCurr, orgLocCurr);
  }

  private void setGroupExchange(BillScaleProcessor scale) {
    FieldInfo rate =
        new FieldInfo(InvoiceHeaderVO.NGROUPEXCHGRATE, IBillItem.HEAD, null);
    FieldInfo orgOrigCurr =
        new FieldInfo(InvoiceHeaderVO.CORIGCURRENCYID, IBillItem.HEAD, null);
    FieldInfo orgLocCurr =
        new FieldInfo(InvoiceHeaderVO.CCURRENCYID, IBillItem.HEAD, null);
    scale.setGroupExchangeCtlInfo(rate, orgOrigCurr, orgLocCurr);
  }

  private void setOrgExchange(BillScaleProcessor scale) {
    FieldInfo rate =
        new FieldInfo(InvoiceHeaderVO.NEXCHANGERATE, IBillItem.HEAD, null);
    FieldInfo srcCurr =
        new FieldInfo(InvoiceHeaderVO.CORIGCURRENCYID, IBillItem.HEAD, null);
    FieldInfo destCurr =
        new FieldInfo(InvoiceHeaderVO.CCURRENCYID, IBillItem.HEAD, null);
    FieldInfo org = new FieldInfo(InvoiceHeaderVO.PK_ORG, IBillItem.HEAD, null);
    scale.setOrgExchangeCtlInfo(rate, srcCurr, destCurr, org);
  }

  private void setScale(BillScaleProcessor scale, TotalValueScale totalScale,
      PosEnum posEnum, boolean forScaleCheck) {
    //
    // 全局本位币金额
    String[] globalmnykeys = new String[] {
      InvoiceItemVO.NGLOBALMNY, InvoiceItemVO.NGLOBALTAXMNY
    };
    // 集团本位币金额
    String[] groupmnykeys = new String[] {
      InvoiceItemVO.NGROUPMNY, InvoiceItemVO.NGROUPTAXMNY
    };
    // 原币金额
    String[] orgmnykeys = new String[] {
      InvoiceItemVO.NORIGMNY, InvoiceItemVO.NORIGTAXMNY
    };
    // 表头合计金额－根据公共需求2011.9.7走模板精度
    String[] headMnykeys = new String[] {
      InvoiceHeaderVO.NTOTALORIGMNY
    };

    // 本币金额
    String[] mnykeys =
        new String[] {
          InvoiceItemVO.NMNY, InvoiceItemVO.NTAXMNY, InvoiceItemVO.NTAX,
          InvoiceItemVO.NACCUMSETTMNY, InvoiceItemVO.NCALTAXMNY,
          InvoiceItemVO.NNOSUBTAX, InvoiceItemVO.NCALCOSTMNY
        };
    // 业务单位数量
    String[] assistNumkeys = new String[] {
      InvoiceItemVO.NASTNUM
    };
    // 主数量
    String[] numkeys =
        new String[] {
          InvoiceItemVO.NNUM, InvoiceItemVO.NACCUMSETTNUM,
          InvoiceItemVO.NREASONWASTENUM
        };
    // 本币价格
    String[] CurrPricekeys =
        new String[] {
          InvoiceItemVO.NPRICE, InvoiceItemVO.NTAXPRICE,
          InvoiceItemVO.NASTPRICE, InvoiceItemVO.NASTTAXPRICE
        };
    // 原币价格
    String[] OcurrPricekeys =
        new String[] {
          InvoiceItemVO.NORIGPRICE, InvoiceItemVO.NORIGTAXPRICE,
          InvoiceItemVO.NASTORIGPRICE, InvoiceItemVO.NASTORIGTAXPRICE,
          InvoiceItemVO.NPLANPRICE
        };
    // 表体税率
    String[] taxRateKey_B = new String[] {
      InvoiceItemVO.NTAXRATE, InvoiceItemVO.NNOSUBTAXRATE
    };
    // 表头税率
    String[] taxRateKey_H = new String[] {
      InvoiceHeaderVO.NTAXRATEH
    };
    // 换算率
    String[] changeRates = new String[] {
      InvoiceItemVO.VCHANGERATE
    };
    // 业务单位数量精度
    scale.setNumCtlInfo(assistNumkeys, posEnum, null, InvoiceItemVO.CASTUNITID,
        posEnum, null);
    // 主单位数量精度
    scale.setNumCtlInfo(numkeys, posEnum, null, InvoiceItemVO.CUNITID, posEnum,
        null);
    // 本币单价精度
    scale.setPriceCtlInfo(CurrPricekeys, posEnum, null,
        InvoiceHeaderVO.CCURRENCYID, PosEnum.head, null);
    // 原币单价精度
    scale.setPriceCtlInfo(OcurrPricekeys, posEnum, null,
        InvoiceHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
    // 本币金额精度
    scale.setMnyCtlInfo(mnykeys, posEnum, null, InvoiceHeaderVO.CCURRENCYID,
        PosEnum.head, null);

    // 原币金额精度
    scale.setMnyCtlInfo(orgmnykeys, posEnum, null,
        InvoiceHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
    // 此处放精度校验不需要处理的部分,精度校验只针对简单的单价、金额、数量。
    if (!forScaleCheck) {
      // 表体税率
      scale.setTaxRateCtlInfo(taxRateKey_B, posEnum, null);
      // 表头整单税率
      scale.setTaxRateCtlInfo(taxRateKey_H, PosEnum.head, null);
      // 全局本位币金额精度
      scale.setGlobalLocMnyCtlInfo(globalmnykeys, posEnum, null);
      // 集团本位币金额精度
      scale.setGroupLocMnyCtlInfo(groupmnykeys, posEnum, null);
      // 换算率
      scale.setHslCtlInfo(changeRates, PosEnum.body, null);
      // 汇率精度处理
      this.setOrgExchange(scale);
      this.setGroupExchange(scale);
      this.setGlobalExchange(scale);

      // 表头合计金额－根据公共需求2011.9.7走模板精度
      // 表头合计数量的精度钟老大和孙宝前已经确定抹零处理（金额合计一般都会按币种处理，特殊需要抹零的请各个确认）。
      scale.setMnyCtlInfo(headMnykeys, PosEnum.head, null,
          InvoiceHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
    }
    // 进行计算
    scale.process();
    if (totalScale != null) {
      // 表头合计
      String[] totalKeys = new String[] {
        InvoiceHeaderVO.NTOTALASTNUM
      };
      totalScale.setHeadTailKeys(totalKeys);
    }
  }
}
