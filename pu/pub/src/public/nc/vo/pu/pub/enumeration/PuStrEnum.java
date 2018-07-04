package nc.vo.pu.pub.enumeration;

/**
 * 采购string型常量枚举
 * 
 * @since 6.1
 * @version 2011-12-13 上午8:45:51
 * @author yangtian
 */
public enum PuStrEnum {
  /** 订单价格预警，生效时间 **/
  poprc_altt_effibegintime("20:00:00"),
  /** 订单价格预警， 无效价格计算预警默认开始时间 **/
  poprc_daily_altexectm("21:00:00"),
  /** 订单价格预警， 无效价格计算预警类型pk **/
  poprc_daily_alttypepk("0001Z810000000001145"),
  /** 订单价格预警，价格提取预警默认开始时间 **/
  poprc_monthly_altexectm("22:00:00"),
  /** 订单价格预警，价格提取预警类型pk **/
  poprc_monthly_alttypepk("0001Z810000000001147"),
  /** 订单价格预警，价格提取，执行日期 **/
  poprc_monthly_execday("02"),
  /** 不支持交易类型的单据VO，取交易类型的空属性名 **/
  vo_att_notranstype("po_transtype_no_support");

  private String code;

  private PuStrEnum(String code) {
    this.code = code;
  }

  public String code() {
    return this.code;
  }
}
