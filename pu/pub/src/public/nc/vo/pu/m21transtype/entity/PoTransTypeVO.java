package nc.vo.pu.m21transtype.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * �ɹ���������������չ����VO
 * 
 * @author GGR
 * @time 2009-12-22 ����04:33:56
 * @since 6.0
 */
public class PoTransTypeVO extends SuperVO {
  /** ���� */
  public static final String BARRIVE = "barrive";

  /** ���Ʋɹ������Ƿ���ɹ�ҵ��ί�й�ϵ */
  public static final String BCHECKCENPUR = "bcheckcenpur";

  /** �Է�ȷ�� */
  public static final String BCONFIRM = "bconfirm";

  /** �Է�ȷ�ϵ��ݺ� */
  public static final String BCONFIRMCODE = "bconfirmcode";

  /** �Է�ȷ�ϵ������� */
  public static final String BCONFIRMDATE = "bconfirmdate";

  /** �Է�ȷ�ϵ������� */
  public static final String BCONFIRMNUM = "bconfirmnum";

  /** �������� */
  public static final String BCONSIGN = "bconsign";

  /** �������ݺ� */
  public static final String BCONSIGNCODE = "bconsigncode";

  /** ������������ */
  public static final String BCONSIGNDATE = "bconsigndate";

  /** ������������ */
  public static final String BCONSIGNNUM = "bconsignnum";

  /** ���ػ��� */
  public static final String BCUSTOM = "bcustom";

  /** ���ص��ݺ� */
  public static final String BCUSTOMCODE = "bcustomcode";

  /** ���ص������� */
  public static final String BCUSTOMDATE = "bcustomdate";

  /** �Ƿ�ֱ�˲ɹ� */
  public static final String BDIRECT = "bdirect";

  /** װ�˻��� */
  public static final String BLOAD = "bload";

  /** װ�˵��ݺ� */
  public static final String BLOADCODE = "bloadcode";

  /** װ�˵������� */
  public static final String BLOADDATE = "bloaddate";

  /** ���ػ��� */
  public static final String BOUTCUSTOM = "boutcustom";

  /** ���ص��ݺ� */
  public static final String BOUTCUSTOMCODE = "boutcustomcode";

  /** ���ص������� */
  public static final String BOUTCUSTOMDATE = "boutcustomdate";

  /** ��� */
  public static final String BOUTPUT = "boutput";

  /** ������������ */
  public static final String BOVERPAY = "boverpay";

  /** �Ƿ���е����ƻ����� */
  public static final String BRECEIVEPLAN = "breceiveplan";

  /** ��� */
  public static final String BSTORE = "bstore";

  /** �Ƿ�Ӧ�̼Ĵ� */
  public static final String BVMI = "bvmi";

  /** �������ͱ��� */
  public static final String CTRANTYPEID = "ctrantypeid";

  /** dr */
  public static final String DR = "dr";

  /** ��˺���״̬ */
  public static final String IAPPROVEAFT = "iapproveaft";

  /** ȷ�Ϻ���״̬ */
  public static final String ICONFIRMAFT = "iconfirmaft";

  /** ��������״̬ */
  public static final String ICONSIGNAFT = "iconsignaft";

  /** ���غ���״̬ */
  public static final String ICUSTOMAFT = "icustomaft";

  /** װ�˺���״̬ */
  public static final String ILOADAFT = "iloadaft";

  /** ��;��ʼ */
  public static final String IONWAYBEGIN = "ionwaybegin";

  /** ��;���� */
  public static final String IONWAYEND = "ionwayend";

  /** ���غ���״̬ */
  public static final String IOUTCUSTOMAFT = "ioutcustomaft";

  /** �������״̬ */
  public static final String IOUTPUTAFT = "ioutputaft";

  /** �빺�����ɶ������Ʒ�ʽ */
  public static final String IPRTOPOLIMIT = "iprtopolimit";

  /** Ԥ����������Эͬ�ɹ���VO�����Ľ������� **/
  public static final String M21_COOP = "21-coop";

  /** ���� */
  public static final String PK_GROUP = "pk_group";

  /** ���� */
  public static final String PK_POTRANTYPE = "pk_potrantype";

  /** ts */
  public static final String TS = "ts";

  /** �������ͱ��� */
  public static final String VTRANTYPECODE = "vtrantypecode";

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -8942893753479189991L;

  /** ���� getter ���� */
  public UFBoolean getBarrive() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BARRIVE);
  }

  /** ���Ʋɹ������Ƿ���ɹ�ҵ��ί�й�ϵ getter ���� */
  public UFBoolean getBcheckcenpur() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BCHECKCENPUR);
  }

  /** �Է�ȷ�� getter ���� */
  public UFBoolean getBconfirm() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BCONFIRM);
  }

  /** �Է�ȷ�ϵ��ݺ� getter ���� */
  public UFBoolean getBconfirmcode() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BCONFIRMCODE);
  }

  /** �Է�ȷ�ϵ������� getter ���� */
  public UFBoolean getBconfirmdate() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BCONFIRMDATE);
  }

  /** �Է�ȷ�ϵ������� getter ���� */
  public UFBoolean getBconfirmnum() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BCONFIRMNUM);
  }

  /** �������� getter ���� */
  public UFBoolean getBconsign() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BCONSIGN);
  }

  /** �������ݺ� getter ���� */
  public UFBoolean getBconsigncode() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BCONSIGNCODE);
  }

  /** ������������ getter ���� */
  public UFBoolean getBconsigndate() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BCONSIGNDATE);
  }

  /** ������������ getter ���� */
  public UFBoolean getBconsignnum() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BCONSIGNNUM);
  }

  /** ���ػ��� getter ���� */
  public UFBoolean getBcustom() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BCUSTOM);
  }

  /** ���ص��ݺ� getter ���� */
  public UFBoolean getBcustomcode() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BCUSTOMCODE);
  }

  /** ���ص������� getter ���� */
  public UFBoolean getBcustomdate() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BCUSTOMDATE);
  }

  /** �Ƿ�ֱ�˲ɹ� getter ���� */
  public UFBoolean getBdirect() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BDIRECT);
  }

  /** װ�˻��� getter ���� */
  public UFBoolean getBload() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BLOAD);
  }

  /** װ�˵��ݺ� getter ���� */
  public UFBoolean getBloadcode() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BLOADCODE);
  }

  /** װ�˵������� getter ���� */
  public UFBoolean getBloaddate() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BLOADDATE);
  }

  /** ���ػ��� getter ���� */
  public UFBoolean getBoutcustom() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BOUTCUSTOM);
  }

  /** ���ص��ݺ� getter ���� */
  public UFBoolean getBoutcustomcode() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BOUTCUSTOMCODE);
  }

  /** ���ص������� getter ���� */
  public UFBoolean getBoutcustomdate() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BOUTCUSTOMDATE);
  }

  /** ��� getter ���� */
  public UFBoolean getBoutput() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BOUTPUT);
  }

  /** ������������ getter ���� */
  public UFBoolean getBoverpay() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BOVERPAY);
  }

  /** �Ƿ���е����ƻ����� getter ���� */
  public UFBoolean getBreceiveplan() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BRECEIVEPLAN);
  }

  /** ��� getter ���� */
  public UFBoolean getBstore() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BSTORE);
  }

  /** �Ƿ�Ӧ�̼Ĵ� getter ���� */
  public UFBoolean getBvmi() {
    return (UFBoolean) this.getAttributeValue(PoTransTypeVO.BVMI);
  }

  /** �������ͱ��� getter ���� */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(PoTransTypeVO.CTRANTYPEID);
  }

  /** dr getter ���� */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(PoTransTypeVO.DR);
  }

  /** ��˺���״̬ getter ���� */
  public Integer getIapproveaft() {
    return (Integer) this.getAttributeValue(PoTransTypeVO.IAPPROVEAFT);
  }

  /** ȷ�Ϻ���״̬ getter ���� */
  public Integer getIconfirmaft() {
    return (Integer) this.getAttributeValue(PoTransTypeVO.ICONFIRMAFT);
  }

  /** ��������״̬ getter ���� */
  public Integer getIconsignaft() {
    return (Integer) this.getAttributeValue(PoTransTypeVO.ICONSIGNAFT);
  }

  /** ���غ���״̬ getter ���� */
  public Integer getIcustomaft() {
    return (Integer) this.getAttributeValue(PoTransTypeVO.ICUSTOMAFT);
  }

  /** װ�˺���״̬ getter ���� */
  public Integer getIloadaft() {
    return (Integer) this.getAttributeValue(PoTransTypeVO.ILOADAFT);
  }

  /** ��;��ʼ getter ���� */
  public Integer getIonwaybegin() {
    return (Integer) this.getAttributeValue(PoTransTypeVO.IONWAYBEGIN);
  }

  /** ��;���� getter ���� */
  public Integer getIonwayend() {
    return (Integer) this.getAttributeValue(PoTransTypeVO.IONWAYEND);
  }

  /** ���غ���״̬ getter ���� */
  public Integer getIoutcustomaft() {
    return (Integer) this.getAttributeValue(PoTransTypeVO.IOUTCUSTOMAFT);
  }

  /** �������״̬ getter ���� */
  public Integer getIoutputaft() {
    return (Integer) this.getAttributeValue(PoTransTypeVO.IOUTPUTAFT);
  }

  /** �빺�����ɶ������Ʒ�ʽ getter ���� */
  public Integer getIprtopolimit() {
    return (Integer) this.getAttributeValue(PoTransTypeVO.IPRTOPOLIMIT);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta(PUEntity.M21_TRANTYPE);
    return meta;
  }

  /** ���� getter ���� */
  public String getPk_group() {
    return (String) this.getAttributeValue(PoTransTypeVO.PK_GROUP);
  }

  /** ���� getter ���� */
  public String getPk_potrantype() {
    return (String) this.getAttributeValue(PoTransTypeVO.PK_POTRANTYPE);
  }

  /** ts getter ���� */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(PoTransTypeVO.TS);
  }

  /** �������ͱ��� getter ���� */
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(PoTransTypeVO.VTRANTYPECODE);
  }

  /** ���� setter ���� */
  public void setBarrive(UFBoolean barrive) {
    this.setAttributeValue(PoTransTypeVO.BARRIVE, barrive);
  }

  /** ���Ʋɹ������Ƿ���ɹ�ҵ��ί�й�ϵ setter ���� */
  public void setBcheckcenpur(UFBoolean bcheckcenpur) {
    this.setAttributeValue(PoTransTypeVO.BCHECKCENPUR, bcheckcenpur);
  }

  /** �Է�ȷ�� setter ���� */
  public void setBconfirm(UFBoolean bconfirm) {
    this.setAttributeValue(PoTransTypeVO.BCONFIRM, bconfirm);
  }

  /** �Է�ȷ�ϵ��ݺ� setter ���� */
  public void setBconfirmcode(UFBoolean bconfirmcode) {
    this.setAttributeValue(PoTransTypeVO.BCONFIRMCODE, bconfirmcode);
  }

  /** �Է�ȷ�ϵ������� setter ���� */
  public void setBconfirmdate(UFBoolean bconfirmdate) {
    this.setAttributeValue(PoTransTypeVO.BCONFIRMDATE, bconfirmdate);
  }

  /** �Է�ȷ�ϵ������� setter ���� */
  public void setBconfirmnum(UFBoolean bconfirmnum) {
    this.setAttributeValue(PoTransTypeVO.BCONFIRMNUM, bconfirmnum);
  }

  /** �������� setter ���� */
  public void setBconsign(UFBoolean bconsign) {
    this.setAttributeValue(PoTransTypeVO.BCONSIGN, bconsign);
  }

  /** �������ݺ� setter ���� */
  public void setBconsigncode(UFBoolean bconsigncode) {
    this.setAttributeValue(PoTransTypeVO.BCONSIGNCODE, bconsigncode);
  }

  /** ������������ setter ���� */
  public void setBconsigndate(UFBoolean bconsigndate) {
    this.setAttributeValue(PoTransTypeVO.BCONSIGNDATE, bconsigndate);
  }

  /** ������������ setter ���� */
  public void setBconsignnum(UFBoolean bconsignnum) {
    this.setAttributeValue(PoTransTypeVO.BCONSIGNNUM, bconsignnum);
  }

  /** ���ػ��� setter ���� */
  public void setBcustom(UFBoolean bcustom) {
    this.setAttributeValue(PoTransTypeVO.BCUSTOM, bcustom);
  }

  /** ���ص��ݺ� setter ���� */
  public void setBcustomcode(UFBoolean bcustomcode) {
    this.setAttributeValue(PoTransTypeVO.BCUSTOMCODE, bcustomcode);
  }

  /** ���ص������� setter ���� */
  public void setBcustomdate(UFBoolean bcustomdate) {
    this.setAttributeValue(PoTransTypeVO.BCUSTOMDATE, bcustomdate);
  }

  /** �Ƿ�ֱ�˲ɹ� setter ���� */
  public void setBdirect(UFBoolean bdirect) {
    this.setAttributeValue(PoTransTypeVO.BDIRECT, bdirect);
  }

  /** װ�˻��� setter ���� */
  public void setBload(UFBoolean bload) {
    this.setAttributeValue(PoTransTypeVO.BLOAD, bload);
  }

  /** װ�˵��ݺ� setter ���� */
  public void setBloadcode(UFBoolean bloadcode) {
    this.setAttributeValue(PoTransTypeVO.BLOADCODE, bloadcode);
  }

  /** װ�˵������� setter ���� */
  public void setBloaddate(UFBoolean bloaddate) {
    this.setAttributeValue(PoTransTypeVO.BLOADDATE, bloaddate);
  }

  /** ���ػ��� setter ���� */
  public void setBoutcustom(UFBoolean boutcustom) {
    this.setAttributeValue(PoTransTypeVO.BOUTCUSTOM, boutcustom);
  }

  /** ���ص��ݺ� setter ���� */
  public void setBoutcustomcode(UFBoolean boutcustomcode) {
    this.setAttributeValue(PoTransTypeVO.BOUTCUSTOMCODE, boutcustomcode);
  }

  /** ���ص������� setter ���� */
  public void setBoutcustomdate(UFBoolean boutcustomdate) {
    this.setAttributeValue(PoTransTypeVO.BOUTCUSTOMDATE, boutcustomdate);
  }

  /** ��� setter ���� */
  public void setBoutput(UFBoolean boutput) {
    this.setAttributeValue(PoTransTypeVO.BOUTPUT, boutput);
  }

  /** ������������ setter ���� */
  public void setBoverpay(UFBoolean boverpay) {
    this.setAttributeValue(PoTransTypeVO.BOVERPAY, boverpay);
  }

  /** �Ƿ���е����ƻ����� setter ���� */
  public void setBreceiveplan(UFBoolean breceiveplan) {
    this.setAttributeValue(PoTransTypeVO.BRECEIVEPLAN, breceiveplan);
  }

  /** ��� setter ���� */
  public void setBstore(UFBoolean bstore) {
    this.setAttributeValue(PoTransTypeVO.BSTORE, bstore);
  }

  /** �Ƿ�Ӧ�̼Ĵ� setter ���� */
  public void setBvmi(UFBoolean bvmi) {
    this.setAttributeValue(PoTransTypeVO.BVMI, bvmi);
  }

  /** �������ͱ��� setter ���� */
  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(PoTransTypeVO.CTRANTYPEID, ctrantypeid);
  }

  /** dr setter ���� */
  public void setDr(Integer dr) {
    this.setAttributeValue(PoTransTypeVO.DR, dr);
  }

  /** ��˺���״̬ setter ���� */
  public void setIapproveaft(Integer iapproveaft) {
    this.setAttributeValue(PoTransTypeVO.IAPPROVEAFT, iapproveaft);
  }

  /** ȷ�Ϻ���״̬ setter ���� */
  public void setIconfirmaft(Integer iconfirmaft) {
    this.setAttributeValue(PoTransTypeVO.ICONFIRMAFT, iconfirmaft);
  }

  /** ��������״̬ setter ���� */
  public void setIconsignaft(Integer iconsignaft) {
    this.setAttributeValue(PoTransTypeVO.ICONSIGNAFT, iconsignaft);
  }

  /** ���غ���״̬ setter ���� */
  public void setIcustomaft(Integer icustomaft) {
    this.setAttributeValue(PoTransTypeVO.ICUSTOMAFT, icustomaft);
  }

  /** װ�˺���״̬ setter ���� */
  public void setIloadaft(Integer iloadaft) {
    this.setAttributeValue(PoTransTypeVO.ILOADAFT, iloadaft);
  }

  /** ��;��ʼ setter ���� */
  public void setIonwaybegin(Integer ionwaybegin) {
    this.setAttributeValue(PoTransTypeVO.IONWAYBEGIN, ionwaybegin);
  }

  /** ��;���� setter ���� */
  public void setIonwayend(Integer ionwayend) {
    this.setAttributeValue(PoTransTypeVO.IONWAYEND, ionwayend);
  }

  /** ���غ���״̬ setter ���� */
  public void setIoutcustomaft(Integer ioutcustomaft) {
    this.setAttributeValue(PoTransTypeVO.IOUTCUSTOMAFT, ioutcustomaft);
  }

  /** �������״̬ setter ���� */
  public void setIoutputaft(Integer ioutputaft) {
    this.setAttributeValue(PoTransTypeVO.IOUTPUTAFT, ioutputaft);
  }

  /** �빺�����ɶ������Ʒ�ʽ setter ���� */
  public void setIprtopolimit(Integer iprtopolimit) {
    this.setAttributeValue(PoTransTypeVO.IPRTOPOLIMIT, iprtopolimit);
  }

  /** ���� setter ���� */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(PoTransTypeVO.PK_GROUP, pk_group);
  }

  /** ���� setter ���� */
  public void setPk_potrantype(String pk_potrantype) {
    this.setAttributeValue(PoTransTypeVO.PK_POTRANTYPE, pk_potrantype);
  }

  /** ts setter ���� */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(PoTransTypeVO.TS, ts);
  }

  /** �������ͱ��� setter ���� */
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(PoTransTypeVO.VTRANTYPECODE, vtrantypecode);
  }
}
