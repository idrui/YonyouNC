package nc.bs.pu.m21.state;

import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.view.IDataView;

/**
 * 
 * @description
 * �ɹ�������״̬�ĳ���ʵ����
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-17 ����4:36:39
 * @author luojw
 */
public abstract class AbstractOrderRowState<E extends IDataView> 
       extends AbstractOrderState<E> {
  

  /**
   * ʵ������Class
   */
  private Class<? extends ISuperVO> clazz;

  /**
   * ��״̬���캯��
   * 
   * @param clazz ʵ������Class
   * @param stateKey ״̬�ֶ�
   * @param stateValue ״ֵ̬
   */
  public AbstractOrderRowState(Class<? extends ISuperVO> clazz, String stateKey,
      Object stateValue) {
    super(stateKey, stateValue);
    this.clazz = clazz;
  }
  
  /**
   * ��״̬���캯��
   *
   * @param clazz
   * @param stateKey
   * @param stateValue
   * @param isAutoUpdate �Ƿ��Զ�ִ��update���
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
        String message = "��ǰVO�����ظ�������ͬ��״̬";/*-=notranslate=-*/
        ExceptionUtils.wrappBusinessException(message);
      }
      else if (!this.isPrevStateValid(view)) {
        String message = "��ǰVO������״̬�������õ�ǰ״̬";/*-=notranslate=-*/
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
