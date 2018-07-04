package nc.vo.pu.position.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * 计划岗（采购岗）物料设置表体VO
 * 
 * @author GGR
 * @time 2009-12-22 下午04:33:56
 * @since 6.0
 */
public class PositionItemVO extends SuperVO {

  /** dr */
  public static final String DR = "dr";

  /** 排除/应用 */
  public static final String FFLAG = "fflag";

  /** 物料基本分类编码 */
  public static final String MARBASCLASS_CODE = "marbasclass_code";

  /** 物料采购分类编码 */
  public static final String MARPUCLASS_CODE = "marpuclass_code";

  /** 物料编码 */
  public static final String MATERIAL_CODE = "material_code";

  /** 物料基本分类 */
  public static final String PK_MARBASCLASS = "pk_marbasclass";

  /** 物料采购分类 */
  public static final String PK_MARPUCLASS = "pk_marpuclass";

  /** 物料 */
  public static final String PK_MATERIAL = "pk_material";

  /** 组织 */
  public static final String PK_ORG = "pk_org";

  /** 岗位设置子表 */
  public static final String PK_POSITION = "pk_position";

  /** 岗位设置子表 */
  public static final String PK_POSITION_B = "pk_position_b";

  /** 物料oid */
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  /** ts */
  public static final String TS = "ts";

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /** dr getter 方法 */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(PositionItemVO.DR);
  }

  /** 排除/应用 getter 方法 */
  public Integer getFflag() {
    return (Integer) this.getAttributeValue(PositionItemVO.FFLAG);
  }

  /** 物料基本分类编码 getter 方法 */
  public String getMarbasclass_code() {
    return (String) this.getAttributeValue(PositionItemVO.MARBASCLASS_CODE);
  }

  /** 物料采购分类编码 getter 方法 */
  public String getMarpuclass_code() {
    return (String) this.getAttributeValue(PositionItemVO.MARPUCLASS_CODE);
  }

  /** 物料编码 getter 方法 */
  public String getMaterial_code() {
    return (String) this.getAttributeValue(PositionItemVO.MATERIAL_CODE);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta(PUEntity.POSITION_B);
    return meta;
  }

  /** 物料基本分类 getter 方法 */
  public String getPk_marbasclass() {
    return (String) this.getAttributeValue(PositionItemVO.PK_MARBASCLASS);
  }

  /** 物料采购分类 getter 方法 */
  public String getPk_marpuclass() {
    return (String) this.getAttributeValue(PositionItemVO.PK_MARPUCLASS);
  }

  /** 物料 getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(PositionItemVO.PK_MATERIAL);
  }

  /** 组织 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(PositionItemVO.PK_ORG);
  }

  /** 岗位设置子表 getter 方法 */
  public String getPk_position() {
    return (String) this.getAttributeValue(PositionItemVO.PK_POSITION);
  }

  /** 岗位设置子表 getter 方法 */
  public String getPk_position_b() {
    return (String) this.getAttributeValue(PositionItemVO.PK_POSITION_B);
  }

  /** 物料 OID getter 方法 */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(PositionItemVO.PK_SRCMATERIAL);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(PositionItemVO.TS);
  }

  /** dr setter 方法 */
  public void setDr(Integer dr) {
    this.setAttributeValue(PositionItemVO.DR, dr);
  }

  /** 排除/应用 setter 方法 */
  public void setFflag(Integer fflag) {
    this.setAttributeValue(PositionItemVO.FFLAG, fflag);
  }

  /** 物料基本分类编码 setter 方法 */
  public void setMarbasclass_code(String marbasclass_code) {
    this.setAttributeValue(PositionItemVO.MARBASCLASS_CODE, marbasclass_code);
  }

  /** 物料采购分类编码 setter 方法 */
  public void setMarpuclass_code(String marpuclass_code) {
    this.setAttributeValue(PositionItemVO.MARPUCLASS_CODE, marpuclass_code);
  }

  /** 物料编码 setter 方法 */
  public void setMaterial_code(String material_code) {
    this.setAttributeValue(PositionItemVO.MATERIAL_CODE, material_code);
  }

  /** 物料基本分类 setter 方法 */
  public void setPk_marbasclass(String pk_marbasclass) {
    this.setAttributeValue(PositionItemVO.PK_MARBASCLASS, pk_marbasclass);
  }

  /** 物料采购分类 setter 方法 */
  public void setPk_marpuclass(String pk_marpuclass) {
    this.setAttributeValue(PositionItemVO.PK_MARPUCLASS, pk_marpuclass);
  }

  /** 物料 setter 方法 */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(PositionItemVO.PK_MATERIAL, pk_material);
  }

  /** 组织 setter 方法 */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(PositionItemVO.PK_ORG, pk_org);
  }

  /** 岗位设置子表 setter 方法 */
  public void setPk_position(String pk_position) {
    this.setAttributeValue(PositionItemVO.PK_POSITION, pk_position);
  }

  /** 岗位设置子表 setter 方法 */
  public void setPk_position_b(String pk_position_b) {
    this.setAttributeValue(PositionItemVO.PK_POSITION_B, pk_position_b);
  }

  /** 物料oid setter 方法 */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(PositionItemVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

  /** ts setter 方法 */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(PositionItemVO.TS, ts);
  }
}
