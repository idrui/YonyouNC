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
   * 父类方法重写
   * 
   * @see nc.uap.pf.metadata.FlowBizImpl#setApproveStatus(java.lang.Integer)
   *      0自由(缺省) 1未用 2审批中 3审批通过 4审批未通过 5输出 7执行完毕
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
