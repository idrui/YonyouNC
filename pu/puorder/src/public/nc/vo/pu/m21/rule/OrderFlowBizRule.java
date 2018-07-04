package nc.vo.pu.m21.rule;

import nc.md.data.access.NCObject;
import nc.uap.pf.metadata.FlowBizImpl;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.IPfRetCheckInfo;

public class OrderFlowBizRule extends FlowBizImpl {

  public OrderFlowBizRule(NCObject ncobject) {
    super(ncobject);
  }

  /**
   * ���෽����д
   * 
   * @see nc.uap.pf.metadata.FlowBizImpl#setApproveStatus(java.lang.Integer)
   *      0����(ȱʡ) 1δ�� 2������ 3����ͨ�� 4����δͨ�� 5��� 7ִ�����
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
