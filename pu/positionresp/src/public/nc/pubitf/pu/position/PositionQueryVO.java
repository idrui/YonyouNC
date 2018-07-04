package nc.pubitf.pu.position;

import java.io.Serializable;

/**
 * 采购岗查询参数VO
 * 
 * @since 6.0
 * @version 2012-10-31 下午04:35:52
 * @author wuxla
 */
public class PositionQueryVO implements Serializable {
  private static final long serialVersionUID = -5426404702928672356L;

  /**
   * 物料基本分类，必须是末级分类，目前只应用于合同
   */
  private String pk_marbaseclass;

  /**
   * 采购岗主键
   */
  private String pk_position;

  /**
   * 采购组织，必输
   */
  private String pk_purchaseorg;

  /**
   * 物料OID，和物料基本分类（目前只用于合同）互质，其中一个必输
   */
  private String pk_srcmaterial;

  /**
   * 库存组织，VO对照时传到请购单主组织或者采购订单需求库存组织，如果没有不用设置值
   */
  private String pk_stockorg;

  /**
   * @return 物料基本分类
   */
  public String getPk_marbaseclass() {
    return this.pk_marbaseclass;
  }

  /**
   * @return 采购岗主键
   */
  public String getPk_position() {
    return this.pk_position;
  }

  /**
   * @return 采购组织
   */
  public String getPk_purchaseorg() {
    return this.pk_purchaseorg;
  }

  /**
   * @return 物料OID
   */
  public String getPk_srcmaterial() {
    return this.pk_srcmaterial;
  }

  /**
   * @return 库存组织
   */
  public String getPk_stockorg() {
    return this.pk_stockorg;
  }

  /**
   * 设置物料基本分类，必须是末级分类，目前只有合同使用
   * 
   * @param pk_marbaseclass
   */
  public void setPk_marbaseclass(String pk_marbaseclass) {
    this.pk_marbaseclass = pk_marbaseclass;
  }

  /**
   * 设置采购岗主键
   * 
   * @param pk_position
   */
  public void setPk_position(String pk_position) {
    this.pk_position = pk_position;
  }

  /**
   * 设置采购组织
   * 
   * @param pk_purchaseorg
   */
  public void setPk_purchaseorg(String pk_purchaseorg) {
    this.pk_purchaseorg = pk_purchaseorg;
  }

  /**
   * 设置物料OID
   * 和物料基本分类互斥，只能一个有值
   * 物料基本分类目前只有合同用，所以其他模块必须设值
   * 
   * @param pk_srcmaterial
   */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.pk_srcmaterial = pk_srcmaterial;
  }

  /**
   * 库存组织
   * VO对照时传到请购单主组织或者采购订单需求库存组织，如果没有对照（比如合同、价格审批单）不用设置值
   * 
   * @param pk_stockorg
   */
  public void setPk_stockorg(String pk_stockorg) {
    this.pk_stockorg = pk_stockorg;
  }
}
