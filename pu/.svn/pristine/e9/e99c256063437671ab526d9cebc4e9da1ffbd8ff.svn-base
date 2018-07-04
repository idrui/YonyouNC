/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-12 上午09:52:06
 */
package nc.bs.pu.m21.state.row;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pu.m21.state.AbstractOrderRowState;
import nc.bs.pu.m21.state.IOrderState;
import nc.bs.pu.m21.state.ITransitionState;
import nc.bs.pu.m21.state.OrderCloseStateUtil;
import nc.bs.pu.m21.state.bill.OrderFinalCloseState;
import nc.bs.pu.m21.state.rule.CloseUpdateReceivePlanRule;
import nc.bs.pu.m21.state.rule.OpenUpdateReceivePlanRule;
import nc.bs.pu.m21.state.rule.RowCloseEventAfterRule;
import nc.bs.pu.m21.state.rule.RowCloseEventBeforeRule;
import nc.bs.pu.m21.state.rule.RowOpenEventAfterRule;
import nc.bs.pu.m21.state.rule.RowOpenEventBeforeRule;
import nc.bs.pu.m21.state.rule.StateMPPCtrlCheckRule;
import nc.bs.pu.m21.state.rule.UnChgStateRowFilterRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.tbb.BillOperationEnum;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>行关闭/打开
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-12 上午09:52:06
 */
public class OrderRowCloseState extends AbstractOrderRowState<OrderCloseVO>
    implements ITransitionState<OrderCloseVO, OrderVO> {

  private Integer value;

  public OrderRowCloseState(EnumActive value) {
    super(OrderItemVO.class, OrderItemVO.FISACTIVE, value.value());
    this.value = (Integer) value.value();
  }

  @Override
  public IOrderState<OrderVO> getTransitTargetState() {
    if (EnumActive.CLOSE.value().equals(this.value)) {
      return new OrderFinalCloseState(UFBoolean.TRUE);
    }
    else if (EnumActive.ACTIVE.value().equals(this.value)) {
      return new OrderFinalCloseState(UFBoolean.FALSE);
    }

    return null;
  }

  /**
   * @return value
   */
  public Integer getValue() {
    return this.value;
  }

  @Override
  public boolean isAutoTransitable(OrderCloseVO vo) {
    if (EnumActive.CLOSE.value().equals(this.value)) {
      return OrderCloseStateUtil.getInstance().isRowClosable(vo);
    }

    return OrderCloseStateUtil.getInstance().isRowOpenable(vo);
  }

  @Override
  public boolean isPrevStateValid(OrderCloseVO vo) {
    return true;
  }

  @Override
  public List<IOrderState<OrderCloseVO>> nextState() {
    List<IOrderState<OrderCloseVO>> nextStates =
        new ArrayList<IOrderState<OrderCloseVO>>();

    if (EnumActive.CLOSE.value().equals(this.value)) {
      // 同时进行到货关闭
      nextStates.add(new OrderArriveCloseState(UFBoolean.TRUE));
      // 同时进行入库关闭
      nextStates.add(new OrderStoreCloseState(UFBoolean.TRUE));
      // 同时进行发票关闭
      nextStates.add(new OrderInvoiceCloseState(UFBoolean.TRUE));
      // 同时进行付款关闭
      nextStates.add(new OrderPayCloseState(UFBoolean.TRUE));
    }
    else {
      // 同时进行到货打开
      nextStates.add(new OrderArriveCloseState(UFBoolean.FALSE));
      // 同时进行入库打开
      nextStates.add(new OrderStoreCloseState(UFBoolean.FALSE));
      // 同时进行发票打开
      nextStates.add(new OrderInvoiceCloseState(UFBoolean.FALSE));
      // 同时进行付款打开
      nextStates.add(new OrderPayCloseState(UFBoolean.FALSE));
    }
    return nextStates;
  }

  @Override
  public void setState(OrderCloseVO[] views) {
    AroundProcesser<OrderCloseVO> processer = null;
    if (EnumActive.CLOSE.value().equals(this.value)) {
      processer = new AroundProcesser<OrderCloseVO>(OrderPluginPoint.ROW_CLOSE);
    }
    else {
      processer = new AroundProcesser<OrderCloseVO>(OrderPluginPoint.ROW_OPEN);
    }
    this.addBeforeRule(processer);
    this.addAfterRule(processer);

    OrderCloseVO[] beforedViews = processer.before(views);
    super.setState(beforedViews);
    processer.after(beforedViews);
  }

  /**
   * @param value 要设置的 value
   */
  public void setValue(Integer value) {
    this.value = value;
  }

  private void addAfterRule(AroundProcesser<OrderCloseVO> processer) {
    if (EnumActive.CLOSE.value().equals(this.value)) {
      processer.addAfterRule(new CloseUpdateReceivePlanRule());
      // mengjian by 20150120
      if (SysInitGroupQuery.isMPPEnabled()) {
        processer.addAfterRule(new StateMPPCtrlCheckRule(POBillType.Order
            .getCode(), BillOperationEnum.CLOSE));
      }
      processer.addBeforeRule(new RowCloseEventAfterRule());
    }
    else if (EnumActive.ACTIVE.value().equals(this.value)) {
      processer.addAfterRule(new OpenUpdateReceivePlanRule());
      // mengjian by 20150120
      if (SysInitGroupQuery.isMPPEnabled()) {
        processer.addAfterRule(new StateMPPCtrlCheckRule(POBillType.Order
            .getCode(), BillOperationEnum.OPEN));
      }
      processer.addBeforeRule(new RowOpenEventAfterRule());
    }
  }

  private void addBeforeRule(AroundProcesser<OrderCloseVO> processer) {
    if (EnumActive.CLOSE.value().equals(this.value)) {
      processer.addBeforeRule(new UnChgStateRowFilterRule(
          OrderItemVO.FISACTIVE, EnumActive.CLOSE.value()));
      processer.addBeforeRule(new RowCloseEventBeforeRule());
    }
    else if (EnumActive.ACTIVE.value().equals(this.value)) {
      processer.addBeforeRule(new UnChgStateRowFilterRule(
          OrderItemVO.FISACTIVE, EnumActive.ACTIVE.value()));
      processer.addBeforeRule(new RowOpenEventBeforeRule());
    }
  }

}
