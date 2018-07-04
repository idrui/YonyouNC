/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-22 下午01:38:32
 */
package nc.vo.pu.m21.rule;

import nc.ui.pub.bill.IBillItem;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.rule.IScaleProcessor;
import nc.vo.pu.report.scale.PUPubMetaNameConst;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TotalValueScale;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单的精度处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-10-22 下午01:38:32
 */
public class OrderScaleRule implements IScaleProcessor {
  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param scale
   * @param totalScale <p>
   * @since 6.0
   * @author tianft
   * @time 2010-10-22 下午02:18:37
   */
  @Override
  public void setScale(BillScaleProcessor scale, TotalValueScale totalScale) {
    if (scale != null) {
      this.setBodyScale(scale);
    }
    if (totalScale != null) {
      this.setHeadScale(totalScale);
    }
  }

  /**
   * 精度检查需要的精度设置
   * 
   * @param scale
   */
  public void setScaleForCheck(BillScaleProcessor scale) {
    if (scale != null) {
      this.setScale(scale, PosEnum.body, true);
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

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param scale <p>
   * @since 6.0
   * @author tianft
   * @time 2010-10-22 下午02:18:13
   */
  private void setBodyScale(BillScaleProcessor scale) {
    this.setScale(scale, PosEnum.body);

  }

  private void setGlobalExchange(BillScaleProcessor scale, int pos) {
    FieldInfo rate = new FieldInfo(OrderItemVO.NGLOBALEXCHGRATE, pos, null);
    FieldInfo orgOrigCurr =
        new FieldInfo(OrderItemVO.CORIGCURRENCYID, IBillItem.HEAD, null);
    FieldInfo orgLocCurr = new FieldInfo(OrderItemVO.CCURRENCYID, pos, null);
    scale.setGlobalExchangeCtlInfo(rate, orgOrigCurr, orgLocCurr);
  }

  private void setGroupExchange(BillScaleProcessor scale, int pos) {
    FieldInfo rate = new FieldInfo(OrderItemVO.NGROUPEXCHGRATE, pos, null);
    FieldInfo orgOrigCurr =
        new FieldInfo(OrderItemVO.CORIGCURRENCYID, IBillItem.HEAD, null);
    FieldInfo orgLocCurr = new FieldInfo(OrderItemVO.CCURRENCYID, pos, null);
    scale.setGroupExchangeCtlInfo(rate, orgOrigCurr, orgLocCurr);
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param scale <p>
   * @since 6.0
   * @author tianft
   * @time 2010-10-22 下午02:18:29
   */
  private void setHeadScale(TotalValueScale scale) {
    scale.setHeadTailKeys(new String[] {
      OrderHeaderVO.NTOTALASTNUM, OrderHeaderVO.NTOTALPIECE,
      OrderHeaderVO.NTOTALVOLUME, OrderHeaderVO.NTOTALWEIGHT
    });
  }

  private void setOrgExchange(BillScaleProcessor scale, int pos) {
    FieldInfo rate = new FieldInfo(OrderItemVO.NEXCHANGERATE, pos, null);
    FieldInfo srcCurr =
        new FieldInfo(OrderHeaderVO.CORIGCURRENCYID, IBillItem.HEAD, null);
    FieldInfo destCurr = new FieldInfo(OrderItemVO.CCURRENCYID, pos, null);
    FieldInfo org = new FieldInfo(OrderItemVO.PK_PSFINANCEORG, pos, null);
    scale.setOrgExchangeCtlInfo(rate, srcCurr, destCurr, org);
  }

  private void setScale(BillScaleProcessor scale, PosEnum posEnum) {
    this.setScale(scale, posEnum, false);
  }

  private void setScale(BillScaleProcessor scale, PosEnum posEnum,
      boolean forScaleCheck) {
    // 成本单价
    String[] costpricekeys = new String[] {};
    // 全局本位币金额
    String[] globalmnykeys = new String[] {
      OrderItemVO.NGLOBALMNY, OrderItemVO.NGLOBALTAXMNY
    };
    // 集团本位币金额
    String[] groupmnykeys = new String[] {
      OrderItemVO.NGROUPMNY, OrderItemVO.NGROUPTAXMNY
    };
    // 换算率
    String[] changeRates = new String[] {
      OrderItemVO.VCHANGERATE, OrderItemVO.VQTUNITRATE
    };
    // 本币金额
    String[] mnykeys =
        new String[] {
          OrderItemVO.NMNY, OrderItemVO.NTAXMNY, OrderItemVO.NTAX,
          OrderItemVO.NACCCANCELINVMNY, OrderItemVO.NACCUMINVOICEMNY,
          OrderItemVO.NFEEMNY, OrderItemVO.NCALTAXMNY, OrderItemVO.NNOSUBTAX,
          OrderItemVO.NCALCOSTMNY
        };
    // 表头合计金额－根据公共需求2011.9.7走模板精度
    String[] headMnykeys = new String[] {
      OrderHeaderVO.NTOTALORIGMNY,OrderHeaderVO.NORGPREPAYLIMIT
    };
    // 报价单位数量
    String[] quoteNumkeys = new String[] {
      OrderItemVO.NQTUNITNUM, OrderItemVO.NSENDPLANNUM
    };
    // 业务单位数量
    String[] assistNumkeys = new String[] {
      OrderItemVO.NASTNUM
    };
    // 主数量
    String[] numkeys =
        new String[] {
          OrderItemVO.NACCUMARRVNUM, OrderItemVO.NACCUMDEVNUM,
          OrderItemVO.NACCUMINVOICENUM, OrderItemVO.NACCUMRPNUM,
          OrderItemVO.NACCUMSTORENUM, OrderItemVO.NACCUMWASTNUM,
          OrderItemVO.NBACKARRVNUM, OrderItemVO.NBACKSTORENUM,
          OrderItemVO.NCANARRIVENUM, OrderItemVO.NCANINVOICENUM,
          OrderItemVO.NCONFIRMNUM, OrderItemVO.NNUM, OrderItemVO.NCANINNUM
        };
    // 本币价格
    String[] CurrPricekeys =
        new String[] {
          OrderItemVO.NNETPRICE, OrderItemVO.NQTNETPRICE,
          OrderItemVO.NQTTAXNETPRICE, OrderItemVO.NTAXNETPRICE,
          OrderItemVO.NTAXPRICE, OrderItemVO.NPRICE, OrderItemVO.NQTPRICE,
          OrderItemVO.NQTTAXPRICE
        };
    // 原币价格
    String[] OcurrPricekeys =
        new String[] {
          OrderItemVO.NORIGNETPRICE, OrderItemVO.NORIGPRICE,
          OrderItemVO.NORIGTAXNETPRICE, OrderItemVO.NORIGTAXPRICE,
          OrderItemVO.NQTORIGNETPRICE, OrderItemVO.NQTORIGPRICE,
          OrderItemVO.NQTORIGTAXNETPRC, OrderItemVO.NQTORIGTAXPRICE
        };
    // 原币金额  
    // 可开票金额是订单到发票转单模板的新加字段，值为含税单价*可开票主数量
    String[] orgmnykeys = new String[] {
      OrderItemVO.NORIGMNY, OrderItemVO.NORIGTAXMNY, OrderItemVO.NNOPAYORGMNY, 
      PUPubMetaNameConst.NCANINVOICEMNY
    };
    // 重量
    String[] weightkeys = new String[] {
      OrderItemVO.NWEIGHT
    };
    // 体积
    String[] volumnkeys = new String[] {
      OrderItemVO.NVOLUMN
    };
    // 件数
    String[] packkeys = new String[] {
      OrderItemVO.NPACKNUM
    };

    // 表体税率
    String[] taxRateKey_B = new String[] {
      OrderItemVO.NTAXRATE, OrderItemVO.NNOSUBTAXRATE
    };
    // 表头税率
    String[] taxRateKey_H = new String[] {
      OrderHeaderVO.NHTAXRATE
    };
    // 表体折扣
    String[] discountKey_B = new String[] {
      OrderItemVO.NITEMDISCOUNTRATE
    };
    // 2012-05-24 tianft： 对于精度的检查可以不必那么全面，根据需要检查部分关键单价金额即可
    if (!forScaleCheck) {
      // 表体税率
      scale.setTaxRateCtlInfo(taxRateKey_B, posEnum, null);
      // 表头整单税率
      scale.setTaxRateCtlInfo(taxRateKey_H, PosEnum.head, null);
      // 表体折扣（和需求确认2011.6.27wangyf，走2位精度）
      scale.setTaxRateCtlInfo(discountKey_B, posEnum, null);
      // 全局本位币金额精度
      scale.setGlobalLocMnyCtlInfo(globalmnykeys, posEnum, null);
      // 集团本位币金额精度
      scale.setGroupLocMnyCtlInfo(groupmnykeys, posEnum, null);
      // 换算率精度
      scale.setHslCtlInfo(changeRates, posEnum, null);
      // 重量精度
      scale.setWeightCtlInfo(weightkeys, posEnum, null);
      // 体积精度
      scale.setVolumnCtlInfo(volumnkeys, posEnum, null);
      // 件数精度
      scale.setUnitCtlInfo(packkeys, posEnum, null, OrderItemVO.PK_MATERIAL,
          posEnum, null);
      // 汇率精度
      this.setOrgExchange(scale, PosEnum.body.equals(posEnum) ? IBillItem.BODY
          : IBillItem.HEAD);
      this.setGlobalExchange(scale,
          PosEnum.body.equals(posEnum) ? IBillItem.BODY : IBillItem.HEAD);
      this.setGroupExchange(scale,
          PosEnum.body.equals(posEnum) ? IBillItem.BODY : IBillItem.HEAD);

      // 表头合计金额－根据公共需求2011.9.7走模板精度
      // 2013.5.10表头合计数量的精度钟老大和孙宝前已经确定抹零处理（金额合计一般都会按币种处理，特殊需要抹零的请各个确认）。
      scale.setMnyCtlInfo(headMnykeys, PosEnum.head, null,
          OrderHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
    }
    // 成本价格字段精度
    scale.setCostPriceCtlInfo(costpricekeys, posEnum, null);
    // 报价单位数量精度
    scale.setNumCtlInfo(quoteNumkeys, posEnum, null, OrderItemVO.CQTUNITID,
        posEnum, null);
    // 业务单位数量精度
    scale.setNumCtlInfo(assistNumkeys, posEnum, null, OrderItemVO.CASTUNITID,
        posEnum, null);
    // 主单位数量精度
    scale.setNumCtlInfo(numkeys, posEnum, null, OrderItemVO.CUNITID, posEnum,
        null);
    // 本币金额精度
    scale.setMnyCtlInfo(mnykeys, posEnum, null, OrderItemVO.CCURRENCYID,
        posEnum, null);
    // 本币单价精度
    scale.setPriceCtlInfo(CurrPricekeys, posEnum, null,
        OrderItemVO.CCURRENCYID, posEnum, null);
    // 原币单价精度
    scale.setPriceCtlInfo(OcurrPricekeys, posEnum, null,
        OrderHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
    // 原币金额精度
    scale.setMnyCtlInfo(orgmnykeys, posEnum, null,
        OrderHeaderVO.CORIGCURRENCYID, PosEnum.head, null);

    // 进行计算
    scale.process();
  }
}
