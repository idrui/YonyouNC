package nc.vo.pu.m4203.entity;

import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * 委托加工财务体VO
 * 
 * @since 6.0
 * @version 2011-1-19 下午02:25:49
 * @author zhaoyha
 */
public class SubcontinFIItemVO extends SuperVO {
  /** 影响成本标志 */
  public static final String BAFFECTCOST = "baffectcost";

  /** 是否结算完成 */
  public static final String BSETTLEFINISH = "bsettlefinish";

  /** 客户 */
  public static final String CASSCUSTID = "casscustid";

  /** 单位 */
  public static final String CASTUNITID = "castunitid";

  /** 本位币 */
  public static final String CCURRENCYID = "ccurrencyid";

  /** 特征码 **/
  public static final String CFFILEID = "cffileid";

  /** 源头单据表体 */
  public static final String CFIRSTBID = "cfirstbid";

  /** 源头单据表头 */
  public static final String CFIRSTID = "cfirstid";

  /** 源头单据类型 */
  public static final String CFIRSTTYPECODE = "cfirsttypecode";

  /** 生产厂商 */
  public static final String CPRODUCTORID = "cproductorid";

  /** 项目 */
  public static final String CPROJECTID = "cprojectid";

  /** 质量等级 */
  public static final String CQUALITYLEVELID = "cqualitylevelid";

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

  /** 主单位 */
  public static final String CUNITID = "cunitid";

  /** 工作中心 */
  public static final String CWORKCENTERID = "cworkcenterid";

  /** 业务日期 */
  public static final String DBIZDATE = "dbizdate";

  /** 源头单据制单日期 */
  public static final String DFIRSTBILLDATE = "dfirstbilldate";

  /** dr */
  public static final String DR = "dr";

  /** 暂估日期 */
  public static final String DTOCOSTAPDATE = "dtocostapdate";

  /** 传成本标志 */
  public static final String FTOIAFLAG = "ftoiaflag";
  
  /** 产品类型 */
  public static final String FOUTPUTTYPE = "foutputtype";

  /** 累计回冲暂估成本金额 */
  public static final String NACCESTCOSTWASHMNY = "naccestcostwashmny";

  /** 累计费用结算金额 */
  public static final String NACCFEESETTLEMNY = "naccfeesettlemny";

  /** 累计货物结算金额 */
  public static final String NACCGOODSSETTLEMNY = "naccgoodssettlemny";

  /** 累计结算金额 */
  public static final String NACCSETTLEMNY = "naccsettlemny";

  /** 累计结算数量 */
  public static final String NACCUMSETTLENUM = "naccumsettlenum";

  /** 金额 */
  public static final String NCOSTMNY = "ncostmny";

  /** 单价 */
  public static final String NCOSTPRICE = "ncostprice";

  /** 实入辅数量 */
  public static final String NINASSISTNUM = "ninassistnum";

  /** 入库主数量 */
  public static final String NINNUM = "ninnum";

  /** 计划金额 */
  public static final String NPLANNEDMNY = "nplannedmny";

  /** 计划单价 */
  public static final String NPLANNEDPRICE = "nplannedprice";

  /** 应付财务组织 */
  public static final String PK_APFINANCEORG = "pk_apfinanceorg";

  /** 应付财务组织版本 */
  public static final String PK_APFINANCEORG_V = "pk_apfinanceorg_v";

  /** 利润中心 */
  public static final String PK_APLIABCENTER = "pk_apliabcenter";

  /** 利润中心版本 */
  public static final String PK_APLIABCENTER_V = "pk_apliabcenter_v";

  /** 批次档案 */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /** 公司最新版本 */
  public static final String PK_CORP = "pk_corp";

  /** 公司 */
  public static final String PK_CORP_V = "pk_corp_v";

  /** 成本域 */
  public static final String PK_COSTREGION = "pk_costregion";

  /** 结算财务组织 */
  public static final String PK_FINANCEORG = "pk_financeorg";

  /** 结算财务组织版本 */
  public static final String PK_FINANCEORG_V = "pk_financeorg_v";

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

  /** 物料 */
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  /** 委托加工入财务明细 */
  public static final String PK_STOCKPS = "pk_stockps";

  /** 委托加工入明细主键 */
  public static final String PK_STOCKPS_B = "pk_stockps_b";

  /** 供应商 */
  public static final String PK_SUPPLIER = "pk_supplier";

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

  /** 生产批号 */
  public static final String VPRODUCTBATCH = "vproductbatch";

  /** 生产订单号 */
  public static final String VPRODUCTBILLCODE = "vproductbillcode";

  /** 来源单据号 */
  public static final String VSOURCECODE = "vsourcecode";

  /** 来源单据行号 */
  public static final String VSOURCEROWNO = "vsourcerowno";

  /** 来源交易类型 */
  public static final String VSOURCETRANTYPE = "vsourcetrantype";

  private static final long serialVersionUID = -2235857194115893338L;

  /** 影响成本标志 getter 方法 */
  public UFBoolean getBaffectcost() {
    return (UFBoolean) this.getAttributeValue(SubcontinFIItemVO.BAFFECTCOST);
  }

  /** 是否结算完成 getter 方法 */
  public UFBoolean getBsettlefinish() {
    return (UFBoolean) this.getAttributeValue(SubcontinFIItemVO.BSETTLEFINISH);
  }

  /** 客户 getter 方法 */
  public String getCasscustid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CASSCUSTID);
  }

  /** 单位 getter 方法 */
  public String getCastunitid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CASTUNITID);
  }

  /** 本位币 getter 方法 */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CCURRENCYID);
  }

  /** 特征码 **/
  public String getCffileid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CFFILEID);
  }

  /** 源头单据表体 getter 方法 */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CFIRSTBID);
  }

  /** 源头单据表头 getter 方法 */
  public String getCfirstid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CFIRSTID);
  }

  /** 源头单据类型 getter 方法 */
  public String getCfirsttypecode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CFIRSTTYPECODE);
  }

  /** 生产厂商 getter 方法 */
  public String getCproductorid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CPRODUCTORID);
  }

  /** 项目 getter 方法 */
  public String getCprojectid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CPROJECTID);
  }

  /** 质量等级 getter 方法 */
  public String getCqualitylevelid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CQUALITYLEVELID);
  }

  /** 行号 getter 方法 */
  public String getCrowno() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CROWNO);
  }

  /** 来源单据表体 getter 方法 */
  public String getCsourcebid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CSOURCEBID);
  }

  /** 来源单据表头 getter 方法 */
  public String getCsourceid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CSOURCEID);
  }

  /** 来源单据类型 getter 方法 */
  public String getCsourcetypecode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CSOURCETYPECODE);
  }

  /** 库存状态 getter 方法 */
  public String getCstateid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CSTATEID);
  }

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CUNITID);
  }

  /** 工作中心 getter 方法 */
  public String getCworkcenterid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CWORKCENTERID);
  }

  /** 业务日期 getter 方法 */
  public UFDate getDbizdate() {
    return (UFDate) this.getAttributeValue(SubcontinFIItemVO.DBIZDATE);
  }

  /** 源头单据制单日期 getter 方法 */
  public String getDfirstbilldate() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.DFIRSTBILLDATE);
  }

  /** dr getter 方法 */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(SubcontinFIItemVO.DR);
  }

  /** 暂估日期 getter 方法 */
  public UFDateTime getDtocostapdate() {
    return (UFDateTime) this.getAttributeValue(SubcontinFIItemVO.DTOCOSTAPDATE);
  }

  /** 传成本标志 getter 方法 */
  public Integer getFtoiaflag() {
    return (Integer) this.getAttributeValue(SubcontinFIItemVO.FTOIAFLAG);
  }
  
  /** 产品类型 getter 方法 */
  public Integer getFoutputtype() {
    return (Integer) this.getAttributeValue(SubcontinFIItemVO.FOUTPUTTYPE);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta(PUEntity.SUBCONTIN_B);
    return meta;
  }

  /** 累计回冲暂估成本金额 getter 方法 */
  public UFDouble getNaccestcostwashmny() {
    return (UFDouble) this
        .getAttributeValue(SubcontinFIItemVO.NACCESTCOSTWASHMNY);
  }

  /** 累计费用结算金额 getter 方法 */
  public UFDouble getNaccfeesettlemny() {
    return (UFDouble) this
        .getAttributeValue(SubcontinFIItemVO.NACCFEESETTLEMNY);
  }

  /** 累计货物结算金额 getter 方法 */
  public UFDouble getNaccgoodssettlemny() {
    return (UFDouble) this
        .getAttributeValue(SubcontinFIItemVO.NACCGOODSSETTLEMNY);
  }

  /** 累计结算金额 getter 方法 */
  public UFDouble getNaccsettlemny() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NACCSETTLEMNY);
  }

  /** 累计结算数量 getter 方法 */
  public UFDouble getNaccumsettlenum() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NACCUMSETTLENUM);
  }

  /** 金额 getter 方法 */
  public UFDouble getNcostmny() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NCOSTMNY);
  }

  /** 单价 getter 方法 */
  public UFDouble getNcostprice() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NCOSTPRICE);
  }

  /** 实入辅数量 getter 方法 */
  public UFDouble getNinassistnum() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NINASSISTNUM);
  }

  /** 入库主数量 getter 方法 */
  public UFDouble getNinnum() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NINNUM);
  }

  /** 计划金额 getter 方法 */
  public UFDouble getNplannedmny() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NPLANNEDMNY);
  }

  /** 计划单价 getter 方法 */
  public UFDouble getNplannedprice() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NPLANNEDPRICE);
  }

  /** 应付财务组织 getter 方法 */
  public String getPk_apfinanceorg() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_APFINANCEORG);
  }

  /** 应付财务组织版本 getter 方法 */
  public String getPk_apfinanceorg_v() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_APFINANCEORG_V);
  }

  /** 利润中心 getter 方法 */
  public String getPk_apliabcenter() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_APLIABCENTER);
  }

  /** 利润中心版本 getter 方法 */
  public String getPk_apliabcenter_v() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_APLIABCENTER_V);
  }

  /** 批次档案 getter 方法 */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_BATCHCODE);
  }

  /** 公司最新版本 getter 方法 */
  public String getPk_corp() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_CORP);
  }

  /** 公司 getter 方法 */
  public String getPk_corp_v() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_CORP_V);
  }

  /** 成本域 getter 方法 */
  public String getPk_costregion() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_COSTREGION);
  }

  /** 结算财务组织 getter 方法 */
  public String getPk_financeorg() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_FINANCEORG);
  }

  /** 结算财务组织版本 getter 方法 */
  public String getPk_financeorg_v() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_FINANCEORG_V);
  }

  /** 集团 getter 方法 */
  public String getPk_group() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_GROUP);
  }

  /** 开票供应商 getter 方法 */
  public String getPk_invcsupllier() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_INVCSUPLLIER);
  }

  /** 物料编码 getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_MATERIAL);
  }

  /** 订单 getter 方法 */
  public String getPk_order() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_ORDER);
  }

  /** 订单明细 getter 方法 */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_ORDER_B);
  }

  /** 库存组织 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_ORG);
  }

  /** 库存组织版本 getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_ORG_V);
  }

  /** 采购组织 getter 方法 */
  public String getPk_purchaseorg() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_PURCHASEORG);
  }

  /** 采购组织版本 getter 方法 */
  public String getPk_purchaseorg_v() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_PURCHASEORG_V);
  }

  /** 物料 getter 方法 */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_SRCMATERIAL);
  }

  /** 委托加工入财务明细 getter 方法 */
  public String getPk_stockps() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_STOCKPS);
  }

  /** 委托加工入明细主键 getter 方法 */
  public String getPk_stockps_b() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_STOCKPS_B);
  }

  /** 供应商 getter 方法 */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_SUPPLIER);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SubcontinFIItemVO.TS);
  }

  /** 批次号 getter 方法 */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBATCHCODE);
  }

  /** 自定义项1 getter 方法 */
  public String getVbdef1() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF1);
  }

  /** 自定义项10 getter 方法 */
  public String getVbdef10() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF10);
  }

  /** 自定义项11 getter 方法 */
  public String getVbdef11() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF11);
  }

  /** 自定义项12 getter 方法 */
  public String getVbdef12() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF12);
  }

  /** 自定义项13 getter 方法 */
  public String getVbdef13() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF13);
  }

  /** 自定义项14 getter 方法 */
  public String getVbdef14() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF14);
  }

  /** 自定义项15 getter 方法 */
  public String getVbdef15() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF15);
  }

  /** 自定义项16 getter 方法 */
  public String getVbdef16() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF16);
  }

  /** 自定义项17 getter 方法 */
  public String getVbdef17() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF17);
  }

  /** 自定义项18 getter 方法 */
  public String getVbdef18() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF18);
  }

  /** 自定义项19 getter 方法 */
  public String getVbdef19() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF19);
  }

  /** 自定义项2 getter 方法 */
  public String getVbdef2() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF2);
  }

  /** 自定义项20 getter 方法 */
  public String getVbdef20() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF20);
  }

  /** 自定义项3 getter 方法 */
  public String getVbdef3() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF3);
  }

  /** 自定义项4 getter 方法 */
  public String getVbdef4() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF4);
  }

  /** 自定义项5 getter 方法 */
  public String getVbdef5() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF5);
  }

  /** 自定义项6 getter 方法 */
  public String getVbdef6() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF6);
  }

  /** 自定义项7 getter 方法 */
  public String getVbdef7() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF7);
  }

  /** 自定义项8 getter 方法 */
  public String getVbdef8() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF8);
  }

  /** 自定义项9 getter 方法 */
  public String getVbdef9() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF9);
  }

  /** 换算率 getter 方法 */
  public String getVchangerate() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VCHANGERATE);
  }

  /** 采购合同号 getter 方法 */
  public String getVctcode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VCTCODE);
  }

  /** 源头单据号 getter 方法 */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFIRSTCODE);
  }

  /** 源头单据行号 getter 方法 */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFIRSTROWNO);
  }

  /** 源头单据交易类型 getter 方法 */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFIRSTTRANTYPE);
  }

  /** 自由辅助属性1 getter 方法 */
  public String getVfree1() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE1);
  }

  /** 自由辅助属性10 getter 方法 */
  public String getVfree10() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE10);
  }

  /** 自由辅助属性2 getter 方法 */
  public String getVfree2() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE2);
  }

  /** 自由辅助属性3 getter 方法 */
  public String getVfree3() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE3);
  }

  /** 自由辅助属性4 getter 方法 */
  public String getVfree4() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE4);
  }

  /** 自由辅助属性5 getter 方法 */
  public String getVfree5() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE5);
  }

  /** 自由辅助属性6 getter 方法 */
  public String getVfree6() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE6);
  }

  /** 自由辅助属性7 getter 方法 */
  public String getVfree7() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE7);
  }

  /** 自由辅助属性8 getter 方法 */
  public String getVfree8() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE8);
  }

  /** 自由辅助属性9 getter 方法 */
  public String getVfree9() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE9);
  }

  /** 行备注 getter 方法 */
  public String getVnotebody() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VNOTEBODY);
  }

  /** 订单号 getter 方法 */
  public String getVordercode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VORDERCODE);
  }

  /** 订单交易类型 getter 方法 */
  public String getVordertrantypecode() {
    return (String) this
        .getAttributeValue(SubcontinFIItemVO.VORDERTRANTYPECODE);
  }

  /** 生产批号 getter 方法 */
  public String getVproductbatch() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VPRODUCTBATCH);
  }

  /** 生产订单号 getter 方法 */
  public String getVproductbillcode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VPRODUCTBILLCODE);
  }

  /** 来源单据号 getter 方法 */
  public String getVsourcecode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VSOURCECODE);
  }

  /** 来源单据行号 getter 方法 */
  public String getVsourcerowno() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VSOURCEROWNO);
  }

  /** 来源交易类型 getter 方法 */
  public String getVsourcetrantype() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VSOURCETRANTYPE);
  }

  /** 影响成本标志 setter 方法 */
  public void setBaffectcost(UFBoolean baffectcost) {
    this.setAttributeValue(SubcontinFIItemVO.BAFFECTCOST, baffectcost);
  }

  /** 是否结算完成 setter 方法 */
  public void setBsettlefinish(UFBoolean bsettlefinish) {
    this.setAttributeValue(SubcontinFIItemVO.BSETTLEFINISH, bsettlefinish);
  }

  /** 客户 setter 方法 */
  public void setCasscustid(String casscustid) {
    this.setAttributeValue(SubcontinFIItemVO.CASSCUSTID, casscustid);
  }

  /** 单位 setter 方法 */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(SubcontinFIItemVO.CASTUNITID, castunitid);
  }

  /** 本位币 setter 方法 */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(SubcontinFIItemVO.CCURRENCYID, ccurrencyid);
  }

  /** 特征码 **/
  public void setCffileid(String cffileid) {
    this.setAttributeValue(VmiFIHeaderVO.CFFILEID, cffileid);
  }

  /** 源头单据表体 setter 方法 */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(SubcontinFIItemVO.CFIRSTBID, cfirstbid);
  }

  /** 源头单据表头 setter 方法 */
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(SubcontinFIItemVO.CFIRSTID, cfirstid);
  }

  /** 源头单据类型 setter 方法 */
  public void setCfirsttypecode(String cfirsttypecode) {
    this.setAttributeValue(SubcontinFIItemVO.CFIRSTTYPECODE, cfirsttypecode);
  }

  /** 生产厂商 setter 方法 */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(SubcontinFIItemVO.CPRODUCTORID, cproductorid);
  }

  /** 项目 setter 方法 */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(SubcontinFIItemVO.CPROJECTID, cprojectid);
  }

  /** 质量等级 setter 方法 */
  public void setCqualitylevelid(String cqualitylevelid) {
    this.setAttributeValue(SubcontinFIItemVO.CQUALITYLEVELID, cqualitylevelid);
  }

  /** 行号 setter 方法 */
  public void setCrowno(String crowno) {
    this.setAttributeValue(SubcontinFIItemVO.CROWNO, crowno);
  }

  /** 来源单据表体 setter 方法 */
  public void setCsourcebid(String csourcebid) {
    this.setAttributeValue(SubcontinFIItemVO.CSOURCEBID, csourcebid);
  }

  /** 来源单据表头 setter 方法 */
  public void setCsourceid(String csourceid) {
    this.setAttributeValue(SubcontinFIItemVO.CSOURCEID, csourceid);
  }

  /** 来源单据类型 setter 方法 */
  public void setCsourcetypecode(String csourcetypecode) {
    this.setAttributeValue(SubcontinFIItemVO.CSOURCETYPECODE, csourcetypecode);
  }

  /** 库存状态 setter 方法 */
  public void setCstateid(String cstateid) {
    this.setAttributeValue(SubcontinFIItemVO.CSTATEID, cstateid);
  }

  /** 主单位 setter 方法 */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(SubcontinFIItemVO.CUNITID, cunitid);
  }

  /** 工作中心 setter 方法 */
  public void setCworkcenterid(String cworkcenterid) {
    this.setAttributeValue(SubcontinFIItemVO.CWORKCENTERID, cworkcenterid);
  }

  /** 业务日期 setter 方法 */
  public void setDbizdate(UFDate dbizdate) {
    this.setAttributeValue(SubcontinFIItemVO.DBIZDATE, dbizdate);
  }

  /** 源头单据制单日期 setter 方法 */
  public void setDfirstbilldate(String dfirstbilldate) {
    this.setAttributeValue(SubcontinFIItemVO.DFIRSTBILLDATE, dfirstbilldate);
  }

  /** dr setter 方法 */
  public void setDr(Integer dr) {
    this.setAttributeValue(SubcontinFIItemVO.DR, dr);
  }

  /** 暂估日期 setter 方法 */
  public void setDtocostapdate(UFDateTime dtocostapdate) {
    this.setAttributeValue(SubcontinFIItemVO.DTOCOSTAPDATE, dtocostapdate);
  }

  /** 传成本标志 setter 方法 */
  public void setFtoiaflag(Integer ftoiaflag) {
    this.setAttributeValue(SubcontinFIItemVO.FTOIAFLAG, ftoiaflag);
  }
  
  /** 产品类型 setter 方法 */
  public void setFoutputtype(Integer foutputtype) {
    this.setAttributeValue(SubcontinFIItemVO.FOUTPUTTYPE, foutputtype);
  }

  /** 累计回冲暂估成本金额 setter 方法 */
  public void setNaccestcostwashmny(UFDouble naccestcostwashmny) {
    this.setAttributeValue(SubcontinFIItemVO.NACCESTCOSTWASHMNY,
        naccestcostwashmny);
  }

  /** 累计费用结算金额 setter 方法 */
  public void setNaccfeesettlemny(UFDouble naccfeesettlemny) {
    this.setAttributeValue(SubcontinFIItemVO.NACCFEESETTLEMNY, naccfeesettlemny);
  }

  /** 累计货物结算金额 setter 方法 */
  public void setNaccgoodssettlemny(UFDouble naccgoodssettlemny) {
    this.setAttributeValue(SubcontinFIItemVO.NACCGOODSSETTLEMNY,
        naccgoodssettlemny);
  }

  /** 累计结算金额 setter 方法 */
  public void setNaccsettlemny(UFDouble naccsettlemny) {
    this.setAttributeValue(SubcontinFIItemVO.NACCSETTLEMNY, naccsettlemny);
  }

  /** 累计结算数量 setter 方法 */
  public void setNaccumsettlenum(UFDouble naccumsettlenum) {
    this.setAttributeValue(SubcontinFIItemVO.NACCUMSETTLENUM, naccumsettlenum);
  }

  /** 金额 setter 方法 */
  public void setNcostmny(UFDouble ncostmny) {
    this.setAttributeValue(SubcontinFIItemVO.NCOSTMNY, ncostmny);
  }

  /** 单价 setter 方法 */
  public void setNcostprice(UFDouble ncostprice) {
    this.setAttributeValue(SubcontinFIItemVO.NCOSTPRICE, ncostprice);
  }

  /** 实入辅数量 setter 方法 */
  public void setNinassistnum(UFDouble ninassistnum) {
    this.setAttributeValue(SubcontinFIItemVO.NINASSISTNUM, ninassistnum);
  }

  /** 入库主数量 setter 方法 */
  public void setNinnum(UFDouble ninnum) {
    this.setAttributeValue(SubcontinFIItemVO.NINNUM, ninnum);
  }

  /** 计划金额 setter 方法 */
  public void setNplannedmny(UFDouble nplannedmny) {
    this.setAttributeValue(SubcontinFIItemVO.NPLANNEDMNY, nplannedmny);
  }

  /** 计划单价 setter 方法 */
  public void setNplannedprice(UFDouble nplannedprice) {
    this.setAttributeValue(SubcontinFIItemVO.NPLANNEDPRICE, nplannedprice);
  }

  /** 应付财务组织 setter 方法 */
  public void setPk_apfinanceorg(String pk_apfinanceorg) {
    this.setAttributeValue(SubcontinFIItemVO.PK_APFINANCEORG, pk_apfinanceorg);
  }

  /** 应付财务组织版本 setter 方法 */
  public void setPk_apfinanceorg_v(String pk_apfinanceorg_v) {
    this.setAttributeValue(SubcontinFIItemVO.PK_APFINANCEORG_V,
        pk_apfinanceorg_v);
  }

  /** 利润中心 setter 方法 */
  public void setPk_apliabcenter(String pk_apliabcenter) {
    this.setAttributeValue(SubcontinFIItemVO.PK_APLIABCENTER, pk_apliabcenter);
  }

  /** 利润中心版本 setter 方法 */
  public void setPk_apliabcenter_v(String pk_apliabcenter_v) {
    this.setAttributeValue(SubcontinFIItemVO.PK_APLIABCENTER_V,
        pk_apliabcenter_v);
  }

  /** 批次档案 setter 方法 */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(SubcontinFIItemVO.PK_BATCHCODE, pk_batchcode);
  }

  /** 公司最新版本 setter 方法 */
  public void setPk_corp(String pk_corp) {
    this.setAttributeValue(SubcontinFIItemVO.PK_CORP, pk_corp);
  }

  /** 公司 setter 方法 */
  public void setPk_corp_v(String pk_corp_v) {
    this.setAttributeValue(SubcontinFIItemVO.PK_CORP_V, pk_corp_v);
  }

  /** 成本域 setter 方法 */
  public void setPk_costregion(String pk_costregion) {
    this.setAttributeValue(SubcontinFIItemVO.PK_COSTREGION, pk_costregion);
  }

  /** 结算财务组织 setter 方法 */
  public void setPk_financeorg(String pk_financeorg) {
    this.setAttributeValue(SubcontinFIItemVO.PK_FINANCEORG, pk_financeorg);
  }

  /** 结算财务组织版本 setter 方法 */
  public void setPk_financeorg_v(String pk_financeorg_v) {
    this.setAttributeValue(SubcontinFIItemVO.PK_FINANCEORG_V, pk_financeorg_v);
  }

  /** 集团 setter 方法 */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SubcontinFIItemVO.PK_GROUP, pk_group);
  }

  /** 开票供应商 setter 方法 */
  public void setPk_invcsupllier(String pk_invcsupllier) {
    this.setAttributeValue(SubcontinFIItemVO.PK_INVCSUPLLIER, pk_invcsupllier);
  }

  /** 物料编码 setter 方法 */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(SubcontinFIItemVO.PK_MATERIAL, pk_material);
  }

  /** 订单 setter 方法 */
  public void setPk_order(String pk_order) {
    this.setAttributeValue(SubcontinFIItemVO.PK_ORDER, pk_order);
  }

  /** 订单明细 setter 方法 */
  public void setPk_order_b(String pk_order_b) {
    this.setAttributeValue(SubcontinFIItemVO.PK_ORDER_B, pk_order_b);
  }

  /** 库存组织 setter 方法 */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SubcontinFIItemVO.PK_ORG, pk_org);
  }

  /** 库存组织版本 setter 方法 */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(SubcontinFIItemVO.PK_ORG_V, pk_org_v);
  }

  /** 采购组织 setter 方法 */
  public void setPk_purchaseorg(String pk_purchaseorg) {
    this.setAttributeValue(SubcontinFIItemVO.PK_PURCHASEORG, pk_purchaseorg);
  }

  /** 采购组织版本 setter 方法 */
  public void setPk_purchaseorg_v(String pk_purchaseorg_v) {
    this.setAttributeValue(SubcontinFIItemVO.PK_PURCHASEORG_V, pk_purchaseorg_v);
  }

  /** 物料 setter 方法 */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(SubcontinFIItemVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

  /** 委托加工入财务明细 setter 方法 */
  public void setPk_stockps(String pk_stockps) {
    this.setAttributeValue(SubcontinFIItemVO.PK_STOCKPS, pk_stockps);
  }

  /** 委托加工入明细主键 setter 方法 */
  public void setPk_stockps_b(String pk_stockps_b) {
    this.setAttributeValue(SubcontinFIItemVO.PK_STOCKPS_B, pk_stockps_b);
  }

  /** 供应商 setter 方法 */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(SubcontinFIItemVO.PK_SUPPLIER, pk_supplier);
  }

  /** ts setter 方法 */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SubcontinFIItemVO.TS, ts);
  }

  /** 批次号 setter 方法 */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(SubcontinFIItemVO.VBATCHCODE, vbatchcode);
  }

  /** 自定义项1 setter 方法 */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF1, vbdef1);
  }

  /** 自定义项10 setter 方法 */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF10, vbdef10);
  }

  /** 自定义项11 setter 方法 */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF11, vbdef11);
  }

  /** 自定义项12 setter 方法 */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF12, vbdef12);
  }

  /** 自定义项13 setter 方法 */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF13, vbdef13);
  }

  /** 自定义项14 setter 方法 */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF14, vbdef14);
  }

  /** 自定义项15 setter 方法 */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF15, vbdef15);
  }

  /** 自定义项16 setter 方法 */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF16, vbdef16);
  }

  /** 自定义项17 setter 方法 */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF17, vbdef17);
  }

  /** 自定义项18 setter 方法 */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF18, vbdef18);
  }

  /** 自定义项19 setter 方法 */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF19, vbdef19);
  }

  /** 自定义项2 setter 方法 */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF2, vbdef2);
  }

  /** 自定义项20 setter 方法 */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF20, vbdef20);
  }

  /** 自定义项3 setter 方法 */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF3, vbdef3);
  }

  /** 自定义项4 setter 方法 */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF4, vbdef4);
  }

  /** 自定义项5 setter 方法 */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF5, vbdef5);
  }

  /** 自定义项6 setter 方法 */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF6, vbdef6);
  }

  /** 自定义项7 setter 方法 */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF7, vbdef7);
  }

  /** 自定义项8 setter 方法 */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF8, vbdef8);
  }

  /** 自定义项9 setter 方法 */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF9, vbdef9);
  }

  /** 换算率 setter 方法 */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(SubcontinFIItemVO.VCHANGERATE, vchangerate);
  }

  /** 采购合同号 setter 方法 */
  public void setVctcode(String vctcode) {
    this.setAttributeValue(SubcontinFIItemVO.VCTCODE, vctcode);
  }

  /** 源头单据号 setter 方法 */
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(SubcontinFIItemVO.VFIRSTCODE, vfirstcode);
  }

  /** 源头单据行号 setter 方法 */
  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(SubcontinFIItemVO.VFIRSTROWNO, vfirstrowno);
  }

  /** 源头单据交易类型 setter 方法 */
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(SubcontinFIItemVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /** 自由辅助属性1 setter 方法 */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(SubcontinFIItemVO.VFREE1, vfree1);
  }

  /** 自由辅助属性10 setter 方法 */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(SubcontinFIItemVO.VFREE10, vfree10);
  }

  /** 自由辅助属性2 setter 方法 */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(SubcontinFIItemVO.VFREE2, vfree2);
  }

  /** 自由辅助属性3 setter 方法 */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(SubcontinFIItemVO.VFREE3, vfree3);
  }

  /** 自由辅助属性4 setter 方法 */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(SubcontinFIItemVO.VFREE4, vfree4);
  }

  /** 自由辅助属性5 setter 方法 */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(SubcontinFIItemVO.VFREE5, vfree5);
  }

  /** 自由辅助属性6 setter 方法 */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(SubcontinFIItemVO.VFREE6, vfree6);
  }

  /** 自由辅助属性7 setter 方法 */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(SubcontinFIItemVO.VFREE7, vfree7);
  }

  /** 自由辅助属性8 setter 方法 */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(SubcontinFIItemVO.VFREE8, vfree8);
  }

  /** 自由辅助属性9 setter 方法 */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(SubcontinFIItemVO.VFREE9, vfree9);
  }

  /** 行备注 setter 方法 */
  public void setVnotebody(String vnotebody) {
    this.setAttributeValue(SubcontinFIItemVO.VNOTEBODY, vnotebody);
  }

  /** 订单号 setter 方法 */
  public void setVordercode(String vordercode) {
    this.setAttributeValue(SubcontinFIItemVO.VORDERCODE, vordercode);
  }

  /** 订单交易类型 setter 方法 */
  public void setVordertrantypecode(String vordertrantypecode) {
    this.setAttributeValue(SubcontinFIItemVO.VORDERTRANTYPECODE,
        vordertrantypecode);
  }

  /** 生产批号 setter 方法 */
  public void setVproductbatch(String vproductbatch) {
    this.setAttributeValue(SubcontinFIItemVO.VPRODUCTBATCH, vproductbatch);
  }

  /** 生产订单号 setter 方法 */
  public void setVproductbillcode(String vproductbillcode) {
    this.setAttributeValue(SubcontinFIItemVO.VPRODUCTBILLCODE, vproductbillcode);
  }

  /** 来源单据号 setter 方法 */
  public void setVsourcecode(String vsourcecode) {
    this.setAttributeValue(SubcontinFIItemVO.VSOURCECODE, vsourcecode);
  }

  /** 来源单据行号 setter 方法 */
  public void setVsourcerowno(String vsourcerowno) {
    this.setAttributeValue(SubcontinFIItemVO.VSOURCEROWNO, vsourcerowno);
  }

  /** 来源交易类型 setter 方法 */
  public void setVsourcetrantype(String vsourcetrantype) {
    this.setAttributeValue(SubcontinFIItemVO.VSOURCETRANTYPE, vsourcetrantype);
  }

}
