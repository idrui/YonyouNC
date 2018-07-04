/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-22 下午09:54:03
 */
package nc.bs.pu.m21.writeback.pu.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.pu.m21.state.IOrderState;
import nc.bs.pu.m21.state.OrderCloseStateUtil;
import nc.bs.pu.m21.state.OrderStateMachine;
import nc.bs.pu.m21.state.row.OrderInvoiceCloseState;
import nc.bs.pu.m21.writeback.OrderWriteBackCloseUtil;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.ICompareRule;
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票回写自动关闭/打开处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-1-22 下午09:54:03
 */
public class AutoInvoiceCloseRule implements ICompareRule<OrderViewVO> {
  
  private IOrderState<OrderCloseVO> state;
  
  @Override
  public void process(OrderViewVO[] vos, OrderViewVO[] originVOs) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    // 关闭
    Set<OrderViewVO> setInvoiceClose = new HashSet<OrderViewVO>();
    // 打开
    Set<OrderViewVO> setInvoiceOpen = new HashSet<OrderViewVO>();
    for (OrderViewVO vo : vos) {
      if (this.isInvoiceClose(vo)) {
        setInvoiceClose.add(vo);
      }
      else if (this.isInvoiceOpen(vo)) {
        setInvoiceOpen.add(vo);
      }
    }

    // 自动关闭
    if (!setInvoiceClose.isEmpty()) {
      OrderViewVO[] views = setInvoiceClose.toArray(new OrderViewVO[0]);
      OrderCloseVO[] closeVOs = this.getCloseVO(views, originVOs);
      this.close(closeVOs);
    }

    // 自动打开
    if (!setInvoiceOpen.isEmpty()) {
      OrderViewVO[] views = setInvoiceOpen.toArray(new OrderViewVO[0]);
      OrderCloseVO[] openVOs = this.getCloseVO(views, originVOs);
      this.open(openVOs);
    }
    
    this.updateNames(vos, originVOs);
  }
  

  private void updateNames(OrderViewVO[] views, OrderViewVO[] originVOs) {
    String[] names =  new String[] {
        OrderItemVO.NACCUMINVOICENUM, OrderItemVO.NACCUMINVOICEMNY,
        OrderItemVO.NFEEMNY
      };
    OrderCloseVO[] vos = this.getCloseVO(views, originVOs);
    if(this.state != null){
      for(String name : names){
        this.state.addStateKeyToList(name);
      }
      this.state.updateStateKey(vos);
    }else{
      ViewUpdate<OrderViewVO> bo = new ViewUpdate<OrderViewVO>();
      bo.update(views, OrderItemVO.class, names);
    }
  }

  /**
   * 方法功能描述：关闭
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-26 下午03:18:28
   */
  private void close(OrderCloseVO[] vos) {
    OrderWriteBackCloseUtil util = OrderWriteBackCloseUtil.getInstance();
    Map<String, PUParaValue.po09> map = util.getPO09Map(vos);
    Set<OrderCloseVO> instanceSet = new HashSet<OrderCloseVO>();
    Set<OrderCloseVO> ontimeSet = new HashSet<OrderCloseVO>();
    util.splitVO(vos, map, instanceSet, ontimeSet);

    if (!instanceSet.isEmpty()) {
      this.setState(instanceSet, true);
    }

    if (!ontimeSet.isEmpty()) {
      this.setState(ontimeSet, false);
    }
  }
  
  private void setState(Set<OrderCloseVO> voSet, boolean instanceClose) {
    OrderCloseVO[] instanceVOs = voSet.toArray(new OrderCloseVO[0]);
    OrderCloseStateUtil.getInstance().setbInstanceClose(instanceClose);
    this.state = new OrderInvoiceCloseState(UFBoolean.TRUE, UFBoolean.FALSE);
    new OrderStateMachine().setState(this.state, instanceVOs);
  }

  /**
   * 方法功能描述：得到关闭视图
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-28 下午03:19:01
   */
  private OrderCloseVO[] getCloseVO(OrderViewVO[] vos, OrderViewVO[] originVOs) {
    OrderCloseVO[] closeVOs = new OrderCloseVO[vos.length];
    for (int i = 0; i < vos.length; ++i) {
      closeVOs[i] = new OrderCloseVO();
      closeVOs[i].setVO(vos[i].getVO(OrderHeaderVO.class));
      closeVOs[i].setVO(vos[i].getVO(OrderItemVO.class));
      OrderViewVO origin = this.getOrigView(vos[i], originVOs);
      closeVOs[i].setNaccuminvoicenum_pre(origin.getNaccuminvoicenum());
      closeVOs[i].setNaccuminvoicemny_pre(origin.getNaccuminvoicemny());
    }

    return closeVOs;
  }

  private OrderViewVO getOrigView(OrderViewVO vo, OrderViewVO[] originVOs) {
    if (!ArrayUtils.isEmpty(originVOs)) {
      for (OrderViewVO origin : originVOs) {
        if (vo.getPk_order_b().equals(origin.getPk_order_b())) {
          return origin;
        }
      }
    }
    return null;
  }

  /**
   * 方法功能描述：非费用折扣物料，累计开票数量>=订单主数量时，采购订单将自动开票关闭。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-28 下午03:18:52
   */
  private boolean isInvoiceClose(OrderViewVO vo) {
    if (vo.getBinvoiceclose().booleanValue()) {
      return false;
    }

    // 累计发票主数量>=订单主数量，因为费用发票不会写数量，所以费用发票永远不会满足条件，也就是费用发票不会自动关闭
    return MathTool.absCompareTo(vo.getNnum(), vo.getNaccuminvoicenum()) <= 0;
  }

  /**
   * 方法功能描述：改小时小于订单数时将自动打开
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-10 下午06:25:27
   */
  private boolean isInvoiceOpen(OrderViewVO vo) {
    if (!vo.getBinvoiceclose().booleanValue()) {
      return false;
    }

    // 累计发票主数量<订单主数量
    return MathTool.absCompareTo(vo.getNnum(), vo.getNaccuminvoicenum()) > 0;
  }

  /**
   * 方法功能描述：打开
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-10 下午06:26:35
   */
  private void open(OrderCloseVO[] vos) {
    this.state = new OrderInvoiceCloseState(UFBoolean.FALSE, UFBoolean.FALSE);
    new OrderStateMachine().setState(this.state, vos);
  }

}
