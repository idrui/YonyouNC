package nc.vo.pu.m23.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单的表体VO类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 下午03:44:40
 */
public class ArriveItemVO extends SuperVO {

  /** 退货基于原订单补货 */
  public static final String BBACKREFORDER = "bbackreforder";

  /** 批次是否封存 */
  public static final String BC_BSEAL = "bc_bseal";

  /** 批次质量等级 */
  public static final String BC_CQUALITYLEVELID = "bc_cqualitylevelid";

  /** 批次库存状态 */
  public static final String BC_CSTATEID = "bc_cstateid";

  /** 批次形成时间 */
  public static final String BC_TBATCHTIME = "bc_tbatchtime";

  /** 检验时间 */
  public static final String BC_TCHECKTIME = "bc_tchecktime";

  /** 批次时间戳 */
  public static final String BC_TS = "bc_ts";

  /** 批次自定义项1 */
  public static final String BC_VDEF1 = "bc_vdef1";

  /** 批次自定义项10 */
  public static final String BC_VDEF10 = "bc_vdef10";

  /** 批次自定义项11 */
  public static final String BC_VDEF11 = "bc_vdef11";

  /** 批次自定义项12 */
  public static final String BC_VDEF12 = "bc_vdef12";

  /** 批次自定义项13 */
  public static final String BC_VDEF13 = "bc_vdef13";

  /** 批次自定义项14 */
  public static final String BC_VDEF14 = "bc_vdef14";

  /** 批次自定义项15 */
  public static final String BC_VDEF15 = "bc_vdef15";

  /** 批次自定义项16 */
  public static final String BC_VDEF16 = "bc_vdef16";

  /** 批次自定义项17 */
  public static final String BC_VDEF17 = "bc_vdef17";

  /** 批次自定义项18 */
  public static final String BC_VDEF18 = "bc_vdef18";

  /** 批次自定义项19 */
  public static final String BC_VDEF19 = "bc_vdef19";

  /** 批次自定义项2 */
  public static final String BC_VDEF2 = "bc_vdef2";

  /** 批次自定义项20 */
  public static final String BC_VDEF20 = "bc_vdef20";

  /** 批次自定义项3 */
  public static final String BC_VDEF3 = "bc_vdef3";

  /** 批次自定义项4 */
  public static final String BC_VDEF4 = "bc_vdef4";

  /** 批次自定义项5 */
  public static final String BC_VDEF5 = "bc_vdef5";

  /** 批次自定义项6 */
  public static final String BC_VDEF6 = "bc_vdef6";

  /** 批次自定义项7 */
  public static final String BC_VDEF7 = "bc_vdef7";

  /** 批次自定义项8 */
  public static final String BC_VDEF8 = "bc_vdef8";

  /** 批次自定义项9 */
  public static final String BC_VDEF9 = "bc_vdef9";

  /** 批次版本 */
  public static final String BC_VERSION = "bc_version";

  /** 批次散列码 */
  public static final String BC_VHASHCODE = "bc_vhashcode";

  /** 批次备注 */
  public static final String BC_VNOTE = "bc_vnote";

  /** 供应商批次号 */
  public static final String BC_VVENDBATCHCODE = "bc_vvendbatchcode";

  /**
   * 到货关闭（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BCLOSEORDER = "bcloseorder";

  /** 已生成资产片 */
  public static final String BFAFLAG = "bfaflag";

  /** 固定换算率 */
  public static final String BFIXEDRATE = "bfixedrate";

  /** 是否紧急放行状态 */
  public static final String BLETGOSTATE = "bletgostate";

  /** 赠品 */
  public static final String BPRESENT = "bpresent";

  /** 来源订单行是否赠品 */
  public static final String BPRESENTSOURCE = "bpresentsource";

  /** 已生成转固单 */
  public static final String BTRANSASSET = "btransasset";

  /** 三角贸易（为到采购入分单而加的字段） */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /** 客户 */
  public static final String CASSCUSTID = "casscustid";

  /** 单位 */
  public static final String CASTUNITID = "castunitid";

  /** 本币币种 */
  public static final String CCURRENCYID = "ccurrencyid";

  /** 特征码 **/
  public static final String CFFILEID = "cffileid";

  /** 源头单据明细 */
  public static final String CFIRSTBID = "cfirstbid";

  /** 源头单据 */
  public static final String CFIRSTID = "cfirstid";

  /** 源头单据类型 */
  public static final String CFIRSTTYPECODE = "cfirsttypecode";

  /** 原币币种 */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /** 紧急放行单行号 */
  public static final String CPASSBOLLROWNO = "cpassbollrowno";

  /** 生产厂商 */
  public static final String CPRODUCTORID = "cproductorid";

  /** 项目 */
  public static final String CPROJECTID = "cprojectid";

  /** 项目任务 */
  public static final String CPROJECTTASKID = "cprojecttaskid";

  /** 质量等级 */
  public static final String CQUALITYLEVELID = "cqualitylevelid";

  /** 收货国家/地区（为到采购入分单而加的字段） */
  public static final String CRECECOUNTRYID = "crececountryid";

  /** 报告人 */
  public static final String CREPORTERID = "creporterid";

  /** 行号 */
  public static final String CROWNO = "crowno";

  /** 发货国家/地区（为到采购入分单而加的字段） */
  public static final String CSENDCOUNTRYID = "csendcountryid";

  /** 来源到货单明细 */
  public static final String CSOURCEARRIVEBID = "csourcearrivebid";

  /** 来源到货单 */
  public static final String CSOURCEARRIVEID = "csourcearriveid";

  /** 来源单据明细 */
  public static final String CSOURCEBID = "csourcebid";

  /** 来源单据 */
  public static final String CSOURCEID = "csourceid";

  /** 来源单据类型 */
  public static final String CSOURCETYPECODE = "csourcetypecode";

  /** 报税国家/地区（为到采购入分单而加的字段） */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /** 主单位 */
  public static final String CUNITID = "cunitid";

  /** 到货日期 */
  public static final String DBILLDATE = "dbilldate";

  /** 失效日期 */
  public static final String DINVALIDDATE = "dinvaliddate";

  /** 计划到货日期 */
  public static final String DPLANRECEIVEDATE = "dplanreceivedate";

  /** 生产日期 */
  public static final String DPRODUCEDATE = "dproducedate";

  /** dr */
  public static final String DR = "dr";

  /** 报告日期 */
  public static final String DREPORTDATE = "dreportdate";

  /** 购销类型（为到采购入分单而加的字段） */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /** 产品类型 支持委外联副 */
  public static final String FPRODUCTCLASS = "fproductclass";

  /** 扣税类别 */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /** 保质期天数 */
  public static final String IVALIDDAY = "ivalidday";

  /** 累计退货主数量 */
  public static final String NACCUMBACKNUM = "naccumbacknum";

  /** 累计报检主数量 */
  public static final String NACCUMCHECKNUM = "naccumchecknum";

  /** 累计紧急放行入库主数量 */
  public static final String NACCUMLETGOINNUM = "naccumletgoinnum";

  /** 累计紧急放行主数量 */
  public static final String NACCUMLETGONUM = "naccumletgonum";

  /** 累计补货主数量 */
  public static final String NACCUMREPLNUM = "naccumreplnum";

  /** 累计入库主数量 */
  public static final String NACCUMSTORENUM = "naccumstorenum";

  /** 到货数量 */
  public static final String NASTNUM = "nastnum";

  /**
   * 可到货主数量（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String NCANARRIVENUM = "ncanarrivenum";

  /** 可补货主数量 */
  public static final String NCANREPLNUM = "ncanreplnum";

  /** 可入库主数量 */
  public static final String NCANSTORENUM = "ncanstorenum";

  /** 本次报检数量 */
  public static final String NCHECKNUM = "nchecknum";

  /** 累计合格主数量 */
  public static final String NELIGNUM = "nelignum";

  /** 折本汇率 */
  public static final String NEXCHANGERATE = "nexchangerate";

  /** 本币无税金额 */
  public static final String NMNY = "nmny";

  /** 累计不合格主数量 */
  public static final String NNOTELIGNUM = "nnotelignum";

  /** 到货主数量 */
  public static final String NNUM = "nnum";

  /** 原币无税金额 */
  public static final String NORIGMNY = "norigmny";

  /** 主原币无税单价 */
  public static final String NORIGPRICE = "norigprice";

  /** 原币含税金额 */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /** 主原币含税单价 */
  public static final String NORIGTAXPRICE = "norigtaxprice";

  /** 应到数量 */
  public static final String NPLANASTNUM = "nplanastnum";

  /** 应到主数量 */
  public static final String NPLANNUM = "nplannum";

  /** 赠品数量 */
  public static final String NPRESENTASTNUM = "npresentastnum";

  /** 赠品主数量 */
  public static final String NPRESENTNUM = "npresentnum";

  /** 主本币无税单价 */
  public static final String NPRICE = "nprice";

  /** 来源单据主数量 */
  public static final String NSOURCENUM = "nsourcenum";

  /** 本币税额 */
  public static final String NTAX = "ntax";

  /** 本币含税金额 */
  public static final String NTAXMNY = "ntaxmny";

  /** 主本币含税单价 */
  public static final String NTAXPRICE = "ntaxprice";

  /** 税率 */
  public static final String NTAXRATE = "ntaxrate";

  /** 途耗数量 */
  public static final String NWASTASTNUM = "nwastastnum";

  /** 途耗主数量 */
  public static final String NWASTNUM = "nwastnum";

  /** 合格主数量 */
  public static final String NWILLELIGNUM = "nwillelignum";

  /** 不合格主数量 */
  public static final String NWILLNOTELIGNUM = "nwillnotelignum";

  /** 应付组织最新版本 */
  public static final String PK_APFINANCEORG = "pk_apfinanceorg";

  /** 应付组织 */
  public static final String PK_APFINANCEORG_V = "pk_apfinanceorg_v";

  /** 结算利润中心最新版本 */
  public static final String PK_APLIABCENTER = "pk_apliabcenter";

  /** 结算利润中心 */
  public static final String PK_APLIABCENTER_V = "pk_apliabcenter_v";

  /** 到货单明细 */
  public static final String PK_ARRIVEORDER = "pk_arriveorder";

  /** 到货单明细 */
  public static final String PK_ARRIVEORDER_B = "pk_arriveorder_b";

  /** 收货利润中心最新版本 */
  public static final String PK_ARRLIABCENTER = "pk_arrliabcenter";

  /** 收货利润中心 */
  public static final String PK_ARRLIABCENTER_V = "pk_arrliabcenter_v";

  /** 批次号主键 */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /** 检验明细主键 */
  public static final String PK_CHECKDETAIL = "pk_checkdetail";

  /** 集团 */
  public static final String PK_GROUP = "pk_group";

  /** 物料VID */
  public static final String PK_MATERIAL = "pk_material";

  /** 采购订单 */
  public static final String PK_ORDER = "pk_order";

  /** 采购订单明细 */
  public static final String PK_ORDER_B = "pk_order_b";

  /** 订单到货计划 */
  public static final String PK_ORDER_BB1 = "pk_order_bb1";

  /** 库存组织最新版本 */
  public static final String PK_ORG = "pk_org";

  /** 库存组织 */
  public static final String PK_ORG_V = "pk_org_v";

  /** 紧急放行单主键 */
  public static final String PK_PASSBILL = "pk_passbill";

  /** 紧急放行单表体主键 */
  public static final String PK_PASSBILL_B = "pk_passbill_b";

  /** 结算财务组织最新版本 */
  public static final String PK_PSFINANCEORG = "pk_psfinanceorg";

  /** 结算财务组织 */
  public static final String PK_PSFINANCEORG_V = "pk_psfinanceorg_v";

  /** 质检中心 */
  public static final String PK_QCCENTER = "pk_qccenter";

  /** 货位 */
  public static final String PK_RACK = "pk_rack";

  /** 收货仓库 */
  public static final String PK_RECEIVESTORE = "pk_receivestore";

  /** 需求库存组织最新版本 */
  public static final String PK_REQSTOORG = "pk_reqstoorg";

  /** 需求库存组织 */
  public static final String PK_REQSTOORG_V = "pk_reqstoorg_v";

  /** 需求仓库 */
  public static final String PK_REQSTORE = "pk_reqstore";

  /** 物料OID */
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  /** 来源单据行TS */
  public static final String SOURCEBTS = "sourcebts";

  /** 来源单据TS */
  public static final String SOURCETS = "sourcets";

  /** ts */
  public static final String TS = "ts";

  /** 退货理由 */
  public static final String VBACKREASONB = "vbackreasonb";

  /** 批次号*/
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

  /** 备注 */
  public static final String VMEMOB = "vmemob";

  /** 紧急放行单号 */
  public static final String VPASSBILLCODE = "vpassbillcode";

  /** 生产批次号 */
  public static final String VPRODBATCHCODE = "vprodbatchcode";

  /** 来源单据号 */
  public static final String VSOURCECODE = "vsourcecode";

  /** 来源单据行号 */
  public static final String VSOURCEROWNO = "vsourcerowno";

  /** 来源交易类型 */
  public static final String VSOURCETRANTYPE = "vsourcetrantype";

  private static final long serialVersionUID = 1L;

  /** 退货基于原订单补货 getter 方法 */
  public UFBoolean getBbackreforder() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BBACKREFORDER);
  }

  /** 批次是否封存 getter 方法 */
  public UFBoolean getBc_bseal() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BC_BSEAL);
  }

  /** 批次质量等级 getter 方法 */
  public String getBc_cqualitylevelid() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_CQUALITYLEVELID);
  }

  /** 批次库存状态 getter 方法 */
  public String getBc_cstateid() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_CSTATEID);
  }

  /** 批次形成时间 getter 方法 */
  public UFDateTime getBc_tbatchtime() {
    return (UFDateTime) this.getAttributeValue(ArriveItemVO.BC_TBATCHTIME);
  }

  /** 检验时间 getter 方法 */
  public UFDateTime getBc_tchecktime() {
    return (UFDateTime) this.getAttributeValue(ArriveItemVO.BC_TCHECKTIME);
  }

  /** 批次时间戳 getter 方法 */
  public UFDateTime getBc_ts() {
    return (UFDateTime) this.getAttributeValue(ArriveItemVO.BC_TS);
  }

  /** 批次自定义项1 getter 方法 */
  public String getBc_vdef1() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF1);
  }

  /** 批次自定义项10 getter 方法 */
  public String getBc_vdef10() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF10);
  }

  /** 批次自定义项11 getter 方法 */
  public String getBc_vdef11() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF11);
  }

  /** 批次自定义项12 getter 方法 */
  public String getBc_vdef12() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF12);
  }

  /** 批次自定义项13 getter 方法 */
  public String getBc_vdef13() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF13);
  }

  /** 批次自定义项14 getter 方法 */
  public String getBc_vdef14() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF14);
  }

  /** 批次自定义项15 getter 方法 */
  public String getBc_vdef15() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF15);
  }

  /** 批次自定义项16 getter 方法 */
  public String getBc_vdef16() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF16);
  }

  /** 批次自定义项17 getter 方法 */
  public String getBc_vdef17() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF17);
  }

  /** 批次自定义项18 getter 方法 */
  public String getBc_vdef18() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF18);
  }

  /** 批次自定义项19 getter 方法 */
  public String getBc_vdef19() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF19);
  }

  /** 批次自定义项2 getter 方法 */
  public String getBc_vdef2() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF2);
  }

  /** 批次自定义项20 getter 方法 */
  public String getBc_vdef20() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF20);
  }

  /** 批次自定义项3 getter 方法 */
  public String getBc_vdef3() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF3);
  }

  /** 批次自定义项4 getter 方法 */
  public String getBc_vdef4() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF4);
  }

  /** 批次自定义项5 getter 方法 */
  public String getBc_vdef5() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF5);
  }

  /** 批次自定义项6 getter 方法 */
  public String getBc_vdef6() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF6);
  }

  /** 批次自定义项7 getter 方法 */
  public String getBc_vdef7() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF7);
  }

  /** 批次自定义项8 getter 方法 */
  public String getBc_vdef8() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF8);
  }

  /** 批次自定义项9 getter 方法 */
  public String getBc_vdef9() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF9);
  }

  /** 批次版本 getter 方法 */
  public Integer getBc_version() {
    return (Integer) this.getAttributeValue(ArriveItemVO.BC_VERSION);
  }

  /** 批次散列码 getter 方法 */
  public String getBc_vhashcode() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VHASHCODE);
  }

  /** 批次备注 getter 方法 */
  public String getBc_vnote() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VNOTE);
  }

  /** 供应商批次号 getter 方法 */
  public String getBc_vvendbatchcode() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VVENDBATCHCODE);
  }

  /**
   * 获取到货关闭（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return 到货关闭
   */
  public UFBoolean getBcloseorder() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BCLOSEORDER);
  }

  /** 已生成资产片 getter 方法 */
  public UFBoolean getBfaflag() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BFAFLAG);
  }

  /** 固定换算率 getter 方法 */
  public UFBoolean getBfixedrate() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BFIXEDRATE);
  }

  /** 是否紧急放行状态 getter 方法 */
  public UFBoolean getBletgostate() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BLETGOSTATE);
  }

  /** 赠品 getter 方法 */
  public UFBoolean getBpresent() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BPRESENT);
  }

  /** 来源订单行是否赠品 getter 方法 */
  public UFBoolean getBpresentsource() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BPRESENTSOURCE);
  }

  /** 已生成转固单 getter 方法 */
  public UFBoolean getBtransasset() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BTRANSASSET);
  }

  /** 三角贸易 getter 方法 */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BTRIATRADEFLAG);
  }

  /** 客户 getter 方法 */
  public String getCasscustid() {
    return (String) this.getAttributeValue(ArriveItemVO.CASSCUSTID);
  }

  /** 单位 getter 方法 */
  public String getCastunitid() {
    return (String) this.getAttributeValue(ArriveItemVO.CASTUNITID);
  }

  /** 本币币种 getter 方法 */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(ArriveItemVO.CCURRENCYID);
  }

  /** 特征码 **/
  public String getCffileid() {
    return (String) this.getAttributeValue(ArriveItemVO.CFFILEID);
  }

  /** 源头单据明细 getter 方法 */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(ArriveItemVO.CFIRSTBID);
  }

  /** 源头单据 getter 方法 */
  public String getCfirstid() {
    return (String) this.getAttributeValue(ArriveItemVO.CFIRSTID);
  }

  /** 源头单据类型 getter 方法 */
  public String getCfirsttypecode() {
    return (String) this.getAttributeValue(ArriveItemVO.CFIRSTTYPECODE);
  }

  /** 原币币种 getter 方法 */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(ArriveItemVO.CORIGCURRENCYID);
  }

  /** 紧急放行单行号 getter 方法 */
  public String getCpassbollrowno() {
    return (String) this.getAttributeValue(ArriveItemVO.CPASSBOLLROWNO);
  }

  /** 生产厂商 getter 方法 */
  public String getCproductorid() {
    return (String) this.getAttributeValue(ArriveItemVO.CPRODUCTORID);
  }

  /** 项目 getter 方法 */
  public String getCprojectid() {
    return (String) this.getAttributeValue(ArriveItemVO.CPROJECTID);
  }

  /** 项目任务 getter 方法 */
  public String getCprojecttaskid() {
    return (String) this.getAttributeValue(ArriveItemVO.CPROJECTTASKID);
  }

  /** 质量等级 getter 方法 */
  public String getCqualitylevelid() {
    return (String) this.getAttributeValue(ArriveItemVO.CQUALITYLEVELID);
  }

  /** 收货国家/地区 getter 方法 */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(ArriveItemVO.CRECECOUNTRYID);
  }

  /** 报告人 getter 方法 */
  public String getCreporterid() {
    return (String) this.getAttributeValue(ArriveItemVO.CREPORTERID);
  }

  /** 行号 getter 方法 */
  public String getCrowno() {
    return (String) this.getAttributeValue(ArriveItemVO.CROWNO);
  }

  /** 发货国家/地区 getter 方法 */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(ArriveItemVO.CSENDCOUNTRYID);
  }

  /** 来源到货单明细 getter方法 */
  public String getCsourcearrivebid() {
    return (String) this.getAttributeValue(ArriveItemVO.CSOURCEARRIVEBID);
  }

  /** 来源到货单 getter方法 */
  public String getCsourcearriveid() {
    return (String) this.getAttributeValue(ArriveItemVO.CSOURCEARRIVEID);
  }

  /** 来源单据明细 getter 方法 */
  public String getCsourcebid() {
    return (String) this.getAttributeValue(ArriveItemVO.CSOURCEBID);
  }

  /** 来源单据 getter 方法 */
  public String getCsourceid() {
    return (String) this.getAttributeValue(ArriveItemVO.CSOURCEID);
  }

  /** 来源单据类型 getter 方法 */
  public String getCsourcetypecode() {
    return (String) this.getAttributeValue(ArriveItemVO.CSOURCETYPECODE);
  }

  /** 报税国家/地区 getter 方法 */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(ArriveItemVO.CTAXCOUNTRYID);
  }

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(ArriveItemVO.CUNITID);
  }

  /** 到货日期 getter 方法 */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(ArriveItemVO.DBILLDATE);
  }

  /** 失效日期 getter 方法 */
  public UFDate getDinvaliddate() {
    return (UFDate) this.getAttributeValue(ArriveItemVO.DINVALIDDATE);
  }

  /** 计划到货日期 getter 方法 */
  public UFDate getDplanreceivedate() {
    return (UFDate) this.getAttributeValue(ArriveItemVO.DPLANRECEIVEDATE);
  }

  /** 生产日期 getter 方法 */
  public UFDate getDproducedate() {
    return (UFDate) this.getAttributeValue(ArriveItemVO.DPRODUCEDATE);
  }

  /** dr getter 方法 */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(ArriveItemVO.DR);
  }

  /** 报告日期 getter 方法 */
  public UFDate getDreportdate() {
    return (UFDate) this.getAttributeValue(ArriveItemVO.DREPORTDATE);
  }

  /** 购销类型 getter 方法 */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(ArriveItemVO.FBUYSELLFLAG);
  }

  /** 产品类型 getter 方法 */
  public Integer getFproductclass() {
    return (Integer) this.getAttributeValue(ArriveItemVO.FPRODUCTCLASS);
  }

  /** 扣税类别 getter 方法 */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(ArriveItemVO.FTAXTYPEFLAG);
  }

  /** 保质期天数 getter 方法 */
  public Integer getIvalidday() {
    return (Integer) this.getAttributeValue(ArriveItemVO.IVALIDDAY);
  }

  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.M23_B);
  }

  /** 累计退货主数量 getter方法 */
  public UFDouble getNaccumbacknum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NACCUMBACKNUM);
  }

  /** 累计报检主数量 getter 方法 */
  public UFDouble getNaccumchecknum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NACCUMCHECKNUM);
  }

  /** 累计紧急放行入库主数量 getter 方法 */
  public UFDouble getNaccumletgoinnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NACCUMLETGOINNUM);
  }

  /** 累计紧急放行主数量 getter 方法 */
  public UFDouble getNaccumletgonum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NACCUMLETGONUM);
  }

  /** 累计补货主数量 getter 方法 */
  public UFDouble getNaccumreplnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NACCUMREPLNUM);
  }

  /** 累计入库主数量 getter 方法 */
  public UFDouble getNaccumstorenum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NACCUMSTORENUM);
  }

  /** 到货数量 getter 方法 */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NASTNUM);
  }

  /**
   * 获取可到货主数量（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return 可到货主数量
   */
  public UFDouble getNcanarrivenum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NCANARRIVENUM);
  }

  /** 可补货主数量 getter 方法 */
  public UFDouble getNcanreplnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NCANREPLNUM);
  }

  /** 可入库主数量 getter 方法 */
  public UFDouble getNcanstorenum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NCANSTORENUM);
  }

  /** 本次报检数量 getter 方法 */
  public UFDouble getNchecknum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NCHECKNUM);
  }

  /** 累计合格主数量 getter 方法 */
  public UFDouble getNelignum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NELIGNUM);
  }

  /** 折本汇率 getter 方法 */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NEXCHANGERATE);
  }

  /** 本币无税金额 getter 方法 */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NMNY);
  }

  /** 累计不合格主数量 getter 方法 */
  public UFDouble getNnotelignum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NNOTELIGNUM);
  }

  /** 到货主数量 getter 方法 */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NNUM);
  }

  /** 原币无税金额 getter 方法 */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NORIGMNY);
  }

  /** 主原币无税单价 getter 方法 */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NORIGPRICE);
  }

  /** 原币含税金额 getter 方法 */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NORIGTAXMNY);
  }

  /** 主原币含税单价 getter 方法 */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NORIGTAXPRICE);
  }

  /** 应到数量 getter 方法 */
  public UFDouble getNplanastnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NPLANASTNUM);
  }

  /** 应到主数量 getter 方法 */
  public UFDouble getNplannum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NPLANNUM);
  }

  /** 赠品数量 getter 方法 */
  public UFDouble getNpresentastnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NPRESENTASTNUM);
  }

  /** 赠品主数量 getter 方法 */
  public UFDouble getNpresentnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NPRESENTNUM);
  }

  /** 主本币无税单价 getter 方法 */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NPRICE);
  }

  /** 来源单据主数量 getter 方法 */
  public UFDouble getNsourcenum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NSOURCENUM);
  }

  /** 本币税额 getter 方法 */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NTAX);
  }

  /** 本币含税金额 getter 方法 */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NTAXMNY);
  }

  /** 主本币含税单价 getter 方法 */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NTAXPRICE);
  }

  /** 税率 getter 方法 */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NTAXRATE);
  }

  /** 途耗数量 getter 方法 */
  public UFDouble getNwastastnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NWASTASTNUM);
  }

  /** 途耗主数量 getter 方法 */
  public UFDouble getNwastnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NWASTNUM);
  }

  /** 合格主数量 getter 方法 */
  public UFDouble getNwillelignum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NWILLELIGNUM);
  }

  /** 不合格主数量 getter 方法 */
  public UFDouble getNwillnotelignum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NWILLNOTELIGNUM);
  }

  /** 应付组织最新版本 getter 方法 */
  public String getPk_apfinanceorg() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_APFINANCEORG);
  }

  /** 应付组织 getter 方法 */
  public String getPk_apfinanceorg_v() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_APFINANCEORG_V);
  }

  /** 收货利润中心最新版本getter 方法 */
  public String getPk_apliabcenter() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ARRLIABCENTER);
  }

  /** 收货利润中心getter 方法 */
  public String getPk_apliabcenter_v() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ARRLIABCENTER_V);
  }

  /** 到货单明细 getter 方法 */
  public String getPk_arriveorder() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ARRIVEORDER);
  }

  /** 到货单明细 getter 方法 */
  public String getPk_arriveorder_b() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ARRIVEORDER_B);
  }

  /** 结算利润中心最新版本getter 方法 */
  public String getPk_arrliabcenter() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_APLIABCENTER);
  }

  /** 结算利润中心getter 方法 */
  public String getPk_arrliabcenter_v() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_APLIABCENTER_V);
  }

  /** 批次号主键 getter 方法 */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_BATCHCODE);
  }

  /** 检验明细主键 getter 方法 */
  public String getPk_checkdetail() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_CHECKDETAIL);
  }

  /** 集团 getter 方法 */
  public String getPk_group() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_GROUP);
  }

  /** 物料VID getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_MATERIAL);
  }

  /** 采购订单 getter 方法 */
  public String getPk_order() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ORDER);
  }

  /** 采购订单明细 getter 方法 */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ORDER_B);
  }

  /** 订单到货计划 getter 方法 */
  public String getPk_order_bb1() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ORDER_BB1);
  }

  /** 库存组织最新版本 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ORG);
  }

  /** 库存组织 getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ORG_V);
  }

  /** 紧急放行单主键 getter 方法 */
  public String getPk_passbill() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_PASSBILL);
  }

  /** 紧急放行单表体主键 getter 方法 */
  public String getPk_passbill_b() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_PASSBILL_B);
  }

  /** 结算财务组织最新版本 getter 方法 */
  public String getPk_psfinanceorg() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_PSFINANCEORG);
  }

  /** 结算财务组织 getter 方法 */
  public String getPk_psfinanceorg_v() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_PSFINANCEORG_V);
  }

  /** 质检中心 getter 方法 */
  public String getPk_qccenter() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_QCCENTER);
  }

  /** 货位 getter 方法 */
  public String getPk_rack() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_RACK);
  }

  /** 收货仓库 getter 方法 */
  public String getPk_receivestore() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_RECEIVESTORE);
  }

  /** 需求库存组织最新版本 getter 方法 */
  public String getPk_reqstoorg() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_REQSTOORG);
  }

  /** 需求库存组织 getter 方法 */
  public String getPk_reqstoorg_v() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_REQSTOORG_V);
  }

  /** 需求仓库 getter 方法 */
  public String getPk_reqstore() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_REQSTORE);
  }

  /** 物料OID getter 方法 */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_SRCMATERIAL);
  }

  /** 来源单据行TS getter 方法 */
  public UFDateTime getSourcebts() {
    return (UFDateTime) this.getAttributeValue(ArriveItemVO.SOURCEBTS);
  }

  /** 来源单据TS getter 方法 */
  public UFDateTime getSourcets() {
    return (UFDateTime) this.getAttributeValue(ArriveItemVO.SOURCETS);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(ArriveItemVO.TS);
  }

  /** 退货理由 getter 方法 */
  public String getVbackreasonb() {
    return (String) this.getAttributeValue(ArriveItemVO.VBACKREASONB);
  }

  /** 批次号 getter 方法 */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(ArriveItemVO.VBATCHCODE);
  }

  /** 自定义项1 getter 方法 */
  public String getVbdef1() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF1);
  }

  /** 自定义项10 getter 方法 */
  public String getVbdef10() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF10);
  }

  /** 自定义项11 getter 方法 */
  public String getVbdef11() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF11);
  }

  /** 自定义项12 getter 方法 */
  public String getVbdef12() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF12);
  }

  /** 自定义项13 getter 方法 */
  public String getVbdef13() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF13);
  }

  /** 自定义项14 getter 方法 */
  public String getVbdef14() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF14);
  }

  /** 自定义项15 getter 方法 */
  public String getVbdef15() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF15);
  }

  /** 自定义项16 getter 方法 */
  public String getVbdef16() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF16);
  }

  /** 自定义项17 getter 方法 */
  public String getVbdef17() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF17);
  }

  /** 自定义项18 getter 方法 */
  public String getVbdef18() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF18);
  }

  /** 自定义项19 getter 方法 */
  public String getVbdef19() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF19);
  }

  /** 自定义项2 getter 方法 */
  public String getVbdef2() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF2);
  }

  /** 自定义项20 getter 方法 */
  public String getVbdef20() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF20);
  }

  /** 自定义项3 getter 方法 */
  public String getVbdef3() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF3);
  }

  /** 自定义项4 getter 方法 */
  public String getVbdef4() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF4);
  }

  /** 自定义项5 getter 方法 */
  public String getVbdef5() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF5);
  }

  /** 自定义项6 getter 方法 */
  public String getVbdef6() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF6);
  }

  /** 自定义项7 getter 方法 */
  public String getVbdef7() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF7);
  }

  /** 自定义项8 getter 方法 */
  public String getVbdef8() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF8);
  }

  /** 自定义项9 getter 方法 */
  public String getVbdef9() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF9);
  }

  /** 换算率 getter 方法 */
  public String getVchangerate() {
    return (String) this.getAttributeValue(ArriveItemVO.VCHANGERATE);
  }

  /** 源头单据号 getter 方法 */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(ArriveItemVO.VFIRSTCODE);
  }

  /** 源头单据行号 getter 方法 */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(ArriveItemVO.VFIRSTROWNO);
  }

  /** 源头交易类型 getter 方法 */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(ArriveItemVO.VFIRSTTRANTYPE);
  }

  /** 自由辅助属性1 getter 方法 */
  public String getVfree1() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE1);
  }

  /** 自由辅助属性10 getter 方法 */
  public String getVfree10() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE10);
  }

  /** 自由辅助属性2 getter 方法 */
  public String getVfree2() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE2);
  }

  /** 自由辅助属性3 getter 方法 */
  public String getVfree3() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE3);
  }

  /** 自由辅助属性4 getter 方法 */
  public String getVfree4() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE4);
  }

  /** 自由辅助属性5 getter 方法 */
  public String getVfree5() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE5);
  }

  /** 自由辅助属性6 getter 方法 */
  public String getVfree6() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE6);
  }

  /** 自由辅助属性7 getter 方法 */
  public String getVfree7() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE7);
  }

  /** 自由辅助属性8 getter 方法 */
  public String getVfree8() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE8);
  }

  /** 自由辅助属性9 getter 方法 */
  public String getVfree9() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE9);
  }

  /** 备注 getter 方法 */
  public String getVmemob() {
    return (String) this.getAttributeValue(ArriveItemVO.VMEMOB);
  }

  /** 紧急放行单号 getter 方法 */
  public String getVpassbillcode() {
    return (String) this.getAttributeValue(ArriveItemVO.VPASSBILLCODE);
  }

  /** 生产批次号 getter 方法 */
  public String getVprodbatchcode() {
    return (String) this.getAttributeValue(ArriveItemVO.VPRODBATCHCODE);
  }

  /** 来源单据号 getter 方法 */
  public String getVsourcecode() {
    return (String) this.getAttributeValue(ArriveItemVO.VSOURCECODE);
  }

  /** 来源单据行号 getter 方法 */
  public String getVsourcerowno() {
    return (String) this.getAttributeValue(ArriveItemVO.VSOURCEROWNO);
  }

  /** 来源交易类型 getter 方法 */
  public String getVsourcetrantype() {
    return (String) this.getAttributeValue(ArriveItemVO.VSOURCETRANTYPE);
  }

  /** 退货基于原订单补货 setter 方法 */
  public void setBbackreforder(UFBoolean bbackreforder) {
    this.setAttributeValue(ArriveItemVO.BBACKREFORDER, bbackreforder);
  }

  /** 批次是否封存 setter 方法 */
  public void setBc_bseal(UFBoolean bc_bseal) {
    this.setAttributeValue(ArriveItemVO.BC_BSEAL, bc_bseal);
  }

  /** 批次质量等级 setter 方法 */
  public void setBc_cqualitylevelid(String bc_cqualitylevelid) {
    this.setAttributeValue(ArriveItemVO.BC_CQUALITYLEVELID, bc_cqualitylevelid);
  }

  /** 批次库存状态 setter 方法 */
  public void setBc_cstateid(String bc_cstateid) {
    this.setAttributeValue(ArriveItemVO.BC_CSTATEID, bc_cstateid);
  }

  /** 批次形成时间 setter 方法 */
  public void setBc_tbatchtime(UFDateTime bc_tbatchtime) {
    this.setAttributeValue(ArriveItemVO.BC_TBATCHTIME, bc_tbatchtime);
  }

  /** 检验时间 setter 方法 */
  public void setBc_tchecktime(UFDateTime bc_tchecktime) {
    this.setAttributeValue(ArriveItemVO.BC_TCHECKTIME, bc_tchecktime);
  }

  /** 批次时间戳 setter 方法 */
  public void setBc_ts(UFDateTime bc_ts) {
    this.setAttributeValue(ArriveItemVO.BC_TS, bc_ts);
  }

  /** 批次自定义项1 setter 方法 */
  public void setBc_vdef1(String bc_vdef1) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF1, bc_vdef1);
  }

  /** 批次自定义项10 setter 方法 */
  public void setBc_vdef10(String bc_vdef10) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF10, bc_vdef10);
  }

  /** 批次自定义项11 setter 方法 */
  public void setBc_vdef11(String bc_vdef11) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF11, bc_vdef11);
  }

  /** 批次自定义项12 setter 方法 */
  public void setBc_vdef12(String bc_vdef12) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF12, bc_vdef12);
  }

  /** 批次自定义项13 setter 方法 */
  public void setBc_vdef13(String bc_vdef13) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF13, bc_vdef13);
  }

  /** 批次自定义项14 setter 方法 */
  public void setBc_vdef14(String bc_vdef14) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF14, bc_vdef14);
  }

  /** 批次自定义项15 setter 方法 */
  public void setBc_vdef15(String bc_vdef15) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF15, bc_vdef15);
  }

  /** 批次自定义项16 setter 方法 */
  public void setBc_vdef16(String bc_vdef16) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF16, bc_vdef16);
  }

  /** 批次自定义项17 setter 方法 */
  public void setBc_vdef17(String bc_vdef17) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF17, bc_vdef17);
  }

  /** 批次自定义项18 setter 方法 */
  public void setBc_vdef18(String bc_vdef18) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF18, bc_vdef18);
  }

  /** 批次自定义项19 setter 方法 */
  public void setBc_vdef19(String bc_vdef19) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF19, bc_vdef19);
  }

  /** 批次自定义项2 setter 方法 */
  public void setBc_vdef2(String bc_vdef2) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF2, bc_vdef2);
  }

  /** 批次自定义项20 setter 方法 */
  public void setBc_vdef20(String bc_vdef20) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF20, bc_vdef20);
  }

  /** 批次自定义项3 setter 方法 */
  public void setBc_vdef3(String bc_vdef3) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF3, bc_vdef3);
  }

  /** 批次自定义项4 setter 方法 */
  public void setBc_vdef4(String bc_vdef4) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF4, bc_vdef4);
  }

  /** 批次自定义项5 setter 方法 */
  public void setBc_vdef5(String bc_vdef5) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF5, bc_vdef5);
  }

  /** 批次自定义项6 setter 方法 */
  public void setBc_vdef6(String bc_vdef6) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF6, bc_vdef6);
  }

  /** 批次自定义项7 setter 方法 */
  public void setBc_vdef7(String bc_vdef7) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF7, bc_vdef7);
  }

  /** 批次自定义项8 setter 方法 */
  public void setBc_vdef8(String bc_vdef8) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF8, bc_vdef8);
  }

  /** 批次自定义项9 setter 方法 */
  public void setBc_vdef9(String bc_vdef9) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF9, bc_vdef9);
  }

  /** 批次版本 setter 方法 */
  public void setBc_version(Integer bc_version) {
    this.setAttributeValue(ArriveItemVO.BC_VERSION, bc_version);
  }

  /** 批次散列码 setter 方法 */
  public void setBc_vhashcode(String bc_vhashcode) {
    this.setAttributeValue(ArriveItemVO.BC_VHASHCODE, bc_vhashcode);
  }

  /** 批次备注 setter 方法 */
  public void setBc_vnote(String bc_vnote) {
    this.setAttributeValue(ArriveItemVO.BC_VNOTE, bc_vnote);
  }

  /** 供应商批次号 setter 方法 */
  public void setBc_vvendbatchcode(String bc_vvendbatchcode) {
    this.setAttributeValue(ArriveItemVO.BC_VVENDBATCHCODE, bc_vvendbatchcode);
  }

  /**
   * 设置到货关闭（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bcloseorder 到货关闭
   */
  public void setBcloseorder(UFBoolean bcloseorder) {
    this.setAttributeValue(ArriveItemVO.BCLOSEORDER, bcloseorder);
  }

  /** 已生成资产片 setter 方法 */
  public void setBfaflag(UFBoolean bfaflag) {
    this.setAttributeValue(ArriveItemVO.BFAFLAG, bfaflag);
  }

  /** 固定换算率 setter 方法 */
  public void setBfixedrate(UFBoolean bfixedrate) {
    this.setAttributeValue(ArriveItemVO.BFIXEDRATE, bfixedrate);
  }

  /** 是否紧急放行状态 setter 方法 */
  public void setBletgostate(UFBoolean bletgostate) {
    this.setAttributeValue(ArriveItemVO.BLETGOSTATE, bletgostate);
  }

  /** 赠品 setter 方法 */
  public void setBpresent(UFBoolean bpresent) {
    this.setAttributeValue(ArriveItemVO.BPRESENT, bpresent);
  }

  /** 来源订单行是否赠品 setter 方法 */
  public void setBpresentsource(UFBoolean bpresentsource) {
    this.setAttributeValue(ArriveItemVO.BPRESENTSOURCE, bpresentsource);
  }

  /** 已生成转固单 setter 方法 */
  public void setBtransasset(UFBoolean btransasset) {
    this.setAttributeValue(ArriveItemVO.BTRANSASSET, btransasset);
  }

  /** 三角贸易 setter 方法 */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(ArriveItemVO.BTRIATRADEFLAG, btriatradeflag);
  }

  /** 客户 setter 方法 */
  public void setCasscustid(String casscustid) {
    this.setAttributeValue(ArriveItemVO.CASSCUSTID, casscustid);
  }

  /** 单位 setter 方法 */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(ArriveItemVO.CASTUNITID, castunitid);
  }

  /** 本币币种 setter 方法 */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(ArriveItemVO.CCURRENCYID, ccurrencyid);
  }

  /** 特征码 **/
  public void setCffileid(String cffileid) {
    this.setAttributeValue(ArriveItemVO.CFFILEID, cffileid);
  }

  /** 源头单据明细 setter 方法 */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(ArriveItemVO.CFIRSTBID, cfirstbid);
  }

  /** 源头单据 setter 方法 */
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(ArriveItemVO.CFIRSTID, cfirstid);
  }

  /** 源头单据类型 setter 方法 */
  public void setCfirsttypecode(String cfirsttypecode) {
    this.setAttributeValue(ArriveItemVO.CFIRSTTYPECODE, cfirsttypecode);
  }

  /** 原币币种 setter 方法 */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(ArriveItemVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /** 紧急放行单行号 setter 方法 */
  public void setCpassbollrowno(String cpassbollrowno) {
    this.setAttributeValue(ArriveItemVO.CPASSBOLLROWNO, cpassbollrowno);
  }

  /** 生产厂商 setter 方法 */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(ArriveItemVO.CPRODUCTORID, cproductorid);
  }

  /** 项目 setter 方法 */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(ArriveItemVO.CPROJECTID, cprojectid);
  }

  /** 项目任务 setter 方法 */
  public void setCprojecttaskid(String cprojecttaskid) {
    this.setAttributeValue(ArriveItemVO.CPROJECTTASKID, cprojecttaskid);
  }

  /** 质量等级 setter 方法 */
  public void setCqualitylevelid(String cqualitylevelid) {
    this.setAttributeValue(ArriveItemVO.CQUALITYLEVELID, cqualitylevelid);
  }

  /** 收货国家/地区 setter 方法 */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(ArriveItemVO.CRECECOUNTRYID, crececountryid);
  }

  /** 报告人 setter 方法 */
  public void setCreporterid(String creporterid) {
    this.setAttributeValue(ArriveItemVO.CREPORTERID, creporterid);
  }

  /** 行号 setter 方法 */
  public void setCrowno(String crowno) {
    this.setAttributeValue(ArriveItemVO.CROWNO, crowno);
  }

  /** 发货国家/地区 setter 方法 */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(ArriveItemVO.CSENDCOUNTRYID, csendcountryid);
  }

  /** 来源到货单明细 setter方法 */
  public void setCsourcearrivebid(String csourcearrivebid) {
    this.setAttributeValue(ArriveItemVO.CSOURCEARRIVEBID, csourcearrivebid);
  }

  /** 来源到货单 setter方法 */
  public void setCsourcearriveid(String csourcearriveid) {
    this.setAttributeValue(ArriveItemVO.CSOURCEARRIVEID, csourcearriveid);
  }

  /** 来源单据明细 setter 方法 */
  public void setCsourcebid(String csourcebid) {
    this.setAttributeValue(ArriveItemVO.CSOURCEBID, csourcebid);
  }

  /** 来源单据 setter 方法 */
  public void setCsourceid(String csourceid) {
    this.setAttributeValue(ArriveItemVO.CSOURCEID, csourceid);
  }

  /** 来源单据类型 setter 方法 */
  public void setCsourcetypecode(String csourcetypecode) {
    this.setAttributeValue(ArriveItemVO.CSOURCETYPECODE, csourcetypecode);
  }

  /** 报税国家/地区 setter 方法 */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(ArriveItemVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  /** 主单位 setter 方法 */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(ArriveItemVO.CUNITID, cunitid);
  }

  /** 到货日期 setter 方法 */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(ArriveItemVO.DBILLDATE, dbilldate);
  }

  /** 失效日期 setter 方法 */
  public void setDinvaliddate(UFDate dinvaliddate) {
    this.setAttributeValue(ArriveItemVO.DINVALIDDATE, dinvaliddate);
  }

  /** 计划到货日期 setter 方法 */
  public void setDplanreceivedate(UFDate dplanreceivedate) {
    this.setAttributeValue(ArriveItemVO.DPLANRECEIVEDATE, dplanreceivedate);
  }

  /** 生产日期 setter 方法 */
  public void setDproducedate(UFDate dproducedate) {
    this.setAttributeValue(ArriveItemVO.DPRODUCEDATE, dproducedate);
  }

  /** dr setter 方法 */
  public void setDr(Integer dr) {
    this.setAttributeValue(ArriveItemVO.DR, dr);
  }

  /** 报告日期 setter 方法 */
  public void setDreportdate(UFDate dreportdate) {
    this.setAttributeValue(ArriveItemVO.DREPORTDATE, dreportdate);
  }

  /** 购销类型 setter 方法 */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(ArriveItemVO.FBUYSELLFLAG, fbuysellflag);
  }

  /** 产品类型 setter 方法 */
  public void setFproductclass(Integer fproductclass) {
    this.setAttributeValue(ArriveItemVO.FPRODUCTCLASS, fproductclass);
  }

  /** 扣税类别 setter 方法 */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(ArriveItemVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /** 保质期天数 setter 方法 */
  public void setIvalidday(Integer ivalidday) {
    this.setAttributeValue(ArriveItemVO.IVALIDDAY, ivalidday);
  }

  /** 累计退货主数量 setter方法 */
  public void setNaccumbacknum(UFDouble naccumbacknum) {
    this.setAttributeValue(ArriveItemVO.NACCUMBACKNUM, naccumbacknum);
  }

  /** 累计报检主数量 setter 方法 */
  public void setNaccumchecknum(UFDouble naccumchecknum) {
    this.setAttributeValue(ArriveItemVO.NACCUMCHECKNUM, naccumchecknum);
  }

  /** 累计紧急放行入库主数量 setter 方法 */
  public void setNaccumletgoinnum(UFDouble naccumletgoinnum) {
    this.setAttributeValue(ArriveItemVO.NACCUMLETGOINNUM, naccumletgoinnum);
  }

  /** 累计紧急放行主数量 setter 方法 */
  public void setNaccumletgonum(UFDouble naccumletgonum) {
    this.setAttributeValue(ArriveItemVO.NACCUMLETGONUM, naccumletgonum);
  }

  /** 累计补货主数量 setter 方法 */
  public void setNaccumreplnum(UFDouble naccumreplnum) {
    this.setAttributeValue(ArriveItemVO.NACCUMREPLNUM, naccumreplnum);
  }

  /** 累计入库主数量 setter 方法 */
  public void setNaccumstorenum(UFDouble naccumstorenum) {
    this.setAttributeValue(ArriveItemVO.NACCUMSTORENUM, naccumstorenum);
  }

  /** 到货数量 setter 方法 */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(ArriveItemVO.NASTNUM, nastnum);
  }

  /**
   * 设置可到货主数量（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param ncanarrivenum 可到货主数量
   */
  public void setNcanarrivenum(UFDouble ncanarrivenum) {
    this.setAttributeValue(ArriveItemVO.NCANARRIVENUM, ncanarrivenum);
  }

  /** 可补货主数量 setter 方法 */
  public void setNcanreplnum(UFDouble ncanreplnum) {
    this.setAttributeValue(ArriveItemVO.NCANREPLNUM, ncanreplnum);
  }

  /** 可入库主数量 setter 方法 */
  public void setNcanstorenum(UFDouble ncanstorenum) {
    this.setAttributeValue(ArriveItemVO.NCANSTORENUM, ncanstorenum);
  }

  /** 本次报检数量 setter 方法 */
  public void setNchecknum(UFDouble nchecknum) {
    this.setAttributeValue(ArriveItemVO.NCHECKNUM, nchecknum);
  }

  /** 累计合格主数量 setter 方法 */
  public void setNelignum(UFDouble nelignum) {
    this.setAttributeValue(ArriveItemVO.NELIGNUM, nelignum);
  }

  /** 折本汇率 setter 方法 */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(ArriveItemVO.NEXCHANGERATE, nexchangerate);
  }

  /** 本币无税金额 setter 方法 */
  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(ArriveItemVO.NMNY, nmny);
  }

  /** 累计不合格主数量 setter 方法 */
  public void setNnotelignum(UFDouble nnotelignum) {
    this.setAttributeValue(ArriveItemVO.NNOTELIGNUM, nnotelignum);
  }

  /** 到货主数量 setter 方法 */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(ArriveItemVO.NNUM, nnum);
  }

  /** 原币无税金额 setter 方法 */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(ArriveItemVO.NORIGMNY, norigmny);
  }

  /** 主原币无税单价 setter 方法 */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(ArriveItemVO.NORIGPRICE, norigprice);
  }

  /** 原币含税金额 setter 方法 */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(ArriveItemVO.NORIGTAXMNY, norigtaxmny);
  }

  /** 主原币含税单价 setter 方法 */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(ArriveItemVO.NORIGTAXPRICE, norigtaxprice);
  }

  /** 应到数量 setter 方法 */
  public void setNplanastnum(UFDouble nplanastnum) {
    this.setAttributeValue(ArriveItemVO.NPLANASTNUM, nplanastnum);
  }

  /** 应到主数量 setter 方法 */
  public void setNplannum(UFDouble nplannum) {
    this.setAttributeValue(ArriveItemVO.NPLANNUM, nplannum);
  }

  /** 赠品数量 setter 方法 */
  public void setNpresentastnum(UFDouble npresentastnum) {
    this.setAttributeValue(ArriveItemVO.NPRESENTASTNUM, npresentastnum);
  }

  /** 赠品主数量 setter 方法 */
  public void setNpresentnum(UFDouble npresentnum) {
    this.setAttributeValue(ArriveItemVO.NPRESENTNUM, npresentnum);
  }

  /** 主本币无税单价 setter 方法 */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(ArriveItemVO.NPRICE, nprice);
  }

  /** 来源单据主数量 setter 方法 */
  public void setNsourcenum(UFDouble nsourcenum) {
    this.setAttributeValue(ArriveItemVO.NSOURCENUM, nsourcenum);
  }

  /** 本币税额 setter 方法 */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(ArriveItemVO.NTAX, ntax);
  }

  /** 本币含税金额 setter 方法 */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(ArriveItemVO.NTAXMNY, ntaxmny);
  }

  /** 主本币含税单价 setter 方法 */
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(ArriveItemVO.NTAXPRICE, ntaxprice);
  }

  /** 税率 setter 方法 */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(ArriveItemVO.NTAXRATE, ntaxrate);
  }

  /** 途耗数量 setter 方法 */
  public void setNwastastnum(UFDouble nwastastnum) {
    this.setAttributeValue(ArriveItemVO.NWASTASTNUM, nwastastnum);
  }

  /** 途耗主数量 setter 方法 */
  public void setNwastnum(UFDouble nwastnum) {
    this.setAttributeValue(ArriveItemVO.NWASTNUM, nwastnum);
  }

  /** 合格主数量 setter 方法 */
  public void setNwillelignum(UFDouble nwillelignum) {
    this.setAttributeValue(ArriveItemVO.NWILLELIGNUM, nwillelignum);
  }

  /** 不合格主数量 setter 方法 */
  public void setNwillnotelignum(UFDouble nwillnotelignum) {
    this.setAttributeValue(ArriveItemVO.NWILLNOTELIGNUM, nwillnotelignum);
  }

  /** 应付组织最新版本 setter 方法 */
  public void setPk_apfinanceorg(String pk_apfinanceorg) {
    this.setAttributeValue(ArriveItemVO.PK_APFINANCEORG, pk_apfinanceorg);
  }

  /** 应付组织 setter 方法 */
  public void setPk_apfinanceorg_v(String pk_apfinanceorg_v) {
    this.setAttributeValue(ArriveItemVO.PK_APFINANCEORG_V, pk_apfinanceorg_v);
  }

  /** 结算利润中心最新版本 setter 方法 */
  public void setPk_apliabcenter(String pk_apliabcenter) {
    this.setAttributeValue(ArriveItemVO.PK_APLIABCENTER, pk_apliabcenter);
  }

  /** 结算利润中心 setter 方法 */
  public void setPk_apliabcenter_v(String pk_apliabcenter_v) {
    this.setAttributeValue(ArriveItemVO.PK_APLIABCENTER_V, pk_apliabcenter_v);
  }

  /** 到货单明细 setter 方法 */
  public void setPk_arriveorder(String pk_arriveorder) {
    this.setAttributeValue(ArriveItemVO.PK_ARRIVEORDER, pk_arriveorder);
  }

  /** 到货单明细 setter 方法 */
  public void setPk_arriveorder_b(String pk_arriveorder_b) {
    this.setAttributeValue(ArriveItemVO.PK_ARRIVEORDER_B, pk_arriveorder_b);
  }

  /** 收货利润中心最新版本 setter 方法 */
  public void setPk_arrliabcenter(String pk_arrliabcenter) {
    this.setAttributeValue(ArriveItemVO.PK_ARRLIABCENTER, pk_arrliabcenter);
  }

  /** 收货利润中心 setter 方法 */
  public void setPk_arrliabcenter_v(String pk_arrliabcenter_v) {
    this.setAttributeValue(ArriveItemVO.PK_ARRLIABCENTER_V, pk_arrliabcenter_v);
  }

  /** 批次号主键 setter 方法 */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(ArriveItemVO.PK_BATCHCODE, pk_batchcode);
  }

  /** 检验明细主键 setter 方法 */
  public void setPk_checkdetail(String pk_checkdetail) {
    this.setAttributeValue(ArriveItemVO.PK_CHECKDETAIL, pk_checkdetail);
  }

  /** 集团 setter 方法 */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(ArriveItemVO.PK_GROUP, pk_group);
  }

  /** 物料VID setter 方法 */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(ArriveItemVO.PK_MATERIAL, pk_material);
  }

  /** 采购订单 setter 方法 */
  public void setPk_order(String pk_order) {
    this.setAttributeValue(ArriveItemVO.PK_ORDER, pk_order);
  }

  /** 采购订单明细 setter 方法 */
  public void setPk_order_b(String pk_order_b) {
    this.setAttributeValue(ArriveItemVO.PK_ORDER_B, pk_order_b);
  }

  /** 订单到货计划 setter 方法 */
  public void setPk_order_bb1(String pk_order_bb1) {
    this.setAttributeValue(ArriveItemVO.PK_ORDER_BB1, pk_order_bb1);
  }

  /** 库存组织最新版本 setter 方法 */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(ArriveItemVO.PK_ORG, pk_org);
  }

  /** 库存组织 setter 方法 */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(ArriveItemVO.PK_ORG_V, pk_org_v);
  }

  /** 紧急放行单主键 setter 方法 */
  public void setPk_passbill(String pk_passbill) {
    this.setAttributeValue(ArriveItemVO.PK_PASSBILL, pk_passbill);
  }

  /** 紧急放行单表体主键 setter 方法 */
  public void setPk_passbill_b(String pk_passbill_b) {
    this.setAttributeValue(ArriveItemVO.PK_PASSBILL_B, pk_passbill_b);
  }

  /** 结算财务组织最新版本 setter 方法 */
  public void setPk_psfinanceorg(String pk_psfinanceorg) {
    this.setAttributeValue(ArriveItemVO.PK_PSFINANCEORG, pk_psfinanceorg);
  }

  /** 结算财务组织 setter 方法 */
  public void setPk_psfinanceorg_v(String pk_psfinanceorg_v) {
    this.setAttributeValue(ArriveItemVO.PK_PSFINANCEORG_V, pk_psfinanceorg_v);
  }

  /** 质检中心 setter 方法 */
  public void setPk_qccenter(String pk_qccenter) {
    this.setAttributeValue(ArriveItemVO.PK_QCCENTER, pk_qccenter);
  }

  /** 货位 setter 方法 */
  public void setPk_rack(String pk_rack) {
    this.setAttributeValue(ArriveItemVO.PK_RACK, pk_rack);
  }

  /** 收货仓库 setter 方法 */
  public void setPk_receivestore(String pk_receivestore) {
    this.setAttributeValue(ArriveItemVO.PK_RECEIVESTORE, pk_receivestore);
  }

  /** 需求库存组织最新版本 setter 方法 */
  public void setPk_reqstoorg(String pk_reqstoorg) {
    this.setAttributeValue(ArriveItemVO.PK_REQSTOORG, pk_reqstoorg);
  }

  /** 需求库存组织 setter 方法 */
  public void setPk_reqstoorg_v(String pk_reqstoorg_v) {
    this.setAttributeValue(ArriveItemVO.PK_REQSTOORG_V, pk_reqstoorg_v);
  }

  /** 需求仓库 setter 方法 */
  public void setPk_reqstore(String pk_reqstore) {
    this.setAttributeValue(ArriveItemVO.PK_REQSTORE, pk_reqstore);
  }

  /** 物料OID setter 方法 */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(ArriveItemVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

  /** 来源单据行TS setter 方法 */
  public void setSourcebts(UFDateTime sourcebts) {
    this.setAttributeValue(ArriveItemVO.SOURCEBTS, sourcebts);
  }

  /** 来源单据TS setter 方法 */
  public void setSourcets(UFDateTime sourcets) {
    this.setAttributeValue(ArriveItemVO.SOURCETS, sourcets);
  }

  /** ts setter 方法 */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(ArriveItemVO.TS, ts);
  }

  /** 退货理由 setter 方法 */
  public void setVbackreasonb(String vbackreasonb) {
    this.setAttributeValue(ArriveItemVO.VBACKREASONB, vbackreasonb);
  }

  /** 批次号 setter 方法 */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(ArriveItemVO.VBATCHCODE, vbatchcode);
  }

  /** 自定义项1 setter 方法 */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(ArriveItemVO.VBDEF1, vbdef1);
  }

  /** 自定义项10 setter 方法 */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(ArriveItemVO.VBDEF10, vbdef10);
  }

  /** 自定义项11 setter 方法 */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(ArriveItemVO.VBDEF11, vbdef11);
  }

  /** 自定义项12 setter 方法 */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(ArriveItemVO.VBDEF12, vbdef12);
  }

  /** 自定义项13 setter 方法 */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(ArriveItemVO.VBDEF13, vbdef13);
  }

  /** 自定义项14 setter 方法 */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(ArriveItemVO.VBDEF14, vbdef14);
  }

  /** 自定义项15 setter 方法 */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(ArriveItemVO.VBDEF15, vbdef15);
  }

  /** 自定义项16 setter 方法 */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(ArriveItemVO.VBDEF16, vbdef16);
  }

  /** 自定义项17 setter 方法 */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(ArriveItemVO.VBDEF17, vbdef17);
  }

  /** 自定义项18 setter 方法 */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(ArriveItemVO.VBDEF18, vbdef18);
  }

  /** 自定义项19 setter 方法 */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(ArriveItemVO.VBDEF19, vbdef19);
  }

  /** 自定义项2 setter 方法 */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(ArriveItemVO.VBDEF2, vbdef2);
  }

  /** 自定义项20 setter 方法 */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(ArriveItemVO.VBDEF20, vbdef20);
  }

  /** 自定义项3 setter 方法 */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(ArriveItemVO.VBDEF3, vbdef3);
  }

  /** 自定义项4 setter 方法 */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(ArriveItemVO.VBDEF4, vbdef4);
  }

  /** 自定义项5 setter 方法 */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(ArriveItemVO.VBDEF5, vbdef5);
  }

  /** 自定义项6 setter 方法 */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(ArriveItemVO.VBDEF6, vbdef6);
  }

  /** 自定义项7 setter 方法 */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(ArriveItemVO.VBDEF7, vbdef7);
  }

  /** 自定义项8 setter 方法 */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(ArriveItemVO.VBDEF8, vbdef8);
  }

  /** 自定义项9 setter 方法 */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(ArriveItemVO.VBDEF9, vbdef9);
  }

  /** 换算率 setter 方法 */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(ArriveItemVO.VCHANGERATE, vchangerate);
  }

  /** 源头单据号 setter 方法 */
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(ArriveItemVO.VFIRSTCODE, vfirstcode);
  }

  /** 源头单据行号 setter 方法 */
  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(ArriveItemVO.VFIRSTROWNO, vfirstrowno);
  }

  /** 源头交易类型 setter 方法 */
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(ArriveItemVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /** 自由辅助属性1 setter 方法 */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(ArriveItemVO.VFREE1, vfree1);
  }

  /** 自由辅助属性10 setter 方法 */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(ArriveItemVO.VFREE10, vfree10);
  }

  /** 自由辅助属性2 setter 方法 */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(ArriveItemVO.VFREE2, vfree2);
  }

  /** 自由辅助属性3 setter 方法 */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(ArriveItemVO.VFREE3, vfree3);
  }

  /** 自由辅助属性4 setter 方法 */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(ArriveItemVO.VFREE4, vfree4);
  }

  /** 自由辅助属性5 setter 方法 */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(ArriveItemVO.VFREE5, vfree5);
  }

  /** 自由辅助属性6 setter 方法 */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(ArriveItemVO.VFREE6, vfree6);
  }

  /** 自由辅助属性7 setter 方法 */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(ArriveItemVO.VFREE7, vfree7);
  }

  /** 自由辅助属性8 setter 方法 */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(ArriveItemVO.VFREE8, vfree8);
  }

  /** 自由辅助属性9 setter 方法 */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(ArriveItemVO.VFREE9, vfree9);
  }

  /** 备注 setter 方法 */
  public void setVmemob(String vmemob) {
    this.setAttributeValue(ArriveItemVO.VMEMOB, vmemob);
  }

  /** 紧急放行单号 setter 方法 */
  public void setVpassbillcode(String vpassbillcode) {
    this.setAttributeValue(ArriveItemVO.VPASSBILLCODE, vpassbillcode);
  }

  /** 生产批次号 setter 方法 */
  public void setVprodbatchcode(String vprodbatchcode) {
    this.setAttributeValue(ArriveItemVO.VPRODBATCHCODE, vprodbatchcode);
  }

  /** 来源单据号 setter 方法 */
  public void setVsourcecode(String vsourcecode) {
    this.setAttributeValue(ArriveItemVO.VSOURCECODE, vsourcecode);
  }

  /** 来源单据行号 setter 方法 */
  public void setVsourcerowno(String vsourcerowno) {
    this.setAttributeValue(ArriveItemVO.VSOURCEROWNO, vsourcerowno);
  }

  /** 来源交易类型 setter 方法 */
  public void setVsourcetrantype(String vsourcetrantype) {
    this.setAttributeValue(ArriveItemVO.VSOURCETRANTYPE, vsourcetrantype);
  }
}
