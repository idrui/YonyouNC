package nc.vo.pu.m23.rule.api;

import java.io.Serializable;

/**
 * 
 * @description
 *		到货单VO
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *		到货单VO
 * @since 6.5
 * @version 2015-11-10 下午7:50:56
 * @author wandl
 */
public interface IArriveVO extends Serializable {
  
  /**
   * 到货单
   */
  public static final String PK_ARRIVEORDER = "pk_arriveorder";
  /**
   * 集团
   */
  public static final String PK_GROUP = "pk_group";
  /**
   * 库存组织
   */
  public static final String PK_ORG = "pk_org";
  /**
   * 库存组织
   */
  public static final String PK_ORG_V = "pk_org_v";
  /**
   * 采购组织
   */
  public static final String PK_PURCHASEORG = "pk_purchaseorg";
  /**
   * 采购组织
   */
  public static final String PK_PURCHASEORG_V = "pk_purchaseorg_v";
  /**
   * 到货单号
   */
  public static final String VBILLCODE = "vbillcode";
  /**
   * 到货日期
   */
  public static final String DBILLDATE = "dbilldate";
  /**
   * 散户
   */
  public static final String PK_FREECUST = "pk_freecust";
  /**
   * 供应商
   */
  public static final String PK_SUPPLIER = "pk_supplier";
  /**
   * 业务流程
   */
  public static final String PK_BUSITYPE = "pk_busitype";
  /**
   * 到货类型编码编码
   */
  public static final String VTRANTYPECODE = "vtrantypecode";
  /**
   * 运输方式
   */
  public static final String PK_TRANSPORTTYPE = "pk_transporttype";
  /**
   * 收货人
   */
  public static final String PK_RECEIVEPSNDOC = "pk_receivepsndoc";
  /**
   * 采购部门
   */
  public static final String PK_DEPT = "pk_dept";
  /**
   * 采购部门
   */
  public static final String PK_DEPT_V = "pk_dept_v";
  /**
   * 采购员
   */
  public static final String PK_PUPSNDOC = "pk_pupsndoc";
  /**
   * 单据状态
   */
  public static final String FBILLSTATUS = "fbillstatus";
  /**
   * 备注
   */
  public static final String VMEMO = "vmemo";
  /**
   * 退货
   */
  public static final String BISBACK = "bisback";
  /**
   * 打印次数
   */
  public static final String IPRINTCOUNT = "iprintcount";
  /**
   * 退货理由
   */
  public static final String VBACKREASON = "vbackreason";
  /**
   * 总数量
   */
  public static final String NTOTALASTNUM = "ntotalastnum";
  /**
   * 本币价税合计
   */
  public static final String NTOTALTAXMNY = "ntotaltaxmny";
  /**
   * 创建时间
   */
  public static final String CREATIONTIME = "creationtime";
  /**
   * 制单人
   */
  public static final String BILLMAKER = "billmaker";
  /**
   * 制单日期
   */
  public static final String DMAKEDATE = "dmakedate";
  /**
   * 审批日期
   */
  public static final String TAUDITTIME = "taudittime";
  /**
   * 审批人
   */
  public static final String APPROVER = "approver";
  /**
   * 最后修改时间
   */
  public static final String MODIFIEDTIME = "modifiedtime";
  /**
   * 最后修改人
   */
  public static final String MODIFIER = "modifier";
  /**
   * 创建人
   */
  public static final String CREATOR = "creator";
  /**
   * 自定义项1
   */
  public static final String VDEF1 = "vdef1";
  /**
   * 自定义项2
   */
  public static final String VDEF2 = "vdef2";
  /**
   * 自定义项3
   */
  public static final String VDEF3 = "vdef3";
  /**
   * 自定义项4
   */
  public static final String VDEF4 = "vdef4";
  /**
   * 自定义项5
   */
  public static final String VDEF5 = "vdef5";
  /**
   * 自定义项6
   */
  public static final String VDEF6 = "vdef6";
  /**
   * 自定义项7
   */
  public static final String VDEF7 = "vdef7";
  /**
   * 自定义项8
   */
  public static final String VDEF8 = "vdef8";
  /**
   * 自定义项9
   */
  public static final String VDEF9 = "vdef9";
  /**
   * 自定义项10
   */
  public static final String VDEF10 = "vdef10";
  /**
   * 自定义项11
   */
  public static final String VDEF11 = "vdef11";
  /**
   * 自定义项12
   */
  public static final String VDEF12 = "vdef12";
  /**
   * 自定义项13
   */
  public static final String VDEF13 = "vdef13";
  /**
   * 自定义项14
   */
  public static final String VDEF14 = "vdef14";
  /**
   * 自定义项15
   */
  public static final String VDEF15 = "vdef15";
  /**
   * 自定义项16
   */
  public static final String VDEF16 = "vdef16";
  /**
   * 自定义项17
   */
  public static final String VDEF17 = "vdef17";
  /**
   * 自定义项18
   */
  public static final String VDEF18 = "vdef18";
  /**
   * 自定义项19
   */
  public static final String VDEF19 = "vdef19";
  /**
   * 自定义项20
   */
  public static final String VDEF20 = "vdef20";
  /**
   * 到货类型
   */
  public static final String CTRANTYPEID = "ctrantypeid";
  /**
   * 发布
   */
  public static final String BPUBLISH = "bpublish";
  /**
   * 发布人
   */
  public static final String PK_PUBPSN = "pk_pubpsn";
  /**
   * 发布时间
   */
  public static final String TPUBTIME = "tpubtime";
  /**
   * 响应状态
   */
  public static final String IRESPSTATUS = "irespstatus";
  /**
   * 响应人
   */
  public static final String PK_RESPPSN = "pk_resppsn";
  /**
   * 响应时间
   */
  public static final String TRESPTIME = "tresptime";
  /**
   * 供应商退货说明
   */
  public static final String VSUPBACKREASON = "vsupbackreason";
  /**
   * vostatus
   */
  public static final String STATUS = "status";
  /**
   * dr
   */
  public static final String DR = "dr";
  /**
   * ts
   */
  public static final String TS = "ts";
  /**
   * 到货单明细.到货单明细
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_B = "pk_arriveorder_b.pk_arriveorder_b";
  /**
   * 到货单明细.行号
   */
  public static final String PK_ARRIVEORDER_B_CROWNO = "pk_arriveorder_b.crowno";
  /**
   * 到货单明细.库存组织
   */
  public static final String PK_ARRIVEORDER_B_PK_ORG = "pk_arriveorder_b.pk_org";
  /**
   * 到货单明细.库存组织
   */
  public static final String PK_ARRIVEORDER_B_PK_ORG_V = "pk_arriveorder_b.pk_org_v";
  /**
   * 到货单明细.集团
   */
  public static final String PK_ARRIVEORDER_B_PK_GROUP = "pk_arriveorder_b.pk_group";
  /**
   * 到货单明细.结算财务组织
   */
  public static final String PK_ARRIVEORDER_B_PK_PSFINANCEORG = "pk_arriveorder_b.pk_psfinanceorg";
  /**
   * 到货单明细.结算财务组织
   */
  public static final String PK_ARRIVEORDER_B_PK_PSFINANCEORG_V = "pk_arriveorder_b.pk_psfinanceorg_v";
  /**
   * 到货单明细.应付组织
   */
  public static final String PK_ARRIVEORDER_B_PK_APFINANCEORG = "pk_arriveorder_b.pk_apfinanceorg";
  /**
   * 到货单明细.应付组织
   */
  public static final String PK_ARRIVEORDER_B_PK_APFINANCEORG_V = "pk_arriveorder_b.pk_apfinanceorg_v";
  /**
   * 到货单明细.需求库存组织
   */
  public static final String PK_ARRIVEORDER_B_PK_REQSTOORG = "pk_arriveorder_b.pk_reqstoorg";
  /**
   * 到货单明细.需求库存组织
   */
  public static final String PK_ARRIVEORDER_B_PK_REQSTOORG_V = "pk_arriveorder_b.pk_reqstoorg_v";
  /**
   * 到货单明细.需求仓库
   */
  public static final String PK_ARRIVEORDER_B_PK_REQSTORE = "pk_arriveorder_b.pk_reqstore";
  /**
   * 到货单明细.物料版本
   */
  public static final String PK_ARRIVEORDER_B_PK_MATERIAL = "pk_arriveorder_b.pk_material";
  /**
   * 到货单明细.物料
   */
  public static final String PK_ARRIVEORDER_B_PK_SRCMATERIAL = "pk_arriveorder_b.pk_srcmaterial";
  /**
   * 到货单明细.主单位
   */
  public static final String PK_ARRIVEORDER_B_CUNITID = "pk_arriveorder_b.cunitid";
  /**
   * 到货单明细.单位
   */
  public static final String PK_ARRIVEORDER_B_CASTUNITID = "pk_arriveorder_b.castunitid";
  /**
   * 到货单明细.换算率
   */
  public static final String PK_ARRIVEORDER_B_VCHANGERATE = "pk_arriveorder_b.vchangerate";
  /**
   * 到货单明细.固定换算率
   */
  public static final String PK_ARRIVEORDER_B_BFIXEDRATE = "pk_arriveorder_b.bfixedrate";
  /**
   * 到货单明细.到货主数量
   */
  public static final String PK_ARRIVEORDER_B_NNUM = "pk_arriveorder_b.nnum";
  /**
   * 到货单明细.到货数量
   */
  public static final String PK_ARRIVEORDER_B_NASTNUM = "pk_arriveorder_b.nastnum";
  /**
   * 到货单明细.途耗主数量
   */
  public static final String PK_ARRIVEORDER_B_NWASTNUM = "pk_arriveorder_b.nwastnum";
  /**
   * 到货单明细.途耗数量
   */
  public static final String PK_ARRIVEORDER_B_NWASTASTNUM = "pk_arriveorder_b.nwastastnum";
  /**
   * 到货单明细.应到主数量
   */
  public static final String PK_ARRIVEORDER_B_NPLANNUM = "pk_arriveorder_b.nplannum";
  /**
   * 到货单明细.应到数量
   */
  public static final String PK_ARRIVEORDER_B_NPLANASTNUM = "pk_arriveorder_b.nplanastnum";
  /**
   * 到货单明细.赠品
   */
  public static final String PK_ARRIVEORDER_B_BPRESENT = "pk_arriveorder_b.bpresent";
  /**
   * 到货单明细.赠品主数量
   */
  public static final String PK_ARRIVEORDER_B_NPRESENTNUM = "pk_arriveorder_b.npresentnum";
  /**
   * 到货单明细.赠品数量
   */
  public static final String PK_ARRIVEORDER_B_NPRESENTASTNUM = "pk_arriveorder_b.npresentastnum";
  /**
   * 到货单明细.原币币种
   */
  public static final String PK_ARRIVEORDER_B_CORIGCURRENCYID = "pk_arriveorder_b.corigcurrencyid";
  /**
   * 到货单明细.主原币含税单价
   */
  public static final String PK_ARRIVEORDER_B_NORIGTAXPRICE = "pk_arriveorder_b.norigtaxprice";
  /**
   * 到货单明细.主原币无税单价
   */
  public static final String PK_ARRIVEORDER_B_NORIGPRICE = "pk_arriveorder_b.norigprice";
  /**
   * 到货单明细.原币价税合计
   */
  public static final String PK_ARRIVEORDER_B_NORIGTAXMNY = "pk_arriveorder_b.norigtaxmny";
  /**
   * 到货单明细.原币无税金额
   */
  public static final String PK_ARRIVEORDER_B_NORIGMNY = "pk_arriveorder_b.norigmny";
  /**
   * 到货单明细.本币币种
   */
  public static final String PK_ARRIVEORDER_B_CCURRENCYID = "pk_arriveorder_b.ccurrencyid";
  /**
   * 到货单明细.主本币含税单价
   */
  public static final String PK_ARRIVEORDER_B_NTAXPRICE = "pk_arriveorder_b.ntaxprice";
  /**
   * 到货单明细.主本币无税单价
   */
  public static final String PK_ARRIVEORDER_B_NPRICE = "pk_arriveorder_b.nprice";
  /**
   * 到货单明细.本币价税合计
   */
  public static final String PK_ARRIVEORDER_B_NTAXMNY = "pk_arriveorder_b.ntaxmny";
  /**
   * 到货单明细.本币无税金额
   */
  public static final String PK_ARRIVEORDER_B_NMNY = "pk_arriveorder_b.nmny";
  /**
   * 到货单明细.扣税类别
   */
  public static final String PK_ARRIVEORDER_B_FTAXTYPEFLAG = "pk_arriveorder_b.ftaxtypeflag";
  /**
   * 到货单明细.税额
   */
  public static final String PK_ARRIVEORDER_B_NTAX = "pk_arriveorder_b.ntax";
  /**
   * 到货单明细.折本汇率
   */
  public static final String PK_ARRIVEORDER_B_NEXCHANGERATE = "pk_arriveorder_b.nexchangerate";
  /**
   * 到货单明细.计划到货日期
   */
  public static final String PK_ARRIVEORDER_B_DPLANRECEIVEDATE = "pk_arriveorder_b.dplanreceivedate";
  /**
   * 到货单明细.生产日期
   */
  public static final String PK_ARRIVEORDER_B_DPRODUCEDATE = "pk_arriveorder_b.dproducedate";
  /**
   * 到货单明细.保质期天数
   */
  public static final String PK_ARRIVEORDER_B_IVALIDDAY = "pk_arriveorder_b.ivalidday";
  /**
   * 到货单明细.失效日期
   */
  public static final String PK_ARRIVEORDER_B_DINVALIDDATE = "pk_arriveorder_b.dinvaliddate";
  /**
   * 到货单明细.退货基于原订单补货
   */
  public static final String PK_ARRIVEORDER_B_BBACKREFORDER = "pk_arriveorder_b.bbackreforder";
  /**
   * 到货单明细.报告人
   */
  public static final String PK_ARRIVEORDER_B_CREPORTERID = "pk_arriveorder_b.creporterid";
  /**
   * 到货单明细.报告日期
   */
  public static final String PK_ARRIVEORDER_B_DREPORTDATE = "pk_arriveorder_b.dreportdate";
  /**
   * 到货单明细.累计合格主数量
   */
  public static final String PK_ARRIVEORDER_B_NELIGNUM = "pk_arriveorder_b.nelignum";
  /**
   * 到货单明细.累计不合格主数量
   */
  public static final String PK_ARRIVEORDER_B_NNOTELIGNUM = "pk_arriveorder_b.nnotelignum";
  /**
   * 到货单明细.是否紧急放行状态
   */
  public static final String PK_ARRIVEORDER_B_BLETGOSTATE = "pk_arriveorder_b.bletgostate";
  /**
   * 到货单明细.紧急放行单主键
   */
  public static final String PK_ARRIVEORDER_B_PK_PASSBILL = "pk_arriveorder_b.pk_passbill";
  /**
   * 到货单明细.紧急放行单号
   */
  public static final String PK_ARRIVEORDER_B_VPASSBILLCODE = "pk_arriveorder_b.vpassbillcode";
  /**
   * 到货单明细.紧急放行单表体主键
   */
  public static final String PK_ARRIVEORDER_B_PK_PASSBILL_B = "pk_arriveorder_b.pk_passbill_b";
  /**
   * 到货单明细.紧急放行单行号
   */
  public static final String PK_ARRIVEORDER_B_CPASSBOLLROWNO = "pk_arriveorder_b.cpassbollrowno";
  /**
   * 到货单明细.累计紧急放行主数量
   */
  public static final String PK_ARRIVEORDER_B_NACCUMLETGONUM = "pk_arriveorder_b.naccumletgonum";
  /**
   * 到货单明细.累计紧急放行入库主数量
   */
  public static final String PK_ARRIVEORDER_B_NACCUMLETGOINNUM = "pk_arriveorder_b.naccumletgoinnum";
  /**
   * 到货单明细.累计报检主数量
   */
  public static final String PK_ARRIVEORDER_B_NACCUMCHECKNUM = "pk_arriveorder_b.naccumchecknum";
  /**
   * 到货单明细.累计入库主数量
   */
  public static final String PK_ARRIVEORDER_B_NACCUMSTORENUM = "pk_arriveorder_b.naccumstorenum";
  /**
   * 到货单明细.累计补货主数量
   */
  public static final String PK_ARRIVEORDER_B_NACCUMREPLNUM = "pk_arriveorder_b.naccumreplnum";
  /**
   * 到货单明细.收货仓库
   */
  public static final String PK_ARRIVEORDER_B_PK_RECEIVESTORE = "pk_arriveorder_b.pk_receivestore";
  /**
   * 到货单明细.货位
   */
  public static final String PK_ARRIVEORDER_B_PK_RACK = "pk_arriveorder_b.pk_rack";
  /**
   * 到货单明细.批次号主键
   */
  public static final String PK_ARRIVEORDER_B_PK_BATCHCODE = "pk_arriveorder_b.pk_batchcode";
  /**
   * 到货单明细.备注
   */
  public static final String PK_ARRIVEORDER_B_VMEMOB = "pk_arriveorder_b.vmemob";
  /**
   * 到货单明细.退货理由
   */
  public static final String PK_ARRIVEORDER_B_VBACKREASONB = "pk_arriveorder_b.vbackreasonb";
  /**
   * 到货单明细.项目
   */
  public static final String PK_ARRIVEORDER_B_CPROJECTID = "pk_arriveorder_b.cprojectid";
  /**
   * 到货单明细.生产厂商
   */
  public static final String PK_ARRIVEORDER_B_CPRODUCTORID = "pk_arriveorder_b.cproductorid";
  /**
   * 到货单明细.已生成资产片
   */
  public static final String PK_ARRIVEORDER_B_BFAFLAG = "pk_arriveorder_b.bfaflag";
  /**
   * 到货单明细.来源订单行是否赠品
   */
  public static final String PK_ARRIVEORDER_B_BPRESENTSOURCE = "pk_arriveorder_b.bpresentsource";
  /**
   * 到货单明细.采购订单
   */
  public static final String PK_ARRIVEORDER_B_PK_ORDER = "pk_arriveorder_b.pk_order";
  /**
   * 到货单明细.采购订单明细
   */
  public static final String PK_ARRIVEORDER_B_PK_ORDER_B = "pk_arriveorder_b.pk_order_b";
  /**
   * 到货单明细.订单到货计划
   */
  public static final String PK_ARRIVEORDER_B_PK_ORDER_BB1 = "pk_arriveorder_b.pk_order_bb1";
  /**
   * 到货单明细.来源单据类型
   */
  public static final String PK_ARRIVEORDER_B_CSOURCETYPECODE = "pk_arriveorder_b.csourcetypecode";
  /**
   * 到货单明细.来源单据号
   */
  public static final String PK_ARRIVEORDER_B_VSOURCECODE = "pk_arriveorder_b.vsourcecode";
  /**
   * 到货单明细.来源单据
   */
  public static final String PK_ARRIVEORDER_B_CSOURCEID = "pk_arriveorder_b.csourceid";
  /**
   * 到货单明细.来源单据明细
   */
  public static final String PK_ARRIVEORDER_B_CSOURCEBID = "pk_arriveorder_b.csourcebid";
  /**
   * 到货单明细.来源单据行号
   */
  public static final String PK_ARRIVEORDER_B_VSOURCEROWNO = "pk_arriveorder_b.vsourcerowno";
  /**
   * 到货单明细.来源交易类型
   */
  public static final String PK_ARRIVEORDER_B_VSOURCETRANTYPE = "pk_arriveorder_b.vsourcetrantype";
  /**
   * 到货单明细.源头单据类型
   */
  public static final String PK_ARRIVEORDER_B_CFIRSTTYPECODE = "pk_arriveorder_b.cfirsttypecode";
  /**
   * 到货单明细.源头单据号
   */
  public static final String PK_ARRIVEORDER_B_VFIRSTCODE = "pk_arriveorder_b.vfirstcode";
  /**
   * 到货单明细.源头单据
   */
  public static final String PK_ARRIVEORDER_B_CFIRSTID = "pk_arriveorder_b.cfirstid";
  /**
   * 到货单明细.源头单据明细
   */
  public static final String PK_ARRIVEORDER_B_CFIRSTBID = "pk_arriveorder_b.cfirstbid";
  /**
   * 到货单明细.源头单据行号
   */
  public static final String PK_ARRIVEORDER_B_VFIRSTROWNO = "pk_arriveorder_b.vfirstrowno";
  /**
   * 到货单明细.源头交易类型
   */
  public static final String PK_ARRIVEORDER_B_VFIRSTTRANTYPE = "pk_arriveorder_b.vfirsttrantype";
  /**
   * 到货单明细.自由辅助属性1
   */
  public static final String PK_ARRIVEORDER_B_VFREE1 = "pk_arriveorder_b.vfree1";
  /**
   * 到货单明细.自由辅助属性2
   */
  public static final String PK_ARRIVEORDER_B_VFREE2 = "pk_arriveorder_b.vfree2";
  /**
   * 到货单明细.自由辅助属性3
   */
  public static final String PK_ARRIVEORDER_B_VFREE3 = "pk_arriveorder_b.vfree3";
  /**
   * 到货单明细.自由辅助属性4
   */
  public static final String PK_ARRIVEORDER_B_VFREE4 = "pk_arriveorder_b.vfree4";
  /**
   * 到货单明细.自由辅助属性5
   */
  public static final String PK_ARRIVEORDER_B_VFREE5 = "pk_arriveorder_b.vfree5";
  /**
   * 到货单明细.自由辅助属性6
   */
  public static final String PK_ARRIVEORDER_B_VFREE6 = "pk_arriveorder_b.vfree6";
  /**
   * 到货单明细.自由辅助属性7
   */
  public static final String PK_ARRIVEORDER_B_VFREE7 = "pk_arriveorder_b.vfree7";
  /**
   * 到货单明细.自由辅助属性8
   */
  public static final String PK_ARRIVEORDER_B_VFREE8 = "pk_arriveorder_b.vfree8";
  /**
   * 到货单明细.自由辅助属性9
   */
  public static final String PK_ARRIVEORDER_B_VFREE9 = "pk_arriveorder_b.vfree9";
  /**
   * 到货单明细.自由辅助属性10
   */
  public static final String PK_ARRIVEORDER_B_VFREE10 = "pk_arriveorder_b.vfree10";
  /**
   * 到货单明细.自定义项1
   */
  public static final String PK_ARRIVEORDER_B_VBDEF1 = "pk_arriveorder_b.vbdef1";
  /**
   * 到货单明细.自定义项2
   */
  public static final String PK_ARRIVEORDER_B_VBDEF2 = "pk_arriveorder_b.vbdef2";
  /**
   * 到货单明细.自定义项3
   */
  public static final String PK_ARRIVEORDER_B_VBDEF3 = "pk_arriveorder_b.vbdef3";
  /**
   * 到货单明细.自定义项4
   */
  public static final String PK_ARRIVEORDER_B_VBDEF4 = "pk_arriveorder_b.vbdef4";
  /**
   * 到货单明细.自定义项5
   */
  public static final String PK_ARRIVEORDER_B_VBDEF5 = "pk_arriveorder_b.vbdef5";
  /**
   * 到货单明细.自定义项6
   */
  public static final String PK_ARRIVEORDER_B_VBDEF6 = "pk_arriveorder_b.vbdef6";
  /**
   * 到货单明细.自定义项7
   */
  public static final String PK_ARRIVEORDER_B_VBDEF7 = "pk_arriveorder_b.vbdef7";
  /**
   * 到货单明细.自定义项8
   */
  public static final String PK_ARRIVEORDER_B_VBDEF8 = "pk_arriveorder_b.vbdef8";
  /**
   * 到货单明细.自定义项9
   */
  public static final String PK_ARRIVEORDER_B_VBDEF9 = "pk_arriveorder_b.vbdef9";
  /**
   * 到货单明细.自定义项10
   */
  public static final String PK_ARRIVEORDER_B_VBDEF10 = "pk_arriveorder_b.vbdef10";
  /**
   * 到货单明细.自定义项11
   */
  public static final String PK_ARRIVEORDER_B_VBDEF11 = "pk_arriveorder_b.vbdef11";
  /**
   * 到货单明细.自定义项12
   */
  public static final String PK_ARRIVEORDER_B_VBDEF12 = "pk_arriveorder_b.vbdef12";
  /**
   * 到货单明细.自定义项13
   */
  public static final String PK_ARRIVEORDER_B_VBDEF13 = "pk_arriveorder_b.vbdef13";
  /**
   * 到货单明细.自定义项14
   */
  public static final String PK_ARRIVEORDER_B_VBDEF14 = "pk_arriveorder_b.vbdef14";
  /**
   * 到货单明细.自定义项15
   */
  public static final String PK_ARRIVEORDER_B_VBDEF15 = "pk_arriveorder_b.vbdef15";
  /**
   * 到货单明细.自定义项16
   */
  public static final String PK_ARRIVEORDER_B_VBDEF16 = "pk_arriveorder_b.vbdef16";
  /**
   * 到货单明细.自定义项17
   */
  public static final String PK_ARRIVEORDER_B_VBDEF17 = "pk_arriveorder_b.vbdef17";
  /**
   * 到货单明细.自定义项18
   */
  public static final String PK_ARRIVEORDER_B_VBDEF18 = "pk_arriveorder_b.vbdef18";
  /**
   * 到货单明细.自定义项19
   */
  public static final String PK_ARRIVEORDER_B_VBDEF19 = "pk_arriveorder_b.vbdef19";
  /**
   * 到货单明细.自定义项20
   */
  public static final String PK_ARRIVEORDER_B_VBDEF20 = "pk_arriveorder_b.vbdef20";
  /**
   * 到货单明细.来源单据时间戳
   */
  public static final String PK_ARRIVEORDER_B_SOURCETS = "pk_arriveorder_b.sourcets";
  /**
   * 到货单明细.来源单据行时间戳
   */
  public static final String PK_ARRIVEORDER_B_SOURCEBTS = "pk_arriveorder_b.sourcebts";
  /**
   * 到货单明细.可入库主数量
   */
  public static final String PK_ARRIVEORDER_B_NCANSTORENUM = "pk_arriveorder_b.ncanstorenum";
  /**
   * 到货单明细.批次号
   */
  public static final String PK_ARRIVEORDER_B_VBATCHCODE = "pk_arriveorder_b.vbatchcode";
  /**
   * 到货单明细.供应商批次号
   */
  public static final String PK_ARRIVEORDER_B_BC_VVENDBATCHCODE = "pk_arriveorder_b.bc_vvendbatchcode";
  /**
   * 到货单明细.批次质量等级
   */
  public static final String PK_ARRIVEORDER_B_BC_CQUALITYLEVELID = "pk_arriveorder_b.bc_cqualitylevelid";
  /**
   * 到货单明细.检验时间
   */
  public static final String PK_ARRIVEORDER_B_BC_TCHECKTIME = "pk_arriveorder_b.bc_tchecktime";
  /**
   * 到货单明细.批次是否封存
   */
  public static final String PK_ARRIVEORDER_B_BC_BSEAL = "pk_arriveorder_b.bc_bseal";
  /**
   * 到货单明细.批次形成时间
   */
  public static final String PK_ARRIVEORDER_B_BC_TBATCHTIME = "pk_arriveorder_b.bc_tbatchtime";
  /**
   * 到货单明细.批次备注
   */
  public static final String PK_ARRIVEORDER_B_BC_VNOTE = "pk_arriveorder_b.bc_vnote";
  /**
   * 到货单明细.批次库存状态
   */
  public static final String PK_ARRIVEORDER_B_BC_CSTATEID = "pk_arriveorder_b.bc_cstateid";
  /**
   * 到货单明细.批次版本
   */
  public static final String PK_ARRIVEORDER_B_BC_VERSION = "pk_arriveorder_b.bc_version";
  /**
   * 到货单明细.批次时间戳
   */
  public static final String PK_ARRIVEORDER_B_BC_TS = "pk_arriveorder_b.bc_ts";
  /**
   * 到货单明细.批次散列码
   */
  public static final String PK_ARRIVEORDER_B_BC_VHASHCODE = "pk_arriveorder_b.bc_vhashcode";
  /**
   * 到货单明细.批次自定义项1
   */
  public static final String PK_ARRIVEORDER_B_BC_VDEF1 = "pk_arriveorder_b.bc_vdef1";
  /**
   * 到货单明细.批次自定义项2
   */
  public static final String PK_ARRIVEORDER_B_BC_VDEF2 = "pk_arriveorder_b.bc_vdef2";
  /**
   * 到货单明细.批次自定义项3
   */
  public static final String PK_ARRIVEORDER_B_BC_VDEF3 = "pk_arriveorder_b.bc_vdef3";
  /**
   * 到货单明细.批次自定义项4
   */
  public static final String PK_ARRIVEORDER_B_BC_VDEF4 = "pk_arriveorder_b.bc_vdef4";
  /**
   * 到货单明细.批次自定义项5
   */
  public static final String PK_ARRIVEORDER_B_BC_VDEF5 = "pk_arriveorder_b.bc_vdef5";
  /**
   * 到货单明细.批次自定义项6
   */
  public static final String PK_ARRIVEORDER_B_BC_VDEF6 = "pk_arriveorder_b.bc_vdef6";
  /**
   * 到货单明细.批次自定义项7
   */
  public static final String PK_ARRIVEORDER_B_BC_VDEF7 = "pk_arriveorder_b.bc_vdef7";
  /**
   * 到货单明细.批次自定义项8
   */
  public static final String PK_ARRIVEORDER_B_BC_VDEF8 = "pk_arriveorder_b.bc_vdef8";
  /**
   * 到货单明细.批次自定义项9
   */
  public static final String PK_ARRIVEORDER_B_BC_VDEF9 = "pk_arriveorder_b.bc_vdef9";
  /**
   * 到货单明细.批次自定义项10
   */
  public static final String PK_ARRIVEORDER_B_BC_VDEF10 = "pk_arriveorder_b.bc_vdef10";
  /**
   * 到货单明细.批次自定义项11
   */
  public static final String PK_ARRIVEORDER_B_BC_VDEF11 = "pk_arriveorder_b.bc_vdef11";
  /**
   * 到货单明细.批次自定义项12
   */
  public static final String PK_ARRIVEORDER_B_BC_VDEF12 = "pk_arriveorder_b.bc_vdef12";
  /**
   * 到货单明细.批次自定义项13
   */
  public static final String PK_ARRIVEORDER_B_BC_VDEF13 = "pk_arriveorder_b.bc_vdef13";
  /**
   * 到货单明细.批次自定义项14
   */
  public static final String PK_ARRIVEORDER_B_BC_VDEF14 = "pk_arriveorder_b.bc_vdef14";
  /**
   * 到货单明细.批次自定义项15
   */
  public static final String PK_ARRIVEORDER_B_BC_VDEF15 = "pk_arriveorder_b.bc_vdef15";
  /**
   * 到货单明细.批次自定义项16
   */
  public static final String PK_ARRIVEORDER_B_BC_VDEF16 = "pk_arriveorder_b.bc_vdef16";
  /**
   * 到货单明细.批次自定义项17
   */
  public static final String PK_ARRIVEORDER_B_BC_VDEF17 = "pk_arriveorder_b.bc_vdef17";
  /**
   * 到货单明细.批次自定义项18
   */
  public static final String PK_ARRIVEORDER_B_BC_VDEF18 = "pk_arriveorder_b.bc_vdef18";
  /**
   * 到货单明细.批次自定义项19
   */
  public static final String PK_ARRIVEORDER_B_BC_VDEF19 = "pk_arriveorder_b.bc_vdef19";
  /**
   * 到货单明细.批次自定义项20
   */
  public static final String PK_ARRIVEORDER_B_BC_VDEF20 = "pk_arriveorder_b.bc_vdef20";
  /**
   * 到货单明细.到货日期
   */
  public static final String PK_ARRIVEORDER_B_DBILLDATE = "pk_arriveorder_b.dbilldate";
  /**
   * 到货单明细.税率
   */
  public static final String PK_ARRIVEORDER_B_NTAXRATE = "pk_arriveorder_b.ntaxrate";
  /**
   * 到货单明细.可补货主数量
   */
  public static final String PK_ARRIVEORDER_B_NCANREPLNUM = "pk_arriveorder_b.ncanreplnum";
  /**
   * 到货单明细.检验明细主键
   */
  public static final String PK_ARRIVEORDER_B_PK_CHECKDETAIL = "pk_arriveorder_b.pk_checkdetail";
  /**
   * 到货单明细.可到货主数量
   */
  public static final String PK_ARRIVEORDER_B_NCANARRIVENUM = "pk_arriveorder_b.ncanarrivenum";
  /**
   * 到货单明细.到货关闭
   */
  public static final String PK_ARRIVEORDER_B_BCLOSEORDER = "pk_arriveorder_b.bcloseorder";
  /**
   * 到货单明细.质检中心
   */
  public static final String PK_ARRIVEORDER_B_PK_QCCENTER = "pk_arriveorder_b.pk_qccenter";
  /**
   * 到货单明细.质量等级
   */
  public static final String PK_ARRIVEORDER_B_CQUALITYLEVELID = "pk_arriveorder_b.cqualitylevelid";
  /**
   * 到货单明细.生产批次号
   */
  public static final String PK_ARRIVEORDER_B_VPRODBATCHCODE = "pk_arriveorder_b.vprodbatchcode";
  /**
   * 到货单明细.已生成转固单
   */
  public static final String PK_ARRIVEORDER_B_BTRANSASSET = "pk_arriveorder_b.btransasset";
  /**
   * 到货单明细.来源单据主数量
   */
  public static final String PK_ARRIVEORDER_B_NSOURCENUM = "pk_arriveorder_b.nsourcenum";
  /**
   * 到货单明细.本次报检数量
   */
  public static final String PK_ARRIVEORDER_B_NCHECKNUM = "pk_arriveorder_b.nchecknum";
  /**
   * 到货单明细.合格主数量
   */
  public static final String PK_ARRIVEORDER_B_NWILLELIGNUM = "pk_arriveorder_b.nwillelignum";
  /**
   * 到货单明细.不合格主数量
   */
  public static final String PK_ARRIVEORDER_B_NWILLNOTELIGNUM = "pk_arriveorder_b.nwillnotelignum";
  /**
   * 到货单明细.客户
   */
  public static final String PK_ARRIVEORDER_B_CASSCUSTID = "pk_arriveorder_b.casscustid";
  /**
   * 到货单明细.项目任务
   */
  public static final String PK_ARRIVEORDER_B_CPROJECTTASKID = "pk_arriveorder_b.cprojecttaskid";
  /**
   * 到货单明细.发货国家/地区
   */
  public static final String PK_ARRIVEORDER_B_CSENDCOUNTRYID = "pk_arriveorder_b.csendcountryid";
  /**
   * 到货单明细.收货国家/地区
   */
  public static final String PK_ARRIVEORDER_B_CRECECOUNTRYID = "pk_arriveorder_b.crececountryid";
  /**
   * 到货单明细.报税国家/地区
   */
  public static final String PK_ARRIVEORDER_B_CTAXCOUNTRYID = "pk_arriveorder_b.ctaxcountryid";
  /**
   * 到货单明细.购销类型
   */
  public static final String PK_ARRIVEORDER_B_FBUYSELLFLAG = "pk_arriveorder_b.fbuysellflag";
  /**
   * 到货单明细.三角贸易
   */
  public static final String PK_ARRIVEORDER_B_BTRIATRADEFLAG = "pk_arriveorder_b.btriatradeflag";
  /**
   * 到货单明细.来源到货单
   */
  public static final String PK_ARRIVEORDER_B_CSOURCEARRIVEID = "pk_arriveorder_b.csourcearriveid";
  /**
   * 到货单明细.来源到货单明细
   */
  public static final String PK_ARRIVEORDER_B_CSOURCEARRIVEBID = "pk_arriveorder_b.csourcearrivebid";
  /**
   * 到货单明细.累计退货主数量
   */
  public static final String PK_ARRIVEORDER_B_NACCUMBACKNUM = "pk_arriveorder_b.naccumbacknum";
  /**
   * 到货单明细.结算利润中心最新版本
   */
  public static final String PK_ARRIVEORDER_B_PK_APLIABCENTER = "pk_arriveorder_b.pk_apliabcenter";
  /**
   * 到货单明细.结算利润中心
   */
  public static final String PK_ARRIVEORDER_B_PK_APLIABCENTER_V = "pk_arriveorder_b.pk_apliabcenter_v";
  /**
   * 到货单明细.收货利润中心最新版本
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRLIABCENTER = "pk_arriveorder_b.pk_arrliabcenter";
  /**
   * 到货单明细.收货利润中心
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRLIABCENTER_V = "pk_arriveorder_b.pk_arrliabcenter_v";
  /**
   * 到货单明细.产品类型
   */
  public static final String PK_ARRIVEORDER_B_FPRODUCTCLASS = "pk_arriveorder_b.fproductclass";
  /**
   * 到货单明细.特征码
   */
  public static final String PK_ARRIVEORDER_B_CFFILEID = "pk_arriveorder_b.cffileid";
  /**
   * 到货单明细.vostatus
   */
  public static final String PK_ARRIVEORDER_B_STATUS = "pk_arriveorder_b.status";
  /**
   * 到货单明细.dr
   */
  public static final String PK_ARRIVEORDER_B_DR = "pk_arriveorder_b.dr";
  /**
   * 到货单明细.ts
   */
  public static final String PK_ARRIVEORDER_B_TS = "pk_arriveorder_b.ts";
  /**
   * 到货单明细.到货单质检明细.到货单质检明细
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_PK_ARRIVEORDER_BB = "pk_arriveorder_b.pk_arriveorder_bb.pk_arriveorder_bb";
  /**
   * 到货单明细.到货单质检明细.到货单主键
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_PK_ARRIVEORDER = "pk_arriveorder_b.pk_arriveorder_bb.pk_arriveorder";
  /**
   * 到货单明细.到货单质检明细.所属集团
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_PK_GROUP = "pk_arriveorder_b.pk_arriveorder_bb.pk_group";
  /**
   * 到货单明细.到货单质检明细.是否可入库
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_BCANSTORE = "pk_arriveorder_b.pk_arriveorder_bb.bcanstore";
  /**
   * 到货单明细.到货单质检明细.主数量
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_NNUM = "pk_arriveorder_b.pk_arriveorder_bb.nnum";
  /**
   * 到货单明细.到货单质检明细.数量
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_NASTNUM = "pk_arriveorder_b.pk_arriveorder_bb.nastnum";
  /**
   * 到货单明细.到货单质检明细.入库批次号主键
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_PK_INBATCHCODE = "pk_arriveorder_b.pk_arriveorder_bb.pk_inbatchcode";
  /**
   * 到货单明细.到货单质检明细.入库批次号编码
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_VINBATCHCODE = "pk_arriveorder_b.pk_arriveorder_bb.vinbatchcode";
  /**
   * 到货单明细.到货单质检明细.累计入库数量
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_NACCUMSTORENUM = "pk_arriveorder_b.pk_arriveorder_bb.naccumstorenum";
  /**
   * 到货单明细.到货单质检明细.是否合格
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_BELIGIBLE = "pk_arriveorder_b.pk_arriveorder_bb.beligible";
  /**
   * 到货单明细.到货单质检明细.是否改判
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_BCHANGED = "pk_arriveorder_b.pk_arriveorder_bb.bchanged";
  /**
   * 到货单明细.到货单质检明细.改判物料版本
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_PK_CHGMATERIAL = "pk_arriveorder_b.pk_arriveorder_bb.pk_chgmaterial";
  /**
   * 到货单明细.到货单质检明细.改判物料
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_PK_CHGSRCMATERIAL = "pk_arriveorder_b.pk_arriveorder_bb.pk_chgsrcmaterial";
  /**
   * 到货单明细.到货单质检明细.主单位
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_CUNITID = "pk_arriveorder_b.pk_arriveorder_bb.cunitid";
  /**
   * 到货单明细.到货单质检明细.单位
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_CASTUNITID = "pk_arriveorder_b.pk_arriveorder_bb.castunitid";
  /**
   * 到货单明细.到货单质检明细.换算率
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_VCHANGERATE = "pk_arriveorder_b.pk_arriveorder_bb.vchangerate";
  /**
   * 到货单明细.到货单质检明细.改判自由辅助属性1
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_VCHGFREE1 = "pk_arriveorder_b.pk_arriveorder_bb.vchgfree1";
  /**
   * 到货单明细.到货单质检明细.改判自由辅助属性2
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_VCHGFREE2 = "pk_arriveorder_b.pk_arriveorder_bb.vchgfree2";
  /**
   * 到货单明细.到货单质检明细.改判自由辅助属性3
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_VCHGFREE3 = "pk_arriveorder_b.pk_arriveorder_bb.vchgfree3";
  /**
   * 到货单明细.到货单质检明细.改判自由辅助属性4
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_VCHGFREE4 = "pk_arriveorder_b.pk_arriveorder_bb.vchgfree4";
  /**
   * 到货单明细.到货单质检明细.改判自由辅助属性5
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_VCHGFREE5 = "pk_arriveorder_b.pk_arriveorder_bb.vchgfree5";
  /**
   * 到货单明细.到货单质检明细.改判自由辅助属性6
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_VCHGFREE6 = "pk_arriveorder_b.pk_arriveorder_bb.vchgfree6";
  /**
   * 到货单明细.到货单质检明细.改判自由辅助属性7
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_VCHGFREE7 = "pk_arriveorder_b.pk_arriveorder_bb.vchgfree7";
  /**
   * 到货单明细.到货单质检明细.改判自由辅助属性8
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_VCHGFREE8 = "pk_arriveorder_b.pk_arriveorder_bb.vchgfree8";
  /**
   * 到货单明细.到货单质检明细.改判自由辅助属性9
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_VCHGFREE9 = "pk_arriveorder_b.pk_arriveorder_bb.vchgfree9";
  /**
   * 到货单明细.到货单质检明细.改判自由辅助属性10
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_VCHGFREE10 = "pk_arriveorder_b.pk_arriveorder_bb.vchgfree10";
  /**
   * 到货单明细.到货单质检明细.质检报告主键
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_PK_QCREPORT = "pk_arriveorder_b.pk_arriveorder_bb.pk_qcreport";
  /**
   * 到货单明细.到货单质检明细.vostatus
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_STATUS = "pk_arriveorder_b.pk_arriveorder_bb.status";
  /**
   * 到货单明细.到货单质检明细.dr
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_DR = "pk_arriveorder_b.pk_arriveorder_bb.dr";
  /**
   * 到货单明细.到货单质检明细.ts
   */
  public static final String PK_ARRIVEORDER_B_PK_ARRIVEORDER_BB_TS = "pk_arriveorder_b.pk_arriveorder_bb.ts";
}


