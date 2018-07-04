/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-20 上午10:22:31
 */
package nc.bs.pu.m422x.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物资需求申请二次开发的可插入点
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-20 上午10:22:31
 */
public enum StoreReqAppPluginPoint implements IPluginPoint {
  /** 审批 **/
  APPROVE,
  /** 整单关闭 **/
  CLOSE,
  /** 删除 **/
  DELETE,
  /** 整单打开 **/
  OPEN,
  /**
   * 出库申请单拉单
   */
  PULL_4455,
  /** 行关闭 */
  ROW_CLOSE,
  /** 行打开 */
  ROW_OPEN,
  /** 保存的BP **/
  SAVE_BP,
  /** 前台新增保存 **/
  UI_INSERT,
  /** 前台更新保存 **/
  UI_UPDATE,
  /** 弃审 **/
  UNAPPROVE,
  /** 收回 **/
  UNSENDAPPROVE,
  /**
   * 回写出库申请单
   */
  WRITEBACK_4455,

  /**
   * 回写材料出库单
   */
  WRITEBACK_4D;

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.plugin.IPluginPoint#getComponent()
   */
  @Override
  public String getComponent() {
    return POBillType.MRBill.getCode();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.plugin.IPluginPoint#getModule()
   */
  @Override
  public String getModule() {
    return NCModule.PO.getSystemCode();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.plugin.IPluginPoint#getPoint()
   */
  @Override
  public String getPoint() {
    return this.name();
  }

}
