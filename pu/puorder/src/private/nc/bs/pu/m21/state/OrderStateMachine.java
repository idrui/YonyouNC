/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-12 下午04:03:52
 */
package nc.bs.pu.m21.state;

import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-12 下午04:03:52
 */
public class OrderStateMachine {

  private TwainStateMachine<OrderVO, OrderCloseVO> machine = null;

  public OrderStateMachine() {
    this.machine =
        new TwainStateMachine<OrderVO, OrderCloseVO>(OrderVO.class,
            OrderCloseVO.class);
  }

  public void setState(IOrderState<OrderCloseVO> state, OrderCloseVO[] vos) {
    this.machine.setRowState(state, vos);
  }

  public void setState(IOrderState<OrderVO> state, OrderVO[] vos) {
    this.machine.setBillState(state, vos);
  }

}
