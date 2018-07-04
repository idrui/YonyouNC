package nc.vo.pu.m21.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <p>
 * <b>采购订单主表VO</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author donggq
 * @time 2009-6-16 下午07:02:15
 */
public class OrderHeaderVO extends nc.vo.pub.SuperVO {
  /** 审批人 */
  public static final String APPROVER = "approver";

  /** 已协同生成销售订单 */
  public static final String BCOOPTOSO = "bcooptoso";

  /** 是否直运采购 */
  public static final String BDIRECT = "bdirect";

  /** 最终关闭 */
  public static final String BFINALCLOSE = "bfinalclose";

  /** 冻结 */
  public static final String BFROZEN = "bfrozen";

  /** 制单人 */
  public static final String BILLMAKER = "billmaker";

  /** 最新版本 */
  public static final String BISLATEST = "bislatest";

  /** 补货 */
  public static final String BISREPLENISH = "bisreplenish";

  /** 发布 */
  public static final String BPUBLISH = "bpublish";

  /** 退货/库基于原订单补货 */
  public static final String BREFWHENRETURN = "brefwhenreturn";

  /** 曾经发布 */
  public static final String BRELEASEDOVER = "breleasedover";

  /** 退货 */
  public static final String BRETURN = "breturn";

  /** 由销售订单协同生成 */
  public static final String BSOCOOPTOME = "bsocooptome";

  /** 合同附件 */
  public static final String CCONTRACTTEXTPATH = "ccontracttextpath";

  /** 采购员 */
  public static final String CEMPLOYEEID = "cemployeeid";

  /** 币种 */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /** 创建时间 */
  public static final String CREATIONTIME = "creationtime";

  /** 创建人 */
  public static final String CREATOR = "creator";

  /** 修订人 */
  public static final String CREVISEPSN = "crevisepsn";

  /** 贸易术语 */
  public static final String CTRADEWORDID = "ctradewordid";

  /** 订单类型 */
  public static final String CTRANTYPEID = "ctrantypeid";

  /** 订单日期 */
  public static final String DBILLDATE = "dbilldate";

  /** 最终关闭日期 */
  public static final String DCLOSEDATE = "dclosedate";

  /** 制单日期 */
  public static final String DMAKEDATE = "dmakedate";

  /** dr */
  public static final String DR = "dr";

  /** 整单扣税类别 */
  public static final String FHTAXTYPEFLAG = "fhtaxtypeflag";

  /** 单据状态 */
  public static final String FORDERSTATUS = "forderstatus";

  /** 打印次数 */
  public static final String IPRINTCOUNT = "iprintcount";

  /** 响应状态 */
  public static final String IRESPSTATUS = "irespstatus";

  /** 最后修改时间 */
  public static final String MODIFIEDTIME = "modifiedtime";

  /** 最后修改人 */
  public static final String MODIFIER = "modifier";

  /** 整单税率 */
  public static final String NHTAXRATE = "nhtaxrate";

  /** 预付款限额 */
  public static final String NORGPREPAYLIMIT = "norgprepaylimit";

  /** 总数量 */
  public static final String NTOTALASTNUM = "ntotalastnum";

  /** 价税合计 */
  public static final String NTOTALORIGMNY = "ntotalorigmny";

  /** 总件数 */
  public static final String NTOTALPIECE = "ntotalpiece";

  /** 总体积 */
  public static final String NTOTALVOLUME = "ntotalvolume";

  /** 总重量 */
  public static final String NTOTALWEIGHT = "ntotalweight";

  /** 版本号 */
  public static final String NVERSION = "nversion";
  
  /** 结算方式 */
  public static final String PK_BALATYPE = "pk_balatype";

  /** 开户银行 */
  public static final String PK_BANKDOC = "pk_bankdoc";

  /** 业务流程 */
  public static final String PK_BUSITYPE = "pk_busitype";

  /** 供应商发货地址 */
  public static final String PK_DELIVERADD = "pk_deliveradd";

  /** 采购部门最新版本 */
  public static final String PK_DEPT = "pk_dept";

  /** 采购部门 */
  public static final String PK_DEPT_V = "pk_dept_v";

  /** 散户 */
  public static final String PK_FREECUST = "pk_freecust";

  /** 冻结人 */
  public static final String PK_FREEZEPSNDOC = "pk_freezepsndoc";

  /** 所属集团 */
  public static final String PK_GROUP = "pk_group";

  /** 开票供应商 */
  public static final String PK_INVCSUPLLIER = "pk_invcsupllier";

  /** 采购订单 */
  public static final String PK_ORDER = "pk_order";

  /** 采购组织版本信息 */
  public static final String PK_ORG = "pk_org";

  /** 采购组织 */
  public static final String PK_ORG_V = "pk_org_v";

  /** 付款协议 */
  public static final String PK_PAYTERM = "pk_payterm";

  /** 项目 */
  public static final String PK_PROJECT = "pk_project";

  /** 发布人 */
  public static final String PK_PUBPSN = "pk_pubpsn";

  /** 收货客户 */
  public static final String PK_RECVCUSTOMER = "pk_recvcustomer";

  /** 响应人 */
  public static final String PK_RESPPSN = "pk_resppsn";

  /** 供应商 */
  public static final String PK_SUPPLIER = "pk_supplier";

  /** 运输方式 */
  public static final String PK_TRANSPORTTYPE = "pk_transporttype";

  /** 审批日期 */
  public static final String TAUDITTIME = "taudittime";

  /** 冻结日期 */
  public static final String TFREEZETIME = "tfreezetime";

  /** 发布日期 */
  public static final String TPUBTIME = "tpubtime";

  /** 响应日期 */
  public static final String TRESPTIME = "tresptime";

  /** 修订日期 */
  public static final String TREVISIONTIME = "trevisiontime";

  /** ts */
  public static final String TS = "ts";

  /** 银行账号 */
  public static final String VBANKACCOUNT = "vbankaccount";

  /** 订单编号 */
  public static final String VBILLCODE = "vbillcode";

  /** 对方订单号 */
  public static final String VCOOPORDERCODE = "vcoopordercode";

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

  /** 冻结原因 */
  public static final String VFROZENREASON = "vfrozenreason";

  /** 备注 */
  public static final String VMEMO = "vmemo";

  /** 拒绝/变更理由 */
  public static final String VREASON = "vreason";

  /** 订单类型 */
  public static final String VTRANTYPECODE = "vtrantypecode";

  /**
   * 
   */
  private static final long serialVersionUID = -7001523181741213828L;

  /** 审批人 getter 方法 */
  public String getApprover() {
    return (String) this.getAttributeValue(OrderHeaderVO.APPROVER);
  }

  /** 已协同生成销售订单 getter 方法 */
  public UFBoolean getBcooptoso() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BCOOPTOSO);
  }

  /** 是否直运采购 getter 方法 */
  public UFBoolean getBdirect() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BDIRECT);
  }

  /** 最终关闭 getter 方法 */
  public UFBoolean getBfinalclose() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BFINALCLOSE);
  }

  /** 冻结 getter 方法 */
  public UFBoolean getBfrozen() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BFROZEN);
  }

  /** 制单人 getter 方法 */
  public String getBillmaker() {
    return (String) this.getAttributeValue(OrderHeaderVO.BILLMAKER);
  }

  /** 最新版本 getter 方法 */
  public UFBoolean getBislatest() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BISLATEST);
  }

  /** 补货 getter 方法 */
  public UFBoolean getBisreplenish() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BISREPLENISH);
  }

  /** 发布 getter 方法 */
  public UFBoolean getBpublish() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BPUBLISH);
  }

  /** 退货/库基于原订单补货 getter 方法 */
  public UFBoolean getBrefwhenreturn() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BREFWHENRETURN);
  }

  /** 曾经发布 getter 方法 */
  public UFBoolean getBreleasedover() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BRELEASEDOVER);
  }

  /** 退货 getter 方法 */
  public UFBoolean getBreturn() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BRETURN);
  }

  /** 由销售订单协同生成 getter 方法 */
  public UFBoolean getBsocooptome() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BSOCOOPTOME);
  }

  /** 合同附件 getter 方法 */
  public String getCcontracttextpath() {
    return (String) this.getAttributeValue(OrderHeaderVO.CCONTRACTTEXTPATH);
  }

  /** 采购员 getter 方法 */
  public String getCemployeeid() {
    return (String) this.getAttributeValue(OrderHeaderVO.CEMPLOYEEID);
  }

  /** 币种 getter 方法 */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(OrderHeaderVO.CORIGCURRENCYID);
  }

  /** 创建时间 getter 方法 */
  public UFDateTime getCreationtime() {
    return (UFDateTime) this.getAttributeValue(OrderHeaderVO.CREATIONTIME);
  }

  /** 创建人 getter 方法 */
  public String getCreator() {
    return (String) this.getAttributeValue(OrderHeaderVO.CREATOR);
  }

  /** 修订人 getter 方法 */
  public String getCrevisepsn() {
    return (String) this.getAttributeValue(OrderHeaderVO.CREVISEPSN);
  }

  /** 贸易术语 getter 方法 */
  public String getCtradewordid() {
    return (String) this.getAttributeValue(OrderHeaderVO.CTRADEWORDID);
  }

  /** 订单类型 getter 方法 */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(OrderHeaderVO.CTRANTYPEID);
  }

  /** 订单日期 getter 方法 */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(OrderHeaderVO.DBILLDATE);
  }

  /** 最终关闭日期 getter 方法 */
  public UFDate getDclosedate() {
    return (UFDate) this.getAttributeValue(OrderHeaderVO.DCLOSEDATE);
  }

  /** 制单日期 **/
  public UFDate getDmakedate() {
    return (UFDate) this.getAttributeValue(OrderHeaderVO.DMAKEDATE);
  }

  /** dr getter 方法 */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(OrderHeaderVO.DR);
  }

  /** 整单扣税类别 getter 方法 */
  public Integer getFhtaxtypeflag() {
    return (Integer) this.getAttributeValue(OrderHeaderVO.FHTAXTYPEFLAG);
  }

  /** 单据状态 getter 方法 */
  public Integer getForderstatus() {
    return (Integer) this.getAttributeValue(OrderHeaderVO.FORDERSTATUS);
  }

  /** 打印次数 getter 方法 */
  public Integer getIprintcount() {
    return (Integer) this.getAttributeValue(OrderHeaderVO.IPRINTCOUNT);
  }

  /** 响应状态 getter 方法 */
  public Integer getIrespstatus() {
    return (Integer) this.getAttributeValue(OrderHeaderVO.IRESPSTATUS);
  }

  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.M21_H);
  }

  /** 最后修改时间 getter 方法 */
  public UFDateTime getModifiedtime() {
    return (UFDateTime) this.getAttributeValue(OrderHeaderVO.MODIFIEDTIME);
  }

  /** 最后修改人 getter 方法 */
  public String getModifier() {
    return (String) this.getAttributeValue(OrderHeaderVO.MODIFIER);
  }

  /** 整单税率 getter 方法 */
  public UFDouble getNhtaxrate() {
    return (UFDouble) this.getAttributeValue(OrderHeaderVO.NHTAXRATE);
  }

  /** 预付款限额 getter 方法 */
  public UFDouble getNorgprepaylimit() {
    return (UFDouble) this.getAttributeValue(OrderHeaderVO.NORGPREPAYLIMIT);
  }

  /** 总数量 getter 方法 */
  public UFDouble getNtotalastnum() {
    return (UFDouble) this.getAttributeValue(OrderHeaderVO.NTOTALASTNUM);
  }

  /** 价税合计 getter 方法 */
  public UFDouble getNtotalorigmny() {
    return (UFDouble) this.getAttributeValue(OrderHeaderVO.NTOTALORIGMNY);
  }

  /** 总件数 getter 方法 */
  public UFDouble getNtotalpiece() {
    return (UFDouble) this.getAttributeValue(OrderHeaderVO.NTOTALPIECE);
  }

  /** 总体积 getter 方法 */
  public UFDouble getNtotalvolume() {
    return (UFDouble) this.getAttributeValue(OrderHeaderVO.NTOTALVOLUME);
  }

  /** 总重量 getter 方法 */
  public UFDouble getNtotalweight() {
    return (UFDouble) this.getAttributeValue(OrderHeaderVO.NTOTALWEIGHT);
  }

  /** 版本号 getter 方法 */
  public Integer getNversion() {
    return (Integer) this.getAttributeValue(OrderHeaderVO.NVERSION);
  }
  
  /** 结算方式 getter 方法 */
  public String getPk_balatype() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_BALATYPE);
  }

  /** 开户银行 getter 方法 */
  public String getPk_bankdoc() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_BANKDOC);
  }

  /** 业务流程 getter 方法 */
  public String getPk_busitype() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_BUSITYPE);
  }

  /** 供应商发货地址 getter 方法 */
  public String getPk_deliveradd() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_DELIVERADD);
  }

  /** 采购部门最新版本 getter 方法 */
  public String getPk_dept() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_DEPT);
  }

  /** 采购部门 getter 方法 */
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_DEPT_V);
  }

  /** 散户 getter 方法 */
  public String getPk_freecust() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_FREECUST);
  }

  /** 冻结人 getter 方法 */
  public String getPk_freezepsndoc() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_FREEZEPSNDOC);
  }

  /** 所属集团 getter 方法 */
  public String getPk_group() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_GROUP);
  }

  /** 开票供应商 getter 方法 */
  public String getPk_invcsupllier() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_INVCSUPLLIER);
  }

  /** 采购订单 getter 方法 */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_ORDER);
  }

  /** 采购组织版本信息 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_ORG);
  }

  /** 采购组织 getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_ORG_V);
  }

  /** 付款协议 getter 方法 */
  public String getPk_payterm() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_PAYTERM);
  }

  /** 项目 getter 方法 */
  public String getPk_project() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_PROJECT);
  }

  /** 发布人 getter 方法 */
  public String getPk_pubpsn() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_PUBPSN);
  }

  /** 收货客户 getter 方法 */
  public String getPk_recvcustomer() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_RECVCUSTOMER);
  }

  /** 响应人 getter 方法 */
  public String getPk_resppsn() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_RESPPSN);
  }

  /** 供应商 getter 方法 */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_SUPPLIER);
  }

  /** 运输方式 getter 方法 */
  public String getPk_transporttype() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_TRANSPORTTYPE);
  }

  /** 审批日期 getter 方法 */
  public UFDate getTaudittime() {
    return (UFDate) this.getAttributeValue(OrderHeaderVO.TAUDITTIME);
  }

  /** 冻结日期 getter 方法 */
  public UFDate getTfreezetime() {
    return (UFDate) this.getAttributeValue(OrderHeaderVO.TFREEZETIME);
  }

  /** 发布日期 getter 方法 */
  public UFDate getTpubtime() {
    return (UFDate) this.getAttributeValue(OrderHeaderVO.TPUBTIME);
  }

  /** 响应日期 getter 方法 */
  public UFDate getTresptime() {
    return (UFDate) this.getAttributeValue(OrderHeaderVO.TRESPTIME);
  }

  /** 修订日期 getter 方法 */
  public UFDate getTrevisiontime() {
    return (UFDate) this.getAttributeValue(OrderHeaderVO.TREVISIONTIME);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(OrderHeaderVO.TS);
  }

  /** 订单编号 getter 方法 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(OrderHeaderVO.VBILLCODE);
  }

  /** 对方订单号 getter 方法 */
  public String getVcoopordercode() {
    return (String) this.getAttributeValue(OrderHeaderVO.VCOOPORDERCODE);
  }

  /** 自定义项1 getter 方法 */
  public String getVdef1() {
    return (String) this.getAttributeValue(OrderHeaderVO.VDEF1);
  }

  /** 自定义项10 getter 方法 */
  public String getVdef10() {
    return (String) this.getAttributeValue(OrderHeaderVO.VDEF10);
  }

  /** 自定义项11 getter 方法 */
  public String getVdef11() {
    return (String) this.getAttributeValue(OrderHeaderVO.VDEF11);
  }

  /** 自定义项12 getter 方法 */
  public String getVdef12() {
    return (String) this.getAttributeValue(OrderHeaderVO.VDEF12);
  }

  /** 自定义项13 getter 方法 */
  public String getVdef13() {
    return (String) this.getAttributeValue(OrderHeaderVO.VDEF13);
  }

  /** 自定义项14 getter 方法 */
  public String getVdef14() {
    return (String) this.getAttributeValue(OrderHeaderVO.VDEF14);
  }

  /** 自定义项15 getter 方法 */
  public String getVdef15() {
    return (String) this.getAttributeValue(OrderHeaderVO.VDEF15);
  }

  /** 自定义项16 getter 方法 */
  public String getVdef16() {
    return (String) this.getAttributeValue(OrderHeaderVO.VDEF16);
  }

  /** 自定义项17 getter 方法 */
  public String getVdef17() {
    return (String) this.getAttributeValue(OrderHeaderVO.VDEF17);
  }

  /** 自定义项18 getter 方法 */
  public String getVdef18() {
    return (String) this.getAttributeValue(OrderHeaderVO.VDEF18);
  }

  /** 自定义项19 getter 方法 */
  public String getVdef19() {
    return (String) this.getAttributeValue(OrderHeaderVO.VDEF19);
  }

  /** 自定义项2 getter 方法 */
  public String getVdef2() {
    return (String) this.getAttributeValue(OrderHeaderVO.VDEF2);
  }

  /** 自定义项20 getter 方法 */
  public String getVdef20() {
    return (String) this.getAttributeValue(OrderHeaderVO.VDEF20);
  }

  /** 自定义项3 getter 方法 */
  public String getVdef3() {
    return (String) this.getAttributeValue(OrderHeaderVO.VDEF3);
  }

  /** 自定义项4 getter 方法 */
  public String getVdef4() {
    return (String) this.getAttributeValue(OrderHeaderVO.VDEF4);
  }

  /** 自定义项5 getter 方法 */
  public String getVdef5() {
    return (String) this.getAttributeValue(OrderHeaderVO.VDEF5);
  }

  /** 自定义项6 getter 方法 */
  public String getVdef6() {
    return (String) this.getAttributeValue(OrderHeaderVO.VDEF6);
  }

  /** 自定义项7 getter 方法 */
  public String getVdef7() {
    return (String) this.getAttributeValue(OrderHeaderVO.VDEF7);
  }

  /** 自定义项8 getter 方法 */
  public String getVdef8() {
    return (String) this.getAttributeValue(OrderHeaderVO.VDEF8);
  }

  /** 自定义项9 getter 方法 */
  public String getVdef9() {
    return (String) this.getAttributeValue(OrderHeaderVO.VDEF9);
  }

  /** 冻结原因 getter 方法 */
  public String getVfrozenreason() {
    return (String) this.getAttributeValue(OrderHeaderVO.VFROZENREASON);
  }

  /** 备注 getter 方法 */
  public String getVmemo() {
    return (String) this.getAttributeValue(OrderHeaderVO.VMEMO);
  }

  /** 拒绝/变更理由 getter 方法 */
  public String getVreason() {
    return (String) this.getAttributeValue(OrderHeaderVO.VREASON);
  }

  /** 订单类型 getter 方法 */
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(OrderHeaderVO.VTRANTYPECODE);
  }

  /** 审批人 setter 方法 */
  public void setApprover(String approver) {
    this.setAttributeValue(OrderHeaderVO.APPROVER, approver);
  }

  /** 已协同生成销售订单 setter 方法 */
  public void setBcooptoso(UFBoolean bcooptoso) {
    this.setAttributeValue(OrderHeaderVO.BCOOPTOSO, bcooptoso);
  }

  /** 是否直运采购 setter 方法 */
  public void setBdirect(UFBoolean bdirect) {
    this.setAttributeValue(OrderHeaderVO.BDIRECT, bdirect);
  }

  /** 最终关闭 setter 方法 */
  public void setBfinalclose(UFBoolean bfinalclose) {
    this.setAttributeValue(OrderHeaderVO.BFINALCLOSE, bfinalclose);
  }

  /** 冻结 setter 方法 */
  public void setBfrozen(UFBoolean bfrozen) {
    this.setAttributeValue(OrderHeaderVO.BFROZEN, bfrozen);
  }

  /** 制单人 setter 方法 */
  public void setBillmaker(String billmaker) {
    this.setAttributeValue(OrderHeaderVO.BILLMAKER, billmaker);
  }

  /** 最新版本 setter 方法 */
  public void setBislatest(UFBoolean bislatest) {
    this.setAttributeValue(OrderHeaderVO.BISLATEST, bislatest);
  }

  /** 补货 setter 方法 */
  public void setBisreplenish(UFBoolean bisreplenish) {
    this.setAttributeValue(OrderHeaderVO.BISREPLENISH, bisreplenish);
  }

  /** 发布 setter 方法 */
  public void setBpublish(UFBoolean bpublish) {
    this.setAttributeValue(OrderHeaderVO.BPUBLISH, bpublish);
  }

  /** 退货/库基于原订单补货 setter 方法 */
  public void setBrefwhenreturn(UFBoolean brefwhenreturn) {
    this.setAttributeValue(OrderHeaderVO.BREFWHENRETURN, brefwhenreturn);
  }

  /** 曾经发布 setter 方法 */
  public void setBreleasedover(UFBoolean breleasedover) {
    this.setAttributeValue(OrderHeaderVO.BRELEASEDOVER, breleasedover);
  }

  /** 退货 setter 方法 */
  public void setBreturn(UFBoolean breturn) {
    this.setAttributeValue(OrderHeaderVO.BRETURN, breturn);
  }

  /** 由销售订单协同生成 setter 方法 */
  public void setBsocooptome(UFBoolean bsocooptome) {
    this.setAttributeValue(OrderHeaderVO.BSOCOOPTOME, bsocooptome);
  }

  /** 合同附件 setter 方法 */
  public void setCcontracttextpath(String ccontracttextpath) {
    this.setAttributeValue(OrderHeaderVO.CCONTRACTTEXTPATH, ccontracttextpath);
  }

  /** 采购员 setter 方法 */
  public void setCemployeeid(String cemployeeid) {
    this.setAttributeValue(OrderHeaderVO.CEMPLOYEEID, cemployeeid);
  }

  /** 币种 setter 方法 */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(OrderHeaderVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /** 创建时间 setter 方法 */
  public void setCreationtime(UFDateTime creationtime) {
    this.setAttributeValue(OrderHeaderVO.CREATIONTIME, creationtime);
  }

  /** 创建人 setter 方法 */
  public void setCreator(String creator) {
    this.setAttributeValue(OrderHeaderVO.CREATOR, creator);
  }

  /** 修订人 setter 方法 */
  public void setCrevisepsn(String crevisepsn) {
    this.setAttributeValue(OrderHeaderVO.CREVISEPSN, crevisepsn);
  }

  /** 贸易术语 setter 方法 */
  public void setCtradewordid(String ctradewordid) {
    this.setAttributeValue(OrderHeaderVO.CTRADEWORDID, ctradewordid);
  }

  /** 订单类型 setter 方法 */
  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(OrderHeaderVO.CTRANTYPEID, ctrantypeid);
  }

  /** 订单日期 setter 方法 */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(OrderHeaderVO.DBILLDATE, dbilldate);
  }

  /** 最终关闭日期 setter 方法 */
  public void setDclosedate(UFDate dclosedate) {
    this.setAttributeValue(OrderHeaderVO.DCLOSEDATE, dclosedate);
  }

  /** 制单日期 **/
  public void setDmakedate(UFDate dmakedate) {
    this.setAttributeValue(OrderHeaderVO.DMAKEDATE, dmakedate);
  }

  /** dr setter 方法 */
  public void setDr(Integer dr) {
    this.setAttributeValue(OrderHeaderVO.DR, dr);
  }

  /** 整单扣税类别 setter 方法 */
  public void setFhtaxtypeflag(Integer fhtaxtypeflag) {
    this.setAttributeValue(OrderHeaderVO.FHTAXTYPEFLAG, fhtaxtypeflag);
  }

  /** 单据状态 setter 方法 */
  public void setForderstatus(Integer forderstatus) {
    this.setAttributeValue(OrderHeaderVO.FORDERSTATUS, forderstatus);
  }

  /** 打印次数 setter 方法 */
  public void setIprintcount(Integer iprintcount) {
    this.setAttributeValue(OrderHeaderVO.IPRINTCOUNT, iprintcount);
  }

  /** 响应状态 setter 方法 */
  public void setIrespstatus(Integer irespstatus) {
    this.setAttributeValue(OrderHeaderVO.IRESPSTATUS, irespstatus);
  }

  /** 最后修改时间 setter 方法 */
  public void setModifiedtime(UFDateTime modifiedtime) {
    this.setAttributeValue(OrderHeaderVO.MODIFIEDTIME, modifiedtime);
  }

  /** 最后修改人 setter 方法 */
  public void setModifier(String modifier) {
    this.setAttributeValue(OrderHeaderVO.MODIFIER, modifier);
  }

  /** 整单税率 setter 方法 */
  public void setNhtaxrate(UFDouble nhtaxrate) {
    this.setAttributeValue(OrderHeaderVO.NHTAXRATE, nhtaxrate);
  }

  /** 预付款限额 setter 方法 */
  public void setNorgprepaylimit(UFDouble norgprepaylimit) {
    this.setAttributeValue(OrderHeaderVO.NORGPREPAYLIMIT, norgprepaylimit);
  }

  /** 总数量 setter 方法 */
  public void setNtotalastnum(UFDouble ntotalastnum) {
    this.setAttributeValue(OrderHeaderVO.NTOTALASTNUM, ntotalastnum);
  }

  /** 价税合计 setter 方法 */
  public void setNtotalorigmny(UFDouble ntotalorigmny) {
    this.setAttributeValue(OrderHeaderVO.NTOTALORIGMNY, ntotalorigmny);
  }

  /** 总件数 setter 方法 */
  public void setNtotalpiece(UFDouble ntotalpiece) {
    this.setAttributeValue(OrderHeaderVO.NTOTALPIECE, ntotalpiece);
  }

  /** 总体积 setter 方法 */
  public void setNtotalvolume(UFDouble ntotalvolume) {
    this.setAttributeValue(OrderHeaderVO.NTOTALVOLUME, ntotalvolume);
  }

  /** 总重量 setter 方法 */
  public void setNtotalweight(UFDouble ntotalweight) {
    this.setAttributeValue(OrderHeaderVO.NTOTALWEIGHT, ntotalweight);
  }

  /** 版本号 setter 方法 */
  public void setNversion(Integer nversion) {
    this.setAttributeValue(OrderHeaderVO.NVERSION, nversion);
  }
  
  /** 结算方式 setter 方法 */
  public void setPk_balatype(String pk_balatype) {
    this.setAttributeValue(OrderHeaderVO.PK_BALATYPE, pk_balatype);
  }

  /** 开户银行 setter 方法 */
  public void setPk_bankdoc(String pk_bankdoc) {
    this.setAttributeValue(OrderHeaderVO.PK_BANKDOC, pk_bankdoc);
  }

  /** 业务流程 setter 方法 */
  public void setPk_busitype(String pk_busitype) {
    this.setAttributeValue(OrderHeaderVO.PK_BUSITYPE, pk_busitype);
  }

  /** 供应商发货地址 setter 方法 */
  public void setPk_deliveradd(String pk_deliveradd) {
    this.setAttributeValue(OrderHeaderVO.PK_DELIVERADD, pk_deliveradd);
  }

  /** 采购部门最新版本 setter 方法 */
  public void setPk_dept(String pk_dept) {
    this.setAttributeValue(OrderHeaderVO.PK_DEPT, pk_dept);
  }

  /** 采购部门 setter 方法 */
  public void setPk_dept_v(String pk_dept_v) {
    this.setAttributeValue(OrderHeaderVO.PK_DEPT_V, pk_dept_v);
  }

  /** 散户 setter 方法 */
  public void setPk_freecust(String pk_freecust) {
    this.setAttributeValue(OrderHeaderVO.PK_FREECUST, pk_freecust);
  }

  /** 冻结人 setter 方法 */
  public void setPk_freezepsndoc(String pk_freezepsndoc) {
    this.setAttributeValue(OrderHeaderVO.PK_FREEZEPSNDOC, pk_freezepsndoc);
  }

  /** 所属集团 setter 方法 */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(OrderHeaderVO.PK_GROUP, pk_group);
  }

  /** 开票供应商 setter 方法 */
  public void setPk_invcsupllier(String pk_invcsupllier) {
    this.setAttributeValue(OrderHeaderVO.PK_INVCSUPLLIER, pk_invcsupllier);
  }

  /** 采购订单 setter 方法 */
  public void setPk_order(String pk_order) {
    this.setAttributeValue(OrderHeaderVO.PK_ORDER, pk_order);
  }

  /** 采购组织版本信息 setter 方法 */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(OrderHeaderVO.PK_ORG, pk_org);
  }

  /** 采购组织 setter 方法 */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(OrderHeaderVO.PK_ORG_V, pk_org_v);
  }

  /** 付款协议 setter 方法 */
  public void setPk_payterm(String pk_payterm) {
    this.setAttributeValue(OrderHeaderVO.PK_PAYTERM, pk_payterm);
  }

  /** 项目 setter 方法 */
  public void setPk_project(String pk_project) {
    this.setAttributeValue(OrderHeaderVO.PK_PROJECT, pk_project);
  }

  /** 发布人 setter 方法 */
  public void setPk_pubpsn(String pk_pubpsn) {
    this.setAttributeValue(OrderHeaderVO.PK_PUBPSN, pk_pubpsn);
  }

  /** 收货客户 setter 方法 */
  public void setPk_recvcustomer(String pk_recvcustomer) {
    this.setAttributeValue(OrderHeaderVO.PK_RECVCUSTOMER, pk_recvcustomer);
  }

  /** 响应人 setter 方法 */
  public void setPk_resppsn(String pk_resppsn) {
    this.setAttributeValue(OrderHeaderVO.PK_RESPPSN, pk_resppsn);
  }

  /** 供应商 setter 方法 */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(OrderHeaderVO.PK_SUPPLIER, pk_supplier);
  }

  /** 运输方式 setter 方法 */
  public void setPk_transporttype(String pk_transporttype) {
    this.setAttributeValue(OrderHeaderVO.PK_TRANSPORTTYPE, pk_transporttype);
  }

  /** 审批日期 setter 方法 */
  public void setTaudittime(UFDate taudittime) {
    this.setAttributeValue(OrderHeaderVO.TAUDITTIME, taudittime);
  }

  /** 冻结日期 setter 方法 */
  public void setTfreezetime(UFDate tfreezetime) {
    this.setAttributeValue(OrderHeaderVO.TFREEZETIME, tfreezetime);
  }

  /** 发布日期 setter 方法 */
  public void setTpubtime(UFDate tpubtime) {
    this.setAttributeValue(OrderHeaderVO.TPUBTIME, tpubtime);
  }

  /** 响应日期 setter 方法 */
  public void setTresptime(UFDate tresptime) {
    this.setAttributeValue(OrderHeaderVO.TRESPTIME, tresptime);
  }

  /** 修订日期 setter 方法 */
  public void setTrevisiontime(UFDate trevisiontime) {
    this.setAttributeValue(OrderHeaderVO.TREVISIONTIME, trevisiontime);
  }

  /** ts setter 方法 */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(OrderHeaderVO.TS, ts);
  }

  /** 订单编号 setter 方法 */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(OrderHeaderVO.VBILLCODE, vbillcode);
  }

  /** 对方订单号 setter 方法 */
  public void setVcoopordercode(String vcoopordercode) {
    this.setAttributeValue(OrderHeaderVO.VCOOPORDERCODE, vcoopordercode);
  }

  /** 自定义项1 setter 方法 */
  public void setVdef1(String vdef1) {
    this.setAttributeValue(OrderHeaderVO.VDEF1, vdef1);
  }

  /** 自定义项10 setter 方法 */
  public void setVdef10(String vdef10) {
    this.setAttributeValue(OrderHeaderVO.VDEF10, vdef10);
  }

  /** 自定义项11 setter 方法 */
  public void setVdef11(String vdef11) {
    this.setAttributeValue(OrderHeaderVO.VDEF11, vdef11);
  }

  /** 自定义项12 setter 方法 */
  public void setVdef12(String vdef12) {
    this.setAttributeValue(OrderHeaderVO.VDEF12, vdef12);
  }

  /** 自定义项13 setter 方法 */
  public void setVdef13(String vdef13) {
    this.setAttributeValue(OrderHeaderVO.VDEF13, vdef13);
  }

  /** 自定义项14 setter 方法 */
  public void setVdef14(String vdef14) {
    this.setAttributeValue(OrderHeaderVO.VDEF14, vdef14);
  }

  /** 自定义项15 setter 方法 */
  public void setVdef15(String vdef15) {
    this.setAttributeValue(OrderHeaderVO.VDEF15, vdef15);
  }

  /** 自定义项16 setter 方法 */
  public void setVdef16(String vdef16) {
    this.setAttributeValue(OrderHeaderVO.VDEF16, vdef16);
  }

  /** 自定义项17 setter 方法 */
  public void setVdef17(String vdef17) {
    this.setAttributeValue(OrderHeaderVO.VDEF17, vdef17);
  }

  /** 自定义项18 setter 方法 */
  public void setVdef18(String vdef18) {
    this.setAttributeValue(OrderHeaderVO.VDEF18, vdef18);
  }

  /** 自定义项19 setter 方法 */
  public void setVdef19(String vdef19) {
    this.setAttributeValue(OrderHeaderVO.VDEF19, vdef19);
  }

  /** 自定义项2 setter 方法 */
  public void setVdef2(String vdef2) {
    this.setAttributeValue(OrderHeaderVO.VDEF2, vdef2);
  }

  /** 自定义项20 setter 方法 */
  public void setVdef20(String vdef20) {
    this.setAttributeValue(OrderHeaderVO.VDEF20, vdef20);
  }

  /** 自定义项3 setter 方法 */
  public void setVdef3(String vdef3) {
    this.setAttributeValue(OrderHeaderVO.VDEF3, vdef3);
  }

  /** 自定义项4 setter 方法 */
  public void setVdef4(String vdef4) {
    this.setAttributeValue(OrderHeaderVO.VDEF4, vdef4);
  }

  /** 自定义项5 setter 方法 */
  public void setVdef5(String vdef5) {
    this.setAttributeValue(OrderHeaderVO.VDEF5, vdef5);
  }

  /** 自定义项6 setter 方法 */
  public void setVdef6(String vdef6) {
    this.setAttributeValue(OrderHeaderVO.VDEF6, vdef6);
  }

  /** 自定义项7 setter 方法 */
  public void setVdef7(String vdef7) {
    this.setAttributeValue(OrderHeaderVO.VDEF7, vdef7);
  }

  /** 自定义项8 setter 方法 */
  public void setVdef8(String vdef8) {
    this.setAttributeValue(OrderHeaderVO.VDEF8, vdef8);
  }

  /** 自定义项9 setter 方法 */
  public void setVdef9(String vdef9) {
    this.setAttributeValue(OrderHeaderVO.VDEF9, vdef9);
  }

  /** 冻结原因 setter 方法 */
  public void setVfrozenreason(String vfrozenreason) {
    this.setAttributeValue(OrderHeaderVO.VFROZENREASON, vfrozenreason);
  }

  /** 备注 setter 方法 */
  public void setVmemo(String vmemo) {
    this.setAttributeValue(OrderHeaderVO.VMEMO, vmemo);
  }

  /** 拒绝/变更理由 setter 方法 */
  public void setVreason(String vreason) {
    this.setAttributeValue(OrderHeaderVO.VREASON, vreason);
  }

  /** 订单类型 setter 方法 */
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(OrderHeaderVO.VTRANTYPECODE, vtrantypecode);
  }

}
