package nc.vo.pu.pub.rule.pf;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * @description
 *              根据审批流的要求,单据修改保存时,如果当前状态是审批不通过,则将状态置为自由态
 * @scene
 *        物资需求申请单、到货单、请购单和采购订单保存、修改
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午10:43:41
 * @author yanxm5
 */
public class NoPassUpdateRule<E extends AbstractBill> implements IRule<E> {

  @Override
  public void process(E[] vos) {
    ApproveFlowUtil.processBillStatusWhenEdit(vos);
  }

}
