/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-17 下午09:23:17
 */
package nc.bs.pu.m21.writeback.pu.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.pu.m21.state.IOrderState;
import nc.bs.pu.m21.state.OrderCloseStateUtil;
import nc.bs.pu.m21.state.OrderStateMachine;
import nc.bs.pu.m21.state.row.OrderArriveCloseState;
import nc.bs.pu.m21.writeback.OrderWriteBackCloseUtil;
import nc.impl.pu.m21.action.rule.close.ATPBeforeUpdateRule;
import nc.impl.pu.m21.action.rule.close.ATPUpdateRule;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.pu.m21.pu.m23.IOrderWriteBackParaFor23;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m21.pub.OrderVOUtil;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单回写进行自动关闭/打开处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-1-17 下午09:23:17
 */
public class AutoArriveCloseRule implements ICompareRule<OrderViewVO> {
  private IOrderWriteBackParaFor23[] wbParas;
  
  private IOrderState<OrderCloseVO> state;
  
  public AutoArriveCloseRule(IOrderWriteBackParaFor23[] wbParas) {
    super();
    this.wbParas = wbParas;
  }

  @Override
  public void process(OrderViewVO[] vos, OrderViewVO[] originVOs) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    Set<String> closeBIDSet =
        OrderVOUtil.getInsance().getCloseBIDSet(this.wbParas);
    // 关闭
    Set<OrderViewVO> setArrClose = new HashSet<OrderViewVO>();
    // 打开
    Set<OrderViewVO> setArrOpen = new HashSet<OrderViewVO>();
    for (OrderViewVO vo : vos) {
      if (this.isArriveClose(vo) || closeBIDSet.contains(vo.getPk_order_b())) {
        setArrClose.add(vo);
      }
      else if (this.isArriveOpen(vo)) {
        setArrOpen.add(vo);
      }
    }

    // 自动关闭
    if (!setArrClose.isEmpty()) {
      OrderViewVO[] views = setArrClose.toArray(new OrderViewVO[0]);
      OrderCloseVO[] closeVOs = this.getCloseVO(views, originVOs);
      this.close(closeVOs);
    }

    // 自动打开
    if (!setArrOpen.isEmpty()) {
      OrderViewVO[] views = setArrOpen.toArray(new OrderViewVO[0]);
      OrderCloseVO[] openVOs = this.getCloseVO(views, originVOs);
      this.open(openVOs);
    }
    
    this.updateNames(vos, originVOs);
  }

  private void updateNames(OrderViewVO[] views, OrderViewVO[] originVOs) {
    String[] names =  new String[] {
        OrderItemVO.NACCUMARRVNUM, OrderItemVO.NBACKARRVNUM,
        OrderItemVO.NACCUMWASTNUM
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
   * 方法功能描述：规则
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-26 下午03:24:02
   */
  private void addRule(AroundProcesser<OrderCloseVO> processer) {
    processer.addBeforeRule(new ATPBeforeUpdateRule());
    processer.addAfterRule(new ATPUpdateRule());
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
    AroundProcesser<OrderCloseVO> processer =
        new AroundProcesser<OrderCloseVO>(null);
    this.addRule(processer);
    processer.before(vos);

    OrderWriteBackCloseUtil util = OrderWriteBackCloseUtil.getInstance();
    Map<String, PUParaValue.po09> map = util.getPO09Map(vos);
    Set<OrderCloseVO> instanceSet = new HashSet<OrderCloseVO>();
    Set<OrderCloseVO> ontimeSet = new HashSet<OrderCloseVO>();
    util.splitVO(vos, map, instanceSet, ontimeSet);

    if (!instanceSet.isEmpty()) {
      this.setState(instanceSet, true);
    }

    if (!ontimeSet.isEmpty()) {
      this.setState(instanceSet, false);
    }

    processer.after(vos);
  }

  private void setState(Set<OrderCloseVO> voSet, boolean instanceClose) {
    OrderCloseVO[] instanceVOs = voSet.toArray(new OrderCloseVO[0]);
    OrderCloseStateUtil.getInstance().setbInstanceClose(instanceClose);
    this.state = new OrderArriveCloseState(UFBoolean.TRUE, UFBoolean.FALSE);
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
   * @time 2010-4-26 下午03:17:23
   */
  private OrderCloseVO[] getCloseVO(OrderViewVO[] vos, OrderViewVO[] originVOs) {
    OrderCloseVO[] closeVOs = new OrderCloseVO[vos.length];
    for (int i = 0; i < vos.length; ++i) {
      closeVOs[i] = new OrderCloseVO();
      closeVOs[i].setVO(vos[i].getVO(OrderHeaderVO.class));
      closeVOs[i].setVO(vos[i].getVO(OrderItemVO.class));
      OrderViewVO origin = this.getOrigView(vos[i], originVOs);
      closeVOs[i].setNaccumarrvnum_pre(origin.getNaccumarrvnum());
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
   * 方法功能描述：到货关闭，采购订单累计到货主数量>=订单主数量时，采购订单将自动到货关闭。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-26 下午07:00:35
   */
  private boolean isArriveClose(OrderViewVO vo) {
    if (vo.getBarriveclose().booleanValue()) {
      return false;
    }

    // 累计到货主数量>=订单主数量
    return MathTool.absCompareTo(vo.getNnum(), vo.getNaccumarrvnum()) <= 0;
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
   * @time 2010-5-10 下午04:55:54
   */
  private boolean isArriveOpen(OrderViewVO vo) {
    if (!vo.getBarriveclose().booleanValue()) {
      return false;
    }

    // 累计到货主数量<订单主数量
    return MathTool.absCompareTo(vo.getNnum(), vo.getNaccumarrvnum()) > 0;
  }

  /**
   * 方法功能描述：打开
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-10 下午05:07:54
   */
  private void open(OrderCloseVO[] vos) {
    AroundProcesser<OrderCloseVO> processer =
        new AroundProcesser<OrderCloseVO>(null);
    this.addRule(processer);
    processer.before(vos);
    this.state = new OrderArriveCloseState(UFBoolean.FALSE, UFBoolean.FALSE);
    new OrderStateMachine().setState(this.state, vos);
    processer.after(vos);
  }
}
