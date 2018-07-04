package nc.bs.pu.m21.query.ic.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @description
 *              过滤未审批的订单
 *              计算可入库数量
 * @scene
 *        采购订单销售出库单查询
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午9:12:29
 * @author luojw
 */

public class FilterAndCalcFor4CRule implements IFilterRule<OrderVO> {

  @Override
  public OrderVO[] process(OrderVO[] vos) {
    List<OrderVO> list = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      if (POEnumBillStatus.APPROVE.toInt() != vo.getHVO().getForderstatus()
          .intValue()) {
        continue;
      }
      for (OrderItemVO itemVO : vo.getBVO()) {
        itemVO.setNcaninnum(MathTool.sub(itemVO.getNnum(),
            itemVO.getNaccumstorenum()));
      }
      list.add(vo);
    }
    if (0 == list.size()) {
      return null;
    }
    return list.toArray(new OrderVO[list.size()]);
  }

}
