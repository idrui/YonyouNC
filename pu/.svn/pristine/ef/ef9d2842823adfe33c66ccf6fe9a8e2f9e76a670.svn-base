package nc.impl.pu.m21.action.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              审批后直接推采购入库单时需要设置可入库数量，此规则为订单审批直接推入库单提供支持
 * @scene
 *        采购订单审批
 * @param 无
 * @since 6.3
 * @version 2014-10-20 下午4:55:35
 * @author luojw
 */

public class FillNcaninnumRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (OrderVO vo : vos) {
      for (OrderItemVO itemVO : vo.getBVO()) {
        itemVO.setNcaninnum(itemVO.getNnum());
      }
    }
  }

}
