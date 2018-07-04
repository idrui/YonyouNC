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
 * �ɹ���Ʊ����ʵ��
 * 
 * @since 6.3
 * @version 2013-08-19 14:41:28
 * @author zhangyhh
 */
public class InvoiceSettleItemVO extends SuperVO {

  /**
   * Ԥ����
   */
  public static final String BPREFLAG = "bpreflag";

  /**
   * ��Ч����
   */
  public static final String CINCOMEPERIOD = "cincomeperiod";

  /**
   * �к�
   */
  public static final String CSETTLEROWNO = "csettlerowno";

  /**
   * ���㷽ʽ
   */
  public static final String CSETTLETYPE = "csettletype";

  /**
   * Ԥ�Ƹ�������
   */
  public static final String DPAYDATE = "dpaydate";

  /**
   * ��Ч���ڶ�Ӧ����
   */
  public static final String FEFFECTDATEBILL = "feffectdatebill";

  /**
   * ��Ч��
   */
  public static final String FEFFECTMONTH = "feffectmonth";

  /**
   * �̶�������
   */
  public static final String ICHECKDATA = "icheckdata";

  /**
   * ��Ч������������
   */
  public static final String IEFFECTADDDAY = "ieffectaddday";

  /**
   * ������
   */
  public static final String IEFFECTADDMONTH = "ieffectaddmonth";

  /**
   * ��������
   */
  public static final String IPAYMENTDAY = "ipaymentday";

  /**
   * Ԥ�Ƹ�����
   */
  public static final String NPAYMNY = "npaymny";

  /**
   * �������
   */
  public static final String NPAYRATE = "npayrate";

  /**
   * ������֯
   */
  public static final String PK_APFINANCEORG = "pk_apfinanceorg";

  /**
   * ������֯
   */
  public static final String PK_APFINANCEORG_V = "pk_apfinanceorg_v";

  /**
   * ��������
   */
  public static final String PK_GROUP = "pk_group";

  /** ��Ʊ��ʵ�� */
  public static final String PK_INVOICE = "pk_invoice";

  /**
   * �ɹ���Ʊ����
   */
  public static final String PK_INVOICE_SETTLE = "pk_invoice_settle";

  /**
   * ʱ���
   */
  public static final String TS = "ts";

  /**
   * ��ע
   */
  public static final String VMEMO = "vmemo";

  /**
   * ������
   */
  public static final String VPAYDATE = "vpaydate";

  /**
   * 
   */
  private static final long serialVersionUID = -2964724531606587111L;

  /**
   * ��ȡԤ����
   * 
   * @return Ԥ����
   */
  public UFBoolean getBpreflag() {
    return (UFBoolean) this.getAttributeValue(InvoiceSettleItemVO.BPREFLAG);
  }

  /**
   * ��ȡ��Ч����
   * 
   * @return ��Ч����
   */
  public String getCincomeperiod() {
    return (String) this.getAttributeValue(InvoiceSettleItemVO.CINCOMEPERIOD);
  }

  /**
   * ��ȡ�к�
   * 
   * @return �к�
   */
  public String getCsettlerowno() {
    return (String) this.getAttributeValue(InvoiceSettleItemVO.CSETTLEROWNO);
  }

  /**
   * ��ȡ���㷽ʽ
   * 
   * @return ���㷽ʽ
   */
  public String getCsettletype() {
    return (String) this.getAttributeValue(InvoiceSettleItemVO.CSETTLETYPE);
  }

  /**
   * Ԥ�Ƹ�������
   * 
   * @return Ԥ�Ƹ�������
   */
  public UFDate getDpaydate() {
    return (UFDate) this.getAttributeValue(InvoiceSettleItemVO.DPAYDATE);
  }

  /**
   * ��ȡ��Ч���ڶ�Ӧ����
   * 
   * @return ��Ч���ڶ�Ӧ����
   * @see EffectDateBill
   */
  public Integer getFeffectdatebill() {
    return (Integer) this
        .getAttributeValue(InvoiceSettleItemVO.FEFFECTDATEBILL);
  }

  /**
   * ��ȡ��Ч��
   * 
   * @return ��Ч��
   */
  public Integer getFeffectmonth() {
    return (Integer) this.getAttributeValue(InvoiceSettleItemVO.FEFFECTMONTH);
  }

  /**
   * ��ȡ�̶�������
   * 
   * @return �̶�������
   */
  public Integer getIcheckdata() {
    return (Integer) this.getAttributeValue(InvoiceSettleItemVO.ICHECKDATA);
  }

  /**
   * ��ȡ��Ч������������
   * 
   * @return ��Ч������������
   */
  public Integer getIeffectaddday() {
    return (Integer) this.getAttributeValue(InvoiceSettleItemVO.IEFFECTADDDAY);
  }

  /**
   * ��ȡ������
   * 
   * @return ������
   */
  public Integer getIeffectaddmonth() {
    return (Integer) this
        .getAttributeValue(InvoiceSettleItemVO.IEFFECTADDMONTH);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   */
  public Integer getIpaymentday() {
    return (Integer) this.getAttributeValue(InvoiceSettleItemVO.IPAYMENTDAY);
  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pub.ISuperVO#getMetaData()
   */
  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta(PUEntity.M25_SETTLE);
    return meta;
  }

  /**
   * Ԥ�Ƹ�����
   * 
   * @return Ԥ�Ƹ�����
   */
  public UFDouble getNpaymny() {
    return (UFDouble) this.getAttributeValue(InvoiceSettleItemVO.NPAYMNY);
  }

  /**
   * ��ȡ�������
   * 
   * @return �������
   */
  public UFDouble getNpayrate() {
    return (UFDouble) this.getAttributeValue(InvoiceSettleItemVO.NPAYRATE);
  }

  /**
   * ��ȡ������֯
   * 
   * @return ������֯
   */
  public String getPk_apfinanceorg() {
    return (String) this.getAttributeValue(InvoiceSettleItemVO.PK_APFINANCEORG);
  }

  /**
   * ��ȡ������֯
   * 
   * @return ������֯
   */
  public String getPk_apfinanceorg_v() {
    return (String) this
        .getAttributeValue(InvoiceSettleItemVO.PK_APFINANCEORG_V);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(InvoiceSettleItemVO.PK_GROUP);
  }

  /** ��Ʊ��ʵ�� getter ���� */
  public String getPk_invoice() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_INVOICE);
  }

  /**
   * ��ȡ�ɹ���Ʊ����
   * 
   * @return �ɹ���Ʊ����
   */
  public String getPk_invoice_settle() {
    return (String) this
        .getAttributeValue(InvoiceSettleItemVO.PK_INVOICE_SETTLE);
  }

  /**
   * ��ȡʱ���
   * 
   * @return ʱ���
   */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(InvoiceSettleItemVO.TS);
  }

  /**
   * ��ȡ��ע
   * 
   * @return ��ע
   */
  public String getVmemo() {
    return (String) this.getAttributeValue(InvoiceSettleItemVO.VMEMO);
  }

  /**
   * ��ȡ������
   * 
   * @return ������
   */
  public String getVpaydate() {
    return (String) this.getAttributeValue(InvoiceSettleItemVO.VPAYDATE);
  }

  /**
   * ����Ԥ����
   * 
   * @param bpreflag Ԥ����
   */
  public void setBpreflag(UFBoolean bpreflag) {
    this.setAttributeValue(InvoiceSettleItemVO.BPREFLAG, bpreflag);
  }

  /**
   * ������Ч����
   * 
   * @param cincomeperiod ��Ч����
   */
  public void setCincomeperiod(String cincomeperiod) {
    this.setAttributeValue(InvoiceSettleItemVO.CINCOMEPERIOD, cincomeperiod);
  }

  /**
   * �����к�
   * 
   * @param csettlerowno �к�
   */
  public void setCsettlerowno(String csettlerowno) {
    this.setAttributeValue(InvoiceSettleItemVO.CSETTLEROWNO, csettlerowno);
  }

  /**
   * ���ý��㷽ʽ
   * 
   * @param csettletype ���㷽ʽ
   */
  public void setCsettletype(String csettletype) {
    this.setAttributeValue(InvoiceSettleItemVO.CSETTLETYPE, csettletype);
  }

  /**
   * Ԥ�Ƹ�������
   */
  public void setDpaydate(UFDate dpaydate) {
    this.setAttributeValue(InvoiceSettleItemVO.DPAYDATE, dpaydate);
  }

  /**
   * ������Ч���ڶ�Ӧ����
   * 
   * @param feffectdatebill ��Ч���ڶ�Ӧ����
   * @see EffectDateBill
   */
  public void setFeffectdatebill(Integer feffectdatebill) {
    this.setAttributeValue(InvoiceSettleItemVO.FEFFECTDATEBILL, feffectdatebill);
  }

  /**
   * ������Ч��
   * 
   * @param feffectmonth ��Ч��
   */
  public void setFeffectmonth(Integer feffectmonth) {
    this.setAttributeValue(InvoiceSettleItemVO.FEFFECTMONTH, feffectmonth);
  }

  /**
   * ���ù̶�������
   * 
   * @param icheckdata �̶�������
   */
  public void setIcheckdata(Integer icheckdata) {
    this.setAttributeValue(InvoiceSettleItemVO.ICHECKDATA, icheckdata);
  }

  /**
   * ������Ч������������
   * 
   * @param ieffectaddday ��Ч������������
   */
  public void setIeffectaddday(Integer ieffectaddday) {
    this.setAttributeValue(InvoiceSettleItemVO.IEFFECTADDDAY, ieffectaddday);
  }

  /**
   * ���ø�����
   * 
   * @param ieffectaddmonth ������
   */
  public void setIeffectaddmonth(Integer ieffectaddmonth) {
    this.setAttributeValue(InvoiceSettleItemVO.IEFFECTADDMONTH, ieffectaddmonth);
  }

  /**
   * ������������
   * 
   * @param ipaymentday ��������
   */
  public void setIpaymentday(Integer ipaymentday) {
    this.setAttributeValue(InvoiceSettleItemVO.IPAYMENTDAY, ipaymentday);
  }

  /**
   * Ԥ�Ƹ�����
   */
  public void setNpaymny(UFDouble npaymny) {
    this.setAttributeValue(InvoiceSettleItemVO.NPAYMNY, npaymny);
  }

  /**
   * ���ø������
   * 
   * @param npayrate �������
   */
  public void setNpayrate(UFDouble npayrate) {
    this.setAttributeValue(InvoiceSettleItemVO.NPAYRATE, npayrate);
  }

  /**
   * ���ò�����֯
   * 
   * @param pk_apfinanceorg ������֯
   */
  public void setPk_apfinanceorg(String pk_apfinanceorg) {
    this.setAttributeValue(InvoiceSettleItemVO.PK_APFINANCEORG, pk_apfinanceorg);
  }

  /**
   * ���ò�����֯
   * 
   * @param pk_apfinanceorg_v ������֯
   */
  public void setPk_apfinanceorg_v(String pk_apfinanceorg_v) {
    this.setAttributeValue(InvoiceSettleItemVO.PK_APFINANCEORG_V,
        pk_apfinanceorg_v);
  }

  /**
   * ������������
   * 
   * @param pk_group ��������
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(InvoiceSettleItemVO.PK_GROUP, pk_group);
  }

  /** ��Ʊ��ʵ�� setter ���� */
  public void setPk_invoice(String pk_invoice) {
    this.setAttributeValue(InvoiceItemVO.PK_INVOICE, pk_invoice);
  }

  /**
   * ���òɹ���Ʊ����
   * 
   * @param pk_invoice_settle �ɹ���Ʊ����
   */
  public void setPk_invoice_settle(String pk_invoice_settle) {
    this.setAttributeValue(InvoiceSettleItemVO.PK_INVOICE_SETTLE,
        pk_invoice_settle);
  }

  /**
   * ����ʱ���
   * 
   * @param ts ʱ���
   */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(InvoiceSettleItemVO.TS, ts);
  }

  /**
   * ���ñ�ע
   * 
   * @param vmemo ��ע
   */
  public void setVmemo(String vmemo) {
    this.setAttributeValue(InvoiceSettleItemVO.VMEMO, vmemo);
  }

  /**
   * ���ø�����
   * 
   * @param vpaydate ������
   */
  public void setVpaydate(String vpaydate) {
    this.setAttributeValue(InvoiceSettleItemVO.VPAYDATE, vpaydate);
  }
}
