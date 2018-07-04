package nc.vo.pu.m4201.entity;

import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class PurchaseinFIItemVO extends SuperVO {
  /** 影响成本标志 **/
  public static final String BAFFECTCOST = "baffectcost";

  /** 影响利润中心成本标志 **/
  public static final String BAFFECTPCCOST = "baffectpccost";

  /** 是否赠品 */
  public static final String BLARGESS = "blargess";

  /** 逆向征税 */
  public static final String BOPPTAXFLAG = "bopptaxflag";

  /** 是否结算完成 */
  public static final String BSETTLEFINISH = "bsettlefinish";

  /** 客户 */
  public static final String CASSCUSTID = "casscustid";

  /** 单位 */
  public static final String CASTUNITID = "castunitid";

  /** 本位币 */
  public static final String CCURRENCYID = "ccurrencyid";

  /** 目的地地区 */
  public static final String CDESTIAREAID = "cdestiareaid";

  /** 目的地国 */
  public static final String CDESTICOUNTRYID = "cdesticountryid";

  /** 税码 */
  public static final String CESTTAXCODEID = "cesttaxcodeid";

  /** 特征码 **/
  public static final String CFFILEID = "cffileid";

  /** 源头单据表体 */
  public static final String CFIRSTBID = "cfirstbid";

  /** 源头单据表头 */
  public static final String CFIRSTID = "cfirstid";

  /** 源头单据类型 */
  public static final String CFIRSTTYPECODE = "cfirsttypecode";

  /** 原产地区 */
  public static final String CORIGAREAID = "corigareaid";

  /** 原产国 */
  public static final String CORIGCOUNTRYID = "corigcountryid";

  /** 原币币种 */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /** 生产厂商 */
  public static final String CPRODUCTORID = "cproductorid";

  /** 项目 */
  public static final String CPROJECTID = "cprojectid";

  /** 项目任务 */
  public static final String CPROJECTTASKID = "cprojecttaskid";

  /** 报价单位 */
  public static final String CQTUNITID = "cqtunitid";

  /** 收货单位 */
  public static final String CRECEIEVEID = "creceieveid";

  /** 行号 */
  public static final String CROWNO = "crowno";

  /** 来源单据表体 */
  public static final String CSOURCEBID = "csourcebid";

  /** 来源单据表头 */
  public static final String CSOURCEID = "csourceid";

  /** 来源单据类型 */
  public static final String CSOURCETYPECODE = "csourcetypecode";

  /** 库存状态 */
  public static final String CSTATEID = "cstateid";

  /** 税码 */
  public static final String CTAXCODEID = "ctaxcodeid";

  /** 主单位 */
  public static final String CUNITID = "cunitid";

  /** 业务日期 */
  public static final String DBIZDATE = "dbizdate";

  /** 源头单据制单日期 */
  public static final String DFIRSTBILLDATE = "dfirstbilldate";

  /** dr */
  public static final String DR = "dr";

  /** 需求日期 */
  public static final String DREQUIREDATE = "drequiredate";

  /** 暂估日期 */
  public static final String DTOCOSTAPDATE = "dtocostapdate";

  /** 扣税类别 */
  public static final String FESTTAXTYPE = "festtaxtype";

  /** 入库单扣税类别 */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /** 传应付标志 */
  public static final String FTOAPFLAG = "ftoapflag";

  /** 传成本标志 */
  public static final String FTOIAFLAG = "ftoiaflag";

  /** 累计调整结算金额 */
  public static final String NACCADJUSTMNY = "naccadjustmny";

  /** 暂估部分累计结算数量 */
  public static final String NACCESTCOSTSTTLNUM = "naccestcoststtlnum";

  /** 累计回冲暂估成本金额 */
  public static final String NACCESTCOSTWASHMNY = "naccestcostwashmny";

  /** 累计费用结算金额 */
  public static final String NACCFEESETTLEMNY = "naccfeesettlemny";

  /** 累计货物结算金额 */
  public static final String NACCGOODSSETTLEMNY = "naccgoodssettlemny";

  /** 暂估前累计结算金额 */
  public static final String NACCPREESTSTTLMNY = "naccpreeststtlmny";

  /** 累计结算金额 */
  public static final String NACCSETTLEMNY = "naccsettlemny";

  /** 累计调整确认应付原币价税合计 */
  public static final String NACCTOAPADJSTOTMNY = "nacctoapadjstotmny";

  /** 累计调整确认成本金额 */
  public static final String NACCTOCOSTADJSTMNY = "nacctocostadjstmny";

  /** 累计结算数量 */
  public static final String NACCUMSETTLENUM = "naccumsettlenum";

  /** 计成本金额 */
  public static final String NCALCOSTMNY = "ncalcostmny";

  /** 入库单确认成本单价 */
  public static final String NCALCOSTPRICE = "ncalcostprice";

  /** 计税金额 */
  public static final String NCALTAXMNY = "ncaltaxmny";

  /** 入库单折本汇率 */
  public static final String NCHANGESTDRATE = "nchangestdrate";

  /** 金额 */
  public static final String NCOSTMNY = "ncostmny";

  /** 单价 */
  public static final String NCOSTPRICE = "ncostprice";

  /** 计成本金额 */
  public static final String NESTCALCOSTMNY = "nestcalcostmny";

  /** 计成本单价 */
  public static final String NESTCALCOSTPRICE = "nestcalcostprice";

  /** 计税金额 */
  public static final String NESTCALTAXMNY = "nestcaltaxmny";

  /** 折本汇率 */
  public static final String NESTEXHGRATE = "nestexhgrate";

  /** 本币无税金额 */
  public static final String NESTMNY = "nestmny";

  /** 不可抵扣税额 */
  public static final String NESTNOSUBTAX = "nestnosubtax";

  /** 不可抵扣税率 */
  public static final String NESTNOSUBTAXRATE = "nestnosubtaxrate";

  /** 暂估主数量 */
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

  /** 全局本位币汇率 */
  public static final String NGLOBALEXCHGRATE = "nglobalexchgrate";

  /** 全局本币无税金额 */
  public static final String NGLOBALMNY = "nglobalmny";

  /** 全局本币价税合计 */
  public static final String NGLOBALTAXMNY = "nglobaltaxmny";

  /** 集团本位币汇率 */
  public static final String NGROUPEXCHGRATE = "ngroupexchgrate";

  /** 集团本币无税金额 */
  public static final String NGROUPMNY = "ngroupmny";

  /** 集团本币价税合计 */
  public static final String NGROUPTAXMNY = "ngrouptaxmny";

  /** 实入辅数量 */
  public static final String NINASSISTNUM = "ninassistnum";

  /** 入库主数量 */
  public static final String NINNUM = "ninnum";

  /** 入库单本币无税金额 */
  public static final String NMNY = "nmny";

  /** 主本币无税净价 */
  public static final String NNETPRICE = "nnetprice";

  /** 不可抵扣税额 */
  public static final String NNOSUBTAX = "nnosubtax";

  /** 不可抵扣税率 */
  public static final String NNOSUBTAXRATE = "nnosubtaxrate";

  /** 入库单无税金额 */
  public static final String NORIGMNY = "norigmny";

  /** 主无税净价 */
  public static final String NORIGNETPRICE = "norignetprice";

  /** 入库单主无税单价 */
  public static final String NORIGPRICE = "norigprice";

  /** 入库单价税合计 */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /** 主含税净价 */
  public static final String NORIGTAXNETPRICE = "norigtaxnetprice";

  /** 入库单主含税单价 */
  public static final String NORIGTAXPRICE = "norigtaxprice";

  /** 计划金额 */
  public static final String NPLANNEDMNY = "nplannedmny";

  /** 计划单价 */
  public static final String NPLANNEDPRICE = "nplannedprice";

  /** 主本币无税单价 */
  public static final String NPRICE = "nprice";

  /** 本币无税净价 */
  public static final String NQTNETPRICE = "nqtnetprice";

  /** 无税净价 */
  public static final String NQTORIGNETPRICE = "nqtorignetprice";

  /** 无税单价 */
  public static final String NQTORIGPRICE = "nqtorigprice";

  /** 含税净价 */
  public static final String NQTORIGTAXNETPRICE = "nqtorigtaxnetprice";

  /** 含税单价 */
  public static final String NQTORIGTAXPRICE = "nqtorigtaxprice";

  /** 本币无税单价 */
  public static final String NQTPRICE = "nqtprice";

  /** 本币含税净价 */
  public static final String NQTTAXNETPRICE = "nqttaxnetprice";

  /** 本币含税单价 */
  public static final String NQTTAXPRICE = "nqttaxprice";

  /** 报价数量 */
  public static final String NQTUNITNUM = "nqtunitnum";

  /** 入库单本币税额 */
  public static final String NTAX = "ntax";

  /** 入库单本币价税合计 */
  public static final String NTAXMNY = "ntaxmny";

  /** 主本币含税净价 */
  public static final String NTAXNETPRICE = "ntaxnetprice";

  /** 主本币含税单价 */
  public static final String NTAXPRICE = "ntaxprice";

  /** 入库单税率 */
  public static final String NTAXRATE = "ntaxrate";

  /** 总本币无税金额 */
  public static final String NTOTALMNY = "ntotalmny";

  /** 总本币价税合计 */
  public static final String NTOTALTAXMNY = "ntotaltaxmny";

  /** 应付财务组织 */
  public static final String PK_APFINANCEORG = "pk_apfinanceorg";

  /** 应付财务组织版本 */
  public static final String PK_APFINANCEORG_V = "pk_apfinanceorg_v";

  /** 利润中心 */
  public static final String PK_APLIABCENTER = "pk_apliabcenter";

  /** 利润中心版本 */
  public static final String PK_APLIABCENTER_V = "pk_apliabcenter_v";

  /** 收货利润中心最新版本 */
  public static final String PK_ARRLIABCENTER = "pk_arrliabcenter";

  /** 收货利润中心 */
  public static final String PK_ARRLIABCENTER_V = "pk_arrliabcenter_v";

  /** 批次档案 */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /** 传成本和应付人 */
  public static final String PK_COSTAPPSN = "pk_costappsn";

  /** 成本域 */
  public static final String PK_COSTREGION = "pk_costregion";

  /** 相应的直运销售订单行 */
  public static final String PK_DTRANSALEBID = "pk_dtransalebid";

  /** 相应的直运销售订单 */
  public static final String PK_DTRANSALEID = "pk_dtransaleid";

  /** 币种 */
  public static final String PK_ESTCURRENCY = "pk_estcurrency";

  /** 结算财务组织 */
  public static final String PK_FINANCEORG = "pk_financeorg";

  /** 结算财务组织版本 */
  public static final String PK_FINANCEORG_V = "pk_financeorg_v";

  /** 结算完毕的结算单行明细 */
  public static final String PK_FINISHSETTLE_B = "pk_finishsettle_b";

  /** 集团 */
  public static final String PK_GROUP = "pk_group";

  /** 开票供应商 */
  public static final String PK_INVCSUPLLIER = "pk_invcsupllier";

  /** 物料编码 */
  public static final String PK_MATERIAL = "pk_material";

  /** 订单 */
  public static final String PK_ORDER = "pk_order";

  /** 订单明细 */
  public static final String PK_ORDER_B = "pk_order_b";

  /** 库存组织 */
  public static final String PK_ORG = "pk_org";

  /** 库存组织版本 */
  public static final String PK_ORG_V = "pk_org_v";

  /** 采购组织 */
  public static final String PK_PURCHASEORG = "pk_purchaseorg";

  /** 采购组织版本 */
  public static final String PK_PURCHASEORG_V = "pk_purchaseorg_v";

  /** 需求仓库 */
  public static final String PK_REQSTOCDOC = "pk_reqstocdoc";

  /** 需求库存组织 */
  public static final String PK_REQSTOCKORG = "pk_reqstockorg";

  /** 需求库存组织版本 */
  public static final String PK_REQSTOCKORG_V = "pk_reqstockorg_v";

  /** 物料 */
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  /** 库存财务体 */
  public static final String PK_STOCKPS = "pk_stockps";

  /** 采购入体ID */
  public static final String PK_STOCKPS_B = "pk_stockps_b";

  /** 供应商 */
  public static final String PK_SUPPLIER = "pk_supplier";

  /** 寄存供应商 */
  public static final String PK_VMISUPPLIER = "pk_vmisupplier";

  /** ts */
  public static final String TS = "ts";

  /** 批次号 */
  public static final String VBATCHCODE = "vbatchcode";

  /** 自定义项1 */
  public static final String VBDEF1 = "vbdef1";

  /** 自定义项10 */
  public static final String VBDEF10 = "vbdef10";

  /** 自定义项11 */
  public static final String VBDEF11 = "vbdef11";

  /** 自定义项12 */
  public static final String VBDEF12 = "vbdef12";

  /** 自定义项13 */
  public static final String VBDEF13 = "vbdef13";

  /** 自定义项14 */
  public static final String VBDEF14 = "vbdef14";

  /** 自定义项15 */
  public static final String VBDEF15 = "vbdef15";

  /** 自定义项16 */
  public static final String VBDEF16 = "vbdef16";

  /** 自定义项17 */
  public static final String VBDEF17 = "vbdef17";

  /** 自定义项18 */
  public static final String VBDEF18 = "vbdef18";

  /** 自定义项19 */
  public static final String VBDEF19 = "vbdef19";

  /** 自定义项2 */
  public static final String VBDEF2 = "vbdef2";

  /** 自定义项20 */
  public static final String VBDEF20 = "vbdef20";

  /** 自定义项3 */
  public static final String VBDEF3 = "vbdef3";

  /** 自定义项4 */
  public static final String VBDEF4 = "vbdef4";

  /** 自定义项5 */
  public static final String VBDEF5 = "vbdef5";

  /** 自定义项6 */
  public static final String VBDEF6 = "vbdef6";

  /** 自定义项7 */
  public static final String VBDEF7 = "vbdef7";

  /** 自定义项8 */
  public static final String VBDEF8 = "vbdef8";

  /** 自定义项9 */
  public static final String VBDEF9 = "vbdef9";

  /** 换算率 */
  public static final String VCHANGERATE = "vchangerate";

  /** 采购合同号 */
  public static final String VCTCODE = "vctcode";

  /** 源头单据号 */
  public static final String VFIRSTCODE = "vfirstcode";

  /** 源头单据行号 */
  public static final String VFIRSTROWNO = "vfirstrowno";

  /** 源头单据交易类型 */
  public static final String VFIRSTTRANTYPE = "vfirsttrantype";

  /** 自由辅助属性1 */
  public static final String VFREE1 = "vfree1";

  /** 自由辅助属性10 */
  public static final String VFREE10 = "vfree10";

  /** 自由辅助属性2 */
  public static final String VFREE2 = "vfree2";

  /** 自由辅助属性3 */
  public static final String VFREE3 = "vfree3";

  /** 自由辅助属性4 */
  public static final String VFREE4 = "vfree4";

  /** 自由辅助属性5 */
  public static final String VFREE5 = "vfree5";

  /** 自由辅助属性6 */
  public static final String VFREE6 = "vfree6";

  /** 自由辅助属性7 */
  public static final String VFREE7 = "vfree7";

  /** 自由辅助属性8 */
  public static final String VFREE8 = "vfree8";

  /** 自由辅助属性9 */
  public static final String VFREE9 = "vfree9";

  /** 行备注 */
  public static final String VNOTEBODY = "vnotebody";

  /** 订单号 */
  public static final String VORDERCODE = "vordercode";

  /** 订单交易类型 */
  public static final String VORDERTRANTYPECODE = "vordertrantypecode";

  /** 报价单位换算率 */
  public static final String VQTUNITRATE = "vqtunitrate";

  /** 来源单据号 */
  public static final String VSOURCECODE = "vsourcecode";

  /** 来源单据行号 */
  public static final String VSOURCEROWNO = "vsourcerowno";

  /** 来源交易类型 */
  public static final String VSOURCETRANTYPE = "vsourcetrantype";

  private static final long serialVersionUID = -4764377041749905253L;

  /** 影响成本标志 **/
  public UFBoolean getBaffectcost() {
    return (UFBoolean) this.getAttributeValue(PurchaseinFIItemVO.BAFFECTCOST);
  }

  /** 影响利润中心成本标志 **/
  public UFBoolean getBaffectpccost() {
    return (UFBoolean) this.getAttributeValue(PurchaseinFIItemVO.BAFFECTPCCOST);
  }

  /** 是否赠品 getter 方法 */
  public UFBoolean getBlargess() {
    return (UFBoolean) this.getAttributeValue(PurchaseinFIItemVO.BLARGESS);
  }

  /** 逆向征税 getter 方法 */
  public UFBoolean getBopptaxflag() {
    return (UFBoolean) this.getAttributeValue(PurchaseinFIItemVO.BOPPTAXFLAG);
  }

  /** 是否结算完成 getter 方法 */
  public UFBoolean getBsettlefinish() {
    return (UFBoolean) this.getAttributeValue(PurchaseinFIItemVO.BSETTLEFINISH);
  }

  /** 客户 getter 方法 */
  public String getCasscustid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CASSCUSTID);
  }

  /** 单位 getter 方法 */
  public String getCastunitid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CASTUNITID);
  }

  /** 本位币 getter 方法 */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CCURRENCYID);
  }

  /** 目的地地区 getter 方法 */
  public String getCdestiareaid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CDESTIAREAID);
  }

  /** 目的地国 getter 方法 */
  public String getCdesticountryid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CDESTICOUNTRYID);
  }

  /** 税码 getter 方法 */
  public String getCesttaxcodeid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CESTTAXCODEID);
  }

  /** 特征码 **/
  public String getCffileid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CFFILEID);
  }

  /** 源头单据表体 getter 方法 */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CFIRSTBID);
  }

  /** 源头单据表头 getter 方法 */
  public String getCfirstid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CFIRSTID);
  }

  /** 源头单据类型 getter 方法 */
  public String getCfirsttypecode() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CFIRSTTYPECODE);
  }

  /** 原产地区 getter 方法 */
  public String getCorigareaid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CORIGAREAID);
  }

  /** 原产国 getter 方法 */
  public String getCorigcountryid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CORIGCOUNTRYID);
  }

  /** 原币币种 getter 方法 */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CORIGCURRENCYID);
  }

  /** 生产厂商 getter 方法 */
  public String getCproductorid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CPRODUCTORID);
  }

  /** 项目 getter 方法 */
  public String getCprojectid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CPROJECTID);
  }

  /** 项目任务 getter 方法 */
  public String getCprojecttaskid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CPROJECTTASKID);
  }

  /** 报价单位 getter 方法 */
  public String getCqtunitid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CQTUNITID);
  }

  /** 收货单位 getter 方法 */
  public String getCreceieveid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CRECEIEVEID);
  }

  /** 行号 getter 方法 */
  public String getCrowno() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CROWNO);
  }

  /** 来源单据表体 getter 方法 */
  public String getCsourcebid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CSOURCEBID);
  }

  /** 来源单据表头 getter 方法 */
  public String getCsourceid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CSOURCEID);
  }

  /** 来源单据类型 getter 方法 */
  public String getCsourcetypecode() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CSOURCETYPECODE);
  }

  /** 库存状态 getter 方法 */
  public String getCstateid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CSTATEID);
  }

  /** 税码 getter 方法 */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CTAXCODEID);
  }

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.CUNITID);
  }

  /** 业务日期 getter 方法 */
  public UFDate getDbizdate() {
    return (UFDate) this.getAttributeValue(PurchaseinFIItemVO.DBIZDATE);
  }

  /** 源头单据制单日期 getter 方法 */
  public UFDate getDfirstbilldate() {
    return (UFDate) this.getAttributeValue(PurchaseinFIItemVO.DFIRSTBILLDATE);
  }

  /** dr getter 方法 */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(PurchaseinFIItemVO.DR);
  }

  /** 需求日期 getter 方法 */
  public UFDate getDrequiredate() {
    return (UFDate) this.getAttributeValue(PurchaseinFIItemVO.DREQUIREDATE);
  }

  /** 暂估日期 getter 方法 */
  public UFDate getDtocostapdate() {
    return (UFDate) this.getAttributeValue(PurchaseinFIItemVO.DTOCOSTAPDATE);
  }

  /** 扣税类别 getter 方法 */
  public Integer getFesttaxtype() {
    return (Integer) this.getAttributeValue(PurchaseinFIItemVO.FESTTAXTYPE);
  }

  /** 入库单扣税类别 getter 方法 */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(PurchaseinFIItemVO.FTAXTYPEFLAG);
  }

  /** 传应付标志 getter 方法 */
  public Integer getFtoapflag() {
    return (Integer) this.getAttributeValue(PurchaseinFIItemVO.FTOAPFLAG);
  }

  /** 传成本标志 getter 方法 */
  public Integer getFtoiaflag() {
    return (Integer) this.getAttributeValue(PurchaseinFIItemVO.FTOIAFLAG);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(PUEntity.PurchaseinFI_B);
    return meta;
  }

  /** 累计调整结算金额 getter 方法 */
  public UFDouble getNaccadjustmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NACCADJUSTMNY);
  }

  /** 暂估部分累计结算数量 getter 方法 */
  public UFDouble getNaccestcoststtlnum() {
    return (UFDouble) this
        .getAttributeValue(PurchaseinFIItemVO.NACCESTCOSTSTTLNUM);
  }

  /** 累计回冲暂估成本金额 getter 方法 */
  public UFDouble getNaccestcostwashmny() {
    return (UFDouble) this
        .getAttributeValue(PurchaseinFIItemVO.NACCESTCOSTWASHMNY);
  }

  /** 累计费用结算金额 getter 方法 */
  public UFDouble getNaccfeesettlemny() {
    return (UFDouble) this
        .getAttributeValue(PurchaseinFIItemVO.NACCFEESETTLEMNY);
  }

  /** 累计货物结算金额 getter 方法 */
  public UFDouble getNaccgoodssettlemny() {
    return (UFDouble) this
        .getAttributeValue(PurchaseinFIItemVO.NACCGOODSSETTLEMNY);
  }

  /** 暂估前累计结算金额 getter 方法 */
  public UFDouble getNaccpreeststtlmny() {
    return (UFDouble) this
        .getAttributeValue(PurchaseinFIItemVO.NACCPREESTSTTLMNY);
  }

  /** 累计结算金额 getter 方法 */
  public UFDouble getNaccsettlemny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NACCSETTLEMNY);
  }

  /** 累计调整确认应付原币价税合计 getter 方法 */
  public UFDouble getNacctoapadjstotmny() {
    return (UFDouble) this
        .getAttributeValue(PurchaseinFIItemVO.NACCTOAPADJSTOTMNY);
  }

  /** 累计调整确认成本金额 getter 方法 */
  public UFDouble getNacctocostadjstmny() {
    return (UFDouble) this
        .getAttributeValue(PurchaseinFIItemVO.NACCTOCOSTADJSTMNY);
  }

  /** 累计结算数量 getter 方法 */
  public UFDouble getNaccumsettlenum() {
    return (UFDouble) this
        .getAttributeValue(PurchaseinFIItemVO.NACCUMSETTLENUM);
  }

  /** 计成本金额 getter 方法 */
  public UFDouble getNcalcostmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NCALCOSTMNY);
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
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NCALCOSTPRICE);
  }

  /** 计税金额 getter 方法 */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NCALTAXMNY);
  }

  /** 入库单折本汇率 getter 方法 */
  public UFDouble getNchangestdrate() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NCHANGESTDRATE);
  }

  /** 金额 getter 方法 */
  public UFDouble getNcostmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NCOSTMNY);
  }

  /** 单价 getter 方法 */
  public UFDouble getNcostprice() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NCOSTPRICE);
  }

  /** 计成本金额 getter 方法 */
  public UFDouble getNestcalcostmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NESTCALCOSTMNY);
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
    return (UFDouble) this
        .getAttributeValue(PurchaseinFIItemVO.NESTCALCOSTPRICE);
  }

  /** 计税金额 getter 方法 */
  public UFDouble getNestcaltaxmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NESTCALTAXMNY);
  }

  /** 折本汇率 getter 方法 */
  public UFDouble getNestexhgrate() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NESTEXHGRATE);
  }

  /** 本币无税金额 getter 方法 */
  public UFDouble getNestmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NESTMNY);
  }

  /** 不可抵扣税额 getter 方法 */
  public UFDouble getNestnosubtax() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NESTNOSUBTAX);
  }

  /** 不可抵扣税率 getter 方法 */
  public UFDouble getNestnosubtaxrate() {
    return (UFDouble) this
        .getAttributeValue(PurchaseinFIItemVO.NESTNOSUBTAXRATE);
  }

  /** 暂估主数量 getter 方法 */
  public UFDouble getNestnum() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NESTNUM);
  }

  /** 无税金额 getter 方法 */
  public UFDouble getNestomny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NESTOMNY);
  }

  /** 主无税单价 getter 方法 */
  public UFDouble getNestoprice() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NESTOPRICE);
  }

  /** 主含税单价 getter 方法 */
  public UFDouble getNestotaxprice() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NESTOTAXPRICE);
  }

  /** 价税合计 getter 方法 */
  public UFDouble getNestototalmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NESTOTOTALMNY);
  }

  /** 暂估单价 getter 方法 */
  public UFDouble getNestprice() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NESTPRICE);
  }

  /** 本币税额 getter 方法 */
  public UFDouble getNesttaxmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NESTTAXMNY);
  }

  /** 暂估含税单价 getter 方法 */
  public UFDouble getNesttaxprice() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NESTTAXPRICE);
  }

  /** 税率 getter 方法 */
  public UFDouble getNesttaxrate() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NESTTAXRATE);
  }

  /** 本币价税合计 getter 方法 */
  public UFDouble getNesttotalmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NESTTOTALMNY);
  }

  /** 费用本币无税金额 getter 方法 */
  public UFDouble getNfeemny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NFEEMNY);
  }

  /** 费用本币价税合计 getter 方法 */
  public UFDouble getNfeetaxmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NFEETAXMNY);
  }

  /** 全局本位币汇率 getter 方法 */
  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this
        .getAttributeValue(PurchaseinFIItemVO.NGLOBALEXCHGRATE);
  }

  /** 全局本币无税金额 getter 方法 */
  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NGLOBALMNY);
  }

  /** 全局本币价税合计 getter 方法 */
  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NGLOBALTAXMNY);
  }

  /** 集团本位币汇率 getter 方法 */
  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this
        .getAttributeValue(PurchaseinFIItemVO.NGROUPEXCHGRATE);
  }

  /** 集团本币无税金额 getter 方法 */
  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NGROUPMNY);
  }

  /** 集团本币价税合计 getter 方法 */
  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NGROUPTAXMNY);
  }

  /** 实入辅数量 getter 方法 */
  public UFDouble getNinassistnum() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NINASSISTNUM);
  }

  /** 入库主数量 getter 方法 */
  public UFDouble getNinnum() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NINNUM);
  }

  /** 入库单本币无税金额 getter 方法 */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NMNY);
  }

  /** 主本币无税净价 getter 方法 */
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NNETPRICE);
  }

  /** 不可抵扣税额 getter 方法 */
  public UFDouble getNnosubtax() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NNOSUBTAX);
  }

  /** 不可抵扣税率 getter 方法 */
  public UFDouble getNnosubtaxrate() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NNOSUBTAXRATE);
  }

  /** 入库单无税金额 getter 方法 */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NORIGMNY);
  }

  /** 主无税净价 getter 方法 */
  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NORIGNETPRICE);
  }

  /** 入库单主无税单价 getter 方法 */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NORIGPRICE);
  }

  /** 入库单价税合计 getter 方法 */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NORIGTAXMNY);
  }

  /** 主含税净价 getter 方法 */
  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this
        .getAttributeValue(PurchaseinFIItemVO.NORIGTAXNETPRICE);
  }

  /** 入库单主含税单价 getter 方法 */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NORIGTAXPRICE);
  }

  /** 计划金额 getter 方法 */
  public UFDouble getNplannedmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NPLANNEDMNY);
  }

  /** 计划单价 getter 方法 */
  public UFDouble getNplannedprice() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NPLANNEDPRICE);
  }

  /** 主本币无税单价 getter 方法 */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NPRICE);
  }

  /** 本币无税净价 getter 方法 */
  public UFDouble getNqtnetprice() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NQTNETPRICE);
  }

  /** 无税净价 getter 方法 */
  public UFDouble getNqtorignetprice() {
    return (UFDouble) this
        .getAttributeValue(PurchaseinFIItemVO.NQTORIGNETPRICE);
  }

  /** 无税单价 getter 方法 */
  public UFDouble getNqtorigprice() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NQTORIGPRICE);
  }

  /** 含税净价 getter 方法 */
  public UFDouble getNqtorigtaxnetprice() {
    return (UFDouble) this
        .getAttributeValue(PurchaseinFIItemVO.NQTORIGTAXNETPRICE);
  }

  /** 含税单价 getter 方法 */
  public UFDouble getNqtorigtaxprice() {
    return (UFDouble) this
        .getAttributeValue(PurchaseinFIItemVO.NQTORIGTAXPRICE);
  }

  /** 本币无税单价 getter 方法 */
  public UFDouble getNqtprice() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NQTPRICE);
  }

  /** 本币含税净价 getter 方法 */
  public UFDouble getNqttaxnetprice() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NQTTAXNETPRICE);
  }

  /** 本币含税单价 getter 方法 */
  public UFDouble getNqttaxprice() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NQTTAXPRICE);
  }

  /** 报价数量 getter 方法 */
  public UFDouble getNqtunitnum() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NQTUNITNUM);
  }

  /** 入库单本币税额 getter 方法 */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NTAX);
  }

  /** 入库单本币价税合计 getter 方法 */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NTAXMNY);
  }

  /** 主本币含税净价 getter 方法 */
  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NTAXNETPRICE);
  }

  /** 主本币含税单价 getter 方法 */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NTAXPRICE);
  }

  /** 入库单税率 getter 方法 */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NTAXRATE);
  }

  /** 总本币无税金额 getter 方法 */
  public UFDouble getNtotalmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NTOTALMNY);
  }

  /** 总本币价税合计 getter 方法 */
  public UFDouble getNtotaltaxmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NTOTALTAXMNY);
  }

  /** 应付财务组织 getter 方法 */
  public String getPk_apfinanceorg() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_APFINANCEORG);
  }

  /** 应付财务组织版本 getter 方法 */
  public String getPk_apfinanceorg_v() {
    return (String) this
        .getAttributeValue(PurchaseinFIItemVO.PK_APFINANCEORG_V);
  }

  /** 利润中心 getter 方法 */
  public String getPk_apliabcenter() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_APLIABCENTER);
  }

  /** 利润中心版本 getter 方法 */
  public String getPk_apliabcenter_v() {
    return (String) this
        .getAttributeValue(PurchaseinFIItemVO.PK_APLIABCENTER_V);
  }

  /** 收货利润中心最新版本 getter 方法 */
  public String getPk_arrliabcenter() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ARRLIABCENTER);
  }

  /** 收货利润中心 getter 方法 */
  public String getPk_arrliabcenter_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ARRLIABCENTER_V);
  }

  /** 批次档案 getter 方法 */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_BATCHCODE);
  }

  /** 传成本和应付人 getter 方法 */
  public String getPk_costappsn() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_COSTAPPSN);
  }

  /** 成本域 getter 方法 */
  public String getPk_costregion() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_COSTREGION);
  }

  /** 相应的直运销售订单行 getter 方法 */
  public String getPk_dtransalebid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_DTRANSALEBID);
  }

  /** 相应的直运销售订单 getter 方法 */
  public String getPk_dtransaleid() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_DTRANSALEID);
  }

  /** 币种 getter 方法 */
  public String getPk_estcurrency() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_ESTCURRENCY);
  }

  /** 结算财务组织 getter 方法 */
  public String getPk_financeorg() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_FINANCEORG);
  }

  /** 结算财务组织版本 getter 方法 */
  public String getPk_financeorg_v() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_FINANCEORG_V);
  }

  /** 结算完毕的结算单行明细 getter 方法 */
  public String getPk_finishsettle_b() {
    return (String) this
        .getAttributeValue(PurchaseinFIItemVO.PK_FINISHSETTLE_B);
  }

  /** 集团 getter 方法 */
  public String getPk_group() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_GROUP);
  }

  /** 开票供应商 getter 方法 */
  public String getPk_invcsupllier() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_INVCSUPLLIER);
  }

  /** 物料编码 getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_MATERIAL);
  }

  /** 订单 getter 方法 */
  public String getPk_order() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_ORDER);
  }

  /** 订单明细 getter 方法 */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_ORDER_B);
  }

  /** 库存组织 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_ORG);
  }

  /** 库存组织版本 getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_ORG_V);
  }

  /** 采购组织 getter 方法 */
  public String getPk_purchaseorg() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_PURCHASEORG);
  }

  /** 采购组织版本 getter 方法 */
  public String getPk_purchaseorg_v() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_PURCHASEORG_V);
  }

  /** 需求仓库 getter 方法 */
  public String getPk_reqstocdoc() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_REQSTOCDOC);
  }

  /** 需求库存组织 getter 方法 */
  public String getPk_reqstockorg() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_REQSTOCKORG);
  }

  /** 需求库存组织版本 getter 方法 */
  public String getPk_reqstockorg_v() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_REQSTOCKORG_V);
  }

  /** 物料 getter 方法 */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_SRCMATERIAL);
  }

  /** 库存财务体 getter 方法 */
  public String getPk_stockps() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_STOCKPS);
  }

  /** 采购入体ID getter 方法 */
  public String getPk_stockps_b() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_STOCKPS_B);
  }

  /** 供应商 getter 方法 */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_SUPPLIER);
  }

  /** 寄存供应商 getter 方法 */
  public String getPk_vmisupplier() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.PK_VMISUPPLIER);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(PurchaseinFIItemVO.TS);
  }

  /** 批次号 getter 方法 */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VBATCHCODE);
  }

  /** 自定义项1 getter 方法 */
  public String getVbdef1() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VBDEF1);
  }

  /** 自定义项10 getter 方法 */
  public String getVbdef10() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VBDEF10);
  }

  /** 自定义项11 getter 方法 */
  public String getVbdef11() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VBDEF11);
  }

  /** 自定义项12 getter 方法 */
  public String getVbdef12() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VBDEF12);
  }

  /** 自定义项13 getter 方法 */
  public String getVbdef13() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VBDEF13);
  }

  /** 自定义项14 getter 方法 */
  public String getVbdef14() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VBDEF14);
  }

  /** 自定义项15 getter 方法 */
  public String getVbdef15() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VBDEF15);
  }

  /** 自定义项16 getter 方法 */
  public String getVbdef16() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VBDEF16);
  }

  /** 自定义项17 getter 方法 */
  public String getVbdef17() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VBDEF17);
  }

  /** 自定义项18 getter 方法 */
  public String getVbdef18() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VBDEF18);
  }

  /** 自定义项19 getter 方法 */
  public String getVbdef19() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VBDEF19);
  }

  /** 自定义项2 getter 方法 */
  public String getVbdef2() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VBDEF2);
  }

  /** 自定义项20 getter 方法 */
  public String getVbdef20() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VBDEF20);
  }

  /** 自定义项3 getter 方法 */
  public String getVbdef3() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VBDEF3);
  }

  /** 自定义项4 getter 方法 */
  public String getVbdef4() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VBDEF4);
  }

  /** 自定义项5 getter 方法 */
  public String getVbdef5() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VBDEF5);
  }

  /** 自定义项6 getter 方法 */
  public String getVbdef6() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VBDEF6);
  }

  /** 自定义项7 getter 方法 */
  public String getVbdef7() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VBDEF7);
  }

  /** 自定义项8 getter 方法 */
  public String getVbdef8() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VBDEF8);
  }

  /** 自定义项9 getter 方法 */
  public String getVbdef9() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VBDEF9);
  }

  /** 换算率 getter 方法 */
  public String getVchangerate() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VCHANGERATE);
  }

  /** 采购合同号 getter 方法 */
  public String getVctcode() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VCTCODE);
  }

  /** 源头单据号 getter 方法 */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VFIRSTCODE);
  }

  /** 源头单据行号 getter 方法 */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VFIRSTROWNO);
  }

  /** 源头单据交易类型 getter 方法 */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VFIRSTTRANTYPE);
  }

  /** 自由辅助属性1 getter 方法 */
  public String getVfree1() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VFREE1);
  }

  /** 自由辅助属性10 getter 方法 */
  public String getVfree10() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VFREE10);
  }

  /** 自由辅助属性2 getter 方法 */
  public String getVfree2() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VFREE2);
  }

  /** 自由辅助属性3 getter 方法 */
  public String getVfree3() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VFREE3);
  }

  /** 自由辅助属性4 getter 方法 */
  public String getVfree4() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VFREE4);
  }

  /** 自由辅助属性5 getter 方法 */
  public String getVfree5() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VFREE5);
  }

  /** 自由辅助属性6 getter 方法 */
  public String getVfree6() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VFREE6);
  }

  /** 自由辅助属性7 getter 方法 */
  public String getVfree7() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VFREE7);
  }

  /** 自由辅助属性8 getter 方法 */
  public String getVfree8() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VFREE8);
  }

  /** 自由辅助属性9 getter 方法 */
  public String getVfree9() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VFREE9);
  }

  /** 行备注 getter 方法 */
  public String getVnotebody() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VNOTEBODY);
  }

  /** 订单号 getter 方法 */
  public String getVordercode() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VORDERCODE);
  }

  /** 订单交易类型 getter 方法 */
  public String getVordertrantypecode() {
    return (String) this
        .getAttributeValue(PurchaseinFIItemVO.VORDERTRANTYPECODE);
  }

  /** 报价单位换算率 getter 方法 */
  public String getVqtunitrate() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VQTUNITRATE);
  }

  /** 来源单据号 getter 方法 */
  public String getVsourcecode() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VSOURCECODE);
  }

  /** 来源单据行号 getter 方法 */
  public String getVsourcerowno() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VSOURCEROWNO);
  }

  /** 来源交易类型 getter 方法 */
  public String getVsourcetrantype() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VSOURCETRANTYPE);
  }

  /** 影响成本标志 **/
  public void setBaffectcost(UFBoolean baffectcost) {
    this.setAttributeValue(PurchaseinFIItemVO.BAFFECTCOST, baffectcost);
  }

  /** 影响利润中心成本标志 **/
  public void setBaffectpccost(UFBoolean baffectpccost) {
    this.setAttributeValue(PurchaseinFIItemVO.BAFFECTPCCOST, baffectpccost);
  }

  /** 是否赠品 setter 方法 */
  public void setBlargess(UFBoolean blargess) {
    this.setAttributeValue(PurchaseinFIItemVO.BLARGESS, blargess);
  }

  /** 逆向征税 setter 方法 */
  public void setBopptaxflag(UFBoolean bopptaxflag) {
    this.setAttributeValue(PurchaseinFIItemVO.BOPPTAXFLAG, bopptaxflag);
  }

  /** 是否结算完成 setter 方法 */
  public void setBsettlefinish(UFBoolean bsettlefinish) {
    this.setAttributeValue(PurchaseinFIItemVO.BSETTLEFINISH, bsettlefinish);
  }

  /** 客户 setter 方法 */
  public void setCasscustid(String casscustid) {
    this.setAttributeValue(PurchaseinFIItemVO.CASSCUSTID, casscustid);
  }

  /** 单位 setter 方法 */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(PurchaseinFIItemVO.CASTUNITID, castunitid);
  }

  /** 本位币 setter 方法 */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(PurchaseinFIItemVO.CCURRENCYID, ccurrencyid);
  }

  /** 目的地地区 setter 方法 */
  public void setCdestiareaid(String cdestiareaid) {
    this.setAttributeValue(PurchaseinFIItemVO.CDESTIAREAID, cdestiareaid);
  }

  /** 目的地国 setter 方法 */
  public void setCdesticountryid(String cdesticountryid) {
    this.setAttributeValue(PurchaseinFIItemVO.CDESTICOUNTRYID, cdesticountryid);
  }

  /** 税码 setter 方法 */
  public void setCesttaxcodeid(String cesttaxcodeid) {
    this.setAttributeValue(PurchaseinFIItemVO.CESTTAXCODEID, cesttaxcodeid);
  }

  /** 特征码 **/
  public void setCffileid(String cffileid) {
    this.setAttributeValue(PurchaseinFIItemVO.CFFILEID, cffileid);
  }

  /** 源头单据表体 setter 方法 */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(PurchaseinFIItemVO.CFIRSTBID, cfirstbid);
  }

  /** 源头单据表头 setter 方法 */
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(PurchaseinFIItemVO.CFIRSTID, cfirstid);
  }

  /** 源头单据类型 setter 方法 */
  public void setCfirsttypecode(String cfirsttypecode) {
    this.setAttributeValue(PurchaseinFIItemVO.CFIRSTTYPECODE, cfirsttypecode);
  }

  /** 原产地区 setter 方法 */
  public void setCorigareaid(String corigareaid) {
    this.setAttributeValue(PurchaseinFIItemVO.CORIGAREAID, corigareaid);
  }

  /** 原产国 setter 方法 */
  public void setCorigcountryid(String corigcountryid) {
    this.setAttributeValue(PurchaseinFIItemVO.CORIGCOUNTRYID, corigcountryid);
  }

  /** 原币币种 setter 方法 */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(PurchaseinFIItemVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /** 生产厂商 setter 方法 */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(PurchaseinFIItemVO.CPRODUCTORID, cproductorid);
  }

  /** 项目 setter 方法 */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(PurchaseinFIItemVO.CPROJECTID, cprojectid);
  }

  /** 项目任务 setter 方法 */
  public void setCprojecttaskid(String cprojecttaskid) {
    this.setAttributeValue(PurchaseinFIItemVO.CPROJECTTASKID, cprojecttaskid);
  }

  /** 报价单位 setter 方法 */
  public void setCqtunitid(String cqtunitid) {
    this.setAttributeValue(PurchaseinFIItemVO.CQTUNITID, cqtunitid);
  }

  /** 收货单位 setter 方法 */
  public void setCreceieveid(String creceieveid) {
    this.setAttributeValue(PurchaseinFIItemVO.CRECEIEVEID, creceieveid);
  }

  /** 行号 setter 方法 */
  public void setCrowno(String crowno) {
    this.setAttributeValue(PurchaseinFIItemVO.CROWNO, crowno);
  }

  /** 来源单据表体 setter 方法 */
  public void setCsourcebid(String csourcebid) {
    this.setAttributeValue(PurchaseinFIItemVO.CSOURCEBID, csourcebid);
  }

  /** 来源单据表头 setter 方法 */
  public void setCsourceid(String csourceid) {
    this.setAttributeValue(PurchaseinFIItemVO.CSOURCEID, csourceid);
  }

  /** 来源单据类型 setter 方法 */
  public void setCsourcetypecode(String csourcetypecode) {
    this.setAttributeValue(PurchaseinFIItemVO.CSOURCETYPECODE, csourcetypecode);
  }

  /** 库存状态 setter 方法 */
  public void setCstateid(String cstateid) {
    this.setAttributeValue(PurchaseinFIItemVO.CSTATEID, cstateid);
  }

  /** 税码 setter 方法 */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(PurchaseinFIItemVO.CTAXCODEID, ctaxcodeid);
  }

  /** 主单位 setter 方法 */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(PurchaseinFIItemVO.CUNITID, cunitid);
  }

  /** 业务日期 setter 方法 */
  public void setDbizdate(UFDate dbizdate) {
    this.setAttributeValue(PurchaseinFIItemVO.DBIZDATE, dbizdate);
  }

  /** 源头单据制单日期 setter 方法 */
  public void setDfirstbilldate(UFDate dfirstbilldate) {
    this.setAttributeValue(PurchaseinFIItemVO.DFIRSTBILLDATE, dfirstbilldate);
  }

  /** dr setter 方法 */
  public void setDr(Integer dr) {
    this.setAttributeValue(PurchaseinFIItemVO.DR, dr);
  }

  /** 需求日期 setter 方法 */
  public void setDrequiredate(UFDate drequiredate) {
    this.setAttributeValue(PurchaseinFIItemVO.DREQUIREDATE, drequiredate);
  }

  /** 暂估日期 setter 方法 */
  public void setDtocostapdate(UFDate dtocostapdate) {
    this.setAttributeValue(PurchaseinFIItemVO.DTOCOSTAPDATE, dtocostapdate);
  }

  /** 扣税类别 setter 方法 */
  public void setFesttaxtype(Integer festtaxtype) {
    this.setAttributeValue(PurchaseinFIItemVO.FESTTAXTYPE, festtaxtype);
  }

  /** 入库单扣税类别 setter 方法 */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(PurchaseinFIItemVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /** 传应付标志 setter 方法 */
  public void setFtoapflag(Integer ftoapflag) {
    this.setAttributeValue(PurchaseinFIItemVO.FTOAPFLAG, ftoapflag);
  }

  /** 传成本标志 setter 方法 */
  public void setFtoiaflag(Integer ftoiaflag) {
    this.setAttributeValue(PurchaseinFIItemVO.FTOIAFLAG, ftoiaflag);
  }

  /** 累计调整结算金额 setter 方法 */
  public void setNaccadjustmny(UFDouble naccadjustmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NACCADJUSTMNY, naccadjustmny);
  }

  /** 暂估部分累计结算数量 setter 方法 */
  public void setNaccestcoststtlnum(UFDouble naccestcoststtlnum) {
    this.setAttributeValue(PurchaseinFIItemVO.NACCESTCOSTSTTLNUM,
        naccestcoststtlnum);
  }

  /** 累计回冲暂估成本金额 setter 方法 */
  public void setNaccestcostwashmny(UFDouble naccestcostwashmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NACCESTCOSTWASHMNY,
        naccestcostwashmny);
  }

  /** 累计费用结算金额 setter 方法 */
  public void setNaccfeesettlemny(UFDouble naccfeesettlemny) {
    this.setAttributeValue(PurchaseinFIItemVO.NACCFEESETTLEMNY,
        naccfeesettlemny);
  }

  /** 累计货物结算金额 setter 方法 */
  public void setNaccgoodssettlemny(UFDouble naccgoodssettlemny) {
    this.setAttributeValue(PurchaseinFIItemVO.NACCGOODSSETTLEMNY,
        naccgoodssettlemny);
  }

  /** 暂估前累计结算金额 setter 方法 */
  public void setNaccpreeststtlmny(UFDouble naccpreeststtlmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NACCPREESTSTTLMNY,
        naccpreeststtlmny);
  }

  /** 累计结算金额 setter 方法 */
  public void setNaccsettlemny(UFDouble naccsettlemny) {
    this.setAttributeValue(PurchaseinFIItemVO.NACCSETTLEMNY, naccsettlemny);
  }

  /** 累计调整确认应付原币价税合计 setter 方法 */
  public void setNacctoapadjstotmny(UFDouble nacctoapadjstotmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NACCTOAPADJSTOTMNY,
        nacctoapadjstotmny);
  }

  /** 累计调整确认成本金额 setter 方法 */
  public void setNacctocostadjstmny(UFDouble nacctocostadjstmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NACCTOCOSTADJSTMNY,
        nacctocostadjstmny);
  }

  /** 累计结算数量 setter 方法 */
  public void setNaccumsettlenum(UFDouble naccumsettlenum) {
    this.setAttributeValue(PurchaseinFIItemVO.NACCUMSETTLENUM, naccumsettlenum);
  }

  /** 计成本金额 setter 方法 */
  public void setNcalcostmny(UFDouble ncalcostmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NCALCOSTMNY, ncalcostmny);
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
    this.setAttributeValue(PurchaseinFIItemVO.NCALCOSTPRICE, ncalcostprice);
  }

  /** 计税金额 setter 方法 */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NCALTAXMNY, ncaltaxmny);
  }

  /** 入库单折本汇率 setter 方法 */
  public void setNchangestdrate(UFDouble nchangestdrate) {
    this.setAttributeValue(PurchaseinFIItemVO.NCHANGESTDRATE, nchangestdrate);
  }

  /** 金额 setter 方法 */
  public void setNcostmny(UFDouble ncostmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NCOSTMNY, ncostmny);
  }

  /** 单价 setter 方法 */
  public void setNcostprice(UFDouble ncostprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NCOSTPRICE, ncostprice);
  }

  /** 计成本金额 setter 方法 */
  public void setNestcalcostmny(UFDouble nestcalcostmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NESTCALCOSTMNY, nestcalcostmny);
  }

  /**
   * 记成本单价
   * <p>
   * 使用场景：
   * <ul>
   * <li>wuxla v61
   * </ul>
   * 
   * @param nestcalcostprice
   */
  public void setNestcalcostprice(UFDouble nestcalcostprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NESTCALCOSTPRICE,
        nestcalcostprice);
  }

  /** 计税金额 setter 方法 */
  public void setNestcaltaxmny(UFDouble nestcaltaxmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NESTCALTAXMNY, nestcaltaxmny);
  }

  /** 折本汇率 setter 方法 */
  public void setNestexhgrate(UFDouble nestexhgrate) {
    this.setAttributeValue(PurchaseinFIItemVO.NESTEXHGRATE, nestexhgrate);
  }

  /** 本币无税金额 setter 方法 */
  public void setNestmny(UFDouble nestmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NESTMNY, nestmny);
  }

  /** 不可抵扣税额 setter 方法 */
  public void setNestnosubtax(UFDouble nestnosubtax) {
    this.setAttributeValue(PurchaseinFIItemVO.NESTNOSUBTAX, nestnosubtax);
  }

  /** 不可抵扣税率 setter 方法 */
  public void setNestnosubtaxrate(UFDouble nestnosubtaxrate) {
    this.setAttributeValue(PurchaseinFIItemVO.NESTNOSUBTAXRATE,
        nestnosubtaxrate);
  }

  /** 暂估主数量 setter 方法 */
  public void setNestnum(UFDouble nestnum) {
    this.setAttributeValue(PurchaseinFIItemVO.NESTNUM, nestnum);
  }

  /** 无税金额 setter 方法 */
  public void setNestomny(UFDouble nestomny) {
    this.setAttributeValue(PurchaseinFIItemVO.NESTOMNY, nestomny);
  }

  /** 主无税单价 setter 方法 */
  public void setNestoprice(UFDouble nestoprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NESTOPRICE, nestoprice);
  }

  /** 主含税单价 setter 方法 */
  public void setNestotaxprice(UFDouble nestotaxprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NESTOTAXPRICE, nestotaxprice);
  }

  /** 价税合计 setter 方法 */
  public void setNestototalmny(UFDouble nestototalmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NESTOTOTALMNY, nestototalmny);
  }

  /** 暂估单价 setter 方法 */
  public void setNestprice(UFDouble nestprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NESTPRICE, nestprice);
  }

  /** 本币税额 setter 方法 */
  public void setNesttaxmny(UFDouble nesttaxmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NESTTAXMNY, nesttaxmny);
  }

  /** 暂估含税单价 setter 方法 */
  public void setNesttaxprice(UFDouble nesttaxprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NESTTAXPRICE, nesttaxprice);
  }

  /** 税率 setter 方法 */
  public void setNesttaxrate(UFDouble nesttaxrate) {
    this.setAttributeValue(PurchaseinFIItemVO.NESTTAXRATE, nesttaxrate);
  }

  /** 本币价税合计 setter 方法 */
  public void setNesttotalmny(UFDouble nesttotalmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NESTTOTALMNY, nesttotalmny);
  }

  /** 费用本币无税金额 setter 方法 */
  public void setNfeemny(UFDouble nfeemny) {
    this.setAttributeValue(PurchaseinFIItemVO.NFEEMNY, nfeemny);
  }

  /** 费用本币价税合计 setter 方法 */
  public void setNfeetaxmny(UFDouble nfeetaxmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NFEETAXMNY, nfeetaxmny);
  }

  /** 全局本位币汇率 setter 方法 */
  public void setNglobalexchgrate(UFDouble nglobalexchgrate) {
    this.setAttributeValue(PurchaseinFIItemVO.NGLOBALEXCHGRATE,
        nglobalexchgrate);
  }

  /** 全局本币无税金额 setter 方法 */
  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NGLOBALMNY, nglobalmny);
  }

  /** 全局本币价税合计 setter 方法 */
  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  /** 集团本位币汇率 setter 方法 */
  public void setNgroupexchgrate(UFDouble ngroupexchgrate) {
    this.setAttributeValue(PurchaseinFIItemVO.NGROUPEXCHGRATE, ngroupexchgrate);
  }

  /** 集团本币无税金额 setter 方法 */
  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NGROUPMNY, ngroupmny);
  }

  /** 集团本币价税合计 setter 方法 */
  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  /** 实入辅数量 setter 方法 */
  public void setNinassistnum(UFDouble ninassistnum) {
    this.setAttributeValue(PurchaseinFIItemVO.NINASSISTNUM, ninassistnum);
  }

  /** 入库主数量 setter 方法 */
  public void setNinnum(UFDouble ninnum) {
    this.setAttributeValue(PurchaseinFIItemVO.NINNUM, ninnum);
  }

  /** 入库单本币无税金额 setter 方法 */
  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NMNY, nmny);
  }

  /** 主本币无税净价 setter 方法 */
  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NNETPRICE, nnetprice);
  }

  /** 不可抵扣税额 setter 方法 */
  public void setNnosubtax(UFDouble nnosubtax) {
    this.setAttributeValue(PurchaseinFIItemVO.NNOSUBTAX, nnosubtax);
  }

  /** 不可抵扣税率 setter 方法 */
  public void setNnosubtaxrate(UFDouble nnosubtaxrate) {
    this.setAttributeValue(PurchaseinFIItemVO.NNOSUBTAXRATE, nnosubtaxrate);
  }

  /** 入库单无税金额 setter 方法 */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NORIGMNY, norigmny);
  }

  /** 主无税净价 setter 方法 */
  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NORIGNETPRICE, norignetprice);
  }

  /** 入库单主无税单价 setter 方法 */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NORIGPRICE, norigprice);
  }

  /** 入库单价税合计 setter 方法 */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NORIGTAXMNY, norigtaxmny);
  }

  /** 主含税净价 setter 方法 */
  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NORIGTAXNETPRICE,
        norigtaxnetprice);
  }

  /** 入库单主含税单价 setter 方法 */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NORIGTAXPRICE, norigtaxprice);
  }

  /** 计划金额 setter 方法 */
  public void setNplannedmny(UFDouble nplannedmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NPLANNEDMNY, nplannedmny);
  }

  /** 计划单价 setter 方法 */
  public void setNplannedprice(UFDouble nplannedprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NPLANNEDPRICE, nplannedprice);
  }

  /** 主本币无税单价 setter 方法 */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NPRICE, nprice);
  }

  /** 本币无税净价 setter 方法 */
  public void setNqtnetprice(UFDouble nqtnetprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NQTNETPRICE, nqtnetprice);
  }

  /** 无税净价 setter 方法 */
  public void setNqtorignetprice(UFDouble nqtorignetprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NQTORIGNETPRICE, nqtorignetprice);
  }

  /** 无税单价 setter 方法 */
  public void setNqtorigprice(UFDouble nqtorigprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NQTORIGPRICE, nqtorigprice);
  }

  /** 含税净价 setter 方法 */
  public void setNqtorigtaxnetprice(UFDouble nqtorigtaxnetprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NQTORIGTAXNETPRICE,
        nqtorigtaxnetprice);
  }

  /** 含税单价 setter 方法 */
  public void setNqtorigtaxprice(UFDouble nqtorigtaxprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NQTORIGTAXPRICE, nqtorigtaxprice);
  }

  /** 本币无税单价 setter 方法 */
  public void setNqtprice(UFDouble nqtprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NQTPRICE, nqtprice);
  }

  /** 本币含税净价 setter 方法 */
  public void setNqttaxnetprice(UFDouble nqttaxnetprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NQTTAXNETPRICE, nqttaxnetprice);
  }

  /** 本币含税单价 setter 方法 */
  public void setNqttaxprice(UFDouble nqttaxprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NQTTAXPRICE, nqttaxprice);
  }

  /** 报价数量 setter 方法 */
  public void setNqtunitnum(UFDouble nqtunitnum) {
    this.setAttributeValue(PurchaseinFIItemVO.NQTUNITNUM, nqtunitnum);
  }

  /** 入库单本币税额 setter 方法 */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(PurchaseinFIItemVO.NTAX, ntax);
  }

  /** 入库单本币价税合计 setter 方法 */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NTAXMNY, ntaxmny);
  }

  /** 主本币含税净价 setter 方法 */
  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NTAXNETPRICE, ntaxnetprice);
  }

  /** 主本币含税单价 setter 方法 */
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(PurchaseinFIItemVO.NTAXPRICE, ntaxprice);
  }

  /** 入库单税率 setter 方法 */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(PurchaseinFIItemVO.NTAXRATE, ntaxrate);
  }

  /** 总本币无税金额 setter 方法 */
  public void setNtotalmny(UFDouble ntotalmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NTOTALMNY, ntotalmny);
  }

  /** 总本币价税合计 setter 方法 */
  public void setNtotaltaxmny(UFDouble ntotaltaxmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NTOTALTAXMNY, ntotaltaxmny);
  }

  /** 应付财务组织 setter 方法 */
  public void setPk_apfinanceorg(String pk_apfinanceorg) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_APFINANCEORG, pk_apfinanceorg);
  }

  /** 应付财务组织版本 setter 方法 */
  public void setPk_apfinanceorg_v(String pk_apfinanceorg_v) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_APFINANCEORG_V,
        pk_apfinanceorg_v);
  }

  /** 利润中心 setter 方法 */
  public void setPk_apliabcenter(String pk_apliabcenter) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_APLIABCENTER, pk_apliabcenter);
  }

  /** 利润中心版本 setter 方法 */
  public void setPk_apliabcenter_v(String pk_apliabcenter_v) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_APLIABCENTER_V,
        pk_apliabcenter_v);
  }

  /** 收货利润中心最新版本 setter方法 */
  public void setPk_arrliabcenter(String pk_arrliabcenter) {
    this.setAttributeValue(OrderItemVO.PK_ARRLIABCENTER, pk_arrliabcenter);
  }

  /** 收货利润中心 setter方法 */
  public void setPk_arrliabcenter_v(String pk_arrliabcenter_v) {
    this.setAttributeValue(OrderItemVO.PK_ARRLIABCENTER_V, pk_arrliabcenter_v);
  }

  /** 批次档案 setter 方法 */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_BATCHCODE, pk_batchcode);
  }

  /** 传成本和应付人 setter 方法 */
  public void setPk_costappsn(String pk_costappsn) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_COSTAPPSN, pk_costappsn);
  }

  /** 成本域 setter 方法 */
  public void setPk_costregion(String pk_costregion) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_COSTREGION, pk_costregion);
  }

  /** 相应的直运销售订单行 setter 方法 */
  public void setPk_dtransalebid(String pk_dtransalebid) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_DTRANSALEBID, pk_dtransalebid);
  }

  /** 相应的直运销售订单 setter 方法 */
  public void setPk_dtransaleid(String pk_dtransaleid) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_DTRANSALEID, pk_dtransaleid);
  }

  /** 币种 setter 方法 */
  public void setPk_estcurrency(String pk_estcurrency) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_ESTCURRENCY, pk_estcurrency);
  }

  /** 结算财务组织 setter 方法 */
  public void setPk_financeorg(String pk_financeorg) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_FINANCEORG, pk_financeorg);
  }

  /** 结算财务组织版本 setter 方法 */
  public void setPk_financeorg_v(String pk_financeorg_v) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_FINANCEORG_V, pk_financeorg_v);
  }

  /** 结算完毕的结算单行明细 setter 方法 */
  public void setPk_finishsettle_b(String pk_finishsettle_b) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_FINISHSETTLE_B,
        pk_finishsettle_b);
  }

  /** 集团 setter 方法 */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_GROUP, pk_group);
  }

  /** 开票供应商 setter 方法 */
  public void setPk_invcsupllier(String pk_invcsupllier) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_INVCSUPLLIER, pk_invcsupllier);
  }

  /** 物料编码 setter 方法 */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_MATERIAL, pk_material);
  }

  /** 订单 setter 方法 */
  public void setPk_order(String pk_order) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_ORDER, pk_order);
  }

  /** 订单明细 setter 方法 */
  public void setPk_order_b(String pk_order_b) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_ORDER_B, pk_order_b);
  }

  /** 库存组织 setter 方法 */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_ORG, pk_org);
  }

  /** 库存组织版本 setter 方法 */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_ORG_V, pk_org_v);
  }

  /** 采购组织 setter 方法 */
  public void setPk_purchaseorg(String pk_purchaseorg) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_PURCHASEORG, pk_purchaseorg);
  }

  /** 采购组织版本 setter 方法 */
  public void setPk_purchaseorg_v(String pk_purchaseorg_v) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_PURCHASEORG_V,
        pk_purchaseorg_v);
  }

  /** 需求仓库 setter 方法 */
  public void setPk_reqstocdoc(String pk_reqstocdoc) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_REQSTOCDOC, pk_reqstocdoc);
  }

  /** 需求库存组织 setter 方法 */
  public void setPk_reqstockorg(String pk_reqstockorg) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_REQSTOCKORG, pk_reqstockorg);
  }

  /** 需求库存组织版本 setter 方法 */
  public void setPk_reqstockorg_v(String pk_reqstockorg_v) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_REQSTOCKORG_V,
        pk_reqstockorg_v);
  }

  /** 物料 setter 方法 */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

  /** 库存财务体 setter 方法 */
  public void setPk_stockps(String pk_stockps) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_STOCKPS, pk_stockps);
  }

  /** 采购入体ID setter 方法 */
  public void setPk_stockps_b(String pk_stockps_b) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_STOCKPS_B, pk_stockps_b);
  }

  /** 供应商 setter 方法 */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_SUPPLIER, pk_supplier);
  }

  /** 寄存供应商 setter 方法 */
  public void setPk_vmisupplier(String pk_vmisupplier) {
    this.setAttributeValue(PurchaseinFIItemVO.PK_VMISUPPLIER, pk_vmisupplier);
  }

  /** ts setter 方法 */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(PurchaseinFIItemVO.TS, ts);
  }

  /** 批次号 setter 方法 */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(PurchaseinFIItemVO.VBATCHCODE, vbatchcode);
  }

  /** 自定义项1 setter 方法 */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(PurchaseinFIItemVO.VBDEF1, vbdef1);
  }

  /** 自定义项10 setter 方法 */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(PurchaseinFIItemVO.VBDEF10, vbdef10);
  }

  /** 自定义项11 setter 方法 */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(PurchaseinFIItemVO.VBDEF11, vbdef11);
  }

  /** 自定义项12 setter 方法 */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(PurchaseinFIItemVO.VBDEF12, vbdef12);
  }

  /** 自定义项13 setter 方法 */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(PurchaseinFIItemVO.VBDEF13, vbdef13);
  }

  /** 自定义项14 setter 方法 */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(PurchaseinFIItemVO.VBDEF14, vbdef14);
  }

  /** 自定义项15 setter 方法 */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(PurchaseinFIItemVO.VBDEF15, vbdef15);
  }

  /** 自定义项16 setter 方法 */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(PurchaseinFIItemVO.VBDEF16, vbdef16);
  }

  /** 自定义项17 setter 方法 */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(PurchaseinFIItemVO.VBDEF17, vbdef17);
  }

  /** 自定义项18 setter 方法 */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(PurchaseinFIItemVO.VBDEF18, vbdef18);
  }

  /** 自定义项19 setter 方法 */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(PurchaseinFIItemVO.VBDEF19, vbdef19);
  }

  /** 自定义项2 setter 方法 */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(PurchaseinFIItemVO.VBDEF2, vbdef2);
  }

  /** 自定义项20 setter 方法 */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(PurchaseinFIItemVO.VBDEF20, vbdef20);
  }

  /** 自定义项3 setter 方法 */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(PurchaseinFIItemVO.VBDEF3, vbdef3);
  }

  /** 自定义项4 setter 方法 */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(PurchaseinFIItemVO.VBDEF4, vbdef4);
  }

  /** 自定义项5 setter 方法 */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(PurchaseinFIItemVO.VBDEF5, vbdef5);
  }

  /** 自定义项6 setter 方法 */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(PurchaseinFIItemVO.VBDEF6, vbdef6);
  }

  /** 自定义项7 setter 方法 */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(PurchaseinFIItemVO.VBDEF7, vbdef7);
  }

  /** 自定义项8 setter 方法 */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(PurchaseinFIItemVO.VBDEF8, vbdef8);
  }

  /** 自定义项9 setter 方法 */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(PurchaseinFIItemVO.VBDEF9, vbdef9);
  }

  /** 换算率 setter 方法 */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(PurchaseinFIItemVO.VCHANGERATE, vchangerate);
  }

  /** 采购合同号 setter 方法 */
  public void setVctcode(String vctcode) {
    this.setAttributeValue(PurchaseinFIItemVO.VCTCODE, vctcode);
  }

  /** 源头单据号 setter 方法 */
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(PurchaseinFIItemVO.VFIRSTCODE, vfirstcode);
  }

  /** 源头单据行号 setter 方法 */
  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(PurchaseinFIItemVO.VFIRSTROWNO, vfirstrowno);
  }

  /** 源头单据交易类型 setter 方法 */
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(PurchaseinFIItemVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /** 自由辅助属性1 setter 方法 */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(PurchaseinFIItemVO.VFREE1, vfree1);
  }

  /** 自由辅助属性10 setter 方法 */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(PurchaseinFIItemVO.VFREE10, vfree10);
  }

  /** 自由辅助属性2 setter 方法 */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(PurchaseinFIItemVO.VFREE2, vfree2);
  }

  /** 自由辅助属性3 setter 方法 */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(PurchaseinFIItemVO.VFREE3, vfree3);
  }

  /** 自由辅助属性4 setter 方法 */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(PurchaseinFIItemVO.VFREE4, vfree4);
  }

  /** 自由辅助属性5 setter 方法 */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(PurchaseinFIItemVO.VFREE5, vfree5);
  }

  /** 自由辅助属性6 setter 方法 */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(PurchaseinFIItemVO.VFREE6, vfree6);
  }

  /** 自由辅助属性7 setter 方法 */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(PurchaseinFIItemVO.VFREE7, vfree7);
  }

  /** 自由辅助属性8 setter 方法 */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(PurchaseinFIItemVO.VFREE8, vfree8);
  }

  /** 自由辅助属性9 setter 方法 */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(PurchaseinFIItemVO.VFREE9, vfree9);
  }

  /** 行备注 setter 方法 */
  public void setVnotebody(String vnotebody) {
    this.setAttributeValue(PurchaseinFIItemVO.VNOTEBODY, vnotebody);
  }

  /** 订单号 setter 方法 */
  public void setVordercode(String vordercode) {
    this.setAttributeValue(PurchaseinFIItemVO.VORDERCODE, vordercode);
  }

  /** 订单交易类型 setter 方法 */
  public void setVordertrantypecode(String vordertrantypecode) {
    this.setAttributeValue(PurchaseinFIItemVO.VORDERTRANTYPECODE,
        vordertrantypecode);
  }

  /** 报价单位换算率 setter 方法 */
  public void setVqtunitrate(String vqtunitrate) {
    this.setAttributeValue(PurchaseinFIItemVO.VQTUNITRATE, vqtunitrate);
  }

  /** 来源单据号 setter 方法 */
  public void setVsourcecode(String vsourcecode) {
    this.setAttributeValue(PurchaseinFIItemVO.VSOURCECODE, vsourcecode);
  }

  /** 来源单据行号 setter 方法 */
  public void setVsourcerowno(String vsourcerowno) {
    this.setAttributeValue(PurchaseinFIItemVO.VSOURCEROWNO, vsourcerowno);
  }

  /** 来源交易类型 setter 方法 */
  public void setVsourcetrantype(String vsourcetrantype) {
    this.setAttributeValue(PurchaseinFIItemVO.VSOURCETRANTYPE, vsourcetrantype);
  }

}
