/**
 * 
 */
package nc.vo.pu.m25.rule;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m27.IInvoiceAutoMatch;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 审批后自动结算,发票审批后自动结算，调用结算组件的服务
 * @scene
 * 审批,生成虚拟发票
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午2:52:07
 * @author zhangshqb
 */
public class InvoiceAutoSettleRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    try {
      NCLocator.getInstance().lookup(IInvoiceAutoMatch.class).invoiceAutoMatch(
          vos);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }
}
