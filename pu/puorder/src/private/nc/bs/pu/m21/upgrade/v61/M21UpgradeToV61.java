package nc.bs.pu.m21.upgrade.v61;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.pu.m21.alert.PriceCalculateTaskReg;
import nc.bs.pu.m21.maintain.rule.save.OrderDirectPurchaseUpdateRule;
import nc.bs.pu.m21.upgrade.v61.rule.OrderVoStatusUpdateRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.scmmm.upgrade.scmpub.BillPageUpgrade;
import nc.scmmm.upgrade.scmpub.VODefaultValueUpgrade;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.rule.upgrade.TaxtypeFlagUpgradeRule;
import nc.vo.pu.pub.rule.upgrade.VATValueUpgrade;
import nc.vo.pubapp.AppContext;
import nc.vo.scmpub.vatupdate.VatFieldEnum;
import nc.vo.scmpub.vatupdate.VatUpdateProcess;

/**
 * 采购订单v61升级入口类
 * 
 * @since 6.0
 * @version 2012-2-29 下午04:44:26
 * @author tianft
 */
public class M21UpgradeToV61 {

  public void doAfterUpdateDataFrom60To61() {
    // 数据升级
    this.doDataUpgrade();
    // 采购订单询价预警设置升级
    this.RegPriceCalculateTask();
  }

  /**
   * 数据升级
   */
  private void doDataUpgrade() {
    // 公共vat升级处理，国家，税码，购销类型等
    this.upgradeDataByVatProcessor();
    // vo级的分页处理升级
    this.upgradeDataByBillPage();
    // 将表头默认为null的参照设置为~，表体的用upgradeDataByVatProcessor升级
    this.updateHeadNull();
  }

  private void RegPriceCalculateTask() {

    // 使用场景：在升级采购模块的时候注册询价相关的预警信息，因为有些系统是6.1之前的，而采购询价的算法是6.1才出来，
    // 所以要考虑那些老系统支持采购询价的新算法，就需要在这里进行支持
    PriceCalculateTaskReg priceCalculateTaskReg =
        new PriceCalculateTaskReg(AppContext.getInstance().getPkGroup());

    priceCalculateTaskReg.regMonthlyAlert();
    priceCalculateTaskReg.regDailyAlert();
  }

  /**
   * 将表头默认为null的参照设置为~
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  private void updateHeadNull() {
    String[] arrs = new String[] {
      OrderHeaderVO.CTRADEWORDID
    };
    new VODefaultValueUpgrade<OrderHeaderVO>(OrderHeaderVO.class).upValue(arrs);
  }

  /**
   * vo级的分页处理升级
   */
  private void upgradeDataByBillPage() {
    BillPageUpgrade<OrderVO> billUpgrade =
        new BillPageUpgrade<OrderVO>(OrderVO.class);
    List<IRule<OrderVO>> rules = new ArrayList<IRule<OrderVO>>();
    // 扣税类别升级
    rules.add(new TaxtypeFlagUpgradeRule<OrderVO>(OrderHeaderVO.FHTAXTYPEFLAG,
        OrderHeaderVO.NHTAXRATE));
    // VAT金额税率升级
    rules.add(new VATValueUpgrade<OrderVO>());
    // 2. 直运升级
    rules.add(new OrderDirectPurchaseUpdateRule());
    // 3.单据vo状态更新
    rules.add(new OrderVoStatusUpdateRule());
    billUpgrade.doUpgrade(null, rules);
  }

  /**
   * 公共vat升级处理，国家，税码，购销类型等
   */
  private void upgradeDataByVatProcessor() {
    VatUpdateProcess sqlUpdate = new VatUpdateProcess();
    // 购销类型-国内采购
    sqlUpdate.setExpressValue(VatFieldEnum.FBUYSELLFLAG,
        BuySellFlagEnum.NATIONAL_BUY.value().toString());
    Map<String, String> moreExpress = new HashMap<String, String>();
    // 体：需要更新成~
    // corigcountryid原产国
    // corigareaid原产地区
    // cdesticountryid目的地国
    // cdestiareaid目的地地区
    // casscustid客户
    // cprojecttaskid项目任务
    moreExpress.put(OrderItemVO.CORIGCOUNTRYID, "'~'");
    moreExpress.put(OrderItemVO.CORIGAREAID, "'~'");
    moreExpress.put(OrderItemVO.CDESTICOUNTRYID, "'~'");
    moreExpress.put(OrderItemVO.CDESTIAREAID, "'~'");
    moreExpress.put(OrderItemVO.CASSCUSTID, "'~'");
    moreExpress.put(OrderItemVO.CPROJECTTASKID, "'~'");

    // 三角贸易，国家相关
    sqlUpdate.processVatUpdate(PUEntity.M21_B_TABLE, new VatFieldEnum[] {
      VatFieldEnum.BTRIATRADEFLAG, VatFieldEnum.CSENDCOUNTRYID,
      VatFieldEnum.CTAXCOUNTRYID, VatFieldEnum.CRECECOUNTRYID,
      VatFieldEnum.FBUYSELLFLAG
    }, moreExpress);
    // 税码升级
    sqlUpdate.processTaxCodeUpdate(PUEntity.M21_B_TABLE,
        OrderItemVO.CTAXCODEID, OrderItemVO.PK_MATERIAL);
  }
}
