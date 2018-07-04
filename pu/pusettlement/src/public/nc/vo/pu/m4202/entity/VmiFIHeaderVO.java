/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-6 上午10:37:32
 */
package nc.vo.pu.m4202.entity;

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
 * <li>消耗汇总财务表头(单表)VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-6 上午10:37:32
 */
public class VmiFIHeaderVO extends SuperVO {

  /** 审批人 */
  public static final String APPROVER = "approver";

  /** 影响成本标志 */
  public static final String BAFFECTCOST = "baffectcost";

  /** 制单人 */
  public static final String BILLMAKER = "billmaker";

  /** 是否普通采购 */
  public static final String BNORMPUR = "bnormpur";

  /** 逆向征税 */
  public static final String BOPPTAXFLAG = "bopptaxflag";

  /** 是否结算完成 */
  public static final String BSETTLEFINISH = "bsettlefinish";

  /** 三角贸易 */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /** 客户 */
  public static final String CASSCUSTID = "casscustid";

  /** 单位 */
  public static final String CASTUNITID = "castunitid";

  /** 消耗单据表头 */
  public static final String CCONSUMEHID = "cconsumehid";

  /** 本位币 */
  public static final String CCURRENCYID = "ccurrencyid";

  /** 税码 */
  public static final String CESTTAXCODEID = "cesttaxcodeid";

  /** 特征码 **/
  public static final String CFFILEID = "cffileid";

  /** 生产厂商 */
  public static final String CPRODUCTORID = "cproductorid";

  /** 项目 */
  public static final String CPROJECTID = "cprojectid";

  /** 创建时间 */
  public static final String CREATIONTIME = "creationtime";

  /** 创建人 */
  public static final String CREATOR = "creator";

  /** 收货国家/地区 */
  public static final String CRECECOUNTRYID = "crececountryid";

  /** 行号 */
  public static final String CROWNO = "crowno";

  /** 发货国家/地区 */
  public static final String CSENDCOUNTRYID = "csendcountryid";

  /** 库存状态 */
  public static final String CSTATEID = "cstateid";

  /** 消耗汇总规则ID */
  public static final String CSUMRULEID = "csumruleid";

  /** 报税国家/地区 */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /** 交易类型 */
  public static final String CTRANTYPEID = "ctrantypeid";

  /** 主单位 */
  public static final String CUNITID = "cunitid";

  /** 汇总日期 */
  public static final String DBILLDATE = "dbilldate";

  /** 业务日期 */
  public static final String DBIZDATE = "dbizdate";

  /** dr */
  public static final String DR = "dr";

  /** 暂估日期 */
  public static final String DTOCOSTAPDATE = "dtocostapdate";

  /** 购销类型 */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /** 扣税类别 */
  public static final String FESTTAXTYPE = "festtaxtype";

  /** 传应付标志 */
  public static final String FTOAPFLAG = "ftoapflag";

  /** 传成本标志 */
  public static final String FTOIAFLAG = "ftoiaflag";

  /** 最后修改时间 */
  public static final String MODIFIEDTIME = "modifiedtime";

  /** 最后修改人 */
  public static final String MODIFIER = "modifier";

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
  public static final String NESTCALCOSTMNY = "nestcalcostmny";

  /** 记成本单价 **/
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

  /** 汇总数量 */
  public static final String NINASSISTNUM = "ninassistnum";

  /** 汇总主数量 */
  public static final String NINNUM = "ninnum";

  /** 主本币无税净价 */
  public static final String NNETPRICE = "nnetprice";

  /** 主本币含税净价 */
  public static final String NTAXNETPRICE = "ntaxnetprice";

  /** 总本币无税金额 */
  public static final String NTOTALMNY = "ntotalmny";

  /** 总本币价税合计 */
  public static final String NTOTALTAXMNY = "ntotaltaxmny";

  /** 批次档案 */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /** 业务流程 */
  public static final String PK_BUSITYPE = "pk_busitype";

  /** 传成本和应付人 */
  public static final String PK_COSTAPPSN = "pk_costappsn";

  /** 成本域 */
  public static final String PK_COSTREGION = "pk_costregion";

  /** 币种 */
  public static final String PK_ESTCURRENCY = "pk_estcurrency";

  /** 结算财务组织最新版本 */
  public static final String PK_FINANCEORG = "pk_financeorg";

  /** 结算财务组织 */
  public static final String PK_FINANCEORG_V = "pk_financeorg_v";

  /** 集团 */
  public static final String PK_GROUP = "pk_group";

  /** 物料编码 */
  public static final String PK_MATERIAL = "pk_material";

  /** 公司最新版本 */
  public static final String PK_ORG = "pk_org";

  /** 公司 */
  public static final String PK_ORG_V = "pk_org_v";

  /** 物料最新版本 */
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  /** 消耗汇总财务辅标识 */
  public static final String PK_STOCKPS = "pk_stockps";

  /** 消耗汇总财务主标识 */
  public static final String PK_STOCKPS_B = "pk_stockps_b";

  /** 仓库 */
  public static final String PK_STORDOC = "pk_stordoc";

  /** 库存组织最新版本 */
  public static final String PK_STOREORG = "pk_storeorg";

  /** 库存组织 */
  public static final String PK_STOREORG_V = "pk_storeorg_v";

  /** 供应商 */
  public static final String PK_SUPPLIER = "pk_supplier";

  /** 审批时间 */
  public static final String TAUDITTIME = "taudittime";

  /** ts */
  public static final String TS = "ts";

  /** 批次号 */
  public static final String VBATCHCODE = "vbatchcode";

  /** 单据号 */
  public static final String VBILLCODE = "vbillcode";

  /** 换算率 */
  public static final String VCHANGERATE = "vchangerate";

  /** 消耗单据号 */
  public static final String VCONSUMEBILLCODE = "vconsumebillcode";

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

  /** 消耗匹配采购入库的规则 */
  public static final String VMIMATCHPURINRULE = "vmimatchpurinrule";

  /** 交易类型 */
  public static final String VTRANTYPECODE = "vtrantypecode";

  /**
   * 
   */
  private static final long serialVersionUID = 6646250056893765086L;

  /** 审批人 getter 方法 */
  public String getApprover() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.APPROVER);
  }

  /** 影响成本标志 getter 方法 */
  public UFBoolean getBaffectcost() {
    return (UFBoolean) this.getAttributeValue(VmiFIHeaderVO.BAFFECTCOST);
  }

  /** 制单人 getter 方法 */
  public String getBillmaker() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.BILLMAKER);
  }

  /** 是否普通采购 getter 方法 */
  public UFBoolean getBnormpur() {
    return (UFBoolean) this.getAttributeValue(VmiFIHeaderVO.BNORMPUR);
  }

  /** 逆向征税 getter 方法 */
  public UFBoolean getBopptaxflag() {
    return (UFBoolean) this.getAttributeValue(VmiFIHeaderVO.BOPPTAXFLAG);
  }

  /** 是否结算完成 getter 方法 */
  public UFBoolean getBsettlefinish() {
    return (UFBoolean) this.getAttributeValue(VmiFIHeaderVO.BSETTLEFINISH);
  }

  /** 三角贸易 getter 方法 */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(VmiFIHeaderVO.BTRIATRADEFLAG);
  }

  /** 客户 getter 方法 */
  public String getCasscustid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CASSCUSTID);
  }

  /** 单位 getter 方法 */
  public String getCastunitid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CASTUNITID);
  }

  /** 消耗单据表头 getter 方法 */
  public String getCconsumehid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CCONSUMEHID);
  }

  /** 本位币 getter 方法 */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CCURRENCYID);
  }

  /** 税码 getter 方法 */
  public String getCesttaxcodeid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CESTTAXCODEID);
  }

  /** 特征码 **/
  public String getCffileid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CFFILEID);
  }

  /** 生产厂商 getter 方法 */
  public String getCproductorid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CPRODUCTORID);
  }

  /** 项目 getter 方法 */
  public String getCprojectid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CPROJECTID);
  }

  /** 创建时间 getter 方法 */
  public UFDateTime getCreationtime() {
    return (UFDateTime) this.getAttributeValue(VmiFIHeaderVO.CREATIONTIME);
  }

  /** 创建人 getter 方法 */
  public String getCreator() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CREATOR);
  }

  /** 收货国家/地区 getter 方法 */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CRECECOUNTRYID);
  }

  /** 行号 getter 方法 */
  public String getCrowno() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CROWNO);
  }

  /** 发货国家/地区 getter 方法 */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CSENDCOUNTRYID);
  }

  /** 库存状态 getter 方法 */
  public String getCstateid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CSTATEID);
  }

  /** 消耗汇总规则ID getter 方法 */
  public String getCsumruleid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CSUMRULEID);
  }

  /** 报税国家/地区 getter 方法 */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CTAXCOUNTRYID);
  }

  /** 交易类型 getter 方法 */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CTRANTYPEID);
  }

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CUNITID);
  }

  /** 汇总日期 getter 方法 */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(VmiFIHeaderVO.DBILLDATE);
  }

  /** 业务日期 getter 方法 */
  public UFDate getDbizdate() {
    return (UFDate) this.getAttributeValue(VmiFIHeaderVO.DBIZDATE);
  }

  /** dr getter 方法 */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(VmiFIHeaderVO.DR);
  }

  /** 暂估日期 getter 方法 */
  public UFDate getDtocostapdate() {
    return (UFDate) this.getAttributeValue(VmiFIHeaderVO.DTOCOSTAPDATE);
  }

  /** 购销类型 getter 方法 */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(VmiFIHeaderVO.FBUYSELLFLAG);
  }

  /** 扣税类别 getter 方法 */
  public Integer getFesttaxtype() {
    return (Integer) this.getAttributeValue(VmiFIHeaderVO.FESTTAXTYPE);
  }

  /** 传应付标志 getter 方法 */
  public Integer getFtoapflag() {
    return (Integer) this.getAttributeValue(VmiFIHeaderVO.FTOAPFLAG);
  }

  /** 传成本标志 getter 方法 */
  public Integer getFtoiaflag() {
    return (Integer) this.getAttributeValue(VmiFIHeaderVO.FTOIAFLAG);
  }

  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.VMIFI_H);
  }

  /** 最后修改时间 getter 方法 */
  public UFDateTime getModifiedtime() {
    return (UFDateTime) this.getAttributeValue(VmiFIHeaderVO.MODIFIEDTIME);
  }

  /** 最后修改人 getter 方法 */
  public String getModifier() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.MODIFIER);
  }

  /** 暂估部分累计结算数量 getter 方法 */
  public UFDouble getNaccestcoststtlnum() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCESTCOSTSTTLNUM);
  }

  /** 累计回冲暂估成本金额 getter 方法 */
  public UFDouble getNaccestcostwashmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCESTCOSTWASHMNY);
  }

  /** 累计费用结算金额 getter 方法 */
  public UFDouble getNaccfeesettlemny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCFEESETTLEMNY);
  }

  /** 累计货物结算金额 getter 方法 */
  public UFDouble getNaccgoodssettlemny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCGOODSSETTLEMNY);
  }

  /** 暂估前累计结算金额 getter 方法 */
  public UFDouble getNaccpreeststtlmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCPREESTSTTLMNY);
  }

  /** 累计结算金额 getter 方法 */
  public UFDouble getNaccsettlemny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCSETTLEMNY);
  }

  /** 累计调整确认应付原币价税合计 getter 方法 */
  public UFDouble getNacctoapadjstotmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCTOAPADJSTOTMNY);
  }

  /** 累计调整确认成本金额 getter 方法 */
  public UFDouble getNacctocostadjstmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCTOCOSTADJSTMNY);
  }

  /** 累计结算数量 getter 方法 */
  public UFDouble getNaccumsettlenum() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCUMSETTLENUM);
  }

  /** 计成本金额 getter 方法 */
  public UFDouble getNestcalcostmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTCALCOSTMNY);
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
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTCALCOSTPRICE);
  }

  /** 计税金额 getter 方法 */
  public UFDouble getNestcaltaxmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTCALTAXMNY);
  }

  /** 折本汇率 getter 方法 */
  public UFDouble getNestexhgrate() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTEXHGRATE);
  }

  /** 本币无税金额 getter 方法 */
  public UFDouble getNestmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTMNY);
  }

  /** 不可抵扣税额 getter 方法 */
  public UFDouble getNestnosubtax() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTNOSUBTAX);
  }

  /** 不可抵扣税率 getter 方法 */
  public UFDouble getNestnosubtaxrate() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTNOSUBTAXRATE);
  }

  /** 暂估主数量 getter 方法 */
  public UFDouble getNestnum() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTNUM);
  }

  /** 无税金额 getter 方法 */
  public UFDouble getNestomny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTOMNY);
  }

  /** 主无税单价 getter 方法 */
  public UFDouble getNestoprice() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTOPRICE);
  }

  /** 主含税单价 getter 方法 */
  public UFDouble getNestotaxprice() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTOTAXPRICE);
  }

  /** 价税合计 getter 方法 */
  public UFDouble getNestototalmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTOTOTALMNY);
  }

  /** 暂估单价 getter 方法 */
  public UFDouble getNestprice() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTPRICE);
  }

  /** 本币税额 getter 方法 */
  public UFDouble getNesttaxmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTTAXMNY);
  }

  /** 暂估含税单价 getter 方法 */
  public UFDouble getNesttaxprice() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTTAXPRICE);
  }

  /** 税率 getter 方法 */
  public UFDouble getNesttaxrate() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTTAXRATE);
  }

  /** 本币价税合计 getter 方法 */
  public UFDouble getNesttotalmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTTOTALMNY);
  }

  /** 费用本币无税金额 getter 方法 */
  public UFDouble getNfeemny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NFEEMNY);
  }

  /** 费用本币价税合计 getter 方法 */
  public UFDouble getNfeetaxmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NFEETAXMNY);
  }

  /** 汇总数量 getter 方法 */
  public UFDouble getNinassistnum() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NINASSISTNUM);
  }

  /** 汇总主数量 getter 方法 */
  public UFDouble getNinnum() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NINNUM);
  }

  /** 主本币无税净价 getter 方法 */
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NNETPRICE);
  }

  /** 主本币含税净价 getter 方法 */
  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NTAXNETPRICE);
  }

  /** 总本币无税金额 getter 方法 */
  public UFDouble getNtotalmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NTOTALMNY);
  }

  /** 总本币价税合计 getter 方法 */
  public UFDouble getNtotaltaxmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NTOTALTAXMNY);
  }

  /** 批次档案 getter 方法 */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_BATCHCODE);
  }

  /** 业务流程 getter 方法 */
  public String getPk_busitype() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_BUSITYPE);
  }

  /** 传成本和应付人 getter 方法 */
  public String getPk_costappsn() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_COSTAPPSN);
  }

  /** 成本域 getter 方法 */
  public String getPk_costregion() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_COSTREGION);
  }

  /** 币种 getter 方法 */
  public String getPk_estcurrency() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_ESTCURRENCY);
  }

  /** 结算财务组织最新版本 getter 方法 */
  public String getPk_financeorg() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_FINANCEORG);
  }

  /** 结算财务组织 getter 方法 */
  public String getPk_financeorg_v() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_FINANCEORG_V);
  }

  /** 集团 getter 方法 */
  public String getPk_group() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_GROUP);
  }

  /** 物料编码 getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_MATERIAL);
  }

  /** 公司最新版本 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_ORG);
  }

  /** 公司 getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_ORG_V);
  }

  /** 物料最新版本 getter 方法 */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_SRCMATERIAL);
  }

  /** 消耗汇总财务辅标识 getter 方法 */
  public String getPk_stockps() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_STOCKPS);
  }

  /** 消耗汇总财务主标识 getter 方法 */
  public String getPk_stockps_b() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_STOCKPS_B);
  }

  /** 仓库 getter 方法 */
  public String getPk_stordoc() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_STORDOC);
  }

  /** 库存组织最新版本 getter 方法 */
  public String getPk_storeorg() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_STOREORG);
  }

  /** 库存组织 getter 方法 */
  public String getPk_storeorg_v() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_STOREORG_V);
  }

  /** 供应商 getter 方法 */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_SUPPLIER);
  }

  /** 审批时间 getter 方法 */
  public UFDateTime getTaudittime() {
    return (UFDateTime) this.getAttributeValue(VmiFIHeaderVO.TAUDITTIME);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(VmiFIHeaderVO.TS);
  }

  /** 批次号 getter 方法 */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VBATCHCODE);
  }

  /** 单据号 getter 方法 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VBILLCODE);
  }

  /** 换算率 getter 方法 */
  public String getVchangerate() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VCHANGERATE);
  }

  /** 消耗单据号 getter 方法 */
  public String getVconsumebillcode() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VCONSUMEBILLCODE);
  }

  /** 自由辅助属性1 getter 方法 */
  public String getVfree1() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE1);
  }

  /** 自由辅助属性10 getter 方法 */
  public String getVfree10() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE10);
  }

  /** 自由辅助属性2 getter 方法 */
  public String getVfree2() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE2);
  }

  /** 自由辅助属性3 getter 方法 */
  public String getVfree3() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE3);
  }

  /** 自由辅助属性4 getter 方法 */
  public String getVfree4() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE4);
  }

  /** 自由辅助属性5 getter 方法 */
  public String getVfree5() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE5);
  }

  /** 自由辅助属性6 getter 方法 */
  public String getVfree6() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE6);
  }

  /** 自由辅助属性7 getter 方法 */
  public String getVfree7() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE7);
  }

  /** 自由辅助属性8 getter 方法 */
  public String getVfree8() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE8);
  }

  /** 自由辅助属性9 getter 方法 */
  public String getVfree9() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE9);
  }

  /** 消耗匹配采购入库的规则 getter 方法 */
  public String getVmimatchpurinrule() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VMIMATCHPURINRULE);
  }

  /** 交易类型 getter 方法 */
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VTRANTYPECODE);
  }

  /** 审批人 setter 方法 */
  public void setApprover(String approver) {
    this.setAttributeValue(VmiFIHeaderVO.APPROVER, approver);
  }

  /** 影响成本标志 setter 方法 */
  public void setBaffectcost(UFBoolean baffectcost) {
    this.setAttributeValue(VmiFIHeaderVO.BAFFECTCOST, baffectcost);
  }

  /** 制单人 setter 方法 */
  public void setBillmaker(String billmaker) {
    this.setAttributeValue(VmiFIHeaderVO.BILLMAKER, billmaker);
  }

  /** 是否普通采购 setter 方法 */
  public void setBnormpur(UFBoolean bnormpur) {
    this.setAttributeValue(VmiFIHeaderVO.BNORMPUR, bnormpur);
  }

  /** 逆向征税 setter 方法 */
  public void setBopptaxflag(UFBoolean bopptaxflag) {
    this.setAttributeValue(VmiFIHeaderVO.BOPPTAXFLAG, bopptaxflag);
  }

  /** 是否结算完成 setter 方法 */
  public void setBsettlefinish(UFBoolean bsettlefinish) {
    this.setAttributeValue(VmiFIHeaderVO.BSETTLEFINISH, bsettlefinish);
  }

  /** 三角贸易 setter 方法 */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(VmiFIHeaderVO.BTRIATRADEFLAG, btriatradeflag);
  }

  /** 客户 setter 方法 */
  public void setCasscustid(String casscustid) {
    this.setAttributeValue(VmiFIHeaderVO.CASSCUSTID, casscustid);
  }

  /** 单位 setter 方法 */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(VmiFIHeaderVO.CASTUNITID, castunitid);
  }

  /** 消耗单据表头setter 方法 */
  public void setCconsumehid(String cconsumehid) {
    this.setAttributeValue(VmiFIHeaderVO.CCONSUMEHID, cconsumehid);
  }

  /** 本位币 setter 方法 */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(VmiFIHeaderVO.CCURRENCYID, ccurrencyid);
  }

  /** 税码 setter 方法 */
  public void setCesttaxcodeid(String cesttaxcodeid) {
    this.setAttributeValue(VmiFIHeaderVO.CESTTAXCODEID, cesttaxcodeid);
  }

  /** 特征码 **/
  public void setCffileid(String cffileid) {
    this.setAttributeValue(VmiFIHeaderVO.CFFILEID, cffileid);
  }

  /** 生产厂商 setter 方法 */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(VmiFIHeaderVO.CPRODUCTORID, cproductorid);
  }

  /** 项目 setter 方法 */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(VmiFIHeaderVO.CPROJECTID, cprojectid);
  }

  /** 创建时间 setter 方法 */
  public void setCreationtime(UFDateTime creationtime) {
    this.setAttributeValue(VmiFIHeaderVO.CREATIONTIME, creationtime);
  }

  /** 创建人 setter 方法 */
  public void setCreator(String creator) {
    this.setAttributeValue(VmiFIHeaderVO.CREATOR, creator);
  }

  /** 收货国家/地区 setter 方法 */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(VmiFIHeaderVO.CRECECOUNTRYID, crececountryid);
  }

  /** 行号 setter 方法 */
  public void setCrowno(String crowno) {
    this.setAttributeValue(VmiFIHeaderVO.CROWNO, crowno);
  }

  /** 发货国家/地区 setter 方法 */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(VmiFIHeaderVO.CSENDCOUNTRYID, csendcountryid);
  }

  /** 库存状态 setter 方法 */
  public void setCstateid(String cstateid) {
    this.setAttributeValue(VmiFIHeaderVO.CSTATEID, cstateid);
  }

  /** 消耗汇总规则ID setter 方法 */
  public void setCsumruleid(String csumruleid) {
    this.setAttributeValue(VmiFIHeaderVO.CSUMRULEID, csumruleid);
  }

  /** 报税国家/地区 setter 方法 */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(VmiFIHeaderVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  /** 交易类型 setter 方法 */
  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(VmiFIHeaderVO.CTRANTYPEID, ctrantypeid);
  }

  /** 主单位 setter 方法 */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(VmiFIHeaderVO.CUNITID, cunitid);
  }

  /** 汇总日期 setter 方法 */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(VmiFIHeaderVO.DBILLDATE, dbilldate);
  }

  /** 业务日期 setter 方法 */
  public void setDbizdate(UFDate dbizdate) {
    this.setAttributeValue(VmiFIHeaderVO.DBIZDATE, dbizdate);
  }

  /** dr setter 方法 */
  public void setDr(Integer dr) {
    this.setAttributeValue(VmiFIHeaderVO.DR, dr);
  }

  /** 暂估日期 setter 方法 */
  public void setDtocostapdate(UFDate dtocostapdate) {
    this.setAttributeValue(VmiFIHeaderVO.DTOCOSTAPDATE, dtocostapdate);
  }

  /** 购销类型 setter 方法 */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(VmiFIHeaderVO.FBUYSELLFLAG, fbuysellflag);
  }

  /** 扣税类别 setter 方法 */
  public void setFesttaxtype(Integer festtaxtype) {
    this.setAttributeValue(VmiFIHeaderVO.FESTTAXTYPE, festtaxtype);
  }

  /** 传应付标志 setter 方法 */
  public void setFtoapflag(Integer ftoapflag) {
    this.setAttributeValue(VmiFIHeaderVO.FTOAPFLAG, ftoapflag);
  }

  /** 传成本标志 setter 方法 */
  public void setFtoiaflag(Integer ftoiaflag) {
    this.setAttributeValue(VmiFIHeaderVO.FTOIAFLAG, ftoiaflag);
  }

  /** 最后修改时间 setter 方法 */
  public void setModifiedtime(UFDateTime modifiedtime) {
    this.setAttributeValue(VmiFIHeaderVO.MODIFIEDTIME, modifiedtime);
  }

  /** 最后修改人 setter 方法 */
  public void setModifier(String modifier) {
    this.setAttributeValue(VmiFIHeaderVO.MODIFIER, modifier);
  }

  /** 暂估部分累计结算数量 setter 方法 */
  public void setNaccestcoststtlnum(UFDouble naccestcoststtlnum) {
    this.setAttributeValue(VmiFIHeaderVO.NACCESTCOSTSTTLNUM, naccestcoststtlnum);
  }

  /** 累计回冲暂估成本金额 setter 方法 */
  public void setNaccestcostwashmny(UFDouble naccestcostwashmny) {
    this.setAttributeValue(VmiFIHeaderVO.NACCESTCOSTWASHMNY, naccestcostwashmny);
  }

  /** 累计费用结算金额 setter 方法 */
  public void setNaccfeesettlemny(UFDouble naccfeesettlemny) {
    this.setAttributeValue(VmiFIHeaderVO.NACCFEESETTLEMNY, naccfeesettlemny);
  }

  /** 累计货物结算金额 setter 方法 */
  public void setNaccgoodssettlemny(UFDouble naccgoodssettlemny) {
    this.setAttributeValue(VmiFIHeaderVO.NACCGOODSSETTLEMNY, naccgoodssettlemny);
  }

  /** 暂估前累计结算金额 setter 方法 */
  public void setNaccpreeststtlmny(UFDouble naccpreeststtlmny) {
    this.setAttributeValue(VmiFIHeaderVO.NACCPREESTSTTLMNY, naccpreeststtlmny);
  }

  /** 累计结算金额 setter 方法 */
  public void setNaccsettlemny(UFDouble naccsettlemny) {
    this.setAttributeValue(VmiFIHeaderVO.NACCSETTLEMNY, naccsettlemny);
  }

  /** 累计调整确认应付原币价税合计 setter 方法 */
  public void setNacctoapadjstotmny(UFDouble nacctoapadjstotmny) {
    this.setAttributeValue(VmiFIHeaderVO.NACCTOAPADJSTOTMNY, nacctoapadjstotmny);
  }

  /** 累计调整确认成本金额 setter 方法 */
  public void setNacctocostadjstmny(UFDouble nacctocostadjstmny) {
    this.setAttributeValue(VmiFIHeaderVO.NACCTOCOSTADJSTMNY, nacctocostadjstmny);
  }

  /** 累计结算数量 setter 方法 */
  public void setNaccumsettlenum(UFDouble naccumsettlenum) {
    this.setAttributeValue(VmiFIHeaderVO.NACCUMSETTLENUM, naccumsettlenum);
  }

  /** 计成本金额 setter 方法 */
  public void setNestcalcostmny(UFDouble nestcalcostmny) {
    this.setAttributeValue(VmiFIHeaderVO.NESTCALCOSTMNY, nestcalcostmny);
  }

  /**
   * 记成本单价
   * <p>
   * 使用场景：
   * <ul>
   * <li>wuxla V61
   * </ul>
   * 
   * @param nestcalcostprice
   */
  public void setNestcalcostprice(UFBoolean nestcalcostprice) {
    this.setAttributeValue(VmiFIHeaderVO.NESTCALCOSTPRICE, nestcalcostprice);
  }

  /** 计税金额 setter 方法 */
  public void setNestcaltaxmny(UFDouble nestcaltaxmny) {
    this.setAttributeValue(VmiFIHeaderVO.NESTCALTAXMNY, nestcaltaxmny);
  }

  /** 折本汇率 setter 方法 */
  public void setNestexhgrate(UFDouble nestexhgrate) {
    this.setAttributeValue(VmiFIHeaderVO.NESTEXHGRATE, nestexhgrate);
  }

  /** 本币无税金额 setter 方法 */
  public void setNestmny(UFDouble nestmny) {
    this.setAttributeValue(VmiFIHeaderVO.NESTMNY, nestmny);
  }

  /** 不可抵扣税额 setter 方法 */
  public void setNestnosubtax(UFDouble nnosubtax) {
    this.setAttributeValue(VmiFIHeaderVO.NESTNOSUBTAX, nnosubtax);
  }

  /** 不可抵扣税率 setter 方法 */
  public void setNestnosubtaxrate(UFDouble nestnosubtaxrate) {
    this.setAttributeValue(VmiFIHeaderVO.NESTNOSUBTAXRATE, nestnosubtaxrate);
  }

  /** 暂估主数量 setter 方法 */
  public void setNestnum(UFDouble nestnum) {
    this.setAttributeValue(VmiFIHeaderVO.NESTNUM, nestnum);
  }

  /** 无税金额 setter 方法 */
  public void setNestomny(UFDouble nestomny) {
    this.setAttributeValue(VmiFIHeaderVO.NESTOMNY, nestomny);
  }

  /** 主无税单价 setter 方法 */
  public void setNestoprice(UFDouble nestoprice) {
    this.setAttributeValue(VmiFIHeaderVO.NESTOPRICE, nestoprice);
  }

  /** 主含税单价 setter 方法 */
  public void setNestotaxprice(UFDouble nestotaxprice) {
    this.setAttributeValue(VmiFIHeaderVO.NESTOTAXPRICE, nestotaxprice);
  }

  /** 价税合计 setter 方法 */
  public void setNestototalmny(UFDouble nestototalmny) {
    this.setAttributeValue(VmiFIHeaderVO.NESTOTOTALMNY, nestototalmny);
  }

  /** 暂估单价 setter 方法 */
  public void setNestprice(UFDouble nestprice) {
    this.setAttributeValue(VmiFIHeaderVO.NESTPRICE, nestprice);
  }

  /** 本币税额 setter 方法 */
  public void setNesttaxmny(UFDouble nesttaxmny) {
    this.setAttributeValue(VmiFIHeaderVO.NESTTAXMNY, nesttaxmny);
  }

  /** 暂估含税单价 setter 方法 */
  public void setNesttaxprice(UFDouble nesttaxprice) {
    this.setAttributeValue(VmiFIHeaderVO.NESTTAXPRICE, nesttaxprice);
  }

  /** 税率 setter 方法 */
  public void setNesttaxrate(UFDouble nesttaxrate) {
    this.setAttributeValue(VmiFIHeaderVO.NESTTAXRATE, nesttaxrate);
  }

  /** 本币价税合计 setter 方法 */
  public void setNesttotalmny(UFDouble nesttotalmny) {
    this.setAttributeValue(VmiFIHeaderVO.NESTTOTALMNY, nesttotalmny);
  }

  /** 费用本币无税金额 setter 方法 */
  public void setNfeemny(UFDouble nfeemny) {
    this.setAttributeValue(VmiFIHeaderVO.NFEEMNY, nfeemny);
  }

  /** 费用本币价税合计 setter 方法 */
  public void setNfeetaxmny(UFDouble nfeetaxmny) {
    this.setAttributeValue(VmiFIHeaderVO.NFEETAXMNY, nfeetaxmny);
  }

  /** 汇总数量 setter 方法 */
  public void setNinassistnum(UFDouble ninassistnum) {
    this.setAttributeValue(VmiFIHeaderVO.NINASSISTNUM, ninassistnum);
  }

  /** 汇总主数量 setter 方法 */
  public void setNinnum(UFDouble ninnum) {
    this.setAttributeValue(VmiFIHeaderVO.NINNUM, ninnum);
  }

  /** 主本币无税净价 setter 方法 */
  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(VmiFIHeaderVO.NNETPRICE, nnetprice);
  }

  /** 主本币含税净价 setter 方法 */
  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(VmiFIHeaderVO.NTAXNETPRICE, ntaxnetprice);
  }

  /** 总本币无税金额 setter 方法 */
  public void setNtotalmny(UFDouble ntotalmny) {
    this.setAttributeValue(VmiFIHeaderVO.NTOTALMNY, ntotalmny);
  }

  /** 总本币价税合计 setter 方法 */
  public void setNtotaltaxmny(UFDouble ntotaltaxmny) {
    this.setAttributeValue(VmiFIHeaderVO.NTOTALTAXMNY, ntotaltaxmny);
  }

  /** 批次档案 setter 方法 */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(VmiFIHeaderVO.PK_BATCHCODE, pk_batchcode);
  }

  /** 业务流程 setter 方法 */
  public void setPk_busitype(String pk_busitype) {
    this.setAttributeValue(VmiFIHeaderVO.PK_BUSITYPE, pk_busitype);
  }

  /** 传成本和应付人 setter 方法 */
  public void setPk_costappsn(String pk_costappsn) {
    this.setAttributeValue(VmiFIHeaderVO.PK_COSTAPPSN, pk_costappsn);
  }

  /** 成本域 setter 方法 */
  public void setPk_costregion(String pk_costregion) {
    this.setAttributeValue(VmiFIHeaderVO.PK_COSTREGION, pk_costregion);
  }

  /** 币种 setter 方法 */
  public void setPk_estcurrency(String pk_estcurrency) {
    this.setAttributeValue(VmiFIHeaderVO.PK_ESTCURRENCY, pk_estcurrency);
  }

  /** 结算财务组织最新版本 setter 方法 */
  public void setPk_financeorg(String pk_financeorg) {
    this.setAttributeValue(VmiFIHeaderVO.PK_FINANCEORG, pk_financeorg);
  }

  /** 结算财务组织 setter 方法 */
  public void setPk_financeorg_v(String pk_financeorg_v) {
    this.setAttributeValue(VmiFIHeaderVO.PK_FINANCEORG_V, pk_financeorg_v);
  }

  /** 集团 setter 方法 */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(VmiFIHeaderVO.PK_GROUP, pk_group);
  }

  /** 物料编码 setter 方法 */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(VmiFIHeaderVO.PK_MATERIAL, pk_material);
  }

  /** 公司最新版本 setter 方法 */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(VmiFIHeaderVO.PK_ORG, pk_org);
  }

  /** 公司 setter 方法 */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(VmiFIHeaderVO.PK_ORG_V, pk_org_v);
  }

  /** 物料最新版本 setter 方法 */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(VmiFIHeaderVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

  /** 消耗汇总财务辅标识 setter 方法 */
  public void setPk_stockps(String pk_stockps) {
    this.setAttributeValue(VmiFIHeaderVO.PK_STOCKPS, pk_stockps);
  }

  /** 消耗汇总财务主标识 setter 方法 */
  public void setPk_stockps_b(String pk_stockps_b) {
    this.setAttributeValue(VmiFIHeaderVO.PK_STOCKPS_B, pk_stockps_b);
  }

  /** 仓库 setter 方法 */
  public void setPk_stordoc(String pk_stordoc) {
    this.setAttributeValue(VmiFIHeaderVO.PK_STORDOC, pk_stordoc);
  }

  /** 库存组织最新版本 setter 方法 */
  public void setPk_storeorg(String pk_storeorg) {
    this.setAttributeValue(VmiFIHeaderVO.PK_STOREORG, pk_storeorg);
  }

  /** 库存组织 setter 方法 */
  public void setPk_storeorg_v(String pk_storeorg_v) {
    this.setAttributeValue(VmiFIHeaderVO.PK_STOREORG_V, pk_storeorg_v);
  }

  /** 供应商 setter 方法 */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(VmiFIHeaderVO.PK_SUPPLIER, pk_supplier);
  }

  /** 审批时间 setter 方法 */
  public void setTaudittime(UFDateTime taudittime) {
    this.setAttributeValue(VmiFIHeaderVO.TAUDITTIME, taudittime);
  }

  /** ts setter 方法 */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(VmiFIHeaderVO.TS, ts);
  }

  /** 批次号 setter 方法 */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(VmiFIHeaderVO.VBATCHCODE, vbatchcode);
  }

  /** 单据号 setter 方法 */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(VmiFIHeaderVO.VBILLCODE, vbillcode);
  }

  /** 换算率 setter 方法 */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(VmiFIHeaderVO.VCHANGERATE, vchangerate);
  }

  /** 消耗单据号 setter 方法 */
  public void setVconsumebillcode(String vconsumebillcode) {
    this.setAttributeValue(VmiFIHeaderVO.VCONSUMEBILLCODE, vconsumebillcode);
  }

  /** 自由辅助属性1 setter 方法 */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(VmiFIHeaderVO.VFREE1, vfree1);
  }

  /** 自由辅助属性10 setter 方法 */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(VmiFIHeaderVO.VFREE10, vfree10);
  }

  /** 自由辅助属性2 setter 方法 */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(VmiFIHeaderVO.VFREE2, vfree2);
  }

  /** 自由辅助属性3 setter 方法 */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(VmiFIHeaderVO.VFREE3, vfree3);
  }

  /** 自由辅助属性4 setter 方法 */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(VmiFIHeaderVO.VFREE4, vfree4);
  }

  /** 自由辅助属性5 setter 方法 */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(VmiFIHeaderVO.VFREE5, vfree5);
  }

  /** 自由辅助属性6 setter 方法 */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(VmiFIHeaderVO.VFREE6, vfree6);
  }

  /** 自由辅助属性7 setter 方法 */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(VmiFIHeaderVO.VFREE7, vfree7);
  }

  /** 自由辅助属性8 setter 方法 */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(VmiFIHeaderVO.VFREE8, vfree8);
  }

  /** 自由辅助属性9 setter 方法 */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(VmiFIHeaderVO.VFREE9, vfree9);
  }

  /** 消耗匹配采购入库的规则 setter 方法 */
  public void setVmimatchpurinrule(String vmimatchpurinrule) {
    this.setAttributeValue(VmiFIHeaderVO.VMIMATCHPURINRULE, vmimatchpurinrule);
  }

  /** 交易类型 setter 方法 */
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(VmiFIHeaderVO.VTRANTYPECODE, vtrantypecode);
  }

}
