package nc.vo.pu.m422x.rule.api;

import java.io.Serializable;
/**
 * 
 * @description
 *		物资需求申请单VO
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *		物资需求申请单VO
 * @since 6.5
 * @version 2015-11-10 下午7:51:31
 * @author wandl
 */
public interface IStoreReqAppVO extends Serializable {
  
  /**
   * 物资需求申请单
   */
  public static final String PK_STOREREQ = "pk_storereq";
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
   * 申请单号
   */
  public static final String VBILLCODE = "vbillcode";
  /**
   * 申请日期
   */
  public static final String DBILLDATE = "dbilldate";
  /**
   * 申请部门
   */
  public static final String PK_APPDEPTH = "pk_appdepth";
  /**
   * 申请部门
   */
  public static final String PK_APPDEPTH_V = "pk_appdepth_v";
  /**
   * 申请人
   */
  public static final String PK_APPPSNH = "pk_apppsnh";
  /**
   * 单据类型编码编码
   */
  public static final String VTRANTYPECODE = "vtrantypecode";
  /**
   * 单据状态
   */
  public static final String FBILLSTATUS = "fbillstatus";
  /**
   * 紧急
   */
  public static final String BURGENCY = "burgency";
  /**
   * 总数量
   */
  public static final String NTOTALASTNUM = "ntotalastnum";
  /**
   * 价税合计
   */
  public static final String NTOTALORIGMNY = "ntotalorigmny";
  /**
   * 项目
   */
  public static final String PK_PROJECT = "pk_project";
  /**
   * 创建人
   */
  public static final String CREATOR = "creator";
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
   * 审批人
   */
  public static final String APPROVER = "approver";
  /**
   * 审批日期
   */
  public static final String TAUDITTIME = "taudittime";
  /**
   * 最后修改人
   */
  public static final String MODIFIER = "modifier";
  /**
   * 最后修改时间
   */
  public static final String MODIFIEDTIME = "modifiedtime";
  /**
   * 打印次数
   */
  public static final String IPRINTCOUNT = "iprintcount";
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
   * 备注
   */
  public static final String VMEMO = "vmemo";
  /**
   * 需求类型
   */
  public static final String FREQTYPEFLAG = "freqtypeflag";
  /**
   * 物资需求申请类型
   */
  public static final String CTRANTYPEID = "ctrantypeid";
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
   * 物资需求申请单明细.物资需求申请单明细
   */
  public static final String PK_STOREREQ_B_PK_STOREREQ_B = "pk_storereq_b.pk_storereq_b";
  /**
   * 物资需求申请单明细.集团
   */
  public static final String PK_STOREREQ_B_PK_GROUP = "pk_storereq_b.pk_group";
  /**
   * 物资需求申请单明细.库存组织
   */
  public static final String PK_STOREREQ_B_PK_ORG = "pk_storereq_b.pk_org";
  /**
   * 物资需求申请单明细.库存组织
   */
  public static final String PK_STOREREQ_B_PK_ORG_V = "pk_storereq_b.pk_org_v";
  /**
   * 物资需求申请单明细.行号
   */
  public static final String PK_STOREREQ_B_CROWNO = "pk_storereq_b.crowno";
  /**
   * 物资需求申请单明细.物料版本信息
   */
  public static final String PK_STOREREQ_B_PK_MATERIAL = "pk_storereq_b.pk_material";
  /**
   * 物资需求申请单明细.物料信息
   */
  public static final String PK_STOREREQ_B_PK_SRCMATERIAL = "pk_storereq_b.pk_srcmaterial";
  /**
   * 物资需求申请单明细.主单位
   */
  public static final String PK_STOREREQ_B_CUNITID = "pk_storereq_b.cunitid";
  /**
   * 物资需求申请单明细.主数量
   */
  public static final String PK_STOREREQ_B_NNUM = "pk_storereq_b.nnum";
  /**
   * 物资需求申请单明细.单位
   */
  public static final String PK_STOREREQ_B_CASTUNITID = "pk_storereq_b.castunitid";
  /**
   * 物资需求申请单明细.数量
   */
  public static final String PK_STOREREQ_B_NASTNUM = "pk_storereq_b.nastnum";
  /**
   * 物资需求申请单明细.换算率
   */
  public static final String PK_STOREREQ_B_VCHANGERATE = "pk_storereq_b.vchangerate";
  /**
   * 物资需求申请单明细.主本币含税单价
   */
  public static final String PK_STOREREQ_B_NTAXPRICE = "pk_storereq_b.ntaxprice";
  /**
   * 物资需求申请单明细.本币价税合计
   */
  public static final String PK_STOREREQ_B_NTAXMNY = "pk_storereq_b.ntaxmny";
  /**
   * 物资需求申请单明细.需求仓库
   */
  public static final String PK_STOREREQ_B_PK_REQSTORDOC = "pk_storereq_b.pk_reqstordoc";
  /**
   * 物资需求申请单明细.需求日期
   */
  public static final String PK_STOREREQ_B_DREQDATE = "pk_storereq_b.dreqdate";
  /**
   * 物资需求申请单明细.申请人
   */
  public static final String PK_STOREREQ_B_PK_APPPSN = "pk_storereq_b.pk_apppsn";
  /**
   * 物资需求申请单明细.申请部门
   */
  public static final String PK_STOREREQ_B_PK_APPDEPT_V = "pk_storereq_b.pk_appdept_v";
  /**
   * 物资需求申请单明细.申请部门
   */
  public static final String PK_STOREREQ_B_PK_APPDEPT = "pk_storereq_b.pk_appdept";
  /**
   * 物资需求申请单明细.收货地址
   */
  public static final String PK_STOREREQ_B_PK_RECEIVEADDRESS = "pk_storereq_b.pk_receiveaddress";
  /**
   * 物资需求申请单明细.收货地区
   */
  public static final String PK_STOREREQ_B_CDEVAREAID = "pk_storereq_b.cdevareaid";
  /**
   * 物资需求申请单明细.收货地点
   */
  public static final String PK_STOREREQ_B_CDEVADDRID = "pk_storereq_b.cdevaddrid";
  /**
   * 物资需求申请单明细.来源单据类型
   */
  public static final String PK_STOREREQ_B_CSOURCETYPECODE = "pk_storereq_b.csourcetypecode";
  /**
   * 物资需求申请单明细.来源单据
   */
  public static final String PK_STOREREQ_B_CSOURCEID = "pk_storereq_b.csourceid";
  /**
   * 物资需求申请单明细.来源单据明细
   */
  public static final String PK_STOREREQ_B_CSOURCEBID = "pk_storereq_b.csourcebid";
  /**
   * 物资需求申请单明细.来源单据号
   */
  public static final String PK_STOREREQ_B_VSOURCECODE = "pk_storereq_b.vsourcecode";
  /**
   * 物资需求申请单明细.来源交易类型
   */
  public static final String PK_STOREREQ_B_VSOURCETRANTYPE = "pk_storereq_b.vsourcetrantype";
  /**
   * 物资需求申请单明细.来源单据行号
   */
  public static final String PK_STOREREQ_B_VSOURCEROWNO = "pk_storereq_b.vsourcerowno";
  /**
   * 物资需求申请单明细.来源单据时间戳
   */
  public static final String PK_STOREREQ_B_SOURCETS = "pk_storereq_b.sourcets";
  /**
   * 物资需求申请单明细.来源单据行时间戳
   */
  public static final String PK_STOREREQ_B_SOURCEBTS = "pk_storereq_b.sourcebts";
  /**
   * 物资需求申请单明细.源头单据类型
   */
  public static final String PK_STOREREQ_B_CFIRSTTYPECODE = "pk_storereq_b.cfirsttypecode";
  /**
   * 物资需求申请单明细.源头单据
   */
  public static final String PK_STOREREQ_B_CFIRSTID = "pk_storereq_b.cfirstid";
  /**
   * 物资需求申请单明细.源头单据明细
   */
  public static final String PK_STOREREQ_B_CFIRSTBID = "pk_storereq_b.cfirstbid";
  /**
   * 物资需求申请单明细.源头单据号
   */
  public static final String PK_STOREREQ_B_VFIRSTCODE = "pk_storereq_b.vfirstcode";
  /**
   * 物资需求申请单明细.源头交易类型
   */
  public static final String PK_STOREREQ_B_VFIRSTTRANTYPE = "pk_storereq_b.vfirsttrantype";
  /**
   * 物资需求申请单明细.源头单据行号
   */
  public static final String PK_STOREREQ_B_VFIRSTROWNO = "pk_storereq_b.vfirstrowno";
  /**
   * 物资需求申请单明细.累计出库主数量
   */
  public static final String PK_STOREREQ_B_NACCUOUTNUM = "pk_storereq_b.naccuoutnum";
  /**
   * 物资需求申请单明细.累计申请出库主数量
   */
  public static final String PK_STOREREQ_B_NACCUOUTREQNUM = "pk_storereq_b.naccuoutreqnum";
  /**
   * 物资需求申请单明细.可申请出库主数量
   */
  public static final String PK_STOREREQ_B_NCANOUTREQNUM = "pk_storereq_b.ncanoutreqnum";
  /**
   * 物资需求申请单明细.是否关闭
   */
  public static final String PK_STOREREQ_B_BCLOSE = "pk_storereq_b.bclose";
  /**
   * 物资需求申请单明细.批次号主键
   */
  public static final String PK_STOREREQ_B_PK_BATCHCODE = "pk_storereq_b.pk_batchcode";
  /**
   * 物资需求申请单明细.批次号
   */
  public static final String PK_STOREREQ_B_VBATCHCODE = "pk_storereq_b.vbatchcode";
  /**
   * 物资需求申请单明细.供应商
   */
  public static final String PK_STOREREQ_B_CVENDORID = "pk_storereq_b.cvendorid";
  /**
   * 物资需求申请单明细.生产厂商
   */
  public static final String PK_STOREREQ_B_CPRODUCTORID = "pk_storereq_b.cproductorid";
  /**
   * 物资需求申请单明细.项目
   */
  public static final String PK_STOREREQ_B_CPROJECTID = "pk_storereq_b.cprojectid";
  /**
   * 物资需求申请单明细.项目任务
   */
  public static final String PK_STOREREQ_B_CPROJECTTASKID = "pk_storereq_b.cprojecttaskid";
  /**
   * 物资需求申请单明细.成本要素
   */
  public static final String PK_STOREREQ_B_CCOSTELEMENTID = "pk_storereq_b.ccostelementid";
  /**
   * 物资需求申请单明细.自由辅助属性1
   */
  public static final String PK_STOREREQ_B_VFREE1 = "pk_storereq_b.vfree1";
  /**
   * 物资需求申请单明细.自由辅助属性2
   */
  public static final String PK_STOREREQ_B_VFREE2 = "pk_storereq_b.vfree2";
  /**
   * 物资需求申请单明细.自由辅助属性3
   */
  public static final String PK_STOREREQ_B_VFREE3 = "pk_storereq_b.vfree3";
  /**
   * 物资需求申请单明细.自由辅助属性4
   */
  public static final String PK_STOREREQ_B_VFREE4 = "pk_storereq_b.vfree4";
  /**
   * 物资需求申请单明细.自由辅助属性5
   */
  public static final String PK_STOREREQ_B_VFREE5 = "pk_storereq_b.vfree5";
  /**
   * 物资需求申请单明细.自由辅助属性6
   */
  public static final String PK_STOREREQ_B_VFREE6 = "pk_storereq_b.vfree6";
  /**
   * 物资需求申请单明细.自由辅助属性7
   */
  public static final String PK_STOREREQ_B_VFREE7 = "pk_storereq_b.vfree7";
  /**
   * 物资需求申请单明细.自由辅助属性8
   */
  public static final String PK_STOREREQ_B_VFREE8 = "pk_storereq_b.vfree8";
  /**
   * 物资需求申请单明细.自由辅助属性9
   */
  public static final String PK_STOREREQ_B_VFREE9 = "pk_storereq_b.vfree9";
  /**
   * 物资需求申请单明细.自由辅助属性10
   */
  public static final String PK_STOREREQ_B_VFREE10 = "pk_storereq_b.vfree10";
  /**
   * 物资需求申请单明细.自定义项1
   */
  public static final String PK_STOREREQ_B_VBDEF1 = "pk_storereq_b.vbdef1";
  /**
   * 物资需求申请单明细.自定义项2
   */
  public static final String PK_STOREREQ_B_VBDEF2 = "pk_storereq_b.vbdef2";
  /**
   * 物资需求申请单明细.自定义项3
   */
  public static final String PK_STOREREQ_B_VBDEF3 = "pk_storereq_b.vbdef3";
  /**
   * 物资需求申请单明细.自定义项4
   */
  public static final String PK_STOREREQ_B_VBDEF4 = "pk_storereq_b.vbdef4";
  /**
   * 物资需求申请单明细.自定义项5
   */
  public static final String PK_STOREREQ_B_VBDEF5 = "pk_storereq_b.vbdef5";
  /**
   * 物资需求申请单明细.自定义项6
   */
  public static final String PK_STOREREQ_B_VBDEF6 = "pk_storereq_b.vbdef6";
  /**
   * 物资需求申请单明细.自定义项7
   */
  public static final String PK_STOREREQ_B_VBDEF7 = "pk_storereq_b.vbdef7";
  /**
   * 物资需求申请单明细.自定义项8
   */
  public static final String PK_STOREREQ_B_VBDEF8 = "pk_storereq_b.vbdef8";
  /**
   * 物资需求申请单明细.自定义项9
   */
  public static final String PK_STOREREQ_B_VBDEF9 = "pk_storereq_b.vbdef9";
  /**
   * 物资需求申请单明细.自定义项10
   */
  public static final String PK_STOREREQ_B_VBDEF10 = "pk_storereq_b.vbdef10";
  /**
   * 物资需求申请单明细.自定义项11
   */
  public static final String PK_STOREREQ_B_VBDEF11 = "pk_storereq_b.vbdef11";
  /**
   * 物资需求申请单明细.自定义项12
   */
  public static final String PK_STOREREQ_B_VBDEF12 = "pk_storereq_b.vbdef12";
  /**
   * 物资需求申请单明细.自定义项13
   */
  public static final String PK_STOREREQ_B_VBDEF13 = "pk_storereq_b.vbdef13";
  /**
   * 物资需求申请单明细.自定义项14
   */
  public static final String PK_STOREREQ_B_VBDEF14 = "pk_storereq_b.vbdef14";
  /**
   * 物资需求申请单明细.自定义项15
   */
  public static final String PK_STOREREQ_B_VBDEF15 = "pk_storereq_b.vbdef15";
  /**
   * 物资需求申请单明细.自定义项16
   */
  public static final String PK_STOREREQ_B_VBDEF16 = "pk_storereq_b.vbdef16";
  /**
   * 物资需求申请单明细.自定义项17
   */
  public static final String PK_STOREREQ_B_VBDEF17 = "pk_storereq_b.vbdef17";
  /**
   * 物资需求申请单明细.自定义项18
   */
  public static final String PK_STOREREQ_B_VBDEF18 = "pk_storereq_b.vbdef18";
  /**
   * 物资需求申请单明细.自定义项19
   */
  public static final String PK_STOREREQ_B_VBDEF19 = "pk_storereq_b.vbdef19";
  /**
   * 物资需求申请单明细.自定义项20
   */
  public static final String PK_STOREREQ_B_VBDEF20 = "pk_storereq_b.vbdef20";
  /**
   * 物资需求申请单明细.备注
   */
  public static final String PK_STOREREQ_B_VBMEMO = "pk_storereq_b.vbmemo";
  /**
   * 物资需求申请单明细.申请日期
   */
  public static final String PK_STOREREQ_B_DBILLDATE = "pk_storereq_b.dbilldate";
  /**
   * 物资需求申请单明细.本币币种
   */
  public static final String PK_STOREREQ_B_CCURRENCYID = "pk_storereq_b.ccurrencyid";
  /**
   * 物资需求申请单明细.累计请购主数量
   */
  public static final String PK_STOREREQ_B_NACCUMBUYREQNUM = "pk_storereq_b.naccumbuyreqnum";
  /**
   * 物资需求申请单明细.可请购主数量
   */
  public static final String PK_STOREREQ_B_NCANBUYREQNNUM = "pk_storereq_b.ncanbuyreqnnum";
  /**
   * 物资需求申请单明细.原始需求库存组织
   */
  public static final String PK_STOREREQ_B_PK_REQSTOORG_V = "pk_storereq_b.pk_reqstoorg_v";
  /**
   * 物资需求申请单明细.原始需求库存组织最新版本
   */
  public static final String PK_STOREREQ_B_PK_REQSTOORG = "pk_storereq_b.pk_reqstoorg";
  /**
   * 物资需求申请单明细.下次平衡库存组织
   */
  public static final String PK_STOREREQ_B_PK_NEXTBALANCEORG_V = "pk_storereq_b.pk_nextbalanceorg_v";
  /**
   * 物资需求申请单明细.下次平衡库存组织最新版本
   */
  public static final String PK_STOREREQ_B_PK_NEXTBALANCEORG = "pk_storereq_b.pk_nextbalanceorg";
  /**
   * 物资需求申请单明细.已平衡
   */
  public static final String PK_STOREREQ_B_BENDGATHER = "pk_storereq_b.bendgather";
  /**
   * 物资需求申请单明细.库存满足主数量
   */
  public static final String PK_STOREREQ_B_NACCUSTORNUM = "pk_storereq_b.naccustornum";
  /**
   * 物资需求申请单明细.转净需求主数量
   */
  public static final String PK_STOREREQ_B_NNETNUM = "pk_storereq_b.nnetnum";
  /**
   * 物资需求申请单明细.下游单据
   */
  public static final String PK_STOREREQ_B_CSOURCEID2 = "pk_storereq_b.csourceid2";
  /**
   * 物资需求申请单明细.下游单据行
   */
  public static final String PK_STOREREQ_B_CSOURCEBID2 = "pk_storereq_b.csourcebid2";
  /**
   * 物资需求申请单明细.下游单据号
   */
  public static final String PK_STOREREQ_B_VSOURCECODE2 = "pk_storereq_b.vsourcecode2";
  /**
   * 物资需求申请单明细.下游单据行号
   */
  public static final String PK_STOREREQ_B_VSOURCEROWNO2 = "pk_storereq_b.vsourcerowno2";
  /**
   * 物资需求申请单明细.下游单据类型
   */
  public static final String PK_STOREREQ_B_CSOURCETYPECODE2 = "pk_storereq_b.csourcetypecode2";
  /**
   * 物资需求申请单明细.下游单据交易类型
   */
  public static final String PK_STOREREQ_B_VSOURCETRANTYPE2 = "pk_storereq_b.vsourcetrantype2";
  /**
   * 物资需求申请单明细.调拨订单
   */
  public static final String PK_STOREREQ_B_CFIRSTID2 = "pk_storereq_b.cfirstid2";
  /**
   * 物资需求申请单明细.调拨订单明细
   */
  public static final String PK_STOREREQ_B_CFIRSTBID2 = "pk_storereq_b.cfirstbid2";
  /**
   * 物资需求申请单明细.调拨单据号
   */
  public static final String PK_STOREREQ_B_VFIRSTCODE2 = "pk_storereq_b.vfirstcode2";
  /**
   * 物资需求申请单明细.调拨单据行号
   */
  public static final String PK_STOREREQ_B_VFIRSTROWNO2 = "pk_storereq_b.vfirstrowno2";
  /**
   * 物资需求申请单明细.调拨单据类型
   */
  public static final String PK_STOREREQ_B_CFIRSTTYPECODE2 = "pk_storereq_b.cfirsttypecode2";
  /**
   * 物资需求申请单明细.调拨单据交易类型
   */
  public static final String PK_STOREREQ_B_VFIRSTTRANTYPE2 = "pk_storereq_b.vfirsttrantype2";
  /**
   * 物资需求申请单明细.汇总时间
   */
  public static final String PK_STOREREQ_B_TGATHERTIME = "pk_storereq_b.tgathertime";
  /**
   * 物资需求申请单明细.汇总人
   */
  public static final String PK_STOREREQ_B_CGATHERPSNID = "pk_storereq_b.cgatherpsnid";
  /**
   * 物资需求申请单明细.汇总ID
   */
  public static final String PK_STOREREQ_B_CGATHERID = "pk_storereq_b.cgatherid";
  /**
   * 物资需求申请单明细.汇总平衡转请购主数量
   */
  public static final String PK_STOREREQ_B_NACCUMMINUSNUM = "pk_storereq_b.naccumminusnum";
  /**
   * 物资需求申请单明细.CBS
   */
  public static final String PK_STOREREQ_B_CBS = "pk_storereq_b.cbs";
  /**
   * 物资需求申请单明细.vostatus
   */
  public static final String PK_STOREREQ_B_STATUS = "pk_storereq_b.status";
  /**
   * 物资需求申请单明细.dr
   */
  public static final String PK_STOREREQ_B_DR = "pk_storereq_b.dr";
  /**
   * 物资需求申请单明细.ts
   */
  public static final String PK_STOREREQ_B_TS = "pk_storereq_b.ts";
}


