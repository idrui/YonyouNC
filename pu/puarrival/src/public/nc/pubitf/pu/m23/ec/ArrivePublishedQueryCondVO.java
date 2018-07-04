package nc.pubitf.pu.m23.ec;

import nc.vo.pu.ec.BasePUQueryECCondVO;
import nc.vo.pubapp.query2.sql.process.QueryCondition;

/**
 * @since 6.0
 * @version 2011-5-16 ����01:14:19
 * @author wuxla
 */

public class ArrivePublishedQueryCondVO extends BasePUQueryECCondVO {

  private static final long serialVersionUID = -2277286570101207186L;

  // ��������
  private QueryCondition arriveCodeCond;

  // �Ƿ��˻�
  private QueryCondition backCond;

  // �ɹ�����
  private QueryCondition deptCond;

  // ������
  private QueryCondition orderCodeCond;

  // �ջ���
  private QueryCondition recPsnCond;

  public QueryCondition getArriveCodeCond() {
    return this.arriveCodeCond;
  }

  public QueryCondition getBackCond() {
    return this.backCond;
  }

  public QueryCondition getDeptCond() {
    return this.deptCond;
  }

  public QueryCondition getOrderCodeCond() {
    return this.orderCodeCond;
  }

  public QueryCondition getRecPsnCond() {
    return this.recPsnCond;
  }

  public void setArriveCodeCond(QueryCondition arriveCodeCond) {
    this.arriveCodeCond = arriveCodeCond;
  }

  public void setBackCond(QueryCondition backCond) {
    this.backCond = backCond;
  }

  public void setDeptCond(QueryCondition deptCond) {
    this.deptCond = deptCond;
  }

  public void setOrderCodeCond(QueryCondition orderCodeCond) {
    this.orderCodeCond = orderCodeCond;
  }

  public void setRecPsnCond(QueryCondition recPsnCond) {
    this.recPsnCond = recPsnCond;
  }

}
