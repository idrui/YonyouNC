package nc.bs.pu.m27.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购结算单的扩展插入点
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-8-30 下午05:13:32
 */
public enum SettlebillPluginPoint implements IPluginPoint {
  /**
   * 取消传存货核算
   */
  CANCEL_IA,
  /**
   * 取消传存货核算_IT
   */
  CANCEL_IA_IT,

  /**
   * 取消费用结算
   */
  CancelFeeSettleBP,
  /**
   * 取消费用结算_IT
   */
  CancelFeeSettleBP_IT,
  /**
   * 删除结算单
   */
  DELETE,
  /**
   * 删除结算单_IT
   */
  DELETE_IT, /**
   * 费用结算
   */
  FeeSettleBP, /**
   * 费用结算_IT
   */
  FeeSettleBP_IT,
  /**
   * 结算完毕保存结算单
   */
  SAVE,
  /**
   * 结算完毕保存结算单_IT
   */
  SAVE_IT,
  /**
   * 传存货核算
   */
  TO_IA,
  /**
   * 传存货核算_IT
   */
  TO_IA_IT;

  @Override
  public String getComponent() {
    return POBillType.SettleBill.getCode();
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
