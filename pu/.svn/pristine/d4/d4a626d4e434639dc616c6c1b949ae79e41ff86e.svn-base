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
 * 采购发票结算实体
 * 
 * @since 6.3
 * @version 2013-08-19 14:41:28
 * @author zhangyhh
 */
public class InvoiceSettleItemVO extends SuperVO {

  /**
   * 预付款
   */
  public static final String BPREFLAG = "bpreflag";

  /**
   * 起效日期
   */
  public static final String CINCOMEPERIOD = "cincomeperiod";

  /**
   * 行号
   */
  public static final String CSETTLEROWNO = "csettlerowno";

  /**
   * 结算方式
   */
  public static final String CSETTLETYPE = "csettletype";

  /**
   * 预计付款日期
   */
  public static final String DPAYDATE = "dpaydate";

  /**
   * 起效日期对应单据
   */
  public static final String FEFFECTDATEBILL = "feffectdatebill";

  /**
   * 生效月
   */
  public static final String FEFFECTMONTH = "feffectmonth";

  /**
   * 固定结账日
   */
  public static final String ICHECKDATA = "icheckdata";

  /**
   * 起效日期延期天数
   */
  public static final String IEFFECTADDDAY = "ieffectaddday";

  /**
   * 附加月
   */
  public static final String IEFFECTADDMONTH = "ieffectaddmonth";

  /**
   * 账期天数
   */
  public static final String IPAYMENTDAY = "ipaymentday";

  /**
   * 预计付款金额
   */
  public static final String NPAYMNY = "npaymny";

  /**
   * 付款比例
   */
  public static final String NPAYRATE = "npayrate";

  /**
   * 财务组织
   */
  public static final String PK_APFINANCEORG = "pk_apfinanceorg";

  /**
   * 财务组织
   */
  public static final String PK_APFINANCEORG_V = "pk_apfinanceorg_v";

  /**
   * 所属集团
   */
  public static final String PK_GROUP = "pk_group";

  /** 发票主实体 */
  public static final String PK_INVOICE = "pk_invoice";

  /**
   * 采购发票结算
   */
  public static final String PK_INVOICE_SETTLE = "pk_invoice_settle";

  /**
   * 时间戳
   */
  public static final String TS = "ts";

  /**
   * 备注
   */
  public static final String VMEMO = "vmemo";

  /**
   * 付款期
   */
  public static final String VPAYDATE = "vpaydate";

  /**
   * 
   */
  private static final long serialVersionUID = -2964724531606587111L;

  /**
   * 获取预付款
   * 
   * @return 预付款
   */
  public UFBoolean getBpreflag() {
    return (UFBoolean) this.getAttributeValue(InvoiceSettleItemVO.BPREFLAG);
  }

  /**
   * 获取起效日期
   * 
   * @return 起效日期
   */
  public String getCincomeperiod() {
    return (String) this.getAttributeValue(InvoiceSettleItemVO.CINCOMEPERIOD);
  }

  /**
   * 获取行号
   * 
   * @return 行号
   */
  public String getCsettlerowno() {
    return (String) this.getAttributeValue(InvoiceSettleItemVO.CSETTLEROWNO);
  }

  /**
   * 获取结算方式
   * 
   * @return 结算方式
   */
  public String getCsettletype() {
    return (String) this.getAttributeValue(InvoiceSettleItemVO.CSETTLETYPE);
  }

  /**
   * 预计付款日期
   * 
   * @return 预计付款日期
   */
  public UFDate getDpaydate() {
    return (UFDate) this.getAttributeValue(InvoiceSettleItemVO.DPAYDATE);
  }

  /**
   * 获取起效日期对应单据
   * 
   * @return 起效日期对应单据
   * @see EffectDateBill
   */
  public Integer getFeffectdatebill() {
    return (Integer) this
        .getAttributeValue(InvoiceSettleItemVO.FEFFECTDATEBILL);
  }

  /**
   * 获取生效月
   * 
   * @return 生效月
   */
  public Integer getFeffectmonth() {
    return (Integer) this.getAttributeValue(InvoiceSettleItemVO.FEFFECTMONTH);
  }

  /**
   * 获取固定结账日
   * 
   * @return 固定结账日
   */
  public Integer getIcheckdata() {
    return (Integer) this.getAttributeValue(InvoiceSettleItemVO.ICHECKDATA);
  }

  /**
   * 获取起效日期延期天数
   * 
   * @return 起效日期延期天数
   */
  public Integer getIeffectaddday() {
    return (Integer) this.getAttributeValue(InvoiceSettleItemVO.IEFFECTADDDAY);
  }

  /**
   * 获取附加月
   * 
   * @return 附加月
   */
  public Integer getIeffectaddmonth() {
    return (Integer) this
        .getAttributeValue(InvoiceSettleItemVO.IEFFECTADDMONTH);
  }

  /**
   * 获取账期天数
   * 
   * @return 账期天数
   */
  public Integer getIpaymentday() {
    return (Integer) this.getAttributeValue(InvoiceSettleItemVO.IPAYMENTDAY);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.ISuperVO#getMetaData()
   */
  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta(PUEntity.M25_SETTLE);
    return meta;
  }

  /**
   * 预计付款金额
   * 
   * @return 预计付款金额
   */
  public UFDouble getNpaymny() {
    return (UFDouble) this.getAttributeValue(InvoiceSettleItemVO.NPAYMNY);
  }

  /**
   * 获取付款比例
   * 
   * @return 付款比例
   */
  public UFDouble getNpayrate() {
    return (UFDouble) this.getAttributeValue(InvoiceSettleItemVO.NPAYRATE);
  }

  /**
   * 获取财务组织
   * 
   * @return 财务组织
   */
  public String getPk_apfinanceorg() {
    return (String) this.getAttributeValue(InvoiceSettleItemVO.PK_APFINANCEORG);
  }

  /**
   * 获取财务组织
   * 
   * @return 财务组织
   */
  public String getPk_apfinanceorg_v() {
    return (String) this
        .getAttributeValue(InvoiceSettleItemVO.PK_APFINANCEORG_V);
  }

  /**
   * 获取所属集团
   * 
   * @return 所属集团
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(InvoiceSettleItemVO.PK_GROUP);
  }

  /** 发票主实体 getter 方法 */
  public String getPk_invoice() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_INVOICE);
  }

  /**
   * 获取采购发票结算
   * 
   * @return 采购发票结算
   */
  public String getPk_invoice_settle() {
    return (String) this
        .getAttributeValue(InvoiceSettleItemVO.PK_INVOICE_SETTLE);
  }

  /**
   * 获取时间戳
   * 
   * @return 时间戳
   */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(InvoiceSettleItemVO.TS);
  }

  /**
   * 获取备注
   * 
   * @return 备注
   */
  public String getVmemo() {
    return (String) this.getAttributeValue(InvoiceSettleItemVO.VMEMO);
  }

  /**
   * 获取付款期
   * 
   * @return 付款期
   */
  public String getVpaydate() {
    return (String) this.getAttributeValue(InvoiceSettleItemVO.VPAYDATE);
  }

  /**
   * 设置预付款
   * 
   * @param bpreflag 预付款
   */
  public void setBpreflag(UFBoolean bpreflag) {
    this.setAttributeValue(InvoiceSettleItemVO.BPREFLAG, bpreflag);
  }

  /**
   * 设置起效日期
   * 
   * @param cincomeperiod 起效日期
   */
  public void setCincomeperiod(String cincomeperiod) {
    this.setAttributeValue(InvoiceSettleItemVO.CINCOMEPERIOD, cincomeperiod);
  }

  /**
   * 设置行号
   * 
   * @param csettlerowno 行号
   */
  public void setCsettlerowno(String csettlerowno) {
    this.setAttributeValue(InvoiceSettleItemVO.CSETTLEROWNO, csettlerowno);
  }

  /**
   * 设置结算方式
   * 
   * @param csettletype 结算方式
   */
  public void setCsettletype(String csettletype) {
    this.setAttributeValue(InvoiceSettleItemVO.CSETTLETYPE, csettletype);
  }

  /**
   * 预计付款日期
   */
  public void setDpaydate(UFDate dpaydate) {
    this.setAttributeValue(InvoiceSettleItemVO.DPAYDATE, dpaydate);
  }

  /**
   * 设置起效日期对应单据
   * 
   * @param feffectdatebill 起效日期对应单据
   * @see EffectDateBill
   */
  public void setFeffectdatebill(Integer feffectdatebill) {
    this.setAttributeValue(InvoiceSettleItemVO.FEFFECTDATEBILL, feffectdatebill);
  }

  /**
   * 设置生效月
   * 
   * @param feffectmonth 生效月
   */
  public void setFeffectmonth(Integer feffectmonth) {
    this.setAttributeValue(InvoiceSettleItemVO.FEFFECTMONTH, feffectmonth);
  }

  /**
   * 设置固定结账日
   * 
   * @param icheckdata 固定结账日
   */
  public void setIcheckdata(Integer icheckdata) {
    this.setAttributeValue(InvoiceSettleItemVO.ICHECKDATA, icheckdata);
  }

  /**
   * 设置起效日期延期天数
   * 
   * @param ieffectaddday 起效日期延期天数
   */
  public void setIeffectaddday(Integer ieffectaddday) {
    this.setAttributeValue(InvoiceSettleItemVO.IEFFECTADDDAY, ieffectaddday);
  }

  /**
   * 设置附加月
   * 
   * @param ieffectaddmonth 附加月
   */
  public void setIeffectaddmonth(Integer ieffectaddmonth) {
    this.setAttributeValue(InvoiceSettleItemVO.IEFFECTADDMONTH, ieffectaddmonth);
  }

  /**
   * 设置账期天数
   * 
   * @param ipaymentday 账期天数
   */
  public void setIpaymentday(Integer ipaymentday) {
    this.setAttributeValue(InvoiceSettleItemVO.IPAYMENTDAY, ipaymentday);
  }

  /**
   * 预计付款金额
   */
  public void setNpaymny(UFDouble npaymny) {
    this.setAttributeValue(InvoiceSettleItemVO.NPAYMNY, npaymny);
  }

  /**
   * 设置付款比例
   * 
   * @param npayrate 付款比例
   */
  public void setNpayrate(UFDouble npayrate) {
    this.setAttributeValue(InvoiceSettleItemVO.NPAYRATE, npayrate);
  }

  /**
   * 设置财务组织
   * 
   * @param pk_apfinanceorg 财务组织
   */
  public void setPk_apfinanceorg(String pk_apfinanceorg) {
    this.setAttributeValue(InvoiceSettleItemVO.PK_APFINANCEORG, pk_apfinanceorg);
  }

  /**
   * 设置财务组织
   * 
   * @param pk_apfinanceorg_v 财务组织
   */
  public void setPk_apfinanceorg_v(String pk_apfinanceorg_v) {
    this.setAttributeValue(InvoiceSettleItemVO.PK_APFINANCEORG_V,
        pk_apfinanceorg_v);
  }

  /**
   * 设置所属集团
   * 
   * @param pk_group 所属集团
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(InvoiceSettleItemVO.PK_GROUP, pk_group);
  }

  /** 发票主实体 setter 方法 */
  public void setPk_invoice(String pk_invoice) {
    this.setAttributeValue(InvoiceItemVO.PK_INVOICE, pk_invoice);
  }

  /**
   * 设置采购发票结算
   * 
   * @param pk_invoice_settle 采购发票结算
   */
  public void setPk_invoice_settle(String pk_invoice_settle) {
    this.setAttributeValue(InvoiceSettleItemVO.PK_INVOICE_SETTLE,
        pk_invoice_settle);
  }

  /**
   * 设置时间戳
   * 
   * @param ts 时间戳
   */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(InvoiceSettleItemVO.TS, ts);
  }

  /**
   * 设置备注
   * 
   * @param vmemo 备注
   */
  public void setVmemo(String vmemo) {
    this.setAttributeValue(InvoiceSettleItemVO.VMEMO, vmemo);
  }

  /**
   * 设置付款期
   * 
   * @param vpaydate 付款期
   */
  public void setVpaydate(String vpaydate) {
    this.setAttributeValue(InvoiceSettleItemVO.VPAYDATE, vpaydate);
  }
}
