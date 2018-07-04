package nc.vo.pu.m23.rule;

import nc.md.data.access.NCObject;
import nc.uap.pf.metadata.FlowBizImpl;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.pf.IPfRetCheckInfo;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>IFlowBizItf接口实现类, 在元数据建模时，注册到原模型上。本类主要完成以下功能：</b>
 * <ul>
 * <li>将审批流的单据状态转换为到货单的单据状态
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-20 下午04:16:19
 */
public class ArriveFlowBizImpl extends FlowBizImpl {

  public ArriveFlowBizImpl(
      NCObject ncobject) {
    super(ncobject);
  }

  @Override
  public void setApproveStatus(Integer icheckState) {
    Integer state = icheckState;
    // 将审批流的单据状态转换为到货单的单据状态
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
        String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0146")/*@res "将审批平台状态转为单据状态时出错。"*/;
        ExceptionUtils.wrappBusinessException(message);
    }
    super.setApproveStatus(state);
  }
}