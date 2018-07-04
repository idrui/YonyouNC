package nc.bs.pu.m25.upgrade.v61;

import java.util.HashMap;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.rule.upgrade.VatUpgradeUtil;
import nc.vo.scmpub.vatupdate.VatFieldEnum;
import nc.vo.scmpub.vatupdate.VatUpdateProcess;

/**
 * 采购发票为升级到v61处理类
 * 
 * @since 6.0
 * @version 2012-3-6 上午10:51:16
 * @author tianft
 */
public class M25UpgradeForV61 {

  public void doAfterUpdateDataFrom60To61() {
    // 数据升级
    this.doDataUpgrade();
  }

  /**
   * 数据升级
   */
  private void doDataUpgrade() {
    this.upgradeDataByVatProcessor();
    // this.upgradeDataByBillPage();
    // this.updateItemNull();
  }

  /**
   * 新增字段默认为Null的参照设置为~
   * <p>
   * 使用场景：
   * <ul>
   * <li>新增字段默认为Null的参照设置为~
   * </ul>
   */
  // private void updateItemNull() {
  // String[] arrs = new String[] {
  // InvoiceItemVO.CASSCUSTID, InvoiceItemVO.CPROJECTTASKID
  // };
  // new
  // VODefaultValueUpgrade<InvoiceItemVO>(InvoiceItemVO.class).upValue(arrs);
  // }

  // private void upgradeDataByBillPage() {
  // BillPageUpgrade<InvoiceVO> billUpgrade =
  // new BillPageUpgrade<InvoiceVO>(InvoiceVO.class);
  // List<IRule<InvoiceVO>> rules = new ArrayList<IRule<InvoiceVO>>();
  // // 扣税类别升级
  // rules.add(new TaxtypeFlagUpgradeRule<InvoiceVO>(
  // InvoiceHeaderVO.FTAXTYPEFLAGH, InvoiceHeaderVO.NTAXRATEH));
  // // VAT金额税率升级
  // rules.add(new VATValueUpgrade<InvoiceVO>());
  // // 单据vo状态更新（升级必须加上，否则不更新）
  // rules.add(new VOStatusUpdateRule<InvoiceVO>());
  // billUpgrade.doUpgrade(" and dr = 0 ", rules);
  // }

  /**
   * 公共vat升级处理，国家，税码，购销类型等
   */
  private void upgradeDataByVatProcessor() {
    VatUpdateProcess sqlUpdate = new VatUpdateProcess();
    // 购销类型-国内采购
    sqlUpdate.setExpressValue(VatFieldEnum.FBUYSELLFLAG,
        BuySellFlagEnum.NATIONAL_BUY.value().toString());
    Map<String, String> moreExpress = new HashMap<String, String>();
    moreExpress.put(InvoiceHeaderVO.CTRADEWORDID, "'~'");
    // 三角贸易，国家相关,逆向征税
    sqlUpdate.processVatUpdate(PUEntity.M25_H_TAB, new VatFieldEnum[] {
      VatFieldEnum.BTRIATRADEFLAG, VatFieldEnum.CSENDCOUNTRYID,
      VatFieldEnum.CTAXCOUNTRYID, VatFieldEnum.CRECECOUNTRYID,
      VatFieldEnum.BOPPTAXFLAG, VatFieldEnum.FBUYSELLFLAG
    }, moreExpress);

    VatFieldEnum[] bodyfieldEnums =
        new VatFieldEnum[] {
          VatFieldEnum.FTAXTYPEFLAG, VatFieldEnum.NNOSUBTAXRATE,
          VatFieldEnum.NNOSUBTAX, VatFieldEnum.NCALCOSTMNY,
          VatFieldEnum.NCALTAXMNY
        };

    VatUpdateProcess bodyPro = new VatUpdateProcess();
    VatUpgradeUtil taxtype = new VatUpgradeUtil();
    bodyPro.setExpressValue(VatFieldEnum.FTAXTYPEFLAG,
        taxtype.getTaxtypeFlagValue());
    bodyPro.setExpressValue(VatFieldEnum.NCALTAXMNY,
        taxtype.getNcaltaxmnyValue());
    // 本币无税金额
    bodyPro.setExpressValue(VatFieldEnum.NCALCOSTMNY, InvoiceItemVO.NMNY);
    Map<String, String> moreBodyExpress = new HashMap<String, String>();
    moreExpress.put(InvoiceItemVO.NTAXRATE, taxtype.getTaxrateValue());
    moreExpress.put(InvoiceItemVO.CASSCUSTID, "'~'");
    moreExpress.put(InvoiceItemVO.CPROJECTTASKID, "'~'");

    bodyPro.processVatUpdate(PUEntity.M25_B_TAB, bodyfieldEnums,
        moreBodyExpress);

    // 税码升级
    sqlUpdate.processTaxCodeUpdate(PUEntity.M25_B_TAB,
        InvoiceItemVO.CTAXCODEID, InvoiceItemVO.PK_MATERIAL);
  }

}
