/**
 *
 */
package nc.vo.pu.m25.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description
 * 发票状态检查,必须是审批中态
 * @scene
 * 收回
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:52:48
 * @author zhangshqb
 */
public class UnSendApproveStatusChkRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    StringBuffer buffer = new StringBuffer();
    for (InvoiceVO vo : vos) {
      buffer.append(this.checkHeaderVO(vo.getParentVO()));
    }
    if (buffer.length() > 0) {
      ExceptionUtils.wrappBusinessException(buffer.toString());
    }
  }

  private StringBuffer checkHeaderVO(InvoiceHeaderVO headerVO) {
    StringBuffer buffer = new StringBuffer();
    // 根据公共需求2011.6.25
    // 审批中状态、审批人为空的单据可以收回
    if (POEnumBillStatus.APPROVING.toInt() != headerVO.getFbillstatus()
        .intValue()
        || StringUtils.isNotBlank(headerVO.getApprover())) {
      buffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0055")/*@res "只有审批中未有人审批的发票才能收回！"*/);
    }
    return buffer;
  }

}