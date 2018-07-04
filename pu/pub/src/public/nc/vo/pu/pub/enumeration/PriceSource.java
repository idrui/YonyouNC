/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-19 上午10:03:31
 */
package nc.vo.pu.pub.enumeration;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购价格来源的枚举
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-19 上午10:03:31
 */
public enum PriceSource {
  /** 无默认 **/
  NoDefault(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0064")/*@res "无默认"*/),
  /** 订单最低价 **/
  OrderMinPrice(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0065")/*@res "订单最低价"*/),
  /** 订单最新价 **/
  OrderNewestPrice(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0066")/*@res "订单最新价"*/),
  /** 订单价 **/
  OrderPice(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0067")/*@res "订单价"*/),
  /** 计划价 **/
  PlanPrice(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0003465")/*@res "计划价"*/),
  /** 请购单价 **/
  PrayPrice(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0068")/*@res "请购单单价"*/),
  /** 入库单价 **/
  PurchaseInPrice(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0000370")/*@res "入库单价"*/),
  /** 参考成本 **/
  RefCostPrice(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0000958")/*@res "参考成本"*/),
  /** 最新结算价 **/
  SettleNewestPrice(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0069")/*@res "最新结算价"*/),
  /** 供应商价目表 **/
  SupplierPrice(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0070")/*@res "供应商价目表"*/);

  private String code = null;

  private PriceSource(String code) {
    this.code = code;
  }

  public String code() {
    return this.code;
  }
}