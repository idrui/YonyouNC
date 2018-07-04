package nc.bs.pu.m21.state;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pubapp.pattern.data.IObjectConvert;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;

/**
 * 
 * @description
 * ��ԾǨ��״̬��������ǰ״̬��1����״̬�¼�ʱ������ԾǨ����һ��״̬��2��״̬��
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-17 ����4:45:48
 * @author luojw
 */
public class TransitStateMachine<E, F> {

  /**
   * ״̬��1
   */
  private StateMachine<E> machine1 = new StateMachine<E>();

  /**
   * ״̬��2
   */
  private StateMachine<F> machine2 = new StateMachine<F>();

  /**
   * ����ת����������״̬��ԾǨ��ʱ����Ҫ�Զ������ת��
   */
  private IObjectConvert<E, F> convertor;

  /**
   * ��ԾǨ��״̬�����캯��
   * 
   * @param convertor ����ת����
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
   * ��ʵ������״̬
   * 
   * @param state Ҫ���õ�״̬
   * @param vos ʵ������
   */
  public void setState(IOrderState<E> state, E[] vos) {
    this.machine1.setState(state, vos);

    // ״̬��1��״̬��2��ԾǨ
    List<ITransitionState<E, F>> list = this.getTransitStates(state);
    if (list.size() == 0) {
      return;
    }
    for (ITransitionState<E, F> transitState : list) {
      this.transit(transitState, vos);
    }
  }

  /**
   * ����Ч�ʷ���Ŀ��ǣ������ڶ���״̬ԾǨ��ʱ��ͬ����������������
   * 
   * @param vos ʵ�����1
   * @param anotherVOs ʵ�����2
   */
  protected void synchronizeData(E[] vos, F[] anotherVOs) {
  }

  private void transit(ITransitionState<E, F> state, E[] vos) {
    List<E> list = new ArrayList<E>();
    // ���ҵ�ǰVO��״̬�Ƿ���Խ���ԾǨ
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
    // ���ڿ���ԾǨ��VO�ٽ��м��㣬������ʵ���Ƿ���Խ���״̬������
    this.transitToMachine2(anotherState, vos, anotherVOs);
  }

  private void transitToMachine2(IOrderState<F> state, E[] vos, F[] anotherVOs) {
    List<F> list = new ArrayList<F>();
    for (F vo : anotherVOs) {
      // ���������У�����һ���йرգ�Ȼ�����ֹ����������رգ���ʱ�Ѿ��رյ��в�����
      // ������
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
