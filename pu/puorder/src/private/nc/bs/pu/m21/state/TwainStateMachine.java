package nc.bs.pu.m21.state;

import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.impl.pubapp.bill.convertor.ViewToBillConvertor;
import nc.vo.pubapp.pattern.data.IObjectConvert;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.entity.view.IDataView;

/**
 * 
 * @description
 * ����״̬����״̬�Ĳ���״̬������������״̬֮���ԾǨ
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-17 ����4:46:04
 * @author luojw
 */
public class TwainStateMachine<E extends IBill, F extends IDataView> {

  /**
   * ����״̬��1
   */
  private TransitStateMachine<E, F> billMatchine;

  /**
   * ��״̬��2
   */
  private TransitStateMachine<F, E> rowMachine;

  /**
   * ����״̬�����캯��
   * 
   * @param billClazz ����ʵ������Class
   * @param viewClazz ��ͼ����Class
   */
  public TwainStateMachine(Class<E> billClazz, Class<F> viewClazz) {
    IObjectConvert<E, F> convertor1 = new BillToViewConvertor<E, F>(viewClazz);
    IObjectConvert<F, E> convertor2 = new ViewToBillConvertor<F, E>(billClazz);
    this.billMatchine = new TransitStateMachine<E, F>(convertor1);
    this.rowMachine = new RowTransitStateMachine<F, E>(convertor2);
  }

  /**
   * ��������״̬
   * 
   * @param state ����״̬
   * @param vos ����ʵ������
   */
  public void setBillState(IOrderState<E> state, E[] vos) {
    this.billMatchine.setState(state, vos);
  }

  /**
   * ������״̬
   * 
   * @param state ��״̬
   * @param vos ��ͼ����
   */
  public void setRowState(IOrderState<F> state, F[] vos) {
    this.rowMachine.setState(state, vos);
  }

}
