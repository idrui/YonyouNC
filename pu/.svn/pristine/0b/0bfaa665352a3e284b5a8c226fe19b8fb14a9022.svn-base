/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-13 下午07:01:08
 */
package nc.pubimpl.pu.m25.pu.settle.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;

/**
 * 
 * @description
 * 虚拟发票删除时清除审批信息等
 * @scene
 * 删除虚拟发票
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:06:29
 * @author zhangshqb
 */
public class VirtualDelClearInfoRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    for (InvoiceVO vo : vos) {
      InvoiceHeaderVO header = vo.getParentVO();
      header.setApprover(null);
      header.setTaudittime(null);
      header.setFbillstatus(POEnumBillStatus.FREE.toInteger());
    }
  }

}
