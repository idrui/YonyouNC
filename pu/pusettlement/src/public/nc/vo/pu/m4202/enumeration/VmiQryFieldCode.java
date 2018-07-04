package nc.vo.pu.m4202.enumeration;

/**
 * 消耗汇总结算查询对话框使用的field_code枚举
 * 
 * @since 6.0
 * @version 2011-3-31 下午02:53:04
 * @author 田锋涛
 */
public enum VmiQryFieldCode {

  /** 是否已费用结算 **/
  bfeesettled("bfeesettled"),
  /** 包含的物料（用于结算的查询－是否按已输入的过滤） **/
  bmarinclude("bmarinclude"),
  /** 是否已结算 **/
  bsettled("bsettled"),
  /** 结算财务组织 */
  financeorg("pk_financeorg"),
  /** 集团 **/
  group("pk_group"),
  /** 物料基本分类 **/
  marbascls("pk_material.pk_marbasclass.code"),
  /** 物料编码 **/
  marcode("pk_stockps_b.pk_material.code"),
  /** 物料名称 **/
  marname("pk_stockps_b.pk_material.name"),
  /** 主组织 **/
  org("pk_org"),
  /** 物料编码 **/
  srcmarcode("pk_srcmaterial.code"),
  /** 物料名称 **/
  srcmarname("pk_srcmaterial.name"),
  /** 仓库 */
  stordoc("pk_stordoc"),
  /** 库存组织 */
  storeorg("pk_storeorg"),
  /** 供应商 **/
  supplier("pk_supplier");

  private String code;

  private VmiQryFieldCode(String code) {
    this.code = code;
  }

  public String code() {
    return this.code;
  }

}
