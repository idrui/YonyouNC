/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-6-26 ����02:55:09
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhaoyha
 * @time 2009-6-26 ����02:55:09
 */
public class InvoiceHeaderVO extends SuperVO {
  /** ������ */
  public static final String APPROVER = "approver";

  /** �Ѵ�Ӧ����־ */
  public static final String BAPFLAG = "bapflag";

  /** ���÷�Ʊ */
  public static final String BFEE = "bfee";

  /** ���� */
  public static final String BFROZEN = "bfrozen";

  /** �Ƶ��� */
  public static final String BILLMAKER = "billmaker";

  /** �Ƿ��ڳ���Ʊ */
  public static final String BINITIAL = "binitial";

  /** ������˰ */
  public static final String BOPPTAXFLAG = "bopptaxflag";

  /** ����ó�� */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /** ���ⷢƱ��־ */
  public static final String BVIRTUAL = "bvirtual";

  /** ���ұ���(��λ��) */
  public static final String CCURRENCYID = "ccurrencyid";

  /** ���� */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /** ����ʱ�� */
  public static final String CREATIONTIME = "creationtime";

  /** ������ */
  public static final String CREATOR = "creator";

  /** �ջ�����/���� */
  public static final String CRECECOUNTRYID = "crececountryid";

  /** ������/���� */
  public static final String CSENDCOUNTRYID = "csendcountryid";

  /** ��˰��/���� */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /** ó������ */
  public static final String CTRADEWORDID = "ctradewordid";

  /** �������� */
  public static final String CTRANTYPEID = "ctrantypeid";

  /** Ʊ������ */
  public static final String DARRIVEDATE = "darrivedate";

  /** ��Ʊ���� */
  public static final String DBILLDATE = "dbilldate";

  /** �Ƶ����� */
  public static final String DMAKEDATE = "dmakedate";

  /** dr */
  public static final String DR = "dr";

  /** ����״̬ */
  public static final String FBILLSTATUS = "fbillstatus";

  /** �������� */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /** ��Ʊ���� */
  public static final String FINVOICECLASS = "finvoiceclass";

  /** ��Ʊ���� */
  public static final String FINVOICETYPE = "finvoicetype";

  /** ������˰��� */
  public static final String FTAXTYPEFLAGH = "ftaxtypeflagh";

  /** ��ӡ���� */
  public static final String IPRINTCOUNT = "iprintcount";

  /** ����޸�ʱ�� */
  public static final String MODIFIEDTIME = "modifiedtime";

  /** ����޸��� */
  public static final String MODIFIER = "modifier";

  /** ���� */
  public static final String NEXCHANGERATE = "nexchangerate";

  /** ȫ�ֱ�λ�һ��� */
  public static final String NGLOBALEXCHGRATE = "nglobalexchgrate";

  /** ���ű�λ�һ��� */
  public static final String NGROUPEXCHGRATE = "ngroupexchgrate";

  /** ����˰�� */
  public static final String NTAXRATEH = "ntaxrateh";

  /** �������� */
  public static final String NTOTALASTNUM = "ntotalastnum";

  /** ������˰�ϼ�(ԭ��) */
  public static final String NTOTALORIGMNY = "ntotalorigmny";
  
  /** ���㷽ʽ */
  public static final String PK_BALATYPE = "pk_balatype";

  /** �����˻� */
  public static final String PK_BANKACCBAS = "pk_bankaccbas";

  /** ҵ��Ա */
  public static final String PK_BIZPSN = "pk_bizpsn";

  /** ҵ������ */
  public static final String PK_BUSITYPE = "pk_busitype";

  /** �ɹ�����(OID) */
  public static final String PK_DEPT = "pk_dept";

  /** �ɹ�����(VID) */
  public static final String PK_DEPT_V = "pk_dept_v";

  /** ɢ�� */
  public static final String PK_FREECUST = "pk_freecust";

  /** ������ */
  public static final String PK_FROZENUSER = "pk_frozenuser";

  /** �������� */
  public static final String PK_GROUP = "pk_group";

  /** �ɹ���Ʊ */
  public static final String PK_INVOICE = "pk_invoice";

  /** ������֯(OID) */
  public static final String PK_ORG = "pk_org";

  /** ������֯(VID) */
  public static final String PK_ORG_V = "pk_org_v";

  /** ���÷�Ʊ��Ӧ���﷢Ʊ */
  public static final String PK_PARENTINVOICE = "pk_parentinvoice";

  /** ����Э�� */
  public static final String PK_PAYTERM = "pk_payterm";

  /** ���λ */
  public static final String PK_PAYTOSUPPLIER = "pk_paytosupplier";

  /** �ɹ���֯(OID) */
  public static final String PK_PURCHASEORG = "pk_purchaseorg";

  /** �ɹ���֯(VID) */
  public static final String PK_PURCHASEORG_V = "pk_purchaseorg_v";

  /** �����֯(OID) */
  public static final String PK_STOCKORG = "pk_stockorg";

  /** �����֯(VID) */
  public static final String PK_STOCKORG_V = "pk_stockorg_v";

  /** ��Ӧ�� */
  public static final String PK_SUPPLIER = "pk_supplier";

  /** �������� */
  public static final String TAUDITTIME = "taudittime";

  /** �������� */
  public static final String TFROZENTIME = "tfrozentime";

  /** ʱ��� */
  public static final String TS = "ts";

  /** ����ԭ�� */
  public static final String VADJUSTREASON = "vadjustreason";

  /** �����˺� */
  public static final String VBANKACCOUNT = "vbankaccount";

  /** ��Ʊ�� */
  public static final String VBILLCODE = "vbillcode";

  /** �Զ�����1 */
  public static final String VDEF1 = "vdef1";

  /** �Զ�����10 */
  public static final String VDEF10 = "vdef10";

  /** �Զ�����11 */
  public static final String VDEF11 = "vdef11";

  /** �Զ�����12 */
  public static final String VDEF12 = "vdef12";

  /** �Զ�����13 */
  public static final String VDEF13 = "vdef13";

  /** �Զ�����14 */
  public static final String VDEF14 = "vdef14";

  /** �Զ�����15 */
  public static final String VDEF15 = "vdef15";

  /** �Զ�����16 */
  public static final String VDEF16 = "vdef16";

  /** �Զ�����17 */
  public static final String VDEF17 = "vdef17";

  /** �Զ�����18 */
  public static final String VDEF18 = "vdef18";

  /** �Զ�����19 */
  public static final String VDEF19 = "vdef19";

  /** �Զ�����2 */
  public static final String VDEF2 = "vdef2";

  /** �Զ�����20 */
  public static final String VDEF20 = "vdef20";

  /** �Զ�����3 */
  public static final String VDEF3 = "vdef3";

  /** �Զ�����4 */
  public static final String VDEF4 = "vdef4";

  /** �Զ�����5 */
  public static final String VDEF5 = "vdef5";

  /** �Զ�����6 */
  public static final String VDEF6 = "vdef6";

  /** �Զ�����7 */
  public static final String VDEF7 = "vdef7";

  /** �Զ�����8 */
  public static final String VDEF8 = "vdef8";

  /** �Զ�����9 */
  public static final String VDEF9 = "vdef9";

  /** ��󶳽�ԭ�� */
  public static final String VFROZENREASON = "vfrozenreason";

  /** ��ע */
  public static final String VMEMO = "vmemo";

  /** ��Ʊ����(��������) */
  public static final String VTRANTYPECODE = "vtrantypecode";

  /** VATע���� */
  public static final String VVATCODE = "vvatcode";

  /** ��Ӧ��VATע���� */
  public static final String VVENDORVATCODE = "vvendorvatcode";

  /**
   * 
   */
  private static final long serialVersionUID = -2862148524851751202L;

  /** ������ getter ���� */
  public String getApprover() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.APPROVER);
  }

  /** �Ѵ�Ӧ����־ getter ���� */
  public UFBoolean getBapflag() {
    return (UFBoolean) this.getAttributeValue(InvoiceHeaderVO.BAPFLAG);
  }

  /** ���÷�Ʊ getter ���� */
  public UFBoolean getBfee() {
    return (UFBoolean) this.getAttributeValue(InvoiceHeaderVO.BFEE);
  }

  /** ���� getter ���� */
  public UFBoolean getBfrozen() {
    return (UFBoolean) this.getAttributeValue(InvoiceHeaderVO.BFROZEN);
  }

  /** �Ƶ��� getter ���� */
  public String getBillmaker() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.BILLMAKER);
  }

  /** �Ƿ��ڳ���Ʊ getter ���� */
  public UFBoolean getBinitial() {
    return (UFBoolean) this.getAttributeValue(InvoiceHeaderVO.BINITIAL);
  }

  /** ������˰ getter ���� */
  public UFBoolean getBopptaxflag() {
    return (UFBoolean) this.getAttributeValue(InvoiceHeaderVO.BOPPTAXFLAG);
  }

  /** ����ó�� getter ���� */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(InvoiceHeaderVO.BTRIATRADEFLAG);
  }

  /** ���ⷢƱ��־ getter ���� */
  public UFBoolean getBvirtual() {
    return (UFBoolean) this.getAttributeValue(InvoiceHeaderVO.BVIRTUAL);
  }

  /** ���ұ���(��λ��) getter ���� */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.CCURRENCYID);
  }

  /** ���� getter ���� */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.CORIGCURRENCYID);
  }

  /** ����ʱ�� getter ���� */
  public UFDateTime getCreationtime() {
    return (UFDateTime) this.getAttributeValue(InvoiceHeaderVO.CREATIONTIME);
  }

  /** ������ getter ���� */
  public String getCreator() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.CREATOR);
  }

  /** �ջ�����/���� getter ���� */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.CRECECOUNTRYID);
  }

  /** ������/���� getter ���� */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.CSENDCOUNTRYID);
  }

  /** ��˰��/���� getter ���� */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.CTAXCOUNTRYID);
  }

  /** ó������ getter ���� */
  public String getCtradewordid() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.CTRADEWORDID);
  }

  /** �������� getter ���� */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.CTRANTYPEID);
  }

  /** Ʊ������ getter ���� */
  public UFDate getDarrivedate() {
    return (UFDate) this.getAttributeValue(InvoiceHeaderVO.DARRIVEDATE);
  }

  /** ��Ʊ���� getter ���� */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(InvoiceHeaderVO.DBILLDATE);
  }

  /** �Ƶ����� **/
  public UFDate getDmakedate() {
    return (UFDate) this.getAttributeValue(InvoiceHeaderVO.DMAKEDATE);
  }

  /** dr getter ���� */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(InvoiceHeaderVO.DR);
  }

  /** ����״̬ getter ���� */
  public Integer getFbillstatus() {
    return (Integer) this.getAttributeValue(InvoiceHeaderVO.FBILLSTATUS);
  }

  /** �������� getter ���� */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(InvoiceHeaderVO.FBUYSELLFLAG);
  }

  /** ��Ʊ���� getter ���� */
  public Integer getFinvoiceclass() {
    return (Integer) this.getAttributeValue(InvoiceHeaderVO.FINVOICECLASS);
  }

  /** ��ȡ��Ʊ���� getter ���� */
  public Integer getFinvoicetype() {
    return (Integer) this.getAttributeValue(InvoiceHeaderVO.FINVOICETYPE);
  }

  /** ������˰��� getter ���� */
  public Integer getFtaxtypeflagh() {
    return (Integer) this.getAttributeValue(InvoiceHeaderVO.FTAXTYPEFLAGH);
  }

  /** ��ӡ���� getter ���� */
  public Integer getIprintcount() {
    return (Integer) this.getAttributeValue(InvoiceHeaderVO.IPRINTCOUNT);
  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pub.ISuperVO#getMetaData()
   */
  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta(PUEntity.M25_H);
    return meta;
  }

  /** ����޸�ʱ�� getter ���� */
  public UFDateTime getModifiedtime() {
    return (UFDateTime) this.getAttributeValue(InvoiceHeaderVO.MODIFIEDTIME);
  }

  /** ����޸��� getter ���� */
  public String getModifier() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.MODIFIER);
  }

  /** ���� getter ���� */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(InvoiceHeaderVO.NEXCHANGERATE);
  }

  /** ȫ�ֱ�λ�һ��� getter ���� */
  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this.getAttributeValue(InvoiceHeaderVO.NGLOBALEXCHGRATE);
  }

  /** ���ű�λ�һ��� getter ���� */
  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this.getAttributeValue(InvoiceHeaderVO.NGROUPEXCHGRATE);
  }

  /** ����˰�� getter ���� */
  public UFDouble getNtaxrateh() {
    return (UFDouble) this.getAttributeValue(InvoiceHeaderVO.NTAXRATEH);
  }

  /** �������� getter ���� */
  public UFDouble getNtotalastnum() {
    return (UFDouble) this.getAttributeValue(InvoiceHeaderVO.NTOTALASTNUM);
  }

  /** ������˰�ϼ�(ԭ��) getter ���� */
  public UFDouble getNtotalorigmny() {
    return (UFDouble) this.getAttributeValue(InvoiceHeaderVO.NTOTALORIGMNY);
  }
  
  /** ���㷽ʽ getter ���� */
  public String getPk_balatype() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_BALATYPE);
  }

  /** �����˻� getter ���� */
  public String getPk_bankaccbas() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_BANKACCBAS);
  }

  /** ҵ��Ա getter ���� */
  public String getPk_bizpsn() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_BIZPSN);
  }

  /** ҵ������ getter ���� */
  public String getPk_busitype() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_BUSITYPE);
  }

  /** �ɹ�����(OID) getter ���� */
  public String getPk_dept() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_DEPT);
  }

  /** �ɹ�����(VID) getter ���� */
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_DEPT_V);
  }

  /** ɢ�� getter ���� */
  public String getPk_freecust() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_FREECUST);
  }

  /** ������ getter ���� */
  public String getPk_frozenuser() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_FROZENUSER);
  }

  /** �������� getter ���� */
  public String getPk_group() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_GROUP);
  }

  /** �ɹ���Ʊ getter ���� */
  public String getPk_invoice() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_INVOICE);
  }

  /** ������֯(OID) getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_ORG);
  }

  /** ������֯(VID) getter ���� */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_ORG_V);
  }

  /** ���÷�Ʊ��Ӧ���﷢Ʊ getter ���� */
  public String getPk_parentinvoice() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_PARENTINVOICE);
  }

  /** ����Э�� getter ���� */
  public String getPk_payterm() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_PAYTERM);
  }

  /** ���λ getter ���� */
  public String getPk_paytosupplier() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_PAYTOSUPPLIER);
  }

  /** �ɹ���֯(OID) getter ���� */
  public String getPk_purchaseorg() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_PURCHASEORG);
  }

  /** �ɹ���֯(VID) getter ���� */
  public String getPk_purchaseorg_v() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_PURCHASEORG_V);
  }

  /** �����֯(OID) getter ���� */
  public String getPk_stockorg() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_STOCKORG);
  }

  /** �����֯(VID) getter ���� */
  public String getPk_stockorg_v() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_STOCKORG_V);
  }

  /** ��Ӧ�� getter ���� */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_SUPPLIER);
  }

  /** �������� getter ���� */
  public UFDate getTaudittime() {
    return (UFDate) this.getAttributeValue(InvoiceHeaderVO.TAUDITTIME);
  }

  /** �������� getter ���� */
  public UFDate getTfrozentime() {
    return (UFDate) this.getAttributeValue(InvoiceHeaderVO.TFROZENTIME);
  }

  /** ʱ��� getter ���� */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(InvoiceHeaderVO.TS);
  }

  /** ��ȡ����ԭ�� getter ���� */
  public String getVadjustreason() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VADJUSTREASON);
  }

  public String getVbankaccount() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VBANKACCOUNT);
  }

  /** ��Ʊ�� getter ���� */
  public String getVbillcode() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VBILLCODE);
  }

  /** �Զ�����1 getter ���� */
  public String getVdef1() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF1);
  }

  /** �Զ�����10 getter ���� */
  public String getVdef10() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF10);
  }

  /** �Զ�����11 getter ���� */
  public String getVdef11() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF11);
  }

  /** �Զ�����12 getter ���� */
  public String getVdef12() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF12);
  }

  /** �Զ�����13 getter ���� */
  public String getVdef13() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF13);
  }

  /** �Զ�����14 getter ���� */
  public String getVdef14() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF14);
  }

  /** �Զ�����15 getter ���� */
  public String getVdef15() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF15);
  }

  /** �Զ�����16 getter ���� */
  public String getVdef16() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF16);
  }

  /** �Զ�����17 getter ���� */
  public String getVdef17() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF17);
  }

  /** �Զ�����18 getter ���� */
  public String getVdef18() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF18);
  }

  /** �Զ�����19 getter ���� */
  public String getVdef19() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF19);
  }

  /** �Զ�����2 getter ���� */
  public String getVdef2() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF2);
  }

  /** �Զ�����20 getter ���� */
  public String getVdef20() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF20);
  }

  /** �Զ�����3 getter ���� */
  public String getVdef3() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF3);
  }

  /** �Զ�����4 getter ���� */
  public String getVdef4() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF4);
  }

  /** �Զ�����5 getter ���� */
  public String getVdef5() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF5);
  }

  /** �Զ�����6 getter ���� */
  public String getVdef6() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF6);
  }

  /** �Զ�����7 getter ���� */
  public String getVdef7() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF7);
  }

  /** �Զ�����8 getter ���� */
  public String getVdef8() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF8);
  }

  /** �Զ�����9 getter ���� */
  public String getVdef9() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VDEF9);
  }

  /** ��󶳽�ԭ�� getter ���� */
  public String getVfrozenreason() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VFROZENREASON);
  }

  /** ��ע getter ���� */
  public String getVmemo() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VMEMO);
  }

  /** ��Ʊ����(��������) getter ���� */
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VTRANTYPECODE);
  }

  /** VATע���� getter ���� */
  public String getVvatcode() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VVATCODE);
  }

  /** ��Ӧ��VATע���� getter ���� */
  public String getVvendorvatcode() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VVENDORVATCODE);
  }

  /** ������ setter ���� */
  public void setApprover(String approver) {
    this.setAttributeValue(InvoiceHeaderVO.APPROVER, approver);
  }

  /** �Ѵ�Ӧ����־ setter ���� */
  public void setBapflag(UFBoolean bapflag) {
    this.setAttributeValue(InvoiceHeaderVO.BAPFLAG, bapflag);
  }

  /** ���÷�Ʊ setter ���� */
  public void setBfee(UFBoolean bfee) {
    this.setAttributeValue(InvoiceHeaderVO.BFEE, bfee);
  }

  /** ���� setter ���� */
  public void setBfrozen(UFBoolean bfrozen) {
    this.setAttributeValue(InvoiceHeaderVO.BFROZEN, bfrozen);
  }

  /** �Ƶ��� setter ���� */
  public void setBillmaker(String billmaker) {
    this.setAttributeValue(InvoiceHeaderVO.BILLMAKER, billmaker);
  }

  /** �Ƿ��ڳ���Ʊ setter ���� */
  public void setBinitial(UFBoolean binitial) {
    this.setAttributeValue(InvoiceHeaderVO.BINITIAL, binitial);
  }

  /** ������˰ setter ���� */
  public void setBopptaxflag(UFBoolean bopptaxflag) {
    this.setAttributeValue(InvoiceHeaderVO.BOPPTAXFLAG, bopptaxflag);
  }

  /** ����ó�� setter ���� */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(InvoiceHeaderVO.BTRIATRADEFLAG, btriatradeflag);
  }

  /** ���ⷢƱ��־ setter ���� */
  public void setBvirtual(UFBoolean bvirtual) {
    this.setAttributeValue(InvoiceHeaderVO.BVIRTUAL, bvirtual);
  }

  /** ���ұ���(��λ��) setter ���� */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(InvoiceHeaderVO.CCURRENCYID, ccurrencyid);
  }

  /** ���� setter ���� */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(InvoiceHeaderVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /** ����ʱ�� setter ���� */
  public void setCreationtime(UFDateTime creationtime) {
    this.setAttributeValue(InvoiceHeaderVO.CREATIONTIME, creationtime);
  }

  /** ������ setter ���� */
  public void setCreator(String creator) {
    this.setAttributeValue(InvoiceHeaderVO.CREATOR, creator);
  }

  /** �ջ�����/���� setter ���� */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(InvoiceHeaderVO.CRECECOUNTRYID, crececountryid);
  }

  /** ������/���� setter ���� */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(InvoiceHeaderVO.CSENDCOUNTRYID, csendcountryid);
  }

  /** ��˰��/���� setter ���� */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(InvoiceHeaderVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  /** ó������ setter ���� */
  public void setCtradewordid(String ctradewordid) {
    this.setAttributeValue(InvoiceHeaderVO.CTRADEWORDID, ctradewordid);
  }

  /** �������� setter ���� */
  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(InvoiceHeaderVO.CTRANTYPEID, ctrantypeid);
  }

  /** Ʊ������ setter ���� */
  public void setDarrivedate(UFDate darrivedate) {
    this.setAttributeValue(InvoiceHeaderVO.DARRIVEDATE, darrivedate);
  }

  /** ��Ʊ���� setter ���� */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(InvoiceHeaderVO.DBILLDATE, dbilldate);
  }

  /** �Ƶ����� **/
  public void setDmakedate(UFDate dmakedate) {
    this.setAttributeValue(InvoiceHeaderVO.DMAKEDATE, dmakedate);
  }

  /** dr setter ���� */
  public void setDr(Integer dr) {
    this.setAttributeValue(InvoiceHeaderVO.DR, dr);
  }

  /** ����״̬ setter ���� */
  public void setFbillstatus(Integer fbillstatus) {
    this.setAttributeValue(InvoiceHeaderVO.FBILLSTATUS, fbillstatus);
  }

  /** �������� setter ���� */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(InvoiceHeaderVO.FBUYSELLFLAG, fbuysellflag);
  }

  /** ��Ʊ���� setter ���� */
  public void setFinvoiceclass(Integer finvoiceclass) {
    this.setAttributeValue(InvoiceHeaderVO.FINVOICECLASS, finvoiceclass);
  }

  /** ���÷�Ʊ���� setter ���� */
  public void setFinvoicetype(Integer finvoicetype) {
    this.setAttributeValue(InvoiceHeaderVO.FINVOICETYPE, finvoicetype);
  }

  /** ������˰��� setter ���� */
  public void setFtaxtypeflagh(Integer ftaxtypeflagh) {
    this.setAttributeValue(InvoiceHeaderVO.FTAXTYPEFLAGH, ftaxtypeflagh);
  }

  /** ��ӡ���� setter ���� */
  public void setIprintcount(Integer iprintcount) {
    this.setAttributeValue(InvoiceHeaderVO.IPRINTCOUNT, iprintcount);
  }

  /** ����޸�ʱ�� setter ���� */
  public void setModifiedtime(UFDateTime modifiedtime) {
    this.setAttributeValue(InvoiceHeaderVO.MODIFIEDTIME, modifiedtime);
  }

  /** ����޸��� setter ���� */
  public void setModifier(String modifier) {
    this.setAttributeValue(InvoiceHeaderVO.MODIFIER, modifier);
  }

  /** ���� setter ���� */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(InvoiceHeaderVO.NEXCHANGERATE, nexchangerate);
  }

  /** ȫ�ֱ�λ�һ��� setter ���� */
  public void setNglobalexchgrate(UFDouble nglobalexchgrate) {
    this.setAttributeValue(InvoiceHeaderVO.NGLOBALEXCHGRATE, nglobalexchgrate);
  }

  /** ���ű�λ�һ��� setter ���� */
  public void setNgroupexchgrate(UFDouble ngroupexchgrate) {
    this.setAttributeValue(InvoiceHeaderVO.NGROUPEXCHGRATE, ngroupexchgrate);
  }

  /** ����˰�� setter ���� */
  public void setNtaxrateh(UFDouble ntaxrateh) {
    this.setAttributeValue(InvoiceHeaderVO.NTAXRATEH, ntaxrateh);
  }

  /** �������� setter ���� */
  public void setNtotalastnum(UFDouble ntotalastnum) {
    this.setAttributeValue(InvoiceHeaderVO.NTOTALASTNUM, ntotalastnum);
  }

  /** ������˰�ϼ�(ԭ��) setter ���� */
  public void setNtotalorigmny(UFDouble ntotalorigmny) {
    this.setAttributeValue(InvoiceHeaderVO.NTOTALORIGMNY, ntotalorigmny);
  }
  
  /** ���㷽ʽ setter ���� */
  public void setPk_balatype(String pk_balatype) {
    this.setAttributeValue(InvoiceHeaderVO.PK_BALATYPE, pk_balatype);
  }

  /** �����˻� setter ���� */
  public void setPk_bankaccbas(String pk_bankaccbas) {
    this.setAttributeValue(InvoiceHeaderVO.PK_BANKACCBAS, pk_bankaccbas);
  }

  /** ҵ��Ա setter ���� */
  public void setPk_bizpsn(String pk_bizpsn) {
    this.setAttributeValue(InvoiceHeaderVO.PK_BIZPSN, pk_bizpsn);
  }

  /** ҵ������ setter ���� */
  public void setPk_busitype(String pk_busitype) {
    this.setAttributeValue(InvoiceHeaderVO.PK_BUSITYPE, pk_busitype);
  }

  /** �ɹ�����(OID) setter ���� */
  public void setPk_dept(String pk_dept) {
    this.setAttributeValue(InvoiceHeaderVO.PK_DEPT, pk_dept);
  }

  /** �ɹ�����(VID) setter ���� */
  public void setPk_dept_v(String pk_dept_v) {
    this.setAttributeValue(InvoiceHeaderVO.PK_DEPT_V, pk_dept_v);
  }

  /** ɢ�� setter ���� */
  public void setPk_freecust(String pk_freecust) {
    this.setAttributeValue(InvoiceHeaderVO.PK_FREECUST, pk_freecust);
  }

  /** ������ setter ���� */
  public void setPk_frozenuser(String pk_frozenuser) {
    this.setAttributeValue(InvoiceHeaderVO.PK_FROZENUSER, pk_frozenuser);
  }

  /** �������� setter ���� */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(InvoiceHeaderVO.PK_GROUP, pk_group);
  }

  /** �ɹ���Ʊ setter ���� */
  public void setPk_invoice(String pk_invoice) {
    this.setAttributeValue(InvoiceHeaderVO.PK_INVOICE, pk_invoice);
  }

  /** ������֯(OID) setter ���� */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(InvoiceHeaderVO.PK_ORG, pk_org);
  }

  /** ������֯(VID) setter ���� */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(InvoiceHeaderVO.PK_ORG_V, pk_org_v);
  }

  /** ���÷�Ʊ��Ӧ���﷢Ʊ setter ���� */
  public void setPk_parentinvoice(String pk_parentinvoice) {
    this.setAttributeValue(InvoiceHeaderVO.PK_PARENTINVOICE, pk_parentinvoice);
  }

  /** ����Э�� setter ���� */
  public void setPk_payterm(String pk_payterm) {
    this.setAttributeValue(InvoiceHeaderVO.PK_PAYTERM, pk_payterm);
  }

  /** ���λ setter ���� */
  public void setPk_paytosupplier(String pk_paytosupplier) {
    this.setAttributeValue(InvoiceHeaderVO.PK_PAYTOSUPPLIER, pk_paytosupplier);
  }

  /** �ɹ���֯(OID) setter ���� */
  public void setPk_purchaseorg(String pk_purchaseorg) {
    this.setAttributeValue(InvoiceHeaderVO.PK_PURCHASEORG, pk_purchaseorg);
  }

  /** �ɹ���֯(VID) setter ���� */
  public void setPk_purchaseorg_v(String pk_purchaseorg_v) {
    this.setAttributeValue(InvoiceHeaderVO.PK_PURCHASEORG_V, pk_purchaseorg_v);
  }

  /** �����֯(OID) setter ���� */
  public void setPk_stockorg(String pk_stockorg) {
    this.setAttributeValue(InvoiceHeaderVO.PK_STOCKORG, pk_stockorg);
  }

  /** �����֯(VID) setter ���� */
  public void setPk_stockorg_v(String pk_stockorg_v) {
    this.setAttributeValue(InvoiceHeaderVO.PK_STOCKORG_V, pk_stockorg_v);
  }

  /** ��Ӧ�� setter ���� */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(InvoiceHeaderVO.PK_SUPPLIER, pk_supplier);
  }

  /** �������� setter ���� */
  public void setTaudittime(UFDate taudittime) {
    this.setAttributeValue(InvoiceHeaderVO.TAUDITTIME, taudittime);
  }

  /** �������� setter ���� */
  public void setTfrozentime(UFDate tfrozentime) {
    this.setAttributeValue(InvoiceHeaderVO.TFROZENTIME, tfrozentime);
  }

  /** ʱ��� setter ���� */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(InvoiceHeaderVO.TS, ts);
  }

  /** ���õ���ԭ�� setter ���� */
  public void setVadjustreason(String vadjustreason) {
    this.setAttributeValue(InvoiceHeaderVO.VADJUSTREASON, vadjustreason);
  }

  public void setVbankaccount(String vbankaccount) {
    this.setAttributeValue(InvoiceHeaderVO.VBANKACCOUNT, vbankaccount);
  }

  /** ��Ʊ�� setter ���� */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(InvoiceHeaderVO.VBILLCODE, vbillcode);
  }

  /** �Զ�����1 setter ���� */
  public void setVdef1(String vdef1) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF1, vdef1);
  }

  /** �Զ�����10 setter ���� */
  public void setVdef10(String vdef10) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF10, vdef10);
  }

  /** �Զ�����11 setter ���� */
  public void setVdef11(String vdef11) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF11, vdef11);
  }

  /** �Զ�����12 setter ���� */
  public void setVdef12(String vdef12) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF12, vdef12);
  }

  /** �Զ�����13 setter ���� */
  public void setVdef13(String vdef13) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF13, vdef13);
  }

  /** �Զ�����14 setter ���� */
  public void setVdef14(String vdef14) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF14, vdef14);
  }

  /** �Զ�����15 setter ���� */
  public void setVdef15(String vdef15) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF15, vdef15);
  }

  /** �Զ�����16 setter ���� */
  public void setVdef16(String vdef16) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF16, vdef16);
  }

  /** �Զ�����17 setter ���� */
  public void setVdef17(String vdef17) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF17, vdef17);
  }

  /** �Զ�����18 setter ���� */
  public void setVdef18(String vdef18) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF18, vdef18);
  }

  /** �Զ�����19 setter ���� */
  public void setVdef19(String vdef19) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF19, vdef19);
  }

  /** �Զ�����2 setter ���� */
  public void setVdef2(String vdef2) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF2, vdef2);
  }

  /** �Զ�����20 setter ���� */
  public void setVdef20(String vdef20) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF20, vdef20);
  }

  /** �Զ�����3 setter ���� */
  public void setVdef3(String vdef3) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF3, vdef3);
  }

  /** �Զ�����4 setter ���� */
  public void setVdef4(String vdef4) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF4, vdef4);
  }

  /** �Զ�����5 setter ���� */
  public void setVdef5(String vdef5) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF5, vdef5);
  }

  /** �Զ�����6 setter ���� */
  public void setVdef6(String vdef6) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF6, vdef6);
  }

  /** �Զ�����7 setter ���� */
  public void setVdef7(String vdef7) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF7, vdef7);
  }

  /** �Զ�����8 setter ���� */
  public void setVdef8(String vdef8) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF8, vdef8);
  }

  /** �Զ�����9 setter ���� */
  public void setVdef9(String vdef9) {
    this.setAttributeValue(InvoiceHeaderVO.VDEF9, vdef9);
  }

  /** ��󶳽�ԭ�� setter ���� */
  public void setVfrozenreason(String vfrozenreason) {
    this.setAttributeValue(InvoiceHeaderVO.VFROZENREASON, vfrozenreason);
  }

  /** ��ע setter ���� */
  public void setVmemo(String vmemo) {
    this.setAttributeValue(InvoiceHeaderVO.VMEMO, vmemo);
  }

  /** ��Ʊ����(��������) setter ���� */
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(InvoiceHeaderVO.VTRANTYPECODE, vtrantypecode);
  }

  /** VATע���� setter ���� */
  public void setVvatcode(String vvatcode) {
    this.setAttributeValue(InvoiceHeaderVO.VVATCODE, vvatcode);
  }

  /** ��Ӧ��VATע���� setter ���� */
  public void setVvendorvatcode(String vvendorvatcode) {
    this.setAttributeValue(InvoiceHeaderVO.VVENDORVATCODE, vvendorvatcode);
  }
}
