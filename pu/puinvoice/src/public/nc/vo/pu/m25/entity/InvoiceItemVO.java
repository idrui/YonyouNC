/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-6-26 下午02:56:11
 */
package nc.vo.pu.m25.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author zhaoyha
 * @time 2009-6-26 下午02:56:11
 */
public class InvoiceItemVO extends SuperVO {

  // add by zhangyhh for 进出口发票
  /** 被调整发票主键 */
  public static final String CADJUSTEDINVID = "cadjustedinvid";

  /** 被调整发票行主键 */
  public static final String CADJUSTEDROWID = "cadjustedrowid";

  /** 客户 */
  public static final String CASSCUSTID = "casscustid";

  /** 单位 */
  public static final String CASTUNITID = "castunitid";

  /** 合同主键 */
  public static final String CCONTRACTID = "ccontractid";

  /** 合同行主键 */
  public static final String CCONTRACTROWID = "ccontractrowid";

  /** 特征码 **/
  public static final String CFFILEID = "cffileid";

  /** 源头单据行主键 */
  public static final String CFIRSTBID = "cfirstbid";

  /** 源头单据主键 */
  public static final String CFIRSTID = "cfirstid";

  /** 源头单据类型 */
  public static final String CFIRSTTYPECODE = "cfirsttypecode";

  /** 生产厂商 */
  public static final String CPRODUCTORID = "cproductorid";

  /** 项目 */
  public static final String CPROJECTID = "cprojectid";

  /** 项目任务 */
  public static final String CPROJECTTASKID = "cprojecttaskid";

  /** 质量等级 */
  public static final String CQUALITYLEVELID = "cqualitylevelid";

  /** 行号 */
  public static final String CROWNO = "crowno";

  /** 来源单据行主键 */
  public static final String CSOURCEBID = "csourcebid";

  /** 来源单据主键 */
  public static final String CSOURCEID = "csourceid";

  /** 来源单据类型 */
  public static final String CSOURCETYPECODE = "csourcetypecode";

  /** 税码 */
  public static final String CTAXCODEID = "ctaxcodeid";

  /** 主单位 */
  public static final String CUNITID = "cunitid";

  /** 发票日期 */
  public static final String DBILLDATE = "dbilldate";

  /** dr */
  public static final String DR = "dr";

  /** 来源单据日期 */
  public static final String DSOURCEDATE = "dsourcedate";

  /** 源头单据行TS */
  public static final String FIRSTBTS = "firstbts";

  /** 源头单据TS */
  public static final String FIRSTTS = "firstts";

  /** 行类型 */
  public static final String FROWTYPE = "frowtype";

  /** 扣税类别 */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /** 累计本币结算金额 */
  public static final String NACCUMSETTMNY = "naccumsettmny";

  /** 累计结算主数量 */
  public static final String NACCUMSETTNUM = "naccumsettnum";

  /** 调整单价 */
  public static final String NADJUSTORGPRICE = "nadjustorgprice";

  /** 数量 */
  public static final String NASTNUM = "nastnum";

  /** 无税单价 */
  public static final String NASTORIGPRICE = "nastorigprice";

  /** 含税单价 */
  public static final String NASTORIGTAXPRICE = "nastorigtaxprice";

  /** 本币无税单价 */
  public static final String NASTPRICE = "nastprice";

  /** 本币含税单价 */
  public static final String NASTTAXPRICE = "nasttaxprice";

  /** 计成本金额 */
  public static final String NCALCOSTMNY = "ncalcostmny";

  /** 计税金额 */
  public static final String NCALTAXMNY = "ncaltaxmny";

  /** 全局本币无税金额 */
  public static final String NGLOBALMNY = "nglobalmny";

  /** 全局本币价税合计 */
  public static final String NGLOBALTAXMNY = "nglobaltaxmny";

  /** 集团本币无税金额 */
  public static final String NGROUPMNY = "ngroupmny";

  /** 集团本币价税合计 */
  public static final String NGROUPTAXMNY = "ngrouptaxmny";

  /** 本币无税金额 */
  public static final String NMNY = "nmny";

  /** 不可抵扣税额 */
  public static final String NNOSUBTAX = "nnosubtax";

  /** 不可抵扣税率 */
  public static final String NNOSUBTAXRATE = "nnosubtaxrate";

  /** 主数量 */
  public static final String NNUM = "nnum";

  /** 计算属性，用于回写来源=主数量－合理损耗数量 */
  public static final String NNUMWRBCK = "nnumwrbck";

  /** 无税金额 */
  public static final String NORIGMNY = "norigmny";

  /** 主无税单价 */
  public static final String NORIGPRICE = "norigprice";

  /** 价税合计 */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /** 主含税单价 */
  public static final String NORIGTAXPRICE = "norigtaxprice";

  /** 计划价 */
  public static final String NPLANPRICE = "nplanprice";

  /** 主本币无税单价 */
  public static final String NPRICE = "nprice";

  /** 合理损耗主数量 */
  public static final String NREASONWASTENUM = "nreasonwastenum";

  /** 来源单据主数量 */
  public static final String NSOURCENUM = "nsourcenum";

  /** 本币税额 */
  public static final String NTAX = "ntax";

  /** 本币价税合计 */
  public static final String NTAXMNY = "ntaxmny";

  /** 主本币含税单价 */
  public static final String NTAXPRICE = "ntaxprice";

  /** 税率 */
  public static final String NTAXRATE = "ntaxrate";

  /** 应付财务组织(OID) */
  public static final String PK_APFINANCEORG = "pk_apfinanceorg";

  /** 应付财务组织(VID) */
  public static final String PK_APFINANCEORG_V = "pk_apfinanceorg_v";

  /** 利润中心(OID) */
  public static final String PK_APLIABCENTER = "pk_apliabcenter";

  /** 利润中心(VID) */
  public static final String PK_APLIABCENTER_V = "pk_apliabcenter_v";

  /** 批次档案 */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /** 收支项目 */
  public static final String PK_COSTSUBJ = "pk_costsubj";

  /** 所属集团 */
  public static final String PK_GROUP = "pk_group";

  /** 发票子实体 */
  public static final String PK_INVOICE = "pk_invoice";

  /** 采购发票明细 */
  public static final String PK_INVOICE_B = "pk_invoice_b";

  /** 物料(VID) */
  public static final String PK_MATERIAL = "pk_material";

  /** 采购订单主键 */
  public static final String PK_ORDER = "pk_order";

  /** 采购订单行主键 */
  public static final String PK_ORDER_B = "pk_order_b";

  /** 财务组织(OID) */
  public static final String PK_ORG = "pk_org";

  /** 财务组织(VID) */
  public static final String PK_ORG_V = "pk_org_v";

  /** 物料(OID) */
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  /** 入库单行ID */
  public static final String PK_STOCKPS_B = "pk_stockps_b";

  /** 仓库 */
  public static final String PK_STORDOC = "pk_stordoc";

  /** 供应商 */
  public static final String PK_SUPPLIER = "pk_supplier";

  /** 使用部门(OID) */
  public static final String PK_USEDEPT = "pk_usedept";

  /** 使用部门(VID) */
  public static final String PK_USEDEPT_V = "pk_usedept_v";

  /** 来源单据行TS */
  public static final String SOURCEBTS = "sourcebts";

  /** 来源单据TS */
  public static final String SOURCETS = "sourcets";

  /** ts */
  public static final String TS = "ts";

  /** 被调整发票号 */
  public static final String VADJEDBILLCODE = "vadjedbillcode";

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

  /** 合同号 */
  public static final String VCTCODE = "vctcode";

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

  /** 订单号 */
  public static final String VORDERCODE = "vordercode";

  /** 订单交易类型 */
  public static final String VORDERTRANTYPE = "vordertrantype";

  /** 来源单据号 */
  public static final String VSOURCECODE = "vsourcecode";

  /** 来源单据行号 */
  public static final String VSOURCEROWNO = "vsourcerowno";

  /** 来源交易类型 */
  public static final String VSOURCETRANTYPE = "vsourcetrantype";

  private static final long serialVersionUID = 1L;

  /** 获取被调整发票主键 getter 方法 */
  public String getCadjustedinvid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CADJUSTEDINVID);
  }

  /** 获取被调整发票行主键 getter 方法 */
  public String getCadjustedrowid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CADJUSTEDROWID);
  }

  /** 客户 getter 方法 */
  public String getCasscustid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CASSCUSTID);
  }

  /** 单位 getter 方法 */
  public String getCastunitid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CASTUNITID);
  }

  /** 获取合同主键 getter 方法 */
  public String getCcontractid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CCONTRACTID);
  }

  /** 获取合同行主键 getter 方法 */
  public String getCcontractrowid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CCONTRACTROWID);
  }

  /** 特征码 **/
  public String getCffileid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CFFILEID);
  }

  /** 源头单据行主键 getter 方法 */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CFIRSTBID);
  }

  /** 源头单据主键 getter 方法 */
  public String getCfirstid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CFIRSTID);
  }

  /** 源头单据类型 getter 方法 */
  public String getCfirsttypecode() {
    return (String) this.getAttributeValue(InvoiceItemVO.CFIRSTTYPECODE);
  }

  /** 生产厂商 getter 方法 */
  public String getCproductorid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CPRODUCTORID);
  }

  /** 项目 getter 方法 */
  public String getCprojectid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CPROJECTID);
  }

  /** 项目任务 getter 方法 */
  public String getCprojecttaskid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CPROJECTTASKID);
  }

  /** 质量等级 getter 方法 */
  public String getCqualitylevelid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CQUALITYLEVELID);
  }

  /** 行号 getter 方法 */
  public String getCrowno() {
    return (String) this.getAttributeValue(InvoiceItemVO.CROWNO);
  }

  /** 来源单据行主键 getter 方法 */
  public String getCsourcebid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CSOURCEBID);
  }

  /** 来源单据主键 getter 方法 */
  public String getCsourceid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CSOURCEID);
  }

  /** 来源单据类型 getter 方法 */
  public String getCsourcetypecode() {
    return (String) this.getAttributeValue(InvoiceItemVO.CSOURCETYPECODE);
  }

  /** 税码 getter 方法 */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CTAXCODEID);
  }

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CUNITID);
  }

  /** 发票日期 getter 方法 */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(InvoiceItemVO.DBILLDATE);
  }

  /** dr getter 方法 */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(InvoiceItemVO.DR);
  }

  /** 来源单据日期 getter 方法 */
  public UFDate getDsourcedate() {
    return (UFDate) this.getAttributeValue(InvoiceItemVO.DSOURCEDATE);
  }

  /** 源头单据行TS getter 方法 */
  public UFDateTime getFirstbts() {
    return (UFDateTime) this.getAttributeValue(InvoiceItemVO.FIRSTBTS);
  }

  /** 源头单据TS getter 方法 */
  public UFDateTime getFirstts() {
    return (UFDateTime) this.getAttributeValue(InvoiceItemVO.FIRSTTS);
  }

  /** 行类型 getter 方法 */
  public Integer getFrowtype() {
    return (Integer) this.getAttributeValue(InvoiceItemVO.FROWTYPE);
  }

  /** 扣税类别 getter 方法 */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(InvoiceItemVO.FTAXTYPEFLAG);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.ISuperVO#getMetaData()
   */
  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta(PUEntity.M25_B);
    return meta;
  }

  /** 累计本币结算金额 getter 方法 */
  public UFDouble getNaccumsettmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NACCUMSETTMNY);
  }

  /** 累计结算主数量 getter 方法 */
  public UFDouble getNaccumsettnum() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NACCUMSETTNUM);
  }

  /** 获取调整单价 getter 方法 */
  public UFDouble getNadjustorgprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NADJUSTORGPRICE);
  }

  /** 数量 getter 方法 */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NASTNUM);
  }

  /** 无税单价 getter 方法 */
  public UFDouble getNastorigprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NASTORIGPRICE);
  }

  /** 含税单价 getter 方法 */
  public UFDouble getNastorigtaxprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NASTORIGTAXPRICE);
  }

  /** 本币无税单价 getter 方法 */
  public UFDouble getNastprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NASTPRICE);
  }

  /** 本币含税单价 getter 方法 */
  public UFDouble getNasttaxprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NASTTAXPRICE);
  }

  /** 计成本金额 getter 方法 */
  public UFDouble getNcalcostmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NCALCOSTMNY);
  }

  /** 计税金额 getter 方法 */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NCALTAXMNY);
  }

  /** 全局本币无税金额 getter 方法 */
  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NGLOBALMNY);
  }

  /** 全局本币价税合计 getter 方法 */
  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NGLOBALTAXMNY);
  }

  /** 集团本币无税金额 getter 方法 */
  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NGROUPMNY);
  }

  /** 集团本币价税合计 getter 方法 */
  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NGROUPTAXMNY);
  }

  /** 本币无税金额 getter 方法 */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NMNY);
  }

  /** 不可抵扣税额 getter 方法 */
  public UFDouble getNnosubtax() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NNOSUBTAX);
  }

  /** 不可抵扣税率 getter 方法 */
  public UFDouble getNnosubtaxrate() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NNOSUBTAXRATE);
  }

  /** 主数量 getter 方法 */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NNUM);
  }

  /** 计算属性，用于回写来源=主数量－合理损耗数量 */
  public UFDouble getNnumwrbck() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NNUMWRBCK);
  }

  /** 无税金额 getter 方法 */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NORIGMNY);
  }

  /** 主无税单价 getter 方法 */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NORIGPRICE);
  }

  /** 价税合计 getter 方法 */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NORIGTAXMNY);
  }

  /** 主含税单价 getter 方法 */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NORIGTAXPRICE);
  }

  /** 计划价 getter 方法 */
  public UFDouble getNplanprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NPLANPRICE);
  }

  /** 主本币无税单价 getter 方法 */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NPRICE);
  }

  /** 合理损耗主数量 getter 方法 */
  public UFDouble getNreasonwastenum() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NREASONWASTENUM);
  }

  /** 来源单据主数量 getter 方法 */
  public UFDouble getNsourcenum() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NSOURCENUM);
  }

  /** 本币税额 getter 方法 */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NTAX);
  }

  /** 本币价税合计 getter 方法 */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NTAXMNY);
  }

  /** 主本币含税单价 getter 方法 */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NTAXPRICE);
  }

  /** 税率 getter 方法 */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NTAXRATE);
  }

  /** 应付财务组织(OID) getter 方法 */
  public String getPk_apfinanceorg() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_APFINANCEORG);
  }

  /** 应付财务组织(VID) getter 方法 */
  public String getPk_apfinanceorg_v() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_APFINANCEORG_V);
  }

  /** 利润中心(OID) getter 方法 */
  public String getPk_apliabcenter() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_APLIABCENTER);
  }

  /** 利润中心(VID) getter 方法 */
  public String getPk_apliabcenter_v() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_APLIABCENTER_V);
  }

  /** 批次档案 getter 方法 */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_BATCHCODE);
  }

  /** 收支项目 getter 方法 */
  public String getPk_costsubj() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_COSTSUBJ);
  }

  /** 所属集团 getter 方法 */
  public String getPk_group() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_GROUP);
  }

  /** 发票子实体 getter 方法 */
  public String getPk_invoice() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_INVOICE);
  }

  /** 采购发票明细 getter 方法 */
  public String getPk_invoice_b() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_INVOICE_B);
  }

  /** 物料(VID) getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_MATERIAL);
  }

  /** 采购订单主键 getter 方法 */
  public String getPk_order() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_ORDER);
  }

  /** 采购订单行主键 getter 方法 */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_ORDER_B);
  }

  /** 财务组织(OID) getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_ORG);
  }

  /** 财务组织(VID) getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_ORG_V);
  }

  /** 物料(OID) getter 方法 */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_SRCMATERIAL);
  }

  /** 入库单行ID getter 方法 */
  public String getPk_stockps_b() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_STOCKPS_B);
  }

  /** 仓库 getter 方法 */
  public String getPk_stordoc() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_STORDOC);
  }

  /** 供应商 getter 方法 */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_SUPPLIER);
  }

  /** 使用部门(OID) getter 方法 */
  public String getPk_usedept() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_USEDEPT);
  }

  /** 使用部门(VID) getter 方法 */
  public String getPk_usedept_v() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_USEDEPT_V);
  }

  /** 来源单据行TS getter 方法 */
  public UFDateTime getSourcebts() {
    return (UFDateTime) this.getAttributeValue(InvoiceItemVO.SOURCEBTS);
  }

  /** 来源单据TS getter 方法 */
  public UFDateTime getSourcets() {
    return (UFDateTime) this.getAttributeValue(InvoiceItemVO.SOURCETS);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(InvoiceItemVO.TS);
  }

  /** 获取被调整发票号 getter 方法 */
  public String getVadjedbillcode() {
    return (String) this.getAttributeValue(InvoiceItemVO.VADJEDBILLCODE);
  }

  /** 批次号 getter 方法 */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBATCHCODE);
  }

  /** 自定义项1 getter 方法 */
  public String getVbdef1() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF1);
  }

  /** 自定义项10 getter 方法 */
  public String getVbdef10() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF10);
  }

  /** 自定义项11 getter 方法 */
  public String getVbdef11() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF11);
  }

  /** 自定义项12 getter 方法 */
  public String getVbdef12() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF12);
  }

  /** 自定义项13 getter 方法 */
  public String getVbdef13() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF13);
  }

  /** 自定义项14 getter 方法 */
  public String getVbdef14() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF14);
  }

  /** 自定义项15 getter 方法 */
  public String getVbdef15() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF15);
  }

  /** 自定义项16 getter 方法 */
  public String getVbdef16() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF16);
  }

  /** 自定义项17 getter 方法 */
  public String getVbdef17() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF17);
  }

  /** 自定义项18 getter 方法 */
  public String getVbdef18() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF18);
  }

  /** 自定义项19 getter 方法 */
  public String getVbdef19() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF19);
  }

  /** 自定义项2 getter 方法 */
  public String getVbdef2() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF2);
  }

  /** 自定义项20 getter 方法 */
  public String getVbdef20() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF20);
  }

  /** 自定义项3 getter 方法 */
  public String getVbdef3() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF3);
  }

  /** 自定义项4 getter 方法 */
  public String getVbdef4() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF4);
  }

  /** 自定义项5 getter 方法 */
  public String getVbdef5() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF5);
  }

  /** 自定义项6 getter 方法 */
  public String getVbdef6() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF6);
  }

  /** 自定义项7 getter 方法 */
  public String getVbdef7() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF7);
  }

  /** 自定义项8 getter 方法 */
  public String getVbdef8() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF8);
  }

  /** 自定义项9 getter 方法 */
  public String getVbdef9() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF9);
  }

  /** 换算率 getter 方法 */
  public String getVchangerate() {
    return (String) this.getAttributeValue(InvoiceItemVO.VCHANGERATE);
  }

  /** 合同号 getter 方法 */
  public String getVctcode() {
    return (String) this.getAttributeValue(InvoiceItemVO.VCTCODE);
  }

  /** 源头单据号 getter 方法 */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFIRSTCODE);
  }

  /** 源头单据行号 getter 方法 */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFIRSTROWNO);
  }

  /** 源头交易类型 getter 方法 */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFIRSTTRANTYPE);
  }

  /** 自由辅助属性1 getter 方法 */
  public String getVfree1() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE1);
  }

  /** 自由辅助属性10 getter 方法 */
  public String getVfree10() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE10);
  }

  /** 自由辅助属性2 getter 方法 */
  public String getVfree2() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE2);
  }

  /** 自由辅助属性3 getter 方法 */
  public String getVfree3() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE3);
  }

  /** 自由辅助属性4 getter 方法 */
  public String getVfree4() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE4);
  }

  /** 自由辅助属性5 getter 方法 */
  public String getVfree5() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE5);
  }

  /** 自由辅助属性6 getter 方法 */
  public String getVfree6() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE6);
  }

  /** 自由辅助属性7 getter 方法 */
  public String getVfree7() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE7);
  }

  /** 自由辅助属性8 getter 方法 */
  public String getVfree8() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE8);
  }

  /** 自由辅助属性9 getter 方法 */
  public String getVfree9() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE9);
  }

  /** 备注 getter 方法 */
  public String getVmemob() {
    return (String) this.getAttributeValue(InvoiceItemVO.VMEMOB);
  }

  /** 订单号 getter 方法 */
  public String getVordercode() {
    return (String) this.getAttributeValue(InvoiceItemVO.VORDERCODE);
  }

  /** 订单交易类型 getter 方法 */
  public String getVordertrantype() {
    return (String) this.getAttributeValue(InvoiceItemVO.VORDERTRANTYPE);
  }

  /** 来源单据号 getter 方法 */
  public String getVsourcecode() {
    return (String) this.getAttributeValue(InvoiceItemVO.VSOURCECODE);
  }

  /** 来源单据行号 getter 方法 */
  public String getVsourcerowno() {
    return (String) this.getAttributeValue(InvoiceItemVO.VSOURCEROWNO);
  }

  /** 来源交易类型 getter 方法 */
  public String getVsourcetrantype() {
    return (String) this.getAttributeValue(InvoiceItemVO.VSOURCETRANTYPE);
  }

  /** 设置被调整发票主键 setter 方法 */
  public void setCadjustedinvid(String cadjustedinvid) {
    this.setAttributeValue(InvoiceItemVO.CADJUSTEDINVID, cadjustedinvid);
  }

  /** 设置被调整发票行主键 setter 方法 */
  public void setCadjustedrowid(String cadjustedrowid) {
    this.setAttributeValue(InvoiceItemVO.CADJUSTEDROWID, cadjustedrowid);
  }

  /** 客户 setter 方法 */
  public void setCasscustid(String casscustid) {
    this.setAttributeValue(InvoiceItemVO.CASSCUSTID, casscustid);
  }

  /** 单位 setter 方法 */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(InvoiceItemVO.CASTUNITID, castunitid);
  }

  /** 设置合同主键 setter 方法 */
  public void setCcontractid(String ccontractid) {
    this.setAttributeValue(InvoiceItemVO.CCONTRACTID, ccontractid);
  }

  /** 设置合同行主键 setter 方法 */
  public void setCcontractrowid(String ccontractrowid) {
    this.setAttributeValue(InvoiceItemVO.CCONTRACTROWID, ccontractrowid);
  }

  /** 特征码 **/
  public void setCffileid(String cffileid) {
    this.setAttributeValue(InvoiceItemVO.CFFILEID, cffileid);
  }

  /** 源头单据行主键 setter 方法 */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(InvoiceItemVO.CFIRSTBID, cfirstbid);
  }

  /** 源头单据主键 setter 方法 */
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(InvoiceItemVO.CFIRSTID, cfirstid);
  }

  /** 源头单据类型 setter 方法 */
  public void setCfirsttypecode(String cfirsttypecode) {
    this.setAttributeValue(InvoiceItemVO.CFIRSTTYPECODE, cfirsttypecode);
  }

  /** 生产厂商 setter 方法 */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(InvoiceItemVO.CPRODUCTORID, cproductorid);
  }

  /** 项目 setter 方法 */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(InvoiceItemVO.CPROJECTID, cprojectid);
  }

  /** 项目任务 setter 方法 */
  public void setCprojecttaskid(String cprojecttaskid) {
    this.setAttributeValue(InvoiceItemVO.CPROJECTTASKID, cprojecttaskid);
  }

  /** 质量等级 setter 方法 */
  public void setCqualitylevelid(String cqualitylevelid) {
    this.setAttributeValue(InvoiceItemVO.CQUALITYLEVELID, cqualitylevelid);
  }

  /** 行号 setter 方法 */
  public void setCrowno(String crowno) {
    this.setAttributeValue(InvoiceItemVO.CROWNO, crowno);
  }

  /** 来源单据行主键 setter 方法 */
  public void setCsourcebid(String csourcebid) {
    this.setAttributeValue(InvoiceItemVO.CSOURCEBID, csourcebid);
  }

  /** 来源单据主键 setter 方法 */
  public void setCsourceid(String csourceid) {
    this.setAttributeValue(InvoiceItemVO.CSOURCEID, csourceid);
  }

  /** 来源单据类型 setter 方法 */
  public void setCsourcetypecode(String csourcetypecode) {
    this.setAttributeValue(InvoiceItemVO.CSOURCETYPECODE, csourcetypecode);
  }

  /** 税码 setter 方法 */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(InvoiceItemVO.CTAXCODEID, ctaxcodeid);
  }

  /** 主单位 setter 方法 */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(InvoiceItemVO.CUNITID, cunitid);
  }

  /** 发票日期 setter 方法 */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(InvoiceItemVO.DBILLDATE, dbilldate);
  }

  /** dr setter 方法 */
  public void setDr(Integer dr) {
    this.setAttributeValue(InvoiceItemVO.DR, dr);
  }

  /** 来源单据日期 setter 方法 */
  public void setDsourcedate(UFDate dsourcedate) {
    this.setAttributeValue(InvoiceItemVO.DSOURCEDATE, dsourcedate);
  }

  /** 源头单据行TS setter 方法 */
  public void setFirstbts(UFDateTime firstbts) {
    this.setAttributeValue(InvoiceItemVO.FIRSTBTS, firstbts);
  }

  /** 源头单据TS setter 方法 */
  public void setFirstts(UFDateTime firstts) {
    this.setAttributeValue(InvoiceItemVO.FIRSTTS, firstts);
  }

  /** 行类型 setter 方法 */
  public void setFrowtype(Integer frowtype) {
    this.setAttributeValue(InvoiceItemVO.FROWTYPE, frowtype);
  }

  /** 扣税类别 setter 方法 */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(InvoiceItemVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /** 累计本币结算金额 setter 方法 */
  public void setNaccumsettmny(UFDouble naccumsettmny) {
    this.setAttributeValue(InvoiceItemVO.NACCUMSETTMNY, naccumsettmny);
  }

  /** 累计结算主数量 setter 方法 */
  public void setNaccumsettnum(UFDouble naccumsettnum) {
    this.setAttributeValue(InvoiceItemVO.NACCUMSETTNUM, naccumsettnum);
  }

  /** 设置调整单价 setter 方法 */
  public void setNadjustorgprice(UFDouble nadjustorgprice) {
    this.setAttributeValue(InvoiceItemVO.NADJUSTORGPRICE, nadjustorgprice);
  }

  /** 数量 setter 方法 */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(InvoiceItemVO.NASTNUM, nastnum);
  }

  /** 无税单价 setter 方法 */
  public void setNastorigprice(UFDouble nastorigprice) {
    this.setAttributeValue(InvoiceItemVO.NASTORIGPRICE, nastorigprice);
  }

  /** 含税单价 setter 方法 */
  public void setNastorigtaxprice(UFDouble nastorigtaxprice) {
    this.setAttributeValue(InvoiceItemVO.NASTORIGTAXPRICE, nastorigtaxprice);
  }

  /** 本币无税单价 setter 方法 */
  public void setNastprice(UFDouble nastprice) {
    this.setAttributeValue(InvoiceItemVO.NASTPRICE, nastprice);
  }

  /** 本币含税单价 setter 方法 */
  public void setNasttaxprice(UFDouble nasttaxprice) {
    this.setAttributeValue(InvoiceItemVO.NASTTAXPRICE, nasttaxprice);
  }

  /** 计成本金额 setter 方法 */
  public void setNcalcostmny(UFDouble ncalcostmny) {
    this.setAttributeValue(InvoiceItemVO.NCALCOSTMNY, ncalcostmny);
  }

  /** 计税金额 setter 方法 */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(InvoiceItemVO.NCALTAXMNY, ncaltaxmny);
  }

  /** 全局本币无税金额 setter 方法 */
  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(InvoiceItemVO.NGLOBALMNY, nglobalmny);
  }

  /** 全局本币价税合计 setter 方法 */
  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(InvoiceItemVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  /** 集团本币无税金额 setter 方法 */
  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(InvoiceItemVO.NGROUPMNY, ngroupmny);
  }

  /** 集团本币价税合计 setter 方法 */
  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(InvoiceItemVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  /** 本币无税金额 setter 方法 */
  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(InvoiceItemVO.NMNY, nmny);
  }

  /** 不可抵扣税额 setter 方法 */
  public void setNnosubtax(UFDouble nnosubtax) {
    this.setAttributeValue(InvoiceItemVO.NNOSUBTAX, nnosubtax);
  }

  /** 不可抵扣税率 setter 方法 */
  public void setNnosubtaxrate(UFDouble nnosubtaxrate) {
    this.setAttributeValue(InvoiceItemVO.NNOSUBTAXRATE, nnosubtaxrate);
  }

  /** 主数量 setter 方法 */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(InvoiceItemVO.NNUM, nnum);
  }

  /** 计算属性，用于回写来源=主数量－合理损耗数量 */
  public void setNnumwrbck(UFDouble nnumwrbck) {
    this.setAttributeValue(InvoiceItemVO.NNUMWRBCK, nnumwrbck);
  }

  /** 无税金额 setter 方法 */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(InvoiceItemVO.NORIGMNY, norigmny);
  }

  /** 主无税单价 setter 方法 */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(InvoiceItemVO.NORIGPRICE, norigprice);
  }

  /** 价税合计 setter 方法 */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(InvoiceItemVO.NORIGTAXMNY, norigtaxmny);
  }

  /** 主含税单价 setter 方法 */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(InvoiceItemVO.NORIGTAXPRICE, norigtaxprice);
  }

  /** 计划价 setter 方法 */
  public void setNplanprice(UFDouble nplanprice) {
    this.setAttributeValue(InvoiceItemVO.NPLANPRICE, nplanprice);
  }

  /** 主本币无税单价 setter 方法 */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(InvoiceItemVO.NPRICE, nprice);
  }

  /** 合理损耗主数量 setter 方法 */
  public void setNreasonwastenum(UFDouble nreasonwastenum) {
    this.setAttributeValue(InvoiceItemVO.NREASONWASTENUM, nreasonwastenum);
  }

  /** 来源单据主数量 setter 方法 */
  public void setNsourcenum(UFDouble nsourcenum) {
    this.setAttributeValue(InvoiceItemVO.NSOURCENUM, nsourcenum);
  }

  /** 本币税额 setter 方法 */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(InvoiceItemVO.NTAX, ntax);
  }

  /** 本币价税合计 setter 方法 */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(InvoiceItemVO.NTAXMNY, ntaxmny);
  }

  /** 主本币含税单价 setter 方法 */
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(InvoiceItemVO.NTAXPRICE, ntaxprice);
  }

  /** 税率 setter 方法 */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(InvoiceItemVO.NTAXRATE, ntaxrate);
  }

  /** 应付财务组织(OID) setter 方法 */
  public void setPk_apfinanceorg(String pk_apfinanceorg) {
    this.setAttributeValue(InvoiceItemVO.PK_APFINANCEORG, pk_apfinanceorg);
  }

  /** 应付财务组织(VID) setter 方法 */
  public void setPk_apfinanceorg_v(String pk_apfinanceorg_v) {
    this.setAttributeValue(InvoiceItemVO.PK_APFINANCEORG_V, pk_apfinanceorg_v);
  }

  /** 利润中心(OID) setter 方法 */
  public void setPk_apliabcenter(String pk_apliabcenter) {
    this.setAttributeValue(InvoiceItemVO.PK_APLIABCENTER, pk_apliabcenter);
  }

  /** 利润中心(VID) setter 方法 */
  public void setPk_apliabcenter_v(String pk_apliabcenter_v) {
    this.setAttributeValue(InvoiceItemVO.PK_APLIABCENTER_V, pk_apliabcenter_v);
  }

  /** 批次档案 setter 方法 */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(InvoiceItemVO.PK_BATCHCODE, pk_batchcode);
  }

  /** 收支项目 setter 方法 */
  public void setPk_costsubj(String pk_costsubj) {
    this.setAttributeValue(InvoiceItemVO.PK_COSTSUBJ, pk_costsubj);
  }

  /** 所属集团 setter 方法 */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(InvoiceItemVO.PK_GROUP, pk_group);
  }

  /** 发票子实体 setter 方法 */
  public void setPk_invoice(String pk_invoice) {
    this.setAttributeValue(InvoiceItemVO.PK_INVOICE, pk_invoice);
  }

  /** 采购发票明细 setter 方法 */
  public void setPk_invoice_b(String pk_invoice_b) {
    this.setAttributeValue(InvoiceItemVO.PK_INVOICE_B, pk_invoice_b);
  }

  /** 物料(VID) setter 方法 */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(InvoiceItemVO.PK_MATERIAL, pk_material);
  }

  /** 采购订单主键 setter 方法 */
  public void setPk_order(String pk_order) {
    this.setAttributeValue(InvoiceItemVO.PK_ORDER, pk_order);
  }

  /** 采购订单行主键 setter 方法 */
  public void setPk_order_b(String pk_order_b) {
    this.setAttributeValue(InvoiceItemVO.PK_ORDER_B, pk_order_b);
  }

  /** 财务组织(OID) setter 方法 */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(InvoiceItemVO.PK_ORG, pk_org);
  }

  /** 财务组织(VID) setter 方法 */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(InvoiceItemVO.PK_ORG_V, pk_org_v);
  }

  /** 物料(OID) setter 方法 */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(InvoiceItemVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

  /** 入库单行ID setter 方法 */
  public void setPk_stockps_b(String pk_stockps_b) {
    this.setAttributeValue(InvoiceItemVO.PK_STOCKPS_B, pk_stockps_b);
  }

  /** 仓库 setter 方法 */
  public void setPk_stordoc(String pk_stordoc) {
    this.setAttributeValue(InvoiceItemVO.PK_STORDOC, pk_stordoc);
  }

  /** 供应商 setter 方法 */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(InvoiceItemVO.PK_SUPPLIER, pk_supplier);
  }

  /** 使用部门(OID) setter 方法 */
  public void setPk_usedept(String pk_usedept) {
    this.setAttributeValue(InvoiceItemVO.PK_USEDEPT, pk_usedept);
  }

  /** 使用部门(VID) setter 方法 */
  public void setPk_usedept_v(String pk_usedept_v) {
    this.setAttributeValue(InvoiceItemVO.PK_USEDEPT_V, pk_usedept_v);
  }

  /** 来源单据行TS setter 方法 */
  public void setSourcebts(UFDateTime sourcebts) {
    this.setAttributeValue(InvoiceItemVO.SOURCEBTS, sourcebts);
  }

  /** 来源单据TS setter 方法 */
  public void setSourcets(UFDateTime sourcets) {
    this.setAttributeValue(InvoiceItemVO.SOURCETS, sourcets);
  }

  /** ts setter 方法 */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(InvoiceItemVO.TS, ts);
  }

  /** 设置被调整发票号 setter 方法 */
  public void setVadjedbillcode(String vadjedbillcode) {
    this.setAttributeValue(InvoiceItemVO.VADJEDBILLCODE, vadjedbillcode);
  }

  /** 批次号 setter 方法 */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(InvoiceItemVO.VBATCHCODE, vbatchcode);
  }

  /** 自定义项1 setter 方法 */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(InvoiceItemVO.VBDEF1, vbdef1);
  }

  /** 自定义项10 setter 方法 */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(InvoiceItemVO.VBDEF10, vbdef10);
  }

  /** 自定义项11 setter 方法 */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(InvoiceItemVO.VBDEF11, vbdef11);
  }

  /** 自定义项12 setter 方法 */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(InvoiceItemVO.VBDEF12, vbdef12);
  }

  /** 自定义项13 setter 方法 */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(InvoiceItemVO.VBDEF13, vbdef13);
  }

  /** 自定义项14 setter 方法 */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(InvoiceItemVO.VBDEF14, vbdef14);
  }

  /** 自定义项15 setter 方法 */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(InvoiceItemVO.VBDEF15, vbdef15);
  }

  /** 自定义项16 setter 方法 */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(InvoiceItemVO.VBDEF16, vbdef16);
  }

  /** 自定义项17 setter 方法 */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(InvoiceItemVO.VBDEF17, vbdef17);
  }

  /** 自定义项18 setter 方法 */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(InvoiceItemVO.VBDEF18, vbdef18);
  }

  /** 自定义项19 setter 方法 */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(InvoiceItemVO.VBDEF19, vbdef19);
  }

  /** 自定义项2 setter 方法 */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(InvoiceItemVO.VBDEF2, vbdef2);
  }

  /** 自定义项20 setter 方法 */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(InvoiceItemVO.VBDEF20, vbdef20);
  }

  /** 自定义项3 setter 方法 */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(InvoiceItemVO.VBDEF3, vbdef3);
  }

  /** 自定义项4 setter 方法 */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(InvoiceItemVO.VBDEF4, vbdef4);
  }

  /** 自定义项5 setter 方法 */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(InvoiceItemVO.VBDEF5, vbdef5);
  }

  /** 自定义项6 setter 方法 */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(InvoiceItemVO.VBDEF6, vbdef6);
  }

  /** 自定义项7 setter 方法 */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(InvoiceItemVO.VBDEF7, vbdef7);
  }

  /** 自定义项8 setter 方法 */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(InvoiceItemVO.VBDEF8, vbdef8);
  }

  /** 自定义项9 setter 方法 */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(InvoiceItemVO.VBDEF9, vbdef9);
  }

  /** 换算率 setter 方法 */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(InvoiceItemVO.VCHANGERATE, vchangerate);
  }

  /** 合同号 setter 方法 */
  public void setVctcode(String vctcode) {
    this.setAttributeValue(InvoiceItemVO.VCTCODE, vctcode);
  }

  /** 源头单据号 setter 方法 */
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(InvoiceItemVO.VFIRSTCODE, vfirstcode);
  }

  /** 源头单据行号 setter 方法 */
  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(InvoiceItemVO.VFIRSTROWNO, vfirstrowno);
  }

  /** 源头交易类型 setter 方法 */
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(InvoiceItemVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /** 自由辅助属性1 setter 方法 */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(InvoiceItemVO.VFREE1, vfree1);
  }

  /** 自由辅助属性10 setter 方法 */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(InvoiceItemVO.VFREE10, vfree10);
  }

  /** 自由辅助属性2 setter 方法 */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(InvoiceItemVO.VFREE2, vfree2);
  }

  /** 自由辅助属性3 setter 方法 */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(InvoiceItemVO.VFREE3, vfree3);
  }

  /** 自由辅助属性4 setter 方法 */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(InvoiceItemVO.VFREE4, vfree4);
  }

  /** 自由辅助属性5 setter 方法 */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(InvoiceItemVO.VFREE5, vfree5);
  }

  /** 自由辅助属性6 setter 方法 */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(InvoiceItemVO.VFREE6, vfree6);
  }

  /** 自由辅助属性7 setter 方法 */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(InvoiceItemVO.VFREE7, vfree7);
  }

  /** 自由辅助属性8 setter 方法 */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(InvoiceItemVO.VFREE8, vfree8);
  }

  /** 自由辅助属性9 setter 方法 */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(InvoiceItemVO.VFREE9, vfree9);
  }

  /** 备注 setter 方法 */
  public void setVmemob(String vmemob) {
    this.setAttributeValue(InvoiceItemVO.VMEMOB, vmemob);
  }

  /** 订单号 setter 方法 */
  public void setVordercode(String vordercode) {
    this.setAttributeValue(InvoiceItemVO.VORDERCODE, vordercode);
  }

  /** 订单交易类型 setter 方法 */
  public void setVordertrantype(String vordertrantype) {
    this.setAttributeValue(InvoiceItemVO.VORDERTRANTYPE, vordertrantype);
  }

  /** 来源单据号 setter 方法 */
  public void setVsourcecode(String vsourcecode) {
    this.setAttributeValue(InvoiceItemVO.VSOURCECODE, vsourcecode);
  }

  /** 来源单据行号 setter 方法 */
  public void setVsourcerowno(String vsourcerowno) {
    this.setAttributeValue(InvoiceItemVO.VSOURCEROWNO, vsourcerowno);
  }

  /** 来源交易类型 setter 方法 */
  public void setVsourcetrantype(String vsourcetrantype) {
    this.setAttributeValue(InvoiceItemVO.VSOURCETRANTYPE, vsourcetrantype);
  }

}
