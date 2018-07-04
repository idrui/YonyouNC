/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-13 下午03:13:33
 */
package nc.impl.pu.m21.action;

import nc.bs.pu.m21.state.OrderStateMachine;
import nc.bs.pu.m21.state.row.OrderRowCloseState;
import nc.impl.pu.m21.action.rule.close.ATPBeforeUpdateRule;
import nc.impl.pu.m21.action.rule.close.ATPUpdateRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.enumeration.EnumActive;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单的行关闭/打开操作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-13 下午03:13:33
 */
public class OrderRowCloseAction {
  /**
   * 方法功能描述：执行订单的行关闭。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-13 下午03:08:57
   */
  public OrderCloseVO[] close(OrderCloseVO[] vos) {
    // 插入点移到状态机中
    AroundProcesser<OrderCloseVO> processer =
        new AroundProcesser<OrderCloseVO>(null);
    this.addCloseRule(processer);
    processer.before(vos);

    OrderRowCloseState state = new OrderRowCloseState(EnumActive.CLOSE);
    new OrderStateMachine().setState(state, vos);

    processer.after(vos);
    return vos;
  }

  public OrderCloseVO[] open(OrderCloseVO[] vos) {
    AroundProcesser<OrderCloseVO> processer =
        new AroundProcesser<OrderCloseVO>(null);
    this.addOpenRule(processer);
    processer.before(vos);

    OrderRowCloseState state = new OrderRowCloseState(EnumActive.ACTIVE);
    new OrderStateMachine().setState(state, vos);

    processer.after(vos);
    return vos;
  }

  private void addCloseRule(AroundProcesser<OrderCloseVO> processer) {
    processer.addBeforeRule(new ATPBeforeUpdateRule());
    processer.addAfterRule(new ATPUpdateRule());
  }

  private void addOpenRule(AroundProcesser<OrderCloseVO> processer) {
    processer.addBeforeRule(new ATPBeforeUpdateRule());
    processer.addAfterRule(new ATPUpdateRule());
  }
}
