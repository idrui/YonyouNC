package nc.vo.pu.report.enumeration;

/**
 * 发票查询条件字段枚举
 * 
 * @since 6.0
 * @version 2011-7-27 下午03:00:23
 * @author 田锋涛
 */

public enum InvoiceQryFieldCode {

  approver("po_invoice.approver"), // 审批人
  areacl_code("po_invoice.pk_supplier.pk_areacl.code"), // 供应商地区分类
  billmaker("po_invoice.billmaker"), // 制单人
  binitial("po_invoice.binitial"), // 期初类型
  bvirtual("po_invoice.bvirtual"), // 虚拟发票标志
  cproductorid("po_invoice_b.cproductorid"), // 生产厂商
  cprojectid("po_invoice_b.cprojectid"), // 项目
  csourcetypecode("po_invoice_b.csourcetypecode"), // 来源单据类型
  darrivedate("po_invoice.darrivedate"), // 票到日期
  dbilldate("po_invoice.dbilldate"), // 发票日期
  dsourcedate("po_invoice_b.dsourcedate"), // 来源单据日期
  fbillstatus("po_invoice.fbillstatus"), // 审批状态
  finvoiceclass("po_invoice.finvoiceclass"), // 发票分类
  marbasclass_code("po_invoice_b.pk_material.pk_marbasclass.code"), // 物料基本分类编码
  material_code("po_invoice_b.pk_srcmaterial.code"), // 物料编码
  material_name("po_invoice_b.pk_srcmaterial.name"), // 物料名称
  pk_bizpsn("po_invoice.pk_bizpsn"), // 业务员
  pk_dept("po_invoice.pk_dept"), // 采购部门
  pk_org("po_invoice.pk_org"), // 财务组织
  pk_payterm("po_invoice.pk_payterm"), // 付款协议
  pk_paytosupplier("po_invoice.pk_paytosupplier"), // 付款单位
  pk_purchaseorg("po_invoice.pk_purchaseorg"), // 采购组织
  pk_stockorg("po_invoice.pk_stockorg"), // 库存组织
  pk_stordoc("po_invoice_b.pk_stordoc"), // 仓库
  supplier_code("po_invoice.pk_supplier.code"), // 供应商编号
  supplier_name("po_invoice.pk_supplier.name"), // 供应商名称
  taudittime("po_invoice.taudittime"), // 审批时间
  vbatchcode("po_invoice_b.vbatchcode"), // 批次号
  vbillcode("po_invoice.vbillcode"), // 发票号
  vtrantypecode("po_invoice.vtrantypecode");// 发票类型

  private String code;

  private InvoiceQryFieldCode(String code) {
    this.code = code;
  }

  public String code() {
    return this.code;
  }
}
