/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-26 下午01:19:31
 */
package nc.ui.pu.est.util;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.vo.pu.est.util.EstVOUtil.ScaleKeyInfo;
import nc.vo.pu.est.util.EstVOUtil.StockScaleKeyInfo;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估的精度处理工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-26 下午01:19:31
 */
public class EstScaleUtil {
  private BillCardPanel bcp;

  private BillListPanel blp;

  private ScaleKeyInfo keyInfo;

  private String pk_group;

  private StockScaleKeyInfo stockScaleKeyInfo;

  /**
   * EstScaleUtil 的构造子
   * 
   * @param bcp
   * @param keyInfo
   * @param pk_group
   */
  public EstScaleUtil(BillCardPanel bcp, ScaleKeyInfo keyInfo, String pk_group) {
    this.bcp = bcp;
    this.keyInfo = keyInfo;
    this.pk_group = pk_group;
  }

  /**
   * EstScaleUtil 的构造子
   * 
   * @param blp
   * @param keyInfo
   * @param pk_group
   */
  public EstScaleUtil(BillListPanel blp, ScaleKeyInfo keyInfo, String pk_group) {
    this.blp = blp;
    this.keyInfo = keyInfo;
    this.pk_group = pk_group;
  }

  /**
   * 包含入库精度处理的构造子
   * 
   * @param blp
   * @param stockScaleKeyInfo
   * @param keyInfo
   * @param pk_group
   */
  public EstScaleUtil(BillListPanel blp, StockScaleKeyInfo stockScaleKeyInfo,
      String pk_group) {
    this.blp = blp;
    this.stockScaleKeyInfo = stockScaleKeyInfo;
    this.pk_group = pk_group;
  }

  /** 初始化费用暂估（列表表体）精度 **/
  public void initFeeScale() {
    BillScaleProcessor bsp =
        new CardPaneScaleProcessor(this.pk_group, this.bcp);
    // 本币金额精度
    bsp.setMnyCtlInfo(this.keyInfo.getMnyKeys(), PosEnum.body, null,
        this.keyInfo.getCurrencyKey(), PosEnum.body, null);
    // 原币金额精度
    bsp.setMnyCtlInfo(this.keyInfo.getOmnyKeys(), PosEnum.body, null,
        this.keyInfo.getOcurrencyKey(), PosEnum.body, null);

    // 税率精度
    bsp.setTaxRateCtlInfo(this.keyInfo.getTaxRateKey(), PosEnum.body, null);
    // 折本汇率精度
    FieldInfo[] fdInfos = this.keyInfo.getExchgRateKeyInfo();
    if (!ArrayUtils.isEmpty(fdInfos)) {
      bsp.setOrgExchangeCtlInfo(fdInfos[0], fdInfos[1], fdInfos[2], fdInfos[3]);
    }
    bsp.process();
  }

  /** 初始化货物暂估（列表表头）精度 **/
  public void initGoodsScale() {
    BillScaleProcessor bsp =
        new ListPaneScaleProcessor(this.pk_group, this.blp);
    // 主单位数量精度
    bsp.setNumCtlInfo(this.keyInfo.getNumKeys(), PosEnum.head, null,
        this.keyInfo.getunitKey(), PosEnum.head, null);
    // 单价精度(本币)
    bsp.setPriceCtlInfo(this.keyInfo.getCurrPriceKeys(), PosEnum.head, null,
        this.keyInfo.getCurrencyKey(), PosEnum.head, null);
    // 单价精度(原币)
    bsp.setPriceCtlInfo(this.keyInfo.getOcurrPriceKeys(), PosEnum.head, null,
        this.keyInfo.getOcurrencyKey(), PosEnum.head, null);
    // 本币金额精度
    bsp.setMnyCtlInfo(this.keyInfo.getMnyKeys(), PosEnum.head, null,
        this.keyInfo.getCurrencyKey(), PosEnum.head, null);
    // 原币金额精度
    bsp.setMnyCtlInfo(this.keyInfo.getOmnyKeys(), PosEnum.head, null,
        this.keyInfo.getOcurrencyKey(), PosEnum.head, null);
    // 税率精度
    bsp.setTaxRateCtlInfo(this.keyInfo.getTaxRateKey(), PosEnum.head, null);
    // 折本汇率精度
    FieldInfo[] fdInfos = this.keyInfo.getExchgRateKeyInfo();
    if (!ArrayUtils.isEmpty(fdInfos)) {
      bsp.setOrgExchangeCtlInfo(fdInfos[0], fdInfos[1], fdInfos[2], fdInfos[3]);
    }
    bsp.process();
  }

  public void initStockScale() {
    BillScaleProcessor bsp =
        new ListPaneScaleProcessor(this.pk_group, this.blp);
    // 主单位数量精度
    bsp.setNumCtlInfo(this.stockScaleKeyInfo.getNumKeys(), PosEnum.head, null,
        this.stockScaleKeyInfo.getunitKey(), PosEnum.head, null);
    // 辅单位数量精度
    bsp.setNumCtlInfo(this.stockScaleKeyInfo.getAstNumKey(), PosEnum.head,
        null, this.stockScaleKeyInfo.getAstUnitKey(), PosEnum.head, null);
    // 报价单位数量精度
    bsp.setNumCtlInfo(this.stockScaleKeyInfo.getQtNumKey(), PosEnum.head, null,
        this.stockScaleKeyInfo.getQtUnitKey(), PosEnum.head, null);
    // 单价精度(本币)
    bsp.setPriceCtlInfo(this.stockScaleKeyInfo.getCurrPriceKeys(),
        PosEnum.head, null, this.stockScaleKeyInfo.getCurrencyKey(),
        PosEnum.head, null);
    // 单价精度(原币)
    bsp.setPriceCtlInfo(this.stockScaleKeyInfo.getOcurrPriceKeys(),
        PosEnum.head, null, this.stockScaleKeyInfo.getOcurrencyKey(),
        PosEnum.head, null);
    if (!ArrayUtils.isEmpty(this.stockScaleKeyInfo.getMnyKeys())) {
      // 本币金额精度
      bsp.setMnyCtlInfo(this.stockScaleKeyInfo.getMnyKeys(), PosEnum.head,
          null, this.stockScaleKeyInfo.getCurrencyKey(), PosEnum.head, null);
    }
    if (!ArrayUtils.isEmpty(this.stockScaleKeyInfo.getOmnyKeys())) {
      // 原币金额精度
      bsp.setMnyCtlInfo(this.stockScaleKeyInfo.getOmnyKeys(), PosEnum.head,
          null, this.stockScaleKeyInfo.getOcurrencyKey(), PosEnum.head, null);
    }
    if (!ArrayUtils.isEmpty(this.stockScaleKeyInfo.getTaxRateKey())) {
      // 税率精度
      bsp.setTaxRateCtlInfo(this.stockScaleKeyInfo.getTaxRateKey(),
          PosEnum.head, null);
    }
    // 折本汇率精度
    FieldInfo[] fdInfos = this.stockScaleKeyInfo.getExchgRateKeyInfo();
    if (!ArrayUtils.isEmpty(fdInfos)) {
      bsp.setOrgExchangeCtlInfo(fdInfos[0], fdInfos[1], fdInfos[2], fdInfos[3]);
    }
    // 全局本币金额
    if (!ArrayUtils.isEmpty(this.stockScaleKeyInfo.getGlobalmnykeys())) {
      bsp.setGlobalLocMnyCtlInfo(this.stockScaleKeyInfo.getGlobalmnykeys(),
          PosEnum.head, null);
    }
    // 集团本币金额
    if (!ArrayUtils.isEmpty(this.stockScaleKeyInfo.getGroupmnykeys())) {
      bsp.setGroupLocMnyCtlInfo(this.stockScaleKeyInfo.getGroupmnykeys(),
          PosEnum.head, null);
    }
    // 全局本币汇率
    fdInfos = this.stockScaleKeyInfo.getGlobalExchgRateKeyInfo();
    if (!ArrayUtils.isEmpty(fdInfos)) {
      bsp.setGlobalExchangeCtlInfo(fdInfos[0], fdInfos[1], fdInfos[2]);
    }
    // 集团本币汇率
    fdInfos = this.stockScaleKeyInfo.getGroupExchgRateKeyInfo();
    if (!ArrayUtils.isEmpty(fdInfos)) {
      bsp.setGroupExchangeCtlInfo(fdInfos[0], fdInfos[1], fdInfos[2]);
    }
    bsp.process();
  }
}
