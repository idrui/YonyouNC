package nc.bs.pu.m24.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格结算单的扩展插入点
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-8-30 下午05:32:17
 */
public enum PricestlPluginPoint implements IPluginPoint {
  /**
   * 审核
   */
  APPROVE,

  /**
   * 删除
   */
  DELETE,

  /**
   * 新增保存
   */
  INSERT,

  /**
   * 送审
   */
  SEND_APPROVE,

  /**
   * 收回
   */
  UN_SEND_APPROVE,

  /**
   * 取消审核
   */
  UNAPPROVE,

  /**
   * 更新保存
   */
  UPDATE;

  @Override
  public String getComponent() {
    return POBillType.PriceStl.getCode();
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
