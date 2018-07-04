/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-12 ����09:45:45
 */
package nc.bs.pu.m21.state.bill;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pu.m21.state.AbstractOrderBillState;
import nc.bs.pu.m21.state.IOrderState;
import nc.bs.pu.m21.state.ITransitionState;
import nc.bs.pu.m21.state.OrderCloseStateUtil;
import nc.bs.pu.m21.state.row.OrderRowCloseState;
import nc.bs.pu.m21.state.rule.FinalCloseEventAfterRule;
import nc.bs.pu.m21.state.rule.FinalCloseEventBeforeRule;
import nc.bs.pu.m21.state.rule.FinalOpenEventAfterRule;
import nc.bs.pu.m21.state.rule.FinalOpenEventBeforeRule;
import nc.bs.pu.m21.state.rule.OrderWriteBackForEc56;
import nc.bs.pu.m21.state.rule.UnChgStateBillFilterRule;
import nc.bs.pu.m21.state.rule.UpdateCloseDateRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>Ӧ�����������ɶ��������չرմ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-12 ����09:45:45
 */
public class OrderFinalCloseState extends AbstractOrderBillState<OrderVO> implements
    ITransitionState<OrderVO, OrderCloseVO> {

  private UFBoolean value;

  public OrderFinalCloseState(UFBoolean value) {
    super(OrderHeaderVO.BFINALCLOSE, value);
    this.value = value;
  }

  @Override
  public IOrderState<OrderCloseVO> getTransitTargetState() {
    if (this.value.booleanValue()) {
      return new OrderRowCloseState(EnumActive.CLOSE);
    }

    return new OrderRowCloseState(EnumActive.ACTIVE);
  }

  /**
   * @return value
   */
  public UFBoolean getValue() {
    return this.value;
  }

  @Override
  public boolean isAutoTransitable(OrderVO vo) {
    if (this.value.booleanValue()) {
      return OrderCloseStateUtil.getInstance().isFinalClosable(vo);
    }

    return OrderCloseStateUtil.getInstance().isOpenable(vo);
  }

  @Override
  public boolean isPrevStateValid(OrderVO vo) {
    return true;
  }

  @Override
  public List<IOrderState<OrderVO>> nextState() {
    return new ArrayList<IOrderState<OrderVO>>();
  }

  @Override
  public void setState(OrderVO[] vos) {
    AroundProcesser<OrderVO> processer = null;
    if (this.value.booleanValue()) {
      processer = new AroundProcesser<OrderVO>(OrderPluginPoint.FINAL_CLOSE);
    }
    else {
      processer = new AroundProcesser<OrderVO>(OrderPluginPoint.FINAL_OPEN);
    }
    this.addRule(processer);
    OrderVO[] beforedvos = processer.before(vos);
    if (ArrayUtils.isEmpty(beforedvos)) {
      return;
    }
    super.setState(beforedvos);
    processer.after(beforedvos);
  }

  /**
   * @param value Ҫ���õ� value
   */
  public void setValue(UFBoolean value) {
    this.value = value;
  }

  private void addRule(AroundProcesser<OrderVO> processer) {
    processer.addBeforeRule(new UnChgStateBillFilterRule(this.value));
    // ��¼���չر�����
    processer.addAfterRule(new UpdateCloseDateRule(this.value));
    // ���������Դ�ڵ���������Ҫ�����رյ������񶩵�
    processer.addAfterRule(new OrderWriteBackForEc56(this.value));

    if (this.value.booleanValue()) {
      processer.addBeforeRule(new FinalCloseEventBeforeRule());
      processer.addAfterRule(new FinalCloseEventAfterRule());
    }
    else {
      processer.addBeforeRule(new FinalOpenEventBeforeRule());
      processer.addAfterRule(new FinalOpenEventAfterRule());
    }
  }

}
