/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-29 上午09:16:52
 */
package nc.vo.pu.est.entity;

import nc.vo.pu.est.rule.IEstPriceQueryInfoProvide;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>货物暂估顶层VO(表头VO)
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-29 上午09:16:52
 */
public abstract class GoodsEstVO extends AbstractDataView implements
    IEstPriceQueryInfoProvide {
  /** 影响成本标志 **/
  public static final String BAFFECTCOST = "baffectcost";

  /** 影响利润中心成本标志 */
  public static final String BAFFECTPCCOST = "baffectpccost";

  /** 是否普通采购 **/
  public static final String BNORMPUR = "bnormpur";

  /** 逆向征税 */
  public static final String BOPPTAXFLAG = "bopptaxflag";

  /** 是否结算完成 **/
  public static final String BSETTLEFINISH = "bsettlefinish";

  /** 三角贸易 */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /** 本位币 **/
  public static final String CCURRENCYID = "ccurrencyid";

  /** 税码 */
  public static final String CESTTAXCODEID = "cesttaxcodeid";

  /** 源头单据表体 */
  public static final String CFIRSTBID = "cfirstbid";

  /** 源头单据表头 */
  public static final String CFIRSTID = "cfirstid";

  /** 原币币种 **/
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /** 收货国家/地区 */
  public static final String CRECECOUNTRYID = "crececountryid";

  public static final String CROWNO = "crowno";

  /** 发货国家/地区 */
  public static final String CSENDCOUNTRYID = "csendcountryid";

  /** 来源单据表体 */
  public static final String CSOURCEBID = "csourcebid";

  /** 来源单据表头 */
  public static final String CSOURCEID = "csourceid";

  /** 税码 */
  public static final String CTAXCODEID = "ctaxcodeid";

  /** 报税国家/地区 */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /** 主单位 */
  public static final String CUNITID = "cunitid";

  public static final String DBILLDATE = "dbilldate";

  /** 暂估日期 */
  public static final String DTOCOSTAPDATE = "dtocostapdate";

  /** 购销类型 */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /** 扣税类别 */
  public static final String FESTTAXTYPE = "festtaxtype";

  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /** 传应付标志 **/
  public static final String FTOAPFLAG = "ftoapflag";

  /** 传成本标志 **/
  public static final String FTOIAFLAG = "ftoiaflag";

  /** 主实体时间戳 **/
  public static final String HTS = "hts";

  /** 累计调整结算金额 */
  public static final String NACCADJUSTMNY = "naccadjustmny";

  /** 暂估部分累计结算数量 **/
  public static final String NACCESTCOSTSTTLNUM = "naccestcoststtlnum";

  public static final String NACCESTCOSTWASHMNY = "naccestcostwashmny";

  /** 累计费用结算金额 */
  public static final String NACCFEESETTLEMNY = "naccfeesettlemny";

  /** 累计货物结算金额 */
  public static final String NACCGOODSSETTLEMNY = "naccgoodssettlemny";

  /** 暂估前累计结算金额 */
  public static final String NACCPREESTSTTLMNY = "naccpreeststtlmny";

  /** 累计结算金额 */
  public static final String NACCSETTLEMNY = "naccsettlemny";

  /** 累计调整确认应付原币价税合计 **/
  public static final String NACCTOAPADJSTOTMNY = "nacctoapadjstotmny";

  public static final String NACCTOCOSTADJSTMNY = "nacctocostadjstmny";

  /** 累计结算数量 **/
  public static final String NACCUMSETTLENUM = "naccumsettlenum";

  /** 计成本金额 */
  public static final String NCALCOSTMNY = "ncalcostmny";

  /** 入库单记成本单价 **/
  public static final String NCALCOSTPRICE = "ncalcostprice";

  /** 计税金额 */
  public static final String NCALTAXMNY = "ncaltaxmny";

  public static final String NCHANGESTDRATE = "nchangestdrate";

  /** 计成本金额 */
  public static final String NESTCALCOSTMNY = "nestcalcostmny";

  /** 记成本单价 **/
  public static final String NESTCALCOSTPRICE = "nestcalcostprice";

  /** 计税金额 */
  public static final String NESTCALTAXMNY = "nestcaltaxmny";

  /** 折本汇率 */
  public static final String NESTEXHGRATE = "nestexhgrate";

  /** 暂估本币无税金额 **/
  public static final String NESTMNY = "nestmny";

  /** 不可抵扣税额 */
  public static final String NESTNOSUBTAX = "nestnosubtax";

  /** 不可抵扣税率 */
  public static final String NESTNOSUBTAXRATE = "nestnosubtaxrate";

  /** 暂估主数量 **/
  public static final String NESTNUM = "nestnum";

  /** 无税金额 */
  public static final String NESTOMNY = "nestomny";

  /** 主无税单价 */
  public static final String NESTOPRICE = "nestoprice";

  /** 主含税单价 */
  public static final String NESTOTAXPRICE = "nestotaxprice";

  /** 价税合计 */
  public static final String NESTOTOTALMNY = "nestototalmny";

  /** 暂估单价 */
  public static final String NESTPRICE = "nestprice";

  /** 本币税额 */
  public static final String NESTTAXMNY = "nesttaxmny";

  /** 暂估含税单价 */
  public static final String NESTTAXPRICE = "nesttaxprice";

  /** 税率 */
  public static final String NESTTAXRATE = "nesttaxrate";

  /** 本币价税合计 */
  public static final String NESTTOTALMNY = "nesttotalmny";

  /** 费用本币无税金额 */
  public static final String NFEEMNY = "nfeemny";

  /** 费用本币价税合计 */
  public static final String NFEETAXMNY = "nfeetaxmny";

  /** 入库主数量(汇总主数量) */
  public static final String NINNUM = "ninnum";

  public static final String NMNY = "nmny";

  public static final String NNETPRICE = "nnetprice";

  /** 不可抵扣税额 */
  public static final String NNOSUBTAX = "nnosubtax";

  /** 不可抵扣税率 */
  public static final String NNOSUBTAXRATE = "nnosubtaxrate";

  public static final String NORIGMNY = "norigmny";

  public static final String NORIGNETPRICE = "norignetprice";

  /** 税额 */
  // public static final String NESTOTAXMNY = "nestotaxmny";

  public static final String NORIGPRICE = "norigprice";

  public static final String NORIGTAX = "norigtax";

  public static final String NORIGTAXMNY = "norigtaxmny";

  public static final String NORIGTAXNETPRICE = "norigtaxnetprice";

  /** 入库单(消耗汇总)主含税单价 */
  public static final String NORIGTAXPRICE = "norigtaxprice";

  public static final String NPRICE = "nprice";

  public static final String NTAX = "ntax";

  public static final String NTAXMNY = "ntaxmny";

  /** 主本币含税净价 */
  public static final String NTAXNETPRICE = "ntaxnetprice";

  public static final String NTAXPRICE = "ntaxprice";

  /** 入库单税率 */
  public static final String NTAXRATE = "ntaxrate";

  /** 总本币无税金额 */
  public static final String NTOTALMNY = "ntotalmny";

  /** 总本币价税合计 */
  public static final String NTOTALTAXMNY = "ntotaltaxmny";

  /** 应付财务组织 **/
  public static final String PK_APFINANCEORG = "pk_apfinanceorg";

  public static final String PK_COSTAPPSN = "pk_costappsn";

  /** 成本域 */
  public static final String PK_COSTREGION = "pk_costregion";

  /** 币种 */
  public static final String PK_ESTCURRENCY = "pk_estcurrency";

  /** 结算财务组织 **/
  public static final String PK_FINANCEORG = "pk_financeorg";

  /** 结算财务组织版本 **/
  public static final String PK_FINANCEORG_V = "pk_financeorg_v";

  /** 集团 **/
  public static final String PK_GROUP = "pk_group";

  /** 物料编码 **/
  public static final String PK_MATERIAL = "pk_material";

  public static final String PK_ORDER = "pk_order";

  public static final String PK_ORDER_B = "pk_order_b";

  public static final String PK_PURCHASEORG = "pk_purchaseorg";

  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  /** 财务头_主键 **/
  public static final String PK_STOCKPS = "pk_stockps";

  /** 财务体ID **/
  public static final String PK_STOCKPS_B = "pk_stockps_b";

  /** 供应商 */
  public static final String PK_SUPPLIER = "pk_supplier";

  /** 时间戳 **/
  public static final String TS = "ts";

  /** 单据号 **/
  public static final String VBILLCODE = "vbillcode";

  /** 源头单据号 */
  public static final String VFIRSTCODE = "vfirstcode";

  /** 源头单据行号 */
  public static final String VFIRSTROWNO = "vfirstrowno";

  /** 来源单据号 */
  public static final String VSOURCECODE = "vsourcecode";

  /** 来源单据行号 */
  public static final String VSOURCEROWNO = "vsourcerowno";

  /**
   * 
   */
  private static final long serialVersionUID = 2265645612233708778L;

  /** 影响成本标志 **/
  public UFBoolean getBaffectcost() {
    return (UFBoolean) this.getAttributeValue(GoodsEstVO.BAFFECTCOST);
  }

  /** 影响利润中心成本标志 */
  public UFBoolean getBaffectpccost() {
    return (UFBoolean) this.getAttributeValue(PurchaseinFIItemVO.BAFFECTPCCOST);
  }

  @Override
  public String getBID() {
    return this.getPk_stockps_b();
  }

  @Override
  public UFDouble getBillExchgRate() {
    return this.getNchangestdrate();
  }

  @Override
  public UFDouble getBillMny() {
    return this.getNmny();
  }

  @Override
  public UFDouble getBillNum() {
    return this.getNinnum();
  }

  @Override
  public UFDouble getBillOMny() {
    return this.getNorigmny();
  }

  @Override
  public UFDouble getBillOPrice() {
    return this.getNorignetprice();
  }

  @Override
  public UFDouble getBillOTax() {
    return this.getNorigtax();
  }

  @Override
  public UFDouble getBillOTaxPrice() {
    return this.getNorigtaxnetprice();
  }

  @Override
  public UFDouble getBillOTotalmny() {
    return this.getNorigtaxmny();
  }

  @Override
  public UFDouble getBillPrice() {
    return this.getNnetprice();
  }

  @Override
  public String getBillSupplier() {
    return this.getPk_supplier();
  }

  @Override
  public UFDouble getBillTax() {
    return this.getNtax();
  }

  @Override
  public UFDouble getBillTaxPrice() {
    return this.getNtaxnetprice();
  }

  @Override
  public UFDouble getBillTotalmny() {
    return this.getNtaxmny();
  }

  public abstract String getBillType();

  /** 是否普通采购 getter 方法 */
  public UFBoolean getBnormpur() {
    return (UFBoolean) this.getAttributeValue(GoodsEstVO.BNORMPUR);
  }

  /**
   * 逆向征税
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * 
   * @return
   */
  public UFBoolean getBopptaxflag() {
    return (UFBoolean) this.getAttributeValue(GoodsEstVO.BOPPTAXFLAG);
  }

  /** 是否结算完成 **/
  public UFBoolean getBsettlefinish() {
    return (UFBoolean) this.getAttributeValue(GoodsEstVO.BSETTLEFINISH);
  }

  /** 三角贸易 getter 方法 */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(GoodsEstVO.BTRIATRADEFLAG);
  }

  /** 本位币 **/
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(GoodsEstVO.CCURRENCYID);
  }

  /** 税码 getter 方法 */
  public String getCesttaxcodeid() {
    return (String) this.getAttributeValue(GoodsEstVO.CESTTAXCODEID);
  }

  /** 源头单据表体 getter 方法 */
  @Override
  public String getCfirstbid() {
    return (String) this.getAttributeValue(GoodsEstVO.CFIRSTBID);
  }

  /** 源头单据表头 getter 方法 */
  @Override
  public String getCfirstid() {
    return (String) this.getAttributeValue(GoodsEstVO.CFIRSTID);
  }

  /** 原币币种 **/
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(GoodsEstVO.CORIGCURRENCYID);
  }

  @Override
  public String getCostregion() {
    return (String) this.getAttributeValue(GoodsEstVO.PK_COSTREGION);
  }

  /** 收货国家/地区 getter 方法 */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(GoodsEstVO.CRECECOUNTRYID);
  }

  /** 行号 **/
  public String getCrowno() {
    return (String) this.getAttributeValue(GoodsEstVO.CROWNO);
  }

  /** 发货国家/地区 getter 方法 */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(GoodsEstVO.CSENDCOUNTRYID);
  }

  /** 来源单据表体 getter 方法 */
  @Override
  public String getCsourcebid() {
    return (String) this.getAttributeValue(GoodsEstVO.CSOURCEBID);
  }

  /** 来源单据表头 getter 方法 */
  @Override
  public String getCsourceid() {
    return (String) this.getAttributeValue(GoodsEstVO.CSOURCEID);
  }

  /** 税码 getter 方法 */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CTAXCODEID);
  }

  /** 报税国家/地区 getter 方法 */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(GoodsEstVO.CTAXCOUNTRYID);
  }

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(GoodsEstVO.CUNITID);
  }

  /** 单据日期 **/
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(GoodsEstVO.DBILLDATE);
  }

  /** 传成本和应付日期 **/
  public UFDate getDtocostapdate() {
    return (UFDate) this.getAttributeValue(GoodsEstVO.DTOCOSTAPDATE);
  }

  @Override
  public UFDate getEstDate() {
    return this.getDtocostapdate();
  }

  @Override
  public UFDouble getExchgRate() {
    return this.getNestexhgrate();
  }

  /** 购销类型 getter 方法 */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(GoodsEstVO.FBUYSELLFLAG);
  }

  /** 扣税类别 getter 方法 */
  public Integer getFesttaxtype() {
    return (Integer) this.getAttributeValue(GoodsEstVO.FESTTAXTYPE);
  }

  /** 扣税类别 **/
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(GoodsEstVO.FTAXTYPEFLAG);
  }

  /** 传应付标志 **/
  public Integer getFtoapflag() {
    return (Integer) this.getAttributeValue(GoodsEstVO.FTOAPFLAG);
  }

  /** 传成本标志 **/
  public Integer getFtoiaflag() {
    return (Integer) this.getAttributeValue(GoodsEstVO.FTOIAFLAG);
  }

  @Override
  public String getHID() {
    return this.getPk_stockps();
  }

  /** 时间戳 **/
  public UFDateTime getHts() {
    return (UFDateTime) this.getAttributeValue(GoodsEstVO.HTS);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return null;
  }

  /** 累计调整结算金额 getter 方法 */
  public UFDouble getNaccadjustmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NACCADJUSTMNY);
  }

  /** 暂估部分累计结算数量 **/
  public UFDouble getNaccestcoststtlnum() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NACCESTCOSTSTTLNUM);
  }

  /** 累计回冲暂估成本金额 **/
  public UFDouble getNaccestcostwashmny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NACCESTCOSTWASHMNY);
  }

  /** 累计费用结算金额 getter 方法 */
  public UFDouble getNaccfeesettlemny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NACCFEESETTLEMNY);
  }

  /** 累计货物结算金额 getter 方法 */
  public UFDouble getNaccgoodssettlemny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NACCGOODSSETTLEMNY);
  }

  /** 暂估前累计结算金额 **/
  public UFDouble getNaccpreeststtlmny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NACCPREESTSTTLMNY);
  }

  /** 累计结算金额 getter 方法 */
  public UFDouble getNaccsettlemny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NACCSETTLEMNY);
  }

  /** 累计调整确认应付原币价税合计 **/
  public UFDouble getNacctoapadjstotmny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NACCTOAPADJSTOTMNY);
  }

  /** 累计调整确认成本金额 **/
  public UFDouble getNacctocostadjstmny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NACCTOCOSTADJSTMNY);
  }

  /** 累计结算数量 **/
  public UFDouble getNaccumsettlenum() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NACCUMSETTLENUM);
  }

  /** 计成本金额 getter 方法 */

  @Override
  public UFDouble getNcalcostmny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NCALCOSTMNY);
  }

  /**
   * 入库单记成本单价
   * <p>
   * 使用场景：
   * <ul>
   * <li>wuxla v61
   * </ul>
   * 
   * @return
   */
  public UFDouble getNcalcostprice() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NCALCOSTPRICE);
  }

  /** 计税金额 getter 方法 */
  @Override
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NCALTAXMNY);
  }

  /** 折本汇率 **/
  public UFDouble getNchangestdrate() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NCHANGESTDRATE);
  }

  /** 计成本金额 getter 方法 */
  public UFDouble getNestcalcostmny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NESTCALCOSTMNY);
  }

  /**
   * 记成本单价
   * <p>
   * 使用场景：
   * <ul>
   * <li>wuxla v61
   * </ul>
   * 
   * @return
   */
  public UFDouble getNestcalcostprice() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NESTCALCOSTPRICE);
  }

  /** 计税金额 getter 方法 */
  public UFDouble getNestcaltaxmny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NESTCALTAXMNY);
  }

  /** 折本汇率 getter 方法 */
  public UFDouble getNestexhgrate() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NESTEXHGRATE);
  }

  /** 本币无税金额 getter 方法 */
  public UFDouble getNestmny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NESTMNY);
  }

  /** 不可抵扣税额 getter 方法 */
  public UFDouble getNestnosubtax() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NESTNOSUBTAX);
  }

  /** 不可抵扣税率 getter 方法 */
  @Override
  public UFDouble getNestnosubtaxrate() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NESTNOSUBTAXRATE);
  }

  /** 暂估主数量 getter 方法 */
  public UFDouble getNestnum() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NESTNUM);
  }

  /** 无税金额 getter 方法 */
  public UFDouble getNestomny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NESTOMNY);
  }

  /** 主无税单价 getter 方法 */
  public UFDouble getNestoprice() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NESTOPRICE);
  }

  /** 主含税单价 **/
  public UFDouble getNestotaxprice() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NESTOTAXPRICE);
  }

  /** 价税合计 getter 方法 */
  public UFDouble getNestototalmny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NESTOTOTALMNY);
  }

  /** 暂估单价 getter 方法 */
  public UFDouble getNestprice() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NESTPRICE);
  }

  /** 本币税额 getter 方法 */
  public UFDouble getNesttaxmny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NESTTAXMNY);
  }

  /** 暂估含税单价 getter 方法 */
  public UFDouble getNesttaxprice() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NESTTAXPRICE);
  }

  /** 税率 getter 方法 */
  public UFDouble getNesttaxrate() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NESTTAXRATE);
  }

  /** 本币价税合计 getter 方法 */
  public UFDouble getNesttotalmny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NESTTOTALMNY);
  }

  /** 费用本币无税金额 getter 方法 */
  public UFDouble getNfeemny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NFEEMNY);
  }

  /** 费用本币价税合计 getter 方法 */
  public UFDouble getNfeetaxmny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NFEETAXMNY);
  }

  // /** 税额 getter 方法 */
  // public UFDouble getNestotaxmny() {
  // return (UFDouble) this.getAttributeValue(GoodsEstVO.NESTOTAXMNY);
  // }

  /** 入库主数量 getter 方法 */
  public UFDouble getNinnum() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NINNUM);
  }

  /** 本币无税金额 **/
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NMNY);
  }

  /** 主本币无税净价 **/
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NNETPRICE);
  }

  @Override
  public UFDouble getNnosubtax() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NNOSUBTAX);
  }

  /** 不可抵扣税率 getter 方法 */
  public UFDouble getNnosubtaxrate() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NNOSUBTAXRATE);
  }

  /** 无税金额 **/
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NORIGMNY);
  }

  /** 主无税净价 **/
  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NORIGNETPRICE);
  }

  /** 主无税单价 **/
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NORIGPRICE);
  }

  /** 税额 **/
  public UFDouble getNorigtax() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NORIGTAX);
  }

  /** 价税合计 **/
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NORIGTAXMNY);
  }

  /** 主含税净价 **/
  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NORIGTAXNETPRICE);
  }

  /** 入库单(消耗汇总)主含税单价 getter 方法 */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NORIGTAXPRICE);
  }

  /** 主本币无税单价 **/
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NPRICE);
  }

  /** 本币税额 **/
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NTAX);
  }

  /** 本币价税合计 **/
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NTAXMNY);
  }

  /** 主本币含税净价 getter 方法 */
  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NTAXNETPRICE);
  }

  /** 主本币含税单价 **/
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NTAXPRICE);
  }

  /** 入库单(消耗汇总)税率 getter 方法 */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NTAXRATE);
  }

  /** 总本币无税金额 getter 方法 */
  public UFDouble getNtotalmny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NTOTALMNY);
  }

  /** 总本币价税合计 getter 方法 */
  public UFDouble getNtotaltaxmny() {
    return (UFDouble) this.getAttributeValue(GoodsEstVO.NTOTALTAXMNY);
  }

  @Override
  public UFDouble getnum() {
    return this.getNestnum();
  }

  @Override
  public String getorigcurr() {
    return this.getPk_estcurrency();
  }

  /** 应付财务组织 **/
  public String getPk_apfinanceorg() {
    return (String) this.getAttributeValue(GoodsEstVO.PK_APFINANCEORG);
  }

  /** 传成本和应付人 **/
  public String getPk_costappsn() {
    return (String) this.getAttributeValue(GoodsEstVO.PK_COSTAPPSN);
  }

  /** 暂估币种 **/
  public String getPk_estcurrency() {
    return (String) this.getAttributeValue(GoodsEstVO.PK_ESTCURRENCY);
  }

  /** 财务组织 **/
  public String getPk_financeorg() {
    return (String) this.getAttributeValue(GoodsEstVO.PK_FINANCEORG);
  }

  @Override
  public String getPk_fiorg() {
    return this.getPk_financeorg();
  }

  /** 集团 **/
  public String getPk_group() {
    return (String) this.getAttributeValue(GoodsEstVO.PK_GROUP);
  }

  @Override
  public String getPk_loccurr() {
    return this.getCcurrencyid();
  }

  @Override
  public String getPk_material() {
    return (String) this.getAttributeValue(GoodsEstVO.PK_MATERIAL);
  }

  @Override
  public String getPk_order() {
    return (String) this.getAttributeValue(GoodsEstVO.PK_ORDER);
  }

  @Override
  public String getPk_order_b() {
    return (String) this.getAttributeValue(GoodsEstVO.PK_ORDER_B);
  }

  @Override
  public String getPk_purchaseOrg() {
    return (String) this.getAttributeValue(GoodsEstVO.PK_PURCHASEORG);
  }

  @Override
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(GoodsEstVO.PK_SRCMATERIAL);
  }

  /** 财务头_主键 **/
  public String getPk_stockps() {
    return (String) this.getAttributeValue(GoodsEstVO.PK_STOCKPS);
  }

  /** 财务体ID **/
  public String getPk_stockps_b() {
    return (String) this.getAttributeValue(GoodsEstVO.PK_STOCKPS_B);
  }

  /** 供应商 getter 方法 */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(GoodsEstVO.PK_SUPPLIER);
  }

  @Override
  public UFDouble getTaxRate() {
    return this.getNesttaxrate();
  }

  @Override
  public int getTaxtype() {
    return this.getFesttaxtype() == null ? 1 : this.getFesttaxtype().intValue();
  }

  /** 时间戳 **/
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(GoodsEstVO.TS);
  }

  /** 单据号 getter 方法 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(GoodsEstVO.VBILLCODE);
  }

  /** 源头单据号 getter 方法 */
  @Override
  public String getVfirstcode() {
    return (String) this.getAttributeValue(GoodsEstVO.VFIRSTCODE);
  }

  /** 源头单据行号 getter 方法 */
  @Override
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(GoodsEstVO.VFIRSTROWNO);
  }

  /** 来源单据号 getter 方法 */
  @Override
  public String getVsourcecode() {
    return (String) this.getAttributeValue(GoodsEstVO.VSOURCECODE);
  }

  /** 来源单据行号 getter 方法 */
  @Override
  public String getVsourcerowno() {
    return (String) this.getAttributeValue(GoodsEstVO.VSOURCEROWNO);
  }

  /** 影响成本标志 **/
  public void setBaffectcost(UFBoolean baffectcost) {
    this.setAttributeValue(GoodsEstVO.BAFFECTCOST, baffectcost);
  }

  /** 影响利润中心成本标志 */
  public void setBaffectpccost(UFBoolean baffectpccost) {
    this.setAttributeValue(PurchaseinFIItemVO.BAFFECTPCCOST, baffectpccost);
  }

  /** 逆向征税 setter 方法 */
  public void setBopptaxflag(UFBoolean bopptaxflag) {
    this.setAttributeValue(GoodsEstVO.BOPPTAXFLAG, bopptaxflag);
  }

  /** 是否结算完成 **/
  public void setBsettlefinish(UFBoolean bsettlefinish) {
    this.setAttributeValue(GoodsEstVO.BSETTLEFINISH, bsettlefinish);
  }

  /** 三角贸易 setter 方法 */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(GoodsEstVO.BTRIATRADEFLAG, btriatradeflag);
  }

  /** 税码 setter 方法 */
  public void setCesttaxcodeid(String cesttaxcodeid) {
    this.setAttributeValue(GoodsEstVO.CESTTAXCODEID, cesttaxcodeid);
  }

  /** 收货国家/地区 setter 方法 */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(GoodsEstVO.CRECECOUNTRYID, crececountryid);
  }

  /** 行号 **/
  public void setCrowno(String crowno) {
    this.setAttributeValue(GoodsEstVO.CROWNO, crowno);
  }

  /** 发货国家/地区 setter 方法 */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(GoodsEstVO.CSENDCOUNTRYID, csendcountryid);
  }

  /** 税码 setter 方法 */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(PurchaseinFIItemVO.CTAXCODEID, ctaxcodeid);
  }

  /** 报税国家/地区 setter 方法 */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(GoodsEstVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  /** 主单位 setter 方法 */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(GoodsEstVO.CUNITID, cunitid);
  }

  /** 单据日期 **/
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(GoodsEstVO.DBILLDATE, dbilldate);
  }

  /** 传成本和应付日期 **/
  public void setDtocostapdate(UFDate dtocostapdate) {
    this.setAttributeValue(GoodsEstVO.DTOCOSTAPDATE, dtocostapdate);
  }

  /** 购销类型 setter 方法 */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(GoodsEstVO.FBUYSELLFLAG, fbuysellflag);
  }

  /** 扣税类别 setter 方法 */
  public void setFesttaxtype(Integer festtaxtype) {
    this.setAttributeValue(GoodsEstVO.FESTTAXTYPE, festtaxtype);
  }

  /** 传应付标志 **/
  public void setFtoapflag(Integer ftoapflag) {
    this.setAttributeValue(GoodsEstVO.FTOAPFLAG, ftoapflag);
  }

  /** 传成本标志 **/
  public void setFtoiaflag(Integer ftoiaflag) {
    this.setAttributeValue(GoodsEstVO.FTOIAFLAG, ftoiaflag);
  }

  /** 时间戳 **/
  public void setHts(UFDateTime ts) {
    this.setAttributeValue(GoodsEstVO.HTS, ts);
  }

  /** 累计调整结算金额 setter 方法 */
  public void setNaccadjustmny(UFDouble naccadjustmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NACCADJUSTMNY, naccadjustmny);
  }

  /** 暂估部分累计结算数量 **/
  public void setNaccestcoststtlnum(UFDouble naccestcoststtlnum) {
    this.setAttributeValue(GoodsEstVO.NACCESTCOSTSTTLNUM, naccestcoststtlnum);
  }

  /** 累计回冲暂估成本金额 **/
  public void setNaccestcostwashmny(UFDouble naccestcostwashmny) {
    this.setAttributeValue(GoodsEstVO.NACCESTCOSTWASHMNY, naccestcostwashmny);
  }

  /** 累计费用结算金额 setter 方法 */
  public void setNaccfeesettlemny(UFDouble naccfeesettlemny) {
    this.setAttributeValue(GoodsEstVO.NACCFEESETTLEMNY, naccfeesettlemny);
  }

  /** 累计货物结算金额 setter 方法 */
  public void setNaccgoodssettlemny(UFDouble naccgoodssettlemny) {
    this.setAttributeValue(GoodsEstVO.NACCGOODSSETTLEMNY, naccgoodssettlemny);
  }

  /** 暂估前累计结算金额 **/
  public void setNaccpreeststtlmny(UFDouble naccpreeststtlmny) {
    this.setAttributeValue(GoodsEstVO.NACCPREESTSTTLMNY, naccpreeststtlmny);
  }

  /** 累计结算金额 setter 方法 */
  public void setNaccsettlemny(UFDouble naccsettlemny) {
    this.setAttributeValue(GoodsEstVO.NACCSETTLEMNY, naccsettlemny);
  }

  /** 累计调整确认应付原币价税合计 **/
  public void setNacctoapadjstotmny(UFDouble nacctoapadjstotmny) {
    this.setAttributeValue(GoodsEstVO.NACCTOAPADJSTOTMNY, nacctoapadjstotmny);
  }

  /** 累计调整确认成本金额 **/
  public void setNacctocostadjstmny(UFDouble nacctocostadjstmny) {
    this.setAttributeValue(GoodsEstVO.NACCTOCOSTADJSTMNY, nacctocostadjstmny);
  }

  /** 累计结算数量 **/
  public void setNaccumsettlenum(UFDouble naccumsettlenum) {
    this.setAttributeValue(GoodsEstVO.NACCUMSETTLENUM, naccumsettlenum);
  }

  /** 计成本金额 setter 方法 */
  public void setNcalcostmny(UFDouble ncalcostmny) {
    this.setAttributeValue(GoodsEstVO.NCALCOSTMNY, ncalcostmny);
  }

  /**
   * 入库单记成本单价
   * <p>
   * 使用场景：
   * <ul>
   * <li>wuxla v61
   * </ul>
   * 
   * @param ncalcostprice
   */
  public void setNcalcostprice(UFDouble ncalcostprice) {
    this.setAttributeValue(GoodsEstVO.NCALCOSTPRICE, ncalcostprice);
  }

  /** 计税金额 setter 方法 */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NCALTAXMNY, ncaltaxmny);
  }

  /** 计成本金额 setter 方法 */
  public void setNestcalcostmny(UFDouble nestcalcostmny) {
    this.setAttributeValue(GoodsEstVO.NESTCALCOSTMNY, nestcalcostmny);
  }

  /**
   * 计成本单价
   * <p>
   * 使用场景：
   * <ul>
   * <li>wuxla V61
   * </ul>
   * 
   * @param nestcalcostprice
   */
  public void setNestcalcostprice(UFDouble nestcalcostprice) {
    this.setAttributeValue(GoodsEstVO.NESTCALCOSTPRICE, nestcalcostprice);
  }

  /** 计税金额 setter 方法 */
  public void setNestcaltaxmny(UFDouble nestcaltaxmny) {
    this.setAttributeValue(GoodsEstVO.NESTCALTAXMNY, nestcaltaxmny);
  }

  /** 折本汇率 setter 方法 */
  public void setNestexhgrate(UFDouble nestexhgrate) {
    this.setAttributeValue(GoodsEstVO.NESTEXHGRATE, nestexhgrate);
  }

  /** 本币无税金额 setter 方法 */
  public void setNestmny(UFDouble nestmny) {
    this.setAttributeValue(GoodsEstVO.NESTMNY, nestmny);
  }

  /** 不可抵扣税额 setter 方法 */
  public void setNestnosubtax(UFDouble nestnosubtax) {
    this.setAttributeValue(GoodsEstVO.NESTNOSUBTAX, nestnosubtax);
  }

  /** 不可抵扣税率 setter 方法 */
  public void setNestnosubtaxrate(UFDouble nestnosubtaxrate) {
    this.setAttributeValue(GoodsEstVO.NESTNOSUBTAXRATE, nestnosubtaxrate);
  }

  /** 暂估主数量 setter 方法 */
  public void setNestnum(UFDouble nestnum) {
    this.setAttributeValue(GoodsEstVO.NESTNUM, nestnum);
  }

  /** 无税金额 setter 方法 */
  public void setNestomny(UFDouble nestomny) {
    this.setAttributeValue(GoodsEstVO.NESTOMNY, nestomny);
  }

  /** 主无税单价 setter 方法 */
  public void setNestoprice(UFDouble nestoprice) {
    this.setAttributeValue(GoodsEstVO.NESTOPRICE, nestoprice);
  }

  // /** 税额 setter 方法 */
  // public void setNestotaxmny(UFDouble nestotaxmny) {
  // this.setAttributeValue(GoodsEstVO.NESTOTAXMNY, nestotaxmny);
  // }

  /** 主含税单价 **/
  public void setNestotaxprice(UFDouble nestotaxprice) {
    this.setAttributeValue(GoodsEstVO.NESTOTAXPRICE, nestotaxprice);
  }

  /** 价税合计 setter 方法 */
  public void setNestototalmny(UFDouble nestototalmny) {
    this.setAttributeValue(GoodsEstVO.NESTOTOTALMNY, nestototalmny);
  }

  /** 暂估单价 setter 方法 */
  public void setNestprice(UFDouble nestprice) {
    this.setAttributeValue(GoodsEstVO.NESTPRICE, nestprice);
  }

  /** 本币税额 setter 方法 */
  public void setNesttaxmny(UFDouble nesttaxmny) {
    this.setAttributeValue(GoodsEstVO.NESTTAXMNY, nesttaxmny);
  }

  /** 暂估含税单价 setter 方法 */
  public void setNesttaxprice(UFDouble nesttaxprice) {
    this.setAttributeValue(GoodsEstVO.NESTTAXPRICE, nesttaxprice);
  }

  /** 税率 setter 方法 */
  public void setNesttaxrate(UFDouble nesttaxrate) {
    this.setAttributeValue(GoodsEstVO.NESTTAXRATE, nesttaxrate);
  }

  /** 本币价税合计 setter 方法 */
  public void setNesttotalmny(UFDouble nesttotalmny) {
    this.setAttributeValue(GoodsEstVO.NESTTOTALMNY, nesttotalmny);
  }

  /** 费用本币无税金额 setter 方法 */
  public void setNfeemny(UFDouble nfeemny) {
    this.setAttributeValue(GoodsEstVO.NFEEMNY, nfeemny);
  }

  /** 费用本币价税合计 setter 方法 */
  public void setNfeetaxmny(UFDouble nfeetaxmny) {
    this.setAttributeValue(GoodsEstVO.NFEETAXMNY, nfeetaxmny);
  }

  /** 实入主数量 **/
  public void setNinnum(UFDouble ninnum) {
    this.setAttributeValue(GoodsEstVO.NINNUM, ninnum);
  }

  /** 主本币无税净价 **/
  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(GoodsEstVO.NNETPRICE, nnetprice);
  }

  /** 不可抵扣税率 setter 方法 */
  public void setNnosubtaxrate(UFDouble nnosubtaxrate) {
    this.setAttributeValue(PurchaseinFIItemVO.NNOSUBTAXRATE, nnosubtaxrate);
  }

  /** 入库单主(消耗汇总)含税单价 setter 方法 */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(GoodsEstVO.NORIGTAXPRICE, norigtaxprice);
  }

  /** 本币税额 **/
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(GoodsEstVO.NTAX, ntax);
  }

  /** 主本币含税净价 setter 方法 */
  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(GoodsEstVO.NTAXNETPRICE, ntaxnetprice);
  }

  /** 入库单税率(消耗汇总) setter 方法 */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(GoodsEstVO.NTAXRATE, ntaxrate);
  }

  /** 总本币无税金额 setter 方法 */
  public void setNtotalmny(UFDouble ntotalmny) {
    this.setAttributeValue(GoodsEstVO.NTOTALMNY, ntotalmny);
  }

  /** 总本币价税合计 setter 方法 */
  public void setNtotaltaxmny(UFDouble ntotaltaxmny) {
    this.setAttributeValue(GoodsEstVO.NTOTALTAXMNY, ntotaltaxmny);
  }

  /** 应付财务组织 **/
  public void setPk_apfinanceorg(String pk_apfinanceorg) {
    this.setAttributeValue(GoodsEstVO.PK_APFINANCEORG, pk_apfinanceorg);
  }

  /** 传成本和应付人 **/
  public void setPk_costappsn(String pk_costappsn) {
    this.setAttributeValue(GoodsEstVO.PK_COSTAPPSN, pk_costappsn);
  }

  /** 暂估币种 **/
  public void setPk_estcurrency(String pk_estcurrency) {
    this.setAttributeValue(GoodsEstVO.PK_ESTCURRENCY, pk_estcurrency);
  }

  /** 结算财务组织 setter 方法 */
  public void setPk_financeorg(String pk_financeorg) {
    this.setAttributeValue(GoodsEstVO.PK_FINANCEORG, pk_financeorg);
  }

  /** 财务头_主键 **/
  public void setPk_stockps(String pk_stockps) {
    this.setAttributeValue(GoodsEstVO.PK_STOCKPS, pk_stockps);
  }

  /** 财务体ID **/
  public void setPk_stockps_b(String pk_stockps_b) {
    this.setAttributeValue(GoodsEstVO.PK_STOCKPS_B, pk_stockps_b);
  }

  /** 时间戳 **/
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(GoodsEstVO.TS, ts);
  }

}
