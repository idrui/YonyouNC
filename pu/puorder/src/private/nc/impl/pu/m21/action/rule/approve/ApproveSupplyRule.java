package nc.impl.pu.m21.action.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ic.ReserveServices;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.OrderVOUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              审批预留的采购订单单据
 * @scene
 *        采购订单审批
 * @param 无
 * @since 6.3
 * @version 2014-10-20 下午4:33:57
 * @author luojw
 */

public class ApproveSupplyRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    OrderVO[] reserveVOs = OrderVOUtil.getInsance().getSupplyVO(vos);
    if (ArrayUtils.isEmpty(reserveVOs)) {
      return;
    }
    ReserveServices.auditSupply(reserveVOs);
  }

}
