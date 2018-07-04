/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-25 下午07:52:05
 */
package nc.vo.pu.m4t.entity;

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
 * <li>期初暂估单表头VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-25 下午07:52:05
 */
public class InitialEstHeaderVO extends SuperVO {
  /** 审批人 */
  public static final String APPROVER = "approver";

  /** 制单人 */
  public static final String BILLMAKER = "billmaker";

  /** 是否普通采购 */
  public static final String BNORMPUR = "bnormpur";

  /** 本位币 */
  public static final String CCURRENCYID = "ccurrencyid";

  /** 币种 */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /** 创建时间 */
  public static final String CREATIONTIME = "creationtime";

  /** 创建人 */
  public static final String CREATOR = "creator";

  /** 交易类型 */
  public static final String CTRANTYPEID = "ctrantypeid";

  /** 单据日期 */
  public static final String DBILLDATE = "dbilldate";

  /** 制单日期 */
  public static final String DMAKEDATE = "dmakedate";

  /** dr */
  public static final String DR = "dr";

  /** 单据状态 */
  public static final String FBILLSTATUS = "fbillstatus";

  /** 最后修改时间 */
  public static final String MODIFIEDTIME = "modifiedtime";

  /** 最后修改人 */
  public static final String MODIFIER = "modifier";

  /** 折本汇率 */
  public static final String NEXCHANGERATE = "nexchangerate";

  /** 总数量 */
  public static final String NTOTALASTNUM = "ntotalastnum";

  /** 总价税合计 */
  public static final String NTOTALORIGMNY = "ntotalorigmny";

  /** 业务员 */
  public static final String PK_BIZPSN = "pk_bizpsn";

  /** 业务流程 */
  public static final String PK_BUSITYPE = "pk_busitype";

  /** 成本域 */
  public static final String PK_COSTREGION = "pk_costregion";

  /** 采购部门原始版本 */
  public static final String PK_DEPT = "pk_dept";

  /** 采购部门 */
  public static final String PK_DEPT_V = "pk_dept_v";

  /** 所属集团 */
  public static final String PK_GROUP = "pk_group";

  /** 期初暂估单 */
  public static final String PK_INITIALEST = "pk_initialest";

  /** 开票供应商 */
  public static final String PK_INVCSUPLLIER = "pk_invcsupllier";

  /** 保管员 */
  public static final String PK_MANAGEPSN = "pk_managepsn";

  /** 财务组织 */
  public static final String PK_ORG = "pk_org";

  /** 财务组织版本 */
  public static final String PK_ORG_V = "pk_org_v";

  /** 采购组织最新版本 */
  public static final String PK_PURCHASEORG = "pk_purchaseorg";

  /** 采购组织 */
  public static final String PK_PURCHASEORG_V = "pk_purchaseorg_v";

  /** 库存组织 */
  public static final String PK_STOCKORG = "pk_stockorg";

  /** 库存组织版本 */
  public static final String PK_STOCKORG_V = "pk_stockorg_v";

  /** 仓库 */
  public static final String PK_STORDOC = "pk_stordoc";

  /** 供应商 */
  public static final String PK_SUPPLIER = "pk_supplier";

  /** 审批日期 */
  public static final String TAUDITTIME = "taudittime";

  /** ts */
  public static final String TS = "ts";

  /** 期初暂估单号 */
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
  public static final String VMEMO = "vmemo";

  /** 期初暂估类型 */
  public static final String VTRANTYPECODE = "vtrantypecode";

  /**
   * 
   */
  private static final long serialVersionUID = 1833309499875207815L;

  /** 审批人 getter 方法 */
  public String getApprover() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.APPROVER);
  }

  /** 制单人 getter 方法 */
  public String getBillmaker() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.BILLMAKER);
  }

  /** 是否普通采购 getter 方法 */
  public UFBoolean getBnormpur() {
    return (UFBoolean) this.getAttributeValue(InitialEstHeaderVO.BNORMPUR);
  }

  /** 本位币 getter 方法 */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.CCURRENCYID);
  }

  /** 币种 getter 方法 */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.CORIGCURRENCYID);
  }

  /** 创建时间 getter 方法 */
  public UFDateTime getCreationtime() {
    return (UFDateTime) this.getAttributeValue(InitialEstHeaderVO.CREATIONTIME);
  }

  /** 创建人 getter 方法 */
  public String getCreator() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.CREATOR);
  }

  /** 交易类型 getter 方法 */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.CTRANTYPEID);
  }

  /** 单据日期 getter 方法 */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(InitialEstHeaderVO.DBILLDATE);
  }

  /** 制单日期 **/
  public UFDate getDmakedate() {
    return (UFDate) this.getAttributeValue(InitialEstHeaderVO.DMAKEDATE);
  }

  /** dr getter 方法 */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(InitialEstHeaderVO.DR);
  }

  /** 单据状态 getter 方法 */
  public Integer getFbillstatus() {
    return (Integer) this.getAttributeValue(InitialEstHeaderVO.FBILLSTATUS);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.SuperVO#getMetaData()
   */
  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.M4T_H);
  }

  /** 最后修改时间 getter 方法 */
  public UFDateTime getModifiedtime() {
    return (UFDateTime) this.getAttributeValue(InitialEstHeaderVO.MODIFIEDTIME);
  }

  /** 最后修改人 getter 方法 */
  public String getModifier() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.MODIFIER);
  }

  /** 折本汇率 getter 方法 */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(InitialEstHeaderVO.NEXCHANGERATE);
  }

  /** 总数量 getter 方法 */
  public UFDouble getNtotalastnum() {
    return (UFDouble) this.getAttributeValue(InitialEstHeaderVO.NTOTALASTNUM);
  }

  /** 总价税合计 getter 方法 */
  public UFDouble getNtotalorigmny() {
    return (UFDouble) this.getAttributeValue(InitialEstHeaderVO.NTOTALORIGMNY);
  }

  /** 业务员 getter 方法 */
  public String getPk_bizpsn() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_BIZPSN);
  }

  /** 业务流程 getter 方法 */
  public String getPk_busitype() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_BUSITYPE);
  }

  /** 成本域 getter 方法 */
  public String getPk_costregion() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_COSTREGION);
  }

  /** 采购部门原始版本 getter 方法 */
  public String getPk_dept() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_DEPT);
  }

  /** 采购部门 getter 方法 */
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_DEPT_V);
  }

  /** 所属集团 getter 方法 */
  public String getPk_group() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_GROUP);
  }

  /** 期初暂估单 getter 方法 */
  public String getPk_initialest() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_INITIALEST);
  }

  /** 开票供应商 getter 方法 */
  public String getPk_invcsupllier() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_INVCSUPLLIER);
  }

  /** 保管员 getter 方法 */
  public String getPk_managepsn() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_MANAGEPSN);
  }

  /** 财务组织 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_ORG);
  }

  /** 财务组织版本 getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_ORG_V);
  }

  /** 采购组织最新版本 getter 方法 */
  public String getPk_purchaseorg() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_PURCHASEORG);
  }

  /** 采购组织 getter 方法 */
  public String getPk_purchaseorg_v() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_PURCHASEORG_V);
  }

  /** 库存组织 getter 方法 */
  public String getPk_stockorg() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_STOCKORG);
  }

  /** 库存组织版本 getter 方法 */
  public String getPk_stockorg_v() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_STOCKORG_V);
  }

  /** 仓库 getter 方法 */
  public String getPk_stordoc() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_STORDOC);
  }

  /** 供应商 getter 方法 */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_SUPPLIER);
  }

  /** 审批日期 getter 方法 */
  public UFDate getTaudittime() {
    return (UFDate) this.getAttributeValue(InitialEstHeaderVO.TAUDITTIME);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(InitialEstHeaderVO.TS);
  }

  /** 期初暂估单号 getter 方法 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VBILLCODE);
  }

  /** 自定义项1 getter 方法 */
  public String getVdef1() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF1);
  }

  /** 自定义项10 getter 方法 */
  public String getVdef10() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF10);
  }

  /** 自定义项11 getter 方法 */
  public String getVdef11() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF11);
  }

  /** 自定义项12 getter 方法 */
  public String getVdef12() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF12);
  }

  /** 自定义项13 getter 方法 */
  public String getVdef13() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF13);
  }

  /** 自定义项14 getter 方法 */
  public String getVdef14() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF14);
  }

  /** 自定义项15 getter 方法 */
  public String getVdef15() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF15);
  }

  /** 自定义项16 getter 方法 */
  public String getVdef16() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF16);
  }

  /** 自定义项17 getter 方法 */
  public String getVdef17() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF17);
  }

  /** 自定义项18 getter 方法 */
  public String getVdef18() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF18);
  }

  /** 自定义项19 getter 方法 */
  public String getVdef19() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF19);
  }

  /** 自定义项2 getter 方法 */
  public String getVdef2() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF2);
  }

  /** 自定义项20 getter 方法 */
  public String getVdef20() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF20);
  }

  /** 自定义项3 getter 方法 */
  public String getVdef3() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF3);
  }

  /** 自定义项4 getter 方法 */
  public String getVdef4() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF4);
  }

  /** 自定义项5 getter 方法 */
  public String getVdef5() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF5);
  }

  /** 自定义项6 getter 方法 */
  public String getVdef6() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF6);
  }

  /** 自定义项7 getter 方法 */
  public String getVdef7() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF7);
  }

  /** 自定义项8 getter 方法 */
  public String getVdef8() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF8);
  }

  /** 自定义项9 getter 方法 */
  public String getVdef9() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF9);
  }

  /** 备注 getter 方法 */
  public String getVmemo() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VMEMO);
  }

  /** 期初暂估类型 getter 方法 */
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VTRANTYPECODE);
  }

  /** 审批人 setter 方法 */
  public void setApprover(String approver) {
    this.setAttributeValue(InitialEstHeaderVO.APPROVER, approver);
  }

  /** 制单人 setter 方法 */
  public void setBillmaker(String billmaker) {
    this.setAttributeValue(InitialEstHeaderVO.BILLMAKER, billmaker);
  }

  /** 是否普通采购 setter 方法 */
  public void setBnormpur(UFBoolean bnormpur) {
    this.setAttributeValue(InitialEstHeaderVO.BNORMPUR, bnormpur);
  }

  /** 本位币 setter 方法 */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(InitialEstHeaderVO.CCURRENCYID, ccurrencyid);
  }

  /** 币种 setter 方法 */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(InitialEstHeaderVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /** 创建时间 setter 方法 */
  public void setCreationtime(UFDateTime creationtime) {
    this.setAttributeValue(InitialEstHeaderVO.CREATIONTIME, creationtime);
  }

  /** 创建人 setter 方法 */
  public void setCreator(String creator) {
    this.setAttributeValue(InitialEstHeaderVO.CREATOR, creator);
  }

  /** 交易类型 setter 方法 */
  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(InitialEstHeaderVO.CTRANTYPEID, ctrantypeid);
  }

  /** 单据日期 setter 方法 */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(InitialEstHeaderVO.DBILLDATE, dbilldate);
  }

  /** 制单日期 **/
  public void setDmakedate(UFDate dmakedate) {
    this.setAttributeValue(InitialEstHeaderVO.DMAKEDATE, dmakedate);
  }

  /** dr setter 方法 */
  public void setDr(Integer dr) {
    this.setAttributeValue(InitialEstHeaderVO.DR, dr);
  }

  /** 单据状态 setter 方法 */
  public void setFbillstatus(Integer fbillstatus) {
    this.setAttributeValue(InitialEstHeaderVO.FBILLSTATUS, fbillstatus);
  }

  /** 最后修改时间 setter 方法 */
  public void setModifiedtime(UFDateTime modifiedtime) {
    this.setAttributeValue(InitialEstHeaderVO.MODIFIEDTIME, modifiedtime);
  }

  /** 最后修改人 setter 方法 */
  public void setModifier(String modifier) {
    this.setAttributeValue(InitialEstHeaderVO.MODIFIER, modifier);
  }

  /** 折本汇率 setter 方法 */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(InitialEstHeaderVO.NEXCHANGERATE, nexchangerate);
  }

  /** 总数量 setter 方法 */
  public void setNtotalastnum(UFDouble ntotalastnum) {
    this.setAttributeValue(InitialEstHeaderVO.NTOTALASTNUM, ntotalastnum);
  }

  /** 总价税合计 setter 方法 */
  public void setNtotalorigmny(UFDouble ntotalorigmny) {
    this.setAttributeValue(InitialEstHeaderVO.NTOTALORIGMNY, ntotalorigmny);
  }

  /** 业务员 setter 方法 */
  public void setPk_bizpsn(String pk_bizpsn) {
    this.setAttributeValue(InitialEstHeaderVO.PK_BIZPSN, pk_bizpsn);
  }

  /** 业务流程 setter 方法 */
  public void setPk_busitype(String pk_busitype) {
    this.setAttributeValue(InitialEstHeaderVO.PK_BUSITYPE, pk_busitype);
  }

  /** 成本域 setter 方法 */
  public void setPk_costregion(String pk_costregion) {
    this.setAttributeValue(InitialEstHeaderVO.PK_COSTREGION, pk_costregion);
  }

  /** 采购部门原始版本 setter 方法 */
  public void setPk_dept(String pk_dept) {
    this.setAttributeValue(InitialEstHeaderVO.PK_DEPT, pk_dept);
  }

  /** 采购部门 setter 方法 */
  public void setPk_dept_v(String pk_dept_v) {
    this.setAttributeValue(InitialEstHeaderVO.PK_DEPT_V, pk_dept_v);
  }

  /** 所属集团 setter 方法 */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(InitialEstHeaderVO.PK_GROUP, pk_group);
  }

  /** 期初暂估单 setter 方法 */
  public void setPk_initialest(String pk_initialest) {
    this.setAttributeValue(InitialEstHeaderVO.PK_INITIALEST, pk_initialest);
  }

  /** 开票供应商 setter 方法 */
  public void setPk_invcsupllier(String pk_invcsupllier) {
    this.setAttributeValue(InitialEstHeaderVO.PK_INVCSUPLLIER, pk_invcsupllier);
  }

  /** 保管员 setter 方法 */
  public void setPk_managepsn(String pk_managepsn) {
    this.setAttributeValue(InitialEstHeaderVO.PK_MANAGEPSN, pk_managepsn);
  }

  /** 财务组织 setter 方法 */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(InitialEstHeaderVO.PK_ORG, pk_org);
  }

  /** 财务组织版本 setter 方法 */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(InitialEstHeaderVO.PK_ORG_V, pk_org_v);
  }

  /** 采购组织最新版本 setter 方法 */
  public void setPk_purchaseorg(String pk_purchaseorg) {
    this.setAttributeValue(InitialEstHeaderVO.PK_PURCHASEORG, pk_purchaseorg);
  }

  /** 采购组织 setter 方法 */
  public void setPk_purchaseorg_v(String pk_purchaseorg_v) {
    this.setAttributeValue(InitialEstHeaderVO.PK_PURCHASEORG_V,
        pk_purchaseorg_v);
  }

  /** 库存组织 setter 方法 */
  public void setPk_stockorg(String pk_stockorg) {
    this.setAttributeValue(InitialEstHeaderVO.PK_STOCKORG, pk_stockorg);
  }

  /** 库存组织版本 setter 方法 */
  public void setPk_stockorg_v(String pk_stockorg_v) {
    this.setAttributeValue(InitialEstHeaderVO.PK_STOCKORG_V, pk_stockorg_v);
  }

  /** 仓库 setter 方法 */
  public void setPk_stordoc(String pk_stordoc) {
    this.setAttributeValue(InitialEstHeaderVO.PK_STORDOC, pk_stordoc);
  }

  /** 供应商 setter 方法 */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(InitialEstHeaderVO.PK_SUPPLIER, pk_supplier);
  }

  /** 审批日期 setter 方法 */
  public void setTaudittime(UFDate taudittime) {
    this.setAttributeValue(InitialEstHeaderVO.TAUDITTIME, taudittime);
  }

  /** ts setter 方法 */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(InitialEstHeaderVO.TS, ts);
  }

  /** 期初暂估单号 setter 方法 */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(InitialEstHeaderVO.VBILLCODE, vbillcode);
  }

  /** 自定义项1 setter 方法 */
  public void setVdef1(String vdef1) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF1, vdef1);
  }

  /** 自定义项10 setter 方法 */
  public void setVdef10(String vdef10) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF10, vdef10);
  }

  /** 自定义项11 setter 方法 */
  public void setVdef11(String vdef11) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF11, vdef11);
  }

  /** 自定义项12 setter 方法 */
  public void setVdef12(String vdef12) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF12, vdef12);
  }

  /** 自定义项13 setter 方法 */
  public void setVdef13(String vdef13) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF13, vdef13);
  }

  /** 自定义项14 setter 方法 */
  public void setVdef14(String vdef14) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF14, vdef14);
  }

  /** 自定义项15 setter 方法 */
  public void setVdef15(String vdef15) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF15, vdef15);
  }

  /** 自定义项16 setter 方法 */
  public void setVdef16(String vdef16) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF16, vdef16);
  }

  /** 自定义项17 setter 方法 */
  public void setVdef17(String vdef17) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF17, vdef17);
  }

  /** 自定义项18 setter 方法 */
  public void setVdef18(String vdef18) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF18, vdef18);
  }

  /** 自定义项19 setter 方法 */
  public void setVdef19(String vdef19) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF19, vdef19);
  }

  /** 自定义项2 setter 方法 */
  public void setVdef2(String vdef2) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF2, vdef2);
  }

  /** 自定义项20 setter 方法 */
  public void setVdef20(String vdef20) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF20, vdef20);
  }

  /** 自定义项3 setter 方法 */
  public void setVdef3(String vdef3) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF3, vdef3);
  }

  /** 自定义项4 setter 方法 */
  public void setVdef4(String vdef4) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF4, vdef4);
  }

  /** 自定义项5 setter 方法 */
  public void setVdef5(String vdef5) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF5, vdef5);
  }

  /** 自定义项6 setter 方法 */
  public void setVdef6(String vdef6) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF6, vdef6);
  }

  /** 自定义项7 setter 方法 */
  public void setVdef7(String vdef7) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF7, vdef7);
  }

  /** 自定义项8 setter 方法 */
  public void setVdef8(String vdef8) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF8, vdef8);
  }

  /** 自定义项9 setter 方法 */
  public void setVdef9(String vdef9) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF9, vdef9);
  }

  /** 备注 setter 方法 */
  public void setVmemo(String vmemo) {
    this.setAttributeValue(InitialEstHeaderVO.VMEMO, vmemo);
  }

  /** 期初暂估类型 setter 方法 */
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(InitialEstHeaderVO.VTRANTYPECODE, vtrantypecode);
  }

}
