/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-18 ����07:27:39
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����������뵥��ͷ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-18 ����07:27:39
 */
public class StoreReqAppHeaderVO extends SuperVO {
  /** ������ */
  public static final String APPROVER = "approver";

  /** �Ƶ��� */
  public static final String BILLMAKER = "billmaker";

  /** �Ƿ���� */
  public static final String BURGENCY = "burgency";

  /** ����ʱ�� */
  public static final String CREATIONTIME = "creationtime";

  /** ������ */
  public static final String CREATOR = "creator";

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

  /** ����޸�ʱ�� */
  public static final String MODIFIEDTIME = "modifiedtime";

  /** ����޸��� */
  public static final String MODIFIER = "modifier";

  /** ������ */
  public static final String NTOTALASTNUM = "ntotalastnum";

  /** ��˰�ϼ� */
  public static final String NTOTALORIGMNY = "ntotalorigmny";

  /** ���벿�����°汾 */
  public static final String PK_APPDEPTH = "pk_appdepth";

  /** ���벿�� */
  public static final String PK_APPDEPTH_V = "pk_appdepth_v";

  /** ������ */
  public static final String PK_APPPSNH = "pk_apppsnh";

  /** ���� */
  public static final String PK_GROUP = "pk_group";

  /** �����֯���°汾 */
  public static final String PK_ORG = "pk_org";

  /** �����֯ */
  public static final String PK_ORG_V = "pk_org_v";

  /** ��Ŀ */
  public static final String PK_PROJECT = "pk_project";

  /** �����������뵥 */
  public static final String PK_STOREREQ = "pk_storereq";

  /** ����ʱ�� */
  public static final String TAUDITTIME = "taudittime";

  /** ts */
  public static final String TS = "ts";

  /** ���뵥�� */
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

  /** �������� */
  public static final String VTRANTYPECODE = "vtrantypecode";

  /** �������� */
  public static final String FREQTYPEFLAG = "freqtypeflag";

  /** ���������������� */
  public static final String CTRANTYPEID = "ctrantypeid";

  private static final long serialVersionUID = 5630575391170517509L;

  /** ������ getter ���� */
  public String getApprover() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.APPROVER);
  }

  /** �Ƶ��� getter ���� */
  public String getBillmaker() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.BILLMAKER);
  }

  /** �Ƿ���� getter ���� */
  public UFBoolean getBurgency() {
    return (UFBoolean) this.getAttributeValue(StoreReqAppHeaderVO.BURGENCY);
  }

  /** ����ʱ�� getter ���� */
  public UFDateTime getCreationtime() {
    return (UFDateTime) this
        .getAttributeValue(StoreReqAppHeaderVO.CREATIONTIME);
  }

  /** ������ getter ���� */
  public String getCreator() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.CREATOR);
  }

  /** �������� getter ���� */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(StoreReqAppHeaderVO.DBILLDATE);
  }

  /** �Ƶ����� **/
  public UFDate getDmakedate() {
    return (UFDate) this.getAttributeValue(StoreReqAppHeaderVO.DMAKEDATE);
  }

  /** dr getter ���� */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(StoreReqAppHeaderVO.DR);
  }

  /** ����״̬ getter ���� */
  public Integer getFbillstatus() {
    return (Integer) this.getAttributeValue(StoreReqAppHeaderVO.FBILLSTATUS);
  }

  /** ��ӡ���� getter ���� */
  public Integer getIprintcount() {
    return (Integer) this.getAttributeValue(StoreReqAppHeaderVO.IPRINTCOUNT);
  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pub.SuperVO#getMetaData()
   */
  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.M422X_H);
  }

  /** ����޸�ʱ�� getter ���� */
  public UFDateTime getModifiedtime() {
    return (UFDateTime) this
        .getAttributeValue(StoreReqAppHeaderVO.MODIFIEDTIME);
  }

  /** ����޸��� getter ���� */
  public String getModifier() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.MODIFIER);
  }

  /** ������ getter ���� */
  public UFDouble getNtotalastnum() {
    return (UFDouble) this.getAttributeValue(StoreReqAppHeaderVO.NTOTALASTNUM);
  }

  /** ��˰�ϼ� getter ���� */
  public UFDouble getNtotalorigmny() {
    return (UFDouble) this.getAttributeValue(StoreReqAppHeaderVO.NTOTALORIGMNY);
  }

  /** ���벿�����°汾 getter ���� */
  public String getPk_appdepth() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.PK_APPDEPTH);
  }

  /** ���벿�� getter ���� */
  public String getPk_appdepth_v() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.PK_APPDEPTH_V);
  }

  /** ������ getter ���� */
  public String getPk_apppsnh() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.PK_APPPSNH);
  }

  /** ���� getter ���� */
  public String getPk_group() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.PK_GROUP);
  }

  /** �����֯���°汾 getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.PK_ORG);
  }

  /** �����֯ getter ���� */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.PK_ORG_V);
  }

  /** ��Ŀ getter ���� */
  public String getPk_project() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.PK_PROJECT);
  }

  /** �����������뵥 getter ���� */
  public String getPk_storereq() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.PK_STOREREQ);
  }

  /** ����ʱ�� getter ���� */
  public UFDate getTaudittime() {
    return (UFDate) this.getAttributeValue(StoreReqAppHeaderVO.TAUDITTIME);
  }

  /** ts getter ���� */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(StoreReqAppHeaderVO.TS);
  }

  /** ���뵥�� getter ���� */
  public String getVbillcode() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VBILLCODE);
  }

  /** �Զ�����1 getter ���� */
  public String getVdef1() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF1);
  }

  /** �Զ�����10 getter ���� */
  public String getVdef10() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF10);
  }

  /** �Զ�����11 getter ���� */
  public String getVdef11() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF11);
  }

  /** �Զ�����12 getter ���� */
  public String getVdef12() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF12);
  }

  /** �Զ�����13 getter ���� */
  public String getVdef13() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF13);
  }

  /** �Զ�����14 getter ���� */
  public String getVdef14() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF14);
  }

  /** �Զ�����15 getter ���� */
  public String getVdef15() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF15);
  }

  /** �Զ�����16 getter ���� */
  public String getVdef16() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF16);
  }

  /** �Զ�����17 getter ���� */
  public String getVdef17() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF17);
  }

  /** �Զ�����18 getter ���� */
  public String getVdef18() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF18);
  }

  /** �Զ�����19 getter ���� */
  public String getVdef19() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF19);
  }

  /** �Զ�����2 getter ���� */
  public String getVdef2() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF2);
  }

  /** �Զ�����20 getter ���� */
  public String getVdef20() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF20);
  }

  /** �Զ�����3 getter ���� */
  public String getVdef3() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF3);
  }

  /** �Զ�����4 getter ���� */
  public String getVdef4() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF4);
  }

  /** �Զ�����5 getter ���� */
  public String getVdef5() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF5);
  }

  /** �Զ�����6 getter ���� */
  public String getVdef6() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF6);
  }

  /** �Զ�����7 getter ���� */
  public String getVdef7() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF7);
  }

  /** �Զ�����8 getter ���� */
  public String getVdef8() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF8);
  }

  /** �Զ�����9 getter ���� */
  public String getVdef9() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VDEF9);
  }

  /** ��ע getter ���� */
  public String getVmemo() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VMEMO);
  }

  /** �������� getter ���� */
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VTRANTYPECODE);
  }

  /** �������� getter ���� */
  public Integer getFreqtypeflag() {
    return (Integer) this.getAttributeValue(StoreReqAppHeaderVO.FREQTYPEFLAG);
  }

  /** ���������������� getter ���� */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.CTRANTYPEID);
  }

  /** ������ setter ���� */
  public void setApprover(String approver) {
    this.setAttributeValue(StoreReqAppHeaderVO.APPROVER, approver);
  }

  /** �Ƶ��� setter ���� */
  public void setBillmaker(String billmaker) {
    this.setAttributeValue(StoreReqAppHeaderVO.BILLMAKER, billmaker);
  }

  /** �Ƿ���� setter ���� */
  public void setBurgency(UFBoolean burgency) {
    this.setAttributeValue(StoreReqAppHeaderVO.BURGENCY, burgency);
  }

  /** ����ʱ�� setter ���� */
  public void setCreationtime(UFDateTime creationtime) {
    this.setAttributeValue(StoreReqAppHeaderVO.CREATIONTIME, creationtime);
  }

  /** ������ setter ���� */
  public void setCreator(String creator) {
    this.setAttributeValue(StoreReqAppHeaderVO.CREATOR, creator);
  }

  /** �������� setter ���� */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(StoreReqAppHeaderVO.DBILLDATE, dbilldate);
  }

  /** �Ƶ����� **/
  public void setDmakedate(UFDate dmakedate) {
    this.setAttributeValue(StoreReqAppHeaderVO.DMAKEDATE, dmakedate);
  }

  /** dr setter ���� */
  public void setDr(Integer dr) {
    this.setAttributeValue(StoreReqAppHeaderVO.DR, dr);
  }

  /** ����״̬ setter ���� */
  public void setFbillstatus(Integer fbillstatus) {
    this.setAttributeValue(StoreReqAppHeaderVO.FBILLSTATUS, fbillstatus);
  }

  /** ��ӡ���� setter ���� */
  public void setIprintcount(Integer iprintcount) {
    this.setAttributeValue(StoreReqAppHeaderVO.IPRINTCOUNT, iprintcount);
  }

  /** ����޸�ʱ�� setter ���� */
  public void setModifiedtime(UFDateTime modifiedtime) {
    this.setAttributeValue(StoreReqAppHeaderVO.MODIFIEDTIME, modifiedtime);
  }

  /** ����޸��� setter ���� */
  public void setModifier(String modifier) {
    this.setAttributeValue(StoreReqAppHeaderVO.MODIFIER, modifier);
  }

  /** ������ setter ���� */
  public void setNtotalastnum(UFDouble ntotalastnum) {
    this.setAttributeValue(StoreReqAppHeaderVO.NTOTALASTNUM, ntotalastnum);
  }

  /** ��˰�ϼ� setter ���� */
  public void setNtotalorigmny(UFDouble ntotalorigmny) {
    this.setAttributeValue(StoreReqAppHeaderVO.NTOTALORIGMNY, ntotalorigmny);
  }

  /** ���벿�����°汾 setter ���� */
  public void setPk_appdepth(String pk_appdepth) {
    this.setAttributeValue(StoreReqAppHeaderVO.PK_APPDEPTH, pk_appdepth);
  }

  /** ���벿�� setter ���� */
  public void setPk_appdepth_v(String pk_appdepth_v) {
    this.setAttributeValue(StoreReqAppHeaderVO.PK_APPDEPTH_V, pk_appdepth_v);
  }

  /** ������ setter ���� */
  public void setPk_apppsnh(String pk_apppsnh) {
    this.setAttributeValue(StoreReqAppHeaderVO.PK_APPPSNH, pk_apppsnh);
  }

  /** ���� setter ���� */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(StoreReqAppHeaderVO.PK_GROUP, pk_group);
  }

  /** �����֯���°汾 setter ���� */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(StoreReqAppHeaderVO.PK_ORG, pk_org);
  }

  /** �����֯ setter ���� */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(StoreReqAppHeaderVO.PK_ORG_V, pk_org_v);
  }

  /** ��Ŀ setter ���� */
  public void setPk_project(String pk_project) {
    this.setAttributeValue(StoreReqAppHeaderVO.PK_PROJECT, pk_project);
  }

  /** �����������뵥 setter ���� */
  public void setPk_storereq(String pk_storereq) {
    this.setAttributeValue(StoreReqAppHeaderVO.PK_STOREREQ, pk_storereq);
  }

  /** ����ʱ�� setter ���� */
  public void setTaudittime(UFDate taudittime) {
    this.setAttributeValue(StoreReqAppHeaderVO.TAUDITTIME, taudittime);
  }

  /** ts setter ���� */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(StoreReqAppHeaderVO.TS, ts);
  }

  /** ���뵥�� setter ���� */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(StoreReqAppHeaderVO.VBILLCODE, vbillcode);
  }

  /** �Զ�����1 setter ���� */
  public void setVdef1(String vdef1) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF1, vdef1);
  }

  /** �Զ�����10 setter ���� */
  public void setVdef10(String vdef10) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF10, vdef10);
  }

  /** �Զ�����11 setter ���� */
  public void setVdef11(String vdef11) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF11, vdef11);
  }

  /** �Զ�����12 setter ���� */
  public void setVdef12(String vdef12) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF12, vdef12);
  }

  /** �Զ�����13 setter ���� */
  public void setVdef13(String vdef13) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF13, vdef13);
  }

  /** �Զ�����14 setter ���� */
  public void setVdef14(String vdef14) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF14, vdef14);
  }

  /** �Զ�����15 setter ���� */
  public void setVdef15(String vdef15) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF15, vdef15);
  }

  /** �Զ�����16 setter ���� */
  public void setVdef16(String vdef16) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF16, vdef16);
  }

  /** �Զ�����17 setter ���� */
  public void setVdef17(String vdef17) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF17, vdef17);
  }

  /** �Զ�����18 setter ���� */
  public void setVdef18(String vdef18) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF18, vdef18);
  }

  /** �Զ�����19 setter ���� */
  public void setVdef19(String vdef19) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF19, vdef19);
  }

  /** �Զ�����2 setter ���� */
  public void setVdef2(String vdef2) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF2, vdef2);
  }

  /** �Զ�����20 setter ���� */
  public void setVdef20(String vdef20) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF20, vdef20);
  }

  /** �Զ�����3 setter ���� */
  public void setVdef3(String vdef3) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF3, vdef3);
  }

  /** �Զ�����4 setter ���� */
  public void setVdef4(String vdef4) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF4, vdef4);
  }

  /** �Զ�����5 setter ���� */
  public void setVdef5(String vdef5) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF5, vdef5);
  }

  /** �Զ�����6 setter ���� */
  public void setVdef6(String vdef6) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF6, vdef6);
  }

  /** �Զ�����7 setter ���� */
  public void setVdef7(String vdef7) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF7, vdef7);
  }

  /** �Զ�����8 setter ���� */
  public void setVdef8(String vdef8) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF8, vdef8);
  }

  /** �Զ�����9 setter ���� */
  public void setVdef9(String vdef9) {
    this.setAttributeValue(StoreReqAppHeaderVO.VDEF9, vdef9);
  }

  /** ��ע setter ���� */
  public void setVmemo(String vmemo) {
    this.setAttributeValue(StoreReqAppHeaderVO.VMEMO, vmemo);
  }

  /** �������� setter ���� */
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(StoreReqAppHeaderVO.VTRANTYPECODE, vtrantypecode);
  }

  /** �������� setter ���� */
  public void setFreqtypeflag(Integer freqtypeflag) {
    this.setAttributeValue(StoreReqAppHeaderVO.FREQTYPEFLAG, freqtypeflag);
  }

  /** ���������������� setter ���� */
  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(StoreReqAppHeaderVO.CTRANTYPEID, ctrantypeid);
  }
}
