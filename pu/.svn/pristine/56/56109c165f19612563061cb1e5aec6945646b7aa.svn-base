package nc.bs.pu.m21.state;

/**
 * 
 * @description
 * 可跃迁的状态
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-17 下午4:44:48
 * @author luojw
 */
public interface ITransitionState<E, F> extends IOrderState<E> {
  /**
   * 当前状态可以跃迁到的另一个状态机的状态
   * 
   * @return 可以跃迁到的另一个状态机的状态
   */
  IOrderState<F> getTransitTargetState();

}
