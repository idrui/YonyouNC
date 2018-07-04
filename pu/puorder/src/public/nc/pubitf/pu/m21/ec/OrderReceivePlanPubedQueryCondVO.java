package nc.pubitf.pu.m21.ec;

import nc.vo.pu.ec.BasePUQueryECCondVO;
import nc.vo.pubapp.query2.sql.process.QueryCondition;

/**
 * @since 6.0
 * @version 2011-5-16 ����01:28:00
 * @author wuxla
 */

public class OrderReceivePlanPubedQueryCondVO extends BasePUQueryECCondVO {
  private static final long serialVersionUID = -8864057189917269713L;

  // �ջ���֯name
  private QueryCondition arrvstoorgCond;

  // �ɹ�����name
  private QueryCondition deptCond;

  private QueryCondition orderCodeCond;

  // �ƻ���������
  private QueryCondition planArrDateCond;

  // ���������֯name
  private QueryCondition psfinanceorgCond;

  // �ջ���ַname
  private QueryCondition receiveaddressCond;

  // �ջ��ֿ�name
  private QueryCondition recvstordocCond;

  // ������name
  private QueryCondition reqdeptCond;

  // ��������
  private QueryCondition sendDate;

  /**
   * �ջ���֯name
   * 
   * @return
   */
  public QueryCondition getArrvstoorgCond() {
    return this.arrvstoorgCond;
  }

  /**
   * �ɹ�����name
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
   * ���������֯name
   * 
   * @return
   */
  public QueryCondition getPsfinanceorgCond() {
    return this.psfinanceorgCond;
  }

  /**
   * �ջ���ַname
   * 
   * @return
   */
  public QueryCondition getReceiveaddressCond() {
    return this.receiveaddressCond;
  }

  /**
   * �ջ��ֿ�name
   * 
   * @return
   */
  public QueryCondition getRecvstordocCond() {
    return this.recvstordocCond;
  }

  /**
   * ������name
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
   * �ջ���֯name
   * 
   * @return
   */
  public void setArrvstoorgCond(QueryCondition arrvstoorgCond) {
    this.arrvstoorgCond = arrvstoorgCond;
  }

  /**
   * �ɹ�����name
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
   * ���������֯name
   * 
   * @return
   */
  public void setPsfinanceorgCond(QueryCondition psfinanceorgCond) {
    this.psfinanceorgCond = psfinanceorgCond;
  }

  /**
   * �ջ���ַname
   * 
   * @return
   */
  public void setReceiveaddressCond(QueryCondition receiveaddressCond) {
    this.receiveaddressCond = receiveaddressCond;
  }

  /**
   * �ջ��ֿ�name
   * 
   * @return
   */
  public void setRecvstordocCond(QueryCondition recvstordocCond) {
    this.recvstordocCond = recvstordocCond;
  }

  /**
   * ������name
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
