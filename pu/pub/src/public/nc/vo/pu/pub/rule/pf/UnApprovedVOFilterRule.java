package nc.vo.pu.pub.rule.pf;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * @description
 *              请购单取消审批的后规则， 过滤出原来是审批过的，当前操作后取消审批的VO<br>
 *              即原始VO为审批通过状态，当前VO为非审批通过状态<br>
 *              有审批流的情况下，尽管只是中间的审批环节时取消审批操作，也会调用我们的取消审批代码<br>
 *              在此情况下，单据只有从审批通过状态变为非审批通过状态才去做取消审批后规则<br>
 *              一般放到取消审批后需要过滤的各个规则前<br>
 * @scene
 *        请购单弃审
 * @param
 *        AbstractBill[] origVos 单据VO
 * @since 6.3
 * @version 2014-10-21 上午10:29:53
 * @author yanxm5
 */
public class UnApprovedVOFilterRule<E extends AbstractBill> implements
    IFilterRule<E> {
  private AbstractBill[] origVos;

  public UnApprovedVOFilterRule(AbstractBill[] origVos) {
    this.origVos = origVos;
  }

  @SuppressWarnings("unchecked")
  @Override
  public AbstractBill[] process(AbstractBill[] vos) {
    return ApproveFlowUtil.filterUnApprovedVO(vos, this.origVos);
  }

}
