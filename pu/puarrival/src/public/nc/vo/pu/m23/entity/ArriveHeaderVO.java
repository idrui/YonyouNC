package nc.vo.pu.m23.entity;

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
 * <li>�������ı�ͷVO��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 ����03:42:17
 */
public class ArriveHeaderVO extends SuperVO {

  /** ������ */
  public static final String APPROVER = "approver";

  /** �Ƶ��� */
  public static final String BILLMAKER = "billmaker";

  /** �˻� */
  public static final String BISBACK = "bisback";

  /** ���� */
  public static final String BPUBLISH = "bpublish";

  /** ����ʱ�� */
  public static final String CREATIONTIME = "creationtime";

  /** ������ */
  public static final String CREATOR = "creator";

  /** �������� */
  public static final String CTRANTYPEID = "ctrantypeid";

  /** �������� */
  public static final String DBILLDATE = "dbilldate";

  /** �Ƶ����� */
  public static final String DMAKEDATE = "dmakedate";

  /** dr */
  public static final String DR = "dr";

  /** ����״̬ */
  public static final String FBILLSTATUS = "fbillstatus";

  /** ��ӡ���� */
  public static final String IPRINTCOUNT = "iprintcount";

  /** ��Ӧ״̬ */
  public static final String IRESPSTATUS = "irespstatus";

  /** ����޸�ʱ�� */
  public static final String MODIFIEDTIME = "modifiedtime";

  /** ����޸��� */
  public static final String MODIFIER = "modifier";

  /** ������ */
  public static final String NTOTALASTNUM = "ntotalastnum";

  /** ���Ҽ�˰�ϼ� */
  public static final String NTOTALTAXMNY = "ntotaltaxmny";

  /** ������ */
  public static final String PK_ARRIVEORDER = "pk_arriveorder";

  /** ҵ������ */
  public static final String PK_BUSITYPE = "pk_busitype";

  /** �ɹ����� */
  public static final String PK_DEPT = "pk_dept";

  /** �ɹ����Ű汾 */
  public static final String PK_DEPT_V = "pk_dept_v";

  /** ɢ�� */
  public static final String PK_FREECUST = "pk_freecust";

  /** ���� */
  public static final String PK_GROUP = "pk_group";

  /** �����֯ */
  public static final String PK_ORG = "pk_org";

  /** �����֯�汾 */
  public static final String PK_ORG_V = "pk_org_v";

  /** ������ */
  public static final String PK_PUBPSN = "pk_pubpsn";

  /** �ɹ�Ա */
  public static final String PK_PUPSNDOC = "pk_pupsndoc";

  /** �ɹ���֯ */
  public static final String PK_PURCHASEORG = "pk_purchaseorg";

  /** �ɹ���֯�汾 */
  public static final String PK_PURCHASEORG_V = "pk_purchaseorg_v";

  /** �ջ��� */
  public static final String PK_RECEIVEPSNDOC = "pk_receivepsndoc";

  /** ��Ӧ�� */
  public static final String PK_RESPPSN = "pk_resppsn";

  /** ��Ӧ�� */
  public static final String PK_SUPPLIER = "pk_supplier";

  /** ���䷽ʽ */
  public static final String PK_TRANSPORTTYPE = "pk_transporttype";

  /** �������� */
  public static final String TAUDITTIME = "taudittime";

  /** �������� */
  public static final String TPUBTIME = "tpubtime";

  /** ��Ӧ���� */
  public static final String TRESPTIME = "tresptime";

  /** ts */
  public static final String TS = "ts";

  /** �˻����� */
  public static final String VBACKREASON = "vbackreason";

  /** �������� */
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

  /** ��ע */
  public static final String VMEMO = "vmemo";

  /** ��Ӧ���˻�˵�� */
  public static final String VSUPBACKREASON = "vsupbackreason";

  /** �������� */
  public static final String VTRANTYPECODE = "vtrantypecode";

  private static final long serialVersionUID = 2425412685227941160L;

  /** ������ getter ���� */
  public String getApprover() {
    return (String) this.getAttributeValue(ArriveHeaderVO.APPROVER);
  }

  /** �Ƶ��� getter ���� */
  public String getBillmaker() {
    return (String) this.getAttributeValue(ArriveHeaderVO.BILLMAKER);
  }

  /** �˻� getter ���� */
  public UFBoolean getBisback() {
    return (UFBoolean) this.getAttributeValue(ArriveHeaderVO.BISBACK);
  }

  /** ���� getter ���� */
  public UFBoolean getBpublish() {
    return (UFBoolean) this.getAttributeValue(ArriveHeaderVO.BPUBLISH);
  }

  /** ����ʱ�� getter ���� */
  public UFDateTime getCreationtime() {
    return (UFDateTime) this.getAttributeValue(ArriveHeaderVO.CREATIONTIME);
  }

  /** ������ getter ���� */
  public String getCreator() {
    return (String) this.getAttributeValue(ArriveHeaderVO.CREATOR);
  }

  /** �������� getter ���� */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(ArriveHeaderVO.CTRANTYPEID);
  }

  /** �������� getter ���� */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(ArriveHeaderVO.DBILLDATE);
  }

  /** �Ƶ����� **/
  public UFDate getDmakedate() {
    return (UFDate) this.getAttributeValue(ArriveHeaderVO.DMAKEDATE);
  }

  /** dr getter ���� */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(ArriveHeaderVO.DR);
  }

  /** ����״̬ getter ���� */
  public Integer getFbillstatus() {
    return (Integer) this.getAttributeValue(ArriveHeaderVO.FBILLSTATUS);
  }

  /** ��ӡ���� getter ���� */
  public Integer getIprintcount() {
    return (Integer) this.getAttributeValue(ArriveHeaderVO.IPRINTCOUNT);
  }

  /** ��Ӧ״̬ getter ���� */
  public Integer getIrespstatus() {
    return (Integer) this.getAttributeValue(ArriveHeaderVO.IRESPSTATUS);
  }

  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.M23_H);
  }

  /** ����޸�ʱ�� getter ���� */
  public UFDateTime getModifiedtime() {
    return (UFDateTime) this.getAttributeValue(ArriveHeaderVO.MODIFIEDTIME);
  }

  /** ����޸��� getter ���� */
  public String getModifier() {
    return (String) this.getAttributeValue(ArriveHeaderVO.MODIFIER);
  }

  /** ������ getter ���� */
  public UFDouble getNtotalastnum() {
    return (UFDouble) this.getAttributeValue(ArriveHeaderVO.NTOTALASTNUM);
  }

  /** ���Ҽ�˰�ϼ� getter ���� */
  public UFDouble getNtotaltaxmny() {
    return (UFDouble) this.getAttributeValue(ArriveHeaderVO.NTOTALTAXMNY);
  }

  /** ������ getter ���� */
  public String getPk_arriveorder() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_ARRIVEORDER);
  }

  /** ҵ������ getter ���� */
  public String getPk_busitype() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_BUSITYPE);
  }

  /** �ɹ����� getter ���� */
  public String getPk_dept() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_DEPT);
  }

  /** �ɹ����Ű汾 getter ���� */
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_DEPT_V);
  }

  /** ɢ�� getter ���� */
  public String getPk_freecust() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_FREECUST);
  }

  /** ���� getter ���� */
  public String getPk_group() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_GROUP);
  }

  /** �����֯ getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_ORG);
  }

  /** �����֯�汾 getter ���� */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_ORG_V);
  }

  /** ������ getter ���� */
  public String getPk_pubpsn() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_PUBPSN);
  }

  /** �ɹ�Ա getter ���� */
  public String getPk_pupsndoc() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_PUPSNDOC);
  }

  /** �ɹ���֯ getter ���� */
  public String getPk_purchaseorg() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_PURCHASEORG);
  }

  /** �ɹ���֯�汾 getter ���� */
  public String getPk_purchaseorg_v() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_PURCHASEORG_V);
  }

  /** �ջ��� getter ���� */
  public String getPk_receivepsndoc() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_RECEIVEPSNDOC);
  }

  /** ��Ӧ�� getter ���� */
  public String getPk_resppsn() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_RESPPSN);
  }

  /** ��Ӧ�� getter ���� */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_SUPPLIER);
  }

  /** ���䷽ʽ getter ���� */
  public String getPk_transporttype() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_TRANSPORTTYPE);
  }

  /** �������� getter ���� */
  public UFDate getTaudittime() {
    return (UFDate) this.getAttributeValue(ArriveHeaderVO.TAUDITTIME);
  }

  /** �������� getter ���� */
  public UFDate getTpubtime() {
    return (UFDate) this.getAttributeValue(ArriveHeaderVO.TPUBTIME);
  }

  /** ��Ӧ���� getter ���� */
  public UFDateTime getTresptime() {
    return (UFDateTime) this.getAttributeValue(ArriveHeaderVO.TRESPTIME);
  }

  /** ts getter ���� */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(ArriveHeaderVO.TS);
  }

  /** �˻����� getter ���� */
  public String getVbackreason() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VBACKREASON);
  }

  /** �������� getter ���� */
  public String getVbillcode() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VBILLCODE);
  }

  /** �Զ�����1 getter ���� */
  public String getVdef1() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VDEF1);
  }

  /** �Զ�����10 getter ���� */
  public String getVdef10() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VDEF10);
  }

  /** �Զ�����11 getter ���� */
  public String getVdef11() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VDEF11);
  }

  /** �Զ�����12 getter ���� */
  public String getVdef12() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VDEF12);
  }

  /** �Զ�����13 getter ���� */
  public String getVdef13() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VDEF13);
  }

  /** �Զ�����14 getter ���� */
  public String getVdef14() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VDEF14);
  }

  /** �Զ�����15 getter ���� */
  public String getVdef15() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VDEF15);
  }

  /** �Զ�����16 getter ���� */
  public String getVdef16() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VDEF16);
  }

  /** �Զ�����17 getter ���� */
  public String getVdef17() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VDEF17);
  }

  /** �Զ�����18 getter ���� */
  public String getVdef18() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VDEF18);
  }

  /** �Զ�����19 getter ���� */
  public String getVdef19() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VDEF19);
  }

  /** �Զ�����2 getter ���� */
  public String getVdef2() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VDEF2);
  }

  /** �Զ�����20 getter ���� */
  public String getVdef20() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VDEF20);
  }

  /** �Զ�����3 getter ���� */
  public String getVdef3() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VDEF3);
  }

  /** �Զ�����4 getter ���� */
  public String getVdef4() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VDEF4);
  }

  /** �Զ�����5 getter ���� */
  public String getVdef5() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VDEF5);
  }

  /** �Զ�����6 getter ���� */
  public String getVdef6() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VDEF6);
  }

  /** �Զ�����7 getter ���� */
  public String getVdef7() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VDEF7);
  }

  /** �Զ�����8 getter ���� */
  public String getVdef8() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VDEF8);
  }

  /** �Զ�����9 getter ���� */
  public String getVdef9() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VDEF9);
  }

  /** ��ע getter ���� */
  public String getVmemo() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VMEMO);
  }

  /** ��Ӧ���˻�˵�� getter ���� */
  public String getVsupbackreason() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VSUPBACKREASON);
  }

  /** �������� getter ���� */
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VTRANTYPECODE);
  }

  /** ������ setter ���� */
  public void setApprover(String approver) {
    this.setAttributeValue(ArriveHeaderVO.APPROVER, approver);
  }

  /** �Ƶ��� setter ���� */
  public void setBillmaker(String billmaker) {
    this.setAttributeValue(ArriveHeaderVO.BILLMAKER, billmaker);
  }

  /** �˻� setter ���� */
  public void setBisback(UFBoolean bisback) {
    this.setAttributeValue(ArriveHeaderVO.BISBACK, bisback);
  }

  /** ���� setter ���� */
  public void setBpublish(UFBoolean bpublish) {
    this.setAttributeValue(ArriveHeaderVO.BPUBLISH, bpublish);
  }

  /** ����ʱ�� setter ���� */
  public void setCreationtime(UFDateTime creationtime) {
    this.setAttributeValue(ArriveHeaderVO.CREATIONTIME, creationtime);
  }

  /** ������ setter ���� */
  public void setCreator(String creator) {
    this.setAttributeValue(ArriveHeaderVO.CREATOR, creator);
  }

  /** �������� setter ���� */
  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(ArriveHeaderVO.CTRANTYPEID, ctrantypeid);
  }

  /** �������� setter ���� */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(ArriveHeaderVO.DBILLDATE, dbilldate);
  }

  /** �Ƶ����� **/
  public void setDmakedate(UFDate dmakedate) {
    this.setAttributeValue(ArriveHeaderVO.DMAKEDATE, dmakedate);
  }

  /** dr setter ���� */
  public void setDr(Integer dr) {
    this.setAttributeValue(ArriveHeaderVO.DR, dr);
  }

  /** ����״̬ setter ���� */
  public void setFbillstatus(Integer fbillstatus) {
    this.setAttributeValue(ArriveHeaderVO.FBILLSTATUS, fbillstatus);
  }

  /** ��ӡ���� setter ���� */
  public void setIprintcount(Integer iprintcount) {
    this.setAttributeValue(ArriveHeaderVO.IPRINTCOUNT, iprintcount);
  }

  /** ��Ӧ״̬ setter ���� */
  public void setIrespstatus(Integer irespstatus) {
    this.setAttributeValue(ArriveHeaderVO.IRESPSTATUS, irespstatus);
  }

  /** ����޸�ʱ�� setter ���� */
  public void setModifiedtime(UFDateTime modifiedtime) {
    this.setAttributeValue(ArriveHeaderVO.MODIFIEDTIME, modifiedtime);
  }

  /** ����޸��� setter ���� */
  public void setModifier(String modifier) {
    this.setAttributeValue(ArriveHeaderVO.MODIFIER, modifier);
  }

  /** ������ setter ���� */
  public void setNtotalastnum(UFDouble ntotalastnum) {
    this.setAttributeValue(ArriveHeaderVO.NTOTALASTNUM, ntotalastnum);
  }

  /** ���Ҽ�˰�ϼ� setter ���� */
  public void setNtotaltaxmny(UFDouble ntotaltaxmny) {
    this.setAttributeValue(ArriveHeaderVO.NTOTALTAXMNY, ntotaltaxmny);
  }

  /** ������ setter ���� */
  public void setPk_arriveorder(String pk_arriveorder) {
    this.setAttributeValue(ArriveHeaderVO.PK_ARRIVEORDER, pk_arriveorder);
  }

  /** ҵ������ setter ���� */
  public void setPk_busitype(String pk_busitype) {
    this.setAttributeValue(ArriveHeaderVO.PK_BUSITYPE, pk_busitype);
  }

  /** �ɹ����� setter ���� */
  public void setPk_dept(String pk_dept) {
    this.setAttributeValue(ArriveHeaderVO.PK_DEPT, pk_dept);
  }

  /** �ɹ����Ű汾 setter ���� */
  public void setPk_dept_v(String pk_dept_v) {
    this.setAttributeValue(ArriveHeaderVO.PK_DEPT_V, pk_dept_v);
  }

  /** ɢ�� setter ���� */
  public void setPk_freecust(String pk_freecust) {
    this.setAttributeValue(ArriveHeaderVO.PK_FREECUST, pk_freecust);
  }

  /** ���� setter ���� */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(ArriveHeaderVO.PK_GROUP, pk_group);
  }

  /** �����֯ setter ���� */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(ArriveHeaderVO.PK_ORG, pk_org);
  }

  /** �����֯�汾 setter ���� */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(ArriveHeaderVO.PK_ORG_V, pk_org_v);
  }

  /** ������ setter ���� */
  public void setPk_pubpsn(String pk_pubpsn) {
    this.setAttributeValue(ArriveHeaderVO.PK_PUBPSN, pk_pubpsn);
  }

  /** �ɹ�Ա setter ���� */
  public void setPk_pupsndoc(String pk_pupsndoc) {
    this.setAttributeValue(ArriveHeaderVO.PK_PUPSNDOC, pk_pupsndoc);
  }

  /** �ɹ���֯ setter ���� */
  public void setPk_purchaseorg(String pk_purchaseorg) {
    this.setAttributeValue(ArriveHeaderVO.PK_PURCHASEORG, pk_purchaseorg);
  }

  /** �ɹ���֯�汾 setter ���� */
  public void setPk_purchaseorg_v(String pk_purchaseorg_v) {
    this.setAttributeValue(ArriveHeaderVO.PK_PURCHASEORG_V, pk_purchaseorg_v);
  }

  /** �ջ��� setter ���� */
  public void setPk_receivepsndoc(String pk_receivepsndoc) {
    this.setAttributeValue(ArriveHeaderVO.PK_RECEIVEPSNDOC, pk_receivepsndoc);
  }

  /** ��Ӧ�� setter ���� */
  public void setPk_resppsn(String pk_resppsn) {
    this.setAttributeValue(ArriveHeaderVO.PK_RESPPSN, pk_resppsn);
  }

  /** ��Ӧ�� setter ���� */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(ArriveHeaderVO.PK_SUPPLIER, pk_supplier);
  }

  /** ���䷽ʽ setter ���� */
  public void setPk_transporttype(String pk_transporttype) {
    this.setAttributeValue(ArriveHeaderVO.PK_TRANSPORTTYPE, pk_transporttype);
  }

  /** �������� setter ���� */
  public void setTaudittime(UFDate taudittime) {
    this.setAttributeValue(ArriveHeaderVO.TAUDITTIME, taudittime);
  }

  /** �������� setter ���� */
  public void setTpubtime(UFDate tpubtime) {
    this.setAttributeValue(ArriveHeaderVO.TPUBTIME, tpubtime);
  }

  /** ��Ӧ���� setter ���� */
  public void setTresptime(UFDateTime tresptime) {
    this.setAttributeValue(ArriveHeaderVO.TRESPTIME, tresptime);
  }

  /** ts setter ���� */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(ArriveHeaderVO.TS, ts);
  }

  /** �˻����� setter ���� */
  public void setVbackreason(String vbackreason) {
    this.setAttributeValue(ArriveHeaderVO.VBACKREASON, vbackreason);
  }

  /** �������� setter ���� */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(ArriveHeaderVO.VBILLCODE, vbillcode);
  }

  /** �Զ�����1 setter ���� */
  public void setVdef1(String vdef1) {
    this.setAttributeValue(ArriveHeaderVO.VDEF1, vdef1);
  }

  /** �Զ�����10 setter ���� */
  public void setVdef10(String vdef10) {
    this.setAttributeValue(ArriveHeaderVO.VDEF10, vdef10);
  }

  /** �Զ�����11 setter ���� */
  public void setVdef11(String vdef11) {
    this.setAttributeValue(ArriveHeaderVO.VDEF11, vdef11);
  }

  /** �Զ�����12 setter ���� */
  public void setVdef12(String vdef12) {
    this.setAttributeValue(ArriveHeaderVO.VDEF12, vdef12);
  }

  /** �Զ�����13 setter ���� */
  public void setVdef13(String vdef13) {
    this.setAttributeValue(ArriveHeaderVO.VDEF13, vdef13);
  }

  /** �Զ�����14 setter ���� */
  public void setVdef14(String vdef14) {
    this.setAttributeValue(ArriveHeaderVO.VDEF14, vdef14);
  }

  /** �Զ�����15 setter ���� */
  public void setVdef15(String vdef15) {
    this.setAttributeValue(ArriveHeaderVO.VDEF15, vdef15);
  }

  /** �Զ�����16 setter ���� */
  public void setVdef16(String vdef16) {
    this.setAttributeValue(ArriveHeaderVO.VDEF16, vdef16);
  }

  /** �Զ�����17 setter ���� */
  public void setVdef17(String vdef17) {
    this.setAttributeValue(ArriveHeaderVO.VDEF17, vdef17);
  }

  /** �Զ�����18 setter ���� */
  public void setVdef18(String vdef18) {
    this.setAttributeValue(ArriveHeaderVO.VDEF18, vdef18);
  }

  /** �Զ�����19 setter ���� */
  public void setVdef19(String vdef19) {
    this.setAttributeValue(ArriveHeaderVO.VDEF19, vdef19);
  }

  /** �Զ�����2 setter ���� */
  public void setVdef2(String vdef2) {
    this.setAttributeValue(ArriveHeaderVO.VDEF2, vdef2);
  }

  /** �Զ�����20 setter ���� */
  public void setVdef20(String vdef20) {
    this.setAttributeValue(ArriveHeaderVO.VDEF20, vdef20);
  }

  /** �Զ�����3 setter ���� */
  public void setVdef3(String vdef3) {
    this.setAttributeValue(ArriveHeaderVO.VDEF3, vdef3);
  }

  /** �Զ�����4 setter ���� */
  public void setVdef4(String vdef4) {
    this.setAttributeValue(ArriveHeaderVO.VDEF4, vdef4);
  }

  /** �Զ�����5 setter ���� */
  public void setVdef5(String vdef5) {
    this.setAttributeValue(ArriveHeaderVO.VDEF5, vdef5);
  }

  /** �Զ�����6 setter ���� */
  public void setVdef6(String vdef6) {
    this.setAttributeValue(ArriveHeaderVO.VDEF6, vdef6);
  }

  /** �Զ�����7 setter ���� */
  public void setVdef7(String vdef7) {
    this.setAttributeValue(ArriveHeaderVO.VDEF7, vdef7);
  }

  /** �Զ�����8 setter ���� */
  public void setVdef8(String vdef8) {
    this.setAttributeValue(ArriveHeaderVO.VDEF8, vdef8);
  }

  /** �Զ�����9 setter ���� */
  public void setVdef9(String vdef9) {
    this.setAttributeValue(ArriveHeaderVO.VDEF9, vdef9);
  }

  /** ��ע setter ���� */
  public void setVmemo(String vmemo) {
    this.setAttributeValue(ArriveHeaderVO.VMEMO, vmemo);
  }

  /** ��Ӧ���˻�˵�� setter ���� */
  public void setVsupbackreason(String vsupbackreason) {
    this.setAttributeValue(ArriveHeaderVO.VSUPBACKREASON, vsupbackreason);
  }

  /** �������� setter ���� */
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(ArriveHeaderVO.VTRANTYPECODE, vtrantypecode);
  }
}
