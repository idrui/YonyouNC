/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-8-4 下午04:41:10
 */
package nc.vo.pu.m25.rule;

import nc.md.data.access.NCObject;
import nc.uap.pf.metadata.FlowBizImpl;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.pf.IPfRetCheckInfo;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author zhaoyha
 * @time 2009-8-4 下午04:41:10
 */
public class InvoiceFlowBizRule extends FlowBizImpl {
  /**
   * InvoiceFlowBizRule 的构造子
   * 
   * @param ncobject
   */
  public InvoiceFlowBizRule(NCObject ncobject) {
    super(ncobject);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.uap.pf.metadata.FlowBizImpl#setApproveStatus(java.lang.Integer)
   */
  @Override
  public void setApproveStatus(Integer icheckState) {
    int checkState = icheckState.intValue();
    switch (checkState) {
      case IPfRetCheckInfo.GOINGON:
      case IPfRetCheckInfo.COMMIT:
        checkState = POEnumBillStatus.APPROVING.toInt();
        break;
      case IPfRetCheckInfo.PASSING:
        checkState = POEnumBillStatus.APPROVE.toInt();
        break;
      case IPfRetCheckInfo.NOPASS:
        checkState = POEnumBillStatus.NOPASS.toInt();
        break;
      default:
        checkState = POEnumBillStatus.FREE.toInt();
        break;
    }
    super.setApproveStatus(Integer.valueOf(checkState));
  }

}
