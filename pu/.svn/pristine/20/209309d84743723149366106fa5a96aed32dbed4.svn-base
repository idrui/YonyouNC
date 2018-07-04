package nc.bs.pu.m23.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * @description
 *              到货单的操作的二次开发插入点定义
 * @scene
 * @param 无
 * @since 6.3
 * @version 2010-1-13 上午10:26:37
 * @author hanbin
 */
public enum ArriveActionPlugInPoint implements IPluginPoint {
  /**
   * 反检
   */
  AntiQualityCheckAction,

  /**
   * 审批
   */
  ArriveApproveAction,

  /**
   * 删除
   */
  ArriveDeleteAction,

  /**
   * 新增保存
   */
  ArriveInsertAction,

  /**
   * 送审
   */
  ArriveSendAction,
  /**
   * 弃审
   */
  ArriveUnApproveAction,

  /**
   * 收回
   */
  ArriveUnSendAction,

  /**
   * 修改保存
   */
  ArriveUpdateAction,

  /**
   * 质检
   */
  QualityCheckAction,
  
  /**
   * 到货单查询
   */
  ArriveQueryAction,
  
  /**
   * 到货单质检查询
   */
  ArriveQcQueryAction;

  @Override
  public String getComponent() {
    return POBillType.Arrive.getCode();
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
