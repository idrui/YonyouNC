package nc.vo.pu.pub.constant;

/**
 * 采购参数值的常量类
 * 
 * @since 6.0
 * @version 2011-7-2 下午01:13:26
 * @author zhaoyha
 */
public class PUParaValue {
  public enum ctrltype {
    /** 提示 **/
    ask,
    /** 冻结 **/
    freeze,
    /** 不控制 **/
    not_control,
    /** 不保存 **/
    not_save
  }

  public enum po09 {
    /** 即时 **/
    instance,
    /** 定时 **/
    timing
  }

  public enum po12 {
    /** 单到补差 **/
    mend,
    /** 单到回冲 **/
    rush
  }

  public enum po13 {
    /** 成本 **/
    cost,
    /** 损益 **/
    profit_loss
  }

  public enum po38 {
    /** 最新方案和标准 **/
    latest_schm_std,
    /** 匹配有效期 **/
    match_validdate
  }

  public enum po44 {
    /** 到货单 **/
    arrival,
    /** 到货计划 **/
    arrived_plan,
    /** 无单据 **/
    no_bill,
    /** 采购订单 **/
    order
  }

  public enum po46 {
    /** 审核时自动结算 **/
    approve_settle,
    /** 不自动结算 **/
    not_settle
  }

  public enum po84 {
    /** 调整折扣 **/
    adjust_discount,
    /** 调整单价 **/
    adjust_price
  }

  public enum po85 {
    /** 物料基本分类 **/
    base_marclass,
    /** 物料采购分类 **/
    pu_marclass,
  }

}
