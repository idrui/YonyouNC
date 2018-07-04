/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-6-26 下午02:55:09
 */
package nc.vo.pu.m25.entity;

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
 * @time 2009-6-26 下午02:55:09
 */
public class InvoiceHeaderVO extends SuperVO {
  /** 审批人 */
  public static final String APPROVER = "approver";

  /** 已传应付标志 */
  public static final String BAPFLAG = "bapflag";

  /** 费用发票 */
  public static final String BFEE = "bfee";

  /** 冻结 */
  public static final String BFROZEN = "bfrozen";

  /** 制单人 */
  public static final String BILLMAKER = "billmaker";

  /** 是否期初发票 */
  public static final String BINITIAL = "binitial";

  /** 逆向征税 */
  public static final String BOPPTAXFLAG = "bopptaxflag";

  /** 三角贸易 */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /** 虚拟发票标志 */
  public static final String BVIRTUAL = "bvirtual";

  /** 本币币种(本位币) */
  public static final String CCURRENCYID = "ccurrencyid";

  /** 币种 */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /** 创建时间 */
  public static final String CREATIONTIME = "creationtime";

  /** 创建人 */
  public static final String CREATOR = "creator";

  /** 收货国家/地区 */
  public static final String CRECECOUNTRYID = "crececountryid";

  /** 发货国/地区 */
  public static final String CSENDCOUNTRYID = "csendcountryid";

  /** 报税国/地区 */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /** 贸易术语 */
  public static final String CTRADEWORDID = "ctradewordid";

  /** 交易类型 */
  public static final String CTRANTYPEID = "ctrantypeid";

  /** 票到日期 */
  public static final String DARRIVEDATE = "darrivedate";

  /** 发票日期 */
  public static final String DBILLDATE = "dbilldate";

  /** 制单日期 */
  public static final String DMAKEDATE = "dmakedate";

  /** dr */
  public static final String DR = "dr";

  /** 单据状态 */
  public static final String FBILLSTATUS = "fbillstatus";

  /** 购销类型 */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /** 发票分类 */
  public static final String FINVOICECLASS = "finvoiceclass";

  /** 发票归属 */
  public static final String FINVOICETYPE = "finvoicetype";

  /** 整单扣税类别 */
  public static final String FTAXTYPEFLAGH = "ftaxtypeflagh";

  /** 打印次数 */
  public static final String IPRINTCOUNT = "iprintcount";

  /** 最后修改时间 */
  public static final String MODIFIEDTIME = "modifiedtime";

  /** 最后修改人 */
  public static final String MODIFIER = "modifier";

  /** 汇率 */
  public static final String NEXCHANGERATE = "nexchangerate";

  /** 全局本位币汇率 */
  public static final String NGLOBALEXCHGRATE = "nglobalexchgrate";

  /** 集团本位币汇率 */
  public static final String NGROUPEXCHGRATE = "ngroupexchgrate";

  /** 整单税率 */
  public static final String NTAXRATEH = "ntaxrateh";

  /** 整单数量 */
  public static final String NTOTALASTNUM = "ntotalastnum";

  /** 整单价税合计(原币) */
  public static final String NTOTALORIGMNY = "ntotalorigmny";
  
  /** 结算方式 */
  public static final String PK_BALATYPE = "pk_balatype";

  /** 银行账户 */
  public static final String PK_BANKACCBAS = "pk_bankaccbas";

  /** 业务员 */
  public static final String PK_BIZPSN = "pk_bizpsn";

  /** 业务流程 */
  public static final String PK_BUSITYPE = "pk_busitype";

  /** 采购部门(OID) */
  public static final String PK_DEPT = "pk_dept";

  /** 采购部门(VID) */
  public static final String PK_DEPT_V = "pk_dept_v";

  /** 散户 */
  public static final String PK_FREECUST = "pk_freecust";

  /** 冻结人 */
  public static final String PK_FROZENUSER = "pk_frozenuser";

  /** 所属集团 */
  public static final String PK_GROUP = "pk_group";

  /** 采购发票 */
  public static final String PK_INVOICE = "pk_invoice";

  /** 财务组织(OID) */
  public static final String PK_ORG = "pk_org";

  /** 财务组织(VID) */
  public static final String PK_ORG_V = "pk_org_v";

  /** 费用发票对应货物发票 */
  public static final String PK_PARENTINVOICE = "pk_parentinvoice";

  /** 付款协议 */
  public static final String PK_PAYTERM = "pk_payterm";

  /** 付款单位 */
  public static final String PK_PAYTOSUPPLIER = "pk_paytosupplier";

  /** 采购组织(OID) */
  public static final String PK_PURCHASEORG = "pk_purchaseorg";

  /** 采购组织(VID) */
  public static final String PK_PURCHASEORG_V = "pk_purchaseorg_v";

  /** 库存组织(OID) */
  public static final String PK_STOCKORG = "pk_stockorg";

  /** 库存组织(VID) */
  public static final String PK_STOCKORG_V = "pk_stockorg_v";

  /** 供应商 */
  public static final String PK_SUPPLIER = "pk_supplier";

  /** 审批日期 */
  public static final String TAUDITTIME = "taudittime";

  /** 冻结日期 */
  public static final String TFROZENTIME = "tfrozentime";

  /** 时间戳 */
  public static final String TS = "ts";

  /** 调整原因 */
  public static final String VADJUSTREASON = "vadjustreason";

  /** 银行账号 */
  public static final String VBANKACCOUNT = "vbankaccount";

  /** 发票号 */
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

  /** 最后冻结原因 */
  public static final String VFROZENREASON = "vfrozenreason";

  /** 备注 */
  public static final String VMEMO = "vmemo";

  /** 发票类型(交易类型) */
  public static final String VTRANTYPECODE = "vtrantypecode";

  /** VAT注册码 */
  public static final String VVATCODE = "vvatcode";

  /** 供应商VAT注册码 */
  public static final String VVENDORVATCODE = "vvendorvatcode";

  /**
   * 
   */
  private static final long serialVersionUID = -2862148524851751202L;

  /** 审批人 getter 方法 */
  public String getApprover() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.APPROVER);
  }

  /** 已传应付标志 getter 方法 */
  public UFBoolean getBapflag() {
    return (UFBoolean) this.getAttributeValue(InvoiceHeaderVO.BAPFLAG);
  }

  /** 费用发票 getter 方法 */
  public UFBoolean getBfee() {
    return (UFBoolean) this.getAttributeValue(InvoiceHeaderVO.BFEE);
  }

  /** 冻结 getter 方法 */
  public UFBoolean getBfrozen() {
    return (UFBoolean) this.getAttributeValue(InvoiceHeaderVO.BFROZEN);
  }

  /** 制单人 getter 方法 */
  public String getBillmaker() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.BILLMAKER);
  }

  /** 是否期初发票 getter 方法 */
  public UFBoolean getBinitial() {
    return (UFBoolean) this.getAttributeValue(InvoiceHeaderVO.BINITIAL);
  }

  /** 逆向征税 getter 方法 */
  public UFBoolean getBopptaxflag() {
    return (UFBoolean) this.getAttributeValue(InvoiceHeaderVO.BOPPTAXFLAG);
  }

  /** 三角贸易 getter 方法 */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(InvoiceHeaderVO.BTRIATRADEFLAG);
  }

  /** 虚拟发票标志 getter 方法 */
  public UFBoolean getBvirtual() {
    return (UFBoolean) this.getAttributeValue(InvoiceHeaderVO.BVIRTUAL);
  }

  /** 本币币种(本位币) getter 方法 */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.CCURRENCYID);
  }

  /** 币种 getter 方法 */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.CORIGCURRENCYID);
  }

  /** 创建时间 getter 方法 */
  public UFDateTime getCreationtime() {
    return (UFDateTime) this.getAttributeValue(InvoiceHeaderVO.CREATIONTIME);
  }

  /** 创建人 getter 方法 */
  public String getCreator() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.CREATOR);
  }

  /** 收货国家/地区 getter 方法 */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.CRECECOUNTRYID);
  }

  /** 发货国/地区 getter 方法 */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.CSENDCOUNTRYID);
  }

  /** 报税国/地区 getter 方法 */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.CTAXCOUNTRYID);
  }

  /** 贸易术语 getter 方法 */
  public String getCtradewordid() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.CTRADEWORDID);
  }

  /** 交易类型 getter 方法 */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.CTRANTYPEID);
  }

  /** 票到日期 getter 方法 */
  public UFDate getDarrivedate() {
    return (UFDate) this.getAttributeValue(InvoiceHeaderVO.DARRIVEDATE);
  }

  /** 发票日期 getter 方法 */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(InvoiceHeaderVO.DBILLDATE);
  }

  /** 制单日期 **/
  public UFDate getDmakedate() {
    return (UFDate) this.getAttributeValue(InvoiceHeaderVO.DMAKEDATE);
  }

  /** dr getter 方法 */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(InvoiceHeaderVO.DR);
  }

  /** 单据状态 getter 方法 */
  public Integer getFbillstatus() {
    return (Integer) this.getAttributeValue(InvoiceHeaderVO.FBILLSTATUS);
  }

  /** 购销类型 getter 方法 */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(InvoiceHeaderVO.FBUYSELLFLAG);
  }

  /** 发票分类 getter 方法 */
  public Integer getFinvoiceclass() {
    return (Integer) this.getAttributeValue(InvoiceHeaderVO.FINVOICECLASS);
  }

  /** 获取发票归属 getter 方法 */
  public Integer getFinvoicetype() {
    return (Integer) this.getAttributeValue(InvoiceHeaderVO.FINVOICETYPE);
  }

  /** 整单扣税类别 getter 方法 */
  public Integer getFtaxtypeflagh() {
    return (Integer) this.getAttributeValue(InvoiceHeaderVO.FTAXTYPEFLAGH);
  }

  /** 打印次数 getter 方法 */
  public Integer getIprintcount() {
    return (Integer) this.getAttributeValue(InvoiceHeaderVO.IPRINTCOUNT);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.ISuperVO#getMetaData()
   */
  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta(PUEntity.M25_H);
    return meta;
  }

  /** 最后修改时间 getter 方法 */
  public UFDateTime getModifiedtime() {
    return (UFDateTime) this.getAttributeValue(InvoiceHeaderVO.MODIFIEDTIME);
  }

  /** 最后修改人 getter 方法 */
  public String getModifier() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.MODIFIER);
  }

  /** 汇率 getter 方法 */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(InvoiceHeaderVO.NEXCHANGERATE);
  }

  /** 全局本位币汇率 getter 方法 */
  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this.getAttributeValue(InvoiceHeaderVO.NGLOBALEXCHGRATE);
  }

  /** 集团本位币汇率 getter 方法 */
  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this.getAttributeValue(InvoiceHeaderVO.NGROUPEXCHGRATE);
  }

  /** 整单税率 getter 方法 */
  public UFDouble getNtaxrateh() {
    return (UFDouble) this.getAttributeValue(InvoiceHeaderVO.NTAXRATEH);
  }

  /** 整单数量 getter 方法 */
  public UFDouble getNtotalastnum() {
    return (UFDouble) this.getAttributeValue(InvoiceHeaderVO.NTOTALASTNUM);
  }

  /** 整单价税合计(原币) getter 方法 */
  public UFDouble getNtotalorigmny() {
    return (UFDouble) this.getAttributeValue(InvoiceHeaderVO.NTOTALORIGMNY);
  }
  
  /** 结算方式 getter 方法 */
  public String getPk_balatype() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_BALATYPE);
  }

  /** 银行账户 getter 方法 */
  public String getPk_bankaccbas() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_BANKACCBAS);
  }

  /** 业务员 getter 方法 */
  public String getPk_bizpsn() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_BIZPSN);
  }

  /** 业务流程 getter 方法 */
  public String getPk_busitype() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_BUSITYPE);
  }

  /** 采购部门(OID) getter 方法 */
  public String getPk_dept() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_DEPT);
  }

  /** 采购部门(VID) getter 方法 */
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_DEPT_V);
  }

  /** 散户 getter 方法 */
  public String getPk_freecust() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_FREECUST);
  }

  /** 冻结人 getter 方法 */
  public String getPk_frozenuser() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_FROZENUSER);
  }

  /** 所属集团 getter 方法 */
  public String getPk_group() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_GROUP);
  }

  /** 采购发票 getter 方法 */
  public String getPk_invoice() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_INVOICE);
  }

  /** 财务组织(OID) getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_ORG);
  }

  /** 财务组织(VID) getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_ORG_V);
  }

  /** 费用发票对应货物发票 getter 方法 */
  public String getPk_parentinvoice() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_PARENTINVOICE);
  }

  /** 付款协议 getter 方法 */
  public String getPk_payterm() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_PAYTERM);
  }

  /** 付款单位 getter 方法 */
  public String getPk_paytosupplier() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_PAYTOSUPPLIER);
  }

  /** 采购组织(OID) getter 方法 */
  public String getPk_purchaseorg() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_PURCHASEORG);
  }

  /** 采购组织(VID) getter 方法 */
  public String getPk_purchaseorg_v() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_PURCHASEORG_V);
  }

  /** 库存组织(OID) getter 方法 */
  public String getPk_stockorg() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_STOCKORG);
  }

  /** 库存组织(VID) getter 方法 */
  public String getPk_stockorg_v() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_STOCKORG_V);
  }

  /** 供应商 getter 方法 */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_SUPPLIER);
  }

  /** 审批日期 getter 方法 */
  public UFDate getTaudittime() {
    return (UFDate) this.getAttributeValue(InvoiceHeaderVO.TAUDITTIME);
  }

  /** 冻结日期 getter 方法 */
  public UFDate getTfrozentime() {
    return (UFDate) this.getAttributeValue(InvoiceHeaderVO.TFROZENTIME);
  }

  /** 时间戳 getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(InvoiceHeaderVO.TS);
  }

  /** 获取调整原因 getter 方法 */
  public String getVadjustreason() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VADJUSTREASON);
  }

  public String getVbankaccount() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VBANKACCOUNT);
  }

  /** 发票号 getter 方法 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VBILLCODE);
  }

  /** 自定义项1 getter 方法 */
  public String getVdef1() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF1);
  }

  /** 自定义项10 getter 方法 */
  public String getVdef10() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF10);
  }

  /** 自定义项11 getter 方法 */
  public String getVdef11() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF11);
  }

  /** 自定义项12 getter 方法 */
  public String getVdef12() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF12);
  }

  /** 自定义项13 getter 方法 */
  public String getVdef13() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF13);
  }

  /** 自定义项14 getter 方法 */
  public String getVdef14() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF14);
  }

  /** 自定义项15 getter 方法 */
  public String getVdef15() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF15);
  }

  /** 自定义项16 getter 方法 */
  public String getVdef16() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF16);
  }

  /** 自定义项17 getter 方法 */
  public String getVdef17() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF17);
  }

  /** 自定义项18 getter 方法 */
  public String getVdef18() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF18);
  }

  /** 自定义项19 getter 方法 */
  public String getVdef19() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF19);
  }

  /** 自定义项2 getter 方法 */
  public String getVdef2() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF2);
  }

  /** 自定义项20 getter 方法 */
  public String getVdef20() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF20);
  }

  /** 自定义项3 getter 方法 */
  public String getVdef3() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF3);
  }

  /** 自定义项4 getter 方法 */
  public String getVdef4() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF4);
  }

  /** 自定义项5 getter 方法 */
  public String getVdef5() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF5);
  }

  /** 自定义项6 getter 方法 */
  public String getVdef6() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF6);
  }

  /** 自定义项7 getter 方法 */
  public String getVdef7() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF7);
  }

  /** 自定义项8 getter 方法 */
  public String getVdef8() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF8);
  }

  /** 自定义项9 getter 方法 */
  public String getVdef9() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF9);
  }

  /** 最后冻结原因 getter 方法 */
  public String getVfrozenreason() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VFROZENREASON);
  }

  /** 备注 getter 方法 */
  public String getVmemo() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VMEMO);
  }

  /** 发票类型(交易类型) getter 方法 */
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VTRANTYPECODE);
  }

  /** VAT注册码 getter 方法 */
  public String getVvatcode() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VVATCODE);
  }

  /** 供应商VAT注册码 getter 方法 */
  public String getVvendorvatcode() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VVENDORVATCODE);
  }

  /** 审批人 setter 方法 */
  public void setApprover(String approver) {
    this.setAttributeValue(InvoiceHeaderVO.APPROVER, approver);
  }

  /** 已传应付标志 setter 方法 */
  public void setBapflag(UFBoolean bapflag) {
    this.setAttributeValue(InvoiceHeaderVO.BAPFLAG, bapflag);
  }

  /** 费用发票 setter 方法 */
  public void setBfee(UFBoolean bfee) {
    this.setAttributeValue(InvoiceHeaderVO.BFEE, bfee);
  }

  /** 冻结 setter 方法 */
  public void setBfrozen(UFBoolean bfrozen) {
    this.setAttributeValue(InvoiceHeaderVO.BFROZEN, bfrozen);
  }

  /** 制单人 setter 方法 */
  public void setBillmaker(String billmaker) {
    this.setAttributeValue(InvoiceHeaderVO.BILLMAKER, billmaker);
  }

  /** 是否期初发票 setter 方法 */
  public void setBinitial(UFBoolean binitial) {
    this.setAttributeValue(InvoiceHeaderVO.BINITIAL, binitial);
  }

  /** 逆向征税 setter 方法 */
  public void setBopptaxflag(UFBoolean bopptaxflag) {
    this.setAttributeValue(InvoiceHeaderVO.BOPPTAXFLAG, bopptaxflag);
  }

  /** 三角贸易 setter 方法 */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(InvoiceHeaderVO.BTRIATRADEFLAG, btriatradeflag);
  }

  /** 虚拟发票标志 setter 方法 */
  public void setBvirtual(UFBoolean bvirtual) {
    this.setAttributeValue(InvoiceHeaderVO.BVIRTUAL, bvirtual);
  }

  /** 本币币种(本位币) setter 方法 */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(InvoiceHeaderVO.CCURRENCYID, ccurrencyid);
  }

  /** 币种 setter 方法 */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(InvoiceHeaderVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /** 创建时间 setter 方法 */
  public void setCreationtime(UFDateTime creationtime) {
    this.setAttributeValue(InvoiceHeaderVO.CREATIONTIME, creationtime);
  }

  /** 创建人 setter 方法 */
  public void setCreator(String creator) {
    this.setAttributeValue(InvoiceHeaderVO.CREATOR, creator);
  }

  /** 收货国家/地区 setter 方法 */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(InvoiceHeaderVO.CRECECOUNTRYID, crececountryid);
  }

  /** 发货国/地区 setter 方法 */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(InvoiceHeaderVO.CSENDCOUNTRYID, csendcountryid);
  }

  /** 报税国/地区 setter 方法 */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(InvoiceHeaderVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  /** 贸易术语 setter 方法 */
  public void setCtradewordid(String ctradewordid) {
    this.setAttributeValue(InvoiceHeaderVO.CTRADEWORDID, ctradewordid);
  }

  /** 交易类型 setter 方法 */
  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(InvoiceHeaderVO.CTRANTYPEID, ctrantypeid);
  }

  /** 票到日期 setter 方法 */
  public void setDarrivedate(UFDate darrivedate) {
    this.setAttributeValue(InvoiceHeaderVO.DARRIVEDATE, darrivedate);
  }

  /** 发票日期 setter 方法 */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(InvoiceHeaderVO.DBILLDATE, dbilldate);
  }

  /** 制单日期 **/
  public void setDmakedate(UFDate dmakedate) {
    this.setAttributeValue(InvoiceHeaderVO.DMAKEDATE, dmakedate);
  }

  /** dr setter 方法 */
  public void setDr(Integer dr) {
    this.setAttributeValue(InvoiceHeaderVO.DR, dr);
  }

  /** 单据状态 setter 方法 */
  public void setFbillstatus(Integer fbillstatus) {
    this.setAttributeValue(InvoiceHeaderVO.FBILLSTATUS, fbillstatus);
  }

  /** 购销类型 setter 方法 */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(InvoiceHeaderVO.FBUYSELLFLAG, fbuysellflag);
  }

  /** 发票分类 setter 方法 */
  public void setFinvoiceclass(Integer finvoiceclass) {
    this.setAttributeValue(InvoiceHeaderVO.FINVOICECLASS, finvoiceclass);
  }

  /** 设置发票归属 setter 方法 */
  public void setFinvoicetype(Integer finvoicetype) {
    this.setAttributeValue(InvoiceHeaderVO.FINVOICETYPE, finvoicetype);
  }

  /** 整单扣税类别 setter 方法 */
  public void setFtaxtypeflagh(Integer ftaxtypeflagh) {
    this.setAttributeValue(InvoiceHeaderVO.FTAXTYPEFLAGH, ftaxtypeflagh);
  }

  /** 打印次数 setter 方法 */
  public void setIprintcount(Integer iprintcount) {
    this.setAttributeValue(InvoiceHeaderVO.IPRINTCOUNT, iprintcount);
  }

  /** 最后修改时间 setter 方法 */
  public void setModifiedtime(UFDateTime modifiedtime) {
    this.setAttributeValue(InvoiceHeaderVO.MODIFIEDTIME, modifiedtime);
  }

  /** 最后修改人 setter 方法 */
  public void setModifier(String modifier) {
    this.setAttributeValue(InvoiceHeaderVO.MODIFIER, modifier);
  }

  /** 汇率 setter 方法 */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(InvoiceHeaderVO.NEXCHANGERATE, nexchangerate);
  }

  /** 全局本位币汇率 setter 方法 */
  public void setNglobalexchgrate(UFDouble nglobalexchgrate) {
    this.setAttributeValue(InvoiceHeaderVO.NGLOBALEXCHGRATE, nglobalexchgrate);
  }

  /** 集团本位币汇率 setter 方法 */
  public void setNgroupexchgrate(UFDouble ngroupexchgrate) {
    this.setAttributeValue(InvoiceHeaderVO.NGROUPEXCHGRATE, ngroupexchgrate);
  }

  /** 整单税率 setter 方法 */
  public void setNtaxrateh(UFDouble ntaxrateh) {
    this.setAttributeValue(InvoiceHeaderVO.NTAXRATEH, ntaxrateh);
  }

  /** 整单数量 setter 方法 */
  public void setNtotalastnum(UFDouble ntotalastnum) {
    this.setAttributeValue(InvoiceHeaderVO.NTOTALASTNUM, ntotalastnum);
  }

  /** 整单价税合计(原币) setter 方法 */
  public void setNtotalorigmny(UFDouble ntotalorigmny) {
    this.setAttributeValue(InvoiceHeaderVO.NTOTALORIGMNY, ntotalorigmny);
  }
  
  /** 结算方式 setter 方法 */
  public void setPk_balatype(String pk_balatype) {
    this.setAttributeValue(InvoiceHeaderVO.PK_BALATYPE, pk_balatype);
  }

  /** 银行账户 setter 方法 */
  public void setPk_bankaccbas(String pk_bankaccbas) {
    this.setAttributeValue(InvoiceHeaderVO.PK_BANKACCBAS, pk_bankaccbas);
  }

  /** 业务员 setter 方法 */
  public void setPk_bizpsn(String pk_bizpsn) {
    this.setAttributeValue(InvoiceHeaderVO.PK_BIZPSN, pk_bizpsn);
  }

  /** 业务流程 setter 方法 */
  public void setPk_busitype(String pk_busitype) {
    this.setAttributeValue(InvoiceHeaderVO.PK_BUSITYPE, pk_busitype);
  }

  /** 采购部门(OID) setter 方法 */
  public void setPk_dept(String pk_dept) {
    this.setAttributeValue(InvoiceHeaderVO.PK_DEPT, pk_dept);
  }

  /** 采购部门(VID) setter 方法 */
  public void setPk_dept_v(String pk_dept_v) {
    this.setAttributeValue(InvoiceHeaderVO.PK_DEPT_V, pk_dept_v);
  }

  /** 散户 setter 方法 */
  public void setPk_freecust(String pk_freecust) {
    this.setAttributeValue(InvoiceHeaderVO.PK_FREECUST, pk_freecust);
  }

  /** 冻结人 setter 方法 */
  public void setPk_frozenuser(String pk_frozenuser) {
    this.setAttributeValue(InvoiceHeaderVO.PK_FROZENUSER, pk_frozenuser);
  }

  /** 所属集团 setter 方法 */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(InvoiceHeaderVO.PK_GROUP, pk_group);
  }

  /** 采购发票 setter 方法 */
  public void setPk_invoice(String pk_invoice) {
    this.setAttributeValue(InvoiceHeaderVO.PK_INVOICE, pk_invoice);
  }

  /** 财务组织(OID) setter 方法 */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(InvoiceHeaderVO.PK_ORG, pk_org);
  }

  /** 财务组织(VID) setter 方法 */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(InvoiceHeaderVO.PK_ORG_V, pk_org_v);
  }

  /** 费用发票对应货物发票 setter 方法 */
  public void setPk_parentinvoice(String pk_parentinvoice) {
    this.setAttributeValue(InvoiceHeaderVO.PK_PARENTINVOICE, pk_parentinvoice);
  }

  /** 付款协议 setter 方法 */
  public void setPk_payterm(String pk_payterm) {
    this.setAttributeValue(InvoiceHeaderVO.PK_PAYTERM, pk_payterm);
  }

  /** 付款单位 setter 方法 */
  public void setPk_paytosupplier(String pk_paytosupplier) {
    this.setAttributeValue(InvoiceHeaderVO.PK_PAYTOSUPPLIER, pk_paytosupplier);
  }

  /** 采购组织(OID) setter 方法 */
  public void setPk_purchaseorg(String pk_purchaseorg) {
    this.setAttributeValue(InvoiceHeaderVO.PK_PURCHASEORG, pk_purchaseorg);
  }

  /** 采购组织(VID) setter 方法 */
  public void setPk_purchaseorg_v(String pk_purchaseorg_v) {
    this.setAttributeValue(InvoiceHeaderVO.PK_PURCHASEORG_V, pk_purchaseorg_v);
  }

  /** 库存组织(OID) setter 方法 */
  public void setPk_stockorg(String pk_stockorg) {
    this.setAttributeValue(InvoiceHeaderVO.PK_STOCKORG, pk_stockorg);
  }

  /** 库存组织(VID) setter 方法 */
  public void setPk_stockorg_v(String pk_stockorg_v) {
    this.setAttributeValue(InvoiceHeaderVO.PK_STOCKORG_V, pk_stockorg_v);
  }

  /** 供应商 setter 方法 */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(InvoiceHeaderVO.PK_SUPPLIER, pk_supplier);
  }

  /** 审批日期 setter 方法 */
  public void setTaudittime(UFDate taudittime) {
    this.setAttributeValue(InvoiceHeaderVO.TAUDITTIME, taudittime);
  }

  /** 冻结日期 setter 方法 */
  public void setTfrozentime(UFDate tfrozentime) {
    this.setAttributeValue(InvoiceHeaderVO.TFROZENTIME, tfrozentime);
  }

  /** 时间戳 setter 方法 */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(InvoiceHeaderVO.TS, ts);
  }

  /** 设置调整原因 setter 方法 */
  public void setVadjustreason(String vadjustreason) {
    this.setAttributeValue(InvoiceHeaderVO.VADJUSTREASON, vadjustreason);
  }

  public void setVbankaccount(String vbankaccount) {
    this.setAttributeValue(InvoiceHeaderVO.VBANKACCOUNT, vbankaccount);
  }

  /** 发票号 setter 方法 */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(InvoiceHeaderVO.VBILLCODE, vbillcode);
  }

  /** 自定义项1 setter 方法 */
  public void setVdef1(String vdef1) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF1, vdef1);
  }

  /** 自定义项10 setter 方法 */
  public void setVdef10(String vdef10) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF10, vdef10);
  }

  /** 自定义项11 setter 方法 */
  public void setVdef11(String vdef11) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF11, vdef11);
  }

  /** 自定义项12 setter 方法 */
  public void setVdef12(String vdef12) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF12, vdef12);
  }

  /** 自定义项13 setter 方法 */
  public void setVdef13(String vdef13) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF13, vdef13);
  }

  /** 自定义项14 setter 方法 */
  public void setVdef14(String vdef14) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF14, vdef14);
  }

  /** 自定义项15 setter 方法 */
  public void setVdef15(String vdef15) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF15, vdef15);
  }

  /** 自定义项16 setter 方法 */
  public void setVdef16(String vdef16) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF16, vdef16);
  }

  /** 自定义项17 setter 方法 */
  public void setVdef17(String vdef17) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF17, vdef17);
  }

  /** 自定义项18 setter 方法 */
  public void setVdef18(String vdef18) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF18, vdef18);
  }

  /** 自定义项19 setter 方法 */
  public void setVdef19(String vdef19) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF19, vdef19);
  }

  /** 自定义项2 setter 方法 */
  public void setVdef2(String vdef2) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF2, vdef2);
  }

  /** 自定义项20 setter 方法 */
  public void setVdef20(String vdef20) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF20, vdef20);
  }

  /** 自定义项3 setter 方法 */
  public void setVdef3(String vdef3) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF3, vdef3);
  }

  /** 自定义项4 setter 方法 */
  public void setVdef4(String vdef4) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF4, vdef4);
  }

  /** 自定义项5 setter 方法 */
  public void setVdef5(String vdef5) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF5, vdef5);
  }

  /** 自定义项6 setter 方法 */
  public void setVdef6(String vdef6) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF6, vdef6);
  }

  /** 自定义项7 setter 方法 */
  public void setVdef7(String vdef7) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF7, vdef7);
  }

  /** 自定义项8 setter 方法 */
  public void setVdef8(String vdef8) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF8, vdef8);
  }

  /** 自定义项9 setter 方法 */
  public void setVdef9(String vdef9) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF9, vdef9);
  }

  /** 最后冻结原因 setter 方法 */
  public void setVfrozenreason(String vfrozenreason) {
    this.setAttributeValue(InvoiceHeaderVO.VFROZENREASON, vfrozenreason);
  }

  /** 备注 setter 方法 */
  public void setVmemo(String vmemo) {
    this.setAttributeValue(InvoiceHeaderVO.VMEMO, vmemo);
  }

  /** 发票类型(交易类型) setter 方法 */
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(InvoiceHeaderVO.VTRANTYPECODE, vtrantypecode);
  }

  /** VAT注册码 setter 方法 */
  public void setVvatcode(String vvatcode) {
    this.setAttributeValue(InvoiceHeaderVO.VVATCODE, vvatcode);
  }

  /** 供应商VAT注册码 setter 方法 */
  public void setVvendorvatcode(String vvendorvatcode) {
    this.setAttributeValue(InvoiceHeaderVO.VVENDORVATCODE, vvendorvatcode);
  }
}
