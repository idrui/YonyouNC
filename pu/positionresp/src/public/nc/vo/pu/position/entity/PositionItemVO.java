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
public class PositionItemVO extends SuperVO {

  /** dr */
  public static final String DR = "dr";

  /** �ų�/Ӧ�� */
  public static final String FFLAG = "fflag";

  /** ���ϻ���������� */
  public static final String MARBASCLASS_CODE = "marbasclass_code";

  /** ���ϲɹ�������� */
  public static final String MARPUCLASS_CODE = "marpuclass_code";

  /** ���ϱ��� */
  public static final String MATERIAL_CODE = "material_code";

  /** ���ϻ������� */
  public static final String PK_MARBASCLASS = "pk_marbasclass";

  /** ���ϲɹ����� */
  public static final String PK_MARPUCLASS = "pk_marpuclass";

  /** ���� */
  public static final String PK_MATERIAL = "pk_material";

  /** ��֯ */
  public static final String PK_ORG = "pk_org";

  /** ��λ�����ӱ� */
  public static final String PK_POSITION = "pk_position";

  /** ��λ�����ӱ� */
  public static final String PK_POSITION_B = "pk_position_b";

  /** ����oid */
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  /** ts */
  public static final String TS = "ts";

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /** dr getter ���� */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(PositionItemVO.DR);
  }

  /** �ų�/Ӧ�� getter ���� */
  public Integer getFflag() {
    return (Integer) this.getAttributeValue(PositionItemVO.FFLAG);
  }

  /** ���ϻ���������� getter ���� */
  public String getMarbasclass_code() {
    return (String) this.getAttributeValue(PositionItemVO.MARBASCLASS_CODE);
  }

  /** ���ϲɹ�������� getter ���� */
  public String getMarpuclass_code() {
    return (String) this.getAttributeValue(PositionItemVO.MARPUCLASS_CODE);
  }

  /** ���ϱ��� getter ���� */
  public String getMaterial_code() {
    return (String) this.getAttributeValue(PositionItemVO.MATERIAL_CODE);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta(PUEntity.POSITION_B);
    return meta;
  }

  /** ���ϻ������� getter ���� */
  public String getPk_marbasclass() {
    return (String) this.getAttributeValue(PositionItemVO.PK_MARBASCLASS);
  }

  /** ���ϲɹ����� getter ���� */
  public String getPk_marpuclass() {
    return (String) this.getAttributeValue(PositionItemVO.PK_MARPUCLASS);
  }

  /** ���� getter ���� */
  public String getPk_material() {
    return (String) this.getAttributeValue(PositionItemVO.PK_MATERIAL);
  }

  /** ��֯ getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(PositionItemVO.PK_ORG);
  }

  /** ��λ�����ӱ� getter ���� */
  public String getPk_position() {
    return (String) this.getAttributeValue(PositionItemVO.PK_POSITION);
  }

  /** ��λ�����ӱ� getter ���� */
  public String getPk_position_b() {
    return (String) this.getAttributeValue(PositionItemVO.PK_POSITION_B);
  }

  /** ���� OID getter ���� */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(PositionItemVO.PK_SRCMATERIAL);
  }

  /** ts getter ���� */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(PositionItemVO.TS);
  }

  /** dr setter ���� */
  public void setDr(Integer dr) {
    this.setAttributeValue(PositionItemVO.DR, dr);
  }

  /** �ų�/Ӧ�� setter ���� */
  public void setFflag(Integer fflag) {
    this.setAttributeValue(PositionItemVO.FFLAG, fflag);
  }

  /** ���ϻ���������� setter ���� */
  public void setMarbasclass_code(String marbasclass_code) {
    this.setAttributeValue(PositionItemVO.MARBASCLASS_CODE, marbasclass_code);
  }

  /** ���ϲɹ�������� setter ���� */
  public void setMarpuclass_code(String marpuclass_code) {
    this.setAttributeValue(PositionItemVO.MARPUCLASS_CODE, marpuclass_code);
  }

  /** ���ϱ��� setter ���� */
  public void setMaterial_code(String material_code) {
    this.setAttributeValue(PositionItemVO.MATERIAL_CODE, material_code);
  }

  /** ���ϻ������� setter ���� */
  public void setPk_marbasclass(String pk_marbasclass) {
    this.setAttributeValue(PositionItemVO.PK_MARBASCLASS, pk_marbasclass);
  }

  /** ���ϲɹ����� setter ���� */
  public void setPk_marpuclass(String pk_marpuclass) {
    this.setAttributeValue(PositionItemVO.PK_MARPUCLASS, pk_marpuclass);
  }

  /** ���� setter ���� */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(PositionItemVO.PK_MATERIAL, pk_material);
  }

  /** ��֯ setter ���� */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(PositionItemVO.PK_ORG, pk_org);
  }

  /** ��λ�����ӱ� setter ���� */
  public void setPk_position(String pk_position) {
    this.setAttributeValue(PositionItemVO.PK_POSITION, pk_position);
  }

  /** ��λ�����ӱ� setter ���� */
  public void setPk_position_b(String pk_position_b) {
    this.setAttributeValue(PositionItemVO.PK_POSITION_B, pk_position_b);
  }

  /** ����oid setter ���� */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(PositionItemVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

  /** ts setter ���� */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(PositionItemVO.TS, ts);
  }
}
