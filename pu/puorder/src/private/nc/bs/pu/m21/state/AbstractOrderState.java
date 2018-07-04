package nc.bs.pu.m21.state;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.bill.state.IState;
import nc.vo.pub.lang.UFBoolean;

/**
 * 
 * @description
 * 采购订单状态的抽象实现类
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-17 下午4:36:10
 * @author luojw
 */
public abstract class AbstractOrderState<E> implements IOrderState<E> {

  /**
   * 是否自动更新字段, 默认自动
   */
  private UFBoolean isAuotUpdate = UFBoolean.TRUE;
  
  /**
   * 状态字段
   */
  protected String stateKey;
  
  /**
   * 所有变化字段的集合
   */
  private List<String> stateKeyList = new ArrayList<>();

  /**
   * 状态值
   */
  protected Object stateValue;

  /**
   * 行状态构造函数
   * 
   * @param clazz 实体类型Class
   * @param stateKey 状态字段
   * @param stateValue 状态值
   */
  public AbstractOrderState(String stateKey, Object stateValue) {
    this.stateKey = stateKey;
    this.stateValue = stateValue;
    this.stateKeyList.add(stateKey);
  }
  
  public AbstractOrderState(String stateKey, Object stateValue, UFBoolean isAutoUpdate) {
    this(stateKey, stateValue);
    this.isAuotUpdate = isAutoUpdate;
  }

  @Override
  public String getStateKey(){
    return this.stateKey;
  }
  
  @Override
  public List<String> getStateKeyList() {
    return this.stateKeyList;
  }
  
  @Override
  public void addStateKeyToList(String key) {
    this.stateKeyList.add(key);
  }
  
  @Override
  public List<IState<E>> next() {
    return null;
  }
  
  @Override
  public void updateStateKey(E[] vos) {
  }
  
  @Override
  public boolean isAutoUpdate() {
    return this.isAuotUpdate.booleanValue();
  }
}
