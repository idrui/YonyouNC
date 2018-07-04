package nc.bs.pu.est.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单二次开发的可插入点
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-12 上午11:14:43
 */
public enum InitialEstPluginPoint implements IPluginPoint {
  /** 审批 **/
  APPROVE,
  /** 删除 **/
  DELETE,
  /** 删除-ACTION **/
  DELETE_ACTION,
  /** 暂估应付（BP） **/
  EST_AP,
  /** 保存的BP **/
  SAVE_BP,
  /** 前台新增保存 **/
  UI_INSERT,
  /** 前台更新保存 **/
  UI_UPDATE,
  /** 弃审 **/
  UNAPPROVE;

  @Override
  public String getComponent() {
    return POBillType.InitEstimate.getCode();
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
