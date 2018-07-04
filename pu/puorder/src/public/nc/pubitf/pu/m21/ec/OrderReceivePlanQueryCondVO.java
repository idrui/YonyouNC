package nc.pubitf.pu.m21.ec;

import nc.vo.pu.ec.BasePUQueryECCondVO;
import nc.vo.pubapp.query2.sql.process.QueryCondition;

/**
 * @since 6.0
 * @version 2011-5-16 ����01:06:50
 * @author wuxla
 */

public class OrderReceivePlanQueryCondVO extends BasePUQueryECCondVO {

  private static final long serialVersionUID = -1123287975389488906L;

  // ��Ӧ�������������š���Ʒ���ơ���������

  // �ջ���֯name
  private QueryCondition arrvstoorgCond;

  // �ɹ�����name
  private QueryCondition deptCond;

  private QueryCondition orderCodeCond;

  // ���������֯name
  private QueryCondition psfinanceorgCond;

  // �ջ���ַname
  private QueryCondition receiveaddressCond;

  // �ջ��ֿ�name
  private QueryCondition recvstordocCond;

  // ������name
  private QueryCondition reqdeptCond;

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
}
