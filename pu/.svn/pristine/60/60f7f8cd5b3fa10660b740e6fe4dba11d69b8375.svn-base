/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-13 下午07:29:43
 */
package nc.pubimpl.pu.m25.pu.settle.rule;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m25.pf.IInvoiceSendAP;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * 删除虚拟发票前取消传应付
 * @scene
 * 删除虚拟发票
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:06:05
 * @author zhangshqb
 */
public class VirtualCancelSendAPRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    InvoiceUIToBSEnv env = new InvoiceUIToBSEnv();
    env.setBManual(UFBoolean.FALSE);
    try {
      // 调用取消传应付的服务
      NCLocator.getInstance().lookup(IInvoiceSendAP.class)
          .cancelSendAP(vos, env);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }
}
