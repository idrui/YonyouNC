package nc.vo.pu.m25.enumeration;

/**
 * 采购发票查询对话框过滤使用的field_code枚举
 * 
 * @since 6.0
 * @version 2011-1-17 上午10:53:08
 * @author zhaoyha
 */
public enum InvoiceQryFieldCode {
  /** 是否费用发票（用于结算的查询） **/
  bfeeinvoice("bfeeinvoice"),
  /** 包含的物料（用于结算的查询） **/
  bmarinclude("bmarinclude"),
  /** 是否查出关联费用发票是否费用发票 **/
  brelafeeinclude("brelafeeinclude"),
  /** 采购部门 **/
  deptcode("pk_dept.code"),
  /** 发票子表单据日期 **/
  invoicebdate("invoicebody.dbilldate"),
  /** 发票子表集团 **/
  invoicebgroup("invoicebody.pk_group"),
  /** 发票子表主键 **/
  invoicebid("invoicebody.pk_invoice_b"),
  /** 发票子表主组织 **/
  invoiceborg("invoicebody.pk_org"),
  /** 发票子表供应商 **/
  invoicebsup("invoicebody.pk_supplier"),
  /** 物料基本分类 **/
  marbascls("invoicebody.pk_material.pk_marbasclass"),
  /** 物料编码 **/
  marcode("invoicebody.pk_material.code"),
  /** 物料名称 **/
  marname("invoicebody.pk_material.name"),
  /** 发票主组织 **/
  pkorg("pk_org"),
  /** 物料编码 **/
  srcmarcode("invoicebody.pk_srcmaterial.code"),
  /** 物料名称 **/
  srcmarname("invoicebody.pk_srcmaterial.name"),
  /** 供应商 **/
  supplier("pk_supplier");

  private String code;

  private InvoiceQryFieldCode(String code) {
    this.code = code;
  }

  public String code() {
    return this.code;
  }

}
