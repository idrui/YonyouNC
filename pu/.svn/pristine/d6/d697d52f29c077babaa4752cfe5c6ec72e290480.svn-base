package nc.vo.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;

/**
 * 直运清空
 * 
 * @since 6.0
 * @version 2011-4-1 下午04:44:10
 * @author wuxla
 */

public class DirectOrderClearRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      for (OrderItemVO itemVO : vo.getBVO()) {
        // 直运单价和金额不对照，所以清空
        itemVO.setNorigtaxmny(null);
        itemVO.setNorigmny(null);
        itemVO.setNorignetprice(null);
        itemVO.setNorigprice(null);
        itemVO.setNorigtaxnetprice(null);
        itemVO.setNorigtaxprice(null);
      }
    }
  }

}
