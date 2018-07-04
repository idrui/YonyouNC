/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-25 ����07:52:05
 */
package nc.vo.pu.m4t.entity;

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
 * <li>�ڳ��ݹ�����ͷVO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-25 ����07:52:05
 */
public class InitialEstHeaderVO extends SuperVO {
  /** ������ */
  public static final String APPROVER = "approver";

  /** �Ƶ��� */
  public static final String BILLMAKER = "billmaker";

  /** �Ƿ���ͨ�ɹ� */
  public static final String BNORMPUR = "bnormpur";

  /** ��λ�� */
  public static final String CCURRENCYID = "ccurrencyid";

  /** ���� */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

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

  /** ����޸�ʱ�� */
  public static final String MODIFIEDTIME = "modifiedtime";

  /** ����޸��� */
  public static final String MODIFIER = "modifier";

  /** �۱����� */
  public static final String NEXCHANGERATE = "nexchangerate";

  /** ������ */
  public static final String NTOTALASTNUM = "ntotalastnum";

  /** �ܼ�˰�ϼ� */
  public static final String NTOTALORIGMNY = "ntotalorigmny";

  /** ҵ��Ա */
  public static final String PK_BIZPSN = "pk_bizpsn";

  /** ҵ������ */
  public static final String PK_BUSITYPE = "pk_busitype";

  /** �ɱ��� */
  public static final String PK_COSTREGION = "pk_costregion";

  /** �ɹ�����ԭʼ�汾 */
  public static final String PK_DEPT = "pk_dept";

  /** �ɹ����� */
  public static final String PK_DEPT_V = "pk_dept_v";

  /** �������� */
  public static final String PK_GROUP = "pk_group";

  /** �ڳ��ݹ��� */
  public static final String PK_INITIALEST = "pk_initialest";

  /** ��Ʊ��Ӧ�� */
  public static final String PK_INVCSUPLLIER = "pk_invcsupllier";

  /** ����Ա */
  public static final String PK_MANAGEPSN = "pk_managepsn";

  /** ������֯ */
  public static final String PK_ORG = "pk_org";

  /** ������֯�汾 */
  public static final String PK_ORG_V = "pk_org_v";

  /** �ɹ���֯���°汾 */
  public static final String PK_PURCHASEORG = "pk_purchaseorg";

  /** �ɹ���֯ */
  public static final String PK_PURCHASEORG_V = "pk_purchaseorg_v";

  /** �����֯ */
  public static final String PK_STOCKORG = "pk_stockorg";

  /** �����֯�汾 */
  public static final String PK_STOCKORG_V = "pk_stockorg_v";

  /** �ֿ� */
  public static final String PK_STORDOC = "pk_stordoc";

  /** ��Ӧ�� */
  public static final String PK_SUPPLIER = "pk_supplier";

  /** �������� */
  public static final String TAUDITTIME = "taudittime";

  /** ts */
  public static final String TS = "ts";

  /** �ڳ��ݹ����� */
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

  /** �ڳ��ݹ����� */
  public static final String VTRANTYPECODE = "vtrantypecode";

  /**
   * 
   */
  private static final long serialVersionUID = 1833309499875207815L;

  /** ������ getter ���� */
  public String getApprover() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.APPROVER);
  }

  /** �Ƶ��� getter ���� */
  public String getBillmaker() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.BILLMAKER);
  }

  /** �Ƿ���ͨ�ɹ� getter ���� */
  public UFBoolean getBnormpur() {
    return (UFBoolean) this.getAttributeValue(InitialEstHeaderVO.BNORMPUR);
  }

  /** ��λ�� getter ���� */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.CCURRENCYID);
  }

  /** ���� getter ���� */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.CORIGCURRENCYID);
  }

  /** ����ʱ�� getter ���� */
  public UFDateTime getCreationtime() {
    return (UFDateTime) this.getAttributeValue(InitialEstHeaderVO.CREATIONTIME);
  }

  /** ������ getter ���� */
  public String getCreator() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.CREATOR);
  }

  /** �������� getter ���� */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.CTRANTYPEID);
  }

  /** �������� getter ���� */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(InitialEstHeaderVO.DBILLDATE);
  }

  /** �Ƶ����� **/
  public UFDate getDmakedate() {
    return (UFDate) this.getAttributeValue(InitialEstHeaderVO.DMAKEDATE);
  }

  /** dr getter ���� */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(InitialEstHeaderVO.DR);
  }

  /** ����״̬ getter ���� */
  public Integer getFbillstatus() {
    return (Integer) this.getAttributeValue(InitialEstHeaderVO.FBILLSTATUS);
  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pub.SuperVO#getMetaData()
   */
  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.M4T_H);
  }

  /** ����޸�ʱ�� getter ���� */
  public UFDateTime getModifiedtime() {
    return (UFDateTime) this.getAttributeValue(InitialEstHeaderVO.MODIFIEDTIME);
  }

  /** ����޸��� getter ���� */
  public String getModifier() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.MODIFIER);
  }

  /** �۱����� getter ���� */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(InitialEstHeaderVO.NEXCHANGERATE);
  }

  /** ������ getter ���� */
  public UFDouble getNtotalastnum() {
    return (UFDouble) this.getAttributeValue(InitialEstHeaderVO.NTOTALASTNUM);
  }

  /** �ܼ�˰�ϼ� getter ���� */
  public UFDouble getNtotalorigmny() {
    return (UFDouble) this.getAttributeValue(InitialEstHeaderVO.NTOTALORIGMNY);
  }

  /** ҵ��Ա getter ���� */
  public String getPk_bizpsn() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_BIZPSN);
  }

  /** ҵ������ getter ���� */
  public String getPk_busitype() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_BUSITYPE);
  }

  /** �ɱ��� getter ���� */
  public String getPk_costregion() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_COSTREGION);
  }

  /** �ɹ�����ԭʼ�汾 getter ���� */
  public String getPk_dept() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_DEPT);
  }

  /** �ɹ����� getter ���� */
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_DEPT_V);
  }

  /** �������� getter ���� */
  public String getPk_group() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_GROUP);
  }

  /** �ڳ��ݹ��� getter ���� */
  public String getPk_initialest() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_INITIALEST);
  }

  /** ��Ʊ��Ӧ�� getter ���� */
  public String getPk_invcsupllier() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_INVCSUPLLIER);
  }

  /** ����Ա getter ���� */
  public String getPk_managepsn() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_MANAGEPSN);
  }

  /** ������֯ getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_ORG);
  }

  /** ������֯�汾 getter ���� */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_ORG_V);
  }

  /** �ɹ���֯���°汾 getter ���� */
  public String getPk_purchaseorg() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_PURCHASEORG);
  }

  /** �ɹ���֯ getter ���� */
  public String getPk_purchaseorg_v() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_PURCHASEORG_V);
  }

  /** �����֯ getter ���� */
  public String getPk_stockorg() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_STOCKORG);
  }

  /** �����֯�汾 getter ���� */
  public String getPk_stockorg_v() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_STOCKORG_V);
  }

  /** �ֿ� getter ���� */
  public String getPk_stordoc() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_STORDOC);
  }

  /** ��Ӧ�� getter ���� */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_SUPPLIER);
  }

  /** �������� getter ���� */
  public UFDate getTaudittime() {
    return (UFDate) this.getAttributeValue(InitialEstHeaderVO.TAUDITTIME);
  }

  /** ts getter ���� */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(InitialEstHeaderVO.TS);
  }

  /** �ڳ��ݹ����� getter ���� */
  public String getVbillcode() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VBILLCODE);
  }

  /** �Զ�����1 getter ���� */
  public String getVdef1() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF1);
  }

  /** �Զ�����10 getter ���� */
  public String getVdef10() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF10);
  }

  /** �Զ�����11 getter ���� */
  public String getVdef11() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF11);
  }

  /** �Զ�����12 getter ���� */
  public String getVdef12() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF12);
  }

  /** �Զ�����13 getter ���� */
  public String getVdef13() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF13);
  }

  /** �Զ�����14 getter ���� */
  public String getVdef14() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF14);
  }

  /** �Զ�����15 getter ���� */
  public String getVdef15() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF15);
  }

  /** �Զ�����16 getter ���� */
  public String getVdef16() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF16);
  }

  /** �Զ�����17 getter ���� */
  public String getVdef17() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF17);
  }

  /** �Զ�����18 getter ���� */
  public String getVdef18() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF18);
  }

  /** �Զ�����19 getter ���� */
  public String getVdef19() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF19);
  }

  /** �Զ�����2 getter ���� */
  public String getVdef2() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF2);
  }

  /** �Զ�����20 getter ���� */
  public String getVdef20() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF20);
  }

  /** �Զ�����3 getter ���� */
  public String getVdef3() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF3);
  }

  /** �Զ�����4 getter ���� */
  public String getVdef4() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF4);
  }

  /** �Զ�����5 getter ���� */
  public String getVdef5() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF5);
  }

  /** �Զ�����6 getter ���� */
  public String getVdef6() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF6);
  }

  /** �Զ�����7 getter ���� */
  public String getVdef7() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF7);
  }

  /** �Զ�����8 getter ���� */
  public String getVdef8() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF8);
  }

  /** �Զ�����9 getter ���� */
  public String getVdef9() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VDEF9);
  }

  /** ��ע getter ���� */
  public String getVmemo() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VMEMO);
  }

  /** �ڳ��ݹ����� getter ���� */
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VTRANTYPECODE);
  }

  /** ������ setter ���� */
  public void setApprover(String approver) {
    this.setAttributeValue(InitialEstHeaderVO.APPROVER, approver);
  }

  /** �Ƶ��� setter ���� */
  public void setBillmaker(String billmaker) {
    this.setAttributeValue(InitialEstHeaderVO.BILLMAKER, billmaker);
  }

  /** �Ƿ���ͨ�ɹ� setter ���� */
  public void setBnormpur(UFBoolean bnormpur) {
    this.setAttributeValue(InitialEstHeaderVO.BNORMPUR, bnormpur);
  }

  /** ��λ�� setter ���� */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(InitialEstHeaderVO.CCURRENCYID, ccurrencyid);
  }

  /** ���� setter ���� */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(InitialEstHeaderVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /** ����ʱ�� setter ���� */
  public void setCreationtime(UFDateTime creationtime) {
    this.setAttributeValue(InitialEstHeaderVO.CREATIONTIME, creationtime);
  }

  /** ������ setter ���� */
  public void setCreator(String creator) {
    this.setAttributeValue(InitialEstHeaderVO.CREATOR, creator);
  }

  /** �������� setter ���� */
  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(InitialEstHeaderVO.CTRANTYPEID, ctrantypeid);
  }

  /** �������� setter ���� */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(InitialEstHeaderVO.DBILLDATE, dbilldate);
  }

  /** �Ƶ����� **/
  public void setDmakedate(UFDate dmakedate) {
    this.setAttributeValue(InitialEstHeaderVO.DMAKEDATE, dmakedate);
  }

  /** dr setter ���� */
  public void setDr(Integer dr) {
    this.setAttributeValue(InitialEstHeaderVO.DR, dr);
  }

  /** ����״̬ setter ���� */
  public void setFbillstatus(Integer fbillstatus) {
    this.setAttributeValue(InitialEstHeaderVO.FBILLSTATUS, fbillstatus);
  }

  /** ����޸�ʱ�� setter ���� */
  public void setModifiedtime(UFDateTime modifiedtime) {
    this.setAttributeValue(InitialEstHeaderVO.MODIFIEDTIME, modifiedtime);
  }

  /** ����޸��� setter ���� */
  public void setModifier(String modifier) {
    this.setAttributeValue(InitialEstHeaderVO.MODIFIER, modifier);
  }

  /** �۱����� setter ���� */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(InitialEstHeaderVO.NEXCHANGERATE, nexchangerate);
  }

  /** ������ setter ���� */
  public void setNtotalastnum(UFDouble ntotalastnum) {
    this.setAttributeValue(InitialEstHeaderVO.NTOTALASTNUM, ntotalastnum);
  }

  /** �ܼ�˰�ϼ� setter ���� */
  public void setNtotalorigmny(UFDouble ntotalorigmny) {
    this.setAttributeValue(InitialEstHeaderVO.NTOTALORIGMNY, ntotalorigmny);
  }

  /** ҵ��Ա setter ���� */
  public void setPk_bizpsn(String pk_bizpsn) {
    this.setAttributeValue(InitialEstHeaderVO.PK_BIZPSN, pk_bizpsn);
  }

  /** ҵ������ setter ���� */
  public void setPk_busitype(String pk_busitype) {
    this.setAttributeValue(InitialEstHeaderVO.PK_BUSITYPE, pk_busitype);
  }

  /** �ɱ��� setter ���� */
  public void setPk_costregion(String pk_costregion) {
    this.setAttributeValue(InitialEstHeaderVO.PK_COSTREGION, pk_costregion);
  }

  /** �ɹ�����ԭʼ�汾 setter ���� */
  public void setPk_dept(String pk_dept) {
    this.setAttributeValue(InitialEstHeaderVO.PK_DEPT, pk_dept);
  }

  /** �ɹ����� setter ���� */
  public void setPk_dept_v(String pk_dept_v) {
    this.setAttributeValue(InitialEstHeaderVO.PK_DEPT_V, pk_dept_v);
  }

  /** �������� setter ���� */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(InitialEstHeaderVO.PK_GROUP, pk_group);
  }

  /** �ڳ��ݹ��� setter ���� */
  public void setPk_initialest(String pk_initialest) {
    this.setAttributeValue(InitialEstHeaderVO.PK_INITIALEST, pk_initialest);
  }

  /** ��Ʊ��Ӧ�� setter ���� */
  public void setPk_invcsupllier(String pk_invcsupllier) {
    this.setAttributeValue(InitialEstHeaderVO.PK_INVCSUPLLIER, pk_invcsupllier);
  }

  /** ����Ա setter ���� */
  public void setPk_managepsn(String pk_managepsn) {
    this.setAttributeValue(InitialEstHeaderVO.PK_MANAGEPSN, pk_managepsn);
  }

  /** ������֯ setter ���� */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(InitialEstHeaderVO.PK_ORG, pk_org);
  }

  /** ������֯�汾 setter ���� */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(InitialEstHeaderVO.PK_ORG_V, pk_org_v);
  }

  /** �ɹ���֯���°汾 setter ���� */
  public void setPk_purchaseorg(String pk_purchaseorg) {
    this.setAttributeValue(InitialEstHeaderVO.PK_PURCHASEORG, pk_purchaseorg);
  }

  /** �ɹ���֯ setter ���� */
  public void setPk_purchaseorg_v(String pk_purchaseorg_v) {
    this.setAttributeValue(InitialEstHeaderVO.PK_PURCHASEORG_V,
        pk_purchaseorg_v);
  }

  /** �����֯ setter ���� */
  public void setPk_stockorg(String pk_stockorg) {
    this.setAttributeValue(InitialEstHeaderVO.PK_STOCKORG, pk_stockorg);
  }

  /** �����֯�汾 setter ���� */
  public void setPk_stockorg_v(String pk_stockorg_v) {
    this.setAttributeValue(InitialEstHeaderVO.PK_STOCKORG_V, pk_stockorg_v);
  }

  /** �ֿ� setter ���� */
  public void setPk_stordoc(String pk_stordoc) {
    this.setAttributeValue(InitialEstHeaderVO.PK_STORDOC, pk_stordoc);
  }

  /** ��Ӧ�� setter ���� */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(InitialEstHeaderVO.PK_SUPPLIER, pk_supplier);
  }

  /** �������� setter ���� */
  public void setTaudittime(UFDate taudittime) {
    this.setAttributeValue(InitialEstHeaderVO.TAUDITTIME, taudittime);
  }

  /** ts setter ���� */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(InitialEstHeaderVO.TS, ts);
  }

  /** �ڳ��ݹ����� setter ���� */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(InitialEstHeaderVO.VBILLCODE, vbillcode);
  }

  /** �Զ�����1 setter ���� */
  public void setVdef1(String vdef1) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF1, vdef1);
  }

  /** �Զ�����10 setter ���� */
  public void setVdef10(String vdef10) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF10, vdef10);
  }

  /** �Զ�����11 setter ���� */
  public void setVdef11(String vdef11) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF11, vdef11);
  }

  /** �Զ�����12 setter ���� */
  public void setVdef12(String vdef12) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF12, vdef12);
  }

  /** �Զ�����13 setter ���� */
  public void setVdef13(String vdef13) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF13, vdef13);
  }

  /** �Զ�����14 setter ���� */
  public void setVdef14(String vdef14) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF14, vdef14);
  }

  /** �Զ�����15 setter ���� */
  public void setVdef15(String vdef15) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF15, vdef15);
  }

  /** �Զ�����16 setter ���� */
  public void setVdef16(String vdef16) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF16, vdef16);
  }

  /** �Զ�����17 setter ���� */
  public void setVdef17(String vdef17) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF17, vdef17);
  }

  /** �Զ�����18 setter ���� */
  public void setVdef18(String vdef18) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF18, vdef18);
  }

  /** �Զ�����19 setter ���� */
  public void setVdef19(String vdef19) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF19, vdef19);
  }

  /** �Զ�����2 setter ���� */
  public void setVdef2(String vdef2) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF2, vdef2);
  }

  /** �Զ�����20 setter ���� */
  public void setVdef20(String vdef20) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF20, vdef20);
  }

  /** �Զ�����3 setter ���� */
  public void setVdef3(String vdef3) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF3, vdef3);
  }

  /** �Զ�����4 setter ���� */
  public void setVdef4(String vdef4) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF4, vdef4);
  }

  /** �Զ�����5 setter ���� */
  public void setVdef5(String vdef5) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF5, vdef5);
  }

  /** �Զ�����6 setter ���� */
  public void setVdef6(String vdef6) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF6, vdef6);
  }

  /** �Զ�����7 setter ���� */
  public void setVdef7(String vdef7) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF7, vdef7);
  }

  /** �Զ�����8 setter ���� */
  public void setVdef8(String vdef8) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF8, vdef8);
  }

  /** �Զ�����9 setter ���� */
  public void setVdef9(String vdef9) {
    this.setAttributeValue(InitialEstHeaderVO.VDEF9, vdef9);
  }

  /** ��ע setter ���� */
  public void setVmemo(String vmemo) {
    this.setAttributeValue(InitialEstHeaderVO.VMEMO, vmemo);
  }

  /** �ڳ��ݹ����� setter ���� */
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(InitialEstHeaderVO.VTRANTYPECODE, vtrantypecode);
  }

}
