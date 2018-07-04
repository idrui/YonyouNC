package nc.pubitf.pu.m21.ec;

import nc.vo.pu.ec.BasePUQueryECCondVO;
import nc.vo.pubapp.query2.sql.process.QueryCondition;

/**
 * 输入：供应商主键、订单号、采购组织、产品名称、确认状态、订单日期、确认日期
 * 
 * @since 6.0
 * @version 2011-5-16 上午11:20:46
 * @author wuxla
 */

public class OrderApprovedQueryCondVO extends BasePUQueryECCondVO {
  private static final long serialVersionUID = -1845897756270030274L;

  // 确认日期
  private QueryCondition confirmDateCond;

  // 订单号
  private QueryCondition orderCodeCond;

  // 确认状态
  private QueryCondition statusCond;

  public QueryCondition getConfirmDateCond() {
    return this.confirmDateCond;
  }

  public QueryCondition getOrderCodeCond() {
    return this.orderCodeCond;
  }

  public QueryCondition getStatusCond() {
    return this.statusCond;
  }

  public void setConfirmDateCond(QueryCondition confirmDateCond) {
    this.confirmDateCond = confirmDateCond;
  }

  public void setOrderCodeCond(QueryCondition orderCodeCond) {
    this.orderCodeCond = orderCodeCond;
  }

  public void setStatusCond(QueryCondition statusCond) {
    this.statusCond = statusCond;
  }

}
