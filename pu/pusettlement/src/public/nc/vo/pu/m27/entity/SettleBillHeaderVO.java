package nc.vo.pu.m27.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.scmpub.util.selffun.BillMapInfoVO;

public class SettleBillHeaderVO extends SuperVO {
  /** �Ƶ��� */
  public static final String BILLMAKER = "billmaker";

  /** �Ƿ��Ѵ��ɱ� **/
  public static final String BTOIA = "btoia";

  /** �Ƿ����ⷢƱ�Ľ��� **/
  public static final String BVIRTUALSETTLE = "bvirtualsettle";

  /** ���ұ��� */
  public static final String CCURRENCYID = "ccurrencyid";

  /** ����ʱ�� **/
  public static final String CREATIONTIME = "creationtime";

  /** ������ **/
  public static final String CREATOR = "creator";

  /** �������� **/
  public static final String DBILLDATE = "dbilldate";

  /** �Ƶ����� */
  public static final String DMAKEDATE = "dmakedate";

  /** dr **/
  public static final String DR = "dr";

  /** ���㵥������ **/
  public static final String FSETTLETYPE = "fsettletype";

  /** ��ӡ���� **/
  public static final String IPRINTCOUNT = "iprintcount";

  /** �������� **/
  public static final String PK_GROUP = "pk_group";

  /** ������֯ **/
  public static final String PK_ORG = "pk_org";

  /** ������֯�汾 **/
  public static final String PK_ORG_V = "pk_org_v";

  /** ���㵥 **/
  public static final String PK_SETTLEBILL = "pk_settlebill";

  /** ��浥�� */
  public static final String STOCKBILL = "stockbill";

  /** ʱ��� **/
  public static final String TS = "ts";

  /** ���㵥�� **/
  public static final String VBILLCODE = "vbillcode";

  private static final long serialVersionUID = -4691704696643217113L;

  /** �Ƶ��� **/
  public String getBillmaker() {
    return (String) this.getAttributeValue(SettleBillHeaderVO.BILLMAKER);
  }

  /** �Ƿ��Ѵ��ɱ� **/
  public UFBoolean getBtoia() {
    return (UFBoolean) this.getAttributeValue(SettleBillHeaderVO.BTOIA);
  }

  /** �Ƿ����ⷢƱ�Ľ��� **/
  public UFBoolean getBvirtualsettle() {
    return (UFBoolean) this
        .getAttributeValue(SettleBillHeaderVO.BVIRTUALSETTLE);
  }

  /** ���ұ��� getter ���� */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(SettleBillHeaderVO.CCURRENCYID);
  }

  /** ����ʱ�� **/
  public UFDateTime getCreationtime() {
    return (UFDateTime) this.getAttributeValue(SettleBillHeaderVO.CREATIONTIME);
  }

  /** ������ **/
  public String getCreator() {
    return (String) this.getAttributeValue(SettleBillHeaderVO.CREATOR);
  }

  /** �������� **/
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SettleBillHeaderVO.DBILLDATE);
  }

  /** �Ƶ����� **/
  public UFDate getDmakedate() {
    return (UFDate) this.getAttributeValue(SettleBillHeaderVO.DMAKEDATE);
  }

  /** dr **/
  public Integer getDr() {
    return (Integer) this.getAttributeValue(SettleBillHeaderVO.DR);
  }

  /** ���㵥������ **/
  public Integer getFsettletype() {
    return (Integer) this.getAttributeValue(SettleBillHeaderVO.FSETTLETYPE);
  }

  /** ��ӡ���� **/
  public Integer getIprintcount() {
    return (Integer) this.getAttributeValue(SettleBillHeaderVO.IPRINTCOUNT);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta(PUEntity.SettleBill_H);
    return meta;
  }

  /** �������� **/
  public String getPk_group() {
    return (String) this.getAttributeValue(SettleBillHeaderVO.PK_GROUP);
  }

  /** ������֯ **/
  public String getPk_org() {
    return (String) this.getAttributeValue(SettleBillHeaderVO.PK_ORG);
  }

  /** ������֯�汾 **/
  public String getPk_org_v() {
    return (String) this.getAttributeValue(SettleBillHeaderVO.PK_ORG_V);
  }

  /** ���㵥 **/
  public String getPk_settlebill() {
    return (String) this.getAttributeValue(SettleBillHeaderVO.PK_SETTLEBILL);
  }

  /**
   * ��浥��
   * <p>
   * ʹ�ó������������ԣ�ֻ���ڴ����ʱVO���մ���������������;��
   * <ul>
   * <li>
   * </ul>
   * 
   * @return
   */
  public BillMapInfoVO getStockbill() {
    return (BillMapInfoVO) this.getAttributeValue(SettleBillHeaderVO.STOCKBILL);
  }

  /** ʱ��� **/
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SettleBillHeaderVO.TS);
  }

  /** ���㵥�� **/
  public String getVbillcode() {
    return (String) this.getAttributeValue(SettleBillHeaderVO.VBILLCODE);
  }

  /** �Ƶ��� **/
  public void setBillmaker(String operator) {
    this.setAttributeValue(SettleBillHeaderVO.BILLMAKER, operator);
  }

  /** �Ƿ��Ѵ��ɱ� **/
  public void setBtoia(UFBoolean btoia) {
    this.setAttributeValue(SettleBillHeaderVO.BTOIA, btoia);
  }

  /** �Ƿ����ⷢƱ�Ľ��� **/
  public void setBvirtualsettle(UFBoolean bvirtualsettle) {
    this.setAttributeValue(SettleBillHeaderVO.BVIRTUALSETTLE, bvirtualsettle);
  }

  /** ���ұ��� setter ���� */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(SettleBillHeaderVO.CCURRENCYID, ccurrencyid);
  }

  /** ����ʱ�� **/
  public void setCreationtime(UFDateTime creationtime) {
    this.setAttributeValue(SettleBillHeaderVO.CREATIONTIME, creationtime);
  }

  /** ������ **/
  public void setCreator(String creator) {
    this.setAttributeValue(SettleBillHeaderVO.CREATOR, creator);
  }

  /** �������� **/
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SettleBillHeaderVO.DBILLDATE, dbilldate);
  }

  /** �Ƶ����� **/
  public void setDmakedate(UFDate dmakedate) {
    this.setAttributeValue(SettleBillHeaderVO.DMAKEDATE, dmakedate);
  }

  /** dr **/
  public void setDr(Integer dr) {
    this.setAttributeValue(SettleBillHeaderVO.DR, dr);
  }

  /** ���㵥������ **/
  public void setFsettletype(Integer fsettletype) {
    this.setAttributeValue(SettleBillHeaderVO.FSETTLETYPE, fsettletype);
  }

  /** ��ӡ���� **/
  public void setIprintcount(Integer iprintcount) {
    this.setAttributeValue(SettleBillHeaderVO.IPRINTCOUNT, iprintcount);
  }

  /** �������� **/
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SettleBillHeaderVO.PK_GROUP, pk_group);
  }

  /** ������֯ **/
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SettleBillHeaderVO.PK_ORG, pk_org);
  }

  /** ������֯�汾 **/
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(SettleBillHeaderVO.PK_ORG_V, pk_org_v);
  }

  /** ���㵥 **/
  public void setPk_settlebill(String pk_settlebill) {
    this.setAttributeValue(SettleBillHeaderVO.PK_SETTLEBILL, pk_settlebill);
  }

  /**
   * ��浥��
   * <p>
   * ʹ�ó�����ֻ���ڴ����ʱVO��������������������;
   * <ul>
   * <li>
   * </ul>
   * 
   * @param stockbill
   */
  public void setStockbill(BillMapInfoVO stockbill) {
    this.setAttributeValue(SettleBillHeaderVO.STOCKBILL, stockbill);
  }

  /** ʱ��� **/
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SettleBillHeaderVO.TS, ts);
  }

  /** ���㵥�� **/
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(SettleBillHeaderVO.VBILLCODE, vbillcode);
  }
}
