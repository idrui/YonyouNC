package nc.vo.pu.pub.enumeration;

/**
 * PO16采购订单自动询价条件
 * 供应商、物料、币种、单据日期、报价单位、结算财务组织、生产厂商、质量等级、收货地区、运输方式
 * 默认值 供应商、物料
 * Supplier,Material,Currency,BillDate,Qtunit,Psfinanceorg,Productor,
 * Qualitylevel,ReceiveArea,Vehicletype
 * 
 * @since 6.5
 * @version 2014-1-14 下午01:51:35
 * @author mengjian
 */
public enum PriceParam {
  /** 单据日期 **/
  BillDate(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
      "04004000-0146")/* @res "单据日期" */),
  /** 币种 **/
  Currency(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
      "04004000-0145")/* @res "币种" */),
  /** 物料 **/
  Material(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
      "04004000-0144")/* @res "物料" */),
  /** 生产厂商 **/
  Productor(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
      "04004000-0149")/* @res "生产厂商" */),
  /** 结算财务组织 **/
  Psfinanceorg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0148")/* @res "结算财务组织" */),
  /** 报价单位 **/
  Qtunit(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
      "04004000-0147")/* @res "报价单位" */),
  /** 质量等级 **/
  Qualitylevel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0150")/* @res "质量等级" */),
  /** 收货地区 **/
  ReceiveArea(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0151")/* @res "收货地区" */),
  /** 供应商 **/
  Supplier(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
      "04004000-0143")/* @res "供应商" */),
  /** 运输方式 **/
  Vehicletype(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0152")/* @res "运输方式" */);

  private String code = null;

  private PriceParam(String code) {
    this.code = code;
  }

  public String code() {
    return this.code;
  }
}
