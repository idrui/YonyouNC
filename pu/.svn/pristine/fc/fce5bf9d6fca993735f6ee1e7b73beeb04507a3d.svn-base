package nc.bs.pu.m21.state.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.m21.state.OrderStateMachine;
import nc.bs.pu.m21.state.row.OrderRowCloseState;
import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pub.lang.UFBoolean;

/**
 * @description
 *              过滤掉已经最终关闭/打开状态VO
 * @scene
 *        采购订单最终打开、关闭
 * @param UFBoolean closeState 订单的关闭状态
 * @since 6.3
 * @version 2014-10-21 上午9:02:01
 * @author luojw
 */
public class UnChgStateBillFilterRule implements IFilterRule<OrderVO> {

  private UFBoolean closeState = UFBoolean.TRUE;

  public UnChgStateBillFilterRule(UFBoolean closeState) {
    this.closeState = closeState;
  }

  @Override
  public OrderVO[] process(OrderVO[] vos) {
    List<OrderVO> filterVos = new ArrayList<OrderVO>();
    List<OrderCloseVO> openVOList = new ArrayList<OrderCloseVO>();
    for (OrderVO vo : vos) {
      if (!this.closeState.equals(vo.getHVO().getBfinalclose())) {
        filterVos.add(vo);
      }
      else {
        OrderHeaderVO headVO = vo.getHVO();
        for (OrderItemVO itemVO : vo.getBVO()) {
          OrderCloseVO openVO = new OrderCloseVO();
          openVO.setVO(headVO);
          openVO.setVO(itemVO);
          openVOList.add(openVO);
        }
      }
    }

    if (openVOList.size() > 0) {
      OrderRowCloseState state = new OrderRowCloseState(EnumActive.ACTIVE);
      new OrderStateMachine().setState(state,
          openVOList.toArray(new OrderCloseVO[openVOList.size()]));
    }
    return filterVos.toArray(new OrderVO[filterVos.size()]);
  }

}
