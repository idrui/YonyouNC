/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-12 上午11:14:43
 */
package nc.bs.pu.m25.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购发票二次开发的可插入点
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-12 上午11:14:43
 */
public enum InvoicePluginPoint implements IPluginPoint {
  /**
   * 审批
   */
  APPROVE,
  /**
   * 取消传应付
   */
  CANCEL_SEND_AP,
  /**
   * 删除虚拟发票
   */
  DEL_VIRTUAL,
  /**
   * 删除
   */
  DELETE,
  /**
   * 删除-IMPL
   */
  DELETE_IMPL,
  /**
   * 冻结
   */
  FREEZE,
  /**
   * 生成虚拟发票
   */
  GEN_VIRTUAL,
  /**
   * 生成虚拟发票_IT
   */
  GEN_VIRTUAL_IT,
  /**
   * 采购入库单推式保存
   */
  M45_PUASHSAVE,
  /**
   * 保存的BP
   */
  SAVE_BP,
  /**
   * 传应付
   */
  SEND_AP,
  /**
   * 结算(匹配)后自动传应付
   */
  SEND_AP_AFTER_SETTLE,
  /**
   * 传应付-IMPL
   */
  SEND_AP_IMPL,
  /**
   * 前台新增保存
   */
  UI_INSERT,
  /**
   * 前台更新保存
   */
  UI_UPDATE,
  /**
   * 弃审
   */
  UNAPPROVE,
  /**
   * 解冻
   */
  UNFREEZE, /**
   * 收回
   */
  UNSENDAPPROVE;

  @Override
  public String getComponent() {
    return POBillType.Invoice.getCode();
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
