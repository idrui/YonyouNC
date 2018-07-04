package nc.bs.pu.m21.state;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pubapp.pattern.data.IObjectConvert;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;

/**
 * 
 * @description
 * 可跃迁的状态机。当当前状态机1触发状态事件时，可以跃迁到另一个状态机2的状态上
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-17 下午4:45:48
 * @author luojw
 */
public class TransitStateMachine<E, F> {

  /**
   * 状态机1
   */
  private StateMachine<E> machine1 = new StateMachine<E>();

  /**
   * 状态机2
   */
  private StateMachine<F> machine2 = new StateMachine<F>();

  /**
   * 对象转换器，两个状态机跃迁的时候，需要对对象进行转换
   */
  private IObjectConvert<E, F> convertor;

  /**
   * 可跃迁的状态机构造函数
   * 
   * @param convertor 对象转换器
   */
  public TransitStateMachine(IObjectConvert<E, F> convertor) {
    this.convertor = convertor;
  }

  @SuppressWarnings("unchecked")
  private List<ITransitionState<E, F>> getTransitStates(IOrderState<E> state) {
    List<ITransitionState<E, F>> list = new ArrayList<ITransitionState<E, F>>();
    if (state instanceof ITransitionState) {
      list.add((ITransitionState<E, F>) state);
    }
    List<IOrderState<E>> stateList = state.nextState();
    for (IOrderState<E> postState : stateList) {
      if (postState instanceof ITransitionState) {
        list.add((ITransitionState<E, F>) postState);
      }
    }
    return list;
  }

  /**
   * 对实体设置状态
   * 
   * @param state 要设置的状态
   * @param vos 实体数组
   */
  public void setState(IOrderState<E> state, E[] vos) {
    this.machine1.setState(state, vos);

    // 状态机1到状态机2的跃迁
    List<ITransitionState<E, F>> list = this.getTransitStates(state);
    if (list.size() == 0) {
      return;
    }
    for (ITransitionState<E, F> transitState : list) {
      this.transit(transitState, vos);
    }
  }

  /**
   * 由于效率方面的考虑，可以在对象状态跃迁的时候，同步两个对象间的数据
   * 
   * @param vos 实体对象1
   * @param anotherVOs 实体对象2
   */
  protected void synchronizeData(E[] vos, F[] anotherVOs) {
  }

  private void transit(ITransitionState<E, F> state, E[] vos) {
    List<E> list = new ArrayList<E>();
    // 查找当前VO的状态是否可以进行跃迁
    for (E vo : vos) {
      if (state.isThisState(vo)) {
        list.add(vo);
      }
    }
    if (list.size() == 0) {
      return;
    }
    ListToArrayTool<E> tool = new ListToArrayTool<E>();
    E[] items = tool.convertToArray(list);
    F[] anotherVOs = this.convertor.convert(items);
    IOrderState<F> anotherState = state.getTransitTargetState();
    // 对于可以跃迁的VO再进行计算，看看其实际是否可以进行状态的设置
    this.transitToMachine2(anotherState, vos, anotherVOs);
  }

  private void transitToMachine2(IOrderState<F> state, E[] vos, F[] anotherVOs) {
    List<F> list = new ArrayList<F>();
    for (F vo : anotherVOs) {
      // 单据有五行，其中一行行关闭，然后再手工触发整单关闭，此时已经关闭的行不用再
      // 做处理
      if (state.isThisState(vo)) {
        continue;
      }
      else if (state.isAutoTransitable(vo)) {
        list.add(vo);
      }
    }
    if (list.size() == 0) {
      return;
    }
    ListToArrayTool<F> tool = new ListToArrayTool<F>();
    F[] items = tool.convertToArray(list);
    this.machine2.setState(state, items);
    this.synchronizeData(vos, anotherVOs);
  }
}
