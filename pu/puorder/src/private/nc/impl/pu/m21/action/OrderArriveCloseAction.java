/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-12 下午02:33:28
 */
package nc.impl.pu.m21.action;

import nc.bs.pu.m21.state.OrderStateMachine;
import nc.bs.pu.m21.state.row.OrderArriveCloseState;
import nc.impl.pu.m21.action.rule.close.ATPBeforeUpdateRule;
import nc.impl.pu.m21.action.rule.close.ATPUpdateRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货的打开/关闭动作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-12 下午02:33:28
 */
public class OrderArriveCloseAction {
  public OrderCloseVO[] close(OrderCloseVO[] vos) {
    // 插入点放到state中，这样自动和手动都会执行。
    AroundProcesser<OrderCloseVO> processer =
        new AroundProcesser<OrderCloseVO>(null);
    this.addCloseRule(processer);
    processer.before(vos);

    OrderArriveCloseState state = new OrderArriveCloseState(UFBoolean.TRUE);
    new OrderStateMachine().setState(state, vos);

    processer.after(vos);
    return vos;
  }

  public OrderCloseVO[] open(OrderCloseVO[] vos) {
    // 插入点放到state中，这样自动和手动都会执行。
    AroundProcesser<OrderCloseVO> processer =
        new AroundProcesser<OrderCloseVO>(null);
    this.addOpenRule(processer);

    processer.before(vos);
    OrderArriveCloseState state = new OrderArriveCloseState(UFBoolean.FALSE);
    new OrderStateMachine().setState(state, vos);

    processer.after(vos);
    return vos;
  }

  private void addCloseRule(AroundProcesser<OrderCloseVO> processer) {
    processer.addBeforeRule(new ATPBeforeUpdateRule());// 可用量更新前处理
    processer.addAfterRule(new ATPUpdateRule()); // 可用量更新
  }

  private void addOpenRule(AroundProcesser<OrderCloseVO> processer) {
    processer.addBeforeRule(new ATPBeforeUpdateRule());// 可用量更新前处理
    processer.addAfterRule(new ATPUpdateRule()); // 可用量更新
  }

}
