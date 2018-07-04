package nc.bs.pu.m21.state;

import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.impl.pubapp.bill.convertor.ViewToBillConvertor;
import nc.vo.pubapp.pattern.data.IObjectConvert;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.entity.view.IDataView;

/**
 * 
 * @description
 * 整单状态与行状态的并行状态机，控制两种状态之间的跃迁
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-17 下午4:46:04
 * @author luojw
 */
public class TwainStateMachine<E extends IBill, F extends IDataView> {

  /**
   * 单据状态机1
   */
  private TransitStateMachine<E, F> billMatchine;

  /**
   * 行状态机2
   */
  private TransitStateMachine<F, E> rowMachine;

  /**
   * 并行状态机构造函数
   * 
   * @param billClazz 单据实体类型Class
   * @param viewClazz 视图类型Class
   */
  public TwainStateMachine(Class<E> billClazz, Class<F> viewClazz) {
    IObjectConvert<E, F> convertor1 = new BillToViewConvertor<E, F>(viewClazz);
    IObjectConvert<F, E> convertor2 = new ViewToBillConvertor<F, E>(billClazz);
    this.billMatchine = new TransitStateMachine<E, F>(convertor1);
    this.rowMachine = new RowTransitStateMachine<F, E>(convertor2);
  }

  /**
   * 更新整单状态
   * 
   * @param state 整单状态
   * @param vos 单据实体数组
   */
  public void setBillState(IOrderState<E> state, E[] vos) {
    this.billMatchine.setState(state, vos);
  }

  /**
   * 更新行状态
   * 
   * @param state 行状态
   * @param vos 视图数组
   */
  public void setRowState(IOrderState<F> state, F[] vos) {
    this.rowMachine.setState(state, vos);
  }

}
