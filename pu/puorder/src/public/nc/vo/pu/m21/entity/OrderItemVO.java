package nc.vo.pu.m21.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <p>
 * <b>采购订单子表VO</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author donggq
 * @time 2009-6-16 下午07:02:42
 */
public class OrderItemVO extends nc.vo.pub.SuperVO {

  /** 到货关闭 */
  public static final String BARRIVECLOSE = "barriveclose";

  /** 借入转采购 */
  public static final String BBORROWPUR = "bborrowpur";

  /** 开票关闭 */
  public static final String BINVOICECLOSE = "binvoiceclose";

  /** 是否赠品 */
  public static final String BLARGESS = "blargess";

  /** 付款关闭 */
  public static final String BPAYCLOSE = "bpayclose";

  /** 存在到货计划 */
  public static final String BRECEIVEPLAN = "breceiveplan";

  /** 入库关闭 */
  public static final String BSTOCKCLOSE = "bstockclose";

  /** 是否运输关闭 */
  public static final String BTRANSCLOSED = "btransclosed";

  /** 三角贸易 */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /** 客户 */
  public static final String CASSCUSTID = "casscustid";

  /** 单位 */
  public static final String CASTUNITID = "castunitid";

  /** 合同信息 */
  public static final String CCONTRACTID = "ccontractid";

  /** 合同明细 */
  public static final String CCONTRACTROWID = "ccontractrowid";

  /** 本币币种 */
  public static final String CCURRENCYID = "ccurrencyid";

  /** 目的地地区 */
  public static final String CDESTIAREAID = "cdestiareaid";

  /** 目的地国 */
  public static final String CDESTICOUNTRYID = "cdesticountryid";

  /** 收货地点 */
  public static final String CDEVADDRID = "cdevaddrid";

  /** 收货地区 */
  public static final String CDEVAREAID = "cdevareaid";

  /** 电子商务单据明细 */
  public static final String CECBILLBID = "cecbillbid";

  /** 电子商务单据 */
  public static final String CECBILLID = "cecbillid";

  /** 电子商务单据类型 */
  public static final String CECTYPECODE = "cectypecode";

  /** 特征码 **/
  public static final String CFFILEID = "cffileid";

  /** 源头单据明细 */
  public static final String CFIRSTBID = "cfirstbid";

  /** 源头单据 */
  public static final String CFIRSTID = "cfirstid";

  /** 源头单据类型 */
  public static final String CFIRSTTYPECODE = "cfirsttypecode";

  /** 操作员 */
  public static final String CHANDLER = "chandler";

  /** 原产地区 */
  public static final String CORIGAREAID = "corigareaid";

  /** 原产国 */
  public static final String CORIGCOUNTRYID = "corigcountryid";

  /** 币种 */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /** 请购单表体主键 */
  public static final String CPRAYBILLBID = "cpraybillbid";

  /** 请购单号 */
  public static final String CPRAYBILLCODE = "cpraybillcode";

  /** 请购单表头主键 */
  public static final String CPRAYBILLHID = "cpraybillhid";

  /** 请购单行号 */
  public static final String CPRAYBILLROWNO = "cpraybillrowno";

  /** 请购单单据类型 */
  public static final String CPRAYTYPECODE = "cpraytypecode";

  /** 价格审批单存货子子表 */
  public static final String CPRICEAUDIT_BB1ID = "cpriceaudit_bb1id";

  /** 价格审批单存货明细 */
  public static final String CPRICEAUDIT_BID = "cpriceaudit_bid";

  /** 价格审批单 */
  public static final String CPRICEAUDITID = "cpriceauditid";

  /** 生产厂商 */
  public static final String CPRODUCTORID = "cproductorid";

  /** 项目 */
  public static final String CPROJECTID = "cprojectid";

  /** 项目任务 */
  public static final String CPROJECTTASKID = "cprojecttaskid";

  /** 优质优价方案 */
  public static final String CQPBASESCHEMEID = "cqpbaseschemeid";

  /** 报价单位 */
  public static final String CQTUNITID = "cqtunitid";

  /** 质量等级 */
  public static final String CQUALITYLEVELID = "cqualitylevelid";

  /** 收货国家/地区 */
  public static final String CRECECOUNTRYID = "crececountryid";

  /** 行号 */
  public static final String CROWNO = "crowno";

  /** 发货国/地区 */
  public static final String CSENDCOUNTRYID = "csendcountryid";

  /** 来源单据明细 */
  public static final String CSOURCEBID = "csourcebid";

  /** 来源单据 */
  public static final String CSOURCEID = "csourceid";

  /** 来源单据类型 */
  public static final String CSOURCETYPECODE = "csourcetypecode";

  /** 税码 */
  public static final String CTAXCODEID = "ctaxcodeid";

  /** 报税国/地区 */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /** 主单位 */
  public static final String CUNITID = "cunitid";

  /** 供应商发货地点 */
  public static final String CVENDDEVADDRID = "cvenddevaddrid";

  /** 供应商发货地区 */
  public static final String CVENDDEVAREAID = "cvenddevareaid";

  /** 订单日期 */
  public static final String DBILLDATE = "dbilldate";

  /** 确认日期 */
  public static final String DCONFIRMDATE = "dconfirmdate";

  /** 修正日期 */
  public static final String DCORRECTDATE = "dcorrectdate";

  /** 原始计划到货日期 */
  public static final String DORIGPLANARRVDATE = "dorigplanarrvdate";

  /** 计划到货日期 */
  public static final String DPLANARRVDATE = "dplanarrvdate";

  /** dr */
  public static final String DR = "dr";

  /** 实执采购类型 */
  public static final String FACTPURTYPE = "factpurtype";

  /** 购销类型 */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /** 激活 */
  public static final String FISACTIVE = "fisactive";

  /** 应执采购类型 */
  public static final String FNEEDPURTYPE = "fneedpurtype";

  /** 扣税类别 */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /** 供应商交货状态 */
  public static final String ISTORESTATUS = "istorestatus";

  /** 累计已核销本币开票金额 */
  public static final String NACCCANCELINVMNY = "nacccancelinvmny";

  /** 累计到货主数量 */
  public static final String NACCUMARRVNUM = "naccumarrvnum";

  /** 累计运输主数量 */
  public static final String NACCUMDEVNUM = "naccumdevnum";

  /** 累计本币开票金额 */
  public static final String NACCUMINVOICEMNY = "naccuminvoicemny";

  /** 累计开票主数量 */
  public static final String NACCUMINVOICENUM = "naccuminvoicenum";

  /** 累计拣货主数量 */
  public static final String NACCUMPICKUPNUM = "naccumpickupnum";

  /** 累计到货计划主数量 */
  public static final String NACCUMRPNUM = "naccumrpnum";

  /** 累计入库主数量 */
  public static final String NACCUMSTORENUM = "naccumstorenum";

  /** 累计途耗主数量 */
  public static final String NACCUMWASTNUM = "naccumwastnum";

  /** 数量 */
  public static final String NASTNUM = "nastnum";

  /** 累计退货主数量 */
  public static final String NBACKARRVNUM = "nbackarrvnum";

  /** 累计退库主数量 */
  public static final String NBACKSTORENUM = "nbackstorenum";

  /** 计成本金额 */
  public static final String NCALCOSTMNY = "ncalcostmny";

  /** 计税金额 */
  public static final String NCALTAXMNY = "ncaltaxmny";

  /** 可到货数量 */
  public static final String NCANARRIVENUM = "ncanarrivenum";

  /** 可入库数量 */
  public static final String NCANINNUM = "ncaninnum";

  /** 可开票数量 */
  public static final String NCANINVOICENUM = "ncaninvoicenum";

  /** 确认金额 */
  public static final String NCONFIRMMNY = "nconfirmmny";

  /** 确认数量 */
  public static final String NCONFIRMNUM = "nconfirmnum";

  /** 折本汇率 */
  public static final String NEXCHANGERATE = "nexchangerate";

  /** 费用累计开票金额 */
  public static final String NFEEMNY = "nfeemny";

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

  /** 折扣 */
  public static final String NITEMDISCOUNTRATE = "nitemdiscountrate";

  /** 最高限价 */
  public static final String NMAXPRICE = "nmaxprice";

  /** 本币无税金额 */
  public static final String NMNY = "nmny";

  /** 主本币无税净价 */
  public static final String NNETPRICE = "nnetprice";

  /** 未付款金额 */
  public static final String NNOPAYORGMNY = "nnopayorgmny";

  /** 不可抵扣税额 */
  public static final String NNOSUBTAX = "nnosubtax";

  /** 不可抵扣税率 */
  public static final String NNOSUBTAXRATE = "nnosubtaxrate";

  /** 主数量 */
  public static final String NNUM = "nnum";

  /** 无税金额 */
  public static final String NORIGMNY = "norigmny";

  /** 主无税净价 */
  public static final String NORIGNETPRICE = "norignetprice";

  /** 原始订单数量 */
  public static final String NORIGORDERNUM = "norigordernum";

  /** 原始订单净含税单价 */
  public static final String NORIGORDERPRICE = "norigorderprice";

  /** 主无税单价 */
  public static final String NORIGPRICE = "norigprice";

  /** 价税合计 */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /** 主含税净价 */
  public static final String NORIGTAXNETPRICE = "norigtaxnetprice";

  /** 主含税单价 */
  public static final String NORIGTAXPRICE = "norigtaxprice";

  /** 件数 */
  public static final String NPACKNUM = "npacknum";

  /** 主本币无税单价 */
  public static final String NPRICE = "nprice";

  /** 本币无税净价 */
  public static final String NQTNETPRICE = "nqtnetprice";

  /** 无税净价 */
  public static final String NQTORIGNETPRICE = "nqtorignetprice";

  /** 无税单价 */
  public static final String NQTORIGPRICE = "nqtorigprice";

  /** 含税净价 */
  public static final String NQTORIGTAXNETPRC = "nqtorigtaxnetprc";

  /** 含税单价 */
  public static final String NQTORIGTAXPRICE = "nqtorigtaxprice";

  // /** 税额 */ 2012-02-08 需求去掉此字段，本币税额改为税额
  // public static final String NORIGTAX = "norigtax";

  /** 报价本币无税单价 */
  public static final String NQTPRICE = "nqtprice";

  /** 本币含税净价 */
  public static final String NQTTAXNETPRICE = "nqttaxnetprice";

  /** 报价本币含税单价 */
  public static final String NQTTAXPRICE = "nqttaxprice";

  /** 报价数量 */
  public static final String NQTUNITNUM = "nqtunitnum";

  // modify by liangchen1 港华电商补丁
  /** 送货计划数量 */
  public static final String NSENDPLANNUM = "nsendplannum";

  /** 来源单据主数量 */
  public static final String NSOURCENUM = "nsourcenum";

  /** 被预留数量 */
  public static final String NSUPRSNUM = "nsuprsnum";

  /** 本币税额 */
  public static final String NTAX = "ntax";

  /** 本币价税合计 */
  public static final String NTAXMNY = "ntaxmny";

  /** 主本币含税净价 */
  public static final String NTAXNETPRICE = "ntaxnetprice";

  /** 主本币含税单价 */
  public static final String NTAXPRICE = "ntaxprice";

  /** 税率 */
  public static final String NTAXRATE = "ntaxrate";

  /** 体积 */
  public static final String NVOLUMN = "nvolumn";

  /** 重量 */
  public static final String NWEIGHT = "nweight";

  /** 应付组织最新版本 */
  public static final String PK_APFINANCEORG = "pk_apfinanceorg";

  /** 应付组织 */
  public static final String PK_APFINANCEORG_V = "pk_apfinanceorg_v";

  /** 结算利润中心最新版本 */
  public static final String PK_APLIABCENTER = "pk_apliabcenter";

  /** 结算利润中心 */
  public static final String PK_APLIABCENTER_V = "pk_apliabcenter_v";

  /** 收货利润中心最新版本 */
  public static final String PK_ARRLIABCENTER = "pk_arrliabcenter";

  /** 收货利润中心 */
  public static final String PK_ARRLIABCENTER_V = "pk_arrliabcenter_v";

  /** 收货库存组织最新版本 */
  public static final String PK_ARRVSTOORG = "pk_arrvstoorg";

  /** 收货库存组织 */
  public static final String PK_ARRVSTOORG_V = "pk_arrvstoorg_v";

  /** 批次号主键 */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /** 集采控制规则子表 */
  public static final String PK_CENPURULE_B = "pk_cenpurule_b";

  /** 折扣规则编码 */
  public static final String PK_DISCOUNT = "pk_discount";

  /** 物流组织最新版本 */
  public static final String PK_FLOWSTOCKORG = "pk_flowstockorg";

  /** 物流组织 */
  public static final String PK_FLOWSTOCKORG_V = "pk_flowstockorg_v";

  /** 所属集团 */
  public static final String PK_GROUP = "pk_group";

  /** 物料版本信息 */
  public static final String PK_MATERIAL = "pk_material";

  /** 采购订单明细 */
  public static final String PK_ORDER = "pk_order";

  /** 采购订单明细 */
  public static final String PK_ORDER_B = "pk_order_b";

  /** 采购组织版本信息 */
  public static final String PK_ORG = "pk_org";

  /** 采购组织 */
  public static final String PK_ORG_V = "pk_org_v";

  /** 结算财务组织最新版本 */
  public static final String PK_PSFINANCEORG = "pk_psfinanceorg";

  /** 结算财务组织 */
  public static final String PK_PSFINANCEORG_V = "pk_psfinanceorg_v";

  /** 收货地址 */
  public static final String PK_RECEIVEADDRESS = "pk_receiveaddress";

  /** 到货计划主键 */
  public static final String PK_RECEIVEPLAN = "pk_receiveplan";

  /** 收货仓库 */
  public static final String PK_RECVSTORDOC = "pk_recvstordoc";

  /** 需求公司 */
  public static final String PK_REQCORP = "pk_reqcorp";

  /** 需求部门最新版本 */
  public static final String PK_REQDEPT = "pk_reqdept";

  /** 需求部门 */
  public static final String PK_REQDEPT_V = "pk_reqdept_v";

  /** 需求库存组织最新版本 */
  public static final String PK_REQSTOORG = "pk_reqstoorg";

  /** 需求库存组织 */
  public static final String PK_REQSTOORG_V = "pk_reqstoorg_v";

  /** 需求仓库 */
  public static final String PK_REQSTORDOC = "pk_reqstordoc";

  /** 排程计划 */
  public static final String PK_SCHEDULE = "pk_schedule";

  /** 排程计划明细 */
  public static final String PK_SCHEDULE_B = "pk_schedule_b";

  /** 物料信息 */
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  /** 修订来源订单明细 */
  public static final String PK_SRCORDER_B = "pk_srcorder_b";

  /** 供应商 */
  public static final String PK_SUPPLIER = "pk_supplier";

  /** 来源单据行TS */
  public static final String SOURCEBTS = "sourcebts";

  /** 来源单据TS */
  public static final String SOURCETS = "sourcets";

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

  /** 备注 */
  public static final String VBMEMO = "vbmemo";

  /** 换算率 */
  public static final String VCHANGERATE = "vchangerate";

  /** 合同号 */
  public static final String VCONTRACTCODE = "vcontractcode";

  /** 电子商务单据号 */
  public static final String VECBILLCODE = "vecbillcode";

  /** 源头单据号 */
  public static final String VFIRSTCODE = "vfirstcode";

  /** 源头单据行号 */
  public static final String VFIRSTROWNO = "vfirstrowno";

  /** 源头交易类型 */
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

  /** 价格审批单号 */
  public static final String VPRICEAUDITCODE = "vpriceauditcode";

  /** 报价换算率 */
  public static final String VQTUNITRATE = "vqtunitrate";

  /** 来源单据号 */
  public static final String VSOURCECODE = "vsourcecode";

  /** 来源单据行号 */
  public static final String VSOURCEROWNO = "vsourcerowno";

  /** 来源交易类型 */
  public static final String VSOURCETRANTYPE = "vsourcetrantype";

  /** 供应商发货地址 */
  public static final String VVENDDEVADDR = "vvenddevaddr";

  /** 对应物料编码 */
  public static final String VVENDINVENTORYCODE = "vvendinventorycode";

  /** 对应物料名称 */
  public static final String VVENDINVENTORYNAME = "vvendinventoryname";

  /** 对方订单号 */
  public static final String VVENDORORDERCODE = "vvendorordercode";

  /** 对方订单行号 */
  public static final String VVENDORORDERROW = "vvendororderrow";

  private static final long serialVersionUID = -8877575473271036743L;

  /** 到货关闭 getter 方法 */
  public UFBoolean getBarriveclose() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BARRIVECLOSE);
  }

  /** 借入转采购 getter 方法 */
  public UFBoolean getBborrowpur() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BBORROWPUR);
  }

  /** 开票关闭 getter 方法 */
  public UFBoolean getBinvoiceclose() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BINVOICECLOSE);
  }

  /** 是否赠品 getter 方法 */
  public UFBoolean getBlargess() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BLARGESS);
  }

  /** 付款关闭 getter 方法 */
  public UFBoolean getBpayclose() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BPAYCLOSE);
  }

  /** 存在到货计划 getter 方法 */
  public UFBoolean getBreceiveplan() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BRECEIVEPLAN);
  }

  /** 入库关闭 getter 方法 */
  public UFBoolean getBstockclose() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BSTOCKCLOSE);
  }

  /** 是否运输关闭 getter 方法 */
  public UFBoolean getBtransclosed() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BTRANSCLOSED);
  }

  /** 三角贸易 getter 方法 */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BTRIATRADEFLAG);
  }

  /** 客户 getter 方法 */
  public String getCasscustid() {
    return (String) this.getAttributeValue(OrderItemVO.CASSCUSTID);
  }

  /** 单位 getter 方法 */
  public String getCastunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CASTUNITID);
  }

  /** 合同信息 getter 方法 */
  public String getCcontractid() {
    return (String) this.getAttributeValue(OrderItemVO.CCONTRACTID);
  }

  /** 合同明细 getter 方法 */
  public String getCcontractrowid() {
    return (String) this.getAttributeValue(OrderItemVO.CCONTRACTROWID);
  }

  /** 本币币种 getter 方法 */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(OrderItemVO.CCURRENCYID);
  }

  /** 目的地地区 getter 方法 */
  public String getCdestiareaid() {
    return (String) this.getAttributeValue(OrderItemVO.CDESTIAREAID);
  }

  /** 目的地国 getter 方法 */
  public String getCdesticountryid() {
    return (String) this.getAttributeValue(OrderItemVO.CDESTICOUNTRYID);
  }

  /** 收货地点 getter 方法 */
  public String getCdevaddrid() {
    return (String) this.getAttributeValue(OrderItemVO.CDEVADDRID);
  }

  /** 收货地区 getter 方法 */
  public String getCdevareaid() {
    return (String) this.getAttributeValue(OrderItemVO.CDEVAREAID);
  }

  /** 电子商务单据明细 getter 方法 */
  public String getCecbillbid() {
    return (String) this.getAttributeValue(OrderItemVO.CECBILLBID);
  }

  /** 电子商务单据 getter 方法 */
  public String getCecbillid() {
    return (String) this.getAttributeValue(OrderItemVO.CECBILLID);
  }

  /** 电子商务单据类型 getter 方法 */
  public String getCectypecode() {
    return (String) this.getAttributeValue(OrderItemVO.CECTYPECODE);
  }

  /** 特征码 **/
  public String getCffileid() {
    return (String) this.getAttributeValue(OrderItemVO.CFFILEID);
  }

  /** 源头单据明细 getter 方法 */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(OrderItemVO.CFIRSTBID);
  }

  /** 源头单据 getter 方法 */
  public String getCfirstid() {
    return (String) this.getAttributeValue(OrderItemVO.CFIRSTID);
  }

  /** 源头单据类型 getter 方法 */
  public String getCfirsttypecode() {
    return (String) this.getAttributeValue(OrderItemVO.CFIRSTTYPECODE);
  }

  /** 操作员 getter 方法 */
  public String getChandler() {
    return (String) this.getAttributeValue(OrderItemVO.CHANDLER);
  }

  /** 原产地区 getter 方法 */
  public String getCorigareaid() {
    return (String) this.getAttributeValue(OrderItemVO.CORIGAREAID);
  }

  /** 原产国 getter 方法 */
  public String getCorigcountryid() {
    return (String) this.getAttributeValue(OrderItemVO.CORIGCOUNTRYID);
  }

  /** 币种 getter 方法 */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(OrderItemVO.CORIGCURRENCYID);
  }

  /** 请购单表体主键 getter 方法 */
  public String getCpraybillbid() {
    return (String) this.getAttributeValue(OrderItemVO.CPRAYBILLBID);
  }

  /** 请购单号 getter 方法 */
  public String getCpraybillcode() {
    return (String) this.getAttributeValue(OrderItemVO.CPRAYBILLCODE);
  }

  /** 请购单表头主键 getter 方法 */
  public String getCpraybillhid() {
    return (String) this.getAttributeValue(OrderItemVO.CPRAYBILLHID);
  }

  /** 请购单行号 getter 方法 */
  public String getCpraybillrowno() {
    return (String) this.getAttributeValue(OrderItemVO.CPRAYBILLROWNO);
  }

  /** 请购单单据类型 */
  public String getCpraytypecode() {
    return (String) this.getAttributeValue(OrderItemVO.CPRAYTYPECODE);
  }

  /** 价格审批单存货子子表 getter 方法 */
  public String getCpriceaudit_bb1id() {
    return (String) this.getAttributeValue(OrderItemVO.CPRICEAUDIT_BB1ID);
  }

  /** 价格审批单存货明细 getter 方法 */
  public String getCpriceaudit_bid() {
    return (String) this.getAttributeValue(OrderItemVO.CPRICEAUDIT_BID);
  }

  /** 价格审批单 getter 方法 */
  public String getCpriceauditid() {
    return (String) this.getAttributeValue(OrderItemVO.CPRICEAUDITID);
  }

  /** 生产厂商 getter 方法 */
  public String getCproductorid() {
    return (String) this.getAttributeValue(OrderItemVO.CPRODUCTORID);
  }

  /** 项目 getter 方法 */
  public String getCprojectid() {
    return (String) this.getAttributeValue(OrderItemVO.CPROJECTID);
  }

  /** 项目任务 getter 方法 */
  public String getCprojecttaskid() {
    return (String) this.getAttributeValue(OrderItemVO.CPROJECTTASKID);
  }

  /** 优质优价方案 getter 方法 */
  public String getCqpbaseschemeid() {
    return (String) this.getAttributeValue(OrderItemVO.CQPBASESCHEMEID);
  }

  /** 报价单位 getter 方法 */
  public String getCqtunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CQTUNITID);
  }

  /** 质量等级 getter 方法 */
  public String getCqualitylevelid() {
    return (String) this.getAttributeValue(OrderItemVO.CQUALITYLEVELID);
  }

  /** 收货国家/地区 getter 方法 */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(OrderItemVO.CRECECOUNTRYID);
  }

  /** 行号 getter 方法 */
  public String getCrowno() {
    return (String) this.getAttributeValue(OrderItemVO.CROWNO);
  }

  /** 发货国/地区 getter 方法 */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(OrderItemVO.CSENDCOUNTRYID);
  }

  /** 来源单据明细 getter 方法 */
  public String getCsourcebid() {
    return (String) this.getAttributeValue(OrderItemVO.CSOURCEBID);
  }

  /** 来源单据 getter 方法 */
  public String getCsourceid() {
    return (String) this.getAttributeValue(OrderItemVO.CSOURCEID);
  }

  /** 来源单据类型 getter 方法 */
  public String getCsourcetypecode() {
    return (String) this.getAttributeValue(OrderItemVO.CSOURCETYPECODE);
  }

  /** 税码 getter 方法 */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(OrderItemVO.CTAXCODEID);
  }

  /** 报税国/地区 getter 方法 */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(OrderItemVO.CTAXCOUNTRYID);
  }

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CUNITID);
  }

  /** 供应商发货地点 getter 方法 */
  public String getCvenddevaddrid() {
    return (String) this.getAttributeValue(OrderItemVO.CVENDDEVADDRID);
  }

  /** 供应商发货地区 getter 方法 */
  public String getCvenddevareaid() {
    return (String) this.getAttributeValue(OrderItemVO.CVENDDEVAREAID);
  }

  /** 订单日期 getter 方法 */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(OrderItemVO.DBILLDATE);
  }

  /** 确认日期 getter 方法 */
  public UFDate getDconfirmdate() {
    return (UFDate) this.getAttributeValue(OrderItemVO.DCONFIRMDATE);
  }

  /** 修正日期 getter 方法 */
  public UFDate getDcorrectdate() {
    return (UFDate) this.getAttributeValue(OrderItemVO.DCORRECTDATE);
  }

  /** 原始计划到货日期 getter 方法 */
  public UFDate getDorigplanarrvdate() {
    return (UFDate) this.getAttributeValue(OrderItemVO.DORIGPLANARRVDATE);
  }

  /** 计划到货日期 getter 方法 */
  public UFDate getDplanarrvdate() {
    return (UFDate) this.getAttributeValue(OrderItemVO.DPLANARRVDATE);
  }

  /** dr getter 方法 */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(OrderItemVO.DR);
  }

  /** 实执采购类型 getter 方法 */
  public Integer getFactpurtype() {
    return (Integer) this.getAttributeValue(OrderItemVO.FACTPURTYPE);
  }

  /** 购销类型 getter 方法 */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(OrderItemVO.FBUYSELLFLAG);
  }

  /** 激活 getter 方法 */
  public Integer getFisactive() {
    return (Integer) this.getAttributeValue(OrderItemVO.FISACTIVE);
  }

  /** 应执采购类型 getter方法 */
  public Integer getFneedpurtype() {
    return (Integer) this.getAttributeValue(OrderItemVO.FNEEDPURTYPE);
  }

  /** 扣税类别 getter 方法 */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(OrderItemVO.FTAXTYPEFLAG);
  }

  /** 供应商交货状态 getter 方法 */
  public Integer getIstorestatus() {
    return (Integer) this.getAttributeValue(OrderItemVO.ISTORESTATUS);
  }

  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.M21_B);
  }

  /** 累计已核销本币开票金额 getter 方法 */
  public UFDouble getNacccancelinvmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCCANCELINVMNY);
  }

  /** 累计到货主数量 getter 方法 */
  public UFDouble getNaccumarrvnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMARRVNUM);
  }

  /** 累计运输主数量 getter 方法 */
  public UFDouble getNaccumdevnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMDEVNUM);
  }

  /** 累计本币开票金额 getter 方法 */
  public UFDouble getNaccuminvoicemny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMINVOICEMNY);
  }

  /** 累计开票主数量 getter 方法 */
  public UFDouble getNaccuminvoicenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMINVOICENUM);
  }

  /** 累计拣货主数量 getter 方法 */
  public UFDouble getNaccumpickupnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMPICKUPNUM);
  }

  /** 累计到货计划主数量 getter 方法 */
  public UFDouble getNaccumrpnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMRPNUM);
  }

  /** 累计入库主数量 getter 方法 */
  public UFDouble getNaccumstorenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMSTORENUM);
  }

  /** 累计途耗主数量 getter 方法 */
  public UFDouble getNaccumwastnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMWASTNUM);
  }

  /** 数量 getter 方法 */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NASTNUM);
  }

  /** 累计退货主数量 getter 方法 */
  public UFDouble getNbackarrvnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NBACKARRVNUM);
  }

  /** 累计退库主数量 getter 方法 */
  public UFDouble getNbackstorenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NBACKSTORENUM);
  }

  /** 计成本金额 getter 方法 */
  public UFDouble getNcalcostmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCALCOSTMNY);
  }

  /** 计税金额 getter 方法 */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCALTAXMNY);
  }

  /** 可到货数量 getter 方法 */
  public UFDouble getNcanarrivenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCANARRIVENUM);
  }

  /** 可入库数量 getter 方法 */
  public UFDouble getNcaninnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCANINNUM);
  }

  /** 可开票数量 getter 方法 */
  public UFDouble getNcaninvoicenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCANINVOICENUM);
  }

  /** 确认金额 getter 方法 */
  public UFDouble getNconfirmmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCONFIRMMNY);
  }

  /** 确认数量 getter 方法 */
  public UFDouble getNconfirmnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCONFIRMNUM);
  }

  /** 折本汇率 getter 方法 */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NEXCHANGERATE);
  }

  /** 费用累计开票金额 getter 方法 */
  public UFDouble getNfeemny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NFEEMNY);
  }

  /** 全局本位币汇率 getter 方法 */
  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NGLOBALEXCHGRATE);
  }

  /** 全局本币无税金额 getter 方法 */
  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NGLOBALMNY);
  }

  /** 全局本币价税合计 getter 方法 */
  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NGLOBALTAXMNY);
  }

  /** 集团本位币汇率 getter 方法 */
  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NGROUPEXCHGRATE);
  }

  /** 集团本币无税金额 getter 方法 */
  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NGROUPMNY);
  }

  /** 集团本币价税合计 getter 方法 */
  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NGROUPTAXMNY);
  }

  /** 折扣 getter 方法 */
  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NITEMDISCOUNTRATE);
  }

  /** 最高限价 getter 方法 */
  public UFDouble getNmaxprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NMAXPRICE);
  }

  /** 本币无税金额 getter 方法 */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NMNY);
  }

  /** 主本币无税净价 getter 方法 */
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNETPRICE);
  }

  /** 未付款金额 getter 方法 */
  public UFDouble getNnopayorgmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNOPAYORGMNY);
  }

  /** 不可抵扣税额 getter 方法 */
  public UFDouble getNnosubtax() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNOSUBTAX);
  }

  /** 不可抵扣税率 getter 方法 */
  public UFDouble getNnosubtaxrate() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNOSUBTAXRATE);
  }

  /** 主数量 getter 方法 */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNUM);
  }

  /** 无税金额 getter 方法 */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGMNY);
  }

  /** 主无税净价 getter 方法 */
  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGNETPRICE);
  }

  /** 原始订单数量 getter 方法 */
  public UFDouble getNorigordernum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGORDERNUM);
  }

  /** 原始订单净含税单价 getter 方法 */
  public UFDouble getNorigorderprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGORDERPRICE);
  }

  /** 主无税单价 getter 方法 */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGPRICE);
  }

  /** 价税合计 getter 方法 */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGTAXMNY);
  }

  /** 主含税净价 getter 方法 */
  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGTAXNETPRICE);
  }

  /** 主含税单价 getter 方法 */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGTAXPRICE);
  }

  /** 件数 getter 方法 */
  public UFDouble getNpacknum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NPACKNUM);
  }

  /** 主本币无税单价 getter 方法 */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NPRICE);
  }

  /** 本币无税净价 getter 方法 */
  public UFDouble getNqtnetprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTNETPRICE);
  }

  /** 无税净价 getter 方法 */
  public UFDouble getNqtorignetprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTORIGNETPRICE);
  }

  // /** 税额 getter 方法 */
  // public UFDouble getNorigtax() {
  // return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGTAX);
  // }

  /** 无税单价 getter 方法 */
  public UFDouble getNqtorigprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTORIGPRICE);
  }

  /** 含税净价 getter 方法 */
  public UFDouble getNqtorigtaxnetprc() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTORIGTAXNETPRC);
  }

  /** 含税单价 getter 方法 */
  public UFDouble getNqtorigtaxprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTORIGTAXPRICE);
  }

  /** 报价本币无税单价 getter 方法 */
  public UFDouble getNqtprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTPRICE);
  }

  /** 本币含税净价 getter 方法 */
  public UFDouble getNqttaxnetprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTTAXNETPRICE);
  }

  /** 报价本币含税单价 getter 方法 */
  public UFDouble getNqttaxprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTTAXPRICE);
  }

  /** 报价数量 getter 方法 */
  public UFDouble getNqtunitnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTUNITNUM);
  }

  // modify by liangchen1 港华电商补丁
  /** 送货计划数量getter 方法 */
  public UFDouble getNsendplannum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NSENDPLANNUM);
  }

  /** 来源单据主数量 getter 方法 */
  public UFDouble getNsourcenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NSOURCENUM);
  }

  /** 被预留数量 getter 方法 */
  public UFDouble getNsuprsnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NSUPRSNUM);
  }

  /** 本币税额 getter 方法 */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NTAX);
  }

  /** 本币价税合计 getter 方法 */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NTAXMNY);
  }

  /** 主本币含税净价 getter 方法 */
  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NTAXNETPRICE);
  }

  /** 主本币含税单价 getter 方法 */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NTAXPRICE);
  }

  /** 税率 getter 方法 */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NTAXRATE);
  }

  /** 体积 getter 方法 */
  public UFDouble getNvolumn() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NVOLUMN);
  }

  /** 重量 getter 方法 */
  public UFDouble getNweight() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NWEIGHT);
  }

  /** 应付组织最新版本 getter 方法 */
  public String getPk_apfinanceorg() {
    return (String) this.getAttributeValue(OrderItemVO.PK_APFINANCEORG);
  }

  /** 应付组织 getter 方法 */
  public String getPk_apfinanceorg_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_APFINANCEORG_V);
  }

  /** 结算利润中心最新版本 getter 方法 */
  public String getPk_apliabcenter() {
    return (String) this.getAttributeValue(OrderItemVO.PK_APLIABCENTER);
  }

  /** 结算利润中心 getter 方法 */
  public String getPk_apliabcenter_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_APLIABCENTER_V);
  }

  /** 收货利润中心最新版本 getter 方法 */
  public String getPk_arrliabcenter() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ARRLIABCENTER);
  }

  /** 收货利润中心 getter 方法 */
  public String getPk_arrliabcenter_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ARRLIABCENTER_V);
  }

  /** 收货库存组织最新版本 getter 方法 */
  public String getPk_arrvstoorg() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ARRVSTOORG);
  }

  /** 收货库存组织 getter 方法 */
  public String getPk_arrvstoorg_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ARRVSTOORG_V);
  }

  /** 批次号主键 getter 方法 */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(OrderItemVO.PK_BATCHCODE);
  }

  /** 集采控制规则子表 getter 方法 */
  public String getPk_cenpurule_b() {
    return (String) this.getAttributeValue(OrderItemVO.PK_CENPURULE_B);
  }

  /** 折扣规则编码 getter 方法 */
  public String getPk_discount() {
    return (String) this.getAttributeValue(OrderItemVO.PK_DISCOUNT);
  }

  /** 物流组织最新版本 getter 方法 */
  public String getPk_flowstockorg() {
    return (String) this.getAttributeValue(OrderItemVO.PK_FLOWSTOCKORG);
  }

  /** 物流组织 getter 方法 */
  public String getPk_flowstockorg_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_FLOWSTOCKORG_V);
  }

  /** 所属集团 getter 方法 */
  public String getPk_group() {
    return (String) this.getAttributeValue(OrderItemVO.PK_GROUP);
  }

  /** 物料版本信息 getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(OrderItemVO.PK_MATERIAL);
  }

  /** 采购订单明细 getter 方法 */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORDER);
  }

  /** 采购订单明细 getter 方法 */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORDER_B);
  }

  /** 采购组织版本信息 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORG);
  }

  /** 采购组织 getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORG_V);
  }

  /** 结算财务组织最新版本 getter 方法 */
  public String getPk_psfinanceorg() {
    return (String) this.getAttributeValue(OrderItemVO.PK_PSFINANCEORG);
  }

  /** 结算财务组织 getter 方法 */
  public String getPk_psfinanceorg_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_PSFINANCEORG_V);
  }

  /** 收货地址 getter 方法 */
  public String getPk_receiveaddress() {
    return (String) this.getAttributeValue(OrderItemVO.PK_RECEIVEADDRESS);
  }

  /** 到货计划主键 getter 方法 */
  public String getPk_receiveplan() {
    return (String) this.getAttributeValue(OrderItemVO.PK_RECEIVEPLAN);
  }

  /** 收货仓库 getter 方法 */
  public String getPk_recvstordoc() {
    return (String) this.getAttributeValue(OrderItemVO.PK_RECVSTORDOC);
  }

  /** 需求公司 getter 方法 */
  public String getPk_reqcorp() {
    return (String) this.getAttributeValue(OrderItemVO.PK_REQCORP);
  }

  /** 需求部门最新版本 getter 方法 */
  public String getPk_reqdept() {
    return (String) this.getAttributeValue(OrderItemVO.PK_REQDEPT);
  }

  /** 需求部门 getter 方法 */
  public String getPk_reqdept_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_REQDEPT_V);
  }

  /** 需求库存组织最新版本 getter 方法 */
  public String getPk_reqstoorg() {
    return (String) this.getAttributeValue(OrderItemVO.PK_REQSTOORG);
  }

  /** 需求库存组织 getter 方法 */
  public String getPk_reqstoorg_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_REQSTOORG_V);
  }

  /** 需求仓库 getter 方法 */
  public String getPk_reqstordoc() {
    return (String) this.getAttributeValue(OrderItemVO.PK_REQSTORDOC);
  }

  /** 排程计划 getter 方法 */
  public String getPk_schedule() {
    return (String) this.getAttributeValue(OrderItemVO.PK_SCHEDULE);
  }

  /** 排程计划明细 getter 方法 */
  public String getPk_schedule_b() {
    return (String) this.getAttributeValue(OrderItemVO.PK_SCHEDULE_B);
  }

  /** 物料信息 getter 方法 */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(OrderItemVO.PK_SRCMATERIAL);
  }

  /** 修订来源订单明细 getter 方法 */
  public String getPk_srcorder_b() {
    return (String) this.getAttributeValue(OrderItemVO.PK_SRCORDER_B);
  }

  /** 供应商 getter 方法 */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(OrderItemVO.PK_SUPPLIER);
  }

  /** 来源单据行TS getter 方法 */
  public UFDateTime getSourcebts() {
    return (UFDateTime) this.getAttributeValue(OrderItemVO.SOURCEBTS);
  }

  /** 来源单据TS getter 方法 */
  public UFDateTime getSourcets() {
    return (UFDateTime) this.getAttributeValue(OrderItemVO.SOURCETS);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(OrderItemVO.TS);
  }

  /** 批次号 getter 方法 */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(OrderItemVO.VBATCHCODE);
  }

  /** 自定义项1 getter 方法 */
  public String getVbdef1() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF1);
  }

  /** 自定义项10 getter 方法 */
  public String getVbdef10() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF10);
  }

  /** 自定义项11 getter 方法 */
  public String getVbdef11() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF11);
  }

  /** 自定义项12 getter 方法 */
  public String getVbdef12() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF12);
  }

  /** 自定义项13 getter 方法 */
  public String getVbdef13() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF13);
  }

  /** 自定义项14 getter 方法 */
  public String getVbdef14() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF14);
  }

  /** 自定义项15 getter 方法 */
  public String getVbdef15() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF15);
  }

  /** 自定义项16 getter 方法 */
  public String getVbdef16() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF16);
  }

  /** 自定义项17 getter 方法 */
  public String getVbdef17() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF17);
  }

  /** 自定义项18 getter 方法 */
  public String getVbdef18() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF18);
  }

  /** 自定义项19 getter 方法 */
  public String getVbdef19() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF19);
  }

  /** 自定义项2 getter 方法 */
  public String getVbdef2() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF2);
  }

  /** 自定义项20 getter 方法 */
  public String getVbdef20() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF20);
  }

  /** 自定义项3 getter 方法 */
  public String getVbdef3() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF3);
  }

  /** 自定义项4 getter 方法 */
  public String getVbdef4() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF4);
  }

  /** 自定义项5 getter 方法 */
  public String getVbdef5() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF5);
  }

  /** 自定义项6 getter 方法 */
  public String getVbdef6() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF6);
  }

  /** 自定义项7 getter 方法 */
  public String getVbdef7() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF7);
  }

  /** 自定义项8 getter 方法 */
  public String getVbdef8() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF8);
  }

  /** 自定义项9 getter 方法 */
  public String getVbdef9() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF9);
  }

  /** 备注 getter 方法 */
  public String getVbmemo() {
    return (String) this.getAttributeValue(OrderItemVO.VBMEMO);
  }

  /** 换算率 getter 方法 */
  public String getVchangerate() {
    return (String) this.getAttributeValue(OrderItemVO.VCHANGERATE);
  }

  /** 合同号 getter 方法 */
  public String getVcontractcode() {
    return (String) this.getAttributeValue(OrderItemVO.VCONTRACTCODE);
  }

  /** 电子商务单据号 getter 方法 */
  public String getVecbillcode() {
    return (String) this.getAttributeValue(OrderItemVO.VECBILLCODE);
  }

  /** 源头单据号 getter 方法 */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(OrderItemVO.VFIRSTCODE);
  }

  /** 源头单据行号 getter 方法 */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(OrderItemVO.VFIRSTROWNO);
  }

  /** 源头交易类型 getter 方法 */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(OrderItemVO.VFIRSTTRANTYPE);
  }

  /** 自由辅助属性1 getter 方法 */
  public String getVfree1() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE1);
  }

  /** 自由辅助属性10 getter 方法 */
  public String getVfree10() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE10);
  }

  /** 自由辅助属性2 getter 方法 */
  public String getVfree2() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE2);
  }

  /** 自由辅助属性3 getter 方法 */
  public String getVfree3() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE3);
  }

  /** 自由辅助属性4 getter 方法 */
  public String getVfree4() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE4);
  }

  /** 自由辅助属性5 getter 方法 */
  public String getVfree5() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE5);
  }

  /** 自由辅助属性6 getter 方法 */
  public String getVfree6() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE6);
  }

  /** 自由辅助属性7 getter 方法 */
  public String getVfree7() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE7);
  }

  /** 自由辅助属性8 getter 方法 */
  public String getVfree8() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE8);
  }

  /** 自由辅助属性9 getter 方法 */
  public String getVfree9() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE9);
  }

  /** 价格审批单号 getter 方法 */
  public String getVpriceauditcode() {
    return (String) this.getAttributeValue(OrderItemVO.VPRICEAUDITCODE);
  }

  /** 报价换算率 getter 方法 */
  public String getVqtunitrate() {
    return (String) this.getAttributeValue(OrderItemVO.VQTUNITRATE);
  }

  /** 来源单据号 getter 方法 */
  public String getVsourcecode() {
    return (String) this.getAttributeValue(OrderItemVO.VSOURCECODE);
  }

  /** 来源单据行号 getter 方法 */
  public String getVsourcerowno() {
    return (String) this.getAttributeValue(OrderItemVO.VSOURCEROWNO);
  }

  /** 来源交易类型 getter 方法 */
  public String getVsourcetrantype() {
    return (String) this.getAttributeValue(OrderItemVO.VSOURCETRANTYPE);
  }

  /** 供应商发货地址 getter 方法 */
  public String getVvenddevaddr() {
    return (String) this.getAttributeValue(OrderItemVO.VVENDDEVADDR);
  }

  /** 对应物料编码 getter 方法 */
  public String getVvendinventorycode() {
    return (String) this.getAttributeValue(OrderItemVO.VVENDINVENTORYCODE);
  }

  /** 对应物料名称 getter 方法 */
  public String getVvendinventoryname() {
    return (String) this.getAttributeValue(OrderItemVO.VVENDINVENTORYNAME);
  }

  /** 对方订单号 getter 方法 */
  public String getVvendorordercode() {
    return (String) this.getAttributeValue(OrderItemVO.VVENDORORDERCODE);
  }

  /** 对方订单行号 getter 方法 */
  public String getVvendororderrow() {
    return (String) this.getAttributeValue(OrderItemVO.VVENDORORDERROW);
  }

  /** 到货关闭 setter 方法 */
  public void setBarriveclose(UFBoolean barriveclose) {
    this.setAttributeValue(OrderItemVO.BARRIVECLOSE, barriveclose);
  }

  /** 借入转采购 setter 方法 */
  public void setBborrowpur(UFBoolean bborrowpur) {
    this.setAttributeValue(OrderItemVO.BBORROWPUR, bborrowpur);
  }

  /** 开票关闭 setter 方法 */
  public void setBinvoiceclose(UFBoolean binvoiceclose) {
    this.setAttributeValue(OrderItemVO.BINVOICECLOSE, binvoiceclose);
  }

  /** 是否赠品 setter 方法 */
  public void setBlargess(UFBoolean blargess) {
    this.setAttributeValue(OrderItemVO.BLARGESS, blargess);
  }

  /** 付款关闭 setter 方法 */
  public void setBpayclose(UFBoolean bpayclose) {
    this.setAttributeValue(OrderItemVO.BPAYCLOSE, bpayclose);
  }

  /** 存在到货计划 setter 方法 */
  public void setBreceiveplan(UFBoolean breceiveplan) {
    this.setAttributeValue(OrderItemVO.BRECEIVEPLAN, breceiveplan);
  }

  /** 入库关闭 setter 方法 */
  public void setBstockclose(UFBoolean bstockclose) {
    this.setAttributeValue(OrderItemVO.BSTOCKCLOSE, bstockclose);
  }

  /** 是否运输关闭 setter 方法 */
  public void setBtransclosed(UFBoolean btransclosed) {
    this.setAttributeValue(OrderItemVO.BTRANSCLOSED, btransclosed);
  }

  /** 三角贸易 setter 方法 */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(OrderItemVO.BTRIATRADEFLAG, btriatradeflag);
  }

  /** 客户 setter 方法 */
  public void setCasscustid(String casscustid) {
    this.setAttributeValue(OrderItemVO.CASSCUSTID, casscustid);
  }

  /** 单位 setter 方法 */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(OrderItemVO.CASTUNITID, castunitid);
  }

  /** 合同信息 setter 方法 */
  public void setCcontractid(String ccontractid) {
    this.setAttributeValue(OrderItemVO.CCONTRACTID, ccontractid);
  }

  /** 合同明细 setter 方法 */
  public void setCcontractrowid(String ccontractrowid) {
    this.setAttributeValue(OrderItemVO.CCONTRACTROWID, ccontractrowid);
  }

  /** 本币币种 setter 方法 */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(OrderItemVO.CCURRENCYID, ccurrencyid);
  }

  /** 目的地地区 setter 方法 */
  public void setCdestiareaid(String cdestiareaid) {
    this.setAttributeValue(OrderItemVO.CDESTIAREAID, cdestiareaid);
  }

  /** 目的地国 setter 方法 */
  public void setCdesticountryid(String cdesticountryid) {
    this.setAttributeValue(OrderItemVO.CDESTICOUNTRYID, cdesticountryid);
  }

  /** 收货地点 setter 方法 */
  public void setCdevaddrid(String cdevaddrid) {
    this.setAttributeValue(OrderItemVO.CDEVADDRID, cdevaddrid);
  }

  /** 收货地区 setter 方法 */
  public void setCdevareaid(String cdevareaid) {
    this.setAttributeValue(OrderItemVO.CDEVAREAID, cdevareaid);
  }

  /** 电子商务单据明细 setter 方法 */
  public void setCecbillbid(String cecbillbid) {
    this.setAttributeValue(OrderItemVO.CECBILLBID, cecbillbid);
  }

  /** 电子商务单据 setter 方法 */
  public void setCecbillid(String cecbillid) {
    this.setAttributeValue(OrderItemVO.CECBILLID, cecbillid);
  }

  /** 电子商务单据类型 setter 方法 */
  public void setCectypecode(String cectypecode) {
    this.setAttributeValue(OrderItemVO.CECTYPECODE, cectypecode);
  }

  /** 特征码 **/
  public void setCffileid(String cffileid) {
    this.setAttributeValue(OrderItemVO.CFFILEID, cffileid);
  }

  /** 源头单据明细 setter 方法 */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(OrderItemVO.CFIRSTBID, cfirstbid);
  }

  /** 源头单据 setter 方法 */
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(OrderItemVO.CFIRSTID, cfirstid);
  }

  /** 源头单据类型 setter 方法 */
  public void setCfirsttypecode(String cfirsttypecode) {
    this.setAttributeValue(OrderItemVO.CFIRSTTYPECODE, cfirsttypecode);
  }

  /** 操作员 setter 方法 */
  public void setChandler(String chandler) {
    this.setAttributeValue(OrderItemVO.CHANDLER, chandler);
  }

  /** 原产地区 setter 方法 */
  public void setCorigareaid(String corigareaid) {
    this.setAttributeValue(OrderItemVO.CORIGAREAID, corigareaid);
  }

  /** 原产国 setter 方法 */
  public void setCorigcountryid(String corigcountryid) {
    this.setAttributeValue(OrderItemVO.CORIGCOUNTRYID, corigcountryid);
  }

  /** 币种 setter 方法 */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(OrderItemVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /** 请购单表体主键 setter 方法 */
  public void setCpraybillbid(String cpraybillbid) {
    this.setAttributeValue(OrderItemVO.CPRAYBILLBID, cpraybillbid);
  }

  /** 请购单号 setter 方法 */
  public void setCpraybillcode(String cpraybillcode) {
    this.setAttributeValue(OrderItemVO.CPRAYBILLCODE, cpraybillcode);
  }

  /** 请购单表头主键 setter 方法 */
  public void setCpraybillhid(String cpraybillhid) {
    this.setAttributeValue(OrderItemVO.CPRAYBILLHID, cpraybillhid);
  }

  /** 请购单行号 setter 方法 */
  public void setCpraybillrowno(String cpraybillrowno) {
    this.setAttributeValue(OrderItemVO.CPRAYBILLROWNO, cpraybillrowno);
  }

  /** 请购单单据类型 */
  public void setCpraytypecode(String cpraytypecode) {
    this.setAttributeValue(OrderItemVO.CPRAYTYPECODE, cpraytypecode);
  }

  /** 价格审批单存货子子表 setter 方法 */
  public void setCpriceaudit_bb1id(String cpriceaudit_bb1id) {
    this.setAttributeValue(OrderItemVO.CPRICEAUDIT_BB1ID, cpriceaudit_bb1id);
  }

  /** 价格审批单存货明细 setter 方法 */
  public void setCpriceaudit_bid(String cpriceaudit_bid) {
    this.setAttributeValue(OrderItemVO.CPRICEAUDIT_BID, cpriceaudit_bid);
  }

  /** 价格审批单 setter 方法 */
  public void setCpriceauditid(String cpriceauditid) {
    this.setAttributeValue(OrderItemVO.CPRICEAUDITID, cpriceauditid);
  }

  /** 生产厂商 setter 方法 */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(OrderItemVO.CPRODUCTORID, cproductorid);
  }

  /** 项目 setter 方法 */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(OrderItemVO.CPROJECTID, cprojectid);
  }

  /** 项目任务 setter 方法 */
  public void setCprojecttaskid(String cprojecttaskid) {
    this.setAttributeValue(OrderItemVO.CPROJECTTASKID, cprojecttaskid);
  }

  /** 优质优价方案 setter 方法 */
  public void setCqpbaseschemeid(String cqpbaseschemeid) {
    this.setAttributeValue(OrderItemVO.CQPBASESCHEMEID, cqpbaseschemeid);
  }

  /** 报价单位 setter 方法 */
  public void setCqtunitid(String cqtunitid) {
    this.setAttributeValue(OrderItemVO.CQTUNITID, cqtunitid);
  }

  /** 质量等级 setter 方法 */
  public void setCqualitylevelid(String cqualitylevelid) {
    this.setAttributeValue(OrderItemVO.CQUALITYLEVELID, cqualitylevelid);
  }

  /** 收货国家/地区 setter 方法 */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(OrderItemVO.CRECECOUNTRYID, crececountryid);
  }

  /** 行号 setter 方法 */
  public void setCrowno(String crowno) {
    this.setAttributeValue(OrderItemVO.CROWNO, crowno);
  }

  /** 发货国/地区 setter 方法 */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(OrderItemVO.CSENDCOUNTRYID, csendcountryid);
  }

  /** 来源单据明细 setter 方法 */
  public void setCsourcebid(String csourcebid) {
    this.setAttributeValue(OrderItemVO.CSOURCEBID, csourcebid);
  }

  /** 来源单据 setter 方法 */
  public void setCsourceid(String csourceid) {
    this.setAttributeValue(OrderItemVO.CSOURCEID, csourceid);
  }

  /** 来源单据类型 setter 方法 */
  public void setCsourcetypecode(String csourcetypecode) {
    this.setAttributeValue(OrderItemVO.CSOURCETYPECODE, csourcetypecode);
  }

  /** 税码 setter 方法 */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(OrderItemVO.CTAXCODEID, ctaxcodeid);
  }

  /** 报税国/地区 setter 方法 */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(OrderItemVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  /** 主单位 setter 方法 */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(OrderItemVO.CUNITID, cunitid);
  }

  /** 供应商发货地点 setter 方法 */
  public void setCvenddevaddrid(String cvenddevaddrid) {
    this.setAttributeValue(OrderItemVO.CVENDDEVADDRID, cvenddevaddrid);
  }

  /** 供应商发货地区 setter 方法 */
  public void setCvenddevareaid(String cvenddevareaid) {
    this.setAttributeValue(OrderItemVO.CVENDDEVAREAID, cvenddevareaid);
  }

  /** 订单日期 setter 方法 */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(OrderItemVO.DBILLDATE, dbilldate);
  }

  /** 确认日期 setter 方法 */
  public void setDconfirmdate(UFDate dconfirmdate) {
    this.setAttributeValue(OrderItemVO.DCONFIRMDATE, dconfirmdate);
  }

  /** 修正日期 setter 方法 */
  public void setDcorrectdate(UFDate dcorrectdate) {
    this.setAttributeValue(OrderItemVO.DCORRECTDATE, dcorrectdate);
  }

  /** 原始计划到货日期 setter 方法 */
  public void setDorigplanarrvdate(UFDate dorigplanarrvdate) {
    this.setAttributeValue(OrderItemVO.DORIGPLANARRVDATE, dorigplanarrvdate);
  }

  /** 计划到货日期 setter 方法 */
  public void setDplanarrvdate(UFDate dplanarrvdate) {
    this.setAttributeValue(OrderItemVO.DPLANARRVDATE, dplanarrvdate);
  }

  /** dr setter 方法 */
  public void setDr(Integer dr) {
    this.setAttributeValue(OrderItemVO.DR, dr);
  }

  /** 实执采购类型 setter 方法 */
  public void setFactpurtype(Integer factpurtype) {
    this.setAttributeValue(OrderItemVO.FACTPURTYPE, factpurtype);
  }

  /** 购销类型 setter 方法 */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(OrderItemVO.FBUYSELLFLAG, fbuysellflag);
  }

  /** 激活 setter 方法 */
  public void setFisactive(Integer fisactive) {
    this.setAttributeValue(OrderItemVO.FISACTIVE, fisactive);
  }

  /** 应执采购类型 setter 方法 */
  public void setFneedpurtype(Integer fneedpurtype) {
    this.setAttributeValue(OrderItemVO.FNEEDPURTYPE, fneedpurtype);
  }

  /** 扣税类别 setter 方法 */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(OrderItemVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /** 供应商交货状态 setter 方法 */
  public void setIstorestatus(Integer istorestatus) {
    this.setAttributeValue(OrderItemVO.ISTORESTATUS, istorestatus);
  }

  /** 累计已核销本币开票金额 setter 方法 */
  public void setNacccancelinvmny(UFDouble nacccancelinvmny) {
    this.setAttributeValue(OrderItemVO.NACCCANCELINVMNY, nacccancelinvmny);
  }

  /** 累计到货主数量 setter 方法 */
  public void setNaccumarrvnum(UFDouble naccumarrvnum) {
    this.setAttributeValue(OrderItemVO.NACCUMARRVNUM, naccumarrvnum);
  }

  /** 累计运输主数量 setter 方法 */
  public void setNaccumdevnum(UFDouble naccumdevnum) {
    this.setAttributeValue(OrderItemVO.NACCUMDEVNUM, naccumdevnum);
  }

  /** 累计本币开票金额 setter 方法 */
  public void setNaccuminvoicemny(UFDouble naccuminvoicemny) {
    this.setAttributeValue(OrderItemVO.NACCUMINVOICEMNY, naccuminvoicemny);
  }

  /** 累计开票主数量 setter 方法 */
  public void setNaccuminvoicenum(UFDouble naccuminvoicenum) {
    this.setAttributeValue(OrderItemVO.NACCUMINVOICENUM, naccuminvoicenum);
  }

  /** 累计拣货主数量 setter 方法 */
  public void setNaccumpickupnum(UFDouble naccumpickupnum) {
    this.setAttributeValue(OrderItemVO.NACCUMPICKUPNUM, naccumpickupnum);
  }

  /** 累计到货计划主数量 setter 方法 */
  public void setNaccumrpnum(UFDouble naccumrpnum) {
    this.setAttributeValue(OrderItemVO.NACCUMRPNUM, naccumrpnum);
  }

  /** 累计入库主数量 setter 方法 */
  public void setNaccumstorenum(UFDouble naccumstorenum) {
    this.setAttributeValue(OrderItemVO.NACCUMSTORENUM, naccumstorenum);
  }

  /** 累计途耗主数量 setter 方法 */
  public void setNaccumwastnum(UFDouble naccumwastnum) {
    this.setAttributeValue(OrderItemVO.NACCUMWASTNUM, naccumwastnum);
  }

  /** 数量 setter 方法 */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(OrderItemVO.NASTNUM, nastnum);
  }

  /** 累计退货主数量 setter 方法 */
  public void setNbackarrvnum(UFDouble nbackarrvnum) {
    this.setAttributeValue(OrderItemVO.NBACKARRVNUM, nbackarrvnum);
  }

  /** 累计退库主数量 setter 方法 */
  public void setNbackstorenum(UFDouble nbackstorenum) {
    this.setAttributeValue(OrderItemVO.NBACKSTORENUM, nbackstorenum);
  }

  /** 计成本金额 setter 方法 */
  public void setNcalcostmny(UFDouble ncalcostmny) {
    this.setAttributeValue(OrderItemVO.NCALCOSTMNY, ncalcostmny);
  }

  /** 计税金额 setter 方法 */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(OrderItemVO.NCALTAXMNY, ncaltaxmny);
  }

  /** 可到货数量 setter 方法 */
  public void setNcanarrivenum(UFDouble ncanarrivenum) {
    this.setAttributeValue(OrderItemVO.NCANARRIVENUM, ncanarrivenum);
  }

  /** 可入库数量 setter 方法 */
  public void setNcaninnum(UFDouble ncaninnum) {
    this.setAttributeValue(OrderItemVO.NCANINNUM, ncaninnum);
  }

  /** 可开票数量 setter 方法 */
  public void setNcaninvoicenum(UFDouble ncaninvoicenum) {
    this.setAttributeValue(OrderItemVO.NCANINVOICENUM, ncaninvoicenum);
  }

  /** 确认金额 setter 方法 */
  public void setNconfirmmny(UFDouble nconfirmmny) {
    this.setAttributeValue(OrderItemVO.NCONFIRMMNY, nconfirmmny);
  }

  /** 确认数量 setter 方法 */
  public void setNconfirmnum(UFDouble nconfirmnum) {
    this.setAttributeValue(OrderItemVO.NCONFIRMNUM, nconfirmnum);
  }

  /** 折本汇率 setter 方法 */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(OrderItemVO.NEXCHANGERATE, nexchangerate);
  }

  /** 费用累计开票金额 setter 方法 */
  public void setNfeemny(UFDouble nfeemny) {
    this.setAttributeValue(OrderItemVO.NFEEMNY, nfeemny);
  }

  /** 全局本位币汇率 setter 方法 */
  public void setNglobalexchgrate(UFDouble nglobalexchgrate) {
    this.setAttributeValue(OrderItemVO.NGLOBALEXCHGRATE, nglobalexchgrate);
  }

  /** 全局本币无税金额 setter 方法 */
  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(OrderItemVO.NGLOBALMNY, nglobalmny);
  }

  /** 全局本币价税合计 setter 方法 */
  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(OrderItemVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  /** 集团本位币汇率 setter 方法 */
  public void setNgroupexchgrate(UFDouble ngroupexchgrate) {
    this.setAttributeValue(OrderItemVO.NGROUPEXCHGRATE, ngroupexchgrate);
  }

  /** 集团本币无税金额 setter 方法 */
  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(OrderItemVO.NGROUPMNY, ngroupmny);
  }

  /** 集团本币价税合计 setter 方法 */
  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(OrderItemVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  /** 折扣 setter 方法 */
  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(OrderItemVO.NITEMDISCOUNTRATE, nitemdiscountrate);
  }

  /** 最高限价 setter 方法 */
  public void setNmaxprice(UFDouble nmaxprice) {
    this.setAttributeValue(OrderItemVO.NMAXPRICE, nmaxprice);
  }

  /** 本币无税金额 setter 方法 */
  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(OrderItemVO.NMNY, nmny);
  }

  /** 主本币无税净价 setter 方法 */
  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(OrderItemVO.NNETPRICE, nnetprice);
  }

  /** 未付款金额 setter 方法 */
  public void setNnopayorgmny(UFDouble nnopayorgmny) {
    this.setAttributeValue(OrderItemVO.NNOPAYORGMNY, nnopayorgmny);
  }

  /** 不可抵扣税额 setter 方法 */
  public void setNnosubtax(UFDouble nnosubtax) {
    this.setAttributeValue(OrderItemVO.NNOSUBTAX, nnosubtax);
  }

  /** 不可抵扣税率 setter 方法 */
  public void setNnosubtaxrate(UFDouble nnosubtaxrate) {
    this.setAttributeValue(OrderItemVO.NNOSUBTAXRATE, nnosubtaxrate);
  }

  /** 主数量 setter 方法 */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(OrderItemVO.NNUM, nnum);
  }

  /** 无税金额 setter 方法 */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(OrderItemVO.NORIGMNY, norigmny);
  }

  /** 主无税净价 setter 方法 */
  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(OrderItemVO.NORIGNETPRICE, norignetprice);
  }

  /** 原始订单数量 setter 方法 */
  public void setNorigordernum(UFDouble norigordernum) {
    this.setAttributeValue(OrderItemVO.NORIGORDERNUM, norigordernum);
  }

  /** 原始订单净含税单价 setter 方法 */
  public void setNorigorderprice(UFDouble norigorderprice) {
    this.setAttributeValue(OrderItemVO.NORIGORDERPRICE, norigorderprice);
  }

  /** 主无税单价 setter 方法 */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(OrderItemVO.NORIGPRICE, norigprice);
  }

  /** 价税合计 setter 方法 */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(OrderItemVO.NORIGTAXMNY, norigtaxmny);
  }

  // /** 税额 setter 方法 */
  // public void setNorigtax(UFDouble norigtax) {
  // this.setAttributeValue(OrderItemVO.NORIGTAX, norigtax);
  // }

  /** 主含税净价 setter 方法 */
  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(OrderItemVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  /** 主含税单价 setter 方法 */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(OrderItemVO.NORIGTAXPRICE, norigtaxprice);
  }

  /** 件数 setter 方法 */
  public void setNpacknum(UFDouble npacknum) {
    this.setAttributeValue(OrderItemVO.NPACKNUM, npacknum);
  }

  /** 主本币无税单价 setter 方法 */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(OrderItemVO.NPRICE, nprice);
  }

  /** 本币无税净价 setter 方法 */
  public void setNqtnetprice(UFDouble nqtnetprice) {
    this.setAttributeValue(OrderItemVO.NQTNETPRICE, nqtnetprice);
  }

  /** 无税净价 setter 方法 */
  public void setNqtorignetprice(UFDouble nqtorignetprice) {
    this.setAttributeValue(OrderItemVO.NQTORIGNETPRICE, nqtorignetprice);
  }

  /** 无税单价 setter 方法 */
  public void setNqtorigprice(UFDouble nqtorigprice) {
    this.setAttributeValue(OrderItemVO.NQTORIGPRICE, nqtorigprice);
  }

  /** 含税净价 setter 方法 */
  public void setNqtorigtaxnetprc(UFDouble nqtorigtaxnetprc) {
    this.setAttributeValue(OrderItemVO.NQTORIGTAXNETPRC, nqtorigtaxnetprc);
  }

  /** 含税单价 setter 方法 */
  public void setNqtorigtaxprice(UFDouble nqtorigtaxprice) {
    this.setAttributeValue(OrderItemVO.NQTORIGTAXPRICE, nqtorigtaxprice);
  }

  /** 报价本币无税单价 setter 方法 */
  public void setNqtprice(UFDouble nqtprice) {
    this.setAttributeValue(OrderItemVO.NQTPRICE, nqtprice);
  }

  /** 本币含税净价 setter 方法 */
  public void setNqttaxnetprice(UFDouble nqttaxnetprice) {
    this.setAttributeValue(OrderItemVO.NQTTAXNETPRICE, nqttaxnetprice);
  }

  /** 报价本币含税单价 setter 方法 */
  public void setNqttaxprice(UFDouble nqttaxprice) {
    this.setAttributeValue(OrderItemVO.NQTTAXPRICE, nqttaxprice);
  }

  /** 报价数量 setter 方法 */
  public void setNqtunitnum(UFDouble nqtunitnum) {
    this.setAttributeValue(OrderItemVO.NQTUNITNUM, nqtunitnum);
  }

  /** 送货计划数量 setter 方法 */
  public void setNsendplannum(UFDouble nsendplannum) {
    this.setAttributeValue(OrderItemVO.NSENDPLANNUM, nsendplannum);
  }

  /** 来源单据主数量 setter 方法 */
  public void setNsourcenum(UFDouble nsourcenum) {
    this.setAttributeValue(OrderItemVO.NSOURCENUM, nsourcenum);
  }

  /** 被预留数量 setter 方法 */
  public void setNsuprsnum(UFDouble nsuprsnum) {
    this.setAttributeValue(OrderItemVO.NSUPRSNUM, nsuprsnum);
  }

  /** 本币税额 setter 方法 */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(OrderItemVO.NTAX, ntax);
  }

  /** 本币价税合计 setter 方法 */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(OrderItemVO.NTAXMNY, ntaxmny);
  }

  /** 主本币含税净价 setter 方法 */
  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(OrderItemVO.NTAXNETPRICE, ntaxnetprice);
  }

  /** 主本币含税单价 setter 方法 */
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(OrderItemVO.NTAXPRICE, ntaxprice);
  }

  /** 税率 setter 方法 */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(OrderItemVO.NTAXRATE, ntaxrate);
  }

  /** 体积 setter 方法 */
  public void setNvolumn(UFDouble nvolumn) {
    this.setAttributeValue(OrderItemVO.NVOLUMN, nvolumn);
  }

  /** 重量 setter 方法 */
  public void setNweight(UFDouble nweight) {
    this.setAttributeValue(OrderItemVO.NWEIGHT, nweight);
  }

  /** 应付组织最新版本 setter 方法 */
  public void setPk_apfinanceorg(String pk_apfinanceorg) {
    this.setAttributeValue(OrderItemVO.PK_APFINANCEORG, pk_apfinanceorg);
  }

  /** 应付组织 setter 方法 */
  public void setPk_apfinanceorg_v(String pk_apfinanceorg_v) {
    this.setAttributeValue(OrderItemVO.PK_APFINANCEORG_V, pk_apfinanceorg_v);
  }

  /** 结算利润中心最新版本 setter 方法 */
  public void setPk_apliabcenter(String pk_apliabcenter) {
    this.setAttributeValue(OrderItemVO.PK_APLIABCENTER, pk_apliabcenter);
  }

  /** 结算利润中心 setter 方法 */
  public void setPk_apliabcenter_v(String pk_apliabcenter_v) {
    this.setAttributeValue(OrderItemVO.PK_APLIABCENTER_V, pk_apliabcenter_v);
  }

  /** 收货利润中心最新版本 setter 方法 */
  public void setPk_arrliabcenter(String pk_arrliabcenter) {
    this.setAttributeValue(OrderItemVO.PK_ARRLIABCENTER, pk_arrliabcenter);
  }

  /** 收货利润中心 setter 方法 */
  public void setPk_arrliabcenter_v(String pk_arrliabcenter_v) {
    this.setAttributeValue(OrderItemVO.PK_ARRLIABCENTER_V, pk_arrliabcenter_v);
  }

  /** 收货库存组织最新版本 setter 方法 */
  public void setPk_arrvstoorg(String pk_arrvstoorg) {
    this.setAttributeValue(OrderItemVO.PK_ARRVSTOORG, pk_arrvstoorg);
  }

  /** 收货库存组织 setter 方法 */
  public void setPk_arrvstoorg_v(String pk_arrvstoorg_v) {
    this.setAttributeValue(OrderItemVO.PK_ARRVSTOORG_V, pk_arrvstoorg_v);
  }

  /** 批次号主键 setter 方法 */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(OrderItemVO.PK_BATCHCODE, pk_batchcode);
  }

  /** 集采控制规则子表 setter 方法 */
  public void setPk_cenpurule_b(String pk_cenpurule_b) {
    this.setAttributeValue(OrderItemVO.PK_CENPURULE_B, pk_cenpurule_b);
  }

  /** 折扣规则编码 setter 方法 */
  public void setPk_discount(String pk_discount) {
    this.setAttributeValue(OrderItemVO.PK_DISCOUNT, pk_discount);
  }

  /** 物流组织最新版本 setter 方法 */
  public void setPk_flowstockorg(String pk_flowstockorg) {
    this.setAttributeValue(OrderItemVO.PK_FLOWSTOCKORG, pk_flowstockorg);
  }

  /** 物流组织 setter 方法 */
  public void setPk_flowstockorg_v(String pk_flowstockorg_v) {
    this.setAttributeValue(OrderItemVO.PK_FLOWSTOCKORG_V, pk_flowstockorg_v);
  }

  /** 所属集团 setter 方法 */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(OrderItemVO.PK_GROUP, pk_group);
  }

  /** 物料版本信息 setter 方法 */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(OrderItemVO.PK_MATERIAL, pk_material);
  }

  /** 采购订单明细 setter 方法 */
  public void setPk_order(String pk_order) {
    this.setAttributeValue(OrderItemVO.PK_ORDER, pk_order);
  }

  /** 采购订单明细 setter 方法 */
  public void setPk_order_b(String pk_order_b) {
    this.setAttributeValue(OrderItemVO.PK_ORDER_B, pk_order_b);
  }

  /** 采购组织版本信息 setter 方法 */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(OrderItemVO.PK_ORG, pk_org);
  }

  /** 采购组织 setter 方法 */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(OrderItemVO.PK_ORG_V, pk_org_v);
  }

  /** 结算财务组织最新版本 setter 方法 */
  public void setPk_psfinanceorg(String pk_psfinanceorg) {
    this.setAttributeValue(OrderItemVO.PK_PSFINANCEORG, pk_psfinanceorg);
  }

  /** 结算财务组织 setter 方法 */
  public void setPk_psfinanceorg_v(String pk_psfinanceorg_v) {
    this.setAttributeValue(OrderItemVO.PK_PSFINANCEORG_V, pk_psfinanceorg_v);
  }

  /** 收货地址 setter 方法 */
  public void setPk_receiveaddress(String pk_receiveaddress) {
    this.setAttributeValue(OrderItemVO.PK_RECEIVEADDRESS, pk_receiveaddress);
  }

  /** 到货计划主键 setter 方法 */
  public void setPk_receiveplan(String pk_receiveplan) {
    this.setAttributeValue(OrderItemVO.PK_RECEIVEPLAN, pk_receiveplan);
  }

  /** 收货仓库 setter 方法 */
  public void setPk_recvstordoc(String pk_recvstordoc) {
    this.setAttributeValue(OrderItemVO.PK_RECVSTORDOC, pk_recvstordoc);
  }

  /** 需求公司 setter 方法 */
  public void setPk_reqcorp(String pk_reqcorp) {
    this.setAttributeValue(OrderItemVO.PK_REQCORP, pk_reqcorp);
  }

  /** 需求部门最新版本 setter 方法 */
  public void setPk_reqdept(String pk_reqdept) {
    this.setAttributeValue(OrderItemVO.PK_REQDEPT, pk_reqdept);
  }

  /** 需求部门 setter 方法 */
  public void setPk_reqdept_v(String pk_reqdept_v) {
    this.setAttributeValue(OrderItemVO.PK_REQDEPT_V, pk_reqdept_v);
  }

  /** 需求库存组织最新版本 setter 方法 */
  public void setPk_reqstoorg(String pk_reqstoorg) {
    this.setAttributeValue(OrderItemVO.PK_REQSTOORG, pk_reqstoorg);
  }

  /** 需求库存组织 setter 方法 */
  public void setPk_reqstoorg_v(String pk_reqstoorg_v) {
    this.setAttributeValue(OrderItemVO.PK_REQSTOORG_V, pk_reqstoorg_v);
  }

  /** 需求仓库 setter 方法 */
  public void setPk_reqstordoc(String pk_reqstordoc) {
    this.setAttributeValue(OrderItemVO.PK_REQSTORDOC, pk_reqstordoc);
  }

  /** 排程计划 setter 方法 */
  public void setPk_schedule(String pk_schedule) {
    this.setAttributeValue(OrderItemVO.PK_SCHEDULE, pk_schedule);
  }

  /** 排程计划明细 setter 方法 */
  public void setPk_schedule_b(String pk_schedule_b) {
    this.setAttributeValue(OrderItemVO.PK_SCHEDULE_B, pk_schedule_b);
  }

  /** 物料信息 setter 方法 */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(OrderItemVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

  /** 修订来源订单明细 setter 方法 */
  public void setPk_srcorder_b(String pk_srcorder_b) {
    this.setAttributeValue(OrderItemVO.PK_SRCORDER_B, pk_srcorder_b);
  }

  /** 供应商 setter 方法 */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(OrderItemVO.PK_SUPPLIER, pk_supplier);
  }

  /** 来源单据行TS setter 方法 */
  public void setSourcebts(UFDateTime sourcebts) {
    this.setAttributeValue(OrderItemVO.SOURCEBTS, sourcebts);
  }

  /** 来源单据TS setter 方法 */
  public void setSourcets(UFDateTime sourcets) {
    this.setAttributeValue(OrderItemVO.SOURCETS, sourcets);
  }

  /** ts setter 方法 */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(OrderItemVO.TS, ts);
  }

  /** 批次号 setter 方法 */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(OrderItemVO.VBATCHCODE, vbatchcode);
  }

  /** 自定义项1 setter 方法 */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(OrderItemVO.VBDEF1, vbdef1);
  }

  /** 自定义项10 setter 方法 */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(OrderItemVO.VBDEF10, vbdef10);
  }

  /** 自定义项11 setter 方法 */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(OrderItemVO.VBDEF11, vbdef11);
  }

  /** 自定义项12 setter 方法 */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(OrderItemVO.VBDEF12, vbdef12);
  }

  /** 自定义项13 setter 方法 */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(OrderItemVO.VBDEF13, vbdef13);
  }

  /** 自定义项14 setter 方法 */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(OrderItemVO.VBDEF14, vbdef14);
  }

  /** 自定义项15 setter 方法 */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(OrderItemVO.VBDEF15, vbdef15);
  }

  /** 自定义项16 setter 方法 */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(OrderItemVO.VBDEF16, vbdef16);
  }

  /** 自定义项17 setter 方法 */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(OrderItemVO.VBDEF17, vbdef17);
  }

  /** 自定义项18 setter 方法 */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(OrderItemVO.VBDEF18, vbdef18);
  }

  /** 自定义项19 setter 方法 */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(OrderItemVO.VBDEF19, vbdef19);
  }

  /** 自定义项2 setter 方法 */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(OrderItemVO.VBDEF2, vbdef2);
  }

  /** 自定义项20 setter 方法 */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(OrderItemVO.VBDEF20, vbdef20);
  }

  /** 自定义项3 setter 方法 */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(OrderItemVO.VBDEF3, vbdef3);
  }

  /** 自定义项4 setter 方法 */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(OrderItemVO.VBDEF4, vbdef4);
  }

  /** 自定义项5 setter 方法 */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(OrderItemVO.VBDEF5, vbdef5);
  }

  /** 自定义项6 setter 方法 */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(OrderItemVO.VBDEF6, vbdef6);
  }

  /** 自定义项7 setter 方法 */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(OrderItemVO.VBDEF7, vbdef7);
  }

  /** 自定义项8 setter 方法 */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(OrderItemVO.VBDEF8, vbdef8);
  }

  /** 自定义项9 setter 方法 */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(OrderItemVO.VBDEF9, vbdef9);
  }

  /** 备注 setter 方法 */
  public void setVbmemo(String vbmemo) {
    this.setAttributeValue(OrderItemVO.VBMEMO, vbmemo);
  }

  /** 换算率 setter 方法 */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(OrderItemVO.VCHANGERATE, vchangerate);
  }

  /** 合同号 setter 方法 */
  public void setVcontractcode(String vcontractcode) {
    this.setAttributeValue(OrderItemVO.VCONTRACTCODE, vcontractcode);
  }

  /** 电子商务单据号 setter 方法 */
  public void setVecbillcode(String vecbillcode) {
    this.setAttributeValue(OrderItemVO.VECBILLCODE, vecbillcode);
  }

  /** 源头单据号 setter 方法 */
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(OrderItemVO.VFIRSTCODE, vfirstcode);
  }

  /** 源头单据行号 setter 方法 */
  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(OrderItemVO.VFIRSTROWNO, vfirstrowno);
  }

  /** 源头交易类型 setter 方法 */
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(OrderItemVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /** 自由辅助属性1 setter 方法 */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(OrderItemVO.VFREE1, vfree1);
  }

  /** 自由辅助属性10 setter 方法 */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(OrderItemVO.VFREE10, vfree10);
  }

  /** 自由辅助属性2 setter 方法 */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(OrderItemVO.VFREE2, vfree2);
  }

  /** 自由辅助属性3 setter 方法 */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(OrderItemVO.VFREE3, vfree3);
  }

  /** 自由辅助属性4 setter 方法 */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(OrderItemVO.VFREE4, vfree4);
  }

  /** 自由辅助属性5 setter 方法 */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(OrderItemVO.VFREE5, vfree5);
  }

  /** 自由辅助属性6 setter 方法 */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(OrderItemVO.VFREE6, vfree6);
  }

  /** 自由辅助属性7 setter 方法 */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(OrderItemVO.VFREE7, vfree7);
  }

  /** 自由辅助属性8 setter 方法 */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(OrderItemVO.VFREE8, vfree8);
  }

  /** 自由辅助属性9 setter 方法 */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(OrderItemVO.VFREE9, vfree9);
  }

  /** 价格审批单号 setter 方法 */
  public void setVpriceauditcode(String vpriceauditcode) {
    this.setAttributeValue(OrderItemVO.VPRICEAUDITCODE, vpriceauditcode);
  }

  /** 报价换算率 setter 方法 */
  public void setVqtunitrate(String vqtunitrate) {
    this.setAttributeValue(OrderItemVO.VQTUNITRATE, vqtunitrate);
  }

  /** 来源单据号 setter 方法 */
  public void setVsourcecode(String vsourcecode) {
    this.setAttributeValue(OrderItemVO.VSOURCECODE, vsourcecode);
  }

  /** 来源单据行号 setter 方法 */
  public void setVsourcerowno(String vsourcerowno) {
    this.setAttributeValue(OrderItemVO.VSOURCEROWNO, vsourcerowno);
  }

  /** 来源交易类型 setter 方法 */
  public void setVsourcetrantype(String vsourcetrantype) {
    this.setAttributeValue(OrderItemVO.VSOURCETRANTYPE, vsourcetrantype);
  }

  /** 供应商发货地址 setter 方法 */
  public void setVvenddevaddr(String vvenddevaddr) {
    this.setAttributeValue(OrderItemVO.VVENDDEVADDR, vvenddevaddr);
  }

  /** 对应物料编码 setter 方法 */
  public void setVvendinventorycode(String vvendinventorycode) {
    this.setAttributeValue(OrderItemVO.VVENDINVENTORYCODE, vvendinventorycode);
  }

  /** 对应物料名称 setter 方法 */
  public void setVvendinventoryname(String vvendinventoryname) {
    this.setAttributeValue(OrderItemVO.VVENDINVENTORYNAME, vvendinventoryname);
  }

  /** 对方订单号 setter 方法 */
  public void setVvendorordercode(String vvendorordercode) {
    this.setAttributeValue(OrderItemVO.VVENDORORDERCODE, vvendorordercode);
  }

  /** 对方订单行号 setter 方法 */
  public void setVvendororderrow(String vvendororderrow) {
    this.setAttributeValue(OrderItemVO.VVENDORORDERROW, vvendororderrow);
  }

}
