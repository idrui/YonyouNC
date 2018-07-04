package nc.vo.pu.m4t.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单表体VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-25 下午07:55:12
 */
public class InitialEstItemVO extends SuperVO {
  /** 影响成本标志 **/
  public static final String BAFFECTCOST = "baffectcost";

  /** 影响利润中心成本 **/
  public static final String BAFFECTPCCOST = "baffectpccost";

  /** 暂估应付标志 */
  public static final String BESTIMATEAP = "bestimateap";

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

  /** 特征码 **/
  public static final String CFFILEID = "cffileid";

  /** 订单行号 */
  public static final String CORDERROWNO = "corderrowno";

  /** 生产厂商 */
  public static final String CPRODUCTORID = "cproductorid";

  /** 项目 */
  public static final String CPROJECTID = "cprojectid";

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

  /** dr */
  public static final String DR = "dr";

  /** 购销类型 */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /** 扣税类别 */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /** 累计费用结算金额 */
  public static final String NACCFEESETTLEMNY = "naccfeesettlemny";

  /** 累计货物结算金额 */
  public static final String NACCGOODSSETTLEMNY = "naccgoodssettlemny";

  /** 累计开票数量 */
  public static final String NACCINVOICENUM = "naccinvoicenum";

  /** 累计结算金额 */
  public static final String NACCSETTLEMNY = "naccsettlemny";

  /** 累计结算数量 */
  public static final String NACCSETTLENUM = "naccsettlenum";

  /** 累计冲减暂估金额 */
  public static final String NACCWASHMNY = "naccwashmny";

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

  /** 可开票数量 */
  public static final String NCANINVOICENUM = "ncaninvoicenum";

  /** 计成本单价 */
  public static final String NESTCALCOSTPRICE = "nestcalcostprice";

  /** 本币无税金额 */
  public static final String NMNY = "nmny";

  /** 不可抵扣税额 */
  public static final String NNOSUBTAX = "nnosubtax";

  /** 不可抵扣税率 */
  public static final String NNOSUBTAXRATE = "nnosubtaxrate";

  /** 主数量 */
  public static final String NNUM = "nnum";

  /** 无税金额 */
  public static final String NORIGMNY = "norigmny";

  /** 主无税单价 */
  public static final String NORIGPRICE = "norigprice";

  /** 价税合计 */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /** 主含税单价 */
  public static final String NORIGTAXPRICE = "norigtaxprice";

  /** 主本币无税价 */
  public static final String NPRICE = "nprice";

  /** 来源单据主数量 */
  public static final String NSOURCENUM = "nsourcenum";

  /** 本币税额 */
  public static final String NTAX = "ntax";

  /** 本币价税合计 */
  public static final String NTAXMNY = "ntaxmny";

  /** 主本币含税价 */
  public static final String NTAXPRICE = "ntaxprice";

  /** 税率 */
  public static final String NTAXRATE = "ntaxrate";

  /** 应付组织最新版本 */
  public static final String PK_APFINANCEORG = "pk_apfinanceorg";

  /** 应付组织 */
  public static final String PK_APFINANCEORG_V = "pk_apfinanceorg_v";

  /** 利润中心最新版本 **/
  public static final String PK_APLIABCENTER = "pk_apliabcenter";

  /** 利润中心 **/
  public static final String PK_APLIABCENTER_V = "pk_apliabcenter_v";

  // public static final String NORIGTAX = "norigtax";

  /** 批次档案 */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /** 所属集团 */
  public static final String PK_GROUP = "pk_group";

  /** 子实体关联 */
  public static final String PK_INITIALEST = "pk_initialest";

  /** 期初暂估单明细 */
  public static final String PK_INITIALEST_B = "pk_initialest_b";

  /** 物料VID */
  public static final String PK_MATERIAL = "pk_material";

  /** 订单ID */
  public static final String PK_ORDER = "pk_order";

  /** 订单行ID */
  public static final String PK_ORDER_B = "pk_order_b";

  /** 财务组织最新版本 */
  public static final String PK_ORG = "pk_org";

  /** 财务组织 */
  public static final String PK_ORG_V = "pk_org_v";

  /** 物料OID */
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

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
  public static final String VCTCODE = "vctcode";

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

  /**
   * 
   */
  private static final long serialVersionUID = 3317655814711767008L;

  /** 影响成本标志 **/
  public UFBoolean getBaffectcost() {
    return (UFBoolean) this.getAttributeValue(InitialEstItemVO.BAFFECTCOST);
  }

  /** 影响利润中心成本 **/
  public UFBoolean getBaffectpccost() {
    return (UFBoolean) this.getAttributeValue(InitialEstItemVO.BAFFECTPCCOST);
  }

  /** 暂估应付标志 getter 方法 */
  public UFBoolean getBestimateap() {
    return (UFBoolean) this.getAttributeValue(InitialEstItemVO.BESTIMATEAP);
  }

  /** 逆向征税 getter 方法 */
  public UFBoolean getBopptaxflag() {
    return (UFBoolean) this.getAttributeValue(InitialEstItemVO.BOPPTAXFLAG);
  }

  /** 是否结算完成 getter 方法 */
  public UFBoolean getBsettlefinish() {
    return (UFBoolean) this.getAttributeValue(InitialEstItemVO.BSETTLEFINISH);
  }

  /** 三角贸易 getter 方法 */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(InitialEstItemVO.BTRIATRADEFLAG);
  }

  /** 客户 getter 方法 */
  public String getCasscustid() {
    return (String) this.getAttributeValue(InitialEstItemVO.CASSCUSTID);
  }

  /** 单位 getter 方法 */
  public String getCastunitid() {
    return (String) this.getAttributeValue(InitialEstItemVO.CASTUNITID);
  }

  /** 特征码 **/
  public String getCffileid() {
    return (String) this.getAttributeValue(InitialEstItemVO.CFFILEID);
  }

  /** 订单行号 getter 方法 */
  public String getCorderrowno() {
    return (String) this.getAttributeValue(InitialEstItemVO.CORDERROWNO);
  }

  /** 生产厂商 getter 方法 */
  public String getCproductorid() {
    return (String) this.getAttributeValue(InitialEstItemVO.CPRODUCTORID);
  }

  /** 项目 getter 方法 */
  public String getCprojectid() {
    return (String) this.getAttributeValue(InitialEstItemVO.CPROJECTID);
  }

  /** 收货国家/地区 getter 方法 */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(InitialEstItemVO.CRECECOUNTRYID);
  }

  /** 行号 getter 方法 */
  public String getCrowno() {
    return (String) this.getAttributeValue(InitialEstItemVO.CROWNO);
  }

  /** 发货国/地区 getter 方法 */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(InitialEstItemVO.CSENDCOUNTRYID);
  }

  /** 来源单据明细 getter 方法 */
  public String getCsourcebid() {
    return (String) this.getAttributeValue(InitialEstItemVO.CSOURCEBID);
  }

  /** 来源单据 getter 方法 */
  public String getCsourceid() {
    return (String) this.getAttributeValue(InitialEstItemVO.CSOURCEID);
  }

  /** 来源单据类型 getter 方法 */
  public String getCsourcetypecode() {
    return (String) this.getAttributeValue(InitialEstItemVO.CSOURCETYPECODE);
  }

  /** 税码 getter 方法 */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(InitialEstItemVO.CTAXCODEID);
  }

  /** 报税国/地区 getter 方法 */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(InitialEstItemVO.CTAXCOUNTRYID);
  }

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(InitialEstItemVO.CUNITID);
  }

  /** dr getter 方法 */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(InitialEstItemVO.DR);
  }

  /** 购销类型 getter 方法 */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(InitialEstItemVO.FBUYSELLFLAG);
  }

  /** 扣税类别 getter 方法 */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(InitialEstItemVO.FTAXTYPEFLAG);
  }

  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.M4T_B);
  }

  /** 累计费用结算金额 getter 方法 */
  public UFDouble getNaccfeesettlemny() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NACCFEESETTLEMNY);
  }

  /** 累计货物结算金额 getter 方法 */
  public UFDouble getNaccgoodssettlemny() {
    return (UFDouble) this
        .getAttributeValue(InitialEstItemVO.NACCGOODSSETTLEMNY);
  }

  /** 累计开票数量 getter 方法 */
  public UFDouble getNaccinvoicenum() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NACCINVOICENUM);
  }

  /** 累计结算金额 getter 方法 */
  public UFDouble getNaccsettlemny() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NACCSETTLEMNY);
  }

  /** 累计结算数量 getter 方法 */
  public UFDouble getNaccsettlenum() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NACCSETTLENUM);
  }

  /** 累计冲减暂估金额 getter 方法 */
  public UFDouble getNaccwashmny() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NACCWASHMNY);
  }

  /** 数量 getter 方法 */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NASTNUM);
  }

  /** 无税单价 getter 方法 */
  public UFDouble getNastorigprice() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NASTORIGPRICE);
  }

  /** 含税单价 getter 方法 */
  public UFDouble getNastorigtaxprice() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NASTORIGTAXPRICE);
  }

  /** 本币无税单价 getter 方法 */
  public UFDouble getNastprice() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NASTPRICE);
  }

  /** 本币含税单价 getter 方法 */
  public UFDouble getNasttaxprice() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NASTTAXPRICE);
  }

  /** 计成本金额 getter 方法 */
  public UFDouble getNcalcostmny() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NCALCOSTMNY);
  }

  /** 计税金额 getter 方法 */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NCALTAXMNY);
  }

  /** 可开票数量 getter 方法 */
  public UFDouble getNcaninvoicenum() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NCANINVOICENUM);
  }

  /**
   * 计成本单价
   * 
   * @return
   */
  public UFDouble getNestcalcostprice() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NESTCALCOSTPRICE);
  }

  /** 本币无税金额 getter 方法 */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NMNY);
  }

  /** 不可抵扣税额 getter 方法 */
  public UFDouble getNnosubtax() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NNOSUBTAX);
  }

  /** 不可抵扣税率 getter 方法 */
  public UFDouble getNnosubtaxrate() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NNOSUBTAXRATE);
  }

  /** 主数量 getter 方法 */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NNUM);
  }

  /** 无税金额 getter 方法 */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NORIGMNY);
  }

  /** 主无税单价 getter 方法 */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NORIGPRICE);
  }

  /** 价税合计 getter 方法 */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NORIGTAXMNY);
  }

  /** 主含税单价 getter 方法 */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NORIGTAXPRICE);
  }

  /** 主本币无税价 getter 方法 */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NPRICE);
  }

  /** 来源单据主数量 getter 方法 */
  public UFDouble getNsourcenum() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NSOURCENUM);
  }

  /** 本币税额 getter 方法 */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NTAX);
  }

  /** 本币价税合计 getter 方法 */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NTAXMNY);
  }

  /** 主本币含税价 getter 方法 */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NTAXPRICE);
  }

  /** 税率 getter 方法 */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NTAXRATE);
  }

  /** 应付组织最新版本 getter 方法 */
  public String getPk_apfinanceorg() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_APFINANCEORG);
  }

  /** 应付组织 getter 方法 */
  public String getPk_apfinanceorg_v() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_APFINANCEORG_V);
  }

  /** 利润中心最新版本 **/
  public String getPk_apliabcenter() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_APLIABCENTER);
  }

  /** 利润中心 **/
  public String getPk_apliabcenter_v() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_APLIABCENTER_V);
  }

  /** 批次档案 getter 方法 */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_BATCHCODE);
  }

  /** 所属集团 getter 方法 */
  public String getPk_group() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_GROUP);
  }

  /** 子实体关联 getter 方法 */
  public String getPk_initialest() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_INITIALEST);
  }

  /** 期初暂估单明细 getter 方法 */
  public String getPk_initialest_b() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_INITIALEST_B);
  }

  /** 物料VID getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_MATERIAL);
  }

  /** 订单ID getter 方法 */
  public String getPk_order() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_ORDER);
  }

  /** 订单行ID getter 方法 */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_ORDER_B);
  }

  /** 财务组织最新版本 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_ORG);
  }

  /** 财务组织 getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_ORG_V);
  }

  /** 物料OID getter 方法 */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_SRCMATERIAL);
  }

  /** 来源单据行TS getter 方法 */
  public UFDateTime getSourcebts() {
    return (UFDateTime) this.getAttributeValue(InitialEstItemVO.SOURCEBTS);
  }

  /** 来源单据TS getter 方法 */
  public UFDateTime getSourcets() {
    return (UFDateTime) this.getAttributeValue(InitialEstItemVO.SOURCETS);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(InitialEstItemVO.TS);
  }

  /** 批次号 getter 方法 */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBATCHCODE);
  }

  /** 自定义项1 getter 方法 */
  public String getVbdef1() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBDEF1);
  }

  /** 自定义项10 getter 方法 */
  public String getVbdef10() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBDEF10);
  }

  /** 自定义项11 getter 方法 */
  public String getVbdef11() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBDEF11);
  }

  /** 自定义项12 getter 方法 */
  public String getVbdef12() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBDEF12);
  }

  /** 自定义项13 getter 方法 */
  public String getVbdef13() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBDEF13);
  }

  /** 自定义项14 getter 方法 */
  public String getVbdef14() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBDEF14);
  }

  /** 自定义项15 getter 方法 */
  public String getVbdef15() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBDEF15);
  }

  /** 自定义项16 getter 方法 */
  public String getVbdef16() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBDEF16);
  }

  /** 自定义项17 getter 方法 */
  public String getVbdef17() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBDEF17);
  }

  /** 自定义项18 getter 方法 */
  public String getVbdef18() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBDEF18);
  }

  /** 自定义项19 getter 方法 */
  public String getVbdef19() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBDEF19);
  }

  /** 自定义项2 getter 方法 */
  public String getVbdef2() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBDEF2);
  }

  /** 自定义项20 getter 方法 */
  public String getVbdef20() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBDEF20);
  }

  /** 自定义项3 getter 方法 */
  public String getVbdef3() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBDEF3);
  }

  /** 自定义项4 getter 方法 */
  public String getVbdef4() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBDEF4);
  }

  /** 自定义项5 getter 方法 */
  public String getVbdef5() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBDEF5);
  }

  /** 自定义项6 getter 方法 */
  public String getVbdef6() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBDEF6);
  }

  /** 自定义项7 getter 方法 */
  public String getVbdef7() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBDEF7);
  }

  /** 自定义项8 getter 方法 */
  public String getVbdef8() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBDEF8);
  }

  /** 自定义项9 getter 方法 */
  public String getVbdef9() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBDEF9);
  }

  /** 备注 getter 方法 */
  public String getVbmemo() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBMEMO);
  }

  /** 换算率 getter 方法 */
  public String getVchangerate() {
    return (String) this.getAttributeValue(InitialEstItemVO.VCHANGERATE);
  }

  /** 合同号 getter 方法 */
  public String getVctcode() {
    return (String) this.getAttributeValue(InitialEstItemVO.VCTCODE);
  }

  /** 自由辅助属性1 getter 方法 */
  public String getVfree1() {
    return (String) this.getAttributeValue(InitialEstItemVO.VFREE1);
  }

  /** 自由辅助属性10 getter 方法 */
  public String getVfree10() {
    return (String) this.getAttributeValue(InitialEstItemVO.VFREE10);
  }

  /** 自由辅助属性2 getter 方法 */
  public String getVfree2() {
    return (String) this.getAttributeValue(InitialEstItemVO.VFREE2);
  }

  /** 自由辅助属性3 getter 方法 */
  public String getVfree3() {
    return (String) this.getAttributeValue(InitialEstItemVO.VFREE3);
  }

  /** 自由辅助属性4 getter 方法 */
  public String getVfree4() {
    return (String) this.getAttributeValue(InitialEstItemVO.VFREE4);
  }

  /** 自由辅助属性5 getter 方法 */
  public String getVfree5() {
    return (String) this.getAttributeValue(InitialEstItemVO.VFREE5);
  }

  /** 自由辅助属性6 getter 方法 */
  public String getVfree6() {
    return (String) this.getAttributeValue(InitialEstItemVO.VFREE6);
  }

  /** 自由辅助属性7 getter 方法 */
  public String getVfree7() {
    return (String) this.getAttributeValue(InitialEstItemVO.VFREE7);
  }

  /** 自由辅助属性8 getter 方法 */
  public String getVfree8() {
    return (String) this.getAttributeValue(InitialEstItemVO.VFREE8);
  }

  /** 自由辅助属性9 getter 方法 */
  public String getVfree9() {
    return (String) this.getAttributeValue(InitialEstItemVO.VFREE9);
  }

  /** 订单号 getter 方法 */
  public String getVordercode() {
    return (String) this.getAttributeValue(InitialEstItemVO.VORDERCODE);
  }

  /** 订单交易类型 getter 方法 */
  public String getVordertrantype() {
    return (String) this.getAttributeValue(InitialEstItemVO.VORDERTRANTYPE);
  }

  /** 来源单据号 getter 方法 */
  public String getVsourcecode() {
    return (String) this.getAttributeValue(InitialEstItemVO.VSOURCECODE);
  }

  /** 来源单据行号 getter 方法 */
  public String getVsourcerowno() {
    return (String) this.getAttributeValue(InitialEstItemVO.VSOURCEROWNO);
  }

  /** 来源交易类型 getter 方法 */
  public String getVsourcetrantype() {
    return (String) this.getAttributeValue(InitialEstItemVO.VSOURCETRANTYPE);
  }

  /** 影响成本标志 **/
  public void setBaffectcost(UFBoolean baffectcost) {
    this.setAttributeValue(InitialEstItemVO.BAFFECTCOST, baffectcost);
  }

  /** 影响利润中心成本 **/
  public void setBaffectpccost(UFBoolean baffectpccost) {
    this.setAttributeValue(InitialEstItemVO.BAFFECTPCCOST, baffectpccost);
  }

  /** 暂估应付标志 setter 方法 */
  public void setBestimateap(UFBoolean bestimateap) {
    this.setAttributeValue(InitialEstItemVO.BESTIMATEAP, bestimateap);
  }

  /** 逆向征税 setter 方法 */
  public void setBopptaxflag(UFBoolean bopptaxflag) {
    this.setAttributeValue(InitialEstItemVO.BOPPTAXFLAG, bopptaxflag);
  }

  /** 是否结算完成 setter 方法 */
  public void setBsettlefinish(UFBoolean bsettlefinish) {
    this.setAttributeValue(InitialEstItemVO.BSETTLEFINISH, bsettlefinish);
  }

  /** 三角贸易 setter 方法 */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(InitialEstItemVO.BTRIATRADEFLAG, btriatradeflag);
  }

  /** 客户 setter 方法 */
  public void setCasscustid(String casscustid) {
    this.setAttributeValue(InitialEstItemVO.CASSCUSTID, casscustid);
  }

  /** 单位 setter 方法 */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(InitialEstItemVO.CASTUNITID, castunitid);
  }

  /** 特征码 **/
  public void setCffileid(String cffileid) {
    this.setAttributeValue(InitialEstItemVO.CFFILEID, cffileid);
  }

  /** 订单行号 setter 方法 */
  public void setCorderrowno(String corderrowno) {
    this.setAttributeValue(InitialEstItemVO.CORDERROWNO, corderrowno);
  }

  /** 生产厂商 setter 方法 */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(InitialEstItemVO.CPRODUCTORID, cproductorid);
  }

  /** 项目 setter 方法 */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(InitialEstItemVO.CPROJECTID, cprojectid);
  }

  /** 收货国家/地区 setter 方法 */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(InitialEstItemVO.CRECECOUNTRYID, crececountryid);
  }

  /** 行号 setter 方法 */
  public void setCrowno(String crowno) {
    this.setAttributeValue(InitialEstItemVO.CROWNO, crowno);
  }

  /** 发货国/地区 setter 方法 */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(InitialEstItemVO.CSENDCOUNTRYID, csendcountryid);
  }

  /** 来源单据明细 setter 方法 */
  public void setCsourcebid(String csourcebid) {
    this.setAttributeValue(InitialEstItemVO.CSOURCEBID, csourcebid);
  }

  /** 来源单据 setter 方法 */
  public void setCsourceid(String csourceid) {
    this.setAttributeValue(InitialEstItemVO.CSOURCEID, csourceid);
  }

  /** 来源单据类型 setter 方法 */
  public void setCsourcetypecode(String csourcetypecode) {
    this.setAttributeValue(InitialEstItemVO.CSOURCETYPECODE, csourcetypecode);
  }

  /** 税码 setter 方法 */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(InitialEstItemVO.CTAXCODEID, ctaxcodeid);
  }

  /** 报税国/地区 setter 方法 */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(InitialEstItemVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  /** 主单位 setter 方法 */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(InitialEstItemVO.CUNITID, cunitid);
  }

  /** dr setter 方法 */
  public void setDr(Integer dr) {
    this.setAttributeValue(InitialEstItemVO.DR, dr);
  }

  /** 购销类型 setter 方法 */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(InitialEstItemVO.FBUYSELLFLAG, fbuysellflag);
  }

  /** 扣税类别 setter 方法 */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(InitialEstItemVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /** 累计费用结算金额 setter 方法 */
  public void setNaccfeesettlemny(UFDouble naccfeesettlemny) {
    this.setAttributeValue(InitialEstItemVO.NACCFEESETTLEMNY, naccfeesettlemny);
  }

  /** 累计货物结算金额 setter 方法 */
  public void setNaccgoodssettlemny(UFDouble naccgoodssettlemny) {
    this.setAttributeValue(InitialEstItemVO.NACCGOODSSETTLEMNY,
        naccgoodssettlemny);
  }

  /** 累计开票数量 setter 方法 */
  public void setNaccinvoicenum(UFDouble naccinvoicenum) {
    this.setAttributeValue(InitialEstItemVO.NACCINVOICENUM, naccinvoicenum);
  }

  /** 累计结算金额 setter 方法 */
  public void setNaccsettlemny(UFDouble naccsettlemny) {
    this.setAttributeValue(InitialEstItemVO.NACCSETTLEMNY, naccsettlemny);
  }

  /** 累计结算数量 setter 方法 */
  public void setNaccsettlenum(UFDouble naccsettlenum) {
    this.setAttributeValue(InitialEstItemVO.NACCSETTLENUM, naccsettlenum);
  }

  /** 累计冲减暂估金额 setter 方法 */
  public void setNaccwashmny(UFDouble naccwashmny) {
    this.setAttributeValue(InitialEstItemVO.NACCWASHMNY, naccwashmny);
  }

  /** 数量 setter 方法 */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(InitialEstItemVO.NASTNUM, nastnum);
  }

  /** 无税单价 setter 方法 */
  public void setNastorigprice(UFDouble nastorigprice) {
    this.setAttributeValue(InitialEstItemVO.NASTORIGPRICE, nastorigprice);
  }

  /** 含税单价 setter 方法 */
  public void setNastorigtaxprice(UFDouble nastorigtaxprice) {
    this.setAttributeValue(InitialEstItemVO.NASTORIGTAXPRICE, nastorigtaxprice);
  }

  /** 本币无税单价 setter 方法 */
  public void setNastprice(UFDouble nastprice) {
    this.setAttributeValue(InitialEstItemVO.NASTPRICE, nastprice);
  }

  /** 本币含税单价 setter 方法 */
  public void setNasttaxprice(UFDouble nasttaxprice) {
    this.setAttributeValue(InitialEstItemVO.NASTTAXPRICE, nasttaxprice);
  }

  /** 计成本金额 setter 方法 */
  public void setNcalcostmny(UFDouble ncalcostmny) {
    this.setAttributeValue(InitialEstItemVO.NCALCOSTMNY, ncalcostmny);
  }

  /** 计税金额 setter 方法 */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(InitialEstItemVO.NCALTAXMNY, ncaltaxmny);
  }

  /** 可开票数量 setter 方法 */
  public void setNcaninvoicenum(UFDouble ncaninvoicenum) {
    this.setAttributeValue(InitialEstItemVO.NCANINVOICENUM, ncaninvoicenum);
  }

  /**
   * 计成本单价
   * 
   * @param nestcalcostprice
   */
  public void setNestcalcostprice(UFDouble nestcalcostprice) {
    this.setAttributeValue(InitialEstItemVO.NESTCALCOSTPRICE, nestcalcostprice);
  }

  /** 本币无税金额 setter 方法 */
  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(InitialEstItemVO.NMNY, nmny);

  }

  /** 不可抵扣税额 setter 方法 */
  public void setNnosubtax(UFDouble nnosubtax) {
    this.setAttributeValue(InitialEstItemVO.NNOSUBTAX, nnosubtax);
  }

  /** 不可抵扣税率 setter 方法 */
  public void setNnosubtaxrate(UFDouble nnosubtaxrate) {
    this.setAttributeValue(InitialEstItemVO.NNOSUBTAXRATE, nnosubtaxrate);
  }

  /** 主数量 setter 方法 */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(InitialEstItemVO.NNUM, nnum);
  }

  /** 无税金额 setter 方法 */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(InitialEstItemVO.NORIGMNY, norigmny);
  }

  /** 主无税单价 setter 方法 */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(InitialEstItemVO.NORIGPRICE, norigprice);
  }

  /** 价税合计 setter 方法 */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(InitialEstItemVO.NORIGTAXMNY, norigtaxmny);
  }

  /** 主含税单价 setter 方法 */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(InitialEstItemVO.NORIGTAXPRICE, norigtaxprice);
  }

  /** 主本币无税价 setter 方法 */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(InitialEstItemVO.NPRICE, nprice);
  }

  /** 来源单据主数量 setter 方法 */
  public void setNsourcenum(UFDouble nsourcenum) {
    this.setAttributeValue(InitialEstItemVO.NSOURCENUM, nsourcenum);
  }

  /** 本币税额 setter 方法 */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(InitialEstItemVO.NTAX, ntax);
  }

  /** 本币价税合计 setter 方法 */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(InitialEstItemVO.NTAXMNY, ntaxmny);
  }

  /** 主本币含税价 setter 方法 */
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(InitialEstItemVO.NTAXPRICE, ntaxprice);
  }

  /** 税率 setter 方法 */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(InitialEstItemVO.NTAXRATE, ntaxrate);
  }

  /** 应付组织最新版本 setter 方法 */
  public void setPk_apfinanceorg(String pk_apfinanceorg) {
    this.setAttributeValue(InitialEstItemVO.PK_APFINANCEORG, pk_apfinanceorg);
  }

  /** 应付组织 setter 方法 */
  public void setPk_apfinanceorg_v(String pk_apfinanceorg_v) {
    this.setAttributeValue(InitialEstItemVO.PK_APFINANCEORG_V,
        pk_apfinanceorg_v);
  }

  /** 利润中心最新版本 **/
  public void setPk_apliabcenter(String pk_apliabcenter) {
    this.setAttributeValue(InitialEstItemVO.PK_APLIABCENTER, pk_apliabcenter);
  }

  /** 利润中心 **/
  public void setPk_apliabcenter_v(String pk_apliabcenter_v) {
    this.setAttributeValue(InitialEstItemVO.PK_APLIABCENTER_V,
        pk_apliabcenter_v);
  }

  /** 批次档案 setter 方法 */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(InitialEstItemVO.PK_BATCHCODE, pk_batchcode);
  }

  /** 所属集团 setter 方法 */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(InitialEstItemVO.PK_GROUP, pk_group);
  }

  /** 子实体关联 setter 方法 */
  public void setPk_initialest(String pk_initialest) {
    this.setAttributeValue(InitialEstItemVO.PK_INITIALEST, pk_initialest);
  }

  /** 期初暂估单明细 setter 方法 */
  public void setPk_initialest_b(String pk_initialest_b) {
    this.setAttributeValue(InitialEstItemVO.PK_INITIALEST_B, pk_initialest_b);
  }

  /** 物料VID setter 方法 */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(InitialEstItemVO.PK_MATERIAL, pk_material);
  }

  /** 订单ID setter 方法 */
  public void setPk_order(String pk_order) {
    this.setAttributeValue(InitialEstItemVO.PK_ORDER, pk_order);
  }

  /** 订单行ID setter 方法 */
  public void setPk_order_b(String pk_order_b) {
    this.setAttributeValue(InitialEstItemVO.PK_ORDER_B, pk_order_b);
  }

  /** 财务组织最新版本 setter 方法 */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(InitialEstItemVO.PK_ORG, pk_org);
  }

  /** 财务组织 setter 方法 */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(InitialEstItemVO.PK_ORG_V, pk_org_v);
  }

  /** 物料OID setter 方法 */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(InitialEstItemVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

  /** 来源单据行TS setter 方法 */
  public void setSourcebts(UFDateTime sourcebts) {
    this.setAttributeValue(InitialEstItemVO.SOURCEBTS, sourcebts);
  }

  /** 来源单据TS setter 方法 */
  public void setSourcets(UFDateTime sourcets) {
    this.setAttributeValue(InitialEstItemVO.SOURCETS, sourcets);
  }

  /** ts setter 方法 */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(InitialEstItemVO.TS, ts);
  }

  /** 批次号 setter 方法 */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(InitialEstItemVO.VBATCHCODE, vbatchcode);
  }

  /** 自定义项1 setter 方法 */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(InitialEstItemVO.VBDEF1, vbdef1);
  }

  /** 自定义项10 setter 方法 */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(InitialEstItemVO.VBDEF10, vbdef10);
  }

  /** 自定义项11 setter 方法 */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(InitialEstItemVO.VBDEF11, vbdef11);
  }

  /** 自定义项12 setter 方法 */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(InitialEstItemVO.VBDEF12, vbdef12);
  }

  /** 自定义项13 setter 方法 */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(InitialEstItemVO.VBDEF13, vbdef13);
  }

  /** 自定义项14 setter 方法 */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(InitialEstItemVO.VBDEF14, vbdef14);
  }

  /** 自定义项15 setter 方法 */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(InitialEstItemVO.VBDEF15, vbdef15);
  }

  /** 自定义项16 setter 方法 */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(InitialEstItemVO.VBDEF16, vbdef16);
  }

  /** 自定义项17 setter 方法 */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(InitialEstItemVO.VBDEF17, vbdef17);
  }

  /** 自定义项18 setter 方法 */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(InitialEstItemVO.VBDEF18, vbdef18);
  }

  /** 自定义项19 setter 方法 */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(InitialEstItemVO.VBDEF19, vbdef19);
  }

  /** 自定义项2 setter 方法 */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(InitialEstItemVO.VBDEF2, vbdef2);
  }

  /** 自定义项20 setter 方法 */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(InitialEstItemVO.VBDEF20, vbdef20);
  }

  /** 自定义项3 setter 方法 */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(InitialEstItemVO.VBDEF3, vbdef3);
  }

  /** 自定义项4 setter 方法 */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(InitialEstItemVO.VBDEF4, vbdef4);
  }

  /** 自定义项5 setter 方法 */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(InitialEstItemVO.VBDEF5, vbdef5);
  }

  /** 自定义项6 setter 方法 */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(InitialEstItemVO.VBDEF6, vbdef6);
  }

  /** 自定义项7 setter 方法 */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(InitialEstItemVO.VBDEF7, vbdef7);
  }

  /** 自定义项8 setter 方法 */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(InitialEstItemVO.VBDEF8, vbdef8);
  }

  /** 自定义项9 setter 方法 */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(InitialEstItemVO.VBDEF9, vbdef9);
  }

  /** 备注 setter 方法 */
  public void setVbmemo(String vbmemo) {
    this.setAttributeValue(InitialEstItemVO.VBMEMO, vbmemo);
  }

  /** 换算率 setter 方法 */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(InitialEstItemVO.VCHANGERATE, vchangerate);
  }

  /** 合同号 setter 方法 */
  public void setVctcode(String vctcode) {
    this.setAttributeValue(InitialEstItemVO.VCTCODE, vctcode);
  }

  /** 自由辅助属性1 setter 方法 */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(InitialEstItemVO.VFREE1, vfree1);
  }

  /** 自由辅助属性10 setter 方法 */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(InitialEstItemVO.VFREE10, vfree10);
  }

  /** 自由辅助属性2 setter 方法 */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(InitialEstItemVO.VFREE2, vfree2);
  }

  /** 自由辅助属性3 setter 方法 */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(InitialEstItemVO.VFREE3, vfree3);
  }

  /** 自由辅助属性4 setter 方法 */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(InitialEstItemVO.VFREE4, vfree4);
  }

  /** 自由辅助属性5 setter 方法 */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(InitialEstItemVO.VFREE5, vfree5);
  }

  /** 自由辅助属性6 setter 方法 */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(InitialEstItemVO.VFREE6, vfree6);
  }

  /** 自由辅助属性7 setter 方法 */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(InitialEstItemVO.VFREE7, vfree7);
  }

  /** 自由辅助属性8 setter 方法 */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(InitialEstItemVO.VFREE8, vfree8);
  }

  /** 自由辅助属性9 setter 方法 */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(InitialEstItemVO.VFREE9, vfree9);
  }

  /** 订单号 setter 方法 */
  public void setVordercode(String vordercode) {
    this.setAttributeValue(InitialEstItemVO.VORDERCODE, vordercode);
  }

  /** 订单交易类型 setter 方法 */
  public void setVordertrantype(String vordertrantype) {
    this.setAttributeValue(InitialEstItemVO.VORDERTRANTYPE, vordertrantype);
  }

  /** 来源单据号 setter 方法 */
  public void setVsourcecode(String vsourcecode) {
    this.setAttributeValue(InitialEstItemVO.VSOURCECODE, vsourcecode);
  }

  /** 来源单据行号 setter 方法 */
  public void setVsourcerowno(String vsourcerowno) {
    this.setAttributeValue(InitialEstItemVO.VSOURCEROWNO, vsourcerowno);
  }

  /** 来源交易类型 setter 方法 */
  public void setVsourcetrantype(String vsourcetrantype) {
    this.setAttributeValue(InitialEstItemVO.VSOURCETRANTYPE, vsourcetrantype);
  }

}
