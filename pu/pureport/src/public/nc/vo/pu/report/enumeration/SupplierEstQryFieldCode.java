package nc.vo.pu.report.enumeration;

/**
 * 供应商暂估相关的查询条件字段枚举
 * 
 * @since 6.0
 * @version 2011-7-27 下午03:00:23
 * @author 田锋涛
 */

public enum SupplierEstQryFieldCode {

  /** 已全部结算入库单是否统计 */
  bincldfinish("bincldfinish"),
  /** 日期区间 */
  fperiod("fperiod"),
  /** 物料名称 */
  material_name("bd_material.name"),
  /** 供应商地区分类 */
  pk_areacl("bd_supplier.pk_areacl"),
  /** 采购部门 */
  pk_dept("eh.pk_dept"),
  /** 财务组织 */
  pk_financeorg("eb.pk_financeorg"),
  /** 物料基本分类编码 */
  pk_marbasclass("bd_material.pk_marbasclass"),
  /** 库存组织 */
  pk_org("eb.pk_org"),
  /** 采购员 */
  pk_psndoc("eh.pk_psndoc"),
  /** 采购组织 */
  pk_purchaseorg("eb.pk_purchaseorg"),
  /** 物料编码 */
  pk_srcmaterial("eb.pk_srcmaterial"),
  /** 供应商编码 */
  pk_supplier("eb.pk_supplier"),
  /** 统计内容 */
  qrycontent("qrycontent"),
  /** 供应商名称 */
  supplier_name("bd_supplier.name"),

  /** 批次号 */
  vbatchcode("eb.vbatchcode");

  private String code;

  private SupplierEstQryFieldCode(String code) {
    this.code = code;
  }

  public String code() {
    return this.code;
  }
}
