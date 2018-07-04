/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-20 下午09:15:37
 */
package nc.vo.pu.m422x.rule;

import nc.md.data.access.NCObject;
import nc.uap.pf.metadata.FlowBizImpl;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.IPfRetCheckInfo;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-20 下午09:15:37
 */
public class StoreReqAppFlowBizRule extends FlowBizImpl {

  /**
   * StoreReqAppFlowBizRule 的构造子
   * 
   * @param ncobject
   */
  public StoreReqAppFlowBizRule(NCObject ncobject) {
    super(ncobject);
  }

  /**
   * 父类方法重写
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
