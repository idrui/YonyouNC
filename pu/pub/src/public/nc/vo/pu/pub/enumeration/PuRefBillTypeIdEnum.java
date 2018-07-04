package nc.vo.pu.pub.enumeration;

/**
 * 采购引用的单据类型pk常量类
 * 场景：外领域单据类型编码和主键不一致的情况下，主要用于存放外领域单据类型pk常量
 * 
 * @since 6.5
 * @version 2014-3-17 下午04:38:02
 * @author fanly3
 */
public enum PuRefBillTypeIdEnum {
  /**
   * 资产配置申请
   */
  M4A08("1001Z91000000001U0LZ"),
  /**
   * 维修计划
   */
  M4B32("1001Z900000000002213");

  // 单据类型编码对应的pk
  private String billTypeId;

  private PuRefBillTypeIdEnum(String billTypeId) {
    this.billTypeId = billTypeId;
  }

  public String getBillTypeId() {
    return this.billTypeId;
  }

}
