package nc.bs.pu.m4t.upgrade.v61;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.scmmm.upgrade.scmpub.BillPageUpgrade;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.rule.InitialEstAffectCostFlagRule;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.rule.upgrade.VatUpgradeUtil;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.vatupdate.VatFieldEnum;
import nc.vo.scmpub.vatupdate.VatUpdateProcess;

/**
 * 期初暂估单V61升级
 * 
 * @since 6.0
 * @version 2012-2-27 下午04:53:01
 * @author wuxla
 */
public class M4TUpgradeFor61 {
  /**
   * 期初暂估单V61升级
   * <p>
   * 使用场景：
   * <ul>
   * <li>v61
   * </ul>
   */
  public void doAfterUpdateDataFrom60To61() {
    List<IRule<InitialEstVO>> list = new ArrayList<IRule<InitialEstVO>>();
    // 更新表体影响成本标志
    list.add(new InitialEstAffectCostFlagRule());
    // // 扣税类别
    // list.add(new TaxtypeFlagUpgradeRule<InitialEstVO>());
    // // VAT
    // list.add(new M4TUpgradeVATRule());
    // list.add(new VATValueUpgrade<InitialEstVO>());
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and " + PUEntity.M4T_H_TAB + "." + InitialEstHeaderVO.DR, 0);
    new BillPageUpgrade<InitialEstVO>(InitialEstVO.class).doUpgrade(
        sql.toString(), list);

    this.vatUpgrade();
  }

  private void vatUpgrade() {
    VatFieldEnum[] fieldEnums =
        new VatFieldEnum[] {
          VatFieldEnum.FTAXTYPEFLAG, VatFieldEnum.CSENDCOUNTRYID,
          VatFieldEnum.CRECECOUNTRYID, VatFieldEnum.CTAXCOUNTRYID,
          VatFieldEnum.FBUYSELLFLAG, VatFieldEnum.BTRIATRADEFLAG,
          VatFieldEnum.BOPPTAXFLAG, VatFieldEnum.NNOSUBTAXRATE,
          VatFieldEnum.NNOSUBTAX, VatFieldEnum.NCALCOSTMNY,
          VatFieldEnum.NCALTAXMNY
        };
    VatUpdateProcess pro = new VatUpdateProcess();
    pro.setExpressValue(VatFieldEnum.FBUYSELLFLAG, BuySellFlagEnum.NATIONAL_BUY
        .value().toString());
    VatUpgradeUtil taxtype = new VatUpgradeUtil();
    pro.setExpressValue(VatFieldEnum.FTAXTYPEFLAG,
        taxtype.getTaxtypeFlagValue());
    pro.setExpressValue(VatFieldEnum.NCALTAXMNY, taxtype.getNcaltaxmnyValue());
    // 本币无税金额
    pro.setExpressValue(VatFieldEnum.NCALCOSTMNY, InitialEstItemVO.NMNY);
    Map<String, String> moreExpress = new HashMap<String, String>();
    moreExpress.put(InitialEstItemVO.NTAXRATE, taxtype.getTaxrateValue());
    moreExpress.put(InitialEstItemVO.NESTCALCOSTPRICE, InitialEstItemVO.NPRICE);
    // 默认为null的新增参照设置为~
    moreExpress.put(InitialEstItemVO.CASSCUSTID, "'~'");

    String message = "upgrade[" + PUEntity.M4T_B_TAB + "] vat value start.";
    Log.debug(message);

    pro.processVatUpdate(PUEntity.M4T_B_TAB, fieldEnums, moreExpress);

    message = "upgrade[" + PUEntity.M4T_B_TAB + "] vat value end.";
    Log.debug(message);

    message = "upgrade[" + PUEntity.M4T_B_TAB + "] vat taxcode value start.";
    Log.debug(message);

    pro.processTaxCodeUpdate(PUEntity.M4T_B_TAB, InitialEstItemVO.CTAXCODEID,
        InitialEstItemVO.PK_MATERIAL);

    message = "upgrade[" + PUEntity.M4T_B_TAB + "] vat taxcode value end.";
    Log.debug(message);
  }
}
