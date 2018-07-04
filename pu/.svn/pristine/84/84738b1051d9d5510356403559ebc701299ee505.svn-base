package nc.bs.pu.est.upgrade.v61;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.jdbc.framework.util.DBConsts;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.m4203.entity.SubcontinFIItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.enumeration.EnumDiscounttaxtype;
import nc.vo.pu.pub.rule.upgrade.VatUpgradeUtil;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.DBHintConstantValue;
import nc.vo.scmpub.vatupdate.VatFieldEnum;
import nc.vo.scmpub.vatupdate.VatUpdateProcess;

/**
 * 暂估V61升级
 * 
 * @since 6.0
 * @version 2012-3-29 上午09:42:27
 * @author wuxla
 */
public class EstUpgradeFor61 {
  /**
   * 暂估V61升级
   * <p>
   * 使用场景：
   * <ul>
   * <li>暂估V61升级
   * </ul>
   */
  public void doAfterUpdateDataFrom60To61() {
    // this.upgradeStockps();
    this.upgradePurchaseEst();

    this.upgradeVmiEst();

    this.upgradeSubFiItemNull();
  }

  private void upgradeFeeEst(String tablename) {
    VatFieldEnum[] fieldEnums =
        new VatFieldEnum[] {
          VatFieldEnum.FTAXTYPEFLAG, VatFieldEnum.CSENDCOUNTRYID,
          VatFieldEnum.CRECECOUNTRYID, VatFieldEnum.CTAXCOUNTRYID,
          VatFieldEnum.FBUYSELLFLAG, VatFieldEnum.BTRIATRADEFLAG,
          VatFieldEnum.BOPPTAXFLAG, VatFieldEnum.NNOSUBTAXRATE,
          VatFieldEnum.NNOSUBTAX, VatFieldEnum.NCALTAXMNY,
          VatFieldEnum.NCALCOSTMNY
        };

    // 国家/地区：全部升级为中国大陆
    // 不可抵扣税率、不可抵扣税额：均升级为0。
    // 三角贸易：否
    // 逆向征税：升级为否。
    VatUpdateProcess pro = new VatUpdateProcess();
    // 购销类型：国内采购
    pro.setExpressValue(VatFieldEnum.FBUYSELLFLAG, BuySellFlagEnum.NATIONAL_BUY
        .value().toString());
    // 原来即按内含方式处理的，全部升级为“应税内含”
    pro.setExpressValue(VatFieldEnum.FTAXTYPEFLAG, EnumDiscounttaxtype.TAXIN
        .toInteger().toString());
    // 因为扣税类别是“应税内含”，应税内含：本币价税合计
    pro.setExpressValue(VatFieldEnum.NCALTAXMNY, FeeEstVO.NESTTOTALMNY);

    // 计成本金额：升级为本币无税金额。
    pro.setExpressValue(VatFieldEnum.NCALCOSTMNY, FeeEstVO.NESTMNY);

    String message = "upgrade[" + tablename + "] vat value start.";
    TimeLog.info(message);

    pro.processVatUpdate(tablename, fieldEnums);

    message = "upgrade[" + tablename + "] vat value end.";
    TimeLog.info(message);

    message = "upgrade[" + tablename + "] vat taxcode value start.";
    TimeLog.info(message);

    pro.processTaxCodeUpdate(tablename, FeeEstVO.CTAXCODEID,
        FeeEstVO.PK_FEEMATERIAL);

    message = "upgrade[" + tablename + "] vat taxcode value end.";
    TimeLog.info(message);
  }

  /**
   * 采购暂估升级
   * 货物暂估税码在升级副本时已经升级，不需要再升级
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  private void upgradePurchaseEst() {
    this.upgradePurchaseHead();
    this.upgradePurchaseGoodsEst();
    this.upgradeFeeEst(PUEntity.PurchaseinFI_Fee_TAB);
  }

  private void upgradePurchaseGoodsEst() {
    VatFieldEnum[] fieldEnums =
        new VatFieldEnum[] {
          VatFieldEnum.FTAXTYPEFLAG, VatFieldEnum.NNOSUBTAXRATE,
          VatFieldEnum.NNOSUBTAX, VatFieldEnum.NCALCOSTMNY,
          VatFieldEnum.NCALTAXMNY
        };
    VatUpdateProcess pro = new VatUpdateProcess();
    // 不可抵扣税率、不可抵扣税额：均升级为0,逆向征税用库存的逆向征税，升级副本时升级
    pro.setFieldName(VatFieldEnum.NNOSUBTAXRATE, GoodsEstVO.NESTNOSUBTAXRATE);
    pro.setFieldName(VatFieldEnum.NNOSUBTAX, GoodsEstVO.NESTNOSUBTAX);

    VatUpgradeUtil taxtype = new VatUpgradeUtil();
    taxtype.setFtaxtypeflag(GoodsEstVO.FESTTAXTYPE);
    taxtype.setNmny(GoodsEstVO.NESTMNY);
    taxtype.setNtaxmny(GoodsEstVO.NESTTOTALMNY);
    taxtype.setNtaxrate(GoodsEstVO.NESTTAXRATE);

    // 计税金额
    // 应税内含：本币价税合计
    // 应税外加：本币无税金额
    pro.setFieldName(VatFieldEnum.NCALTAXMNY, GoodsEstVO.NESTCALTAXMNY);
    pro.setExpressValue(VatFieldEnum.NCALTAXMNY, taxtype.getNcaltaxmnyValue());

    // 计成本金额：升级为本币无税金额。
    pro.setFieldName(VatFieldEnum.NCALCOSTMNY, GoodsEstVO.NESTCALCOSTMNY);
    pro.setExpressValue(VatFieldEnum.NCALCOSTMNY, GoodsEstVO.NESTMNY);

    // 扣税类别:如果为应税外加、应税内含：仍保留原来的扣税类别。
    // 如果为不计税，升级为应税外加，同时税率升为0；
    pro.setFieldName(VatFieldEnum.FTAXTYPEFLAG, GoodsEstVO.FESTTAXTYPE);
    pro.setExpressValue(VatFieldEnum.FTAXTYPEFLAG,
        taxtype.getTaxtypeFlagValue());
    Map<String, String> moreExpress = new HashMap<String, String>();
    moreExpress.put(GoodsEstVO.NESTTAXRATE, taxtype.getTaxrateValue());
    moreExpress.put(PurchaseinFIItemVO.CASSCUSTID, "'~'");
    moreExpress.put(PurchaseinFIItemVO.CPROJECTTASKID, "'~'");
    moreExpress.put(GoodsEstVO.NNOSUBTAXRATE, "0");
    moreExpress.put(GoodsEstVO.NNOSUBTAX, "0");
    moreExpress.put(GoodsEstVO.BOPPTAXFLAG, "'N'");
    moreExpress.put(PurchaseinFIItemVO.CORIGCOUNTRYID, "'~'");
    moreExpress.put(PurchaseinFIItemVO.CORIGAREAID, "'~'");
    moreExpress.put(PurchaseinFIItemVO.CDESTICOUNTRYID, "'~'");
    moreExpress.put(PurchaseinFIItemVO.CDESTIAREAID, "'~'");
    moreExpress.put(GoodsEstVO.NCALCOSTMNY, GoodsEstVO.NMNY);
    
    //暂估记成本单价
    moreExpress.put(GoodsEstVO.NESTCALCOSTPRICE, GoodsEstVO.NESTPRICE);

    VatUpgradeUtil m45VatUtil = new VatUpgradeUtil();
    moreExpress.put(GoodsEstVO.NCALTAXMNY, m45VatUtil.getNcaltaxmnyValue());
    moreExpress.put(PurchaseinFIItemVO.FTAXTYPEFLAG,
        m45VatUtil.getTaxtypeFlagValue());
    moreExpress
        .put(PurchaseinFIItemVO.NCALCOSTPRICE, PurchaseinFIItemVO.NPRICE);
    moreExpress.put(PurchaseinFIItemVO.NTAXRATE, m45VatUtil.getTaxrateValue());

    // 必须先执行暂估税码，再执行入库单税码，或者相反。
    moreExpress.put(GoodsEstVO.CTAXCODEID, GoodsEstVO.CESTTAXCODEID);
    String message =
        "upgrade[" + PUEntity.PurchaseinFI_B_TAB + "] vat taxcode value start.";
    TimeLog.info(message);
    pro.processTaxCodeUpdate(PUEntity.PurchaseinFI_B_TAB,
        GoodsEstVO.CESTTAXCODEID, GoodsEstVO.PK_MATERIAL);
    // 入库单税码和暂估税码应该是一致的。
    // pro.processTaxCodeUpdate(PUEntity.PurchaseinFI_B_TAB,
    // GoodsEstVO.CTAXCODEID, GoodsEstVO.PK_MATERIAL);
    message =
        "upgrade[" + PUEntity.PurchaseinFI_B_TAB + "] vat taxcode value end.";
    TimeLog.info(message);

    message = "upgrade[" + PUEntity.PurchaseinFI_B_TAB + "] vat value start.";
    TimeLog.info(message);

    pro.processVatUpdate(PUEntity.PurchaseinFI_B_TAB, fieldEnums, moreExpress);

    message = "upgrade[" + PUEntity.PurchaseinFI_B_TAB + "] vat value end.";
    TimeLog.info(message);

  }

  private void upgradePurchaseHead() {
    VatFieldEnum[] fieldEnums =
        new VatFieldEnum[] {
          VatFieldEnum.CSENDCOUNTRYID, VatFieldEnum.CRECECOUNTRYID,
          VatFieldEnum.CTAXCOUNTRYID, VatFieldEnum.FBUYSELLFLAG,
          VatFieldEnum.BTRIATRADEFLAG
        };

    Map<String, String> moreExpress = new HashMap<String, String>();
    moreExpress.put(PurchaseinFIHeaderVO.CSENDTYPEID, "'~'");

    VatUpdateProcess pro = new VatUpdateProcess();
    // 购销类型：国内采购
    pro.setExpressValue(VatFieldEnum.FBUYSELLFLAG, BuySellFlagEnum.NATIONAL_BUY
        .value().toString());
    pro.processVatUpdate(PUEntity.PurchaseinFI_H_TAB, fieldEnums, moreExpress);

  }

  /**
   * 暂估升级
   * <p>
   * 使用场景：
   * <ul>
   * <li>结算副本升级:入库单先升级，所以直接取值
   * </ul>
   */
  // private void upgradeStockps() {
  // List<IRule<PurchaseinFIVO>> list = new ArrayList<IRule<PurchaseinFIVO>>();
  // list.add(new EstUpgradeStockPsRule());
  // // 确认成本单价
  // list.add(new CalCostPriceRule());
  // SqlBuilder sql = new SqlBuilder();
  // sql.append(
  // " and " + PUEntity.PurchaseinFI_H_TAB + "." + PuAttrNameEnum.dr.name(),
  // 0);
  //
  // new BillPageUpgrade<PurchaseinFIVO>(PurchaseinFIVO.class).doUpgrade(
  // sql.toString(), list);
  //
  // }

  private void upgradeSubFiItemNull() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" update ");
    sql.append(DBHintConstantValue.getHintCode(PUEntity.SUBCONTIN_B_TAB));
    sql.append(PUEntity.SUBCONTIN_B_TAB);
    sql.append(" set ");
    sql.append(SubcontinFIItemVO.CASSCUSTID, DBConsts.NULL_WAVE);
    sql.append(" where ");
    sql.append(SubcontinFIItemVO.DR, 0);
    new DataAccessUtils().update(sql.toString());
  }

  /**
   * 消耗汇总暂估升级
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  private void upgradeVmiEst() {

    this.upgradeVmiGoodsEst();
    this.upgradeFeeEst(PUEntity.VMIFI_Fee_TAB);
  }

  private void upgradeVmiGoodsEst() {
    VatFieldEnum[] fieldEnums =
        new VatFieldEnum[] {
          VatFieldEnum.FTAXTYPEFLAG, VatFieldEnum.CSENDCOUNTRYID,
          VatFieldEnum.CRECECOUNTRYID, VatFieldEnum.CTAXCOUNTRYID,
          VatFieldEnum.FBUYSELLFLAG, VatFieldEnum.BTRIATRADEFLAG,
          VatFieldEnum.BOPPTAXFLAG, VatFieldEnum.NNOSUBTAXRATE,
          VatFieldEnum.NNOSUBTAX, VatFieldEnum.NCALTAXMNY,
          VatFieldEnum.NCALCOSTMNY
        };

    // 国家/地区：全部升级为中国大陆。
    // 三角贸易：否
    // 逆向征税：否
    VatUpdateProcess pro = new VatUpdateProcess();
    pro.setFieldName(VatFieldEnum.NNOSUBTAXRATE, GoodsEstVO.NESTNOSUBTAXRATE);
    pro.setFieldName(VatFieldEnum.NNOSUBTAX, GoodsEstVO.NESTNOSUBTAX);
    // 购销类型：国内采购
    pro.setExpressValue(VatFieldEnum.FBUYSELLFLAG, BuySellFlagEnum.NATIONAL_BUY
        .value().toString());

    VatUpgradeUtil taxtype = new VatUpgradeUtil();
    taxtype.setFtaxtypeflag(GoodsEstVO.FESTTAXTYPE);
    taxtype.setNmny(GoodsEstVO.NESTMNY);
    taxtype.setNtaxmny(GoodsEstVO.NESTTOTALMNY);
    taxtype.setNtaxrate(GoodsEstVO.NESTTAXRATE);

    // 如果为应税外加、应税内含：仍保留原来的扣税类别。
    // 如果为不计税，升级为应税外加，同时税率升为0；
    pro.setFieldName(VatFieldEnum.FTAXTYPEFLAG, GoodsEstVO.FESTTAXTYPE);
    pro.setExpressValue(VatFieldEnum.FTAXTYPEFLAG,
        taxtype.getTaxtypeFlagValue());

    // 计税金额
    pro.setFieldName(VatFieldEnum.NCALTAXMNY, GoodsEstVO.NESTCALTAXMNY);
    pro.setExpressValue(VatFieldEnum.NCALTAXMNY, taxtype.getNcaltaxmnyValue());

    // 计成本金额
    pro.setFieldName(VatFieldEnum.NCALCOSTMNY, GoodsEstVO.NESTCALCOSTMNY);
    pro.setExpressValue(VatFieldEnum.NCALCOSTMNY, GoodsEstVO.NESTMNY);

    Map<String, String> moreExpress = new HashMap<String, String>();
    moreExpress.put(GoodsEstVO.NESTTAXRATE, taxtype.getTaxrateValue());
    moreExpress.put(VmiFIHeaderVO.CASSCUSTID, "'~'");
    
    //暂估记成本单价
    moreExpress.put(VmiFIHeaderVO.NESTCALCOSTPRICE, VmiFIHeaderVO.NESTPRICE);

    String message = "upgrade[" + PUEntity.VMIFI_H_TAB + "] vat value start.";
    TimeLog.info(message);

    pro.processVatUpdate(PUEntity.VMIFI_H_TAB, fieldEnums, moreExpress);

    message = "upgrade[" + PUEntity.VMIFI_H_TAB + "] vat value end.";
    TimeLog.info(message);

    message = "upgrade[" + PUEntity.VMIFI_H_TAB + "] vat taxcode value start.";
    TimeLog.info(message);

    pro.processTaxCodeUpdate(PUEntity.VMIFI_H_TAB, GoodsEstVO.CESTTAXCODEID,
        GoodsEstVO.PK_MATERIAL);

    message = "upgrade[" + PUEntity.VMIFI_H_TAB + "] vat taxcode value end.";
    TimeLog.info(message);
  }

}
