/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-18 下午07:45:42
 */
package nc.vo.pu.m422x.entity;

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
 * <li>物资需求申请单表体
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-18 下午07:45:42
 */
public class StoreReqAppItemVO extends SuperVO {

  /** 是否关闭 */
  public static final String BCLOSE = "bclose";

  /** 已平衡 */
  public static final String BENDGATHER = "bendgather";

  /** 单位 */
  public static final String CASTUNITID = "castunitid";

  /** CBS */
  public static final String CBS = "cbs";

  /** 本币币种 */
  public static final String CCURRENCYID = "ccurrencyid";

  /** 收货地点 */
  public static final String CDEVADDRID = "cdevaddrid";

  /** 收货地区 */
  public static final String CDEVAREAID = "cdevareaid";

  /** 源头单据明细 */
  public static final String CFIRSTBID = "cfirstbid";

  /** 调拨单据明细 */
  public static final String CFIRSTBID2 = "cfirstbid2";

  /** 源头单据 */
  public static final String CFIRSTID = "cfirstid";

  /** 调拨单据 */
  public static final String CFIRSTID2 = "cfirstid2";

  /** 源头单据类型 */
  public static final String CFIRSTTYPECODE = "cfirsttypecode";

  /** 调拨单据类型 */
  public static final String CFIRSTTYPECODE2 = "cfirsttypecode2";

  /** 汇总id */
  public static final String CGATHERID = "cgatherid";

  /** 汇总人 */
  public static final String CGATHERPSNID = "cgatherpsnid";

  /** 生产厂商 */
  public static final String CPRODUCTORID = "cproductorid";

  /** 项目 */
  public static final String CPROJECTID = "cprojectid";

  /** 项目任务 */
  public static final String CPROJECTTASKID = "cprojecttaskid";

  /** 行号 */
  public static final String CROWNO = "crowno";

  /** 来源单据明细 */
  public static final String CSOURCEBID = "csourcebid";

  /** 下游单据明细 */
  public static final String CSOURCEBID2 = "csourcebid2";

  /** 来源单据 */
  public static final String CSOURCEID = "csourceid";

  /** 下游单据 */
  public static final String CSOURCEID2 = "csourceid2";

  /** 来源单据类型 */
  public static final String CSOURCETYPECODE = "csourcetypecode";

  /** 下游单据类型 */
  public static final String CSOURCETYPECODE2 = "csourcetypecode2";

  /** 主单位 */
  public static final String CUNITID = "cunitid";

  /** 供应商 */
  public static final String CVENDORID = "cvendorid";

  /** 申请日期 */
  public static final String DBILLDATE = "dbilldate";

  /** dr */
  public static final String DR = "dr";

  /** 需求日期 */
  public static final String DREQDATE = "dreqdate";

  /** 库存满足数量 */
  public static final String NACCCUSTORNUM = "naccustornum";

  /** 累计请购主数量 **/
  public static final String NACCUMBUYREQNUM = "naccumbuyreqnum";

  /** mengjian 汇总平衡转请购主数量（原累计消减主数量 ） */
  public static final String NACCUMMINUSNUM = "naccumminusnum";

  /** 累计出库数量 */
  public static final String NACCUOUTNUM = "naccuoutnum";

  /** 累计申请出库主数量 */
  public static final String NACCUOUTREQNUM = "naccuoutreqnum";

  /** 数量 */
  public static final String NASTNUM = "nastnum";

  /** 可请购主数量 */
  public static final String NCANBUYREQNNUM = "ncanbuyreqnnum";

  /** 可申请出库主数量 */
  public static final String NCANOUTREQNUM = "ncanoutreqnum";

  /** 转净需求数量 */
  public static final String NNETNUM = "nnetnum";

  /** 主数量 */
  public static final String NNUM = "nnum";

  /** 本币价税合计 */
  public static final String NTAXMNY = "ntaxmny";

  /** 主本币含税单价 */
  public static final String NTAXPRICE = "ntaxprice";

  /** 申请部门最新版本 */
  public static final String PK_APPDEPT = "pk_appdept";

  /** 申请部门 */
  public static final String PK_APPDEPT_V = "pk_appdept_v";

  /** 申请人 */
  public static final String PK_APPPSN = "pk_apppsn";

  /** 批次号主键 */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /** 集团 */
  public static final String PK_GROUP = "pk_group";

  /** 物料版本信息 */
  public static final String PK_MATERIAL = "pk_material";

  /** 下次平衡库存组织最新版本 */
  public static final String PK_NEXTBALANCEORG = "pk_nextbalanceorg";

  /** 下次平衡库存组织 */
  public static final String PK_NEXTBALANCEORG_V = "pk_nextbalanceorg_v";

  /** 库存组织最新版本 */
  public static final String PK_ORG = "pk_org";

  /** 库存组织 */
  public static final String PK_ORG_V = "pk_org_v";

  /** 收货地址 */
  public static final String PK_RECEIVEADDRESS = "pk_receiveaddress";

  /** 原始需求库存组织最新版本 */
  public static final String PK_REQSTOORG = "pk_reqstoorg";

  // zhangyhh 2013-5-7 库存计划
  /** 原始需求库存组织 */
  public static final String PK_REQSTOORG_V = "pk_reqstoorg_v";

  /** 需求仓库 */
  public static final String PK_REQSTORDOC = "pk_reqstordoc";

  /** 物料信息 */
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  /** 物资需求申请单明细 */
  public static final String PK_STOREREQ = "pk_storereq";

  /** 物资需求申请单明细 */
  public static final String PK_STOREREQ_B = "pk_storereq_b";

  /** 来源单据行TS */
  public static final String SOURCEBTS = "sourcebts";

  /** 来源单据TS */
  public static final String SOURCETS = "sourcets";

  /** 汇总时间 */
  public static final String TGATHERTIME = "tgathertime";

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

  /** 源头单据号 */
  public static final String VFIRSTCODE = "vfirstcode";

  /** 调拨单据号 */
  public static final String VFIRSTCODE2 = "vfirstcode2";

  /** 源头单据行号 */
  public static final String VFIRSTROWNO = "vfirstrowno";

  /** 调拨单据行号 */
  public static final String VFIRSTROWNO2 = "vfirstrowno2";

  /** 源头交易类型 */
  public static final String VFIRSTTRANTYPE = "vfirsttrantype";

  /** 调拨交易类型 */
  public static final String VFIRSTTRANTYPE2 = "vfirsttrantype2";

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

  /** 来源单据号 */
  public static final String VSOURCECODE = "vsourcecode";

  /** 下游单据号 */
  public static final String VSOURCECODE2 = "vsourcecode2";

  /** 来源单据行号 */
  public static final String VSOURCEROWNO = "vsourcerowno";

  /** 下游单据行号 */
  public static final String VSOURCEROWNO2 = "vsourcerowno2";

  /** 来源交易类型 */
  public static final String VSOURCETRANTYPE = "vsourcetrantype";

  /** 下游交易类型 */
  public static final String VSOURCETRANTYPE2 = "vsourcetrantype2";

  private static final long serialVersionUID = -6965316168376936499L;

  /** 是否关闭 getter 方法 */
  public UFBoolean getBclose() {
    return (UFBoolean) this.getAttributeValue(StoreReqAppItemVO.BCLOSE);
  }

  /** 已平衡 getter 方法 */
  public UFBoolean getBendgather() {
    return (UFBoolean) this.getAttributeValue(StoreReqAppItemVO.BENDGATHER);
  }

  /** 单位 getter 方法 */
  public String getCastunitid() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CASTUNITID);
  }

  /** CBS getter 方法 */
  public String getCbs() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CBS);
  }

  /** 本币币种 getter 方法 */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CCURRENCYID);
  }

  /** 收货地点 getter 方法 */
  public String getCdevaddrid() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CDEVADDRID);
  }

  /** 收货地区 getter 方法 */
  public String getCdevareaid() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CDEVAREAID);
  }

  /** 源头单据明细 getter 方法 */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CFIRSTBID);
  }

  /** 调拨单据明细 getter 方法 */
  public String getCfirstbid2() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CFIRSTBID2);
  }

  /** 源头单据 getter 方法 */
  public String getCfirstid() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CFIRSTID);
  }

  /** 调拨单据 getter 方法 */
  public String getCfirstid2() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CFIRSTID2);
  }

  /** 源头单据类型 getter 方法 */
  public String getCfirsttypecode() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CFIRSTTYPECODE);
  }

  /** 调拨单据类型 getter 方法 */
  public String getCfirsttypecode2() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CFIRSTTYPECODE2);
  }

  /** 汇总id getter 方法 */
  public String getCgatherid() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CGATHERID);
  }

  /** 汇总人 getter 方法 */
  public String getCgatherpsnid() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CGATHERPSNID);
  }

  /** 生产厂商 getter 方法 */
  public String getCproductorid() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CPRODUCTORID);
  }

  /** 项目 getter 方法 */
  public String getCprojectid() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CPROJECTID);
  }

  /** 项目任务 getter 方法 */
  public String getCprojecttaskid() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CPROJECTTASKID);
  }

  /** 行号 getter 方法 */
  public String getCrowno() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CROWNO);
  }

  /** 来源单据明细 getter 方法 */
  public String getCsourcebid() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CSOURCEBID);
  }

  /** 下游单据明细 getter 方法 */
  public String getCsourcebid2() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CSOURCEBID2);
  }

  /** 来源单据 getter 方法 */
  public String getCsourceid() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CSOURCEID);
  }

  /** 下游单据 getter 方法 */
  public String getCsourceid2() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CSOURCEID2);
  }

  /** 来源单据类型 getter 方法 */
  public String getCsourcetypecode() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CSOURCETYPECODE);
  }

  /** 下游单据类型 getter 方法 */
  public String getCsourcetypecode2() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CSOURCETYPECODE2);
  }

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CUNITID);
  }

  /** 供应商 getter 方法 */
  public String getCvendorid() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CVENDORID);
  }

  /** 申请日期 getter 方法 */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(StoreReqAppItemVO.DBILLDATE);
  }

  /** dr getter 方法 */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(StoreReqAppItemVO.DR);
  }

  /** 需求日期 getter 方法 */
  public UFDate getDreqdate() {
    return (UFDate) this.getAttributeValue(StoreReqAppItemVO.DREQDATE);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.SuperVO#getMetaData()
   */
  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.M422X_B);
  }

  /** 累计请购主数量 getter 方法 **/
  public UFDouble getNaccumbuyreqnum() {
    return (UFDouble) this.getAttributeValue(StoreReqAppItemVO.NACCUMBUYREQNUM);
  }

  /** 汇总平衡转请购主数量（原累计消减主数量 ） getter 方法 */
  public UFDouble getNaccumminusnum() {
    return (UFDouble) this.getAttributeValue(StoreReqAppItemVO.NACCUMMINUSNUM);
  }

  /** 累计出库数量 getter 方法 */
  public UFDouble getNaccuoutnum() {
    return (UFDouble) this.getAttributeValue(StoreReqAppItemVO.NACCUOUTNUM);
  }

  /** 累计申请出库主数量 getter 方法 */
  public UFDouble getNaccuoutreqnum() {
    return (UFDouble) this.getAttributeValue(StoreReqAppItemVO.NACCUOUTREQNUM);
  }

  /** 库存满足数量 getter 方法 */
  public UFDouble getNaccustornum() {
    return (UFDouble) this.getAttributeValue(StoreReqAppItemVO.NACCCUSTORNUM);
  }

  /** 数量 getter 方法 */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(StoreReqAppItemVO.NASTNUM);
  }

  /** 可请购主数量 getter 方法 */
  public UFDouble getNcanbuyreqnnum() {
    return (UFDouble) this.getAttributeValue(StoreReqAppItemVO.NCANBUYREQNNUM);
  }

  /** 可申请出库主数量 getter 方法 */
  public UFDouble getNcanoutreqnum() {
    return (UFDouble) this.getAttributeValue(StoreReqAppItemVO.NCANOUTREQNUM);
  }

  /** 转净需求数量 getter 方法 */
  public UFDouble getNnetnum() {
    return (UFDouble) this.getAttributeValue(StoreReqAppItemVO.NNETNUM);
  }

  /** 主数量 getter 方法 */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(StoreReqAppItemVO.NNUM);
  }

  /** 本币价税合计 getter 方法 */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(StoreReqAppItemVO.NTAXMNY);
  }

  /** 主本币含税单价 getter 方法 */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(StoreReqAppItemVO.NTAXPRICE);
  }

  /** 申请部门最新版本 getter 方法 */
  public String getPk_appdept() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.PK_APPDEPT);
  }

  /** 申请部门 getter 方法 */
  public String getPk_appdept_v() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.PK_APPDEPT_V);
  }

  /** 申请人 getter 方法 */
  public String getPk_apppsn() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.PK_APPPSN);
  }

  /** 批次号主键 getter 方法 */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.PK_BATCHCODE);
  }

  /** 集团 getter 方法 */
  public String getPk_group() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.PK_GROUP);
  }

  /** 物料版本信息 getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.PK_MATERIAL);
  }

  /** 下次平衡库存组织最新版本 getter 方法 */
  public String getPk_nextbalanceorg() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.PK_NEXTBALANCEORG);
  }

  /** 下次平衡库存组织 getter 方法 */
  public String getPk_nextbalanceorg_v() {
    return (String) this
        .getAttributeValue(StoreReqAppItemVO.PK_NEXTBALANCEORG_V);
  }

  /** 库存组织最新版本 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.PK_ORG);
  }

  /** 库存组织 getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.PK_ORG_V);
  }

  /** 收货地址 getter 方法 */
  public String getPk_receiveaddress() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.PK_RECEIVEADDRESS);
  }

  /** 原始需求库存组织最新版本 getter 方法 */
  public String getPk_reqstoorg() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.PK_REQSTOORG);
  }

  // zhangyhh 2014-5-7
  /** 原始需求库存组织 getter 方法 */
  public String getPk_reqstoorg_v() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.PK_REQSTOORG_V);
  }

  /** 需求仓库 getter 方法 */
  public String getPk_reqstordoc() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.PK_REQSTORDOC);
  }

  /** 物料信息 getter 方法 */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.PK_SRCMATERIAL);
  }

  /** 物资需求申请单明细 getter 方法 */
  public String getPk_storereq() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.PK_STOREREQ);
  }

  /** 物资需求申请单明细 getter 方法 */
  public String getPk_storereq_b() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.PK_STOREREQ_B);
  }

  /** 来源单据行TS getter 方法 */
  public UFDateTime getSourcebts() {
    return (UFDateTime) this.getAttributeValue(StoreReqAppItemVO.SOURCEBTS);
  }

  /** 来源单据TS getter 方法 */
  public UFDateTime getSourcets() {
    return (UFDateTime) this.getAttributeValue(StoreReqAppItemVO.SOURCETS);
  }

  /** 汇总时间 getter 方法 */
  public UFDate getTgathertime() {
    return (UFDate) this.getAttributeValue(StoreReqAppItemVO.TGATHERTIME);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(StoreReqAppItemVO.TS);
  }

  /** 批次号 getter 方法 */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBATCHCODE);
  }

  /** 自定义项1 getter 方法 */
  public String getVbdef1() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBDEF1);
  }

  /** 自定义项10 getter 方法 */
  public String getVbdef10() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBDEF10);
  }

  /** 自定义项11 getter 方法 */
  public String getVbdef11() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBDEF11);
  }

  /** 自定义项12 getter 方法 */
  public String getVbdef12() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBDEF12);
  }

  /** 自定义项13 getter 方法 */
  public String getVbdef13() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBDEF13);
  }

  /** 自定义项14 getter 方法 */
  public String getVbdef14() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBDEF14);
  }

  /** 自定义项15 getter 方法 */
  public String getVbdef15() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBDEF15);
  }

  /** 自定义项16 getter 方法 */
  public String getVbdef16() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBDEF16);
  }

  /** 自定义项17 getter 方法 */
  public String getVbdef17() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBDEF17);
  }

  /** 自定义项18 getter 方法 */
  public String getVbdef18() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBDEF18);
  }

  /** 自定义项19 getter 方法 */
  public String getVbdef19() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBDEF19);
  }

  /** 自定义项2 getter 方法 */
  public String getVbdef2() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBDEF2);
  }

  /** 自定义项20 getter 方法 */
  public String getVbdef20() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBDEF20);
  }

  /** 自定义项3 getter 方法 */
  public String getVbdef3() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBDEF3);
  }

  /** 自定义项4 getter 方法 */
  public String getVbdef4() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBDEF4);
  }

  /** 自定义项5 getter 方法 */
  public String getVbdef5() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBDEF5);
  }

  /** 自定义项6 getter 方法 */
  public String getVbdef6() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBDEF6);
  }

  /** 自定义项7 getter 方法 */
  public String getVbdef7() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBDEF7);
  }

  /** 自定义项8 getter 方法 */
  public String getVbdef8() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBDEF8);
  }

  /** 自定义项9 getter 方法 */
  public String getVbdef9() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBDEF9);
  }

  /** 备注 getter 方法 */
  public String getVbmemo() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VBMEMO);
  }

  /** 换算率 getter 方法 */
  public String getVchangerate() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VCHANGERATE);
  }

  /** 源头单据号 getter 方法 */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VFIRSTCODE);
  }

  /** 调拨单据号 getter 方法 */
  public String getVfirstcode2() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VFIRSTCODE2);
  }

  /** 源头单据行号 getter 方法 */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VFIRSTROWNO);
  }

  /** 调拨单据行号 getter 方法 */
  public String getVfirstrowno2() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VFIRSTROWNO2);
  }

  /** 源头交易类型 getter 方法 */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VFIRSTTRANTYPE);
  }

  /** 调拨交易类型 getter 方法 */
  public String getVfirsttrantype2() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VFIRSTTRANTYPE2);
  }

  /** 自由辅助属性1 getter 方法 */
  public String getVfree1() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VFREE1);
  }

  /** 自由辅助属性10 getter 方法 */
  public String getVfree10() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VFREE10);
  }

  /** 自由辅助属性2 getter 方法 */
  public String getVfree2() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VFREE2);
  }

  /** 自由辅助属性3 getter 方法 */
  public String getVfree3() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VFREE3);
  }

  /** 自由辅助属性4 getter 方法 */
  public String getVfree4() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VFREE4);
  }

  /** 自由辅助属性5 getter 方法 */
  public String getVfree5() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VFREE5);
  }

  /** 自由辅助属性6 getter 方法 */
  public String getVfree6() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VFREE6);
  }

  /** 自由辅助属性7 getter 方法 */
  public String getVfree7() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VFREE7);
  }

  /** 自由辅助属性8 getter 方法 */
  public String getVfree8() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VFREE8);
  }

  /** 自由辅助属性9 getter 方法 */
  public String getVfree9() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VFREE9);
  }

  /** 来源单据号 getter 方法 */
  public String getVsourcecode() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VSOURCECODE);
  }

  /** 下游单据号 getter 方法 */
  public String getVsourcecode2() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VSOURCECODE2);
  }

  /** 来源单据行号 getter 方法 */
  public String getVsourcerowno() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VSOURCEROWNO);
  }

  /** 下游单据行号 getter 方法 */
  public String getVsourcerowno2() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VSOURCEROWNO2);
  }

  /** 来源交易类型 getter 方法 */
  public String getVsourcetrantype() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VSOURCETRANTYPE);
  }

  /** 下游交易类型 getter 方法 */
  public String getVsourcetrantype2() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.VSOURCETRANTYPE2);
  }

  /** 是否关闭 setter 方法 */
  public void setBclose(UFBoolean bclose) {
    this.setAttributeValue(StoreReqAppItemVO.BCLOSE, bclose);
  }

  /** 已平衡 setter 方法 */
  public void setBendgather(UFBoolean bendgather) {
    this.setAttributeValue(StoreReqAppItemVO.BENDGATHER, bendgather);
  }

  /** 单位 setter 方法 */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(StoreReqAppItemVO.CASTUNITID, castunitid);
  }

  /** CBS setter 方法 */
  public void setCbs(String cbs) {
    this.setAttributeValue(StoreReqAppItemVO.CBS, cbs);
  }

  /** 本币币种 setter 方法 */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(StoreReqAppItemVO.CCURRENCYID, ccurrencyid);
  }

  /** 收货地点 setter 方法 */
  public void setCdevaddrid(String cdevaddrid) {
    this.setAttributeValue(StoreReqAppItemVO.CDEVADDRID, cdevaddrid);
  }

  /** 收货地区 setter 方法 */
  public void setCdevareaid(String cdevareaid) {
    this.setAttributeValue(StoreReqAppItemVO.CDEVAREAID, cdevareaid);
  }

  /** 源头单据明细 setter 方法 */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(StoreReqAppItemVO.CFIRSTBID, cfirstbid);
  }

  /** 调拨单据明细 setter 方法 */
  public void setCfirstbid2(String cfirstbid2) {
    this.setAttributeValue(StoreReqAppItemVO.CFIRSTBID2, cfirstbid2);
  }

  /** 源头单据 setter 方法 */
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(StoreReqAppItemVO.CFIRSTID, cfirstid);
  }

  /** 调拨单据 setter 方法 */
  public void setCfirstid2(String cfirstid2) {
    this.setAttributeValue(StoreReqAppItemVO.CFIRSTID2, cfirstid2);
  }

  /** 源头单据类型 setter 方法 */
  public void setCfirsttypecode(String cfirsttypecode) {
    this.setAttributeValue(StoreReqAppItemVO.CFIRSTTYPECODE, cfirsttypecode);
  }

  /** 调拨单据类型 setter 方法 */
  public void setCfirsttypecode2(String cfirsttypecode2) {
    this.setAttributeValue(StoreReqAppItemVO.CFIRSTTYPECODE2, cfirsttypecode2);
  }

  /** 汇总id setter 方法 */
  public void setCgatherid(String cgatherid) {
    this.setAttributeValue(StoreReqAppItemVO.CGATHERID, cgatherid);
  }

  /** 汇总人 setter 方法 */
  public void setCgatherpsnid(String cgatherpsnid) {
    this.setAttributeValue(StoreReqAppItemVO.CGATHERPSNID, cgatherpsnid);
  }

  /** 生产厂商 setter 方法 */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(StoreReqAppItemVO.CPRODUCTORID, cproductorid);
  }

  /** 项目 setter 方法 */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(StoreReqAppItemVO.CPROJECTID, cprojectid);
  }

  /** 项目任务 setter 方法 */
  public void setCprojecttaskid(String cprojecttaskid) {
    this.setAttributeValue(StoreReqAppItemVO.CPROJECTTASKID, cprojecttaskid);
  }

  /** 行号 setter 方法 */
  public void setCrowno(String crowno) {
    this.setAttributeValue(StoreReqAppItemVO.CROWNO, crowno);
  }

  /** 来源单据明细 setter 方法 */
  public void setCsourcebid(String csourcebid) {
    this.setAttributeValue(StoreReqAppItemVO.CSOURCEBID, csourcebid);
  }

  /** 下游单据明细 setter 方法 */
  public void setCsourcebid2(String csourcebid2) {
    this.setAttributeValue(StoreReqAppItemVO.CSOURCEBID2, csourcebid2);
  }

  /** 来源单据 setter 方法 */
  public void setCsourceid(String csourceid) {
    this.setAttributeValue(StoreReqAppItemVO.CSOURCEID, csourceid);
  }

  /** 下游单据 setter 方法 */
  public void setCsourceid2(String csourceid2) {
    this.setAttributeValue(StoreReqAppItemVO.CSOURCEID2, csourceid2);
  }

  /** 来源单据类型 setter 方法 */
  public void setCsourcetypecode(String csourcetypecode) {
    this.setAttributeValue(StoreReqAppItemVO.CSOURCETYPECODE, csourcetypecode);
  }

  /** 下游单据类型 setter 方法 */
  public void setCsourcetypecode2(String csourcetypecode2) {
    this.setAttributeValue(StoreReqAppItemVO.CSOURCETYPECODE2, csourcetypecode2);
  }

  /** 主单位 setter 方法 */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(StoreReqAppItemVO.CUNITID, cunitid);
  }

  /** 供应商 setter 方法 */
  public void setCvendorid(String cvendorid) {
    this.setAttributeValue(StoreReqAppItemVO.CVENDORID, cvendorid);
  }

  /** 申请日期 setter 方法 */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(StoreReqAppItemVO.DBILLDATE, dbilldate);
  }

  /** dr setter 方法 */
  public void setDr(Integer dr) {
    this.setAttributeValue(StoreReqAppItemVO.DR, dr);
  }

  /** 需求日期 setter 方法 */
  public void setDreqdate(UFDate dreqdate) {
    this.setAttributeValue(StoreReqAppItemVO.DREQDATE, dreqdate);
  }

  /** 累计请购主数量 setter 方法 **/
  public void setNaccumbuyreqnum(UFDouble naccumbuyreqnum) {
    this.setAttributeValue(StoreReqAppItemVO.NACCUMBUYREQNUM, naccumbuyreqnum);
  }

  /** 汇总平衡转请购主数量（原累计消减主数量 ）setter 方法 */
  public void setNaccumminusnum(UFDouble naccumminusnum) {
    this.setAttributeValue(StoreReqAppItemVO.NACCUMMINUSNUM, naccumminusnum);
  }

  /** 累计出库数量 setter 方法 */
  public void setNaccuoutnum(UFDouble naccuoutnum) {
    this.setAttributeValue(StoreReqAppItemVO.NACCUOUTNUM, naccuoutnum);
  }

  /** 累计申请出库主数量 setter 方法 */
  public void setNaccuoutreqnum(UFDouble naccuoutreqnum) {
    this.setAttributeValue(StoreReqAppItemVO.NACCUOUTREQNUM, naccuoutreqnum);
  }

  /** 库存满足数量 setter 方法 */
  public void setNaccustornum(UFDouble naccustornum) {
    this.setAttributeValue(StoreReqAppItemVO.NACCCUSTORNUM, naccustornum);
  }

  /** 数量 setter 方法 */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(StoreReqAppItemVO.NASTNUM, nastnum);
  }

  /** 可请购主数量 setter 方法 */
  public void setNcanbuyreqnnum(UFDouble ncanbuyreqnnum) {
    this.setAttributeValue(StoreReqAppItemVO.NCANBUYREQNNUM, ncanbuyreqnnum);
  }

  /** 可申请出库主数量 setter 方法 */
  public void setNcanoutreqnum(UFDouble ncanoutreqnum) {
    this.setAttributeValue(StoreReqAppItemVO.NCANOUTREQNUM, ncanoutreqnum);
  }

  /** 转净需求数量 setter 方法 */
  public void setNnetnum(UFDouble nnetnum) {
    this.setAttributeValue(StoreReqAppItemVO.NNETNUM, nnetnum);
  }

  /** 主数量 setter 方法 */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(StoreReqAppItemVO.NNUM, nnum);
  }

  /** 本币价税合计 setter 方法 */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(StoreReqAppItemVO.NTAXMNY, ntaxmny);
  }

  /** 主本币含税单价 setter 方法 */
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(StoreReqAppItemVO.NTAXPRICE, ntaxprice);
  }

  /** 申请部门最新版本 setter 方法 */
  public void setPk_appdept(String pk_appdept) {
    this.setAttributeValue(StoreReqAppItemVO.PK_APPDEPT, pk_appdept);
  }

  /** 申请部门 setter 方法 */
  public void setPk_appdept_v(String pk_appdept_v) {
    this.setAttributeValue(StoreReqAppItemVO.PK_APPDEPT_V, pk_appdept_v);
  }

  /** 申请人 setter 方法 */
  public void setPk_apppsn(String pk_apppsn) {
    this.setAttributeValue(StoreReqAppItemVO.PK_APPPSN, pk_apppsn);
  }

  /** 批次号主键 setter 方法 */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(StoreReqAppItemVO.PK_BATCHCODE, pk_batchcode);
  }

  /** 集团 setter 方法 */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(StoreReqAppItemVO.PK_GROUP, pk_group);
  }

  /** 物料版本信息 setter 方法 */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(StoreReqAppItemVO.PK_MATERIAL, pk_material);
  }

  /** 下次平衡库存组织最新版本 setter 方法 */
  public void setPk_nextbalanceorg(String pk_nextbalanceorg) {
    this.setAttributeValue(StoreReqAppItemVO.PK_NEXTBALANCEORG,
        pk_nextbalanceorg);
  }

  /** 下次平衡库存组织 setter 方法 */
  public void setPk_nextbalanceorg_v(String pk_nextbalanceorg_v) {
    this.setAttributeValue(StoreReqAppItemVO.PK_NEXTBALANCEORG_V,
        pk_nextbalanceorg_v);
  }

  /** 库存组织最新版本 setter 方法 */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(StoreReqAppItemVO.PK_ORG, pk_org);
  }

  /** 库存组织 setter 方法 */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(StoreReqAppItemVO.PK_ORG_V, pk_org_v);
  }

  /** 收货地址 setter 方法 */
  public void setPk_receiveaddress(String pk_receiveaddress) {
    this.setAttributeValue(StoreReqAppItemVO.PK_RECEIVEADDRESS,
        pk_receiveaddress);
  }

  /** 原始需求库存组织最新版本 setter 方法 */
  public void setPk_reqstoorg(String pk_reqstoorg) {
    this.setAttributeValue(StoreReqAppItemVO.PK_REQSTOORG, pk_reqstoorg);
  }

  // zhangyhh setter 2014-5-7
  /** 原始需求库存组织 setter 方法 */
  public void setPk_reqstoorg_v(String pk_reqstoorg_v) {
    this.setAttributeValue(StoreReqAppItemVO.PK_REQSTOORG_V, pk_reqstoorg_v);
  }

  /** 需求仓库 setter 方法 */
  public void setPk_reqstordoc(String pk_reqstordoc) {
    this.setAttributeValue(StoreReqAppItemVO.PK_REQSTORDOC, pk_reqstordoc);
  }

  /** 物料信息 setter 方法 */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(StoreReqAppItemVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

  /** 物资需求申请单明细 setter 方法 */
  public void setPk_storereq(String pk_storereq) {
    this.setAttributeValue(StoreReqAppItemVO.PK_STOREREQ, pk_storereq);
  }

  /** 物资需求申请单明细 setter 方法 */
  public void setPk_storereq_b(String pk_storereq_b) {
    this.setAttributeValue(StoreReqAppItemVO.PK_STOREREQ_B, pk_storereq_b);
  }

  /** 来源单据行TS setter 方法 */
  public void setSourcebts(UFDateTime sourcebts) {
    this.setAttributeValue(StoreReqAppItemVO.SOURCEBTS, sourcebts);
  }

  /** 来源单据TS setter 方法 */
  public void setSourcets(UFDateTime sourcets) {
    this.setAttributeValue(StoreReqAppItemVO.SOURCETS, sourcets);
  }

  /** 汇总时间 setter 方法 */
  public void setTgathertime(UFDate tgathertime) {
    this.setAttributeValue(StoreReqAppItemVO.TGATHERTIME, tgathertime);
  }

  /** ts setter 方法 */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(StoreReqAppItemVO.TS, ts);
  }

  /** 批次号 setter 方法 */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(StoreReqAppItemVO.VBATCHCODE, vbatchcode);
  }

  /** 自定义项1 setter 方法 */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(StoreReqAppItemVO.VBDEF1, vbdef1);
  }

  /** 自定义项10 setter 方法 */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(StoreReqAppItemVO.VBDEF10, vbdef10);
  }

  /** 自定义项11 setter 方法 */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(StoreReqAppItemVO.VBDEF11, vbdef11);
  }

  /** 自定义项12 setter 方法 */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(StoreReqAppItemVO.VBDEF12, vbdef12);
  }

  /** 自定义项13 setter 方法 */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(StoreReqAppItemVO.VBDEF13, vbdef13);
  }

  /** 自定义项14 setter 方法 */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(StoreReqAppItemVO.VBDEF14, vbdef14);
  }

  /** 自定义项15 setter 方法 */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(StoreReqAppItemVO.VBDEF15, vbdef15);
  }

  /** 自定义项16 setter 方法 */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(StoreReqAppItemVO.VBDEF16, vbdef16);
  }

  /** 自定义项17 setter 方法 */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(StoreReqAppItemVO.VBDEF17, vbdef17);
  }

  /** 自定义项18 setter 方法 */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(StoreReqAppItemVO.VBDEF18, vbdef18);
  }

  /** 自定义项19 setter 方法 */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(StoreReqAppItemVO.VBDEF19, vbdef19);
  }

  /** 自定义项2 setter 方法 */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(StoreReqAppItemVO.VBDEF2, vbdef2);
  }

  /** 自定义项20 setter 方法 */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(StoreReqAppItemVO.VBDEF20, vbdef20);
  }

  /** 自定义项3 setter 方法 */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(StoreReqAppItemVO.VBDEF3, vbdef3);
  }

  /** 自定义项4 setter 方法 */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(StoreReqAppItemVO.VBDEF4, vbdef4);
  }

  /** 自定义项5 setter 方法 */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(StoreReqAppItemVO.VBDEF5, vbdef5);
  }

  /** 自定义项6 setter 方法 */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(StoreReqAppItemVO.VBDEF6, vbdef6);
  }

  /** 自定义项7 setter 方法 */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(StoreReqAppItemVO.VBDEF7, vbdef7);
  }

  /** 自定义项8 setter 方法 */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(StoreReqAppItemVO.VBDEF8, vbdef8);
  }

  /** 自定义项9 setter 方法 */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(StoreReqAppItemVO.VBDEF9, vbdef9);
  }

  /** 备注 setter 方法 */
  public void setVbmemo(String vbmemo) {
    this.setAttributeValue(StoreReqAppItemVO.VBMEMO, vbmemo);
  }

  /** 换算率 setter 方法 */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(StoreReqAppItemVO.VCHANGERATE, vchangerate);
  }

  /** 源头单据号 setter 方法 */
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(StoreReqAppItemVO.VFIRSTCODE, vfirstcode);
  }

  /** 调拨单据号 setter 方法 */
  public void setVfirstcode2(String vfirstcode2) {
    this.setAttributeValue(StoreReqAppItemVO.VFIRSTCODE2, vfirstcode2);
  }

  /** 源头单据行号 setter 方法 */
  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(StoreReqAppItemVO.VFIRSTROWNO, vfirstrowno);
  }

  /** 调拨单据行号 setter 方法 */
  public void setVfirstrowno2(String vfirstrowno2) {
    this.setAttributeValue(StoreReqAppItemVO.VFIRSTROWNO2, vfirstrowno2);
  }

  /** 源头交易类型 setter 方法 */
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(StoreReqAppItemVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /** 调拨交易类型 setter 方法 */
  public void setVfirsttrantype2(String vfirsttrantype2) {
    this.setAttributeValue(StoreReqAppItemVO.VFIRSTTRANTYPE2, vfirsttrantype2);
  }

  /** 自由辅助属性1 setter 方法 */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(StoreReqAppItemVO.VFREE1, vfree1);
  }

  /** 自由辅助属性10 setter 方法 */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(StoreReqAppItemVO.VFREE10, vfree10);
  }

  /** 自由辅助属性2 setter 方法 */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(StoreReqAppItemVO.VFREE2, vfree2);
  }

  /** 自由辅助属性3 setter 方法 */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(StoreReqAppItemVO.VFREE3, vfree3);
  }

  /** 自由辅助属性4 setter 方法 */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(StoreReqAppItemVO.VFREE4, vfree4);
  }

  /** 自由辅助属性5 setter 方法 */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(StoreReqAppItemVO.VFREE5, vfree5);
  }

  /** 自由辅助属性6 setter 方法 */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(StoreReqAppItemVO.VFREE6, vfree6);
  }

  /** 自由辅助属性7 setter 方法 */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(StoreReqAppItemVO.VFREE7, vfree7);
  }

  /** 自由辅助属性8 setter 方法 */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(StoreReqAppItemVO.VFREE8, vfree8);
  }

  /** 自由辅助属性9 setter 方法 */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(StoreReqAppItemVO.VFREE9, vfree9);
  }

  /** 来源单据号 setter 方法 */
  public void setVsourcecode(String vsourcecode) {
    this.setAttributeValue(StoreReqAppItemVO.VSOURCECODE, vsourcecode);
  }

  /** 下游单据号 setter 方法 */
  public void setVsourcecode2(String vsourcecode2) {
    this.setAttributeValue(StoreReqAppItemVO.VSOURCECODE2, vsourcecode2);
  }

  /** 来源单据行号 setter 方法 */
  public void setVsourcerowno(String vsourcerowno) {
    this.setAttributeValue(StoreReqAppItemVO.VSOURCEROWNO, vsourcerowno);
  }

  /** 下游单据行号 setter 方法 */
  public void setVsourcerowno2(String vsourcerowno2) {
    this.setAttributeValue(StoreReqAppItemVO.VSOURCEROWNO2, vsourcerowno2);
  }

  /** 来源交易类型 setter 方法 */
  public void setVsourcetrantype(String vsourcetrantype) {
    this.setAttributeValue(StoreReqAppItemVO.VSOURCETRANTYPE, vsourcetrantype);
  }

  /** 下游交易类型 setter 方法 */
  public void setVsourcetrantype2(String vsourcetrantype2) {
    this.setAttributeValue(StoreReqAppItemVO.VSOURCETRANTYPE2, vsourcetrantype2);
  }

}
