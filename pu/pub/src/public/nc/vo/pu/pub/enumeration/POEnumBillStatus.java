/**
 * $文件说明$
 * 
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-2 下午01:25:42
 */
package nc.vo.pu.pub.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购单据状态枚举公共类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-2 下午01:25:42
 */
public class POEnumBillStatus extends NCMDEnum {
  /** 审批通过 */
  public static final POEnumBillStatus APPROVE =
      MDEnum.valueOf(POEnumBillStatus.class, Integer.valueOf(3));

  /** 审批中 */
  public static final POEnumBillStatus APPROVING =
      MDEnum.valueOf(POEnumBillStatus.class, Integer.valueOf(2));

  /** 提交 */
  public static final POEnumBillStatus COMMIT =
      MDEnum.valueOf(POEnumBillStatus.class, Integer.valueOf(1));

  /** 自由 */
  public static final POEnumBillStatus FREE =
      MDEnum.valueOf(POEnumBillStatus.class, Integer.valueOf(0));

  /** 审批未通过 */
  public static final POEnumBillStatus NOPASS =
      MDEnum.valueOf(POEnumBillStatus.class, Integer.valueOf(4));

  /**
   * POEnumBillStatus 的构造子
   * 
   * @param enumvalue
   */
  public POEnumBillStatus(IEnumValue enumvalue) {
    super(enumvalue);
  }

  @Override
  public int getReturnType() {
    return IType.TYPE_INT;
  }
}
