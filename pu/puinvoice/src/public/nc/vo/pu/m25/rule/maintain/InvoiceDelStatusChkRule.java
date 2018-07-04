/**
 *
 */
package nc.vo.pu.m25.rule.maintain;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * 发票状态检查:虚拟发票不能删除；(这里是BP，应该放到),只有自由状态(或审批中无审批人)的才能删除
 * @scene
 * 删除
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:08:33
 * @author zhangshqb
 */
public class InvoiceDelStatusChkRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    StringBuffer buffer = new StringBuffer();
    for (InvoiceVO vo : vos) {
      InvoiceHeaderVO headerVO = vo.getParentVO();
      if (null == headerVO) {
        buffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0029")/*@res "表头不能为空\r\n"*/);
        continue;
      }
      if (!ApproveFlowUtil.isCanDel(vo)) {
        buffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0056")/*@res "发票当前状态下不能删除\r\n"*/);
      }
    }
    if (0 < buffer.length()) {
      ExceptionUtils.wrappBusinessException(buffer.toString());
    }
  }
}