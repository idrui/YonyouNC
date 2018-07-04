/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-22 下午02:28:35
 */
package nc.impl.pu.m25.action.rule.approve;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.VOStatus;

/**
 * 
 * @description
 * 审批后更新数据库信息
 * @scene
 * 审批
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午2:43:50
 * @author zhangshqb
 */
public class UpdateApproveInfoRule implements ICompareRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos, InvoiceVO[] orgVos) {
    for (InvoiceVO vo : vos) {
      vo.getParentVO().setStatus(VOStatus.UPDATED);
    }
    new BillUpdate<InvoiceVO>().update(vos, orgVos);

  }

}
