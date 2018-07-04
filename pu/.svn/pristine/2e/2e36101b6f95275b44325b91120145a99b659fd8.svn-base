/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-6 上午10:34:59
 */
package nc.bs.pu.m21.state.row;

import java.util.List;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pu.m21.state.AbstractOrderRowState;
import nc.bs.pu.m21.state.IOrderState;
import nc.bs.pu.m21.state.ITransitionState;
import nc.bs.pu.m21.state.bill.OrderFinalCloseState;
import nc.bs.pu.m21.state.rule.RowOpenEventAfterRule;
import nc.bs.pu.m21.state.rule.RowOpenEventBeforeRule;
import nc.bs.pu.m21.state.rule.StateMPPCtrlCheckRule;
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
 * <li>在到货、入库、发票、付款其中之一打开时，行打开
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-6 上午10:34:59
 */
public class OrderRowAutoOpenState extends AbstractOrderRowState<OrderCloseVO>
    implements ITransitionState<OrderCloseVO, OrderVO> {

  public OrderRowAutoOpenState() {
    super(OrderItemVO.class, OrderItemVO.FISACTIVE, EnumActive.ACTIVE.value());
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.scmpub.bill.state.ITransitionState#getTransitTargetState()
   */
  @Override
  public IOrderState<OrderVO> getTransitTargetState() {
    return new OrderFinalCloseState(UFBoolean.FALSE);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.scmpub.bill.state.IOrderState#isAutoTransitable(java.lang.Object)
   */
  @Override
  public boolean isAutoTransitable(OrderCloseVO vo) {
    return true;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.scmpub.bill.state.IOrderState#isPrevStateValid(java.lang.Object)
   */
  @Override
  public boolean isPrevStateValid(OrderCloseVO vo) {
    return true;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.scmpub.bill.state.IOrderState#next()
   */
  @Override
  public List<IOrderState<OrderCloseVO>> nextState() {
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.scmpub.bill.state.AbstractRowState#setState(E[])
   */
  @Override
  public void setState(OrderCloseVO[] views) {
    AroundProcesser<OrderCloseVO> processer =
        new AroundProcesser<OrderCloseVO>(OrderPluginPoint.AUTO_ROW_OPEN);
    this.addBeforeRule(processer);
    this.addAfterRule(processer);

    processer.before(views);
    super.setState(views);
    processer.after(views);
  }

  private void addAfterRule(AroundProcesser<OrderCloseVO> processer) {
    // mengjian by 20150120
    if (SysInitGroupQuery.isMPPEnabled()) {
      processer.addAfterRule(new StateMPPCtrlCheckRule(POBillType.Order
          .getCode(), BillOperationEnum.OPEN));
    }
    processer.addAfterRule(new RowOpenEventAfterRule());
  }

  private void addBeforeRule(AroundProcesser<OrderCloseVO> processer) {
    processer.addBeforeRule(new RowOpenEventBeforeRule());
  }
}
