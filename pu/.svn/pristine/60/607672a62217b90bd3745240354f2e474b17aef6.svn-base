package nc.vo.pu.m4201.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * <p>
 * <b>传应付标志</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author wangyf
 * @time 2010-1-26 上午11:25:10
 */
public class EnumToAPFlag extends NCMDEnum {
  /**
   * 确认应付
   */
  public static final EnumToAPFlag ConfirmToAP = MDEnum.valueOf(
      EnumToAPFlag.class, Integer.valueOf(2));

  /**
   * 暂估应付
   */
  public static final EnumToAPFlag EstimateToAP = MDEnum.valueOf(
      EnumToAPFlag.class, Integer.valueOf(1));

  /**
   * 未传应付
   */
  public static final EnumToAPFlag NotToAP = MDEnum.valueOf(EnumToAPFlag.class,
      Integer.valueOf(0));

  public EnumToAPFlag(IEnumValue value) {
    super(value);
  }

  @Override
  public int getReturnType() {
    return IType.TYPE_Integer;
  }
}
