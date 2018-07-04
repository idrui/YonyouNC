package nc.vo.pu.costfactor.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * �ɱ�Ҫ�ض����ͷVO
 * 
 * @author GGR
 * @time 2009-12-22 ����04:33:56
 * @since 6.0
 */
public class CostfactorHeaderVO extends SuperVO {

  /** �Ƿ�������ɱ� **/
  public static final String BENTERCOST = "bentercost";

  /** dr **/
  public static final String DR = "dr";

  /** ��̯��ʽ **/
  public static final String FAPPORTIONMODE = "fapportionmode";

  /** �ɱ�Ҫ��˳�� **/
  public static final String IFACTORORDER = "ifactororder";

  /** �ɱ�Ҫ��ID **/
  public static final String PK_COSTFACTOR = "pk_costfactor";

  /** �������� **/
  public static final String PK_GROUP = "pk_group";

  /** ����֯ **/
  public static final String PK_ORG = "pk_org";

  /** ����֯�汾��Ϣ **/
  public static final String PK_ORG_V = "pk_org_v";

  /** ʱ��� **/
  public static final String TS = "ts";

  /** �ɱ�Ҫ������ **/
  public static final String VFACTORNAME = "vfactorname";

  /** �ɱ�Ҫ������2 **/
  public static final String VFACTORNAME2 = "vfactorname2";

  /** �ɱ�Ҫ������3 **/
  public static final String VFACTORNAME3 = "vfactorname3";

  /** �ɱ�Ҫ������4 **/
  public static final String VFACTORNAME4 = "vfactorname4";

  /** �ɱ�Ҫ������5 **/
  public static final String VFACTORNAME5 = "vfactorname5";

  /** �ɱ�Ҫ������6 **/
  public static final String VFACTORNAME6 = "vfactorname6";

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -7832486845427281050L;

  /** �Ƿ�������ɱ� **/
  public UFBoolean getBentercost() {
    return (UFBoolean) this.getAttributeValue(CostfactorHeaderVO.BENTERCOST);
  }

  /** dr **/
  public Integer getDr() {
    return (Integer) this.getAttributeValue(CostfactorHeaderVO.DR);
  }

  /** ��̯��ʽ **/
  public Integer getFapportionmode() {
    return (Integer) this.getAttributeValue(CostfactorHeaderVO.FAPPORTIONMODE);
  }

  /** �ɱ�Ҫ��˳�� **/
  public Integer getIfactororder() {
    return (Integer) this.getAttributeValue(CostfactorHeaderVO.IFACTORORDER);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta(PUEntity.COSTFACTOR_H);
    return meta;
  }

  /** �ɱ�Ҫ��ID **/
  public String getPk_costfactor() {
    return (String) this.getAttributeValue(CostfactorHeaderVO.PK_COSTFACTOR);
  }

  /** �������� **/
  public String getPk_group() {
    return (String) this.getAttributeValue(CostfactorHeaderVO.PK_GROUP);
  }

  /** ����֯ **/
  public String getPk_org() {
    return (String) this.getAttributeValue(CostfactorHeaderVO.PK_ORG);
  }

  /** ����֯�汾��Ϣ **/
  public String getPk_org_v() {
    return (String) this.getAttributeValue(CostfactorHeaderVO.PK_ORG_V);
  }

  /** ʱ��� **/
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(CostfactorHeaderVO.TS);
  }

  /** �ɱ�Ҫ������ **/
  public String getVfactorname() {
    return (String) this.getAttributeValue(CostfactorHeaderVO.VFACTORNAME);
  }

  /** �ɱ�Ҫ������2 **/
  public String getVfactorname2() {
    return (String) this.getAttributeValue(CostfactorHeaderVO.VFACTORNAME2);
  }

  /** �ɱ�Ҫ������3 **/
  public String getVfactorname3() {
    return (String) this.getAttributeValue(CostfactorHeaderVO.VFACTORNAME3);
  }

  /** �ɱ�Ҫ������ 4 **/
  public String getVfactorname4() {
    return (String) this.getAttributeValue(CostfactorHeaderVO.VFACTORNAME4);
  }

  /** �ɱ�Ҫ������5 **/
  public String getVfactorname5() {
    return (String) this.getAttributeValue(CostfactorHeaderVO.VFACTORNAME5);
  }

  /** �ɱ�Ҫ������6 **/
  public String getVfactorname6() {
    return (String) this.getAttributeValue(CostfactorHeaderVO.VFACTORNAME6);
  }

  /** �Ƿ�������ɱ� **/
  public void setBentercost(UFBoolean bentercost) {
    this.setAttributeValue(CostfactorHeaderVO.BENTERCOST, bentercost);
  }

  /** dr **/
  public void setDr(Integer dr) {
    this.setAttributeValue(CostfactorHeaderVO.DR, dr);
  }

  /** ��̯��ʽ **/
  public void setFapportionmode(Integer fapportionmode) {
    this.setAttributeValue(CostfactorHeaderVO.FAPPORTIONMODE, fapportionmode);
  }

  /** �ɱ�Ҫ��˳�� **/
  public void setIfactororder(Integer ifactororder) {
    this.setAttributeValue(CostfactorHeaderVO.IFACTORORDER, ifactororder);
  }

  /** �ɱ�Ҫ��ID **/
  public void setPk_costfactor(String pk_costfactor) {
    this.setAttributeValue(CostfactorHeaderVO.PK_COSTFACTOR, pk_costfactor);
  }

  /** �������� **/
  public void setPk_group(String pk_group) {
    this.setAttributeValue(CostfactorHeaderVO.PK_GROUP, pk_group);
  }

  /** ����֯ **/
  public void setPk_org(String pk_org) {
    this.setAttributeValue(CostfactorHeaderVO.PK_ORG, pk_org);
  }

  /** ����֯�汾��Ϣ **/
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(CostfactorHeaderVO.PK_ORG_V, pk_org_v);
  }

  /** ʱ��� **/
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(CostfactorHeaderVO.TS, ts);
  }

  /** �ɱ�Ҫ������ **/
  public void setVfactorname(String vfactorname) {
    this.setAttributeValue(CostfactorHeaderVO.VFACTORNAME, vfactorname);
  }

  /** �ɱ�Ҫ������2 **/
  public void setVfactorname2(String vfactorname2) {
    this.setAttributeValue(CostfactorHeaderVO.VFACTORNAME2, vfactorname2);
  }

  /** �ɱ�Ҫ������3 **/
  public void setVfactorname3(String vfactorname3) {
    this.setAttributeValue(CostfactorHeaderVO.VFACTORNAME3, vfactorname3);
  }

  /** �ɱ�Ҫ������ 4 **/
  public void setVfactorname4(String vfactorname4) {
    this.setAttributeValue(CostfactorHeaderVO.VFACTORNAME4, vfactorname4);
  }

  /** �ɱ�Ҫ������5 **/
  public void setVfactorname5(String vfactorname5) {
    this.setAttributeValue(CostfactorHeaderVO.VFACTORNAME5, vfactorname5);
  }

  /** �ɱ�Ҫ������6 **/
  public void setVfactorname6(String vfactorname6) {
    this.setAttributeValue(CostfactorHeaderVO.VFACTORNAME6, vfactorname6);
  }

}
