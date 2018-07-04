package nc.bs.pu.m21.state;

import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.view.IDataView;

/**
 * 
 * @description
 * 采购订单行状态的抽象实现类
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-17 下午4:36:39
 * @author luojw
 */
public abstract class AbstractOrderRowState<E extends IDataView> 
       extends AbstractOrderState<E> {
  

  /**
   * 实体类型Class
   */
  private Class<? extends ISuperVO> clazz;

  /**
   * 行状态构造函数
   * 
   * @param clazz 实体类型Class
   * @param stateKey 状态字段
   * @param stateValue 状态值
   */
  public AbstractOrderRowState(Class<? extends ISuperVO> clazz, String stateKey,
      Object stateValue) {
    super(stateKey, stateValue);
    this.clazz = clazz;
  }
  
  /**
   * 行状态构造函数
   *
   * @param clazz
   * @param stateKey
   * @param stateValue
   * @param isAutoUpdate 是否自动执行update语句
   */
  public AbstractOrderRowState(Class<? extends ISuperVO> clazz, String stateKey,
      Object stateValue, UFBoolean isAutoUpdate) {
    super(stateKey, stateValue, isAutoUpdate);
    this.clazz = clazz;
  }

  @Override
  public boolean isThisState(E vo) {
    Object value = vo.getAttributeValue(this.stateKey);
    if (value == null) {
      return false;
    }
    return this.stateValue.equals(value);
  }

  @Override
  public void setState(E[] views) {
    for (E view : views) {
      if (this.isThisState(view)) {
        String message = "当前VO不能重复设置相同的状态";/*-=notranslate=-*/
        ExceptionUtils.wrappBusinessException(message);
      }
      else if (!this.isPrevStateValid(view)) {
        String message = "当前VO所属的状态不能设置当前状态";/*-=notranslate=-*/
        ExceptionUtils.wrappBusinessException(message);
      }
      view.setAttributeValue(this.stateKey, this.stateValue);
    }
  }
  
  @Override
  public void updateStateKey(E[] vos) {
    String[] keys = this.getStateKeyList().toArray(new String[0]);
    ViewUpdate<E> bo = new ViewUpdate<E>();
    bo.update(vos, this.clazz, keys);
  }

}
