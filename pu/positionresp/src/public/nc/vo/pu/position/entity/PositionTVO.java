package nc.vo.pu.position.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * �ƻ��ڣ��ɹ��ڣ��������ñ���VO
 * 
 * @author GGR
 * @time 2009-12-22 ����04:33:56
 * @since 6.0
 */
public class PositionTVO extends SuperVO {

  /** �ɹ�Ա */
  public static final String CEMPLOYEEID = "cemployeeid";

  /** ��λ���� */
  public static final String CODE = "code";

  /** dr */
  public static final String DR = "dr";

  /** ��λ���� */
  public static final String FPOSITIONTYPE = "fpositiontype";

  /** ���Ϸ������ */
  public static final String MARCLASSCODE = "marclasscode";

  /** ���� */
  public static final String PK_GROUP = "pk_group";

  /** ���Ϸ��� */
  public static final String PK_MARCLASS = "pk_marclass";

  /** ��֯ */
  public static final String PK_ORG = "pk_org";

  /** ��λ���� */
  public static final String PK_POSITION = "pk_position";

  /** ��λ�����ӱ� */
  public static final String PK_POSITION_B = "pk_position_b";

  /** ���� */
  public static final String PK_POSITION_T = "pk_position_t";

  /** ts */
  public static final String TS = "ts";

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /** �ɹ�Ա getter ���� */
  public String getCemployeeid() {
    return (String) this.getAttributeValue(PositionTVO.CEMPLOYEEID);
  }

  /** ��λ���� getter ���� */
  public String getCode() {
    return (String) this.getAttributeValue(PositionTVO.CODE);
  }

  /** dr getter ���� */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(PositionTVO.DR);
  }

  /** ��λ���� getter ���� */
  public Integer getFpositiontype() {
    return (Integer) this.getAttributeValue(PositionHeaderVO.FPOSITIONTYPE);
  }

  /** ���Ϸ������ getter ���� */
  public String getMarclasscode() {
    return (String) this.getAttributeValue(PositionTVO.MARCLASSCODE);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta(PUEntity.POSITION_T);
    return meta;
  }

  /** ���� getter ���� */
  public String getPk_group() {
    return (String) this.getAttributeValue(PositionTVO.PK_GROUP);
  }

  /** ���Ϸ��� getter ���� */
  public String getPk_marclass() {
    return (String) this.getAttributeValue(PositionTVO.PK_MARCLASS);
  }

  /** ��֯ getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(PositionTVO.PK_ORG);
  }

  /** ��λ���� getter ���� */
  public String getPk_position() {
    return (String) this.getAttributeValue(PositionTVO.PK_POSITION);
  }

  /** ��λ�����ӱ� getter ���� */
  public String getPk_position_b() {
    return (String) this.getAttributeValue(PositionTVO.PK_POSITION_B);
  }

  /** ���� getter ���� */
  public String getPk_position_t() {
    return (String) this.getAttributeValue(PositionTVO.PK_POSITION_T);
  }

  /** ts getter ���� */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(PositionTVO.TS);
  }

  /** �ɹ�Ա setter ���� */
  public void setCemployeeid(String cemployeeid) {
    this.setAttributeValue(PositionTVO.CEMPLOYEEID, cemployeeid);
  }

  /** ��λ���� setter ���� */
  public void setCode(String code) {
    this.setAttributeValue(PositionTVO.CODE, code);
  }

  /** dr setter ���� */
  public void setDr(Integer dr) {
    this.setAttributeValue(PositionTVO.DR, dr);
  }

  /** ��λ���� setter ���� */
  public void setFpositiontype(Integer fpositiontype) {
    this.setAttributeValue(PositionHeaderVO.FPOSITIONTYPE, fpositiontype);
  }

  /** ���Ϸ������ setter ���� */
  public void setMarclasscode(String marclasscode) {
    this.setAttributeValue(PositionTVO.MARCLASSCODE, marclasscode);
  }

  /** ���� setter ���� */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(PositionTVO.PK_GROUP, pk_group);
  }

  /** ���Ϸ��� setter ���� */
  public void setPk_marclass(String pk_marclass) {
    this.setAttributeValue(PositionTVO.PK_MARCLASS, pk_marclass);
  }

  /** ��֯ setter ���� */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(PositionTVO.PK_ORG, pk_org);
  }

  /** ��λ���� setter ���� */
  public void setPk_position(String pk_position) {
    this.setAttributeValue(PositionTVO.PK_POSITION, pk_position);
  }

  /** ��λ�����ӱ� setter ���� */
  public void setPk_position_b(String pk_position_b) {
    this.setAttributeValue(PositionTVO.PK_POSITION_B, pk_position_b);
  }

  /** ���� setter ���� */
  public void setPk_position_t(String pk_position_t) {
    this.setAttributeValue(PositionTVO.PK_POSITION_T, pk_position_t);
  }

  /** ts setter ���� */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(PositionTVO.TS, ts);
  }
}
