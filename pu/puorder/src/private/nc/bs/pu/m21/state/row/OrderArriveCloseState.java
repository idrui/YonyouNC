/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-12 ����09:49:26
 */
package nc.bs.pu.m21.state.row;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pu.m21.state.AbstractOrderRowState;
import nc.bs.pu.m21.state.IOrderState;
import nc.bs.pu.m21.state.rule.StateMPPCtrlCheckRule;
import nc.bs.pu.m21.state.rule.UnChgStateRowFilterRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.tbb.BillOperationEnum;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����ر�״̬����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-12 ����09:49:26
 */
public class OrderArriveCloseState extends AbstractOrderRowState<OrderCloseVO> {

  private UFBoolean value;

  public OrderArriveCloseState(UFBoolean value) {
    super(OrderItemVO.class, OrderItemVO.BARRIVECLOSE, value);
    this.value = value;
  }
  
  public OrderArriveCloseState(UFBoolean value, UFBoolean isAutoUpdate) {
    super(OrderItemVO.class, OrderItemVO.BARRIVECLOSE, value, isAutoUpdate);
    this.value = value;
  }

  @Override
  public boolean isAutoTransitable(OrderCloseVO vo) {
    // ֱ�ӷ���true,�������Ϊ����״̬������һ����ر�
    return true;
  }

  @Override
  public boolean isPrevStateValid(OrderCloseVO vo) {
    // ������һ״̬��ʲô���������õ����ر�
    return true;
  }

  @Override
  public List<IOrderState<OrderCloseVO>> nextState() {
    List<IOrderState<OrderCloseVO>> nextStates =
        new ArrayList<IOrderState<OrderCloseVO>>();

    if (this.value.booleanValue()) {
      // ����ͬʱ�����йر�
      nextStates.add(new OrderRowCloseState(EnumActive.CLOSE));
    }
    else {
      // �����򿪺�ͬʱ��������
      nextStates.add(new OrderStoreCloseState(UFBoolean.FALSE));
      // ͬʱ�����д�
      nextStates.add(new OrderRowAutoOpenState());
    }
    return nextStates;
  }

  @Override
  public void setState(OrderCloseVO[] views) {
    AroundProcesser<OrderCloseVO> processer = null;
    if (this.value.booleanValue()) {
      processer =
          new AroundProcesser<OrderCloseVO>(OrderPluginPoint.ARRIVE_CLOSE);
    }
    else {
      processer =
          new AroundProcesser<OrderCloseVO>(OrderPluginPoint.ARRIVE_OPEN);
    }

    this.addBeforeRule(processer);
    this.addAfterRule(processer);

    OrderCloseVO[] beforedViews = processer.before(views);
    super.setState(beforedViews);
    processer.after(beforedViews);
  }

  private void addAfterRule(AroundProcesser<OrderCloseVO> processer) {
    // �ɹ��ƻ����� // mengjian by 20150120
    if (SysInitGroupQuery.isMPPEnabled()) {
      processer.addAfterRule(new StateMPPCtrlCheckRule(POBillType.Arrive
          .getCode(), this.value.booleanValue() ? BillOperationEnum.CLOSE
          : BillOperationEnum.OPEN));
    }
  }

  private void addBeforeRule(AroundProcesser<OrderCloseVO> processer) {
    if (this.value.booleanValue()) {
      processer.addBeforeRule(new UnChgStateRowFilterRule(
          OrderItemVO.BARRIVECLOSE, UFBoolean.TRUE));// �����ѹرյ���
    }
    else {
      processer.addBeforeRule(new UnChgStateRowFilterRule(
          OrderItemVO.BARRIVECLOSE, UFBoolean.FALSE));
    }
  }

}
