package nc.vo.pu.report.enumeration;

public enum OrderSummaryQryFieldCode {

  /** 最终关闭 */
  bfinalclose("bfinalclose"),
  /** 冻结 */
  bfrozen("bfrozen"),
  /** 退货 */
  breturn("breturn"),
  /** 客户 */
  casscustid("pk_order_b.casscustid"),
  /** 采购员 */
  cemployeeid("cemployeeid"),
  /** 生产厂商 */
  cproductorid("pk_order_b.cproductorid"),
  /** 项目 */
  cprojectid("pk_order_b.cprojectid"),
  /** 订单类型 */
  ctrantypeid("ctrantypeid"),
  /** 订单日期 */
  dbilldate("dbilldate"),
  /** 单据状态 */
  forderstatus("forderstatus"),
  /** 物料采购分类编码 */
  marpuclasscode("pk_order_b.pk_material.materialstock.pk_marpuclass.code"),
  /** 物料分类 */
  materialclasscode("pk_order_b.pk_srcmaterial.pk_marbasclass.code"),
  /** 物料编码 */
  materialcode("pk_order_b.pk_srcmaterial.code"),
  /** 物料名称 */
  materialname("pk_order_b.pk_srcmaterial.name"),
  /** 采购部门 */
  pk_dept("pk_dept"),
  /** 表体自定义项1 */
  pk_order_b_vbdef1("pk_order_b.vbdef1"),
  /** 表体自定义项10 */
  pk_order_b_vbdef10("pk_order_b.vbdef10"),
  /** 表体自定义项11 */
  pk_order_b_vbdef11("pk_order_b.vbdef11"),
  /** 表体自定义项12 */
  pk_order_b_vbdef12("pk_order_b.vbdef12"),
  /** 表体自定义项13 */
  pk_order_b_vbdef13("pk_order_b.vbdef13"),
  /** 表体自定义项14 */
  pk_order_b_vbdef14("pk_order_b.vbdef14"),
  /** 表体自定义项15 */
  pk_order_b_vbdef15("pk_order_b.vbdef15"),
  /** 表体自定义项16 */
  pk_order_b_vbdef16("pk_order_b.vbdef16"),
  /** 表体自定义项17 */
  pk_order_b_vbdef17("pk_order_b.vbdef17"),
  /** 表体自定义项18 */
  pk_order_b_vbdef18("pk_order_b.vbdef18"),
  /** 表体自定义项19 */
  pk_order_b_vbdef19("pk_order_b.vbdef19"),
  /** 表体自定义项2 */
  pk_order_b_vbdef2("pk_order_b.vbdef2"),
  /** 表体自定义项20 */
  pk_order_b_vbdef20("pk_order_b.vbdef20"),
  /** 表体自定义项3 */
  pk_order_b_vbdef3("pk_order_b.vbdef3"),
  /** 表体自定义项4 */
  pk_order_b_vbdef4("pk_order_b.vbdef4"),
  /** 表体自定义项5 */
  pk_order_b_vbdef5("pk_order_b.vbdef5"),
  /** 表体自定义项6 */
  pk_order_b_vbdef6("pk_order_b.vbdef6"),
  /** 表体自定义项7 */
  pk_order_b_vbdef7("pk_order_b.vbdef7"),
  /** 表体自定义项8 */
  pk_order_b_vbdef8("pk_order_b.vbdef8"),
  /** 表体自定义项9 */
  pk_order_b_vbdef9("pk_order_b.vbdef9"),
  /** 自由辅助属性1 */
  pk_order_b_vfree1("pk_order_b.vfree1"),
  /** 自由辅助属性2 */
  pk_order_b_vfree2("pk_order_b.vfree2"),
  /** 自由辅助属性3 */
  pk_order_b_vfree3("pk_order_b.vfree3"),
  /** 自由辅助属性4 */
  pk_order_b_vfree4("pk_order_b.vfree4"),
  /** 自由辅助属性5 */
  pk_order_b_vfree5("pk_order_b.vfree5"),
  /** 采购组织 */
  pk_org("pk_org"),
  /** 供应商地区分类 */
  supplierareacl("pk_supplier.pk_areacl"),
  /** 供应商编码 */
  suppliercode("pk_supplier.code"),
  /** 供应商名称 */
  suppliername("pk_supplier.name"),
  /** 订单编号 */
  vbillcode("vbillcode"),
  /** 自定义项1 */
  vdef1("vdef1"),
  /** 自定义项10 */
  vdef10("vdef10"),
  /** 自定义项11 */
  vdef11("vdef11"),
  /** 自定义项12 */
  vdef12("vdef12"),
  /** 自定义项13 */
  vdef13("vdef13"),
  /** 自定义项14 */
  vdef14("vdef14"),
  /** 自定义项15 */
  vdef15("vdef15"),
  /** 自定义项16 */
  vdef16("vdef16"),
  /** 自定义项17 */
  vdef17("vdef17"),
  /** 自定义项18 */
  vdef18("vdef18"),
  /** 自定义项19 */
  vdef19("vdef19"),
  /** 自定义项2 */
  vdef2("vdef2"),
  /** 自定义项20 */
  vdef20("vdef20"),
  /** 自定义项3 */
  vdef3("vdef3"),
  /** 自定义项4 */
  vdef4("vdef4"),
  /** 自定义项5 */
  vdef5("vdef5"),
  /** 自定义项6 */
  vdef6("vdef6"),
  /** 自定义项7 */
  vdef7("vdef7"),
  /** 自定义项8 */
  vdef8("vdef8"),
  /** 自定义项9 */
  vdef9("vdef9");

  private String code = null;

  private OrderSummaryQryFieldCode(String code) {
    this.code = code;
  }

  public String getFieldCode() {
    return this.code;
  }
}
