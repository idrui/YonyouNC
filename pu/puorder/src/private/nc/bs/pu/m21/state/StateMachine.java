package nc.bs.pu.m21.state;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pubapp.pattern.pub.ListToArrayTool;

/**
 * 
 * @description
 * ʵ��״̬����ʵ����
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-17 ����4:45:32
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
    // ����ǰ״̬�ı���ֶΣ���ӵ����״̬��������
    currentState.addStateKeyToList(state.getStateKey());
  }

  /**
   * ��ʵ������״̬
   * 
   * @param state Ҫ���õ�״̬
   * @param vos ʵ������
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
    // ��Щ�����Ҫ�ֶ�����update����
    if(state.isAutoUpdate()){
      state.updateStateKey(vos);
    }
  }

}
