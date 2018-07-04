/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-12 上午09:50:33
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>开票关闭的状态处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-12 上午09:50:33
 */
public class OrderInvoiceCloseState extends AbstractOrderRowState<OrderCloseVO> {

  private UFBoolean value;

  public OrderInvoiceCloseState(UFBoolean value) {
    super(OrderItemVO.class, OrderItemVO.BINVOICECLOSE, value);
    this.value = value;
  }
  
  public OrderInvoiceCloseState(UFBoolean value, UFBoolean isAutoUpdate) {
    super(OrderItemVO.class, OrderItemVO.BINVOICECLOSE, value, isAutoUpdate);
    this.value = value;
  }

  @Override
  public boolean isAutoTransitable(OrderCloseVO vo) {
    return true;
  }

  @Override
  public boolean isPrevStateValid(OrderCloseVO vo) {
    return true;
  }

  @Override
  public List<IOrderState<OrderCloseVO>> nextState() {
    List<IOrderState<OrderCloseVO>> nextStates =
        new ArrayList<IOrderState<OrderCloseVO>>();
    if (this.value.booleanValue()) {
      // 可能同时进行行关闭
      nextStates.add(new OrderRowCloseState(EnumActive.CLOSE));
    }
    else {
      // 打开后同时进行行打开
      nextStates.add(new OrderRowAutoOpenState());
    }
    return nextStates;
  }

  @Override
  public void setState(OrderCloseVO[] views) {
    AroundProcesser<OrderCloseVO> processer = null;
    if (this.value.booleanValue()) {
      processer =
          new AroundProcesser<OrderCloseVO>(OrderPluginPoint.INVOICE_CLOSE);
    }
    else {
      processer =
          new AroundProcesser<OrderCloseVO>(OrderPluginPoint.INVOICE_OPEN);
    }

    this.addRule(processer);

    OrderCloseVO[] beforedViews = processer.before(views);
    super.setState(beforedViews);
    processer.after(beforedViews);
  }

  private void addRule(AroundProcesser<OrderCloseVO> processer) {
    if (this.value.booleanValue()) {
      processer.addBeforeRule(new UnChgStateRowFilterRule(
          OrderItemVO.BINVOICECLOSE, UFBoolean.TRUE));
    }
    else {
      processer.addBeforeRule(new UnChgStateRowFilterRule(
          OrderItemVO.BINVOICECLOSE, UFBoolean.FALSE));
    }
    // 采购计划控制
    // mengjian by 20150120
    if (SysInitGroupQuery.isMPPEnabled()) {
      processer.addAfterRule(new StateMPPCtrlCheckRule(POBillType.Invoice
          .getCode(), this.value.booleanValue() ? BillOperationEnum.CLOSE
          : BillOperationEnum.OPEN));
    }
  }

}
