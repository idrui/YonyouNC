package nc.bs.pu.m21.state.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.m21.entity.OrderCloseVO;

/**
 * @description
 *              过滤掉已经是当前关闭/打开状态VO
 * @scene
 *        采购订单行状态打开、关闭
 * @param Object state 状态 String stateKey 状态key 根据key查询出来的状态和前者对比
 * @since 6.3
 * @version 2014-10-21 上午8:25:02
 * @author luojw
 */
public class UnChgStateRowFilterRule implements IFilterRule<OrderCloseVO> {

  private Object state;

  private String stateKey;

  public UnChgStateRowFilterRule(String stateKey, Object state) {
    this.stateKey = stateKey;
    this.state = state;
  }

  @Override
  public OrderCloseVO[] process(OrderCloseVO[] vos) {
    List<OrderCloseVO> filterVos = new ArrayList<OrderCloseVO>();
    for (OrderCloseVO vo : vos) {
      if (!this.state.equals(vo.getAttributeValue(this.stateKey))) {
        filterVos.add(vo);
      }
    }
    return filterVos.toArray(new OrderCloseVO[filterVos.size()]);
  }

}
