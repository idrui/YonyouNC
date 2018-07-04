package nc.vo.pu.m23.rule;

import nc.md.data.access.NCObject;
import nc.uap.pf.metadata.FlowBizImpl;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.pf.IPfRetCheckInfo;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>IFlowBizItf�ӿ�ʵ����, ��Ԫ���ݽ�ģʱ��ע�ᵽԭģ���ϡ�������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���������ĵ���״̬ת��Ϊ�������ĵ���״̬
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-20 ����04:16:19
 */
public class ArriveFlowBizImpl extends FlowBizImpl {

  public ArriveFlowBizImpl(
      NCObject ncobject) {
    super(ncobject);
  }

  @Override
  public void setApproveStatus(Integer icheckState) {
    Integer state = icheckState;
    // ���������ĵ���״̬ת��Ϊ�������ĵ���״̬
    switch (state.intValue()) {
      case IPfRetCheckInfo.NOSTATE:
        state = POEnumBillStatus.FREE.toInteger();
        break;
      case IPfRetCheckInfo.COMMIT:
        state = POEnumBillStatus.APPROVING.toInteger();
        break;
      case IPfRetCheckInfo.PASSING:
        state = POEnumBillStatus.APPROVE.toInteger();
        break;
      case IPfRetCheckInfo.NOPASS:
        state = POEnumBillStatus.NOPASS.toInteger();
        break;
      case IPfRetCheckInfo.GOINGON:
        state = POEnumBillStatus.APPROVING.toInteger();
        break;
      default:
        String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0146")/*@res "������ƽ̨״̬תΪ����״̬ʱ����"*/;
        ExceptionUtils.wrappBusinessException(message);
    }
    super.setApproveStatus(state);
  }
}