package nc.bs.pu.m21.state;

import java.util.List;

import nc.impl.pubapp.bill.state.IState;

/**
 * 
 * @description
 * 订单状态的接口。
 * 订单一个状态的改变，经常引起多个状态的改变。
 * 按照之前的接口，每个状态变化都会有一条update的sql。
 * 增加一个返回变化字段的方法，写一个统一处理的sql。
 * @scene
 * 订单几个行状态的关闭/打开
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-17 下午2:30:46
 * @author luojw
 */
public interface IOrderState<E> extends IState<E>{
  
  /**
   * 获得当前变化的字段
   * 
   * @return
   */
  String getStateKey();
  
  /**
   * 获得此状态变化引起所有变化字段的集合
   * 
   * @return
   */
  List<String> getStateKeyList();
  
  /**
   * 将变化的字段添加到集合中
   */
  void addStateKeyToList(String key);
  
  /**
   * 当实体对象变化到当前状态后，还有可能触发其他的状态的变化
   * 
   * @return 后续可能被触发的状态
   */
  List<IOrderState<E>> nextState();
  
  /**
   * 对所有的变化字段执行更新操作
   */
  void updateStateKey(E[] vos);
  
  /**
   * 是否自动执行更新字段操作，默认自动
   * 
   * @return
   */
  boolean isAutoUpdate();

}
