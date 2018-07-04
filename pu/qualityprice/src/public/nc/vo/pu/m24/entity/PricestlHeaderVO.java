/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-13 ����04:53:37
 */
package nc.vo.pu.m24.entity;

import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�۸���㵥��ͷVO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-13 ����04:53:37
 */
public class PricestlHeaderVO extends SuperVO {
  /** ������ */
  public static final String APPROVER = "approver";

  /** �Ƶ��� */
  public static final String BILLMAKER = "billmaker";

  /** ���� */
  public static final String CCURRENCYID = "ccurrencyid";

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

  /** �ɹ����� */
  public static final String PK_DEPT = "pk_dept";

  /** �ɹ����Ű汾 */
  public static final String PK_DEPT_V = "pk_dept_v";

  /** �ɹ�Ա */
  public static final String PK_EMPLOYEE = "pk_employee";

  /** ���� */
  public static final String PK_GROUP = "pk_group";

  /** �ɹ���֯ */
  public static final String PK_ORG = "pk_org";

  /** �ɹ���֯�汾 */
  public static final String PK_ORG_V = "pk_org_v";

  /** �۸���㵥 */
  public static final String PK_PRICESETTLE = "pk_pricesettle";

  /** �����֯ */
  public static final String PK_STOREORG = "pk_storeorg";

  /** �����֯�汾 */
  public static final String PK_STOREORG_V = "pk_storeorg_v";

  /** ��Ӧ�� */
  public static final String PK_SUPPLIER = "pk_supplier";

  /** �������� */
  public static final String TAUDITTIME = "taudittime";

  /** ts */
  public static final String TS = "ts";

  /** �۸���㵥�� */
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

  private static final long serialVersionUID = 2670869802710761424L;

  /** ������ getter ���� */
  public String getApprover() {
    return (String) this.getAttributeValue(PricestlHeaderVO.APPROVER);
  }

  /** �Ƶ��� getter ���� */
  public String getBillmaker() {
    return (String) this.getAttributeValue(PricestlHeaderVO.BILLMAKER);
  }

  /** ���� getter ���� */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(PricestlHeaderVO.CCURRENCYID);
  }

  /** ����ʱ�� getter ���� */
  public UFDateTime getCreationtime() {
    return (UFDateTime) this.getAttributeValue(PricestlHeaderVO.CREATIONTIME);
  }

  /** ������ getter ���� */
  public String getCreator() {
    return (String) this.getAttributeValue(PricestlHeaderVO.CREATOR);
  }

  /** �������� getter ���� */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(PricestlHeaderVO.DBILLDATE);
  }

  /** �Ƶ����� **/
  public UFDate getDmakedate() {
    return (UFDate) this.getAttributeValue(StoreReqAppHeaderVO.DMAKEDATE);
  }

  /** dr getter ���� */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(PricestlHeaderVO.DR);
  }

  /** ����״̬ getter ���� */
  public Integer getFbillstatus() {
    return (Integer) this.getAttributeValue(PricestlHeaderVO.FBILLSTATUS);
  }

  /** ��ӡ���� getter ���� */
  public Integer getIprintcount() {
    return (Integer) this.getAttributeValue(PricestlHeaderVO.IPRINTCOUNT);
  }

  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.M24_H);
  }

  /** ����޸�ʱ�� getter ���� */
  public UFDateTime getModifiedtime() {
    return (UFDateTime) this.getAttributeValue(PricestlHeaderVO.MODIFIEDTIME);
  }

  /** ����޸��� getter ���� */
  public String getModifier() {
    return (String) this.getAttributeValue(PricestlHeaderVO.MODIFIER);
  }

  /** �ɹ����� getter ���� */
  public String getPk_dept() {
    return (String) this.getAttributeValue(PricestlHeaderVO.PK_DEPT);
  }

  /** �ɹ����Ű汾 getter ���� */
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(PricestlHeaderVO.PK_DEPT_V);
  }

  /** �ɹ�Ա getter ���� */
  public String getPk_employee() {
    return (String) this.getAttributeValue(PricestlHeaderVO.PK_EMPLOYEE);
  }

  /** ���� getter ���� */
  public String getPk_group() {
    return (String) this.getAttributeValue(PricestlHeaderVO.PK_GROUP);
  }

  /** �ɹ���֯ getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(PricestlHeaderVO.PK_ORG);
  }

  /** �ɹ���֯�汾 getter ���� */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(PricestlHeaderVO.PK_ORG_V);
  }

  /** �۸���㵥 getter ���� */
  public String getPk_pricesettle() {
    return (String) this.getAttributeValue(PricestlHeaderVO.PK_PRICESETTLE);
  }

  /** �����֯ getter ���� */
  public String getPk_storeorg() {
    return (String) this.getAttributeValue(PricestlHeaderVO.PK_STOREORG);
  }

  /** �����֯�汾 getter ���� */
  public String getPk_storeorg_v() {
    return (String) this.getAttributeValue(PricestlHeaderVO.PK_STOREORG_V);
  }

  /** ��Ӧ�� getter ���� */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(PricestlHeaderVO.PK_SUPPLIER);
  }

  /** �������� getter ���� */
  public UFDate getTaudittime() {
    return (UFDate) this.getAttributeValue(PricestlHeaderVO.TAUDITTIME);
  }

  /** ts getter ���� */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(PricestlHeaderVO.TS);
  }

  /** �۸���㵥�� getter ���� */
  public String getVbillcode() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VBILLCODE);
  }

  /** �Զ�����1 getter ���� */
  public String getVdef1() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VDEF1);
  }

  /** �Զ�����10 getter ���� */
  public String getVdef10() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VDEF10);
  }

  /** �Զ�����11 getter ���� */
  public String getVdef11() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VDEF11);
  }

  /** �Զ�����12 getter ���� */
  public String getVdef12() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VDEF12);
  }

  /** �Զ�����13 getter ���� */
  public String getVdef13() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VDEF13);
  }

  /** �Զ�����14 getter ���� */
  public String getVdef14() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VDEF14);
  }

  /** �Զ�����15 getter ���� */
  public String getVdef15() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VDEF15);
  }

  /** �Զ�����16 getter ���� */
  public String getVdef16() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VDEF16);
  }

  /** �Զ�����17 getter ���� */
  public String getVdef17() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VDEF17);
  }

  /** �Զ�����18 getter ���� */
  public String getVdef18() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VDEF18);
  }

  /** �Զ�����19 getter ���� */
  public String getVdef19() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VDEF19);
  }

  /** �Զ�����2 getter ���� */
  public String getVdef2() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VDEF2);
  }

  /** �Զ�����20 getter ���� */
  public String getVdef20() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VDEF20);
  }

  /** �Զ�����3 getter ���� */
  public String getVdef3() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VDEF3);
  }

  /** �Զ�����4 getter ���� */
  public String getVdef4() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VDEF4);
  }

  /** �Զ�����5 getter ���� */
  public String getVdef5() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VDEF5);
  }

  /** �Զ�����6 getter ���� */
  public String getVdef6() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VDEF6);
  }

  /** �Զ�����7 getter ���� */
  public String getVdef7() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VDEF7);
  }

  /** �Զ�����8 getter ���� */
  public String getVdef8() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VDEF8);
  }

  /** �Զ�����9 getter ���� */
  public String getVdef9() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VDEF9);
  }

  /** ��ע getter ���� */
  public String getVmemo() {
    return (String) this.getAttributeValue(PricestlHeaderVO.VMEMO);
  }

  /** ������ setter ���� */
  public void setApprover(String approver) {
    this.setAttributeValue(PricestlHeaderVO.APPROVER, approver);
  }

  /** �Ƶ��� setter ���� */
  public void setBillmaker(String billmaker) {
    this.setAttributeValue(PricestlHeaderVO.BILLMAKER, billmaker);
  }

  /** ���� setter ���� */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(PricestlHeaderVO.CCURRENCYID, ccurrencyid);
  }

  /** ����ʱ�� setter ���� */
  public void setCreationtime(UFDateTime creationtime) {
    this.setAttributeValue(PricestlHeaderVO.CREATIONTIME, creationtime);
  }

  /** ������ setter ���� */
  public void setCreator(String creator) {
    this.setAttributeValue(PricestlHeaderVO.CREATOR, creator);
  }

  /** �������� setter ���� */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(PricestlHeaderVO.DBILLDATE, dbilldate);
  }

  /** �Ƶ����� **/
  public void setDmakedate(UFDate dmakedate) {
    this.setAttributeValue(StoreReqAppHeaderVO.DMAKEDATE, dmakedate);
  }

  /** dr setter ���� */
  public void setDr(Integer dr) {
    this.setAttributeValue(PricestlHeaderVO.DR, dr);
  }

  /** ����״̬ setter ���� */
  public void setFbillstatus(Integer fbillstatus) {
    this.setAttributeValue(PricestlHeaderVO.FBILLSTATUS, fbillstatus);
  }

  /** ��ӡ���� setter ���� */
  public void setIprintcount(Integer iprintcount) {
    this.setAttributeValue(PricestlHeaderVO.IPRINTCOUNT, iprintcount);
  }

  /** ����޸�ʱ�� setter ���� */
  public void setModifiedtime(UFDateTime modifiedtime) {
    this.setAttributeValue(PricestlHeaderVO.MODIFIEDTIME, modifiedtime);
  }

  /** ����޸��� setter ���� */
  public void setModifier(String modifier) {
    this.setAttributeValue(PricestlHeaderVO.MODIFIER, modifier);
  }

  /** �ɹ����� setter ���� */
  public void setPk_dept(String pk_dept) {
    this.setAttributeValue(PricestlHeaderVO.PK_DEPT, pk_dept);
  }

  /** �ɹ����Ű汾 setter ���� */
  public void setPk_dept_v(String pk_dept_v) {
    this.setAttributeValue(PricestlHeaderVO.PK_DEPT_V, pk_dept_v);
  }

  /** �ɹ�Ա setter ���� */
  public void setPk_employee(String pk_employee) {
    this.setAttributeValue(PricestlHeaderVO.PK_EMPLOYEE, pk_employee);
  }

  /** ���� setter ���� */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(PricestlHeaderVO.PK_GROUP, pk_group);
  }

  /** �ɹ���֯ setter ���� */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(PricestlHeaderVO.PK_ORG, pk_org);
  }

  /** �ɹ���֯�汾 setter ���� */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(PricestlHeaderVO.PK_ORG_V, pk_org_v);
  }

  /** �۸���㵥 setter ���� */
  public void setPk_pricesettle(String pk_pricesettle) {
    this.setAttributeValue(PricestlHeaderVO.PK_PRICESETTLE, pk_pricesettle);
  }

  /** �����֯ setter ���� */
  public void setPk_storeorg(String pk_storeorg) {
    this.setAttributeValue(PricestlHeaderVO.PK_STOREORG, pk_storeorg);
  }

  /** �����֯�汾 setter ���� */
  public void setPk_storeorg_v(String pk_storeorg_v) {
    this.setAttributeValue(PricestlHeaderVO.PK_STOREORG_V, pk_storeorg_v);
  }

  /** ��Ӧ�� setter ���� */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(PricestlHeaderVO.PK_SUPPLIER, pk_supplier);
  }

  /** �������� setter ���� */
  public void setTaudittime(UFDate taudittime) {
    this.setAttributeValue(PricestlHeaderVO.TAUDITTIME, taudittime);
  }

  /** ts setter ���� */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(PricestlHeaderVO.TS, ts);
  }

  /** �۸���㵥�� setter ���� */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(PricestlHeaderVO.VBILLCODE, vbillcode);
  }

  /** �Զ�����1 setter ���� */
  public void setVdef1(String vdef1) {
    this.setAttributeValue(PricestlHeaderVO.VDEF1, vdef1);
  }

  /** �Զ�����10 setter ���� */
  public void setVdef10(String vdef10) {
    this.setAttributeValue(PricestlHeaderVO.VDEF10, vdef10);
  }

  /** �Զ�����11 setter ���� */
  public void setVdef11(String vdef11) {
    this.setAttributeValue(PricestlHeaderVO.VDEF11, vdef11);
  }

  /** �Զ�����12 setter ���� */
  public void setVdef12(String vdef12) {
    this.setAttributeValue(PricestlHeaderVO.VDEF12, vdef12);
  }

  /** �Զ�����13 setter ���� */
  public void setVdef13(String vdef13) {
    this.setAttributeValue(PricestlHeaderVO.VDEF13, vdef13);
  }

  /** �Զ�����14 setter ���� */
  public void setVdef14(String vdef14) {
    this.setAttributeValue(PricestlHeaderVO.VDEF14, vdef14);
  }

  /** �Զ�����15 setter ���� */
  public void setVdef15(String vdef15) {
    this.setAttributeValue(PricestlHeaderVO.VDEF15, vdef15);
  }

  /** �Զ�����16 setter ���� */
  public void setVdef16(String vdef16) {
    this.setAttributeValue(PricestlHeaderVO.VDEF16, vdef16);
  }

  /** �Զ�����17 setter ���� */
  public void setVdef17(String vdef17) {
    this.setAttributeValue(PricestlHeaderVO.VDEF17, vdef17);
  }

  /** �Զ�����18 setter ���� */
  public void setVdef18(String vdef18) {
    this.setAttributeValue(PricestlHeaderVO.VDEF18, vdef18);
  }

  /** �Զ�����19 setter ���� */
  public void setVdef19(String vdef19) {
    this.setAttributeValue(PricestlHeaderVO.VDEF19, vdef19);
  }

  /** �Զ�����2 setter ���� */
  public void setVdef2(String vdef2) {
    this.setAttributeValue(PricestlHeaderVO.VDEF2, vdef2);
  }

  /** �Զ�����20 setter ���� */
  public void setVdef20(String vdef20) {
    this.setAttributeValue(PricestlHeaderVO.VDEF20, vdef20);
  }

  /** �Զ�����3 setter ���� */
  public void setVdef3(String vdef3) {
    this.setAttributeValue(PricestlHeaderVO.VDEF3, vdef3);
  }

  /** �Զ�����4 setter ���� */
  public void setVdef4(String vdef4) {
    this.setAttributeValue(PricestlHeaderVO.VDEF4, vdef4);
  }

  /** �Զ�����5 setter ���� */
  public void setVdef5(String vdef5) {
    this.setAttributeValue(PricestlHeaderVO.VDEF5, vdef5);
  }

  /** �Զ�����6 setter ���� */
  public void setVdef6(String vdef6) {
    this.setAttributeValue(PricestlHeaderVO.VDEF6, vdef6);
  }

  /** �Զ�����7 setter ���� */
  public void setVdef7(String vdef7) {
    this.setAttributeValue(PricestlHeaderVO.VDEF7, vdef7);
  }

  /** �Զ�����8 setter ���� */
  public void setVdef8(String vdef8) {
    this.setAttributeValue(PricestlHeaderVO.VDEF8, vdef8);
  }

  /** �Զ�����9 setter ���� */
  public void setVdef9(String vdef9) {
    this.setAttributeValue(PricestlHeaderVO.VDEF9, vdef9);
  }

  /** ��ע setter ���� */
  public void setVmemo(String vmemo) {
    this.setAttributeValue(PricestlHeaderVO.VMEMO, vmemo);
  }
}
