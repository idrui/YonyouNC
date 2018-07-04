/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-12 ����02:33:00
 */
package nc.impl.pu.m21.action;

import nc.bs.pu.m21.state.OrderStateMachine;
import nc.bs.pu.m21.state.row.OrderStoreCloseState;
import nc.impl.pu.m21.action.rule.close.ATPBeforeUpdateRule;
import nc.impl.pu.m21.action.rule.close.ATPUpdateRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ر�/�򿪲���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-12 ����02:33:00
 */
public class OrderStoreCloseAction {

  /**
   * ��������������ִ�ж��������رա�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-13 ����09:37:49
   */
  public OrderCloseVO[] close(OrderCloseVO[] vos) {
    // ������Ƶ�״̬��
    AroundProcesser<OrderCloseVO> processer =
        new AroundProcesser<OrderCloseVO>(null);
    this.addCloseRule(processer);
    processer.before(vos);

    OrderStoreCloseState state = new OrderStoreCloseState(UFBoolean.TRUE);
    new OrderStateMachine().setState(state, vos);

    processer.after(vos);
    return vos;
  }

  /**
   * ��������������ִ�ж��������򿪡�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-13 ����09:38:07
   */
  public OrderCloseVO[] open(OrderCloseVO[] vos) {
    AroundProcesser<OrderCloseVO> processer =
        new AroundProcesser<OrderCloseVO>(null);
    this.addOpenRule(processer);
    processer.before(vos);

    OrderStoreCloseState state = new OrderStoreCloseState(UFBoolean.FALSE);
    new OrderStateMachine().setState(state, vos);

    processer.after(vos);
    return vos;
  }

  private void addCloseRule(AroundProcesser<OrderCloseVO> processer) {
    processer.addBeforeRule(new ATPBeforeUpdateRule());
    processer.addAfterRule(new ATPUpdateRule());
  }

  private void addOpenRule(AroundProcesser<OrderCloseVO> processer) {
    processer.addBeforeRule(new ATPBeforeUpdateRule());
    processer.addAfterRule(new ATPUpdateRule());
  }

}
