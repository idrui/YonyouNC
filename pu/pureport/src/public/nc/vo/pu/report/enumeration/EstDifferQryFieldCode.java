package nc.vo.pu.report.enumeration;

/**
 * 暂估差异表查询条件字段枚举
 * 
 * @since 6.3
 * @version 2012-8-24 上午09:56:13
 * @author tianft
 */

public enum EstDifferQryFieldCode {

  boverDifferRate("boverDifferRate"), // 只查询差异率超过报警标准的记录
  fperiod("fperiod"), // 日期区间
  groupon("groupon"), // 统计项目
  materialname("materialname"), // 物料名称
  ndifferRate("ndifferRate"), // 差异率报警标准
  pk_areacl("pk_areacl"), // 供应商地区分类
  pk_dept("pk_dept"), // 采购部门
  pk_financeorg("pk_financeorg"), // 财务组织
  pk_marbasclass("pk_marbasclass"), // 物料基本分类编码
  pk_psndoc("pk_psndoc"), // 采购员
  pk_purchaseorg("pk_purchaseorg"), // 采购组织
  pk_srcmaterial("pk_srcmaterial"), // 物料编码
  pk_storeorg("pk_storeorg"), // 库存组织
  pk_supplier("pk_supplier"), // 供应商编码
  qrycontent("qrycontent"), // 统计内容
  suppliername("suppliername");// 供应商名称

  private String code;

  private EstDifferQryFieldCode(String code) {
    this.code = code;
  }

  public String code() {
    return this.code;
  }
}
