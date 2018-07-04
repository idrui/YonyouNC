/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-28 上午11:18:06
 */
package nc.bs.pu.m422x.state.bill;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.m422x.plugin.StoreReqAppPluginPoint;
import nc.bs.pu.m422x.state.row.StoreReqAppRowCloseState;
import nc.bs.pu.m422x.state.rule.FinalCloseEventAfterRule;
import nc.bs.pu.m422x.state.rule.FinalCloseEventBeforeRule;
import nc.bs.pu.m422x.state.rule.FinalOpenEventAfterRule;
import nc.bs.pu.m422x.state.rule.FinalOpenEventBeforeRule;
import nc.bs.pu.m422x.state.rule.UnChgStateBillFilterRule;
import nc.impl.pubapp.bill.state.AbstractBillState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.bill.state.ITransitionState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m422x.entity.StoreReqAppCloseVO;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.enumeration.EnumBillStatus;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>整单关闭/打开
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-28 上午11:18:06
 */
public class StoreReqAppBillCloseState extends AbstractBillState<StoreReqAppVO>
    implements ITransitionState<StoreReqAppVO, StoreReqAppCloseVO> {

  private Integer value;

  /**
   * StoreReqAppBillCloseState 的构造子
   * 
   * @param stateValue
   */
  public StoreReqAppBillCloseState(Integer stateValue) {
    super(StoreReqAppHeaderVO.FBILLSTATUS, stateValue);
    this.value = stateValue;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.scmpub.bill.state.ITransitionState#getTransitTargetState()
   */
  @Override
  public IState<StoreReqAppCloseVO> getTransitTargetState() {
    if (EnumBillStatus.CLOSE.value().equals(this.value)) {
      return new StoreReqAppRowCloseState(UFBoolean.TRUE);
    }

    return new StoreReqAppRowCloseState(UFBoolean.FALSE);
  }

  /**
   * @return value
   */
  public Integer getValue() {
    return this.value;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.scmpub.bill.state.IState#isAutoTransitable(java.lang.Object)
   */
  @Override
  public boolean isAutoTransitable(StoreReqAppVO vo) {
    if (EnumBillStatus.CLOSE.value().equals(this.value)) {
      return this.isAllClose(vo);
    }
    return true;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.scmpub.bill.state.IState#isPrevStateValid(java.lang.Object)
   */
  @Override
  public boolean isPrevStateValid(StoreReqAppVO vo) {
    return true;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.scmpub.bill.state.IState#next()
   */
  @Override
  public List<IState<StoreReqAppVO>> next() {
    return new ArrayList<IState<StoreReqAppVO>>();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.scmpub.bill.state.AbstractBillState#setState(E[])
   */
  @Override
  public void setState(StoreReqAppVO[] bills) {
    AroundProcesser<StoreReqAppVO> processer = null;
    if (EnumBillStatus.CLOSE.toInteger().equals(this.value)) {
      processer =
          new AroundProcesser<StoreReqAppVO>(StoreReqAppPluginPoint.CLOSE);
    }
    else {
      processer =
          new AroundProcesser<StoreReqAppVO>(StoreReqAppPluginPoint.OPEN);
    }
    this.addRule(processer);

    StoreReqAppVO[] beforedBills = processer.before(bills);

    if (ArrayUtils.isEmpty(beforedBills)) {
      return;
    }

    super.setState(beforedBills);
    processer.after(beforedBills);
  }

  /**
   * @param value
   *          要设置的 value
   */
  public void setValue(Integer value) {
    this.value = value;
  }

  private void addRule(AroundProcesser<StoreReqAppVO> processer) {
    if (EnumBillStatus.CLOSE.value().equals(this.value)) {
      processer.addBeforeRule(new UnChgStateBillFilterRule(
          (Integer) EnumBillStatus.CLOSE.value()));
      // 整单关闭前事件
      processer.addBeforeRule(new FinalCloseEventBeforeRule());
      // 整单关闭后事件
      processer.addAfterRule(new FinalCloseEventAfterRule());
    }
    else {
      processer.addBeforeRule(new UnChgStateBillFilterRule(
          (Integer) POEnumBillStatus.APPROVE.value()));
      // 整单打开前事件
      processer.addBeforeRule(new FinalOpenEventBeforeRule());
      // 整单打开后事件
      processer.addAfterRule(new FinalOpenEventAfterRule());
    }
  }

  private boolean isAllClose(StoreReqAppVO vo) {
    for (StoreReqAppItemVO itemVO : vo.getBVO()) {
      if (UFBoolean.FALSE.equals(itemVO.getBclose())) {
        return false;
      }
    }
    return true;
  }
}
