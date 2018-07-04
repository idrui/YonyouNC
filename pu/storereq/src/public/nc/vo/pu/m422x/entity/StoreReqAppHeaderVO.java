/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-18 下午07:27:39
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
 * <li>物资需求申请单表头
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-18 下午07:27:39
 */
public class StoreReqAppHeaderVO extends SuperVO {
  /** 审批人 */
  public static final String APPROVER = "approver";

  /** 制单人 */
  public static final String BILLMAKER = "billmaker";

  /** 是否紧急 */
  public static final String BURGENCY = "burgency";

  /** 创建时间 */
  public static final String CREATIONTIME = "creationtime";

  /** 创建人 */
  public static final String CREATOR = "creator";

  /** 申请日期 */
  public static final String DBILLDATE = "dbilldate";

  /** 制单日期 */
  public static final String DMAKEDATE = "dmakedate";

  /** dr */
  public static final String DR = "dr";

  /** 单据状态 */
  public static final String FBILLSTATUS = "fbillstatus";

  /** 打印次数 */
  public static final String IPRINTCOUNT = "iprintcount";

  /** 最后修改时间 */
  public static final String MODIFIEDTIME = "modifiedtime";

  /** 最后修改人 */
  public static final String MODIFIER = "modifier";

  /** 总数量 */
  public static final String NTOTALASTNUM = "ntotalastnum";

  /** 价税合计 */
  public static final String NTOTALORIGMNY = "ntotalorigmny";

  /** 申请部门最新版本 */
  public static final String PK_APPDEPTH = "pk_appdepth";

  /** 申请部门 */
  public static final String PK_APPDEPTH_V = "pk_appdepth_v";

  /** 申请人 */
  public static final String PK_APPPSNH = "pk_apppsnh";

  /** 集团 */
  public static final String PK_GROUP = "pk_group";

  /** 库存组织最新版本 */
  public static final String PK_ORG = "pk_org";

  /** 库存组织 */
  public static final String PK_ORG_V = "pk_org_v";

  /** 项目 */
  public static final String PK_PROJECT = "pk_project";

  /** 物资需求申请单 */
  public static final String PK_STOREREQ = "pk_storereq";

  /** 审批时间 */
  public static final String TAUDITTIME = "taudittime";

  /** ts */
  public static final String TS = "ts";

  /** 申请单号 */
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

  /** 单据类型 */
  public static final String VTRANTYPECODE = "vtrantypecode";

  /** 需求类型 */
  public static final String FREQTYPEFLAG = "freqtypeflag";

  /** 物资需求申请类型 */
  public static final String CTRANTYPEID = "ctrantypeid";

  private static final long serialVersionUID = 5630575391170517509L;

  /** 审批人 getter 方法 */
  public String getApprover() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.APPROVER);
  }

  /** 制单人 getter 方法 */
  public String getBillmaker() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.BILLMAKER);
  }

  /** 是否紧急 getter 方法 */
  public UFBoolean getBurgency() {
    return (UFBoolean) this.getAttributeValue(StoreReqAppHeaderVO.BURGENCY);
  }

  /** 创建时间 getter 方法 */
  public UFDateTime getCreationtime() {
    return (UFDateTime) this
        .getAttributeValue(StoreReqAppHeaderVO.CREATIONTIME);
  }

  /** 创建人 getter 方法 */
  public String getCreator() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.CREATOR);
  }

  /** 申请日期 getter 方法 */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(StoreReqAppHeaderVO.DBILLDATE);
  }

  /** 制单日期 **/
  public UFDate getDmakedate() {
    return (UFDate) this.getAttributeValue(StoreReqAppHeaderVO.DMAKEDATE);
  }

  /** dr getter 方法 */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(StoreReqAppHeaderVO.DR);
  }

  /** 单据状态 getter 方法 */
  public Integer getFbillstatus() {
    return (Integer) this.getAttributeValue(StoreReqAppHeaderVO.FBILLSTATUS);
  }

  /** 打印次数 getter 方法 */
  public Integer getIprintcount() {
    return (Integer) this.getAttributeValue(StoreReqAppHeaderVO.IPRINTCOUNT);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.SuperVO#getMetaData()
   */
  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.M422X_H);
  }

  /** 最后修改时间 getter 方法 */
  public UFDateTime getModifiedtime() {
    return (UFDateTime) this
        .getAttributeValue(StoreReqAppHeaderVO.MODIFIEDTIME);
  }

  /** 最后修改人 getter 方法 */
  public String getModifier() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.MODIFIER);
  }

  /** 总数量 getter 方法 */
  public UFDouble getNtotalastnum() {
    return (UFDouble) this.getAttributeValue(StoreReqAppHeaderVO.NTOTALASTNUM);
  }

  /** 价税合计 getter 方法 */
  public UFDouble getNtotalorigmny() {
    return (UFDouble) this.getAttributeValue(StoreReqAppHeaderVO.NTOTALORIGMNY);
  }

  /** 申请部门最新版本 getter 方法 */
  public String getPk_appdepth() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.PK_APPDEPTH);
  }

  /** 申请部门 getter 方法 */
  public String getPk_appdepth_v() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.PK_APPDEPTH_V);
  }

  /** 申请人 getter 方法 */
  public String getPk_apppsnh() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.PK_APPPSNH);
  }

  /** 集团 getter 方法 */
  public String getPk_group() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.PK_GROUP);
  }

  /** 库存组织最新版本 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.PK_ORG);
  }

  /** 库存组织 getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.PK_ORG_V);
  }

  /** 项目 getter 方法 */
  public String getPk_project() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.PK_PROJECT);
  }

  /** 物资需求申请单 getter 方法 */
  public String getPk_storereq() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.PK_STOREREQ);
  }

  /** 审批时间 getter 方法 */
  public UFDate getTaudittime() {
    return (UFDate) this.getAttributeValue(StoreReqAppHeaderVO.TAUDITTIME);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(StoreReqAppHeaderVO.TS);
  }

  /** 申请单号 getter 方法 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VBILLCODE);
  }

  /** 自定义项1 getter 方法 */
  public String getVdef1() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF1);
  }

  /** 自定义项10 getter 方法 */
  public String getVdef10() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF10);
  }

  /** 自定义项11 getter 方法 */
  public String getVdef11() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF11);
  }

  /** 自定义项12 getter 方法 */
  public String getVdef12() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF12);
  }

  /** 自定义项13 getter 方法 */
  public String getVdef13() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF13);
  }

  /** 自定义项14 getter 方法 */
  public String getVdef14() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF14);
  }

  /** 自定义项15 getter 方法 */
  public String getVdef15() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF15);
  }

  /** 自定义项16 getter 方法 */
  public String getVdef16() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF16);
  }

  /** 自定义项17 getter 方法 */
  public String getVdef17() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF17);
  }

  /** 自定义项18 getter 方法 */
  public String getVdef18() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF18);
  }

  /** 自定义项19 getter 方法 */
  public String getVdef19() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF19);
  }

  /** 自定义项2 getter 方法 */
  public String getVdef2() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF2);
  }

  /** 自定义项20 getter 方法 */
  public String getVdef20() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF20);
  }

  /** 自定义项3 getter 方法 */
  public String getVdef3() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF3);
  }

  /** 自定义项4 getter 方法 */
  public String getVdef4() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF4);
  }

  /** 自定义项5 getter 方法 */
  public String getVdef5() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF5);
  }

  /** 自定义项6 getter 方法 */
  public String getVdef6() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF6);
  }

  /** 自定义项7 getter 方法 */
  public String getVdef7() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF7);
  }

  /** 自定义项8 getter 方法 */
  public String getVdef8() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF8);
  }

  /** 自定义项9 getter 方法 */
  public String getVdef9() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF9);
  }

  /** 备注 getter 方法 */
  public String getVmemo() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VMEMO);
  }

  /** 单据类型 getter 方法 */
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VTRANTYPECODE);
  }

  /** 需求类型 getter 方法 */
  public Integer getFreqtypeflag() {
    return (Integer) this.getAttributeValue(StoreReqAppHeaderVO.FREQTYPEFLAG);
  }

  /** 物资需求申请类型 getter 方法 */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.CTRANTYPEID);
  }

  /** 审批人 setter 方法 */
  public void setApprover(String approver) {
    this.setAttributeValue(StoreReqAppHeaderVO.APPROVER, approver);
  }

  /** 制单人 setter 方法 */
  public void setBillmaker(String billmaker) {
    this.setAttributeValue(StoreReqAppHeaderVO.BILLMAKER, billmaker);
  }

  /** 是否紧急 setter 方法 */
  public void setBurgency(UFBoolean burgency) {
    this.setAttributeValue(StoreReqAppHeaderVO.BURGENCY, burgency);
  }

  /** 创建时间 setter 方法 */
  public void setCreationtime(UFDateTime creationtime) {
    this.setAttributeValue(StoreReqAppHeaderVO.CREATIONTIME, creationtime);
  }

  /** 创建人 setter 方法 */
  public void setCreator(String creator) {
    this.setAttributeValue(StoreReqAppHeaderVO.CREATOR, creator);
  }

  /** 申请日期 setter 方法 */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(StoreReqAppHeaderVO.DBILLDATE, dbilldate);
  }

  /** 制单日期 **/
  public void setDmakedate(UFDate dmakedate) {
    this.setAttributeValue(StoreReqAppHeaderVO.DMAKEDATE, dmakedate);
  }

  /** dr setter 方法 */
  public void setDr(Integer dr) {
    this.setAttributeValue(StoreReqAppHeaderVO.DR, dr);
  }

  /** 单据状态 setter 方法 */
  public void setFbillstatus(Integer fbillstatus) {
    this.setAttributeValue(StoreReqAppHeaderVO.FBILLSTATUS, fbillstatus);
  }

  /** 打印次数 setter 方法 */
  public void setIprintcount(Integer iprintcount) {
    this.setAttributeValue(StoreReqAppHeaderVO.IPRINTCOUNT, iprintcount);
  }

  /** 最后修改时间 setter 方法 */
  public void setModifiedtime(UFDateTime modifiedtime) {
    this.setAttributeValue(StoreReqAppHeaderVO.MODIFIEDTIME, modifiedtime);
  }

  /** 最后修改人 setter 方法 */
  public void setModifier(String modifier) {
    this.setAttributeValue(StoreReqAppHeaderVO.MODIFIER, modifier);
  }

  /** 总数量 setter 方法 */
  public void setNtotalastnum(UFDouble ntotalastnum) {
    this.setAttributeValue(StoreReqAppHeaderVO.NTOTALASTNUM, ntotalastnum);
  }

  /** 价税合计 setter 方法 */
  public void setNtotalorigmny(UFDouble ntotalorigmny) {
    this.setAttributeValue(StoreReqAppHeaderVO.NTOTALORIGMNY, ntotalorigmny);
  }

  /** 申请部门最新版本 setter 方法 */
  public void setPk_appdepth(String pk_appdepth) {
    this.setAttributeValue(StoreReqAppHeaderVO.PK_APPDEPTH, pk_appdepth);
  }

  /** 申请部门 setter 方法 */
  public void setPk_appdepth_v(String pk_appdepth_v) {
    this.setAttributeValue(StoreReqAppHeaderVO.PK_APPDEPTH_V, pk_appdepth_v);
  }

  /** 申请人 setter 方法 */
  public void setPk_apppsnh(String pk_apppsnh) {
    this.setAttributeValue(StoreReqAppHeaderVO.PK_APPPSNH, pk_apppsnh);
  }

  /** 集团 setter 方法 */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(StoreReqAppHeaderVO.PK_GROUP, pk_group);
  }

  /** 库存组织最新版本 setter 方法 */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(StoreReqAppHeaderVO.PK_ORG, pk_org);
  }

  /** 库存组织 setter 方法 */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(StoreReqAppHeaderVO.PK_ORG_V, pk_org_v);
  }

  /** 项目 setter 方法 */
  public void setPk_project(String pk_project) {
    this.setAttributeValue(StoreReqAppHeaderVO.PK_PROJECT, pk_project);
  }

  /** 物资需求申请单 setter 方法 */
  public void setPk_storereq(String pk_storereq) {
    this.setAttributeValue(StoreReqAppHeaderVO.PK_STOREREQ, pk_storereq);
  }

  /** 审批时间 setter 方法 */
  public void setTaudittime(UFDate taudittime) {
    this.setAttributeValue(StoreReqAppHeaderVO.TAUDITTIME, taudittime);
  }

  /** ts setter 方法 */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(StoreReqAppHeaderVO.TS, ts);
  }

  /** 申请单号 setter 方法 */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(StoreReqAppHeaderVO.VBILLCODE, vbillcode);
  }

  /** 自定义项1 setter 方法 */
  public void setVdef1(String vdef1) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF1, vdef1);
  }

  /** 自定义项10 setter 方法 */
  public void setVdef10(String vdef10) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF10, vdef10);
  }

  /** 自定义项11 setter 方法 */
  public void setVdef11(String vdef11) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF11, vdef11);
  }

  /** 自定义项12 setter 方法 */
  public void setVdef12(String vdef12) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF12, vdef12);
  }

  /** 自定义项13 setter 方法 */
  public void setVdef13(String vdef13) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF13, vdef13);
  }

  /** 自定义项14 setter 方法 */
  public void setVdef14(String vdef14) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF14, vdef14);
  }

  /** 自定义项15 setter 方法 */
  public void setVdef15(String vdef15) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF15, vdef15);
  }

  /** 自定义项16 setter 方法 */
  public void setVdef16(String vdef16) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF16, vdef16);
  }

  /** 自定义项17 setter 方法 */
  public void setVdef17(String vdef17) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF17, vdef17);
  }

  /** 自定义项18 setter 方法 */
  public void setVdef18(String vdef18) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF18, vdef18);
  }

  /** 自定义项19 setter 方法 */
  public void setVdef19(String vdef19) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF19, vdef19);
  }

  /** 自定义项2 setter 方法 */
  public void setVdef2(String vdef2) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF2, vdef2);
  }

  /** 自定义项20 setter 方法 */
  public void setVdef20(String vdef20) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF20, vdef20);
  }

  /** 自定义项3 setter 方法 */
  public void setVdef3(String vdef3) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF3, vdef3);
  }

  /** 自定义项4 setter 方法 */
  public void setVdef4(String vdef4) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF4, vdef4);
  }

  /** 自定义项5 setter 方法 */
  public void setVdef5(String vdef5) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF5, vdef5);
  }

  /** 自定义项6 setter 方法 */
  public void setVdef6(String vdef6) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF6, vdef6);
  }

  /** 自定义项7 setter 方法 */
  public void setVdef7(String vdef7) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF7, vdef7);
  }

  /** 自定义项8 setter 方法 */
  public void setVdef8(String vdef8) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF8, vdef8);
  }

  /** 自定义项9 setter 方法 */
  public void setVdef9(String vdef9) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF9, vdef9);
  }

  /** 备注 setter 方法 */
  public void setVmemo(String vmemo) {
    this.setAttributeValue(StoreReqAppHeaderVO.VMEMO, vmemo);
  }

  /** 单据类型 setter 方法 */
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(StoreReqAppHeaderVO.VTRANTYPECODE, vtrantypecode);
  }

  /** 需求类型 setter 方法 */
  public void setFreqtypeflag(Integer freqtypeflag) {
    this.setAttributeValue(StoreReqAppHeaderVO.FREQTYPEFLAG, freqtypeflag);
  }

  /** 物资需求申请类型 setter 方法 */
  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(StoreReqAppHeaderVO.CTRANTYPEID, ctrantypeid);
  }
}
