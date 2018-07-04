/**
 *
 */
package nc.vo.pu.m25.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * 发票状态检查,发票状态检查，必须是审批态；虚拟发票不能弃审
 * @scene
 * 弃审
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:49:42
 * @author zhangshqb
 */
public class UnApproveStatusChkRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    StringBuffer buffer = new StringBuffer();
    for (InvoiceVO vo : vos) {
      buffer.append(this.checkHeaderVO(vo));
    }
    if (buffer.length() > 0) {
      ExceptionUtils.wrappBusinessException(buffer.toString());
    }
  }

  private StringBuffer checkHeaderVO(InvoiceVO vo) {
    StringBuffer buffer = new StringBuffer();
    if (!ApproveFlowUtil.isCanUnApprove(vo)) {
      buffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0053")/*@res "发票的当前状态不能取消审批！"*/);
    }
    InvoiceHeaderVO headerVO = vo.getParentVO();
    if (headerVO.getBvirtual().booleanValue()) {
      buffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0054")/*@res "虚拟发票不能手工取消审批！"*/);
    }
    return buffer;
  }

}