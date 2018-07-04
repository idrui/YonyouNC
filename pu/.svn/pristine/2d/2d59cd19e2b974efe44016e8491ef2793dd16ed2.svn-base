package nc.vo.pu.m4201.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * <p>
 * <b>传成本标志</b>
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
public class EnumToIAFlag extends NCMDEnum {
  /**
   * 确认成本
   */
  public static final EnumToIAFlag ConfirmToIA = MDEnum.valueOf(
      EnumToIAFlag.class, Integer.valueOf(2));

  /**
   * 确认利润中心成本
   */
  public static final EnumToIAFlag ConfirmToPC = MDEnum.valueOf(
      EnumToIAFlag.class, Integer.valueOf(4));

  /**
   * 暂估成本
   */
  public static final EnumToIAFlag EstimateToIA = MDEnum.valueOf(
      EnumToIAFlag.class, Integer.valueOf(1));

  /**
   * 暂估利润中心成本
   */
  public static final EnumToIAFlag EstimateToPC = MDEnum.valueOf(
      EnumToIAFlag.class, Integer.valueOf(5));

  /**
   * 未传成本
   */
  public static final EnumToIAFlag NotToIA = MDEnum.valueOf(EnumToIAFlag.class,
      Integer.valueOf(0));

  /**
   * 未传利润中心成本
   */
  public static final EnumToIAFlag NotToPC = MDEnum.valueOf(EnumToIAFlag.class,
      Integer.valueOf(6));

  public EnumToIAFlag(IEnumValue value) {
    super(value);
  }

  @Override
  public int getReturnType() {
    return IType.TYPE_Integer;
  }
}
