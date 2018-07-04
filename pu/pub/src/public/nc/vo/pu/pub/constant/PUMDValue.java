package nc.vo.pu.pub.constant;

/**
 * 元数据常量
 * 
 * @since 6.0
 * @version 2011-2-28 下午05:30:42
 * @author wuxla
 */

public enum PUMDValue {

  /** 到货单 */
  Arrival("8fbe8162-4588-465e-9b07-78befaf383ce"),

  /** 采购订单 */
  Order("8a2b1c3f-0185-40f6-b4a1-eb9d1733ebcf"),

  /** 请购单 */
  PrayBill("faa17d57-f79a-49df-9958-b98c1ef4a976"),

  /** 请购单单据状态枚举元数据id */
  PrayBillStatus("ff16f639-dc98-467a-a376-9baa8407cca7"),

  /** 采购订单单据状态枚举元数据id */
  OrderStatus("705fb2e1-f3b8-48f1-8cb1-df9a3588b7bf"),

  /** 物资需求申请单 */
  StoreReqApp("f8025fb7-1aa5-4de8-9c64-95e1cad22f6a"),

  /** 采购订单到货计划 */
  ReceivePlan("51d94a40-72b9-49ee-a709-0a61b2b3c98d");
  private String value;

  private PUMDValue(String value) {
    this.value = value;
  }

  public String value() {
    return this.value;
  }
}
