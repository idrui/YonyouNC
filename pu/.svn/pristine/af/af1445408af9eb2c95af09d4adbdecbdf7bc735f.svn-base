/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-7 上午08:51:06
 */
package nc.vo.pu.m4t.rule;

import nc.itf.uap.pf.metadata.IFlowBizItf;
import nc.md.data.access.NCObject;
import nc.uap.pf.metadata.FlowBizImpl;
import nc.vo.pu.m4t.enumeration.InitialEstStatus;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.pf.IPfRetCheckInfo;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>流程平台业务接口实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-7 上午08:51:06
 */
public class InitialEstFlowBizRule extends FlowBizImpl {

  /**
   * InitialEstFlowBizRule 的构造子
   * 
   * @param ncobject
   */
  public InitialEstFlowBizRule(NCObject ncobject) {
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
  public void setApproveDate(UFDateTime approveDate) {
    UFDate appDate = null != approveDate ? approveDate.getDate() : null;
    this.setAttributeValue(IFlowBizItf.ATTRIBUTE_APPROVEDATE, appDate);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.uap.pf.metadata.FlowBizImpl#setApproveStatus(java.lang.Integer)
   */
  @Override
  public void setApproveStatus(Integer icheckState) {
    int status = icheckState.intValue();
    switch (status) {
      case IPfRetCheckInfo.PASSING:
        status =
            Integer.parseInt(InitialEstStatus.APPROVED.getEnumValue()
                .getValue());
        break;
      default:
        status = 0;
        break;
    }
    if (this.ncobject.getVOStatus() == VOStatus.UNCHANGED) {
      this.ncobject.setVOStatus(VOStatus.UPDATED);
    }
    super.setApproveStatus(Integer.valueOf(status));
  }

}
