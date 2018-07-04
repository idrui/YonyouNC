package nc.vo.pu.tbb;

/**
 * 用于采购计划中的单据的审批、弃审、修订、关闭、打开等操作的标识
 * 
 * @since 6.0
 * @version 2011-3-15 下午04:29:55
 * @author yinfy
 */

public enum BillOperationEnum {

  /** 审批 */
  APPROVE(0),
  /** 关闭 */
  CLOSE(1),
  /** 删除 */
  DELETE(2),
  /** 修改 */
  MODIFY(3),
  /** 打开 */
  OPEN(4),
  /** 修订 */
  REVISE(5),
  /** 取消审批 */
  UNAPPROVE(6);

  private int value;

  private BillOperationEnum(int value) {
    this.value = value;
  }

  public int getValue() {
    return this.value;
  }
}
