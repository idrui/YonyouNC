/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-19 下午04:31:45
 */
package nc.vo.pu.m25.enumeration;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票的流程平台动作名称
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-19 下午04:31:45
 */
public enum InvoiceBillAction {
  /** 审批 **/
  APPROVE,
  /** 取消传应付 **/
  CANCELSENDAP,
  /** 删除 **/
  DISCARD,
  /** 库存采购入库单推式保存 **/
  M45PUSHSAVEM25,
  /** 送审 **/
  SAVE,
  /** 保存 **/
  SAVEBASE,
  /** 传应付 **/
  SENDAP,
  /** 弃审 **/
  UNAPPROVE;
}
