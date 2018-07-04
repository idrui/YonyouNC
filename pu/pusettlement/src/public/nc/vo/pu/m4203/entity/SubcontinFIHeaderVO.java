package nc.vo.pu.m4203.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * 委托加工财务头VO
 * 
 * @since 6.0
 * @version 2011-1-19 下午02:23:31
 * @author zhaoyha
 */
public class SubcontinFIHeaderVO extends SuperVO {
  /** 制单人 */
  public static final String BILLMAKER = "billmaker";

  /** 是否普通采购 */
  public static final String BNORMPUR = "bnormpur";

  /** 单据类型编码 */
  public static final String CBILLTYPECODE = "cbilltypecode";

  /** 创建时间 */
  public static final String CREATIONTIME = "creationtime";

  /** 创建人 */
  public static final String CREATOR = "creator";

  /** 交易类型 */
  public static final String CTRANTYPEID = "ctrantypeid";

  /** 库管员 */
  public static final String CWHSMANAGERID = "cwhsmanagerid";

  /** 入库日期 */
  public static final String DBILLDATE = "dbilldate";

  /** dr */
  public static final String DR = "dr";

  /** 退货标志 */
  public static final String FREPLENISHFLAG = "freplenishflag";

  /** 最后修改时间 */
  public static final String MODIFIEDTIME = "modifiedtime";

  /** 最后修改人 */
  public static final String MODIFIER = "modifier";

  /** 业务流程 */
  public static final String PK_BUSITYPE = "pk_busitype";

  /** 公司最新版 */
  public static final String PK_CORP = "pk_corp";

  /** 公司 */
  public static final String PK_CORP_V = "pk_corp_v";

  /** 部门原始信息 */
  public static final String PK_DEPT = "pk_dept";

  /** 部门 */
  public static final String PK_DEPT_V = "pk_dept_v";

  /** 集团 */
  public static final String PK_GROUP = "pk_group";

  /** 库存组织 */
  public static final String PK_ORG = "pk_org";

  /** 库存组织版本 */
  public static final String PK_ORG_V = "pk_org_v";

  /** 业务员 */
  public static final String PK_PSNDOC = "pk_psndoc";

  /** 委托加工入主键 */
  public static final String PK_STOCKPS = "pk_stockps";

  /** 仓库 */
  public static final String PK_STORDOC = "pk_stordoc";

  /** ts */
  public static final String TS = "ts";

  /** 入库单号 */
  public static final String VBILLCODE = "vbillcode";

  /** 自定义项1 */
  public static final String VDEF1 = "vdef1";

  /** 自定义项10 */
  public static final String VDEF10 = "vdef10";

  /** 自定义项11 */
  public static final String VDEF11 = "vdef11";

  /** 自定义项12 */
  public static final String VDEF12 = "vdef12";

  /** 自定义项13 */
  public static final String VDEF13 = "vdef13";

  /** 自定义项14 */
  public static final String VDEF14 = "vdef14";

  /** 自定义项15 */
  public static final String VDEF15 = "vdef15";

  /** 自定义项16 */
  public static final String VDEF16 = "vdef16";

  /** 自定义项17 */
  public static final String VDEF17 = "vdef17";

  /** 自定义项18 */
  public static final String VDEF18 = "vdef18";

  /** 自定义项19 */
  public static final String VDEF19 = "vdef19";

  /** 自定义项2 */
  public static final String VDEF2 = "vdef2";

  /** 自定义项20 */
  public static final String VDEF20 = "vdef20";

  /** 自定义项3 */
  public static final String VDEF3 = "vdef3";

  /** 自定义项4 */
  public static final String VDEF4 = "vdef4";

  /** 自定义项5 */
  public static final String VDEF5 = "vdef5";

  /** 自定义项6 */
  public static final String VDEF6 = "vdef6";

  /** 自定义项7 */
  public static final String VDEF7 = "vdef7";

  /** 自定义项8 */
  public static final String VDEF8 = "vdef8";

  /** 自定义项9 */
  public static final String VDEF9 = "vdef9";

  /** 备注 */
  public static final String VNOTE = "vnote";

  /** 交易类型 */
  public static final String VTRANTYPECODE = "vtrantypecode";

  private static final long serialVersionUID = 3439302740338695425L;

  /** 制单人 getter 方法 */
  public String getBillmaker() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.BILLMAKER);
  }

  /** 是否普通采购 getter 方法 */
  public UFBoolean getBnormpur() {
    return (UFBoolean) this.getAttributeValue(SubcontinFIHeaderVO.BNORMPUR);
  }

  /** 单据类型编码 getter 方法 */
  public String getCbilltypecode() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.CBILLTYPECODE);
  }

  /** 创建时间 getter 方法 */
  public UFDate getCreationtime() {
    return (UFDate) this.getAttributeValue(SubcontinFIHeaderVO.CREATIONTIME);
  }

  /** 创建人 getter 方法 */
  public String getCreator() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.CREATOR);
  }

  /** 交易类型 getter 方法 */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.CTRANTYPEID);
  }

  /** 库管员 getter 方法 */
  public String getCwhsmanagerid() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.CWHSMANAGERID);
  }

  /** 入库日期 getter 方法 */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SubcontinFIHeaderVO.DBILLDATE);
  }

  /** dr getter 方法 */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(SubcontinFIHeaderVO.DR);
  }

  /** 退货标志 getter 方法 */
  public UFBoolean getFreplenishflag() {
    return (UFBoolean) this
        .getAttributeValue(SubcontinFIHeaderVO.FREPLENISHFLAG);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta(PUEntity.SUBCONTIN_H);
    return meta;
  }

  /** 最后修改时间 getter 方法 */
  public UFDate getModifiedtime() {
    return (UFDate) this.getAttributeValue(SubcontinFIHeaderVO.MODIFIEDTIME);
  }

  /** 最后修改人 getter 方法 */
  public String getModifier() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.MODIFIER);
  }

  /** 业务流程 getter 方法 */
  public String getPk_busitype() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.PK_BUSITYPE);
  }

  /** 公司最新版 getter 方法 */
  public String getPk_corp() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.PK_CORP);
  }

  /** 公司 getter 方法 */
  public String getPk_corp_v() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.PK_CORP_V);
  }

  /** 部门原始信息 getter 方法 */
  public String getPk_dept() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.PK_DEPT);
  }

  /** 部门 getter 方法 */
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.PK_DEPT_V);
  }

  /** 集团 getter 方法 */
  public String getPk_group() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.PK_GROUP);
  }

  /** 库存组织 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.PK_ORG);
  }

  /** 库存组织版本 getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.PK_ORG_V);
  }

  /** 业务员 getter 方法 */
  public String getPk_psndoc() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.PK_PSNDOC);
  }

  /** 委托加工入主键 getter 方法 */
  public String getPk_stockps() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.PK_STOCKPS);
  }

  /** 仓库 getter 方法 */
  public String getPk_stordoc() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.PK_STORDOC);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SubcontinFIHeaderVO.TS);
  }

  /** 入库单号 getter 方法 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VBILLCODE);
  }

  /** 自定义项1 getter 方法 */
  public String getVdef1() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VDEF1);
  }

  /** 自定义项10 getter 方法 */
  public String getVdef10() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VDEF10);
  }

  /** 自定义项11 getter 方法 */
  public String getVdef11() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VDEF11);
  }

  /** 自定义项12 getter 方法 */
  public String getVdef12() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VDEF12);
  }

  /** 自定义项13 getter 方法 */
  public String getVdef13() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VDEF13);
  }

  /** 自定义项14 getter 方法 */
  public String getVdef14() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VDEF14);
  }

  /** 自定义项15 getter 方法 */
  public String getVdef15() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VDEF15);
  }

  /** 自定义项16 getter 方法 */
  public String getVdef16() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VDEF16);
  }

  /** 自定义项17 getter 方法 */
  public String getVdef17() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VDEF17);
  }

  /** 自定义项18 getter 方法 */
  public String getVdef18() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VDEF18);
  }

  /** 自定义项19 getter 方法 */
  public String getVdef19() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VDEF19);
  }

  /** 自定义项2 getter 方法 */
  public String getVdef2() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VDEF2);
  }

  /** 自定义项20 getter 方法 */
  public String getVdef20() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VDEF20);
  }

  /** 自定义项3 getter 方法 */
  public String getVdef3() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VDEF3);
  }

  /** 自定义项4 getter 方法 */
  public String getVdef4() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VDEF4);
  }

  /** 自定义项5 getter 方法 */
  public String getVdef5() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VDEF5);
  }

  /** 自定义项6 getter 方法 */
  public String getVdef6() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VDEF6);
  }

  /** 自定义项7 getter 方法 */
  public String getVdef7() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VDEF7);
  }

  /** 自定义项8 getter 方法 */
  public String getVdef8() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VDEF8);
  }

  /** 自定义项9 getter 方法 */
  public String getVdef9() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VDEF9);
  }

  /** 备注 getter 方法 */
  public String getVnote() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VNOTE);
  }

  /** 交易类型 getter 方法 */
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VTRANTYPECODE);
  }

  /** 制单人 setter 方法 */
  public void setBillmaker(String billmaker) {
    this.setAttributeValue(SubcontinFIHeaderVO.BILLMAKER, billmaker);
  }

  /** 是否普通采购 setter 方法 */
  public void setBnormpur(UFBoolean bnormpur) {
    this.setAttributeValue(SubcontinFIHeaderVO.BNORMPUR, bnormpur);
  }

  /** 单据类型编码 setter 方法 */
  public void setCbilltypecode(String cbilltypecode) {
    this.setAttributeValue(SubcontinFIHeaderVO.CBILLTYPECODE, cbilltypecode);
  }

  /** 创建时间 setter 方法 */
  public void setCreationtime(UFDate creationtime) {
    this.setAttributeValue(SubcontinFIHeaderVO.CREATIONTIME, creationtime);
  }

  /** 创建人 setter 方法 */
  public void setCreator(String creator) {
    this.setAttributeValue(SubcontinFIHeaderVO.CREATOR, creator);
  }

  /** 交易类型 setter 方法 */
  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(SubcontinFIHeaderVO.CTRANTYPEID, ctrantypeid);
  }

  /** 库管员 setter 方法 */
  public void setCwhsmanagerid(String cwhsmanagerid) {
    this.setAttributeValue(SubcontinFIHeaderVO.CWHSMANAGERID, cwhsmanagerid);
  }

  /** 入库日期 setter 方法 */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SubcontinFIHeaderVO.DBILLDATE, dbilldate);
  }

  /** dr setter 方法 */
  public void setDr(Integer dr) {
    this.setAttributeValue(SubcontinFIHeaderVO.DR, dr);
  }

  /** 退货标志 setter 方法 */
  public void setFreplenishflag(UFBoolean freplenishflag) {
    this.setAttributeValue(SubcontinFIHeaderVO.FREPLENISHFLAG, freplenishflag);
  }

  /** 最后修改时间 setter 方法 */
  public void setModifiedtime(UFDate modifiedtime) {
    this.setAttributeValue(SubcontinFIHeaderVO.MODIFIEDTIME, modifiedtime);
  }

  /** 最后修改人 setter 方法 */
  public void setModifier(String modifier) {
    this.setAttributeValue(SubcontinFIHeaderVO.MODIFIER, modifier);
  }

  /** 业务流程 setter 方法 */
  public void setPk_busitype(String pk_busitype) {
    this.setAttributeValue(SubcontinFIHeaderVO.PK_BUSITYPE, pk_busitype);
  }

  /** 公司最新版 setter 方法 */
  public void setPk_corp(String pk_corp) {
    this.setAttributeValue(SubcontinFIHeaderVO.PK_CORP, pk_corp);
  }

  /** 公司 setter 方法 */
  public void setPk_corp_v(String pk_corp_v) {
    this.setAttributeValue(SubcontinFIHeaderVO.PK_CORP_V, pk_corp_v);
  }

  /** 部门原始信息 setter 方法 */
  public void setPk_dept(String pk_dept) {
    this.setAttributeValue(SubcontinFIHeaderVO.PK_DEPT, pk_dept);
  }

  /** 部门 setter 方法 */
  public void setPk_dept_v(String pk_dept_v) {
    this.setAttributeValue(SubcontinFIHeaderVO.PK_DEPT_V, pk_dept_v);
  }

  /** 集团 setter 方法 */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SubcontinFIHeaderVO.PK_GROUP, pk_group);
  }

  /** 库存组织 setter 方法 */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SubcontinFIHeaderVO.PK_ORG, pk_org);
  }

  /** 库存组织版本 setter 方法 */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(SubcontinFIHeaderVO.PK_ORG_V, pk_org_v);
  }

  /** 业务员 setter 方法 */
  public void setPk_psndoc(String pk_psndoc) {
    this.setAttributeValue(SubcontinFIHeaderVO.PK_PSNDOC, pk_psndoc);
  }

  /** 委托加工入主键 setter 方法 */
  public void setPk_stockps(String pk_stockps) {
    this.setAttributeValue(SubcontinFIHeaderVO.PK_STOCKPS, pk_stockps);
  }

  /** 仓库 setter 方法 */
  public void setPk_stordoc(String pk_stordoc) {
    this.setAttributeValue(SubcontinFIHeaderVO.PK_STORDOC, pk_stordoc);
  }

  /** ts setter 方法 */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SubcontinFIHeaderVO.TS, ts);
  }

  /** 入库单号 setter 方法 */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(SubcontinFIHeaderVO.VBILLCODE, vbillcode);
  }

  /** 自定义项1 setter 方法 */
  public void setVdef1(String vdef1) {
    this.setAttributeValue(SubcontinFIHeaderVO.VDEF1, vdef1);
  }

  /** 自定义项10 setter 方法 */
  public void setVdef10(String vdef10) {
    this.setAttributeValue(SubcontinFIHeaderVO.VDEF10, vdef10);
  }

  /** 自定义项11 setter 方法 */
  public void setVdef11(String vdef11) {
    this.setAttributeValue(SubcontinFIHeaderVO.VDEF11, vdef11);
  }

  /** 自定义项12 setter 方法 */
  public void setVdef12(String vdef12) {
    this.setAttributeValue(SubcontinFIHeaderVO.VDEF12, vdef12);
  }

  /** 自定义项13 setter 方法 */
  public void setVdef13(String vdef13) {
    this.setAttributeValue(SubcontinFIHeaderVO.VDEF13, vdef13);
  }

  /** 自定义项14 setter 方法 */
  public void setVdef14(String vdef14) {
    this.setAttributeValue(SubcontinFIHeaderVO.VDEF14, vdef14);
  }

  /** 自定义项15 setter 方法 */
  public void setVdef15(String vdef15) {
    this.setAttributeValue(SubcontinFIHeaderVO.VDEF15, vdef15);
  }

  /** 自定义项16 setter 方法 */
  public void setVdef16(String vdef16) {
    this.setAttributeValue(SubcontinFIHeaderVO.VDEF16, vdef16);
  }

  /** 自定义项17 setter 方法 */
  public void setVdef17(String vdef17) {
    this.setAttributeValue(SubcontinFIHeaderVO.VDEF17, vdef17);
  }

  /** 自定义项18 setter 方法 */
  public void setVdef18(String vdef18) {
    this.setAttributeValue(SubcontinFIHeaderVO.VDEF18, vdef18);
  }

  /** 自定义项19 setter 方法 */
  public void setVdef19(String vdef19) {
    this.setAttributeValue(SubcontinFIHeaderVO.VDEF19, vdef19);
  }

  /** 自定义项2 setter 方法 */
  public void setVdef2(String vdef2) {
    this.setAttributeValue(SubcontinFIHeaderVO.VDEF2, vdef2);
  }

  /** 自定义项20 setter 方法 */
  public void setVdef20(String vdef20) {
    this.setAttributeValue(SubcontinFIHeaderVO.VDEF20, vdef20);
  }

  /** 自定义项3 setter 方法 */
  public void setVdef3(String vdef3) {
    this.setAttributeValue(SubcontinFIHeaderVO.VDEF3, vdef3);
  }

  /** 自定义项4 setter 方法 */
  public void setVdef4(String vdef4) {
    this.setAttributeValue(SubcontinFIHeaderVO.VDEF4, vdef4);
  }

  /** 自定义项5 setter 方法 */
  public void setVdef5(String vdef5) {
    this.setAttributeValue(SubcontinFIHeaderVO.VDEF5, vdef5);
  }

  /** 自定义项6 setter 方法 */
  public void setVdef6(String vdef6) {
    this.setAttributeValue(SubcontinFIHeaderVO.VDEF6, vdef6);
  }

  /** 自定义项7 setter 方法 */
  public void setVdef7(String vdef7) {
    this.setAttributeValue(SubcontinFIHeaderVO.VDEF7, vdef7);
  }

  /** 自定义项8 setter 方法 */
  public void setVdef8(String vdef8) {
    this.setAttributeValue(SubcontinFIHeaderVO.VDEF8, vdef8);
  }

  /** 自定义项9 setter 方法 */
  public void setVdef9(String vdef9) {
    this.setAttributeValue(SubcontinFIHeaderVO.VDEF9, vdef9);
  }

  /** 备注 setter 方法 */
  public void setVnote(String vnote) {
    this.setAttributeValue(SubcontinFIHeaderVO.VNOTE, vnote);
  }

  /** 交易类型 setter 方法 */
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(SubcontinFIHeaderVO.VTRANTYPECODE, vtrantypecode);
  }
}
