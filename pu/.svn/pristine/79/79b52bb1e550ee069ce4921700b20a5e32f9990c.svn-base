package nc.impl.pu.m21.action.rule.approve;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.m21.entity.OrderVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              通过状态过滤采购订单
 * @scene
 *        采购订单审批、修订
 * @param 无
 * @since 6.3
 * @version 2014-10-20 下午3:42:41
 * @author luojw
 */

public class FilterOrderByStatusRule implements IFilterRule<OrderVO> {
  private int status;

  public FilterOrderByStatusRule(int status) {
    this.status = status;
  }

  @Override
  public OrderVO[] process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    List<OrderVO> list = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      if (this.status == vo.getHVO().getForderstatus().intValue()) {
        list.add(vo);
      }
    }
    if (list.size() > 0) {
      return list.toArray(new OrderVO[list.size()]);
    }
    return null;
  }

}
