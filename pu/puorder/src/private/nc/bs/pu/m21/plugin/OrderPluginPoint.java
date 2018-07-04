package nc.bs.pu.m21.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单扩展插入点
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-8-30 下午04:41:31
 */
public enum OrderPluginPoint implements IPluginPoint {
  /**
   * 审批
   */
  APPROVE,

  /**
   * 到货关闭
   */
  ARRIVE_CLOSE,

  /**
   * 到货打开
   */
  ARRIVE_OPEN,

  /**
   * 行自动打开
   */
  AUTO_ROW_OPEN,

  /**
   * 自动入库关闭
   */
  AUTO_STORE_CLOSE,

  /**
   * 退货单转单查询
   */
  BACK23,

  /**
   * 退库单转单查询
   */
  BACK45,

  /**
   * 删除
   */
  DELETE,

  /**
   * 最终关闭
   */
  FINAL_CLOSE,

  /**
   * 最终打开
   */
  FINAL_OPEN,

  /**
   * 冻结
   */
  FREEZE,

  /**
   * 新增保存
   */
  INSERT,

  /**
   * 发票关闭
   */
  INVOICE_CLOSE,

  /**
   * 发票打开
   */
  INVOICE_OPEN,

  /**
   * 付款关闭
   */
  PAY_CLOSE,

  /**
   * 付款打开
   */
  PAY_OPEN,

  /**
   * 付款计划取消付款申请
   */
  PAYPLAN_CANCELPAYREQ,

  /**
   * 付款计划删除
   */
  PAYPLAN_DELETE_BP,

  /**
   * 付款计划新增
   */
  PAYPLAN_INSERT_BP,

  /**
   * 付款计划到付款申请
   */
  PAYPLAN_PAYREQ,

  /**
   * 付款计划查询
   */
  PAYPLAN_QUERY,

  /**
   * 付款计划修改
   */
  PAYPLAN_UPDATE,

  /**
   * 付款计划修改BP
   */
  PAYPLAN_UPDATE_BP,

  /**
   * 到货单转单查询
   */
  PULL23,

  /**
   * 采购发票转单查询
   */
  PULL25,

  /**
   * 入库单转单查询
   */
  PULL45,

  /**
   * 运输单
   */
  PULL4804,

  /**
   * 期初暂估单转单查询
   */
  PULL4T,

  /**
   * 销售出库单
   */
  PUSH4C,

  /**
   * 协同销售订单
   */
  PUSHCOOP30,

  /**
   * 出口明细单拣配查询订单
   */
  Query_ET,

  /**
   * 采购入库单回写到货计划
   */
  RECEIVE_PLAN_WRITEBACK_45,

  /**
   * 运输单回写到货计划
   */
  RECEIVE_PLAN_WRITEBACK_4804,

  /**
   * 修订
   */
  REVISE,

  /**
   * 行关闭
   */
  ROW_CLOSE,

  /**
   * 行打开
   */
  ROW_OPEN,

  /**
   * 送审
   */
  SEND_APPROVE,

  /**
   * 入库关闭
   */
  STORE_CLOSE,

  /**
   * 入库打开
   */
  STORE_OPEN,

  /**
   * 取消审核
   */
  UNAPPROVE,

  /**
   * 解冻
   */
  UNFREEZE,

  /**
   * 收回
   */
  UNSAVE,

  /**
   * 修改保存
   */
  UPDATE,
  /**
   * 付款申请单回写
   */
  WRITEBACK_36DA,

  /**
   * 采购入库单回写订单
   */
  WRITEBACK_45,

  /**
   * 运输单回写订单
   */
  WRITEBACK_4804,
  /**
   * 协同回写
   */
  WRITEBACK_COOP30,

  /**
   * 出口明细单回写订单
   */
  WRITEBACK_ET,

  /**
   * 核销回写
   */
  WRITEBACK_F1,
  /**
   * 付款单回写
   */
  WRITEBACK_F3;

  @Override
  public String getComponent() {
    return POBillType.Order.getCode();
  }

  @Override
  public String getModule() {
    return NCModule.PO.getSystemCode();
  }

  @Override
  public String getPoint() {
    return this.name();
  }

}
