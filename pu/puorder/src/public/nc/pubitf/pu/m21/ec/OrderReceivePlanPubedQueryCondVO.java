package nc.pubitf.pu.m21.ec;

import nc.vo.pu.ec.BasePUQueryECCondVO;
import nc.vo.pubapp.query2.sql.process.QueryCondition;

/**
 * @since 6.0
 * @version 2011-5-16 下午01:28:00
 * @author wuxla
 */

public class OrderReceivePlanPubedQueryCondVO extends BasePUQueryECCondVO {
  private static final long serialVersionUID = -8864057189917269713L;

  // 收货组织name
  private QueryCondition arrvstoorgCond;

  // 采购部门name
  private QueryCondition deptCond;

  private QueryCondition orderCodeCond;

  // 计划到货日期
  private QueryCondition planArrDateCond;

  // 结算财务组织name
  private QueryCondition psfinanceorgCond;

  // 收货地址name
  private QueryCondition receiveaddressCond;

  // 收货仓库name
  private QueryCondition recvstordocCond;

  // 需求部门name
  private QueryCondition reqdeptCond;

  // 发货日期
  private QueryCondition sendDate;

  /**
   * 收货组织name
   * 
   * @return
   */
  public QueryCondition getArrvstoorgCond() {
    return this.arrvstoorgCond;
  }

  /**
   * 采购部门name
   * 
   * @return
   */
  public QueryCondition getDeptCond() {
    return this.deptCond;
  }

  public QueryCondition getOrderCodeCond() {
    return this.orderCodeCond;
  }

  public QueryCondition getPlanArrDateCond() {
    return this.planArrDateCond;
  }

  /**
   * 结算财务组织name
   * 
   * @return
   */
  public QueryCondition getPsfinanceorgCond() {
    return this.psfinanceorgCond;
  }

  /**
   * 收货地址name
   * 
   * @return
   */
  public QueryCondition getReceiveaddressCond() {
    return this.receiveaddressCond;
  }

  /**
   * 收货仓库name
   * 
   * @return
   */
  public QueryCondition getRecvstordocCond() {
    return this.recvstordocCond;
  }

  /**
   * 需求部门name
   * 
   * @return
   */
  public QueryCondition getReqdeptCond() {
    return this.reqdeptCond;
  }

  public QueryCondition getSendDate() {
    return this.sendDate;
  }

  /**
   * 收货组织name
   * 
   * @return
   */
  public void setArrvstoorgCond(QueryCondition arrvstoorgCond) {
    this.arrvstoorgCond = arrvstoorgCond;
  }

  /**
   * 采购部门name
   * 
   * @return
   */
  public void setDeptCond(QueryCondition deptCond) {
    this.deptCond = deptCond;
  }

  public void setOrderCodeCond(QueryCondition orderCodeCond) {
    this.orderCodeCond = orderCodeCond;
  }

  public void setPlanArrDateCond(QueryCondition planArrDateCond) {
    this.planArrDateCond = planArrDateCond;
  }

  /**
   * 结算财务组织name
   * 
   * @return
   */
  public void setPsfinanceorgCond(QueryCondition psfinanceorgCond) {
    this.psfinanceorgCond = psfinanceorgCond;
  }

  /**
   * 收货地址name
   * 
   * @return
   */
  public void setReceiveaddressCond(QueryCondition receiveaddressCond) {
    this.receiveaddressCond = receiveaddressCond;
  }

  /**
   * 收货仓库name
   * 
   * @return
   */
  public void setRecvstordocCond(QueryCondition recvstordocCond) {
    this.recvstordocCond = recvstordocCond;
  }

  /**
   * 需求部门name
   * 
   * @return
   */
  public void setReqdeptCond(QueryCondition reqdeptCond) {
    this.reqdeptCond = reqdeptCond;
  }

  public void setSendDate(QueryCondition sendDate) {
    this.sendDate = sendDate;
  }
}
