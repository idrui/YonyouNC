/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-19 上午08:39:16
 */
package nc.vo.pu.m422x.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-19 上午08:39:16
 */
public class EnumBillStatus extends POEnumBillStatus {

  /** 关闭 */
  public static final EnumBillStatus CLOSE = MDEnum.valueOf(
      EnumBillStatus.class, Integer.valueOf(5));

  /**
   * EnumBillStatus 的构造子
   * 
   * @param enumValue
   */
  public EnumBillStatus(IEnumValue enumValue) {
    super(enumValue);
  }

}
