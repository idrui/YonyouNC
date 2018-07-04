/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-13 下午02:54:25
 */
package nc.impl.pu.m21.action;

import nc.bs.pu.m21.state.OrderStateMachine;
import nc.bs.pu.m21.state.row.OrderPayCloseState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>执行订单的付款关闭/打开操作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-13 下午02:54:25
 */
public class OrderPayCloseAction {

  public OrderCloseVO[] close(OrderCloseVO[] vos) {
    // 插入点放到状态机中
    AroundProcesser<OrderCloseVO> processer =
        new AroundProcesser<OrderCloseVO>(null);
    processer.before(vos);
    OrderPayCloseState state = new OrderPayCloseState(UFBoolean.TRUE);
    new OrderStateMachine().setState(state, vos);
    processer.after(vos);
    return vos;
  }

  public OrderCloseVO[] open(OrderCloseVO[] vos) {
    AroundProcesser<OrderCloseVO> processer =
        new AroundProcesser<OrderCloseVO>(null);
    processer.before(vos);
    OrderPayCloseState state = new OrderPayCloseState(UFBoolean.FALSE);
    new OrderStateMachine().setState(state, vos);
    processer.after(vos);
    return vos;
  }

}
