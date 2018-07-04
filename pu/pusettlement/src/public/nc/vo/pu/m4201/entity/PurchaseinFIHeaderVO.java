package nc.vo.pu.m4201.entity;

import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class PurchaseinFIHeaderVO extends SuperVO {

  /** �Զ��������־ **/
  public static final String BAUTOTOFI = "bautotofi";

  /** �Ƶ��� */
  public static final String BILLMAKER = "billmaker";

  /** ������ⵥ **/
  public static final String BITINBILL = "bitinbill";

  /** �Ƿ���ͨ�ɹ� */
  public static final String BNORMPUR = "bnormpur";

  /** ����ó�� */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /** �������ͱ��� **/
  public static final String CBILLTYPECODE = "cbilltypecode";

  /** ����ʱ�� **/
  public static final String CREATIONTIME = "creationtime";

  /** ������ **/
  public static final String CREATOR = "creator";

  /** �ջ�����/���� */
  public static final String CRECECOUNTRYID = "crececountryid";

  /** ������/���� */
  public static final String CSENDCOUNTRYID = "csendcountryid";

  /** ���䷽ʽ */
  public static final String CSENDTYPEID = "csendtypeid";

  /** ��˰��/���� */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /** ó������ */
  public static final String CTRADEWORDID = "ctradewordid";

  /** �������� */
  public static final String CTRANTYPEID = "ctrantypeid";

  /** ���Ա **/
  public static final String CWHSMANAGERID = "cwhsmanagerid";

  /** �������� **/
  public static final String DBILLDATE = "dbilldate";

  /** �������� */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /** �˻���־ **/
  public static final String FREPLENISHFLAG = "freplenishflag";

  /** �޸�ʱ�� **/
  public static final String MODIFIEDTIME = "modifiedtime";

  /** �޸��� **/
  public static final String MODIFIER = "modifier";

  /** ҵ������ **/
  public static final String PK_BUSITYPE = "pk_busitype";

  /** ����������Ϣ **/
  public static final String PK_DEPT = "pk_dept";

  /** ���� **/
  public static final String PK_DEPT_V = "pk_dept_v";

  /** ���� **/
  public static final String PK_GROUP = "pk_group";

  /** �����֯ **/
  public static final String PK_ORG = "pk_org";

  /** �����֯�汾 **/
  public static final String PK_ORG_V = "pk_org_v";

  /** ҵ��Ա **/
  public static final String PK_PSNDOC = "pk_psndoc";

  /** �ɹ���ͷID **/
  public static final String PK_STOCKPS = "pk_stockps";

  /** �ֿ� **/
  public static final String PK_STORDOC = "pk_stordoc";

  /** ʱ��� **/
  public static final String TS = "ts";

  /** ���ݺ� **/
  public static final String VBILLCODE = "vbillcode";

  /** �Զ�����1 **/
  public static final String VDEF1 = "vdef1";

  /** �Զ�����10 **/
  public static final String VDEF10 = "vdef10";

  /** �Զ�����11 **/
  public static final String VDEF11 = "vdef11";

  /** �Զ�����12 **/
  public static final String VDEF12 = "vdef12";

  /** �Զ�����13 **/
  public static final String VDEF13 = "vdef13";

  /** �Զ�����14 **/
  public static final String VDEF14 = "vdef14";

  /** �Զ�����15 **/
  public static final String VDEF15 = "vdef15";

  /** �Զ�����16 **/
  public static final String VDEF16 = "vdef16";

  /** �Զ�����17 **/
  public static final String VDEF17 = "vdef17";

  /** �Զ�����18 **/
  public static final String VDEF18 = "vdef18";

  /** �Զ�����19 **/
  public static final String VDEF19 = "vdef19";

  /** �Զ�����2 **/
  public static final String VDEF2 = "vdef2";

  /** �Զ�����20 **/
  public static final String VDEF20 = "vdef20";

  /** �Զ�����3 **/
  public static final String VDEF3 = "vdef3";

  /** �Զ�����4 **/
  public static final String VDEF4 = "vdef4";

  /** �Զ�����5 **/
  public static final String VDEF5 = "vdef5";

  /** �Զ�����6 **/
  public static final String VDEF6 = "vdef6";

  /** �Զ�����7 **/
  public static final String VDEF7 = "vdef7";

  /** �Զ�����8 **/
  public static final String VDEF8 = "vdef8";

  /** �Զ�����9 **/
  public static final String VDEF9 = "vdef9";

  /** ��ע **/
  public static final String VNOTE = "vnote";

  /** �������� **/
  public static final String VTRANTYPECODE = "vtrantypecode";
  
  /** ǩ���� **/
  public static final String APPROVER = "approver";

  /** ǩ������ **/
  public static final String TAUDITTIME = "taudittime";

  private static final long serialVersionUID = 2792868667841242256L;

  /** �Զ��������־ **/
  public UFBoolean getBautotofi() {
    return (UFBoolean) this.getAttributeValue(PurchaseinFIHeaderVO.BAUTOTOFI);
  }

  /** �Ƶ��� getter ���� */
  public String getBillmaker() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.BILLMAKER);
  }

  /** ������ⵥ **/
  public UFBoolean getBitinbill() {
    return (UFBoolean) this.getAttributeValue(PurchaseinFIHeaderVO.BITINBILL);
  }

  /** �Ƿ���ͨ�ɹ� getter ���� */
  public UFBoolean getBnormpur() {
    return (UFBoolean) this.getAttributeValue(PurchaseinFIHeaderVO.BNORMPUR);
  }

  /** ����ó�� getter ���� */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this
        .getAttributeValue(PurchaseinFIHeaderVO.BTRIATRADEFLAG);
  }

  /** �������ͱ��� **/
  public String getCbilltypecode() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.CBILLTYPECODE);
  }

  /** ����ʱ�� **/
  public UFDateTime getCreationtime() {
    return (UFDateTime) this
        .getAttributeValue(PurchaseinFIHeaderVO.CREATIONTIME);
  }

  /** ������ **/
  public String getCreator() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.CREATOR);
  }

  /** �ջ�����/���� getter ���� */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.CRECECOUNTRYID);
  }

  /** ������/���� getter ���� */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.CSENDCOUNTRYID);
  }

  /** ���䷽ʽ getter ���� */
  public String getCsendtypeid() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.CSENDTYPEID);
  }

  /** ��˰��/���� getter ���� */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.CTAXCOUNTRYID);
  }

  /** ó������ getter ���� */
  public String getCtradewordid() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.CTRADEWORDID);
  }

  /** �������� getter ���� */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.CTRANTYPEID);
  }

  /** ���Ա **/
  public String getCwhsmanagerid() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.CWHSMANAGERID);
  }

  /** �������� **/
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(PurchaseinFIHeaderVO.DBILLDATE);
  }

  /** �������� getter ���� */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(PurchaseinFIHeaderVO.FBUYSELLFLAG);
  }

  /** �˻���־ **/
  public UFBoolean getFreplenishflag() {
    return (UFBoolean) this
        .getAttributeValue(PurchaseinFIHeaderVO.FREPLENISHFLAG);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(PUEntity.PurchaseinFI_H);
    return meta;
  }

  /** �޸�ʱ�� **/
  public UFDateTime getModifiedtime() {
    return (UFDateTime) this
        .getAttributeValue(PurchaseinFIHeaderVO.MODIFIEDTIME);
  }

  /** �޸��� **/
  public String getModifier() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.MODIFIER);
  }

  /** ҵ������ **/
  public String getPk_busitype() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.PK_BUSITYPE);
  }

  /** ����������Ϣ **/
  public String getPk_dept() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.PK_DEPT);
  }

  /** ���� **/
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.PK_DEPT_V);
  }

  /** ���� **/
  public String getPk_group() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.PK_GROUP);
  }

  /** �����֯ **/
  public String getPk_org() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.PK_ORG);
  }

  /** �����֯�汾 **/
  public String getPk_org_v() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.PK_ORG_V);
  }

  /** ҵ��Ա **/
  public String getPk_psndoc() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.PK_PSNDOC);
  }

  /** �ɹ���ͷID **/
  public String getPk_stockps() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.PK_STOCKPS);
  }

  /** �ֿ� **/
  public String getPk_stordoc() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.PK_STORDOC);
  }

  /** ʱ��� **/
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(PurchaseinFIHeaderVO.TS);
  }

  /** ���ݺ� **/
  public String getVbillcode() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VBILLCODE);
  }

  /** �Զ�����1 **/
  public String getVdef1() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VDEF1);
  }

  /** �Զ�����10 **/
  public String getVdef10() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VDEF10);
  }

  /** �Զ�����11 **/
  public String getVdef11() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VDEF11);
  }

  /** �Զ�����12 **/
  public String getVdef12() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VDEF12);
  }

  /** �Զ�����13 **/
  public String getVdef13() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VDEF13);
  }

  /** �Զ�����14 **/
  public String getVdef14() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VDEF14);
  }

  /** �Զ�����15 **/
  public String getVdef15() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VDEF15);
  }

  /** �Զ�����16 **/
  public String getVdef16() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VDEF16);
  }

  /** �Զ�����17 **/
  public String getVdef17() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VDEF17);
  }

  /** �Զ�����18 **/
  public String getVdef18() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VDEF18);
  }

  /** �Զ�����19 **/
  public String getVdef19() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VDEF19);
  }

  /** �Զ�����2 **/
  public String getVdef2() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VDEF2);
  }

  /** �Զ�����20 **/
  public String getVdef20() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VDEF20);
  }

  /** �Զ�����3 **/
  public String getVdef3() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VDEF3);
  }

  /** �Զ�����4 **/
  public String getVdef4() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VDEF4);
  }

  /** �Զ�����5 **/
  public String getVdef5() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VDEF5);
  }

  /** �Զ�����6 **/
  public String getVdef6() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VDEF6);
  }

  /** �Զ�����7 **/
  public String getVdef7() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VDEF7);
  }

  /** �Զ�����8 **/
  public String getVdef8() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VDEF8);
  }

  /** �Զ�����9 **/
  public String getVdef9() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VDEF9);
  }

  /** ��ע **/
  public String getVnote() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VNOTE);
  }

  /** �������� **/
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(PurchaseinFIHeaderVO.VTRANTYPECODE);
  }
  
  /** ǩ����  **/
  public String getApprover() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.APPROVER);
  }

  /** ǩ������  **/
  public UFDate getTaudittime() {
    return (UFDate) this.getAttributeValue(InitialEstHeaderVO.TAUDITTIME);
  }
  
  /** �Զ��������־ **/
  public void setBautotofi(UFBoolean bautotofi) {
    this.setAttributeValue(PurchaseinFIHeaderVO.BAUTOTOFI, bautotofi);
  }

  /** �Ƶ��� setter ���� */
  public void setBillmaker(String billmaker) {
    this.setAttributeValue(PurchaseinFIHeaderVO.BILLMAKER, billmaker);
  }

  /** ������ⵥ **/
  public void setBitinbill(UFBoolean bitinbill) {
    this.setAttributeValue(PurchaseinFIHeaderVO.BITINBILL, bitinbill);
  }

  /** �Ƿ���ͨ�ɹ� setter ���� */
  public void setBnormpur(UFBoolean bnormpur) {
    this.setAttributeValue(PurchaseinFIHeaderVO.BNORMPUR, bnormpur);
  }

  /** ����ó�� setter ���� */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(PurchaseinFIHeaderVO.BTRIATRADEFLAG, btriatradeflag);
  }

  /** �������ͱ��� **/
  public void setCbilltypecode(String cbilltypecode) {
    this.setAttributeValue(PurchaseinFIHeaderVO.CBILLTYPECODE, cbilltypecode);
  }

  /** ����ʱ�� **/
  public void setCreationtime(UFDateTime creationtime) {
    this.setAttributeValue(PurchaseinFIHeaderVO.CREATIONTIME, creationtime);
  }

  /** ������ **/
  public void setCreator(String creator) {
    this.setAttributeValue(PurchaseinFIHeaderVO.CREATOR, creator);
  }

  /** �ջ�����/���� setter ���� */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(PurchaseinFIHeaderVO.CRECECOUNTRYID, crececountryid);
  }

  /** ������/���� setter ���� */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(PurchaseinFIHeaderVO.CSENDCOUNTRYID, csendcountryid);
  }

  /** ���䷽ʽ setter ���� */
  public void setCsendtypeid(String csendtypeid) {
    this.setAttributeValue(PurchaseinFIHeaderVO.CSENDTYPEID, csendtypeid);
  }

  /** ��˰��/���� setter ���� */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(PurchaseinFIHeaderVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  /** ó������ setter ���� */
  public void setCtradewordid(String ctradewordid) {
    this.setAttributeValue(PurchaseinFIHeaderVO.CTRADEWORDID, ctradewordid);
  }

  /** �������� setter ���� */
  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(PurchaseinFIHeaderVO.CTRANTYPEID, ctrantypeid);
  }

  /** ���Ա **/
  public void setCwhsmanagerid(String cwhsmanagerid) {
    this.setAttributeValue(PurchaseinFIHeaderVO.CWHSMANAGERID, cwhsmanagerid);
  }

  /** �������� **/
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(PurchaseinFIHeaderVO.DBILLDATE, dbilldate);
  }

  /** �������� setter ���� */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(PurchaseinFIHeaderVO.FBUYSELLFLAG, fbuysellflag);
  }

  /** �˻���־ **/
  public void setFreplenishflag(UFBoolean freplenishflag) {
    this.setAttributeValue(PurchaseinFIHeaderVO.FREPLENISHFLAG, freplenishflag);
  }

  /** �޸�ʱ�� **/
  public void setModifiedtime(UFDateTime modifiedtime) {
    this.setAttributeValue(PurchaseinFIHeaderVO.MODIFIEDTIME, modifiedtime);
  }

  /** �޸��� **/
  public void setModifier(String modifier) {
    this.setAttributeValue(PurchaseinFIHeaderVO.MODIFIER, modifier);
  }

  /** ҵ������ **/
  public void setPk_busitype(String pk_busitype) {
    this.setAttributeValue(PurchaseinFIHeaderVO.PK_BUSITYPE, pk_busitype);
  }

  /** ����������Ϣ **/
  public void setPk_dept(String pk_dept) {
    this.setAttributeValue(PurchaseinFIHeaderVO.PK_DEPT, pk_dept);
  }

  /** ���� **/
  public void setPk_dept_v(String pk_dept_v) {
    this.setAttributeValue(PurchaseinFIHeaderVO.PK_DEPT_V, pk_dept_v);
  }

  /** ���� **/
  public void setPk_group(String pk_group) {
    this.setAttributeValue(PurchaseinFIHeaderVO.PK_GROUP, pk_group);
  }

  /** �����֯ **/
  public void setPk_org(String pk_org) {
    this.setAttributeValue(PurchaseinFIHeaderVO.PK_ORG, pk_org);
  }

  /** �����֯�汾 **/
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(PurchaseinFIHeaderVO.PK_ORG_V, pk_org_v);
  }

  /** ҵ��Ա **/
  public void setPk_psndoc(String pk_psndoc) {
    this.setAttributeValue(PurchaseinFIHeaderVO.PK_PSNDOC, pk_psndoc);
  }

  /** �ɹ���ͷID **/
  public void setPk_stockps(String pk_stockps) {
    this.setAttributeValue(PurchaseinFIHeaderVO.PK_STOCKPS, pk_stockps);
  }

  /** �ֿ� **/
  public void setPk_stordoc(String pk_stordoc) {
    this.setAttributeValue(PurchaseinFIHeaderVO.PK_STORDOC, pk_stordoc);
  }

  /** ʱ��� **/
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(PurchaseinFIHeaderVO.TS, ts);
  }

  /** ���ݺ� **/
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VBILLCODE, vbillcode);
  }

  /** �Զ�����1 **/
  public void setVdef1(String vdef1) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VDEF1, vdef1);
  }

  /** �Զ�����10 **/
  public void setVdef10(String vdef10) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VDEF10, vdef10);
  }

  /** �Զ�����11 **/
  public void setVdef11(String vdef11) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VDEF11, vdef11);
  }

  /** �Զ�����12 **/
  public void setVdef12(String vdef12) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VDEF12, vdef12);
  }

  /** �Զ�����13 **/
  public void setVdef13(String vdef13) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VDEF13, vdef13);
  }

  /** �Զ�����14 **/
  public void setVdef14(String vdef14) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VDEF14, vdef14);
  }

  /** �Զ�����15 **/
  public void setVdef15(String vdef15) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VDEF15, vdef15);
  }

  /** �Զ�����16 **/
  public void setVdef16(String vdef16) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VDEF16, vdef16);
  }

  /** �Զ�����17 **/
  public void setVdef17(String vdef17) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VDEF17, vdef17);
  }

  /** �Զ�����18 **/
  public void setVdef18(String vdef18) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VDEF18, vdef18);
  }

  /** �Զ�����19 **/
  public void setVdef19(String vdef19) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VDEF19, vdef19);
  }

  /** �Զ�����2 **/
  public void setVdef2(String vdef2) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VDEF2, vdef2);
  }

  /** �Զ�����20 **/
  public void setVdef20(String vdef20) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VDEF20, vdef20);
  }

  /** �Զ�����3 **/
  public void setVdef3(String vdef3) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VDEF3, vdef3);
  }

  /** �Զ�����4 **/
  public void setVdef4(String vdef4) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VDEF4, vdef4);
  }

  /** �Զ�����5 **/
  public void setVdef5(String vdef5) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VDEF5, vdef5);
  }

  /** �Զ�����6 **/
  public void setVdef6(String vdef6) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VDEF6, vdef6);
  }

  /** �Զ�����7 **/
  public void setVdef7(String vdef7) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VDEF7, vdef7);
  }

  /** �Զ�����8 **/
  public void setVdef8(String vdef8) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VDEF8, vdef8);
  }

  /** �Զ�����9 **/
  public void setVdef9(String vdef9) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VDEF9, vdef9);
  }

  /** ��ע **/
  public void setVnote(String vnote) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VNOTE, vnote);
  }

  /** �������� **/
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(PurchaseinFIHeaderVO.VTRANTYPECODE, vtrantypecode);
  }

  /** ǩ���� **/
  public void setApprover(String approver) {
    this.setAttributeValue(InitialEstHeaderVO.APPROVER, approver);
  }
  
  /** ǩ������ **/
  public void setTaudittime(UFDate taudittime) {
    this.setAttributeValue(InitialEstHeaderVO.TAUDITTIME, taudittime);
  }

}
