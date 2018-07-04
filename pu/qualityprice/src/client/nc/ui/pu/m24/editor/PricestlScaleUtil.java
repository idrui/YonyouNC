package nc.ui.pu.m24.editor;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pu.m24.entity.PricestlHeaderVO;
import nc.vo.pu.m24.entity.PricestlItemBVO;
import nc.vo.pu.m24.entity.PricestlItemVO;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格结算单精度设置
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-8-3 上午10:21:15
 */
public class PricestlScaleUtil {

  /**
   * 方法功能描述：卡片精度设置。
   * <p>
   * <b>参数说明</b>
   * 
   * @param billform <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-6 上午09:07:30
   */
  public void orgChgScale(ShowUpableBillForm billform) {
    String pk_group = billform.getModel().getContext().getPk_group();
    BillCardPanel bcp = billform.getBillCardPanel();
    this.setScale(new CardPaneScaleProcessor(pk_group, bcp));
  }

  /**
   * 方法功能描述：列表精度设置。
   * <p>
   * <b>参数说明</b>
   * 
   * @param billform <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-6 上午09:07:30
   */
  public void setListScale(BillListPanel blp, String pk_group) {
    this.setScale(new ListPaneScaleProcessor(pk_group, blp));
  }

  /**
   * 方法功能描述：VO精度设置。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_group
   * @param bills <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-6 上午09:08:04
   */
  public void setVOScale(String pk_group, PricestlVO[] bills) {
    this.setScale(new BillVOScaleProcessor(pk_group, bills));
  }

  private void setScale(BillScaleProcessor scale) {

    // 本币价税合计
    String[] mnykeys = new String[] {
      PricestlItemVO.NTAXMNY
    };
    // 计算结果
    String[] standCalValue = new String[] {
        PricestlItemBVO.NSTANDCALVALUE
    };
    // 总计算结果
    String[] calValue = new String[] {
        PricestlItemVO.NSCHEMECALVALUE
    };
    // 业务单位数量
    String[] assistNumkeys =
        new String[] {
          PricestlItemVO.NASTARRVNUM, PricestlItemVO.NASTINNUM,
          PricestlItemVO.NASTSHOULDINNUM
        };
    // 主数量
    String[] numkeys =
        new String[] {
          PricestlItemVO.NARRVNUM, PricestlItemVO.NINNUM,
          PricestlItemVO.NSHOULDINNUM
        };
    // 价格
    String[] pricekeys =
        new String[] {
          PricestlItemVO.NTAXPRICE, PricestlItemVO.NBASEPRICE,
          PricestlItemVO.NBASETAXPRICE, PricestlItemVO.NCALQUALPRICE,
          PricestlItemVO.NPRICE, PricestlItemVO.DBASEPRICE
        };
    // 表尾价格
    String[] tailPriceKeys = new String[] {
      PricestlItemVO.DBASEPRICE
    };

    // 换算率
    String[] changeRates = new String[] {
      PricestlItemVO.VCHANGERATE,
    };
    // 表体税率
    String[] taxRateKey_B = new String[] {
      PricestlItemVO.NTAXRATE
    };
    // 表体税率
    scale.setTaxRateCtlInfo(taxRateKey_B, PosEnum.body, null);
    // 换算率精度
    scale.setHslCtlInfo(changeRates, PosEnum.body, null);
    // 业务单位数量精度
    scale.setNumCtlInfo(assistNumkeys, PosEnum.body, null,
        PricestlItemVO.CASTUNITID, PosEnum.body, null);
    // 主单位数量精度
    scale.setNumCtlInfo(numkeys, PosEnum.body, null, PricestlItemVO.CUNITID,
        PosEnum.body, null);
    // 本币金额精度
    scale.setMnyCtlInfo(mnykeys, PosEnum.body, null,
        PricestlHeaderVO.CCURRENCYID, PosEnum.head, null);
    // 单价精度
    scale.setPriceCtlInfo(pricekeys, PosEnum.body, null,
        PricestlHeaderVO.CCURRENCYID, PosEnum.head, null);
    // 表尾价格精度
    scale.setPriceCtlInfo(tailPriceKeys, PosEnum.tail, null,
        PricestlHeaderVO.CCURRENCYID, PosEnum.tail, null);
    // 计算结果
    scale.setPriceCtlInfo(standCalValue, PosEnum.body, StockOrg.BODY_HQHP,
        PricestlHeaderVO.CCURRENCYID, PosEnum.head, null);
    // 总计算结果
    scale.setPriceCtlInfo(calValue, PosEnum.tail, null,
        PricestlHeaderVO.CCURRENCYID, PosEnum.head, null);
    // 进行计算
    scale.process();
  }

}
