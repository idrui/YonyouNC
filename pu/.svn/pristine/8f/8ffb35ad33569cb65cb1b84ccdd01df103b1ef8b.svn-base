package nc.vo.pu.m4201.enumeration;

/**
 * 入库单结算查询对话框使用的field_code枚举
 * 
 * @since 6.0
 * @version 2011-3-6 下午10:04:12
 * @author zhaoyha
 */
public enum StockQryFieldCode {
  /** 是否已确认应付和成本 **/
  bconfirmed("bconfirminclude"),
  /** 是否已费用结算 **/
  bfeesettled("bfeesettled"),
  /** 包含的物料（用于结算的查询－是否按已输入的入库单物料过滤入库单物料） **/
  bmarinclude("bmarinclude"),
  /** 是否已结算（用于结算的查询） **/
  bsettled("bsettled"),
  /** 结算财务组织 **/
  financeorgbody("pk_stockps_b.pk_financeorg"),
  /** 发票的物料key，用于传scheme作为key使用 */
  invoice_matrial_id("invoice_matrial_id"),
  /** 入库单子表集团 **/
  invoicebgroup("pk_stockps_b.pk_group"),
  /** 入库单子表主组织 **/
  invoiceborg("pk_stockps_b.pk_org"),
  /** 入库单子表供应商 **/
  invoicebsup("pk_stockps_b.pk_supplier"),
  /** 物料基本分类 **/
  marbascls("pk_stockps_b.pk_material.pk_marbasclass"),
  /** 物料编码 **/
  marcode("pk_stockps_b.pk_material.code"),
  /** 物料名称 **/
  marname("pk_stockps_b.pk_material.name"),
  /** 物料编码 **/
  srcmarcode("pk_stockps_b.pk_srcmaterial.code"),
  /** 物料名称 **/
  srcmarname("pk_stockps_b.pk_srcmaterial.name"),
  /** 查询的入库单单据类型 **/
  stockbilltype("stockbilltype"),

  /** 仓库 */
  stordoc("pk_stordoc"),

  /** 库存组织 */
  storeorg("pk_storeorg"),
  /** 合同号 */
  vctcode("pk_stockps_b.vctcode");

  private String code;

  private StockQryFieldCode(String code) {
    this.code = code;
  }

  public String code() {
    return this.code;
  }

}
