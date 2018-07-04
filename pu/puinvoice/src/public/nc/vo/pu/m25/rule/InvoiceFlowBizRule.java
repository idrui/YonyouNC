/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-8-4 ����04:41:10
 */
package nc.vo.pu.m25.rule;

import nc.md.data.access.NCObject;
import nc.uap.pf.metadata.FlowBizImpl;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.pf.IPfRetCheckInfo;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhaoyha
 * @time 2009-8-4 ����04:41:10
 */
public class InvoiceFlowBizRule extends FlowBizImpl {
  /**
   * InvoiceFlowBizRule �Ĺ�����
   * 
   * @param ncobject
   */
  public InvoiceFlowBizRule(NCObject ncobject) {
    super(ncobject);
  }

  /**
   * ���෽����д
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
