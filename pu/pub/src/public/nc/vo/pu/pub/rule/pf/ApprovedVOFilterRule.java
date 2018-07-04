package nc.vo.pu.pub.rule.pf;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * @description
 *              请购单审批的后规则，找到所有已经审批过的VO<br>
 *              有审批流的情况下，尽管只是中间的审批环节时审批时，也会调用我们的审批<br>
 *              在此情况下，单据还未最后审批通过，此处VO就不应该去做审批通过后才做的规则<br>
 *              一般放到审批后需求过滤的各个规则前<br>
 * @scene
 *        请购单审批
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午8:57:18
 * @author yanxm5
 */
public class ApprovedVOFilterRule<E extends AbstractBill> implements
    IFilterRule<E> {

  @SuppressWarnings("unchecked")
  @Override
  public AbstractBill[] process(AbstractBill[] vos) {
    return ApproveFlowUtil.filterApprovedVO(vos);
  }

}
