/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-28 上午10:35:32
 */
package nc.bs.pu.m422x.state;

import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.bill.state.TwainStateMachine;
import nc.vo.pu.m422x.entity.StoreReqAppCloseVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物资需求申请状态机
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-28 上午10:35:32
 */
public class StoreReqAppStateMachine {

  private TwainStateMachine<StoreReqAppVO, StoreReqAppCloseVO> machine = null;

  public StoreReqAppStateMachine() {
    this.machine =
        new TwainStateMachine<StoreReqAppVO, StoreReqAppCloseVO>(
            StoreReqAppVO.class, StoreReqAppCloseVO.class);
  }

  public void setState(IState<StoreReqAppCloseVO> state,
      StoreReqAppCloseVO[] vos) {
    this.machine.setRowState(state, vos);
  }

  public void setState(IState<StoreReqAppVO> state, StoreReqAppVO[] vos) {
    this.machine.setBillState(state, vos);
  }
}
