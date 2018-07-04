/**
 * $$文件说明$$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-01-06 14:48:39
 */
package nc.vo.pu.m21.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单关闭类型
 * </ul>
 * <p>
 * ${tags}
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-01-06 14:48:39
 */
public class EnumCloseFlag extends NCMDEnum {
  /** 到货关闭 **/
  public static final EnumCloseFlag ARRIVE_CLOSE = MDEnum.valueOf(
      EnumCloseFlag.class, Integer.valueOf(2));

  /** 最终关闭 **/
  public static final EnumCloseFlag FINAL_CLOSE = MDEnum.valueOf(
      EnumCloseFlag.class, Integer.valueOf(0));

  /** 开票关闭 **/
  public static final EnumCloseFlag INVOICE_CLOSE = MDEnum.valueOf(
      EnumCloseFlag.class, Integer.valueOf(4));

  /** 付款关闭 **/
  public static final EnumCloseFlag PAY_CLOSE = MDEnum.valueOf(
      EnumCloseFlag.class, Integer.valueOf(5));

  /** 行关闭 **/
  public static final EnumCloseFlag ROW_CLOSE = MDEnum.valueOf(
      EnumCloseFlag.class, Integer.valueOf(1));

  /** 入库关闭 **/
  public static final EnumCloseFlag STOCK_CLOSE = MDEnum.valueOf(
      EnumCloseFlag.class, Integer.valueOf(3));

  public EnumCloseFlag(IEnumValue enumvalue) {
    super(enumvalue);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.md.model.impl.MDEnum#getReturnType()
   */
  @Override
  public int getReturnType() {
    return IType.TYPE_Integer;
  }

  /**
   * 父类方法重写
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
