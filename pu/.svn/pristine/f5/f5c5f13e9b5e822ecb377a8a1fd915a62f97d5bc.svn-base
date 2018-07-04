package nc.bs.pu.m21.state;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pubapp.pattern.pub.ListToArrayTool;

/**
 * 
 * @description
 * 实体状态机的实现类
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-17 下午4:45:32
 * @author luojw
 */
public class StateMachine<E> {

  private void setAffectState(IOrderState<E> currentState, IOrderState<E> state, E[] vos) {
    List<E> list = new ArrayList<E>();
    for (E vo : vos) {
      if (state.isThisState(vo)) {
        continue;
      }
      else if (state.isAutoTransitable(vo)) {
        list.add(vo);
      }
    }
    int size = list.size();
    if (size == 0) {
      return;
    }
    ListToArrayTool<E> tool = new ListToArrayTool<E>();
    E[] items = tool.convertToArray(list);
    state.setState(items);
    // 将当前状态改变的字段，添加到最初状态更改类中
    currentState.addStateKeyToList(state.getStateKey());
  }

  /**
   * 对实体设置状态
   * 
   * @param state 要设置的状态
   * @param vos 实体数组
   */
  public void setState(IOrderState<E> state, E[] vos) {
    state.setState(vos);
    List<IOrderState<E>> stateList = state.nextState();
    if (stateList.size() == 0) {
      return;
    }
    for (IOrderState<E> nextState : stateList) {
      this.setAffectState(state, nextState, vos);
    }
    // 有些情况需要手动调用update方法
    if(state.isAutoUpdate()){
      state.updateStateKey(vos);
    }
  }

}
