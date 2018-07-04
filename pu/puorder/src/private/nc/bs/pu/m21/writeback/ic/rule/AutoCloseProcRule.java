/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-15 上午11:47:16
 */
package nc.bs.pu.m21.writeback.ic.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pu.m21.state.OrderCloseStateUtil;
import nc.bs.pu.m21.state.OrderStateMachine;
import nc.bs.pu.m21.state.row.OrderArriveCloseState;
import nc.bs.pu.m21.state.row.OrderStoreCloseState;
import nc.bs.pu.m21.writeback.OrderWriteBackCloseUtil;
import nc.impl.pu.m21.action.rule.close.ATPBeforeUpdateRule;
import nc.impl.pu.m21.action.rule.close.ATPUpdateRule;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.pu.m21.ic.m45.IOrderWriteBackPara;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m21.pub.OrderVOUtil;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 入库单回写进行自动关闭/打开处理
 * @scene
 * 入库单回写
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-1-15 上午11:47:16
 * @author zhaoyha
 */
public class AutoCloseProcRule implements ICompareRule<OrderViewVO> {

  private IOrderWriteBackPara[] wbParas;
  
  /** 存在入库环节的交易类型map, key为交易类型id, value为是否存在到货环节。为N或Y*/ 
  private Map<String, String> trantypeMap;

  public AutoCloseProcRule(IOrderWriteBackPara[] wbParas) {
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
    List<OrderViewVO> setStoreClose = new ArrayList<OrderViewVO>();
    // 打开
    List<OrderViewVO> setStoreOpen = new ArrayList<OrderViewVO>();
    // 到货打开
    List<OrderViewVO> setArriveOpen = new ArrayList<OrderViewVO>();
    for (OrderViewVO vo : vos) {
      if (this.isStoreClose(vo) || closeBIDSet.contains(vo.getPk_order_b())) {
        setStoreClose.add(vo);
      }
      else if (this.isArriveOpen(vo)) {
        setArriveOpen.add(vo);
      }
      else if (this.isStoreOpen(vo)) {
        setStoreOpen.add(vo);
      }
    }

    // 自动关闭
    if (!setStoreClose.isEmpty()) {
      OrderViewVO[] views = setStoreClose.toArray(new OrderViewVO[0]);
      OrderCloseVO[] closeVOs = this.getCloseVO(views, originVOs);
      this.close(closeVOs);
    }

    if (!setArriveOpen.isEmpty()) {
      OrderViewVO[] views = setArriveOpen.toArray(new OrderViewVO[0]);
      OrderCloseVO[] openVOs = this.getArriveOpenVO(views, originVOs);
      this.openArrive(openVOs);
    }

    // 自动打开
    if (!setStoreOpen.isEmpty()) {
      OrderViewVO[] views = setStoreOpen.toArray(new OrderViewVO[0]);
      OrderCloseVO[] openVOs = this.getCloseVO(views, originVOs);
      this.open(openVOs);
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
        new AroundProcesser<OrderCloseVO>(OrderPluginPoint.STORE_CLOSE);
    this.addRule(processer);
    processer.before(vos);

    OrderWriteBackCloseUtil util = OrderWriteBackCloseUtil.getInstance();
    Map<String, PUParaValue.po09> map = util.getPO09Map(vos);
    Set<OrderCloseVO> instanceSet = new HashSet<OrderCloseVO>();
    Set<OrderCloseVO> ontimeSet = new HashSet<OrderCloseVO>();
    util.splitVO(vos, map, instanceSet, ontimeSet);

    if (!instanceSet.isEmpty()) {
      OrderCloseVO[] instanceVOs = instanceSet.toArray(new OrderCloseVO[0]);
      OrderCloseStateUtil.getInstance().setbInstanceClose(true);
      OrderStoreCloseState state = new OrderStoreCloseState(UFBoolean.TRUE);
      new OrderStateMachine().setState(state, instanceVOs);
    }

    if (!ontimeSet.isEmpty()) {
      OrderCloseVO[] ontimeVOs = ontimeSet.toArray(new OrderCloseVO[0]);
      OrderCloseStateUtil.getInstance().setbInstanceClose(false);
      OrderStoreCloseState state = new OrderStoreCloseState(UFBoolean.TRUE);
      new OrderStateMachine().setState(state, ontimeVOs);
    }

    processer.after(vos);
  }

  private OrderCloseVO[] getArriveOpenVO(OrderViewVO[] vos,
      OrderViewVO[] originVOs) {
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
      closeVOs[i].setNaccumstorenum_pre(origin.getNaccumstorenum());
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
   * 判断是否需要到货打开
   * 1、如果到货关闭未关闭或数量小于累计到货数量，则直接返回false
   * 2、如果是基于原订单补货，则直接返回true。因为做退库，不用做退货
   * 3、如果订单对应的交易类型存在到货环节，则返回false；否则返回true
   * @param vo
   * @param trantype
   * @return
   */
  private boolean isArriveOpen(OrderViewVO vo) {
    if(!vo.getBarriveclose().booleanValue()
        || MathTool.absCompareTo(vo.getNnum(), vo.getNaccumarrvnum()) < 0){
      return false;
    }
    if (UFBoolean.TRUE.equals(vo.getBrefwhenreturn())) {
      return true;
    }
    return !this.judgeIsArrive(vo.getCtrantypeid());
  }

  /**
   * 方法功能描述：入库关闭，采购订单的累计入库主数量>=订单主数量时，采购订单将自动入库关闭。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-26 下午03:17:17
   */
  private boolean isStoreClose(OrderViewVO vo) {
    if (vo.getBstockclose().booleanValue()) {
      return false;
    }

    // 累计入库主数量>=订单主数量
    return MathTool.absCompareTo(vo.getNnum(), vo.getNaccumstorenum()) <= 0;
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
   * @time 2010-5-10 下午05:58:47
   */
  private boolean isStoreOpen(OrderViewVO vo) {
    if (!vo.getBstockclose().booleanValue()) {
      return false;
    }

    // 累计入库主数量<订单主数量
    return MathTool.absCompareTo(vo.getNnum(), vo.getNaccumstorenum()) > 0;
  }

  private void open(OrderCloseVO[] vos) {
    AroundProcesser<OrderCloseVO> processer =
        new AroundProcesser<OrderCloseVO>(null);
    this.addRule(processer);
    processer.before(vos);
    OrderStoreCloseState state = new OrderStoreCloseState(UFBoolean.FALSE);
    new OrderStateMachine().setState(state, vos);
    processer.after(vos);
  }

  private void openArrive(OrderCloseVO[] vos) {
    AroundProcesser<OrderCloseVO> processer =
        new AroundProcesser<OrderCloseVO>(null);
    this.addRule(processer);
    processer.before(vos);
    OrderArriveCloseState state = new OrderArriveCloseState(UFBoolean.FALSE);
    new OrderStateMachine().setState(state, vos);
    processer.after(vos);
  }

  /**
   * 根据传人的交易类型id，做判断是否存在到货环节
   * 
   * @param typeid
   * @return
   * @author luojw
   */
  private boolean judgeIsArrive(String typeid) {
    if(this.trantypeMap == null){
      this.trantypeMap = new HashMap<>();
      String pk_group = AppContext.getInstance().getPkGroup(); 
      DataAccessUtils utils = new DataAccessUtils();
      SqlBuilder sql = new SqlBuilder();
      sql.append("select " + PoTransTypeVO.CTRANTYPEID + ", " + PoTransTypeVO.BARRIVE +
          " from " + PUEntity.M21_TRANTYPE_TABLE + " where ");
      sql.append(PoTransTypeVO.BSTORE + " = 'Y' ");
      sql.append("and " + PoTransTypeVO.PK_GROUP, pk_group);
      IRowSet rs =  utils.query(sql.toString());
      while(rs.next()){
        this.trantypeMap.put(rs.getString(0), rs.getString(1));
      }
    }
    return UFBoolean.TRUE.toString().equals(this.trantypeMap.get(typeid));
  }
}
