/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-8 ����04:05:13
 */
package nc.bs.pu.m21.writeback.arap.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.pu.m21.state.OrderCloseStateUtil;
import nc.bs.pu.m21.state.OrderStateMachine;
import nc.bs.pu.m21.state.row.OrderPayCloseState;
import nc.bs.pu.m21.writeback.OrderWriteBackCloseUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-8 ����04:05:13
 */
public class AutoPayCloseRule implements IRule<OrderViewVO> {

  public void close(OrderCloseVO[] vos) {
    OrderWriteBackCloseUtil util = OrderWriteBackCloseUtil.getInstance();
    Map<String, PUParaValue.po09> map = util.getPO09Map(vos);
    Set<OrderCloseVO> instanceSet = new HashSet<OrderCloseVO>();
    Set<OrderCloseVO> ontimeSet = new HashSet<OrderCloseVO>();
    util.splitVO(vos, map, instanceSet, ontimeSet);

    if (!instanceSet.isEmpty()) {
      OrderCloseVO[] instanceVOs = instanceSet.toArray(new OrderCloseVO[0]);
      OrderCloseStateUtil.getInstance().setbInstanceClose(true);
      OrderPayCloseState state = new OrderPayCloseState(UFBoolean.TRUE);
      new OrderStateMachine().setState(state, instanceVOs);
    }

    if (!ontimeSet.isEmpty()) {
      OrderCloseVO[] ontimeVOs = ontimeSet.toArray(new OrderCloseVO[0]);
      OrderCloseStateUtil.getInstance().setbInstanceClose(false);
      OrderPayCloseState state = new OrderPayCloseState(UFBoolean.TRUE);
      new OrderStateMachine().setState(state, ontimeVOs);
    }
  }

  public void open(OrderCloseVO[] vos) {
    OrderPayCloseState state = new OrderPayCloseState(UFBoolean.FALSE);
    new OrderStateMachine().setState(state, vos);
  }

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderViewVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    // �ر�
    Set<OrderViewVO> setPayClose = new HashSet<OrderViewVO>();
    // ��
    Set<OrderViewVO> setPayOpen = new HashSet<OrderViewVO>();
    for (OrderViewVO vo : vos) {
      if (this.isPayClose(vo)) {
        setPayClose.add(vo);
      }
      else if (this.isPayOpen(vo)) {
        setPayOpen.add(vo);
      }
    }

    // �Զ��ر�
    if (!setPayClose.isEmpty()) {
      OrderViewVO[] views = setPayClose.toArray(new OrderViewVO[0]);
      OrderCloseVO[] closeVOs = this.getCloseVO(views);
      this.close(closeVOs);
    }

    // �Զ���
    if (!setPayOpen.isEmpty()) {
      OrderViewVO[] views = setPayOpen.toArray(new OrderViewVO[0]);
      OrderCloseVO[] openVOs = this.getCloseVO(views);
      this.open(openVOs);
    }
  }

  private OrderCloseVO[] getCloseVO(OrderViewVO[] vos) {
    OrderCloseVO[] closeVOs = new OrderCloseVO[vos.length];
    for (int i = 0; i < vos.length; ++i) {
      closeVOs[i] = new OrderCloseVO();
      closeVOs[i].setVO(vos[i].getVO(OrderHeaderVO.class));
      closeVOs[i].setVO(vos[i].getVO(OrderItemVO.class));
    }

    return closeVOs;
  }

  /**
   * ����������������Ʊ�رգ����Ҷ�����Ӧ�����вɹ���Ʊ���ۼƿ�Ʊ�����ȫ����������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-8 ����04:38:47
   */
  private boolean isPayClose(OrderViewVO vo) {
    if (!vo.getBinvoiceclose().booleanValue()) {
      return false;
    }
    if (vo.getBpayclose().booleanValue()) {
      return false;
    }

    return MathTool.absCompareTo(vo.getNaccuminvoicemny(), vo
        .getNacccancelinvmny()) <= 0;

  }

  private boolean isPayOpen(OrderViewVO vo) {
    if (!vo.getBpayclose().booleanValue()) {
      return false;
    }

    return MathTool.absCompareTo(vo.getNaccuminvoicemny(), vo
        .getNacccancelinvmny()) > 0;
  }
}
