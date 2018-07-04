/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-20 ����09:15:37
 */
package nc.vo.pu.m422x.rule;

import nc.md.data.access.NCObject;
import nc.uap.pf.metadata.FlowBizImpl;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.IPfRetCheckInfo;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-20 ����09:15:37
 */
public class StoreReqAppFlowBizRule extends FlowBizImpl {

  /**
   * StoreReqAppFlowBizRule �Ĺ�����
   * 
   * @param ncobject
   */
  public StoreReqAppFlowBizRule(NCObject ncobject) {
    super(ncobject);
  }

  /**
   * ���෽����д
   * 
   * @see nc.uap.pf.metadata.FlowBizImpl#setApproveStatus(java.lang.Integer)
   */
  @Override
  public void setApproveStatus(Integer icheckState) {
    int status = icheckState.intValue();
    Integer orderStatus;
    switch (status) {
      case IPfRetCheckInfo.GOINGON:
        orderStatus = POEnumBillStatus.APPROVING.toInteger();
        break;
      case IPfRetCheckInfo.COMMIT:
        orderStatus = POEnumBillStatus.APPROVING.toInteger();
        break;
      case IPfRetCheckInfo.PASSING:
        orderStatus = POEnumBillStatus.APPROVE.toInteger();
        break;
      case IPfRetCheckInfo.NOPASS:
        orderStatus = POEnumBillStatus.NOPASS.toInteger();
        break;
      default:
        orderStatus = POEnumBillStatus.FREE.toInteger();
        break;
    }
    if (this.ncobject.getVOStatus() == VOStatus.UNCHANGED) {
      this.ncobject.setVOStatus(VOStatus.UPDATED);
    }
    super.setApproveStatus(orderStatus);
  }
}
