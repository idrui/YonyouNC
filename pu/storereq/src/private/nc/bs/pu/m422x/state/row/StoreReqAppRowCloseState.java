/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-28 上午11:25:02
 */
package nc.bs.pu.m422x.state.row;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.m422x.plugin.StoreReqAppPluginPoint;
import nc.bs.pu.m422x.state.bill.StoreReqAppBillCloseState;
import nc.bs.pu.m422x.state.rule.RowCloseEventAfterRule;
import nc.bs.pu.m422x.state.rule.RowCloseEventBeforeRule;
import nc.bs.pu.m422x.state.rule.RowOpenEventAfterRule;
import nc.bs.pu.m422x.state.rule.RowOpenEventBeforeRule;
import nc.bs.pu.m422x.state.rule.UnChgStateRowFilterRule;
import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.bill.state.ITransitionState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m422x.entity.StoreReqAppCloseVO;
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
 * <li>行关闭/打开
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-28 上午11:25:02
 */
public class StoreReqAppRowCloseState extends
    AbstractRowState<StoreReqAppCloseVO> implements
    ITransitionState<StoreReqAppCloseVO, StoreReqAppVO> {

  private UFBoolean value;

  /**
   * StoreReqAppRowCloseState 的构造子
   * 
   * @param stateValue
   */
  public StoreReqAppRowCloseState(UFBoolean stateValue) {
    super(StoreReqAppItemVO.class, StoreReqAppItemVO.BCLOSE, stateValue);
    this.value = stateValue;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.scmpub.bill.state.ITransitionState#getTransitTargetState()
   */
  @Override
  public IState<StoreReqAppVO> getTransitTargetState() {
    if (UFBoolean.TRUE.equals(this.value)) {
      return new StoreReqAppBillCloseState(
          (Integer) EnumBillStatus.CLOSE.value());
    }

    return new StoreReqAppBillCloseState(
        (Integer) POEnumBillStatus.APPROVE.value());
  }

  /**
   * @return value
   */
  public UFBoolean getValue() {
    return this.value;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.scmpub.bill.state.IState#isAutoTransitable(java.lang.Object)
   */
  @Override
  public boolean isAutoTransitable(StoreReqAppCloseVO vo) {
    return true;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.scmpub.bill.state.IState#isPrevStateValid(java.lang.Object)
   */
  @Override
  public boolean isPrevStateValid(StoreReqAppCloseVO vo) {
    return true;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.scmpub.bill.state.IState#next()
   */
  @Override
  public List<IState<StoreReqAppCloseVO>> next() {
    return new ArrayList<IState<StoreReqAppCloseVO>>();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.scmpub.bill.state.AbstractRowState#setState(E[])
   */
  @Override
  public void setState(StoreReqAppCloseVO[] views) {
    AroundProcesser<StoreReqAppCloseVO> processer = null;
    if (this.value.booleanValue()) {
      processer =
          new AroundProcesser<StoreReqAppCloseVO>(
              StoreReqAppPluginPoint.ROW_CLOSE);
    }
    else {
      processer =
          new AroundProcesser<StoreReqAppCloseVO>(
              StoreReqAppPluginPoint.ROW_OPEN);
    }
    this.addRule(processer);

    StoreReqAppCloseVO[] beforedViews = processer.before(views);

    if (ArrayUtils.isEmpty(beforedViews)) {
      return;
    }

    super.setState(beforedViews);
    processer.after(beforedViews);
  }

  /**
   * @param value
   *          要设置的 value
   */
  public void setValue(UFBoolean value) {
    this.value = value;
  }

  private void addRule(AroundProcesser<StoreReqAppCloseVO> processer) {
    if (this.value.booleanValue()) {
      processer.addBeforeRule(new UnChgStateRowFilterRule(UFBoolean.TRUE));
      // 行关闭前事件
      processer.addBeforeRule(new RowCloseEventBeforeRule());
      // 行关闭后事件
      processer.addAfterRule(new RowCloseEventAfterRule());
    }
    else {
      processer.addBeforeRule(new UnChgStateRowFilterRule(UFBoolean.FALSE));
      // 行打开前事件
      processer.addBeforeRule(new RowOpenEventBeforeRule());
      // 行打开后事件
      processer.addAfterRule(new RowOpenEventAfterRule());
    }
  }

}
