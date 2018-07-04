/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-28 上午10:54:35
 */
package nc.bs.pu.m20.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单的扩展插入点枚举
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-28 上午10:54:35
 */
public enum PraybillPluginPoint implements IPluginPoint {

  /**
   * 审批
   */
  APPROVE,

  /**
   * 整单关闭
   */
  CLOSE_BILL,
  /**
   * 行关闭
   */
  CLOSE_ROW,

  /**
   * 删除
   */
  DELETE,

  /**
   * 新增
   */
  INSERT,

  /**
   * 进口合同回写订单数量
   */
  ITREWRITENUM,
  /**
   * 整单打开
   */
  OPEN_BILL,

  /**
   * 行打开
   */
  OPEN_ROW,

  /**
   * 发布到电子商务
   */
  PUBLISH_TO_EC,
  /**
   * 采购订单回写订单数量
   */
  REWRITENUM,
  /**
   * 弃审
   */
  UNAPPROVE,

  /**
   * 取消发布到电子商务
   */
  UNPUBLISH_TO_EC,

  /**
   * 收回
   */
  UNSENDAPPROVE,

  /**
   * 修改
   */
  UPDATE;

  @Override
  public String getComponent() {
    return POBillType.PrayBill.getCode();
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
