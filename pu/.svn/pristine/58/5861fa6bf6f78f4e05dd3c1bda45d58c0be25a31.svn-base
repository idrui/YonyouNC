package nc.vo.pu.pub.rule;

import nc.itf.uap.pf.metadata.IFlowBizItf;
import nc.md.data.access.NCObject;
import nc.uap.pf.metadata.FlowBizImpl;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.pf.IPfRetCheckInfo;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>IFlowBizItf接口实现类, 在元数据建模时，注册到原模型上。本类主要完成以下功能：</b>
 * <ul>
 * <li>将审批流的单据状态转换为采购单据的单据状态
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-9-9 下午01:46:12
 */
public class POFlowBizRule extends FlowBizImpl {
  /**
   * PrayFlowBizImpl 的构造子
   * 
   * @param ncobject
   */
  public POFlowBizRule(NCObject ncobject) {
    super(ncobject);
  }

  @Override
  public UFDateTime getApproveDate() {
    Object resObj = this.getAttributeValue(IFlowBizItf.ATTRIBUTE_APPROVEDATE);
    if (resObj instanceof UFDate) {
      return new UFDateTime(((UFDate) resObj).getMillis());
    }
    else if (resObj instanceof UFDateTime) {
      return (UFDateTime) resObj;
    }
    return null;
  }

  @Override
  public Integer getApproveStatus() {
    Object resObj = this.getAttributeValue(IFlowBizItf.ATTRIBUTE_APPROVESTATUS);
    Integer result = null;
    if (resObj instanceof Integer) {
      if (POEnumBillStatus.FREE.toInteger().equals(resObj)) {
        result = Integer.valueOf(IPfRetCheckInfo.NOSTATE);
      }
      else if (POEnumBillStatus.APPROVING.toInteger().equals(resObj)) {
        result = Integer.valueOf(IPfRetCheckInfo.GOINGON);
      }
      else if (POEnumBillStatus.APPROVE.toInteger().equals(resObj)) {
        result = Integer.valueOf(IPfRetCheckInfo.PASSING);
      }
      else if (POEnumBillStatus.NOPASS.value().equals(resObj)) {
        result = Integer.valueOf(IPfRetCheckInfo.NOPASS);
      }
      return result;
    }
    return super.getApproveStatus();
  }

  @Override
  public void setApproveDate(UFDateTime approveDate) {
    UFDate appDate = null != approveDate ? approveDate.getDate() : null;
    this.setAttributeValue(IFlowBizItf.ATTRIBUTE_APPROVEDATE, appDate);
  }

  @Override
  public void setApproveStatus(Integer icheckState) {
    int status = icheckState.intValue();
    // 将审批流的单据状态转换为采购单据的单据状态
    switch (status) {
      case IPfRetCheckInfo.NOSTATE:
        status = POEnumBillStatus.FREE.toInt();
        break;
      case IPfRetCheckInfo.COMMIT:
        status = POEnumBillStatus.APPROVING.toInt();
        break;
      case IPfRetCheckInfo.PASSING:
        status = POEnumBillStatus.APPROVE.toInt();
        break;
      case IPfRetCheckInfo.NOPASS:
        status = POEnumBillStatus.NOPASS.toInt();
        break;
      case IPfRetCheckInfo.GOINGON:
        status = POEnumBillStatus.APPROVING.toInt();
        break;
      default:
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004000_0", "04004000-0087")/*
                                                                     * @res
                                                                     * "将审批平台状态转为单据状态时出错。"
                                                                     */);
    }
    if (this.ncobject.getVOStatus() == VOStatus.UNCHANGED) {
      this.ncobject.setVOStatus(VOStatus.UPDATED);
    }
    super.setApproveStatus(Integer.valueOf(status));
  }
}
