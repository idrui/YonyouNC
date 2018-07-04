package nc.vo.pu.report.enumeration;

/**
 * 采购在途查询字段枚举
 * 
 * @since 6.0
 * @version 2012-9-18 上午10:53:28
 * @author lixyp
 */
public enum OrderOnwayQueryFieldCode {

  /** 供应商地区分类 */
  AREACL_NAME("bd_areacl.name"),

  /** 物料基本分类 */
  MARBASCLASS_CODE("bd_marbasclass.code"),

  /** 物料采购分类 */
  MARPUCLASS_CODE("bd_marpuclass.code"),

  /** 物料编码 */
  MATERIAL_CODE("bd_material_v.code"),

  /** 物料名称 */
  MATERIAL_NAME("bd_material_v.name"),

  /** 供应商编码 */
  SUPPLIER_CODE("bd_supplier.code"),

  /** 供应商名称 */
  SUPPLIER_NAME("bd_supplier.name");

  private String code = null;

  private OrderOnwayQueryFieldCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return this.code;
  }
}
