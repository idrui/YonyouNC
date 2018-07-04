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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置卡片精度
 * <li>设置列表精度
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-20 下午03:29:50
 */
public class ConfirmScaleSetter {
  private String pk_group;

  public ConfirmScaleSetter(String pk_group) {
    this.pk_group = pk_group;
  }

  /**
   * 方法功能描述：设置卡片界面精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel
   *          卡片
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-5-17 下午03:35:01
   */
  public void setCardScale(BillCardPanel panel) {
    this.setBodyScale(new CardPaneScaleProcessor(this.pk_group, panel));
    this.setHeadScale(new TotalValueScaleProcessor(panel));
  }

  /**
   * 方法功能描述：设置列表界面精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel
   *          列表
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-5-17 下午03:35:17
   */
  public void setListScale(BillListPanel panel) {
    this.setBodyScale(new ListPaneScaleProcessor(this.pk_group, panel));
    this.setHeadScale(new TotalValueScaleProcessor(panel));
  }

  private void setBodyScale(BillScaleProcessor scale) {
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
    // 表体税率
    String[] taxRateKey_B = new String[] {
      OrderItemVO.NTAXRATE, OrderItemVO.NNOSUBTAXRATE
    };

    // 本币金额
    String[] mnykeys =
        new String[] {
          OrderItemVO.NMNY, OrderItemVO.NTAXMNY, OrderItemVO.NTAX,
          OrderItemVO.NACCCANCELINVMNY, OrderItemVO.NACCUMINVOICEMNY,
          OrderItemVO.NFEEMNY
        };
    // 报价单位数量
    String[] quoteNumkeys = new String[] {
      OrderItemVO.NQTUNITNUM
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
          OrderItemVO.NCONFIRMNUM, OrderItemVO.NNUM,
        };
    // 原币价格
    String[] OcurrPricekeys =
        new String[] {
          OrderItemVO.NORIGNETPRICE, OrderItemVO.NORIGPRICE,
          OrderItemVO.NORIGTAXNETPRICE, OrderItemVO.NORIGTAXPRICE,
          OrderItemVO.NQTORIGNETPRICE, OrderItemVO.NQTORIGPRICE,
          OrderItemVO.NQTORIGTAXNETPRC, OrderItemVO.NQTORIGTAXPRICE
        };
    // 本币价格
    String[] CurrPriceKeys =
        new String[] {
          OrderItemVO.NNETPRICE, OrderItemVO.NQTNETPRICE,
          OrderItemVO.NQTTAXNETPRICE, OrderItemVO.NTAXNETPRICE
        };
    // 原币金额
    String[] orgmnykeys = new String[] {
      OrderItemVO.NORIGMNY, OrderItemVO.NORIGTAXMNY, OrderItemVO.NCONFIRMMNY
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

    // 税率
    scale.setTaxRateCtlInfo(taxRateKey_B, PosEnum.body, null);

    // 成本价格字段精度
    scale.setCostPriceCtlInfo(costpricekeys, PosEnum.body, null);
    // 全局本位币金额精度
    scale.setGlobalLocMnyCtlInfo(globalmnykeys, PosEnum.body, null);
    // 集团本位币金额精度
    scale.setGroupLocMnyCtlInfo(groupmnykeys, PosEnum.body, null);
    // 换算率精度
    scale.setHslCtlInfo(changeRates, PosEnum.body, null);
    // 报价单位数量精度
    scale.setNumCtlInfo(quoteNumkeys, PosEnum.body, null,
        OrderItemVO.CQTUNITID, PosEnum.body, null);
    // 业务单位数量精度
    scale.setNumCtlInfo(assistNumkeys, PosEnum.body, null,
        OrderItemVO.CASTUNITID, PosEnum.body, null);
    // 主单位数量精度
    scale.setNumCtlInfo(numkeys, PosEnum.body, null, OrderItemVO.CUNITID,
        PosEnum.body, null);
    // 本币金额精度
    scale.setMnyCtlInfo(mnykeys, PosEnum.body, null, OrderItemVO.CCURRENCYID,
        PosEnum.body, null);
    // 原币单价精度
    scale.setPriceCtlInfo(OcurrPricekeys, PosEnum.body, null,
        OrderHeaderVO.CORIGCURRENCYID, PosEnum.body, null);
    // 本币单价精度
    scale.setPriceCtlInfo(CurrPriceKeys, PosEnum.body, null,
        OrderItemVO.CCURRENCYID, PosEnum.body, null);
    // 原币金额精度
    scale.setMnyCtlInfo(orgmnykeys, PosEnum.body, null,
        OrderHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
    // 重量精度
    scale.setWeightCtlInfo(weightkeys, PosEnum.body, null);
    // 体积精度
    scale.setVolumnCtlInfo(volumnkeys, PosEnum.body, null);
    // 件数精度
    scale.setUnitCtlInfo(packkeys, PosEnum.body, null, OrderItemVO.PK_MATERIAL,
        PosEnum.body, null);
    // 进行计算
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
