package nc.bs.pu.est.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>消耗汇总暂估二次开发的可插入点
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-12 上午11:14:43
 */
public enum VMIEstPluginPoint implements IPluginPoint {
  /**
   * 暂估的BP操作
   */
  EST_BP,
  /**
   * 费用暂估动作
   */
  FEE_EST_ACTION,
  /**
   * 费用暂估-BP
   */
  FEE_EST_BP,
  /**
   * 前台费用暂估查询暂估
   */
  FEE_QUERY,
  /**
   * 货物暂估动作
   */
  GOODS_EST_ACTION,
  /**
   * 前台货物(费用同时)暂估查询暂估
   */
  GOODS_QUERY,
  /**
   * 取消费用暂估-ACTION
   */
  UN_FEE_EST_ACTION,
  /**
   * 取消自动暂估
   */
  UNAUTO_EST,
  /**
   * 取消暂估-动作
   */
  UNEST_ACTION,
  /**
   * 取消暂估-BP
   */
  UNEST_BP,
  /**
   * 取消暂估查询-BP
   */
  UNEST_QUERY_BP;

  @Override
  public String getComponent() {
    return POBillType.VoiConsumeFinance.getCode();
  }

  @Override
  public String getModule() {
    return NCModule.PO.getSystemCode();
  }

  @Override
  public String getPoint() {
    return this.name();
  }

}
