package nc.pubitf.pu.m21.ec;

import nc.vo.pu.ec.BasePUQueryECCondVO;
import nc.vo.pubapp.query2.sql.process.QueryCondition;

/**
 * ���룺��Ӧ�������������š��ɹ���֯����Ʒ���ơ�ȷ��״̬���������ڡ�ȷ������
 * 
 * @since 6.0
 * @version 2011-5-16 ����11:20:46
 * @author wuxla
 */

public class OrderApprovedQueryCondVO extends BasePUQueryECCondVO {
  private static final long serialVersionUID = -1845897756270030274L;

  // ȷ������
  private QueryCondition confirmDateCond;

  // ������
  private QueryCondition orderCodeCond;

  // ȷ��״̬
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
