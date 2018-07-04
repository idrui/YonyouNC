/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-10 上午09:19:39
 */
package nc.vo.pu.m21.entity;

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
 * <li>状态维护在途状态页面用表头vo
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-10 上午09:19:39
 */
public class OrderOnwayHeaderVO extends SuperVO {
  /** 审批人 */
  public static final String APPROVER = "approver";

  /** 已协同生成销售订单 */
  public static final String BCOOPTOSO = "bcooptoso";

  /** 最终关闭 */
  public static final String BFINALCLOSE = "bfinalclose";

  /** 冻结 */
  public static final String BFROZEN = "bfrozen";

  /** 最新版本 */
  public static final String BISLATEST = "bislatest";

  /** 补货 */
  public static final String BISREPLENISH = "bisreplenish";

  /** 退货/库基于原订单补货 */
  public static final String BREFWHENRETURN = "brefwhenreturn";

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

  /** 最后修改时间 */
  public static final String MODIFIEDTIME = "modifiedtime";

  /** 最后修改人 */
  public static final String MODIFIER = "modifier";

  /** 整单税率 */
  public static final String NHTAXRATE = "nhtaxrate";

  /** 预付款限额 */
  public static final String NORGPREPAYLIMIT = "norgprepaylimit";

  /** 预付款 */
  public static final String NORGPREPAYMNY = "norgprepaymny";

  /** 总数量 */
  public static final String NTOTALASTNUM = "ntotalastnum";

  /** 累计付款总额 */
  public static final String NTOTALORGPAYMNY = "ntotalorgpaymny";

  /** 价税合计 */
  public static final String NTOTALORIGMNY = "ntotalorigmny";

  /** 累计本币付款总额 */
  public static final String NTOTALPAYMNY = "ntotalpaymny";

  /** 总件数 */
  public static final String NTOTALPIECE = "ntotalpiece";

  /** 总体积 */
  public static final String NTOTALVOLUME = "ntotalvolume";

  /** 总重量 */
  public static final String NTOTALWEIGHT = "ntotalweight";

  /** 版本号 */
  public static final String NVERSION = "nversion";

  /** 制单人 */
  public static final String OPERATOR = "operator";

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

  /** 收货客户 */
  public static final String PK_RECVCUSTOMER = "pk_recvcustomer";

  /** 供应商 */
  public static final String PK_SUPPLIER = "pk_supplier";

  /** 运输方式 */
  public static final String PK_TRANSPORTTYPE = "pk_transporttype";

  /** 审批日期 */
  public static final String TAUDITTIME = "taudittime";

  /** 冻结日期 */
  public static final String TFREEZETIME = "tfreezetime";

  /** 修订日期 */
  public static final String TREVISIONTIME = "trevisiontime";

  /** ts */
  public static final String TS = "ts";

  /** 订单编号 */
  public static final String VBILLCODE = "vbillcode";

  /** 对方订单号 */
  public static final String VCOOPORDERCODE = "vcoopordercode";

  /** 冻结原因 */
  public static final String VFREEZEREASON = "vfreezereason";

  /** 自定义项1 */
  public static final String VHDEF1 = "vhdef1";

  /** 自定义项10 */
  public static final String VHDEF10 = "vhdef10";

  /** 自定义项11 */
  public static final String VHDEF11 = "vhdef11";

  /** 自定义项12 */
  public static final String VHDEF12 = "vhdef12";

  /** 自定义项13 */
  public static final String VHDEF13 = "vhdef13";

  /** 自定义项14 */
  public static final String VHDEF14 = "vhdef14";

  /** 自定义项15 */
  public static final String VHDEF15 = "vhdef15";

  /** 自定义项16 */
  public static final String VHDEF16 = "vhdef16";

  /** 自定义项17 */
  public static final String VHDEF17 = "vhdef17";

  /** 自定义项18 */
  public static final String VHDEF18 = "vhdef18";

  /** 自定义项19 */
  public static final String VHDEF19 = "vhdef19";

  /** 自定义项2 */
  public static final String VHDEF2 = "vhdef2";

  /** 自定义项20 */
  public static final String VHDEF20 = "vhdef20";

  /** 自定义项21 */
  public static final String VHDEF21 = "vhdef21";

  /** 自定义项22 */
  public static final String VHDEF22 = "vhdef22";

  /** 自定义项23 */
  public static final String VHDEF23 = "vhdef23";

  /** 自定义项24 */
  public static final String VHDEF24 = "vhdef24";

  /** 自定义项25 */
  public static final String VHDEF25 = "vhdef25";

  /** 自定义项26 */
  public static final String VHDEF26 = "vhdef26";

  /** 自定义项27 */
  public static final String VHDEF27 = "vhdef27";

  /** 自定义项28 */
  public static final String VHDEF28 = "vhdef28";

  /** 自定义项29 */
  public static final String VHDEF29 = "vhdef29";

  /** 自定义项3 */
  public static final String VHDEF3 = "vhdef3";

  /** 自定义项30 */
  public static final String VHDEF30 = "vhdef30";

  /** 自定义项31 */
  public static final String VHDEF31 = "vhdef31";

  /** 自定义项32 */
  public static final String VHDEF32 = "vhdef32";

  /** 自定义项33 */
  public static final String VHDEF33 = "vhdef33";

  /** 自定义项34 */
  public static final String VHDEF34 = "vhdef34";

  /** 自定义项35 */
  public static final String VHDEF35 = "vhdef35";

  /** 自定义项36 */
  public static final String VHDEF36 = "vhdef36";

  /** 自定义项37 */
  public static final String VHDEF37 = "vhdef37";

  /** 自定义项38 */
  public static final String VHDEF38 = "vhdef38";

  /** 自定义项39 */
  public static final String VHDEF39 = "vhdef39";

  /** 自定义项4 */
  public static final String VHDEF4 = "vhdef4";

  /** 自定义项40 */
  public static final String VHDEF40 = "vhdef40";

  /** 自定义项5 */
  public static final String VHDEF5 = "vhdef5";

  /** 自定义项6 */
  public static final String VHDEF6 = "vhdef6";

  /** 自定义项7 */
  public static final String VHDEF7 = "vhdef7";

  /** 自定义项8 */
  public static final String VHDEF8 = "vhdef8";

  /** 自定义项9 */
  public static final String VHDEF9 = "vhdef9";

  /** 备注 */
  public static final String VMEMO = "vmemo";

  /** 订单类型 */
  public static final String VTRANTYPECODE = "vtrantypecode";

  /**
   * 
   */
  private static final long serialVersionUID = -7113212374538592041L;

  /** 审批人 getter 方法 */
  public String getApprover() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.APPROVER);
  }

  /** 已协同生成销售订单 getter 方法 */
  public UFBoolean getBcooptoso() {
    return (UFBoolean) this.getAttributeValue(OrderOnwayHeaderVO.BCOOPTOSO);
  }

  /** 最终关闭 getter 方法 */
  public UFBoolean getBfinalclose() {
    return (UFBoolean) this.getAttributeValue(OrderOnwayHeaderVO.BFINALCLOSE);
  }

  /** 冻结 getter 方法 */
  public UFBoolean getBfrozen() {
    return (UFBoolean) this.getAttributeValue(OrderOnwayHeaderVO.BFROZEN);
  }

  /** 最新版本 getter 方法 */
  public UFBoolean getBislatest() {
    return (UFBoolean) this.getAttributeValue(OrderOnwayHeaderVO.BISLATEST);
  }

  /** 补货 getter 方法 */
  public UFBoolean getBisreplenish() {
    return (UFBoolean) this.getAttributeValue(OrderOnwayHeaderVO.BISREPLENISH);
  }

  /** 退货/库基于原订单补货 getter 方法 */
  public UFBoolean getBrefwhenreturn() {
    return (UFBoolean) this
        .getAttributeValue(OrderOnwayHeaderVO.BREFWHENRETURN);
  }

  /** 退货 getter 方法 */
  public UFBoolean getBreturn() {
    return (UFBoolean) this.getAttributeValue(OrderOnwayHeaderVO.BRETURN);
  }

  /** 由销售订单协同生成 getter 方法 */
  public UFBoolean getBsocooptome() {
    return (UFBoolean) this.getAttributeValue(OrderOnwayHeaderVO.BSOCOOPTOME);
  }

  /** 合同附件 getter 方法 */
  public String getCcontracttextpath() {
    return (String) this
        .getAttributeValue(OrderOnwayHeaderVO.CCONTRACTTEXTPATH);
  }

  /** 采购员 getter 方法 */
  public String getCemployeeid() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.CEMPLOYEEID);
  }

  /** 币种 getter 方法 */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.CORIGCURRENCYID);
  }

  /** 创建时间 getter 方法 */
  public UFDateTime getCreationtime() {
    return (UFDateTime) this.getAttributeValue(OrderOnwayHeaderVO.CREATIONTIME);
  }

  /** 创建人 getter 方法 */
  public String getCreator() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.CREATOR);
  }

  /** 修订人 getter 方法 */
  public String getCrevisepsn() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.CREVISEPSN);
  }

  /** 订单日期 getter 方法 */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(OrderOnwayHeaderVO.DBILLDATE);
  }

  /** 最终关闭日期 getter 方法 */
  public UFDate getDclosedate() {
    return (UFDate) this.getAttributeValue(OrderOnwayHeaderVO.DCLOSEDATE);
  }

  /** 制单日期 **/
  public UFDate getDmakedate() {
    return (UFDate) this.getAttributeValue(OrderOnwayHeaderVO.DMAKEDATE);
  }

  /** dr getter 方法 */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(OrderOnwayHeaderVO.DR);
  }

  /** 整单扣税类别 getter 方法 */
  public Integer getFhtaxtypeflag() {
    return (Integer) this.getAttributeValue(OrderOnwayHeaderVO.FHTAXTYPEFLAG);
  }

  /** 单据状态 getter 方法 */
  public Integer getForderstatus() {
    return (Integer) this.getAttributeValue(OrderOnwayHeaderVO.FORDERSTATUS);
  }

  /** 打印次数 getter 方法 */
  public Integer getIprintcount() {
    return (Integer) this.getAttributeValue(OrderOnwayHeaderVO.IPRINTCOUNT);
  }

  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("pu.po_order_vo");
  }

  /** 最后修改时间 getter 方法 */
  public UFDateTime getModifiedtime() {
    return (UFDateTime) this.getAttributeValue(OrderOnwayHeaderVO.MODIFIEDTIME);
  }

  /** 最后修改人 getter 方法 */
  public String getModifier() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.MODIFIER);
  }

  /** 整单税率 getter 方法 */
  public UFDouble getNhtaxrate() {
    return (UFDouble) this.getAttributeValue(OrderOnwayHeaderVO.NHTAXRATE);
  }

  /** 预付款限额 getter 方法 */
  public UFDouble getNorgprepaylimit() {
    return (UFDouble) this
        .getAttributeValue(OrderOnwayHeaderVO.NORGPREPAYLIMIT);
  }

  /** 预付款 getter 方法 */
  public UFDouble getNorgprepaymny() {
    return (UFDouble) this.getAttributeValue(OrderOnwayHeaderVO.NORGPREPAYMNY);
  }

  /** 总数量 getter 方法 */
  public UFDouble getNtotalastnum() {
    return (UFDouble) this.getAttributeValue(OrderOnwayHeaderVO.NTOTALASTNUM);
  }

  /** 累计付款总额 getter 方法 */
  public UFDouble getNtotalorgpaymny() {
    return (UFDouble) this
        .getAttributeValue(OrderOnwayHeaderVO.NTOTALORGPAYMNY);
  }

  /** 价税合计 getter 方法 */
  public UFDouble getNtotalorigmny() {
    return (UFDouble) this.getAttributeValue(OrderOnwayHeaderVO.NTOTALORIGMNY);
  }

  /** 累计本币付款总额 getter 方法 */
  public UFDouble getNtotalpaymny() {
    return (UFDouble) this.getAttributeValue(OrderOnwayHeaderVO.NTOTALPAYMNY);
  }

  /** 总件数 getter 方法 */
  public UFDouble getNtotalpiece() {
    return (UFDouble) this.getAttributeValue(OrderOnwayHeaderVO.NTOTALPIECE);
  }

  /** 总体积 getter 方法 */
  public UFDouble getNtotalvolume() {
    return (UFDouble) this.getAttributeValue(OrderOnwayHeaderVO.NTOTALVOLUME);
  }

  /** 总重量 getter 方法 */
  public UFDouble getNtotalweight() {
    return (UFDouble) this.getAttributeValue(OrderOnwayHeaderVO.NTOTALWEIGHT);
  }

  /** 版本号 getter 方法 */
  public Integer getNversion() {
    return (Integer) this.getAttributeValue(OrderOnwayHeaderVO.NVERSION);
  }

  /** 制单人 getter 方法 */
  public String getOperator() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.OPERATOR);
  }

  /** 开户银行 getter 方法 */
  public String getPk_bankdoc() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.PK_BANKDOC);
  }

  /** 业务流程 getter 方法 */
  public String getPk_busitype() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.PK_BUSITYPE);
  }

  /** 供应商发货地址 getter 方法 */
  public String getPk_deliveradd() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.PK_DELIVERADD);
  }

  /** 采购部门最新版本 getter 方法 */
  public String getPk_dept() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.PK_DEPT);
  }

  /** 采购部门 getter 方法 */
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.PK_DEPT_V);
  }

  /** 散户 getter 方法 */
  public String getPk_freecust() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.PK_FREECUST);
  }

  /** 冻结人 getter 方法 */
  public String getPk_freezepsndoc() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.PK_FREEZEPSNDOC);
  }

  /** 所属集团 getter 方法 */
  public String getPk_group() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.PK_GROUP);
  }

  /** 开票供应商 getter 方法 */
  public String getPk_invcsupllier() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.PK_INVCSUPLLIER);
  }

  /** 采购订单 getter 方法 */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.PK_ORDER);
  }

  /** 采购组织版本信息 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.PK_ORG);
  }

  /** 采购组织 getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.PK_ORG_V);
  }

  /** 付款协议 getter 方法 */
  public String getPk_payterm() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.PK_PAYTERM);
  }

  /** 项目 getter 方法 */
  public String getPk_project() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.PK_PROJECT);
  }

  /** 收货客户 getter 方法 */
  public String getPk_recvcustomer() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.PK_RECVCUSTOMER);
  }

  /** 供应商 getter 方法 */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.PK_SUPPLIER);
  }

  /** 运输方式 getter 方法 */
  public String getPk_transporttype() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.PK_TRANSPORTTYPE);
  }

  /** 审批日期 getter 方法 */
  public UFDate getTaudittime() {
    return (UFDate) this.getAttributeValue(OrderOnwayHeaderVO.TAUDITTIME);
  }

  /** 冻结日期 getter 方法 */
  public UFDate getTfreezetime() {
    return (UFDate) this.getAttributeValue(OrderOnwayHeaderVO.TFREEZETIME);
  }

  /** 修订日期 getter 方法 */
  public UFDate getTrevisiontime() {
    return (UFDate) this.getAttributeValue(OrderOnwayHeaderVO.TREVISIONTIME);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(OrderOnwayHeaderVO.TS);
  }

  /** 订单编号 getter 方法 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VBILLCODE);
  }

  /** 对方订单号 getter 方法 */
  public String getVcoopordercode() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VCOOPORDERCODE);
  }

  /** 冻结原因 getter 方法 */
  public String getVfreezereason() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VFREEZEREASON);
  }

  /** 自定义项1 getter 方法 */
  public String getVhdef1() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF1);
  }

  /** 自定义项10 getter 方法 */
  public String getVhdef10() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF10);
  }

  /** 自定义项11 getter 方法 */
  public String getVhdef11() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF11);
  }

  /** 自定义项12 getter 方法 */
  public String getVhdef12() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF12);
  }

  /** 自定义项13 getter 方法 */
  public String getVhdef13() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF13);
  }

  /** 自定义项14 getter 方法 */
  public String getVhdef14() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF14);
  }

  /** 自定义项15 getter 方法 */
  public String getVhdef15() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF15);
  }

  /** 自定义项16 getter 方法 */
  public String getVhdef16() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF16);
  }

  /** 自定义项17 getter 方法 */
  public String getVhdef17() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF17);
  }

  /** 自定义项18 getter 方法 */
  public String getVhdef18() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF18);
  }

  /** 自定义项19 getter 方法 */
  public String getVhdef19() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF19);
  }

  /** 自定义项2 getter 方法 */
  public String getVhdef2() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF2);
  }

  /** 自定义项20 getter 方法 */
  public String getVhdef20() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF20);
  }

  /** 自定义项21 getter 方法 */
  public String getVhdef21() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF21);
  }

  /** 自定义项22 getter 方法 */
  public String getVhdef22() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF22);
  }

  /** 自定义项23 getter 方法 */
  public String getVhdef23() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF23);
  }

  /** 自定义项24 getter 方法 */
  public String getVhdef24() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF24);
  }

  /** 自定义项25 getter 方法 */
  public String getVhdef25() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF25);
  }

  /** 自定义项26 getter 方法 */
  public String getVhdef26() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF26);
  }

  /** 自定义项27 getter 方法 */
  public String getVhdef27() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF27);
  }

  /** 自定义项28 getter 方法 */
  public String getVhdef28() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF28);
  }

  /** 自定义项29 getter 方法 */
  public String getVhdef29() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF29);
  }

  /** 自定义项3 getter 方法 */
  public String getVhdef3() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF3);
  }

  /** 自定义项30 getter 方法 */
  public String getVhdef30() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF30);
  }

  /** 自定义项31 getter 方法 */
  public String getVhdef31() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF31);
  }

  /** 自定义项32 getter 方法 */
  public String getVhdef32() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF32);
  }

  /** 自定义项33 getter 方法 */
  public String getVhdef33() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF33);
  }

  /** 自定义项34 getter 方法 */
  public String getVhdef34() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF34);
  }

  /** 自定义项35 getter 方法 */
  public String getVhdef35() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF35);
  }

  /** 自定义项36 getter 方法 */
  public String getVhdef36() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF36);
  }

  /** 自定义项37 getter 方法 */
  public String getVhdef37() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF37);
  }

  /** 自定义项38 getter 方法 */
  public String getVhdef38() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF38);
  }

  /** 自定义项39 getter 方法 */
  public String getVhdef39() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF39);
  }

  /** 自定义项4 getter 方法 */
  public String getVhdef4() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF4);
  }

  /** 自定义项40 getter 方法 */
  public String getVhdef40() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF40);
  }

  /** 自定义项5 getter 方法 */
  public String getVhdef5() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF5);
  }

  /** 自定义项6 getter 方法 */
  public String getVhdef6() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF6);
  }

  /** 自定义项7 getter 方法 */
  public String getVhdef7() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF7);
  }

  /** 自定义项8 getter 方法 */
  public String getVhdef8() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF8);
  }

  /** 自定义项9 getter 方法 */
  public String getVhdef9() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VHDEF9);
  }

  /** 备注 getter 方法 */
  public String getVmemo() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VMEMO);
  }

  /** 订单类型 getter 方法 */
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(OrderOnwayHeaderVO.VTRANTYPECODE);
  }

  /** 审批人 setter 方法 */
  public void setApprover(String approver) {
    this.setAttributeValue(OrderOnwayHeaderVO.APPROVER, approver);
  }

  /** 已协同生成销售订单 setter 方法 */
  public void setBcooptoso(UFBoolean bcooptoso) {
    this.setAttributeValue(OrderOnwayHeaderVO.BCOOPTOSO, bcooptoso);
  }

  /** 最终关闭 setter 方法 */
  public void setBfinalclose(UFBoolean bfinalclose) {
    this.setAttributeValue(OrderOnwayHeaderVO.BFINALCLOSE, bfinalclose);
  }

  /** 冻结 setter 方法 */
  public void setBfrozen(UFBoolean bfrozen) {
    this.setAttributeValue(OrderOnwayHeaderVO.BFROZEN, bfrozen);
  }

  /** 最新版本 setter 方法 */
  public void setBislatest(UFBoolean bislatest) {
    this.setAttributeValue(OrderOnwayHeaderVO.BISLATEST, bislatest);
  }

  /** 补货 setter 方法 */
  public void setBisreplenish(UFBoolean bisreplenish) {
    this.setAttributeValue(OrderOnwayHeaderVO.BISREPLENISH, bisreplenish);
  }

  /** 退货/库基于原订单补货 setter 方法 */
  public void setBrefwhenreturn(UFBoolean brefwhenreturn) {
    this.setAttributeValue(OrderOnwayHeaderVO.BREFWHENRETURN, brefwhenreturn);
  }

  /** 退货 setter 方法 */
  public void setBreturn(UFBoolean breturn) {
    this.setAttributeValue(OrderOnwayHeaderVO.BRETURN, breturn);
  }

  /** 由销售订单协同生成 setter 方法 */
  public void setBsocooptome(UFBoolean bsocooptome) {
    this.setAttributeValue(OrderOnwayHeaderVO.BSOCOOPTOME, bsocooptome);
  }

  /** 合同附件 setter 方法 */
  public void setCcontracttextpath(String ccontracttextpath) {
    this.setAttributeValue(OrderOnwayHeaderVO.CCONTRACTTEXTPATH,
        ccontracttextpath);
  }

  /** 采购员 setter 方法 */
  public void setCemployeeid(String cemployeeid) {
    this.setAttributeValue(OrderOnwayHeaderVO.CEMPLOYEEID, cemployeeid);
  }

  /** 币种 setter 方法 */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(OrderOnwayHeaderVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /** 创建时间 setter 方法 */
  public void setCreationtime(UFDateTime creationtime) {
    this.setAttributeValue(OrderOnwayHeaderVO.CREATIONTIME, creationtime);
  }

  /** 创建人 setter 方法 */
  public void setCreator(String creator) {
    this.setAttributeValue(OrderOnwayHeaderVO.CREATOR, creator);
  }

  /** 修订人 setter 方法 */
  public void setCrevisepsn(String crevisepsn) {
    this.setAttributeValue(OrderOnwayHeaderVO.CREVISEPSN, crevisepsn);
  }

  /** 订单日期 setter 方法 */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(OrderOnwayHeaderVO.DBILLDATE, dbilldate);
  }

  /** 最终关闭日期 setter 方法 */
  public void setDclosedate(UFDate dclosedate) {
    this.setAttributeValue(OrderOnwayHeaderVO.DCLOSEDATE, dclosedate);
  }

  /** 制单日期 **/
  public void setDmakedate(UFDate dmakedate) {
    this.setAttributeValue(OrderOnwayHeaderVO.DMAKEDATE, dmakedate);
  }

  /** dr setter 方法 */
  public void setDr(Integer dr) {
    this.setAttributeValue(OrderOnwayHeaderVO.DR, dr);
  }

  /** 整单扣税类别 setter 方法 */
  public void setFhtaxtypeflag(Integer fhtaxtypeflag) {
    this.setAttributeValue(OrderOnwayHeaderVO.FHTAXTYPEFLAG, fhtaxtypeflag);
  }

  /** 单据状态 setter 方法 */
  public void setForderstatus(Integer forderstatus) {
    this.setAttributeValue(OrderOnwayHeaderVO.FORDERSTATUS, forderstatus);
  }

  /** 打印次数 setter 方法 */
  public void setIprintcount(Integer iprintcount) {
    this.setAttributeValue(OrderOnwayHeaderVO.IPRINTCOUNT, iprintcount);
  }

  /** 最后修改时间 setter 方法 */
  public void setModifiedtime(UFDateTime modifiedtime) {
    this.setAttributeValue(OrderOnwayHeaderVO.MODIFIEDTIME, modifiedtime);
  }

  /** 最后修改人 setter 方法 */
  public void setModifier(String modifier) {
    this.setAttributeValue(OrderOnwayHeaderVO.MODIFIER, modifier);
  }

  /** 整单税率 setter 方法 */
  public void setNhtaxrate(UFDouble nhtaxrate) {
    this.setAttributeValue(OrderOnwayHeaderVO.NHTAXRATE, nhtaxrate);
  }

  /** 预付款限额 setter 方法 */
  public void setNorgprepaylimit(UFDouble norgprepaylimit) {
    this.setAttributeValue(OrderOnwayHeaderVO.NORGPREPAYLIMIT, norgprepaylimit);
  }

  /** 预付款 setter 方法 */
  public void setNorgprepaymny(UFDouble norgprepaymny) {
    this.setAttributeValue(OrderOnwayHeaderVO.NORGPREPAYMNY, norgprepaymny);
  }

  /** 总数量 setter 方法 */
  public void setNtotalastnum(UFDouble ntotalastnum) {
    this.setAttributeValue(OrderOnwayHeaderVO.NTOTALASTNUM, ntotalastnum);
  }

  /** 累计付款总额 setter 方法 */
  public void setNtotalorgpaymny(UFDouble ntotalorgpaymny) {
    this.setAttributeValue(OrderOnwayHeaderVO.NTOTALORGPAYMNY, ntotalorgpaymny);
  }

  /** 价税合计 setter 方法 */
  public void setNtotalorigmny(UFDouble ntotalorigmny) {
    this.setAttributeValue(OrderOnwayHeaderVO.NTOTALORIGMNY, ntotalorigmny);
  }

  /** 累计本币付款总额 setter 方法 */
  public void setNtotalpaymny(UFDouble ntotalpaymny) {
    this.setAttributeValue(OrderOnwayHeaderVO.NTOTALPAYMNY, ntotalpaymny);
  }

  /** 总件数 setter 方法 */
  public void setNtotalpiece(UFDouble ntotalpiece) {
    this.setAttributeValue(OrderOnwayHeaderVO.NTOTALPIECE, ntotalpiece);
  }

  /** 总体积 setter 方法 */
  public void setNtotalvolume(UFDouble ntotalvolume) {
    this.setAttributeValue(OrderOnwayHeaderVO.NTOTALVOLUME, ntotalvolume);
  }

  /** 总重量 setter 方法 */
  public void setNtotalweight(UFDouble ntotalweight) {
    this.setAttributeValue(OrderOnwayHeaderVO.NTOTALWEIGHT, ntotalweight);
  }

  /** 版本号 setter 方法 */
  public void setNversion(Integer nversion) {
    this.setAttributeValue(OrderOnwayHeaderVO.NVERSION, nversion);
  }

  /** 制单人 setter 方法 */
  public void setOperator(String operator) {
    this.setAttributeValue(OrderOnwayHeaderVO.OPERATOR, operator);
  }

  /** 开户银行 setter 方法 */
  public void setPk_bankdoc(String pk_bankdoc) {
    this.setAttributeValue(OrderOnwayHeaderVO.PK_BANKDOC, pk_bankdoc);
  }

  /** 业务流程 setter 方法 */
  public void setPk_busitype(String pk_busitype) {
    this.setAttributeValue(OrderOnwayHeaderVO.PK_BUSITYPE, pk_busitype);
  }

  /** 供应商发货地址 setter 方法 */
  public void setPk_deliveradd(String pk_deliveradd) {
    this.setAttributeValue(OrderOnwayHeaderVO.PK_DELIVERADD, pk_deliveradd);
  }

  /** 采购部门最新版本 setter 方法 */
  public void setPk_dept(String pk_dept) {
    this.setAttributeValue(OrderOnwayHeaderVO.PK_DEPT, pk_dept);
  }

  /** 采购部门 setter 方法 */
  public void setPk_dept_v(String pk_dept_v) {
    this.setAttributeValue(OrderOnwayHeaderVO.PK_DEPT_V, pk_dept_v);
  }

  /** 散户 setter 方法 */
  public void setPk_freecust(String pk_freecust) {
    this.setAttributeValue(OrderOnwayHeaderVO.PK_FREECUST, pk_freecust);
  }

  /** 冻结人 setter 方法 */
  public void setPk_freezepsndoc(String pk_freezepsndoc) {
    this.setAttributeValue(OrderOnwayHeaderVO.PK_FREEZEPSNDOC, pk_freezepsndoc);
  }

  /** 所属集团 setter 方法 */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(OrderOnwayHeaderVO.PK_GROUP, pk_group);
  }

  /** 开票供应商 setter 方法 */
  public void setPk_invcsupllier(String pk_invcsupllier) {
    this.setAttributeValue(OrderOnwayHeaderVO.PK_INVCSUPLLIER, pk_invcsupllier);
  }

  /** 采购订单 setter 方法 */
  public void setPk_order(String pk_order) {
    this.setAttributeValue(OrderOnwayHeaderVO.PK_ORDER, pk_order);
  }

  /** 采购组织版本信息 setter 方法 */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(OrderOnwayHeaderVO.PK_ORG, pk_org);
  }

  /** 采购组织 setter 方法 */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(OrderOnwayHeaderVO.PK_ORG_V, pk_org_v);
  }

  /** 付款协议 setter 方法 */
  public void setPk_payterm(String pk_payterm) {
    this.setAttributeValue(OrderOnwayHeaderVO.PK_PAYTERM, pk_payterm);
  }

  /** 项目 setter 方法 */
  public void setPk_project(String pk_project) {
    this.setAttributeValue(OrderOnwayHeaderVO.PK_PROJECT, pk_project);
  }

  /** 收货客户 setter 方法 */
  public void setPk_recvcustomer(String pk_recvcustomer) {
    this.setAttributeValue(OrderOnwayHeaderVO.PK_RECVCUSTOMER, pk_recvcustomer);
  }

  /** 供应商 setter 方法 */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(OrderOnwayHeaderVO.PK_SUPPLIER, pk_supplier);
  }

  /** 运输方式 setter 方法 */
  public void setPk_transporttype(String pk_transporttype) {
    this.setAttributeValue(OrderOnwayHeaderVO.PK_TRANSPORTTYPE,
        pk_transporttype);
  }

  /** 审批日期 setter 方法 */
  public void setTaudittime(UFDate taudittime) {
    this.setAttributeValue(OrderOnwayHeaderVO.TAUDITTIME, taudittime);
  }

  /** 冻结日期 setter 方法 */
  public void setTfreezetime(UFDate tfreezetime) {
    this.setAttributeValue(OrderOnwayHeaderVO.TFREEZETIME, tfreezetime);
  }

  /** 修订日期 setter 方法 */
  public void setTrevisiontime(UFDate trevisiontime) {
    this.setAttributeValue(OrderOnwayHeaderVO.TREVISIONTIME, trevisiontime);
  }

  /** ts setter 方法 */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(OrderOnwayHeaderVO.TS, ts);
  }

  /** 订单编号 setter 方法 */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(OrderOnwayHeaderVO.VBILLCODE, vbillcode);
  }

  /** 对方订单号 setter 方法 */
  public void setVcoopordercode(String vcoopordercode) {
    this.setAttributeValue(OrderOnwayHeaderVO.VCOOPORDERCODE, vcoopordercode);
  }

  /** 冻结原因 setter 方法 */
  public void setVfreezereason(String vfreezereason) {
    this.setAttributeValue(OrderOnwayHeaderVO.VFREEZEREASON, vfreezereason);
  }

  /** 自定义项1 setter 方法 */
  public void setVhdef1(String vhdef1) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF1, vhdef1);
  }

  /** 自定义项10 setter 方法 */
  public void setVhdef10(String vhdef10) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF10, vhdef10);
  }

  /** 自定义项11 setter 方法 */
  public void setVhdef11(String vhdef11) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF11, vhdef11);
  }

  /** 自定义项12 setter 方法 */
  public void setVhdef12(String vhdef12) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF12, vhdef12);
  }

  /** 自定义项13 setter 方法 */
  public void setVhdef13(String vhdef13) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF13, vhdef13);
  }

  /** 自定义项14 setter 方法 */
  public void setVhdef14(String vhdef14) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF14, vhdef14);
  }

  /** 自定义项15 setter 方法 */
  public void setVhdef15(String vhdef15) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF15, vhdef15);
  }

  /** 自定义项16 setter 方法 */
  public void setVhdef16(String vhdef16) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF16, vhdef16);
  }

  /** 自定义项17 setter 方法 */
  public void setVhdef17(String vhdef17) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF17, vhdef17);
  }

  /** 自定义项18 setter 方法 */
  public void setVhdef18(String vhdef18) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF18, vhdef18);
  }

  /** 自定义项19 setter 方法 */
  public void setVhdef19(String vhdef19) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF19, vhdef19);
  }

  /** 自定义项2 setter 方法 */
  public void setVhdef2(String vhdef2) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF2, vhdef2);
  }

  /** 自定义项20 setter 方法 */
  public void setVhdef20(String vhdef20) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF20, vhdef20);
  }

  /** 自定义项21 setter 方法 */
  public void setVhdef21(String vhdef21) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF21, vhdef21);
  }

  /** 自定义项22 setter 方法 */
  public void setVhdef22(String vhdef22) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF22, vhdef22);
  }

  /** 自定义项23 setter 方法 */
  public void setVhdef23(String vhdef23) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF23, vhdef23);
  }

  /** 自定义项24 setter 方法 */
  public void setVhdef24(String vhdef24) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF24, vhdef24);
  }

  /** 自定义项25 setter 方法 */
  public void setVhdef25(String vhdef25) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF25, vhdef25);
  }

  /** 自定义项26 setter 方法 */
  public void setVhdef26(String vhdef26) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF26, vhdef26);
  }

  /** 自定义项27 setter 方法 */
  public void setVhdef27(String vhdef27) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF27, vhdef27);
  }

  /** 自定义项28 setter 方法 */
  public void setVhdef28(String vhdef28) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF28, vhdef28);
  }

  /** 自定义项29 setter 方法 */
  public void setVhdef29(String vhdef29) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF29, vhdef29);
  }

  /** 自定义项3 setter 方法 */
  public void setVhdef3(String vhdef3) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF3, vhdef3);
  }

  /** 自定义项30 setter 方法 */
  public void setVhdef30(String vhdef30) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF30, vhdef30);
  }

  /** 自定义项31 setter 方法 */
  public void setVhdef31(String vhdef31) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF31, vhdef31);
  }

  /** 自定义项32 setter 方法 */
  public void setVhdef32(String vhdef32) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF32, vhdef32);
  }

  /** 自定义项33 setter 方法 */
  public void setVhdef33(String vhdef33) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF33, vhdef33);
  }

  /** 自定义项34 setter 方法 */
  public void setVhdef34(String vhdef34) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF34, vhdef34);
  }

  /** 自定义项35 setter 方法 */
  public void setVhdef35(String vhdef35) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF35, vhdef35);
  }

  /** 自定义项36 setter 方法 */
  public void setVhdef36(String vhdef36) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF36, vhdef36);
  }

  /** 自定义项37 setter 方法 */
  public void setVhdef37(String vhdef37) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF37, vhdef37);
  }

  /** 自定义项38 setter 方法 */
  public void setVhdef38(String vhdef38) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF38, vhdef38);
  }

  /** 自定义项39 setter 方法 */
  public void setVhdef39(String vhdef39) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF39, vhdef39);
  }

  /** 自定义项4 setter 方法 */
  public void setVhdef4(String vhdef4) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF4, vhdef4);
  }

  /** 自定义项40 setter 方法 */
  public void setVhdef40(String vhdef40) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF40, vhdef40);
  }

  /** 自定义项5 setter 方法 */
  public void setVhdef5(String vhdef5) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF5, vhdef5);
  }

  /** 自定义项6 setter 方法 */
  public void setVhdef6(String vhdef6) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF6, vhdef6);
  }

  /** 自定义项7 setter 方法 */
  public void setVhdef7(String vhdef7) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF7, vhdef7);
  }

  /** 自定义项8 setter 方法 */
  public void setVhdef8(String vhdef8) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF8, vhdef8);
  }

  /** 自定义项9 setter 方法 */
  public void setVhdef9(String vhdef9) {
    this.setAttributeValue(OrderOnwayHeaderVO.VHDEF9, vhdef9);
  }

  /** 备注 setter 方法 */
  public void setVmemo(String vmemo) {
    this.setAttributeValue(OrderOnwayHeaderVO.VMEMO, vmemo);
  }

  /** 订单类型 setter 方法 */
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(OrderOnwayHeaderVO.VTRANTYPECODE, vtrantypecode);
  }

}
