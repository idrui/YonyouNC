/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-19 下午01:25:26
 */
package nc.vo.pu.costfactor.entity;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>成本要素的视图VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-19 下午01:25:26
 */
public class CostfactorViewVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = -5436282098365933096L;

  /**
   * 属性是否进入存货成本的Getter方法.
   * 
   * @time 2009-12-22 16:22:55
   * @return 是否进入存货成本
   */
  public UFBoolean getBentercost() {
    return (UFBoolean) this.getAttributeValue(CostfactorHeaderVO.BENTERCOST);
  }

  public UFBoolean getBshow() {
    return (UFBoolean) this.getAttributeValue(CostfactorItemVO.BSHOW);
  }

  /**
   * 属性分摊方式的Getter方法.
   * 
   * @time 2009-12-22 16:22:55
   * @return 分摊方式
   */
  public Integer getFapportionmode() {
    return (Integer) this.getAttributeValue(CostfactorHeaderVO.FAPPORTIONMODE);
  }

  /** 成本要素顺序 **/
  public Integer getIfactororder() {
    return (Integer) this.getAttributeValue(CostfactorHeaderVO.IFACTORORDER);
  }

  /** 显示顺序 **/
  public Integer getIshoworder() {
    return (Integer) this.getAttributeValue(CostfactorItemVO.ISHOWORDER);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.pattern.model.entity.view.AbstractDataView#getMetaData()
   */
  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance()
        .getBillViewMeta(CostfactorVO.class);
  }

  // /** 费用项对应的默认税率 **/
  // public UFDouble getNmartaxrate() {
  // return ((CostfactorItemVO) this.getVO(CostfactorItemVO.class))
  // .getNmartaxrate();
  // }

  /**
   * 属性成本要素表头_主键的Getter方法.
   * 
   * @time 2009-12-22 16:22:55
   * @return 成本要素表头_主键
   */
  public String getPk_costfactor() {
    return (String) this.getAttributeValue(CostfactorItemVO.PK_COSTFACTOR);
  }

  /**
   * 属性成本要素行ID的Getter方法.
   * 
   * @time 2009-12-22 16:22:55
   * @return 成本要素行ID
   */
  public String getPk_costfactor_b() {
    return (String) this.getAttributeValue(CostfactorItemVO.PK_COSTFACTOR_B);
  }

  /**
   * 属性所属集团的Getter方法.
   * 
   * @time 2009-12-22 16:22:55
   * @return 所属集团
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(CostfactorHeaderVO.PK_GROUP);
  }

  /**
   * 属性费用物料ID的Getter方法.
   * 
   * @time 2009-12-22 16:22:55
   * @return 费用物料ID
   */
  public String getPk_material() {
    return (String) this.getAttributeValue(CostfactorItemVO.PK_MATERIAL);
  }

  /**
   * 属性主组织的Getter方法.
   * 
   * @time 2009-12-22 16:22:55
   * @return 主组织
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(CostfactorHeaderVO.PK_ORG);
  }

  /** 组织版本 **/
  public String getPk_org_v() {
    return (String) this.getAttributeValue(CostfactorHeaderVO.PK_ORG_V);
  }

  /**
   * 属性物料OID的Getter方法.
   * 
   * @time 2009-12-22 16:22:55
   * @return 物料OID
   */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(CostfactorItemVO.PK_SRCMATERIAL);
  }

  /**
   * 属性是否进入存货成本的Setter方法.
   * 
   * @time 2009-12-22 16:22:55
   * @param bentercost 是否进入存货成本
   */
  public void setBentercost(UFBoolean bentercost) {
    this.setAttributeValue(CostfactorHeaderVO.BENTERCOST, bentercost);
  }

  /** 是否显示 **/
  public void setBshow(UFBoolean bshow) {
    this.setAttributeValue(CostfactorItemVO.BSHOW, bshow);
  }

  /**
   * 属性分摊方式的Setter方法.
   * 
   * @time 2009-12-22 16:22:55
   * @param iapportionmode 分摊方式
   */
  public void setFapportionmode(Integer fapportionmode) {
    this.setAttributeValue(CostfactorHeaderVO.FAPPORTIONMODE, fapportionmode);
  }

  /** 成本要素顺序 **/
  public void setIfactororder(Integer ifactororder) {
    this.setAttributeValue(CostfactorHeaderVO.IFACTORORDER, ifactororder);
  }

  /** 显示顺序 **/
  public void setIshoworder(Integer ishoworder) {
    this.setAttributeValue(CostfactorItemVO.ISHOWORDER, ishoworder);
  }

  /**
   * 属性成本要素表头_主键的Setter方法.
   * 
   * @time 2009-12-22 16:22:55
   * @param pk_costfactor 成本要素表头_主键
   */
  public void setPk_costfactor(String pk_costfactor) {
    this.setAttributeValue(CostfactorItemVO.PK_COSTFACTOR, pk_costfactor);
  }

  /**
   * 属性成本要素行ID的Setter方法.
   * 
   * @time 2009-12-22 16:22:55
   * @param pk_costfactor_b 成本要素行ID
   */
  public void setPk_costfactor_b(String pk_costfactor_b) {
    this.setAttributeValue(CostfactorItemVO.PK_COSTFACTOR_B, pk_costfactor_b);
  }

  /**
   * 属性所属集团的Setter方法.
   * 
   * @time 2009-12-22 16:22:55
   * @param pk_group 所属集团
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(CostfactorHeaderVO.PK_GROUP, pk_group);
  }

  /**
   * 属性费用物料ID的Setter方法.
   * 
   * @time 2009-12-22 16:22:55
   * @param pk_material 费用物料ID
   */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(CostfactorItemVO.PK_MATERIAL, pk_material);
  }

  /**
   * 属性主组织的Setter方法.
   * 
   * @time 2009-12-22 16:22:55
   * @param pk_org 主组织
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(CostfactorHeaderVO.PK_ORG, pk_org);
  }

  /** 组织版本 **/
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(CostfactorHeaderVO.PK_ORG_V, pk_org_v);
  }

  /**
   * 属性物料OID的Setter方法.
   * 
   * @time 2009-12-22 16:22:55
   * @param pk_material 物料OID
   */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(CostfactorItemVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

}
